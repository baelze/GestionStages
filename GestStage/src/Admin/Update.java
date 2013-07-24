package admin;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
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


public class Update extends HttpServlet {
	public static final String ATT_RESULTAT = "resultat";
	public static final String VUE = "/war/update.jsp";
public void doPost( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
{
	//JUSTE L"EMAIL SUFFIRA
	String email=request.getParameter("email");
	
	//String formation=request.getParameter("formation");
	
	
	// BASE DE DONNEES
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	@SuppressWarnings("deprecation")
	Query q = new Query("Person").addFilter("Email",Query.FilterOperator.EQUAL,email);

	PreparedQuery pq = datastore.prepare(q);
	Entity result = pq.asSingleEntity();
	
	Key cle=result.getKey();
	
	String code_fonction=(String) result.getProperty("code_fonction");
	String nom=(String) result.getProperty("nom");
	String prenom=(String) result.getProperty("prenom");
	String fonction=(String) result.getProperty("fonction");
	String email=(String) result.getProperty("email");
	String password=(String) result.getProperty("password");
	
	/* Suppression de l'ENTITE*/
	datastore.delete(cle);
	
	// RE-ENREGISTREMENT DES L'ENTITE AVEC LES NOUVELLES VALEURS
	
	Entity admin = new Entity("admin");
	admin.setProperty("code_fonction",code_fonction);
	admin.setProperty("nom", nom);
	admin.setProperty("prenom",prenom);
	admin.setProperty("fonction", fonction);
	admin.setProperty("email", email); 
	admin.setProperty("password",password);
	
	datastore.put(admin);
	
	/* Stockage du résultat  dans l'objet request qui sera transmis a la VUE*/
	
	request.setAttribute( ATT_RESULTAT, result );
	/* Transmission de la paire d'objets request à	notre JSP */
	this.getServletContext().getRequestDispatcher( VUE).forward( request, response );
	
}

}