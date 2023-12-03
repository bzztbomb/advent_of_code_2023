import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class App {
  public static void main(String[] args) throws Exception {
    App.partOne();
    App.partTwo();
  }

  private static void partOne() throws Exception {
    int MAX_RED = 12;
    int MAX_GREEN = 13;
    int MAX_BLUE = 14;

    try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
      String line = br.readLine();
      int total = 0;
      while (line != null) {
        // Split game id from full line
        String[] firstSplit = line.split(":");
        // Get the number + ":" by splitting on space
        String[] gameIdSplit = firstSplit[0].split(" ");
        String sGameId = gameIdSplit[1];
        int gameId = Integer.valueOf(sGameId);
        // Next, split out the rounds, they are separated by ";"
        String[] rounds = firstSplit[1].split(";");
        boolean validGame = true;
        for (int i = 0; i < rounds.length && validGame; i++) {
          HashMap<String, Integer> colorsSeen = new HashMap<>();
          colorsSeen.put("red", 0);
          colorsSeen.put("green", 0);
          colorsSeen.put("blue", 0);
          String[] cubes = rounds[i].split(",");
          for (int j = 0; j < cubes.length; j++) {
            String[] numberColor = cubes[j].trim().split(" ");
            Integer currentCount = colorsSeen.get(numberColor[1]);
            int newValue = currentCount.intValue() + Integer.valueOf(numberColor[0]);
            colorsSeen.put(numberColor[1], newValue);
          }
          validGame = (colorsSeen.get("red").intValue() <= MAX_RED &&
              colorsSeen.get("blue").intValue() <= MAX_BLUE &&
              colorsSeen.get("green").intValue() <= MAX_GREEN);
        }
        if (validGame) {
          total += gameId;
        }
        line = br.readLine();
      }
      System.out.println(total);
    }
  }

  private static void partTwo() throws Exception {
    try (BufferedReader br = new BufferedReader(new FileReader("puzzle_input.txt"))) {
      String line = br.readLine();
      int total = 0;
      while (line != null) {
        String[] firstSplit = line.split(":");
        String[] rounds = firstSplit[1].split(";");
        HashMap<String, Integer> maxColors = new HashMap<>();
        maxColors.put("red", 0);
        maxColors.put("green", 0);
        maxColors.put("blue", 0);
        for (int i = 0; i < rounds.length; i++) {
          HashMap<String, Integer> colorsSeen = new HashMap<>();
          colorsSeen.put("red", 0);
          colorsSeen.put("green", 0);
          colorsSeen.put("blue", 0);
          String[] cubes = rounds[i].split(",");
          for (int j = 0; j < cubes.length; j++) {
            String[] numberColor = cubes[j].trim().split(" ");
            Integer currentCount = colorsSeen.get(numberColor[1]);
            int newValue = currentCount.intValue() + Integer.valueOf(numberColor[0]);
            colorsSeen.put(numberColor[1], newValue);
          }
          maxColors.put("red", Math.max(maxColors.get("red"), colorsSeen.get("red")));
          maxColors.put("green", Math.max(maxColors.get("green"), colorsSeen.get("green")));
          maxColors.put("blue", Math.max(maxColors.get("blue"), colorsSeen.get("blue")));
        }
        int power = maxColors.get("red") * maxColors.get("green") * maxColors.get("blue");
        total += power;
        line = br.readLine();
      }
      System.out.println(total);
    }
  }
}
