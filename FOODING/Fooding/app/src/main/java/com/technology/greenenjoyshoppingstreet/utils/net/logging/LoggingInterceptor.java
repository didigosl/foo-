package com.technology.greenenjoyshoppingstreet.utils.net.logging;

import android.text.TextUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/1.
 */

public class LoggingInterceptor implements Interceptor {
    private final boolean isDebug;
    private LoggingInterceptor.Builder builder;

    private LoggingInterceptor(LoggingInterceptor.Builder builder) {
        this.builder = builder;
        this.isDebug = builder.isDebug;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (this.builder.getHeaders().size() > 0) {
            Headers requestBody = request.headers();
            Set rContentType = requestBody.names();
            Iterator rSubtype = rContentType.iterator();
            okhttp3.Request.Builder st = request.newBuilder();
            st.headers(this.builder.getHeaders());

            while (rSubtype.hasNext()) {
                String name = (String) rSubtype.next();
                st.addHeader(name, requestBody.get(name));
            }

            request = st.build();
        }

        if (this.isDebug && this.builder.getLevel() != Level.NONE) {
            RequestBody requestBody1 = request.body();
            MediaType rContentType1 = null;
            if (requestBody1 != null) {
                rContentType1 = request.body().contentType();
            }

            String rSubtype1 = null;
            if (rContentType1 != null) {
                rSubtype1 = rContentType1.subtype();
            }

            if (rSubtype1 == null || !rSubtype1.contains("form") && !rSubtype1.contains("json") && !rSubtype1.contains("xml") && !rSubtype1.contains("plain") && !rSubtype1.contains("html")) {
                Logger.printFileRequest(this.builder, request);
            } else {
                Logger.printJsonRequest(this.builder, request);
            }

            long st1 = System.nanoTime();
            Response response = chain.proceed(request);
            List segmentList = ((Request) request.tag()).url().encodedPathSegments();
            long chainMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - st1);
            String header = response.headers().toString();
            int code = response.code();
            boolean isSuccessful = response.isSuccessful();
            ResponseBody responseBody = response.body();
            MediaType contentType = responseBody.contentType();
            String subtype = null;
            if (contentType != null) {
                subtype = contentType.subtype();
            }

            if (subtype == null || !subtype.contains("json") && !subtype.contains("xml") && !subtype.contains("plain") && !subtype.contains("html")) {
                Logger.printFileResponse(this.builder, chainMs, isSuccessful, code, header, segmentList);
                return response;
            } else {
                String bodyString = Logger.getJsonString(responseBody.string());
                Logger.printJsonResponse(this.builder, chainMs, isSuccessful, code, header, bodyString, segmentList);
                ResponseBody body = ResponseBody.create(contentType, bodyString);
                return response.newBuilder().body(body).build();
            }
        } else {
            return chain.proceed(request);
        }
    }

    public static class Builder {
        private static String TAG = "LoggingI";
        private boolean isDebug;
        private int type = 4;
        private String requestTag;
        private String responseTag;
        private Level level;
        private okhttp3.Headers.Builder builder;

        public Builder() {
            this.level = Level.BASIC;
            this.builder = new okhttp3.Headers.Builder();
        }

        int getType() {
            return this.type;
        }

        Level getLevel() {
            return this.level;
        }

        Headers getHeaders() {
            return this.builder.build();
        }

        String getTag(boolean isRequest) {
            return isRequest ? (TextUtils.isEmpty(this.requestTag) ? TAG : this.requestTag) : (TextUtils.isEmpty(this.responseTag) ? TAG : this.responseTag);
        }

        public LoggingInterceptor.Builder addHeader(String name, String value) {
            this.builder.set(name, value);
            return this;
        }

        public LoggingInterceptor.Builder setLevel(Level level) {
            this.level = level;
            return this;
        }

        public LoggingInterceptor.Builder tag(String tag) {
            TAG = tag;
            return this;
        }

        public LoggingInterceptor.Builder request(String tag) {
            this.requestTag = tag;
            return this;
        }

        public LoggingInterceptor.Builder response(String tag) {
            this.responseTag = tag;
            return this;
        }

        public LoggingInterceptor.Builder loggable(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public LoggingInterceptor.Builder log(int type) {
            this.type = type;
            return this;
        }

        public LoggingInterceptor build() {
            return new LoggingInterceptor(this);
        }
    }
}