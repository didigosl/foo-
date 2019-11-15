package com.technology.greenenjoyshoppingstreet.utils.net.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.technology.greenenjoyshoppingstreet.BaseApplication;
import com.technology.greenenjoyshoppingstreet.BuildConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.Canceller;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.NetResponseInfo;
import com.technology.greenenjoyshoppingstreet.utils.net.logging.Level;
import com.technology.greenenjoyshoppingstreet.utils.net.logging.LoggingInterceptor;
import com.technology.greenenjoyshoppingstreet.utils.net.parameter.NetParameter;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.PersistentCookieStore;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;


/**
 * 用okhttp网络框架实现{@link NetEngine}
 *
 * @version V1.0
 */
public class OkHttpNetEngine implements NetEngine {


    /**
     * The DEBUG TAG.
     */
    public static final String TAG = OkHttpNetEngine.class.getSimpleName();

    /**
     * Instantiates a new Ok http net engine.
     *
     * @param context the context
     */
    public OkHttpNetEngine(Context context) {
        initContext(context);
    }

    /**
     * Add task tag.
     *
     * @param taskTag the task tag
     */
    @Override
    public void addTaskTag(Object taskTag) {
        this.taskTag = taskTag;
    }

    /**
     * 任务TAG
     */
    private Object taskTag;

    /**
     * @version V1.0
     * @Title: Ok http excutor
     * 2016.12.02
     * @Title: Ok http excutor
     */
    private static class OkHttpExcutor {

        /**
         * M ok http client.
         */
        private static final OkHttpClient okHttpClient;
        /**
         * 连接超时.
         */
        private static final int CONNECT_TIME_OUT_MS = 60 * 1000;
        /**
         * 读取超时.
         */
        private static final int READ_TIME_OUT_MS = 60 * 1000;

        private static final String BEARER_PREFIX = "Bearer ";

        static {
            OkHttpClient.Builder builder
                    = new OkHttpClient.Builder();
            builder.readTimeout(READ_TIME_OUT_MS, TimeUnit.MILLISECONDS);
            builder.writeTimeout(READ_TIME_OUT_MS, TimeUnit.MILLISECONDS);
            builder.connectTimeout(CONNECT_TIME_OUT_MS,
                    TimeUnit.MILLISECONDS);
            builder.cookieJar(new CookiesManager());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });


            builder.addInterceptor(new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request(TAG)
                    .response(TAG)
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build());


            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] x509Certificates = new X509Certificate[0];
                        return x509Certificates;
                    }
                }}, new SecureRandom());
                builder.sslSocketFactory(sslContext.getSocketFactory());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            okHttpClient = builder.build();

        }


        /**
         * Cancel task.
         *
         * @param taskTag the task tag
         */
        public static void cancelTask(Object taskTag) {
            if (taskTag != null) {

            }
        }

        /**
         * Request by post net response info.
         *
         * @param params  the params
         * @param taskTag the task tag
         * @param method  the method
         * @return the net response info
         */
        public static NetResponseInfo requestByMethod(NetParameter params, Object taskTag, NetRequestConfig.Method method) {
            NetResponseInfo netEventInfo = new NetResponseInfo();//返回信息
            Request.Builder builder = new Request.Builder();
            ResponseBody responseBody = null;
            try {

                Request request = null;
                switch (method) {
                    case GET:

                        request = builder.url(NetExcutor.createGetUrl(params.getRequestURL(), params.createParameters())).build();
                        break;
                    case DELETE:
                        request = builder.url(params.getRequestURL())
                                .delete(RequestBody.create(MediaType.parse(params.getMediaType()), params.createParameters().toString())).build();
                        break;
                    case POST:
                        Object object = params.createParameters();
                        FormBody.Builder formBuild = new FormBody.Builder();
                        if (object != null && object instanceof List) {

                            List<KeyValuePair> list = (List<KeyValuePair>) object;
                            if (list != null && !list.isEmpty()) {
                                //创建一个FormBody.Builder
                                for (KeyValuePair keyValuePair : list) {
                                    formBuild.add(keyValuePair.getKey(), keyValuePair.getValue());

                                }


                            }
                        }

                        request = builder.url(params.getRequestURL())
                                .post(formBuild.build()).build();
//                        request = builder.url(params.getRequestURL())
//                                .post(RequestBody.create(MediaType.parse(params.getMediaType()), params.createParameters().toString())).build();
                        break;
                    case PUT:

                        request = builder.url(params.getRequestURL())
                                .put(RequestBody.create(MediaType.parse(params.getMediaType()), params.createParameters().toString())).build();
                        break;
                    default:
                        request = builder.url(params.getRequestURL())
                                .post(RequestBody.create(MediaType.parse(params.getMediaType()), params.createParameters().toString())).build();
                        break;


                }
                Call call = okHttpClient.newCall(request);
                netEventInfo.setStartTime(String.valueOf(System.currentTimeMillis()));//网络耗时统计
                Response response = call.execute();
                netEventInfo.setEndTime(String.valueOf(System.currentTimeMillis()));//网络耗时统计
                netEventInfo.setHttpCode(String.valueOf(response.code()));///网络返回状态码
                if (response.isSuccessful()) {
                    netEventInfo.setSuccess(true);
                    responseBody = response.body();
                    if (responseBody != null) {
                        String reponseStr = responseBody.string();

                        if (!TextUtils.isEmpty(reponseStr)) {
                            //网络返回结果解码
                            netEventInfo.setResult(params.decodeResponse(reponseStr));
                        }
                    }


                }
            } catch (Exception e) {

                Log.d(TAG, Arrays.toString(e.getStackTrace()));
                Log.d(TAG, e.toString());
                netEventInfo.setException(e.toString());//记录网络请求错误
            } finally {
                try {
                    responseBody.close();
                } catch (Exception e) {

                }


            }
            return netEventInfo;


        }


    }


    /**
     * Init context.
     *
     * @param context the context
     */
    @Override
    public void initContext(Context context) {

    }

    @Override
    public NetResponseInfo request(NetParameter params, NetRequestConfig.Method method) {
        return OkHttpExcutor.requestByMethod(params, taskTag, method);
    }

    /**
     * Cancel task.
     *
     * @param canceller the canceller
     */
    @Override
    public void cancelTask(Canceller canceller) {
        if (canceller != null) {
            OkHttpExcutor.cancelTask(canceller.getTaskTag());
        }

    }

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

//    static class LoggingInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//
//            Request request = chain.request();
//            long t1 = System.nanoTime();
//            Log.d(TAG, String.format("Sending request %s%s %n%s",
//                    request.url(), request.headers().toString(), stringifyRequestBody(request)));
//
//            Response response = chain.proceed(request);
//
//            long t2 = System.nanoTime();
//            String responseBody = "";
//            if (response != null && response.body() != null) {
//                responseBody = response.body().string();
//            }
//            Log.d(TAG, String.format("Received response for %s in %.1fms%n%s %s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers(),
//                    responseBody));
//            return response;
//        }
//
//        private static String stringifyRequestBody(Request request) {
//            try {
//                final Request copy = request.newBuilder().build();
//                final Buffer buffer = new Buffer();
//                copy.body().writeTo(buffer);
//                return buffer.readUtf8();
//            } catch (final IOException e) {
//                return "did not work";
//            }
//        }
//
//    }

    private static class CookiesManager implements CookieJar {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore
                (BaseApplication.getInstanceContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }

}
