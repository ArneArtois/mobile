package killerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kill2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import killerapp.db.TextFile
import killerapp.xadapter.TextFileListAdapter
import killerapp.model.TextFileViewModel
import killerapp.xadapter.RemoveListAdapter
import kotlinx.android.synthetic.main.remove_item.*

class NewRemoveActivity : AppCompatActivity() {
    private lateinit var textViewModel: TextFileViewModel
    private val newRemoveActivityRequestCode = 1
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
        val fab3 = findViewById<FloatingActionButton>(R.id.fab3)
        fab3.setOnClickListener {
            textViewModel.removeAll()
            val intent = Intent(this@NewRemoveActivity, this::class.java)
            finish()
        }

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}