package com.tumi.data.poi.callback;

import com.googlecode.easyec.sika.Grabber;
import com.googlecode.easyec.sika.WorkData;
import com.googlecode.easyec.sika.WorkbookRowCallback;
import com.googlecode.easyec.sika.WorkingException;

import java.util.ArrayList;
import java.util.List;

public class DefaultWorkbookCallback extends WorkbookRowCallback<List<WorkData>> {

    public DefaultWorkbookCallback(List<List<WorkData>> lists) {
        super(new Grabber<List<WorkData>>() {

            @Override
            public List<List<WorkData>> grab() throws WorkingException {
                return lists;
            }
        });
    }

    @Override
    public List<WorkData> populate(int i, List<WorkData> list) throws WorkingException {
        return list;
    }
}
