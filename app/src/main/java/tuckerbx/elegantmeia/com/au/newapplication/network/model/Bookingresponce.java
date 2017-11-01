
package tuckerbx.elegantmeia.com.au.newapplication.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Bookingresponce {

    @SerializedName("status")
    @Expose
    public boolean status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("data")
    @Expose
    public ArrayList<User> data;

}
