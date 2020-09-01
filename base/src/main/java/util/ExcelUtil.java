package util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import bean.AddEventBean;
import bean.LogManageTable;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 导出 Excel 表，暂时只能使用当个类型，还没办法解决多个类型
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/16 08 : 42
 */
public class ExcelUtil<T> {

    public final static String UTF_ENCODING = "UTF-8";
    public final static String GBK_ENCODING = "GBK";
    public static WritableFont writableFont_4 = null;
    public static WritableCellFormat writableCellFormat_4 = null;
    public static WritableFont writableFont_0 = null;
    public static WritableCellFormat writableCellFormat_0 = null;
    public static WritableFont writableFont_10 = null;
    public static WritableCellFormat writableCellFormat_10 = null;
    public static WritableFont writableFont_12 = null;
    public static WritableCellFormat writableCellFormat_12 = null;

    private ExcelUtilInterface<T> excelUtilInterface;

    public static void format() {

        try {

            writableFont_4 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            writableFont_4.setColour(Colour.LIGHT_GREEN);

            writableCellFormat_4 = new WritableCellFormat(writableFont_4);
            writableCellFormat_4.setAlignment(Alignment.CENTRE);
            writableCellFormat_4.setBorder(Border.ALL, BorderLineStyle.THIN);
            writableCellFormat_4.setBackground(Colour.VERY_LIGHT_YELLOW);

            writableFont_0 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            writableCellFormat_0 = new WritableCellFormat(writableFont_0);
            writableCellFormat_0.setAlignment(Alignment.CENTRE);
            writableCellFormat_0.setBorder(Border.ALL, BorderLineStyle.THIN);
            writableCellFormat_0.setBackground(Colour.LIGHT_GREEN);

            writableFont_12 = new WritableFont(WritableFont.ARIAL, 12);
            writableCellFormat_12 = new WritableCellFormat(writableFont_12);

            writableCellFormat_12.setBorder(Border.ALL, BorderLineStyle.THIN);

        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    public void initExcel(String fileName, String[] columnName, ExcelUtilInterface<T> utilInterface) {

        //保存数据获取的调用接口
        excelUtilInterface = utilInterface;

        format();

        WritableWorkbook writableWorkbook = null;

        try {

            File file = new File(fileName);
            if (!file.exists()) {
                file.delete();
                file.createNewFile();
            }
            writableWorkbook = Workbook.createWorkbook(file);
            WritableSheet sheet = writableWorkbook.createSheet("莱安-数据表格", 0);
            sheet.addCell((WritableCell) new Label(0, 0, fileName, writableCellFormat_4));

            for (int column = 0; column < columnName.length; column++) {
                sheet.addCell(new Label(column, 0, columnName[column], writableCellFormat_0));
            }

            writableWorkbook.write();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (writableWorkbook != null) {
                try {
                    writableWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public File writeObjectListToExcel(List<T> objList, String fileName, Context context) {

        File file = null;

        if (objList != null && objList.size() > 0) {
            WritableWorkbook writableWorkbook = null;
            InputStream inputStream = null;
            file = new File(fileName);

            if (!file.exists()) {
                try {
                    file.delete();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF_ENCODING);
                inputStream = new FileInputStream(file);
                Workbook workbook = Workbook.getWorkbook(inputStream);
                writableWorkbook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = null;

                sheet = writableWorkbook.getSheet(0);

                for (int index = 0; index < objList.size(); index++) {

//                    LogManageTable projectBean = (LogManageTable) objList.get(index);

                    //由调用者处理数据的获取，再将结果返回
                    List<String> list = excelUtilInterface.getExportData(objList.get(index));

//                    AddEventBean.SysLogBean.ListBean projectBean = (AddEventBean.SysLogBean.ListBean) objList.get(index);
//                    List<String> list = new ArrayList<>();
//
//                    list.add(projectBean.getUsername());
//                    list.add(projectBean.getCreateDate());
//                    list.add(projectBean.getOperationType());
//                    list.add(projectBean.getOperationName());
//                    list.add(projectBean.getIp());
//                    list.add(projectBean.getCreateTime());

                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, index + 1, list.get(i), writableCellFormat_12));

                        //由于后台值为空时会传回null，因此必须作判断，这里是做空格的宽度的
                        if (list.get(i) != null && list.get(i).length() <= 4) {
                            sheet.setColumnView(i, list.get(i).length() + 16);
                        } else {
                            //如果为空，直接设置 5
                            if (list.get(i) == null)
                                sheet.setColumnView(i, 5);
                            else
                                //否则就使用本身的长度 + 5
                                sheet.setColumnView(i, list.get(i).length() + 10);
                        }

                    }

                    sheet.setRowView(index + 1, 350);

                }

                writableWorkbook.write();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            } finally {
                if (writableWorkbook != null) {
                    try {
                        writableWorkbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                }

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

        return file;
    }

    public static Object getValueByRef(Class cls, String fieldName) {
        Object value = null;
        fieldName = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
        String getMethodName = "get" + fieldName;

        try {
            Method method = cls.getMethod(getMethodName);
            value = method.invoke(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

}
