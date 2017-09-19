package com.android.indie.school.funwithintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends BaseActivity {

    TextView tvActivityAddress;
    Button btnNext;
    Button btnNextClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        init();
        tvActivityAddress.setText(ThirdActivity.class.getSimpleName());
    }

    private void init() {
        tvActivityAddress = findViewById(R.id.tv_activity_address);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        btnNextClear = (Button) findViewById(R.id.btn_next_clear);
        btnNextClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivityClear();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar, R.string.app_toolbar_name);
    }

    private void goToNextActivity() {
        Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
        startActivity(intent);
    }

    private void goToNextActivityClear() {
        Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
