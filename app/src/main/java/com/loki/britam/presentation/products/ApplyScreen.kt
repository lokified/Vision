package com.loki.britam.presentation.products

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.DefaultInput
import com.loki.britam.presentation.products.insurance.InsuranceViewModel
import com.loki.britam.presentation.theme.BritamTheme
import com.loki.britam.util.ContentUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyScreen(
    viewModel: InsuranceViewModel,
    title: String,
    popScreen: () -> Unit
) {

    val content = ContentUtils.applyContentScreen(title)
    val overview = content.header
    val url = content.url
    val benefits = content.benefits

    var openBottomSheet by rememberSaveable{ mutableStateOf(false) }

    val openBrowser = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {}
    )

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(text = title)
                }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                HeaderSection(header = "Overview", modifier = Modifier.padding(vertical = 8.dp))

                Text(text = stringResource(id = overview), modifier = Modifier.padding(vertical = 8.dp))

                if (benefits.isNotEmpty()) {
                    HeaderSection(header = "Benefits")

                    benefits.forEach { benefit ->

                        Row(
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Center,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(10.dp)
                                    .background(
                                        shape = RoundedCornerShape(50.dp),
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                            )

                            Text(text = benefit, fontSize = 14.sp)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(BottomCenter)
                    .padding(16.dp)
            ) {

                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Center
                ) {

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            openBrowser.launch(intent) },

                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.OpenInNew,
                            contentDescription = null
                        )
                        Text(text = "Read More", modifier = Modifier.padding(start = 8.dp))
                    }

                    Button(
                        onClick = { openBottomSheet = true },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {

                        Text(text = "Apply")
                    }
                }
            }
        }

        if (openBottomSheet) {
            BottomSheet(
                viewModel = viewModel,
                onSubmit = {
                    openBottomSheet = false
                },
                onDismiss = {
                    openBottomSheet = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    viewModel: InsuranceViewModel,
    onSubmit: () -> Unit,
    onDismiss: () -> Unit
) {

    val formState = remember { viewModel.applyFormState }
    val email = formState.getState<TextFieldState>("Email")
    val phone = formState.getState<TextFieldState>("PhoneNumber")
    val message = formState.getState<TextFieldState>("Message")

    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {


            DefaultInput(
                label = "Email Address",
                value = email.value,
                onValueChange = { email.change(it) },
                errorMessage = email.errorMessage,
                isError = email.hasError,
            )

            Spacer(modifier = Modifier.height(8.dp))

            DefaultInput(
                label = "Phone Number",
                value = phone.value,
                onValueChange = { phone.change(it) },
                errorMessage = phone.errorMessage,
                isError = phone.hasError,
            )

            Spacer(modifier = Modifier.height(8.dp))

            DefaultInput(
                label = "Message",
                value = message.value,
                onValueChange = { message.change(it) },
                errorMessage = message.errorMessage,
                isError = message.hasError,
                modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (formState.validate()) {
                        onSubmit()
                    }
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(4.dp)
            ) {

                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ApplyScreenPreview() {
    BritamTheme {
        ApplyScreen(title = "Home Insurance", viewModel = InsuranceViewModel()) {

        }
    }
}