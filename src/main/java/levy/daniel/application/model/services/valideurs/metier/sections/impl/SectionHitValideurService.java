package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import java.util.ArrayList;
import java.util.HashSet;
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
	 * retire les éventuels zéros à gauche de pString.<br/>
	 * par exemple <code><b>retirerZerosAGauche("0021")</b></code> 
	 * retourne '21'.<br/> 
	 * <br/>
	 * - retourne pString si pString equals "n x 0".<br/>
	 * - retourne pString inchangée si pString ne commence pas 
	 * par 1 ou plusieurs zéros.<br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : String avec éventuellement des zéros à gauche.
	 * 
	 * @return : String : String sans les zéros à gauche.<br/>
	 */
	private String retirerZerosAGauche(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne pString si pString equals "n x 0". */		
		final String motif = "0+";
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(pString);
		
		if (matcher.matches()) {
			return pString;
		}
		
		/* retourne pString inchangée si pString ne commence pas par "0". */
		if (!StringUtils.startsWith(pString, "0")) {
			return pString;
		}
		
		String resultat = pString;
		
		while (StringUtils.startsWith(resultat, "0")) {
			resultat = StringUtils.removeStart(resultat, "0");
		} 
		
		return resultat;
		
	} // Fin de retirerZerosAGauche(...).__________________________________
	

	
	/**
	 * retire les éventuels espaces à gauche de pString.<br/>
	 * par exemple <code><b>retirerZerosAGauche("  21")</b></code> 
	 * retourne '21'.<br/> 
	 * <br/>
	 * - retourne pString inchangée si pString ne commence pas 
	 * par 1 ou plusieurs espaces.<br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : String avec éventuellement des espaces à gauche.
	 * 
	 * @return : String : String sans les espaces à gauche.<br/>
	 */
	private String retirerEspacesAGauche(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne pString inchangée si pString ne commence pas par " ". */
		if (!StringUtils.startsWith(pString, " ")) {
			return pString;
		}
		
		String resultat = pString;
		
		while (StringUtils.startsWith(resultat, " ")) {
			resultat = StringUtils.removeStart(resultat, " ");
		} 
		
		return resultat;
		
	} // Fin de retirerEspacesAGauche(...).________________________________
	
	
	
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
		
		/* 8 - numRoute. *******/
		boolean numRouteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumRoute = "numRoute";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumRoute 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumRoute();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumRoute) {
			numRouteValide 
				= this.validerNumRoute(
						pDto, attributNumRoute, erreursMap);
		} else {
			numRouteValide = true;
		}
		
		/* 9 - indiceNumRoute. *******/
		boolean indiceNumRouteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributIndiceNumRoute = "indiceNumRoute";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralIndiceNumRoute 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceNumRoute();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralIndiceNumRoute) {
			indiceNumRouteValide 
				= this.validerIndiceNumRoute(
						pDto, attributIndiceNumRoute, erreursMap);
		} else {
			indiceNumRouteValide = true;
		}
		
		/* 10 - indiceLettreRoute. *******/
		boolean indiceLettreRouteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributIndiceLettreRoute = "indiceLettreRoute";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralIndiceLettreRoute 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceLettreRoute();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralIndiceLettreRoute) {
			indiceLettreRouteValide 
				= this.validerIndiceLettreRoute(
						pDto, attributIndiceLettreRoute, erreursMap);
		} else {
			indiceLettreRouteValide = true;
		}
		
		/* 11 - categorieAdminRoute. *******/
		boolean categorieAdminRouteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributCategorieAdminRoute = "categorieAdminRoute";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralCategorieAdminRoute 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitCategorieAdminRoute();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralCategorieAdminRoute) {
			categorieAdminRouteValide 
				= this.validerCategorieAdminRoute(
						pDto, attributCategorieAdminRoute, erreursMap);
		} else {
			categorieAdminRouteValide = true;
		}
		
		/* 12 - typeComptage. *******/
		boolean typeComptageValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributTypeComptage = "typeComptage";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralTypeComptage 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeComptage();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralTypeComptage) {
			typeComptageValide 
				= this.validerTypeComptage(
						pDto, attributTypeComptage, erreursMap);
		} else {
			typeComptageValide = true;
		}
		
		/* 13 - classementRoute. *******/
		boolean classementRouteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributClassementRoute = "classementRoute";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralClassementRoute 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClassementRoute();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralClassementRoute) {
			classementRouteValide 
				= this.validerClassementRoute(
						pDto, attributClassementRoute, erreursMap);
		} else {
			classementRouteValide = true;
		}
		
		/* 14 - classeLargeurChausseeU. *******/
		boolean classeLargeurChausseeUValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributClasseLargeurChausseeU = "classeLargeurChausseeU";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralClasseLargeurChausseeU 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseeU();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralClasseLargeurChausseeU) {
			classeLargeurChausseeUValide 
				= this.validerClasseLargeurChausseeU(
						pDto, attributClasseLargeurChausseeU, erreursMap);
		} else {
			classeLargeurChausseeUValide = true;
		}
		
		/* 15 - classeLargeurChausseesS. *******/
		boolean classeLargeurChausseesSValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributClasseLargeurChausseesS = "classeLargeurChausseesS";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralClasseLargeurChausseesS 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitClasseLargeurChausseesS();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralClasseLargeurChausseesS) {
			classeLargeurChausseesSValide 
				= this.validerClasseLargeurChausseesS(
						pDto, attributClasseLargeurChausseesS, erreursMap);
		} else {
			classeLargeurChausseesSValide = true;
		}
		
		/* 16 - typeReseau. *******/
		boolean typeReseauValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributTypeReseau = "typeReseau";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralTypeReseau 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitTypeReseau();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralTypeReseau) {
			typeReseauValide 
				= this.validerTypeReseau(
						pDto, attributTypeReseau, erreursMap);
		} else {
			typeReseauValide = true;
		}
		
		/* 17 - pRoupK. *******/
		boolean pRoupKValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPRoupK = "pRoupK";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPRoupK 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPRoupK();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPRoupK) {
			pRoupKValide 
				= this.validerPRoupK(
						pDto, attributPRoupK, erreursMap);
		} else {
			pRoupKValide = true;
		}
		
		/* 18 - lieuDitOrigine. *******/
		boolean lieuDitOrigineValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributLieuDitOrigine = "lieuDitOrigine";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralLieuDitOrigine 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitOrigine();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralLieuDitOrigine) {
			lieuDitOrigineValide 
				= this.validerLieuDitOrigine(
						pDto, attributLieuDitOrigine, erreursMap);
		} else {
			lieuDitOrigineValide = true;
		}
		
		/* 19 - prOrigine. *******/
		boolean prOrigineValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPrOrigine = "prOrigine";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPrOrigine 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrOrigine();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPrOrigine) {
			prOrigineValide 
				= this.validerPrOrigine(
						pDto, attributPrOrigine, erreursMap);
		} else {
			prOrigineValide = true;
		}
		
		/* 20 - absOrigine. *******/
		boolean absOrigineValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributAbsOrigine = "absOrigine";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralAbsOrigine 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsOrigine();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralAbsOrigine) {
			absOrigineValide 
				= this.validerAbsOrigine(
						pDto, attributAbsOrigine, erreursMap);
		} else {
			absOrigineValide = true;
		}
		
		/* 21 - lieuDitExtremite. *******/
		boolean lieuDitExtremiteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributLieuDitExtremite = "lieuDitExtremite";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralLieuDitExtremite 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitExtremite();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralLieuDitExtremite) {
			lieuDitExtremiteValide 
				= this.validerLieuDitExtremite(
						pDto, attributLieuDitExtremite, erreursMap);
		} else {
			lieuDitExtremiteValide = true;
		}
		
		/* 22 - prExtremite. *******/
		boolean prExtremiteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPrExtremite = "prExtremite";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPrExtremite 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrExtremite();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPrExtremite) {
			prExtremiteValide 
				= this.validerPrExtremite(
						pDto, attributPrExtremite, erreursMap);
		} else {
			prExtremiteValide = true;
		}
		
		/* 23 - absExtremite. *******/
		boolean absExtremiteValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributAbsExtremite = "absExtremite";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralAbsExtremite 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsExtremite();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralAbsExtremite) {
			absExtremiteValide 
				= this.validerAbsExtremite(
						pDto, attributAbsExtremite, erreursMap);
		} else {
			absExtremiteValide = true;
		}
		
		/* 24 - lieuDitComptage. *******/
		boolean lieuDitComptageValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributLieuDitComptage = "lieuDitComptage";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralLieuDitComptage 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLieuDitComptage();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralLieuDitComptage) {
			lieuDitComptageValide 
				= this.validerLieuDitComptage(
						pDto, attributLieuDitComptage, erreursMap);
		} else {
			lieuDitComptageValide = true;
		}
		
		/* 25 - prComptage. *******/
		boolean prComptageValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPrComptage = "prComptage";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPrComptage 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPrComptage();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPrComptage) {
			prComptageValide 
				= this.validerPrComptage(
						pDto, attributPrComptage, erreursMap);
		} else {
			prComptageValide = true;
		}
		
		/* 26 - absComptage. *******/
		boolean absComptageValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributAbsComptage = "absComptage";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralAbsComptage 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAbsComptage();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralAbsComptage) {
			absComptageValide 
				= this.validerAbsComptage(
						pDto, attributAbsComptage, erreursMap);
		} else {
			absComptageValide = true;
		}
		
		/* 27 - longueurSection. *******/
		boolean longueurSectionValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributLongueurSection = "longueurSection";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralLongueurSection 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurSection();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralLongueurSection) {
			longueurSectionValide 
				= this.validerLongueurSection(
						pDto, attributLongueurSection, erreursMap);
		} else {
			longueurSectionValide = true;
		}
		
		/* 28 - longueurRaseCampagne. *******/
		boolean longueurRaseCampagneValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributLongueurRaseCampagne = "longueurRaseCampagne";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralLongueurRaseCampagne 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitLongueurRaseCampagne();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralLongueurRaseCampagne) {
			longueurRaseCampagneValide 
				= this.validerLongueurRaseCampagne(
						pDto, attributLongueurRaseCampagne, erreursMap);
		} else {
			longueurRaseCampagneValide = true;
		}
		
		/* 29 - numDepartementRattachement. *******/
		boolean numDepartementRattachementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumDepartementRattachement = "numDepartementRattachement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumDepartementRattachement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementRattachement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumDepartementRattachement) {
			numDepartementRattachementValide 
				= this.validerNumDepartementRattachement(
						pDto, attributNumDepartementRattachement, erreursMap);
		} else {
			numDepartementRattachementValide = true;
		}
		
		/* 30 - numSectionRattachement. *******/
		boolean numSectionRattachementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumSectionRattachement = "numSectionRattachement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumSectionRattachement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionRattachement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumSectionRattachement) {
			numSectionRattachementValide 
				= this.validerNumSectionRattachement(
						pDto, attributNumSectionRattachement, erreursMap);
		} else {
			numSectionRattachementValide = true;
		}
		
		/* 31 - sensRattachement. *******/
		boolean sensRattachementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributSensRattachement = "sensRattachement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralSensRattachement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensRattachement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralSensRattachement) {
			sensRattachementValide 
				= this.validerSensRattachement(
						pDto, attributSensRattachement, erreursMap);
		} else {
			sensRattachementValide = true;
		}
		
		/* 32 - numDepartementLimitrophe. *******/
		boolean numDepartementLimitropheValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumDepartementLimitrophe = "numDepartementLimitrophe";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumDepartementLimitrophe 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumDepartementLimitrophe();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumDepartementLimitrophe) {
			numDepartementLimitropheValide 
				= this.validerNumDepartementLimitrophe(
						pDto, attributNumDepartementLimitrophe, erreursMap);
		} else {
			numDepartementLimitropheValide = true;
		}
		
		/* 33 - numSectionLimitrophe. *******/
		boolean numSectionLimitropheValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributNumSectionLimitrophe = "numSectionLimitrophe";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralNumSectionLimitrophe 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitNumSectionLimitrophe();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralNumSectionLimitrophe) {
			numSectionLimitropheValide 
				= this.validerNumSectionLimitrophe(
						pDto, attributNumSectionLimitrophe, erreursMap);
		} else {
			numSectionLimitropheValide = true;
		}
		
		/* 34 - sensLimitrophe. *******/
		boolean sensLimitropheValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributSensLimitrophe = "sensLimitrophe";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralSensLimitrophe 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitSensLimitrophe();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralSensLimitrophe) {
			sensLimitropheValide 
				= this.validerSensLimitrophe(
						pDto, attributSensLimitrophe, erreursMap);
		} else {
			sensLimitropheValide = true;
		}
		
		/* 35 - moisSectionnement. *******/
		boolean moisSectionnementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributMoisSectionnement = "moisSectionnement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralMoisSectionnement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMoisSectionnement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralMoisSectionnement) {
			moisSectionnementValide 
				= this.validerMoisSectionnement(
						pDto, attributMoisSectionnement, erreursMap);
		} else {
			moisSectionnementValide = true;
		}
		
		/* 36 - anneeSectionnement. *******/
		boolean anneeSectionnementValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributAnneeSectionnement = "anneeSectionnement";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralAnneeSectionnement 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitAnneeSectionnement();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralAnneeSectionnement) {
			anneeSectionnementValide 
				= this.validerAnneeSectionnement(
						pDto, attributAnneeSectionnement, erreursMap);
		} else {
			anneeSectionnementValide = true;
		}
		
		/* 37 - zoneLibre2. *******/
		boolean zoneLibre2Valide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributZoneLibre2 = "zoneLibre2";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralZoneLibre2 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitZoneLibre2();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralZoneLibre2) {
			zoneLibre2Valide 
				= this.validerZoneLibre2(
						pDto, attributZoneLibre2, erreursMap);
		} else {
			zoneLibre2Valide = true;
		}
		
		/* 38 - mjaN. *******/
		boolean mjaNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributMjaN = "mjaN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralMjaN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitMjaN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralMjaN) {
			mjaNValide 
				= this.validerMjaN(
						pDto, attributMjaN, erreursMap);
		} else {
			mjaNValide = true;
		}
		
		/* 39 - modeCalculN. *******/
		boolean modeCalculNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributModeCalculN = "modeCalculN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralModeCalculN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitModeCalculN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralModeCalculN) {
			modeCalculNValide 
				= this.validerModeCalculN(
						pDto, attributModeCalculN, erreursMap);
		} else {
			modeCalculNValide = true;
		}
		
		/* 40 - pcPLN. *******/
		boolean pcPLNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPcPLN = "pcPLN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPcPLN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcPLN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPcPLN) {
			pcPLNValide 
				= this.validerPcPLN(
						pDto, attributPcPLN, erreursMap);
		} else {
			pcPLNValide = true;
		}
		
		/* 41 - evaluationPLN. *******/
		boolean evaluationPLNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributEvaluationPLN = "evaluationPLN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralEvaluationPLN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitEvaluationPLN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralEvaluationPLN) {
			evaluationPLNValide 
				= this.validerEvaluationPLN(
						pDto, attributEvaluationPLN, erreursMap);
		} else {
			evaluationPLNValide = true;
		}
		
		/* 42 - pcNuitAnnuelN. *******/
		boolean pcNuitAnnuelNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributPcNuitAnnuelN = "pcNuitAnnuelN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralPcNuitAnnuelN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitPcNuitAnnuelN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralPcNuitAnnuelN) {
			pcNuitAnnuelNValide 
				= this.validerPcNuitAnnuelN(
						pDto, attributPcNuitAnnuelN, erreursMap);
		} else {
			pcNuitAnnuelNValide = true;
		}
		
		/* 43 - indiceFiabiliteMjaN. *******/
		boolean indiceFiabiliteMjaNValide = false;
		
		/* nom de l'attribut concerné par la validation. */
		final String attributIndiceFiabiliteMjaN = "indiceFiabiliteMjaN";
		
		/* récupère l'interrupteur général de validation des RG 
		 * de l'attribut auprès du Gestionnaire de préferences. */
		final Boolean interrupteurGeneralIndiceFiabiliteMjaN 
		= SectionHitGestionnairePreferencesRG
			.getValiderRGSectionHitIndiceFiabiliteMjaN();
		
		/* n'exécute le test de validation de l'attribut que si 
		 * son interrupteur général de validation des RG vaut true. */
		if (interrupteurGeneralIndiceFiabiliteMjaN) {
			indiceFiabiliteMjaNValide 
				= this.validerIndiceFiabiliteMjaN(
						pDto, attributIndiceFiabiliteMjaN, erreursMap);
		} else {
			indiceFiabiliteMjaNValide = true;
		}

		/* calcul de validité sur tous les attributs. */
		valide = numDepartementValide 
				&& numSectionValide 
				&& sensValide 
				&& natureValide 
				&& classeValide
				&& anneeTraitementValide
				&& zoneLibre1Valide 
				&& numRouteValide 
				&& indiceNumRouteValide 
				&& indiceLettreRouteValide 
				&& categorieAdminRouteValide 
				&& typeComptageValide 
				&& classementRouteValide 
				&& classeLargeurChausseeUValide 
				&& classeLargeurChausseesSValide 
				&& typeReseauValide 
				&& pRoupKValide 
				&& lieuDitOrigineValide 
				&& prOrigineValide 
				&& absOrigineValide 
				&& lieuDitExtremiteValide 
				&& prExtremiteValide 
				&& absExtremiteValide
				&& lieuDitComptageValide 
				&& prComptageValide 
				&& absComptageValide 
				&& longueurSectionValide 
				&& longueurRaseCampagneValide 
				&& numDepartementRattachementValide 
				&& numSectionRattachementValide 
				&& sensRattachementValide 
				&& numDepartementLimitropheValide 
				&& numSectionLimitropheValide 
				&& sensLimitropheValide 
				&& moisSectionnementValide 
				&& anneeSectionnementValide 
				&& zoneLibre2Valide 
				&& mjaNValide 
				&& modeCalculNValide 
				&& pcPLNValide 
				&& evaluationPLNValide 
				&& pcNuitAnnuelNValide 
				&& indiceFiabiliteMjaNValide;
		
		erreursMap.setValide(valide);
		
		/* calcul de l'admissibilité sur tous les attributs obligatoires. */
		final boolean admissible 
			= numDepartementValide 
			&& sensValide 
			&& anneeTraitementValide
			&& numRouteValide 
			&& indiceNumRouteValide 
			&& indiceLettreRouteValide 
			&& categorieAdminRouteValide 
			&& prOrigineValide 
			&& absOrigineValide 
			&& prExtremiteValide 
			&& absExtremiteValide
			&& prComptageValide 
			&& absComptageValide;
		
		erreursMap.setAdmissible(admissible);
		
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
	
	
	
	/* 8 - numRoute. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>numRoute</b></code>.<br/>
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
	private boolean validerNumRoute(
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
		final Boolean interrupteurNumRouteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRenseigne01();
		
		final Boolean interrupteurNumRouteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumRouteRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumRouteRenseigne01) {
			renseigne = this.validerRGSectionHitNumRouteRenseigne01(
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
			if (interrupteurNumRouteRegex02) {
				rg2 = this.validerRGSectionHitNumRouteRegex02(
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
				
	} // Fin de validerNumRoute(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>numRoute</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numRoute</code>.<br/>
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
	private boolean validerRGSectionHitNumRouteRenseigne01(
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
				.getMessageSectionHitNumRouteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumRoute())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumRouteRenseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>numRoute</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{4}] qui signifie 
	 * 'exactement 4 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numRoute</code>.<br/>
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
	private boolean validerRGSectionHitNumRouteRegex02(
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
				.getMessageSectionHitNumRouteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumRoute();
		
		final String motif = "\\d{4}";
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
		
	} // Fin de validerRGSectionHitNumRouteRegex02(...)._____________________
	
	
	
	/* 9 - indiceNumRoute. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>indiceNumRoute</b></code>.<br/>
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
	private boolean validerIndiceNumRoute(
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
		final Boolean interrupteurIndiceNumRouteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRenseigne01();
		
		final Boolean interrupteurIndiceNumRouteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceNumRouteRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurIndiceNumRouteRenseigne01) {
			renseigne = this.validerRGSectionHitIndiceNumRouteRenseigne01(
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
			if (interrupteurIndiceNumRouteRegex02) {
				rg2 = this.validerRGSectionHitIndiceNumRouteRegex02(
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
				
	} // Fin de validerIndiceNumRoute(...).________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>indiceNumRoute</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceNumRoute</code>.<br/>
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
	private boolean validerRGSectionHitIndiceNumRouteRenseigne01(
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
				.getMessageSectionHitIndiceNumRouteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getIndiceNumRoute())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitIndiceNumRouteRenseigne01(...)._________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>indiceNumRoute</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceNumRoute</code>.<br/>
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
	private boolean validerRGSectionHitIndiceNumRouteRegex02(
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
				.getMessageSectionHitIndiceNumRouteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getIndiceNumRoute();
		
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
		
	} // Fin de validerRGSectionHitIndiceNumRouteRegex02(...)._____________
	
	
	
	/* 10 - indiceLettreRoute. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>indiceLettreRoute</b></code>.<br/>
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
	private boolean validerIndiceLettreRoute(
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
		final Boolean interrupteurIndiceLettreRouteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRenseigne01();
		
		final Boolean interrupteurIndiceLettreRouteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceLettreRouteRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurIndiceLettreRouteRenseigne01) {
			renseigne = this.validerRGSectionHitIndiceLettreRouteRenseigne01(
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
			if (interrupteurIndiceLettreRouteRegex02) {
				rg2 = this.validerRGSectionHitIndiceLettreRouteRegex02(
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
				
	} // Fin de validerIndiceLettreRoute(...)._____________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>indiceLettreRoute</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceLettreRoute</code>.<br/>
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
	private boolean validerRGSectionHitIndiceLettreRouteRenseigne01(
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
				.getMessageSectionHitIndiceLettreRouteRenseigne01();
		
		// CONTROLE ***************
		if (pDto.getIndiceLettreRoute() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitIndiceLettreRouteRenseigne01(...).______

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>indiceLettreRoute</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [([a-zA-Z]|\\s){1}] qui signifie 
	 * 'exactement 1 lettre ou 1 espace'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceLettreRoute</code>.<br/>
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
	private boolean validerRGSectionHitIndiceLettreRouteRegex02(
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
				.getMessageSectionHitIndiceLettreRouteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getIndiceLettreRoute();
		
		final String motif = "([a-zA-Z]|\\s){1}";
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
		
	} // Fin de validerRGSectionHitIndiceLettreRouteRegex02(...).__________
	
	
	
	/* 11 - categorieAdminRoute. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>categorieAdminRoute</b></code>.<br/>
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
	private boolean validerCategorieAdminRoute(
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
		final Boolean interrupteurCategorieAdminRouteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRenseigne01();
		
		final Boolean interrupteurCategorieAdminRouteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteRegex02();
		
		final Boolean interrupteurCategorieAdminRouteNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitCategorieAdminRouteNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurCategorieAdminRouteRenseigne01) {
			renseigne = this.validerRGSectionHitCategorieAdminRouteRenseigne01(
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
			if (interrupteurCategorieAdminRouteRegex02) {
				rg2 = this.validerRGSectionHitCategorieAdminRouteRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurCategorieAdminRouteNomenclature03) {
				rg3 = this.validerRGSectionHitCategorieAdminRouteNomenclature03(
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
				
	} // Fin de validerCategorieAdminRoute(...).___________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>categorieAdminRoute</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>categorieAdminRoute</code>.<br/>
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
	private boolean validerRGSectionHitCategorieAdminRouteRenseigne01(
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
				.getMessageSectionHitCategorieAdminRouteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getCategorieAdminRoute())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitCategorieAdminRouteRenseigne01(...).____

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>categorieAdminRoute</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffre'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>categorieAdminRoute</code>.<br/>
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
	private boolean validerRGSectionHitCategorieAdminRouteRegex02(
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
				.getMessageSectionHitCategorieAdminRouteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getCategorieAdminRoute();
		
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
		
	} // Fin de validerRGSectionHitCategorieAdminRouteRegex02(...).________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>categorieAdminRoute</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesCategorieAdminRoute()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>categorieAdminRoute</code>.<br/>
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
	private boolean validerRGSectionHitCategorieAdminRouteNomenclature03(
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
				.getMessageSectionHitCategorieAdminRouteNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getCategorieAdminRoute();
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
			= FactoryNomenclatureHit.getSetClesPossiblesCategorieAdminRoute();
		
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
		
	} // Fin de validerRGSectionHitCategorieAdminRouteNomenclature03(...)._
	
	
	
	/* 12 - typeComptage. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>typeComptage</b></code>.<br/>
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
	private boolean validerTypeComptage(
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
		final Boolean interrupteurTypeComptageRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRenseigne01();
		
		final Boolean interrupteurTypeComptageRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageRegex02();
		
		final Boolean interrupteurTypeComptageNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeComptageNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurTypeComptageRenseigne01) {
			renseigne = this.validerRGSectionHitTypeComptageRenseigne01(
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
			if (interrupteurTypeComptageRegex02) {
				rg2 = this.validerRGSectionHitTypeComptageRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurTypeComptageNomenclature03) {
				rg3 = this.validerRGSectionHitTypeComptageNomenclature03(
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
				
	} // Fin de validerTypeComptage(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>typeComptage</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeComptage</code>.<br/>
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
	private boolean validerRGSectionHitTypeComptageRenseigne01(
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
				.getMessageSectionHitTypeComptageRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getTypeComptage())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitTypeComptageRenseigne01(...).___________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>typeComptage</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeComptage</code>.<br/>
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
	private boolean validerRGSectionHitTypeComptageRegex02(
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
				.getMessageSectionHitTypeComptageRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getTypeComptage();
		
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
		
	} // Fin de validerRGSectionHitTypeComptageRegex02(...)._______________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>typeComptage</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesTypeComptage()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeComptage</code>.<br/>
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
	private boolean validerRGSectionHitTypeComptageNomenclature03(
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
				.getMessageSectionHitTypeComptageNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getTypeComptage();
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
			= FactoryNomenclatureHit.getSetClesPossiblesTypeComptage();
		
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
		
	} // Fin de validerRGSectionHitTypeComptageNomenclature03(...).________
	
	
	
	/* 13 - classementRoute. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>classementRoute</b></code>.<br/>
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
	private boolean validerClassementRoute(
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
		final Boolean interrupteurClassementRouteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRenseigne01();
		
		final Boolean interrupteurClassementRouteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteRegex02();
		
		final Boolean interrupteurClassementRouteNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClassementRouteNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurClassementRouteRenseigne01) {
			renseigne = this.validerRGSectionHitClassementRouteRenseigne01(
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
			if (interrupteurClassementRouteRegex02) {
				rg2 = this.validerRGSectionHitClassementRouteRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurClassementRouteNomenclature03) {
				rg3 = this.validerRGSectionHitClassementRouteNomenclature03(
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
				
	} // Fin de validerClassementRoute(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>classementRoute</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classementRoute</code>.<br/>
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
	private boolean validerRGSectionHitClassementRouteRenseigne01(
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
				.getMessageSectionHitClassementRouteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getClassementRoute())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitClassementRouteRenseigne01(...).________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>classementRoute</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classementRoute</code>.<br/>
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
	private boolean validerRGSectionHitClassementRouteRegex02(
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
				.getMessageSectionHitClassementRouteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClassementRoute();
		
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
		
	} // Fin de validerRGSectionHitClassementRouteRegex02(...).____________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>classementRoute</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesClassementRoute()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classementRoute</code>.<br/>
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
	private boolean validerRGSectionHitClassementRouteNomenclature03(
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
				.getMessageSectionHitClassementRouteNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClassementRoute();
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
			= FactoryNomenclatureHit.getSetClesPossiblesClassementRoute();
		
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
		
	} // Fin de validerRGSectionHitClassementRouteNomenclature03(...)._____
	
	
	
	/* 14 - classeLargeurChausseeU. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>classeLargeurChausseeU</b></code>.<br/>
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
	private boolean validerClasseLargeurChausseeU(
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
		final Boolean interrupteurClasseLargeurChausseeURenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURenseigne01();
		
		final Boolean interrupteurClasseLargeurChausseeURegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeURegex02();
		
		final Boolean interrupteurClasseLargeurChausseeUNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseeUNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurClasseLargeurChausseeURenseigne01) {
			renseigne = this.validerRGSectionHitClasseLargeurChausseeURenseigne01(
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
			if (interrupteurClasseLargeurChausseeURegex02) {
				rg2 = this.validerRGSectionHitClasseLargeurChausseeURegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurClasseLargeurChausseeUNomenclature03) {
				rg3 = this.validerRGSectionHitClasseLargeurChausseeUNomenclature03(
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
				
	} // Fin de validerClasseLargeurChausseeU(...).________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>classeLargeurChausseeU</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseeU</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseeURenseigne01(
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
				.getMessageSectionHitClasseLargeurChausseeURenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getClasseLargeurChausseeU())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitClasseLargeurChausseeURenseigne01(...)._

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>classeLargeurChausseeU</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffre'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseeU</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseeURegex02(
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
				.getMessageSectionHitClasseLargeurChausseeURegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClasseLargeurChausseeU();
		
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
		
	} // Fin de validerRGSectionHitClasseLargeurChausseeURegex02(...)._____

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>classeLargeurChausseeU</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseeU()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseeU</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseeUNomenclature03(
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
				.getMessageSectionHitClasseLargeurChausseeUNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClasseLargeurChausseeU();
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
			= FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseeU();
		
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
		
	} // Fin de validerRGSectionHitClasseLargeurChausseeUNomenclature03(...).
	
	
	
	/* 15 - classeLargeurChausseesS. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>classeLargeurChausseesS</b></code>.<br/>
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
	private boolean validerClasseLargeurChausseesS(
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
		final Boolean interrupteurClasseLargeurChausseesSRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRenseigne01();
		
		final Boolean interrupteurClasseLargeurChausseesSRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSRegex02();
		
		final Boolean interrupteurClasseLargeurChausseesSNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitClasseLargeurChausseesSNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurClasseLargeurChausseesSRenseigne01) {
			renseigne = this.validerRGSectionHitClasseLargeurChausseesSRenseigne01(
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
			if (interrupteurClasseLargeurChausseesSRegex02) {
				rg2 = this.validerRGSectionHitClasseLargeurChausseesSRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurClasseLargeurChausseesSNomenclature03) {
				rg3 = this.validerRGSectionHitClasseLargeurChausseesSNomenclature03(
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
				
	} // Fin de validerClasseLargeurChausseesS(...)._______________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>classeLargeurChausseesS</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseesS</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseesSRenseigne01(
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
				.getMessageSectionHitClasseLargeurChausseesSRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getClasseLargeurChausseesS())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitClasseLargeurChausseesSRenseigne01(...).

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>classeLargeurChausseesS</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseesS</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseesSRegex02(
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
				.getMessageSectionHitClasseLargeurChausseesSRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClasseLargeurChausseesS();
		
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
		
	} // Fin de validerRGSectionHitClasseLargeurChausseesSRegex02(...).____

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>classeLargeurChausseesS</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseesS()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>classeLargeurChausseesS</code>.<br/>
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
	private boolean validerRGSectionHitClasseLargeurChausseesSNomenclature03(
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
				.getMessageSectionHitClasseLargeurChausseesSNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getClasseLargeurChausseesS();
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
			= FactoryNomenclatureHit.getSetClesPossiblesClasseLargeurChausseesS();
		
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
		
	} // Fin de validerRGSectionHitClasseLargeurChausseesSNomenclature03(...).	
	
	
	
	/* 16 - typeReseau. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>typeReseau</b></code>.<br/>
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
	private boolean validerTypeReseau(
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
		final Boolean interrupteurTypeReseauRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRenseigne01();
		
		final Boolean interrupteurTypeReseauRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauRegex02();
		
		final Boolean interrupteurTypeReseauNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitTypeReseauNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurTypeReseauRenseigne01) {
			renseigne = this.validerRGSectionHitTypeReseauRenseigne01(
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
			if (interrupteurTypeReseauRegex02) {
				rg2 = this.validerRGSectionHitTypeReseauRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurTypeReseauNomenclature03) {
				rg3 = this.validerRGSectionHitTypeReseauNomenclature03(
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
				
	} // Fin de validerTypeReseau(...).____________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>typeReseau</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeReseau</code>.<br/>
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
	private boolean validerRGSectionHitTypeReseauRenseigne01(
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
				.getMessageSectionHitTypeReseauRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getTypeReseau())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitTypeReseauRenseigne01(...)._____________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>typeReseau</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeReseau</code>.<br/>
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
	private boolean validerRGSectionHitTypeReseauRegex02(
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
				.getMessageSectionHitTypeReseauRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getTypeReseau();
		
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
		
	} // Fin de validerRGSectionHitTypeReseauRegex02(...)._________________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>typeReseau</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesTypeReseau()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>typeReseau</code>.<br/>
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
	private boolean validerRGSectionHitTypeReseauNomenclature03(
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
				.getMessageSectionHitTypeReseauNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getTypeReseau();
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
			= FactoryNomenclatureHit.getSetClesPossiblesTypeReseau();
		
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
		
	} // Fin de validerRGSectionHitTypeReseauNomenclature03(...).__________
	
	
	
	/* 17 - pRoupK. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>pRoupK</b></code>.<br/>
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
	private boolean validerPRoupK(
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
		final Boolean interrupteurPRoupKRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRenseigne01();
		
		final Boolean interrupteurPRoupKRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKRegex02();
		
		final Boolean interrupteurPRoupKNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPRoupKNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPRoupKRenseigne01) {
			renseigne = this.validerRGSectionHitPRoupKRenseigne01(
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
			if (interrupteurPRoupKRegex02) {
				rg2 = this.validerRGSectionHitPRoupKRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPRoupKNomenclature03) {
				rg3 = this.validerRGSectionHitPRoupKNomenclature03(
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
				
	} // Fin de validerPRoupK(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>pRoupK</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pRoupK</code>.<br/>
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
	private boolean validerRGSectionHitPRoupKRenseigne01(
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
				.getMessageSectionHitPRoupKRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPRoupK())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPRoupKRenseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>pRoupK</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pRoupK</code>.<br/>
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
	private boolean validerRGSectionHitPRoupKRegex02(
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
				.getMessageSectionHitPRoupKRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPRoupK();
		
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
		
	} // Fin de validerRGSectionHitPRoupKRegex02(...)._____________________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>pRoupK</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesPRoupK()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pRoupK</code>.<br/>
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
	private boolean validerRGSectionHitPRoupKNomenclature03(
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
				.getMessageSectionHitPRoupKNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPRoupK();
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
			= FactoryNomenclatureHit.getSetClesPossiblesPRoupK();
		
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
		
	} // Fin de validerRGSectionHitPRoupKNomenclature03(...).______________	
	
	
	
	/* 18 - lieuDitOrigine. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>lieuDitOrigine</b></code>.<br/>
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
	private boolean validerLieuDitOrigine(
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
		final Boolean interrupteurLieuDitOrigineRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRenseigne01();
		
		final Boolean interrupteurLieuDitOrigineRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitOrigineRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurLieuDitOrigineRenseigne01) {
			renseigne = this.validerRGSectionHitLieuDitOrigineRenseigne01(
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
			if (interrupteurLieuDitOrigineRegex02) {
				rg2 = this.validerRGSectionHitLieuDitOrigineRegex02(
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
				
	} // Fin de validerLieuDitOrigine(...).________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>lieuDitOrigine</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitOrigine</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitOrigineRenseigne01(
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
				.getMessageSectionHitLieuDitOrigineRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getLieuDitOrigine())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitLieuDitOrigineRenseigne01(...)._________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>lieuDitOrigine</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [.{20}] qui signifie 
	 * 'exactement 20 caractères quelconques'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitOrigine</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitOrigineRegex02(
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
				.getMessageSectionHitLieuDitOrigineRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLieuDitOrigine();
		
		final String motif = ".{20}";
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
		
	} // Fin de validerRGSectionHitLieuDitOrigineRegex02(...)._____________
	
	
	
	/* 19 - prOrigine. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>prOrigine</b></code>.<br/>
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
	private boolean validerPrOrigine(
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
		final Boolean interrupteurPrOrigineRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRenseigne01();
		
		final Boolean interrupteurPrOrigineRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineRegex02();

		final Boolean interrupteurPrOrigineNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrOrigineNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPrOrigineRenseigne01) {
			renseigne = this.validerRGSectionHitPrOrigineRenseigne01(
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
			if (interrupteurPrOrigineRegex02) {
				rg2 = this.validerRGSectionHitPrOrigineRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPrOrigineNumerique03) {
				rg3 = this.validerRGSectionHitPrOrigineNumerique03(
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
				
	} // Fin de validerPrOrigine(...)._____________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>prOrigine</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prOrigine</code>.<br/>
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
	private boolean validerRGSectionHitPrOrigineRenseigne01(
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
				.getMessageSectionHitPrOrigineRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPrOrigine())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPrOrigineRenseigne01(...).______________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>prOrigine</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{3} qui signifie 
	 * 'exactement 3 espaces ou chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prOrigine</code>.<br/>
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
	private boolean validerRGSectionHitPrOrigineRegex02(
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
				.getMessageSectionHitPrOrigineRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrOrigine();
		
		final String motif = "[\\d\\s]{3}";
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
		
	} // Fin de validerRGSectionHitPrOrigineRegex02(...).__________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>prOrigine</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prOrigine</code>.<br/>
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
	private boolean validerRGSectionHitPrOrigineNumerique03(
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
				.getMessageSectionHitPrOrigineNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrOrigine();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitPrOrigineNumerique03(...).______________	
	
	
	
	/* 20 - absOrigine. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>absOrigine</b></code>.<br/>
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
	private boolean validerAbsOrigine(
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
		final Boolean interrupteurAbsOrigineRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRenseigne01();
		
		final Boolean interrupteurAbsOrigineRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineRegex02();

		final Boolean interrupteurAbsOrigineNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsOrigineNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurAbsOrigineRenseigne01) {
			renseigne = this.validerRGSectionHitAbsOrigineRenseigne01(
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
			if (interrupteurAbsOrigineRegex02) {
				rg2 = this.validerRGSectionHitAbsOrigineRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurAbsOrigineNumerique03) {
				rg3 = this.validerRGSectionHitAbsOrigineNumerique03(
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
				
	} // Fin de validerAbsOrigine(...).____________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>absOrigine</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absOrigine</code>.<br/>
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
	private boolean validerRGSectionHitAbsOrigineRenseigne01(
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
				.getMessageSectionHitAbsOrigineRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getAbsOrigine())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitAbsOrigineRenseigne01(...)._____________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>absOrigine</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{4} qui signifie 
	 * 'exactement 4 chiffres ou espaces'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absOrigine</code>.<br/>
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
	private boolean validerRGSectionHitAbsOrigineRegex02(
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
				.getMessageSectionHitAbsOrigineRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsOrigine();
		
		final String motif = "[\\d\\s]{4}";
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
		
	} // Fin de validerRGSectionHitAbsOrigineRegex02(...)._________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>absOrigine</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absOrigine</code>.<br/>
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
	private boolean validerRGSectionHitAbsOrigineNumerique03(
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
				.getMessageSectionHitAbsOrigineNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsOrigine();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitAbsOrigineNumerique03(...)._____________	
	
	
	
	/* 21 - lieuDitExtremite. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>lieuDitExtremite</b></code>.<br/>
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
	private boolean validerLieuDitExtremite(
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
		final Boolean interrupteurLieuDitExtremiteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRenseigne01();
		
		final Boolean interrupteurLieuDitExtremiteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitExtremiteRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurLieuDitExtremiteRenseigne01) {
			renseigne = this.validerRGSectionHitLieuDitExtremiteRenseigne01(
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
			if (interrupteurLieuDitExtremiteRegex02) {
				rg2 = this.validerRGSectionHitLieuDitExtremiteRegex02(
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
				
	} // Fin de validerLieuDitExtremite(...).______________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>lieuDitExtremite</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitExtremite</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitExtremiteRenseigne01(
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
				.getMessageSectionHitLieuDitExtremiteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getLieuDitExtremite())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitLieuDitExtremiteRenseigne01(...)._______

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>lieuDitExtremite</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [.{20}] qui signifie 
	 * 'exactement 20 caractères quelconques'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitExtremite</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitExtremiteRegex02(
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
				.getMessageSectionHitLieuDitExtremiteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLieuDitExtremite();
		
		final String motif = ".{20}";
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
		
	} // Fin de validerRGSectionHitLieuDitExtremiteRegex02(...).___________
	
	
	
	/* 22 - prExtremite. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>prExtremite</b></code>.<br/>
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
	private boolean validerPrExtremite(
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
		final Boolean interrupteurPrExtremiteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRenseigne01();
		
		final Boolean interrupteurPrExtremiteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteRegex02();

		final Boolean interrupteurPrExtremiteNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrExtremiteNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPrExtremiteRenseigne01) {
			renseigne = this.validerRGSectionHitPrExtremiteRenseigne01(
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
			if (interrupteurPrExtremiteRegex02) {
				rg2 = this.validerRGSectionHitPrExtremiteRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPrExtremiteNumerique03) {
				rg3 = this.validerRGSectionHitPrExtremiteNumerique03(
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
				
	} // Fin de validerPrExtremite(...).___________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>prExtremite</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prExtremite</code>.<br/>
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
	private boolean validerRGSectionHitPrExtremiteRenseigne01(
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
				.getMessageSectionHitPrExtremiteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPrExtremite())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPrExtremiteRenseigne01(...).____________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>prExtremite</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{3} qui signifie 
	 * 'exactement 3 espaces ou chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prExtremite</code>.<br/>
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
	private boolean validerRGSectionHitPrExtremiteRegex02(
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
				.getMessageSectionHitPrExtremiteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrExtremite();
		
		final String motif = "[\\d\\s]{3}";
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
		
	} // Fin de validerRGSectionHitPrExtremiteRegex02(...).________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>prExtremite</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prExtremite</code>.<br/>
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
	private boolean validerRGSectionHitPrExtremiteNumerique03(
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
				.getMessageSectionHitPrExtremiteNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrExtremite();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitPrExtremiteNumerique03(...).____________	
	
	
	
	/* 23 - absExtremite. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>absExtremite</b></code>.<br/>
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
	private boolean validerAbsExtremite(
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
		final Boolean interrupteurAbsExtremiteRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRenseigne01();
		
		final Boolean interrupteurAbsExtremiteRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteRegex02();

		final Boolean interrupteurAbsExtremiteNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsExtremiteNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurAbsExtremiteRenseigne01) {
			renseigne = this.validerRGSectionHitAbsExtremiteRenseigne01(
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
			if (interrupteurAbsExtremiteRegex02) {
				rg2 = this.validerRGSectionHitAbsExtremiteRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurAbsExtremiteNumerique03) {
				rg3 = this.validerRGSectionHitAbsExtremiteNumerique03(
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
				
	} // Fin de validerAbsExtremite(...).__________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>absExtremite</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absExtremite</code>.<br/>
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
	private boolean validerRGSectionHitAbsExtremiteRenseigne01(
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
				.getMessageSectionHitAbsExtremiteRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getAbsExtremite())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitAbsExtremiteRenseigne01(...).___________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>absExtremite</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{4} qui signifie 
	 * 'exactement 4 chiffres ou espaces'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absExtremite</code>.<br/>
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
	private boolean validerRGSectionHitAbsExtremiteRegex02(
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
				.getMessageSectionHitAbsExtremiteRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsExtremite();
		
		final String motif = "[\\d\\s]{4}";
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
		
	} // Fin de validerRGSectionHitAbsExtremiteRegex02(...)._______________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>absExtremite</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absExtremite</code>.<br/>
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
	private boolean validerRGSectionHitAbsExtremiteNumerique03(
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
				.getMessageSectionHitAbsExtremiteNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsExtremite();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitAbsExtremiteNumerique03(...).___________	
	
	
	
	/* 24 - lieuDitComptage. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>lieuDitComptage</b></code>.<br/>
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
	private boolean validerLieuDitComptage(
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
		final Boolean interrupteurLieuDitComptageRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRenseigne01();
		
		final Boolean interrupteurLieuDitComptageRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLieuDitComptageRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurLieuDitComptageRenseigne01) {
			renseigne = this.validerRGSectionHitLieuDitComptageRenseigne01(
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
			if (interrupteurLieuDitComptageRegex02) {
				rg2 = this.validerRGSectionHitLieuDitComptageRegex02(
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
				
	} // Fin de validerLieuDitComptage(...).______________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>lieuDitComptage</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitComptage</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitComptageRenseigne01(
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
				.getMessageSectionHitLieuDitComptageRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getLieuDitComptage())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitLieuDitComptageRenseigne01(...)._______

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>lieuDitComptage</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [.{20}] qui signifie 
	 * 'exactement 20 caractères quelconques'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>lieuDitComptage</code>.<br/>
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
	private boolean validerRGSectionHitLieuDitComptageRegex02(
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
				.getMessageSectionHitLieuDitComptageRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLieuDitComptage();
		
		final String motif = ".{20}";
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
		
	} // Fin de validerRGSectionHitLieuDitComptageRegex02(...).___________
	
	
	
	/* 22 - prComptage. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>prComptage</b></code>.<br/>
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
	private boolean validerPrComptage(
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
		final Boolean interrupteurPrComptageRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRenseigne01();
		
		final Boolean interrupteurPrComptageRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageRegex02();

		final Boolean interrupteurPrComptageNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPrComptageNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPrComptageRenseigne01) {
			renseigne = this.validerRGSectionHitPrComptageRenseigne01(
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
			if (interrupteurPrComptageRegex02) {
				rg2 = this.validerRGSectionHitPrComptageRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPrComptageNumerique03) {
				rg3 = this.validerRGSectionHitPrComptageNumerique03(
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
				
	} // Fin de validerPrComptage(...).___________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>prComptage</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prComptage</code>.<br/>
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
	private boolean validerRGSectionHitPrComptageRenseigne01(
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
				.getMessageSectionHitPrComptageRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPrComptage())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPrComptageRenseigne01(...).____________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>prComptage</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{3} qui signifie 
	 * 'exactement 3 espaces ou chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prComptage</code>.<br/>
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
	private boolean validerRGSectionHitPrComptageRegex02(
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
				.getMessageSectionHitPrComptageRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrComptage();
		
		final String motif = "[\\d\\s]{3}";
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
		
	} // Fin de validerRGSectionHitPrComptageRegex02(...).________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>prComptage</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>prComptage</code>.<br/>
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
	private boolean validerRGSectionHitPrComptageNumerique03(
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
				.getMessageSectionHitPrComptageNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPrComptage();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitPrComptageNumerique03(...).____________	
	
	
	
	/* 23 - absComptage. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>absComptage</b></code>.<br/>
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
	private boolean validerAbsComptage(
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
		final Boolean interrupteurAbsComptageRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRenseigne01();
		
		final Boolean interrupteurAbsComptageRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageRegex02();

		final Boolean interrupteurAbsComptageNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAbsComptageNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurAbsComptageRenseigne01) {
			renseigne = this.validerRGSectionHitAbsComptageRenseigne01(
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
			if (interrupteurAbsComptageRegex02) {
				rg2 = this.validerRGSectionHitAbsComptageRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurAbsComptageNumerique03) {
				rg3 = this.validerRGSectionHitAbsComptageNumerique03(
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
				
	} // Fin de validerAbsComptage(...).__________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>absComptage</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absComptage</code>.<br/>
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
	private boolean validerRGSectionHitAbsComptageRenseigne01(
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
				.getMessageSectionHitAbsComptageRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getAbsComptage())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitAbsComptageRenseigne01(...).___________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>absComptage</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d\\s]{4} qui signifie 
	 * 'exactement 4 chiffres ou espaces'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absComptage</code>.<br/>
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
	private boolean validerRGSectionHitAbsComptageRegex02(
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
				.getMessageSectionHitAbsComptageRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsComptage();
		
		final String motif = "[\\d\\s]{4}";
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
		
	} // Fin de validerRGSectionHitAbsComptageRegex02(...)._______________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>absComptage</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>absComptage</code>.<br/>
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
	private boolean validerRGSectionHitAbsComptageNumerique03(
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
				.getMessageSectionHitAbsComptageNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAbsComptage();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitAbsComptageNumerique03(...).___________	
	
	
	
	/* 27 - longueurSection. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>longueurSection</b></code>.<br/>
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
	private boolean validerLongueurSection(
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
		final Boolean interrupteurLongueurSectionRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRenseigne01();
		
		final Boolean interrupteurLongueurSectionRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionRegex02();

		final Boolean interrupteurLongueurSectionNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurSectionNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurLongueurSectionRenseigne01) {
			renseigne = this.validerRGSectionHitLongueurSectionRenseigne01(
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
			if (interrupteurLongueurSectionRegex02) {
				rg2 = this.validerRGSectionHitLongueurSectionRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurLongueurSectionNumerique03) {
				rg3 = this.validerRGSectionHitLongueurSectionNumerique03(
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
				
	} // Fin de validerLongueurSection(...)._______________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>longueurSection</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurSection</code>.<br/>
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
	private boolean validerRGSectionHitLongueurSectionRenseigne01(
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
				.getMessageSectionHitLongueurSectionRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getLongueurSection())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitLongueurSectionRenseigne01(...).________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>longueurSection</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{6}] qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurSection</code>.<br/>
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
	private boolean validerRGSectionHitLongueurSectionRegex02(
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
				.getMessageSectionHitLongueurSectionRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLongueurSection();
		
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
		
	} // Fin de validerRGSectionHitLongueurSectionRegex02(...).____________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>longueurSection</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurSection</code>.<br/>
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
	private boolean validerRGSectionHitLongueurSectionNumerique03(
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
				.getMessageSectionHitLongueurSectionNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLongueurSection();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitLongueurSectionNumerique03(...).________	
	
	
	
	/* 28 - longueurRaseCampagne. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>longueurRaseCampagne</b></code>.<br/>
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
	private boolean validerLongueurRaseCampagne(
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
		final Boolean interrupteurLongueurRaseCampagneRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRenseigne01();
		
		final Boolean interrupteurLongueurRaseCampagneRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneRegex02();

		final Boolean interrupteurLongueurRaseCampagneNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitLongueurRaseCampagneNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurLongueurRaseCampagneRenseigne01) {
			renseigne = this.validerRGSectionHitLongueurRaseCampagneRenseigne01(
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
			if (interrupteurLongueurRaseCampagneRegex02) {
				rg2 = this.validerRGSectionHitLongueurRaseCampagneRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurLongueurRaseCampagneNumerique03) {
				rg3 = this.validerRGSectionHitLongueurRaseCampagneNumerique03(
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
				
	} // Fin de validerLongueurRaseCampagne(...).__________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>longueurRaseCampagne</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurRaseCampagne</code>.<br/>
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
	private boolean validerRGSectionHitLongueurRaseCampagneRenseigne01(
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
				.getMessageSectionHitLongueurRaseCampagneRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getLongueurRaseCampagne())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitLongueurRaseCampagneRenseigne01(...).___

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>longueurRaseCampagne</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{6}] qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurRaseCampagne</code>.<br/>
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
	private boolean validerRGSectionHitLongueurRaseCampagneRegex02(
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
				.getMessageSectionHitLongueurRaseCampagneRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLongueurRaseCampagne();
		
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
		
	} // Fin de validerRGSectionHitLongueurRaseCampagneRegex02(...)._______

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>longueurRaseCampagne</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>longueurRaseCampagne</code>.<br/>
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
	private boolean validerRGSectionHitLongueurRaseCampagneNumerique03(
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
				.getMessageSectionHitLongueurRaseCampagneNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getLongueurRaseCampagne();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitLongueurRaseCampagneNumerique03(...).___
	
	
	
	/* 29 - numDepartementRattachement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>numDepartementRattachement</b></code>.<br/>
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
	private boolean validerNumDepartementRattachement(
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
		final Boolean interrupteurNumDepartementRattachementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRenseigne01();
		
		final Boolean interrupteurNumDepartementRattachementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementRattachementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumDepartementRattachementRenseigne01) {
			renseigne = this.validerRGSectionHitNumDepartementRattachementRenseigne01(
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
			if (interrupteurNumDepartementRattachementRegex02) {
				rg2 = this.validerRGSectionHitNumDepartementRattachementRegex02(
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
				
	} // Fin de validerNumDepartementRattachement(...).____________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>numDepartementRattachement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numDepartementRattachement</code>.<br/>
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
	private boolean validerRGSectionHitNumDepartementRattachementRenseigne01(
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
				.getMessageSectionHitNumDepartementRattachementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumDepartementRattachement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumDepartementRattachementRenseigne01(...).

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>numDepartementRattachement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{3}] qui signifie 
	 * 'exactement 3 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numDepartementRattachement</code>.<br/>
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
	private boolean validerRGSectionHitNumDepartementRattachementRegex02(
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
				.getMessageSectionHitNumDepartementRattachementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumDepartementRattachement();
		
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
		
	} // Fin de validerRGSectionHitNumDepartementRattachementRegex02(...)._
	
	
	
	/* 30 - numSectionRattachement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>numSectionRattachement</b></code>.<br/>
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
	private boolean validerNumSectionRattachement(
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
		final Boolean interrupteurNumSectionRattachementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRenseigne01();
		
		final Boolean interrupteurNumSectionRattachementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionRattachementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumSectionRattachementRenseigne01) {
			renseigne = this.validerRGSectionHitNumSectionRattachementRenseigne01(
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
			if (interrupteurNumSectionRattachementRegex02) {
				rg2 = this.validerRGSectionHitNumSectionRattachementRegex02(
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
				
	} // Fin de validerNumSectionRattachement(...).________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>numSectionRattachement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numSectionRattachement</code>.<br/>
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
	private boolean validerRGSectionHitNumSectionRattachementRenseigne01(
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
				.getMessageSectionHitNumSectionRattachementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumSectionRattachement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumSectionRattachementRenseigne01(...)._

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>numSectionRattachement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{6}] qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numSectionRattachement</code>.<br/>
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
	private boolean validerRGSectionHitNumSectionRattachementRegex02(
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
				.getMessageSectionHitNumSectionRattachementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumSectionRattachement();
		
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
		
	} // Fin de validerRGSectionHitNumSectionRattachementRegex02(...)._____
	
	
	
	/* 31 - sensRattachement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>sensRattachement</b></code>.<br/>
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
	private boolean validerSensRattachement(
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
		final Boolean interrupteurSensRattachementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRenseigne01();
		
		final Boolean interrupteurSensRattachementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementRegex02();
		
		final Boolean interrupteurSensRattachementNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensRattachementNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurSensRattachementRenseigne01) {
			renseigne = this.validerRGSectionHitSensRattachementRenseigne01(
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
			if (interrupteurSensRattachementRegex02) {
				rg2 = this.validerRGSectionHitSensRattachementRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurSensRattachementNomenclature03) {
				rg3 = this.validerRGSectionHitSensRattachementNomenclature03(
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
				
	} // Fin de validerSensRattachement(...).______________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>sensRattachement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensRattachement</code>.<br/>
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
	private boolean validerRGSectionHitSensRattachementRenseigne01(
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
				.getMessageSectionHitSensRattachementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getSensRattachement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitSensRattachementRenseigne01(...)._______

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>sensRattachement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensRattachement</code>.<br/>
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
	private boolean validerRGSectionHitSensRattachementRegex02(
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
				.getMessageSectionHitSensRattachementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSensRattachement();
		
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
		
	} // Fin de validerRGSectionHitSensRattachementRegex02(...).___________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>sensRattachement</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesSensRattachement()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensRattachement</code>.<br/>
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
	private boolean validerRGSectionHitSensRattachementNomenclature03(
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
				.getMessageSectionHitSensRattachementNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSensRattachement();
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
			= FactoryNomenclatureHit.getSetClesPossiblesSensRattachement();
		
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
		
	} // Fin de validerRGSectionHitSensRattachementNomenclature03(...).____
	
	
	
	/* 32 - numDepartementLimitrophe. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>numDepartementLimitrophe</b></code>.<br/>
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
	private boolean validerNumDepartementLimitrophe(
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
		final Boolean interrupteurNumDepartementLimitropheRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRenseigne01();
		
		final Boolean interrupteurNumDepartementLimitropheRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumDepartementLimitropheRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumDepartementLimitropheRenseigne01) {
			renseigne = this.validerRGSectionHitNumDepartementLimitropheRenseigne01(
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
			if (interrupteurNumDepartementLimitropheRegex02) {
				rg2 = this.validerRGSectionHitNumDepartementLimitropheRegex02(
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
				
	} // Fin de validerNumDepartementLimitrophe(...).______________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>numDepartementLimitrophe</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numDepartementLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitNumDepartementLimitropheRenseigne01(
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
				.getMessageSectionHitNumDepartementLimitropheRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumDepartementLimitrophe())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumDepartementLimitropheRenseigne01(...).

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>numDepartementLimitrophe</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{3}] qui signifie 
	 * 'exactement 3 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numDepartementLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitNumDepartementLimitropheRegex02(
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
				.getMessageSectionHitNumDepartementLimitropheRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumDepartementLimitrophe();
		
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
		
	} // Fin de validerRGSectionHitNumDepartementLimitropheRegex02(...).___
	
	
	
	/* 33 - numSectionLimitrophe. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>numSectionLimitrophe</b></code>.<br/>
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
	private boolean validerNumSectionLimitrophe(
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
		final Boolean interrupteurNumSectionLimitropheRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRenseigne01();
		
		final Boolean interrupteurNumSectionLimitropheRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitNumSectionLimitropheRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurNumSectionLimitropheRenseigne01) {
			renseigne = this.validerRGSectionHitNumSectionLimitropheRenseigne01(
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
			if (interrupteurNumSectionLimitropheRegex02) {
				rg2 = this.validerRGSectionHitNumSectionLimitropheRegex02(
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
				
	} // Fin de validerNumSectionLimitrophe(...).__________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>numSectionLimitrophe</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numSectionLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitNumSectionLimitropheRenseigne01(
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
				.getMessageSectionHitNumSectionLimitropheRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getNumSectionLimitrophe())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitNumSectionLimitropheRenseigne01(...).___

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>numSectionLimitrophe</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{6}] qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>numSectionLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitNumSectionLimitropheRegex02(
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
				.getMessageSectionHitNumSectionLimitropheRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getNumSectionLimitrophe();
		
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
		
	} // Fin de validerRGSectionHitNumSectionLimitropheRegex02(...)._______
	
	
	
	/* 34 - sensLimitrophe. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>sensLimitrophe</b></code>.<br/>
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
	private boolean validerSensLimitrophe(
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
		final Boolean interrupteurSensLimitropheRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRenseigne01();
		
		final Boolean interrupteurSensLimitropheRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheRegex02();
		
		final Boolean interrupteurSensLimitropheNomenclature03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitSensLimitropheNomenclature03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurSensLimitropheRenseigne01) {
			renseigne = this.validerRGSectionHitSensLimitropheRenseigne01(
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
			if (interrupteurSensLimitropheRegex02) {
				rg2 = this.validerRGSectionHitSensLimitropheRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurSensLimitropheNomenclature03) {
				rg3 = this.validerRGSectionHitSensLimitropheNomenclature03(
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
				
	} // Fin de validerSensLimitrophe(...).________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>sensLimitrophe</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitSensLimitropheRenseigne01(
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
				.getMessageSectionHitSensLimitropheRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getSensLimitrophe())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitSensLimitropheRenseigne01(...)._________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>sensLimitrophe</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{1}] qui signifie 
	 * 'exactement 1 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitSensLimitropheRegex02(
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
				.getMessageSectionHitSensLimitropheRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSensLimitrophe();
		
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
		
	} // Fin de validerRGSectionHitSensLimitropheRegex02(...)._____________

	
	
	/**
	 * valide la RG NOMENCLATURE pour 
	 * l'attribut <b>sensLimitrophe</b>.<br/>
	 * <ul>
	 * <li>retourne false si la valeur à contrôler n'est pas homogène 
	 * à un entier.</li>
	 * <li>récupère le Set des valeurs possibles auprès de la 
	 * FactoryNomenclature (<code><b>
	 * FactoryNomenclatureHit.getSetClesPossiblesSensLimitrophe()</b></code>).</li>
	 * <li>retourne false si la valeur à contrôler n'appartient 
	 * pas à la nomenclature.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>sensLimitrophe</code>.<br/>
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
	private boolean validerRGSectionHitSensLimitropheNomenclature03(
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
				.getMessageSectionHitSensLimitropheNomenclature03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getSensLimitrophe();
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
			= FactoryNomenclatureHit.getSetClesPossiblesSensLimitrophe();
		
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
		
	} // Fin de validerRGSectionHitSensLimitropheNomenclature03(...).______
	
	
	
	/* 35 - moisSectionnement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>moisSectionnement</b></code>.<br/>
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
	private boolean validerMoisSectionnement(
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
		final Boolean interrupteurMoisSectionnementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRenseigne01();
		
		final Boolean interrupteurMoisSectionnementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMoisSectionnementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurMoisSectionnementRenseigne01) {
			renseigne = this.validerRGSectionHitMoisSectionnementRenseigne01(
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
			if (interrupteurMoisSectionnementRegex02) {
				rg2 = this.validerRGSectionHitMoisSectionnementRegex02(
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
				
	} // Fin de validerMoisSectionnement(...)._____________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>moisSectionnement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>moisSectionnement</code>.<br/>
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
	private boolean validerRGSectionHitMoisSectionnementRenseigne01(
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
				.getMessageSectionHitMoisSectionnementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getMoisSectionnement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitMoisSectionnementRenseigne01(...).______

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>moisSectionnement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{2}] qui signifie 
	 * 'exactement 2 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>moisSectionnement</code>.<br/>
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
	private boolean validerRGSectionHitMoisSectionnementRegex02(
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
				.getMessageSectionHitMoisSectionnementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getMoisSectionnement();
		
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
		
		final Set<String> setMois = new HashSet<>();
		setMois.add("01");
		setMois.add("02");
		setMois.add("03");
		setMois.add("04");
		setMois.add("05");
		setMois.add("06");
		setMois.add("07");
		setMois.add("08");
		setMois.add("09");
		setMois.add("10");
		setMois.add("11");
		setMois.add("12");
		
		if (!setMois.contains(valeurAControler)) {
			
			final String messageMois = "le mois de sectionnement doit être homogène à un mois ('01' à '12')";
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, messageMois);
			
			/* retoune false si la RG n'est pas validée. */
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitMoisSectionnementRegex02(...)._____________________	
	
	
	
	/* 36 - anneeSectionnement. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>anneeSectionnement</b></code>.<br/>
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
	private boolean validerAnneeSectionnement(
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
		final Boolean interrupteurAnneeSectionnementRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRenseigne01();
		
		final Boolean interrupteurAnneeSectionnementRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitAnneeSectionnementRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurAnneeSectionnementRenseigne01) {
			renseigne = this.validerRGSectionHitAnneeSectionnementRenseigne01(
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
			if (interrupteurAnneeSectionnementRegex02) {
				rg2 = this.validerRGSectionHitAnneeSectionnementRegex02(
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
				
	} // Fin de validerAnneeSectionnement(...).____________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>anneeSectionnement</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>anneeSectionnement</code>.<br/>
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
	private boolean validerRGSectionHitAnneeSectionnementRenseigne01(
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
				.getMessageSectionHitAnneeSectionnementRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getAnneeSectionnement())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitAnneeSectionnementRenseigne01(...)._____

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>anneeSectionnement</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{2}] qui signifie 
	 * 'exactement 2 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>anneeSectionnement</code>.<br/>
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
	private boolean validerRGSectionHitAnneeSectionnementRegex02(
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
				.getMessageSectionHitAnneeSectionnementRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getAnneeSectionnement();
		
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
		
	} // Fin de validerRGSectionHitAnneeSectionnementRegex02(...)._________
	
	
	
	/* 37 - zoneLibre2. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>zoneLibre2</b></code>.<br/>
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
	private boolean validerZoneLibre2(
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
		final Boolean interrupteurZoneLibre2Renseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Renseigne01();
		
		final Boolean interrupteurZoneLibre2Regex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitZoneLibre2Regex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurZoneLibre2Renseigne01) {
			renseigne = this.validerRGSectionHitZoneLibre2Renseigne01(
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
			if (interrupteurZoneLibre2Regex02) {
				rg2 = this.validerRGSectionHitZoneLibre2Regex02(
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
				
	} // Fin de validerZoneLibre2(...).____________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>zoneLibre2</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>zoneLibre2</code>.<br/>
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
	private boolean validerRGSectionHitZoneLibre2Renseigne01(
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
				.getMessageSectionHitZoneLibre2Renseigne01();
		
		// CONTROLE ***************
		if (pDto.getZoneLibre2() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitZoneLibre2Renseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>zoneLibre2</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\s{6}] qui signifie 
	 * 'exactement 6 espaces (caractère blanc = espace, \t, \n, ...)'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>zoneLibre2</code>.<br/>
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
	private boolean validerRGSectionHitZoneLibre2Regex02(
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
				.getMessageSectionHitZoneLibre2Regex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getZoneLibre2();
		
		final String motif = "\\s{6}";
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
		
	} // Fin de validerRGSectionHitZoneLibre2Regex02(...)._________________
	
	
	
	/* 38 - mjaN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>mjaN</b></code>.<br/>
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
	private boolean validerMjaN(
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
		final Boolean interrupteurMjaNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRenseigne01();
		
		final Boolean interrupteurMjaNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNRegex02();

		final Boolean interrupteurMjaNNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitMjaNNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurMjaNRenseigne01) {
			renseigne = this.validerRGSectionHitMjaNRenseigne01(
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
			if (interrupteurMjaNRegex02) {
				rg2 = this.validerRGSectionHitMjaNRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurMjaNNumerique03) {
				rg3 = this.validerRGSectionHitMjaNNumerique03(
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
				
	} // Fin de validerMjaN(...).__________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>mjaN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>mjaN</code>.<br/>
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
	private boolean validerRGSectionHitMjaNRenseigne01(
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
				.getMessageSectionHitMjaNRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getMjaN())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitMjaNRenseigne01(...).___________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>mjaN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{6}] qui signifie 
	 * 'exactement 6 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>mjaN</code>.<br/>
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
	private boolean validerRGSectionHitMjaNRegex02(
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
				.getMessageSectionHitMjaNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getMjaN();
		
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
		
	} // Fin de validerRGSectionHitMjaNRegex02(...)._______________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>mjaN</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>mjaN</code>.<br/>
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
	private boolean validerRGSectionHitMjaNNumerique03(
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
				.getMessageSectionHitMjaNNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getMjaN();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitMjaNNumerique03(...).___________________	
	
	
	
	/* 39 - modeCalculN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>modeCalculN</b></code>.<br/>
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
	private boolean validerModeCalculN(
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
		final Boolean interrupteurModeCalculNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRenseigne01();
		
		final Boolean interrupteurModeCalculNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitModeCalculNRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurModeCalculNRenseigne01) {
			renseigne = this.validerRGSectionHitModeCalculNRenseigne01(
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
			if (interrupteurModeCalculNRegex02) {
				rg2 = this.validerRGSectionHitModeCalculNRegex02(
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
				
	} // Fin de validerModeCalculN(...).___________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>modeCalculN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>modeCalculN</code>.<br/>
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
	private boolean validerRGSectionHitModeCalculNRenseigne01(
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
				.getMessageSectionHitModeCalculNRenseigne01();
		
		// CONTROLE ***************
		if (pDto.getModeCalculN() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitModeCalculNRenseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>modeCalculN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [a-zA-Z0-9\\s]{1} qui signifie 
	 * 'exactement 1 caractère de mot ou 1 espace'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>modeCalculN</code>.<br/>
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
	private boolean validerRGSectionHitModeCalculNRegex02(
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
				.getMessageSectionHitModeCalculNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getModeCalculN();
		
		final String motif = "[a-zA-Z0-9\\s]{1}";
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
		
	} // Fin de validerRGSectionHitModeCalculNRegex02(...).________________
	
	
	
	/* 40 - pcPLN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>pcPLN</b></code>.<br/>
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
	private boolean validerPcPLN(
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
		final Boolean interrupteurPcPLNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRenseigne01();
		
		final Boolean interrupteurPcPLNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNRegex02();

		final Boolean interrupteurPcPLNNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcPLNNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPcPLNRenseigne01) {
			renseigne = this.validerRGSectionHitPcPLNRenseigne01(
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
			if (interrupteurPcPLNRegex02) {
				rg2 = this.validerRGSectionHitPcPLNRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPcPLNNumerique03) {
				rg3 = this.validerRGSectionHitPcPLNNumerique03(
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
				
	} // Fin de validerPcPLN(...).________________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>pcPLN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcPLN</code>.<br/>
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
	private boolean validerRGSectionHitPcPLNRenseigne01(
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
				.getMessageSectionHitPcPLNRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPcPLN())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPcPLNRenseigne01(...).__________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>pcPLN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{3}] qui signifie 
	 * 'exactement 3 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcPLN</code>.<br/>
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
	private boolean validerRGSectionHitPcPLNRegex02(
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
				.getMessageSectionHitPcPLNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPcPLN();
		
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
		
	} // Fin de validerRGSectionHitPcPLNRegex02(...).______________________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>pcPLN</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcPLN</code>.<br/>
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
	private boolean validerRGSectionHitPcPLNNumerique03(
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
				.getMessageSectionHitPcPLNNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPcPLN();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitPcPLNNumerique03(...).__________________
	
	
	
	/* 41 - evaluationPLN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>evaluationPLN</b></code>.<br/>
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
	private boolean validerEvaluationPLN(
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
		final Boolean interrupteurEvaluationPLNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRenseigne01();
		
		final Boolean interrupteurEvaluationPLNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitEvaluationPLNRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurEvaluationPLNRenseigne01) {
			renseigne = this.validerRGSectionHitEvaluationPLNRenseigne01(
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
			if (interrupteurEvaluationPLNRegex02) {
				rg2 = this.validerRGSectionHitEvaluationPLNRegex02(
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
				
	} // Fin de validerEvaluationPLN(...).___________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>evaluationPLN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>evaluationPLN</code>.<br/>
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
	private boolean validerRGSectionHitEvaluationPLNRenseigne01(
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
				.getMessageSectionHitEvaluationPLNRenseigne01();
		
		// CONTROLE ***************
		if (pDto.getEvaluationPLN() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitEvaluationPLNRenseigne01(...)._________________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>evaluationPLN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [a-zA-Z0-9\\s]{1} qui signifie 
	 * 'exactement 1 caractère de mot ou 1 espace'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>evaluationPLN</code>.<br/>
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
	private boolean validerRGSectionHitEvaluationPLNRegex02(
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
				.getMessageSectionHitEvaluationPLNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getEvaluationPLN();
		
		final String motif = "[a-zA-Z0-9\\s]{1}";
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
		
	} // Fin de validerRGSectionHitEvaluationPLNRegex02(...).________________
	
	
	
	/* 42 - pcNuitAnnuelN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>pcNuitAnnuelN</b></code>.<br/>
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
	private boolean validerPcNuitAnnuelN(
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
		final Boolean interrupteurPcNuitAnnuelNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRenseigne01();
		
		final Boolean interrupteurPcNuitAnnuelNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNRegex02();

		final Boolean interrupteurPcNuitAnnuelNNumerique03 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitPcNuitAnnuelNNumerique03();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		boolean rg3 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurPcNuitAnnuelNRenseigne01) {
			renseigne = this.validerRGSectionHitPcNuitAnnuelNRenseigne01(
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
			if (interrupteurPcNuitAnnuelNRegex02) {
				rg2 = this.validerRGSectionHitPcNuitAnnuelNRegex02(
						pAttribut, pDto, pErreursMaps);
			} else {
				/* la validation de la RG retourne systématiquement true 
				 * si son interrupteur n'est pas à true. */
				rg2 = true;
			}

			
			/* applique le contrôle si interrupteur général 
			 * + interrupteur de chaque RG + renseigne sont à true. */
			if (interrupteurPcNuitAnnuelNNumerique03) {
				rg3 = this.validerRGSectionHitPcNuitAnnuelNNumerique03(
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
				
	} // Fin de validerPcNuitAnnuelN(...)._________________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>pcNuitAnnuelN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcNuitAnnuelN</code>.<br/>
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
	private boolean validerRGSectionHitPcNuitAnnuelNRenseigne01(
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
				.getMessageSectionHitPcNuitAnnuelNRenseigne01();
		
		// CONTROLE ***************
		if (StringUtils.isBlank(pDto.getPcNuitAnnuelN())) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitPcNuitAnnuelNRenseigne01(...).__________

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>pcNuitAnnuelN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [\\d{3}] qui signifie 
	 * 'exactement 3 chiffres'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcNuitAnnuelN</code>.<br/>
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
	private boolean validerRGSectionHitPcNuitAnnuelNRegex02(
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
				.getMessageSectionHitPcNuitAnnuelNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPcNuitAnnuelN();
		
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
		
	} // Fin de validerRGSectionHitPcNuitAnnuelNRegex02(...).______________

	
	
	/**
	 * valide la RG NUMERIQUE pour 
	 * l'attribut <b>pcNuitAnnuelN</b>.<br/>
	 * <ul>
	 * <li>retire les éventuels zéros à gauche de l'attribut à contrôler.</li>
	 * <li>retire les éventuels espaces à gauche de l'attribut à contrôler.</li>
	 * <li>retourne false si la valeur à contrôler épurée
	 * ne peut être parsée en Integer.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>pcNuitAnnuelN</code>.<br/>
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
	private boolean validerRGSectionHitPcNuitAnnuelNNumerique03(
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
				.getMessageSectionHitPcNuitAnnuelNNumerique03();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getPcNuitAnnuelN();
		
		/* retire les éventuels zéros à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpureeZeros 
			= this.retirerZerosAGauche(valeurAControler);
		
		/* retire les éventuels espaces à gauche de l'attribut à contrôler. */
		final String valeurAControlerEpuree 
			= this.retirerEspacesAGauche(valeurAControlerEpureeZeros);

		try {
			Integer.valueOf(valeurAControlerEpuree);
		} catch (Exception e) {
						
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			return false;

		}
		
		return true;
		
	} // Fin de validerRGSectionHitPcNuitAnnuelNNumerique03(...).__________
	
	
	
	/* 43 - indiceFiabiliteMjaN. **************/	
	/**
	 * applique les REGLES DE GESTION 
	 * sur l'attribut <code><b>indiceFiabiliteMjaN</b></code>.<br/>
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
	private boolean validerIndiceFiabiliteMjaN(
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
		final Boolean interrupteurIndiceFiabiliteMjaNRenseigne01 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01();
		
		final Boolean interrupteurIndiceFiabiliteMjaNRegex02 
			= SectionHitGestionnairePreferencesRG
				.getValiderRGSectionHitIndiceFiabiliteMjaNRegex02();

		boolean ok = false;
		
		boolean renseigne = false;
		boolean rg2 = false;
		
		/* applique le contrôle si interrupteur général 
		 * + interrupteur de chaque RG sont à true. */
		if (interrupteurIndiceFiabiliteMjaNRenseigne01) {
			renseigne = this.validerRGSectionHitIndiceFiabiliteMjaNRenseigne01(
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
			if (interrupteurIndiceFiabiliteMjaNRegex02) {
				rg2 = this.validerRGSectionHitIndiceFiabiliteMjaNRegex02(
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
				
	} // Fin de validerIndiceFiabiliteMjaN(...).___________________________
	
	
	
	/**
	 * valide la RG RENSEIGNE 
	 * pour l'attribut <code><b>indiceFiabiliteMjaN</b></code>.<br/>
	 * 
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceFiabiliteMjaN</code>.<br/>
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
	private boolean validerRGSectionHitIndiceFiabiliteMjaNRenseigne01(
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
				.getMessageSectionHitIndiceFiabiliteMjaNRenseigne01();
		
		// CONTROLE ***************
		if (pDto.getIndiceFiabiliteMjaN() == null) {
			
			/* crée si nécessaire une entrée dans errorsMapDetaille. */
			this.creerEntreeDansErrorsMapDetaille(pErreursMaps, pAttribut);
			
			/* ajout d'un message dans la liste. */
			pErreursMaps.ajouterMessageAAttributDansErrorsMapDetaille(
					pAttribut, message);
			
			/* retourne false si la RG n'est pas validée. */
			return false;
		}
		
		return true;		

	} // Fin de validerRGSectionHitIndiceFiabiliteMjaNRenseigne01(...).____

	
	
	/**
	 * valide la RG REGEX pour 
	 * l'attribut <code><b>indiceFiabiliteMjaN</b></code>.<br/>
	 * <ul>
	 * <li>utilise la regex [a-zA-Z0-9\\s]{1} qui signifie 
	 * 'exactement 1 caractère de mot ou 1 espace'.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut sur lequel s'applique la Règle de Gestion (RG) 
	 * comme <code>indiceFiabiliteMjaN</code>.<br/>
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
	private boolean validerRGSectionHitIndiceFiabiliteMjaNRegex02(
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
				.getMessageSectionHitIndiceFiabiliteMjaNRegex02();
		
		// CONTROLE ***************
		final String valeurAControler = pDto.getIndiceFiabiliteMjaN();
		
		final String motif = "[a-zA-Z0-9\\s]{1}";
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
		
	} // Fin de validerRGSectionHitIndiceFiabiliteMjaNRegex02(...).________
	
	

} // FIN DE LA CLASSE SectionHitValideurService.-----------------------------
