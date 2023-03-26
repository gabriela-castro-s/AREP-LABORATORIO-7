package org.example;

import org.example.URLReader;

import static spark.Spark.*;

public class HelloSecureSpark2 {

    public static void main(String... args) {
        secure(getKeyStore(), getPwdStore() , null, null);
        port(getPort());

        get("/helloLocal", (req, res) -> "Hello Local Maquina 2");
        get("/helloRemote", (req, res) -> URLReader.readSecureUrl(getUrl(), getKeyOtherStore(), getPwdStore()));
    }

    private static String getUrl() {
        if (System.getenv("URL") != null) {
            return System.getenv("PORT");
        }
        //https://ec2-3-239-158-13.compute-1.amazonaws.com:5001/helloLocal
        return "https://ec2-3-239-158-13.compute-1.amazonaws.com:5000/helloLocal";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    public static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/certificates/ecikeystore2.p12";
    }

    public static String getKeyOtherStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/certificates/ecikeystore1.p12";
    }

    public static String getPwdStore() {
        if (System.getenv("PWDSTORE") != null) {
            return System.getenv("PWDSTORE");
        }
        return "123456";
    }

}