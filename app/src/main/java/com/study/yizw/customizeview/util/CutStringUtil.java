package com.study.yizw.customizeview.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * 字符串截取类
 * 根据编码结构来截取字符串
 * <p>
 * Created by yizw on 2016/9/20 16:48.
 */

public class CutStringUtil {

    /**
     * 判断是否是一个中文汉字
     *
     * @param c 字符
     * @return true表示是中文汉字，false表示是英文字母
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static boolean isChineseChar(char c)
            throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
        return String.valueOf(c).getBytes("GBK").length > 1;
    }

    /**
     * 按字节截取字符串
     *
     * 中文汉字：
     * 编码 GBK ， 字节数 ：2
     * 编码 UTF-8，字节数 ：3
     *
     * @param original 原始字符串
     * @param count   截取位数
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static String substring(String original, int count, String charsetName)
            throws UnsupportedEncodingException {
        if (null == charsetName) {
            charsetName = "UTF-8";
        }
        // 原始字符不为null，也不是空字符串
        if (original != null && !"".equals(original)) {
            // 将原始字符串转换为charsetName编码格式
            original = new String(original.getBytes(), charsetName);
            // 要截取的字节数大于0，且小于原始字符串的字节数
            if (count > 0 && count < original.getBytes("GBK").length) {
                StringBuilder buff = new StringBuilder();
                char c;
                for (int i = 0; i < count; i++) {
                    // charAt(int index)也是按照字符来分解字符串的
                    c = original.charAt(i);
                    // 如果截取到最后一个为汉字且被分开，去掉(不截取)该汉字
                    if (CutStringUtil.isChineseChar(c) && i == (count - 1)) {
                        break;
                    }
                    buff.append(c);
                    if (CutStringUtil.isChineseChar(c)) {
                        // 遇到中文汉字，截取字节总数减1
                        --count;
                    }
                }
                return buff.toString();
            }
        }
        return original;
    }

    /**
     * 判断一个string是否是数字
     *
     * @param str 字符串
     * */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


}
