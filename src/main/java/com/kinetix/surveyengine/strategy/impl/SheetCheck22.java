package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-29 9:38
 */
public class SheetCheck22 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 11 || j == 12 || j == 13) && c == 20) {
            return true;
        } else if (j == 14 && (c >= 15 && c <= 20)) {
            return true;
        } else if ((j == 15 || j == 16 || j == 18 || j == 19 || j == 20 || j == 21 || j == 30 || j == 38 || j == 39 || j == 40 || j == 41) && c == 20) {
            return true;
        } else if (j == 42 && (c >= 12 && c <= 20)) {
            return true;
        } else {
            return false;
        }

    }
}
