package levy.daniel.application.model.services.valideurs.metier.utilisateurs;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.valideurs.ErreursMaps;

/**
 * INTERFACE IUtilisateurCerbereValideurService :<br/>
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
 * @since 10 mars 2019
 *
 */
public interface IUtilisateurCerbereValideurService {

	
	
	/**
	 * applique les REGLES DE GESTION sur chaque attribut d'un DTO 
	 * et retourne une Encapsulation <code>ErreurMaps</code>.<br/>
	 * ErreurMaps est une PURE FABRICATION chargée de contenir deux Maps 
	 * contenant les erreurs lors de la validation d'un OBJET METIER 
	 * par un service.
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
	 * - les Maps retournées ne sont jamais null. 
	 * TESTER si elle sont VIDES.<br/>
	 * - retourne null si pDto == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : DTO.<br/>
	 * @return : ErreursMaps : 
	 * pure fabrication contenant les maps des messages 
	 * d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	ErreursMaps valider(IUtilisateurCerbereDTO pDto) 
													throws Exception;
	
	
	
} // FIN DE L'INTERFACE IUtilisateurCerbereValideurService.------------------
