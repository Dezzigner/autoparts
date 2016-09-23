package service;

import com.d1l.controller.DocumentController;
import com.d1l.dao.DetailDao;
import com.d1l.dao.OrderDao;
import com.d1l.dao.UserDao;
import com.d1l.model.*;
import com.d1l.service.DocumentGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentServiceTest {

    DocumentGenerator documentGenerator;
    DocumentController documentController;

    @Before
    public void setUp() throws Exception {

        documentGenerator = new DocumentGenerator();
        documentController = new DocumentController();
        documentController.setId(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addWaterMark() throws Exception {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, stream);
        pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        document.open();
        documentGenerator.addWaterMark(pdfWriter);
    }

    @Test
    public void setCarsRow() throws Exception {
        documentGenerator.setCarsRow(new Car());
    }

    @Test
    public void setWarehousesRow() throws Exception {
        documentGenerator.setWarehousesRow(new Warehouse());
    }

    @Test
    public void setOrdersRow() throws Exception {
        documentGenerator.setOrdersRow(OrderDao.getOrderById(1));
    }

    @Test
    public void setDetailsRow() throws Exception {
        documentGenerator.setDetailsRow(DetailDao.getDetailById(1));
    }

    @Test
    public void setUsersRow() throws Exception {
        documentGenerator.setUsersRow(UserDao.getUserById(1));
    }

    @Test
    public void getUsersInPdf() throws Exception {
        documentGenerator.generateUsersInPDFById(1);
    }

    @Test
    public void getUsersInXls() throws Exception {
        documentGenerator.generateUsersInXLS();
    }

    @Test
    public void getUsersInCsv() throws Exception {
        documentGenerator.generateUsersInCSV();
    }

    @Test
    public void getCarsInXls() throws Exception {
        documentGenerator.generateCarsInXLS();
    }

    @Test
    public void getCarsInCsv() throws Exception {
        documentGenerator.generateCarsInCSV();
    }

    @Test
    public void getCarsInPdf() throws Exception {
        documentGenerator.generateCarsInPDFById(1);
    }

    @Test
    public void getWarehousesInXls() throws Exception {
        documentGenerator.generateWarehousesInXLS();
    }

    @Test
    public void getWarehousesInCsv() throws Exception {
        documentGenerator.generateWarehousesInCSV();
    }

    @Test
    public void getWarehousesInPdf() throws Exception {
        documentGenerator.generateWarehousesInPDFById(1);
    }

    @Test
    public void getOrdersInXls() throws Exception {
        documentGenerator.generateOrdersInXLS();
    }

    @Test
    public void getOrdersInCsv() throws Exception {
        documentGenerator.generateOrdersInCSV();
    }

    @Test
    public void getOrdersInPdf() throws Exception {
        documentGenerator.generateOrderInPDFById(1);
    }

    @Test
    public void getDetailsInXls() throws Exception {
        documentGenerator.generateDetailsInXLS();
    }

    @Test
    public void getDetailsInCsv() throws Exception {
        documentGenerator.generateDetailsInCSV();
    }

    @Test
    public void getDetailsInPdf() throws Exception {
        documentGenerator.generateDetailsInPDFById(1);
    }

    @Test
    public void getOrdersInCsvController() throws Exception {
        documentController.getOrdersCSV();
    }

    @Test
    public void getOrdersInPdfController() throws Exception {
        documentController.getOrderPDF();
    }

    @Test
    public void getOrdersInXlsController() throws Exception {
        documentController.getOrdersXLS();
    }

    @Test
    public void getDetailsInCsvController() throws Exception {
        documentController.getDetailsCSV();
    }

    @Test
    public void getDetailsInPdfController() throws Exception {
        documentController.getDetailPDF();
    }

    @Test
    public void getDetailsInXlsController() throws Exception {
         documentController.getDetailsXLS();
    }

    @Test
    public void getUsersInPdfController() throws Exception {
        documentController.getUserPDF();
    }

    @Test
    public void getUsersInXlsController() throws Exception {
        documentController.getUsersXLS();
    }

    @Test
    public void getUsersInCsvController() throws Exception {
        documentController.getUsersCSV();
    }

    @Test
    public void getCarsInPdfController() throws Exception {
        documentController.getCarPDF();
    }

    @Test
    public void getCarsInCsvController() throws Exception {
        documentController.getCarsCSV();
    }

    @Test
    public void getCarsInXlsController() throws Exception {
        documentController.getCarsXLS();
    }

    @Test
    public void getWarehousesInXlsController() throws Exception {
        documentController.getWarehousesXLS();
    }

    @Test
    public void getWarehousesInCsvController() throws Exception {
        documentController.getWarehousesCSV();
    }

    @Test
    public void getWarehousesInPdfController() throws Exception {
        documentController.getWarehousePDF();
    }

    @Test
    public void getWarehousesPdfAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getWarehousesXLS());
    }

    @Test
    public void getWarehousesCsvAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getWarehousesCSV());
    }

    @Test
    public void getWarehousesXlsAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getWarehousePDF());
    }

    @Test
    public void makeResponse() throws Exception {
        documentController.execute();
    }

}
