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




    public FractionImpl(int numerator, int denominator) {




        //init the nmr and dmr to start with
        nmr = numerator;
        dmr = denominator;

        //TODO normalise the fraction to smallest poss values
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

    // helper method to find the greatest common divisor of two ints... see below for my fun tangent to find the
    // lowest common divisor...

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
    public static List<Integer> listPrimesTo(int n){
        List<Integer> result = new ArrayList<Integer>();
        result = IntStream.rangeClosed(2, n)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList());
        return result;

    }
    //helper method for the above to check if a number is prime
    private static boolean isPrime(int n){
        for (int i = 2; i*i < n; i++){
            if (n % i == 0) return false;
        }
        return true;
    }

    //helper method to find the LCD of two ints
    // again this is actually redundant so have left it here for curiosity purposes

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
        // TODO
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



    }

    /**
     * @inheritDoc
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
     */
    @Override
    public Fraction subtract(Fraction f) {

        //same as adding but with a minus sign
        return new FractionImpl(((this.nmr*((FractionImpl)f).dmr) - (((FractionImpl)f).nmr*this.dmr)), (this.dmr*((FractionImpl)f).dmr));
    }

    /**
     * @inheritDoc
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
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
     */
    @Override
    public int compareTo(Fraction o) {

        // Convert them to floats to compare their values, unless they are the same
        // because can't do float == float
        float thisdec = nmr/dmr;
        float thatdec = ((FractionImpl)o).nmr/((FractionImpl)o).dmr;


        if (thisdec > thatdec) {
            return Math.round(Math.abs(thisdec - thatdec));
        }
        else if (thatdec > thisdec) {
            return Math.round(-1*(Math.abs(thisdec - thatdec)));
        }
        else { //if (nmr == ((FractionImpl)o).nmr & dmr == ((FractionImpl)o).dmr) {
            return 0;
        }

    }

    /**
     * @inheritDoc
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