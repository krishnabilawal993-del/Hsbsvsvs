package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.AdmissionStream
import com.example.model.LocalizedText
import com.example.model.PortalCategory
import com.example.model.PortalUpdateItem
import com.example.model.ScholarshipCategory
import com.example.model.SchemeScope

private const val FIELD_SEPARATOR = "\n|||\n"

@Entity(tableName = "portal_items")
data class PortalItemEntity(
    @PrimaryKey val id: String,
    val categoryName: String,
    val titleGu: String,
    val titleHi: String,
    val organizationGu: String,
    val organizationHi: String,
    val shortSummaryGu: String,
    val shortSummaryHi: String,
    val eligibilityGu: String,
    val eligibilityHi: String,
    val importantDateNoteGu: String,
    val importantDateNoteHi: String,
    val publishedDate: String,
    val officialSourceName: String,
    val officialUrl: String,
    val districtScopeGu: String,
    val districtScopeHi: String,
    val tagsSerialized: String,
    val requiredDocsGuSerialized: String,
    val requiredDocsHiSerialized: String,
    val stepsGuSerialized: String,
    val stepsHiSerialized: String,
    val scholarshipCategoriesSerialized: String,
    val admissionStreamName: String?,
    val schemeScopeName: String?,
    val isDemo: Boolean,
    val updatedAtMillis: Long
)

fun PortalItemEntity.toDomain(): PortalUpdateItem {
    val category = PortalCategory.entries.firstOrNull { it.name == categoryName }
        ?: PortalCategory.GOVT_JOBS
    val tags = tagsSerialized.split(FIELD_SEPARATOR).map { it.trim() }.filter { it.isNotEmpty() }
    val docsGu = requiredDocsGuSerialized.split(FIELD_SEPARATOR).map { it.trim() }.filter { it.isNotEmpty() }
    val docsHi = requiredDocsHiSerialized.split(FIELD_SEPARATOR).map { it.trim() }.filter { it.isNotEmpty() }
    val docsCount = maxOf(docsGu.size, docsHi.size)
    val docs = (0 until docsCount).map { i ->
        val gu = docsGu.getOrNull(i) ?: docsHi.getOrNull(i).orEmpty()
        val hi = docsHi.getOrNull(i) ?: gu
        LocalizedText(gu = gu, hi = hi)
    }

    val stepsGu = stepsGuSerialized.split(FIELD_SEPARATOR).map { it.trim() }.filter { it.isNotEmpty() }
    val stepsHi = stepsHiSerialized.split(FIELD_SEPARATOR).map { it.trim() }.filter { it.isNotEmpty() }
    val stepsCount = maxOf(stepsGu.size, stepsHi.size)
    val steps = (0 until stepsCount).map { i ->
        val gu = stepsGu.getOrNull(i) ?: stepsHi.getOrNull(i).orEmpty()
        val hi = stepsHi.getOrNull(i) ?: gu
        LocalizedText(gu = gu, hi = hi)
    }

    val scholarshipCats = scholarshipCategoriesSerialized
        .split(",")
        .map { it.trim() }
        .mapNotNull { code -> ScholarshipCategory.entries.firstOrNull { it.name == code } }

    val admissionStream = admissionStreamName?.let { name ->
        AdmissionStream.entries.firstOrNull { it.name == name }
    }

    val schemeScope = schemeScopeName?.let { name ->
        SchemeScope.entries.firstOrNull { it.name == name }
    }

    return PortalUpdateItem(
        id = id,
        category = category,
        title = LocalizedText(titleGu, titleHi),
        organization = LocalizedText(organizationGu, organizationHi),
        shortSummary = LocalizedText(shortSummaryGu, shortSummaryHi),
        eligibility = LocalizedText(eligibilityGu, eligibilityHi),
        importantDateNote = LocalizedText(importantDateNoteGu, importantDateNoteHi),
        publishedDate = publishedDate,
        officialSourceName = officialSourceName,
        officialUrl = officialUrl,
        districtScope = LocalizedText(districtScopeGu, districtScopeHi),
        tags = tags,
        requiredDocuments = docs,
        howToApplySteps = steps,
        scholarshipCategories = scholarshipCats,
        admissionStream = admissionStream,
        schemeScope = schemeScope,
        isDemo = isDemo
    )
}

fun PortalUpdateItem.toEntity(updatedAtMillis: Long = System.currentTimeMillis()): PortalItemEntity {
    return PortalItemEntity(
        id = id,
        categoryName = category.name,
        titleGu = title.gu,
        titleHi = title.hi,
        organizationGu = organization.gu,
        organizationHi = organization.hi,
        shortSummaryGu = shortSummary.gu,
        shortSummaryHi = shortSummary.hi,
        eligibilityGu = eligibility.gu,
        eligibilityHi = eligibility.hi,
        importantDateNoteGu = importantDateNote.gu,
        importantDateNoteHi = importantDateNote.hi,
        publishedDate = publishedDate,
        officialSourceName = officialSourceName,
        officialUrl = officialUrl,
        districtScopeGu = districtScope.gu,
        districtScopeHi = districtScope.hi,
        tagsSerialized = tags.joinToString(FIELD_SEPARATOR),
        requiredDocsGuSerialized = requiredDocuments.joinToString(FIELD_SEPARATOR) { it.gu },
        requiredDocsHiSerialized = requiredDocuments.joinToString(FIELD_SEPARATOR) { it.hi },
        stepsGuSerialized = howToApplySteps.joinToString(FIELD_SEPARATOR) { it.gu },
        stepsHiSerialized = howToApplySteps.joinToString(FIELD_SEPARATOR) { it.hi },
        scholarshipCategoriesSerialized = scholarshipCategories.joinToString(",") { it.name },
        admissionStreamName = admissionStream?.name,
        schemeScopeName = schemeScope?.name,
        isDemo = isDemo,
        updatedAtMillis = updatedAtMillis
    )
}
