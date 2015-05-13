import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: handle MODIFY
public class SilentFailureTracking {

	private static String fileName = "C:\\Desktop\\OSA notes\\silent failure tracking\\sum_2014-09-02_offnet_order.log";
//	private static String fileName = "C:\\Desktop\\OSA notes\\silent failure tracking\\total0709";
    
    private static Pattern idPattern = Pattern.compile("offnet_service_osa_oid=(\\d+)");

    public static void main(String[] args) throws IOException, SQLException {
        Set<String> idSet = readId();

        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        String saoUrl = "jdbc:oracle:thin:@//ossrac-scan.twtelecom.com:1521/SAO_PRD";
        String saoUser = "sao_twtc";
        String saoPassword = "sao#7Wtc";
        Connection saoConn = DriverManager.getConnection(saoUrl, saoUser, saoPassword);

        String saoQuery = "select comp.offnet_component_rsi_id "
                + "from sao_offnet_component comp, sao_offnet_service ser, sao_process p, sao_flow flow, sao_function func "
                + "where comp.offnet_service_id = ser.offnet_service_id and ser.process_id = p.process_id "
                + "and flow.process_id = p.process_id and flow.model_id in (?, ?) "
                + "and func.flow_id = flow.flow_id and func.model_id in (?, ?) "
                + "and comp.offnet_component_rsi_id = ?";
        PreparedStatement saoSt = saoConn.prepareStatement(saoQuery);
        ResultSet saoRs = null;

        String rsiUrl = "jdbc:oracle:thin:@//dbsrvrsi.twtelecom.com:1521/nolb_rsidb";
        String rsiUser = "nc_dev";
        String rsiPassword = "upgr8d3pw";
        Connection rsiConn = DriverManager.getConnection(rsiUrl, rsiUser, rsiPassword);

        String rsiQuery = "select o.name, o.object_id, lv1.value, lv2.value, p3.value"
                + " from nc_objects o left join nc_params p3 on p3.object_id = o.object_id and p3.attr_id = 9136474654513335826,"
        		+ " nc_params p1, nc_params p2, nc_list_values lv1, nc_list_values lv2"
                + " where o.object_id = ?"
                + " and p1.object_id = o.object_id and p1.attr_id = 9132483320113508738 and p1.list_value_id = lv1.list_value_id"
                + " and p2.object_id = o.object_id and p2.attr_id = 9132502044613514020 and p2.list_value_id = lv2.list_value_id";
        PreparedStatement rsiSt = rsiConn.prepareStatement(rsiQuery);
        ResultSet rsiRs = null;
        
        PreparedStatement rsiSt2 = rsiConn.prepareStatement(rsiQuery2);
        ResultSet rsiRs2 = null;
        
        String rsiQuery3 = "select p.value from NC_DEV.NC_PARAMS p where p.object_id = ? and p.attr_id = 3102354323013133596";
        PreparedStatement rsiSt3 = rsiConn.prepareStatement(rsiQuery3);
        ResultSet rsiRs3 = null;
        
//        System.out.println(idSet.size());

        Iterator<String> ite = idSet.iterator();
        while (ite.hasNext()) {
            String id = ite.next();
            rsiSt.setLong(1, Long.valueOf(id));
            rsiRs = rsiSt.executeQuery();
            if (rsiRs.next()) {
                if ("Cancelled".equals(rsiRs.getString(3))
                        || ("Active".equals(rsiRs.getString(3)) && "None".equals(rsiRs.getString(4)))) {
                    // Cancelled, ignore
                    continue;
                } else if ("Disconnect".equals(rsiRs.getString(4))) {
                    // Disconnect
//                    saoSt.setInt(1, 2008);//2010
//                    saoSt.setInt(2, 3052);//3068
					saoSt.setInt(1, 2008);
					saoSt.setInt(2, 2010);
					saoSt.setInt(3, 3052);
					saoSt.setInt(4, 3068);
                } else {
                    // New
//                    saoSt.setInt(1, 2003);
//                    saoSt.setInt(2, 3025);
					saoSt.setInt(1, 2003);
					saoSt.setInt(2, 2009);
					saoSt.setInt(3, 3025);
					saoSt.setInt(4, 3064);
                }

                saoSt.setString(5, id);
                saoRs = saoSt.executeQuery();
                if (!saoRs.next()) {
                    
                    // get order number;
                    
                    String orderNumber = null;
                    
                    rsiSt2.setLong(1, Long.valueOf(id));
                    rsiSt2.setLong(2, Long.valueOf(id));
                    
                    rsiRs2 = rsiSt2.executeQuery();
                    if (rsiRs2.next()) {
                        long serObId = rsiRs2.getLong(3);
                        
                        rsiSt3.setLong(1, serObId);
                        
                        rsiRs3 = rsiSt3.executeQuery();
                        
                        if (rsiRs3.next()) {
                            orderNumber = rsiRs3.getString(1);
                        }
                    }
                    
                    System.out.println(rsiRs.getString(1) + "\t" + rsiRs.getString(2) + "\t" + rsiRs.getString(3)
                            + "\t" + rsiRs.getString(4) + "\t" + orderNumber);
//                    System.out.println(rsiRs.getString(2));
                }
            }
        }

        if (saoRs != null)
            saoRs.close();
        if (rsiRs != null)
            rsiRs.close();
        saoSt.close();
        saoConn.close();
        rsiSt.close();
        rsiConn.close();
    }

	private static Set<String> readId() throws IOException {
		Set<String> set = new TreeSet<String>();
		BufferedReader msgFileRdr = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = msgFileRdr.readLine()) != null) {
			Matcher matcher = idPattern.matcher(line);
			if (matcher.find()) {
				set.add(matcher.group(1));
			}
		    
//		    set.add(line);
		}
		msgFileRdr.close();
		return set;
	}
	
    private static String rsiQuery2 = "( "
            + "                                             SELECT distinct osa.NAME AS OSA "
            + "                                             , cir.NAME AS PAR_CIR "
            + "                                             ,max(par_ser_ord.object_id) as ser_obid "
            + "                                             ,CIR.OBJECT_ID as cir_obid "
            + "                                             ,CIR.OBJECT_TYPE_ID as cir_ob_type "
            + "                                             ,CIR.PARENT_ID as cir_par_obid "
            + "                                             ,PAR_SER_ORD.OBJECT_TYPE_ID as ser_ob_type "
            + "                                             ,SER_ORD.OBJECT_ID as cir_ser_obid "
            + "                                             ,SER_ORD.NAME as cir_ser_name "
            + "                                             ,SER_ORD.OBJECT_TYPE_ID as cir_ser_ob_type "
            + "                                             ,ser_ord.object_id as piid_ser "
            + "                                             ,CIR.OBJECT_ID as cir_piid_obid "
            + "                                             ,cdi_ord.object_id as cdi_obid "
            + "                                             ,cdi_ord.object_type_id as cdi_ot_id "
            + "      "
            + "                                             FROM NC_DEV.NC_OBJECTS osa "
            + "                                             , NC_DEV.NC_OBJECTS cir "
            + "                                             , NC_DEV.NC_REFERENCES par_cir_ref "
            + "                                             , NC_DEV.NC_OBJECTS ser_ord "
            + "                                             , NC_DEV.NC_OBJECTS par_ser_ord "
            + "                                             , NC_DEV.NC_REFERENCES ser_ord_ref "
            + "                                             , nc_dev.nc_objects cdi_ord "
            + "                                             , nc_dev.nc_references cdi_ord_ref "
            + "                                             WHERE (osa.OBJECT_ID = ?) "
            + "                                              AND   (osa.PARENT_ID = cir.OBJECT_ID)                                            "
            + "                                             AND   (cir.OBJECT_CLASS_ID = 8) "
            + "                                             AND   (cir.OBJECT_ID = par_cir_ref.\"REFERENCE\")                                             "
            + "                                             AND   (par_cir_ref.OBJECT_ID = ser_ord.OBJECT_ID) "
            + "  "
            + "                                             AND   (ser_ord_ref.ATTR_ID = 3090923248013355861)  "
            + "                                             AND   (ser_ord.OBJECT_ID = ser_ord_ref.OBJECT_ID)                         "
            + "                                             AND   ser_ord.OBJECT_CLASS_ID in ( "
            + "                                                                                                 3051537054013720615   "
            + "                                                                                                 ,3051537054013720612    "
            + "                                                                                                 ) "
            + "   "
            + "                                            AND   (par_ser_ord.OBJECT_TYPE_ID = 3060367906013764641) "
            + "                                            AND   (ser_ord_ref.REFERENCE = par_ser_ord.OBJECT_ID)  "
            + "                                             "
            + "                                            and    (cdi_ord_ref.object_id = ser_ord.object_id) "
            + "                                            and    (cdi_ord_ref.attr_id = 3090923248013355861) "
            + "                                            and    (cdi_ord.object_id = cdi_ord_ref.\"REFERENCE\") "
            + "                                           group by osa.NAME  "
            + "                                             , cir.NAME  "
            + "                                             ,CIR.OBJECT_ID  "
            + "                                             ,CIR.OBJECT_TYPE_ID  "
            + "                                             ,CIR.PARENT_ID  "
            + "                                             ,PAR_SER_ORD.OBJECT_TYPE_ID  "
            + "                                             ,SER_ORD.OBJECT_ID  "
            + "                                             ,SER_ORD.NAME  "
            + "                                             ,SER_ORD.OBJECT_TYPE_ID  "
            + "                                             ,ser_ord.object_id  "
            + "                                             ,CIR.OBJECT_ID "
            + "                                             ,cdi_ord.object_id "
            + "                                             ,cdi_ord.object_type_id "
            + "  "
            + "                                             union "
            + "                                             select  "
            + "                                             eotdm.OSA "
            + "                                             ,eotdm.PAR_CIR "
            + "                                             ,eotdm.ser_obid "
            + "                                             ,eotdm.cir_obid "
            + "                                             ,eotdm.cir_ob_type "
            + "                                             ,eotdm.cir_par_obid "
            + "                                             ,eotdm.ser_ob_type "
            + "                                             ,eotdm.cir_ser_obid "
            + "                                             ,eotdm.cir_ser_name "
            + "                                             ,eotdm.cir_ser_ob_type "
            + "                                             ,eotdm.piid_ser "
            + "                                             ,eotdm.cir_piid_obid   "
            + "                                             ,eotdm.ser_obid cdi_obid "
            + "                                             ,eotdm.ser_ob_type cdi_ot_id "
            + "  "
            + "                                             from ( "
            + "                                             "
            + "                                                        SELECT distinct  "
            + "                                                         osa.NAME AS OSA "
            + "                                                         , cir.NAME AS PAR_CIR "
            + "                                                         ,max(par_ser_ord.object_id) as ser_obid "
            + "                                                         ,PAR_CIR.OBJECT_ID as cir_obid "
            + "                                                         ,CIR.OBJECT_TYPE_ID as cir_ob_type "
            + "                                                         ,CIR.PARENT_ID as cir_par_obid "
            + "                                                         ,PAR_SER_ORD.OBJECT_TYPE_ID as ser_ob_type "
            + "                                                         ,CIR_SER_ORD.OBJECT_ID as cir_ser_obid "
            + "                                                         ,CIR_SER_ORD.NAME as cir_ser_name "
            + "                                                         ,cir_ser_ord.object_type_id as cir_ser_ob_type "
            + "                                                         ,SER_ORD.OBJECT_ID as piid_ser "
            + "                                                         ,CIR.OBJECT_ID as cir_piid_obid                                             "
            + "                                                         FROM NC_DEV.NC_OBJECTS osa "
            + "                                                         , NC_DEV.NC_OBJECTS cir "
            + "                                                         , NC_DEV.NC_REFERENCES cir_ser_ref "
            + "                                                         , NC_DEV.NC_OBJECTS cir_ser_ord "
            + "                                                         , NC_DEV.NC_OBJECTS pe "
            + "                                                         , NC_DEV.NC_REFERENCES cir_ref "
            + "                                                         , NC_DEV.NC_OBJECTS par_cir "
            + "                                                         , NC_DEV.NC_REFERENCES par_cir_ref "
            + "                                                         , NC_DEV.NC_OBJECTS ser_ord "
            + "                                                         , NC_DEV.NC_OBJECTS par_ser_ord "
            + "                                                         , NC_DEV.NC_REFERENCES ser_ord_ref "
            + "                                                         ,NC_DEV.NC_PARAMS ser_ord_par                                                                      "
            + "                                                         WHERE (osa.OBJECT_ID = ?) "
            + "                                                         AND   (osa.PARENT_ID = cir.OBJECT_ID) "
            + "  "
            + "                                                         AND   (cir.OBJECT_CLASS_ID = 8) "
            + "                                                         AND   (cir.OBJECT_ID = cir_ref.\"REFERENCE\")                                            "
            + "  "
            + "                                                         AND   (cir_ref.OBJECT_ID = pe.OBJECT_ID) "
            + "  "
            + "                                                         AND   (pe.PARENT_ID = par_cir.OBJECT_ID)                                             "
            + "                                                         AND   (pe.OBJECT_CLASS_ID = 9) "
            + "  "
            + "                                                         AND   (par_cir.OBJECT_ID = par_cir_ref.\"REFERENCE\") "
            + "                                                         AND   (par_cir.OBJECT_CLASS_ID = 8) "
            + "                                                         and   PAR_CIR.OBJECT_TYPE_ID <> 6031032137013149793 "
            + "  "
            + "                                                         AND   (par_cir_ref.OBJECT_ID = ser_ord.OBJECT_ID)                                       "
            + "                                                         AND   (ser_ord.OBJECT_CLASS_ID = 3051537054013720612) "
            + "  "
            + "                                                         AND   (ser_ord.OBJECT_ID = ser_ord_ref.OBJECT_ID) "
            + "                                                         AND   (ser_ord_ref.ATTR_ID = 3090923248013355861)  "
            + "  "
            + "                                                         AND   (ser_ord_ref.REFERENCE = par_ser_ord.OBJECT_ID)  "
            + "                                                         AND   (par_ser_ord.OBJECT_TYPE_ID = 3060367906013764641) "
            + "  "
            + "                                                         and CIR.OBJECT_ID = CIR_SER_REF.REFERENCE "
            + "                                                         and CIR_SER_REF.OBJECT_ID = CIR_SER_ORD.OBJECT_ID "
            + "                                                         and CIR_SER_ORD.OBJECT_CLASS_ID = 3051537054013720615 "
            + "                                                          "
            + "                                                         and ser_ord_par.object_id = par_ser_ord.object_id "
            + "                                                         and ser_ord_par.attr_id = 3060367906013764649 "
            + "                                                         and ser_ord_par.value <> 'CLOSED' "
            + "  "
            + "                                                         group by  "
            + "                                                         osa.NAME  "
            + "                                                         , cir.NAME  "
            + "  "
            + "                                                         ,PAR_CIR.OBJECT_ID  "
            + "                                                         ,CIR.OBJECT_TYPE_ID  "
            + "                                                         ,CIR.PARENT_ID  "
            + "                                                         ,PAR_SER_ORD.OBJECT_TYPE_ID  "
            + "                                                         ,CIR_SER_ORD.OBJECT_ID  "
            + "                                                         ,CIR_SER_ORD.NAME  "
            + "                                                         ,cir_ser_ord.object_type_id  "
            + "                                                         ,SER_ORD.OBJECT_ID  "
            + "                                                         ,CIR.OBJECT_ID  "
            + "  "
            + "                                             ) eotdm "
            + "                                     )";
    

}
