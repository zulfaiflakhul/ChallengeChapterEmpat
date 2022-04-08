package zulfa.binar.challengechapterempat

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.custom_dialog_input.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class HomeFragment : Fragment() {

    lateinit var sp : SharedPreferences
    private var mDB : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = requireContext().getSharedPreferences("REG", Context.MODE_PRIVATE)
        mDB = NoteDatabase.getInstance(requireContext())

        val getnama = sp.getString("name", "")
        tv_username.text = "Welcome, $getnama!"

        tv_logout.setOnClickListener {

            val logout = sp.edit()
            logout.clear()
            logout.apply()

            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

        addData()
        viewData()
    }

    fun addData() {

        btn_tambah.setOnClickListener {

            val adTambah = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog_input, null, false)
            val ad = AlertDialog.Builder(requireContext())
                .setView(adTambah)
                .create()


            adTambah.btn_input.setOnClickListener {

                GlobalScope.async {

                    val judul = adTambah.et_input_judul.text.toString()
                    val deskripsi = adTambah.et_input_deskripsi.text.toString()
                    val deadline = adTambah.et_input_deadline.text.toString()

                    val hasil = mDB?.noteDao()?.insertNote(Note(null, judul, deskripsi, deadline))

                    requireActivity().runOnUiThread{
                        if (hasil != 0.toLong()){
                            Toast.makeText(requireContext(), "Succes", Toast.LENGTH_LONG).show()
                            viewData()
                            NoteDatabase.destroyInstance()

                        } else {
                            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                ad.dismiss()
            }
                ad.show()
        }
    }

    fun viewData(){

        rv_note.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        GlobalScope.async {
            val listdata = mDB?.noteDao()?.getAllNote()
            requireActivity().runOnUiThread{
                listdata.let {
                    val adapt = AdapterNote(it!!)
                    rv_note.adapter = adapt
                }
            }
        }
    }
}