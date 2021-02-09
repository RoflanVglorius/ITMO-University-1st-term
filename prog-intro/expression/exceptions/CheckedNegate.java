package expression.exceptions;

import expression.PrioritizedElement;

public class CheckedNegate extends CheckedUnaryOperation {
    public CheckedNegate(PrioritizedElement element) {
        super(element);
    }

    @Override
    protected int calculate(int value) {
        checkOverflow(value);
        return -value;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    protected double calculate(double value) {
        throw new UnsupportedOperationException("Double operations are unsupported");
    }

    private void checkOverflow(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow caused by negation of " + value);
        }
    }

}

