package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 19:05
 */
public class SheetCheck18 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {
        if (j == 16 && (c == 4 || c == 6 || c == 8 || c == 10 || c == 12)) {
            return true;
        } else {
            return false;
        }
    }
}
