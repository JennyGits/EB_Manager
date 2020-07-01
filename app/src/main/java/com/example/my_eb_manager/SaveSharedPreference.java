package com.example.my_eb_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_ID = "id";
    static final String PREF_PASSWORD = "pw";
    static final String PREF_LIBRARY = "library";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    // 계정 정보 저장
    public static void setLoginInfo(Context ctx, final String id, final String pw) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_ID, id);
        editor.putString(PREF_PASSWORD, pw);
        editor.commit();
    }

    // 도서관 정보 저장
    public static void setLibrary(Context ctx, final String lib) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LIBRARY, lib);
        editor.commit();
    }

    //-- 저장된 정보 가져오기 --//
    // username
    public static String getID(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_ID, "");
    }
    // password
    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }
    // library
    public static String getLibrary(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_LIBRARY, "");
    }

    // 로그아웃
    public static void clearLoginInfo(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
