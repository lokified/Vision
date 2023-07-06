package com.loki.britam.presentation.new_company

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.google.firebase.firestore.FirebaseFirestoreException
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.company.CompanyStorage
import com.loki.britam.presentation.VisionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewCompanyViewModel @Inject constructor(
    dataStore: DataStoreStorage,
    private val storage: CompanyStorage
): VisionViewModel(dataStore) {

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
        )
    )

    fun saveCompany(company: Company, popScreen: () -> Unit) {
        launchCatching {
            try {
                isLoading.value = true
                storage.addCompany(company)
                isLoading.value = false
                popScreen()
            }
            catch (e: FirebaseFirestoreException) {
                isLoading.value = false
                errorMessage.value = e.message ?: "something went wrong"
            }
        }
    }
}