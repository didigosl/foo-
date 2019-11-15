package com.duma.ld.baselibarary.base.Http;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.model.HttpSimpleResModel;
import com.duma.ld.baselibarary.util.LogUtil;
import com.duma.ld.baselibarary.util.Ts;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCallback<T> extends AbsCallback<T> {
    protected abstract void onJsonSuccess(com.lzy.okgo.model.Response<T> respons, T t);

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        onJsonSuccess(response, response.body());
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        if (response.code() == 200) {
            //前面返回的请求成功后 code==500 要读取message
            Ts.show(response.getException().getMessage());
//            switch (response.getException().getMessage()) {
//                case "100":
//                    Ts.show("登录已失效!请重新登录");
//                    break;
//                default:
//                    Ts.show("" + response.getException().getMessage());
//                    break;
//            }
        } else {
            Ts.show("网络请求错误!" + response.code() + " msg: " + response.message());
        }
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
            response.close();
            throw new IllegalStateException("没有填写泛型参数");
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        //获取格式化的数据
        HttpResModel httpResModel = null;
        if (typeArgument == Void.class) {
            HttpSimpleResModel simpleResponse = Convert.fromJson(jsonReader, HttpSimpleResModel.class);
            httpResModel = new HttpResModel();
            httpResModel.setCode(simpleResponse.getCode());
            httpResModel.setStatus(simpleResponse.getStatus());
            response.close();
        } else if (rawType == HttpResModel.class) {
            try {
                httpResModel = new Gson().fromJson(jsonReader, type);
            } catch (JsonIOException | JsonSyntaxException e) {
                LogUtil.e(e.getLocalizedMessage());
                throw new IllegalStateException("json 解析错误!可能数据类型不匹配");
            } finally {
                response.close();
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }

        String code = httpResModel.getCode();
        switch (code) {
            case "0":
                return (T) httpResModel;
//            case "400":
//                String msg = httpResModel.getMsg();
//                if (msg == null) {
//                    throw new IllegalStateException("服务器异常!");
//                } else {
//                    throw new IllegalStateException(msg);
//                }
            default:
                throw new IllegalStateException(httpResModel.getStatus());
        }
    }

}