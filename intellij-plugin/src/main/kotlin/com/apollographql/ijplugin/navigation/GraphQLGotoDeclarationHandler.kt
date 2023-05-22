package com.apollographql.ijplugin.navigation

import com.apollographql.ijplugin.project.apolloProjectService
import com.apollographql.ijplugin.util.resolveKtName
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtNameReferenceExpression

/**
 * Allows to navigate to the corresponding GraphQL definition when middle-clicking/cmd-clicking/cmd-b on an Apollo operation/fragment/enum
 * reference, or model field.
 */
class GraphQLGotoDeclarationHandler : GotoDeclarationHandler {
  override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<PsiElement>? {
    if (sourceElement == null) return null
    if (!sourceElement.project.apolloProjectService.isApolloKotlin3Project) return null

    val nameReferenceExpression = sourceElement.parent as? KtNameReferenceExpression ?: return null
    val psiLeaf = PsiTreeUtil.getDeepestFirst(sourceElement)

    val graphQLDefinitions = when {
      nameReferenceExpression.isApolloOperationOrFragmentReference() -> {
        findOperationOrFragmentGraphQLDefinitions(sourceElement.project, psiLeaf.text)
      }

      nameReferenceExpression.isApolloModelField() -> {
        findGraphQLElements(nameReferenceExpression)
      }

      nameReferenceExpression.isApolloEnumClassReference() -> {
        findEnumTypeGraphQLDefinitions(sourceElement.project, psiLeaf.text)
      }

      nameReferenceExpression.isApolloEnumValueReference() -> {
        findEnumValueGraphQLDefinitions(nameReferenceExpression)
      }

      else -> return null
    }

    return buildList {
      // Add GraphQL definition(s)
      addAll(graphQLDefinitions)

      // Add the original referred to element
      val resolvedElement = nameReferenceExpression.resolveKtName()
      if (resolvedElement != null) {
        add(resolvedElement)
      }
    }.toTypedArray()
  }
}