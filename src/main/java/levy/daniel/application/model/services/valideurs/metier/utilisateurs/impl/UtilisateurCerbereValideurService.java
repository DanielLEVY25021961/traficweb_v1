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
		
		/* instanciation d'une nouvelle MAP à chaque appel de la méthode. */
		final Map<String, String> errorsMap 
			= new ConcurrentHashMap<String, String>();
		
		/* VALIDATIONS pour chaque attribut. */
		this.validerCivilite(pDto, errorsMap);
		this.validerPrenom(pDto, errorsMap);
		this.validerNom(pDto, errorsMap);
		this.validerTel(pDto, errorsMap);
		this.validerEmail(pDto, errorsMap);
		this.validerService(pDto, errorsMap);
		this.validerUnite(pDto, errorsMap);
		this.validerProfil(pDto, errorsMap);
		this.validerPortee(pDto, errorsMap);
		this.validerRestriction(pDto, errorsMap);
		
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

	
	
	/**
	 * applique les REGLES DE GESTION sur le tel.<br/>
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
	private void validerTel(
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
		
	} // Fin de validerTel(...).___________________________________________


	
	/**
	 * applique les REGLES DE GESTION sur le email.<br/>
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
	private void validerEmail(
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
		
		if (StringUtils.isBlank(pDto.getEmail())) {
			pErrorsMap.put(
					"email"
					, "l'email doit obligatoirement être renseigné");
		}
		
	} // Fin de validerEmail(...)._________________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur le service.<br/>
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
	private void validerService(
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
		
	} // Fin de validerService(...)._______________________________________


	
	/**
	 * applique les REGLES DE GESTION sur l'unite.<br/>
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
	private void validerUnite(
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
		
		if (StringUtils.isBlank(pDto.getUnite())) {
			pErrorsMap.put(
					"unite"
					, "l'unité doit obligatoirement être renseignée");
		}
		
	} // Fin de validerUnite(...)._________________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur le profil.<br/>
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
	private void validerProfil(
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
		
		if (StringUtils.isBlank(pDto.getProfil())) {
			pErrorsMap.put(
					"profil"
					, "le profil doit obligatoirement être renseignée");
		}
		
	} // Fin de validerProfil(...).________________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur la portee.<br/>
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
	private void validerPortee(
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
		
	} // Fin de validerPortee(...)._______________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur la restriction.<br/>
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
	private void validerRestriction(
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
		
	} // Fin de validerRestriction(...).___________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereValideurService.---------------------
