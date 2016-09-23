package com.d1l.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.d1l.dao.*;
import com.d1l.model.*;
import org.apache.poi.hssf.usermodel.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DocumentGenerator {

    private static final Font FONT_FOR_OBJECT_NAME = FontFactory.getFont(FontFactory.HELVETICA, 20,
            Font.BOLD);
    private static final Font COMMON_FONT = FontFactory.getFont(FontFactory.HELVETICA, 20);
    private static final String[] VISIT_HEADER = {"Visit Number", "Complaints", "Diagnosys"};
    private static final String[] ORDER_HEADER = {"Doctor", "Begin Time"};
    private static final String[] TREATMENTS_HEADER = {"Treatment Number", "Prescription", "Cure", "Cure Count",
                                                        "Using Method"};
    private static final String[] ANALYSE_HEADER = {"Analyse Number", "Name", "Result"};
    private static final String[] EMPTY_ARRAY = {""};

    public static void addWaterMark(PdfWriter writer) {
        Phrase watermark = new Phrase("Autoparts", FontFactory.getFont(FontFactory.HELVETICA, 40,
                Font.BOLD, Color.LIGHT_GRAY));
        Rectangle pageSize = writer.getPageSize();
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, x, y, 45);
    }

    public static List<String> setOrdersRow(Order order ){
        List<String> ordersRow = new LinkedList<String>();
        List<OrderDetail> orderDetails = OrderDetailDao.getOrderDetailsByOrderId(order.getId());
        Customer customer = order.getCustomer();
        ordersRow.add(String.format("%d", order.getId()));

        ordersRow.add(String.format("%s ", customer.getFullname()));

        ordersRow.add(String.format("%s", order.getDate()));

        List<DetailReport> details = new ArrayList<DetailReport>();
        for (OrderDetail od : orderDetails) {
            Detail d = DetailDao.getDetailById(od.getDetailId());
            DetailReport report = new DetailReport();
            report.setCount(od.getCount());
            report.setDetail(d);
            details.add(report);
        }

        for (DetailReport d : details) {
            ordersRow.add(String.format("count: %s; name: %s, %s$ by %s; car: %s %s(release year); warehouse: %s, %s;", d.getCount(),
                    d.getDetail().getName(), d.getDetail().getPrice(),
                    d.getDetail().getSupplier().getCompanyName(), d.getDetail().getCar().getName(),
                    d.getDetail().getCar().getReleaseYear(), d.getDetail().getWarehouse().getName(),
                    d.getDetail().getWarehouse().getAddress()));

        }

        return ordersRow;
    }

    public static ByteArrayOutputStream generateOrderInPDFById(int id) {
        Document document = new Document(PageSize.A6, 20, 20, 20, 20);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Order order = OrderDao.getOrderById(id);
            List<String> ordersRow = setOrdersRow(order);
            Paragraph orderNumber = new Paragraph();
            orderNumber.add(new Chunk("Order #", FONT_FOR_OBJECT_NAME));
            orderNumber.add(new Chunk(ordersRow.get(0), COMMON_FONT));
            orderNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(orderNumber);
            document.add(Chunk.NEWLINE);
            Paragraph clientName = new Paragraph();
            clientName.add(new Chunk("Customer: ", FONT_FOR_OBJECT_NAME));
            clientName.add(new Chunk(ordersRow.get(1), COMMON_FONT));
            document.add(clientName);
            document.add(Chunk.NEWLINE);
            Paragraph time = new Paragraph();
            time.add(new Chunk("Begin time: ", FONT_FOR_OBJECT_NAME));
            time.add(new Chunk(ordersRow.get(2), COMMON_FONT));
            document.add(time);
            document.add(Chunk.NEWLINE);
            Paragraph details = new Paragraph();
            details.add(new Chunk("Details: ", FONT_FOR_OBJECT_NAME));
            document.add(details);
            document.add(Chunk.NEWLINE);
            for (int i = 3; i < ordersRow.size(); i++) {
                Paragraph detail = new Paragraph();
                detail.add(new Chunk("Detail #" + (i - 2) + ": ", FONT_FOR_OBJECT_NAME));
                detail.add(new Chunk(ordersRow.get(i), COMMON_FONT));
                document.add(detail);
                document.add(Chunk.NEWLINE);
            }

            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateOrdersInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("order");
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Order Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Customer"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Date"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Details"));

        sheet.autoSizeColumn(0);
        headerCellStyle.setWrapText(true);
        style.setWrapText(true);
        int[] columnWidths = {20, 20, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        List<Order> orderList = OrderDao.getOrdersList();
        for (int i = 0; i < orderList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ordersRow = setOrdersRow(orderList.get(i));
            for (int j = 0; j < 4; j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue;
                if (j != 3)
                    orderNumberCellValue = new HSSFRichTextString(ordersRow.get(j));
                else {
                    String result = "";
                    for (int k = 3; k < ordersRow.size(); k++) {
                        result += ordersRow.get(k) + "\n";
                    }
                    orderNumberCellValue = new HSSFRichTextString(result);
                }
                sheet.autoSizeColumn(j);
                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);
            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateOrdersInCSV() throws IOException {
        String[] fileHeader = {"Order Number", "Customer", "Date", "Details"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> ordersInString = new LinkedList<String[]>();
        List<Order> orderList = OrderDao.getOrdersList();
        for (int i = 0; i < orderList.size(); i++) {
            List<String> ordersRow = setOrdersRow(orderList.get(i));

            String result = "";
            for (int k = 3; k < ordersRow.size(); k++) {
                result += ordersRow.get(k) + "\n";
            }
            
            String[] tempArray = {ordersRow.get(0), ordersRow.get(1), ordersRow.get(2), result};
            ordersInString.add(tempArray);
        }

        writer.writeAll(ordersInString);
        writer.close();
        return stream;
    }

    public static List<String> setWarehousesRow(Warehouse warehouse) {
        List<String> warehousesRow = new LinkedList<String>();
        warehousesRow.add(String.format("%d", warehouse.getId()));

        warehousesRow.add(String.format("%s ", warehouse.getName()));

        warehousesRow.add(String.format("%s ", warehouse.getAddress()));

        return warehousesRow;
    }


    public static ByteArrayOutputStream generateWarehousesInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Warehouse warehouse = WarehouseDao.getWarehouseById(id);
            List<String> warehousesRow = setWarehousesRow(warehouse);

            Paragraph warehouseNumber = new Paragraph();
            warehouseNumber.add(new Chunk("Warehouse #", FONT_FOR_OBJECT_NAME));
            warehouseNumber.add(new Chunk(warehousesRow.get(0), COMMON_FONT));
            warehouseNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(warehouseNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(warehousesRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph address = new Paragraph();
            address.add(new Chunk("Address: ", FONT_FOR_OBJECT_NAME));
            address.add(new Chunk(warehousesRow.get(2), COMMON_FONT));
            document.add(address);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateWarehousesInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("warehouse");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Warehouse Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Address"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Warehouse> warehouseList = WarehouseDao.getWarehousesList();
        for (int i = 0; i < warehouseList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ordersRow = setWarehousesRow(warehouseList.get(i));
            for (int j = 0; j < ordersRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue = new HSSFRichTextString(ordersRow.get(j));

                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateWarehousesInCSV() throws IOException {
        String[] fileHeader = {"Warehouse Number", "Name", "Address"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> warehousesInString = new LinkedList<String[]>();
        List<Warehouse> warehouseList = WarehouseDao.getWarehousesList();
        for (int i = 0; i < warehouseList.size(); i++) {
            List<String> analysesRow = setWarehousesRow(warehouseList.get(i));
            String[] tempArray = {analysesRow.get(0), analysesRow.get(1), analysesRow.get(2)};
            warehousesInString.add(tempArray);
        }

        writer.writeAll(warehousesInString);
        writer.close();
        return stream;
    }

    public static List<String> setCarsRow(Car car) {
        List<String> carsRow = new LinkedList<String>();
        carsRow.add(String.format("%d", car.getId()));

        carsRow.add(String.format("%s ", car.getName()));

        carsRow.add(String.format("%s ", car.getReleaseYear()));

        return carsRow;
    }


    public static ByteArrayOutputStream generateCarsInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Car car = CarDao.getCarById(id);
            List<String> carsRow = setCarsRow(car);

            Paragraph carNumber = new Paragraph();
            carNumber.add(new Chunk("Car #", FONT_FOR_OBJECT_NAME));
            carNumber.add(new Chunk(carsRow.get(0), COMMON_FONT));
            carNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(carNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(carsRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph address = new Paragraph();
            address.add(new Chunk("Release Year: ", FONT_FOR_OBJECT_NAME));
            address.add(new Chunk(carsRow.get(2), COMMON_FONT));
            document.add(address);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateCarsInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("car");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Car Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Release Year"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Car> carList = CarDao.getCarsList();
        for (int i = 0; i < carList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> carsRow = setCarsRow(carList.get(i));
            for (int j = 0; j < carsRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue = new HSSFRichTextString(carsRow.get(j));

                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateCarsInCSV() throws IOException {
        String[] fileHeader = {"Car Number", "Name", "Release Year"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> carsInString = new LinkedList<String[]>();
        List<Car> carList = CarDao.getCarsList();
        for (int i = 0; i < carList.size(); i++) {
            List<String> carsRow = setCarsRow(carList.get(i));
            String[] tempArray = {carsRow.get(0), carsRow.get(1), carsRow.get(2)};
            carsInString.add(tempArray);
        }

        writer.writeAll(carsInString);
        writer.close();
        return stream;
    }

    public static List<String> setDetailsRow(Detail detail) {
        List<String> detailsRow = new LinkedList<String>();
        detailsRow.add(String.format("%d", detail.getId()));

        detailsRow.add(String.format("%s ", detail.getName()));

        detailsRow.add(String.format("%s ", detail.getSupplier().getCompanyName()));

        detailsRow.add(String.format("%s ", detail.getCar().getName()));

        detailsRow.add(String.format("%s ", detail.getCar().getReleaseYear()));

        detailsRow.add(String.format("%s ", detail.getWarehouse().getName()));

        detailsRow.add(String.format("%s ", detail.getWarehouse().getAddress()));

        detailsRow.add(String.format("%s ", detail.getPrice()));

        return detailsRow;
    }


    public static ByteArrayOutputStream generateDetailsInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Detail detail = DetailDao.getDetailById(id);
            List<String> detailsRow = setDetailsRow(detail);

            Paragraph detailNumber = new Paragraph();
            detailNumber.add(new Chunk("Detail #", FONT_FOR_OBJECT_NAME));
            detailNumber.add(new Chunk(detailsRow.get(0), COMMON_FONT));
            detailNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(detailNumber);
            document.add(Chunk.NEWLINE);

            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(detailsRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);

            Paragraph supplierName = new Paragraph();
            supplierName.add(new Chunk("Supplier name: ", FONT_FOR_OBJECT_NAME));
            supplierName.add(new Chunk(detailsRow.get(2), COMMON_FONT));
            document.add(supplierName);
            document.add(Chunk.NEWLINE);

            Paragraph carName = new Paragraph();
            carName.add(new Chunk("Car name: ", FONT_FOR_OBJECT_NAME));
            carName.add(new Chunk(detailsRow.get(3), COMMON_FONT));
            document.add(carName);
            document.add(Chunk.NEWLINE);

            Paragraph carYear = new Paragraph();
            carYear.add(new Chunk("Car year: ", FONT_FOR_OBJECT_NAME));
            carYear.add(new Chunk(detailsRow.get(4), COMMON_FONT));
            document.add(carYear);
            document.add(Chunk.NEWLINE);

            Paragraph warehouseName = new Paragraph();
            warehouseName.add(new Chunk("Warehouse name: ", FONT_FOR_OBJECT_NAME));
            warehouseName.add(new Chunk(detailsRow.get(5), COMMON_FONT));
            document.add(warehouseName);
            document.add(Chunk.NEWLINE);

            Paragraph warehouseAddress = new Paragraph();
            warehouseAddress.add(new Chunk("Warehouse address: ", FONT_FOR_OBJECT_NAME));
            warehouseAddress.add(new Chunk(detailsRow.get(6), COMMON_FONT));
            document.add(warehouseAddress);
            document.add(Chunk.NEWLINE);

            Paragraph price = new Paragraph();
            price.add(new Chunk("Price: ", FONT_FOR_OBJECT_NAME));
            price.add(new Chunk(detailsRow.get(7), COMMON_FONT));
            document.add(price);
            document.add(Chunk.NEWLINE);

            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateDetailsInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("detail");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Detail Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Supplier name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Car name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Car year"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Warehouse name"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Warehouse address"));
        cell = row.createCell(7);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Price"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Detail> detailList = DetailDao.getDetailsList();
        for (int i = 0; i < detailList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> detailsRow = setDetailsRow(detailList.get(i));
            for (int j = 0; j < detailsRow.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString detailNumberCellValue = new HSSFRichTextString(detailsRow.get(j));

                cell.setCellValue(detailNumberCellValue);
                sheet.autoSizeColumn(j);

                //sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateDetailsInCSV() throws IOException {
        String[] fileHeader = {"Detail Number", "Name", "Supplier name", "Car name", "Car year", "Warehouse name", "Warehouse year", "Price"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> detailsInString = new LinkedList<String[]>();
        List<Detail> detailsList = DetailDao.getDetailsList();
        for (int i = 0; i < detailsList.size(); i++) {
            List<String> detailsRow = setDetailsRow(detailsList.get(i));
            String[] tempArray = {detailsRow.get(0), detailsRow.get(1), detailsRow.get(2), detailsRow.get(3),
                    detailsRow.get(4), detailsRow.get(5), detailsRow.get(6), detailsRow.get(7)};
            detailsInString.add(tempArray);
        }

        writer.writeAll(detailsInString);
        writer.close();
        return stream;
    }

    public static List<String> setUsersRow(User user) {
        List<String> usersRow = new LinkedList<String>();
        usersRow.add(String.format("%d", user.getId()));

        usersRow.add(String.format("%s ", user.getLogin()));

        usersRow.add(String.format("%s ", user.getRole().getName()));

        return usersRow;
    }

    public static ByteArrayOutputStream generateUsersInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            User user = UserDao.getUserById(id);
            List<String> usersRow = setUsersRow(user);

            Paragraph userNumber = new Paragraph();
            userNumber.add(new Chunk("User #", FONT_FOR_OBJECT_NAME));
            userNumber.add(new Chunk(usersRow.get(0), COMMON_FONT));
            userNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(userNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Login: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(usersRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph role = new Paragraph();
            role.add(new Chunk("Role: ", FONT_FOR_OBJECT_NAME));
            role.add(new Chunk(usersRow.get(2), COMMON_FONT));
            document.add(role);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateUsersInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("user");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("User Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Login"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Role"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<User> userList = UserDao.getUsersList();
        for (int i = 0; i < userList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> usersRow = setUsersRow(userList.get(i));
            for (int j = 0; j < usersRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString userNumberCellValue = new HSSFRichTextString(usersRow.get(j));

                cell.setCellValue(userNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateUsersInCSV() throws IOException {
        String[] fileHeader = {"User Number", "Login", "Role"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> usersInString = new LinkedList<String[]>();
        List<User> userList = UserDao.getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            List<String> usersRow = setUsersRow(userList.get(i));
            String[] tempArray = {usersRow.get(0), usersRow.get(1), usersRow.get(2)};
            usersInString.add(tempArray);
        }

        writer.writeAll(usersInString);
        writer.close();
        return stream;
    }

}
