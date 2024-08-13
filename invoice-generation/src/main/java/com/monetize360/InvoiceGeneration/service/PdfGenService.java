package com.monetize360.InvoiceGeneration.service;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.monetize360.InvoiceGeneration.dto.InvoiceDto;
import org.apache.fop.apps.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PdfGenService {

    public void createPdf(){
        File xmlFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\invoice-generation\\src\\main\\resources\\invoice.xml");
        File xsltFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\invoice-generation\\src\\main\\resources\\stylesheet.xsl");
        File pdfFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\invoice-generation\\src\\main\\resources\\invoice.pdf");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        try (OutputStream out = new FileOutputStream(pdfFile)) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Source src = new StreamSource(xmlFile);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);
        } catch (TransformerException |IOException|FOPException e) {
            throw new RuntimeException(e);
        }
    }
    public  void saveXmlToFile(InvoiceDto invoice)  {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            OutputStream file = new FileOutputStream("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\invoice-generation\\src\\main\\resources\\invoice.xml");
            xmlMapper.writeValue(file, invoice);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
