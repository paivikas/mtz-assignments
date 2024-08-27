package com.monetize360.service;

import org.apache.fop.apps.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class PdfGenService {
    public void createPdf() throws IOException, FOPException {
        File xmlFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\pdf-gen\\src\\main\\resources\\input.xml");
        File xsltFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\pdf-gen\\src\\main\\resources\\stylesheet.xsl");
        File pdfFile = new File("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\pdf-gen\\src\\main\\resources\\output.pdf");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        try (OutputStream out = new FileOutputStream(pdfFile)) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Source src = new StreamSource(xmlFile);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}