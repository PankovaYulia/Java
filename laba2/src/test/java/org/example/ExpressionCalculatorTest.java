package org.example;


import org.example.ExpressionCalculator;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class ExpressionCalculatorTest {
    @Test
    public void testAddVariable() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.addVariable("x", 5.0);
        calculator.addVariable("y", 10.0);

        Map<String, Double> variables = calculator.getVariables();

        assertEquals(5.0, variables.get("x").doubleValue(),0.000001);
        assertEquals(10.0, variables.get("y").doubleValue(),0.000001);
    }

    @Test
    public void testEvaluateExpressionWithVariables() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.addVariable("x", 2.0);
        calculator.addVariable("y", 3.0);

        double result1 = calculator.evaluateExpression("x + y");
        assertEquals(5.0, result1, 0.000001);
    }
    @Test
    public void testEvaluateExpressionOrderActionsWithVariables() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.addVariable("x", 2.0);
        calculator.addVariable("y", 3.0);

        double result1 = calculator.evaluateExpression("(x + y)*x");
        assertEquals(10.0, result1, 0.000001);
        double result2 = calculator.evaluateExpression("x + y * x");
        assertEquals(8.0, result2, 0.000001);
    }
    @Test
    public void testEvaluateExpressionOrderActionsWithNoVariables() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result1 = calculator.evaluateExpression("(2 + 3)*2");
        assertEquals(10.0, result1, 0.000001);
        double result2 = calculator.evaluateExpression("2 + 3 * 2");
        assertEquals(8.0, result2, 0.000001);
    }
    @Test
    public void testEvaluateExpressionOrderActionsWithNoVariablesWhithBracket() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result1 = calculator.evaluateExpression("2 - (-2)");
        assertEquals(4.0, result1, 0.000001);
    }
    @Test
    public void testEvaluateExpressionGradeWithVariables() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.addVariable("x", 2.0);

        double result1 = calculator.evaluateExpression("x^3");
        assertEquals(8.0, result1, 0.000001);
    }
    @Test
    public void testEvaluateExpressionGrade() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result1 = calculator.evaluateExpression("2^3");
        assertEquals(8.0, result1, 0.000001);
    }
    @Test
    public void testEvaluateExpressionWithFunctionsUseVariables() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.addVariable("x", 2.0);
        calculator.addVariable("y", 3.0);
        double result2 = calculator.evaluateExpression("sin(x) + cos(y)");
        // sin(2) + cos(3) ≈ 0.0348995 + 0.05233596 ≈ 1.03352903
        assertEquals(1.03352903, result2, 0.00000001);
    }

    @Test
    public void testEvaluateExpressionSimpleAddition() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("2 + 3");
        assertEquals(5.0, result, 0.000001);
    }

    @Test
    public void testEvaluateExpressionSimpleSubtraction() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("5 - 3");
        assertEquals(2.0, result, 0.000001);
    }

    @Test
    public void testEvaluateExpressionSimpleMultiplication() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("2 * 3");
        assertEquals(6.0, result, 0.000001);
    }

    @Test
    public void testEvaluateExpressionSimpleDivision() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("6 / 2");
        assertEquals(3.0, result, 0.000001);
    }


    @Test
    public void testEvaluateExpressionInvalidExpression() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("invalid expression"));
    }

    @Test
    public void testEvaluateExpressionInvalidExpressionMinusPlus() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2--2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2++2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2+-2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2-+2"));
    }
    @Test
    public void testEvaluateExpressionInvalidExpressionMultiDiv() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2//2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2**2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2*/2"));
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("2/*2"));
    }

    @Test
    public void testEvaluateExpressionUnknownFunction() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression("unknownFunction(10)"));
    }

    @Test
    public void testEvaluateExpressionMismatchedParentheses() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression(" sqrt(9 "));
    }

    @Test
    public void testEvaluateExpressionEmptyInput() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        assertThrows(RuntimeException.class, () -> calculator.evaluateExpression(""));
    }
    @Test
    public void testEvaluateExpressionSin() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("sin(2)");
        assertEquals(0.03489949, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionCos() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("cos(2)");
        assertEquals(0.99939082, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionTan() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("tan(2)");
        assertEquals(0.03492076, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionCTan() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("ctan(2)");
        assertEquals(28.63625328, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionATan() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("atan(2)");
        assertEquals(0.03489241, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionASin() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("asin(2)");
        assertEquals(0.03491367, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionACos() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("acos(2)");
        assertEquals(1.53588264, result, 0.000001);
    }
    @Test
    public void testEvaluateExpressionSqrt() {
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.evaluateExpression("sqrt(2)");
        assertEquals(1.41421356, result, 0.000001);
    }

}