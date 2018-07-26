package direction.com.mapdirectiondemo.dependencies.components;

import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.HomeUseCaseModule;
import direction.com.mapdirectiondemo.dependencies.scopes.HomeUseCaseScope;
import direction.com.mapdirectiondemo.domain.interfaces.HomeScreenUseCase;

@HomeUseCaseScope
@Component(modules = HomeUseCaseModule.class)
public interface HomeUseCaseComponent {

    HomeScreenUseCase getHomeScreenUseCase();
}
