package direction.com.mapdirectiondemo.data.repositories.repoimpl;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import direction.com.mapdirectiondemo.data.repositories.HomeRepository;
import direction.com.mapdirectiondemo.models.DirectionResult;
import direction.com.mapdirectiondemo.network.DirectionAPI;
import io.reactivex.Observable;

public class HomeRepoImpl implements HomeRepository {

    @Inject
    DirectionAPI mDirectionAPI;

    @Inject
    public HomeRepoImpl() {
        // primary Constructor for Injection
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<DirectionResult> getDirectionResult(String source, String destination) {
        return mDirectionAPI.getDirections(source, destination, false);
    }
}
