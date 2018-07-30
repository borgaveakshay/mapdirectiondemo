package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.dependencies.scopes.ServiceScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Provides
    @ServiceScope
    DirectionAPI getDirectionAPI(Retrofit retrofit) {
        return retrofit.create(DirectionAPI.class);
    }
}
