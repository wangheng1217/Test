import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTest3 {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        XMLTest3 test = new XMLTest3();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                XMLTest3.class.getResourceAsStream("EXCEPTION_RESPONSE.txt"),
                "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (String line = reader.readLine(); line != null; line = reader
                .readLine()) {
            sb.append(line);
        }

        String response = sb.toString();
        
        System.out.println(response);

        System.out.println(test.getOrderType(response));
    }

    private String getOrderType(String response) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(response
                .getBytes()));
        NodeList childNodes = doc.getFirstChild().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if ("header".equals(childNode.getNodeName())) {
                NodeList headerChildNodes = childNode.getChildNodes();

                for (int j = 0; j < headerChildNodes.getLength(); j++) {
                    Node headerChildNode = headerChildNodes.item(j);
                    if ("ordertype".equals(headerChildNode.getNodeName())) {
                        return headerChildNode.getFirstChild().getNodeValue();
                    }
                }

                break;
            }
        }

        return null;
    }
}
