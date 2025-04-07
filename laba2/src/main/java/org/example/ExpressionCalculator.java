package org.example;

import java.util.*;

public class ExpressionCalculator {
    boolean hasOperator=false;
    private final Map<String, Double> variables;
    public Map<String, Double> getVariables() {
        return variables;
    }
    public ExpressionCalculator() {
        this.variables = new HashMap<>();
    }
    public void addVariable(String variableName, double value) {
        variables.put(variableName, value);
    }
    public double evaluateExpression(String expression) {
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expression = expression.replaceAll(entry.getKey(), Double.toString(entry.getValue()));
        }

        return evaluate(expression);
    }
    public String giveExample(){
        return "cos(x)+tan(y)+sin(x)-asin(x)-x^3 -ctan(y)+ y*x / sqrt(y)";
    }
    private double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }


            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        if (hasOperator) {
                            throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                        }
                        hasOperator = true;
                        x += parseTerm();
                    } else if (eat('-')) {
                        if (hasOperator) {
                            throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                        }
                        hasOperator = true;
                        x -= parseTerm();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) {
                        if (hasOperator) {
                            throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                        }
                        hasOperator = true;
                        x *= parseFactor();
                    } else if (eat('/')) {
                        if (hasOperator) {
                            throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                        }
                        hasOperator = true;
                        x /= parseFactor();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')){
                    if (hasOperator) {
                        throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                    }
                    hasOperator = true;
                    return parseFactor();
                }
                if (eat('-')){
                    if (hasOperator) {
                        throw new RuntimeException("Unexpected operator after another operator at position " + pos);
                    }
                    hasOperator = true;
                    return -parseFactor();
                }

                double x;
                boolean prevhasOperator = hasOperator;
                int startPos = this.pos;
                if (eat('(')) {
                    hasOperator = false;
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Expected closing parenthesis at position " + pos);
                    else hasOperator = prevhasOperator;
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (!eat('(')) throw new RuntimeException("Expected opening parenthesis after function at position " + startPos);
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Expected closing parenthesis after function argument at position " + pos);
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "ctan":
                            x = 1.0 / Math.tan(Math.toRadians(x));
                            break;
                        case "asin":
                            x = Math.asin(Math.toRadians(x));
                            break;
                        case "acos":
                            x = Math.acos(Math.toRadians(x));
                            break;
                        case "atan":
                            x = Math.atan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                hasOperator = false;
                return x;

            }

        }.parse();
    }
}