package EE.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Random;



public class HandlerXML {
    public static void main(String ... args){
        validateXML();
      // createXML();
    }
    public static void validateXML(){
         File xml_input_file = new File("EE/DOM/myGroup.xml");
        //Анализ XML средствами DOM
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(true); // Настройка синтаксического анализатора на проверку  DTD
        builderFactory.setIgnoringElementContentWhitespace(true); //игнорирование разделителей в узлах
        MyErrorHandler myErrorHandler = new MyErrorHandler();
        try {
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            documentBuilder.setErrorHandler(myErrorHandler); //Устанавливает обработчик исключений для выдачи предупреждений и сообщений об
            //ошибках, возникающих при синтаксическом анализе XML-документов.
            Document domDocument = documentBuilder.parse(xml_input_file); // получили обект древовидной структуры DOM описывающей xml
            Element root_element = domDocument.getDocumentElement();
            NodeList student_list = root_element.getChildNodes();
            for(int i =0; i < student_list.getLength(); i++){
                check_average(student_list.item(i));

            }
            //сохранение
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"group.dtd");
            transformer.setOutputProperty(OutputKeys.INDENT,"false");


            DOMSource source = new DOMSource(domDocument);
            StreamResult result = new StreamResult(new FileOutputStream("EE/DOM/CheckedGroup.xml"));
            transformer.transform(source,result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void check_average(Node student){
        Element average_element = (Element) student.getLastChild();
        double averge = Double.parseDouble(average_element.getTextContent());
        NodeList marks = student.getChildNodes();
        double mark_buff =0;
        Element m =null;
        for(int i=0; i < marks.getLength(); i++){
            if(!marks.item(i).getNodeName().equals("average")){
              m = (Element) marks.item(i);
              mark_buff+=Double.parseDouble(m.getAttribute("mark"));
            }
        }
        if(averge!=mark_buff){
            student.getLastChild().setTextContent(Double.toString(mark_buff));
        }
    }
    public static void createXML(){
        File xml_input_file = new File("EE/DOM/group.dtd");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document new_xml_document = builderFactory.newDocumentBuilder().newDocument();

            //root
            Element group = new_xml_document.createElement("group");
            new_xml_document.appendChild(group);

            //student
            group.appendChild(createStud(new_xml_document,"test1","test1",444));
            group.appendChild(createStud(new_xml_document,"test2","test2",555));
            group.appendChild(createStud(new_xml_document,"test3","test3",1213));

            //сохранение
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"group.dtd");
            transformer.setOutputProperty(OutputKeys.INDENT,"false");

            DOMSource source = new DOMSource(new_xml_document);
            StreamResult result = new StreamResult(new FileOutputStream("EE/DOM/myGroup.xml"));
            transformer.transform(source,result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        //root element
    }
    public static Element createStud(Document document,String firstname, String lastname, int group_number){
        Element student = document.createElement("student");
        student.setAttribute("firstname", firstname);
        student.setAttribute("lastname", lastname);
        student.setAttribute("groupnumber", new Integer(group_number).toString());
        student.appendChild(createSubj(document,"Math",3));
        student.appendChild(createSubj(document,"Phyz",5));
        student.appendChild(createAvg(document,(3.0+5)/2));
        return student;
    }
    public static Element createSubj(Document document, String subject, int mark){
        Element subj = document.createElement("subject");
        subj.setAttribute("title",subject);
        subj.setAttribute("mark",new Integer(mark).toString());
        return subj;
    }
    public static Element createAvg(Document document, double avg){
        Element average = document.createElement("average");
        average.setTextContent(new Double(avg).toString());
        return average;
    }

}
