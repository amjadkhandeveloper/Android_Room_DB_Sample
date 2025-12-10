package demo.amjadkhan.roomdatabasesample.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "PersonDetail")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var createDate: Date
)