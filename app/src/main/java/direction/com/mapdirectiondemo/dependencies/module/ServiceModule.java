package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class ServiceModule {

    @Provides
    @SingletonScope
    DirectionAPI getDirectionAPI(Retrofit retrofit) {
        return retrofit.create(DirectionAPI.class);
    }
}
