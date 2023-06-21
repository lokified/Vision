package com.loki.britam.presentation.products.insurance

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

class InsuranceViewModel: ViewModel() {

    val applyFormState = FormState(
        fields = listOf(

            TextFieldState(
                name = "Email",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "PhoneNumber",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Message",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )
}