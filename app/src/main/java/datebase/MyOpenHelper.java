package datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper{
	public static final String DBNAME = "login.db";//数据库名称
	public static final int version = 2;//数据库版本
	//public static final String TANAME = "info";//数据库表名称
	public MyOpenHelper(Context context) {
		super(context, DBNAME, null, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlString = "create table info(name varchar(20) not null,pwd varchar(60) not null);";
		String sqlString2 = "create table info2(name2 varchar(20) not null,pwd2 varchar(60) not null);";
		db.execSQL(sqlString);//执行SQL语句
		db.execSQL(sqlString2);//执行SQL语句
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//当数据库升级的时候调用（先删除表再创建表）
//		String sqlString ="drop table if exists" + "info";//SQL语句
//		db.execSQL(sqlString);//执行SQL语句
//		this.onCreate(db);//创建表
		db.execSQL("DROP TABLE IF EXISTS " + "info");
		db.execSQL("DROP TABLE IF EXISTS " + "info2");
		onCreate(db);
	}

}
