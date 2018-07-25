package direction.com.mapdirectiondemo.dependencies.components;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import dagger.Component;
import direction.com.mapdirectiondemo.MainActivity;
import direction.com.mapdirectiondemo.dependencies.module.MainViewModelModule;
import direction.com.mapdirectiondemo.dependencies.scopes.MainViewModelScope;

@MainViewModelScope
@Component(modules = {MainViewModelModule.class})
public interface MainViewModelComponent {

    ObservableField<String> sourceName();

    ObservableField<String> destinationName();

    MutableLiveData<ArrayList<LatLng>> pollyPoints();

    MutableLiveData<Boolean> getDirectionCallListener();

    MainActivity getMainActivity();

}
