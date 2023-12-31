package calculatordemo2;
import org.junit.jupiter.api.Test;

import calculatordemo2.Calculator.twoOperator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator classUnderTest;

    @BeforeEach
    public void setUp() {
        classUnderTest = new Calculator();
    }

    @DisplayName("Tests the square function")
    @Test
    void testSquare() {
        double num = 5.0;
        assertEquals(num*num, classUnderTest.calcScience(Calculator.singleOperator.square, num));
    }

    @DisplayName("Tests the square root function")
    @Test
    void testSquareRoot() {
        double num = 9.0;
        assertEquals(Math.sqrt(num), classUnderTest.calcScience(Calculator.singleOperator.squareRoot, num));
    }

    @DisplayName("Tests the 1/x function")
    @Test
    void testOneDividedBy() {
        double num = 2.0;
        assertEquals(1/num, classUnderTest.calcScience(Calculator.singleOperator.oneDevidedBy, num));
    }

    @DisplayName("Tests the cosine function in degrees")
    @Test
    void testCos() {
        double num = 45.0;
        assertEquals(Math.cos(num), classUnderTest.calcScience(Calculator.singleOperator.cos, num));
    }

    @DisplayName("Tests the sine function in degrees")
    @Test
    void testSin() {
        double num = 30.0;
        assertEquals(Math.sin(num), classUnderTest.calcScience(Calculator.singleOperator.sin, num));
    }

    @DisplayName("Tests the tangent function in degrees")
    @Test
    void testTan() {
        double num = 60.0;
        assertEquals(Math.tan(num), classUnderTest.calcScience(Calculator.singleOperator.tan, num));
    }

    @DisplayName("Tests the inverse cosine function in degrees")
    @Test
    void testAcos() {
        double num = 45.0;
        assertEquals(Math.acos(num), classUnderTest.calcScience(Calculator.singleOperator.acos, num));
    }

    @DisplayName("Tests the inverse sine function in degrees")
    @Test
    void testAsin() {
        double num = 30.0;
        assertEquals(Math.asin(num), classUnderTest.calcScience(Calculator.singleOperator.asin, num));
    }

    @DisplayName("Tests the inverse tangent function in degrees")
    @Test
    void testAtan() {
        double num = 60.0;
        assertEquals(Math.atan(num), classUnderTest.calcScience(Calculator.singleOperator.atan, num));
    }

    @DisplayName("Tests case 1 of twoOpCaller")
    @Test
    void testTwoOpCallerNormal() {
        double num = 1.0;
        assertEquals(Double.NaN, classUnderTest.twoOpCaller(twoOperator.normal, num));
    }

    @DisplayName("Tests case 2 of twoOpCaller")
    @Test
    void testTwoOpCallerOther() {
        double num = 2.0;
        classUnderTest.num1 = 2.0;
        classUnderTest.mode = twoOperator.multiply;
        assertNotEquals(Double.NaN, classUnderTest.twoOpCaller(twoOperator.multiply, num));
    }

    @DisplayName("Testing 4+5: assumes public") 
    @Test
    public void perform4Plus5() {
        classUnderTest.num1 = 4.0;
        classUnderTest.mode = twoOperator.add;
        classUnderTest.num2 = 5.0;
        assertEquals(classUnderTest.num1+classUnderTest.num2, classUnderTest.twoOpOperations());
    }

    @DisplayName("Testing 7-3: assumes public") 
    @Test
    public void perform7Minus3() {
        classUnderTest.num1 = 7.0;
        classUnderTest.mode = twoOperator.subtract;
        classUnderTest.num2 = 3.0;
        assertEquals(classUnderTest.num1-classUnderTest.num2, classUnderTest.twoOpOperations());
    }

    @DisplayName("Testing 9/3: assumes public") 
    @Test
    public void perform9Div3() {
        classUnderTest.num1 = 9.0;
        classUnderTest.mode = twoOperator.divide;
        classUnderTest.num2 = 3.0;
        assertEquals(classUnderTest.num1/classUnderTest.num2, classUnderTest.twoOpOperations());
    }

    @DisplayName("Testing 4*5: assumes public") 
    @Test
    public void perform4Times5() {
        classUnderTest.num1 = 4.0;
        classUnderTest.mode = twoOperator.multiply;
        classUnderTest.num2 = 5.0;
        assertEquals(classUnderTest.num1*classUnderTest.num2, classUnderTest.twoOpOperations());
    }

    // The following are tests for twoOpOperations.  They assume that 
    // the method modifier is public.
    @DisplayName("Testing 2+3*6: assumes public modifiers for mode, num1, num2 and twoOpOperations()")
    @Test
    public void perform_2_Plus_3_Times_6() {
        classUnderTest.mode = twoOperator.add;
        classUnderTest.num1 = 2.0;
        classUnderTest.num2 = 3.0;
        Double expectedResult = classUnderTest.num1 + classUnderTest.num2;
        Double actualResult = classUnderTest.twoOpOperations();
        // Test that 2+3 gets what we want.
        assertEquals(expectedResult,actualResult);
        // test that 2+3*6 gets what we want.
        classUnderTest.mode = twoOperator.multiply;
        classUnderTest.num1 = actualResult;
        classUnderTest.num2 = 6.0;
        expectedResult = classUnderTest.num1 * classUnderTest.num2;
        actualResult = classUnderTest.twoOpOperations();
        assertEquals(expectedResult,actualResult);

    }

    @DisplayName("Testing 3-1+6: assumes public")
    @Test
    public void perform3Minus1Plus6() {
        classUnderTest.mode = twoOperator.subtract;
        classUnderTest.num1 = 3.0;
        classUnderTest.num2 = 1.0;
        Double expectedResult = classUnderTest.num1 - classUnderTest.num2;
        Double actualResult = classUnderTest.twoOpOperations();
        
        assertEquals(expectedResult,actualResult);
        
        classUnderTest.mode = twoOperator.add;
        classUnderTest.num1 = actualResult;
        classUnderTest.num2 = 6.0;
        expectedResult = classUnderTest.num1 + classUnderTest.num2;
        actualResult = classUnderTest.twoOpOperations();
        assertEquals(expectedResult,actualResult);

    }

    @DisplayName("Testing 6/2*7: assumes public")
    @Test
    public void perform6Div2Times7() {
        classUnderTest.mode = twoOperator.divide;
        classUnderTest.num1 = 6.0;
        classUnderTest.num2 = 2.0;
        Double expectedResult = classUnderTest.num1 / classUnderTest.num2;
        Double actualResult = classUnderTest.twoOpOperations();
        
        assertEquals(expectedResult,actualResult);
        
        classUnderTest.mode = twoOperator.multiply;
        classUnderTest.num1 = actualResult;
        classUnderTest.num2 = 7.0;
        expectedResult = classUnderTest.num1 * classUnderTest.num2;
        actualResult = classUnderTest.twoOpOperations();
        assertEquals(expectedResult,actualResult);

    }

    @DisplayName("Testing 14-7-6: assumes public")
    @Test
    public void perform14Minus7Minus6() {
        classUnderTest.mode = twoOperator.subtract;
        classUnderTest.num1 = 14.0;
        classUnderTest.num2 = 7.0;
        Double expectedResult = classUnderTest.num1 - classUnderTest.num2;
        Double actualResult = classUnderTest.twoOpOperations();
        
        assertEquals(expectedResult,actualResult);
        
        classUnderTest.mode = twoOperator.subtract;
        classUnderTest.num1 = actualResult;
        classUnderTest.num2 = 6.0;
        expectedResult = classUnderTest.num1 - classUnderTest.num2;
        actualResult = classUnderTest.twoOpOperations();
        assertEquals(expectedResult,actualResult);

    }

    @DisplayName("Tests whether an error is thrown if a null mode is passed")
    @Test
    void testThrowError() {
        assertThrows(Error.class, () -> classUnderTest.calcScience(null, 10.0));
    }
}
