package expression.exceptions;

public class NegativeArgumentException extends EvaluationException {
    public NegativeArgumentException(String message){
        super(message);
    }
}
