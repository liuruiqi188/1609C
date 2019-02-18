package com.example.day0216;

import android.text.TextUtils;

/**
 * @author liuruiqi
 * @fileName Utils
 * @package com.example.zuoye0214
 * @date 2019/2/14 16:58
 **/
public class Utils {
    public static boolean isMobileNO(String mobileNum){
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNum))
            return false;
        else
            return mobileNum.matches(telRegex);
    }
}
