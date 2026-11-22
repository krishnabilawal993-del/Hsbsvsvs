package com.example.model

enum class AppLanguage(val code: String, val nativeLabel: String) {
    GUJARATI("gu", "ગુજરાતી"),
    HINDI("hi", "हिन्दी");

    companion object {
        fun fromCode(code: String?): AppLanguage =
            entries.firstOrNull { it.code == code } ?: GUJARATI
    }
}

data class LocalizedText(
    val gu: String,
    val hi: String
) {
    fun get(language: AppLanguage): String = when (language) {
        AppLanguage.GUJARATI -> gu
        AppLanguage.HINDI -> hi
    }

    fun matchesQuery(query: String): Boolean {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return true
        return gu.lowercase().contains(q) || hi.lowercase().contains(q)
    }
}

enum class PortalCategory {
    GOVT_JOBS,
    PRIVATE_JOBS,
    SCHOLARSHIPS,
    COLLEGE_ADMISSIONS,
    MERIT_LISTS,
    GOVT_SCHEMES,
    FORM_HELP
}

enum class JobFilter {
    ALL,
    GOVERNMENT,
    PRIVATE
}

enum class EducationFilter {
    ALL,
    SCHOLARSHIPS,
    ADMISSIONS,
    MERIT_LISTS
}

enum class ScholarshipCategory(val code: String) {
    ALL("ALL"),
    ST("ST"),
    SC("SC"),
    OBC("OBC"),
    GENERAL("GENERAL")
}

enum class AdmissionStream {
    ALL,
    DEGREE_BA_BCOM_BSC,
    ITI,
    PTC,
    DIPLOMA_ENGG
}

enum class SchemeFilter {
    ALL,
    GUJARAT,
    CENTRAL
}

enum class SchemeScope {
    GUJARAT,
    CENTRAL
}

data class PortalUpdateItem(
    val id: String,
    val category: PortalCategory,
    val title: LocalizedText,
    val organization: LocalizedText,
    val shortSummary: LocalizedText,
    val eligibility: LocalizedText,
    val importantDateNote: LocalizedText,
    val publishedDate: String,
    val officialSourceName: String,
    val officialUrl: String,
    val districtScope: LocalizedText,
    val tags: List<String>,
    val requiredDocuments: List<LocalizedText>,
    val howToApplySteps: List<LocalizedText>,
    val scholarshipCategories: List<ScholarshipCategory> = emptyList(),
    val admissionStream: AdmissionStream? = null,
    val schemeScope: SchemeScope? = null,
    val isDemo: Boolean = true
)

data class ChecklistDocument(
    val id: String,
    val label: LocalizedText
)

data class FormStep(
    val stepNumber: Int,
    val title: LocalizedText,
    val instruction: LocalizedText
)

data class FormHelpGuide(
    val id: String,
    val title: LocalizedText,
    val subtitle: LocalizedText,
    val portalName: String,
    val officialUrl: String,
    val publishedDate: String,
    val estimatedMinutes: Int,
    val documentChecklist: List<ChecklistDocument>,
    val steps: List<FormStep>,
    val commonMistakes: List<LocalizedText>,
    val isDemo: Boolean = true
)

data class QuickPortalLink(
    val id: String,
    val name: LocalizedText,
    val domain: String,
    val url: String,
    val tag: LocalizedText
)
