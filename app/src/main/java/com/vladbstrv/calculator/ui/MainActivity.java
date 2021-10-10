package com.vladbstrv.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vladbstrv.calculator.R;

public class MainActivity extends AppCompatActivity{

    private TextView tvInput;
    private TextView tvOutput;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);
        tvOutput = findViewById(R.id.tvOutput);
        presenter = new CalculatorPresenter();

        int[] digitIds = new int[]{
                R.id.btn0,
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8,
                R.id.btn9,
        };

        int[] operationIds = new int[]{
                R.id.btnPlus,
                R.id.btnMinus,
                R.id.btnDivide,
                R.id.btnMultiply,
                R.id.btnEqually,
        };

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDigitPressed(v.getId());
                tvInput.setText(presenter.getText());
            }
        };

        View.OnClickListener actionButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperationPressed(v.getId());
                tvInput.setText(presenter.getText());
            }
        };

        for (int i = 0; i < digitIds.length; i++) {
            findViewById(digitIds[i]).setOnClickListener(numberButtonClickListener);

        }

        for (int i = 0; i < operationIds.length; i++) {
            findViewById(operationIds[i]).setOnClickListener(actionButtonOnClickListener);

        }

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClear();
                tvInput.setText(presenter.getText());
            }
        });

    }
}