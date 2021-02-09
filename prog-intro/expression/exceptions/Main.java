package expression.exceptions;

import expression.TripleExpression;

public class Main {
    public static void main(String[] args) throws ParseException {
        TripleExpression exp1 = new ExpressionParser().parse("(x * y) + ((z - -1) / 10)");
        System.out.println(exp1.evaluate(0,0,0));
        TripleExpression expression = new ExpressionParser().parse("1000000*x*x*x*x*x/(x-1)");
        exp1.evaluate(0,0,0);
        System.out.println("x   f");
        for (int x = 0; x <= 10; x++) {
            try {
                System.out.println(x + " ".repeat(3) + expression.evaluate(x, 0, 0));
            } catch (EvaluationException e) {
                System.out.println(x + " ".repeat(3 - x / 10) + e.getMessage());
            }
        }
    }
}
