package direction.com.mapdirectiondemo.dependencies.components;

import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.ServiceModule;
import direction.com.mapdirectiondemo.dependencies.scopes.ServiceScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;

@ServiceScope
@Component(modules = {ServiceModule.class}, dependencies = RetrofitComponent.class)
public interface ServiceComponent {
    DirectionAPI getDirectionAPI();
}
