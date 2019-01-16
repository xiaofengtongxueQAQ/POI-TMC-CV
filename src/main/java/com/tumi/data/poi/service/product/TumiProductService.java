package com.tumi.data.poi.service.product;

import com.tumi.data.poi.domain.ProductWorkDataFile;
import com.tumi.data.poi.domain.impl.ProductWorkDataFileImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: JinPeng
 * @version: 2019/1/14 11:46 AM
 * @describe:
 */
public interface TumiProductService {

    List<ProductWorkDataFile> refactoring() throws Exception;

    ProductWorkDataFile checkCategories() throws Exception;
}
