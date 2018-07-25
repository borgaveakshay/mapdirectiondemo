package direction.com.mapdirectiondemo.dependencies.components;

import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.ServiceModule;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonServiceScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;

@SingletonServiceScope
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
    DirectionAPI getDirectionAPI();
}
