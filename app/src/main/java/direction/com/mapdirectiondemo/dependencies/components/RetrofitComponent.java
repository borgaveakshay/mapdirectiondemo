package direction.com.mapdirectiondemo.dependencies.components;


import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.RetrofitModule;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonServiceScope;
import retrofit2.Retrofit;

@SingletonServiceScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
