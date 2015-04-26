package me.joeyang.sunshine.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.ArrayAdapter;

/**
 * Created by joe on 15-04-25.
 */
public class SunshineService extends IntentService {
    private ArrayAdapter<String> mForecastAdapter;
    public static final String LOCATION_QUERY_EXTRA = "lqe";
    private final String LOG_TAG = SunshineService.class.getSimpleName();

    public SunshineService(){
        super("SunshineService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String locationQuery = intent.getStringExtra(LOCATION_QUERY_EXTRA);
    }
}
