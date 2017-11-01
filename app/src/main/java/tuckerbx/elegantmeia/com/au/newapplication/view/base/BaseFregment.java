package tuckerbx.elegantmeia.com.au.newapplication.view.base;


import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import tuckerbx.elegantmeia.com.au.newapplication.ProjectApplication;
import tuckerbx.elegantmeia.com.au.newapplication.R;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.ApplicationComponent;

/**
 * Created by dhanushka on 13/10/2017.
 */

public abstract class BaseFregment extends Fragment {

    public Context mContext;
    public String token = null;
    private Dialog mProgress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(((ProjectApplication) getActivity().getApplication()).getcomponent());
    }

    protected abstract void inject(ApplicationComponent getcomponent);


    public void showProgress() {

//        if (mProgress == null) {
//            mProgress = new Dialog(mContext, R.style.Progressbar);
//            mProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            mProgress.setContentView(R.layout.widget_progress_spinner);
//            mProgress.setCancelable(false);
//        }

        if (mProgress.isShowing() == false) {
            mProgress.show();
        }
    }

    public void dismissProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
            mProgress = null;
        }
    }


}