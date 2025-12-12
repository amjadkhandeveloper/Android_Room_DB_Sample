package demo.amjadkhan.roomdatabasesample.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.amjadkhan.roomdatabasesample.MainActivity
import java.util.Date


@Entity(tableName = "PersonDetail")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var createDate: Date,
    // migration from version 1 to version 2 adding new column in db table
    //    var isActivity: Int
)