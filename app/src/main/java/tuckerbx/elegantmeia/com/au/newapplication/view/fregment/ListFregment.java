package tuckerbx.elegantmeia.com.au.newapplication.view.fregment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tuckerbx.elegantmeia.com.au.newapplication.R;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.ApplicationComponent;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.CallApi;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.Bookingresponce;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.User;
import tuckerbx.elegantmeia.com.au.newapplication.view.adapters.ListAdapters;
import tuckerbx.elegantmeia.com.au.newapplication.view.base.BaseFregment;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by dhanushka on 01/11/2017.
 */

public class ListFregment extends BaseFregment implements CallApi.GetSDPListCallback, ListAdapters.ListAdaptersCallback {


    public ListFregment() {

    }

    ArrayList<User> mUser;
    ListAdapters adapters;
    @BindView(R.id.recycleView)
    RecyclerView mRecyclerView;

    @BindView(R.id.search)
    EditText editsearch;

    @Inject
    CallApi mDataManager;

    @Inject
    ApiService mSicModul;

    @Inject
    Picasso mPicasso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view, container, false);
        ButterKnife.bind(this, view);
        mUser = new ArrayList<>();
        intView();
        return view;
    }

    private void intView() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        hideSoftKeyboard();
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mDataManager.getServercal(this);

        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());

                adapters.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });


    }

    @Override
    protected void inject(ApplicationComponent component) {
        this.mSicModul = component.getApplicatonService();
        this.mDataManager = component.getClasesink();
        this.mPicasso = component.getPicasso();
    }

    @Override
    public void onSuccessGetSDPList(Bookingresponce sdpList) {
        mUser.addAll(sdpList.data);


        adapters = new ListAdapters(getActivity(), this, mUser, mPicasso);
        mRecyclerView.setAdapter(adapters);

    }

    @Override
    public void onFailedGetSDPList(String message) {

    }

    @Override
    public void onClickJobItem(User item, int position) {

    }

    public void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
