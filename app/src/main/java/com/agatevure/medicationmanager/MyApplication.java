package com.agatevure.medicationmanager;

/**
 * Created by Gconsult on 4/11/2018.
 */

public class MyApplication {

    private static boolean sActivityVisible;

    public static boolean isActivityVisible(){
        return sActivityVisible;
    }

    public static void activityResumed(){
        sActivityVisible = true;
    }

    public static void activityPaused(){
        sActivityVisible = false;
    }
}