package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.UiStrings
import com.example.model.JobFilter
import com.example.ui.ExternalActions
import com.example.ui.components.DemoNoticeBanner
import com.example.ui.components.EmptyStateView
import com.example.ui.components.LanguageTogglePill
import com.example.ui.components.PortalItemCard
import com.example.ui.components.SahayakSearchBar
import com.example.ui.components.TricolorAccentBar
import com.example.viewmodel.SahayakUiState

@Composable
fun JobsScreen(
    uiState: SahayakUiState,
    onToggleLanguage: () -> Unit,
    onSearchChange: (String) -> Unit,
    onFilterChange: (JobFilter) -> Unit,
    onResetFilters: () -> Unit,
    onItemClick: (String) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language = uiState.language
    val strings = UiStrings.get(language)
    val jobs = uiState.filteredJobs

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("jobs_screen_list"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = strings.navJobs,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    LanguageTogglePill(
                        currentLanguage = language,
                        onToggle = onToggleLanguage
                    )
                }
                TricolorAccentBar()
            }
        }

        item {
            SahayakSearchBar(
                query = uiState.jobsSearchQuery,
                onQueryChange = onSearchChange,
                placeholder = strings.searchPlaceholderJobs,
                testTagName = "jobs_search_input"
            )
        }

        item {
            val options = listOf(
                JobFilter.ALL to strings.allLabel,
                JobFilter.GOVERNMENT to strings.govtJobsLabel,
                JobFilter.PRIVATE to strings.privateJobsLabel
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(options) { (filter, label) ->
                    val selected = uiState.jobFilter == filter
                    FilterChip(
                        selected = selected,
                        onClick = { onFilterChange(filter) },
                        label = { Text(label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.testTag("job_filter_${filter.name.lowercase()}")
                    )
                }
            }
        }

        item {
            DemoNoticeBanner(strings = strings)
        }

        if (jobs.isEmpty()) {
            item {
                EmptyStateView(
                    title = strings.noResultsTitle,
                    subtitle = strings.noResultsSubtitle,
                    actionLabel = strings.clearFiltersLabel,
                    onActionClick = onResetFilters
                )
            }
        } else {
            items(jobs, key = { it.id }) { item ->
                PortalItemCard(
                    item = item,
                    language = language,
                    isBookmarked = item.id in uiState.preferences.bookmarkedIds,
                    onCardClick = { onItemClick(item.id) },
                    onBookmarkClick = { onBookmarkToggle(item.id) },
                    onOpenOfficialClick = {
                        ExternalActions.openOfficialUrl(context, item.officialUrl, strings.openLinkError)
                    }
                )
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }
    }
}
