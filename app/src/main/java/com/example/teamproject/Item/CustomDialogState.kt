package com.example.teamproject.Item

data class CustomAlertDialogState(
    val title: String = "어디로 가시나요?",
    val onClickToFloor: () -> Unit = {},
    val onClickToBottom: () -> Unit = {},
    val onClickCancel : () -> Unit = {}
)
