package com.kinetix.surveyengine.strategy;

import java.util.ArrayList;

/**
 * @author zhangxin
 * @date 2023-03-29 9:54
 */
public class Context {


    public ArrayList<SheetCheck> arrayList = new ArrayList();


    public Context() {

        try {
            for (int i = 0; i < 22; i++) {
                int jjj = i + 1;
                String className = "SheetCheck" + jjj;
                Class c4 = Class.forName("com.zx.upload.strategy.impl." + className);
                SheetCheck sheetCheck = (SheetCheck) c4.newInstance();
                arrayList.add(sheetCheck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Boolean isSkip(int i, int j, int c) {
        if (i > arrayList.size()) {
            return false;
        } else {
            Boolean skip = arrayList.get(i).isSkip(j, c);

            return skip;
        }
    }


    public static void main(String[] args) {

        ArrayList<SheetCheck> arrayList = new ArrayList();

        for (int j = 0; j < arrayList.size(); j++) {
            Boolean skip = arrayList.get(j).isSkip(0, 0);

            System.out.println(skip);
        }


    }


}
