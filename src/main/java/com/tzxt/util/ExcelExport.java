package com.tzxt.util;

import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class ExcelExport {

    /**
     * excel 信息导出
     *
     * @param sheetName    sheet 名称
     * @param titles       标题
     * @param data         数据内容
     * @param outputStream 导出文件流
     * @throws Exception
     */
    public static void doExport(String sheetName, List<String> titles, List<String> titleKeys, List<Map<String, Object>> data, OutputStream outputStream) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook(200);
        workbook.setCompressTempFiles(true);

        SXSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setRandomAccessWindowSize(200);

        // 导出title
        int titleStartIndex = 0;
        Row rowTite = sheet.createRow(titleStartIndex++);
        for (int i = 0; i < titleKeys.size(); i++) {
            Cell titleCell = rowTite.createCell(i);
            titleCell.setCellValue(titles.get(i));
            titleCell.setCellType(Cell.CELL_TYPE_STRING);
        }

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> rowData = data.get(i);
            List<Object> rowDataList = JsonMapper.JSON_NON_DEFAULT_MAPPER.fromJson(
                    JsonMapper.JSON_NON_DEFAULT_MAPPER.toJson(rowData.get("result")),
                    JsonMapper.JSON_NON_DEFAULT_MAPPER.createCollectionType(List.class, Object.class));
            Map<String, Object> rowDataSub = Maps.newHashMap();
            if (!isNull(rowDataList)) {
                rowDataSub = JsonMapper.JSON_NON_DEFAULT_MAPPER.fromJson(
                        JsonMapper.JSON_NON_DEFAULT_MAPPER.toJson(rowDataList.get(0)),
                        JsonMapper.JSON_NON_DEFAULT_MAPPER.createCollectionType(Map.class, String.class, String.class)
                );
            }
            Row rowContent = sheet.createRow(titleStartIndex++);
            for (int j = 0; j < titleKeys.size(); j++) {
                Cell cellContent = rowContent.createCell(j);
                cellContent.setCellType(Cell.CELL_TYPE_STRING);
                if (rowData.containsKey(titleKeys.get(j))) {
                    cellContent.setCellValue(String.valueOf(rowData.get(titleKeys.get(j))));
                } else {
                    cellContent.setCellValue(String.valueOf(rowDataSub.get(titleKeys.get(j))));
                }
            }
        }
        workbook.write(outputStream);
        workbook = null;
    }

}