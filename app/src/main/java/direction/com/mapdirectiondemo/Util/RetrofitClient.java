package direction.com.mapdirectiondemo.Util;

import direction.com.mapdirectiondemo.network.DirectionAPI;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mRetrofitClient;
    private Retrofit mRetrofit;
    private DirectionAPI mDirectionAPI;
    private static final String BASE_URL = "https://maps.googleapis.com";

    private RetrofitClient() {

        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {

        if (mRetrofitClient == null) {

            mRetrofitClient = new RetrofitClient();

        }
        return mRetrofitClient;
    }

    public DirectionAPI getDirectionAPI() {

        if (mDirectionAPI == null) {
            mDirectionAPI = mRetrofit.create(DirectionAPI.class);
        }
        return mDirectionAPI;
    }


}
