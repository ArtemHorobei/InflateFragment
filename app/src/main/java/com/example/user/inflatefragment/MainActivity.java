package com.example.user.inflatefragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //устанавливает содержимое Activity из layout-файла

        Fragment fr = new MainFragment(); // создаем объект типа Фрагмент
        this.getSupportFragmentManager().beginTransaction() //начало действия с фрагментом
                .add(R.id.activity_main, fr) // добавляем на мейнактивити фрагмент
                .commit(); //подтверждаем действие
    }

    @Override
    protected void onResume() {
        super.onResume(); //вызывается перед тем как будет доступно для активности пользователя (взаимодействие)

    }
}
