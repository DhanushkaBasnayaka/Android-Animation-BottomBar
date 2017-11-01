package tuckerbx.elegantmeia.com.au.newapplication.d.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tuckerbx.elegantmeia.com.au.newapplication.d.ApplicationScope;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.DateTimeConverter;

/**
 * Created by dhanushka on 10/10/2017.
 */
@Module(includes = NetworkModule.class)
public class ApplicarionServicemodule {

    @Provides
    @ApplicationScope
    public ApiService apiService(Retrofit applicatioRetrofit) {

        return applicatioRetrofit.create(ApiService.class);
    }

    @Provides
    @ApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("http://dev.appslanka.com/")
                .build();
    }


}
