package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.IUtilisateurCerbereValideurService;

/**
 * CLASSE UtilisateurCerbereValideurService :<br/>
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
@Service(value="UtilisateurCerbereValideurService")
public class UtilisateurCerbereValideurService 
			implements IUtilisateurCerbereValideurService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereValideurService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereValideurService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> valider(
			final IUtilisateurCerbereDTO pDto) {
		
		if (pDto == null) {
			return null;
		}
		
		/* instanciation d'une nouvelle MAP. */
		final Map<String, String> errorsMap 
			= new ConcurrentHashMap<String, String>();
		
		/* VALIDATIONS pour chaque attribut. */
		this.validerCivilite(pDto, errorsMap);
		this.validerPrenom(pDto, errorsMap);
		this.validerNom(pDto, errorsMap);
		
		return errorsMap;
		
	} // Fin de valider(...).______________________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur la civilite.<br/>
	 * alimente pErrorsMap avece les éventuels messages d'erreur.<br/>
	 * <br/>
	 * - ne fait rien si pDto == null.<br/>
	 * - ne fait rien si pErrorsMap == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErrorsMap : Map&lt;String,String&gt; : 
	 * map des messages d'erreur pour chaque champ.<br/>
	 */
	private void validerCivilite(
			final IUtilisateurCerbereDTO pDto
				, final Map<String, String> pErrorsMap) {
		
		/* ne fait rien si pDto == null. */
		if (pDto == null) {
			return;
		}
		
		/* ne fait rien si pErrorsMap == null. */
		if (pErrorsMap == null) {
			return;
		}
		
		return;
		
	} // Fin de validerCivilite(...).______________________________________


	
	/**
	 * applique les REGLES DE GESTION sur le prenom.<br/>
	 * alimente pErrorsMap avece les éventuels messages d'erreur.<br/>
	 * <br/>
	 * - ne fait rien si pDto == null.<br/>
	 * - ne fait rien si pErrorsMap == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErrorsMap : Map&lt;String,String&gt; : 
	 * map des messages d'erreur pour chaque champ.<br/>
	 */
	private void validerPrenom(
			final IUtilisateurCerbereDTO pDto
				, final Map<String, String> pErrorsMap) {
		
		/* ne fait rien si pDto == null. */
		if (pDto == null) {
			return;
		}
		
		/* ne fait rien si pErrorsMap == null. */
		if (pErrorsMap == null) {
			return;
		}
		
		if (StringUtils.isBlank(pDto.getPrenom())) {
			pErrorsMap.put(
					"prenom"
					, "le prénom doit obligatoirement être renseigné");
		}
		
	} // Fin de validerPrenom(...).________________________________________


	
	/**
	 * applique les REGLES DE GESTION sur le nom.<br/>
	 * alimente pErrorsMap avece les éventuels messages d'erreur.<br/>
	 * <br/>
	 * - ne fait rien si pDto == null.<br/>
	 * - ne fait rien si pErrorsMap == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErrorsMap : Map&lt;String,String&gt; : 
	 * map des messages d'erreur pour chaque champ.<br/>
	 */
	private void validerNom(
			final IUtilisateurCerbereDTO pDto
				, final Map<String, String> pErrorsMap) {
		
		/* ne fait rien si pDto == null. */
		if (pDto == null) {
			return;
		}
		
		/* ne fait rien si pErrorsMap == null. */
		if (pErrorsMap == null) {
			return;
		}
		
		if (StringUtils.isBlank(pDto.getNom())) {
			pErrorsMap.put(
					"nom"
					, "le nom doit obligatoirement être renseigné");
		}
		
	} // Fin de validerPrenom(...).________________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereValideurService.---------------------
