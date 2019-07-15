package levy.daniel.application.model.utilitaires.reseau;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE SocketClient :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 15 juil. 2019
 *
 */
public final class SocketClient {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SocketClient.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private SocketClient() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 *
	 * @param pAdresseIpProxy : String : adresse IP du PROXY (par exemple : )
	 * @param pPortProxy
	 * @param pTypeProxy
	 * 
	 * @return : Proxy :  .<br/>
	 */
	public static Proxy creerProxy(String pAdresseIpProxy, int pPortProxy, Proxy.Type pTypeProxy) {
		
		final SocketAddress proxyAdress 
			= new InetSocketAddress(pAdresseIpProxy, pPortProxy);
		
		final Proxy proxy = new Proxy(pTypeProxy, proxyAdress);
		
	}
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 */
	public static void main(
			final String... pArgs) {
		// TODO Auto-generated method stub

	} // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE SocketClient.------------------------------------------
