import java.net.*;
import java.io.*;

public class ReadFromUrl {
  public static void main(String[] args) {
    try {
      URL url = new URI("https://www.janamaitri.edu.np/").toURL();
      URLConnection uc = url.openConnection();
      InputStreamReader in = new InputStreamReader(uc.getInputStream());
      BufferedReader bin = new BufferedReader(in);
      String inputLine;
      while ((inputLine = bin.readLine()) != null)
        System.out.println(inputLine);
      in.close();
    } catch (IOException e) {
      System.out.println(e);
    } catch (URISyntaxException ue) {
      System.out.println(ue);
    }
  }
}
