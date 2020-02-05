package com.mycomp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@RestController
public class XSLTController {
    private static final String SERVER_URI = "http://localhost:8080/lab4_war/rest/";//ссыль где получаем дааные от rest api

    private String xmlToString(Document document, String type) throws TransformerException { // СУКА ТУТ ССЫЛКА href имеет уже контекст (Начало URL)
        ProcessingInstruction i = document.createProcessingInstruction("xml-stylesheet", " href=\"resources/" + type + "\"" + " type=\"text/xsl\"");
        document.insertBefore(i, document.getDocumentElement());
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        return output;
    }

    @RequestMapping(value = "/xslt/doctors", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> doctorsPage() throws TransformerException {
        return watchEntities("doctors");
    }

    @RequestMapping(value = "/xslt/patients", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> watchLessons() throws TransformerException {
        return watchEntities("patients");
    }

    @RequestMapping(value = "/xslt/recipes", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> watchTeachers() throws TransformerException {
        return watchEntities("recipes");
    }

    private ResponseEntity<String> watchEntities(String type) throws TransformerException {
        String element = HttpRequestHelper.getHttp(type, SERVER_URI);
        Document document = HttpRequestHelper.getDocument(element);
       return new ResponseEntity<>(xmlToString(document, type + ".xslt"), HttpStatus.OK);

    }
}