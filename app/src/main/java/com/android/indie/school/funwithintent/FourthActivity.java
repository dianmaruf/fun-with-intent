package com.android.indie.school.funwithintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends BaseActivity {

    TextView tvActivityAddress;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        init();
        tvActivityAddress.setText(FourthActivity.class.getSimpleName());
    }

    private void init() {
        tvActivityAddress = findViewById(R.id.tv_activity_address);
        btnFinish = findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar, R.string.app_toolbar_name);
    }

    public void backToHome() {
        Intent homeIntent = new Intent(FourthActivity.this, HomeActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
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
