package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesControles;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesRG;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl.FactoryNomenclatureHit;
import levy.daniel.application.model.services.valideurs.ErreursMaps;
import levy.daniel.application.model.services.valideurs.metier.sections.ISectionHitValideurService;

/**
 * CLASSE SectionHitValideurService :<br/>
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
 * </span></b>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 13 août 2019
 *
 */
@Service(value="SectionHitValideurService")
public class SectionHitValideurService implements ISectionHitValideurService {

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
		= LogFactory.getLog(SectionHitValideurService.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitValideurService() {
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
	 * @param pErreursMaps : ErreursMaps : 
	 * Encapsulation (pure fabrication) des Maps d'erreurs 
	 * concaténées et détaillées.<br/>
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
	 */
	@Override
	public final ErreursMaps valider(
			final ISectionHitDTO pDto) throws Exception {
		
		if (pDto == null) {
			return null;
		}
		
		/* instancie une nouvelle ErreursMaps (encapsulation des MAPS 
		 * des messages de violation de RG détaillés et concaténés) 
		 * à chaque appel de la méthode. */
		final ErreursMaps erreursMap 
			= new ErreursMaps();
		
		boolean valide = false;  // NOPMD by daniel.levy on 15/07/19 09:58
		
		// VALIDATIONS pour chaque attribut. ***************************
		/* 1 - numDepartement. ***********/
		boolean numDepartementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumDepartement = "numDepartement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumDepartement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumDepartement) {
			numDepartementValide 
				= this.validerNumDepartement(
						pDto, attributNumDepartement, erreursMap);
		} else {
			numDepartementValide = true;
		}

		/* 2 - numSection. ***************/
		boolean numSectionValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumSection = "numSection";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumSection 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSection();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumSection) {
			numSectionValide 
				= this.validerNumSection(
						pDto, attributNumSection, erreursMap);
		} else {
			numSectionValide = true;
		}

		/* 3 - sens. ******************/
		boolean sensValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributSens = "sens";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralSens 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSens();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralSens) {
			sensValide 
				= this.validerSens(
						pDto, attributSens, erreursMap);
		} else {
			sensValide = true;
		}

		/* 4 - nature. ******************/
		boolean natureValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNature = "nature";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNature 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNature();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNature) {
			natureValide 
				= this.validerNature(
						pDto, attributNature, erreursMap);
		} else {
			natureValide = true;
		}
		
		/* 5 - classe. ***************/
		boolean classeValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributClasse = "classe";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralClasse 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasse();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralClasse) {
			classeValide 
				= this.validerClasse(
						pDto, attributClasse, erreursMap);
		} else {
			classeValide = true;
		}
		
		/* 6 - anneeTraitement. *******/
		boolean anneeTraitementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributAnneeTraitement = "anneeTraitement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralAnneeTraitement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeTraitement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralAnneeTraitement) {
			anneeTraitementValide 
				= this.validerAnneeTraitement(
						pDto, attributAnneeTraitement, erreursMap);
		} else {
			anneeTraitementValide = true;
		}
		
		/* 7 - zoneLibre1. *******/
		boolean zoneLibre1Valide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributZoneLibre1 = "zoneLibre1";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralZoneLibre1 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre1();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralZoneLibre1) {
			zoneLibre1Valide 
				= this.validerZoneLibre1(
						pDto, attributZoneLibre1, erreursMap);
		} else {
			zoneLibre1Valide = true;
		}
		/* calcul de validité sur tous les attributs. */
		valide = numDepartementValide 
				&& numSectionValide 
				&& sensValide 
				&& natureValide 
				&& classeValide
				&& anneeTraitementValide
				&& zoneLibre1Valide;
		
		erreursMap.setValide(valide);
		
//		System.out.println("valide ? : " + valide);
		
		return erreursMap;

	} // Fin de valider(...).______________________________________________

	
	
	
	/* 1 - numDepartement. *************/	
	/**
	 * applique les REGLES DE GESTION sur numDepartement.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerNumDepartement(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurNumDepartementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRenseigne01();
		
		final Boolean interrupteurNumDepartementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumDepartementRenseigne01) {
			renseigne = this.validerRGSectionHitNumDepartementRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurNumDepartementRegex02) {
				rg2 = this.validerRGSectionHitNumDepartementRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
						
		}
		
		ok = renseigne && rg2;
		
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
				
	} // Fin de validerNumDepartement(...).______________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE pour numDepartement.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNumDepartementRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumDepartement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumDepartementRenseigne01(...).______________

	
	
	/**
	 * valide la RG REGEX pour numDepartement.<br/>
	 * <ul>
	 * <li>utilise la regex "\\d{3}" qui signifie 
	 * 'exactement 3 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNumDepartementRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumDepartementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumDepartement();
		
		final String motif = "\\d{3}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitNumDepartementRegex02(...)._______________

	
	
	/* 2 - numSection. *************/	
	/**
	 * applique les REGLES DE GESTION sur numSection.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerNumSection(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurNumSectionRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRenseigne01();
		
		final Boolean interrupteurNumSectionRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumSectionRenseigne01) {
			renseigne = this.validerRGSectionHitNumSectionRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurNumSectionRegex02) {
				rg2 = this.validerRGSectionHitNumSectionRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
			
		}
		
		ok = renseigne && rg2;
		
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
				
	} // Fin de validerNumSection(...).______________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE pour numSection.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNumSectionRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumSection())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumSectionRenseigne01(...).______________

	
	
	/**
	 * valide la RG REGEX pour numSection.<br/>
	 * <ul>
	 * <li>utilise la regex "\\d{6}" qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNumSectionRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNumSectionRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumSection();
		
		final String motif = "\\d{6}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitNumSectionRegex02(...)._______________

	
	
	/* 3 - sens. ********************/	
	/**
	 * applique les REGLES DE GESTION sur sens.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerSens(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurSensRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRenseigne01();
		
		final Boolean interrupteurSensRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRegex02();
		
		final Boolean interrupteurSensNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurSensRenseigne01) {
			renseigne = this.validerRGSectionHitSensRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurSensRegex02) {
				rg2 = this.validerRGSectionHitSensRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurSensNomenclature03) {
				rg3 = this.validerRGSectionHitSensNomenclature03(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg3 = true;
			}
			
		}
		
		ok = renseigne && rg2 && rg3;
		
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
				
	} // Fin de validerSens(...).______________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE pour sens.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitSensRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getSens())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitSensRenseigne01(...).______________

	
	
	/**
	 * valide la RG REGEX pour sens.<br/>
	 * <ul>
	 * <li>utilise la regex "\\d{1}" qui signifie 
	 * 'exactement 1 chiffre'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitSensRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSens();
		
		final String motif = "\\d{1}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitSensRegex02(...)._______________

	
	
	/**
	 * valide la RG NOMENCLATURE pour sens.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesSens()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitSensNomenclature03(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitSensNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSens();
		Integer valeurAControlerInteger = null;
		
		try {
			valeurAControlerInteger = Integer.valueOf(valeurAControler);
		} catch (Exception e) {
			valeurAControlerInteger = null;
		}
		
		/* retourne false si la valeur à contrôler 
		 * n'est pas homogène à un entier. */
		if (valeurAControlerInteger == null) {
			return false;
		}
		
		/* récupère le Set des valeurs possibles auprès de la 
		 * FactoryNomenclature. */
		final Set<Integer> setClesPossibles 
			= FactoryNomenclatureHit.getSetClesPossiblesSens();
		
		/* retourne false si la valeur à contrôler 
		 * n'appartient pas à la nomenclature. */
		if (!setClesPossibles.contains(valeurAControlerInteger)) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);

			return false;
		}
		
		return true;
		
	} // Fin de validerRGSectionHitSensNomenclature03(...)._______________

	
	
	/* 4 - nature. ******************/	
	/**
	 * applique les REGLES DE GESTION sur nature.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerNature(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurNatureRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRenseigne01();
		
		final Boolean interrupteurNatureRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureRegex02();
		
		final Boolean interrupteurNatureNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNatureNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNatureRenseigne01) {
			renseigne = this.validerRGSectionHitNatureRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurNatureRegex02) {
				rg2 = this.validerRGSectionHitNatureRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurNatureNomenclature03) {
				rg3 = this.validerRGSectionHitNatureNomenclature03(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg3 = true;
			}
			
		}
		
		ok = renseigne && rg2 && rg3;
		
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
				
	} // Fin de validerNature(...).______________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE pour nature.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNatureRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNature())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNatureRenseigne01(...).______________

	
	
	/**
	 * valide la RG REGEX pour nature.<br/>
	 * <ul>
	 * <li>utilise la regex "\\d{1}" qui signifie 
	 * 'exactement 1 chiffre'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNatureRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNature();
		
		final String motif = "\\d{1}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitNatureRegex02(...)._______________

	
	
	/**
	 * valide la RG NOMENCLATURE pour nature.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesNature()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitNatureNomenclature03(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitNatureNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNature();
		Integer valeurAControlerInteger = null;
		
		try {
			valeurAControlerInteger = Integer.valueOf(valeurAControler);
		} catch (Exception e) {
			valeurAControlerInteger = null;
		}
		
		/* retourne false si la valeur à contrôler 
		 * n'est pas homogène à un entier. */
		if (valeurAControlerInteger == null) {
			return false;
		}
		
		/* récupère le Set des valeurs possibles auprès de la 
		 * FactoryNomenclature. */
		final Set<Integer> setClesPossibles 
			= FactoryNomenclatureHit.getSetClesPossiblesNature();
		
		/* retourne false si la valeur à contrôler 
		 * n'appartient pas à la nomenclature. */
		if (!setClesPossibles.contains(valeurAControlerInteger)) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);

			return false;
		}
		
		return true;
		
	} // Fin de validerRGSectionHitNatureNomenclature03(...)._______________
	
	
	
	/* 5 - classe. ***************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>classe</b></code>.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerClasse(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurClasseRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRenseigne01();
		
		final Boolean interrupteurClasseRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurClasseRenseigne01) {
			renseigne = this.validerRGSectionHitClasseRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurClasseRegex02) {
				rg2 = this.validerRGSectionHitClasseRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
			
		}
		
		ok = renseigne && rg2;
		
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
				
	} // Fin de validerClasse(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>classe</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitClasseRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getClasse())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitClasseRenseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>classe</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex "00" qui signifie 
	 * 'exactement 2 chiffres qui sont exactement '00'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>civilite</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitClasseRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitClasseRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClasse();
		
		final String motif = "00";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitClasseRegex02(...)._____________________
	
	
	
	/* 6 - anneeTraitement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>anneeTraitement</b></code>.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerAnneeTraitement(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurAnneeTraitementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRenseigne01();
		
		final Boolean interrupteurAnneeTraitementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeTraitementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurAnneeTraitementRenseigne01) {
			renseigne = this.validerRGSectionHitAnneeTraitementRenseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurAnneeTraitementRegex02) {
				rg2 = this.validerRGSectionHitAnneeTraitementRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
			
		}
		
		ok = renseigne && rg2;
		
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
				
	} // Fin de validerAnneeTraitement(...)._______________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>anneeTraitement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>anneeTraitement</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitAnneeTraitementRenseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeTraitementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getAnneeTraitement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitAnneeTraitementRenseigne01(...).________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>anneeTraitement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{2}] qui signifie 
	 * 'exactement 2 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>anneeTraitement</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitAnneeTraitementRegex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitAnneeTraitementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAnneeTraitement();
		
		final String motif = "\\d{2}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitAnneeTraitementRegex02(...).____________
	
	
	
	/* 7 - zoneLibre1. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>zoneLibre1</b></code>.<br/>
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
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	private boolean validerZoneLibre1(
			final ISectionHitDTO pDto
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
		final Boolean interrupteurZoneLibre1Renseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Renseigne01();
		
		final Boolean interrupteurZoneLibre1Regex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre1Regex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurZoneLibre1Renseigne01) {
			renseigne = this.validerRGSectionHitZoneLibre1Renseigne01(
					pAttribut, pDto, pErreursMaps);
		} else {
			/* la validation de la RG retourne systématiquement true 
			 * si son interrupteur n'est pas à true. */
			renseigne = true;
		}
		
		/* n'applique les contrôles de validation des autres RG 
		 * (format, longueur, fourchette, ...) que si 
		 * la RG RENSEIGNE est validée. */
		if (renseigne) {
			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurZoneLibre1Regex02) {
				rg2 = this.validerRGSectionHitZoneLibre1Regex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}
			
		}
		
		ok = renseigne && rg2;
		
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
				
	} // Fin de validerZoneLibre1(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>zoneLibre1</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>zoneLibre1</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitZoneLibre1Renseigne01(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre1Renseigne01();
		
		// CONTROLE ***************
		if (pDto.getZoneLibre1() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitZoneLibre1Renseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>zoneLibre1</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\s{1}] qui signifie 
	 * 'exactement 1 espace (caractère blanc = espace, \t, \n, ...)'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>zoneLibre1</code>.<br/>
	 * @param pDto : ISectionHitDTO : 
	 * DTO à contrôler.<br/>
	 * @param pErreursMaps : ErreursMaps : 
	 * encapsulation des maps des messages d'erreur pour chaque attribut.<br/>
	 * 
	 * @return boolean : 
	 * true si l'attribut est valide vis à vis de la RG.
	 * 
	 * @throws Exception 
	 */
	private boolean validerRGSectionHitZoneLibre1Regex02(
			final String pAttribut
				, final ISectionHitDTO pDto
					, final ErreursMaps pErreursMaps) throws Exception {
		
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
			= SectionHitGestionnairePreferencesControles
				.getMessageSectionHitZoneLibre1Regex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getZoneLibre1();
		
		final String motif = "\\s{1}";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(valeurAControler);
		
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
		
	} // Fin de validerRGSectionHitZoneLibre1Regex02(...)._____________________	
		

} // FIN DE LA CLASSE SectionHitValideurService.-----------------------------
