import java.net.*;
import java.io.*;

public class URLPieces {
  public static void main(String[] args) {
    try {
      URL u = new URI("https://www.janamaitri.edu.np/").toURL();
      System.out.println(u.getHost());
      System.out.println(u.getPort());
    } catch (IOException e) {
      System.out.println(e);
    } catch (URISyntaxException ue) {
      System.out.println(ue);
    }
  }
}
