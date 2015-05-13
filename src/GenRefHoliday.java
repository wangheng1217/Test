import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenRefHoliday {

	public static void main(String[] args) throws Exception {
		String osaUrl = "jdbc:oracle:thin:@//ossrac-scan.twtelecom.com:1521/SAO_PRD";
		String osaUser = "osa_twtc";
		String osaPassword = "osa#7Wtc";

		Connection osaConn = DriverManager.getConnection(osaUrl, osaUser,
				osaPassword);

		String osaQuery1 = "(select distinct(icsc) from ref_icsc minus select distinct(icsc) from ref_holiday where to_char(holiday_date, 'yyyy') = '2014') order by icsc";
		PreparedStatement osaSt1 = osaConn.prepareStatement(osaQuery1);
		ResultSet osaRs1 = null;

		String osaQuery2 = "select o.onsp_provider from ref_icsc i, ref_onsp_icsc o where i.id = o.icsc_id and i.icsc = ?";
		PreparedStatement osaSt2 = osaConn.prepareStatement(osaQuery2);
		ResultSet osaRs2 = null;

		int i = 3616;

		osaRs1 = osaSt1.executeQuery();
		while (osaRs1.next()) {
			String icsc = osaRs1.getString(1);
			osaSt2.setString(1, icsc);
			osaRs2 = osaSt2.executeQuery();
			String provider = null;
			if (osaRs2.next()) {
				provider = osaRs2.getString(1);
			} else {
				provider = "";
			}
			
			icsc = icsc.trim();
			provider = provider.trim();
			
			if (provider.contains(",")) {
				provider = "\"" + provider + "\"";
			}

			String preStr = "," + icsc + "," + provider + ",All,";

			System.out.println((++i) + preStr + "2014-01-01,NEW YEARS DAY");
			System.out.println((++i) + preStr + "2014-05-26,MEMORIAL DAY");
			System.out.println((++i) + preStr + "2014-07-04,INDEPENDENCE DAY");
			System.out.println((++i) + preStr + "2014-09-01,LABOR DAY");
			System.out.println((++i) + preStr + "2014-11-27,THANKSGIVING DAY");
			System.out.println((++i) + preStr + "2014-11-28,DAY AFTER THANKSGIVING DAY");
			System.out.println((++i) + preStr + "2014-12-25,CHRISTMAS DAY");
		}
	}

}
