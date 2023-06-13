package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 18:45
 */
public class SheetCheck14 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if ((j >= 15 && j <= 34) && c == 12) {
            return true;
        } else if (j == 35 && (c >= 4 && c <= 12)) {
            return true;
        } else {
            return false;
        }

    }
}
