package levy.daniel.application.model.utilitaires.reseau;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE Reseau :<br/>
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
 * @since 13 juil. 2019
 *
 */
public class Reseau {

	// ************************ATTRIBUTS************************************/

	/**
	 * InetAddress.
	 */
	private static InetAddress address;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Reseau.class);

	// *************************METHODES************************************/
	
	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 *
	 * @param pArgs : String[].<br/>
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
//		InetAddress addresseIpPriveeLocale = connaitreAdressePriveeLocale();
//		showInformations(addresseIpPriveeLocale, "HOTE LOCAL - InetAddress.getLocalHost()");
//		
//		final String adresseIpString = "10.77.222.142";
//		InetAddress addresseIp = connaitreServeurParIp(adresseIpString);
//		showInformations(addresseIp, adresseIpString + " - InetAddress.getByAddress(byteTableau)");
//		
//		final String nomServeur = "www.google.net";
//		InetAddress adrServeur = connaitreAdresseIPparNomServeur(nomServeur);
//		showInformations(adrServeur, nomServeur + " - InetAddress.getByName(pHostName)");
//		
//		System.out.println();
//		final String nomServeurFr = "www.lemonde.fr";
//		InetAddress[] adrServeurs = connaitreToutesAdressesIPparNomServeur(nomServeurFr);
//		showInformations(adrServeurs, nomServeurFr + " - InetAddress.getAllByName(pHostName)");
//		
//		System.out.println();
//		montrerToutesCartesReseaux();
//		
//		System.out.println();
//		final String total = "192.168.1.199/10";
//		calculerPrefixeAdresseMasque(total);
		
		final URL url1 = new URL("http://www.google.fr:80");
		expliciterURL(url1);
		
	} // Fin de main(...)._________________________________________________

	
	
	/**
	 * retourne l'<b>adresse IP privée externe LOCALE 
	 * de la présente machine au sein du réseau LOCAL</b>.
	 * <ul>
	 * <li>Par exemple 10.77.222.142 sur le réseau local wifidtecitm 
	 * pour la présente machine localhost.</li>
	 * <li>adresse <b>interne</b> au réseau local.<br/>
	 * Cette adresse IP n'est <b>pas visible depuis l'Internet</b>.<br/>
	 * Elle sert à la conversation entre le routeur local 
	 * et la présente machine.</li>
	 * <li>Les plages d'addresses IP réservées aux réseaux locaux sont :
	 * <ul>
	 * <li>10.0.0.0 à 10.255.255.255</li>
	 * <li>100.64.0.0 à 100.127.255.255</li>
	 * <li>172.16.0.0 à 172.31.255.255</li>
	 * <li>192.168.1.0 à 192.168.255.255</li>
	 * </ul>
	 * </li>
	 * <li>utilise <code><b>InetAddress.getLocalHost()</b></code></li>
	 * </ul>
	 *
	 * @return : InetAddress : adresse IP privée externe LOCALE.<br/>
	 * 
	 * @throws UnknownHostException 
	 */
	public static InetAddress connaitreAdressePriveeLocale() 
			throws UnknownHostException {
		
		final InetAddress adresseIPExterneLocale = InetAddress.getLocalHost();
		
		return adresseIPExterneLocale;
		
	} // Fin de connaitreAdressePriveeLocale().____________________________

	
	
	/**
	 * retourne les informations sur le serveur à pIp.<br/>
	 * <br/>
	 *
	 * @param pAdresseIp : String : adresse IP.
	 * 
	 * @return InetAddress
	 * @throws UnknownHostException
	 */
	public static InetAddress connaitreServeurParIp(
										final String pAdresseIp) 
												throws UnknownHostException {
		
		final String[] tokens = StringUtils.split(pAdresseIp, '.');
		
		final int tailleTokens = tokens.length;
		
		final byte[] byteTableau = new byte[tailleTokens];
		
		for (int i = 0; i < tailleTokens; i++) {
			
			final String tokenEnString = tokens[i];
			
			Integer tokenEnInteger = Integer.valueOf(tokenEnString);
			
			Byte tokenEnByte = null;
			
			if (tokenEnInteger < 128) {
				tokenEnByte = (byte) Integer.parseInt(tokenEnString, 10);
			} else {
				tokenEnByte = (byte) Integer.parseInt(tokenEnString, 10);
			}
					
			byteTableau[i] = tokenEnByte;
			
		}
		
		final InetAddress adresseIP = InetAddress.getByAddress(byteTableau);
		
		return adresseIP;
		
	} // Fin de connaitreServeurParIp(...).________________________________

	
	
	/**
	 * retourne l'adresse IP et les informations 
	 * du serveur nommé pHostName.<br/>
	 * <br/>
	 * Par exemple : "www.google.net"
	 *
	 * @param pHostName : String : nom du serveur comme "www.google.net"
	 * 
	 * @return InetAddress
	 * 
	 * @throws UnknownHostException
	 */
	public static InetAddress connaitreAdresseIPparNomServeur(
				final String pHostName) 
						throws UnknownHostException {
		
		final InetAddress adresse = InetAddress.getByName(pHostName);
		
		return adresse;
		
	} // Fin de connaitreAdresseIPparNomServeur(...).______________________

	
	
	/**
	 * retourne toutes les adresses IP d'un Serveur.<br/>
	 *
	 * @param pHostName : String : nom du serveur comme "www.google.net"
	 * 
	 * @return InetAddress[] : tableau de toutes les adresses Ip d'un Serveur.
	 * 
	 * @throws UnknownHostException
	 */
	public static InetAddress[] connaitreToutesAdressesIPparNomServeur(
			final String pHostName) 
			throws UnknownHostException {

		final InetAddress[] adresses = InetAddress.getAllByName(pHostName);

		return adresses;

	} // Fin de connaitreToutesAdressesIPparNomServeur(...)._______________
	

	
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
	 * affiche les informations sur toutes les cartes réseau 
	 * de la présente machine.<br/>
	 * 
	 * @throws SocketException 
	 */
	public static void montrerToutesCartesReseaux() throws SocketException {

		final Enumeration<NetworkInterface> list 
			= NetworkInterface.getNetworkInterfaces();

		while (list.hasMoreElements()) {

			final NetworkInterface ni = list.nextElement();
			final Enumeration<InetAddress> listAddress = ni.getInetAddresses();

			while (listAddress.hasMoreElements()) {
				final InetAddress addresseLue = listAddress.nextElement();
				showInformations(addresseLue, addresseLue.getHostName());
			}
		}
		
	} // Fin de montrerToutesCartesReseaux().______________________________
	

	
	/**
	 * calcule .<br/>
	 * <br/>
	 *
	 * @param pString : void :  .<br/>
	 */
	public static void calculerPrefixeAdresseMasque(
			final String pString) {
		
        
        String[] parties = pString.split("/");
        String ip = parties[0];
        int prefixe;
        if (parties.length < 2) {
            prefixe = 0;
        } else {
            prefixe = Integer.parseInt(parties[1]);
        }
        System.out.println("Addresse =\t" + ip+"\nPrefixe =\t" + prefixe);
       
        //convertir le masque entier en un tableau de 32bits
        int masque = 0xffffffff << (32 - prefixe);
        int valeur = masque;
        byte[] bytesMasque = new byte[]{
                (byte) (valeur >>> 24), (byte) (valeur >> 16 & 0xff), (byte) (valeur >> 8 & 0xff), (byte) (valeur & 0xff) };

        try {
              //masque
              InetAddress netAddr = InetAddress.getByAddress(bytesMasque);
              System.out.println("Masque =\t" + netAddr.getHostAddress());
                     
         /*************************
          * Adresse réseau
          */
         //Convertir l'pString IP en long
         long ipl = ipToLong(ip);
             
         //Convertir l'IP en un tableau de 32bits
         byte[] bytesIp = new byte[]{
          (byte) ((ipl >> 24) & 0xFF),
         (byte) ((ipl >> 16) & 0xFF),
          (byte) ((ipl >> 8) & 0xFF),
          (byte) (ipl & 0xFF)};
        
         //Le ET logique entre l'pString IP et le masque
         byte[] bytesReseau = new byte[]{
                 (byte) (bytesIp[0] & bytesMasque[0]),
                 (byte) (bytesIp[1] & bytesMasque[1]),
                 (byte) (bytesIp[2] & bytesMasque[2]),
                 (byte) (bytesIp[3] & bytesMasque[3]),
         };
         //pString réseau obtenue
         InetAddress adrReseau = InetAddress.getByAddress(bytesReseau);
         System.out.println("Adresse réseau =\t"+adrReseau.getHostAddress());
        
         /********************************
          *Adresse de diffusion broadcast            
         */
         //pString réseau - masque inversé ~val & 0xff
         //inverser le masque
         bytesMasque = new byte[]{
                 (byte) (~bytesMasque[0] & 0xff),
                 (byte) (~bytesMasque[1] & 0xff),
                 (byte) (~bytesMasque[2] & 0xff),
                 (byte) (~bytesMasque[3] & 0xff),
         };
         System.out.println("Masque inverse (Wildcard) =\t"+InetAddress.getByAddress(bytesMasque).getHostAddress());
        
         byte[] bytesBroadcast = new byte[]{
                 (byte) (bytesReseau[0] | bytesMasque[0]),
                 (byte) (bytesReseau[1] | bytesMasque[1]),
                 (byte) (bytesReseau[2] | bytesMasque[2]),
                 (byte) (bytesReseau[3] | bytesMasque[3]),
         };
         //pString Broadcast obtenue
         InetAddress adrbroadcast = InetAddress.getByAddress(bytesBroadcast);
         System.out.println("Adresse de diffusion (Broadcast) =\t"+adrbroadcast.getHostAddress());
        
          } catch (UnknownHostException e) {
                 e.printStackTrace();
          }
        
    } // Fin de calculerPrefixeAdresseMasque(...)._________________________


	
     /**
     * .<br/>
     * <br/>
     *
     * @param ipAddress
     * @return : long :  .<br/>
     */
    public static long ipToLong(
    		final String ipAddress) { 
    	
             long result = 0;   
             String[] ipAddressInArray = ipAddress.split("\\.");

             for (int i = 3; i >= 0; i--) {
                  long ip = Long.parseLong(ipAddressInArray[3 - i]);
                  result |= ip << (i * 8);
             }
             
             return result;
             
    } // Fin de ipToLong(...)._____________________________________________
		
	
		
	/**
	 * Affiche les informations sur le serveur correspondant à pAddress.
	 *
	 * @param pAddress : InetAddress
	 * @param pName : String
	 */
	public static void showInformations(
			final InetAddress pAddress, final String pName) {

		System.out.println();
		System.out.println("------------------------------------------------------------------------");
		System.out.println("INFORMATIONS SUR LE SERVEUR : " + pName);
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Nom (getHostName()) : " + pAddress.getHostName());
		System.out.println("Adresse (getHostAddress()) : " + pAddress.getHostAddress());
		System.out.println("Nom canonique(getCanonicalHostName()) : " + pAddress.getCanonicalHostName());
		
		// Cette méthode nous retourne un tableau de byte
		final byte[] bAddress = pAddress.getAddress();
		
		String ip = "";
		
		for (final byte b : bAddress) {
			ip += (b & 0xFF) + "."; // L'instruction & 0xFF permet d'avoir la valeur non signée
		}

		System.out.println("Adresse IP depuis tableau de byte : " + ip);

	} // Fin de showInformations(...)._____________________________________

	
	
	/**
	 * Affiche les informations sur les serveurs correspondant à pAddresses.
	 *
	 * @param pAddresses : InetAddress[] : tableau d'adresses IP 
	 * @param pName : String.
	 */
	public static void showInformations(
			final InetAddress[] pAddresses, final String pName) {
		
		for (int i = 0; i < pAddresses.length; i++) {
			showInformations(pAddresses[i], pName);
		}
		
	} // Fin de showInformations(...)._____________________________________
	
	
	
} // FIN DE LA CLASSE Reseau.------------------------------------------------
