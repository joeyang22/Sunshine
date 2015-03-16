package me.joeyang.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Joe Yang on 2/25/2015.
 */
public class ForecastFragment extends Fragment {

    public ArrayAdapter<String> mForecastAdapter;
    private ArrayList<String> forecastArrayList;

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.forecastfragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> dummyArrayList = new ArrayList<String>();
        dummyArrayList.add("Looks like we couldn't retrieve any data.");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mForecastAdapter = new ArrayAdapter<String>(getActivity(),
                                                R.layout.list_item_forecast,
                                                R.id.list_item_forecast_textView,
                                                dummyArrayList);

        ListView lvForecasts = (ListView) rootView.findViewById(R.id.listView_forecast);
        lvForecasts.setAdapter(mForecastAdapter);

        lvForecasts.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String forecast = mForecastAdapter.getItem(position);
                //Toast.makeText(getActivity(),forecast,Toast. LENGTH_SHORT).show();
                Intent detailActivityIntent = new Intent(getActivity(), DetailActivity.class);
                detailActivityIntent.putExtra(Intent.EXTRA_TEXT,forecast);
                startActivity(detailActivityIntent);
            }
        });

        return rootView;
    }

    //Starts the task for updating weather
    private void updateWeather(){
        FetchWeatherTask task = new FetchWeatherTask(getActivity(),mForecastAdapter);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String locationPreference = preferences.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default));
        task.execute(locationPreference);
    }

    public void onStart(){
        super.onStart();
        updateWeather();
    }
    private double celsiusToFahrenheit(double temperature){
        return temperature*(9.0/5.0)+32.0;
    }


}