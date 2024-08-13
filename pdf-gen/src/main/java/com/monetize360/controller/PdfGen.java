package com.monetize360.controller;

import com.monetize360.service.PdfGenService;
import org.apache.fop.apps.FOPException;

import java.io.IOException;

public class PdfGen{
    public static void main(String[] args) {
        PdfGenService pdfService = new PdfGenService();

        try {
            pdfService.createPdf();
            System.out.println("PDF generated successfully.");
        } catch (IOException | FOPException e) {
            System.err.println("Error generating PDF: " + e.getMessage());
        }
    }
}


