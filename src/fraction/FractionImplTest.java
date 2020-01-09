package fraction;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fraction.FractionImpl.*;
import static org.junit.jupiter.api.Assertions.*;

class FractionImplTest {

    @Test
    void gcdTestPositiveInts() {

        assertEquals(5, gcd(15, 20));
        assertEquals(2, gcd(74,100));
        assertEquals(512, gcd(512, 1024));

    }

    @Test
    void gcdTestNegativeInts() {

        assertEquals(10, gcd(-10, 30));
        assertEquals(8, gcd(8, -24));
    }

    @Test
    void gcdTestZeroes() {

        assertEquals(20, gcd(0,20));
        assertEquals(37, gcd(37,0));
        assertEquals(0, gcd(0,0));

    }

    @Test
    void listPrimesToTest() {
        List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 7, 9, 11, 13, 17, 19, 23, 25,
                29, 31, 37, 41, 43, 47, 49, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)); //prime numbers up to 100
        assertEquals(expectedResult, listPrimesTo(100));

    }

    @Test
    void lcdTest() {
        assertEquals(5, lcd(75, 100));
    }

    @Test
    void addTestPositive() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 4);
        Fraction f3 = new FractionImpl(1);
        Fraction f4 = new FractionImpl(2, 3);
        Fraction f5 = new FractionImpl(7, 6);

        assertTrue(f1.equals(f2.add(f2)));
        assertTrue(f3.equals(f1.add(f2.add(f2))));
        assertTrue(f5.equals(f4.add(f1)));

    }

    @Test
    void addTestNegative() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(-1, 2);
        Fraction f2 = new FractionImpl(-1, 4);
        Fraction f3 = new FractionImpl(-1);
        Fraction f4 = new FractionImpl(-2, 3);
        Fraction f5 = new FractionImpl(-7, 6);

        assertTrue(f1.equals(f2.add(f2)));
        assertTrue(f3.equals(f1.add(f2.add(f2))));
        assertTrue(f5.equals(f4.add(f1)));

    }

    @Test
    void subtractTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 4);
        Fraction f3 = new FractionImpl(1);
        Fraction f4 = new FractionImpl(2, 3);
        Fraction f5 = new FractionImpl(7, 6);

        assertTrue(f2.equals(f1.subtract(f2)));
        assertTrue(f1.equals(f3.subtract(f1)));
        assertTrue(f1.equals(f5.subtract(f4)));
    }

    @Test
    void multiplyTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 4);
        Fraction f3 = new FractionImpl(140, 12);
        Fraction f4 = new FractionImpl(9, 90);
        Fraction f5 = new FractionImpl(7, 6);

        assertTrue(f2.equals(f1.multiply(f1)));
        assertTrue(f5.equals(f4.multiply(f3)));
    }

    @Test
    void divideTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 4);
        Fraction f3 = new FractionImpl(1);
        Fraction f4 = new FractionImpl(2, 3);
        Fraction f5 = new FractionImpl(4, 3);

        assertTrue(f1.equals(f2.divide(f1)));
        assertTrue(f3.equals(f1.divide(f2.divide(f1))));
        assertTrue(f5.equals(f4.divide(f1)));
    }

    @Test
    void absTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(-1, 2);
        Fraction f1a = new FractionImpl(1, 2);
        Fraction f3 = new FractionImpl(-1);
        Fraction f3a = new FractionImpl(1);
        Fraction f5 = new FractionImpl(-7, 6);
        Fraction f5a = new FractionImpl(7, 6);

        assertTrue(f1a.equals(f1.abs()));
        assertTrue(f3a.equals(f3.abs()));
        assertTrue(f5a.equals(f5.abs()));
    }

    @Test
    void negateTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(-1, 2);
        Fraction f1a = new FractionImpl(1, 2);
        Fraction f3 = new FractionImpl(-1);
        Fraction f3a = new FractionImpl(1);
        Fraction f5 = new FractionImpl(-7, 6);
        Fraction f5a = new FractionImpl(7, 6);

        assertTrue(f1.equals(f1a.negate()));
        assertTrue(f3a.equals(f3.negate()));
        assertTrue(f5.equals(f5a.negate()));
    }

    @Test
    void hashCode1() {

    }

    @Test
    void equalsAndConstructorsTest() {
        //Since the equals() is required for all the other tests, this test tests constructors. test
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl("1/2");
        Fraction f3 = new FractionImpl("     -40/     -80");
        Fraction f4 = new FractionImpl(3,1);
        Fraction f5 = new FractionImpl("3");
        Fraction f6 = new FractionImpl(3);

        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f3));
        assertTrue(f3.equals(f1));
        assertTrue(f4.equals(f5));
        assertTrue(f5.equals(f6));
        assertTrue(f6.equals(f4));
        assertFalse(f6.equals(f1));

    }

    @Test
    void clone1() {
    }

    @Test
    void inverseTest() {
        //This test also requires the overridden equals() method to be correctly implemented!
        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(2);
        Fraction f3 = new FractionImpl("     -40/     -80");
        Fraction f4 = new FractionImpl(120,60);
        Fraction f5 = new FractionImpl("3");
        Fraction f6 = new FractionImpl(1,3);

        assertTrue(f1.equals(f2.inverse()));

        assertTrue(f3.equals(f4.inverse()));

        assertTrue(f5.equals(f6.inverse()));

    }

    @Test
    void compareToTest() {

        //assign variables to use for testing
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 4);
        Fraction f3 = new FractionImpl(1);
        Fraction f4 = new FractionImpl(2, 3);
        Fraction f5 = new FractionImpl(7, 6);
        Fraction f6 = new FractionImpl("20/30");

        assertTrue(f1.compareTo(f2) > 0);
        assertTrue(f3.compareTo(f5) < 0);
        assertTrue(f4.compareTo(f6) == 0);

    }

    @Test
    void toString1Test() {
        //tbh this toString is the axial method of the whole thing so if it doesn't work then you're effed
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl("20/40");
        Fraction f3 = new FractionImpl(1);
        Fraction f4 = new FractionImpl(-3,8);
        Fraction f5 = new FractionImpl("3/     -8");


        assertTrue(f1.toString().contentEquals(f2.toString()));
        assertFalse(f3.toString().contentEquals(f4.toString()));
        assertTrue(f4.toString().contentEquals(f5.toString()));
    }

    @Test
    void zeroDenominatorTest() {
        assertThrows(ArithmeticException.class, () -> {
            Fraction f1 = new FractionImpl(361, 0);

        });
    }

    @Test
    void badStringInputTest() {
        assertThrows(NumberFormatException.class, () -> {
            Fraction f1 = new FractionImpl("poo / 2");

        });
    }
}