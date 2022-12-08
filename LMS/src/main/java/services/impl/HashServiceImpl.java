package services.impl;

import lombok.SneakyThrows;
import services.HashService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashServiceImpl implements HashService {

    @Override
    public String hash(String password) {
        String hashedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            hashedPassword = convertToHex(messageDigest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

    @SneakyThrows
    @Override
    public boolean matches(String password, String hash) {
        return hash(password).equals(hash);
    }
}
