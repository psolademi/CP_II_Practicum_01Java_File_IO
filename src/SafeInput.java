import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SafeInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (scanner.hasNextInt()) {
                retVal = scanner.nextInt();
                scanner.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                scanner.nextLine();
                System.out.println("You must enter an int.");
            }
        } while (!done);

        return retVal;
    }

    public static double getRangedDouble(String prompt, double low, double high) {
        double retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (scanner.hasNextDouble()) {
                retVal = scanner.nextDouble();
                scanner.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                scanner.nextLine();
                System.out.println("You must enter a double.");
            }
        } while (!done);

        return retVal;
    }

    public static String getNonEmptyString(String prompt) {
        String retVal = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retVal = scanner.nextLine().trim();

            if (!retVal.isEmpty()) {
                done = true;
            } else {
                System.out.println("Input cannot be empty. Please enter a non-empty string.");
            }

        } while (!done);

        return retVal;
    }

    public static boolean getYNConfirm(String prompt) {
        boolean retVal = true;
        String response;
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]!");
            }

        } while (!gotAVal);

        return retVal;
    }

    public static ArrayList<String> getArrayOfStrings(String prompt) {
        boolean doneEntering = false;
        ArrayList<String> retval = new ArrayList<>();
        System.out.println("\n" + prompt + ": ");
        do {
            if (scanner.hasNext()) {
                retval.add(scanner.nextLine());
            }
            doneEntering = !getYNConfirm("Add more?");
        } while (!doneEntering);
        return retval;
    }

    public static String getEmail(String prompt) {
        String retval = "";
        boolean done = false;
        do {
            System.out.println("\n" + prompt);
            if (scanner.hasNext()) {
                retval = scanner.next();
            }

            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            if (pattern.matcher(retval).matches()) {
                done = true;
            } else {
                System.out.println("Your input doesn't match an Email pattern");
            }

        } while (!done);

        return retval;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
