package direction.com.mapdirectiondemo.domain.interfaces;


import direction.com.mapdirectiondemo.models.DirectionResult;
import io.reactivex.Observable;

public interface HomeScreenUseCase extends BaseUseCase{

    Observable<DirectionResult> getDirectionResult(String source, String destination);

}
