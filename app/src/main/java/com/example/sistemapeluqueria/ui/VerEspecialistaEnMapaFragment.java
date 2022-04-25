package com.example.sistemapeluqueria.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.adapter.MostrarEspecialistaAdapter;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VerEspecialistaEnMapaFragment extends Fragment {

    private List<MostrarEspecialistaModel> listaEspecialistas ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_ver_especialista_en_mapa, container, false);

        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapafragment);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    //@Override
                    //public void onMapClick(@NonNull LatLng latLng) {
                    //    MarkerOptions markerOptions = new MarkerOptions();
                     //   markerOptions.position(latLng);
                    //    markerOptions.title(latLng.latitude +" : "+ latLng.longitude);

                      //  googleMap.clear();
                        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                        //googleMap.addMarker(markerOptions);

                    //}
                //});


                googleMap.getUiSettings().setZoomControlsEnabled(true);
                MostrarEspecialistaModel m= listaEspecialistas.get(0);
                LatLng centro = new LatLng(m.getlatitud(), m.getLongitud());
                for (int i = 0; i<listaEspecialistas.size(); i++){
                   m = listaEspecialistas.get(i);
                    googleMap.addMarker(new MarkerOptions()
                           .position(new LatLng(m.getlatitud(), m.getLongitud()))
                            .title(m.getNombre()).snippet(m.getDireccion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.iconmapa)));
               }

               googleMap.setMaxZoomPreference(65);
               googleMap.setMinZoomPreference(12);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
            }
        });
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            String data = getArguments().getString("especialistas");

            try
            {
                listaEspecialistas= new ArrayList<>();
                JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i<jsonArray.length(); i++)
                {

                    JSONObject o = jsonArray.getJSONObject(i);
                    MostrarEspecialistaModel reg =  new MostrarEspecialistaModel( o.getInt("pk_especialistas"), o.getString("nombre"), o.getString("imagenBase64"),
                            o.getString("direccion"), o.getDouble("latitud"), o.getDouble("longitud"),o.getInt("calificacion"), o.getInt("fk_categoria"), "");
                    listaEspecialistas.add(reg);

                }
                Log.i("Informacion mapa", listaEspecialistas.size() +" ");
            }
            catch (JSONException e)
            {
                Log.i("======> e", e.getMessage());
            }

        }
    }
}