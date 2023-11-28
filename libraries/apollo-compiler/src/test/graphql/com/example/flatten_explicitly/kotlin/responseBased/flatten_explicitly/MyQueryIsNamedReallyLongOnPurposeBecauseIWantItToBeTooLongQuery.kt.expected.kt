//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '$VERSION'.
//
package com.example.flatten_explicitly

import com.apollographql.apollo3.annotations.ApolloAdaptableWith
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.obj
import com.example.flatten_explicitly.adapter.MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_ResponseAdapter
import com.example.flatten_explicitly.adapter.MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_VariablesAdapter
import com.example.flatten_explicitly.fragment.Thisisauserbutwecallitsomethingmuchlarger
import com.example.flatten_explicitly.fragment.UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
import com.example.flatten_explicitly.fragment.UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
import com.example.flatten_explicitly.fragment.UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
import com.example.flatten_explicitly.fragment.UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
import com.example.flatten_explicitly.selections.MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuerySelections
import com.example.flatten_explicitly.type.PageQueryOptions
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import com.example.flatten_explicitly.type.Query as CompiledQuery

public data class MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery(
  public val options: Optional<PageQueryOptions?> = Optional.Absent,
) : Query<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data> {
  override fun id(): String = OPERATION_ID

  override fun document(): String = OPERATION_DOCUMENT

  override fun name(): String = OPERATION_NAME

  override fun serializeVariables(
    writer: JsonWriter,
    customScalarAdapters: CustomScalarAdapters,
    withDefaultValues: Boolean,
  ) {
    MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_VariablesAdapter.serializeVariables(writer,
        this, customScalarAdapters, withDefaultValues)
  }

  override fun adapter(): Adapter<Data> =
      MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_ResponseAdapter.Data.obj()

  override fun rootField(): CompiledField = CompiledField.Builder(
    name = "data",
    type = CompiledQuery.type
  )
  .selections(selections = MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuerySelections.__root)
  .build()

  @ApolloAdaptableWith(MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_ResponseAdapter.Data::class)
  public data class Data(
    public val postspagearealmostlongenoughtobecomicals: Postspagearealmostlongenoughtobecomicals?,
  ) : Query.Data {
    public data class Postspagearealmostlongenoughtobecomicals(
      override val __typename: String,
      override val postisthemaintypebutweneedtomakethenamemuchlargerdata:
          List<Postisthemaintypebutweneedtomakethenamemuchlargerdatum?>?,
      override val meta: Meta?,
      override val paginationlinksbecauseiwantittobetoolonglinks:
          Paginationlinksbecauseiwantittobetoolonglinks?,
    ) : UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong {
      public data class Meta(
        override val totalCount: Int?,
      ) : UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Meta

      public data class Paginationlinksbecauseiwantittobetoolonglinks(
        override val __typename: String,
        override val pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast:
            Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast?,
      ) : UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong,
          UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Paginationlinksbecauseiwantittobetoolonglinks
          {
        public data class Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast(
          override val __typename: String,
          override val page: Int?,
          override val limit: Int?,
        ) : UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong,
            UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast,
            UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast
            {
          public companion object {
            @Suppress("USELESS_CAST")
            public
                fun Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast.unnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong():
                UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong?
                = this as?
                UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
          }
        }

        public companion object {
          @Suppress("USELESS_CAST")
          public
              fun Paginationlinksbecauseiwantittobetoolonglinks.unnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong():
              UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong?
              = this as?
              UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
        }
      }

      public companion object {
        @Suppress("USELESS_CAST")
        public
            fun Postspagearealmostlongenoughtobecomicals.unnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong():
            UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong? =
            this as?
            UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
      }
    }
  }

  public data class Postisthemaintypebutweneedtomakethenamemuchlargerdatum(
    override val __typename: String,
    override val id: String?,
    override val title: String?,
    override val thisisauserbutwecallitsomethingmuchlarger:
        Thisisauserbutwecallitsomethingmuchlarger?,
  ) : UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong,
      UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Postisthemaintypebutweneedtomakethenamemuchlargerdatum
      {
    public data class Thisisauserbutwecallitsomethingmuchlarger(
      override val __typename: String,
      override val id: String?,
      override val name: String?,
      override val whereintheworldiscarmensandiegoaddress: Whereintheworldiscarmensandiegoaddress?,
    ) :
        UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Thisisauserbutwecallitsomethingmuchlarger,
        com.example.flatten_explicitly.fragment.Thisisauserbutwecallitsomethingmuchlarger

    public companion object {
      @Suppress("USELESS_CAST")
      public
          fun Postisthemaintypebutweneedtomakethenamemuchlargerdatum.unnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong():
          UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong? =
          this as?
          UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
    }
  }

  public data class Whereintheworldiscarmensandiegoaddress(
    override val __typename: String,
    override val city: String?,
    override val geoisnotagembutageographiclocationinthiscase:
        Geoisnotagembutageographiclocationinthiscase?,
  ) :
      UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Thisisauserbutwecallitsomethingmuchlarger.Whereintheworldiscarmensandiegoaddress,
      Thisisauserbutwecallitsomethingmuchlarger.Whereintheworldiscarmensandiegoaddress {
    public data class Geoisnotagembutageographiclocationinthiscase(
      override val __typename: String,
      override val lat: Double?,
      override val lng: Double?,
    ) :
        UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong.Thisisauserbutwecallitsomethingmuchlarger.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase,
        Thisisauserbutwecallitsomethingmuchlarger.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase
  }

  public companion object {
    public const val OPERATION_ID: String =
        "5cfb4ddb25ce0071db12da2d1e551b680db7a93a998cca4ac73243f5277ec7a7"

    /**
     * The minimized GraphQL document being sent to the server to save a few bytes.
     * The un-minimized version is:
     *
     * ```
     * query MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLong($options: PageQueryOptions)
     * {
     *   postspagearealmostlongenoughtobecomicals(options: $options) {
     *     __typename
     *     ...UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
     *   }
     * }
     *
     * fragment
     * UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on
     * PostIsTheMainTypeButWeNeedToMakeTheNameMuchLarger {
     *   __typename
     *   id
     *   title
     *   thisisauserbutwecallitsomethingmuchlarger {
     *     __typename
     *     id
     *     name
     *     whereintheworldiscarmensandiegoaddress {
     *       __typename
     *       city
     *       geoisnotagembutageographiclocationinthiscase {
     *         __typename
     *         lat
     *         lng
     *       }
     *     }
     *   }
     * }
     *
     * fragment
     * UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on
     * PageLimitPairPaginationLinksBecauseIWantItToBeTooLongAsWell {
     *   __typename
     *   page
     *   limit
     * }
     *
     * fragment
     * UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on
     * PaginationLinksBecauseIWantItToBeTooLong {
     *   __typename
     *   pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast {
     *     __typename
     *     ...UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
     *   }
     * }
     *
     * fragment UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
     * on PostsPageAreAlmostLongEnoughToBeComical {
     *   __typename
     *   postisthemaintypebutweneedtomakethenamemuchlargerdata {
     *     __typename
     *     ...UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
     *   }
     *   meta {
     *     totalCount
     *   }
     *   paginationlinksbecauseiwantittobetoolonglinks {
     *     __typename
     *    
     * ...UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong
     *   }
     * }
     * ```
     */
    public val OPERATION_DOCUMENT: String
      get() =
          "query MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLong(${'$'}options: PageQueryOptions) { postspagearealmostlongenoughtobecomicals(options: ${'$'}options) { __typename ...UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong } }  fragment UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on PostIsTheMainTypeButWeNeedToMakeTheNameMuchLarger { __typename id title thisisauserbutwecallitsomethingmuchlarger { __typename id name whereintheworldiscarmensandiegoaddress { __typename city geoisnotagembutageographiclocationinthiscase { __typename lat lng } } } }  fragment UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on PageLimitPairPaginationLinksBecauseIWantItToBeTooLongAsWell { __typename page limit }  fragment UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on PaginationLinksBecauseIWantItToBeTooLong { __typename pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast { __typename ...UnnecessaryFragmentForPageLimitPairWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong } }  fragment UnnecessaryFragmentForPostsPageWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong on PostsPageAreAlmostLongEnoughToBeComical { __typename postisthemaintypebutweneedtomakethenamemuchlargerdata { __typename ...UnnecessaryFragmentForPostPageDataWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong } meta { totalCount } paginationlinksbecauseiwantittobetoolonglinks { __typename ...UnnecessaryFragmentForPaginationLinksWithAnExtraBitOfCharactersBecauseIWantItToBeTooLong } }"

    public const val OPERATION_NAME: String =
        "MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLong"
  }
}
