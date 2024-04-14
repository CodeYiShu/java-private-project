package com.codeshu.crypto;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密
 *
 * @author CodeShu
 * @date 2024/4/14 21:14
 */

public class AsymmetricEncryptionDemo {
	public static void main(String[] args) throws Exception {
		String plainText = "hello world";

		// 生成 RSA 密钥对
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair keyPair = keyGen.generateKeyPair();
		// 公钥、私钥
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		// 指定加密解密算法（需和密钥算法一致）
		Cipher cipher = Cipher.getInstance("RSA");

		// 加密（公钥加密）
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes());
		String cipherText = new String(cipherTextBytes);

		// 解密（私钥解密）
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);
		String decryptedText = new String(decryptedBytes);

		System.out.println("明文：" + plainText);
		System.out.println("加密: " + cipherText);
		System.out.println("解密: " + decryptedText);
	}
}
