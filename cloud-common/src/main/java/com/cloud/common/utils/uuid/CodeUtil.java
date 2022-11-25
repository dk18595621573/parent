package com.cloud.common.utils.uuid;

import cn.hutool.core.util.ArrayUtil;
import com.cloud.common.exception.ServiceException;

import java.util.Random;

/**
 * id和随机码转换.
 * 将id转换为字符池长度的进制数
 *
 * @author zenghao
 */
public class CodeUtil {

    /**
     * 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符）.
     */
    private static final char[] CHARS = new char[]
        { '6', 'B',  '2', 'D', 'Z', '3', 'X',
             '5', 'K', '9', 'Q', 'S', 'C', 'A', 'P',
            'M', 'J', 'U', 'F', 'R', '4', 'V', 'Y',
            'T', 'N', 'G', 'H', 'W', 'E', '8'};

    /**
     * 定义一个字符用来补全邀请码长度.
     * （该字符前面是计算出来的邀请码，后面是用来补全用的）
     * 该字符不能存在于自定义字符中
     */
    private static final char FIX_CHAR = '7';

    /**
     * 默认随机码长度.
     */
    private static final int CODE_LENGTH = 10;

    /**
     * 根据ID生成随机码.
     *
     * @param id ID
     * @return 随机码
     */
    public static String toCode(final long id) {
        return toCode(id, CODE_LENGTH);
    }

    /**
     * 根据ID生成随机码.
     *
     * @param pk         ID
     * @param codeLength 长度
     * @return 随机码
     */
    public static String toCode(final long pk, final int codeLength) {
        return toCode(CHARS, FIX_CHAR, pk, codeLength);
    }

    /**
     * 根据ID生成随机码.
     *
     * @param pk         ID
     * @param codeLength 长度
     * @return 随机码
     */
    public static String toCode(final char[] chars, final char fixChar, final long pk, final int codeLength) {
        if (ArrayUtil.isEmpty(chars) || ArrayUtil.contains(chars, fixChar)) {
            throw new ServiceException("随机码基础库错误");
        }
        long id = pk;
        char[] buf = new char[32];
        int charPos = 32;
        int length = chars.length;
        while ((id / length) > 0) {
            int ind = (int) (id % length);
            buf[--charPos] = chars[ind];
            id /= length;
        }
        buf[--charPos] = chars[(int) (id % length)];
        String str = new String(buf, charPos, 32 - charPos);
        // 不够长度的自动随机补全
        if (str.length() < codeLength) {
            StringBuilder sb = new StringBuilder();
            sb.append(fixChar);
            Random rnd = new Random();
            for (int i = 1; i < codeLength - str.length(); i++) {
                sb.append(chars[rnd.nextInt(length)]);
            }
            str += sb.toString();
        }
        return str;
    }

    /**
     * 根据随机码生成ID.
     *
     * @param code 随机码
     * @return ID
     */
    public static long toId(final String code) {
        return toId(CHARS, FIX_CHAR, code);
    }

    public static long toId(final char[] chars, final char fixChar, final String code) {
        char[] chs = code.toCharArray();
        long res = 0L;
        int length = chars.length;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == fixChar) {
                break;
            }
            int ind = 0;
            for (int j = 0; j < length; j++) {
                if (chs[i] == chars[j]) {
                    ind = j;
                    break;
                }
            }
            if (i > 0) {
                res = res * length + ind;
            } else {
                res = ind;
            }
        }
        return res;
    }


//    public static void main(String[] args) {
//        long id = new Random().nextInt(30000);
//        System.out.println("id = " + id);
//
//        String code = toCode(id);
//        System.out.println("code1 = " + code);
//        String code2 = toCode(id, 10);
//        System.out.println("code2 = " + code2);
//
//        System.out.println("id1 = " + toId(code));
//        System.out.println("id2 = " + toId(code2));
//        System.out.println("id = " + toId("88888888"));
//    }
}
