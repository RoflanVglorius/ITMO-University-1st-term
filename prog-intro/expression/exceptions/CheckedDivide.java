package expression.exceptions;

import expression.Divide;
import expression.PrioritizedElement;
import expression.Priority;

public class CheckedDivide extends CheckedBinaryOperation {

    public CheckedDivide(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, false);
    }

    @Override
    protected String getOperation() {
        return "/";
    }

    @Override
    protected int calculate(int first, int second) {
        checkDivisionByZero(first, second);
        checkOverflow(first, second);
        return first / second;
    }

    @Override
    public Priority getPriority() {
        return Priority.multiply;
    }

    private void checkDivisionByZero(int numerator, int denominator) {
        if (denominator == 0) {
            throw new DivisionByZeroException("Division " + numerator + " by zero");
        }
    }

    protected void checkOverflow(int first, int second) {
        if (second == -1 && first == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow caused by division " + first + " by " + second);
        }
    }
}
