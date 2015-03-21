

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.RankedLink;


public class Seearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Storage storage = new Storage();   
    private Map<String,CrawledLink> crawledMap;
    private Map<String,HashMap<String,Float>> crawledIndex;
    private Map<String,RankVector> crawledRanking;
    private SearchEngine search;
    
    public Seearch() {
        super();
        Set<CrawledLink> crawledLinks = storage.readJSON();
        crawledMap = new HashMap<String,CrawledLink>();
        for(CrawledLink link : crawledLinks ){
        	crawledMap.put(link.getLinkURL(),link);
        }
        
        crawledIndex = storage.readIndex();
        crawledRanking = storage.readRanking();
        search = new SearchEngine(crawledRanking, crawledIndex, crawledMap);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchword = request.getParameter("q");
		String website = request.getParameter("s");

		FunnyCrawler obj = new FunnyCrawler();
		List<String> result = obj.getDataFromGoogle(searchword + " site:" + website);
		
		request.setAttribute("special",result);
		
		List<RankedLink> ourresult = search.getSearchResults(searchword);
		request.setAttribute("special2",ourresult);
		
		request.getRequestDispatcher("/search.jsp").forward( request, response );
		
		
	}

}
