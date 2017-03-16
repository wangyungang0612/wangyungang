package com.example.administrator.wangyungang;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import datebase.MyOpenHelper;

public class MySQliteActivity extends Activity {

    public SQLiteDatabase db;
    public MyOpenHelper myOpenHelper;
    @InjectView(R.id.insert1)
    Button insert1;
    @InjectView(R.id.insert2)
    Button insert2;
    @InjectView(R.id.delete1)
    Button delete1;
    @InjectView(R.id.delete2)
    Button delete2;
    @InjectView(R.id.update1)
    Button update1;
    @InjectView(R.id.update2)
    Button update2;
    @InjectView(R.id.quary1)
    Button quary1;
    @InjectView(R.id.quary2)
    Button quary2;
    @InjectView(R.id.quary3)
    Button quary3;
    @InjectView(R.id.replace)
    Button replace;
    @InjectView(R.id.Content_quary)
    Button ContentQuary;
    @InjectView(R.id.Content_insert)
    Button ContentInsert;
    @InjectView(R.id.Content_delete)
    Button ContentDelete;
    @InjectView(R.id.Content_update)
    Button ContentUpdate;
    @InjectView(R.id.Content_quary2)
    Button ContentQuary2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sqlite);
        ButterKnife.inject(this);
        myOpenHelper = new MyOpenHelper(MySQliteActivity.this);
        //  db = myOpenHelper.getReadableDatabase();
        db = myOpenHelper.getWritableDatabase();

    }




    @TargetApi(Build.VERSION_CODES.M)
    @OnClick({R.id.insert1, R.id.insert2, R.id.delete1, R.id.delete2, R.id.update1, R.id.update2,
            R.id.quary1, R.id.quary2, R.id.quary3, R.id.replace, R.id.Content_quary, R.id.Content_insert, R.id.Content_delete, R.id.Content_update, R.id.Content_quary2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert1:
//              String sql_insertString = "insert into info (name , pwd) values ('tom','123');";
//              db.execSQL(sql_insertString);
                String sql_insertString = "insert into info (name , pwd) values (?,?);";
                String[] array = new String[]{"wang", "111"};
                db.execSQL(sql_insertString, array);
                break;
            case R.id.insert2:
                ContentValues cv = new ContentValues();
                cv.put("name", "liang");
                cv.put("pwd", "222");
                db.insert("info", null, cv);
                break;
            case R.id.delete1:
//              String sql_deleteString = "delete from info where name = 'tom';";
//              db.execSQL(sql_deleteString);
                String sql_deleteString = "delete from info where name = ?;";
                String[] array2 = new String[]{"wang"};
                db.execSQL(sql_deleteString, array2);
                break;
            case R.id.delete2:
                String whereClause = "name = ?";
                String[] whereArgs = new String[]{"liang"};
                db.delete("info", whereClause, whereArgs);
                break;
            case R.id.update1:
//                String sql_updateString = "update info set pwd = '000' where name = 'wang';";
//                db.execSQL(sql_updateString);
                //               db.execSQL(sql_updateString);

                //               String sql_updateString = "update info set pwd = ? where name = ?;";
//                String[] array3 = new String[] {"444", "wang" };
                String sql_updateString = "update info set pwd = '555' where name = ?;";
                String[] array3 = new String[]{"wang"};
                db.execSQL(sql_updateString, array3);
                break;
            case R.id.update2:
                ContentValues cv2 = new ContentValues();
                cv2.put("pwd", "333");
                String whereClause2 = "name = ?";
                String[] whereArgs2 = new String[]{"wang"};
                db.update("info", cv2, whereClause2, whereArgs2);
                break;
            case R.id.quary1:
                Cursor cursor = db.rawQuery("select * from info where name = ?",
                        new String[]{"wang"});
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);
                        String name = cursor.getString(cursor
                                .getColumnIndex("name"));
                        String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
//                        System.out.println(name);
//                        System.out.println(pwd);
                        Log.e("WWW", name);
                        Log.e("WWW", pwd);
                    }
                }
                break;
            case R.id.quary2:
                Cursor cursor2 = db.query("info", null, null, null, null, null,
                        null);
                if (cursor2.moveToFirst()) {
                    for (int i = 0; i < cursor2.getCount(); i++) {
                        cursor2.moveToPosition(i);
                        String name = cursor2.getString(cursor2
                                .getColumnIndex("name"));
                        String pwd = cursor2.getString(cursor2
                                .getColumnIndex("pwd"));
                        System.out.println(name);
                        System.out.println(pwd);
                        Log.e("WWW", name);
                        Log.e("WWW", pwd);
                    }
                }
                break;
            case R.id.quary3:
                Log.e("WWW", "11");
                ContentResolver cr = MySQliteActivity.this.getContentResolver();
                Cursor cursor3 = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        null, null, null);
                if (cursor3.moveToFirst()) {
                    for (int i = 0; i < cursor3.getCount(); i++) {
                        cursor3.moveToPosition(i);
                        String numberString = cursor3
                                .getString(cursor3
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String nameString = cursor3
                                .getString(cursor3
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        System.out.println(nameString + "\t" + numberString);
                        Log.e("WWW", nameString);
                        Log.e("WWW", numberString);
                    }

                }
                break;
            case R.id.replace:
                db.beginTransaction();//开启事务
                try {
                    db.delete("info", null, null);
                    if (true) {
                        //在这里手动抛出一个异常，让事务失败
                        // throw new NullPointerException();
                    }
                    ContentValues Values = new ContentValues();
                    Values.put("name", "zhou");
                    Values.put("pwd", "999");
                    db.insert("info", null, Values);
                    db.setTransactionSuccessful();//事务已经执行成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();//结束事务
                }
                break;
            case R.id.Content_quary:
                Uri uri = Uri
                        .parse("content://com.example.administrator.wangyungang.provider.myprovider/info");
                ContentResolver cr2 = MySQliteActivity.this.getContentResolver();
                Cursor cursor4 = cr2.query(uri, null, null, null, null);
                if (cursor4.moveToFirst()) {
                    for (int i = 0; i < cursor4.getCount(); i++) {
                        cursor4.moveToPosition(i);
                        String nameString = cursor4.getString(cursor4
                                .getColumnIndex("name"));
                        String pwdString = cursor4.getString(cursor4
                                .getColumnIndex("pwd"));
                        Log.e("WWW", nameString);
                        Log.e("WWW", pwdString);
                        // System.out.println(nameString + "    " + pwdString);
                    }
                }
                break;
            case R.id.Content_insert:
                Uri uri3 = Uri
                        .parse("content://com.example.administrator.wangyungang.provider.myprovider/info/info");
                ContentResolver cr4 = MySQliteActivity.this.getContentResolver();
                cr4.insert(uri3, null);
                break;
            case R.id.Content_delete:
                Uri uri2 = Uri
                        .parse("content://com.example.administrator.wangyungang.provider.myprovider/info/yun");
                ContentResolver cr3 = MySQliteActivity.this.getContentResolver();
                cr3.delete(uri2, null, null);

                break;
            case R.id.Content_update:
                Uri uri4 = Uri
                        .parse("content://com.example.administrator.wangyungang.provider.myprovider/info/yun");
                ContentResolver cr5 = MySQliteActivity.this.getContentResolver();
                cr5.update(uri4, null, null, null);

                break;
            case R.id.Content_quary2:
                Uri uri5 = Uri
                        .parse("content://com.example.administrator.wangyungang.provider.myprovider/info/yun");
                ContentResolver cr6 = MySQliteActivity.this.getContentResolver();
                cr6.query(uri5, null, null, null, null);

                break;
        }
    }
}
