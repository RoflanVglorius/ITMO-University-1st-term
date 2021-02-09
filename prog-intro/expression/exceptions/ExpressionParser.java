package expression.exceptions;

import expression.*;
import expression.parser.StringSource;

import java.util.HashMap;
import java.util.Map;

import static expression.Priority.*;


public class ExpressionParser implements Parser {
    private boolean isNegate;
    private char currentChar;
    private StringSource expressionSource;
    private boolean isUnary;
    private final Map<String, Priority> priorityMap = new HashMap<>(Map.of("+", add, "-", add,
            "*", multiply, "/", multiply, "&", and, "^",
            xor, "|", or, "min", min, "max", min));
    private PrioritizedElement currentOperation;
    private int balance;
    private String parsedString;


    private void nextChar() {
        currentChar = expressionSource.hasNext() ? expressionSource.next() : '\0';
    }

    @Override
    public PrioritizedElement parse(String expression) {
        expressionSource = new StringSource(expression);
        return parse(expressionSource);
    }

    private PrioritizedElement parse(StringSource source) {
        balance = 0;
        nextChar();
        skipWhitespace();
        PrioritizedElement result = parseExpression();
        if (!source.hasNext() && balance == 0 && isSupportedChar()) {
            return result;
        }
        if (!isSupportedChar())
            throw new UnsupportedCharException("Unsupported char: " + currentChar);
        if (balance < 0)
            throw new UnexpectedCharException("Opening parenthesis expected, found ''");
        if (balance > 0)
            throw new UnexpectedCharException("Closing parenthesis expected, found ''");
        throw new ParseException("End of file expected");
    }


    private PrioritizedElement parseExpression() {
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
            parsedString = parseString();
            if (isOperationString()) {
                return isUnary ? parseUnary() : parseBinary();
            }
            if (!isVariable(parsedString)) {
                throw new IllegalVariableNameException("Illegal variable name: " + parsedString);
            }
            PrioritizedElement result = new Variable(parsedString);
            isUnary = false;
            return result;
        } else if (Character.isDigit(currentChar)) {
            if (!isUnary)
                throw new ParseException("End of expression expected at pos " + expressionSource.getPos());
            StringBuilder sb = new StringBuilder(isNegate ? "-" : "");
            isNegate = false;
            while (Character.isDigit(currentChar)) {
                sb.append(currentChar);
                nextChar();
            }
            isUnary = false;
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new IllegalConstException("Illegal const format");
            }
        } else if (compare('(')) {
            balance++;
            PrioritizedElement result = parseExpression();
            nextChar();
            return result;
        } else if (!isSupportedChar()) {
            throw new UnsupportedCharException("Unsupported char: " + currentChar);
        } else if (isUnary) {
            return parseUnary();
        } else {
            return parseBinary();
        }
    }

    private PrioritizedElement parseUnary() {
        if (parsedString.equals("count")) {
            return new Count(parseArgument());
        } else if (parsedString.equals("abs")) {
            return new CheckedAbs(parseArgument());
        } else if (parsedString.equals("sqrt")) {
            return new CheckedSqrt(parseArgument());
        } else if (compare('-')) {
            skipWhitespace();
            if (Character.isDigit(currentChar)) {
                isNegate = true;
                return parseArgument();
            }
            return new CheckedNegate(parseArgument());
        } else if (compare('~')) {
            return new Not(parseArgument());
        }
        throw new LostArgumentException("Lost argument at pos " + +expressionSource.getPos());
    }


    private PrioritizedElement parseBinary() {
        String currentOperator = parsedString.equals("") ? Character.toString(currentChar) : parsedString;
        isUnary = true;
        PrioritizedElement first = currentOperation;
        skipWhitespace();
        if (!isOperationString())
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

    private PrioritizedElement genElement(PrioritizedElement first, PrioritizedElement second, String
            currentOperation) {
        switch (currentOperation) {
            case "-":
                return new CheckedSubtract(first, second);
            case "+":
                return new CheckedAdd(first, second);
            case "*":
                return new CheckedMultiply(first, second);
            case "/":
                return new CheckedDivide(first, second);
            case "&":
                return new BinaryAnd(first, second);
            case "^":
                return new BinaryXor(first, second);
            case "|":
                return new BinaryOr(first, second);
            case "min":
                return new CheckedMin(first, second);
            case "max":
                return new CheckedMax(first, second);
        }
        throw new LostArgumentException("Lost argument at pos " + expressionSource.getPos());
    }

    private boolean isEndOfExpression() {
        if (currentChar == ')')
            balance--;
        return currentChar == ')' || !expressionSource.hasNext();
    }

    private boolean compare(char ch) {
        skipWhitespace();
        if (ch == currentChar) {
            nextChar();
            return true;
        }
        return false;
    }

    private int comparePriority(String current, String next) {
        skipWhitespace();
        if (priorityMap.containsKey(current) && priorityMap.containsKey(next)) {
            return priorityMap.get(current).compareTo(priorityMap.get(next));
        }
        return 1;
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) {
            nextChar();
        }
    }

    private boolean isVariable(String s) {
        return s.equals("x") || s.equals("y") || s.equals("z");
    }

    protected String parseString() {
        StringBuilder buff = new StringBuilder();
        while (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
            buff.append(currentChar);
            nextChar();
        }
        return buff.toString();
    }

    private boolean isSupportedChar() {
        return Character.isLetterOrDigit(currentChar) || currentChar == ')' || currentChar == '\0' || isOperation();
    }

    private boolean isOperation() {
        return priorityMap.containsKey(Character.toString(currentChar));
    }

    protected boolean isOperationString() {
        return (parsedString.equals("count") || parsedString.equals("abs") || parsedString.equals("sqrt")) && isUnary
                || parsedString.equals("min") || parsedString.equals("max");
    }

}