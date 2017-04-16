package com.xbuyt.sudoku.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xbuyt.sudoku.R;

public class KeyboardFragment extends Fragment {

    static String currentNumber = "";

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    public static Button arrayButton[] = new Button[9];

    public KeyboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyboard, container, false);

        button1 = (Button) view.findViewById(R.id.fragment_keyboard_button_1);
        button2 = (Button) view.findViewById(R.id.fragment_keyboard_button_2);
        button3 = (Button) view.findViewById(R.id.fragment_keyboard_button_3);
        button4 = (Button) view.findViewById(R.id.fragment_keyboard_button_4);
        button5 = (Button) view.findViewById(R.id.fragment_keyboard_button_5);
        button6 = (Button) view.findViewById(R.id.fragment_keyboard_button_6);
        button7 = (Button) view.findViewById(R.id.fragment_keyboard_button_7);
        button8 = (Button) view.findViewById(R.id.fragment_keyboard_button_8);
        button9 = (Button) view.findViewById(R.id.fragment_keyboard_button_9);

        arrayButton[0] = button1;
        arrayButton[1] = button2;
        arrayButton[2] = button3;
        arrayButton[3] = button4;
        arrayButton[4] = button5;
        arrayButton[5] = button6;
        arrayButton[6] = button7;
        arrayButton[7] = button8;
        arrayButton[8] = button9;

        for (final Button button : arrayButton) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNumber = button.getText().toString();
                    setBackgroundColor();
                }
            });
        }

        return view;
    }

    public void setBackgroundColor() {
        for (Button button : arrayButton) {
            if (currentNumber.equals(button.getText().toString())) {
                button.setBackgroundResource(R.drawable.corner_radius_selected_keyboard_button);
            } else {
                button.setBackgroundResource(R.drawable.corner_radius_unselected_keyboard_button);
            }
        }
    }

    public static void resetKeyboard() {
        currentNumber = "";
        for (Button button : arrayButton) {
            button.setBackgroundResource(R.drawable.corner_radius_unselected_keyboard_button);
        }
    }

    public static void setEnabledKeyboard(Boolean bool) {
        for (Button button : arrayButton) {
            button.setEnabled(bool);
        }
    }
}