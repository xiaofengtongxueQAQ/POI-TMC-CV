package com.tumi.data.poi.service.product.impl;

import com.googlecode.easyec.sika.WorkbookReader;
import com.googlecode.easyec.sika.ss.ExcelFactory;
import com.tumi.data.poi.config.PoiProperties;
import com.tumi.data.poi.domain.ProductWorkDataFile;
import com.tumi.data.poi.domain.impl.ProductWorkDataFileImpl;
import com.tumi.data.poi.handler.DefaultWorkbookHandler;
import com.tumi.data.poi.handler.PrdCategoryHandler;
import com.tumi.data.poi.service.product.TumiProductService;
import com.tumi.data.poi.service.scene7.Scene7ImageExtractorService;
import com.tumi.data.poi.utils.CategoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: JinPeng
 * @version: 2019/1/14 11:47 AM
 * @describe:
 */
@Service("tumiProxyService")
public class TumiProductServiceImpl implements TumiProductService {
    private static final Logger LOG = LoggerFactory.getLogger(TumiProductServiceImpl.class);

    @Resource
    private PoiProperties poiProperties;
    @Resource
    private Scene7ImageExtractorService scene7ImageExtractorService;


    @Override
    public List<ProductWorkDataFile> refactoring() throws Exception {
        ProductWorkDataFile baseData = this.checkCategories();
        if (null == baseData) {
            LOG.error("not find any data after check category");
            return null;
        }
        ProductWorkDataFile workDataFile = (ProductWorkDataFile) Proxy.newProxyInstance(
                baseData.getClass().getClassLoader(),
                baseData.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        ProductWorkDataFile invoke = (ProductWorkDataFile) method.invoke(baseData, args);

                        return invoke;
                    }
                });
        return this.scene7ImageExtractorService.syncImages(workDataFile);
    }

    @Override
    public ProductWorkDataFile checkCategories() throws Exception {
        ProductWorkDataFile workDataFile = new ProductWorkDataFileImpl(poiProperties.getProductFile());

        InputStream in1 = loadFromLocal(poiProperties.getCategoryFile());
        InputStream in2 = loadFromLocal(poiProperties.getProductFile());

        PrdCategoryHandler handler1 = new PrdCategoryHandler();
        WorkbookReader r1 = new WorkbookReader();
        r1.add(handler1);

        DefaultWorkbookHandler handler2 = new DefaultWorkbookHandler();
        WorkbookReader r2 = new WorkbookReader();
        r2.add(handler2);

        ExcelFactory.getInstance().read(in1, r1);
        ExcelFactory.getInstance().read(in2, r2);
        LOG.info("begin check product's category list");
        CategoryUtils.fill(
                handler2.getRecords(),
                handler1.getCategories(),
                poiProperties.getCategoryColumn()
        );
        LOG.info("category list check end...");
        workDataFile.setWorkData(handler2.getRecords());
        return workDataFile;
    }

    private InputStream loadFromLocal(String file) throws IOException {
        return new FileInputStream(file);
    }

}
