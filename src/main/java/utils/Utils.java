package utils;

import java.util.*;

public class Utils {
    public static String getEncodedCredential() {
        String credentials = "shivam311kanungo@gmail.com/token:EZts85rwJZUxMlxy5iF6ppGt86xAsT8NJAZGnjVF";
        String encodedCredential = new String(Base64.getEncoder().encode(credentials.getBytes()));
        return encodedCredential;
    }
}
