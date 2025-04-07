package org.example;

import java.util.*;

/**
 * Калькулятор для вычисления математических выражений с поддержкой переменных и функций.
 * Поддерживает основные арифметические операции, тригонометрические функции и возведение в степень.
 */
public class ExpressionCalculator {
    private boolean hasOperator = false;
    private final Map<String, Double> variables;

    /**
     * Возвращает карту переменных и их значений.
     * @return Карта, содержащая имена переменных и их значения.
     */
    public Map<String, Double> getVariables() {
        return variables;
    }

    /**
     * Создает новый калькулятор с пустым набором переменных.
     */
    public ExpressionCalculator() {
        this.variables = new HashMap<>();
    }

    /**
     * Добавляет переменную с указанным именем и значением.
     * @param variableName Имя переменной
     * @param value Значение переменной
     */
    public void addVariable(String variableName, double value) {
        variables.put(variableName, value);
    }

    /**
     * Вычисляет математическое выражение после подстановки значений переменных.
     * @param expression Математическое выражение для вычисления
     * @return Результат вычисления выражения
     * @throws RuntimeException Если выражение содержит синтаксические ошибки или неизвестные переменные/функции
     */
    public double evaluateExpression(String expression) {
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expression = expression.replaceAll(entry.getKey(), Double.toString(entry.getValue()));
        }
        return evaluate(expression);
    }

    /**
     * Возвращает пример поддерживаемого математического выражения.
     * @return Строка с примером выражения с поддерживаемыми функциями
     */
    public String giveExample() {
        return "cos(x)+tan(y)+sin(x)-asin(x)-x^3 -ctan(y)+ y*x / sqrt(y)";
    }

    /**
     * Вычисляет математическое выражение без переменных.
     * @param expression Математическое выражение для вычисления
     * @return Результат вычисления
     * @throws RuntimeException Если выражение содержит синтаксические ошибки
     */
    private double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;

            /**
             * Переходит к следующему символу в выражении.
             */
            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            /**
             * Пытается обработать указанный символ, пропуская пробелы.
             * @param charToEat Символ для обработки
             * @return true если символ найден и обработан, false в противном случае
             */
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            /**
             * Разбирает все выражение.
             * @return Результат разобранного выражения
             */
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char)ch);
                return x;
            }

            /**
             * Разбирает операции сложения и вычитания.
             * @return Результат разобранного терма
             */
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
                        }
                        hasOperator = true;
                        x += parseTerm();
                    } else if (eat('-')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
                        }
                        hasOperator = true;
                        x -= parseTerm();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }

            /**
             * Разбирает операции умножения и деления.
             * @return Результат разобранного фактора
             */
            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
                        }
                        hasOperator = true;
                        x *= parseFactor();
                    } else if (eat('/')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
                        }
                        hasOperator = true;
                        x /= parseFactor();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }

            /**
             * Разбирает факторы, включая числа, скобки, функции и возведение в степень.
             * @return Значение разобранного фактора
             */
            double parseFactor() {
                if (eat('+')){
                    if (hasOperator) {
                        throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
                    }
                    hasOperator = true;
                    return parseFactor();
                }
                if (eat('-')){
                    if (hasOperator) {
                        throw new RuntimeException("Неожиданный оператор после другого оператора в позиции " + pos);
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
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка в позиции " + pos);
                    else hasOperator = prevhasOperator;
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (!eat('(')) throw new RuntimeException("Ожидалась открывающая скобка после функции в позиции " + startPos);
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка после аргумента функции в позиции " + pos);
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
                            throw new RuntimeException("Неизвестная функция: " + func);
                    }
                } else {
                    throw new RuntimeException("Неожиданный символ: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                hasOperator = false;
                return x;
            }
        }.parse();
    }
}