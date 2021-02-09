package expression;

public class Const implements PrioritizedElement {

    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Const.class && value.equals(((Const) obj).value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int evaluate(int value) {
        return this.value.intValue();
    }

    @Override
    public double evaluate(double value) {
        return this.value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public int hashCode() {
        return value.intValue() * 31;
    }
}
