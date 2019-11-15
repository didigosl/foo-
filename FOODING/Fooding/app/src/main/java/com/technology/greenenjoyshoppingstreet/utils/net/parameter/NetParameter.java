package com.technology.greenenjoyshoppingstreet.utils.net.parameter;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/3/1 0001.
 *
 * @version V1.0
 *  2017.05.11
 */
public  class  NetParameter {
    private Object parameter  = null;
    private String url;
    private String mediaType = "application/json";

    public NetParameter(String url,Object parameter) {
        this.parameter = parameter;
        this.url = url;
   }

    public NetParameter(String url,Object parameter,  String mediaType) {
        this.parameter = parameter;
        this.url = url;
        this.mediaType = mediaType;
    }

    public boolean isReadExecute(){
        return parameter!=null;
    }
    /**
     * Create parameters list.
     *
     * @return the list
     */
    public Object createParameters(){
        return parameter;
    }

    public String getRequestURL() {
        return url;
    }

    public String getMediaType() {
        return mediaType;
    }

    /**
     * Decode response string.
     *
     * @param response the response
     * @return the string
     */
    public String decodeResponse(String response) {
        try {

            return URLDecoder.decode(response.replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;
    }

}
