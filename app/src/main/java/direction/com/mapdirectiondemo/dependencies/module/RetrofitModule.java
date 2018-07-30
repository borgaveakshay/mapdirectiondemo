package direction.com.mapdirectiondemo.dependencies.module;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.constants.AppConstants;
import direction.com.mapdirectiondemo.dependencies.scopes.SingletonServiceScope;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @SingletonServiceScope
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
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
    @SingletonServiceScope
    public String getBaseUrl(){
        return AppConstants.RETRO_BASE_URL;
    }
}
