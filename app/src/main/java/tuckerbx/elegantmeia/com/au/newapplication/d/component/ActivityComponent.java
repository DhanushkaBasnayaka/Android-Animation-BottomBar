package tuckerbx.elegantmeia.com.au.newapplication.d.component;



import dagger.Component;
import tuckerbx.elegantmeia.com.au.newapplication.d.PerActivity;
import tuckerbx.elegantmeia.com.au.newapplication.d.module.ActivityModule;
import tuckerbx.elegantmeia.com.au.newapplication.view.base.BaseActivity;

/**
 * Created by dhanushka on 10/10/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
}
