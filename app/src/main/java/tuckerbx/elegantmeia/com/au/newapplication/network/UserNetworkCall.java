package tuckerbx.elegantmeia.com.au.newapplication.network;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationContext;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.ContextModule;

/**
 * Created by dhanushka on 12/10/2017.
 */
@Module(includes = {ContextModule.class})
public class UserNetworkCall {


    @Provides
    @ApplicationScope
    public Context apiService(@ApplicationContext Context context) {

        return context;
    }


}
