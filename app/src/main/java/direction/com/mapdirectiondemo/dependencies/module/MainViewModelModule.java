package direction.com.mapdirectiondemo.dependencies.module;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.databinding.PropertyChangeRegistry;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import direction.com.mapdirectiondemo.views.MainActivity;
import direction.com.mapdirectiondemo.dependencies.qualifires.DirectionQualifier;
import direction.com.mapdirectiondemo.dependencies.qualifires.SorceNameQualifier;
import direction.com.mapdirectiondemo.dependencies.scopes.MainViewModelScope;

@Module
public class MainViewModelModule {

    private final MainActivity mainActivity;

    public MainViewModelModule(MainActivity activity) {
        mainActivity = activity;
    }

    @Provides
    @MainViewModelScope
    @SorceNameQualifier
    public ObservableField<String> sourceName() {
        return new ObservableField<>();
    }

    @Provides
    @MainViewModelScope
    public ObservableField<String> destinationName() {

        return new ObservableField<>();
    }


    @Provides
    @MainViewModelScope
    @DirectionQualifier
    public ObservableField<Boolean> isDirectionButtonEnabled() {

        return new ObservableField<>();
    }

    @Provides
    @MainViewModelScope
    public ObservableField<Boolean> isRefreshing() {

        return new ObservableField<>();
    }


    @Provides
    @MainViewModelScope
    public MutableLiveData<ArrayList<LatLng>> pollyPoints() {

        return new MutableLiveData<>();
    }

    @Provides
    @MainViewModelScope
    public MutableLiveData<Boolean> getDirectionCallListener() {

        return new MutableLiveData<>();
    }

    @Provides
    @MainViewModelScope
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainViewModelScope
    public PropertyChangeRegistry getPropertyChangeRegistry(){
        return new PropertyChangeRegistry();
    }

}
