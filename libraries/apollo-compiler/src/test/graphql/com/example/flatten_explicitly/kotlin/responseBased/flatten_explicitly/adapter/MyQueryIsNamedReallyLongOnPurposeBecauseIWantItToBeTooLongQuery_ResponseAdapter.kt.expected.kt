//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '$VERSION'.
//
package com.example.flatten_explicitly.adapter

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.NullableDoubleAdapter
import com.apollographql.apollo3.api.NullableIntAdapter
import com.apollographql.apollo3.api.NullableStringAdapter
import com.apollographql.apollo3.api.StringAdapter
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.list
import com.apollographql.apollo3.api.missingField
import com.apollographql.apollo3.api.nullable
import com.apollographql.apollo3.api.obj
import com.example.flatten_explicitly.MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.List

public object MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery_ResponseAdapter {
  public object Data : Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data>
      {
    public val RESPONSE_NAMES: List<String> = listOf("postspagearealmostlongenoughtobecomicals")

    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
        MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data {
      var _postspagearealmostlongenoughtobecomicals: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals? = null

      while (true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> _postspagearealmostlongenoughtobecomicals = Postspagearealmostlongenoughtobecomicals.obj().nullable().fromJson(reader, customScalarAdapters)
          else -> break
        }
      }

      return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data(
        postspagearealmostlongenoughtobecomicals = _postspagearealmostlongenoughtobecomicals
      )
    }

    override fun toJson(
      writer: JsonWriter,
      customScalarAdapters: CustomScalarAdapters,
      `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data,
    ) {
      writer.name("postspagearealmostlongenoughtobecomicals")
      Postspagearealmostlongenoughtobecomicals.obj().nullable().toJson(writer, customScalarAdapters,
          value.postspagearealmostlongenoughtobecomicals)
    }

    private object Postspagearealmostlongenoughtobecomicals :
        Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals>
        {
      public val RESPONSE_NAMES: List<String> = listOf("__typename",
          "postisthemaintypebutweneedtomakethenamemuchlargerdata", "meta",
          "paginationlinksbecauseiwantittobetoolonglinks")

      override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
          MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals {
        var __typename: String? = null
        var _postisthemaintypebutweneedtomakethenamemuchlargerdata: List<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum?>? = null
        var _meta: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Meta? = null
        var _paginationlinksbecauseiwantittobetoolonglinks: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks? = null

        while (true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
            1 -> _postisthemaintypebutweneedtomakethenamemuchlargerdata = Postisthemaintypebutweneedtomakethenamemuchlargerdatum.obj().nullable().list().nullable().fromJson(reader, customScalarAdapters)
            2 -> _meta = Meta.obj().nullable().fromJson(reader, customScalarAdapters)
            3 -> _paginationlinksbecauseiwantittobetoolonglinks = Paginationlinksbecauseiwantittobetoolonglinks.obj().nullable().fromJson(reader, customScalarAdapters)
            else -> break
          }
        }

        return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals(
          __typename = __typename ?: missingField(reader, "__typename"),
          postisthemaintypebutweneedtomakethenamemuchlargerdata = _postisthemaintypebutweneedtomakethenamemuchlargerdata,
          meta = _meta,
          paginationlinksbecauseiwantittobetoolonglinks = _paginationlinksbecauseiwantittobetoolonglinks
        )
      }

      override fun toJson(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals,
      ) {
        writer.name("__typename")
        StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

        writer.name("postisthemaintypebutweneedtomakethenamemuchlargerdata")
        Postisthemaintypebutweneedtomakethenamemuchlargerdatum.obj().nullable().list().nullable().toJson(writer,
            customScalarAdapters, value.postisthemaintypebutweneedtomakethenamemuchlargerdata)

        writer.name("meta")
        Meta.obj().nullable().toJson(writer, customScalarAdapters, value.meta)

        writer.name("paginationlinksbecauseiwantittobetoolonglinks")
        Paginationlinksbecauseiwantittobetoolonglinks.obj().nullable().toJson(writer,
            customScalarAdapters, value.paginationlinksbecauseiwantittobetoolonglinks)
      }

      private object Meta :
          Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Meta>
          {
        public val RESPONSE_NAMES: List<String> = listOf("totalCount")

        override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
            MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Meta {
          var _totalCount: Int? = null

          while (true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> _totalCount = NullableIntAdapter.fromJson(reader, customScalarAdapters)
              else -> break
            }
          }

          return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Meta(
            totalCount = _totalCount
          )
        }

        override fun toJson(
          writer: JsonWriter,
          customScalarAdapters: CustomScalarAdapters,
          `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Meta,
        ) {
          writer.name("totalCount")
          NullableIntAdapter.toJson(writer, customScalarAdapters, value.totalCount)
        }
      }

      private object Paginationlinksbecauseiwantittobetoolonglinks :
          Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks>
          {
        public val RESPONSE_NAMES: List<String> = listOf("__typename",
            "pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast")

        override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
            MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks {
          var __typename: String? = null
          var _pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast? = null

          while (true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
              1 -> _pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast = Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast.obj().nullable().fromJson(reader, customScalarAdapters)
              else -> break
            }
          }

          return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks(
            __typename = __typename ?: missingField(reader, "__typename"),
            pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast = _pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast
          )
        }

        override fun toJson(
          writer: JsonWriter,
          customScalarAdapters: CustomScalarAdapters,
          `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks,
        ) {
          writer.name("__typename")
          StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

          writer.name("pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast")
          Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast.obj().nullable().toJson(writer,
              customScalarAdapters,
              value.pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast)
        }

        private object Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast :
            Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast>
            {
          public val RESPONSE_NAMES: List<String> = listOf("__typename", "page", "limit")

          override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
              MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast {
            var __typename: String? = null
            var _page: Int? = null
            var _limit: Int? = null

            while (true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
                1 -> _page = NullableIntAdapter.fromJson(reader, customScalarAdapters)
                2 -> _limit = NullableIntAdapter.fromJson(reader, customScalarAdapters)
                else -> break
              }
            }

            return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast(
              __typename = __typename ?: missingField(reader, "__typename"),
              page = _page,
              limit = _limit
            )
          }

          override fun toJson(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters,
            `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Data.Postspagearealmostlongenoughtobecomicals.Paginationlinksbecauseiwantittobetoolonglinks.Pagelimitpairpaginationlinksbecauseiwantittobetoolongaswelllast,
          ) {
            writer.name("__typename")
            StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

            writer.name("page")
            NullableIntAdapter.toJson(writer, customScalarAdapters, value.page)

            writer.name("limit")
            NullableIntAdapter.toJson(writer, customScalarAdapters, value.limit)
          }
        }
      }
    }
  }

  public object Postisthemaintypebutweneedtomakethenamemuchlargerdatum :
      Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum>
      {
    public val RESPONSE_NAMES: List<String> = listOf("__typename", "id", "title",
        "thisisauserbutwecallitsomethingmuchlarger")

    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
        MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum {
      var __typename: String? = null
      var _id: String? = null
      var _title: String? = null
      var _thisisauserbutwecallitsomethingmuchlarger: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum.Thisisauserbutwecallitsomethingmuchlarger? = null

      while (true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
          1 -> _id = NullableStringAdapter.fromJson(reader, customScalarAdapters)
          2 -> _title = NullableStringAdapter.fromJson(reader, customScalarAdapters)
          3 -> _thisisauserbutwecallitsomethingmuchlarger = Thisisauserbutwecallitsomethingmuchlarger.obj().nullable().fromJson(reader, customScalarAdapters)
          else -> break
        }
      }

      return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum(
        __typename = __typename ?: missingField(reader, "__typename"),
        id = _id,
        title = _title,
        thisisauserbutwecallitsomethingmuchlarger = _thisisauserbutwecallitsomethingmuchlarger
      )
    }

    override fun toJson(
      writer: JsonWriter,
      customScalarAdapters: CustomScalarAdapters,
      `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum,
    ) {
      writer.name("__typename")
      StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

      writer.name("id")
      NullableStringAdapter.toJson(writer, customScalarAdapters, value.id)

      writer.name("title")
      NullableStringAdapter.toJson(writer, customScalarAdapters, value.title)

      writer.name("thisisauserbutwecallitsomethingmuchlarger")
      Thisisauserbutwecallitsomethingmuchlarger.obj().nullable().toJson(writer,
          customScalarAdapters, value.thisisauserbutwecallitsomethingmuchlarger)
    }

    private object Thisisauserbutwecallitsomethingmuchlarger :
        Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum.Thisisauserbutwecallitsomethingmuchlarger>
        {
      public val RESPONSE_NAMES: List<String> = listOf("__typename", "id", "name",
          "whereintheworldiscarmensandiegoaddress")

      override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
          MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum.Thisisauserbutwecallitsomethingmuchlarger {
        var __typename: String? = null
        var _id: String? = null
        var _name: String? = null
        var _whereintheworldiscarmensandiegoaddress: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress? = null

        while (true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
            1 -> _id = NullableStringAdapter.fromJson(reader, customScalarAdapters)
            2 -> _name = NullableStringAdapter.fromJson(reader, customScalarAdapters)
            3 -> _whereintheworldiscarmensandiegoaddress = Whereintheworldiscarmensandiegoaddress.obj().nullable().fromJson(reader, customScalarAdapters)
            else -> break
          }
        }

        return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum.Thisisauserbutwecallitsomethingmuchlarger(
          __typename = __typename ?: missingField(reader, "__typename"),
          id = _id,
          name = _name,
          whereintheworldiscarmensandiegoaddress = _whereintheworldiscarmensandiegoaddress
        )
      }

      override fun toJson(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Postisthemaintypebutweneedtomakethenamemuchlargerdatum.Thisisauserbutwecallitsomethingmuchlarger,
      ) {
        writer.name("__typename")
        StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

        writer.name("id")
        NullableStringAdapter.toJson(writer, customScalarAdapters, value.id)

        writer.name("name")
        NullableStringAdapter.toJson(writer, customScalarAdapters, value.name)

        writer.name("whereintheworldiscarmensandiegoaddress")
        Whereintheworldiscarmensandiegoaddress.obj().nullable().toJson(writer, customScalarAdapters,
            value.whereintheworldiscarmensandiegoaddress)
      }
    }
  }

  public object Whereintheworldiscarmensandiegoaddress :
      Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress>
      {
    public val RESPONSE_NAMES: List<String> = listOf("__typename", "city",
        "geoisnotagembutageographiclocationinthiscase")

    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
        MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress {
      var __typename: String? = null
      var _city: String? = null
      var _geoisnotagembutageographiclocationinthiscase: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase? = null

      while (true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
          1 -> _city = NullableStringAdapter.fromJson(reader, customScalarAdapters)
          2 -> _geoisnotagembutageographiclocationinthiscase = Geoisnotagembutageographiclocationinthiscase.obj().nullable().fromJson(reader, customScalarAdapters)
          else -> break
        }
      }

      return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress(
        __typename = __typename ?: missingField(reader, "__typename"),
        city = _city,
        geoisnotagembutageographiclocationinthiscase = _geoisnotagembutageographiclocationinthiscase
      )
    }

    override fun toJson(
      writer: JsonWriter,
      customScalarAdapters: CustomScalarAdapters,
      `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress,
    ) {
      writer.name("__typename")
      StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

      writer.name("city")
      NullableStringAdapter.toJson(writer, customScalarAdapters, value.city)

      writer.name("geoisnotagembutageographiclocationinthiscase")
      Geoisnotagembutageographiclocationinthiscase.obj().nullable().toJson(writer,
          customScalarAdapters, value.geoisnotagembutageographiclocationinthiscase)
    }

    private object Geoisnotagembutageographiclocationinthiscase :
        Adapter<MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase>
        {
      public val RESPONSE_NAMES: List<String> = listOf("__typename", "lat", "lng")

      override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
          MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase {
        var __typename: String? = null
        var _lat: Double? = null
        var _lng: Double? = null

        while (true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = StringAdapter.fromJson(reader, customScalarAdapters)
            1 -> _lat = NullableDoubleAdapter.fromJson(reader, customScalarAdapters)
            2 -> _lng = NullableDoubleAdapter.fromJson(reader, customScalarAdapters)
            else -> break
          }
        }

        return MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase(
          __typename = __typename ?: missingField(reader, "__typename"),
          lat = _lat,
          lng = _lng
        )
      }

      override fun toJson(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        `value`: MyQueryIsNamedReallyLongOnPurposeBecauseIWantItToBeTooLongQuery.Whereintheworldiscarmensandiegoaddress.Geoisnotagembutageographiclocationinthiscase,
      ) {
        writer.name("__typename")
        StringAdapter.toJson(writer, customScalarAdapters, value.__typename)

        writer.name("lat")
        NullableDoubleAdapter.toJson(writer, customScalarAdapters, value.lat)

        writer.name("lng")
        NullableDoubleAdapter.toJson(writer, customScalarAdapters, value.lng)
      }
    }
  }
}
