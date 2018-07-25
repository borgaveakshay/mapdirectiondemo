package direction.com.mapdirectiondemo.data.repositories.repoimpl;

import android.annotation.SuppressLint;

import direction.com.mapdirectiondemo.Util.App;
import direction.com.mapdirectiondemo.data.repositories.HomeRepository;
import direction.com.mapdirectiondemo.models.DirectionResult;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeRepoImpl implements HomeRepository {

    DirectionAPI mDirectionAPI;
    DirectionResult mDirectionResult;

    public HomeRepoImpl() {

        mDirectionAPI = App.getInstance().getDirectionAPI();
    }

    @SuppressLint("CheckResult")
    @Override
    public DirectionResult getDirectionResult(String source, String destination) {

        mDirectionAPI.getDirections(source, destination, false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            mDirectionResult = result;
                        },
                        error -> {
                        });
        return mDirectionResult;
    }
}
