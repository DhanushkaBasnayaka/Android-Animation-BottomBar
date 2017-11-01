package tuckerbx.elegantmeia.com.au.newapplication.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhanushka on 10/10/2017.
 */

public class User {
    @SerializedName("booking_start_date")
    @Expose
    public String booking_start_date;

    @SerializedName("date_release")
    @Expose
    public String date_release;

    @SerializedName("end_date")
    @Expose
    public String end_date;

    @SerializedName("genre")
    @Expose
    public String genre;

    @SerializedName("imdb_rate")
    @Expose
    public String imdb_rate;

    @SerializedName("movie_content")
    @Expose
    public String movie_content;

    @SerializedName("movie_id")
    @Expose
    public String movie_id;

    @SerializedName("movie_name")
    @Expose
    public String movie_name;

    @SerializedName("portrait_image")
    @Expose
    public String portrait_image;



    @SerializedName("theater")
    @Expose
    public List<Trailer> theater;

    @SerializedName("url_key")
    @Expose
    public String url_key;

}
