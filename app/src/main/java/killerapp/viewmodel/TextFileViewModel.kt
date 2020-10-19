package killerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import killerapp.db.TextFile
import killerapp.db.TextFileDatabase
import killerapp.db.TextFileRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextFileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextFileRepo
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allTextFiles: LiveData<List<TextFile>>

    init {
        val textFileDao = TextFileDatabase.getDatabase(application,viewModelScope).fileDao()
        repository = TextFileRepo(textFileDao)
        allTextFiles = repository.allTextFiles
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(textFile: TextFile) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(textFile)
    }
}