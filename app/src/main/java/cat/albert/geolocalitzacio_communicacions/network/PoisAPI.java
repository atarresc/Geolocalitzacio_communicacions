package cat.albert.geolocalitzacio_communicacions.network;

import cat.albert.geolocalitzacio_communicacions.models.business.entities.Poi;

import retrofit.http.GET;
import retrofit.http.Path;
/**
 * Created by albert on 15/03/2016.
 */
public interface PoisAPI {

    @GET("/pois")
    Poi.Llista getAll();

    @GET("/pois/{city}")
    Poi.Llista getByCity(@Path("/pois/city") String city);

//    @POST("/pois")
//    Response insert(@Path("name") String name, @Path("latitude") String latitude, @Path("longitude") String longitude, @Path("city") String city);
//
//    @PUT("/pois/{id}")
//    Response update(@Path("id") int id, @Path("name") String name, @Path("latitude") String latitude, @Path("longitude") String longitude, @Path("city") String city);
//
//    @DELETE("/pois/{id}")
//    Response delete(@Path("id") int id);
}
