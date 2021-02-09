package expression.exceptions;

import expression.PrioritizedElement;

public class CheckedAbs extends CheckedUnaryOperation {
    public CheckedAbs(PrioritizedElement element) {
        super(element);
    }

    @Override
    protected int calculate(int value) {
        checkOverflow(value);
        return value >= 0 ? value : -value;
    }

    @Override
    protected String getOperation() {
        return "abs";
    }

    private void checkOverflow(int value){
        if (value == Integer.MIN_VALUE){
            throw new OverflowException("Absolute value overflow: " + value);
        }
    }
}
