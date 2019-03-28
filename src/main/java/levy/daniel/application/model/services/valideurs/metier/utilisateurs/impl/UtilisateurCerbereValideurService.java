package levy.daniel.application.model.services.valideurs.metier.utilisateurs.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * classe concrète chargée de valider toutes les Règles de Gestion (RG) 
 * s'appliquant à tous les attributs du DTO de l'OBJET METIER.<br/>
 * 
 * <p>
 * <b>applique dans sa méthode valider(DTO) 
 * les REGLES DE GESTION sur chaque attribut d'un DTO</b> 
 * et retourne une Encapsulation <code>{@link ErreursMaps}</code>.<br/>
 * ErreurMaps est une PURE FABRICATION chargée de contenir deux Maps 
 * contenant les erreurs lors de la validation d'un OBJET METIER 
 * par un service.<br/>
 * Les deux Maps (errorsMap et errorsMapDetaille) 
 * sont VIDES OU PAS ENSEMBLE.<br/>
 * <ul>
 * <li>une Map&lt;String,String&gt; <code>errorsMap</code> contenant les 
 * éventuels messages d'erreur pour chaque attribut avec 
 * toutes les violations des Règles de Gestion (RG) sur une seule ligne 
 * (compatible avec la map errors de SPRING) :
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
 * </p>
 * 	 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Fonctionnement de la validation (diagramme de séquence) : 
 * </span>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../javadoc/images/model/services/valideurs/service_valideur-methode_valider-sequence_1.png" 
 * alt="diagramme de séquence de la validation" />
 * 
 * <img src="../../../../../../../../../../../../javadoc/images/model/services/valideurs/service_valideur-methode_valider-sequence_2.png" 
 * alt="diagramme de séquence de la validation" />
 * </p>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * SAUT_LIGNE_JAVA, System.getProperty("line.separator"), <br/>
 * concaténation d'une liste de String, concatenation d'une liste,<br/>
 * ATTENTION System.getProperty("line.separator") est blank,<br/>
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
	 * séparateur utilisé pour la concaténation 
	 * des divers messages de violation des RG pour 
	 * un attribut dans une Map&lt;String,String&gt; 
	 * <code>errorsMap</code><br/>
	 * System.getProperty("line.separator")
	 */
	public static final String SEPARATEUR_MESSAGES 
		= System.getProperty("line.separator");
		
	/**
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';
	
	/**
	 * " - ".<br/>
	 */
	public static final String MOINS_ESPACE = " - ";
	
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
	 *concatène les lignes comprises dans pListe en les séparant 
	 * avec SEPARATEUR_MESSAGES.<br/>
	 * <ul>
	 * <li>ignore les lignes vides.</li>
	 * <li>n'ajoute pas de séparateur à la dernière ligne de la liste.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String : ligne unique concaténée.<br/>
	 */
	private String concatenerListeStrings(
			final List<String> pList) {
		return this.concatenerListeStrings(pList, SEPARATEUR_MESSAGES);
	} // Fin de concatenerListeStrings(...)._______________________________
	
	
	
	/**
	 * concatène les lignes comprises dans pListe en les séparant 
	 * avec pSeparateur.<br/>
	 * <ul>
	 * <li>ignore les lignes vides.</li>
	 * <li>n'ajoute pas de séparateur à la dernière ligne de la liste.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * @param pSeparateur : String : 
	 * séparateur utilisé pour la concaténation 
	 * des divers messages de violation des RG pour 
	 * un attribut dans une Map&lt;String,String&gt; 
	 * <code>errorsMap</code><br/>
	 * 
	 * @return : String : ligne unique concaténée.<br/>
	 */
	private String concatenerListeStrings(
			final List<String> pList
				, final String pSeparateur) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		final int taille = pList.size();
		int compteur = 0;
		
		for (final String ligne : pList) {
			
			compteur++;
			
			/* ignore les lignes vides. */
			if (!StringUtils.isBlank(ligne)) {
				stb.append(ligne);
			}
			
			/* n'ajoute pas de séparateur à la dernière ligne de la liste. */
			if (compteur < taille) {
				stb.append(pSeparateur);
			}
		}
		
		return stb.toString();
		
	} // Fin de concatenerListeStrings(...)._______________________________


	
	/**
	 * crée une nouvelle entrée dans la map 
	 * <code>this.errorsMapDetaille</code>.<br/>
	 * <ul>
	 * <li>ne peut crée l'entrée que si elle n'existe pas déjà.</li>
	 * </ul>
	 *
	 * @param pErreursMaps : ErreursMaps
	 * @param pNomAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 */
	private void creerEntreeDansErrorsMapDetaille(
			final ErreursMaps pErreursMaps, final String pNomAttribut) {
		
		/* instanciation d'une nouvelle liste de message 
		 * pour errorsMapDetaille de erreursMap POUR CHAQUE ATTRIBUT. */
		final List<String> messages = new ArrayList<String>();
		
		/* AJOUT d'une Entree dans errorsMapDetaille 
		 * de erreursMap POUR CHAQUE ATTRIBUT. */
		pErreursMaps.ajouterEntreeAErrorsMapDetaille(pNomAttribut, messages);
		
	} // Fin de creerEntreeDansErrorsMapDetaille(...)._____________________
	
	
	
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
		
		/* instancie une nouvelle ErreursMaps (encapsulation des MAPS 
		 * des messages de violation de RG détaillés et concaténés) 
		 * à chaque appel de la méthode. */
		final ErreursMaps erreursMap 
			= new ErreursMaps();
				
		/* VALIDATIONS pour chaque attribut. */
		boolean civiliteValide = false;
		
		/* CIVILITE. */
		/* nom de l'attribut concerné par la validation. */
		final String attributCivilite = "civilite";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralCivilite 
		= UtilisateurCerbereGestionnairePreferencesRG
			.getValiderRGUtilisateurCivilite();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralCivilite) {
			civiliteValide 
				= this.validerCivilite(
						pDto, attributCivilite, erreursMap);
		} else {
			civiliteValide = true;
		}
		
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
	 * <li>récupère l'interrupteur de chaque RG sur l'attribut auprès 
	 * du Gestionnaire de préferences.</li>
	 * <li>n'applique le contrôle de validation d'une RG que si 
	 * [interrupteur général + interrupteur de chaque RG] sont à true.</li>
	 * <li>retourne systématiquement true si une RG 
	 * ne doit pas être validée.</li>
	 * </ul>
	 * - retourne false si pDto == null.<br/>
	 * - retourne false si pAttribut est blank.<br/>
	 * - retourne false si pErreursMaps == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerCivilite(
			final IUtilisateurCerbereDTO pDto
				, final String pAttribut
					, final ErreursMaps pErreursMaps) throws Exception {
		
		/* retourne false si pDto == null. */
		if (pDto == null) {
			return false;
		}
		
		/* retourne false si pAttribut est blank. */
		if (StringUtils.isBlank(pAttribut)) {
			return false;
		}
		
		/* retourne false si pErreursMaps == null. */
		if (pErreursMaps == null) {
			return false;
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
		if (interrupteurCiviliteRenseigne01) {
			renseigne = this.validerRGUtilisateurCiviliteRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		ok = renseigne;
		
		/* n'applique les contrôles de validation des autres RG (format, longueur, fourchette, ...) que si la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurCiviliteLitteral02) {
				rg2 = this.validerRGUtilisateurCiviliteLitteral02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurCiviliteLongueur03) {
				rg3 = this.validerRGUtilisateurCiviliteLongueur03(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg3 = true;
			}
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurCiviliteNomenclature04) {
				rg4 = this.validerRGUtilisateurCiviliteNomenclature04(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg4 = true;
			}
			
			ok = renseigne && rg2 && rg3 && rg4;
			
		}
		
		if (!ok) {
			
			final List<String> listeAConcatener 
				= pErreursMaps.fournirListeMessagesAttribut(pAttribut);
			
			final String messageConcatene 
				= this.concatenerListeStrings(listeAConcatener);
			
			if (messageConcatene != null) {
				pErreursMaps
					.ajouterEntreeAErrorsMap(
							pAttribut, messageConcatene);
			}
			
		}
		
		return ok;
				
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
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
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
		final String civilite = pDto.getCivilite();
		
		final String motif = "\\D+";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(civilite);
		
		if (!matcher.matches()) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
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
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
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
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
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
