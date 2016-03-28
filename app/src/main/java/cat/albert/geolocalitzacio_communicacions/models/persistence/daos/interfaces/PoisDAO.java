package cat.albert.geolocalitzacio_communicacions.models.persistence.daos.interfaces;

import java.util.List;

import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;

/**
 * Created by albert on 15/03/2016.
 */
public interface PoisDAO {

    List<Poi> getById(final String id);
    List<Poi> getAll();
}
