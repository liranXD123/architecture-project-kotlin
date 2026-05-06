package com.example.architectureproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // Make sure this is imported!
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.architectureproject.databinding.DetailsLayoutBinding

class DetailsFragment : Fragment() {

    private var _binding : DetailsLayoutBinding? = null
    private val binding get() = _binding!!

    // 1. FIXED: We are now sharing the same ViewModel as the AllItemsFragment
    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsLayoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. FIXED: The UI updates automatically when the chosenItem changes
        // I renamed 'it' to 'item' here just to make it easier to read
        viewModel.chosenItem.observe(viewLifecycleOwner) { item ->

            // 3. FIXED: Using the new Room database property names
            binding.titleDetail.text = item.content
            binding.detailDescription.text = item.description

            if (item.image != null) {
                Glide.with(this)
                    .load(item.image)
                    .into(binding.detailImage)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}