package org.example;

import java.util.*;

public class ExpressionCalculator {
    private final Map<String, Double> variables;

    public ExpressionCalculator() {
        this.variables = new HashMap<>();
    }

    public double evaluateExpression(String expression) {
        // Подставляем значения переменных перед вычислением
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expression = expression.replaceAll(entry.getKey(), entry.getValue().toString());
        }
        return evaluate(expression);
    }

    public void addVariable(String variableName, double value) {
        variables.put(variableName, value);
    }

    private double evaluate(String expression) {
        return new Object() {
            int pos = -1;
            int ch;

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
                if (pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;

                if (eat('(')) {
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (!eat('(')) throw new RuntimeException("Ожидалась открывающая скобка после функции");
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка после аргумента функции");

                    // Обработка математических функций
                    switch (func) {
                        case "sqrt": x = Math.sqrt(x); break;
                        case "sin": x = Math.sin(Math.toRadians(x)); break;
                        case "cos": x = Math.cos(Math.toRadians(x)); break;
                        case "tan": x = Math.tan(Math.toRadians(x)); break;
                        default: throw new RuntimeException("Неизвестная функция: " + func);
                    }
                } else {
                    throw new RuntimeException("Неожиданный символ: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
}