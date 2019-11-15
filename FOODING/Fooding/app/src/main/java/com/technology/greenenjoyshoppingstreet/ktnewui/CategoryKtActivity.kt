package com.technology.greenenjoyshoppingstreet.ktnewui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.technology.greenenjoyshoppingstreet.BaseActivity
import com.technology.greenenjoyshoppingstreet.R
import com.technology.greenenjoyshoppingstreet.ktnewui.adapter.CategoryAdapter
import com.technology.greenenjoyshoppingstreet.ktnewui.api.ServiceGenerator
import com.technology.greenenjoyshoppingstreet.ktnewui.model.Category
import kotlinx.android.synthetic.main.activity_category_kt.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryKtActivity : BaseActivity() {

    lateinit var catList:List<Category.Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_kt)
        getCategoryList()
    }

    private fun getCategoryList() {
        val client = ServiceGenerator.create()
        val call = client.getCategoryList()
        call.enqueue(object : Callback<Category>{
            override fun onFailure(call: Call<Category>, t: Throwable) {
                print(t)
            }
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if (response.isSuccessful){
                    val data = response.body()?.data
                    data?.let {
                        catList = it
                        setUpRecycler()
                    }
                }
            }
        })
    }

    private fun setUpRecycler() {
        recyclerCategory.setHasFixedSize(true)
        recyclerCategory.layoutManager = LinearLayoutManager(this)
        recyclerCategory.adapter = CategoryAdapter(catList)

    }
}
