package com.example.shirokuma706.companysearchapplication;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    /*検索文字列変数*/
    String query;

    /*削除ボタン*/
    private Button button4;
    private View.OnClickListener button4_ClickListener = new View.OnClickListener(){
        public void onClick(View v){button_Delete_Click(v);
    }};

    /*一件削除ボタン*/
    private EditText editText4;
    private Button button6;
    private View.OnClickListener button6_ClickListener = new View.OnClickListener(){
        public void onClick(View v){button_Delete1_Click(v);
        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*戻るボタンクリックリスナー*/
        Button returnButton = (Button) findViewById(R.id.button5);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /*全件削除ボタンクリックリスナー*/
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(button4_ClickListener);

        /*平井の分追加したらID変える↓一件削除クリックリスナー*/
        editText4 = (EditText)findViewById(R.id.editText4);

        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(button6_ClickListener);

        /*WEB検索ボタン処理*/
        Button searchButton = (Button) findViewById(R.id.button3);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button web = (Button) findViewById(R.id.button3);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                EditText edit4 = (EditText)findViewById(R.id.editText4);
                edit4.selectAll();
                query = edit4.getText().toString();
                intent.putExtra(SearchManager.QUERY, query);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        /*データベースの表示*/
        TextView TV = (TextView) findViewById(R.id.textView);
        String[] columns = new String[]{"name","addres","tell"};
        DatabaseHelper myDB = new DatabaseHelper(this);
        SQLiteDatabase db = myDB.getWritableDatabase();
        Cursor ret;
        String result = "";

        try { ret = db.query("MyTable", columns, null,null,null,null,null);
            if (ret.moveToFirst()){
                do{
                    result += "企業名:" + ret.getString(ret.getColumnIndex("name"))
                            + "  住所:" + ret.getString(ret.getColumnIndex("addres"))
                            + "  電話番号:" + ret.getString(ret.getColumnIndex("tell"))
                            + "\n";
                }while(ret.moveToNext());
            }
        } finally {
            db.close();
        }
        TV.setText(result);
    }

    /*全件削除ボタン処理*/
    private void button_Delete_Click(View v) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.delete("MyTable", null, null);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "削除失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "削除成功", Toast.LENGTH_SHORT).show();
        }
        TextView TV = (TextView) findViewById(R.id.textView);
        String result = "";
        TV.setText(result);
    }

    /*一件削除ボタン処理*/
    private void button_Delete1_Click(View v) {
        EditText edit4 = (EditText)findViewById(R.id.editText4);
        edit4.selectAll();
        query = "'";
        query += edit4.getText().toString();
        query += "'";

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.delete("MyTable", "name = " + query, null);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "削除失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "削除成功", Toast.LENGTH_SHORT).show();
        }
        TextView TV = (TextView) findViewById(R.id.textView);
        String[] columns = new String[]{"name","addres","tell"};
        DatabaseHelper myDB = new DatabaseHelper(this);
        SQLiteDatabase db2 = myDB.getWritableDatabase();
        Cursor ret2;
        String result = "";

        try { ret2 = db2.query("MyTable", columns, null,null,null,null,null);
            if (ret2.moveToFirst()){
                do{
                    result += "企業名:" + ret2.getString(ret2.getColumnIndex("name"))
                            + "  住所:" + ret2.getString(ret2.getColumnIndex("addres"))
                            + "  電話番号:" + ret2.getString(ret2.getColumnIndex("tell"))
                            + "\n";
                }while(ret2.moveToNext());
            }
        } finally {
            db2.close();
        }
        TV.setText(result);
    }
}
