package com.technology.greenenjoyshoppingstreet.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/5/20.
 *
 * @version V1.0
 * @date 2017.05.22
 */
public class InputCheckUtil {
    /** Phone number. */
    private static final String PHONE_NUMBER = "[0-9]{11}";
    /** Password. */
    private static final String PASSWORD = "([_0-9a-zA-Z]|[^ \\f\\n\\r\\t\\v]){6,16}";
    /** Symbol. */
    private static final String SYMBOL = "[^ \\f\\n\\r\\t\\v]";
    /** Name. */
    private static final String NAME = "[\\u4E00-\\u9FA5]+";


    /**
     * Instantiates a new Input check util.
     */
    private InputCheckUtil() {
    }

    /**
     * Check phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return phoneNumber.matches(PHONE_NUMBER);
        }
        return false;

    }


    /**
     * Check name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean checkName(String name) {
        if (!TextUtils.isEmpty(name)) {
            return name.matches(NAME);
        }
        return false;
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean checkPassword(String password) {
        if (!TextUtils.isEmpty(password)) {
            return password.matches(PASSWORD);
        }
        return false;
    }

    /**
     * Checksymbol boolean.
     *
     * @param symbol the symbol
     * @return the boolean
     */
    public static boolean checksymbol(String symbol) {
        if (!TextUtils.isEmpty(symbol)) {
            return symbol.matches(SYMBOL);
        }
        return false;

    }
}
