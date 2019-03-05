package com.zqf.food.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by v_zhaoqingfa on 2019/3/5.
 */

public class ToolUtil {
    public static final String KEY_LINK = ",";

    public static String getSelectKey(int shopId, int shopModelId) {
        if (shopId < 0) {
            return "";
        }
        if (shopModelId < 0) {
            return String.format("%s", shopId);
        }
        return String.format("%s" + KEY_LINK + "%s", shopId, shopModelId);
    }

    public static int[] splitSelectKey(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        if (key.contains(KEY_LINK)) {
            String[] split = key.split(KEY_LINK);
            if (split != null && split.length >= 2) {
                String s = split[0];
                String s1 = split[1];
                if (isNumeric(s) && isNumeric(s1)) {
                    return new int[] {Integer.parseInt(s), Integer.parseInt(s1)};
                }
            }
        } else {
            if (isNumeric(key)) {
                return new int[] {Integer.parseInt(key)};
            }
        }
        return null;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
