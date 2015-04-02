package me.joeyang.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private final String FORECASTFRAGMENT_TAG = "FORECAST_FRAGMENT";
    static String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocation = Utility.getPreferredLocation(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment(),FORECASTFRAGMENT_TAG)
                    .commit();
        }

        Log.v(LOG_TAG, "In onCreate");
    }
    protected void onStart(){
        super.onStart();
        Log.v(LOG_TAG, "In onStart");
    }
    protected void onPause(){
        super.onPause();
        Log.v(LOG_TAG, "In onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.v(LOG_TAG, "In onStop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.v(LOG_TAG, "In onDestroy");
    }
    protected void onResume(){
        super.onResume();
        String location = Utility.getPreferredLocation( this );
        if (mLocation!=null && !location.equals(mLocation)){
            ForecastFragment ff = (ForecastFragment)getSupportFragmentManager().findFragmentByTag(FORECASTFRAGMENT_TAG);
            if (null!= ff){
                ff.onLocationChanged();
            }
            mLocation = location;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings){
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        else if(id == R.id.action_map){
            openPreferredLocationInMap();

        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        String mapPreference = preference.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri.Builder builder = new Uri.Builder();
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q",mapPreference)
                .build();
        mapIntent.setData(geoLocation);
        if (mapIntent.resolveActivity(getPackageManager())!=null){
            startActivity(mapIntent);
        }else{
            Log.d(LOG_TAG, "Could not resolve the intent");
        }



    }

}
