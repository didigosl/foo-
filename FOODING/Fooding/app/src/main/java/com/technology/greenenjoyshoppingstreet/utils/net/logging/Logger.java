package com.technology.greenenjoyshoppingstreet.utils.net.logging;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.Request;
import okio.Buffer;

/**
 * Created by Administrator on 2018/1/1.
 */

public class Logger {
    private static final int JSON_INDENT = 3;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String[] OMITTED_REQUEST;
    private static final String[] OMITTED_RESPONSE;
    private static final String DOUBLE_SEPARATOR;
    private static final int MAX_LONG_SIZE = 110;
    private static final String N = "\n";
    private static final String T = "\t";

    protected Logger() {
        throw new UnsupportedOperationException();
    }

    private static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || "\n".equals(line) || "\t".equals(line) || TextUtils.isEmpty(line.trim());
    }

    static void printJsonRequest(LoggingInterceptor.Builder builder, Request request) {
        String requestBody = LINE_SEPARATOR + "Body:" + LINE_SEPARATOR + bodyToString(request);
        String tag = builder.getTag(true);
        I.log(builder.getType(), tag, "╔══════ Request ════════════════════════════════════════════════════════════════════════");
        logLines(builder.getType(), tag, getRequest(request, builder.getLevel()));
        if (builder.getLevel() == Level.BASIC || builder.getLevel() == Level.BODY) {
            logLines(builder.getType(), tag, requestBody.split(LINE_SEPARATOR));
        }

        I.log(builder.getType(), tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    static void printFileRequest(LoggingInterceptor.Builder builder, Request request) {
        String tag = builder.getTag(true);
        I.log(builder.getType(), tag, "╔══════ Request ════════════════════════════════════════════════════════════════════════");
        logLines(builder.getType(), tag, getRequest(request, builder.getLevel()));
        if (builder.getLevel() == Level.BASIC || builder.getLevel() == Level.BODY) {
            logLines(builder.getType(), tag, OMITTED_REQUEST);
        }

        I.log(builder.getType(), tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    static void printJsonResponse(LoggingInterceptor.Builder builder, long chainMs, boolean isSuccessful, int code, String headers, String bodyString, List<String> segments) {
        String responseBody = LINE_SEPARATOR + "Body:" + LINE_SEPARATOR + getJsonString(bodyString);
        String tag = builder.getTag(false);
        I.log(builder.getType(), tag, "╔══════ Response ═══════════════════════════════════════════════════════════════════════");
        logLines(builder.getType(), tag, getResponse(headers, chainMs, code, isSuccessful, builder.getLevel(), segments));
        if (builder.getLevel() == Level.BASIC || builder.getLevel() == Level.BODY) {
            logLines(builder.getType(), tag, responseBody.split(LINE_SEPARATOR));
        }

        I.log(builder.getType(), tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    static void printFileResponse(LoggingInterceptor.Builder builder, long chainMs, boolean isSuccessful, int code, String headers, List<String> segments) {
        String tag = builder.getTag(false);
        I.log(builder.getType(), tag, "╔══════ Response ═══════════════════════════════════════════════════════════════════════");
        logLines(builder.getType(), tag, getResponse(headers, chainMs, code, isSuccessful, builder.getLevel(), segments));
        logLines(builder.getType(), tag, OMITTED_RESPONSE);
        I.log(builder.getType(), tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    private static String[] getRequest(Request request, Level level) {
        String header = request.headers().toString();
        boolean loggableHeader = level == Level.HEADERS || level == Level.BASIC;
        String message = "URL: " + request.url() + DOUBLE_SEPARATOR + "Method: @" + request.method() + DOUBLE_SEPARATOR + (isEmpty(header) ? "" : (loggableHeader ? "Headers:" + LINE_SEPARATOR + dotHeaders(header) : ""));
        return message.split(LINE_SEPARATOR);
    }

    private static String[] getResponse(String header, long tookMs, int code, boolean isSuccessful, Level level, List<String> segments) {
        boolean loggableHeader = level == Level.HEADERS || level == Level.BASIC;
        String segmentString = slashSegments(segments);
        String message = (!TextUtils.isEmpty(segmentString) ? segmentString + " - " : "") + "is success : " + isSuccessful + " - Received in: " + tookMs + "ms" + DOUBLE_SEPARATOR + "Status Code: " + code + DOUBLE_SEPARATOR + (isEmpty(header) ? "" : (loggableHeader ? "Headers:" + LINE_SEPARATOR + dotHeaders(header) : ""));
        return message.split(LINE_SEPARATOR);
    }

    private static String slashSegments(List<String> segments) {
        StringBuilder segmentString = new StringBuilder();
        Iterator var2 = segments.iterator();

        while (var2.hasNext()) {
            String segment = (String) var2.next();
            segmentString.append("/").append(segment);
        }

        return segmentString.toString();
    }

    private static String dotHeaders(String header) {
        String[] headers = header.split(LINE_SEPARATOR);
        StringBuilder builder = new StringBuilder();
        String[] var3 = headers;
        int var4 = headers.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            builder.append("- ").append(item).append("\n");
        }

        return builder.toString();
    }

    private static void logLines(int type, String tag, String[] lines) {
        String[] var3 = lines;
        int var4 = lines.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String line = var3[var5];
            int lineLength = line.length();

            for (int i = 0; i <= lineLength / 110; ++i) {
                int start = i * 110;
                int end = (i + 1) * 110;
                end = end > line.length() ? line.length() : end;
                I.log(type, tag, "║ " + line.substring(start, end));
            }
        }

    }

    private static String bodyToString(Request request) {
        try {
            Request e = request.newBuilder().build();
            Buffer buffer = new Buffer();
            if (e.body() == null) {
                return "";
            } else {
                e.body().writeTo(buffer);
                return getJsonString(buffer.readUtf8());
            }
        } catch (IOException var3) {
            return "{\"err\": \"" + var3.getMessage() + "\"}";
        }
    }

    static String getJsonString(String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject e = new JSONObject(msg);
                message = e.toString(3);
            } else if (msg.startsWith("[")) {
                JSONArray e1 = new JSONArray(msg);
                message = e1.toString(3);
            } else {
                message = msg;
            }
        } catch (JSONException var3) {
            message = msg;
        }

        return message;
    }

    static {
        OMITTED_REQUEST = new String[]{LINE_SEPARATOR, "Omitted request body"};
        OMITTED_RESPONSE = new String[]{LINE_SEPARATOR, "Omitted response body"};
        DOUBLE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR;
    }
}
