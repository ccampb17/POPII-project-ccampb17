package fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FractionImpl implements Fraction {


    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     * Normalize the fraction as you create it.
     * For instance, if the parameters are <pre>(8, -12)</pre>, create a <pre>Fraction</pre> with numerator
     * <pre>-2</pre> and denominator <pre>3</pre>.
     *
     * The constructor should throw an <pre>ArithmeticException</pre> if the denominator is zero.
     *
     * @param numerator
     * @param denominator
     */
    //nmr for the numerator, dmr for the denominator.
    //private because we don't want any naughty people fiddling with our values
    //therefore need getters. don't need setters because we don't need to mutate the fraction. any changes will
    //produce new fraction objects.

    //initialise vars
    private int nmr, dmr;

    //getters
    public int getNmr() {
        return nmr;
    }

    public int getDmr() {
        return dmr;
    }


    /**
     * Basic constructor that takes two integers and creates a fraction.
     * Normalises the fraction (ie expresses it in its lowest terms) using the GCD helper method.
     * @see #gcd(int, int)
     *
     * @param numerator integer that will be the numerator
     * @param denominator   integer that will be the denominator
     */

    public FractionImpl(int numerator, int denominator) {




        //init the nmr and dmr to start with
        nmr = numerator;
        dmr = denominator;

        //convert it to its lowest terms using helper method
        nmr = nmr/gcd(numerator, denominator);
        dmr = dmr/gcd(numerator, denominator);

        //throw exc if dmr is 0
        if (denominator == 0) throw new ArithmeticException("Denominator cannot be zero because maths.");




        //make sure positive nmr over negative dmr normalises to the other way around
        if (nmr > 0 & dmr < 0){
            nmr *= -1;
            dmr *= -1;
        }

    }

    // helper method to find the greatest common divisor of two ints.
    // see below for my fun tangent to find the lowest common divisor...

    /**
     * Finds the greatest common divisor of two integers.
     * @param a first input integer
     * @param b second input integer
     * @return  the greatest common divisor of the two inputs.
     */

    public static int gcd(int a, int b){

        // i made this based on doing a few GCD calculations on paper.
        // before i had a clunkier method that used lots of variables and if statements
        // i think this is more elegant though.

        //have to use absolute values otherwise we get stuck in a naughty endless loop.

        a = Math.abs(a);
        b = Math.abs(b);

        while (a != 0 & b!= 0){
            if (a > b){
                a %= b;
            }
            // previously this was 'else if b > a' but this led to an endless loop if a and b were the same
            else {
                b %= a;
            }

        }

        if (a == 0) return b;
        else return a;

    }

    //!
    //NB i did not see the bit about euclid's theorem and decided to make something that finds the
    // LOWEST common denominator instead of greatest for some reason. I've left it here in case it is interesting and/or
    // useful to me later on. It was definitely good practice coming up with it!

    //helper method to generate primes up to the input value so we can
    //perform operations that require lowest common denominator later on.
    //has to be a list as we don't know the size before starting

    /**
     * Generates a list of primes up to the given number.
     * Does not work well for very large numbers.
     * @param n Number up to which you want primes listed
     * @return  The list of primes up to that number.
     */
    public static List<Integer> listPrimesTo(int n){
        List<Integer> result = new ArrayList<Integer>();
        result = IntStream.rangeClosed(2, n)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList());
        return result;

    }

    //helper method for the above to check if a number is prime

    /**
     * A helper method to determine if a given number is prime.
     * Does not work very well for very large numbers.
     * @param n Number to be tested
     * @return  true if prime, false if not
     */
    private static boolean isPrime(int n){
        for (int i = 2; i*i < n; i++){
            if (n % i == 0) return false;
        }
        return true;
    }

    //helper method to find the LCD of two ints
    // again this is actually redundant so have left it here for curiosity purposes

    /**
     * Finds the lowest common denominator of two integers and returns it.
     * @param a first input integer
     * @param b second input integer
     * @return  the resulting lowest common denominator
     */
    public static int lcd(int a, int b){

        //get largest because we need to iterate up to this
        int largest;
        if (a>b) largest = a;
        else largest = b;

        int lcd = 2;

        for (int i = 2; i<listPrimesTo(largest).size(); i++){
            if ( (a % listPrimesTo(largest).get(i) == 0) & (b % listPrimesTo(largest).get(i) == 0)){
                lcd = listPrimesTo(largest).get(i);
                break;
            }
        }
        return lcd;
    }


    /**
     * The parameter is the numerator and <pre>1</pre> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {

        nmr = wholeNumber;
        dmr = 1;

    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, such as `5` or `-3`, or a fraction,
     * such as "8/-12".
     * Allow blanks around (but not within) integers.
     * The constructor should throw an <pre>ArithmeticException</pre>
     * if given a string representing a fraction whose denominator is zero.
     * <p>
     * You may find it helpful to look at the available String API methods in the Java API.
     *
     * <p>
     *     This is a very important method as it will take in the toString output and allow operations to be performed
     *     using this alone.
     *
     *     I tried to keep it short and sweet, using regular expression-based methods instead of iterating over the
     *     input string and looking for particular characters.
     *
     *     This method is also the point where most types of bad inputs will be caught. This saves having to worry about
     *     putting try-catch clauses everywhere else.
     * </p>
     *
     * @param fraction the string representation of the fraction
     */


    public FractionImpl(String fraction) {

        //get rid of whitespace in the input
        // NB I really love regular expressions

        fraction = fraction.replaceAll("\\s+", "");

        //does the input contain a forward slash? then it can be converted to a nmr and dmr fraction
        //put a try-catch clause to check for bad input

        if (fraction.contains("/")) {
            String[] input;
            input = fraction.split("/");

            try {
                this.nmr = Integer.parseInt(input[0]);
                this.dmr = Integer.parseInt(input[1]);
            }

            catch (NumberFormatException nfe) {
                throw new NumberFormatException("Your input must be composed of one integer, or two separated by a '/'.");
            }
        }

        //now if the input is just an integer, convert it to that/1
        //also include try-catch to get bad input - if this input is not accepted it must be a silly input
            else{
                try {
                    this.nmr = Integer.parseInt(fraction);
                    this.dmr = 1;

                } catch (NumberFormatException nfe) {
                    throw new NumberFormatException("Your input must be composed of one integer, or two separated by a '/'.");

                }
            }

            //if statement to catch if the dmr entered is negative
        if (this.dmr<0){
            nmr *= -1;
            dmr *= -1;
        }

        //throw exc if dmr is 0
        if (dmr == 0) throw new ArithmeticException("Denominator cannot be zero because maths.");

        //convert it to its lowest terms using helper method
        int gcd = gcd(nmr, dmr);
        nmr = nmr/gcd;
        dmr = dmr/gcd;

    }

    /**
     * @inheritDoc
     * Adds two fractions together, returning the result as a new object.
     *
     * @param f the fraction to be added
     * @return the result of the addification as a new object.
     */
    @Override
    public Fraction add(Fraction f) {


        //to calculate x/y + w/z
        // you do
        //(x*z)+(w*y)/(y*z)

        return new FractionImpl(((this.nmr*((FractionImpl)f).dmr) + ((FractionImpl)f).nmr*this.dmr), (this.dmr*((FractionImpl)f).dmr));


    }

    /**
     * @inheritDoc
     * Subtracts one fraction from another.
     * @param f the fraction to be subtracted.
     * @result the result of the subtraction as a new object.
     */
    @Override
    public Fraction subtract(Fraction f) {

        //same as adding but with a minus sign
        return new FractionImpl(((this.nmr*((FractionImpl)f).dmr) - (((FractionImpl)f).nmr*this.dmr)), (this.dmr*((FractionImpl)f).dmr));
    }

    /**
     * @inheritDoc
     * Facilitates the multiplication of two fractions, returning the result as a new object.
     *
     * @param f the fraction to multiply by
     * @return the result as a new object
     */
    @Override
    public Fraction multiply(Fraction f) {
        //to work out x/y * w/z
        //you do
        //(x*w)/(y*z)

        return new FractionImpl((this.nmr*((FractionImpl)f).nmr), (this.dmr*((FractionImpl)f).dmr));
    }

    /**
     * @inheritDoc
     * Facilitates division of one fraction by another, returning the result as a new object.
     *
     * @param f the fraction to be divided by
     * @return the result of the division
     */
    @Override
    public Fraction divide(Fraction f) {

        //to calculate x/y ÷ w/z
        //you flip the second one and multiply, i.e.:
        // (x*z) / (y*w)
        return new FractionImpl((this.nmr*((FractionImpl)f).dmr), (this.dmr*((FractionImpl)f).nmr));
    }

    /**
     * @inheritDoc
     *
     * Returns the absolute value (distance from origin) of input fraction object.
     *
     *
     * @returns absolute value of input fraction.
     */
    @Override
    public Fraction abs() {
        if (this.nmr < 0){
            return new FractionImpl((this.nmr*-1),this.dmr);
        }
        else{
            return new FractionImpl(this.nmr, this.dmr);
        }

    }

    /**
     * @inheritDoc
     *
     * Makes a positive fraction negative and vice versa.
     *
     * @return new fraction object that has the opposite sign to the input.
     */
    @Override
    public FractionImpl negate() {
        //also allows negative numbers to be positivised
        return new FractionImpl((this.nmr*-1), this.dmr);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @inheritDoc
     * Checks if two fractions are of equal value.
     * Fractions are automatically stored in their lowest terms by this class so no need to worry about checking for that.
     * Checks the object being tested is a fraction first to allow inherited equals() to work too.
     *
     * @param obj other fraction object to be compared to for equality
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fraction) {
            return (nmr == ((FractionImpl)obj).nmr && dmr == ((FractionImpl)obj).dmr);
        }
        else {
        return super.equals(obj);
        }
        }

    /**
     * @inheritDoc
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @inheritDoc
     *
     * Flips the fraction over, ie sets the numerator as denominator and vice versa.
     *
     * @return a new fraction object that is the inversion of the input one.
     */
    @Override
    public FractionImpl inverse() {
        //set the numerator as the denominator and vice versa
        return new FractionImpl(this.dmr, this.nmr);
    }

    /**
     * @inheritDoc
     * This method shows the difference between the two numbers to the nearest integer (as per specification).
     * Positive output means the object upon which the method is called is larger.
     * Negative output means the object used as a parameter is larger.
     * Zero means that they are the same.
     *
     * @param o another fraction object with which to compare.
     * @return an int showing the relationship between the object on which this is invoked and the param, see description
     * for more info.
     *
     */
    @Override
    public int compareTo(Fraction o) {

        // Convert them to floats to compare their values, unless they are the same
        // because can't do float == float
        float thisdec = (float)nmr/(float)dmr;
        float thatdec = (float)((FractionImpl)o).nmr/(float)((FractionImpl)o).dmr;


        //the if statements below mean it will return the difference between the two
        // to the nearest nonzero integer

        int difference = Math.round(Math.abs(thisdec - thatdec));

        if (thisdec > thatdec) {
            if (difference == 0) return 1;
            else return difference;
        }

        else if (thatdec > thisdec) {
            if (difference == 0) return -1;
            else return (-1*difference);
        }

        else { //if (nmr == ((FractionImpl)o).nmr & dmr == ((FractionImpl)o).dmr) {
            return 0;
        }

    }

    /**
     * @inheritDoc
     * Returns the stored fraction as a string in the format numerator / denominator.
     * Whole numbers or zero will be returned without denominator, although this is still stored in the object.
     * Output is parsed by the string constructor
     *
     * @see #FractionImpl(String)
     *
     * @return a string version of the fraction.
     */
    @Override
    public String toString() {

        if (dmr == 1 | dmr == 0){
            return Integer.toString(nmr);
        }
        else{

            return String.join("/", Integer.toString(nmr), Integer.toString(dmr));
        }

    }


}