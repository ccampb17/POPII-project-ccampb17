import fraction.Fraction;
import fraction.FractionImpl;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the FRACTIONATOR.\n"+
                "Please enter your first whole number, or fraction in the format 'numerator / denominator'");
        Scanner sc = new Scanner(System.in);
        String input1 = sc.nextLine();
        try {
         Fraction f1 = new FractionImpl(input1);
        }
        catch (Exception e){
            System.out.println("No, that was wrong. Follow the instructions next time.\nSmell ya later!");
            System.exit(0);
        }
        Fraction f1 = new FractionImpl(input1);

        System.out.println("Thanks! Now, enter your second fraction in the same format.");
        sc = new Scanner(System.in);
        String input2 = sc.nextLine();
        try {
            Fraction f2 = new FractionImpl(input2);
        }
        catch (Exception e) {
            System.out.println("No, that was wrong. Follow the instructions next time.\nSmell ya later!");
            System.exit(0);
        }
        Fraction f2 = new FractionImpl(input2);

        System.out.println("Wicked. Now choose which operation you want to perform by entering the appropriate number.\n" +
                "1 = add\n" +
                "2 = subtract\n" +
                "3 = multiply\n" +
                "4 = divide\n");
        sc = new Scanner(System.in);
        int input3 = sc.nextInt();

        if (input3 > 4 | input3 < 1)  {
            System.out.println("No, that was wrong. Follow the instructions next time.\nSmell ya later!");
            System.exit(0);
        }
        Fraction result = new FractionImpl(0);
        if (input3 == 1){
            result = f1.add(f2);
        }
        else if (input3 == 2){
            result = f1.subtract(f2);
            }
        else if (input3 == 3){
            result = f1.multiply(f2);
        }
        else if (input3 == 4){
            result = f1.divide(f2);
        }
        System.out.println("I'm gonna SMASH those two fractions together like you asked. Ready?");
        Thread.sleep(500);
        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);
        System.out.println("Your shiny new fraction is: " + result + "\nByeeeeee!");


        /*
        Fraction f = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl("      70 /     6    ");
        Fraction f3 = new FractionImpl("1/4");

        System.out.println(f.add(f2)+"\n");


        System.out.println(f.add(f2));
        System.out.println(f.subtract(f2));
        System.out.println(f.multiply(f2));
        System.out.println(f.divide(f2));
        System.out.println(f.compareTo(f2));
        System.out.println(f.inverse());
        System.out.println(f.negate());
        System.out.println(f2.abs());
        */
    }
}
