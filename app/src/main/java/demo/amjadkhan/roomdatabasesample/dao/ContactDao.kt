package demo.amjadkhan.roomdatabasesample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import demo.amjadkhan.roomdatabasesample.entities.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM PersonDetail")
    fun getContact(): LiveData<List<Contact>>

    @Query("SELECT * FROM PersonDetail WHERE id = :id")
    suspend fun getContactById(id: Long): Contact?

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)
}