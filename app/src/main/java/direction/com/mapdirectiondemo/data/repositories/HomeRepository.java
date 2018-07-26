package direction.com.mapdirectiondemo.data.repositories;

import direction.com.mapdirectiondemo.models.DirectionResult;
import io.reactivex.Observable;

public interface HomeRepository {

    Observable<DirectionResult> getDirectionResult(String source, String destination);
}
