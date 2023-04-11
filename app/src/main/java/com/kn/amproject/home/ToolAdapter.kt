package com.kn.amproject.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kn.amproject.R
import com.kn.amproject.data.Tool

class ToolAdapter(private val listener: OnToolItemLongClick) :
    RecyclerView.Adapter<ToolAdapter.ToolViewHolder>() {

    private val toolsList = ArrayList<Tool>()

    fun setTools(list: List<Tool>) {
        toolsList.clear()
        toolsList.addAll(list)
        notifyDataSetChanged()
    }

    fun removeTool(tool: Tool, position: Int) {
        toolsList.remove(tool)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_row, parent, false)
        return ToolViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        bindData(holder)
    }

    private fun bindData(holder: ToolViewHolder) {
        val name = holder.itemView.findViewById<TextView>(R.id.toolName)
        val description = holder.itemView.findViewById<TextView>(R.id.description)
        val productionYear = holder.itemView.findViewById<TextView>(R.id.toolProductionYear)
        val image = holder.itemView.findViewById<ImageView>(R.id.toolImage)

        name.text = toolsList[holder.adapterPosition].name
        productionYear.text = toolsList[holder.adapterPosition].productionYear
        description.text = toolsList[holder.adapterPosition].description
        Glide.with(holder.itemView)
            .load(toolsList[holder.adapterPosition].image)
            .into(image)
    }

    override fun getItemCount(): Int {
        return toolsList.size
    }

    inner class ToolViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnLongClickListener() {
                listener.onToolLongClick(toolsList[adapterPosition], adapterPosition)
                true
            }
        }
    }
}

interface OnToolItemLongClick {
    fun onToolLongClick(tool: Tool, position: Int)
}