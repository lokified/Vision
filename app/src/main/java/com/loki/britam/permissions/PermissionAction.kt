package com.loki.britam.permissions

sealed class PermissionAction {
    object PermissionGranted: PermissionAction()
    object PermissionDenied: PermissionAction()
}