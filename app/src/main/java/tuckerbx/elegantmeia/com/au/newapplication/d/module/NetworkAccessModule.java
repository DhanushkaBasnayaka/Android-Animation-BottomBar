package tuckerbx.elegantmeia.com.au.newapplication.d.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationContext;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;
import tuckerbx.elegantmeia.com.au.newapplication.helpers.NetworkAccess;

/**
 * Created by dhanushka on 24/10/2017.
 */
@Module(includes = {ContextModule.class})
public class NetworkAccessModule {

    @Provides
    @ApplicationScope
    public NetworkAccess mNetworkAccess(@ApplicationContext Context context) {
        return new NetworkAccess(context);
    }
}
