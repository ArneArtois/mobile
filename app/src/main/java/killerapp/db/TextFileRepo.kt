package killerapp.db

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TextFileRepo(private val textfileDao: TextFileDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTextFiles: LiveData<List<TextFile>> = textfileDao.getTextFileListOrderedById()


    //IF DOESNT WORK TRY OBJECT METHOD IN TEXTFILEDAO
    suspend fun insert(textFile: TextFile) {
        textfileDao.insert(textFile);
    }
    suspend fun deleteAll(){
        textfileDao.deleteAll()
    }
    suspend fun deleteSelected(){
        textfileDao.deleteSelected()
    }
    suspend fun deleteByIds(arrayList: ArrayList<Int>){
        textfileDao.deleteById(arrayList)
    }
}

