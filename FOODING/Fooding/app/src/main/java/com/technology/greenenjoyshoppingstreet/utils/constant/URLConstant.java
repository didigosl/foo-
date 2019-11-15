package com.technology.greenenjoyshoppingstreet.utils.constant;

/**
 * Created by Administrator on 2017/5/22 0022.
 *
 * @version V1.0
 * @date 2017.05.22
 */
public class URLConstant {
    /**
     * Instantiates a new Url constant.
     */
    private URLConstant() {
    }

    //public static final String HOST = "http://foodinn.didigo.es";
    public static final String HOST = "https://foodinn.didigo.es/";
    /**
     * 服务器基地址.
     */
    public static final String BASE_URL = HOST + "/api/";
    /**
     * 获取手机验证码.
     * phone	string	是	手机号码		13800138000
     * code	string	是	验证码		1111
     */
    public static final String GET_PHONE_CODE = BASE_URL + "main/getPhoneCode";
    /**
     * 注册.
     * phone	number	是	手机号码		13800138000
     * code	number	是	验证码		1111
     */
    public static final String REGISTER = BASE_URL + "user/register";
    /**
     * 登录.
     * phone	string	是	手机号码		13800138000
     * code	string	是	验证码		1111
     */
    public static final String LOGIN = BASE_URL + "main/login";
    public static final String INDEX_RECOMMEND = BASE_URL + "index/recommend";

    /**
     * 下一场抢购.
     */
    public static final String FLASH_SALE_NEXT = BASE_URL + "flash_sale/next";
    /**
     * 当前抢购.
     */
    public static final String FLASH_SALE_LIST = BASE_URL + "flash_sale/list";

    /**
     * .
     */
    public static final String ARTICLE_LIST = BASE_URL + "article/list";
    public static final String ARTICLE_GET = BASE_URL + "article/get";
    public static final String FEEDBACK_ADD = BASE_URL + "feedback/add";
    public static final String MONEYLOG_LIST = BASE_URL + "money_log/list";
    public static final String DRAW_CREATE = BASE_URL + "draw/create";
    /**
     * 商品分类列表.
     * parentId	string	否	上级分类id		6
     */
    public static final String CATEGORY_LIST = BASE_URL + "category/list";
    /**
     * 平台热门搜索词.
     */

    public static final String USER_GETMONEY = BASE_URL + "user/getMoney";
    public static final String COUPON_LIST = BASE_URL + "coupon/list";

    /**
     * 用户最新搜索词.
     */
    public static final String USER_KEYWORD_LIST = BASE_URL + "user_keyword/list";

    /**
     * 分类下的商品列表.
     * categoryId	string	是	分类id		12
     */
    public static final String GOODS_SPU_LIST = BASE_URL + "goods_spu/list";
    /**
     * 搜索商品列表.
     * pageSize	string	否	每页数量		20
     * page	string	否	页码		1
     * order	string	否	排序规则	 枚举值 	onsaleDesc
     * keyword	string	是	搜索关键词		2017
     */
    public static final String GGOODS_SPU_SEARCH = BASE_URL + "goods_spu/search";
    /**
     * 商品详情.
     * spu_id	string	是	商品id		5
     */
    public static final String GOODS_SPU_GET = BASE_URL + "goods_spu/get";
    /**
     * 评论列表.
     * spu_id	string	是	商品id		5
     * page	string	否	页码		1
     * pageLimit	string	否	每页数量		20
     */
    public static final String ORDER_COMMENT_LIST = BASE_URL + "order_comment/list";

    public static final String AD_GET = BASE_URL + "ad/get";
    public static final String CART_LIST = BASE_URL + "cart/list";
    public static final String CART_ADD = BASE_URL + "cart/add";
    public static final String USER_GETINFO = BASE_URL + "user/getInfo";
    public static final String ADDRESS_LIST = BASE_URL + "address/list";
    public static final String ADDRESS_UPDATE = BASE_URL + "address/update";
    public static final String AREA_LIST = BASE_URL + "area/list";
    public static final String ORDER_LIST = BASE_URL + "order/list";
    public static final String ORDER_CANCEL = BASE_URL + "order/cancel";
    public static final String ORDER_FINISH = BASE_URL + "order/finish";
    public static final String ADDRESS_DELETE = BASE_URL + "address/delete";
    public static final String CART_RENEW = BASE_URL + "cart/renew";
    public static final String ORDER_CREATE = BASE_URL + "order/create";
    public static final String USER_UPDATE = BASE_URL + "user/update";
    public static final String UPLOAD_AVATAR = BASE_URL + "upload/avatar";
    public static final String KEYWORD_LIST = BASE_URL + "keyword/list";
    public static final String GOODS_SPU_SEARCH = BASE_URL + "goods_spu/search";
    public static final String ORDER_ADDCOMMENT = BASE_URL + "order/addComment";
    public static final String ORDER_GET = BASE_URL + "order/get";
    public static final String MAIN_GETBANKINFO = BASE_URL + "main/getBankInfo";
    public static final String ORDER_READYTOPAY = BASE_URL + "order/readyToPay";
    public static final String ORDER_PAY = BASE_URL + "order/pay";

    public static final String USER_BUYVIP = BASE_URL + "user/buyVip";
    public static final String USER_PAYVIP = BASE_URL + "user/payVip";


    public static final String GET_ABOUT_US = BASE_URL + "article/getAboutUs";
    public static final String ARTICLE_GETREGTERM = BASE_URL + "article/getRegTerm";
    public static final String CART_RENEWRABATE = BASE_URL + "cart/renewRabate";
    public static final String ORDER_PREPARE = BASE_URL + "order/prepare";
    public static final String ORDER_OFFLINEPAY = BASE_URL + "order/offlinePay";
    public static final String ORDER_BUYNOW = BASE_URL + "order/buyNow";


}
