
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Storage {
	File indexJSON = new File("C:\\Users\\Ye\\Desktop\\CS 454 Search Engine\\workspace\\web\\src\\main\\webapp\\index.json");
	File rankingJSON = new File("C:\\Users\\Ye\\Desktop\\CS 454 Search Engine\\workspace\\web\\src\\main\\webapp\\ranking.json");
	File linksJSON = new File("C:\\Users\\Ye\\Desktop\\CS 454 Search Engine\\workspace\\web\\src\\main\\webapp\\metadata2.json");
	File jsonFile = new File("C:\\Users\\Ye\\Desktop\\CS 454 Search Engine\\workspace\\web\\src\\main\\webapp\\queries");
	ObjectMapper obMap = new ObjectMapper();

	public Storage(String index, String ranking) {
		this.indexJSON = new File(index);
		this.rankingJSON = new File(ranking);
	}

	public Storage(){
		
	}
	
	public void store2(List<CrawledLink> linkList,String query) {
		try {
			System.out.println("Saving to JSON");
			obMap.writeValue(new File(jsonFile , query+".json" ), linkList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void storeIndex(Map<String, HashMap<String, Float>> wordIndex) {
		try {
			obMap.writeValue(indexJSON, wordIndex);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Set<CrawledLink> readJSON() {

		Set<CrawledLink> links = null;
		try {
			links = obMap.readValue(linksJSON,
					new TypeReference<Set<CrawledLink>>() {
					});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return links;
	}
	
	public Map<String,HashMap<String,Float>> readIndex() {

		Map<String,HashMap<String,Float>> links = null;
		try {
			links = obMap.readValue(indexJSON,
					new TypeReference<Map<String,HashMap<String,Float>>>() {
					});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return links;
	}
	
	public Map<String,RankVector> readRanking() {

		Map<String,RankVector> links = null;
		try {
			links = obMap.readValue(rankingJSON,
					new TypeReference<Map<String,RankVector>>() {
					});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return links;
	}

	public void storeRanking(Map<String, RankVector> combinedRanking) {
		try {
			obMap.writeValue(rankingJSON, combinedRanking);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
