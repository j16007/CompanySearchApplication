package com.example.shirokuma706.companysearchapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*ここから木村作成*/
public class MainActivity extends AppCompatActivity {

    private Button button1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    /*登録ボタンクリックリスナー*/
    private OnClickListener button1_ClickListener = new OnClickListener() {
        public void onClick(View v) {buttonInsert_Click(v);}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*一覧表示ボタンでページ遷移*/
        Button movePagesButton = (Button)findViewById(R.id.button2);
        movePagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        /*項目取得*/
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);

        /*登録ボタン*/
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(button1_ClickListener);
    }

    /*登録ボタン処理(insert)*/
    public void buttonInsert_Click(View v){
        ContentValues values = new ContentValues();
        values.put("Name",editText1.getText().toString());
        values.put("Addres",editText2.getText().toString());
        values.put("Tell", String.valueOf(editText3.getText()));

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long ret;

        try{
            ret = db.insert("MyTable",null,values);
        }finally {
            db.close();
        }
        /*登録が成功したかの判断*/
        if (ret == -1) {
            Toast.makeText(this,"登録失敗",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登録成功",Toast.LENGTH_SHORT).show();
        }
        /*登録ボタン押下後テキストを空にする*/
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
    }
}
/*ここまで木村作成*/
