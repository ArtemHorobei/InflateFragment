package com.example.user.inflatefragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 22.09.2016.
 */
public class MainFragment extends Fragment { //наследуемся от класса Фрагмент

    String data; //переменная типа стринг
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //вызывается при первом создании Activity в качестве аргумента бандл
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            data = getArguments().getString("data"); // инициализируем переменную дата
        }
        View v = inflater.inflate(R.layout.main_fragment, null); //из содержимого layout-файла создать View-элемент
        Button btnNextFragment = (Button) v.findViewById(R.id.btnNextFragment); //находим элемент по айди
        Button btnSendNotification = (Button) v.findViewById(R.id.btnSendNotification); //находим элемент по айди
        TextView txt = (TextView)v.findViewById(R.id.receivetext); //находим элемент по айди
        txt.setText(data); //присваиваем текстовому полю пришедшие данные

        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //обработчик события
                Fragment fr = new FirstFragment(); // создаем объект
                getActivity().getSupportFragmentManager().beginTransaction() // начало транзаакции с фрагментом
                        .addToBackStack("main") //возвращаемся на мейнфрагмент
                        .replace(R.id.activity_main, fr) //заменяем активити мейн на текущий фрагмент
                        .commit(); // завершаем транзацию
            }
        });

        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //обработчик события
                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

                Intent intent = new Intent(getActivity(), MainActivity.class); //создаем интент
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);//обертка, которая позволяет стороннему приложению выполнять определенный код (твоего приложения)
                Notification notification = new Notification.Builder(getActivity()) // создаем уведомление
                        .setSmallIcon(R.mipmap.ic_launcher) //устанавливаем иконку
                        .setContentIntent(pendingIntent) //устанавливаем пендингинтент
                        .setContentTitle("Change Activity?") //устанавливаем название
                        .setContentText("Go to second fragment") //устанавливаем текст
                        .build(); //построить уведомление с заданными параметрами
                notificationManager.notify(0, notification); //вызов уведомления
            }
        });

        return v;//возвращаем вью

    }
}
