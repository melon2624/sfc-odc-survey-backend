package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:31
 */
public class SheetCheck6 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {
        if (j == 59 && c == 8) {
            return true;
        } else if (j == 59 && c == 9) {
            return true;
        } else if (j == 59 && c == 14) {
            return true;
        } else if (j == 59 && c == 15) {
            return true;
        } else {
            return false;
        }

    }

}
