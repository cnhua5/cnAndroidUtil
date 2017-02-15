package com.cn.util.databse;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

import ninja.com.google.arch.util.CnLogUtil;

/**
 * Created by wuchunhua on 2016/6/22.
 */
public class SQLdm {

    public static SQLiteDatabase openDatabase(String filePath) {
        File jhPath = new File(filePath);
        //查看数据库文件是否存在
        if (jhPath.exists()) {
            CnLogUtil.printLogInfo("存在数据库(" + filePath + ")");
            //存在则直接返回打开的数据库
            try {
                return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            CnLogUtil.printLogInfo("数据库文件不存在（" + filePath + "）");
        }
        return null;
    }

    public static SQLiteDatabase openDatabase(File dataFile) {
        //查看数据库文件是否存在
        if (dataFile.exists()) {
            CnLogUtil.printLogInfo("存在数据库(" + dataFile.getPath() + ")");
            //存在则直接返回打开的数据库
            try {
                return SQLiteDatabase.openOrCreateDatabase(dataFile, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            CnLogUtil.printLogInfo("数据库文件不存在（" + dataFile.getPath() + "）");
        }
        return null;
    }

    public static Cursor getById(SQLiteDatabase mDB, String TABLE_NAME, long id, String[] columns) {
        String selection = "_id=?";
        String[] selectionArgs = new String[]{Long.toString(id)};
        return mDB.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public static Cursor getBy(SQLiteDatabase mDB, String TABLE_NAME, String title, String titleVal) {
        String selection = title + "=?";
        String[] selectionArgs = new String[]{titleVal};
        return mDB.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }

    public static Cursor getAll(SQLiteDatabase mDB, String TABLE_NAME) {
        return mDB.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
