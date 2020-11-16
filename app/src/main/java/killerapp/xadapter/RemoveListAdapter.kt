package killerapp.xadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kill2.R
import killerapp.db.TextFile
import kotlinx.android.synthetic.main.remove_item.view.*


class RemoveListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RemoveListAdapter.TextFileViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var textFiles = emptyList<TextFile>() // Cached copy of text

    inner class TextFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextFileViewHolder {
        val itemView = inflater.inflate(R.layout.remove_item, parent, false)
        return TextFileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextFileViewHolder, position: Int) {
        val current = textFiles[position]
        holder.wordItemView.text = current.message

       
    }

    internal fun setTextFiles(textFiles: List<TextFile>) {
        this.textFiles = textFiles
        notifyDataSetChanged()
    }

    override fun getItemCount() = textFiles.size


}