package expression;

import expression.exceptions.IllegalVariableNameException;

public class Variable implements PrioritizedElement {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Variable.class && name.equals(((Variable) obj).name);
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    public double evaluate(double value) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalVariableNameException("Illegal variable name: " + name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
