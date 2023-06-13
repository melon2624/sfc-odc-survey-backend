package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 18:36
 */
public class SheetCheck13 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 21 && (c == 9 || c == 10)) {
            return true;
        } else if (j == 35 && (c == 9 || c == 10)) {
            return true;
        } else if (j == 36 || c == 10) {
            return true;
        } else if (j == 38 || c == 9) {
            return true;
        } else {
            return false;
        }

    }
}
