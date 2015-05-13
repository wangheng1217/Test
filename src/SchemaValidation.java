import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SchemaValidation {
    public static void main(String[] arsgs) throws ParserConfigurationException, SAXException, IOException, SQLException {

        String osaUrl = "jdbc:oracle:thin:@//ossrac-scan.twtelecom.com:1521/SAO_PRD";
        String osaUser = "osa_twtc";
        String osaPassword = "osa#7Wtc";
        Connection osaConn = DriverManager.getConnection(osaUrl, osaUser, osaPassword);

        String osaQuery = "select id, pon, ver, message from wisor_messages where message_type = 'ORDER_REQUEST' and message_dt > (sysdate-10) order by message_dt desc";
        
        ResultSet rs = osaConn.prepareStatement(osaQuery).executeQuery();
        
        String id, pon, ver, message;
        
        
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        Document document = parser.parse(new File("C:\\Users\\hwan4805\\Desktop\\ORDER_REQUEST.xml"));

        // create a SchemaFactory capable of understanding WXS schemas
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // load a WXS schema, represented by a Schema instance
        String schemaFileName = "C:\\TWProject\\OSA\\jaxb-gen\\jaxb-gen-asog48\\xsds\\SNCR_ASR48.xsd";
//        String schemaFileName = "C:\\TWProject\\OSA\\jaxb-gen\\jaxb-gen-asog47\\xsds\\SNCR_ASR47.xsd";
//        String schemaFileName = "C:\\TWProject\\OSA\\jaxb-gen\\jaxb-gen-asog47\\xsds\\UOM-ASR_47.xsd";

        Source schemaFile = new StreamSource(new File(schemaFileName));
        Schema schema = factory.newSchema(schemaFile);

        // create a Validator instance, which can be used to validate an
        // instance document
        Validator validator = schema.newValidator();

        validator.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("WARN:\n" + exception.getMessage());
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("ERROR:\n" + exception.getMessage());
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("FATAL:\n" + exception.getMessage());
                throw exception;
            }
        });
        
        Set<String> set = new HashSet<String>();

        
        while(rs.next()) {
            id = rs.getString(1);
            pon = rs.getString(2);
            ver = rs.getString(3);
            message = rs.getString(4);
            
            InputStream stream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
            
            Document document = parser.parse(stream);

            // validate the DOM tree
            
            try {
                validator.validate(new DOMSource(document));
            } catch (Exception e) {
                System.out.println(id + "\t" + pon + "\t" + ver);
                set.add(pon);
            }
        }
        
        System.out.println(set.size());
        
    }
}
