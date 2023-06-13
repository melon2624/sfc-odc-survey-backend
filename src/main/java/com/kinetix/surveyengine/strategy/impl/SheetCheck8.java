package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:40
 */
public class SheetCheck8 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 39 && c == 4) {
            return true;
        } else {
            return false;
        }

    }
}
