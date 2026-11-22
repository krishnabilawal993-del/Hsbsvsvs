package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.UiStrings
import com.example.model.EducationFilter
import com.example.model.JobFilter
import com.example.model.PortalCategory
import com.example.model.SchemeFilter
import com.example.ui.components.AdMobTestBannerSlot
import com.example.ui.screens.AdminPanelScreen
import com.example.ui.screens.DetailScreen
import com.example.ui.screens.EducationScreen
import com.example.ui.screens.FormHelpScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.JobsScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SchemesScreen
import com.example.viewmodel.SahayakUiState
import com.example.viewmodel.SahayakViewModel

object SahayakRoutes {
    const val HOME = "home"
    const val JOBS = "jobs"
    const val EDUCATION = "education"
    const val SCHEMES = "schemes"
    const val PROFILE = "profile"
    const val DETAIL = "detail/{itemId}"
    const val FORM_HELP = "form_help?guideId={guideId}"
    const val ADMIN_PANEL = "admin_panel"

    fun detailRoute(itemId: String): String = "detail/$itemId"
    fun formHelpRoute(guideId: String? = null): String =
        if (guideId != null) "form_help?guideId=$guideId" else "form_help"
}

private data class BottomNavTab(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun GujaratSahayakApp(
    uiState: SahayakUiState,
    viewModel: SahayakViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: SahayakRoutes.HOME
    val strings = UiStrings.get(uiState.language)

    val tabs = listOf(
        BottomNavTab(
            route = SahayakRoutes.HOME,
            label = strings.navHome,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavTab(
            route = SahayakRoutes.JOBS,
            label = strings.navJobs,
            selectedIcon = Icons.Filled.Work,
            unselectedIcon = Icons.Outlined.WorkOutline
        ),
        BottomNavTab(
            route = SahayakRoutes.EDUCATION,
            label = strings.navEducation,
            selectedIcon = Icons.Filled.School,
            unselectedIcon = Icons.Outlined.School
        ),
        BottomNavTab(
            route = SahayakRoutes.SCHEMES,
            label = strings.navSchemes,
            selectedIcon = Icons.Filled.AccountBalance,
            unselectedIcon = Icons.Outlined.AccountBalance
        ),
        BottomNavTab(
            route = SahayakRoutes.PROFILE,
            label = strings.navProfile,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )

    val isTopLevelTab = tabs.any { it.route == currentRoute }

    fun navigateToTab(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun handleCategoryNavigation(category: PortalCategory) {
        when (category) {
            PortalCategory.GOVT_JOBS -> {
                viewModel.setJobFilter(JobFilter.GOVERNMENT)
                navigateToTab(SahayakRoutes.JOBS)
            }
            PortalCategory.PRIVATE_JOBS -> {
                viewModel.setJobFilter(JobFilter.PRIVATE)
                navigateToTab(SahayakRoutes.JOBS)
            }
            PortalCategory.SCHOLARSHIPS -> {
                viewModel.setEducationFilter(EducationFilter.SCHOLARSHIPS)
                navigateToTab(SahayakRoutes.EDUCATION)
            }
            PortalCategory.COLLEGE_ADMISSIONS -> {
                viewModel.setEducationFilter(EducationFilter.ADMISSIONS)
                navigateToTab(SahayakRoutes.EDUCATION)
            }
            PortalCategory.MERIT_LISTS -> {
                viewModel.setEducationFilter(EducationFilter.MERIT_LISTS)
                navigateToTab(SahayakRoutes.EDUCATION)
            }
            PortalCategory.GOVT_SCHEMES -> {
                viewModel.setSchemeFilter(SchemeFilter.ALL)
                navigateToTab(SahayakRoutes.SCHEMES)
            }
            PortalCategory.FORM_HELP -> {
                navController.navigate(SahayakRoutes.formHelpRoute())
            }
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val useNavRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (!useNavRail && isTopLevelTab) {
                    Column {
                        // Official Google AdMob Test Banner Slot (₹0 — safe test ads only)
                        AdMobTestBannerSlot()
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 4.dp,
                            modifier = Modifier.testTag("bottom_navigation_bar")
                        ) {
                            tabs.forEach { tab ->
                                val selected = currentRoute == tab.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { navigateToTab(tab.route) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                            contentDescription = tab.label
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = tab.label,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier.testTag("nav_tab_${tab.route}")
                                )
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useNavRail && isTopLevelTab) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxHeight()
                            .testTag("side_navigation_rail")
                    ) {
                        tabs.forEach { tab ->
                            val selected = currentRoute == tab.route
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateToTab(tab.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = tab.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = tab.label,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                modifier = Modifier.testTag("rail_tab_${tab.route}")
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 840.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = SahayakRoutes.HOME,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(SahayakRoutes.HOME) {
                                HomeScreen(
                                    uiState = uiState,
                                    isExpandedWidth = useNavRail,
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onSearchQueryChange = viewModel::updateHomeSearch,
                                    onCategoryClick = ::handleCategoryNavigation,
                                    onItemClick = { itemId ->
                                        navController.navigate(SahayakRoutes.detailRoute(itemId))
                                    },
                                    onGuideClick = { guideId ->
                                        navController.navigate(SahayakRoutes.formHelpRoute(guideId))
                                    },
                                    onBookmarkToggle = viewModel::toggleBookmark,
                                    onOpenFormHelp = {
                                        navController.navigate(SahayakRoutes.formHelpRoute())
                                    }
                                )
                            }

                            composable(SahayakRoutes.JOBS) {
                                JobsScreen(
                                    uiState = uiState,
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onSearchChange = viewModel::updateJobsSearch,
                                    onFilterChange = viewModel::setJobFilter,
                                    onResetFilters = viewModel::resetJobFilters,
                                    onItemClick = { itemId ->
                                        navController.navigate(SahayakRoutes.detailRoute(itemId))
                                    },
                                    onBookmarkToggle = viewModel::toggleBookmark
                                )
                            }

                            composable(SahayakRoutes.EDUCATION) {
                                EducationScreen(
                                    uiState = uiState,
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onSearchChange = viewModel::updateEducationSearch,
                                    onEducationFilterChange = viewModel::setEducationFilter,
                                    onScholarshipCategoryChange = viewModel::setScholarshipCategoryFilter,
                                    onAdmissionStreamChange = viewModel::setAdmissionStreamFilter,
                                    onResetFilters = viewModel::resetEducationFilters,
                                    onOpenFormHelp = {
                                        navController.navigate(SahayakRoutes.formHelpRoute())
                                    },
                                    onItemClick = { itemId ->
                                        navController.navigate(SahayakRoutes.detailRoute(itemId))
                                    },
                                    onBookmarkToggle = viewModel::toggleBookmark
                                )
                            }

                            composable(SahayakRoutes.SCHEMES) {
                                SchemesScreen(
                                    uiState = uiState,
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onSearchChange = viewModel::updateSchemesSearch,
                                    onSchemeFilterChange = viewModel::setSchemeFilter,
                                    onResetFilters = viewModel::resetSchemeFilters,
                                    onOpenFormHelp = {
                                        navController.navigate(SahayakRoutes.formHelpRoute())
                                    },
                                    onItemClick = { itemId ->
                                        navController.navigate(SahayakRoutes.detailRoute(itemId))
                                    },
                                    onBookmarkToggle = viewModel::toggleBookmark
                                )
                            }

                            composable(SahayakRoutes.PROFILE) {
                                ProfileScreen(
                                    uiState = uiState,
                                    onSelectLanguage = viewModel::setLanguage,
                                    onToggleDarkMode = viewModel::setDarkMode,
                                    onSelectCategory = viewModel::setPreferredCategory,
                                    onSelectDistrict = viewModel::setPreferredDistrict,
                                    onOpenFormHelp = {
                                        navController.navigate(SahayakRoutes.formHelpRoute())
                                    },
                                    onOpenAdminPanel = {
                                        navController.navigate(SahayakRoutes.ADMIN_PANEL)
                                    },
                                    onItemClick = { itemId ->
                                        navController.navigate(SahayakRoutes.detailRoute(itemId))
                                    },
                                    onBookmarkToggle = viewModel::toggleBookmark
                                )
                            }

                            composable(
                                route = SahayakRoutes.DETAIL,
                                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
                            ) { entry ->
                                val itemId = entry.arguments?.getString("itemId").orEmpty()
                                val item = uiState.findItemById(itemId)
                                DetailScreen(
                                    item = item,
                                    language = uiState.language,
                                    isBookmarked = itemId in uiState.preferences.bookmarkedIds,
                                    onBack = { navController.popBackStack() },
                                    onBookmarkToggle = { viewModel.toggleBookmark(itemId) }
                                )
                            }

                            composable(
                                route = SahayakRoutes.FORM_HELP,
                                arguments = listOf(
                                    navArgument("guideId") {
                                        type = NavType.StringType
                                        nullable = true
                                        defaultValue = null
                                    }
                                )
                            ) { entry ->
                                val guideId = entry.arguments?.getString("guideId")
                                FormHelpScreen(
                                    uiState = uiState,
                                    initialGuideId = guideId,
                                    onBack = { navController.popBackStack() },
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onToggleChecklistDoc = viewModel::toggleChecklistDocument
                                )
                            }

                            composable(SahayakRoutes.ADMIN_PANEL) {
                                AdminPanelScreen(
                                    uiState = uiState,
                                    onBack = { navController.popBackStack() },
                                    onToggleLanguage = viewModel::toggleLanguage,
                                    onSaveItem = viewModel::saveAdminItem,
                                    onDeleteItem = viewModel::deleteAdminItem,
                                    onResetDemoCatalog = viewModel::resetDemoCatalog,
                                    onUpdatePin = viewModel::updateAdminPin,
                                    onExportJson = viewModel::exportCatalogJson
                                )
                            }
                        }
                    }

                    if (useNavRail && isTopLevelTab) {
                        AdMobTestBannerSlot(modifier = Modifier.widthIn(max = 840.dp))
                    }
                }
            }
        }
    }
}
