package expression;

public class Count extends UnaryOperation {
    public Count(PrioritizedElement element) {
        super(element);
    }

    @Override
    public Priority getPriority() {
        return Priority.unary;
    }

    @Override
    protected int calculate(int value) {
        return Integer.bitCount(value);
    }

    @Override
    protected double calculate(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getOperation() {
        return "count ";
    }
}
