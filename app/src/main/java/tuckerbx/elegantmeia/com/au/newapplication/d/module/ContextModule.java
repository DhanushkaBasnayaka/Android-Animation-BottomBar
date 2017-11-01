package tuckerbx.elegantmeia.com.au.newapplication.d.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationContext;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;

/**
 * Created by dhanushka on 10/10/2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context context() {
        return context;
    }

}
