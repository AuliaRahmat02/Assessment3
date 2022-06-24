package org.d3if2074.kontak.daftarkontak

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2074.kontak.R
import org.d3if2074.kontak.databinding.FragmentKontakBinding
import org.d3if2074.kontak.db.KontakDb
import org.d3if2074.kontak.model.Kontak

class KontakFragment : Fragment() {

    private val viewModel: KontakViewModel by lazy {
        val db = KontakDb.getInstance(requireContext())
        val factory = KontakViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[KontakViewModel::class.java]
    }

    private lateinit var binding: FragmentKontakBinding
    private lateinit var myAdapter: KontakAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        myAdapter = KontakAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKontakBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }






}