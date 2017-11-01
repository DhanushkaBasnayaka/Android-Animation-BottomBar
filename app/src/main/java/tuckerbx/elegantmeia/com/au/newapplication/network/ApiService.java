package tuckerbx.elegantmeia.com.au.newapplication.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.Bookingresponce;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.User;

/**
 * Created by dhanushka on 10/10/2017.
 */

public interface ApiService {

    @POST("android-test/movies.php")
    Call<Bookingresponce> getAllRepos();

}
