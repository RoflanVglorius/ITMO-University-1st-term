package expression;

public class BinaryOr extends BinaryOperation{
    public BinaryOr(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, true);
    }

    @Override
    public Priority getPriority() {
        return Priority.or;
    }

    @Override
    protected int calculate(int first, int second) {
        return first | second;
    }

    @Override
    protected double calculate(double first, double second) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getOperation() {
        return "|";
    }
}
