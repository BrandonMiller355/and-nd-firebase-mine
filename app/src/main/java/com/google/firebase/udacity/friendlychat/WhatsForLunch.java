package com.google.firebase.udacity.friendlychat;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.util.Locale;

public class WhatsForLunch extends Application {

//    public static UserProfile UserProfile;
    public static Context ApplicationContext;
//    public static Tracker tracker;
    public static String UserAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationContext = this;
        UserAgent = String.format(Locale.US, "TSHQ/%s (%s %s;Android %s)", BuildConfig.VERSION_NAME, Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE);

//        Fonts.loadFonts(this);
//
//        Fabric.with(this, new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder()
//                        .disabled(BuildConfig.DEBUG)
//                        .build())
//                .build());
//
//        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this);
//        }
//
//        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//        tracker = analytics.newTracker(R.xml.global_tracker);
//        tracker.enableAutoActivityTracking(false);
//        tracker.enableExceptionReporting(false);
//
//        JodaTimeAndroid.init(this);
    }

}
