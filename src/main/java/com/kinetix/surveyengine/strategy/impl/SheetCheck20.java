package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 19:08
 */
public class SheetCheck20 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 10 || j == 11) && c == 10) {
            return true;
        } else if (j == 15 && (c >= 5 && c <= 10)) {
            return true;
        } else if (j == 20 && c == 10) {
            return true;
        } else {
            return false;
        }

    }
}
