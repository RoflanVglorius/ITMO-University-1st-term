package expression;

public class Add extends BinaryOperation {

    public Add(PrioritizedElement first, PrioritizedElement second) {
        super(first, second, true);
    }

    @Override
    protected String getOperation() {
        return "+";
    }

    @Override
    public Priority getPriority() {
        return Priority.add;
    }

    @Override
    protected int calculate(int first, int second) {
        return first + second;
    }

    @Override
    protected double calculate(double first, double second) {
        return first + second;
    }


}
