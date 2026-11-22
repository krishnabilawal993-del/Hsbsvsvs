package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
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
import com.example.model.AdmissionStream
import com.example.model.EducationFilter
import com.example.model.ScholarshipCategory
import com.example.ui.ExternalActions
import com.example.ui.components.DemoNoticeBanner
import com.example.ui.components.EmptyStateView
import com.example.ui.components.LanguageTogglePill
import com.example.ui.components.PortalItemCard
import com.example.ui.components.SahayakSearchBar
import com.example.ui.components.TricolorAccentBar
import com.example.viewmodel.SahayakUiState

@Composable
fun EducationScreen(
    uiState: SahayakUiState,
    onToggleLanguage: () -> Unit,
    onSearchChange: (String) -> Unit,
    onEducationFilterChange: (EducationFilter) -> Unit,
    onScholarshipCategoryChange: (ScholarshipCategory) -> Unit,
    onAdmissionStreamChange: (AdmissionStream) -> Unit,
    onResetFilters: () -> Unit,
    onOpenFormHelp: () -> Unit,
    onItemClick: (String) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language = uiState.language
    val strings = UiStrings.get(language)
    val items = uiState.filteredEducation

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("education_screen_list"),
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
                        text = strings.navEducation,
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
                query = uiState.educationSearchQuery,
                onQueryChange = onSearchChange,
                placeholder = strings.searchPlaceholderEducation,
                testTagName = "education_search_input"
            )
        }

        // Primary Education section tabs: All | Scholarships | Admissions | Merit Lists
        item {
            val mainFilters = listOf(
                EducationFilter.ALL to strings.allLabel,
                EducationFilter.SCHOLARSHIPS to strings.scholarshipsLabel,
                EducationFilter.ADMISSIONS to strings.admissionsLabel,
                EducationFilter.MERIT_LISTS to strings.meritListsLabel
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(mainFilters) { (filter, label) ->
                    val selected = uiState.educationFilter == filter
                    FilterChip(
                        selected = selected,
                        onClick = { onEducationFilterChange(filter) },
                        label = { Text(label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.testTag("edu_filter_${filter.name.lowercase()}")
                    )
                }
            }
        }

        // Dynamic sub-filters for Scholarships (ST, SC, OBC, General)
        if (uiState.educationFilter == EducationFilter.SCHOLARSHIPS) {
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ScholarshipCategory.entries) { cat ->
                        val selected = uiState.scholarshipCategoryFilter == cat
                        FilterChip(
                            selected = selected,
                            onClick = { onScholarshipCategoryChange(cat) },
                            label = { Text(UiStrings.scholarshipCategoryLabel(cat, language)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                            ),
                            modifier = Modifier.testTag("scholarship_cat_${cat.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Dynamic sub-filters for College Admissions (BA/BCom/BSc, ITI, PTC, Diploma/Engg)
        if (uiState.educationFilter == EducationFilter.ADMISSIONS) {
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(AdmissionStream.entries) { stream ->
                        val selected = uiState.admissionStreamFilter == stream
                        FilterChip(
                            selected = selected,
                            onClick = { onAdmissionStreamChange(stream) },
                            label = { Text(UiStrings.admissionStreamLabel(stream, language)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                            ),
                            modifier = Modifier.testTag("admission_stream_${stream.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Step-by-Step Form Help Quick Entry Banner
        item {
            Card(
                onClick = onOpenFormHelp,
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("education_form_help_shortcut")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Assignment,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = strings.formHelpLabel,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = strings.formHelpBannerSubtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        item {
            DemoNoticeBanner(strings = strings)
        }

        if (items.isEmpty()) {
            item {
                EmptyStateView(
                    title = strings.noResultsTitle,
                    subtitle = strings.noResultsSubtitle,
                    actionLabel = strings.clearFiltersLabel,
                    onActionClick = onResetFilters
                )
            }
        } else {
            items(items, key = { it.id }) { item ->
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
