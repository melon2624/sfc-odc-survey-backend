package com.kinetix.surveyengine.strategy.impl;

import com.kinetix.surveyengine.strategy.SheetCheck;

/**
 * @author zhangxin
 * @date 2023-03-28 17:23
 */
public class SheetCheck5 implements SheetCheck {

    @Override
    public Boolean isSkip(int j, int c) {

        if (j == 44 && c == 11) {
            return true;
        } else if (j == 55 && c == 12) {
            return true;
        } else {
            return false;
        }

    }


}
