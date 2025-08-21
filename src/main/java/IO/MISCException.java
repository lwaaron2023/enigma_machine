package IO;

public class MISCException extends RuntimeException{

    private String message;

    public MISCException(){
        super();
        message = "";
    }

    public MISCException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
