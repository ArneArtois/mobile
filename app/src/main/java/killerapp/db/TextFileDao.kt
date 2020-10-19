package killerapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import killerapp.db.TextFile
//l zie voorbeeld https://developer.android.com/training/data-storage/room
@Dao
interface TextFileDao {
    //BASED ON QUERY EXAMPLES IN ANDROID ROOM WITH A VIEW - KOTLIN

    /**
     * @Return list of TextFile type
     */
    @Query("SELECT * from text_table ORDER BY id ASC")
    fun getTextFileListOrderedById(): LiveData<List<TextFile>>

    /**
     * @param TextFile
     * insert text file
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(textFile: TextFile)

    /**
     * Delete all
     */
    @Query("DELETE FROM text_table")
    suspend fun deleteAll()

    /**
     * Delete specific file
     */
    @Delete
    suspend fun delete(file: TextFile)


    //BASED ON QUERY EXAMPLES IN ANDROID ROOM DOC
    //insert, getAll, delete

    @Query("SELECT * FROM text_table")
    fun getAll(): LiveData<List<TextFile>>

    @Query("SELECT * FROM text_table WHERE id IN (:Ids)")
    fun loadAllByIds(Ids: IntArray): LiveData<List<TextFile>>

    @Query("SELECT * FROM text_table WHERE message LIKE '%' || :message || '%' LIMIT 1")
    fun findByName(message: String): LiveData<TextFile>

    @Insert
    fun insertAll(vararg files: TextFile)

}