package com.example.noottodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.noottodo.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    lateinit var database: NoteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        database = Room.databaseBuilder(requireActivity(),NoteDatabase::class.java,"Ntoe_DB")
            .allowMainThreadQueries().build()

        var notes: List<Note> =   database.getNoteDao().getAllData()

        var ad = NoteAdapter()
        ad.submitList(notes)

        binding.recyclerview.adapter= ad



        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        binding.logoutBtn.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut().apply {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }





        return binding.root
    }


}