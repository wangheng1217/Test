import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PDC2Cleanup {
	public static void main(String[] args) throws Exception {
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        
        // production
//        String saoUrl = "jdbc:oracle:thin:@//ossrac-scan.twtelecom.com:1521/SAO_PRD";
//        String saoUser = "sao_twtc";
//        String saoPassword = "sao#7Wtc";
        
        // testa
        String saoUrl = "jdbc:oracle:thin:@//swiftdbdev1.twtelecom.com:1521/SAO_IOT";
        String saoUser = "sao_twtc";
        String saoPassword = "sao_iot";
        
        Connection saoConn = DriverManager.getConnection(saoUrl, saoUser, saoPassword);

        String saoQuery = "select p.order_number, fl.resource_id, func.function_id, func.function_status, ser.offnet_service_id, o.offnet_order_id"
        		+ " from sao_function func, sao_flow fl, sao_process p , sao_offnet_service ser, sao_params p2, sao_offnet_order o"
        		+ " where"
        		+ "  ser.offnet_service_id = o.offnet_service_id"
        		+ "  and p2.param_name = 'order_function_id'"
        		+ "  and p2.param_value = func.function_id"
        		+ "  and p2.parent_id = ser.offnet_service_id"
        		+ "  and func.flow_id = fl.flow_id"
        		+ "  and fl.process_id = p.process_id"
        		+ "  and func.function_id in ("
        		+ "    select distinct(func.function_id)"
        		+ "    from sao_function func, sao_offnet_service ser, sao_params p, sao_offnet_order o, sao_offnet_component comp, sao_offnet_order_circuit oc"
        		+ "    where func.model_id = 3025"
        		+ "      and p.param_name = 'order_function_id'"
        		+ "      and p.param_value = func.function_id"
        		+ "      and p.parent_id = ser.offnet_service_id"
        		+ "      and o.offnet_service_id = ser.offnet_service_id"
        		+ "      and comp.offnet_service_id = ser.offnet_service_id"
        		+ "      and oc.offnet_order_id = o.offnet_order_id"
        		+ "      and oc.ckr is null"
        		+ "  )";

        PreparedStatement saoSt = saoConn.prepareStatement(saoQuery);
        ResultSet saoRs = null;
        
        String saoQuery2 = "select transport from sao_offnet_component where offnet_service_id = ? order by offnet_component_rsi_id";
        PreparedStatement saoSt2 = saoConn.prepareStatement(saoQuery2);
        ResultSet saoRs2 = null;
        
        String saoQuery3 = "update sao_offnet_order_circuit set ckr = ? where offnet_order_id = ? and refnum = ?";
        PreparedStatement saoSt3 = saoConn.prepareStatement(saoQuery3);
        
        saoRs = saoSt.executeQuery();
        while (saoRs.next()) {
        	String orderNumber = saoRs.getString(1);
        	
        	String serviceId = saoRs.getString(5);
        	String orderId = saoRs.getString(6);
        	
        	saoSt2.setString(1, serviceId);
        	saoRs2 = saoSt2.executeQuery();
        	int i = 0;
        	while(saoRs2.next()) {
        		String transport = saoRs2.getString(1);
        		String refNum = "000" + (++i);
        		
        		saoSt3.setString(1, transport);
        		saoSt3.setString(2, orderId);
        		saoSt3.setString(3, refNum);
        		
        		int c = saoSt3.executeUpdate();
        		
        		System.out.println(c + " " + orderNumber + " " + refNum + ": " + transport);
        	}
        }

	}
}
