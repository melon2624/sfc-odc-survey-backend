package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 19:01
 */
public class SheetCheck16 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 12 || j == 14 || j == 22 || j == 23) && c == 9) {
            return true;
        } else {
            return false;
        }

    }
}
