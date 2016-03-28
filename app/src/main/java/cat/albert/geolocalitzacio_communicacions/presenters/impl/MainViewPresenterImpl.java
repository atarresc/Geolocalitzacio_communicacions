package cat.albert.geolocalitzacio_communicacions.presenters.impl;

import android.support.v4.app.FragmentActivity;

import java.util.List;

import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;
import cat.albert.geolocalitzacio_communicacions.models.persistence.daos.impl.PoisRESTDAO;
import cat.albert.geolocalitzacio_communicacions.models.persistence.daos.interfaces.PoisDAO;
import cat.albert.geolocalitzacio_communicacions.presenters.interfaces.IMainViewPresenter;
import cat.albert.geolocalitzacio_communicacions.views.impl.activities.BaseActivity;
import cat.albert.geolocalitzacio_communicacions.views.interfaces.IMainView;

/**
 * Created by albert on 15/03/2016.
 */
public class MainViewPresenterImpl implements IMainViewPresenter {

    private BaseActivity view;
    private List<Poi> pois;
    private PoisDAO poisDAO;

    @Override
    public void onCreate(BaseActivity view) {
        this.view = view;
        poisDAO = new PoisRESTDAO(view);
    }

    public void getLocalitzacioFromService() {
        pois = poisDAO.getAll();
    }

    public void getLocalitzacioById(String localitzacio) {
        pois = poisDAO.getById(localitzacio);
    }
}
