import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.vm.admin.exam.Read;



public class TryClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String uur = "qThNext_4_6";
		
		int lenLbl = "qThNext".length();
		
		System.out.println(lenLbl);
		
		int indx1 = uur.indexOf("_");
		System.out.println("indx1:" + indx1);
		
		int indx2 = uur.indexOf("_", indx1+1);
		System.out.println("indx2:" + indx2);
		
		String testId = uur.substring(indx1+1,indx2);
		System.out.println("testId:" + testId);
		
		String qId = uur.substring(indx2+1);
		System.out.println("qId:" + qId);
		
		
		
		
		
	}

}
