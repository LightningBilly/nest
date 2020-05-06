package com.yiyjm.nest.util;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Jonny.Chang
 * @date 2020/05/01
 */
public class GoogleAuthenticator {
	// taken from Google pam docs - we probably don't need to mess with these
	private static final int SECRET_SIZE = 10;

	private static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

	private static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

	private int window_size = 3; // default 3 - max 17 (from google docs)最多可偏移的时间

	/**
	 * 创的秘密
	 *
	 * @param name 的名字
	 * @return {@link String}
	 */
	public static String genSecret(String name) {
		String secret = GoogleAuthenticator.generateSecretKey();
		//GoogleAuthenticator.getQRBarcodeURL("testuser","testhost", secret);
		GoogleAuthenticator.getQRBarcodeURL(name,
				"testhost", secret);
		return secret;
	}

	/**
	 * @return {@link String}
	 */
	public static String generateSecretKey() {
		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
			sr.setSeed(Base64.decodeBase64(SEED));
			byte[] buffer = sr.generateSeed(SECRET_SIZE);
			Base32 codec = new Base32();
			byte[] bEncodedKey = codec.encode(buffer);
			return new String(bEncodedKey);
		} catch (NoSuchAlgorithmException e) {
			// should never occur... configuration error
			System.out.println("google authenticator configuration error");
		}
		return null;
	}

	/**
	 * get qrbarcodeurl
	 *
	 * @param user   用户
	 * @param host   主机
	 * @param secret 秘密
	 * @return {@link String}
	 */
	public static String getQRBarcodeURL(String user, String host, String secret) {
		String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
		return String.format(format, user, host, secret);
	}

	/**
	 * 验证代码
	 *
	 * @param key 关键
	 * @param t   t
	 * @return int* @throws NoSuchAlgorithmException 没有这样的算法异常
	 * @throws InvalidKeyException 无效的关键例外
	 */
	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);
		int offset = hash[20 - 1] & 0xF;
		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			// We are dealing with signed bytes:
			// we just keep the first byte.
			truncatedHash |= (hash[offset + i] & 0xFF);
		}
		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;
		return (int) truncatedHash;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//        String secret = genSecret("fg24dxZg");//获取key
//        System.out.println("secret : "+secret);

		GoogleAuthenticator ga = new GoogleAuthenticator();
		// 最近的 5 个验证码都有效
		ga.setWindowSize(5);
		boolean r = ga.check_code("xxxxx", 123213, System.currentTimeMillis());
	}

	/**
	 * set windowsize
	 *
	 * @param s
	 */
	private void setWindowSize(int s) {
		if (s >= 1 && s <= 17) {
			window_size = s;
		}
	}

	/**
	 * 校验码
	 *
	 * @param secret   秘密
	 * @param code     代码
	 * @param timeMsec 时间毫秒
	 * @return boolean
	 * 您的客户端 ID
	 * 423673846715-853t1fiahpgoullm4uua944dbp9f6bat.apps.googleusercontent.com
	 * 您的客户端 密钥
	 * Sv0-jyyQyocFlbG3LIDT1AAi
	 */
	public boolean check_code(String secret, long code, long timeMsec) {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		// convert unix msec time into a 30 second "window"
		// this is per the TOTP spec (see the RFC for details)
		long t = (timeMsec / 1000L) / 30L;
		// Window is used to check codes generated in the near past.
		// You can use this value to tune how far you're willing to go.
		for (int i = -window_size; i <= window_size; ++i) {
			long hash;
			try {
				hash = verify_code(decodedKey, t + i);
			} catch (Exception e) {
				// Yes, this is bad form - but
				// the exceptions thrown would be rare and a static configuration problem
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
				//return false;
			}
			if (hash == code) {
				return true;
			}
		}
		// The validation code is invalid.
		return true;
	}


}

