import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        App.partOne();
        App.partTwo();
    }

    // Steps:
    // Open the file
    // Print one line of the file
    // Print all lines of the file
    // Next, find the first and last numbers.
    // Print and check the number we found
    // Convert to int
    // Add to total
    private static void partOne() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
            String line = br.readLine();
            ArrayList<Character> numbers = new ArrayList<Character>();
            int total = 0;
            while (line != null) {
                numbers.clear();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c >= '0' && c <= '9') {
                        numbers.add(c);
                    }
                }
                if (numbers.size() > 0) {
                    String digits = numbers.get(0).toString();
                    if (numbers.size() > 1) {
                        digits += numbers.get(numbers.size() - 1);
                    } else {
                        digits += numbers.get(0);
                    }
                    total += Integer.valueOf(digits);
                }
                line = br.readLine();
            }
            System.out.println(total);
        }
    }

    // Same as above, but look for "one", "two", "three", etc..
    private static void partTwo() throws Exception {
        HashMap<String, Character> englishDigits = new HashMap<String, Character>();
        englishDigits.put("one", '1');
        englishDigits.put("two", '2');
        englishDigits.put("three", '3');
        englishDigits.put("four", '4');
        englishDigits.put("five", '5');
        englishDigits.put("six", '6');
        englishDigits.put("seven", '7');
        englishDigits.put("eight", '8');
        englishDigits.put("nine", '9');
        try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
            String line = br.readLine();
            ArrayList<Character> numbers = new ArrayList<Character>();
            int total = 0;
            while (line != null) {
                numbers.clear();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c >= '0' && c <= '9') {
                        numbers.add(c);
                    } else {
                        for (Map.Entry<String, Character> englishDigit : englishDigits.entrySet()) {
                            String english = englishDigit.getKey();
                            if (i + english.length() <= line.length()) {
                                String subStr = line.substring(i, i + english.length());
                                if (subStr.compareTo(english) == 0) {
                                    numbers.add(englishDigit.getValue());
                                }
                            }
                        }
                    }
                }
                if (numbers.size() > 0) {
                    String digits = numbers.get(0).toString();
                    if (numbers.size() > 1) {
                        digits += numbers.get(numbers.size() - 1);
                    } else {
                        digits += numbers.get(0);
                    }
                    System.out.println(line);
                    System.out.println(digits);
                    total += Integer.valueOf(digits);
                }
                line = br.readLine();
            }
            System.out.println(total);
        }
    }

}
