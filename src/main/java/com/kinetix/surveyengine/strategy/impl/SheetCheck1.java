package com.kinetix.surveyengine.strategy.impl;


import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 16:42
 */
public class SheetCheck1 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 0 && c == 0) {
            return true;
        } else {
            return false;
        }

    }
}
