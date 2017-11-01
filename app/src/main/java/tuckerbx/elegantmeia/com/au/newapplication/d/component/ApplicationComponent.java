package tuckerbx.elegantmeia.com.au.newapplication.d.component;


import com.squareup.picasso.Picasso;

import dagger.Component;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.ActivityModule;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.ApplicarionServicemodule;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.NetworkAccessModule;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.PicassoModule;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.SicModul;
import tuckerbx.elegantmeia.com.au.newapplication.helpers.NetworkAccess;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.CallApi;

/**
 * Created by dhanushka on 10/10/2017.
 */

@ApplicationScope
@Component(modules = {ApplicarionServicemodule.class, PicassoModule.class, ActivityModule.class, SicModul.class, NetworkAccessModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();
    ApiService getApplicatonService();
    CallApi getClasesink();
    NetworkAccess getNetworkAccess();
}
