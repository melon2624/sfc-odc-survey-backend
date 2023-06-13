package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 16:44
 */
public class SheetCheck2 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 90 && c == 6) {
            return true;
        } else if (j == 96 && c == 7) {
            return true;
        } else {
            return false;
        }

    }
}
