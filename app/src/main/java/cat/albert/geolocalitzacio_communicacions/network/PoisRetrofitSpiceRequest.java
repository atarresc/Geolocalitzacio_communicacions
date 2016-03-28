package cat.albert.geolocalitzacio_communicacions.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;

/**
 * Created by albert on 15/03/2016.
 */
public class PoisRetrofitSpiceRequest extends RetrofitSpiceRequest<Poi.Llista, PoisAPI> {

    public PoisRetrofitSpiceRequest() {
        super(Poi.Llista.class, PoisAPI.class);
    }

    @Override
    public Poi.Llista loadDataFromNetwork() {
        return getService().getAll();
    }
}
