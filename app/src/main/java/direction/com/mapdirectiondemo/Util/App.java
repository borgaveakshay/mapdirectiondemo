package direction.com.mapdirectiondemo.Util;

import android.app.Application;

import direction.com.mapdirectiondemo.dependencies.components.DaggerRetrofitComponent;
import direction.com.mapdirectiondemo.dependencies.components.DaggerServiceComponent;
import direction.com.mapdirectiondemo.dependencies.components.RetrofitComponent;
import direction.com.mapdirectiondemo.dependencies.components.ServiceComponent;
import direction.com.mapdirectiondemo.network.DirectionAPI;

public class App extends Application {

    private DirectionAPI mDirectionAPI;
    private ServiceComponent mServiceComponent;
    private static App instance;
    private RetrofitComponent mRetrofitComponent;


    public ServiceComponent getServiceComponent() {
        return mServiceComponent;
    }

    public RetrofitComponent getRetrofitComponent() {
        return mRetrofitComponent;
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
        mRetrofitComponent = DaggerRetrofitComponent.builder().build();
        mServiceComponent = DaggerServiceComponent.builder()
                .retrofitComponent(mRetrofitComponent)
                .build();
        setDirectionAPI(mServiceComponent.getDirectionAPI());
    }
}
