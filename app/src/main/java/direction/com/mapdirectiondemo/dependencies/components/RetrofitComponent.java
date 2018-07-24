package direction.com.mapdirectiondemo.dependencies.components;


import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.RetrofitModule;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonScope;
import retrofit2.Retrofit;

@SingletonScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
