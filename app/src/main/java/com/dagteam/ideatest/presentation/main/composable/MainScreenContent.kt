package com.dagteam.ideatest.presentation.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagteam.ideatest.R
import com.dagteam.ideatest.domain.Phone
import com.dagteam.ideatest.presentation.main.mvi.DialogState
import com.dagteam.ideatest.presentation.main.mvi.MainIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    query: String,
    phones: List<Phone>,
    onIntent: (MainIntent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.top_app_bar_title),
                            fontSize = 22.sp,
                            color = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = { query ->
                    onIntent(MainIntent.SearchQuery(query))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.search_placeholder)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_placeholder)
                    )
                }
            )
            PhoneList(
                phones = phones,
                onIntent = onIntent,
            )
        }
    }
}

@Composable
fun PhoneList(
    modifier: Modifier = Modifier,
    phones: List<Phone>,
    onIntent: (MainIntent) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = phones,
            key = Phone::id,
        ) { phone ->
            PhoneItem(
                phone = phone,
                onDeleteClick = {
                    onIntent(MainIntent.ShowDialog(DialogState.Delete(phone)))
                },
                onEditClick = {
                    onIntent(MainIntent.ShowDialog(DialogState.Edit(phone)))
                },
            )
        }
    }
}

@Composable
fun PhoneItem(
    phone: Phone,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    Card(
        onClick = { /* no op */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = phone.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onEditClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            tint = Color.Blue,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = onDeleteClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }
                }
            }
            ListTags(phone.tags)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TitleWithSubtitle(
                    title = stringResource(R.string.subtitle_card_on_warehouse),
                    subtitle = phone.amount.toString(),
                )
                TitleWithSubtitle(
                    title = stringResource(R.string.subtitle_card_date_add),
                    subtitle = phone.time,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListTags(tags: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { tag ->
            AssistChip(
                onClick = { /* no op */ },
                label = {
                    Text(
                        text = tag
                    )
                }
            )
        }
    }
}

@Composable
fun TitleWithSubtitle(title: String, subtitle: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = subtitle
        )
    }
}