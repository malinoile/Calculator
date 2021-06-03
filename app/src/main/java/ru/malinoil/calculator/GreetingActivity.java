package ru.malinoil.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    private static final String BUNDLE_THEME = "theme";
    public static final String EXTRA_THEME_KEY = "extraThemeKey";

    private RadioGroup radioGroup;
    private Button chooseTheme;
    private Button selectTheme;

    private int idTheme = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        idTheme = getIntent().getIntExtra(EXTRA_THEME_KEY, 0);
        if(savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_THEME)) {
            idTheme = savedInstanceState.getInt(BUNDLE_THEME);
        }
        if(idTheme != 0) {
            setTheme(idTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_theme);
        radioGroup.setOnCheckedChangeListener((group, id) -> {
            if(id == R.id.radio_night_theme) {
                idTheme = R.style.Theme_Calculator_Dark;
            } else {
                idTheme = R.style.Theme_Calculator;
            }
        });

        chooseTheme = (Button)findViewById(R.id.choose_theme);
        chooseTheme.setOnClickListener(v -> {
            recreate();
        });

        selectTheme = (Button) findViewById(R.id.select_theme);
        selectTheme.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_THEME_KEY, idTheme);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(idTheme != 0) {
            outState.putInt(BUNDLE_THEME, idTheme);
        }
        super.onSaveInstanceState(outState);
    }
}
