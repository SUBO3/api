package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;





public class MainActivity extends AppCompatActivity {

    private TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 TextView
        weatherTextView = findViewById(R.id.textView);

        // 創建 WeatherFetcher 實例並將 TextView 傳遞給它
        WeatherFetcher weatherFetcher = new WeatherFetcher(weatherTextView);
        weatherFetcher.fetchWeatherData();
    }
}

