package cat.albert.geolocalitzacio_communicacions.models.persistence.daos.impl;

import android.content.Context;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

import cat.albert.geolocalitzacio_communicacions.R;
import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;
import cat.albert.geolocalitzacio_communicacions.models.persistence.daos.interfaces.PoisDAO;
import cat.albert.geolocalitzacio_communicacions.network.PoisAPI;
import cat.albert.geolocalitzacio_communicacions.views.impl.activities.BaseActivity;

/**
 * Created by albert on 15/03/2016.
 */
public class PoisRESTDAO implements PoisDAO {
    private Context context;

    public PoisRESTDAO(Context context) {
        this.context = context;
    }

    @Override
    public List<Poi> getById(final String id) {
        List<Poi> llocs = new ArrayList<>();
        RetrofitSpiceRequest<Poi.Llista, PoisAPI> request =
                new RetrofitSpiceRequest<Poi.Llista,
                        PoisAPI>(Poi.Llista.class,
                        PoisAPI.class) {
                    @Override
                    public Poi.Llista loadDataFromNetwork() throws Exception {
                        return getService().getByCity(id);
                    }
                };
        ((BaseActivity) context).getSpiceManager().execute(request, "Localització", DurationInMillis.ONE_MINUTE,
                (RequestListener<Poi.Llista>)
                        ((BaseActivity) context).getListListener());
        return llocs;
    }

    /**
     * Obtenir tots els titulars.
     * Es fa una petició GET al servei REST
     * @return
     */
    @Override
    public List<Poi> getAll() {
        List<Poi> pois = new ArrayList();

        // create request object
        RetrofitSpiceRequest<Poi.Llista, PoisAPI> request = new RetrofitSpiceRequest<Poi.Llista, PoisAPI>(Poi.Llista.class, PoisAPI.class) {
            @Override
            public Poi.Llista loadDataFromNetwork() throws Exception {
                return getService().getAll();
            }
        };
        ((BaseActivity) context).getSpiceManager().execute(request, context.getString(R.string.cache_pois), DurationInMillis.ONE_MINUTE, (RequestListener<Poi.Llista>) ((BaseActivity) context).getListListener());
        return pois;
    }
}
