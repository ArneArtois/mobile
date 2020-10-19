package killerapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="text_table")
data class TextFile(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "message") val message: String?
)
fun main(){
    TextFile(message = "message")
}