package direction.com.mapdirectiondemo.dependencies.components;

import dagger.Component;
import direction.com.mapdirectiondemo.data.repositories.repoimpl.HomeRepoImpl;
import direction.com.mapdirectiondemo.dependencies.module.HomeUseCaseModule;
import direction.com.mapdirectiondemo.dependencies.scopes.HomeUseCaseScope;

@HomeUseCaseScope
@Component(modules = HomeUseCaseModule.class)
public interface HomeUseCaseComponent {

    HomeRepoImpl getRepository();
}
