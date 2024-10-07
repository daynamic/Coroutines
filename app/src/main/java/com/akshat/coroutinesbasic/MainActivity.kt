package com.akshat.coroutinesbasic

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshat.coroutinesbasic.view.CountryListAdapter
import com.akshat.coroutinesbasic.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var countriesList: RecyclerView
    private lateinit var list_error: TextView
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var viewModel: ListViewModel
    private var countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countriesList = findViewById(R.id.countriesList)
        progressBar = findViewById(R.id.loading_view)
        list_error = findViewById(R.id.list_error)

        viewModel = androidx.lifecycle.ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, androidx.lifecycle.Observer { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, androidx.lifecycle.Observer { isError ->
            list_error.visibility = if (isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, androidx.lifecycle.Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }

        })

    }

    /*
    Part of
    https://github.com/daynamic/Coroutines/commit/fa2fdb6b1b4504373346e61edca12613e931e18e
    imageView = findViewById(R.id.imageView)
    progressBar = findViewById(R.id.progressBar)
    coroutineScope.launch {
        val originalDeferred = coroutineScope.async(Dispatchers.IO){
            getOriginalBitmap()
        }
        val originalBitmap = originalDeferred.await()

        val filteredDeferred = coroutineScope.async(Dispatchers.Default) { applyFilter(originalBitmap) }

        val filteredBitmap = filteredDeferred.await()

        loadImage(filteredBitmap)
    }*/

    /*
    Part of
         https://github.com/daynamic/Coroutines/commit/fa2fdb6b1b4504373346e61edca12613e931e18e
    private fun getOriginalBitmap()=
         URL(IMAGE_URL).openStream().use {
             BitmapFactory.decodeStream(it)
         }

     private fun applyFilter(originalBitmap: Bitmap) = Filter.apply(originalBitmap)

     private fun loadImage(bmp: Bitmap){
         progressBar.visibility = View.GONE
         imageView.setImageBitmap(bmp)
         imageView.visibility = View.VISIBLE
     }*/

}