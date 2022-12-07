package com.example.ch7_database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//원한다면 db file 명 동적으로 여러 파일 가능..
//상위에 db version 정보 설정..
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1)  {
    //앱이 인스톨된후 최초 한번 호출..
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table TB_TODO (" +
                "_id integer primary key autoincrement," +
                "todo not null)")
    }
    //db version 이 변경될 때 마다..
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}