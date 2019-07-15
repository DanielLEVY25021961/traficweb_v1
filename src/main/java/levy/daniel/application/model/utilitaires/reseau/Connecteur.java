package levy.daniel.application.model.utilitaires.reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE Connecteur :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 14 juil. 2019
 *
 */
public final class Connecteur {

	// ************************ATTRIBUTS************************************/

	/**
	 * Browser.
	 */
	private static Browser browser;
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Connecteur.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private Connecteur() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUrl : java.net.URL :  .<br/>
	 * @param pCharset : java.nio.charset.Charset :
	 * 
	 * @throws IOException 
	 */
	public static void connecter(
			final URL pUrl, final Charset pCharset) throws IOException {
		
		expliciterURL(pUrl);
		
		final URLConnection urlConnection = pUrl.openConnection();
		String content = "";
		String line = null;
		
		final InputStream inputStream = urlConnection.getInputStream();
		final InputStreamReader inputStreamReader 
			= new InputStreamReader(inputStream, pCharset);
		final BufferedReader bufferedReader 
			= new BufferedReader(inputStreamReader);
		
		while ((line = bufferedReader.readLine()) != null) {
			content = content + line + NEWLINE;
		}
		
		// AFFICHE LA PAGE WEB à l'URL pUrl DANS UN BROWSER. 
		browser = new Browser(pUrl.getHost(), content);
		
		inputStream.close();
		inputStreamReader.close();
		bufferedReader.close();
		
	} // Fin de connecter(...).____________________________________________

	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUrl
	 * @param pRequete
	 * @param pCharset 
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static String rechercherHTTP(
			final String pUrl
				, final String pRequete
					, final Charset pCharset) 
						throws IOException {
		
		String resultat = "";
		HttpURLConnection httpURLConnection = null;
		
		//encode les caractères spéciaux  
        //pour qu'ils soient interprétables dans une URL.
        //Nous devons fournir la chaîne à encoder et le type d'encodage, ici UTF-8
        String recherche = URLEncoder.encode("q", "UTF-8") + "=";
        recherche = recherche + URLEncoder.encode(pRequete, "UTF-8");
        
        //Nous nous connectons, via un objet HTTPUrlConnection
        //à la nouvelle URL, la recherche se faisant en GET, 
        //les paramètres sont dans l'URL
        System.out.println("URL de recherche : " + pUrl + "?" + recherche);
        
        final URL urlRequete = new URL(pUrl + "?" + recherche);
        
        httpURLConnection = (HttpURLConnection) urlRequete.openConnection();
        
		final InputStream inputStream = httpURLConnection.getInputStream();
		final InputStreamReader inputStreamReader 
			= new InputStreamReader(inputStream, pCharset);
		final BufferedReader bufferedReader 
			= new BufferedReader(inputStreamReader);
		
		String line = null;
		
		while ((line = bufferedReader.readLine()) != null) {
			resultat = resultat + line + NEWLINE;
		}
		
		return resultat;
		
	} // Fin de rechercherHTTP(...)._______________________________________
	
	
	
	/**
	 * affiche les renseignements relatifs à pUrl.
	 *
	 * @param pUrl : java.net.URL : url d'un site 
	 * comme par exemple "http://www.google.fr:80".<br/>
	 */
	public static void expliciterURL(
			final URL pUrl) {
		
        System.out.println("Authority - pUrl.getAuthority() : " + pUrl.getAuthority());
        System.out.println("Default port - pUrl.getDefaultPort() : " + pUrl.getDefaultPort());
        System.out.println("Host - pUrl.getHost() : " + pUrl.getHost());
        System.out.println("Port - pUrl.getPort() : " + pUrl.getPort());
        System.out.println("Protocol - pUrl.getProtocol() : " + pUrl.getProtocol());
        
	} // Fin de expliciterURL(...).________________________________________
	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param args : String[] :  .<br/>
	 * @throws IOException 
	 */
	public static void main(String... args) throws IOException {
		
//		final String siteWeb = "http://search.oracle.com//search/search";
		final String siteWeb = "http://www.google.fr:80";
        final URL url = new URL(siteWeb);
        connecter(url, StandardCharsets.ISO_8859_1);
        
      //Et nous faisons une recherche sur ce même site
        String result = rechercherHTTP(siteWeb, "java", StandardCharsets.ISO_8859_1);
        
        //Nous mettons à jour notre page
        browser.setContent(result);
        
        //Et nous faisons une recherche sur ce même site
//        result = rechercherHTTP(siteWeb, "java.net package & RMI", StandardCharsets.ISO_8859_1);
        
        //Nous mettons à jour notre page
//        browser.setContent(result);

	} // Fin de main(...)._________________________________________________


	
} // FIN DE LA CLASSE Connecteur.--------------------------------------------
