package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 19:03
 */
public class SheetCheck17 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 11 || j == 12 || j == 13 || j == 15 || j == 16) && c == 11) {
            return true;
        } else {
            return false;
        }
    }
}
