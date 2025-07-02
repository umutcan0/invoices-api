package com.example.invoiceapi.service;

import com.example.invoiceapi.entity.InvoiceEntity;
import com.example.invoiceapi.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.Base64;
import java.io.ByteArrayInputStream;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void processInvoice(String base64xml) throws Exception {
        byte[] xmlBytes = Base64.getDecoder().decode(base64xml);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // Eğer namespace varsa

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlBytes));

        String nip = getTagValue(doc, "NIP");
        String p1 = getTagValue(doc, "P1");
        String p2 = getTagValue(doc, "P2");

        if (nip == null || p1 == null || p2 == null) {
            throw new IllegalArgumentException("Required fields (NIP, P1, P2) must be present in the XML");
        }

        InvoiceEntity entity = new InvoiceEntity();
        entity.setNip(nip);
        entity.setP1(p1);
        entity.setP2(p2);

        invoiceRepository.save(entity);
    }

    private String getTagValue(Document doc, String tagName) {
        Node node = doc.getElementsByTagName(tagName).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        return null;
    }
}
