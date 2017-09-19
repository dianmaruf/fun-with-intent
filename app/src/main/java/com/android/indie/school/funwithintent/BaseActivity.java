package com.android.indie.school.funwithintent;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by herisulistiyanto on 3/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int upIndicator = -1;

    /**
     *
     * @param toolbar instance of toolbar
     * @param title string title for toolbar
     */
    protected void setupToolbar(Toolbar toolbar, @StringRes int title) {
        toolbar.setBackgroundColor(parseColor(R.color.colorNavigationBar));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            if (upIndicator != -1) {
                actionBar.setHomeAsUpIndicator(upIndicator);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("");
        }

        if (null != toolbar) {
            TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
        }
    }

    protected final int parseColor(@ColorRes int colorResource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getColor(colorResource);
        } else {
            return this.getResources().getColor(colorResource);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
