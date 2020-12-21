package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tecx.notes.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    lateinit var noteBinding: ActivityNoteBinding
    lateinit var dbHandler: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        dbHandler = DataBaseHandler(this)

        // Use dataBinding to inflate the view
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        noteBinding.notesRecyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)


        refreshList()

        // An onclick listener is set for the hamburger icon on the action bar that slides
        // the navigation view into place when clicked
        noteBinding.toolbarNote.setNavigationOnClickListener {
            noteBinding.drawerLayout.openDrawer(noteBinding.navigationView)
        }


        noteBinding.fabNotesAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        noteBinding.navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {

                R.id.theme -> {
                    AppCompatDelegate.MODE_NIGHT_YES
                }

                R.id.about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    finish()
                }
            }
            noteBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity(0)
    }

    private fun refreshList() {
        noteBinding.notesRecyclerView?.adapter = NoteAdapter(dbHandler.getNotes(), this)
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun hideNoteImage() {
        noteBinding.addNotesImage.visibility = View.GONE
    }

    class NoteAdapter(
        private val list: MutableList<Notes>,
        private val activity: NoteActivity
    ) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(activity).inflate(R.layout.recycler_view_child, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.noteTitle.text = list[position].name
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }
    }
}
