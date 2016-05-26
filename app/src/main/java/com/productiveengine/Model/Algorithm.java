package com.productiveengine.Model;

import com.orm.SugarRecord;

/**
 * Created by Nikolaos on 9/5/2015.
 */
public class Algorithm extends SugarRecord<Algorithm> {
    private String name;
    private int code;
    private String component;
    private String info;

    public Algorithm() {
    }

    public Algorithm(String name, byte code, String component, String info) {
        this.name = name;
        this.code = code;
        this.component = component;
        this.info = info;
    }

    public Algorithm(Algorithm algorithm) {
        this.name = algorithm.name;
        this.code = algorithm.code;
        this.component = algorithm.component;
        this.info = algorithm.info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
