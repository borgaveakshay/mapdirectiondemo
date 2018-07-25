package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.constants.AppConstants;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonServiceScope;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @SingletonServiceScope
    public Retrofit getRetrofit(String baseUrl
            , GsonConverterFactory gsonConverterFactory
            , RxJava2CallAdapterFactory rxJava2CallAdapterFactory
            , OkHttpClient okHttpClient) {

        return new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    @SingletonServiceScope
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @SingletonServiceScope
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @SingletonServiceScope
    public RxJava2CallAdapterFactory getRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public String getBaseUrl(){
        return AppConstants.RETRO_BASE_URL;
    }
}
