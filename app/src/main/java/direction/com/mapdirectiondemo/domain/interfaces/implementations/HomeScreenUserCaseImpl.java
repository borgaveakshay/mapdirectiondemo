package direction.com.mapdirectiondemo.domain.interfaces.implementations;


import javax.inject.Inject;

import direction.com.mapdirectiondemo.data.repositories.HomeRepository;
import direction.com.mapdirectiondemo.domain.interfaces.HomeScreenUseCase;
import direction.com.mapdirectiondemo.models.DirectionResult;
import io.reactivex.Observable;

public class HomeScreenUserCaseImpl implements HomeScreenUseCase {

    @Inject
    HomeRepository mHomeRepository;

    @Inject
    public HomeScreenUserCaseImpl() {
        // primary Constructor for Injection
    }
    @Override
    public Observable<DirectionResult> getDirectionResult(String source, String destination) {
        return mHomeRepository.getDirectionResult(source, destination);
    }
}
