package entreprise;

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
	
	String libelle=(String) result.getProperty("libelle");
	String adresse=(String) result.getProperty("adresse");
	String site_web=(String) result.getProperty("site_web");
	String email=(String) result.getProperty("email");
	String password=(String) result.getProperty("password");
	
	/* Suppression de l'ENTITE*/
	datastore.delete(cle);
	
	// RE-ENREGISTREMENT DES L'ENTITE AVEC LES NOUVELLES VALEURS
	
	Entity entreprise = new Entity("entreprise");
	entreprise.setProperty("libelle",libelle);
	entreprise.setProperty("adresse", adresse);
	entreprise.setProperty("site_web",site_web);
	entreprise.setProperty("email", email); 
	entreprise.setProperty("password",password);
	
	datastore.put(entreprise);
	
	/* Stockage du résultat  dans l'objet request qui sera transmis a la VUE*/
	
	request.setAttribute( ATT_RESULTAT, result );
	/* Transmission de la paire d'objets request à	notre JSP */
	this.getServletContext().getRequestDispatcher( VUE).forward( request, response );
	
}

}