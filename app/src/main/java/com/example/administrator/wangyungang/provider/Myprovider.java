package com.example.administrator.wangyungang.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import datebase.MyOpenHelper;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class Myprovider extends ContentProvider {
    private SQLiteDatabase database;
    private static final UriMatcher MATCHER = new UriMatcher(-1);
    private static final int CODE_1 = 1;
    private static final int CODE_2 = 2;

    static {
        MATCHER.addURI("com.example.administrator.wangyungang.provider.myprovider", "info", 1);
        MATCHER.addURI("com.example.administrator.wangyungang.provider.myprovider", "info/*", 2);
    }
    public Myprovider() {
    }
    @Override
    public boolean onCreate() {
        this.database = (new MyOpenHelper(this.getContext())).getReadableDatabase();
        return false;
    }

    public Uri insert(Uri arg0, ContentValues arg1) {
        switch(MATCHER.match(arg0)) {
            case 1:
                long rowid = this.database.insert("info", (String)null, arg1);
                Uri insertUri = ContentUris.withAppendedId(arg0, rowid);
                this.getContext().getContentResolver().notifyChange(arg0, (ContentObserver)null);
                return insertUri;
            case 2:
                String nameString = (String)arg0.getPathSegments().get(1);
                String pwdString = (String)arg0.getPathSegments().get(1);
                ContentValues values = new ContentValues();
                values.put("name", "yun");
                values.put("pwd", "222");
                this.database.insert("info", (String)null, values);
            default:
                new IllegalArgumentException("Unkwon Uri:" + arg0.toString());
                return null;
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        System.out.println("MATCHER.match(uri) = " + MATCHER.match(uri));
        switch(MATCHER.match(uri)) {
            case 1:
                Cursor cursor = this.database.query("info", projection, selection, selectionArgs, (String)null, (String)null, sortOrder);
                return cursor;
            case 2:
                String nameString1 = (String)uri.getPathSegments().get(1);
                String pwdString1 = (String)uri.getPathSegments().get(1);
                Cursor cursor1 = this.database.query("info", projection, "name = ?", new String[]{nameString1}, (String)null, (String)null, sortOrder);
                if(cursor1.moveToFirst()) {
                    for(int i = 0; i < cursor1.getCount(); ++i) {
                        cursor1.moveToPosition(i);
                        String nameString = cursor1.getString(cursor1.getColumnIndex("name"));
                        String pwdString = cursor1.getString(cursor1.getColumnIndex("pwd"));
                        System.out.println(nameString + "    " + pwdString);
                        Log.e("WWW", nameString);
                        Log.e("WWW", pwdString);
                    }
                }
                break;
            default:
                new IllegalArgumentException("Unkwon Uri:" + uri.toString());
        }

        return null;
    }

    public int update(Uri uri, ContentValues arg1, String arg2, String[] arg3) {
        switch(MATCHER.match(uri)) {
            case 1:
                this.database.update("info", arg1, arg2, arg3);
                break;
            case 2:
                String nameString = (String)uri.getPathSegments().get(1);
                ContentValues values = new ContentValues();
                values.put("pwd", "444");
                this.database.update("info", values, "name = ?", new String[]{nameString});
                break;
            default:
                new IllegalArgumentException("Unkwon Uri:" + uri.toString());
        }

        return 0;
    }

    public int delete(Uri arg0, String arg1, String[] arg2) {
        switch(MATCHER.match(arg0)) {
            case 1:
                this.database.delete("info", arg1, arg2);
                break;
            case 2:
                String name = (String)arg0.getPathSegments().get(1);
                this.database.delete("info", "name = ?", new String[]{name});
                break;
            default:
                new IllegalArgumentException("Unkwon Uri:" + arg0.toString());
        }

        return 0;
    }

    public String getType(Uri arg0) {
        switch(MATCHER.match(arg0)) {
            case 1:
                return "vnd.android.cursor.dir/info";
            default:
                new IllegalArgumentException("Unkwon Uri:" + arg0.toString());
                return null;
        }
    }
}
