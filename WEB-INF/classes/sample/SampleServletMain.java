package sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class SampleServletMain
 */
@WebServlet("/SampleServletMain")
public class SampleServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServletMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<SampleModel> list = new ArrayList<SampleModel>();
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
	    
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
	    
	    SqlSession session = factory.openSession();
	    
	    ArrayList<Object> selectList
	      = (ArrayList<Object>) session.selectList("selectAll");
	    
	    for (Object obj : selectList) {

	   	 if (obj instanceof SampleDto) {
	        SampleDto dto = (SampleDto) obj;
	        list.add(new SampleModel(dto.getId(), dto.getName()));
	        System.out.println(dto.getId() + " : " + dto.getName());
	      }
	   	 request.setAttribute("list", list);
	    }
	    getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
