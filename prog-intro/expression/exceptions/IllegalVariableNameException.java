package expression.exceptions;

public class IllegalVariableNameException extends EvaluationException{
    public IllegalVariableNameException(String message){
        super(message);
    }
}
