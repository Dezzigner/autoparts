package com.d1l.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.d1l.service.DocumentGenerator;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DocumentController extends ActionSupport implements ServletResponseAware {

    private HttpServletResponse response;
    private int id;

    private void makeResponse(ByteArrayOutputStream stream, String contentType, String fileName) throws IOException {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);
        response.setContentLength(stream.size());

        OutputStream os = response.getOutputStream();
        os.write(stream.toByteArray());
        os.flush();
        os.close();
        stream.reset();
    }
    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String getOrderPDF() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateOrderInPDFById(getId()), "application/pdf", "order.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getOrdersXLS() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateOrdersInXLS(), "application/vnd.ms-excel", "orders.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getOrdersCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateOrdersInCSV(), "text/csv", "orders.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getWarehousePDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateWarehousesInPDFById(getId()), "application/pdf", "warehouse.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getWarehousesXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateWarehousesInXLS(), "application/vnd.ms-excel", "warehouses.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getWarehousesCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateWarehousesInCSV(), "text/csv", "warehouses.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCarPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCarsInPDFById(getId()), "application/pdf", "car.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCarsXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCarsInXLS(), "application/vnd.ms-excel", "cars.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getCarsCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCarsInCSV(), "text/csv", "cars.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUserPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateUsersInPDFById(getId()), "application/pdf", "user.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUsersXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateUsersInXLS(), "application/vnd.ms-excel", "users.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getUsersCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateUsersInCSV(), "text/csv", "users.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getDetailPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateDetailsInPDFById(getId()), "application/pdf", "detail.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getDetailsXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateDetailsInXLS(), "application/vnd.ms-excel", "details.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getDetailsCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateDetailsInCSV(), "text/csv", "details.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
