package co.com.inter.ui.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import co.com.inter.R
import co.com.inter.domain.model.Table
import co.com.inter.ui.component.TopAppBarComponent
import kotlinx.coroutines.flow.flowOf

@Composable
fun TableScreen(
    tableViewModel: TableViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val tables = tableViewModel.tables.collectAsLazyPagingItems()

    TableContent(tables) {
        navController.popBackStack()
    }
}

@Composable
private fun TableContent(
    tables: LazyPagingItems<Table>,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBarComponent(
                title = stringResource(R.string.label_table),
                navigationIcon = R.drawable.ic_arrow_left
            ) { onBack() }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = tables.itemCount,
                key = { index -> tables[index]?.tableName ?: index }
            ) { index ->
                tables[index]?.let { table ->
                    TableItemCard(table)
                }
            }

            item {
                when (tables.loadState.append) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(Modifier.padding(16.dp))
                    }

                    is LoadState.Error -> {
                        Text("Error cargando más")
                    }

                    else -> Unit
                }
            }
        }
    }
}

@Composable
private fun TableItemCard(table: Table) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_table),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = table.tableName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

            Column {
                Text(
                    text = stringResource(R.string.label_query),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = table.creationQuery,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = table.dateUpdated,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TableContentPreview() {
    val mockTables = listOf(
        Table(
            batchSize = 1,
            error = null,
            dateUpdated = "2024-02-15T15:46:45.777",
            filter = "",
            methodApp = null,
            tableName = "AbreviaturasDireccion",
            fieldNumber = 1,
            pk = "AD_id",
            creationQuery = "\"CREATE TABLE AbreviaturasDireccion( \\r\\nAD_id int NOT NULL,\\r\\nAD_abreviatura nvarchar (50) NOT NULL,\\r\\nAnchor varbinary (8) NOT NULL,PRIMARY KEY (AD_id))\\r\\n\""
        )
    )

    TableContent(flowOf(PagingData.from(mockTables)).collectAsLazyPagingItems()) {}
}