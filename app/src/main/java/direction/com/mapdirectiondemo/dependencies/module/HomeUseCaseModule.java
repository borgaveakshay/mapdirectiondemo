package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.dependencies.scopes.HomeUseCaseScope;
import direction.com.mapdirectiondemo.domain.interfaces.HomeScreenUseCase;
import direction.com.mapdirectiondemo.domain.interfaces.implementations.HomeScreenUserCaseImpl;

@Module
public class HomeUseCaseModule {

    @Provides
    @HomeUseCaseScope
    public HomeScreenUseCase getRepository(){
        return new HomeScreenUserCaseImpl();
    }
}
