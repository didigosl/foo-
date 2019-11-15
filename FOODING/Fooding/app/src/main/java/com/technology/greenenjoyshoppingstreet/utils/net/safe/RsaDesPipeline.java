package com.technology.greenenjoyshoppingstreet.utils.net.safe;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RsaDesPipeline implements SafePipeline {

    @Override
    public String encode(String source) {
        return source;

    }

    @Override
    public String decode(String decodedText) {
        return decodedText;
    }


}
