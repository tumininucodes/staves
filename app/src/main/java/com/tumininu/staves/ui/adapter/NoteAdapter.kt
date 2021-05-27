package com.tumininu.staves.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tumininu.staves.R
import com.tumininu.staves.db.Notes
import com.tumininu.staves.ui.fragments.NoteFragmentDirections

class NoteAdapter(
    private val list: List<Notes>,
    private val context: Context
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_child, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleLine.text = list[position].name
        holder.noteLine.text = list[position].body

        holder.itemView.setOnClickListener {
            Navigation.findNavController(
                context as Activity,
                R.id.nav_host
            ).navigate(
                NoteFragmentDirections.actionNoteFragmentToUpdateNoteFragment(
                    id = list[position].id,
                    title = list[position].name,
                    body = list[position].body
                )
            )

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val titleLine: TextView = itemView.findViewById(R.id.titleLine)
        val noteLine: TextView = itemView.findViewById(R.id.notesLine)
    }
}