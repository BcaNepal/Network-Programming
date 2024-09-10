import java.io.*;
import java.net.*;
import java.util.*;

public class CookiesManagerEx {

  public static void main(String[] args) {
    CookieManager cookieManager = new CookieManager();
    CookieStore cookieStore = cookieManager.getCookieStore();
    // creating cookie and URL
    HttpCookie cookieA = new HttpCookie("c1", "ram");
    HttpCookie cookieB = new HttpCookie("c2", "sita");
    HttpCookie cookieC = new HttpCookie("c4", "hari");
    URI uri1 = URI.create("https://x.com");
    URI uri2 = URI.create("https://fb.com");
    // ADD COOKIES INTO COOKIESSOTESJKJ#
    //
    cookieStore.add(uri1, cookieA);
    cookieStore.add(uri2, cookieB);
    cookieStore.add(null, cookieC);
    // READ soted cookie
    List cookieList = cookieStore.getCookies();
    System.out.println("Cookie list in Cookiestore:" + cookieList + "\n");
    // REMOVE COOKIE OF URIJK
    cookieStore.remove(uri2, cookieC);
    List<HttpCookie> remainingCookies = cookieStore.getCookies();
    System.out.println("Remaining cookies list in cookiestore " + remainingCookies + "\n");
    // REMOVE ALL Cookie
    //
    cookieStore.removeAll();
    List<HttpCookie> emptyCookieList = cookieStore.getCookies();
    System.out.println("Empty cookies list in cookiestore " + emptyCookieList + "\n");

  }

}
