package com.demo.wiprodemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.wiprodemo.domain.module.User
import com.demo.wiprodemo.presentation.ui.theme.AndroidMVICleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidMVICleanArchitectureTheme {
                val viewModel = viewModel(ComposeViewModel::class)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        val uiState = viewModel.userUiState.collectAsStateWithLifecycle()
                        when (val value = uiState.value) {
                            is UiState.Error -> {
                                ShowError(value.message)
                            }

                            UiState.Loading -> {
                                CircularProgressIndicator()
                            }

                            is UiState.Success -> {
                                UserList(value.users)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserList(
    users: List<User>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) { user ->
            Card(modifier = Modifier.fillMaxWidth()) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(text = user.email)
                }
            }
        }
    }
}


@Composable
fun ShowError(error: String) {
    Text(
        text = "Error $error!"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMVICleanArchitectureTheme {
        ShowError("Android")
    }
}