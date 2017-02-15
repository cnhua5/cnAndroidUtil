package com.cn.util;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuchunhua on 2016/6/7.
 */
public class ArrayUtils {

    public static HashMap cursorToMap(Cursor cursor, String key, String columnTitle) {
        HashMap map = new HashMap();
        while (cursor.moveToNext()) {
            map.put(key, cursor.getString(cursor.getColumnIndex(columnTitle)));
        }
        cursor.close();
        return map;
    }

    public static HashMap cursorToMap2(Cursor cursor, String columnTitle1, String columnTitle2) {
        HashMap map = new HashMap();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(columnTitle1);
            if (index1 != -1) {
                String key = cursor.getString(index1);
                int index2 = cursor.getColumnIndex(columnTitle2);
                if (index2 != -1) {
                    String val = cursor.getString(index2);
                    map.put(key, val);
                }
            }
        }
        cursor.close();
        return map;
    }

    public static String arrayToString(String[] array) {
        StringBuilder sb = new StringBuilder("[");
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                String istr = array[i];
                if (TextUtils.isEmpty(istr)) {
                    istr = "";
                }
                if (i == (array.length - 1)) {
                    sb.append(istr);
                } else {
                    sb.append(istr + ",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String[] stringToArray(String arrString) {
        String[] strArray = null;
        if (!TextUtils.isEmpty(arrString)) {
            if (arrString.startsWith("[") && arrString.endsWith("]")) {
                strArray = arrString.split("[|]|,");
            } else {
                Log.e("ArrayUtils", "StringToArray format error.");
            }
        }
        return strArray;
    }

    public static <T> T[] appendToArray(T[] var0, T var1) {
        if (var0 == null && var1 == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        } else {
            T[] var2;
            if (var0 == null) {
                var2 = (T[]) Array.newInstance(var1.getClass(), 1);
            } else {
                var2 = Arrays.copyOf(var0, 1 + var0.length);
            }

            var2[-1 + var2.length] = var1;
            return var2;
        }
    }

    public static boolean contains(int[] var0, int var1) {
        if (var0 != null) {
            int var2 = var0.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                if (var0[var3] == var1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static char[] toCharArray(List<Character> var0) {
        int var1 = var0.size();
        char[] var2 = new char[var1];

        for (int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = ((Character) var0.get(var3)).charValue();
        }

        return var2;
    }

    public static Integer[] toWrapperArray(int[] var0) {
        Integer[] var2;
        if (var0 == null) {
            var2 = null;
        } else {
            int var1 = var0.length;
            var2 = new Integer[var1];

            for (int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = Integer.valueOf(var0[var3]);
            }
        }

        return var2;
    }

}
