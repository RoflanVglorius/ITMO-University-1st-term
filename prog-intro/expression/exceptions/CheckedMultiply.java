package expression.exceptions;


import expression.PrioritizedElement;
import expression.Priority;


public class CheckedMultiply extends CheckedBinaryOperation {

    public CheckedMultiply(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, true);
    }

    @Override
    protected int calculate(int first, int second) {
        checkOverflow(first, second);
        return first * second;
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    public Priority getPriority() {
        return Priority.multiply;
    }

    private void checkOverflow(int first, int second) {
        boolean sameSign = Integer.signum(first) == Integer.signum(second);
        int edge = sameSign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        if (sameSign && (second == Integer.MIN_VALUE || first == Integer.MIN_VALUE)
                || first != -1 && first != 0 && (second > 0 && second > edge / first
                || second < 0 && second < edge / first)) {
            throw new OverflowException("Overflow caused by multiplying " + first + " by " + second);
        }

    }

}