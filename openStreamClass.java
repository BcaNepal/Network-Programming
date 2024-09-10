import java.net.*;
import java.io.*;

public class openStreamClass {
  public static void main(String[] args) {
    try {
      URL u = new URI("https://www.facebook.com").toURL();
      InputStream in = u.openStream();
      int c;
      while ((c = in.read()) != -1)
        System.out.write(c);
      in.close();
    } catch (URISyntaxException ex) {
      System.out.println(ex);
    }
  }
}
