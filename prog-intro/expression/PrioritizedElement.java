package expression;

public interface PrioritizedElement extends Expression, DoubleExpression, TripleExpression {
    default Priority getPriority() {
        return Priority.unary;
    }

    default boolean isBracketsNeeded(boolean side) {
        return false;
    }
}