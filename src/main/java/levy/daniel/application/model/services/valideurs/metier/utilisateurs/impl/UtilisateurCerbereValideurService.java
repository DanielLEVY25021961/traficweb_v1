package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs.UtilisateurCerbereGestionnairePreferencesRG;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.valideurs.ErreursMaps;
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
	 * @throws Exception 
	 */
	@Override
	public ErreursMaps valider(
			final IUtilisateurCerbereDTO pDto) throws Exception {
		
		if (pDto == null) {
			return null;
		}
		
		/* instanciation d'une nouvelle encapsulation des MAPS 
		 * à chaque appel de la méthode. */
		final ErreursMaps erreursMap 
			= new ErreursMaps();
				
		/* VALIDATIONS pour chaque attribut. */
		this.validerCivilite(pDto, erreursMap);
//		this.validerPrenom(pDto, erreursMap);
//		this.validerNom(pDto, erreursMap);
//		this.validerTel(pDto, erreursMap);
//		this.validerEmail(pDto, erreursMap);
//		this.validerService(pDto, erreursMap);
//		this.validerUnite(pDto, erreursMap);
//		this.validerProfil(pDto, erreursMap);
//		this.validerPortee(pDto, erreursMap);
//		this.validerRestriction(pDto, erreursMap);
		
		return erreursMap;
		
	} // Fin de valider(...).______________________________________________

	
	
	/**
	 * applique les REGLES DE GESTION sur la civilite.<br/>
	 * alimente pErreursMaps avec les éventuels messages d'erreur.<br/>
	 * <ul>
	 * <li>récupère l'interrupteur général auprès du 
	 * Gestionnaire de préferences.</li>
	 * <li>ne contrôle rien si l'interrupteur général est à false.</li>
	 * <li>récupère l'interrupteur de chaque RG sur l'attribut auprès 
	 * du Gestionnaire de préferences.</li>
	 * <li>applique le contrôle si 
	 * [interrupteur général + interrupteur de chaque RG] sont à true.</li>
	 * </ul>
	 * - ne fait rien si pDto == null.<br/>
	 * - ne fait rien si pErreursMaps == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private void validerCivilite(
			final IUtilisateurCerbereDTO pDto
				, final ErreursMaps pErreursMaps) throws Exception {
		
		/* ne fait rien si pDto == null. */
		if (pDto == null) {
			return;
		}
		
		/* ne fait rien si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return;
		}
		
		/* nom de l'attribut concerné par la validation. */
		final String nomAttribut = "civilite";
		
		/* instanciation d'une nouvelle liste de message 
		 * pour errorsMapDetaille de erreursMap POUR CHAQUE ATTRIBUT. */
		final List<String> messages = new ArrayList<String>();
		
		/* AJOUT d'une Entree dans errorsMapDetaille 
		 * de erreursMap POUR CHAQUE ATTRIBUT. */
		pErreursMaps.ajouterEntreeAErrorsMapDetaille(nomAttribut, messages);

		/* récupère l'interrupteur général auprès 
		 * du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralCivilite 
		= UtilisateurCerbereGestionnairePreferencesRG
			.getValiderRGUtilisateurCivilite();
		
		/* ne contrôle rien si l'interrupteur général est à false. */
		if (!interrupteurGeneralCivilite) {
			return;
		}
		
		/* récupère l'interrupteur de chaque RG 
		 * auprès du Gestionnaire de préferences. */
		final Boolean interrupteurCiviliteRenseigne01 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteRenseigne01();
		
		final Boolean interrupteurCiviliteLitteral02 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteLitteral02();

		final Boolean interrupteurCiviliteLongueur03 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteLongueur03();

		final Boolean interrupteurCiviliteNomenclature04 
			= UtilisateurCerbereGestionnairePreferencesRG
				.getValiderRGUtilisateurCiviliteNomenclature04();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		boolean rg4 = false;
		
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurGeneralCivilite 
				&& interrupteurCiviliteRenseigne01) {
			renseigne = this.validerRGUtilisateurCiviliteRenseigne01(
					nomAttribut, pDto, pErreursMaps);
		}
		
		ok = renseigne;
		
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurGeneralCivilite 
					&& interrupteurCiviliteLitteral02) {
				rg2 = this.validerRGUtilisateurCiviliteLitteral02(
						nomAttribut, pDto, pErreursMaps);
			}
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurGeneralCivilite 
					&& interrupteurCiviliteLongueur03) {
				rg3 = this.validerRGUtilisateurCiviliteLongueur03(
						nomAttribut, pDto, pErreursMaps);
			}
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurGeneralCivilite 
					&& interrupteurCiviliteNomenclature04) {
				rg4 = this.validerRGUtilisateurCiviliteNomenclature04(
						nomAttribut, pDto, pErreursMaps);
			}
			
			ok = renseigne && rg2 && rg3 && rg4;
			
		}
		
		if (!ok) {
			
			final String messageConcatene = "";
			
			pErreursMaps.ajouterEntreeAErrorsMap(nomAttribut, messageConcatene);
		}
				
	} // Fin de validerCivilite(...).______________________________________


	
	/**
	 * .<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 */
	private boolean validerRGUtilisateurCiviliteRenseigne01(
			final String pAttribut
				, final IUtilisateurCerbereDTO pDto
					, final ErreursMaps pErreursMaps) {
		
		/* retourne false si pDto == null. */
		if (pDto == null) {
			return false;
		}
		
		/* retourne false si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return false;
		}
		
		/* message utilisateur de la RG. */
		final String message 
			= "la civilité doit obligatoirement être renseignée";
		
		// TEST ***************
		if (StringUtils.isBlank(pDto.getCivilite())) {
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retoune false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	}

	
	
	/**
	 * .<br/>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 */
	private boolean validerRGUtilisateurCiviliteLitteral02(
			final String pAttribut
				, final IUtilisateurCerbereDTO pDto
					, final ErreursMaps pErreursMaps) {
		
		/* retourne false si pDto == null. */
		if (pDto == null) {
			return false;
		}
		
		/* retourne false si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return false;
		}
		
		/* message utilisateur de la RG. */
		final String message 
			= "la civilité doit obligatoirement être littérale";
		
		// TEST ***************
		if (StringUtils.isBlank(pDto.getCivilite())) {
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retoune false si la RG n'est pas validée. */
			return false;
		}
		
		return true;
		
	}

	
	
	/**
	 * .<br/>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 */
	private boolean validerRGUtilisateurCiviliteLongueur03(
			final String pAttribut
				, final IUtilisateurCerbereDTO pDto
					, final ErreursMaps pErreursMaps) {
		
		/* retourne false si pDto == null. */
		if (pDto == null) {
			return false;
		}
		
		/* retourne false si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return false;
		}
		
		/* message utilisateur de la RG. */
		final String message 
			= "la longueur de la civilité doit obligatoirement être inférieure à 15 caractères";
		
		// TEST ***************
		if (StringUtils.isBlank(pDto.getCivilite())) {
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retoune false si la RG n'est pas validée. */
			return false;
		}
		
		return true;
		
	}

	
	
	/**
	 * .<br/>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 */
	private boolean validerRGUtilisateurCiviliteNomenclature04(
			final String pAttribut
				, final IUtilisateurCerbereDTO pDto
					, final ErreursMaps pErreursMaps) {
		
		/* retourne false si pDto == null. */
		if (pDto == null) {
			return false;
		}
		
		/* retourne false si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return false;
		}
		
		/* message utilisateur de la RG. */
		final String message 
			= "la civilité doit obligatoirement respecter une liste finie de valeurs";
		
		// TEST ***************
		if (StringUtils.isBlank(pDto.getCivilite())) {
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retoune false si la RG n'est pas validée. */
			return false;
		}
		
		return true;
		
	}

	
	
//	/**
//	 * applique les REGLES DE GESTION sur le prenom.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerPrenom(
//			final IUtilisateurCerbereDTO pDto
//				, ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		if (StringUtils.isBlank(pDto.getPrenom())) {
//			pErreursMaps.put(
//					"prenom"
//					, "le prénom doit obligatoirement être renseigné");
//		}
//		
//	} // Fin de validerPrenom(...).________________________________________
//
//
//	
//	/**
//	 * applique les REGLES DE GESTION sur le nom.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerNom(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		if (StringUtils.isBlank(pDto.getNom())) {
//			pErreursMaps.put(
//					"nom"
//					, "le nom doit obligatoirement être renseigné");
//		}
//		
//	} // Fin de validerPrenom(...).________________________________________
//
//	
//	
//	/**
//	 * applique les REGLES DE GESTION sur le tel.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerTel(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		return;
//		
//	} // Fin de validerTel(...).___________________________________________
//
//
//	
//	/**
//	 * applique les REGLES DE GESTION sur le email.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerEmail(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		if (StringUtils.isBlank(pDto.getEmail())) {
//			pErreursMaps.put(
//					"email"
//					, "l'email doit obligatoirement être renseigné");
//		}
//		
//	} // Fin de validerEmail(...)._________________________________________
//
//	
//	
//	/**
//	 * applique les REGLES DE GESTION sur le service.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerService(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		return;
//		
//	} // Fin de validerService(...)._______________________________________
//
//
//	
//	/**
//	 * applique les REGLES DE GESTION sur l'unite.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerUnite(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		if (StringUtils.isBlank(pDto.getUnite())) {
//			pErreursMaps.put(
//					"unite"
//					, "l'unité doit obligatoirement être renseignée");
//		}
//		
//	} // Fin de validerUnite(...)._________________________________________
//
//	
//	
//	/**
//	 * applique les REGLES DE GESTION sur le profil.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerProfil(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		if (StringUtils.isBlank(pDto.getProfil())) {
//			pErreursMaps.put(
//					"profil"
//					, "le profil doit obligatoirement être renseignée");
//		}
//		
//	} // Fin de validerProfil(...).________________________________________
//
//	
//	
//	/**
//	 * applique les REGLES DE GESTION sur la portee.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : Map&lt;String,String&gt; : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerPortee(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		return;
//		
//	} // Fin de validerPortee(...)._______________________________________
//
//	
//	
//	/**
//	 * applique les REGLES DE GESTION sur la restriction.<br/>
//	 * alimente pErreursMaps avece les éventuels messages d'erreur.<br/>
//	 * <br/>
//	 * - ne fait rien si pDto == null.<br/>
//	 * - ne fait rien si pErreursMaps == null.<br/>
//	 * <br/>
//	 *
//	 * @param pDto : IUtilisateurCerbereDTO : 
//	 * DTO à contrôler.<br/>
//	 * @param pErreursMaps : ErreursMaps : 
//	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
//	 */
//	private void validerRestriction(
//			final IUtilisateurCerbereDTO pDto
//				, final ErreursMaps pErreursMaps) {
//		
//		/* ne fait rien si pDto == null. */
//		if (pDto == null) {
//			return;
//		}
//		
//		/* ne fait rien si pErreursMaps == null. */
//		if (pErreursMaps == null) {
//			return;
//		}
//		
//		return;
//		
//	} // Fin de validerRestriction(...).___________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereValideurService.---------------------
