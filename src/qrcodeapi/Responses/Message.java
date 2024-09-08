package qrcodeapi.Responses;

public class ErrorMessage {
    public final String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError(){
        return error;
    }
}
