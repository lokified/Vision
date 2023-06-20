package com.loki.britam.presentation.company

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

class CompanyViewModel: ViewModel() {

    val insurance = listOf(
        CompanyInsurance(
            name = "Britam Biashara",
            isActive = true
        ),
        CompanyInsurance(
            name = "Cyber Insurance",
            isActive = false
        ),
        CompanyInsurance(
            name = "Fire Insurance",
            isActive = true
        )
    )

    val expenses = listOf(
        Expense(
            "March",
            "10,000",
            isProfit = true
        ),

        Expense(
            "April",
            "2,000",
            isProfit = false
        ),

        Expense(
            "May",
            "25,000",
            isProfit = true
        ),
    )

    val newExpenseFormState = FormState(
        fields = listOf(

            TextFieldState(
                name = "Salary",
                validators = listOf()
            ),
            TextFieldState(
                name = "Taxes",
                validators = listOf()
            ),
            TextFieldState(
                name = "Marketing",
                validators = listOf()
            ),
            TextFieldState(
                name = "Profit",
                validators = listOf(
                    Validators.Required()
                )
            ),
        )
    )
}