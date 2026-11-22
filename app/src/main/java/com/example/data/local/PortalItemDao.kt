package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PortalItemDao {
    @Query("SELECT * FROM portal_items ORDER BY updatedAtMillis DESC, publishedDate DESC")
    fun observeAllItems(): Flow<List<PortalItemEntity>>

    @Query("SELECT * FROM portal_items WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: String): PortalItemEntity?

    @Query("SELECT COUNT(*) FROM portal_items")
    suspend fun countItems(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: PortalItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PortalItemEntity>)

    @Query("DELETE FROM portal_items WHERE id = :id")
    suspend fun deleteItemById(id: String)

    @Query("DELETE FROM portal_items")
    suspend fun clearAll()
}
