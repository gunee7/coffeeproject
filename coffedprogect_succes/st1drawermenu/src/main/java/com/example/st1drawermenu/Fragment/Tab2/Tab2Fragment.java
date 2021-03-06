package com.example.st1drawermenu.Fragment.Tab2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.content.Intent;

import com.example.st1drawermenu.R;
import com.example.st1drawermenu.SubuMenu.SubMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class Tab2Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    private View         inflatedview2     = null ;
    private Tab2_Adapter_Card tab2Adapter;
    private GridView tab2GridView;
    private List<Tab2_Model_Card> data2;
    private int[] coffee_images    = Tab2MenuButton.caffee_icon;
    private int[] latte_iamges     =  Tab2MenuButton.lattee_icon;
    private int[] beverage_images  = Tab2MenuButton.beverage_icon;
    private int[] tea_iamges       =  Tab2MenuButton.tea_icon;
    private int[] sidemenu_images  = Tab2MenuButton.sidemenu_icon;
    private Button btn_menu1 = null;
    private Button btn_menu2 = null;
    private Button btn_menu3 = null;
    private Button btn_menu4 = null;
    private Button btn_menu5 = null;
    private int getid2 = 0;


    public Tab2Fragment() {

    }

    public static Tab2Fragment newInstance(String param1, String param2) {
        Tab2Fragment fragment = new Tab2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        inflatedview2 = inflater.inflate(R.layout.fragment_tab2, container, false);

        data2 = MakeData1( 0 , coffee_images.length - 1 );

        tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
        tab2GridView = inflatedview2.findViewById( R.id.gridView );
        tab2GridView.setAdapter( tab2Adapter );

        btn_menu1 = inflatedview2.findViewById( R.id.btn_menu1 );
        btn_menu2 = inflatedview2.findViewById( R.id.btn_menu2 );
        btn_menu3 = inflatedview2.findViewById( R.id.btn_menu3 );
        btn_menu4 = inflatedview2.findViewById( R.id.btn_menu4 );
        btn_menu5 = inflatedview2.findViewById( R.id.btn_menu5 );

        return inflatedview2;
    }





    @Override                    // 위젯 관련된 것은 이쪽에서
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated( savedInstanceState );


        tab2GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // intent 여기안에서 하기
                Intent i = new Intent ( getContext(), SubMenuActivity.class);
                i.putExtra( "position" , position );
                i.putExtra( "name"     , getid2 );
                startActivity(i);
            }
        });

        buttonClickListener bcListener = new buttonClickListener();
        btn_menu1.setOnClickListener( bcListener );
        btn_menu2.setOnClickListener( bcListener );
        btn_menu3.setOnClickListener( bcListener );
        btn_menu4.setOnClickListener( bcListener );
        btn_menu5.setOnClickListener( bcListener );

    }

    private class buttonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_menu1:
                    data2 = MakeData1( 0 , coffee_images.length-1 );
                    tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
                    tab2Adapter.notifyDataSetChanged();
                    tab2GridView.setAdapter( tab2Adapter );

                    getid2 = v.getId();

                    break;
                case R.id.btn_menu2:
                    data2 = MakeData2( 0 , latte_iamges.length-1 );
                    tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
                    tab2Adapter.notifyDataSetChanged();
                    tab2GridView.setAdapter( tab2Adapter );

                    getid2 = v.getId();

                    break;
                case R.id.btn_menu3:
                    data2 = MakeData3( 0 , beverage_images.length-1 );
                    tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
                    tab2Adapter.notifyDataSetChanged();
                    tab2GridView.setAdapter( tab2Adapter );

                    getid2 = v.getId();

                    break;
                case R.id.btn_menu4:
                    data2 = MakeData4( 0 , tea_iamges.length-1 );
                    tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
                    tab2Adapter.notifyDataSetChanged();
                    tab2GridView.setAdapter( tab2Adapter );

                    getid2 = v.getId();

                    break;
                case R.id.btn_menu5:
                    data2 = MakeData5( 0 , sidemenu_images.length-1 );
                    tab2Adapter = new Tab2_Adapter_Card( getContext(), R.layout.fragment_tab2_custom, data2 );
                    tab2Adapter.notifyDataSetChanged();
                    tab2GridView.setAdapter( tab2Adapter );

                    getid2 = v.getId();

                    break;
            }
        }
    }
    private List<Tab2_Model_Card> MakeData1( int start, int end ) {

        String[] coffeeName = getResources().getStringArray(R.array.menu_coffee_name);
        List<Tab2_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab2_Model_Card item = new Tab2_Model_Card();
            item.setImageCoffee(getResources().getDrawable(coffee_images[i]));
            item.setTextCoffee( coffeeName[i] );

            list.add(item);
        }

        return list;
    }
    private List<Tab2_Model_Card> MakeData2( int start, int end ) {

        String[] latteName = getResources().getStringArray(R.array.menu_latte_name);
        List<Tab2_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab2_Model_Card item = new Tab2_Model_Card();
            item.setImageCoffee(getResources().getDrawable(latte_iamges[i]));
            item.setTextCoffee( latteName[i] );

            list.add(item);
        }

        return list;
    }
    private List<Tab2_Model_Card> MakeData3( int start, int end ) {

        String[] beverageName = getResources().getStringArray(R.array.menu_beverage_name);
        List<Tab2_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab2_Model_Card item = new Tab2_Model_Card();
            item.setImageCoffee(getResources().getDrawable(beverage_images[i]));
            item.setTextCoffee( beverageName[i] );

            list.add(item);
        }

        return list;
    }
    private List<Tab2_Model_Card> MakeData4( int start, int end ) {

        String[] teaName = getResources().getStringArray(R.array.menu_tea_name);
        List<Tab2_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab2_Model_Card item = new Tab2_Model_Card();
            item.setImageCoffee(getResources().getDrawable(tea_iamges[i]));
            item.setTextCoffee( teaName[i] );

            list.add(item);
        }

        return list;
    }
    private List<Tab2_Model_Card> MakeData5( int start, int end ) {

        String[] sidemenuName = getResources().getStringArray(R.array.menu_sidemenu_name);
        List<Tab2_Model_Card> list = new ArrayList<>();
        for(int i =start ; i<=end; i++){

            Tab2_Model_Card item = new Tab2_Model_Card();
            item.setImageCoffee(getResources().getDrawable(sidemenu_images[i]));
            item.setTextCoffee( sidemenuName[i] );

            list.add(item);
        }

        return list;
    }

}
