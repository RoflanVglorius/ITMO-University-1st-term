package expression.parser;

import expression.*;

import java.util.HashMap;
import java.util.Map;

import static expression.Priority.*;


public class ExpressionParser implements Parser {
    private boolean isNegate;
    private char currentChar;
    private String parsedString;
    private StringSource expressionSource;
    private boolean isUnary;
    private final Map<String, Priority> priorityMap = new HashMap<>(Map.of("+", add, "-", add,
            "*", multiply, "/", multiply, "&", and, "^", xor, "|", or));
    private PrioritizedElement currentOperation;


    private void nextChar() {
        currentChar = expressionSource.hasNext() ? expressionSource.next() : '\0';
    }

    @Override
    public PrioritizedElement parse(String expression) {
        expressionSource = new StringSource(expression);
        return parse(expressionSource);
    }

    public PrioritizedElement parse(StringSource source) {
        nextChar();
        skipWhitespace();
        PrioritizedElement result = parseExpression();
        if (!source.hasNext()) {
            return result;
        }
        throw new IllegalArgumentException();
    }

    public PrioritizedElement parseExpression() {
        isUnary = true;
        skipWhitespace();
        PrioritizedElement result = parseElement();
        skipWhitespace();
        return result;
    }

    private PrioritizedElement parseElement() {
        do {
            currentOperation = parseArgument();
        }
        while (!isEndOfExpression());
        return currentOperation;
    }

    private PrioritizedElement parseArgument() {
        skipWhitespace();
        parsedString = "";
        if (Character.isLetter(currentChar)) {
            parseString();
            if (isOperationString()) {
                return isUnary ? parseUnary() : parseBinary();
            }
            PrioritizedElement result = new Variable(parsedString);
            isUnary = false;
            return result;
        } else if (Character.isDigit(currentChar)) {
            StringBuilder sb = new StringBuilder(isNegate ? "-" : "");
            isNegate = false;
            while (Character.isDigit(currentChar)) {
                sb.append(currentChar);
                nextChar();
            }
            isUnary = false;
            return new Const(Integer.parseInt(sb.toString()));
        } else if (compare('(')) {
            PrioritizedElement result = parseExpression();
            nextChar();
            return result;
        } else if (isUnary) {
            return parseUnary();
        } else {
            return parseBinary();
        }
    }

    public PrioritizedElement parseUnary() {
        if (parsedString.equals("count")) {
            return new Count(parseArgument());
        } else if (compare('-')) {
            skipWhitespace();
            if (Character.isDigit(currentChar)) {
                isNegate = true;
                return parseArgument();
            }
            return new Negate(parseArgument());
        } else if (compare('~')) {
            return new Not(parseArgument());
        }
        throw new IllegalArgumentException();
    }

    public PrioritizedElement parseBinary() {
        String currentOperator = Character.toString(currentChar);
        isUnary = true;
        PrioritizedElement first = currentOperation;
        skipWhitespace();
        nextChar();
        PrioritizedElement second = parseArgument();
        skipWhitespace();
        int compared = comparePriority(currentOperator, Character.toString(currentChar));
        if (compared > 0) {
            return genElement(first, second, currentOperator);
        } else if (compared == 0) {
            currentOperation = genElement(first, second, currentOperator);
            return parseArgument();
        } else {
            currentOperation = second;
            while (expressionSource.hasNext() && comparePriority(currentOperator, Character.toString(currentChar)) < 0) {
                currentOperation = parseArgument();
                skipWhitespace();
            }
            return genElement(first, currentOperation, currentOperator);
        }
    }

    private PrioritizedElement genElement(PrioritizedElement first, PrioritizedElement second, String currentOperation) {
        switch (currentOperation) {
            case "-":
                return new Subtract(first, second);
            case "+":
                return new Add(first, second);
            case "*":
                return new Multiply(first, second);
            case "/":
                return new Divide(first, second);
            case "&":
                return new BinaryAnd(first, second);
            case "^":
                return new BinaryXor(first, second);
            case "|":
                return new BinaryOr(first, second);
        }
        throw new IllegalArgumentException();
    }

    private boolean isEndOfExpression() {
        return currentChar == ')' || !expressionSource.hasNext();
    }

    public boolean compare(char ch) {
        skipWhitespace();
        if (ch == currentChar) {
            nextChar();
            return true;
        }
        return false;
    }

    public int comparePriority(String current, String next) {
        skipWhitespace();
        if (priorityMap.containsKey(current) && priorityMap.containsKey(next)) {
            return priorityMap.get(current).compareTo(priorityMap.get(next));
        }
        return 1;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) {
            nextChar();
        }
    }

    protected void parseString() {
        StringBuilder buff = new StringBuilder();
        while (Character.isLetter(currentChar)) {
            buff.append(currentChar);
            nextChar();
        }
        parsedString = buff.toString();
    }

    protected boolean isOperationString() {
        return parsedString.equals("count") && isUnary;
    }

}