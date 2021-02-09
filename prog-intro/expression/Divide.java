package expression;

public class Divide extends BinaryOperation {

    public Divide(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, false);
    }

    @Override
    protected String getOperation() {
        return "/";
    }

    @Override
    public Priority getPriority() {
        return Priority.multiply;
    }

    @Override
    protected int calculate(int first, int second) {
        return first / second;
    }

    @Override
    protected double calculate(double first, double second) {
        return first / second;
    }


}
