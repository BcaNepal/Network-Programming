import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogProcessor {

  public static void main(String[] args) {
    String logFilePath = "access.log"; // Path to the access.log file

    // Regex pattern to parse log entries
    String logPattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(.+?)\\] \"(\\w+) (.+?) (.+?)\" (\\d+) (\\d+)";
    Pattern pattern = Pattern.compile(logPattern);

    try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
          String ipAddress = matcher.group(1);
          String timestamp = matcher.group(2);
          String method = matcher.group(3);
          String url = matcher.group(4);
          String protocol = matcher.group(5);
          String responseCode = matcher.group(6);
          String responseSize = matcher.group(7);

          // Output the parsed log details
          System.out.println("IP Address: " + ipAddress);
          System.out.println("Timestamp: " + timestamp);
          System.out.println("Method: " + method);
          System.out.println("URL: " + url);
          System.out.println("Protocol: " + protocol);
          System.out.println("Response Code: " + responseCode);
          System.out.println("Response Size: " + responseSize);
          System.out.println("---------------------------------------");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
