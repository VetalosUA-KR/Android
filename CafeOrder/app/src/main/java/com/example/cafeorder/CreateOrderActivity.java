package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffe;

    private String drink;
    private String name;
    private String password;

    private StringBuilder builderAdditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if(intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }
        else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        drink = getString(R.string.tea);
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user), name);
        textViewHello.setText(hello);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        String additions = String.format(getString(R.string.additions),drink);
        textViewAdditions.setText(additions);
        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerOfTea);
        spinnerCoffe = findViewById(R.id.spinnerOfCoffe);
        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffe.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        }
        else if(id == R.id.radioButtonCoffe){
            drink = getString(R.string.coffe);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffe.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
        String additions = String.format(getString(R.string.additions),drink);
        textViewAdditions.setText(additions);
    }

    public void onClickSendOrder(View view) {
        builderAdditions.setLength(0);
        if(checkBoxMilk.isChecked())
        {
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if(checkBoxLemon.isChecked())
        {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        if(checkBoxSugar.isChecked() && drink.equals((getString(R.string.tea))))
        {
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }

        String optionOfDrink = "";
        if(drink.equals(getString(R.string.tea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }
        else{
            optionOfDrink = spinnerCoffe.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
        String addtions;
        if(builderAdditions.length() > 0){
            addtions = getString(R.string.need_additions)+builderAdditions.toString();
        }
        else{
            addtions = "";
        }
        String fullOrder = order + addtions;

        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}