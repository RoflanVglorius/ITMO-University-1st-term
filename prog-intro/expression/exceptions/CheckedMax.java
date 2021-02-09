package expression.exceptions;

import expression.BinaryOperation;
import expression.PrioritizedElement;
import expression.Priority;

public class CheckedMax extends CheckedBinaryOperation {
    public CheckedMax(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, true);
    }

    @Override
    public Priority getPriority() {
        return Priority.min;
    }

    @Override
    protected int calculate(int first, int second) {
        return first < second ? second : first;
    }

    @Override
    protected String getOperation() {
        return " max ";
    }
}