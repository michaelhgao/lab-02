package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityBtn;
    Button delCityBtn;
    LinearLayout addCityBar;
    EditText addCityBarEditText;
    Button addCityBarBtn;
    Integer selectedCityPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCityBtn = findViewById(R.id.add_city_button);
        delCityBtn = findViewById(R.id.delete_city_button);
        addCityBar = findViewById(R.id.add_city_bar);
        addCityBarEditText = findViewById(R.id.add_city_bar_edit_text);
        addCityBarBtn = findViewById(R.id.add_city_bar_button);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter= new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityBar.setVisibility(View.VISIBLE);
            }
        });

        addCityBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = addCityBarEditText.getText().toString().trim();
                if (!city.isEmpty()) {
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                }
                addCityBarEditText.setText("");
                addCityBar.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clearSelectedCityBackground();
                selectedCityPosition = position;
                View selectedCity = cityList.getChildAt(position - cityList.getFirstVisiblePosition());
                selectedCity.setBackgroundColor(Color.GRAY);
            }
        });

        delCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCityPosition != -1) {
                    dataList.remove(selectedCityPosition.intValue());
                    cityAdapter.notifyDataSetChanged();
                    selectedCityPosition = -1;
                }
                clearSelectedCityBackground();
            }
        });

    }

    private void clearSelectedCityBackground() {
        for (int i = 0; i < cityList.getChildCount(); i++) {
            View city = cityList.getChildAt(i);
            city.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}