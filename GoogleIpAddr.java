import java.net.*;

public class GoogleIpAddr{
  public static void main(String[] args) {
    try {
      InetAddress address = InetAddress.getByName("www.google.com");
      System.out.println(address);
    } catch (UnknownHostException ex) {
      System.err.println("Could not find website " + ex);
    }
  }
}
