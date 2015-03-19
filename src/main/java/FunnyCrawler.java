import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

/*
 * http://www.mkyong.com/java/jsoup-send-search-query-to-google/
 * */

public class FunnyCrawler {
	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	static {
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}
	public String getDomainName(String url) {
		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;
	}
	List<String> getDataFromGoogle(String query) {
		List<String> result = new ArrayList<String>();
		String request = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q="+ query;
		try {
			String google = Jsoup.connect(request).ignoreContentType(true).execute().body();
			JSONObject json;
			try {
				json = (JSONObject) new JSONParser().parse(google);
				JSONObject structure = (JSONObject) json.get("responseData");
				JSONArray array = (JSONArray) structure.get("results");
				Iterator<?> i = array.iterator();
				while (i.hasNext()) {
					JSONObject innerObj = (JSONObject) i.next();
					result.add(innerObj.get("titleNoFormatting").toString());
					result.add(innerObj.get("url").toString());
					result.add(innerObj.get("content").toString());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}