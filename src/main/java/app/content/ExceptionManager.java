package app.content;

public class ExceptionManager extends Exception{
    int code;
    public ExceptionManager(int code,String message) {
        super(message);
        this.code = code;
    }
}
