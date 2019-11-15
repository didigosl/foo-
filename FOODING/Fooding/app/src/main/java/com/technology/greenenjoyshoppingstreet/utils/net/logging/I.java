package com.technology.greenenjoyshoppingstreet.utils.net.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/1/1.
 */

public class I {
    protected I() {
        throw new UnsupportedOperationException();
    }

    static void log(int type, String tag, String msg) {
        Logger logger = Logger.getLogger(tag);
        switch (type) {
            case 4:
                logger.log(Level.INFO, msg);
                break;
            default:
                logger.log(Level.WARNING, msg);
        }

    }
}
