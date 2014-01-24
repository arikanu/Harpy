import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.utils.JsonUtil;



public class TryClass {
	
	
	public static long[] getTimeDifference(Date d1, Date d2) {
        long[] result = new long[5];
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(d1);

        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);

        long diff = Math.abs(cal.getTimeInMillis() - t1);
        final int ONE_DAY = 1000 * 60 * 60 * 24;
        final int ONE_HOUR = ONE_DAY / 24;
        final int ONE_MINUTE = ONE_HOUR / 60;
        final int ONE_SECOND = ONE_MINUTE / 60;

        long d = diff / ONE_DAY;
        diff %= ONE_DAY;

        long h = diff / ONE_HOUR;
        diff %= ONE_HOUR;

        long m = diff / ONE_MINUTE;
        diff %= ONE_MINUTE;

        long s = diff / ONE_SECOND;
        long ms = diff % ONE_SECOND;
        result[0] = d;
        result[1] = h;
        result[2] = m;
        result[3] = s;
        result[4] = ms;

        return result;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String uur = "qThNext_4_6";
		uur = "c_3_1_D";
		
		int lenLbl = "c".length();
		
		System.out.println(lenLbl);
		
		int indx1 = uur.indexOf("_");
		System.out.println("indx1:" + indx1);
		
		int indx2 = uur.indexOf("_", indx1+1);
		System.out.println("indx2:" + indx2);
		
		int indx3 = uur.indexOf("_", indx2+1);
		System.out.println("indx3:" + indx3);
		
		String testId = uur.substring(indx1+1,indx2);
		System.out.println("testId:" + testId);
		
		String qId = uur.substring(indx2+1,indx3);
		System.out.println("qId:" + qId);
		
		String qCssId = "q_" + testId + "_" + qId;
		
		qCssId = "q_" + uur.substring(indx1+1, indx3);
		
		System.out.println("qCssId: " + qCssId);
		
		
		
		int i1 = 1;
		int i2 = 2;
		int i3 = 2;
		int i4 = 3;
		int i5 = 3;
		String iii;// = "";
		iii = String.valueOf(i1) + String.valueOf(i2) + String.valueOf(i3) + String.valueOf(i4) + String.valueOf(i5);
		int iint = Integer.valueOf(iii);
		
		System.out.println("iint = " + iint);
		if (iint == 12233) {
			System.out.println("equal");			
		} else {
			System.out.println("not equal");
		}
		
		
		String startTime = "1/21/2014 20:48:00";
		String eendTime = "1/22/2014 01:48:00";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			Date start = df.parse(startTime);
			Long lngStart = start.getTime();
			Date now = new Date();
			Long lngNow = now.getTime();
			Date end = new Date(lngStart + (120 * 60000));
			Long lngEnd = end.getTime();
			
			Date eend = df.parse(eendTime);
			Long lngEEnd = eend.getTime();
			
			long[] diff1 = getTimeDifference(eend, start);
			long[] diff2 = getTimeDifference(now, start);
			
			
			DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			System.out.println("start = " + df2.format(start));
			System.out.println("end = " + df2.format(eend));
			System.out.println("d: " + diff1[0]);
			System.out.println("h: " + diff1[1]);
			System.out.println("m: " + diff1[2]);
			System.out.println("s: " + diff1[3]);
			System.out.println("ms: " + diff1[4]);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("olmadiii");
		}
		
		
		
		
		ArrayList<String> abc = new ArrayList<String> () {{ add("A");add("B");add("C"); }};
		for (String string : abc) {
			System.out.println(string);
		}
		
		/*
		String orr = "{\"key\":\"KPSS_2013-12-27_120_OO_GY_GK\",\"date\":1356818400000,\"duration\":120,\"nbOfQuestions\":6,\"examSubType\":null,\"examId\":0,\"strDate\":\"30/12/2012\",\"tests\":[{\"name\":\"GY\",\"number\":0,\"nbOfQuestions\":2,\"testId\":0,\"questions\":[{\"number\":1,\"correctAnswer\":\"D\",\"questionHtml\":\"�niversitede siyaset bilimi okuyordum. Birden fark ettim ki inanmad���m �eyleri kimi zaman savunmak zorunda kalmak, benim gibi y�ksek sesle konu�an biri i�in �ok zordu. \r\n<P><STRONG>Bu par�ada ge�en alt� �izili s�zle as�l anlat�lmak istenen a�a��dakilerden hangisidir?</STRONG></P>\",\"questionId\":0,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"Ba�kalar�n�n ilgisini �ekmekten ho�lanan\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"S�rekli kendini yenilemeye �al��an\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"G�ncel konular� dikkatle izleyen\",\"question\":null},{\"choiceLineIndex\":4,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"Do�rular� s�ylemekten �ekinmeyen\",\"question\":null},{\"choiceLineIndex\":5,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"S�radan bir insan olmay� istemeyen\",\"question\":null}]},{\"number\":2,\"correctAnswer\":\"E\",\"questionHtml\":\"Babaannem l�zumsuz ve bo� konu�ana, �ok konu�up da hi�bir �ey s�ylemeyene �evinsiz dar� gibi�� der; b�ylelerinin konu�malar�na ----, kalk�p giderdi. Gerideyse bize ��retti�i yeni bir deyim kal�rd�. \r\n<P><STRONG>Bu par�ada bo� b�rak�lan yere a�a��dakilerden hangisi getirilebilir?</STRONG></P>\",\"questionId\":1,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"g�z dikmez\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"dil d�kmez\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"el a�maz\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"burun k�v�rmaz\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"kulak asmaz\",\"question\":null}]}],\"exam\":null},{\"name\":\"GK\",\"number\":1,\"nbOfQuestions\":4,\"testId\":1,\"questions\":[{\"number\":1,\"correctAnswer\":\"A\",\"questionHtml\":\"Kimi filmler vard�r, bizi i�imizdeki cam k�r�klar�na de�erek (dokunarak) kendi eksiklerimizden yakalar. �� I b�yle filmlere gelince a�inas� oldu�umuz (tan�d���m�z) II film okumalar�n� hesaba katmak (yararl� bulmak) III istemeyiz �o�unlukla. �ablonlar�n kabaca (�st�nk�r�) IV uygulanmas�na kar�� ��k���m�z, hayat� ya�an�r k�lan b�yle �eylerin korunmas� ad�na i�sel bir direni�e ge�memizden ileri gelir (kaynaklan�r). V \r\n<P><STRONG>Bu par�adaki alt� �izili s�zlerden hangisinin yerine ayra� i�inde verilen s�z getirildi�inde anlamda bir de�i�iklik olur?</STRONG></P>\",\"questionId\":2,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"III.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"IV.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"V.\",\"question\":null}]},{\"number\":2,\"correctAnswer\":\"C\",\"questionHtml\":\"(I) T�rkiye�nin en b�y�k kervansaraylar�ndan K�rkg�z Han tekrar turizme kazand�r�l�yor. (II) �pek Yolu �zerindeki duraklardan biri. (III) Han, d�rt y�ll�k bir restorasyon s�recinin ard�ndan kullan�ma a��l�yor. (IV) Sel�uklu �mparatorlu�u d�neminde II. G�yaseddin Keyh�srev taraf�ndan yapt�r�lan han, Anadolu�nun sultanlar�n emriyle in�a edilen alt� kervansaray�ndan biri. (V) Bu tarih� yap�da y�lda 1000 ziyaret�inin a��rlanmas� bekleniyor. \r\n<P><STRONG>Bu par�adaki numaralanm�� c�mlelerin hangisinde kar��la�t�rma yap�lm��t�r?</STRONG></P>\",\"questionId\":3,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"III.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"IV.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"V.\",\"question\":null}]},{\"number\":3,\"correctAnswer\":\"E\",\"questionHtml\":\"(I) Ak�am�n alaca karanl���nda Simena Kalesi�nde g�zler �n�ne serilen manzara ola�an�st�. (II) Kar��da Kekova Adas� yer al�yor. (III) Denizden itibaren y�kselen dik yama�ta ise dar sokaklar�, sevimli ta� evleriyle Kalek�y bulunuyor. (IV) Eskiden ta��t yolu bulunmayan Kalek�y�e ya denizden teknelerle ya da ��a��z�dan y�r�yerek ula��l�rd�. (V) �imdi be� y�z metre yak�n�na kadar ara�la gidilebiliyor. \r\n<P><STRONG>Bu par�adaki numaralanm�� c�mlelerin hangilerinde �znellik s�z konusudur?</STRONG></P>\",\"questionId\":4,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I. ve II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"I. ve III.\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"II. ve IV.\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"III. ve V.\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"IV. ve V.\",\"question\":null}]},{\"number\":4,\"correctAnswer\":\"D\",\"questionHtml\":\"Bir foto�raf sergisi, onlarca foto�raf�n i�inden yap�lan bir se�me, bir eleme sonucunda kar��m�za ��k�yor. Neler eleniyor? O elenenleri de g�rmenin bir yolu yok mu? Sergiye de�er katacak olanlar, sergide yer almayanlar olabilir mi? \r\n<P><STRONG>Bu par�adaki alt� �izili s�z, a�a��dakilerden hangisini vurgulamak i�in s�ylenmi� <U>olamaz</U>?</STRONG></P>\",\"questionId\":5,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"Sergide yer almayan foto�raflar�n, kimi izleyicilerin merak�n� uyand�rd���n�\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"Sanat��n�n, iyi bir se�me yap�p yapmad���n�n bilinemeyece�ini\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"Sergiye se�ilmeyenlerin, �tekilerin g�zelli�ini g�lgeleyebilece�ini\",\"question\":null},{\"choiceLineIndex\":4,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"G�zellik alg�s�n�n g�receli oldu�unu\",\"question\":null},{\"choiceLineIndex\":5,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"Sanat��n�n, s�k s�k yeni sergiler d�zenlemesi gerekti�ini\",\"question\":null}]}],\"exam\":null}],\"images\":[]}";
		ObjectMapper mapper = new ObjectMapper();
		Exam miexam = mapper.readValue(orr, Exam.class);
		
		
		System.out.println(orr);
		
		*/
		
		
		
		
		String ugur = "asdfasdfxxxsadfasdfsdfxxsdfsdfxxxuu";
		String[] parts = ugur.split("xxx");
		for (String part : parts) {
			System.out.println("part: " + part);
		}
		
	}

}
