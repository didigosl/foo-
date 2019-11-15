package com.technology.greenenjoyshoppingstreet.ktnewui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.duma.ld.baselibarary.model.HttpResModel
import com.lzy.okgo.OkGo
import com.technology.greenenjoyshoppingstreet.BaseActivity
import com.technology.greenenjoyshoppingstreet.R
import com.technology.greenenjoyshoppingstreet.ktnewui.adapter.ProductAdapter
import com.technology.greenenjoyshoppingstreet.ktnewui.api.IShopClient
import com.technology.greenenjoyshoppingstreet.ktnewui.api.LABEL
import com.technology.greenenjoyshoppingstreet.ktnewui.api.PAGE_LIMIT
import com.technology.greenenjoyshoppingstreet.ktnewui.api.ServiceGenerator
import com.technology.greenenjoyshoppingstreet.ktnewui.model.Ad
import com.technology.greenenjoyshoppingstreet.ktnewui.model.GoodSpu
import com.technology.greenenjoyshoppingstreet.main.SearchActivity
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel
import com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_LIST
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger
import kotlinx.android.synthetic.main.activity_home_kt.*
import kotlinx.coroutines.*
import org.jetbrains.anko.coroutines.experimental.asReference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class HomeKtActivity : BaseActivity()  {

    val adList:MutableList<Ad.Data> = ArrayList()
    val recommendList:MutableList<GoodSpu.Data.Good> = ArrayList()
    val cartList:MutableList<ShoppingCartModel.ListBean> = ArrayList()

    var adapter:ProductAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_kt)

        iv_search.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }

        if (UserInfoManger.isLogin()){
            getCartList()
        }else{
            getAd()
        }
    }

    fun getCartList() {
        OkGo.post<HttpResModel<ShoppingCartModel>>(CART_LIST)
                .tag(this)
                .execute(object : MyJsonCallback<HttpResModel<ShoppingCartModel>>() {
                    override fun onJsonSuccess(respons: com.lzy.okgo.model.Response<HttpResModel<ShoppingCartModel>>, goodsModelHttpResModel: HttpResModel<ShoppingCartModel>) {
                        cartList.clear()
                        cartList.addAll(respons.body().data.list)
                        getAd()
                    }
                })
    }

    fun getAd(){

        val client: IShopClient = ServiceGenerator.create()
        val call = client.getAdList("index")
        call.enqueue(object : Callback<Ad> {
            override fun onFailure(call: Call<Ad>, t: Throwable) {
            }
            override fun onResponse(call: Call<Ad>, response: Response<Ad>) {
                if (response.isSuccessful){
                    val data = response.body()?.data
                    data?.let {
                        adList.clear()
                        adList.addAll(data)
                        getRecommend()
                    }
                }
            }
        })
    }

    fun getRecommend(){
        val client = ServiceGenerator.create()
        val call = client.getGoodListLabel(LABEL,"1", PAGE_LIMIT)
        call.enqueue(object : Callback<GoodSpu>{
            override fun onFailure(call: Call<GoodSpu>, t: Throwable) {
            }
            override fun onResponse(call: Call<GoodSpu>, response: Response<GoodSpu>) {
                if (response.isSuccessful){
                    val data = response.body()?.data
                    data?.let {
                        recommendList.clear()
                        recommendList.addAll(data.list)
                        if (adapter != null){
                            adapter!!.notifyDataSetChanged()
                        }else{
                            createRecycler()
                        }

                    }
                }
            }
        })
    }

    fun createRecycler(){
        recyclerHome.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this,2)
            layoutManager.spanSizeLookup = (object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) {2}else{1}
                }
            })
        recyclerHome.layoutManager = layoutManager

        adapter = ProductAdapter(this,cartList,adList,recommendList)
        recyclerHome.adapter = adapter
    }
}