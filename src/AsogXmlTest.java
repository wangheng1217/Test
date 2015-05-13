import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import asog.notification.Xml;


public class AsogXmlTest {
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Xml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File("C:\\Users\\hwan4805\\Desktop\\test.xml");
        Object object = unmarshaller.unmarshal(file);
        System.out.println(object instanceof JAXBElement<?>);
    }
}
