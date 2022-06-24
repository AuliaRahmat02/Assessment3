package org.d3if2074.kontak.kontakpenting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2074.kontak.daftarkontak.KontakAdapter
import org.d3if2074.kontak.databinding.FragmentKontakpentingBinding
import org.d3if2074.kontak.network.ApiStatus

class PentingFragment : Fragment() {
    private val viewModel: PentingViewModel by lazy {
        ViewModelProvider(this)[PentingViewModel::class.java]
    }

    private lateinit var binding: FragmentKontakpentingBinding
    private lateinit var myAdapter: PentingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKontakpentingBinding.inflate(layoutInflater, container, false)
        myAdapter = PentingAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleAdapter(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus){
        when(status){
            ApiStatus.LOADING ->{
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS ->{
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED ->{
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}