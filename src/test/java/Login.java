import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Login {

	
	CSVReader reader;
	String CSVfile = (System.getProperty("user.dir") + "\\TestData\\LoginTestData.csv");

	@Parameters({ "APIURL" })
	@Test
	public void test_01(String URL) throws NumberFormatException, IOException {

		reader = new CSVReader(new FileReader(CSVfile));
		String[] csvcell;
		JSONObject request = new JSONObject();

		
		while ((csvcell = reader.readNext()) != null) {
			String EmailADDRE = csvcell[0];
			String Passwordtext = csvcell[1];
			String ErrorCode = csvcell[2];
			
			request.put("email", EmailADDRE);
			request.put("password", Passwordtext);
			
			System.out.println(request);

			given().body(request.toJSONString()).when().post(URL).then().statusCode(Integer.parseInt(ErrorCode));
		}
	}
}
