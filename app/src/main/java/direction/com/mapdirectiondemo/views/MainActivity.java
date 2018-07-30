package direction.com.mapdirectiondemo.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import direction.com.mapdirectiondemo.R;
import direction.com.mapdirectiondemo.databinding.ActivityMainBinding;
import direction.com.mapdirectiondemo.dependencies.components.DaggerHomeUseCaseComponent;
import direction.com.mapdirectiondemo.dependencies.components.DaggerMainViewModelComponent;
import direction.com.mapdirectiondemo.dependencies.components.HomeUseCaseComponent;
import direction.com.mapdirectiondemo.dependencies.components.MainViewModelComponent;
import direction.com.mapdirectiondemo.dependencies.module.MainViewModelModule;
import direction.com.mapdirectiondemo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mSupportMapFragment;
    private ActivityMainBinding mActivityMainBinding;
    private MainViewModel mMainViewModel;
    private GoogleMap mGoogleMap;
    private Place mSource;
    private Place mDestination;
    private Marker mSourceMarker;
    private Marker mDestinationMarker;
    private MainViewModelComponent mMainViewModelComponent;
    private HomeUseCaseComponent mUseCaseComponent;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (savedInstanceState == null) {
            mUseCaseComponent = DaggerHomeUseCaseComponent.builder().build();
            mMainViewModelComponent = DaggerMainViewModelComponent
                    .builder()
                    .mainViewModelModule(new MainViewModelModule(this))
                    .build();

            mMainViewModel.setmHomeUseCaseComponent(mUseCaseComponent);
            mMainViewModel.setmMainViewModelComponent(mMainViewModelComponent);
        }
        mMainViewModel.initModel();
        // mMainViewModel = new MainViewModel(mMainViewModelComponent, mUseCaseComponent);
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(this);
        mActivityMainBinding.setMainModel(mMainViewModel);
        setDirectionAPIListener();
        setDirectionCalledListnere();

    }

    private void setDirectionCalledListnere() {

        mMainViewModel.getGetDirectionCallListener().observe(this, this::directionCalled);
    }

    private void directionCalled(boolean isCalled) {

        if (isCalled) {
            mMainViewModel.getDirection(mSource.getLatLng(), mDestination.getLatLng());
        }

    }

    private void setDirectionAPIListener() {

        mMainViewModel.getPollyPoints().observe(this, this::onDirectionAPIResponse);

    }

    private void onDirectionAPIResponse(ArrayList<LatLng> polyLines) {

        addPolyLines(polyLines);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    public void searchLocation(int statusCode) {


        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, statusCode);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainViewModel.SOURCE_LOCATION) {
            if (resultCode == RESULT_OK) {
                mSource = PlaceAutocomplete.getPlace(this, data);
                mMainViewModel.setSourceName(mSource.getName().toString());
                mSourceMarker = addMarker(mSource.getLatLng());
                Log.i(TAG, "Place: " + mSource.getName());
            }

        }
        if (requestCode == MainViewModel.DESTINATION_LOCATION) {
            if (resultCode == RESULT_OK) {
                mDestination = PlaceAutocomplete.getPlace(this, data);
                mMainViewModel.setDestinationName(mDestination.getName().toString());
                mDestinationMarker = addMarker(mDestination.getLatLng());
                Log.i(TAG, "Place: " + mDestination.getName());
            }

        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            // TODO: Handle the error.
            Log.i(TAG, status.getStatusMessage());

        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
        if (mSourceMarker != null && mDestinationMarker != null) {
            zoomMap();
            mMainViewModel.isDirectionButtonEnabled.set(true);
        }
    }

    private Marker addMarker(LatLng latLng) {

        if (mGoogleMap != null) {

            return mGoogleMap.addMarker(new MarkerOptions().position(latLng));
        }
        return null;
    }

    private void zoomMap() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(mSourceMarker.getPosition());
        builder.include(mDestinationMarker.getPosition());
        LatLngBounds bounds = builder.build();
        int padding = 200; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mGoogleMap.animateCamera(cu);
    }

    public void clearMap() {

        if (mGoogleMap != null) {

            mGoogleMap.clear();
        }
    }

    private void addPolyLines(ArrayList<LatLng> polyLines) {
        if (mGoogleMap != null) {
            PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
            for (LatLng latLng : polyLines) {
                options.add(latLng);
            }
            mGoogleMap.addPolyline(options);

        }
    }
}
