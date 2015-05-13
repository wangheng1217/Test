import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpTest {

	public static void main(String[] args) throws HttpException, IOException {
		StringRequestEntity stringRequestEntity = new StringRequestEntity("6");
		PutMethod putMethod = new PutMethod(
				"http://localhost:8080/OSA_ESBApp/http/genpon");
		putMethod.setRequestEntity(stringRequestEntity);
		putMethod.setRequestHeader("Content-type",
				"application/json; charset=ISO-8859-1");
		HttpClient httpclient = new HttpClient();
		int returnCode = httpclient.executeMethod(putMethod);
		System.out.println(returnCode);
		System.out.println(putMethod.getResponseBodyAsString());
	}

}
