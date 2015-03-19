

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Seearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Seearch() {
        super();
        // TODO Auto-generated constructor stub
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
		request.getRequestDispatcher("/search.jsp").forward( request, response );
	}

}
