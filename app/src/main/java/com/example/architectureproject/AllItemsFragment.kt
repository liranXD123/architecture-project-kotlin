package com.example.architectureproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architectureproject.databinding.AllItemsLayoutBinding

class AllItemsFragment : Fragment() {

    private var _binding: AllItemsLayoutBinding?= null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllItemsLayoutBinding.inflate(layoutInflater, container,false)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_allItemsFragment_to_addItemFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("title")?.let{
            Toast.makeText(requireActivity(),it, Toast.LENGTH_SHORT).show()
        }
        binding.recycler.adapter = ItemAdapter(ItemManager.items, object : ItemAdapter.ItemListener {
            override fun onItemClicked(index: Int) {
                Toast.makeText(requireContext(),
                    "${ItemManager.items[index]}", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClicked(index: Int) {
                ItemManager.remove(index)
                binding.recycler.adapter!!.notifyItemRemoved(index)            }
        })
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(object : ItemTouchHelper.Callback()
        {
            override fun getMovementFlags(
                p0: RecyclerView,
                p1: RecyclerView.ViewHolder
            ) = makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                p0: RecyclerView,
                p1: RecyclerView.ViewHolder,
                p2: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                p1: Int
            ) {
                ItemManager.remove(viewHolder.bindingAdapterPosition)
                binding.recycler.adapter!!.notifyItemRemoved(viewHolder.bindingAdapterPosition)
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}