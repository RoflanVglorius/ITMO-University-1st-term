package expression.exceptions;

import expression.PrioritizedElement;
import expression.Priority;

public class CheckedAdd extends CheckedBinaryOperation {
    public CheckedAdd(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, true);
    }

    @Override
    protected int calculate(int first, int second) {
        checkOverflow(first, second);
        return first + second;
    }

    private void checkOverflow(int first, int second) {
        if (second > 0 && Integer.MAX_VALUE - second < first || second < 0 && Integer.MIN_VALUE - second > first) {
            throw new OverflowException("Overflow caused by addition " + first + "to " + second);
        }
    }

    @Override
    protected String getOperation() {
        return "+";
    }

    @Override
    public Priority getPriority() {
        return Priority.add;
    }
}
