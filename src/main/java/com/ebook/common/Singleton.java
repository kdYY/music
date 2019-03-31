package com.ebook.common;

import com.baidu.aip.nlp.AipNlp;

public class Singleton {

    //设置APPID/AK/SK
    private static final String APP_ID = "15827785";
    private static final String API_KEY = "C4cgnkzjVxcNOtbd0tQtChsD";
    private static final String SECRET_KEY = "qoTueGf0yMpNRMiB3c9eR9v98Ij1qP9t";

    private static final AipNlp nlp = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

    private static final String APP_ID_135 = "15831541";
    private static final String API_KEY_135 = "8UG3YZPi7RT3LXapIKi0zw93";
    private static final String SECRET_KEY_135 = "TzAqofqTupuiRxe5Qpg5vFAMaGqglULj";

    private static final AipNlp nlp_135 = new AipNlp(APP_ID_135, API_KEY_135, SECRET_KEY_135);

    public static AipNlp getNlpInstace() {
        return nlp;
    }

    public static AipNlp getNlp_135() { return nlp_135; }

}
