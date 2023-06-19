package com.loki.britam.presentation.new_company

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

class NewCompanyViewModel: ViewModel() {

    val newCompanyFormState = FormState(
        fields = listOf(

            TextFieldState(
                name = "BusinessName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "BusinessType",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "BusinessLocation",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "BusinessDescription",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Estimate",
                validators = listOf(
                    Validators.Required()
                )
            ),
        )
    )
}