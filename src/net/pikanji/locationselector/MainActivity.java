
package net.pikanji.locationselector;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity implements OnClickListener {
    private static final String DEBUG_TAG = "LocationSelector";
    private MapView mMapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout_map);

        String mapKey = getResources().getString(R.string.map_key);
        mMapView = new MapView(this, mapKey);
        mMapView.setEnabled(true);
        mMapView.setClickable(true);
        mMapView.setBuiltInZoomControls(true);

        frameLayout.addView(mMapView, 0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView pointer = (ImageView) findViewById(R.id.img_pointer);
        LinearLayout overlay = (LinearLayout) findViewById(R.id.overlay_layout);
        pointer.setPadding(0, 0, 0, overlay.getHeight());
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onClick(View v) {
        GeoPoint point = mMapView.getMapCenter();
        double lat = ((double) point.getLatitudeE6()) / 1e6;
        double lon = ((double) point.getLongitudeE6()) / 1e6;
        Toast.makeText(this, "Lat: " + lat + "Lon: " + lon + " is selected.", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
