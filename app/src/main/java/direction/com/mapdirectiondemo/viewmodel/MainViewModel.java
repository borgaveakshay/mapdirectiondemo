package direction.com.mapdirectiondemo.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.PropertyChangeRegistry;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import direction.com.mapdirectiondemo.R;
import direction.com.mapdirectiondemo.dependencies.components.HomeUseCaseComponent;
import direction.com.mapdirectiondemo.dependencies.components.MainViewModelComponent;
import direction.com.mapdirectiondemo.models.DirectionResult;
import direction.com.mapdirectiondemo.models.LegsItem;
import direction.com.mapdirectiondemo.models.RoutesItem;
import direction.com.mapdirectiondemo.models.StepsItem;
import direction.com.mapdirectiondemo.views.MainActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("StaticFieldLeak")
public class MainViewModel extends ViewModel implements android.databinding.Observable {

    public static final int SOURCE_LOCATION = 1;
    public static final int DESTINATION_LOCATION = 2;

    public ObservableField<String> sourceName;

    public ObservableField<String> destinationName;

    public ObservableField<Boolean> isDirectionButtonEnabled;

    public ObservableField<Boolean> isRefreshing;

    private MainViewModelComponent mMainViewModelComponent;

    private HomeUseCaseComponent mHomeUseCaseComponent;

    private MutableLiveData<ArrayList<LatLng>> pollyPoints;
    private PropertyChangeRegistry mPropertyChangeRegistry;

    private MutableLiveData<Boolean> getDirectionCallListener;
    private MainActivity mActivity;


    public void setmMainViewModelComponent(MainViewModelComponent mMainViewModelComponent) {
        this.mMainViewModelComponent = mMainViewModelComponent;
    }

    public void setmHomeUseCaseComponent(HomeUseCaseComponent mHomeUseCaseComponent) {
        this.mHomeUseCaseComponent = mHomeUseCaseComponent;
    }

    public MutableLiveData<Boolean> getGetDirectionCallListener() {
        return getDirectionCallListener;
    }

    public void initModel() {
        mActivity = mMainViewModelComponent.getMainActivity();
        isRefreshing = mMainViewModelComponent.isRefreshing();
        isRefreshing.set(false);
        isDirectionButtonEnabled = mMainViewModelComponent.isDirectionButtonEnabled();
        isDirectionButtonEnabled.set(false);
        pollyPoints = mMainViewModelComponent.pollyPoints();
        getDirectionCallListener = mMainViewModelComponent.getDirectionCallListener();
        sourceName = mMainViewModelComponent.sourceName();
        destinationName = mMainViewModelComponent.destinationName();
        sourceName.set(mActivity.getString(R.string.source_default_name));
        destinationName.set(mActivity.getString(R.string.destination_default_name));
        mPropertyChangeRegistry = mMainViewModelComponent.getPropertyChangeRegistry();
    }
    public MutableLiveData<ArrayList<LatLng>> getPollyPoints() {
        return pollyPoints;
    }


    public String getSourceName() {
        return sourceName.get();
    }

    public void setSourceName(String sourceName) {
        this.sourceName.set(sourceName);


    }


    public String getDestinationName() {
        return destinationName.get();
    }

    public void setDestinationName(String destinationName) {
        this.destinationName.set(destinationName);

    }

    public void fetchSource(View view) {

        mActivity.clearMap();
        mActivity.searchLocation(SOURCE_LOCATION);

    }

    public void fetchDestination(View view) {

        mActivity.searchLocation(DESTINATION_LOCATION);

    }

    @SuppressLint("CheckResult")
    public void getDirection(LatLng source, LatLng destination) {

        isRefreshing.set(true);
        isDirectionButtonEnabled.set(false);
        Observable<DirectionResult> directionResultObservable = mHomeUseCaseComponent
                .getHomeScreenUseCase()
                .getDirectionResult(source.latitude + "," + source.longitude
                        , destination.latitude + "," + destination.longitude);

        directionResultObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDirectionResult, error -> Toast.makeText(mActivity, "Something Went Wrong", Toast.LENGTH_LONG).show());
    }

    private void setDirectionResult(DirectionResult directionResult) {
        isDirectionButtonEnabled.set(true);
        isRefreshing.set(false);
        if (directionResult != null) {

            ArrayList<LatLng> polyPoints = new ArrayList<>();
            for (RoutesItem routesItem : directionResult.getRoutes()) {

                for (LegsItem legsItem : routesItem.getLegs()) {
                    for (StepsItem stepsItem : legsItem.getSteps()) {
                        LatLng latLng = new LatLng(stepsItem.getEndLocation().getLat(), stepsItem.getEndLocation().getLng());
                        polyPoints.add(latLng);
                    }
                }
            }
            pollyPoints.postValue(polyPoints);
        }

    }

    public void getDirectionClicked(View view) {

        if (getDirectionCallListener.getValue() == null || !getDirectionCallListener.getValue()) {
            getDirectionCallListener.postValue(true);
        } else {
            getDirectionCallListener.postValue(false);
        }
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

        mPropertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

        mPropertyChangeRegistry.remove(callback);
    }
}
