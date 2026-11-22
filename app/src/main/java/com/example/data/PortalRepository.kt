package com.example.data

import com.example.data.local.PortalItemDao
import com.example.data.local.toDomain
import com.example.data.local.toEntity
import com.example.model.FormHelpGuide
import com.example.model.PortalUpdateItem
import com.example.model.QuickPortalLink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

/**
 * ₹0 Local-First Repository abstraction ready for future updates.
 *
 * Currently backed by Android Jetpack Room (SQLite) on-device with zero paid APIs or cloud billing.
 * In the future, you can plug in a free static JSON URL (e.g., GitHub Pages) without modifying UI screens.
 */
interface PortalRepository {
    fun observePortalItems(): Flow<List<PortalUpdateItem>>
    fun observeFormHelpGuides(): Flow<List<FormHelpGuide>>
    fun observeQuickPortals(): Flow<List<QuickPortalLink>>
    suspend fun ensureSeeded()
    suspend fun upsertItem(item: PortalUpdateItem)
    suspend fun deleteItem(itemId: String)
    suspend fun resetDemoCatalog()
    fun exportCatalogAsJson(items: List<PortalUpdateItem>): String
}

class RoomPortalRepository(
    private val dao: PortalItemDao
) : PortalRepository {

    private val guidesFlow = MutableStateFlow(DemoPortalDataSource.formHelpGuides)
    private val quickPortalsFlow = MutableStateFlow(DemoPortalDataSource.quickPortals)

    override fun observePortalItems(): Flow<List<PortalUpdateItem>> =
        dao.observeAllItems().map { entities -> entities.map { it.toDomain() } }

    override fun observeFormHelpGuides(): Flow<List<FormHelpGuide>> = guidesFlow.asStateFlow()

    override fun observeQuickPortals(): Flow<List<QuickPortalLink>> = quickPortalsFlow.asStateFlow()

    override suspend fun ensureSeeded() {
        if (dao.countItems() == 0) {
            seedDemoItems()
        }
    }

    override suspend fun upsertItem(item: PortalUpdateItem) {
        dao.upsertItem(item.toEntity(updatedAtMillis = System.currentTimeMillis()))
    }

    override suspend fun deleteItem(itemId: String) {
        dao.deleteItemById(itemId)
    }

    override suspend fun resetDemoCatalog() {
        dao.clearAll()
        seedDemoItems()
    }

    private suspend fun seedDemoItems() {
        val baseTime = System.currentTimeMillis()
        val entities = DemoPortalDataSource.demoItems.mapIndexed { index, item ->
            item.toEntity(updatedAtMillis = baseTime - (index * 1_000L))
        }
        dao.upsertAll(entities)
    }

    override fun exportCatalogAsJson(items: List<PortalUpdateItem>): String {
        val array = JSONArray()
        items.forEach { item ->
            val obj = JSONObject().apply {
                put("id", item.id)
                put("category", item.category.name)
                put("titleGu", item.title.gu)
                put("titleHi", item.title.hi)
                put("organizationGu", item.organization.gu)
                put("organizationHi", item.organization.hi)
                put("summaryGu", item.shortSummary.gu)
                put("summaryHi", item.shortSummary.hi)
                put("eligibilityGu", item.eligibility.gu)
                put("eligibilityHi", item.eligibility.hi)
                put("importantDateNoteGu", item.importantDateNote.gu)
                put("importantDateNoteHi", item.importantDateNote.hi)
                put("publishedDate", item.publishedDate)
                put("officialSourceName", item.officialSourceName)
                put("officialUrl", item.officialUrl)
                put("districtGu", item.districtScope.gu)
                put("districtHi", item.districtScope.hi)
                put("tags", JSONArray(item.tags))
                put("isDemo", item.isDemo)
            }
            array.put(obj)
        }
        return JSONObject().apply {
            put("app", "Gujarat Sahayak")
            put("count", items.size)
            put("items", array)
        }.toString(2)
    }
}
