package com.mobile.cleancase.domain.model

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("appVersion") var appVersion: String?
)
