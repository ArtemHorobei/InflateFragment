package com.example.user.inflatefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by User on 22.09.2016.
 */
public class FirstFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, null); //из содержимого layout-файла создать View-элемент
        Button btnBack = (Button)v.findViewById(R.id.buttonback); //находим по айди нужную кнопку
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //обработчик события
                FragmentManager fm = getActivity().getSupportFragmentManager();//вызываем фрагментменеджер
                fm.popBackStack();//возвращаем предыдущее состояние стека
            }
        });

        Button btnBackData = (Button)v.findViewById(R.id.buttonsend); // находим по айди нужную кнопку
        final EditText editText = (EditText)v.findViewById(R.id.editText); //находим по айди нужный элемент
        final Fragment current = this;

        btnBackData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //обработчик события
                FragmentManager fm = getActivity().getSupportFragmentManager();//вызываем фрагментменеджер
                fm.popBackStack();//возвращаем предыдущее состояние стека
                Fragment main = new MainFragment();//создаем объект менйфрагмент
                Bundle bundle = new Bundle();//создаем бандл
                bundle.putString("data", editText.getText().toString());//кладем в бандл дату
                main.setArguments(bundle);
                fm.beginTransaction()//начинаем транзакцию
                        .replace(R.id.activity_main, main)//заменяем текующей фрагмент
                        .remove(current)//текущий удаляем
                        .commit()//завершаем транзацкию
                ;
            }
        });
        return v;//возвращаем вью
    }
}
