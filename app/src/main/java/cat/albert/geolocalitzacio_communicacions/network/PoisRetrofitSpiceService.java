package cat.albert.geolocalitzacio_communicacions.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by albert on 15/03/2016.
 */
public class PoisRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://www.infobosccoma.net/pmdm/pois/v1/pois";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(PoisAPI.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
