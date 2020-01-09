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
    void addTest() {
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
    void subtractTest() {
    }

    @Test
    void multiplyTest() {
    }

    @Test
    void divideTest() {
    }

    @Test
    void absTest() {
    }

    @Test
    void negateTest() {
    }

    @Test
    void hashCode1() {
    }

    @Test
    void equals1Test() {
    }

    @Test
    void clone1() {
    }

    @Test
    void inverseTest() {
    }

    @Test
    void compareToTest() {
    }

    @Test
    void toString1Test() {
    }
}