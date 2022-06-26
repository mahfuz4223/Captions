package com.dark.captions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dark.captions.Adapter.AllCaptionssAdapter
import com.dark.captions.Model.CaptionModel
import com.dark.captions.databinding.ActivityAllCaptionBinding
import com.google.firebase.firestore.FirebaseFirestore

class AllCaption : AppCompatActivity() {
    lateinit var binding: ActivityAllCaptionBinding
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityAllCaptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name=intent.getStringExtra("name")
        val id=intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.back.setOnClickListener {
            onBackPressed()
        }


        binding.catagoryheaad.text= name.toString()

        db.collection("Captions").document(id!!).collection("All").addSnapshotListener{ value, error ->

            val captionList = arrayListOf<CaptionModel>()
            val data = value?.toObjects(CaptionModel::class.java)
            captionList.addAll(data!!)


            binding.allcaptionss.layoutManager= LinearLayoutManager(this)
            binding.allcaptionss.adapter = AllCaptionssAdapter(this,captionList)
        }




    }
}