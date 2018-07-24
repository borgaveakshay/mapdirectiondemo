package direction.com.mapdirectiondemo.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import direction.com.mapdirectiondemo.MainActivity;
import direction.com.mapdirectiondemo.R;
import direction.com.mapdirectiondemo.Util.App;
import direction.com.mapdirectiondemo.models.LegsItem;
import direction.com.mapdirectiondemo.models.RoutesItem;
import direction.com.mapdirectiondemo.models.StepsItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("StaticFieldLeak")
public class MainViewModel extends BaseObservable {

    public static final int SOURCE_LOCATION = 1;
    public static final int DESTINATION_LOCATION = 2;
    public ObservableField<String> sourceName = new ObservableField<>();
    public ObservableField<String> destinationName = new ObservableField<>();
    private MutableLiveData<ArrayList<LatLng>> pollyPoints = new MutableLiveData<>();
    private MutableLiveData<Boolean> getDirectionCallListener = new MutableLiveData<>();

    public MutableLiveData<Boolean> getGetDirectionCallListener() {
        return getDirectionCallListener;
    }

    public void setGetDirectionCallListener(Boolean getDirectionCallListener) {
        this.getDirectionCallListener.setValue(getDirectionCallListener);
    }

    private AppCompatActivity mActivity;

    public MainViewModel(AppCompatActivity activity) {

        mActivity = activity;
        sourceName.set(activity.getString(R.string.source_default_name));
        destinationName.set(activity.getString(R.string.destination_default_name));

    }

    public MutableLiveData<ArrayList<LatLng>> getPollyPoints() {
        return pollyPoints;
    }

    public void setPollyPoints(MutableLiveData<ArrayList<LatLng>> poolyPoints) {
        this.pollyPoints = poolyPoints;
    }

    @Bindable
    public String getSourceName() {
        return sourceName.get();
    }

    public void setSourceName(String sourceName) {
        this.sourceName.set(sourceName);
        notifyPropertyChanged(BR.sourceName);
    }

    @Bindable
    public String getDestinationName() {
        return destinationName.get();
    }

    public void setDestinationName(String destinationName) {
        this.destinationName.set(destinationName);
        notifyPropertyChanged(BR.destinationName);
    }

    public void fetchSource(View view) {

        ((MainActivity) mActivity).clearMap();
        ((MainActivity) mActivity).searchLocation(SOURCE_LOCATION);

    }

    public void fetchDestination(View view) {

        ((MainActivity) mActivity).searchLocation(DESTINATION_LOCATION);

    }

    @SuppressLint("CheckResult")
    public void getDirection(LatLng source, LatLng destination) {


        App
                .getInstance()
                .getDirectionAPI().getDirections(source.latitude + "," + source.longitude, destination.latitude + "," + destination.longitude, false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(directionResult -> {

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

                        },
                        error -> {

                        });
    }

    public void getDirectionClicked(View view) {

        if (getDirectionCallListener.getValue() == null || !getDirectionCallListener.getValue()) {
            getDirectionCallListener.postValue(true);
        } else {
            getDirectionCallListener.postValue(false);
        }
    }

}
