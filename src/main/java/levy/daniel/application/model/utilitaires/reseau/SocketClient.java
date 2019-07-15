package levy.daniel.application.model.utilitaires.reseau;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

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
	 * crée une abstraction d'un PROXY présent sur le réseau.
	 *
	 * @param pAdresseIpProxy : String : 
	 * adresse IP du PROXY (par exemple : "10.77.32.65" pour le PROXY du CEREMA)
	 * @param pPortProxy : int : 
	 * numéro de Port du PROXY (par exemple : 8080 pour le PROXY du CEREMA)
	 * @param pTypeProxy : java.net.Proxy.Type : 
	 * <ul>
	 * <li>Proxy.Type.SOCKS : 
	 * acronyme de Secured Over Credential-based Kerberos</li>
	 * <li>Proxy.Type.HTTP : proxy HTTP</li>
	 * <li>Proxy.Type.DIRECT : pas de Proxy</li>
	 * </ul>
	 * 
	 * @return : java.net.Proxy : .<br/>
	 * 
	 * @throws Exception 
	 */
	public static Proxy creerProxy(
			final String pAdresseIpProxy
				, final int pPortProxy
					, final Proxy.Type pTypeProxy) throws Exception {
		
		final SocketAddress proxyAdress 
			= new InetSocketAddress(pAdresseIpProxy, pPortProxy);
		
		final Proxy proxy = new Proxy(pTypeProxy, proxyAdress);
		
		return proxy;
		
	} // Fin de creerProxy(...).___________________________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pAdresseIpSite
	 * @param pPortProtocole : int :
	 * @param pProxy : java.net.Proxy :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static void creerConnexionViaProxy(
			final String pAdresseIpSite
				, final int pPortProtocole
					, final Proxy pProxy) throws IOException {
		
		final Socket socketProxy = new Socket(pProxy);
		
		final SocketAddress socketAddressRemote 
			= new InetSocketAddress(pAdresseIpSite, pPortProtocole);
		
		try {

			socketProxy.connect(socketAddressRemote);

			System.out.println("Port de communication côté serveur : " + socketProxy.getPort());
			System.out.println("Port de communication côté client : " + socketProxy.getLocalPort());
			System.out.println("Nom de l'hôte distant : " + socketProxy.getInetAddress().getHostName());
			System.out.println("Adresse de l'hôte distant : " + socketProxy.getInetAddress().getHostAddress());
			System.out.println("Adresse socket de l'hôte distant : " + socketProxy.getRemoteSocketAddress());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
			socketProxy.close();
		}
		
	} // Fin de creerConnexionViaProxy(...)._______________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		final String adresseIpProxy = "10.77.32.65";
		final int portProxy = 8080;
		final Proxy.Type typeProxy = Proxy.Type.SOCKS;
		
		final Proxy proxy = creerProxy(adresseIpProxy, portProxy, typeProxy);
		
		/* www.lemonde.fr */
		final String adresseSiteRemote = "151.101.122.217";
		final int portProtocoleHTTP = 80;
		
		creerConnexionViaProxy(adresseSiteRemote, portProtocoleHTTP, proxy);

	} // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE SocketClient.------------------------------------------
