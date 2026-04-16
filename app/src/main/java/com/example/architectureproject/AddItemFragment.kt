package com.example.architectureproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.architectureproject.databinding.AddItemLayoutBinding
import com.example.architectureproject.databinding.AllItemsLayoutBinding

class AddItemFragment : Fragment() {
    private var _binding : AddItemLayoutBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemLayoutBinding.inflate(layoutInflater, container, false)
        binding.finishButton.setOnClickListener {
            val bundle = bundleOf("title" to binding.itemTitle.text.toString(), "description" to binding.itemDescreption.text.toString())
            findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment, bundle)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}