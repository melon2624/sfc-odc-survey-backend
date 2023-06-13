package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:02
 */
public class SheetCheck3 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 99 && c == 7) {
            return true;
        } else if (j == 106 && c == 6) {
            return true;
        } else if (j == 112 && c == 6) {
            return true;
        } else if (j == 116 && c == 6) {
            return true;
        } else if (j == 119 && c == 6) {
            return true;
        } else if (j == 135 && c == 7) {
            return true;
        } else if (j == 161 && c == 7) {
            return true;
        } else if (j == 5189 && c == 7) {
            return true;
        } else {
            return false;
        }

    }
}
