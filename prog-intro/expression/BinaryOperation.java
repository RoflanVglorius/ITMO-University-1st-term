package expression;

public abstract class BinaryOperation implements PrioritizedElement {
    protected final PrioritizedElement first;
    protected final PrioritizedElement second;
    private final boolean isAssociative;

    protected BinaryOperation(PrioritizedElement first, PrioritizedElement second, boolean isAssociative) {
        this.first = first;
        this.second = second;
        this.isAssociative = isAssociative;
    }

    @Override
    public String toMiniString() {
        StringBuilder expression = new StringBuilder();
        addBrackets(expression, first, false);
        expression.append(" ").append(getOperation()).append(" ");
        addBrackets(expression, second, true);
        return expression.toString();
    }

    @Override
    public int evaluate(int value) {
        return calculate(first.evaluate(value), second.evaluate(value));
    }

    @Override
    public double evaluate(double value) {
        return calculate(first.evaluate(value), second.evaluate(value));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            BinaryOperation buff = (BinaryOperation) obj;
            return this.hashCode() == buff.hashCode() && buff.first.equals(first) && buff.second.equals(second);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperation() + " " + second + ")";
    }

    @Override
    public int hashCode() {
        return (first.hashCode() * 31 + second.hashCode()) * 31 + this.getClass().hashCode();
    }

    @Override
    public boolean isBracketsNeeded(boolean side) {
        return first.getPriority().compareTo(this.getPriority()) < 0 && !side ||
                side && (second.getPriority().compareTo(this.getPriority()) < 0 ||
                        second.getPriority().compareTo(this.getPriority()) == 0 &&
                                (!isAssociative || second.getClass() == Divide.class));
    }

    private void addBrackets(StringBuilder expression, PrioritizedElement element, boolean side) {
        if (isBracketsNeeded(side)) {
            expression.append("(").append(element.toMiniString()).append(")");
        } else {
            expression.append(element.toMiniString());
        }
    }

    protected abstract int calculate(int first, int second);

    protected abstract double calculate(double first, double second);

    protected abstract String getOperation();

}
