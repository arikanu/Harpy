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
		String orr = "{\"key\":\"KPSS_2013-12-27_120_OO_GY_GK\",\"date\":1356818400000,\"duration\":120,\"nbOfQuestions\":6,\"examSubType\":null,\"examId\":0,\"strDate\":\"30/12/2012\",\"tests\":[{\"name\":\"GY\",\"number\":0,\"nbOfQuestions\":2,\"testId\":0,\"questions\":[{\"number\":1,\"correctAnswer\":\"D\",\"questionHtml\":\"Üniversitede siyaset bilimi okuyordum. Birden fark ettim ki inanmadýðým þeyleri kimi zaman savunmak zorunda kalmak, benim gibi yüksek sesle konuþan biri için çok zordu. \r\n<P><STRONG>Bu parçada geçen altý çizili sözle asýl anlatýlmak istenen aþaðýdakilerden hangisidir?</STRONG></P>\",\"questionId\":0,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"Baþkalarýnýn ilgisini çekmekten hoþlanan\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"Sürekli kendini yenilemeye çalýþan\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"Güncel konularý dikkatle izleyen\",\"question\":null},{\"choiceLineIndex\":4,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"Doðrularý söylemekten çekinmeyen\",\"question\":null},{\"choiceLineIndex\":5,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"Sýradan bir insan olmayý istemeyen\",\"question\":null}]},{\"number\":2,\"correctAnswer\":\"E\",\"questionHtml\":\"Babaannem lüzumsuz ve boþ konuþana, çok konuþup da hiçbir þey söylemeyene “evinsiz darý gibi…” der; böylelerinin konuþmalarýna ----, kalkýp giderdi. Gerideyse bize öðrettiði yeni bir deyim kalýrdý. \r\n<P><STRONG>Bu parçada boþ býrakýlan yere aþaðýdakilerden hangisi getirilebilir?</STRONG></P>\",\"questionId\":1,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"göz dikmez\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"dil dökmez\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"el açmaz\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"burun kývýrmaz\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"kulak asmaz\",\"question\":null}]}],\"exam\":null},{\"name\":\"GK\",\"number\":1,\"nbOfQuestions\":4,\"testId\":1,\"questions\":[{\"number\":1,\"correctAnswer\":\"A\",\"questionHtml\":\"Kimi filmler vardýr, bizi içimizdeki cam kýrýklarýna deðerek (dokunarak) kendi eksiklerimizden yakalar. Ýþ I böyle filmlere gelince aþinasý olduðumuz (tanýdýðýmýz) II film okumalarýný hesaba katmak (yararlý bulmak) III istemeyiz çoðunlukla. Þablonlarýn kabaca (üstünkörü) IV uygulanmasýna karþý çýkýþýmýz, hayatý yaþanýr kýlan böyle þeylerin korunmasý adýna içsel bir direniþe geçmemizden ileri gelir (kaynaklanýr). V \r\n<P><STRONG>Bu parçadaki altý çizili sözlerden hangisinin yerine ayraç içinde verilen söz getirildiðinde anlamda bir deðiþiklik olur?</STRONG></P>\",\"questionId\":2,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"III.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"IV.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"V.\",\"question\":null}]},{\"number\":2,\"correctAnswer\":\"C\",\"questionHtml\":\"(I) Türkiye’nin en büyük kervansaraylarýndan Kýrkgöz Han tekrar turizme kazandýrýlýyor. (II) Ýpek Yolu üzerindeki duraklardan biri. (III) Han, dört yýllýk bir restorasyon sürecinin ardýndan kullanýma açýlýyor. (IV) Selçuklu Ýmparatorluðu döneminde II. Gýyaseddin Keyhüsrev tarafýndan yaptýrýlan han, Anadolu’nun sultanlarýn emriyle inþa edilen altý kervansarayýndan biri. (V) Bu tarihî yapýda yýlda 1000 ziyaretçinin aðýrlanmasý bekleniyor. \r\n<P><STRONG>Bu parçadaki numaralanmýþ cümlelerin hangisinde karþýlaþtýrma yapýlmýþtýr?</STRONG></P>\",\"questionId\":3,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"III.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"IV.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"V.\",\"question\":null}]},{\"number\":3,\"correctAnswer\":\"E\",\"questionHtml\":\"(I) Akþamýn alaca karanlýðýnda Simena Kalesi’nde gözler önüne serilen manzara olaðanüstü. (II) Karþýda Kekova Adasý yer alýyor. (III) Denizden itibaren yükselen dik yamaçta ise dar sokaklarý, sevimli taþ evleriyle Kaleköy bulunuyor. (IV) Eskiden taþýt yolu bulunmayan Kaleköy’e ya denizden teknelerle ya da Üçaðýz’dan yürüyerek ulaþýlýrdý. (V) Þimdi beþ yüz metre yakýnýna kadar araçla gidilebiliyor. \r\n<P><STRONG>Bu parçadaki numaralanmýþ cümlelerin hangilerinde öznellik söz konusudur?</STRONG></P>\",\"questionId\":4,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"I. ve II.\",\"question\":null},{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"I. ve III.\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"II. ve IV.\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"III. ve V.\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"IV. ve V.\",\"question\":null}]},{\"number\":4,\"correctAnswer\":\"D\",\"questionHtml\":\"Bir fotoðraf sergisi, onlarca fotoðrafýn içinden yapýlan bir seçme, bir eleme sonucunda karþýmýza çýkýyor. Neler eleniyor? O elenenleri de görmenin bir yolu yok mu? Sergiye deðer katacak olanlar, sergide yer almayanlar olabilir mi? \r\n<P><STRONG>Bu parçadaki altý çizili söz, aþaðýdakilerden hangisini vurgulamak için söylenmiþ <U>olamaz</U>?</STRONG></P>\",\"questionId\":5,\"test\":null,\"choices\":[{\"choiceLineIndex\":1,\"correctAnswer\":false,\"choiceCode\":\"A\",\"choiceId\":0,\"choiceHtml\":\"Sergide yer almayan fotoðraflarýn, kimi izleyicilerin merakýný uyandýrdýðýný\",\"question\":null},{\"choiceLineIndex\":2,\"correctAnswer\":false,\"choiceCode\":\"B\",\"choiceId\":0,\"choiceHtml\":\"Sanatçýnýn, iyi bir seçme yapýp yapmadýðýnýn bilinemeyeceðini\",\"question\":null},{\"choiceLineIndex\":3,\"correctAnswer\":false,\"choiceCode\":\"C\",\"choiceId\":0,\"choiceHtml\":\"Sergiye seçilmeyenlerin, ötekilerin güzelliðini gölgeleyebileceðini\",\"question\":null},{\"choiceLineIndex\":4,\"correctAnswer\":false,\"choiceCode\":\"D\",\"choiceId\":0,\"choiceHtml\":\"Güzellik algýsýnýn göreceli olduðunu\",\"question\":null},{\"choiceLineIndex\":5,\"correctAnswer\":false,\"choiceCode\":\"E\",\"choiceId\":0,\"choiceHtml\":\"Sanatçýnýn, sýk sýk yeni sergiler düzenlemesi gerektiðini\",\"question\":null}]}],\"exam\":null}],\"images\":[]}";
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
