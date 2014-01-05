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

		String uur = "qNavLi_0_1";
		
		//System.out.println(uur.length());
		
		String uru = uur.substring(0, 6);
		
		
		
		int indx = uur.indexOf("_", 7);
		System.out.println("indx=" + indx);
		
		uru = uur.substring(7, indx);
		
		uru = uur.substring(indx+1);
		
		//String uru = uur.substring(uur.length()-2, 1);
		
		System.out.println(uru);
		
		
		
	}

}
