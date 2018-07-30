package direction.com.mapdirectiondemo.dependencies.components;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.databinding.PropertyChangeRegistry;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import dagger.Component;
import direction.com.mapdirectiondemo.dependencies.module.MainViewModelModule;
import direction.com.mapdirectiondemo.dependencies.scopes.MainViewModelScope;
import direction.com.mapdirectiondemo.views.MainActivity;

@MainViewModelScope
@Component(modules = {MainViewModelModule.class})
public interface MainViewModelComponent {

    MutableLiveData<ArrayList<LatLng>> pollyPoints();

    ObservableField<String> sourceName();

    ObservableField<String> destinationName();

    ObservableField<Boolean> isRefreshing();

    PropertyChangeRegistry getPropertyChangeRegistry();

    ObservableField<Boolean> isDirectionButtonEnabled();

    MutableLiveData<Boolean> getDirectionCallListener();

    MainActivity getMainActivity();

}
