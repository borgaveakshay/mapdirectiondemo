package direction.com.mapdirectiondemo.data.repositories;

import direction.com.mapdirectiondemo.models.DirectionResult;

public interface HomeRepository {

    DirectionResult getDirectionResult(String source, String destination);
}
