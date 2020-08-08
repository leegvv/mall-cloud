package net.arver.mall.cloud.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * 数字工具类.
 * @author arver
 */
public class NumberUtils {

    /**
     * 判断字符串是否是数值格式.
     * @param str 字符串
     * @return boolean
     */
    public static boolean isDigit(final String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        return str.matches("^\\d+$");
    }

    /**
     * 将一个小数精确到指定位数.
     * @param num 数字
     * @param scale 精度位数
     * @return 小数
     */
    public static double scale(final double num, final int scale) {
        final BigDecimal decimal = new BigDecimal(num);
        return decimal.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 生成指定位数的随机数字.
     * @param len 位数
     * @return 随机数字
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static String generateCode(final int len) {
        final int minLen = Math.min(len, 8);
        final int min = Double.valueOf(Math.pow(10, minLen - 1)).intValue();
        final int num = new Random().nextInt(Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0, len);

    }
}
