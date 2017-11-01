package tuckerbx.elegantmeia.com.au.newapplication.network;


import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tuckerbx.elegantmeia.com.au.newapplication.helpers.NetworkAccess;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.Bookingresponce;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.User;

/**
 * Created by dhanushka on 19/10/2017.
 */

public class CallApi {

    @Inject
    ApiService mSicModul;
    private Context mContext;



    public CallApi(Context context, ApiService m) {
        mContext = context;
        mSicModul = m;
    }


    public void getServercal(final GetSDPListCallback callback) {

        if (!NetworkAccess.isNetworkAvailable()) {
            callback.onFailedGetSDPList(null);
            return;
        }


        mSicModul.getAllRepos().enqueue(new Callback<Bookingresponce>() {
            @Override
            public void onResponse(Call<Bookingresponce> call, Response<Bookingresponce> response) {

                callback.onSuccessGetSDPList(response.body());
            }

            @Override
            public void onFailure(Call<Bookingresponce> call, Throwable t) {

            }
        });


//                });


    }

    public interface GetSDPListCallback {
        void onSuccessGetSDPList(Bookingresponce body);

        void onFailedGetSDPList(String message);
    }
}
