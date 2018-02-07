package com.example.st1drawermenu.PayMenu;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;

import com.example.st1drawermenu.Fragment.Tab2.Tab2_Model_Card;
import com.example.st1drawermenu.R;

import java.util.ArrayList;
import java.util.List;

public class PayMenuActivity extends AppCompatActivity {

    private ListView paylistview = null;
    private PayAdapter payadapter;
    private String PAY_DATA = "SENDDATA";
    private ArrayList<Tab2_Model_Card> list;
    private List<Tab2_Model_Card> data;
    private List<Tab2_Model_Card> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_menu);

        Intent i = getIntent();
        list = i.getParcelableArrayListExtra(PAY_DATA);
        data = Makedata( 0 , list.size() -1 );
        payadapter = new PayAdapter( PayMenuActivity.this, R.layout.pay_custom, data );

        paylistview = findViewById( R.id.pay_listview);
        paylistview.setAdapter( payadapter );
    }

    private List<Tab2_Model_Card> Makedata( int start, int end ) {

        Tab2_Model_Card item = new Tab2_Model_Card();

        for (int i = start; i < end; i++ ){
            item = list.get(i);

            datalist.add(item);
        }

        return datalist;
    }

}
