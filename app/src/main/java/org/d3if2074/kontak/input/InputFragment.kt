package org.d3if2074.kontak.input

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2074.kontak.R
import org.d3if2074.kontak.databinding.FragmentInputBinding
import org.d3if2074.kontak.db.KontakDb

class InputFragment : Fragment() {
    private lateinit var binding: FragmentInputBinding

    private val viewModel: InputViewModel by lazy {
        val db= KontakDb.getInstance(requireContext())
        val factory = InputViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[InputViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentInputBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener{
            inputKontak()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_input -> {
                findNavController().navigate(
                    R.id.action_inputFragment_to_kontakFragment
                )
                return true
            }
            R.id.kontakPenting -> {
                findNavController().navigate(
                    R.id.action_inputFragment_to_pentingFragment3
                )
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun inputKontak(){

        val nama =binding.namaInput.text.toString()

        if(TextUtils.isEmpty(nama)){
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nomor = binding.nomorInput.text.toString()
        if (TextUtils.isEmpty(nomor)){
            Toast.makeText(context, R.string.nomor_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.inputKontak(nama, nomor)

        binding.namaInput.text = null
        binding.nomorInput.text =null

//        findNavController().popBackStack()
    }
}