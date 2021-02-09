package expression.exceptions;

public class UnexpectedCharException extends ParseException {
    public UnexpectedCharException(String message){
        super(message);
    }
}
