package com.tumi.data.poi.handler;

import com.googlecode.easyec.sika.WorkData;
import com.googlecode.easyec.sika.WorkbookRowHandler;
import com.googlecode.easyec.sika.WorkingException;
import com.googlecode.easyec.sika.converters.Object2StringConverter;
import com.googlecode.easyec.sika.mappings.UnknownColumnException;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.googlecode.easyec.sika.event.WorkbookBlankRowListener.DEFAULT;
import static com.googlecode.easyec.sika.mappings.ColumnEvaluatorFactory.evaluateWorkData;

public class PrdCategoryHandler extends WorkbookRowHandler {

    private Set<String> categories = new HashSet<>();

    @Override
    public void doInit() throws WorkingException {
        setBlankRowListener(DEFAULT);
    }

    @Override
    public boolean populate(int i, List<WorkData> list) throws WorkingException {
        String code = getCategory(list);
        if (StringUtils.isNotBlank(code)) {
            categories.add(code);
        }

        return true;
    }

    public Set<String> getCategories() {
        return categories;
    }
//test
    private String getCategory(List<WorkData> list) throws UnknownColumnException {
        String val = evaluateWorkData(list, "A").getValue(new Object2StringConverter());
        if (val != null) val = StringUtils.trim(val);
        return val;
    }
}
