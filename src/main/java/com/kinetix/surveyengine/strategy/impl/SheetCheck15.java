package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 18:48
 */
public class SheetCheck15 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j >= 9 && j <= 13) && c == 9) {
            return true;
        } else if (j == 17 && (c >= 4 && c <= 9)) {
            return true;
        } else if ((j >= 19 && j <= 27) && c == 9) {
            return true;
        } else if (j == 31 && (c >= 4 && c <= 9)) {
            return true;
        } else if (j == 32 && (c >= 4 && c <= 9)) {
            return true;
        } else {
            return false;
        }

    }
}
