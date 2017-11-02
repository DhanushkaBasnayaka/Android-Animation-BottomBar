package tuckerbx.elegantmeia.com.au.newapplication.view.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tuckerbx.elegantmeia.com.au.newapplication.R;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.Bookingresponce;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.User;

/**
 * Created by dhanushka on 01/11/2017.
 */

public class ListAdapters extends RecyclerView.Adapter<ListAdapters.Holder> implements Filterable {

    private Context mContext;
    private ListAdapters.ListAdaptersCallback mCallback;
    private ArrayList<User> mArrayList;
    private ArrayList<User> mFilteredList;
    int lastPosition = -1;
    Picasso picasso;

    public ListAdapters(Context context, ListAdaptersCallback callback, List<User> dataSet, Picasso mPicasso) {
        mContext = context;
        mCallback = callback;
        mArrayList = (ArrayList<User>) dataSet;
        mFilteredList = (ArrayList<User>) dataSet;
        picasso = mPicasso;

    }

    public void addAll(final List<User> dataSet) {
        notifyDataSetChanged();
        mFilteredList.clear();
        mFilteredList.addAll(dataSet);
        notifyDataSetChanged();
    }

    public void addItem(User item) {

        mFilteredList.add(item);
        notifyDataSetChanged();
    }

    public void addItemTopOfList(User item) {

        mFilteredList.add(0, item);
        notifyItemInserted(0);
    }

    public void remove(int index) {

        mFilteredList.remove(index);
        notifyDataSetChanged();
    }

    public void updateItem(int position, User item) {

        mFilteredList.set(position, item);
        notifyItemChanged(position);
    }

    public void clear() {

        if (mFilteredList != null) {
            mFilteredList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public ListAdapters.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListAdapters.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rolls_item_, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ListAdapters.Holder holder, int position) {
        User item = mFilteredList.get(position);
        int valu = (int) (Float.valueOf(item.imdb_rate) / 2);
        holder.ratingBar.setRating(valu);
        holder.txtName.setText(item.movie_name);
        holder.txtSinama.setText(item.theater.get(0).name);


        picasso.load(item.portrait_image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageViewItem);

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(mContext,
                    R.anim.listview_animetion);
            holder.viewitem.startAnimation(animation);
            lastPosition = position;
        }


        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


        if (getCountOfDays(df.format(c.getTime()), (item.end_date)) > 0) {

            if (getCountOfDays(df.format(c.getTime()), (item.booking_start_date)) < 0) {
                holder.timeText.setVisibility(View.GONE);
                holder.bookText.setVisibility(View.VISIBLE);

            }

        } else {
            holder.timeText.setVisibility(View.VISIBLE);
            holder.bookText.setVisibility(View.GONE);
            SimpleDateFormat formatToUTC = new SimpleDateFormat("yyyy-MM-dd");
            formatToUTC.setTimeZone(TimeZone.getTimeZone("GMT"));

            SimpleDateFormat formatLocal = new SimpleDateFormat("MMM");
            formatLocal.setTimeZone(TimeZone.getDefault());

            SimpleDateFormat formatLocal2 = new SimpleDateFormat("dd ");
            formatLocal2.setTimeZone(TimeZone.getDefault());

            try {
                String str = formatLocal.format(formatToUTC.parse(item.booking_start_date));
                String str2 = formatLocal2.format(formatToUTC.parse(item.booking_start_date));
                holder.month.setText("" + str);
                holder.date.setText("" + str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public int getItemCount() {
        return mFilteredList == null ? 0 : mFilteredList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tex_name)
        TextView txtName;

        @BindView(R.id.viewitem)
        ConstraintLayout viewitem;

        @BindView(R.id.text_sinama)
        TextView txtSinama;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.time_text)
        LinearLayout timeText;

        @BindView(R.id.month)
        TextView month;

        @BindView(R.id.book_text)
        TextView bookText;


        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.imageView)
        ImageView imageViewItem;


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ListAdaptersCallback {
        void onClickJobItem(User item, int position);
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;

                } else {

                    ArrayList<User> filteredList = new ArrayList<>();

                    for (User androidVersion : mArrayList) {

                        if (androidVersion.movie_name.toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<User>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    public int getCountOfDays(String createdDateString, String expireDateString) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return ((int) dayCount);
    }
}
