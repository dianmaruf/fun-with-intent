package com.android.indie.school.funwithintent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends BaseActivity {

    TextView tvActivityAddress;
    Button btnNext, btnPhone, btnMedia, btnCamera, btnMessage, btnNextStack;

    private final int REQUEST_PICKFILE = 666;
    private final int REQUEST_IMAGE_CAPTURE = 222;
    private final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        tvActivityAddress.setText(HomeActivity.class.getSimpleName());
    }

    private void init() {
        tvActivityAddress = findViewById(R.id.tv_activity_address);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        btnPhone = findViewById(R.id.btn_call);
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDial();
            }
        });

        btnMedia = findViewById(R.id.btn_media);
        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMedia();
            }
        });

        btnCamera = findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCamera();
            }
        });

        btnMessage = findViewById(R.id.btn_messaging);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMessaging();
            }
        });

        btnNextStack = findViewById(R.id.btn_next_stack);
        btnNextStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextStack(HomeActivity.this);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar, R.string.app_toolbar_name);
    }

    private void goToNextActivity() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class); //Explicit
        startActivity(intent);
    }

    private void goToDial() {
        Intent intent = new Intent(Intent.ACTION_DIAL); //Implicit
        intent.setData(Uri.parse("tel:" + "081733334444"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToMedia() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); //mime
        intent = Intent.createChooser(intent, "Pick a File"); // <--- directory
        startActivityForResult(intent, REQUEST_PICKFILE);
    }

    private void goToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (null != intent.resolveActivity(getPackageManager())) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void goToMessaging() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + "0987654321"));
        intent.putExtra("sms_body", "it should be written on text body");
        if (null != intent.resolveActivity(getPackageManager())) {
            startActivity(intent);
        }
    }

    private void goToNextStack(Context context) {
        TaskStackBuilder builder = TaskStackBuilder.create(context);
        Intent homeIntent = new Intent(context, HomeActivity.class);
        builder.addParentStack(HomeActivity.class);
        builder.addNextIntent(homeIntent);

        Intent secondIntent = new Intent();
        secondIntent.setClass(context, SecondActivity.class);
        secondIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        builder.addNextIntent(secondIntent);

        Intent thirdIntent = new Intent();
        thirdIntent.setClass(context, ThirdActivity.class);
        thirdIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION
                |Intent.FLAG_ACTIVITY_NO_HISTORY
                |Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        builder.addNextIntent(thirdIntent);

        Intent fourthIntent = new Intent(context, FourthActivity.class);
        builder.addNextIntentWithParentStack(fourthIntent);

        builder.startActivities();
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

    private void showToast(final String msg) {
        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICKFILE && resultCode == RESULT_OK) {
            String name = data.getData().getLastPathSegment();
            showToast(name);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            showToast(data.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
