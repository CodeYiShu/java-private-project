package com.codeshu.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对称加密
 *
 * @author CodeShu
 * @date 2024/4/14 20:52
 */
public class SymmetricEncryptionDemo {
	public static void main(String[] args) throws Exception {
		String message = "hello world";

		// 生成 DES 对称密钥（也可以是 AES，init 则需要是 128）
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		SecretKey secretKey = keyGenerator.generateKey();

		// 指定加密解密算法（需和密钥算法一致）
		Cipher cipher = Cipher.getInstance("DES");

		// 加密
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] cipherTextBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		String cipherText = Base64.getEncoder().encodeToString(cipherTextBytes);
		System.out.println("加密后：" + cipherText);

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
		System.out.println("解密后：" + decryptedText);
	}
}
