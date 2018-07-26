package direction.com.mapdirectiondemo.data.repositories.repoimpl;

import android.annotation.SuppressLint;

import direction.com.mapdirectiondemo.Util.App;
import direction.com.mapdirectiondemo.data.repositories.HomeRepository;
import direction.com.mapdirectiondemo.models.DirectionResult;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import io.reactivex.Observable;

public class HomeRepoImpl implements HomeRepository {

    DirectionAPI mDirectionAPI;
    DirectionResult mDirectionResult;

    public HomeRepoImpl() {

        mDirectionAPI = App.getInstance().getDirectionAPI();
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<DirectionResult> getDirectionResult(String source, String destination) {
        return mDirectionAPI.getDirections(source, destination, false);
    }
}
