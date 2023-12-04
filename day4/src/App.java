import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) throws Exception {
    App.partOne();
    App.partTwo();
  }

  private static void partOne() throws Exception {
    try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
      String line = br.readLine();
      int total = 0;
      while (line != null) {
        String[] cardNumbers = line.split(":");
        String[] numberStrings = cardNumbers[1].split("\\|");
        ArrayList<Integer> winningNumbers = readNumbers(numberStrings[0]);
        ArrayList<Integer> numbersIHave = readNumbers(numberStrings[1]);
        int currValue = 0;
        for (Integer numberIHave : numbersIHave) {
          if (winningNumbers.indexOf(numberIHave) != -1) {
            currValue = currValue > 0 ? currValue * 2 : 1;
          }
        }
        total += currValue;
        line = br.readLine();
      }
      System.out.println(total);
    }
  }

  private static void partTwo() throws Exception {
    try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
      String line = br.readLine();
      int total = 0;
      // This array holds the number of copies that have been accumulated from previous
      // winning cards. copies[0] = the next card, copies[1] = the card after that.  It
      // moves with the current card basically.
      ArrayList<Integer> copies = new ArrayList<>();
      while (line != null) {
        String[] cardNumbers = line.split(":");
        String[] numberStrings = cardNumbers[1].split("\\|");
        ArrayList<Integer> winningNumbers = readNumbers(numberStrings[0]);
        ArrayList<Integer> numbersIHave = readNumbers(numberStrings[1]);
        int numMatches = 0;
        for (Integer numberIHave : numbersIHave) {
          if (winningNumbers.indexOf(numberIHave) != -1) {
            numMatches++;
          }
        }
        // Track the original card.
        total += 1;
        // Make sure the copies array is the right size even if we don't get a match below.
        while (copies.size() < numbersIHave.size()) {
          copies.add(0);
        }
        int currCopies = copies.get(0);
        copies.remove(0);
        // Add the running total of copies
        total += currCopies;
        // For each match, add total number of this card we have (1 + currCopies)
        for (int i = 0; i < numMatches; i++) {
          if (copies.size() > i) {
            copies.set(i, copies.get(i) + (currCopies + 1));
          } else {
            copies.add(1);
          }
        }
        line = br.readLine();
      }
      System.out.println(total);
    }
  }


  private static ArrayList<Integer> readNumbers(String s) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    String currNumber = new String();
    for (int i = 0; i < s.length(); i++) {
      char currChar = s.charAt(i);
      if (currChar >= '0' && currChar <= '9') {
        currNumber += currChar;
      } else if (currNumber.length() > 0) {
        ret.add(Integer.valueOf(currNumber));
        currNumber = new String();
      }
    }
    // If the string ends in a number, add it!
    if (currNumber.length() > 0) {
      ret.add(Integer.valueOf(currNumber));
    }
    return ret;
  }
}
