package com.example.tatkal.utils;

import com.example.tatkal.dto.request.AuthTokenRequestDTO;
import com.example.tatkal.dto.response.CaptchaResponseDTO;
import com.example.tatkal.entity.User;
import com.example.tatkal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



@Service
public class EncryptDecrypt {
    @Autowired
    private UserRepo userRepo;

    public String getDataForLogin(AuthTokenRequestDTO authTokenRequestDTO) throws Exception {
        String userName = authTokenRequestDTO.getUserName();
        String captcha = authTokenRequestDTO.getCaptcha();
        User user = userRepo.findByUserName(userName);
        String loginId = user.getUserName();
        String password = user.getPassword();
        String uid = user.getUid();
        String otpLogin = "false"; // Example value
        String lso = ""; // Example value

        // Base64 encode the password
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));

        // Create the 16-character key
        String key = (captcha + uid + "AAAAAAAAAAAAAAAA").substring(0, 16);

        // Concatenate loginId, encodedPassword, and current timestamp
        String concatenated = loginId + "#UP#" + encodedPassword + "#UP#" + System.currentTimeMillis();

        // Encrypt the concatenated string
        String encryptedData = encrypt(key, concatenated);

        // Print the encrypted data
        System.out.println("Encrypted Data: " + encryptedData);
        return encryptedData;
    }

    public static String encrypt(String key, String value) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(keyBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static void main(String[] args) {
        try {
            String loginId = "kumar22096";
            String password = "Kumar@123";
            String captcha = "XPN97";
            String uid = "GQ:ab15124f-ecb3-4892-850a-8f14ce418c93";
            String otpLogin = "false"; // Example value
            String lso = ""; // Example value

            // Base64 encode the password
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));

            // Create the 16-character key
            String key = (captcha + uid + "AAAAAAAAAAAAAAAA").substring(0, 16);

            // Concatenate loginId, encodedPassword, and current timestamp
            String concatenated = loginId + "#UP#" + encodedPassword + "#UP#" + System.currentTimeMillis();

            // Encrypt the concatenated string
            String encryptedData = encrypt(key, concatenated);

            // Print the encrypted data
            System.out.println("Encrypted Data: " + encryptedData);

            // This would be part of the HTTP request body
            String requestBody = String.format("grant_type=password&data=%s&captcha=%s&uid=%s&otpLogin=%s&lso=%s&encodedPwd=true",
                    encryptedData, captcha, uid, otpLogin, lso);

            System.out.println("Request Body: " + requestBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
