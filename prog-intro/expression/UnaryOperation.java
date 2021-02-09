package expression;

public abstract class UnaryOperation implements PrioritizedElement {
    protected PrioritizedElement element;

    public UnaryOperation(PrioritizedElement element) {
        this.element = element;
    }

    @Override
    public int evaluate(int value) {
        return calculate(element.evaluate(value));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(element.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double value) {
        return calculate(element.evaluate(value));
    }

    @Override
    public int hashCode() {
        return (element.hashCode() * 31 + this.getClass().hashCode()) * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            UnaryOperation buff = (UnaryOperation) obj;
            return this.hashCode() == buff.hashCode() && buff.element.equals(this.element);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder expression = new StringBuilder();
        expression.append(getOperation()).append(element.toString());
        return expression.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder expression = new StringBuilder();
        expression.append(getOperation()).append(" ");
        addBrackets(expression);
        return expression.toString();
    }

    private void addBrackets(StringBuilder expression) {
        if (isBracketsNeeded(false)) {
            expression.append("(").append(element.toMiniString()).append(")");
        }
    }

    @Override
    public boolean isBracketsNeeded(boolean side) {
        return this.getPriority().compareTo(element.getPriority()) > 0;
    }

    protected abstract int calculate(int value);

    protected abstract double calculate(double value);

    protected abstract String getOperation();
}
