import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SearchTwo() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchword = request.getParameter("q");
		String siter = request.getParameter("s");
		FunnyCrawler obj = new FunnyCrawler();
		List<String> result = obj.getDataFromGoogle(searchword + " site:" + siter);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		String results = "";
		for(int i = 0; i<result.size();i++){
			if(i%3==0 && i!=0)
				results += "<br>";
			results += result.get(i) + "<br>";
		}
		out.println(results);
		
		out.println("</body>");
		out.println("</html>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchword = request.getParameter("q");
		//String siter = request.getParameter("s");
		FunnyCrawler obj = new FunnyCrawler();
		List<String> result = obj.getDataFromGoogle(searchword);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		String results = "";
		for(int i = 0; i<result.size();i++){
			if(i%3==0 && i!=0)
				results += "<br>";
			results += result.get(i) + "<br>";
		}
		out.println(results);
		
		out.println("</body>");
		out.println("</html>");
	}

}
