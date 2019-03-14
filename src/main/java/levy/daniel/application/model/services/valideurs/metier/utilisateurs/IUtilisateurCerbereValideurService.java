package levy.daniel.application.model.services.valideurs.metier.utilisateurs;

import java.util.Map;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;

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
	 * et retourne une Map&lt;String,String&gt; contenant les 
	 * éventuels messages d'erreur pour chaque champ avec :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>String : le message d'erreur pour l'attribut</li>
	 * </ul>
	 * - la Map retournée n'est jamais null. TESTER si elle est VIDE.<br/>
	 * - retourne null si pDto == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : DTO.<br/>
	 * @return : Map&lt;String,String&gt; : 
	 * map des messages d'erreur pour chaque champ.<br/>
	 * 
	 * @throws Exception 
	 */
	Map<String, String> valider(IUtilisateurCerbereDTO pDto) 
													throws Exception;
	
	
	
} // FIN DE L'INTERFACE IUtilisateurCerbereValideurService.------------------
