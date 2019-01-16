package com.tumi.data.poi.utils;

import com.googlecode.easyec.sika.WorkData;
import com.googlecode.easyec.sika.converters.Object2StringConverter;
import com.googlecode.easyec.sika.mappings.ColumnEvaluatorFactory;
import com.googlecode.easyec.sika.mappings.UnknownColumnException;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: JinPeng
 * @version: 2019/1/16 2:21 PM
 * @describe:
 */
public class WorkDataUtils {

    public static WorkData getData(List<WorkData> list, String col) {
        try {
            return ColumnEvaluatorFactory.evaluateWorkData(list, col);
        } catch (UnknownColumnException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String getDataString(WorkData data) {
        String value = data.getValue(new Object2StringConverter());
        if (StringUtils.isBlank(value)) return null;
        return value;
    }

    public static List<String> getData(WorkData data) {
        String value = data.getValue(new Object2StringConverter());
        if (StringUtils.isBlank(value)) return emptyList();

        List<String> result = new ArrayList<>();
        String[] parts = value.split("\\s*,\\s*");
        for (String part : parts) {
            result.add(StringUtils.trim(part));
        }

        return result;
    }
}
