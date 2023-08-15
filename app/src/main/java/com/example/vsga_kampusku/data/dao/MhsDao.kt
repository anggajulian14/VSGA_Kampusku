package com.example.vsga_kampusku.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vsga_kampusku.data.entity.Mahasiswa

@Dao
interface MhsDao {
    @Query("SELECT * FROM Mahasiswa")
    fun getAll(): List<Mahasiswa>

    @Query("SELECT * FROM Mahasiswa WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Mahasiswa>

    @Query("SELECT * FROM Mahasiswa WHERE uid = :nomorMahasiswa")
    fun getByNomorMahasiswa(nomorMahasiswa: String): Mahasiswa?

    @Insert
    fun insertAll(vararg users: Mahasiswa)

    @Delete
    fun delete(user: Mahasiswa)

    @Query("SELECT * FROM Mahasiswa WHERE uid = :uid")
    fun get(uid: Int): Mahasiswa

    @Update
    fun update(user: Mahasiswa)
}
