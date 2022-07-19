package com.cloud.component.chinapay.util;

import com.cloud.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class Encryptor {

	public static final String ALG_SHA512 = "SHA-512";

	/**
	 * 哈希算法
	 * 
	 * @param input
	 *            摘要数据
	 * @param alg
	 *            算法名称(MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512)
	 * @return
	 */
	public static String encode(String input, String alg) {
		try {
			log.debug("[摘要数据加密] 算法名称=[{}];摘要数据=[{}];", alg, input);
			if(StringUtils.isAnyBlank(input, alg)){
				log.debug("[摘要数据加密]. 摘要数据或算法名称为空;");
				return null;
			}
			//创建加密对象
			MessageDigest digit = MessageDigest.getInstance(alg);
			//摘要加密
			digit.update(input.getBytes(StandardCharsets.UTF_8));
			//得到加密字符串结果
			String str = byte2hex(digit.digest());
			log.debug("[摘要数据{}加密] 摘要加密结果=[{}];", alg, input);
			return str;
		} catch (Exception e) {
			log.warn("[摘要数据{}加密] 摘要数据[{}]加密异常. error:{}", alg, input, e);
		}
		return null;
	}
	
	/**
	 * byte数组转为String
	 * 
	 * @param b byte数组
	 * @return String
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		for (final byte value : b) {
			String stmp = Integer.toHexString(value & 0xFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}
}
