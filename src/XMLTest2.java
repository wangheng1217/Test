import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.twtc.nms.amof.jaxb.*;
import com.twtc.nms.ds.domain.OffnetCircuit;
import com.twtc.nms.ds.domain.OffnetOrderStatus;


public class XMLTest2 {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        System.out.println(buildPDC2Xml(null, null));
        System.out.println(buildPDC2Xml());

    }
    
    private static Marshaller createMarshaller() throws JAXBException {
        JAXBContext jaxbContext = 
            JAXBContext.newInstance("com.twtc.nms.amof.jaxb");

        return jaxbContext.createMarshaller();
    }
  
    private static String buildPDC2Xml(String orderNumber, String servicePiid)
            throws Exception {
        
//        OffnetOrderStatus offnetOrderStatus = new OffnetOrderStatus();//saoDAO.getOffnetOrderStatus(orderNumber, servicePiid);
//        List<OffnetCircuit> offnetCircuitList = new ArrayList<OffnetCircuit>();//saoDAO.getOffnetCircuits(orderNumber, servicePiid);
//        offnetCircuitList.add(new OffnetCircuit());

        String requestType = "ONSPDDCompleteComplete"; //Type (DesignAssignComplete | ONSPDDCompleteComplete) #REQUIRED
        String sysIdValue = "eWFM4.0"; //ID (eWFM4.0) #REQUIRED
        String sysKeyValue = "dummy";
        String seqNumValue = "0";
        
        String orderNumValue = "fake_data";//offnetOrderStatus.getOrderNumber();
        String versionValue = "fake_data";//offnetOrderStatus.getSupp();
        String cdiOrderNumValue = "fake_data";//orderNumValue; //TODO
        String suppType = "fake_data";//offnetOrderStatus.getSuppType(); //Type (Change | DDChange | Cancel) #REQUIRED
        String imtIxcValue = "N"; //default N
        
        String productType = "TOCTransport"; //TODO
        String switchedClli = "COLNNYEJ"; //TODO
        String clli = "WSPF2500"; //TODO
        String market = "Albany"; //TODO
        String serviceType = "None"; //TODO
        String activity = "fake_data";//offnetOrderStatus.getActivity();
        
        String isOffnet = "Y"; //default Y
        String prodInstIdValue = "fake_data";//offnetOrderStatus.getServicePiid();
        
        String objectId = "9134071994013981164"; //TODO
        String circuitIdValue = ""; //TODO
        //if (!offnetCircuitList.isEmpty())
            circuitIdValue = "fake_data";//offnetCircuitList.get(0).getCkr();
        String circuitUrlValue = "http://test"; //TODO

        
        
        TWTCProductDesignComplete dc = new TWTCProductDesignComplete();
        
        StringWriter stringWriter = new StringWriter();
        Marshaller jaxbMarshaller = createMarshaller();
        
        
        jaxbMarshaller.marshal(dc, stringWriter);
        //System.out.println(stringWriter.toString());

        
        
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
        
//        Iterator<OffnetCircuit> ite = offnetCircuitList.iterator();
//        while (ite.hasNext()) {
//            OffnetCircuit offnetCircuit = ite.next();
            
            Element onspOrder = doc.createElement("ONSPOrder");
            onspOrderList.appendChild(onspOrder);
            
            String onspOrderNumValue = "fake_data";//offnetOrderStatus.getPon();
            String lecOrderNumValue = "fake_data";//offnetCircuit.getOrd();
            String dateFormat = "MMDDCCYY";
            String offnetDueDateStr = "fake_data";//offnetOrderStatus.getOffnetDueDate();
            String offnetDueDateActualValue = offnetDueDateStr.replaceAll("/", "").substring(0, 8); //format 07/15/2013 00:00:00
            String offnetPtdStr = "fake_data";//offnetOrderStatus.getFocSupplierPtd();
            String offnetPtdValue = offnetPtdStr.substring(4, 8) + offnetPtdStr.substring(0, 4); //format 20130712
            String offnetCircuitIdValue = "fake_data";//offnetCircuit.getEcckt();
            String zLocValue = "WSPF2500"; //TODO
            String secLocValue = "WSPF2500"; //TODO
            String onspVendorValue = "fake_data";//offnetOrderStatus.getSupplier();
            String tsdsUserIdValue = "fake_data";//offnetOrderStatus.getUserId();
            
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
//        }
        
        Element circuitId = doc.createElement("CircuitID");
        circuitId.setAttribute("ObjectID", objectId);
        circuitId.appendChild(doc.createTextNode(circuitIdValue));
        pidDetail.appendChild(circuitId);
        
        Element circuitUrl = doc.createElement("CircuitTestNotesURL");
        circuitUrl.appendChild(doc.createTextNode(circuitUrlValue));
        pidDetail.appendChild(circuitUrl);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        
        writer.close();
        
        return writer.toString();
    }
    
    private static String buildPDC2Xml() throws Exception {
//      OffnetOrderStatus offnetOrderStatus = new OffnetOrderStatus();//saoDAO.getOffnetOrderStatus(orderNumber, servicePiid);
//      List<OffnetCircuit> offnetCircuitList = new ArrayList<OffnetCircuit>();//saoDAO.getOffnetCircuits(orderNumber, servicePiid);
//      offnetCircuitList.add(new OffnetCircuit());

      String requestType = "ONSPDDCompleteComplete"; //Type (DesignAssignComplete | ONSPDDCompleteComplete) #REQUIRED
      String sysIdValue = "eWFM4.0"; //ID (eWFM4.0) #REQUIRED
      String sysKeyValue = "dummy";
      String seqNumValue = "0";
      
      String orderNumValue = "fake_data";//offnetOrderStatus.getOrderNumber();
      String versionValue = "fake_data";//offnetOrderStatus.getSupp();
      String cdiOrderNumValue = "fake_data";//orderNumValue; //TODO
      String suppType = "fake_data";//offnetOrderStatus.getSuppType(); //Type (Change | DDChange | Cancel) #REQUIRED
      String imtIxcValue = "N"; //default N
      
      String productType = "TOCTransport"; //TODO
      String switchedClli = "COLNNYEJ"; //TODO
      String clli = "WSPF2500"; //TODO
      String market = "Albany"; //TODO
      String serviceType = "None"; //TODO
      String activity = "fake_data";//offnetOrderStatus.getActivity();
      
      String isOffnet = "Y"; //default Y
      String prodInstIdValue = "fake_data";//offnetOrderStatus.getServicePiid();
      
      String objectId = "9134071994013981164"; //TODO
      String circuitIdValue = ""; //TODO
      //if (!offnetCircuitList.isEmpty())
          circuitIdValue = "fake_data";//offnetCircuitList.get(0).getCkr();
      String circuitUrlValue = "http://test"; //TODO

      
      
      TWTCProductDesignComplete dc = new TWTCProductDesignComplete();
      
      StringWriter stringWriter = new StringWriter();
      Marshaller jaxbMarshaller = createMarshaller();
      
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      
      Request request = new Request();
      request.setType(requestType);
      dc.setRequest(request);
      
      TWTCXMLEnvelope xmlEnvelope = new TWTCXMLEnvelope();
      request.setTWTCXMLEnvelope(xmlEnvelope);
      
      Envelope envelope = new Envelope();
      RequestingSystemID sysId = new RequestingSystemID();
      sysId.setID(sysIdValue);
      envelope.setRequestingSystemID(sysId);
      envelope.setRequestingSystemKey(sysKeyValue);
      envelope.setRequestAttemptSequenceNumber(seqNumValue);
      xmlEnvelope.setEnvelope(envelope);
      
      request.setOrderNumber(orderNumValue);
      request.setVersion(versionValue);
      request.setCDIOrderNumber(cdiOrderNumValue);
      request.setSupp(new Supp());
      request.getSupp().setType(suppType);
      request.setIMTIXC(imtIxcValue);
      
      Product product = new Product();
      request.setProduct(product);
      
      product.setActivity(activity);
      product.setCLLI(clli);
      product.setMarket(market);
      product.setServiceType(serviceType);
      product.setSwitchedCLLI(switchedClli);
      product.setType(productType);
      
      PIDDetail pidDetail = new PIDDetail();
      product.getPIDDetail().add(pidDetail);
      
      pidDetail.setIsOffnet(isOffnet);
      pidDetail.setProdInstID(prodInstIdValue);
      
      ONSPOrderList onspOrderList = new ONSPOrderList();
      pidDetail.setONSPOrderList(onspOrderList);
 
      String onspOrderNumValue = "fake_data";//offnetOrderStatus.getPon();
      String lecOrderNumValue = "fake_data";//offnetCircuit.getOrd();
      String dateFormat = "MMDDCCYY";
      String offnetDueDateStr = "fake_data";//offnetOrderStatus.getOffnetDueDate();
      String offnetDueDateActualValue = offnetDueDateStr.replaceAll("/", "").substring(0, 8); //format 07/15/2013 00:00:00
      String offnetPtdStr = "fake_data";//offnetOrderStatus.getFocSupplierPtd();
      String offnetPtdValue = offnetPtdStr.substring(4, 8) + offnetPtdStr.substring(0, 4); //format 20130712
      String offnetCircuitIdValue = "fake_data";//offnetCircuit.getEcckt();
      String zLocValue = "WSPF2500"; //TODO
      String secLocValue = "WSPF2500"; //TODO
      String onspVendorValue = "fake_data";//offnetOrderStatus.getSupplier();
      String tsdsUserIdValue = "fake_data";//offnetOrderStatus.getUserId();

      ONSPOrder onspOrder = new ONSPOrder();
      onspOrderList.getONSPOrder().add(onspOrder);
      onspOrder.setONSPOrderNumber(onspOrderNumValue);
      onspOrder.setLECOrderNumber(lecOrderNumValue);
      OffnetDueDateActual dueDateActual = new OffnetDueDateActual();
      dueDateActual.setFormat(dateFormat);
      dueDateActual.setvalue(offnetDueDateActualValue);
      onspOrder.setOffnetDueDateActual(dueDateActual);
      OffnetPTD offnetPtd = new OffnetPTD();
      offnetPtd.setFormat(dateFormat);
      offnetPtd.setvalue(offnetPtdValue);
      onspOrder.setOffnetPTD(offnetPtd);
      onspOrder.setOffnetCircuitID(offnetCircuitIdValue);
      onspOrder.setZLoc(zLocValue);
      onspOrder.setSECLoc(secLocValue);
      onspOrder.setONSPVendor(onspVendorValue);
      onspOrder.setTSDSUserId(tsdsUserIdValue);
      
      CircuitID circuitId = new CircuitID();
      circuitId.setObjectID(objectId);
      circuitId.setvalue(circuitIdValue);
      pidDetail.setCircuitID(circuitId);
      
      pidDetail.setCircuitTestNotesURL(circuitUrlValue);     
      
      jaxbMarshaller.marshal(dc, stringWriter);
      return stringWriter.toString();

    }

}
