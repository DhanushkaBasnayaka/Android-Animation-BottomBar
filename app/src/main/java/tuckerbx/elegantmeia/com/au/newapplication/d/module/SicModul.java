package tuckerbx.elegantmeia.com.au.newapplication.d.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationContext;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.CallApi;

/**
 * Created by dhanushka on 24/10/2017.
 */
@Module(includes = {ContextModule.class, ApplicarionServicemodule.class})
public class SicModul {

    @Provides
    @ApplicationScope
    public CallApi mCallApi(@ApplicationContext Context context, ApiService mApiService) {
        return new CallApi(context, mApiService);
    }
}
