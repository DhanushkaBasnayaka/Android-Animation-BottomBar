package tuckerbx.elegantmeia.com.au.newapplication.d.module;


import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;

/**
 * Created by dhanushka on 10/10/2017.
 */
@Module
public class ActivityModule {


    private final Activity context;

    public ActivityModule(Activity context) {

        this.context = context;
    }

    @Provides
    @ApplicationScope
    @Named("activity_context")
    public Context context() {
        return context;
    }


}
