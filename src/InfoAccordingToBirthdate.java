import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InfoAccordingToBirthdate{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Birth Date: ");
        String input = scanner.nextLine();

        int birthdate = parseBirthdate(input);

        int luckyNumber = calculateLuckyNumber(birthdate);

        String luckyStone = determineLuckyStone(luckyNumber, getBirthMonth(input));

        String starSign = determineStarSign(birthdate);

        System.out.println("Your lucky number is: " + luckyNumber);
        System.out.println("Your lucky stone is: " + luckyStone);
        System.out.println("Your star sign is: " + starSign);

        scanner.close();
    }
    private static int calculateLuckyNumber(int birthdate) {
        int day = birthdate / 1000000;

        int sum = 0;
        while (day > 0 || sum > 9) {
            if (day == 0) {
                day = sum;
                sum = 0;
            }
            sum += day % 10;
            day /= 10;
        }

        return sum;
    }

    private static String determineLuckyStone(int birthMonth, int month) {
        return switch (birthMonth) {
            case 1 -> "Garnet";
            case 2 -> "Amethyst";
            case 3 -> "Aquamarine";
            case 4 -> "Diamond";
            case 5 -> "Emerald";
            case 6 -> "Pearl";
            case 7 -> "Ruby";
            case 8 -> "Peridot";
            case 9 -> "Sapphire";
            case 10 -> "Opal";
            case 11 -> "Citrine";
            case 12 -> "Turquoise";
            default -> "Unknown";
        };
    }

    private static String determineStarSign(int birthdate) {
        int day = birthdate / 1000000;
        int month = (birthdate / 10000) % 100;

        return switch (month) {
            case 1 -> (day >= 20) ? "Aquarius" : "Capricorn";
            case 2 -> (day >= 19) ? "Pisces" : "Aquarius";
            case 3 -> (day >= 21) ? "Aries" : "Pisces";
            case 4 -> (day >= 20) ? "Taurus" : "Aries";
            case 5 -> (day >= 21) ? "Gemini" : "Taurus";
            case 6 -> (day >= 21) ? "Cancer" : "Gemini";
            case 7 -> (day >= 23) ? "Leo" : "Cancer";
            case 8 -> (day >= 23) ? "Virgo" : "Leo";
            case 9 -> (day >= 23) ? "Libra" : "Virgo";
            case 10 -> (day >= 23) ? "Scorpio" : "Libra";
            case 11 -> (day >= 22) ? "Sagittarius" : "Scorpio";
            case 12 -> (day >= 22) ? "Capricorn" : "Sagittarius";
            default -> "Unknown";
        };
    }

    private static int parseBirthdate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        try {
            Date date = dateFormat.parse(input);
            return Integer.parseInt(new SimpleDateFormat("ddMMyyyy").format(date));
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            System.exit(1);
        }
        return 0;
    }

    private static int getBirthMonth(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
        try {
            Date date = dateFormat.parse(input.split(" ")[1]);
            return Integer.parseInt(new SimpleDateFormat("MM").format(date));
        } catch (ParseException e) {
            System.out.println("Error getting birth month.");
            System.exit(1);
        }
        return 0;
    }
}
