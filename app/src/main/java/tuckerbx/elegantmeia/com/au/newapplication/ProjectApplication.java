package tuckerbx.elegantmeia.com.au.newapplication;


import android.app.Activity;
import android.app.Application;


import com.squareup.picasso.Picasso;

import tuckerbx.elegantmeia.com.au.newapplication.d.component.ApplicationComponent;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.DaggerApplicationComponent;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.ContextModule;
import tuckerbx.elegantmeia.com.au.newapplication.helpers.NetworkAccess;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.CallApi;

/**
 * Created by dhanushka on 10/10/2017.
 */

public class ProjectApplication extends Application {
    private ApplicationComponent component;
    private ApiService apiService;
    private Picasso picasso;
    private CallApi mCallApi;
    private NetworkAccess mNetworkAccess;

    public static ProjectApplication get(Activity activity) {
        return (ProjectApplication) activity.getApplication();

    }


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        apiService = component.getApplicatonService();
        picasso = component.getPicasso();
        mCallApi = component.getClasesink();
        mNetworkAccess = component.getNetworkAccess();
    }

    public ApplicationComponent getcomponent() {
        return component;
    }
}
