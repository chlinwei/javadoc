package lw.pers.framework.bean;

import java.util.HashMap;
import java.util.Map;

public class ActionMapping {
    private String name;
    private String className;
    private String method;
    Map<String,Result> resultMap = new HashMap<>();
    public String getName() {
        return name;
    }

    public Map<String, Result> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Result> resultMap) {
        this.resultMap = resultMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
