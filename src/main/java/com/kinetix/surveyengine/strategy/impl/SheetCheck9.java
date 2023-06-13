package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:42
 */
public class SheetCheck9 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 1008 && c == 5) {
            return true;
        } else {
            return false;
        }

    }
}
