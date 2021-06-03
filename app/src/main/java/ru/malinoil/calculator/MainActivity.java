package ru.malinoil.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ru.malinoil.calculator.models.Calculator;

public class MainActivity extends AppCompatActivity {

    private static final int CHANGE_THEME_REQUEST_CODE = 1;
    private static final String BUNDLE_THEME_ID = "themeId";

    private TextView calculatorText;
    private Calculator calculator;

    private int themeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_THEME_ID)) {
            themeId = savedInstanceState.getInt(BUNDLE_THEME_ID);
            setTheme(themeId);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        calculatorText = (TextView) findViewById(R.id.calculate_text);

        initNumberButtonListener(R.id.button_one, R.string.btn_one);
        initNumberButtonListener(R.id.button_two, R.string.btn_two);
        initNumberButtonListener(R.id.button_three, R.string.btn_three);
        initNumberButtonListener(R.id.button_four, R.string.btn_four);
        initNumberButtonListener(R.id.button_five, R.string.btn_five);
        initNumberButtonListener(R.id.button_six, R.string.btn_six);
        initNumberButtonListener(R.id.button_seven, R.string.btn_seven);
        initNumberButtonListener(R.id.button_eight, R.string.btn_eight);
        initNumberButtonListener(R.id.button_nine, R.string.btn_nine);
        initOperationButtonListener(R.id.button_plus, R.string.btn_plus);
        initOperationButtonListener(R.id.button_minus, R.string.btn_minus);
        initOperationButtonListener(R.id.button_multiple, R.string.btn_multi);
        initOperationButtonListener(R.id.button_division, R.string.btn_division);
        initOperationButtonListener(R.id.button_calc, R.string.btn_calc);

        findViewById(R.id.button_dot).setOnClickListener(v -> {
            calculator.addDot();
            calculatorText.setText(calculator.getTextOnCalculator().toString());
        });

        findViewById(R.id.button_null).setOnClickListener(v -> {
            calculator.addNullNumber();
            calculatorText.setText(calculator.getTextOnCalculator().toString());
        });

        findViewById(R.id.button_clear).setOnClickListener(v -> {
            calculator.clear();
            calculatorText.setText(calculator.getTextOnCalculator().toString());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, GreetingActivity.class);
            intent.putExtra(GreetingActivity.EXTRA_THEME_KEY, themeId);
            startActivityForResult(intent, CHANGE_THEME_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CHANGE_THEME_REQUEST_CODE) {
            if(data != null && resultCode == RESULT_OK) {
                themeId = data.getIntExtra(GreetingActivity.EXTRA_THEME_KEY, themeId);
                recreate();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(themeId != 0) {
            outState.putInt(BUNDLE_THEME_ID, themeId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initNumberButtonListener(int id, int symbol) {
        findViewById(id).setOnClickListener(v -> {
            calculator.addNumber((String) getResources().getText(symbol));
            calculatorText.setText(calculator.getTextOnCalculator().toString());
        });
    }

    private void initOperationButtonListener(int id, int symbol) {
        findViewById(id).setOnClickListener(v -> {
            calculator.addOperation((String) getResources().getText(symbol));
            calculatorText.setText(calculator.getTextOnCalculator().toString());
        });
    }
}