import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;


public class AddressValidation {

    /**
     * @param args
     * @throws SQLException 
     */
	public static void main(String[] args) throws Exception {

		String fileName = "C:\\Desktop\\OSA notes\\silent failure tracking\\total0702_Ver2.txt";

		List<String> list = new ArrayList<String>();
		BufferedReader msgFileRdr = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = msgFileRdr.readLine()) != null) {
			list.add(line);
		}
		msgFileRdr.close();

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
//		String url1 = "http://nmsprdlb.twtelecom.com:8080/nmsis/is";
		String url1 = "http://nmsprdlb.twtelecom.com:8080/nmsds/dataservice";

		for (String functionId : list) {
			SOAPMessage soapResponse = soapConnection.call(
					createSOAPRequest(functionId), url1);

			String resp = printSOAPResponse(soapResponse);
			
//			boolean isValid = resp.contains("<orderNumber>") && resp.contains("<piid>");
//
//			System.out.println(functionId + "\t" + isValid);
			
//			boolean isDBError = resp.contains("ORA-01427");
//			
//			System.out.println(functionId + "\t" + isDBError);
			
            boolean hasActl = resp.contains("<actl>");

            System.out.println(functionId + "\t" + hasActl);

		}

		soapConnection.close();

	}

    public static void checkRSI(long rsiId) {
        
    }
    
    private static SOAPMessage createSOAPRequest(String functionId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://manager.ds.nms.twtc.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("man", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        SOAPElement soapBodyElem = soapBody.addChildElement("sendPDC2", "man");
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("functionId");
//        soapBodyElem1.addTextNode(functionId);
        
//        SOAPBody soapBody = envelope.getBody();
//        SOAPElement soapBodyElem = soapBody.addChildElement("getOsoServiceInfo", "man");
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("osoObjectId");
//        soapBodyElem1.addTextNode(functionId);
        
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getOsoSummaryInfo", "man");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("osoObjectId");
        soapBodyElem1.addTextNode(functionId);


//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

        soapMessage.saveChanges();

        /* Print the request message */
        //System.out.print("Request SOAP Message = ");
        //soapMessage.writeTo(System.out);
        //System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static String printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        //System.out.print("\nResponse SOAP Message = ");
        
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        transformer.transform(sourceContent, result);
        
        writer.close();
        
        String s = writer.toString();
        
        return s;
    }

}
