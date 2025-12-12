package demo.amjadkhan.roomdatabasesample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import demo.amjadkhan.roomdatabasesample.convertors.Convertors
import demo.amjadkhan.roomdatabasesample.dao.ContactDao
import demo.amjadkhan.roomdatabasesample.entities.Contact


@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun ContactDao(): ContactDao


    companion object {
        // Migration from version 1 to version 2 Database adding new things
//        val migration_1_2 = object: Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE PersonDetail ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
//            }
//        }

        @Volatile
        private var DB_INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "PersonDB"
                    )
                        // migration from version 1 to version 2 Database adding new things
//                        .addMigrations(migration_1_2)
                        .build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}