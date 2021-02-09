package expression.exceptions;

import expression.PrioritizedElement;
import expression.Priority;

public class CheckedSubtract extends CheckedBinaryOperation {

    public CheckedSubtract(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, false);
    }

    @Override
    protected int calculate(int first, int second) {
        checkOverflow(first, second);
        return first - second;
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    public Priority getPriority() {
        return Priority.add;
    }

    private void checkOverflow(int first, int second) {
        if (second < 0 && Integer.MAX_VALUE + second < first || second > 0 && Integer.MIN_VALUE + second > first) {
            throw new OverflowException("Overflow caused by subtraction " + first + "to " + second);
        }
    }
}
