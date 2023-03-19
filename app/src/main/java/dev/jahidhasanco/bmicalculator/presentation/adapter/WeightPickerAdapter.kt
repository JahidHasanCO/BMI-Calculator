package dev.jahidhasanco.bmicalculator.presentation.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jahidhasanco.bmicalculator.R


class WeightPickerAdapter(private val context: Context, private var dataList: List<String>, private val recyclerView: RecyclerView) :
    RecyclerView.Adapter<WeightPickerAdapter.TextVH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.weight_picker_item_layout, parent, false)
        return TextVH(view)
    }

    override fun onBindViewHolder(holder: TextVH, position: Int) {

        holder.pickerTxt.text = dataList[position]
        holder.pickerTxt.setOnClickListener {
            recyclerView.smoothScrollToPosition(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(newData: List<String>) {
        dataList = newData
        this.notifyDataSetChanged()
    }

     class TextVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pickerTxt: TextView

        init {
            pickerTxt = itemView.findViewById(R.id.weight_picker_item)
        }
    }

}