package direction.com.mapdirectiondemo.domain.interfaces;

import direction.com.mapdirectiondemo.models.DirectionResult;

public interface HomeScreenUseCase extends BaseUseCase{

    DirectionResult getDirectionResult(String source, String destination);

}
