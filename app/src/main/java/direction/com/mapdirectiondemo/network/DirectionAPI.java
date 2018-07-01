package direction.com.mapdirectiondemo.network;

import direction.com.mapdirectiondemo.models.DirectionResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DirectionAPI {

    @GET("/maps/api/directions/json")
    Observable<DirectionResult> getDirections(@Query("origin") String source, @Query("destination") String destination, @Query("sensor") boolean isSensorEnabled);
}
