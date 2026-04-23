package com.example.architectureproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.architectureproject.databinding.DetailsLayoutBinding

class DetailsFragment : Fragment() {

    private var _binding : DetailsLayoutBinding? = null
    private val binding get() = _binding!!
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
        val title = arguments?.getString("item_title")
        val description = arguments?.getString("item_desc")
        val photoUri = arguments?.getString("item_photo")
        binding.titleDetail.text = title
        binding.detailDescription.text = description
        if (photoUri != null) {
            Glide.with(this)
                .load(photoUri)
                .into(binding.detailImage)
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