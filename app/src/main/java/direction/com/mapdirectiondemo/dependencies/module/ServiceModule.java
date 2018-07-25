package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonServiceScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class ServiceModule {

    @Provides
    @SingletonServiceScope
    DirectionAPI getDirectionAPI(Retrofit retrofit) {
        return retrofit.create(DirectionAPI.class);
    }
}
