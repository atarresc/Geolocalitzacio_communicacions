package cat.albert.geolocalitzacio_communicacions.presenters.interfaces;

import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;
import cat.albert.geolocalitzacio_communicacions.views.impl.activities.BaseActivity;
import cat.albert.geolocalitzacio_communicacions.views.impl.activities.MapActivity;
import cat.albert.geolocalitzacio_communicacions.views.interfaces.IMainView;

/**
 * Created by albert on 15/03/2016.
 */
public interface IMainViewPresenter {

    public void onCreate(BaseActivity view);
    public void getLocalitzacioFromService();
    public void getLocalitzacioById(String localitzacio);


}
