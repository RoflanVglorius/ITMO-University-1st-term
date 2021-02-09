package expression;

public class Negate extends UnaryOperation {
    public Negate(PrioritizedElement element) {
        super(element);
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    protected int calculate(int value) {
        return -value;
    }

    @Override
    protected double calculate(double value) {
        return -value;
    }
}
