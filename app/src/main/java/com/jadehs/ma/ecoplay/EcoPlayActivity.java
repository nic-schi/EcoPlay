package com.jadehs.ma.ecoplay;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class EcoPlayActivity extends AppCompatActivity {

    private final Integer actionBarTitelResourceID;
    private final boolean addMenu;
    private final boolean useBackButton;

    public EcoPlayActivity(Integer actionBarTitelResourceID) {
        this(actionBarTitelResourceID, false);
    }

    public EcoPlayActivity(Integer actionBarTitelResourceID, boolean addMenu) {
        this(actionBarTitelResourceID, addMenu, true);
    }

    public EcoPlayActivity(Integer actionBarTitelResourceID, boolean addMenu, boolean useBackButton) {
        this.addMenu = addMenu;
        this.actionBarTitelResourceID = actionBarTitelResourceID;
        this.useBackButton = useBackButton;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = this.getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(this.useBackButton);

            // titel
            if (actionBarTitelResourceID != null) {
                bar.setTitle(this.actionBarTitelResourceID);
            } else {
                bar.setTitle(R.string.app_name);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (this.addMenu) {
            if (item.getItemId() == R.id.action_setting) {
                // TODO: navigate to settings activity
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menü
        if (this.addMenu) {
            this.getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

}
