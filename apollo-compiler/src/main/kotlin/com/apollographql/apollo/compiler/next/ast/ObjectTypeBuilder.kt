package com.apollographql.apollo.compiler.next.ast

import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.apollographql.apollo.compiler.ir.Condition
import com.apollographql.apollo.compiler.ir.Field
import com.apollographql.apollo.compiler.ir.FragmentRef
import com.apollographql.apollo.compiler.ir.InlineFragment
import com.apollographql.apollo.compiler.parser.introspection.IntrospectionSchema
import com.apollographql.apollo.compiler.parser.introspection.resolveType
import com.apollographql.apollo.compiler.singularize

internal class ObjectTypeBuilder(
    private val schema: IntrospectionSchema,
    private val customTypes: CustomTypes,
    private val typesPackageName: String,
    private val fragmentsPackage: String,
    private val irFragments: Map<String, com.apollographql.apollo.compiler.ir.Fragment>,
    private val nestedTypeContainer: ObjectTypeContainerBuilder,
    private val enclosingType: CodeGenerationAst.TypeRef?
) {

  fun buildObjectType(
      typeRef: CodeGenerationAst.TypeRef,
      schemaType: IntrospectionSchema.Type,
      fields: List<Field>,
      abstract: Boolean,
      implements: Set<CodeGenerationAst.TypeRef> = emptySet()
  ): CodeGenerationAst.ObjectType {
    return CodeGenerationAst.ObjectType(
        name = typeRef.name,
        description = schemaType.description ?: "",
        deprecated = false,
        deprecationReason = "",
        fields = fields.mapNotNull { field ->
          // ignore any fields that don't belong to schemaType because they were merged during parsing inline fragments
          // the way how this merge works is required by old codegen
          buildField(
              field = field,
              schemaType = schemaType,
              abstract = abstract
          )
        },
        implements = implements,
        schemaType = schemaType.name,
        kind = CodeGenerationAst.ObjectType.Kind.Interface.takeIf { abstract } ?: CodeGenerationAst.ObjectType.Kind.Object
    )
  }

  private fun buildObjectType(
      name: String,
      schemaType: IntrospectionSchema.Type,
      fields: List<Field>,
      abstract: Boolean,
      implements: List<CodeGenerationAst.TypeRef>,
      singularizeName: Boolean = true
  ): CodeGenerationAst.TypeRef {
    return nestedTypeContainer.registerObjectType(
        typeName = name,
        enclosingType = enclosingType,
        singularizeName = singularizeName
    ) { typeRef ->
      CodeGenerationAst.ObjectType(
          name = typeRef.name,
          description = schemaType.description ?: "",
          deprecated = false,
          deprecationReason = "",
          fields = fields.mapNotNull { field ->
            // ignore any fields that don't belong to schemaType because they were merged during parsing inline fragments
            // the way how this merge works is required by old codegen
            buildField(
                field = field,
                schemaType = schemaType,
                abstract = abstract
            )
          },
          implements = implements.toSet(),
          schemaType = schemaType.name,
          kind = CodeGenerationAst.ObjectType.Kind.Interface.takeIf { abstract } ?: CodeGenerationAst.ObjectType.Kind.Object
      )
    }
  }

  private fun buildField(
      field: Field,
      schemaType: IntrospectionSchema.Type,
      abstract: Boolean
  ): CodeGenerationAst.Field? {
    val schemaField = schemaType.resolveField(field.fieldName) ?: return null
    return CodeGenerationAst.Field(
        name = field.responseName.escapeKotlinReservedWord(),
        schemaName = field.fieldName,
        responseName = field.responseName,
        type = resolveFieldType(
            field = field,
            schemaTypeRef = schemaField.type,
            abstract = abstract
        ),
        description = field.description,
        deprecated = field.isDeprecated,
        deprecationReason = field.deprecationReason,
        arguments = field.args.associate { it.name to it.value },
        conditions = field.normalizedConditions.toSet(),
        override = false
    )
  }

  fun resolveFieldType(
      field: Field,
      schemaTypeRef: IntrospectionSchema.TypeRef,
      abstract: Boolean,
      singularizeName: Boolean = false
  ): CodeGenerationAst.FieldType {
    return when (schemaTypeRef.kind) {
      IntrospectionSchema.Kind.ENUM -> CodeGenerationAst.FieldType.Scalar.Enum(
          nullable = true,
          typeRef = CodeGenerationAst.TypeRef(
              name = schemaTypeRef.name!!.capitalize().escapeKotlinReservedWord(),
              packageName = typesPackageName
          )
      )

      IntrospectionSchema.Kind.INTERFACE,
      IntrospectionSchema.Kind.OBJECT,
      IntrospectionSchema.Kind.UNION -> {
        if (field.inlineFragments.isEmpty() && field.fragmentRefs.isEmpty()) {
          val typeRef = buildObjectType(
              name = field.responseName,
              schemaType = schema.resolveType(schemaTypeRef),
              fields = field.fields,
              abstract = abstract,
              implements = emptyList(),
              singularizeName = singularizeName
          )
          CodeGenerationAst.FieldType.Object(
              nullable = true,
              typeRef = typeRef
          )
        } else {
          field.resolveFieldWithFragmentsType(
              schemaTypeRef = schemaTypeRef,
              singularizeName = singularizeName
          )
        }
      }

      IntrospectionSchema.Kind.SCALAR -> {
        when (schemaTypeRef.name!!.toUpperCase()) {
          "STRING" -> CodeGenerationAst.FieldType.Scalar.String(nullable = true)
          "INT" -> CodeGenerationAst.FieldType.Scalar.Int(nullable = true)
          "BOOLEAN" -> CodeGenerationAst.FieldType.Scalar.Boolean(nullable = true)
          "FLOAT" -> CodeGenerationAst.FieldType.Scalar.Float(nullable = true)
          else -> {
            val customType = checkNotNull(customTypes[schemaTypeRef.name])
            CodeGenerationAst.FieldType.Scalar.Custom(
                nullable = true,
                schemaType = schemaTypeRef.name,
                type = customType.mappedType,
                customEnumType = CodeGenerationAst.TypeRef(
                    name = customType.name,
                    packageName = typesPackageName,
                    enclosingType = CodeGenerationAst.customTypeRef(typesPackageName)
                )
            )
          }
        }
      }

      IntrospectionSchema.Kind.NON_NULL -> resolveFieldType(
          field = field,
          schemaTypeRef = schemaTypeRef.ofType!!,
          abstract = abstract,
          singularizeName = singularizeName
      ).nonNullable()

      IntrospectionSchema.Kind.LIST -> CodeGenerationAst.FieldType.Array(
          nullable = true,
          rawType = resolveFieldType(
              field = field,
              schemaTypeRef = schemaTypeRef.ofType!!,
              abstract = abstract,
              singularizeName = true
          )
      )

      else -> throw IllegalArgumentException("Unsupported selection field type `$schemaTypeRef`")
    }.let { type ->
      if (field.isConditional) type.nullable() else type
    }
  }

  private fun List<Fragment>.buildFragmentPossibleTypes(
      rootFragmentInterfaceType: CodeGenerationAst.TypeRef,
      rootFragmentInterfaceSchemaType: IntrospectionSchema.TypeRef,
      rootFragmentInterfaceFields: List<Field>
  ): Map<String, CodeGenerationAst.TypeRef> {
    val (fragmentOnObjects, fragmentOnInterfaces) = partition { fragment ->
      val typeConditionSchemaType = schema.resolveType(schema.resolveType(fragment.typeCondition))
      typeConditionSchemaType.kind == IntrospectionSchema.Kind.OBJECT
    }

    // build interfaces for all fragments defined on non concrete types if needed
    val fragmentOnInterfaceTypes = fragmentOnInterfaces.map { fragment ->
      fragment to (fragment.interfaceType ?: buildObjectType(
          name = fragment.typeCondition,
          schemaType = schema.resolveType(schema.resolveType(fragment.typeCondition)),
          fields = fragment.fields,
          abstract = true,
          implements = listOf(rootFragmentInterfaceType)
      ))
    }.toMap()

    val fragmentOnObjectsPossibleTypes = fragmentOnObjects.map { it.typeCondition }
    val fragmentOnInterfacesPossibleTypes = fragmentOnInterfaces.flatMap { it.possibleTypes - fragmentOnObjectsPossibleTypes }

    // for all fragments defined on non concrete types build implementation types
    val fragmentImplementationTypes = fragmentOnInterfacesPossibleTypes
        // for each possible type find all possible fragments defined on it
        .map { possibleType -> possibleType to fragmentOnInterfaces.filter { fragment -> fragment.possibleTypes.contains(possibleType) } }
        // group possible types by the same set of fragments
        .fold(emptyMap<List<Fragment>, List<String>>()) { acc, (possibleType, fragment) ->
          acc + (fragment to (acc[fragment]?.plus(possibleType) ?: listOf(possibleType)))
        }
        .flatMap { (fragments, possibleTypes) ->
          // build implementation type for set of fragments
          val implementationType = fragments.buildImplementationType(
              rootFragmentInterfaceType = rootFragmentInterfaceType,
              rootFragmentInterfaceSchemaType = rootFragmentInterfaceSchemaType,
              rootFragmentInterfaceFields = rootFragmentInterfaceFields,
              fragmentInterfaceTypes = fragmentOnInterfaceTypes
          )
          // associate each possible type with implementation type
          possibleTypes.map { it to implementationType }
        }
        .toMap()

    // build object types for all fragments defined on concrete types
    val fragmentOnObjectTypes = fragmentOnObjects.map { fragment ->
      val fragmentOnInterfaceTypesToImplement = fragmentOnInterfaceTypes
          .filter { (fragmentOnInterface, _) -> fragmentOnInterface.possibleTypes.contains(fragment.typeCondition) }
      val fieldsToMerge = fragmentOnInterfaceTypesToImplement.keys.flatMap { it.fields }
      fragment.typeCondition to buildObjectType(
          name = fragment.interfaceType?.name?.let { "${it}Impl" } ?: fragment.typeCondition,
          schemaType = schema.resolveType(schema.resolveType(fragment.typeCondition)),
          fields = fragment.fields.merge(rootFragmentInterfaceFields + fieldsToMerge),
          abstract = false,
          implements = fragmentOnInterfaceTypesToImplement
              .map { (_, superInterfaceTypeRef) -> superInterfaceTypeRef }
              .plus(rootFragmentInterfaceType)
              .plus(listOfNotNull(fragment.interfaceType))
      )
    }

    return fragmentImplementationTypes + fragmentOnObjectTypes
  }

  private fun List<Fragment>.buildImplementationType(
      rootFragmentInterfaceType: CodeGenerationAst.TypeRef,
      rootFragmentInterfaceSchemaType: IntrospectionSchema.TypeRef,
      rootFragmentInterfaceFields: List<Field>,
      fragmentInterfaceTypes: Map<Fragment, CodeGenerationAst.TypeRef>
  ): CodeGenerationAst.TypeRef {
    val typeName = joinToString(separator = "", postfix = "Impl") { fragment ->
      fragment.interfaceType?.name ?: fragment.typeCondition.capitalize().singularize()
    }
    return nestedTypeContainer.registerObjectType(
        typeName = typeName,
        enclosingType = enclosingType
    ) { typeRef ->

      // collect fields from all fragments
      val fields = flatMap { fragment ->
        fragment.fields.map { field ->
          field to schema.resolveType(fragment.typeCondition)
        }
      }.plus(
          rootFragmentInterfaceFields.map { field ->
            field to rootFragmentInterfaceSchemaType
          }
      ).fold(emptyMap<String, CodeGenerationAst.Field>()) { acc, (irField, type) ->
        val existingFragmentField = acc[irField.responseName]
        if (existingFragmentField == null) {
          val field = buildField(
              field = irField,
              schemaType = schema.resolveType(type),
              abstract = false
          )
          if (field == null) acc else acc + (irField.responseName to field)
        } else acc
      }
      CodeGenerationAst.ObjectType(
          name = typeRef.name,
          description = "",
          deprecated = false,
          deprecationReason = "",
          fields = fields.values.toList(),
          implements = mapNotNull { fragment -> fragmentInterfaceTypes[fragment] }.plus(rootFragmentInterfaceType).toSet(),
          schemaType = null,
          kind = CodeGenerationAst.ObjectType.Kind.Object
      )
    }
  }

  private fun Field.resolveFieldWithFragmentsType(
      schemaTypeRef: IntrospectionSchema.TypeRef,
      singularizeName: Boolean
  ): CodeGenerationAst.FieldType.Object {
    val fragmentRootInterfaceType = nestedTypeContainer.registerObjectType(
        typeName = responseName,
        enclosingType = enclosingType,
        singularizeName = singularizeName
    ) { fragmentRootInterfaceType ->
      val fragmentRootSchemaType = schema.resolveType(schemaTypeRef)

      val fragments = inlineFragments
          .toFragments()
          .plus(fragmentRefs.map { fragmentRef -> fragmentRef.toFragment() })

      val possibleImplementations = fragments.buildFragmentPossibleTypes(
            rootFragmentInterfaceType = fragmentRootInterfaceType,
            rootFragmentInterfaceSchemaType = schemaTypeRef,
            rootFragmentInterfaceFields = fields
        )

      val fragmentRootInterfaceImplements: Set<CodeGenerationAst.TypeRef>
      val fragmentRootInterfaceTypeKind: CodeGenerationAst.ObjectType.Kind
      val fragmentRootInterfaceFields: List<CodeGenerationAst.Field>

      // if there is only 1 named fragment defined on object type that is the same as field type
      if (fragmentRootSchemaType.kind == IntrospectionSchema.Kind.OBJECT &&
          fragments.size == 1 &&
          fragments.first().typeCondition == this.type &&
          fragments.first().interfaceType != null) {
        // - make generated root interface to implement named fragment interface
        fragmentRootInterfaceImplements = fragments.first().interfaceType?.let { setOf(it) } ?: emptySet()

        // - skip default implementation generation and use implementation for this fragment as default implementation
        fragmentRootInterfaceTypeKind = CodeGenerationAst.ObjectType.Kind.Fragment(
            defaultImplementation = possibleImplementations.values.first(),
            possibleImplementations = emptyMap()
        )

        // - merge fields from this named fragment
        fragmentRootInterfaceFields = fields.merge(fragments.first().fields).mapNotNull { field ->
          buildField(
              field = field,
              schemaType = fragmentRootSchemaType,
              abstract = true
          )
        }
      } else {
        val defaultImplementationType = buildObjectType(
            name = "${responseName.singularize()}Impl",
            schemaType = schema.resolveType(schemaTypeRef),
            fields = fields,
            abstract = false,
            implements = listOf(fragmentRootInterfaceType),
            singularizeName = singularizeName
        )
        fragmentRootInterfaceImplements = emptySet()
        fragmentRootInterfaceTypeKind = CodeGenerationAst.ObjectType.Kind.Fragment(
            defaultImplementation = defaultImplementationType,
            possibleImplementations = possibleImplementations
        )
        fragmentRootInterfaceFields = fields.mapNotNull { field ->
          buildField(
              field = field,
              schemaType = fragmentRootSchemaType,
              abstract = true
          )
        }
      }

      CodeGenerationAst.ObjectType(
          name = fragmentRootInterfaceType.name,
          description = fragmentRootSchemaType.description ?: "",
          deprecated = false,
          deprecationReason = "",
          fields = fragmentRootInterfaceFields,
          implements = fragmentRootInterfaceImplements,
          schemaType = fragmentRootSchemaType.name,
          kind = fragmentRootInterfaceTypeKind
      )
    }
    return CodeGenerationAst.FieldType.Object(
        nullable = isOptional(),
        typeRef = fragmentRootInterfaceType
    )
  }

  private fun IntrospectionSchema.Type.fields(): List<IntrospectionSchema.Field> {
    return when (this) {
      is IntrospectionSchema.Type.Object -> fields
      is IntrospectionSchema.Type.Interface -> fields
      is IntrospectionSchema.Type.Union -> fields
      else -> emptyList()
    } ?: emptyList()
  }

  private fun IntrospectionSchema.Type.resolveField(name: String): IntrospectionSchema.Field? {
    return when {
      name == "__schema" && this.name == schema.queryType -> IntrospectionSchema.Field(
          name = "__schema",
          description = null,
          isDeprecated = false,
          deprecationReason = null,
          type = IntrospectionSchema.TypeRef(
              kind = IntrospectionSchema.Kind.NON_NULL,
              ofType = IntrospectionSchema.TypeRef(
                  kind = IntrospectionSchema.Kind.OBJECT,
                  name = "__Schema"
              )
          )
      )
      name == "__typename" -> IntrospectionSchema.Field(
          name = "__typename",
          description = null,
          isDeprecated = false,
          deprecationReason = null,
          type = IntrospectionSchema.TypeRef(
              kind = IntrospectionSchema.Kind.NON_NULL,
              ofType = IntrospectionSchema.TypeRef(
                  kind = IntrospectionSchema.Kind.SCALAR,
                  name = "String"
              )
          )
      )
      else -> fields().find { field -> field.name == name }
    }
  }

  private val Field.normalizedConditions: List<CodeGenerationAst.Field.Condition>
    get() {
      return if (isConditional) {
        conditions.filter { it.kind == Condition.Kind.BOOLEAN.rawValue }.map {
          CodeGenerationAst.Field.Condition.Directive(
              variableName = it.variableName,
              inverted = it.inverted
          )
        }
      } else {
        emptyList()
      }
    }

  private fun List<InlineFragment>.toFragments(): List<Fragment> {
    return if (isEmpty()) {
      emptyList()
    } else {
      flatMap { inlineFragment ->
        listOf(
            Fragment(
                typeCondition = inlineFragment.typeCondition,
                possibleTypes = inlineFragment.possibleTypes,
                description = inlineFragment.description,
                fields = inlineFragment.fields,
                fragments = inlineFragment.fragments,
                interfaceType = null
            )
        ) + inlineFragment.fragments.map { it.toFragment() }
      } + flatMap { fragment -> fragment.inlineFragments }.toFragments()
    }
  }

  private fun FragmentRef.toFragment(): Fragment {
    val fragment = requireNotNull(irFragments[name]) {
      "Unknown fragment `${name}` reference"
    }
    return Fragment(
        typeCondition = fragment.typeCondition,
        possibleTypes = fragment.possibleTypes,
        description = fragment.description,
        fields = fragment.fields,
        fragments = fragment.fragmentRefs,
        interfaceType = CodeGenerationAst.TypeRef(
            name = fragment.fragmentName.capitalize().escapeKotlinReservedWord(),
            packageName = fragmentsPackage
        )
    )
  }

  private data class Fragment(
      val typeCondition: String,
      val possibleTypes: List<String>,
      val description: String,
      val fields: List<Field>,
      val fragments: List<FragmentRef>,
      val interfaceType: CodeGenerationAst.TypeRef?
  )
}