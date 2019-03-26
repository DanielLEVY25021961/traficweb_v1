package levy.daniel.application.model.services.valideurs.metier.utilisateurs;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.valideurs.ErreursMaps;

/**
 * INTERFACE IUtilisateurCerbereValideurService :<br/>
 * INTERFACE modélisant les SERVICES VALIDEURS chargés 
 * de valider toutes les Règles de Gestion (RG) 
 * s'appliquant à tous les attributs du DTO de l'OBJET METIER.<br/>
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
	 * <b>applique les REGLES DE GESTION sur chaque attribut d'un DTO</b> 
	 * et retourne une Encapsulation <code>{@link ErreursMaps}</code>.<br/>
	 * ErreurMaps est une PURE FABRICATION chargée de contenir deux Maps 
	 * contenant les erreurs lors de la validation d'un OBJET METIER 
	 * par un service.<br/>
	 * Les deux Maps (errorsMap et errorsMapDetaille) 
	 * sont VIDES OU PAS ENSEMBLE.<br/>
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
	 * 
	 * <p>
	 * <b><span style="text-decoration: underline;">
	 * Fonctionnement de la validation (diagramme de séquence) : 
	 * </span>
	 * </p>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/model/services/valideurs/service_valideur-methode_valider-sequence_1.png" 
	 * alt="diagramme de séquence de la validation" />
	 * </p>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/model/services/valideurs/service_valideur-methode_valider-sequence_2.png" 
	 * alt="diagramme de séquence de la validation" />
	 * </p>
	 * 
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
