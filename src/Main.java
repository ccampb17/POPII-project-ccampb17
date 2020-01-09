import fraction.Fraction;
import fraction.FractionImpl;


public class Main {


    public static void main(String[] args) {
        System.out.println("Hello World!");
        Fraction f = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl("      4 /     -3    ");
        Fraction f3 = new FractionImpl("1/2");

        System.out.println(f.equals(f3)+"\n");


        System.out.println(f.add(f2));
        System.out.println(f.subtract(f2));
        System.out.println(f.multiply(f2));
        System.out.println(f.divide(f2));
        System.out.println(f.compareTo(f2));
        System.out.println(f.inverse());
        System.out.println(f.negate());
        System.out.println(f2.abs());

    }
}
