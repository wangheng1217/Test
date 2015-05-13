import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Test2 {

    /**
     * @param args
     * @throws SAXException
     * @throws
     */
    public static void main(String[] args) throws Exception {
        SAXParserFactory pf = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser paser = pf.newSAXParser();
        org.xml.sax.XMLReader reader = paser.getXMLReader();
        Source xmlSource = new SAXSource(reader, new InputSource(new StringReader("<XML/>")));

    }

}
