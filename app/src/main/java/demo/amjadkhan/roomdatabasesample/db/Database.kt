package demo.amjadkhan.roomdatabasesample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import demo.amjadkhan.roomdatabasesample.convertors.Convertors
import demo.amjadkhan.roomdatabasesample.dao.ContactDao
import demo.amjadkhan.roomdatabasesample.entities.Contact


@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun ContactDao(): ContactDao

    companion object {
        @Volatile
        private var DB_INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "PersonDB"
                    ).build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}