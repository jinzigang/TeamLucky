package com.tmlk.framework.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by laiguoqiang on 15/5/17.
 */
public class InCondition implements ICondition {

    private String propertyName;
    private Object value;

    /**
     * 创建In实体
     *
     * @param propertyName java字段
     * @param value        List<Object>类型 约定 是 int 或者 long 类型
     */
    public InCondition(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    @Override
    public String generateExpression(Map<String, Object> params) {
        if (value != null) {
            List<Object> ids = (List<Object>) value;
            String inStr = StringUtils.join(ids.toArray(), ",");
            return propertyName + "  in ( " + inStr + " )";
        } else {
            return "";
        }
    }

}
