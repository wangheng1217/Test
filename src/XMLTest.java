import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLTest {

    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws TransformerFactoryConfigurationError
     * @throws TransformerException
     * @throws IOException 
     */
    public static void main(String[] args) throws ParserConfigurationException,
            TransformerFactoryConfigurationError, TransformerException, IOException {
        
        String requestType = "ONSPDDCompleteComplete"; //Type (DesignAssignComplete | ONSPDDCompleteComplete) #REQUIRED
        String sysIdValue = "eWFM4.0"; //ID (eWFM4.0) #REQUIRED
        String sysKeyValue = "dummy";
        String seqNumValue = "0";
        
        String orderNumValue = "1385524";
        String versionValue = "1";
        String cdiOrderNumValue = "1385524";
        String suppType = "DDChange"; //Type (Change | DDChange | Cancel) #REQUIRED
        String imtIxcValue = "N";
        
        String productType = "TOCTransport";
        String switchedClli = "COLNNYEJ";
        String clli = "WSPF2500";
        String market = "Albany";
        String serviceType = "None";
        String activity = "New";
        
        String isOffnet = "Y";
        String prodInstIdValue = "1000236782";
        
        String objectId = "9134071994013981164";
        String circuitIdValue = "19/HCGS/169976/TWCS";
        String circuitUrlValue = "http://test";
        
        
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        Document doc = builder.newDocument();
        doc.setXmlStandalone(true);
        
        Element designComplete = doc.createElement("TWTCProductDesignComplete");
        doc.appendChild(designComplete);
        
        Element request = doc.createElement("Request");
        request.setAttribute("Type", requestType);
        designComplete.appendChild(request);
        
        Element xmlEnvelope = doc.createElement("TWTCXMLEnvelope");
        request.appendChild(xmlEnvelope);
        
        Element envelope = doc.createElement("Envelope");
        xmlEnvelope.appendChild(envelope);
        
        Element sysId = doc.createElement("RequestingSystemID");
        sysId.setAttribute("ID", sysIdValue);
        envelope.appendChild(sysId);
        
        Element sysKey = doc.createElement("RequestingSystemKey");
        sysKey.appendChild(doc.createTextNode(sysKeyValue));
        envelope.appendChild(sysKey);
        
        Element seqNum = doc.createElement("RequestAttemptSequenceNumber");
        seqNum.appendChild(doc.createTextNode(seqNumValue));
        envelope.appendChild(seqNum);
        
        Element correlationID = doc.createElement("CorrelationID");
        xmlEnvelope.appendChild(correlationID);
        
        Element orderNum = doc.createElement("OrderNumber");
        orderNum.appendChild(doc.createTextNode(orderNumValue));
        request.appendChild(orderNum);
        
        Element version = doc.createElement("Version");
        version.appendChild(doc.createTextNode(versionValue));
        request.appendChild(version);
        
        Element cdiOrderNum = doc.createElement("CDIOrderNumber");
        cdiOrderNum.appendChild(doc.createTextNode(cdiOrderNumValue));
        request.appendChild(cdiOrderNum);
        
        Element supp = doc.createElement("Supp");
        supp.setAttribute("Type", suppType);
        request.appendChild(supp);
        
        Element imtIxc = doc.createElement("IMT_IXC");
        imtIxc.appendChild(doc.createTextNode(imtIxcValue));
        request.appendChild(imtIxc);
        
        Element product = doc.createElement("Product");
        product.setAttribute("Type", productType);
        product.setAttribute("SwitchedCLLI", switchedClli);
        product.setAttribute("CLLI", clli);
        product.setAttribute("Market", market);
        product.setAttribute("ServiceType", serviceType);
        product.setAttribute("Activity", activity);
        request.appendChild(product);
        
        Element pidDetail = doc.createElement("PIDDetail");
        pidDetail.setAttribute("IsOffnet", isOffnet);
        product.appendChild(pidDetail);
        
        Element prodInstId = doc.createElement("ProdInstID");
        prodInstId.appendChild(doc.createTextNode(prodInstIdValue));
        pidDetail.appendChild(prodInstId);
        
        Element prodUniqueId = doc.createElement("ProductUniqueIdentifier");
        pidDetail.appendChild(prodUniqueId);
        
        Element onspOrderList = doc.createElement("ONSPOrderList");
        pidDetail.appendChild(onspOrderList);
        
        for(int i = 0; i < 2; i++){
            Element onspOrder = doc.createElement("ONSPOrder");
            onspOrderList.appendChild(onspOrder);
            
            String onspOrderNumValue = "S#207593";
            String lecOrderNumValue = "N77351425";
            String dateFormat = "MMDDCCYY";
            String offnetDueDateActualValue = "02202013";
            String offnetPtdValue = "02202013";
            String offnetCircuitIdValue = "28/HCGS/904132//SW";
            String zLocValue = "WSPF2500";
            String secLocValue = "WSPF2500";
            String onspVendorValue = "AT&T SOUTHWEST - HOUSTON (SW70)(9533)";
            String tsdsUserIdValue = "mjaquez";
            
            Element onspOrderNum = doc.createElement("ONSPOrderNumber");
            onspOrderNum.appendChild(doc.createTextNode(onspOrderNumValue));
            onspOrder.appendChild(onspOrderNum);
            
            Element lecOrderNum = doc.createElement("LECOrderNumber");
            lecOrderNum.appendChild(doc.createTextNode(lecOrderNumValue));
            onspOrder.appendChild(lecOrderNum);
            
            Element offnetDueDateActual = doc.createElement("OffnetDueDateActual");
            offnetDueDateActual.setAttribute("Format", dateFormat);
            offnetDueDateActual.appendChild(doc.createTextNode(offnetDueDateActualValue));
            onspOrder.appendChild(offnetDueDateActual);
            
            Element offnetPtd = doc.createElement("OffnetPTD");
            offnetPtd.setAttribute("Format", dateFormat);
            offnetPtd.appendChild(doc.createTextNode(offnetPtdValue));
            onspOrder.appendChild(offnetPtd);
            
            Element offnetCircuitId = doc.createElement("OffnetCircuitID");
            offnetCircuitId.appendChild(doc.createTextNode(offnetCircuitIdValue));
            onspOrder.appendChild(offnetCircuitId);
            
            Element zLoc = doc.createElement("ZLoc");
            zLoc.appendChild(doc.createTextNode(zLocValue));
            onspOrder.appendChild(zLoc);
            
            Element secLoc = doc.createElement("SECLoc");
            secLoc.appendChild(doc.createTextNode(secLocValue));
            onspOrder.appendChild(secLoc);
            
            Element onspVendor = doc.createElement("ONSPVendor");
            onspVendor.appendChild(doc.createTextNode(onspVendorValue));
            onspOrder.appendChild(onspVendor);
            
            Element tsdsUserId = doc.createElement("TSDSUserId");
            tsdsUserId.appendChild(doc.createTextNode(tsdsUserIdValue));
            onspOrder.appendChild(tsdsUserId);
            
        }
        
        Element circuitId = doc.createElement("CircuitID");
        circuitId.setAttribute("ObjectID", objectId);
        circuitId.appendChild(doc.createTextNode(circuitIdValue));
        pidDetail.appendChild(circuitId);
        
        Element circuitUrl = doc.createElement("CircuitTestNotesURL");
        circuitUrl.appendChild(doc.createTextNode(circuitUrlValue));
        pidDetail.appendChild(circuitUrl);


        // output DOM XML to console
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        
        writer.close();
        
        System.out.println(writer.toString());

    }
}
