package com.cloud.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 精确的浮点数运算
 *
 * @author author
 */
public class Arith {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 这个类不能实例化
     */
    private Arith() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if (b1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * BigDecimal 比较大小 >=.
     *
     * @param big1 比较数.
     * @param big2 被比较数.
     * @return 是否大于等于
     */
    public static boolean gte(final BigDecimal big1, final BigDecimal big2) {
        return big1.compareTo(big2) >= 0;
    }

    /**
     * BigDecimal 比较大小 >.
     *
     * @param big1 比较数.
     * @param big2 被比较数.
     * @return 是否大于等于
     */
    public static boolean gt(final BigDecimal big1, final BigDecimal big2) {
        return big1.compareTo(big2) > 0;
    }


    /**
     * 不限制参数加法.
     *
     * @param values 相加的数字
     * @return 多个数字的和
     */
    public static BigDecimal add(final BigDecimal... values) {
        if (Objects.isNull(values) || Stream.of(values).allMatch(Objects::isNull)) {
            return BigDecimal.ZERO;
        }
        return Stream.of(values).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 不限制参数减法.
     *
     * @param minuend 被减数
     * @param sub     减数
     * @return 若被减数为null，则返回0 若减数中含有null，则不参与计算
     */
    public static BigDecimal sub(final BigDecimal minuend, final BigDecimal... sub) {
        if (Objects.isNull(minuend)) {
            return BigDecimal.ZERO;
        }
        if (Objects.isNull(sub) || Stream.of(sub).allMatch(Objects::isNull)) {
            return minuend;
        }
        return Stream.of(sub).filter(Objects::nonNull).reduce(minuend, BigDecimal::subtract);
    }

    /**
     * 不限制参数乘法.
     *
     * @param values 相乘的数字
     * @return 若相乘数有空，则返回0。否则返回所有数的乘积
     */
    public static BigDecimal mul(final BigDecimal... values) {
        return mul(false, values);
    }

    /**
     * 不限制参数乘法.
     *
     * @param ignoreNull 是否忽略null
     * @param values     相乘的数字
     * @return 返回所有数的乘积 若相乘数有null，则返回根据ignoreNull选择忽略或者返回0
     */
    public static BigDecimal mul(final boolean ignoreNull, final BigDecimal... values) {
        if (!ignoreNull && (Objects.isNull(values) || Stream.of(values).anyMatch(Objects::isNull))) {
            return BigDecimal.ZERO;
        }
        return Stream.of(values).filter(Objects::nonNull).reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    /**
     * 除法。当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入.
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(final BigDecimal v1, final BigDecimal v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 除法。当发生除不尽的情况时，精确到小数点以后 scale 位，以后的数字四舍五入.
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点精确位数
     * @return 两个参数的商
     */
    public static BigDecimal div(final BigDecimal v1, final BigDecimal v2, final int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 除法.
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        小数点精确位数
     * @param roundingMode 小数点最后一位处理方法
     * @return 两个参数的商
     */
    public static BigDecimal div(final BigDecimal v1, final BigDecimal v2, final int scale, final RoundingMode roundingMode) {
        if (Objects.isNull(v1) || v1.compareTo(BigDecimal.ZERO) == 0 || Objects.isNull(v2) || v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return v1.divide(v2, scale, roundingMode);
    }

    /**
     * 取一组值中最小的值.
     *
     * @param values 数字组
     * @return 返回该数组中除null最小的值
     */
    public static BigDecimal min(final BigDecimal... values) {
        if (Objects.isNull(values)) {
            return null;
        }
        return Stream.of(values).filter(Objects::nonNull).min(BigDecimal::compareTo).orElse(null);
    }

    /**
     * 取一组值中最大的值.
     *
     * @param values 数字组
     * @return 返回该数组中除null最大的值
     */
    public static BigDecimal max(final BigDecimal... values) {
        if (Objects.isNull(values)) {
            return null;
        }
        return Stream.of(values).filter(Objects::nonNull).max(Comparator.naturalOrder()).orElse(null);
    }

    /**
     * double转BigDecimal,为空默认0.
     *
     * @param val val
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(final Double val) {
        return toBigDecimal(val, BigDecimal.ZERO);
    }

    /**
     * double转BigDecimal.
     *
     * @param val        val
     * @param defaultVal 默认值
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(final Double val, final BigDecimal defaultVal) {
        return Objects.isNull(val) ? defaultVal : BigDecimal.valueOf(val);
    }

    /**
     * 进制.
     *
     * @param decimal BigDecimal
     * @param scale   int
     * @param mode    进制模式
     * @return BigDecimal.
     */
    public static BigDecimal scale(final BigDecimal decimal, final int scale, final RoundingMode mode) {
        if (Objects.isNull(decimal)) {
            return BigDecimal.ZERO;
        }
        return decimal.setScale(scale, mode);
    }

    /**
     * one 小于 two.
     *
     * @param one one
     * @param two two
     * @return boolean
     */
    public static boolean lt(BigDecimal one, BigDecimal two) {
        return one.compareTo(two) < 0;
    }

    /**
     * one 小于等于 two.
     *
     * @param one one
     * @param two two
     * @return boolean
     */
    public static boolean lte(BigDecimal one, BigDecimal two) {
        return one.compareTo(two) <= 0;
    }

}
