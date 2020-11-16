package killerapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kill2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import killerapp.db.TextFile
import killerapp.xadapter.TextFileListAdapter
import killerapp.model.TextFileViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val newTextActivityRequestCode = 1
    private val newRemoveActivityRequestCode = 1
    private lateinit var textViewModel: TextFileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TextFileListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        textViewModel = ViewModelProvider(this).get(TextFileViewModel::class.java)

        textViewModel.allTextFiles.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setTextFiles(it) }
        })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTextActivity::class.java)
            startActivityForResult(intent, newTextActivityRequestCode)
        }
        val fab3 = findViewById<FloatingActionButton>(R.id.fab3)
        fab3.setOnClickListener {
            val intent = Intent(this@MainActivity, NewRemoveActivity::class.java)
            startActivityForResult(intent, newRemoveActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newTextActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewTextActivity.EXTRA_REPLY)?.let {
                val text = TextFile(message=it)
                textViewModel.insert(text)
            }
        } else {
            val text = ""
            Toast.makeText(
                applicationContext,
                text,
//                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
            }
    }
}