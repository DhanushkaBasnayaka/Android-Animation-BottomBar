package tuckerbx.elegantmeia.com.au.newapplication.view.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public ListAdapters(Context context, ListAdapters.ListAdaptersCallback callback, List<User> dataSet) {
        mContext = context;
        mCallback = callback;
        mArrayList = (ArrayList<User>) dataSet;
        mFilteredList = (ArrayList<User>) dataSet;

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

    @Override
    public void onBindViewHolder(ListAdapters.Holder holder, int position) {
        User item = mFilteredList.get(position);
        holder.ratingBar.setRating(Float.valueOf(item.imdb_rate));
        holder.txtName.setText(item.movie_name);
        holder.txtSinama.setText(item.theater.get(0).name);
        Calendar c = Calendar.getInstance();
        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(mContext,
                    R.anim.listview_animetion);
            holder.viewitem.startAnimation(animation);
            lastPosition = position;
        }

//        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateObj = null;
//        try {
//            dateObj = curFormater.parse(item.end_date);
//
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedDate = df.format(c.getTime());
//
//            System.out.println("Current time => " + formattedDate + "/" + dateObj);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//


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
}
