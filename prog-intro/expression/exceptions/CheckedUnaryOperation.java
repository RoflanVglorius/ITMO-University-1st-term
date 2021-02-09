package expression.exceptions;

import expression.PrioritizedElement;
import expression.UnaryOperation;

public abstract class CheckedUnaryOperation extends UnaryOperation {

    public CheckedUnaryOperation(PrioritizedElement element) {
        super(element);
    }

    protected abstract int calculate(int value);

    protected double calculate(double value){
        throw new UnsupportedOperationException("Double operations are unsupported");
    }

}
