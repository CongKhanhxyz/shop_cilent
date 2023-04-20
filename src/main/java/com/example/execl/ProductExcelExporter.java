package com.example.execl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.DTO.ProductDto;
import com.example.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ProductExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductDto> listProducts;

    public ProductExcelExporter(List<ProductDto> listProducts) {
        this.listProducts = listProducts;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Products");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Product ID", style);
        createCell(row, 1, "Product Name", style);
        createCell(row, 2, "Old Price", style);
        createCell(row, 3, "Urlimage", style);
        createCell(row, 4, "Short description", style);
        createCell(row, 5, "Total Like", style);
        createCell(row, 6, "Sold amount", style);
        createCell(row, 7, "New Price", style);
        createCell(row, 8, "Review Amount", style);
        createCell(row, 9, "Percent Discount", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ProductDto product : listProducts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getId(), style);
            createCell(row, columnCount++, product.getProductName(), style);
            createCell(row, columnCount++, product.getOldPrice(), style);
            createCell(row, columnCount++, product.getUrlImage(), style);
            createCell(row, columnCount++, product.getShortDescription(), style);
            createCell(row, columnCount++, product.getStarCount(), style);
            createCell(row, columnCount++, product.getSoldAmount(), style);
            createCell(row, columnCount++, product.getNewPrice(), style);
            createCell(row, columnCount++, product.getReviewAmount(), style);
            createCell(row, columnCount++, product.getPercentDiscount(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
