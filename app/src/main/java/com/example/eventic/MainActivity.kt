package com.example.eventic

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventic.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.bundled.internal.ThickBarcodeScannerCreator
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Events>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var events: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.Theme_EvenTic)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)



        //navbar
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()
        val fourFragment = FourFragment()

        setCurrentFragment(firstFragment)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mi_home -> setCurrentFragment(firstFragment)
                R.id.mi_favorites -> setCurrentFragment(secondFragment)
                R.id.mi_search -> setCurrentFragment(thirdFragment)
                R.id.mi_profile -> setCurrentFragment(fourFragment)
            }
            true
        }



        imageId = arrayOf(
            R.drawable.index1,
            R.drawable.index_a,
            R.drawable.index_b,
            R.drawable.index_c,
            R.drawable.index_d,
            R.drawable.index_e,
            R.drawable.index_f,
            R.drawable.index_g,
            R.drawable.index_h


        )

        heading = arrayOf(
            "DCC RAG DAY-2022",
            "CONFERENCE ON SUSTAINABLE TECHNOLOGY",
            "BUSINESS CONFERENCE 2022",
            "NANOTECH AND BIO-ENGINEERING CONFERENCE",
            "AGRI BUSINESS CONFERENCE",
            "2nd NATIONAL SCIENCE CARNIVAL",
            "K-DANCE MEETUP",
            "JOY BANGLA CONCERT",
            "BANGLADESH WOMEN MARATHON"

        )

        events = arrayOf(

            getString(R.string.events_a),
            getString(R.string.events_b),
            getString(R.string.events_c),
            getString(R.string.events_d),
            getString(R.string.events_e),
            getString(R.string.events_f),
            getString(R.string.events_g),
            getString(R.string.events_h),
            getString(R.string.events_i)

        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager= LinearLayoutManager(this
        )
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Events>()
        getUserData()

   }
//navbar
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }




//recyclerview
    private fun getUserData() {
        for(i in imageId.indices){
            val events = Events(imageId[i],heading[i])
            newArrayList.add(events)

        }

      val adapter = MyAdapter(newArrayList)
      newRecyclerView.adapter = adapter
        adapter.setOnIemClickListener(object: MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

//                Toast.makeText(this@MainActivity,"You clicked on item no. $position",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, EventsActivity::class.java)
                //recyclerview
                intent.putExtra("heading",newArrayList[position].heading)
                intent.putExtra("imageId",newArrayList[position].titleImage)
                intent.putExtra("events",events[position])
                startActivity(intent)

            }

        })
    }
}