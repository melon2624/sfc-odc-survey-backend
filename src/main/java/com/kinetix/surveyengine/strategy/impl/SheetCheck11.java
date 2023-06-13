package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 18:08
 */
public class SheetCheck11 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j == 14 || j == 21 || j == 62 || j == 77 || j == 79 || j == 100 || j == 103 || j == 108 || j == 114) && (c == 8 || c == 10 || c == 12)) {
            return true;
        } else if ((j == 136 || j == 151 || j == 161 || j == 170) && c == 12) {
            return true;
        } else {
            return false;
        }

    }
}
