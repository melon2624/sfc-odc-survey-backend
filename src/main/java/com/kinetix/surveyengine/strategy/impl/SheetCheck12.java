package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 18:33
 */
public class SheetCheck12 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 17 || j == 41 || j == 50 || j == 60 || j == 63) && c == 8) {
            return true;
        } else {
            return false;
        }

    }
}
