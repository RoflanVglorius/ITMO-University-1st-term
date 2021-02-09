package expression.exceptions;

import expression.BinaryOperation;
import expression.PrioritizedElement;

public abstract class CheckedBinaryOperation extends BinaryOperation {

    protected CheckedBinaryOperation(PrioritizedElement first, PrioritizedElement second, boolean isAssociative) {
        super(first, second, isAssociative);
    }

    @Override
    public double evaluate(double value) {
        return calculate(first.evaluate(value), second.evaluate(value));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected double calculate(double first, double second){
        throw new UnsupportedOperationException("Double operations are unsupported");
    }

}
