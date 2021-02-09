package expression.exceptions;

import expression.PrioritizedElement;

public class CheckedSqrt extends CheckedUnaryOperation {
    public CheckedSqrt(PrioritizedElement element) {
        super(element);
    }

    @Override
    protected int calculate(int value) {
        checkNegativeArgument(value);
        return (int) Math.sqrt(value);
    }

    @Override
    protected double calculate(double value) {
        throw new UnsupportedOperationException("Double operations are unsupported");
    }

    @Override
    protected String getOperation() {
        return "sqrt";
    }

    private void checkNegativeArgument(int argument) {
        if (argument < 0) {
            throw new NegativeArgumentException("Square root argument should be positive");
        }
    }
}
