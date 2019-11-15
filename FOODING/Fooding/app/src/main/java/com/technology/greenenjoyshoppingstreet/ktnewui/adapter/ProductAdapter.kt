package com.technology.greenenjoyshoppingstreet.ktnewui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.duma.ld.baselibarary.model.HttpResModel
import com.duma.ld.baselibarary.util.Constants
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.technology.greenenjoyshoppingstreet.R
import com.technology.greenenjoyshoppingstreet.ktnewui.HomeKtActivity
import com.technology.greenenjoyshoppingstreet.ktnewui.model.Ad
import com.technology.greenenjoyshoppingstreet.ktnewui.model.GoodSpu
import com.technology.greenenjoyshoppingstreet.ktnewui.util.*
import com.technology.greenenjoyshoppingstreet.login.LoginActivity
import com.technology.greenenjoyshoppingstreet.newui.api.ServiceGenerator
import com.technology.greenenjoyshoppingstreet.newui.api.service.ICartClient
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback
import com.technology.greenenjoyshoppingstreet.newui.model.CartHttpModel
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel
import com.technology.greenenjoyshoppingstreet.newui.model.cartShop.CartShop
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsDetailActivity
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsListActivity
import com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEW
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger
import kotlinx.android.synthetic.main.item_ad_kt.view.*
import kotlinx.android.synthetic.main.item_recommend_kt.view.*
import retrofit2.Call
import retrofit2.Callback
import java.util.LinkedHashMap
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import kotlin.collections.indices
import kotlin.collections.set

class ProductAdapter(private val context: Context, private val cartList:MutableList<ShoppingCartModel.ListBean>?, private val dataA: MutableList<Ad.Data>, private val dataR:MutableList<GoodSpu.Data.Good>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val AD = 1
    private val RECOMMEND = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType == AD){
            AdViewHolder(parent.inflate(R.layout.item_ad_kt))
        }else{
            RecommendViewHolder(parent.inflate(R.layout.item_recommend_kt))
        }
    }

    override fun getItemCount(): Int {
        return dataR.size + 1
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == 0){
            AD
        }else{
            RECOMMEND
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when(getItemViewType(pos)){
            AD -> {
                val aHolder = holder as AdViewHolder
                for (item in dataA) aHolder.bindView(item)
            }
            RECOMMEND -> {
                val rHolder = holder as RecommendViewHolder
                val inCart = checkInCart(dataR[pos-1].spuId)
                val num = getNum(dataR[pos-1].spuId)
                rHolder.bindView(dataR[pos-1],inCart,num)
            }
        }
    }

    private fun checkInCart(spuProduct:String) : Boolean{
        var result = false
        cartList?.let {
            for (cart in cartList){
                if (cart.spuId == spuProduct){
                    result = true
                }
            }
        }
        return  result
    }

    private fun getNum(spuProduct:String): String{
        var num = "1"
        cartList?.let {
            for (cart in cartList){
                if (cart.spuId == spuProduct){
                    num = cart.num
                }
            }
        }
        return num
    }

    inner class RecommendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(recommend: GoodSpu.Data.Good, inCart: Boolean, num: String) {
            Glide
                    .with(itemView.context)
                    .load(recommend.cover)
                    .into(itemView.image_recommend)

            itemView.name_recommend.text = recommend.spuName
            val price = "${recommend.price} €"
            itemView.price_recommend.text = price

            if (inCart){
                showView(LAYOUT)
                itemView.num.text = num

                var number = num.toInt()

                if (number < 2){
                    showButton(IMAGEBUTTON)
                }else{
                    showButton(MINUSBUTTON)
                }

                itemView.ib_delete.setOnClickListener {
                    rest(recommend.skus[0].skuId,0)
                }

                itemView.btn_plus.setOnClickListener {
                    addList("1",recommend.skus[0].skuId)

                }
                itemView.btn_minus.setOnClickListener {
                    number--
                    rest(recommend.skus[0].skuId,number)
                }
            }else{
                showView(BUTTON)
                itemView.btnRecommend.setOnClickListener {
                    if (UserInfoManger.isLogin()){
                        showView(LAYOUT)
                        addList("1",recommend.skus[0].skuId)
                    }else{
                        context.startActivity(Intent(context,LoginActivity::class.java))
                    }
                }
            }

            itemView.setOnClickListener {
                val intent = Intent(context,GoodsDetailActivity::class.java)
                intent.putExtra(Constants.intent_id, recommend.spuId)
                context.startActivity(intent)
            }
        }

        private fun showView(view:Int){
            when(view){
                BUTTON -> {
                    itemView.frBtnLayout.visibility = View.VISIBLE
                    itemView.lyManageCart.visibility = View.GONE
                }
                LAYOUT -> {
                    itemView.frBtnLayout.visibility = View.GONE
                    itemView.lyManageCart.visibility = View.VISIBLE
                }
            }
        }

        private fun showButton(view:Int){
            when(view){
                MINUSBUTTON -> {
                    itemView.btn_minus.visibility = View.VISIBLE
                    itemView.ib_delete.visibility = View.GONE
                }
                IMAGEBUTTON -> {
                    itemView.btn_minus.visibility = View.GONE
                    itemView.ib_delete.visibility = View.VISIBLE
                }
            }
        }
    }

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(ad:Ad.Data){
            itemView.v_flipper.addImage(ad.img,itemView.context)

            itemView.setOnClickListener {
                val intent = Intent(context, GoodsListActivity::class.java)
                intent.putExtra(Constants.intent_id, ad.adId)
                intent.putExtra(Constants.intent_Name, ad.adName)
                context.startActivity(intent)
            }
        }
    }

    private fun addList(num:String, skuId:String){
        val client = ServiceGenerator.createService(ICartClient::class.java)

        val map1 = LinkedHashMap<String, String>()
        map1["num"] = num
        map1["skuId"] = skuId
        val signTime = UserInfoManger.getSignRetrofit(map1)
        val sign = signTime[0]
        val time = signTime[1]
        val token = signTime[2]

        val call = client.addCart(num, sign, skuId, time, token)
        call.enqueue(object : Callback<CartShop> {
            override fun onResponse(call: Call<CartShop>, response: retrofit2.Response<CartShop>) {
                if (response.isSuccessful) {
                    when {
                        response.body()!!.status == "SUCCESSS" -> {
                            (context as HomeKtActivity).getCartList()
                        }
                        response.body()!!.status == "fail" -> Toast.makeText(context, response.body()!!.msg, Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(context, "错误", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<CartShop>, t: Throwable) {
                Log.e("GoodListFragment", "onFailure: ", t)
            }
        })
    }

    private fun rest(skuId:String,num:Int){
        val data:MutableList<ShoppingCartModel.ListBean> = ArrayList(cartList)

        for (x in cartList!!.indices){
            if (cartList[x].skuId == skuId){
                if (num == 0){
                    data.removeAt(x)
                }else{
                    data[x].num = num.toString()
                }
            }
        }

        val list:MutableList<CartHttpModel> = ArrayList()
        var cartHttpModel: CartHttpModel
        for (i in data.indices) {
            cartHttpModel = CartHttpModel(data[i].cartId, data[i].num, data[i].spuId)
            list.add(cartHttpModel)
        }
        OkGo.post<HttpResModel<Void>>(CART_RENEW)
                .tag(this)
                .params("data", Gson().toJson(list))
                .execute(object : MyJsonCallback<HttpResModel<Void>>() {
                    override fun onJsonSuccess(respons: Response<HttpResModel<Void>>, goodsModelHttpResModel: HttpResModel<Void>) {
                        (context as HomeKtActivity).getCartList()
                    }
                })
    }

}