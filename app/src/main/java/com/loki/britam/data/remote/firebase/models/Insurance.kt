package com.loki.britam.data.remote.firebase.models

import com.google.firebase.firestore.DocumentId

data class Insurance(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val isActive: Boolean = false,
)
