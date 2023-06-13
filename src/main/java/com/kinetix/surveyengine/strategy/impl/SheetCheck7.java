package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:36
 */
public class SheetCheck7 implements SheetCheck {
    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 1011 && c == 4) {
            return true;
        } else if (j == 1011 && c == 5) {
            return true;
        } else if (j == 1011 && c == 6) {
            return true;
        } else if (j == 1014 && c == 4) {
            return true;
        } else if (j == 1014 && c == 5) {
            return true;
        } else if (j == 1014 && c == 6) {
            return true;
        } else {
            return false;
        }

    }
}
