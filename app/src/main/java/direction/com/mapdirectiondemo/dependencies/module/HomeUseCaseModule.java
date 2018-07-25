package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.data.repositories.repoimpl.HomeRepoImpl;
import direction.com.mapdirectiondemo.dependencies.scopes.HomeUseCaseScope;

@Module
public class HomeUseCaseModule {

    @Provides
    @HomeUseCaseScope
    public HomeRepoImpl getRepository(){
        return new HomeRepoImpl();
    }
}
