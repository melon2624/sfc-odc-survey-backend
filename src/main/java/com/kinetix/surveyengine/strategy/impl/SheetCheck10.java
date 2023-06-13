package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:43
 */
public class SheetCheck10 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j >= 7 && j <= 26) && c == 8) {
            return true;
        } else if ((j == 27 || j == 32) && (c >= 5 && c <= 7)) {
            return true;
        } else {
            return false;
        }

    }
}
