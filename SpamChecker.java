import java.net.InetAddress;

public class SpamChecker {

  // Function to reverse an IP address
  private static String reverseIp(String ip) {
    String[] parts = ip.split("\\.");
    return parts[3] + "." + parts[2] + "." + parts[1] + "." + parts[0];
  }

  // Function to check if an IP is listed in Spamhaus DNSBL
  public static boolean isSpammer(String ip) {
    String reversedIp = reverseIp(ip);
    String query = reversedIp + ".zen.spamhaus.org";

    try {
      InetAddress address = InetAddress.getByName(query);
      if (address != null) {
        return true; // IP is listed in DNSBL
      }
    return false; // IP is not listed in DNSBL
    } catch (Exception e) {
      // If an exception is thrown, it means the IP is not listed
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    String ip = "127.0.0.2"; // Example IP address
    if (isSpammer(ip)) {
      System.out.println(ip + " is a spammer.");
    } else {
      System.out.println(ip + " is not a spammer.");
    }
  }
}
