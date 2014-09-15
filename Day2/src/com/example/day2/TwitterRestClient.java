package com.example.day2;

import java.util.Random;

import android.util.Log;

import com.loopj.android.http.*;

public class TwitterRestClient {
  private static final String BASE_URL = "http://yimin.dp/";

  private static AsyncHttpClient client = new AsyncHttpClient();

  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	  Log.i("mine", "start get");
      client.get(getAbsoluteUrl(url), params, responseHandler);
      Log.i("mine", "over get");
  }

  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
      client.post(getAbsoluteUrl(url), params, responseHandler);
  }

  private static String getAbsoluteUrl(String relativeUrl) {
      return BASE_URL + relativeUrl;
  }
}