package util;

import java.util.List;

/**
 * @ClassName: ExcelUtilInterface
 * @Description: 导出Excel工具类的接口
 * @Author: YIN LUO FEI
 * @Date: 2020/8/29 17:35
 */
public interface ExcelUtilInterface<T> {

    List<String> getExportData(T excelData);

}
