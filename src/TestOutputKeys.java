import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class TestOutputKeys {
    public static void main(String[] args) throws TransformerException {

        // Instantiate transformer input
        Source xmlInput = new StreamSource(new StringReader(
                "<!-- Document comment --><aaa><bbb/><ccc/></aaa>"));
        StreamResult xmlOutput = new StreamResult(new StringWriter());

        // Configure transformer
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(); // An identity transformer
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "testing.dtd");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlInput, xmlOutput);

        System.out.println(xmlOutput.getWriter().toString());
    }

}