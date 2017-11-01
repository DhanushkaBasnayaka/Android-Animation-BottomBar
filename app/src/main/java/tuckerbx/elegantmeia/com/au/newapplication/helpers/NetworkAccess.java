package tuckerbx.elegantmeia.com.au.newapplication.helpers;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by dhanushka on 24/10/2017.
 */

public class NetworkAccess {

    private static Context mContext;

    public NetworkAccess(Context context) {
        mContext = context;
    }

    public static boolean isNetworkAvailable() {

        boolean state = isInternetAvailable();
        if (!state) {

        }
        return state;
    }

    private static boolean isInternetAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
