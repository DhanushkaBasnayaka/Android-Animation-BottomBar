package tuckerbx.elegantmeia.com.au.newapplication.view.base;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;


import tuckerbx.elegantmeia.com.au.newapplication.ProjectApplication;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.ActivityComponent;
import tuckerbx.elegantmeia.com.au.newapplication.d.component.ApplicationComponent;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Picasso picasso;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(((ProjectApplication) getApplication()).getcomponent());

    }


    protected abstract void inject(ApplicationComponent component);

}
