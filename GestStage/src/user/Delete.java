package user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({ "unused", "serial" })


public class Delete extends HttpServlet {
	public static final String VUE = "/war/delete.jsp";
public void doPost( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
{
	String email=request.getParameter("email");
	
	// BASE DE DONNEES
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	@SuppressWarnings("deprecation")
	Query q = new Query("Person").addFilter("Email",Query.FilterOperator.EQUAL,email);

	PreparedQuery pq = datastore.prepare(q);
	Entity result = pq.asSingleEntity();
	
	Key cle=result.getKey();

	/* Suppression de l'ENTITE*/
	datastore.delete(cle);
	
	/* Transmission de la paire d'objets request à	notre JSP */
	this.getServletContext().getRequestDispatcher( VUE).forward( request, response );
	
	
	
}

}