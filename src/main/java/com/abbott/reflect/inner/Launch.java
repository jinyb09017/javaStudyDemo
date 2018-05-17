package com.abbott.reflect.inner;

public class Launch {

    private Inner inner;

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    private static class Apk {

        public String pkgName;

    }


    private static class App {

        private String name;
        private Apk mApk;


    }


    public void launch() {

        App app = new App();
        app.name = "this is app";
        Apk apk = new Apk();
        apk.pkgName = "good man";
        app.mApk = apk;


        test(app);


    }


    public void test(App app){
        System.out.println("good");
        inner.onCreate(app);
    }
}
