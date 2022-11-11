package com.jadehs.ma.ecoplay.ueberuns;

import static org.osmdroid.views.overlay.Marker.ANCHOR_CENTER;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.PreferenceManager;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class UeberUnsActivity extends EcoPlayActivity {

    private MapView map;

    public UeberUnsActivity() {
        super(true, R.string.actionbar_menu_action_about, R.drawable.about_us, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ueber_uns);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(16.5);
        GeoPoint startPoint = new GeoPoint(53.5479279, 8.0882846);
        mapController.setCenter(startPoint);

        Marker m = new Marker(map);
        m.setIcon(AppCompatResources.getDrawable(this, R.drawable.map_marker));
        m.setAnchor(ANCHOR_CENTER, ANCHOR_CENTER);
        m.setTextLabelBackgroundColor(getColor(R.color.akzent));
        m.setTextLabelForegroundColor(getColor(R.color.text_color));
        m.setTextLabelFontSize((int) getResources().getDimension(R.dimen.apptitel_text_size));
        m.setTitle(getString(R.string.jadehochschule) + "\nWilhelmshaven");
        m.showInfoWindow();

        m.setPosition(new GeoPoint(53.5479279, 8.0882846));
        map.getOverlays().add(m);
    }

    public void onResume() {
        super.onResume();
        map.onResume();
    }

    public void onPause() {
        super.onPause();
        map.onPause();
    }

}