package expression;

public class Not extends UnaryOperation {

    public Not(PrioritizedElement element) {
        super(element);
    }

    @Override
    public Priority getPriority() {
        return Priority.unary;
    }

    @Override
    protected int calculate(int value) {
        return ~value;
    }

    @Override
    protected double calculate(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getOperation() {
        return "~";
    }
}
