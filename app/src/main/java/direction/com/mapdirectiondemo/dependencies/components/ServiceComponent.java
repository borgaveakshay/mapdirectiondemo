package direction.com.mapdirectiondemo.dependencies.components;

import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.ServiceModule;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonScope;
import direction.com.mapdirectiondemo.network.DirectionAPI;

@SingletonScope
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
    DirectionAPI getDirectionAPI();
}
