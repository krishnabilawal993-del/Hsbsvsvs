package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UiStrings
import com.example.model.FormHelpGuide
import com.example.model.PortalCategory
import com.example.model.QuickPortalLink
import com.example.ui.ExternalActions
import com.example.ui.components.DemoBadge
import com.example.ui.components.DemoNoticeBanner
import com.example.ui.components.EmptyStateView
import com.example.ui.components.LanguageTogglePill
import com.example.ui.components.PortalItemCard
import com.example.ui.components.SahayakSearchBar
import com.example.ui.components.TricolorAccentBar
import com.example.ui.components.categoryAccentPair
import com.example.ui.components.categoryIcon
import com.example.viewmodel.SahayakUiState

@Composable
fun HomeScreen(
    uiState: SahayakUiState,
    isExpandedWidth: Boolean,
    onToggleLanguage: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onCategoryClick: (PortalCategory) -> Unit,
    onItemClick: (String) -> Unit,
    onGuideClick: (String) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    onOpenFormHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language = uiState.language
    val strings = UiStrings.get(language)
    val isSearching = uiState.homeSearchQuery.isNotBlank()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_list"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Brand Header + Language Switcher
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = strings.appTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Gujarat Citizen Helper Portal",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                LanguageTogglePill(
                    currentLanguage = language,
                    onToggle = onToggleLanguage
                )
            }
        }

        // Hero Banner Card with Tricolor Top Accent
        item {
            Card(
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    TricolorAccentBar()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(168.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_gujarat_hero_banner),
                            contentDescription = stringResource(R.string.cd_hero_banner),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x22082915),
                                            Color(0xCC0A3A1E)
                                        )
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color(0xFFFF7722)
                            ) {
                                Text(
                                    text = if (language == com.example.model.AppLanguage.GUJARATI) {
                                        "ગુજરાત • વિશ્વસનીય અધિકૃત લિંક્સ"
                                    } else {
                                        "गुजरात • विश्वसनीय आधिकारिक लिंक"
                                    },
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Text(
                                text = strings.appTagline,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Global Search Bar
        item {
            SahayakSearchBar(
                query = uiState.homeSearchQuery,
                onQueryChange = onSearchQueryChange,
                placeholder = strings.searchPlaceholderHome,
                testTagName = "home_search_input"
            )
        }

        if (isSearching) {
            item {
                Text(
                    text = "${strings.searchResultsHeading} (${uiState.homeSearchResults.size + uiState.homeGuideSearchResults.size})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            if (uiState.homeSearchResults.isEmpty() && uiState.homeGuideSearchResults.isEmpty()) {
                item {
                    EmptyStateView(
                        title = strings.noResultsTitle,
                        subtitle = strings.noResultsSubtitle,
                        actionLabel = strings.clearFiltersLabel,
                        onActionClick = { onSearchQueryChange("") }
                    )
                }
            } else {
                items(uiState.homeSearchResults, key = { it.id }) { item ->
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
                items(uiState.homeGuideSearchResults, key = { it.id }) { guide ->
                    HomeGuideCompactCard(
                        guide = guide,
                        language = language,
                        demoLabel = strings.demoBadgeLabel,
                        onClick = { onGuideClick(guide.id) }
                    )
                }
            }
        } else {
            // Transparent Demo Notice Banner
            item {
                DemoNoticeBanner(strings = strings)
            }

            // 7 Core Categories Grid
            item {
                Text(
                    text = strings.categoriesHeading,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                val categories = PortalCategory.entries
                val columns = if (isExpandedWidth) 3 else 2
                val rows = categories.chunked(columns)
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    rows.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowItems.forEach { category ->
                                CategoryGridCard(
                                    category = category,
                                    count = uiState.countForCategory(category),
                                    language = language,
                                    onClick = { onCategoryClick(category) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            repeat(columns - rowItems.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            // Step-by-Step Form Help Highlight Card
            item {
                Card(
                    onClick = onOpenFormHelp,
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_form_help_banner_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Assignment,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = strings.formHelpBannerTitle,
                                style = MaterialTheme.typography.titleMedium,
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

            // Quick Official Portals Horizontal Row
            item {
                Text(
                    text = strings.officialPortalsHeading,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(uiState.quickPortals, key = { it.id }) { portal ->
                        QuickPortalChipCard(
                            portal = portal,
                            language = language,
                            onClick = {
                                ExternalActions.openOfficialUrl(context, portal.url, strings.openLinkError)
                            }
                        )
                    }
                }
            }

            // Recent Demo Updates Feed
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = strings.recentDemoUpdatesHeading,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(
                        onClick = { onCategoryClick(PortalCategory.GOVT_JOBS) },
                        modifier = Modifier.testTag("home_view_all_updates_button")
                    ) {
                        Text(text = strings.viewAll)
                    }
                }
            }

            items(uiState.allItems.take(5), key = { "home_${it.id}" }) { item ->
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

@Composable
private fun CategoryGridCard(
    category: PortalCategory,
    count: Int,
    language: com.example.model.AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = UiStrings.get(language)
    val (bg, tint) = categoryAccentPair(category)
    val countText = if (category == PortalCategory.FORM_HELP) {
        "$count ${strings.guidesCountSuffix}"
    } else {
        "$count ${strings.itemsCountSuffix}"
    }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.testTag("category_card_${category.name.lowercase()}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(bg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = categoryIcon(category),
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.size(18.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = UiStrings.categoryLabel(category, language),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = countText,
                    style = MaterialTheme.typography.bodySmall,
                    color = tint,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun QuickPortalChipCard(
    portal: QuickPortalLink,
    language: com.example.model.AppLanguage,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.22f)),
        modifier = Modifier
            .width(172.dp)
            .testTag("quick_portal_${portal.id}")
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Filled.Verified,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(14.dp)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = stringResource(R.string.cd_open_official_link),
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(15.dp)
                )
            }
            Text(
                text = portal.name.get(language),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = portal.tag.get(language),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = portal.domain,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun HomeGuideCompactCard(
    guide: FormHelpGuide,
    language: com.example.model.AppLanguage,
    demoLabel: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("search_guide_${guide.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Assignment,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp)
                )
                DemoBadge(label = demoLabel)
            }
            Text(
                text = guide.title.get(language),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = guide.subtitle.get(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
