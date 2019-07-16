package levy.daniel.application.model.utilitaires.reseau;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ConnecteurFTP :<br/>
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
 * @since 16 juil. 2019
 *
 */
public class ConnecteurFTP {

	// ************************ATTRIBUTS************************************/

	/**
	 * Boolean determinant si les méthodes loggent dans la console.<br/>
	 */
	private static final boolean DEBUG = true;
	
	/**
	 * Login du serveur FTP.
	 */
	private String user;
	
	/**
	 * Mot de passe du serveur FTP.
	 */
	private String password;
	
	/**
	 * nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 */
	private String hostFTP;
	
	/**
	 * port du serveur FTP (21 par défaut).
	 */
	private int port;
	
	/**
	 * Proxy éventuellement présent sur le trajet du présent client FTP.
	 */
	private Proxy proxy;
	
	/**
	 * Socket de connexion au PROXY.
	 */
	private Socket socketProxy;
	
	/**
	 * Socket de connexion au serveur FTP 
	 * (mode BASE pour transmettre des ordres au serveur FTP).
	 */
	private Socket socketCanalServeur;
	
	/**
	 * Socket de connexion au serveur FTP 
	 * (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	 */
	private Socket socketCanalDonnees;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ConnecteurFTP.class);



	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConnecteurFTP() {
		this(null, null, null, 21);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pUser : String : Login du serveur FTP
	 * @param pPassword : String : Mot de passe du serveur FTP
	 * @param pHostFTP : String : nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 * @param pPort : int : port du serveur FTP (21 par défaut).
	 */
	public ConnecteurFTP(
			final String pUser, final String pPassword
			, final String pHostFTP, final int pPort) {
		
		super();
		
		this.user = pUser;
		this.password = pPassword;
		this.hostFTP = pHostFTP;
		this.port = pPort;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		final String adresseIpProxy = "10.77.32.65"; // NOPMD by daniel.levy on 16/07/19 12:10
		final int portProxy = 8080;
		final Proxy.Type typeProxy = Proxy.Type.SOCKS;
		
		final String user = "admin";
		final String password = "admin";
		final String hostFTP = "127.0.0.1"; // NOPMD by daniel.levy on 16/07/19 12:10
		final int portFTP = 21;
		
		final ConnecteurFTP connecteurFTP 
			= new ConnecteurFTP(user, password, hostFTP, portFTP);
		
		final Proxy proxy 
			= connecteurFTP.creerProxy(adresseIpProxy, portProxy, typeProxy);
		
		connecteurFTP.connecterViaProxy(proxy);
		
	} // Fin de main(...)._________________________________________________

	
	
	/**
	 * retourne une adresse IP à partir d'un nom de domaine 
	 * ou d'une adresse IP d'un serveur à contacter.<br/>
	 * Par exemple : retourne "151.101.122.217" 
	 * que l'on saisisse "www.lemonde.fr" ou "151.101.122.217" en paramètre.
	 *
	 * @param pAdresseIpSite : String : 
	 * adresse IP ou nom de domaine d'un serveur
	 * 
	 * @return : String : adresse IP du serveur.
	 * 
	 * @throws UnknownHostException
	 */
	private String fournirIpAPartirHote(
			final String pAdresseIpSite) 
								throws UnknownHostException {
				
		String adresseIpDistante = null;
		
		// Il s'agit d'un nom de domaine et non d'une adresse IPV4
		if (pAdresseIpSite.matches("[a-zA-Z\\.]+")) {
			
			adresseIpDistante 
				= InetAddress.getByName(pAdresseIpSite).getHostAddress();
			
		}		
		// IP V4
		else {
			adresseIpDistante = pAdresseIpSite;
		}
		
		return adresseIpDistante;

	} // Fin de fournirIpAPartirHote(...)._________________________________

	
	
	/**
	 * lit la réponse du serveur FTP (CANAL SERVEUR) et 
	 * la retourne sous forme de String.<br/>
	 *
	 * @return String : réponse du serveur FTP (CANAL SERVEUR)
	 * 
	 * @throws Exception
	 */
	private String readReponseFTPCanalServeur() throws Exception {
		
		String response = "";
		int stream = 0;
		
		final byte[] bytes = new byte[4096];
		
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		
		try {
			
			inputStream = this.socketCanalServeur.getInputStream();
			bufferedInputStream = new BufferedInputStream(inputStream);
			
			stream = bufferedInputStream.read(bytes);
			
			response = new String(bytes, 0, stream);

			if (DEBUG) {
				LOG.debug(response);
			}
							
		} catch (Exception e) { // NOPMD by daniel.levy on 16/07/19 11:58
			
			throw new Exception(e);
			
		} finally {
			
			if (inputStream != null) {
				inputStream.close();
			}
			
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			
		}
		
		return response;
		
	} // Fin de readReponseFTPCanalServeur().______________________________
		
	

	/**
	 * .<br/>
	 * : void :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public void connecterDirectement() throws Exception {

		final String adresseServeurFTP = this.fournirIpAPartirHote(this.hostFTP);

		this.socketCanalServeur = new Socket(adresseServeurFTP, this.port);

		final String responseServeurFTP = this.readReponseFTPCanalServeur();

		if (!responseServeurFTP.startsWith("220")) {
			throw new IOException(
					"Erreur de connexion au FTP : \n" + responseServeurFTP);
		}

	} // Fin de connecterDirectement().____________________________________
	
		
	
	/**
	 * .<br/>
	 *
	 * @param pProxy
	 * @throws Exception : void :  .<br/>
	 */
	public void connecterViaProxy(final Proxy pProxy) throws Exception {
		
		final String adresseServeurFTP 
			= this.fournirIpAPartirHote(this.hostFTP);
		
		this.socketProxy = new Socket(pProxy);
		
		final SocketAddress socketAddressServeurFTP 
			= new InetSocketAddress(adresseServeurFTP, this.port);
		
		this.socketProxy.connect(socketAddressServeurFTP);
		
		this.socketCanalServeur = new Socket(adresseServeurFTP, this.port);

		final String responseServeurFTP = this.readReponseFTPCanalServeur();

		if (!responseServeurFTP.startsWith("220")) {
			throw new IOException(
					"Erreur de connexion au FTP : \n" + responseServeurFTP);
		}
		
	} // Fin de connecterViaProxy(...).____________________________________
	
	
	
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
	public Proxy creerProxy(
			final String pAdresseIpProxy
				, final int pPortProxy
					, final Proxy.Type pTypeProxy) throws Exception {
		
		final SocketAddress proxyAdress 
			= new InetSocketAddress(pAdresseIpProxy, pPortProxy);
		
		this.proxy = new Proxy(pTypeProxy, proxyAdress);
		
		return this.proxy;
		
	} // Fin de creerProxy(...).___________________________________________

	
	
	/**
	 * Getter du Login du serveur FTP.
	 *
	 * @return this.user : String.<br/>
	 */
	public final String getUser() {
		return this.user;
	} // Fin de getUser()._________________________________________________
	
	
	
	/**
	* Setter du Login du serveur FTP.
	*
	* @param pUser : String : 
	* valeur à passer à this.user.<br/>
	*/
	public final void setUser(
			final String pUser) {
		this.user = pUser;
	} // Fin de setUser(...).______________________________________________
	
	
	
	/**
	 * Getter du Mot de passe du serveur FTP.
	 *
	 * @return this.password : String.<br/>
	 */
	public final String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________
	
	
	
	/**
	* Setter du Mot de passe du serveur FTP.
	*
	* @param pPassword : String : 
	* valeur à passer à this.password.<br/>
	*/
	public final void setPassword(
			final String pPassword) {
		this.password = pPassword;
	} // Fin de setPassword(...).__________________________________________
	
	
	
	/**
	 * Getter du nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 *
	 * @return this.hostFTP : String.<br/>
	 */
	public final String getHostFTP() {
		return this.hostFTP;
	} // Fin de getHostFTP().______________________________________________
	
	
	
	/**
	* Setter du nom d'hôte du serveur FTP.<br/>
	* Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	*
	* @param pHostFTP : String : 
	* valeur à passer à this.hostFTP.<br/>
	*/
	public final void setHostFTP(
			final String pHostFTP) {
		this.hostFTP = pHostFTP;
	} // Fin de setHostFTP(...).___________________________________________
	
	
	
	/**
	 * Getter du port du serveur FTP (21 par défaut).
	 *
	 * @return this.port : int.<br/>
	 */
	public final int getPort() {
		return this.port;
	} // Fin de getPort()._________________________________________________
	
	
	
	/**
	* Setter du port du serveur FTP (21 par défaut).
	*
	* @param pPort : int : 
	* valeur à passer à this.port.<br/>
	*/
	public final void setPort(
			final int pPort) {
		this.port = pPort;
	} // Fin de setPort(...).______________________________________________


	
	/**
	 * Getter du Proxy éventuellement présent 
	 * sur le trajet du présent client FTP.
	 *
	 * @return this.proxy : Proxy.<br/>
	 */
	public final Proxy getProxy() {
		return this.proxy;
	} // Fin de getProxy().________________________________________________


	
	/**
	* Setter du Proxy éventuellement présent 
	* sur le trajet du présent client FTP.
	*
	* @param pProxy : Proxy : 
	* valeur à passer à this.proxy.<br/>
	*/
	public final void setProxy(
			final Proxy pProxy) {
		this.proxy = pProxy;
	} // Fin de setProxy(...)._____________________________________________


	
	/**
	 * Getter du Socket de connexion au PROXY.
	 *
	 * @return this.socketProxy : Socket.<br/>
	 */
	public final Socket getSocketProxy() {
		return this.socketProxy;
	} // Fin de getSocketProxy().__________________________________________


	
	/**
	* Setter du Socket de connexion au PROXY.
	*
	* @param pSocketProxy : Socket : 
	* valeur à passer à this.socketProxy.<br/>
	*/
	public final void setSocketProxy(
			final Socket pSocketProxy) {
		this.socketProxy = pSocketProxy;
	} // Fin de setSocketProxy(...)._______________________________________


	
	/**
	 * Getter du Socket de connexion au serveur FTP 
	 * (mode BASE pour transmettre des ordres au serveur FTP).
	 *
	 * @return this.socketCanalServeur : Socket.<br/>
	 */
	public final Socket getSocketCanalServeur() {
		return this.socketCanalServeur;
	} // Fin de getSocketCanalServeur().___________________________________


	
	/**
	* Setter du Socket de connexion au serveur FTP 
	* (mode BASE pour transmettre des ordres au serveur FTP).
	*
	* @param pSocketCanalServeur : Socket : 
	* valeur à passer à this.socketCanalServeur.<br/>
	*/
	public final void setSocketCanalServeur(
			final Socket pSocketCanalServeur) {
		this.socketCanalServeur = pSocketCanalServeur;
	} // Fin de setSocketCanalServeur(...).________________________________


	
	/**
	 * Getter du Socket de connexion au serveur FTP 
	 * (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	 *
	 * @return this.socketCanalDonnees : Socket.<br/>
	 */
	public final Socket getSocketCanalDonnees() {
		return this.socketCanalDonnees;
	} // Fin de getSocketCanalDonnees().___________________________________


	
	/**
	* Setter du Socket de connexion au serveur FTP 
	* (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	*
	* @param pSocketCanalDonnees : Socket : 
	* valeur à passer à this.socketCanalDonnees.<br/>
	*/
	public final void setSocketCanalDonnees(
			final Socket pSocketCanalDonnees) {
		this.socketCanalDonnees = pSocketCanalDonnees;
	} // Fin de setSocketCanalDonnees(...).________________________________

	
		
} // FIN DE LA CLASSE ConnecteurFTP.-----------------------------------------
