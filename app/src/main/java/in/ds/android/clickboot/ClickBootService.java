package in.ds.android.clickboot;

import android.app.*;
import android.os.*;
import android.content.*;
import android.util.*;

public class ClickBootService extends Service
{
	private final IBinder mBinder = new LocalBinder();

	private String TAG = "ClickBootService";

	@Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
	
	public class LocalBinder extends Binder {
        ClickBootService getService() {
            return ClickBootService.this;
        }
    }
	
	public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "WL_DEBUG onLowMemory");
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "WL_DEBUG onRebind intent = " + intent);
    }

    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "WL_DEBUG onStart intent = " + intent);
        Log.e(TAG, "WL_DEBUG onStart startId = " + startId);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);
        Log.e(TAG, "WL_DEBUG onStartCommand intent = " + intent);
        Log.e(TAG, "WL_DEBUG onStartCommand flags = " + flags);
        Log.e(TAG, "WL_DEBUG onStartCommand startId = " + startId);
        Log.e(TAG, "WL_DEBUG onStartCommand result = " + result);
        return 3;
    }

    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG, "WL_DEBUG onTaskRemoved rootIntent = " + rootIntent);
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "WL_DEBUG onTrimMemory level = " + level);
    }

    public boolean onUnbind(Intent intent) {
        boolean result = super.onUnbind(intent);
        Log.e(TAG, "WL_DEBUG onUnbind intent = " + intent);
        Log.e(TAG, "WL_DEBUG onUnbind result = " + result);
        return result;
    }

}
