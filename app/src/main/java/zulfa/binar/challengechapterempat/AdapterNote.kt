package zulfa.binar.challengechapterempat

import android.app.AlertDialog
import android.content.DialogInterface
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_dialog_input.view.*
import kotlinx.android.synthetic.main.custom_dialog_update.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNote(val listdataNote: List<Note>) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private var mDB : NoteDatabase? = null
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNote.ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        holder.itemView.tv_no.text = listdataNote[position].no.toString()
        holder.itemView.tv_judul.text = listdataNote[position].judul
        holder.itemView.tv_deskripsi.text = listdataNote[position].deskripsi
        holder.itemView.tv_deadline.text = listdataNote[position].tenggatwaktu

        holder.itemView.btn_delete.setOnClickListener {
            mDB = NoteDatabase.getInstance(it.context)

            AlertDialog.Builder(it.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin hapus data?")
                .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->

                    GlobalScope.async {

                        val result = mDB?.noteDao()?.delete(listdataNote[position])
                        (holder.itemView.context as HomeFragment).requireActivity().runOnUiThread{
                            if (result != 0){
                                Toast.makeText(it.context, "Data dihapus", Toast.LENGTH_LONG).show()
                                (holder.itemView.context as HomeFragment).viewData()
                            } else{
                                Toast.makeText(it.context, "Gagal", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }

        holder.itemView.btn_edit.setOnClickListener {

            mDB = NoteDatabase.getInstance(it.context)

            val adTambah = LayoutInflater.from(it.context).inflate(R.layout.custom_dialog_update, null, false)
            val ad = AlertDialog.Builder(it.context)
                .setView(adTambah)
                .create()


            adTambah.btn_update.setOnClickListener {

                GlobalScope.async {

                    val upjudul = adTambah.et_update_judul.text.toString()
                    val updeskripsi = adTambah.et_update_deskripsi.text.toString()
                    val updeadline = adTambah.et_update_deadline.text.toString()

                    val result = mDB?.noteDao()?.updateNote(listdataNote[position])
                    (holder.itemView.context as HomeFragment).requireActivity().runOnUiThread{
                        if (result != 0){
                            Toast.makeText(it.context, "Data dihapus", Toast.LENGTH_LONG).show()
                            (holder.itemView.context as HomeFragment).viewData()
                        } else{
                            Toast.makeText(it.context, "Gagal", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                ad.dismiss()
            }
            ad.show()
        }
    }

    override fun getItemCount(): Int {
        return listdataNote.size
    }

}