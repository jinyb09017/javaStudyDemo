package com.abbott.reflect.inner;

import java.lang.reflect.Field;

public class Inner extends Launch {

    public static void main(String[] args) {
        Launch launch = new Launch();
        Inner inner = new Inner();
        launch.setInner(inner);
        launch.launch();
    }


    public void onCreate(Object app) {
        System.out.println("haha");

        System.out.println(app);

        try {
            Field apkField = app.getClass().getDeclaredField("mApk");
            apkField.setAccessible(true);
            Object apkObject = apkField.get(app);
            System.out.println(apkObject);

            Field appName = app.getClass().getDeclaredField("name");
            appName.setAccessible(true);
            System.out.println(appName.get(app));


            Field pkgField = apkObject.getClass().getDeclaredField("pkgName");

            System.out.println(pkgField.get(apkObject));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
