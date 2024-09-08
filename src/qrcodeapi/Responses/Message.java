package qrcodeapi.Responses;

public class Message {
    public final String error;

    public Message(String error) {
        this.error = error;
    }

    public String getError(){
        return error;
    }
}
