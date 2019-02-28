package levy.daniel.application.model.services.metier.utilisateurs;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * INTERFACE IUtilisateurCerbereService :<br/>
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
 * @since 28 févr. 2019
 *
 */
public interface IUtilisateurCerbereService {


	
	/* CREATE ************/

	
	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * et retourne l'objet METIER persisté</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * <li>retourne null si les attributs obligatoires 
	 * de pObject ne sont pas remplis.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return IUtilisateurCerbere : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere create(IUtilisateurCerbere pObject) throws Exception;
	
	

	
	
} // FIN DE L'INTERFACE IUtilisateurCerbereService.--------------------------
