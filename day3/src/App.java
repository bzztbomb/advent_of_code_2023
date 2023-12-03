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
      ArrayList<String> currentLines = new ArrayList<String>();
      // Read the first line to get the length of the schematic strings
      String firstLine = br.readLine(); 
      currentLines.add(new String(".").repeat(firstLine.length()));     
      currentLines.add(firstLine);
      currentLines.add(br.readLine());
      int total = 0;
      int currLine = 0;
      while (currentLines.size() > 1) {
        String lineToProcess = currentLines.get(1);
        String currentPartNumber = new String();        
        for (int i = 0; i < lineToProcess.length(); i++) {
          char c = lineToProcess.charAt(i);
          boolean numberFound = false;
          if (c >= '0' && c <= '9') {
            currentPartNumber += c;
            numberFound = true;
          }
          if (currentPartNumber.length() > 0 && (!numberFound || i == lineToProcess.length() - 1)) {
            // We have a part number, let's see if the number is adjcent to a symbol.
            int adjust = (i == lineToProcess.length() - 1 && numberFound)  ? 1 : 0;
            int numberStart = (i+adjust) - currentPartNumber.length();
            int startSearch = numberStart > 0 ? numberStart - 1 : 0;
            int endSearch = i + 1 < firstLine.length() ? i + 1 : firstLine.length();
            boolean validPartNumber = false;
            for (int j = 0; j < currentLines.size() && !validPartNumber; j++) {
              String searchString = currentLines.get(j).substring(startSearch, endSearch);
              for (int k = 0; k < searchString.length() && !validPartNumber; k++) {
                if ((searchString.charAt(k) < '0' || searchString.charAt(k) > '9') && searchString.charAt(k) != '.') {
                  validPartNumber = true;
                }
              }
            }
            if (validPartNumber) {
              total += Integer.valueOf(currentPartNumber);
            } else {
              validPartNumber = false;
            }
            currentPartNumber = new String();
          }
        }
        currentLines.remove(0);
        String nextLine = br.readLine();
        if (nextLine != null) {
          currentLines.add(nextLine);
        }
        currLine++;
      }
      System.out.println(total);
    }
  }

  private static void partTwo() throws Exception {
    try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
      ArrayList<String> currentLines = new ArrayList<String>();
      // Read the first line to get the length of the schematic strings
      String firstLine = br.readLine(); 
      currentLines.add(new String(".").repeat(firstLine.length()));     
      currentLines.add(firstLine);
      currentLines.add(br.readLine());
      int total = 0;
      int currLine = 0;
      while (currentLines.size() > 1) {
        String lineToProcess = currentLines.get(1);
        for (int i = 0; i < lineToProcess.length(); i++) {
          char c = lineToProcess.charAt(i);
          if (c == '*') {
            int ratio = 1;
            int numFound = 0;
            for (int j = 0; j < currentLines.size(); j++) {
              String searchLine = currentLines.get(j);
              String currentPartNumber = new String();
              for (int k = 0; k < searchLine.length(); k++) {
                boolean numberFound = false;
                char l = searchLine.charAt(k);
                if (l >= '0' && l <= '9') {
                  currentPartNumber += l;
                  numberFound = true;
                }
                if (currentPartNumber.length() > 0 && (!numberFound || k == searchLine.length() - 1)) {
                  // We have a part number, let's see if the number is adjcent to a symbol.
                  int adjust = (k == searchLine.length() - 1 && numberFound)  ? 1 : 0;
                  int numberStart = (k+adjust) - currentPartNumber.length();
                  int startSearch = numberStart > 0 ? numberStart - 1 : 0;
                  int endSearch = k + 1 < firstLine.length() ? k + 1 : firstLine.length();
                  if (startSearch <= i && endSearch > i) {
                    ratio *= Integer.valueOf(currentPartNumber);
                    numFound++;
                  }
                  currentPartNumber = new String();
                }
              }
            }
            if (numFound > 1) {
              total += ratio;
            }
          }
        }
        currentLines.remove(0);
        String nextLine = br.readLine();
        if (nextLine != null) {
          currentLines.add(nextLine);
        }
        currLine++;
      }
      System.out.println(total);
    }
  }
}
