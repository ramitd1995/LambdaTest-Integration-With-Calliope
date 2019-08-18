package calliopeIntegration;



import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CalliopeAPI 
{
    @Test 
	public void calliopeAPIcall()
    {	 
    	final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml");
    	final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    	
        MultipartBody requestBody = null;
		try {
			String report_filename = "C:\\Users\\Lenovo-I7\\git\\Calliope-Integration-with-LambdaTest\\CalliopeSample\\test-output\\testng-results.xml";
			
			requestBody = new MultipartBody.Builder()
			        .setType(MultipartBody.FORM)
			        .addFormDataPart("file", report_filename, RequestBody.create(MEDIA_TYPE_XML, new File(report_filename)))
			        .build();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
        OkHttpClient client = new OkHttpClient();
		String endpoint_url = "https://app.calliope.pro/api/v2/profile/577/report/import?os=myos&platform=myplatform&build=mybuild"; 
		final String API_KEY = "ZDk4ZGVhM2VlMzRlYjlkZGI0Y2MxZTA4Yjg1OTYxNjUyMzQzMGZhZmE0NTY0MTk4Y2MyMmM0NGQ3OTlmNTk2N2Jm";
	    Request request = new Request.Builder().url(endpoint_url).post(requestBody).addHeader("x-api-key", API_KEY).build();
	      
	    Response response = null;
		try {
			response = client.newCall(request).execute();
			System.out.println("=============");
			System.out.println(response);
			String response_body = response.body().string();
			System.out.println(response_body);
			System.out.println("=============");
			
			if (response.isSuccessful()){
				 
				 System.out.println("created");
			} else {
				throw new IOException("Unexpected HTTP code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
      
    }
}
