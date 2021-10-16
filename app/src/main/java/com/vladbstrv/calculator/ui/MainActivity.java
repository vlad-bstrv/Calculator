package com.vladbstrv.calculator.ui;

import static com.vladbstrv.calculator.ui.SettingsActivity.ARG_THEME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vladbstrv.calculator.R;
import com.vladbstrv.calculator.domain.Theme;
import com.vladbstrv.calculator.storage.ThemeStorage;

public class MainActivity extends AppCompatActivity{

    private TextView tvInput;

    private Calculator presenter;

    final static String inputKey = "INPUT";

    private ThemeStorage storage;

    private final ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            if(result.getResultCode() == Activity.RESULT_OK) {
                if(result.getData() != null) {
                    Theme theme = (Theme) result.getData().getSerializableExtra(ARG_THEME);

                    storage.setTheme(theme);
                    recreate();
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        setTheme(storage.getTheme().getTheme());

        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);
        presenter = new Calculator();

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

        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                Theme theme = storage.getTheme();
                intent.putExtra(ARG_THEME, theme);

                settingsLauncher.launch(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(inputKey, presenter);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        presenter = savedInstanceState.getParcelable(inputKey);
        tvInput.setText(presenter.getText());
    }
}
