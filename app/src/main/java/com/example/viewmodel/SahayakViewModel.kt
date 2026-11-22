package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.PortalRepository
import com.example.data.UserPreferences
import com.example.data.UserPreferencesRepository
import com.example.model.AdmissionStream
import com.example.model.AppLanguage
import com.example.model.EducationFilter
import com.example.model.FormHelpGuide
import com.example.model.JobFilter
import com.example.model.PortalCategory
import com.example.model.PortalUpdateItem
import com.example.model.QuickPortalLink
import com.example.model.ScholarshipCategory
import com.example.model.SchemeFilter
import com.example.model.SchemeScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SahayakUiState(
    val preferences: UserPreferences = UserPreferences(),
    val allItems: List<PortalUpdateItem> = emptyList(),
    val formHelpGuides: List<FormHelpGuide> = emptyList(),
    val quickPortals: List<QuickPortalLink> = emptyList(),
    val homeSearchQuery: String = "",
    val jobsSearchQuery: String = "",
    val educationSearchQuery: String = "",
    val schemesSearchQuery: String = "",
    val jobFilter: JobFilter = JobFilter.ALL,
    val educationFilter: EducationFilter = EducationFilter.ALL,
    val scholarshipCategoryFilter: ScholarshipCategory = ScholarshipCategory.ALL,
    val admissionStreamFilter: AdmissionStream = AdmissionStream.ALL,
    val schemeFilter: SchemeFilter = SchemeFilter.ALL
) {
    val language: AppLanguage get() = preferences.language

    val homeSearchResults: List<PortalUpdateItem>
        get() {
            val q = homeSearchQuery.trim()
            if (q.isEmpty()) return emptyList()
            return allItems.filter { it.matchesSearch(q) }
        }

    val homeGuideSearchResults: List<FormHelpGuide>
        get() {
            val q = homeSearchQuery.trim()
            if (q.isEmpty()) return emptyList()
            return formHelpGuides.filter { guide ->
                guide.title.matchesQuery(q) ||
                    guide.subtitle.matchesQuery(q) ||
                    guide.portalName.lowercase().contains(q.lowercase())
            }
        }

    val filteredJobs: List<PortalUpdateItem>
        get() = allItems.filter { item ->
            val matchesTab = when (jobFilter) {
                JobFilter.ALL -> item.category == PortalCategory.GOVT_JOBS || item.category == PortalCategory.PRIVATE_JOBS
                JobFilter.GOVERNMENT -> item.category == PortalCategory.GOVT_JOBS
                JobFilter.PRIVATE -> item.category == PortalCategory.PRIVATE_JOBS
            }
            matchesTab && item.matchesSearch(jobsSearchQuery)
        }

    val filteredEducation: List<PortalUpdateItem>
        get() = allItems.filter { item ->
            val matchesMain = when (educationFilter) {
                EducationFilter.ALL -> item.category in setOf(
                    PortalCategory.SCHOLARSHIPS,
                    PortalCategory.COLLEGE_ADMISSIONS,
                    PortalCategory.MERIT_LISTS
                )
                EducationFilter.SCHOLARSHIPS -> item.category == PortalCategory.SCHOLARSHIPS
                EducationFilter.ADMISSIONS -> item.category == PortalCategory.COLLEGE_ADMISSIONS
                EducationFilter.MERIT_LISTS -> item.category == PortalCategory.MERIT_LISTS
            }
            val matchesScholarshipSub = if (
                educationFilter == EducationFilter.SCHOLARSHIPS &&
                scholarshipCategoryFilter != ScholarshipCategory.ALL
            ) {
                scholarshipCategoryFilter in item.scholarshipCategories
            } else {
                true
            }
            val matchesAdmissionSub = if (
                educationFilter == EducationFilter.ADMISSIONS &&
                admissionStreamFilter != AdmissionStream.ALL
            ) {
                item.admissionStream == admissionStreamFilter
            } else {
                true
            }
            matchesMain && matchesScholarshipSub && matchesAdmissionSub && item.matchesSearch(educationSearchQuery)
        }

    val filteredSchemes: List<PortalUpdateItem>
        get() = allItems.filter { item ->
            val isScheme = item.category == PortalCategory.GOVT_SCHEMES
            val matchesScope = when (schemeFilter) {
                SchemeFilter.ALL -> true
                SchemeFilter.GUJARAT -> item.schemeScope == SchemeScope.GUJARAT
                SchemeFilter.CENTRAL -> item.schemeScope == SchemeScope.CENTRAL
            }
            isScheme && matchesScope && item.matchesSearch(schemesSearchQuery)
        }

    val bookmarkedItems: List<PortalUpdateItem>
        get() = allItems.filter { it.id in preferences.bookmarkedIds }

    fun countForCategory(category: PortalCategory): Int = when (category) {
        PortalCategory.FORM_HELP -> formHelpGuides.size
        else -> allItems.count { it.category == category }
    }

    fun findItemById(id: String): PortalUpdateItem? = allItems.firstOrNull { it.id == id }
}

private fun PortalUpdateItem.matchesSearch(rawQuery: String): Boolean {
    val q = rawQuery.trim().lowercase()
    if (q.isEmpty()) return true
    return title.matchesQuery(q) ||
        organization.matchesQuery(q) ||
        shortSummary.matchesQuery(q) ||
        eligibility.matchesQuery(q) ||
        officialSourceName.lowercase().contains(q) ||
        districtScope.matchesQuery(q) ||
        tags.any { it.lowercase().contains(q) }
}

private data class FilterState(
    val homeSearchQuery: String = "",
    val jobsSearchQuery: String = "",
    val educationSearchQuery: String = "",
    val schemesSearchQuery: String = "",
    val jobFilter: JobFilter = JobFilter.ALL,
    val educationFilter: EducationFilter = EducationFilter.ALL,
    val scholarshipCategoryFilter: ScholarshipCategory = ScholarshipCategory.ALL,
    val admissionStreamFilter: AdmissionStream = AdmissionStream.ALL,
    val schemeFilter: SchemeFilter = SchemeFilter.ALL
)

class SahayakViewModel(
    private val portalRepository: PortalRepository,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val filters = MutableStateFlow(FilterState())

    init {
        viewModelScope.launch {
            portalRepository.ensureSeeded()
        }
    }

    val uiState: StateFlow<SahayakUiState> = combine(
        preferencesRepository.preferencesFlow,
        portalRepository.observePortalItems(),
        portalRepository.observeFormHelpGuides(),
        portalRepository.observeQuickPortals(),
        filters
    ) { prefs, items, guides, portals, filterState ->
        SahayakUiState(
            preferences = prefs,
            allItems = items,
            formHelpGuides = guides,
            quickPortals = portals,
            homeSearchQuery = filterState.homeSearchQuery,
            jobsSearchQuery = filterState.jobsSearchQuery,
            educationSearchQuery = filterState.educationSearchQuery,
            schemesSearchQuery = filterState.schemesSearchQuery,
            jobFilter = filterState.jobFilter,
            educationFilter = filterState.educationFilter,
            scholarshipCategoryFilter = filterState.scholarshipCategoryFilter,
            admissionStreamFilter = filterState.admissionStreamFilter,
            schemeFilter = filterState.schemeFilter
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SahayakUiState()
    )

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch { preferencesRepository.setLanguage(language) }
    }

    fun toggleLanguage() {
        val next = if (uiState.value.language == AppLanguage.GUJARATI) {
            AppLanguage.HINDI
        } else {
            AppLanguage.GUJARATI
        }
        setLanguage(next)
    }

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch { preferencesRepository.setDarkMode(enabled) }
    }

    fun toggleBookmark(itemId: String) {
        viewModelScope.launch { preferencesRepository.toggleBookmark(itemId) }
    }

    fun toggleChecklistDocument(docId: String) {
        viewModelScope.launch { preferencesRepository.toggleChecklistDocument(docId) }
    }

    fun setPreferredCategory(category: ScholarshipCategory) {
        viewModelScope.launch {
            preferencesRepository.setPreferredCategory(category)
            filters.value = filters.value.copy(scholarshipCategoryFilter = category)
        }
    }

    fun setPreferredDistrict(district: String) {
        viewModelScope.launch { preferencesRepository.setPreferredDistrict(district) }
    }

    fun updateAdminPin(newPin: String) {
        viewModelScope.launch { preferencesRepository.setAdminPin(newPin) }
    }

    fun saveAdminItem(item: PortalUpdateItem) {
        viewModelScope.launch { portalRepository.upsertItem(item) }
    }

    fun deleteAdminItem(itemId: String) {
        viewModelScope.launch { portalRepository.deleteItem(itemId) }
    }

    fun resetDemoCatalog() {
        viewModelScope.launch { portalRepository.resetDemoCatalog() }
    }

    fun exportCatalogJson(): String =
        portalRepository.exportCatalogAsJson(uiState.value.allItems)

    fun updateHomeSearch(query: String) {
        filters.value = filters.value.copy(homeSearchQuery = query)
    }

    fun updateJobsSearch(query: String) {
        filters.value = filters.value.copy(jobsSearchQuery = query)
    }

    fun updateEducationSearch(query: String) {
        filters.value = filters.value.copy(educationSearchQuery = query)
    }

    fun updateSchemesSearch(query: String) {
        filters.value = filters.value.copy(schemesSearchQuery = query)
    }

    fun setJobFilter(filter: JobFilter) {
        filters.value = filters.value.copy(jobFilter = filter)
    }

    fun setEducationFilter(filter: EducationFilter) {
        filters.value = filters.value.copy(educationFilter = filter)
    }

    fun setScholarshipCategoryFilter(category: ScholarshipCategory) {
        filters.value = filters.value.copy(scholarshipCategoryFilter = category)
    }

    fun setAdmissionStreamFilter(stream: AdmissionStream) {
        filters.value = filters.value.copy(admissionStreamFilter = stream)
    }

    fun setSchemeFilter(filter: SchemeFilter) {
        filters.value = filters.value.copy(schemeFilter = filter)
    }

    fun resetJobFilters() {
        filters.value = filters.value.copy(jobsSearchQuery = "", jobFilter = JobFilter.ALL)
    }

    fun resetEducationFilters() {
        filters.value = filters.value.copy(
            educationSearchQuery = "",
            educationFilter = EducationFilter.ALL,
            scholarshipCategoryFilter = ScholarshipCategory.ALL,
            admissionStreamFilter = AdmissionStream.ALL
        )
    }

    fun resetSchemeFilters() {
        filters.value = filters.value.copy(schemesSearchQuery = "", schemeFilter = SchemeFilter.ALL)
    }

    class Factory(
        private val portalRepository: PortalRepository,
        private val preferencesRepository: UserPreferencesRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SahayakViewModel(portalRepository, preferencesRepository) as T
        }
    }
}
