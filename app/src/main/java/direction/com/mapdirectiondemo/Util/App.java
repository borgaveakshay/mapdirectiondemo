package direction.com.mapdirectiondemo.Util;

import android.app.Application;

import direction.com.mapdirectiondemo.dependencies.components.DaggerServiceComponent;
import direction.com.mapdirectiondemo.dependencies.components.ServiceComponent;
import direction.com.mapdirectiondemo.network.DirectionAPI;

public class App extends Application {

    private DirectionAPI mDirectionAPI;
    private ServiceComponent mServiceComponent;
    private static App instance;


    public ServiceComponent getServiceComponent() {
        return mServiceComponent;
    }

    public static App getInstance() {
        return instance;
    }

    public DirectionAPI getDirectionAPI() {
        return mDirectionAPI;
    }

    public void setDirectionAPI(DirectionAPI mDirectionAPI) {
        this.mDirectionAPI = mDirectionAPI;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mServiceComponent = DaggerServiceComponent.builder().build();
        setDirectionAPI(mServiceComponent.getDirectionAPI());
    }
}
