package cat.albert.geolocalitzacio_communicacions.views.impl.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import cat.albert.geolocalitzacio_communicacions.R;
import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;
import cat.albert.geolocalitzacio_communicacions.network.PoisRetrofitSpiceRequest;
import cat.albert.geolocalitzacio_communicacions.presenters.impl.MainViewPresenterImpl;
import cat.albert.geolocalitzacio_communicacions.presenters.interfaces.IMainViewPresenter;
import cat.albert.geolocalitzacio_communicacions.views.interfaces.IMainView;

public class MapActivity extends BaseActivity implements IMainView, OnMapReadyCallback, View.OnClickListener {

    // ============================================================================================
    // ATTRIBUTES
    // ============================================================================================
    private GoogleMap mMap;
    private Button button;
    private EditText editText;

    private PoisRetrofitSpiceRequest titularsRequest;
    private IMainViewPresenter presenter;

    private ListUbicacioListener ubicacioListener;


    private UiSettings uiSettings;
    private String text;

    // ============================================================================================
    // ACTIVITY LIFE CYCLE
    // ============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ubicacioListener = new ListUbicacioListener();
        editText = (EditText) findViewById(R.id.editText);

        presenter = new MainViewPresenterImpl();
        presenter.onCreate(MapActivity.this);

        button = (Button) findViewById(R.id.button_cercar);
        button.setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public RequestListener<?> getListListener() {
        return null;
    }

    @Override
    public RequestListener<?> getUpdateListener() {
        return null;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("HOLA").icon(BitmapDescriptorFactory.defaultMarker(
//                BitmapDescriptorFactory.HUE_AZURE)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View view) {
        text = editText.getText().toString().trim().toLowerCase();
        presenter.getLocalitzacioById(text);

        if (text.trim().toUpperCase().equals("OLOT")) {
            mMap.clear();
            LatLng olot = new LatLng(42.188011169434, 2.4883439540863);
            mMap.addMarker(new MarkerOptions().position(olot).title("Volcà Montsacopa")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    .snippet("Olot"));
            LatLng olot2 = new LatLng(42.1711769104, 2.4800658226013);
            mMap.addMarker(new MarkerOptions().position(olot2).title("Museu dels volcans")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    .snippet("Olot"));
            LatLng olot3 = new LatLng(42.180759429932, 2.48015832901);
            mMap.addMarker(new MarkerOptions().position(olot3).title("Volcà Montolivet")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                    .snippet("Olot"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(olot, 13));
        } else if (text.trim().toUpperCase().equals("BARCELONA")) {
            mMap.clear();
            LatLng barcelona = new LatLng(41.404308319092, 2.1736149787903);
            mMap.addMarker(new MarkerOptions().position(barcelona).title("Sagrada Família")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                    .snippet("Barcelona"));
            LatLng barcelona2 = new LatLng(41.380367279053, 2.1224317550659);
            mMap.addMarker(new MarkerOptions().position(barcelona2).title("Camp Nou")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    .snippet("Barcelona"));
            LatLng barcelona3 = new LatLng(41.414085388184, 2.1527070999146);
            mMap.addMarker(new MarkerOptions().position(barcelona3).title("Parc Güell")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .snippet("Barcelona"));
            LatLng barcelona4 = new LatLng(41.366207122803, 2.1553511619568);
            mMap.addMarker(new MarkerOptions().position(barcelona4).title("Estadi Olímpic")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                    .snippet("Barcelona"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 13));
        } else if (text.trim().toUpperCase().equals("SANTA PAU")) {
            mMap.clear();
            LatLng santapau = new LatLng(42.154102325439, 2.5359098911285);
            mMap.addMarker(new MarkerOptions().position(santapau).title("Volcà Croscat")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .snippet("Santa Pau"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(santapau, 13));
        } else {
            mMap.clear();
            presenter.getLocalitzacioById(text);
        }
    }

    public final class ListUbicacioListener implements RequestListener<Poi.Llista> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(MapActivity.this, "ERROR. POI no existent", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Poi.Llista puntsInteres) {
            mMap.clear();
            LatLng latLng;
            Toast.makeText(MapActivity.this, "Cerca efectuada", Toast.LENGTH_SHORT).show();
            for (Poi p : puntsInteres) {
                latLng = new LatLng(p.getLatitude(), p.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(p.getName()));
            }

        }

    }
    // ============================================================================================
    // INTERFACE IMPLEMENTED METHODS
    // ============================================================================================


    // ============================================================================================
    // PRIVATE METHODS
    // ============================================================================================



    /**
     * Actualitzar la llista amb les dades de la llista
     */


    // ============================================================================================
    // PUBLIC METHODS
    // ============================================================================================

    // ============================================================================================
    // INNER CLASS
    // ============================================================================================

    /**
     * Implements a Listener class to manage received data
     */
}
