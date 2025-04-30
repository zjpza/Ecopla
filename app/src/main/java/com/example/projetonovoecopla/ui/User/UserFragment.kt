package com.example.projetonovoecopla.ui.User

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var isSearchBarHidden = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchBar = requireActivity().findViewById<View>(R.id.search_bar)

        if (!isSearchBarHidden) {
            searchBar.visibility = View.GONE
            isSearchBarHidden = true
        }


        val textView: TextView = binding.textUser
            userViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val searchBar = requireActivity().findViewById<View>(R.id.search_bar)
        if (isSearchBarHidden) {
            searchBar.visibility = View.GONE
        }
    }
}