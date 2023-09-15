package com.besirkaraoglu.bankingapp.model

import com.google.gson.annotations.SerializedName


data class RequestResult (
    @SerializedName("ID"                       ) var id                     : Int?    = null,
    @SerializedName("dc_SEHIR"                 ) var city                : String? = null,
    @SerializedName("dc_ILCE"                  ) var district                 : String? = null,
    @SerializedName("dc_BANKA_SUBE"            ) var branch            : String? = null,
    @SerializedName("dc_BANKA_TIPI"            ) var type            : String? = null,
    @SerializedName("dc_BANK_KODU"             ) var code            : String? = null,
    @SerializedName("dc_ADRES_ADI"             ) var addressName             : String? = null,
    @SerializedName("dc_ADRES"                 ) var address                : String? = null,
    @SerializedName("dc_POSTA_KODU"            ) var zipCode            : String? = null,
    @SerializedName("dc_ON_OFF_LINE"           ) var onOffline            : String? = null,
    @SerializedName("dc_ON_OFF_SITE"           ) var onOffSite            : String? = null,
    @SerializedName("dc_BOLGE_KOORDINATORLUGU" ) var regional : String? = null,
    @SerializedName("dc_EN_YAKIM_ATM"          ) var nearestAtm           : String? = null

)
