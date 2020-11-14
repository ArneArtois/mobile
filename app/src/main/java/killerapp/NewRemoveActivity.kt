package killerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kill2.R
import killerapp.xadapter.TextFileListAdapter
import killerapp.model.TextFileViewModel
import killerapp.xadapter.RemoveListAdapter

class NewRemoveActivity : AppCompatActivity() {
    private lateinit var textViewModel: TextFileViewModel
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove)

        val recyclerView = findViewById<RecyclerView>(R.id.remove_recyclerview)
        println("test")
        val adapter = RemoveListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        textViewModel = ViewModelProvider(this).get(TextFileViewModel::class.java)

        textViewModel.allTextFiles.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setTextFiles(it) }
        })
        }
}