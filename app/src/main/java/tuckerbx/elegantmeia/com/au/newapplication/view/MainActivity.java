package tuckerbx.elegantmeia.com.au.newapplication.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tuckerbx.elegantmeia.com.au.newapplication.R;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.ApplicationComponent;
import tuckerbx.elegantmeia.com.au.newapplication.network.ApiService;
import tuckerbx.elegantmeia.com.au.newapplication.network.CallApi;
import tuckerbx.elegantmeia.com.au.newapplication.network.model.User;
import tuckerbx.elegantmeia.com.au.newapplication.view.base.BaseActivity;
import tuckerbx.elegantmeia.com.au.newapplication.view.fregment.ListFregment;

public class MainActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_notifications:
                    viewFregment(new ListFregment());
                    return true;

                case R.id.navigation_home:
                    viewFregment(new ListFregment());
                    return true;
                case R.id.navigation_dashboard:
                    viewFregment(new ListFregment());
                    return true;


            }
            return false;
        }
    };


    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ;
        viewFregment(new ListFregment());

    }

    private void viewFregment(Fragment f) {


        FragmentManager mFragmentManagerSignup = getSupportFragmentManager();
        FragmentTransaction transactionSignup = mFragmentManagerSignup.beginTransaction();
        transactionSignup.replace(R.id.container, f);
        transactionSignup.commit();
    }

    @Override
    protected void inject(ApplicationComponent component) {


    }


}
