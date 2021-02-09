package expression.exceptions;

public class DivisionByZeroException extends EvaluationException{
    public DivisionByZeroException(String message){
        super(message);
    }
}
