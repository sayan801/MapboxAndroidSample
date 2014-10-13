package com.example.technicise_mac3.technicisemapbox;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mapbox.mapboxsdk.api.ILatLng;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.GpsLocationProvider;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.overlay.PathOverlay;
import com.mapbox.mapboxsdk.overlay.UserLocationOverlay;
import com.mapbox.mapboxsdk.views.MapController;
import com.mapbox.mapboxsdk.views.MapView;


public class MyActivity extends Activity
{
    MapView mv;
    MapController mc;
    MapController mcZoom;
    private String currentMap = null;
    private UserLocationOverlay myLocationOverlay;
    public Marker myposmarker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mv = (MapView) findViewById(R.id.mapview);
        mc = new MapController(mv);
        mcZoom= new MapController(mv);
       mv.setMinZoomLevel(mv.getTileProvider().getMinimumZoomLevel());
        mv.setMaxZoomLevel(mv.getTileProvider().getMaximumZoomLevel());
        mv.setCenter(mv.getTileProvider().getCenterCoordinate());
        mv.setZoom(1);

       // currentMap = "amit007.jnedbdll";
        //brunosan.map-cyglrrfu

        // Adds an icon that shows location
        myLocationOverlay = new UserLocationOverlay(new GpsLocationProvider(this), mv);
        myLocationOverlay.setDrawAccuracyEnabled(true);


        //Toast.makeText(getApplicationContext(), latlong.toString()+"", Toast.LENGTH_LONG).show();
        //mv.getOverlays().add(myLocationOverlay);
       // myLocationOverlay.goToMyPosition(true);

//        mv.loadFromGeoJSONURL("https://gist.githubusercontent.com/tmcw/10307131/raw/21c0a20312a2833afeee3b46028c3ed0e9756d4c/map.geojson");

//        Marker m = new Marker(mv, "Edinburgh", "Scotland", new LatLng(55.94629, -3.20777));
//        m.setIcon(new Icon(this, Icon.Size.SMALL, "marker-stroked", "FF0000"));
//        mv.addMarker(m);
//
//        m = new Marker(mv, "Stockholm", "Sweden", new LatLng(59.32995, 18.06461));
//        m.setIcon(new Icon(this, Icon.Size.MEDIUM, "city", "FFFF00"));
//        mv.addMarker(m);
//
//        m = new Marker(mv, "Prague", "Czech Republic", new LatLng(50.08734, 14.42112));
//        m.setIcon(new Icon(this, Icon.Size.LARGE, "land-use", "00FFFF"));
//        mv.addMarker(m);

        Drawable myIcon = getResources().getDrawable( R.drawable.ic_launcher );
        Marker m = new Marker(mv, "hi", "hello", new LatLng(77.0875, 14.42112));
        m.setMarker(myIcon);
        m.setTitle("Hello Android");
        m.setDescription("This is Technicise");

        mv.addMarker(m);

        //m.setIcon(new Icon(getBaseContext(), Icon.Size.LARGE, "land-use", "00FF00"));

//        m = new Marker(mv, "Athens", "Greece", new LatLng(37.97885, 23.71399));
//        mv.addMarker(m);

//        PathOverlay line = new PathOverlay(Color.RED, 3);
//        line.addPoint(new LatLng(51.2, 0.1));
//        line.addPoint(new LatLng(51.7, 0.3));
//        mv.getOverlays().add(line);
    }
public void gotomypos(View v)
    {
        myLocationOverlay.enableMyLocation();
        LatLng latlong =myLocationOverlay.getMyLocation();
        Double myLatitide= latlong.getLatitude();
        Double mylongitude = latlong.getLongitude();
        Double myAltitude = latlong.getAltitude();
        Drawable myIcon = getResources().getDrawable( R.drawable.ic_launcher );
        Marker m = new Marker(mv, "a", "b", new LatLng(myLatitide, mylongitude));
        m.setMarker(myIcon);
        m.setTitle("My Location");
        m.setDescription("I am here");
        myposmarker =m;
        mv.addMarker(m);
       mc.animateTo(new LatLng(myLatitide, mylongitude),true);

            mc.setZoomAnimated(5,new LatLng(myLatitide, mylongitude),true);
        //mcZoom.setZoom(5);
        //mc.setZoomAnimated(5,new LatLng(myLatitide, mylongitude),false);
    }
public void zoomin(float value)
{
    mc.setZoom(value);
}
    public void gotopos2(View v)
    {
        mc.animateTo(new LatLng(77.0875, 14.42112),true);
    }

    public void removemyloc(View v)
    {
//        myLocationOverlay.enableMyLocation();
//        LatLng latlong =myLocationOverlay.getMyLocation();
//        Double myLatitide= latlong.getLatitude();
//        Double mylongitude = latlong.getLongitude();
//        Double myAltitude = latlong.getAltitude();
//        Drawable myIcon = getResources().getDrawable( R.drawable.ic_launcher );
//        Marker m = new Marker(mv, "a", "b", new LatLng(myLatitide, mylongitude));
        mv.removeMarker(myposmarker);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
