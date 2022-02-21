package com.example.projetks;

import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Decrypt {
    public Decrypt(){}
    public Decrypt(ImageView imageView, String pass){
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        int x = 0;
        int y = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double r = pixelReader.getColor(i, j).getRed();
                double g = pixelReader.getColor(i, j).getGreen();
                double b = pixelReader.getColor(i, j).getBlue();
                x++;
                y++;
                if (x==image.getHeight()-1) x=0;
                if (y==image.getWidth()-1) y=0;
                int p = 0;
                char[] passC =pass.toCharArray();
                int a = passC[p];
                r= r * a;
                b= b * a;
                p++;
                if (p==pass.length()-1) x=0;
                Color color = new Color(r, g, b, 1.0);
                pixelWriter.setColor(i, j, color);
            }
        }
        imageView.setImage(writableImage);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String createPassword(String password) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[12];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        md.update(password.getBytes("UTF8"));
        byte[] digest = md.digest();
        BigInteger ins = new BigInteger(bytesToHex(digest), 16);
        return ins.toString();
    }
}