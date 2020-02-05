package com.mycomp.controller;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

public class HttpRequestHelper {
    public static String getHttp(String suffix, String serverUri){
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<String> entity = template.exchange(URI.create(serverUri + suffix), HttpMethod.GET, httpEntity, String.class);
        return entity.getBody();
    }

    public static String postHttp(String suffix, Object object, String serverUri){
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(object, httpHeaders);
        ResponseEntity<String> entity = template.exchange(URI.create(serverUri + suffix), HttpMethod.POST, httpEntity, String.class);
        return entity.getStatusCode().toString();
    }

    public static String httpRequest(String suffix, HttpMethod method, Object object, HttpHeaders httpHeaders, String serverUri){
        RestTemplate template = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(object, httpHeaders);
        ResponseEntity<String> entity = template.exchange(URI.create(serverUri + suffix), method, httpEntity, String.class);
        return entity.getBody();
    }

    public static Document getDocument(String element) {
        Document document = null;
        try {
          /*  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader stringReader = new StringReader(element);
            InputSource is = new InputSource(stringReader);
            is.setEncoding("UTF-8");*/
            //return builder.parse(is);
           return document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(element.getBytes("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return document;
    }
}