
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

import web.RankedLink;

public class SearchEngine {

	Map<String, RankVector> combinedRanking;
	Map<String, HashMap<String, Float>> wordIndex;
	Map<String, CrawledLink> map = new HashMap<String, CrawledLink>();

	SearchEngine(Map<String, RankVector> combinedRanking,
			Map<String, HashMap<String, Float>> wordIndex,
			 Map<String,CrawledLink> crawledMap) {
		this.combinedRanking = combinedRanking;
		this.wordIndex = wordIndex;
		this.map = crawledMap;

	}

	public List<CrawledLink> sortLinks(TreeMap<Double, CrawledLink> rankedMap) {
		List<CrawledLink> linkList = new ArrayList<CrawledLink>();
		Set<Entry<Double, CrawledLink>> set = rankedMap.entrySet();
	    Iterator<Entry<Double, CrawledLink>> i = set.iterator();
	    // Display elements
	    while(i.hasNext()) {
	      Map.Entry<Double, CrawledLink> me = (Map.Entry<Double, CrawledLink>)i.next();
	      linkList.add(me.getValue());
	    }
	    
	    return linkList;

	}


	public List<RankedLink> getSearchResults(String term) {
		List<CrawledLink> rankedList = new ArrayList<CrawledLink>();
		List<RankedLink> rankedLinks = new ArrayList<RankedLink>();
		TreeMap<Double, CrawledLink> rankedMap = 
			      new TreeMap<Double, CrawledLink>(Collections.reverseOrder());
		

		if (!wordIndex.containsKey(term)) {
			return null;
		} else {
			HashMap<String, Float> linkmap = wordIndex.get(term);
			
			for (Entry<String, Float> entry : linkmap.entrySet()) {
				String url = entry.getKey();
				Float tfidf = entry.getValue();
				RankVector rankV = combinedRanking.get(url);
				Double rank = (double) (.1*rankV.getBrinRank() + .8*tfidf * .1*rankV.getOurRank());
				rankedMap.put(rank, map.get(url));
			}
			
			
		}
		
		rankedList = sortLinks(rankedMap);
		
		for (CrawledLink l: rankedList){
			rankedLinks.add(new RankedLink(l.getLinkURL(), l.getMetadata().get("title"), l.getMetadata().get("Description")));
			
		}

		return rankedLinks;

	}

}
