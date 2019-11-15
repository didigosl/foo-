package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;

import org.acra.config.ACRAConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MySenderFactroy implements ReportSenderFactory {

    // NB requires a no arg constructor.

    public ReportSender create(Context context, ACRAConfiguration config) {
        return new MyReportSender(context, config);
    }
}
