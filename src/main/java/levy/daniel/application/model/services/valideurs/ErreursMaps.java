package levy.daniel.application.model.services.valideurs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ErreursMaps :<br/>
 * Encapsulation (PURE FABRICATION) chargée de contenir deux Maps 
 * contenant les erreurs lors de la validation d'un OBJET METIER 
 * par un service.<br/>
 * <ul>
 * <li>une Map&lt;String,String&gt; <code>errorsMap</code> contenant les 
 * éventuels messages d'erreur pour chaque attribut avec 
 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
 * <ul>
 * <li>String : le nom de l'attribut</li>
 * <li>String : le message d'erreur pour l'attribut concaténé 
 * sur une seule ligne</li>
 * </ul>
 * </li>
 * <li>une Map&lt;String,List&lt;String&gt;&gt; <code>errorsMapDetaille</code> 
 * contenant les éventuels messages d'erreur pour chaque attribut avec 
 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
 * pour chaque attribut :
 * <ul>
 * <li>String : le nom de l'attribut</li>
 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
 * dans une liste avec une entrée par RG violée par l'attribut</li>
 * </ul>
 * </li>
 * </ul>
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
 * @since 25 mars 2019
 *
 */
public class ErreursMaps {

	// ************************ATTRIBUTS************************************/

	/**
	 * Map&lt;String,String&gt; contenant les 
	 * éventuels messages d'erreur pour chaque attribut avec 
	 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>String : le message d'erreur pour l'attribut concaténé 
	 * sur une seule ligne</li>
	 * </ul>
	 */
	private Map<String, String> errorsMap 
		= new ConcurrentHashMap<String, String>();
	
	/**
	 * Map&lt;String,List&lt;String&gt;&gt;  
	 * contenant les éventuels messages d'erreur pour chaque attribut avec 
	 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	 * pour chaque attribut :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	 * dans une liste avec une entrée par RG violée par l'attribut</li>
	 * </ul>
	 */
	private Map<String, List<String>> errorsMapDetaille 
		= new ConcurrentHashMap<String, List<String>>();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ErreursMaps.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ErreursMaps() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * Getter de la Map&lt;String,String&gt; contenant les 
	 * éventuels messages d'erreur pour chaque attribut avec 
	 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>String : le message d'erreur pour l'attribut concaténé 
	 * sur une seule ligne</li>
	 * </ul>
	 *
	 * @return this.errorsMap : Map&lt;String,String&gt;.<br/>
	 */
	public final Map<String, String> getErrorsMap() {
		return this.errorsMap;
	} // Fin de getErrorsMap().____________________________________________


	
	/**
	* Setter de la Map&lt;String,String&gt; contenant les 
	* éventuels messages d'erreur pour chaque attribut avec 
	* toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	* <ul>
	* <li>String : le nom de l'attribut</li>
	* <li>String : le message d'erreur pour l'attribut concaténé 
	* sur une seule ligne</li>
	* </ul>
	*
	* @param pErrorsMap : Map&lt;String,String&gt; : 
	* valeur à passer à this.errorsMap.<br/>
	*/
	public final void setErrorsMap(
			final Map<String, String> pErrorsMap) {
		this.errorsMap = pErrorsMap;
	} // Fin de setErrorsMap(...)._________________________________________

	
	
	/**
	 * Getter de la Map&lt;String,List&lt;String&gt;&gt;  
	 * contenant les éventuels messages d'erreur pour chaque attribut avec 
	 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	 * pour chaque attribut :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	 * dans une liste avec une entrée par RG violée par l'attribut</li>
	 * </ul>
	 *
	 * @return this.errorsMapDetaille : 
	 * Map&lt;String,List&lt;String&gt;&gt;.<br/>
	 */
	public final Map<String, List<String>> getErrorsMapDetaille() {
		return this.errorsMapDetaille;
	} // Fin de getErrorsMapDetaille().____________________________________


	
	/**
	* Setter de la Map&lt;String,List&lt;String&gt;&gt;  
	* contenant les éventuels messages d'erreur pour chaque attribut avec 
	* chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	* pour chaque attribut :
	* <ul>
	* <li>String : le nom de l'attribut</li>
	* <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	* dans une liste avec une entrée par RG violée par l'attribut</li>
	* </ul>
	*
	* @param pErrorsMapDetaille : Map&lt;String,List&lt;String&gt;&gt; : 
	* valeur à passer à this.errorsMapDetaille.<br/>
	*/
	public final void setErrorsMapDetaille(
			final Map<String, List<String>> pErrorsMapDetaille) {
		this.errorsMapDetaille = pErrorsMapDetaille;
	} // Fin de setErrorsMapDetaille(...)._________________________________
	
		
	
} // FIN DE LA CLASSE ErreursMaps.-------------------------------------------
