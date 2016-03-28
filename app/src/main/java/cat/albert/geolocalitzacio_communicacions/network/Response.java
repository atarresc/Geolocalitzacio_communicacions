package cat.albert.geolocalitzacio_communicacions.network;

/**
 * Created by albert on 15/03/2016.
 */
public class Response {

    private boolean status;
    private String message;

    public Response() {
    }

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
