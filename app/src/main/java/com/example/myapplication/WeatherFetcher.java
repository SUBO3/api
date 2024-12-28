package com.example.myapplication;

import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherFetcher {

    private TextView textView;

    // API URL
    private static final String API_URL = "https://opendata.cwa.gov.tw/api/v1/rest/datastore/F-C0032-001" +
            "?Authorization=CWA-5A6556A3-BD4C-48AC-8F7C-1CDF34213863" +
            "&format=JSON" +
            "&locationName=%E8%87%BA%E5%8C%97%E5%B8%82" +
            "&elementName=&sort=time";

    // 在構造函數中傳遞 TextView
    public WeatherFetcher(TextView textView) {
        this.textView = textView;
    }

    public void fetchWeatherData() {
        // 建立 OkHttpClient 實例
        OkHttpClient client = new OkHttpClient();

        // 建立 Request
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        // 發送請求（需在子執行緒中執行）
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    // 獲取 JSON 響應
                    String responseBody = response.body().string();
                    System.out.println("Weather Data: " + responseBody);

                    // 如果需要處理 JSON，將其傳遞到解析函數中
                    parseWeatherData(responseBody);
                } else {
                    System.out.println("Request failed: " + response.code());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 示例 JSON 解析函數
    private void parseWeatherData(String jsonData) {
        try {
            // 簡單打印原始 JSON 數據
            System.out.println("Received JSON: " + jsonData);

            // 模擬解析 JSON 數據並更新 TextView
            // 這裡只做一個簡單的顯示
            String weatherInfo = "天氣數據：\n" + jsonData;

            // 更新 TextView
            // 使用 post 方法來確保 UI 更新在主執行緒中執行
            textView.post(() -> textView.setText(weatherInfo));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
