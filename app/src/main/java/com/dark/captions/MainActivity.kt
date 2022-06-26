package com.dark.captions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dark.captions.Adapter.CategoryAdapter
import com.dark.captions.Model.CatagoryModel
import com.dark.captions.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("Captions").addSnapshotListener { value, error ->

            val list = arrayListOf<CatagoryModel>()
            val data = value?.toObjects(CatagoryModel::class.java)
            list.addAll(data!!)

            binding.idCatagory.layoutManager= LinearLayoutManager(this)
            binding.idCatagory.adapter=CategoryAdapter(this,list)
        }

        val list= arrayListOf<String>("Love","Romantic","Caption","All of Best")



    }
}