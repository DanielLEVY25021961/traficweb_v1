package levy.daniel.application.model.persistence.metier.sections.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.persistence.metier.sections.SectionHitConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.sections.entities.jpa.SectionHitEntityJPA;
import levy.daniel.application.model.persistence.metier.sections.localisations.ILocalisationHitDAO;

/**
 * CLASSE SectionHitDAOJPASpring :<br/>
 * DAO (Data Access Object) JPA avec SPRING <i>CONCRET</i> 
 * pour les {@link SectionHit}.<br/>
 *  
 * <p>
 * <span style="text-decoration: underline;">CONCEPT 
 * CONCERNE PAR CE DAO</span>
 * </p>
 * 
 * <p>
 * <b>{@link ISectionHit}</b> modélise un <i>concept</i> 
 * de <b>Localisation</b> pour les SECTIONS DE TRAFIC HIT.
 * </p>
 * 
 * <p>
 * <span style="text-decoration: underline;">DESCRIPTION DE 
 * DAO</span>
 * </p>
 * <ul>
 * <li>DAO <b>CONCRET</b> pour les <b>{@link ISectionHit}</b>.</li>
 * <li>
 * Implémente l'interface <b>ISectionHitDAO</b>.
 * </li>
 * <li>
 * DAO pour serializer des ENTITIES JPA {@link SectionHitEntityJPA} 
 * lors de l'utilisation de Java Persistence API (JPA)
 * pour la persistence dans un contexte SPRING.
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">IMPLEMENTATION DES DAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../../../../javadoc/images/implementation_SectionHit_DAO_JpaSpring.png" 
 * alt="implémentation des DAOs SectionHit JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">UTILISATION DES DAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../javadoc/images/utilisation_SectionHit_DAO_JpaSpring.png" 
 * alt="utilisation des DAOs SectionHit JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
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
 * @since 9 juil. 2019
 *
 */
@Repository(value="SectionHitDAOJPASpring")
public class SectionHitDAOJPASpring implements ISectionHitDAO {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe SectionHitDAOJPASpring".<br/>
	 */
	public static final String CLASSE_SECTIONHITDAO_JPA_SPRING 
		= "Classe SectionHitDAOJPASpring";
	
	/**
	 * " - ".
	 */
	public static final String SEPARATEUR_MOINS_AERE 
		= " - ";
	
	/**
	 * saut de ligne de la plateforme.<br/>
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");
	
	/**
	 * "this.entityManager est NULL dans le présent DAO".<br/>
	 */
	public static final String MESSAGE_ENTITYMANAGER_NULL 
	= "this.entityManager est NULL dans le présent DAO";

	
	/**
	 * "select sectionHit from 
	 * SectionHitEntityJPA as sectionHit ".<br/>
	 */
	public static final String SELECT_OBJET 
		= "select sectionHit from "
				+ "SectionHitEntityJPA as sectionHit ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null".
	 */
	public static final String OBJET_NULL 
		= "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - DOUBLON : 
	 * l'objet existe déjà dans le stockage : ".
	 */
	public static final String DOUBLON 
		= "STOCKAGE IMPOSSIBLE - DOUBLON : l'objet existe déjà dans le stockage : ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (numRoute, categorieAdminRoute, numDepartement, prOrigine, absOrigine, prExtremite, absExtremite, sens, anneeTraitement) ne sont pas remplis : ".
	 */
	public static final String CHAMPS_OBLIGATOIRES 
		= "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (numRoute, categorieAdminRoute, numDepartement, prOrigine, absOrigine, prExtremite, absExtremite, sens, anneeTraitement) ne sont pas remplis : ";
	
	/**
	 * JPA EntityManager <b>injecté par SPRING</b>.<br/>
	 */
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private transient EntityManager entityManager;
	
	/**
	 * Gestionnaire pour les Exceptions de DAO.<br/>
	 */
	private final transient GestionnaireDaoException gestionnaireException 
		= new GestionnaireDaoException();

	/**
	 * Liste des messages d'erreur à l'intention de l'utilisateur.<br/>
	 * Ne peut jamis être null. <b>tester avec isEmpty()</b>.<br/>
	 */
	private transient List<String> messagesErrorUtilisateurList 
		= new ArrayList<String>(); 

	/**
	 * DAO ILocalisationHitDAO.<br/>
	 * injecté par Spring via son Setter.
	 */
	private transient ILocalisationHitDAO localisationHitDAO;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitDAOJPASpring.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitDAOJPASpring() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * retourne un boolean qui stipule si les champs obligatoires 
	 * de pObject sont bien remplis.<br/>
	 * <br/>
	 * - retourne true si les champs obligatoires sont tous remplis.<br/>
	 * - retourne false si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : ISectionHit : Objet Métier.
	 * 
	 * @return : boolean : 
	 * true si les champs obligatoires sont tous remplis.<br/>
	 */
	private boolean champsObligatoiresRemplis(
				final ISectionHit pObject) {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getNumRoute())) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getCategorieAdminRoute())) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getNumDepartement())) {
			return false;
		}
		
		if (pObject.getPrOrigine() == null) {
			return false;
		}
		
		if (pObject.getAbsOrigine() == null) {
			return false;
		}
		
		if (pObject.getPrExtremite() == null) {
			return false;
		}
		
		if (pObject.getAbsExtremite() == null) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getSens())) {
			return false;
		}
		
		if (pObject.getAnneeTraitement() == null) {
			return false;
		}
		
		return true;
		
	} // Fin de champsObligatoiresRemplis(...).____________________________


	
	/**
	 * retourne la requete JPQL paramétrée pour la méthode 
	 * <code><b>retrieve(OBJET METIER pObject)</b></code> 
	 * en tenant compte de la getion des paramètres null.<br/>
	 * <br/>
	 *
	 * @param pObject : ISectionHit : OBJET METIER
	 * 
	 * @return javax.persistence.Query
	 * 
	 * @throws Exception
	 */
	private Query fournirRequeteEgaliteMetier(
			final ISectionHit pObject) throws Exception {
		
		/* REQUETE HQL PARMETREE. */
		String requeteString = null;
		
		Query requete = null;

		requeteString
		= SELECT_OBJET
		+ "where (sectionHit.localisation.id = :pLocalisationId) "
		+ "and ((sectionHit.numSection IS NULL and :pNumSection IS NULL) OR (sectionHit.numSection = :pNumSection)) "
		+ "and ((sectionHit.sens IS NULL and :pSens IS NULL) OR (sectionHit.sens = :pSens)) "
		+ "and ((sectionHit.nature IS NULL and :pNature IS NULL) OR (sectionHit.nature = :pNature)) "
		+ "and ((sectionHit.classe IS NULL and :pClasse IS NULL) OR (sectionHit.classe = :pClasse)) "
		+ "and ((sectionHit.anneeTraitement IS NULL and CAST(:pAnneeTraitement AS date) IS NULL) OR (sectionHit.anneeTraitement = :pAnneeTraitement)) "
		+ "and ((sectionHit.zoneLibre1 IS NULL and :pZoneLibre1 IS NULL) OR (sectionHit.zoneLibre1 = :pZoneLibre1)) "
		+ "and ((sectionHit.typeComptage IS NULL and :pTypeComptage IS NULL) OR (sectionHit.typeComptage = :pTypeComptage)) "
		+ "and ((sectionHit.classementRoute IS NULL and :pClassementRoute IS NULL) OR (sectionHit.classementRoute = :pClassementRoute)) "
		+ "and ((sectionHit.classeLargeurChausseeU IS NULL and :pClasseLargeurChausseeU IS NULL) OR (sectionHit.classeLargeurChausseeU = :pClasseLargeurChausseeU)) "
		+ "and ((sectionHit.classeLargeurChausseesS IS NULL and :pClasseLargeurChausseesS IS NULL) OR (sectionHit.classeLargeurChausseesS = :pClasseLargeurChausseesS)) "
		+ "and ((sectionHit.typeReseau IS NULL and :pTypeReseau IS NULL) OR (sectionHit.typeReseau = :pTypeReseau)) "
		+ "and ((sectionHit.pRoupK IS NULL and :pPRoupK IS NULL) OR (sectionHit.pRoupK = :pPRoupK)) "
		+ "and ((sectionHit.longueurSection IS NULL and :pLongueurSection IS NULL) OR (sectionHit.longueurSection = :pLongueurSection)) "
		+ "and ((sectionHit.longueurRaseCampagne IS NULL and :pLongueurRaseCampagne IS NULL) OR (sectionHit.longueurRaseCampagne = :pLongueurRaseCampagne)) "
		+ "and ((sectionHit.numDepartementRattachement IS NULL and :pNumDepartementRattachement IS NULL) OR (sectionHit.numDepartementRattachement = :pNumDepartementRattachement)) "
		+ "and ((sectionHit.numSectionRattachement IS NULL and :pNumSectionRattachement IS NULL) OR (sectionHit.numSectionRattachement = :pNumSectionRattachement)) "
		+ "and ((sectionHit.sensRattachement IS NULL and :pSensRattachement IS NULL) OR (sectionHit.sensRattachement = :pSensRattachement)) "
		+ "and ((sectionHit.numDepartementLimitrophe IS NULL and :pNumDepartementLimitrophe IS NULL) OR (sectionHit.numDepartementLimitrophe = :pNumDepartementLimitrophe)) "
		+ "and ((sectionHit.numSectionLimitrophe IS NULL and :pNumSectionLimitrophe IS NULL) OR (sectionHit.numSectionLimitrophe = :pNumSectionLimitrophe)) "
		+ "and ((sectionHit.sensLimitrophe IS NULL and :pSensLimitrophe IS NULL) OR (sectionHit.sensLimitrophe = :pSensLimitrophe)) "
		+ "and ((sectionHit.moisSectionnement IS NULL and :pMoisSectionnement IS NULL) OR (sectionHit.moisSectionnement = :pMoisSectionnement)) "
		+ "and ((sectionHit.anneeSectionnement IS NULL and :pAnneeSectionnement IS NULL) OR (sectionHit.anneeSectionnement = :pAnneeSectionnement)) "
		+ "and ((sectionHit.zoneLibre2 IS NULL and :pZoneLibre2 IS NULL) OR (sectionHit.zoneLibre2 = :pZoneLibre2)) "
		+ "and ((sectionHit.mjaN IS NULL and :pMjaN IS NULL) OR (sectionHit.mjaN = :pMjaN)) "
		+ "and ((sectionHit.modeCalculN IS NULL and :pModeCalculN IS NULL) OR (sectionHit.modeCalculN = :pModeCalculN)) "
		+ "and ((sectionHit.pcPLN IS NULL and :pPcPLN IS NULL) OR (sectionHit.pcPLN = :pPcPLN)) "
		+ "and ((sectionHit.evaluationPLN IS NULL and :pEvaluationPLN IS NULL) OR (sectionHit.evaluationPLN = :pEvaluationPLN)) "
		+ "and ((sectionHit.pcNuitAnnuelN IS NULL and :pPcNuitAnnuelN IS NULL) OR (sectionHit.pcNuitAnnuelN = :pPcNuitAnnuelN)) "
		+ "and ((sectionHit.indiceFiabiliteMjaN IS NULL and :pIndiceFiabiliteMjaN IS NULL) OR (sectionHit.indiceFiabiliteMjaN = :pIndiceFiabiliteMjaN)) "
		+ "and ((sectionHit.mjmNmois01 IS NULL and :pMjmNmois01 IS NULL) OR (sectionHit.mjmNmois01 = :pMjmNmois01)) "
		+ "and ((sectionHit.pcNuitNmois01 IS NULL and :pPcNuitNmois01 IS NULL) OR (sectionHit.pcNuitNmois01 = :pPcNuitNmois01)) "
		+ "and ((sectionHit.mjmNmois02 IS NULL and :pMjmNmois02 IS NULL) OR (sectionHit.mjmNmois02 = :pMjmNmois02)) "
		+ "and ((sectionHit.pcNuitNmois02 IS NULL and :pPcNuitNmois02 IS NULL) OR (sectionHit.pcNuitNmois02 = :pPcNuitNmois02)) "
		+ "and ((sectionHit.mjmNmois03 IS NULL and :pMjmNmois03 IS NULL) OR (sectionHit.mjmNmois03 = :pMjmNmois03)) "
		+ "and ((sectionHit.pcNuitNmois03 IS NULL and :pPcNuitNmois03 IS NULL) OR (sectionHit.pcNuitNmois03 = :pPcNuitNmois03)) "
		+ "and ((sectionHit.mjmNmois04 IS NULL and :pMjmNmois04 IS NULL) OR (sectionHit.mjmNmois04 = :pMjmNmois04)) "
		+ "and ((sectionHit.pcNuitNmois04 IS NULL and :pPcNuitNmois04 IS NULL) OR (sectionHit.pcNuitNmois04 = :pPcNuitNmois04)) "
		+ "and ((sectionHit.mjmNmois05 IS NULL and :pMjmNmois05 IS NULL) OR (sectionHit.mjmNmois05 = :pMjmNmois05)) "
		+ "and ((sectionHit.pcNuitNmois05 IS NULL and :pPcNuitNmois05 IS NULL) OR (sectionHit.pcNuitNmois05 = :pPcNuitNmois05)) "
		+ "and ((sectionHit.mjmNmois06 IS NULL and :pMjmNmois06 IS NULL) OR (sectionHit.mjmNmois06 = :pMjmNmois06)) "
		+ "and ((sectionHit.pcNuitNmois06 IS NULL and :pPcNuitNmois06 IS NULL) OR (sectionHit.pcNuitNmois06 = :pPcNuitNmois06)) "
		+ "and ((sectionHit.mjmNmois07 IS NULL and :pMjmNmois07 IS NULL) OR (sectionHit.mjmNmois07 = :pMjmNmois07)) "
		+ "and ((sectionHit.pcNuitNmois07 IS NULL and :pPcNuitNmois07 IS NULL) OR (sectionHit.pcNuitNmois07 = :pPcNuitNmois07)) "
		+ "and ((sectionHit.mjmNmois08 IS NULL and :pMjmNmois08 IS NULL) OR (sectionHit.mjmNmois08 = :pMjmNmois08)) "
		+ "and ((sectionHit.pcNuitNmois08 IS NULL and :pPcNuitNmois08 IS NULL) OR (sectionHit.pcNuitNmois08 = :pPcNuitNmois08)) "
		+ "and ((sectionHit.mjmNmois09 IS NULL and :pMjmNmois09 IS NULL) OR (sectionHit.mjmNmois09 = :pMjmNmois09)) "
		+ "and ((sectionHit.pcNuitNmois09 IS NULL and :pPcNuitNmois09 IS NULL) OR (sectionHit.pcNuitNmois09 = :pPcNuitNmois09)) "
		+ "and ((sectionHit.mjmNmois10 IS NULL and :pMjmNmois10 IS NULL) OR (sectionHit.mjmNmois10 = :pMjmNmois10)) "
		+ "and ((sectionHit.pcNuitNmois10 IS NULL and :pPcNuitNmois10 IS NULL) OR (sectionHit.pcNuitNmois10 = :pPcNuitNmois10)) "
		+ "and ((sectionHit.mjmNmois11 IS NULL and :pMjmNmois11 IS NULL) OR (sectionHit.mjmNmois11 = :pMjmNmois11)) "
		+ "and ((sectionHit.pcNuitNmois11 IS NULL and :pPcNuitNmois11 IS NULL) OR (sectionHit.pcNuitNmois11 = :pPcNuitNmois11)) "
		+ "and ((sectionHit.mjmNmois12 IS NULL and :pMjmNmois12 IS NULL) OR (sectionHit.mjmNmois12 = :pMjmNmois12)) "
		+ "and ((sectionHit.pcNuitNmois12 IS NULL and :pPcNuitNmois12 IS NULL) OR (sectionHit.pcNuitNmois12 = :pPcNuitNmois12)) "
		+ "and ((sectionHit.zoneLibre3 IS NULL and :pZoneLibre3 IS NULL) OR (sectionHit.zoneLibre3 = :pZoneLibre3)) "
		+ "and ((sectionHit.anneeNmoins1 IS NULL and CAST(:pAnneeNmoins1 AS date) IS NULL) OR (sectionHit.anneeNmoins1 = :pAnneeNmoins1)) "
		+ "and ((sectionHit.mjaNmoins1 IS NULL and :pMjaNmoins1 IS NULL) OR (sectionHit.mjaNmoins1 = :pMjaNmoins1)) "
		+ "and ((sectionHit.typeComptageNmoins1 IS NULL and :pTypeComptageNmoins1 IS NULL) OR (sectionHit.typeComptageNmoins1 = :pTypeComptageNmoins1)) "
		+ "and ((sectionHit.modeCalculNmoins1 IS NULL and :pModeCalculNmoins1 IS NULL) OR (sectionHit.modeCalculNmoins1 = :pModeCalculNmoins1)) "
		+ "and ((sectionHit.pcPLNmoins1 IS NULL and :pPcPLNmoins1 IS NULL) OR (sectionHit.pcPLNmoins1 = :pPcPLNmoins1)) "
		+ "and ((sectionHit.evaluationPLNmoins1 IS NULL and :pEvaluationPLNmoins1 IS NULL) OR (sectionHit.evaluationPLNmoins1 = :pEvaluationPLNmoins1)) "
		+ "and ((sectionHit.pcNuitAnnuelNmoins1 IS NULL and :pPcNuitAnnuelNmoins1 IS NULL) OR (sectionHit.pcNuitAnnuelNmoins1 = :pPcNuitAnnuelNmoins1)) "
		+ "and ((sectionHit.indiceFiabiliteMjaNmoins1 IS NULL and :pIndiceFiabiliteMjaNmoins1 IS NULL) OR (sectionHit.indiceFiabiliteMjaNmoins1 = :pIndiceFiabiliteMjaNmoins1)) "
		+ "and ((sectionHit.anneeNmoins2 IS NULL and CAST(:pAnneeNmoins2 AS date) IS NULL) OR (sectionHit.anneeNmoins2 = :pAnneeNmoins2)) "
		+ "and ((sectionHit.mjaNmoins2 IS NULL and :pMjaNmoins2 IS NULL) OR (sectionHit.mjaNmoins2 = :pMjaNmoins2)) "
		+ "and ((sectionHit.typeComptageNmoins2 IS NULL and :pTypeComptageNmoins2 IS NULL) OR (sectionHit.typeComptageNmoins2 = :pTypeComptageNmoins2)) "
		+ "and ((sectionHit.modeCalculNmoins2 IS NULL and :pModeCalculNmoins2 IS NULL) OR (sectionHit.modeCalculNmoins2 = :pModeCalculNmoins2)) "
		+ "and ((sectionHit.pcPLNmoins2 IS NULL and :pPcPLNmoins2 IS NULL) OR (sectionHit.pcPLNmoins2 = :pPcPLNmoins2)) "
		+ "and ((sectionHit.evaluationPLNmoins2 IS NULL and :pEvaluationPLNmoins2 IS NULL) OR (sectionHit.evaluationPLNmoins2 = :pEvaluationPLNmoins2)) "
		+ "and ((sectionHit.pcNuitAnnuelNmoins2 IS NULL and :pPcNuitAnnuelNmoins2 IS NULL) OR (sectionHit.pcNuitAnnuelNmoins2 = :pPcNuitAnnuelNmoins2)) "
		+ "and ((sectionHit.indiceFiabiliteMjaNmoins2 IS NULL and :pIndiceFiabiliteMjaNmoins2 IS NULL) OR (sectionHit.indiceFiabiliteMjaNmoins2 = :pIndiceFiabiliteMjaNmoins2)) "
		+ "and ((sectionHit.anneeNmoins3 IS NULL and CAST(:pAnneeNmoins3 AS date) IS NULL) OR (sectionHit.anneeNmoins3 = :pAnneeNmoins3)) "
		+ "and ((sectionHit.mjaNmoins3 IS NULL and :pMjaNmoins3 IS NULL) OR (sectionHit.mjaNmoins3 = :pMjaNmoins3)) "
		+ "and ((sectionHit.typeComptageNmoins3 IS NULL and :pTypeComptageNmoins3 IS NULL) OR (sectionHit.typeComptageNmoins3 = :pTypeComptageNmoins3)) "
		+ "and ((sectionHit.modeCalculNmoins3 IS NULL and :pModeCalculNmoins3 IS NULL) OR (sectionHit.modeCalculNmoins3 = :pModeCalculNmoins3)) "
		+ "and ((sectionHit.pcPLNmoins3 IS NULL and :pPcPLNmoins3 IS NULL) OR (sectionHit.pcPLNmoins3 = :pPcPLNmoins3)) "
		+ "and ((sectionHit.evaluationPLNmoins3 IS NULL and :pEvaluationPLNmoins3 IS NULL) OR (sectionHit.evaluationPLNmoins3 = :pEvaluationPLNmoins3)) "
		+ "and ((sectionHit.pcNuitAnnuelNmoins3 IS NULL and :pPcNuitAnnuelNmoins3 IS NULL) OR (sectionHit.pcNuitAnnuelNmoins3 = :pPcNuitAnnuelNmoins3)) "
		+ "and ((sectionHit.indiceFiabiliteMjaNmoins3 IS NULL and :pIndiceFiabiliteMjaNmoins3 IS NULL) OR (sectionHit.indiceFiabiliteMjaNmoins3 = :pIndiceFiabiliteMjaNmoins3)) "
		+ "and ((sectionHit.anneeNmoins4 IS NULL and CAST(:pAnneeNmoins4 AS date) IS NULL) OR (sectionHit.anneeNmoins4 = :pAnneeNmoins4)) "
		+ "and ((sectionHit.mjaNmoins4 IS NULL and :pMjaNmoins4 IS NULL) OR (sectionHit.mjaNmoins4 = :pMjaNmoins4)) "
		+ "and ((sectionHit.typeComptageNmoins4 IS NULL and :pTypeComptageNmoins4 IS NULL) OR (sectionHit.typeComptageNmoins4 = :pTypeComptageNmoins4)) "
		+ "and ((sectionHit.modeCalculNmoins4 IS NULL and :pModeCalculNmoins4 IS NULL) OR (sectionHit.modeCalculNmoins4 = :pModeCalculNmoins4)) "
		+ "and ((sectionHit.pcPLNmoins4 IS NULL and :pPcPLNmoins4 IS NULL) OR (sectionHit.pcPLNmoins4 = :pPcPLNmoins4)) "
		+ "and ((sectionHit.evaluationPLNmoins4 IS NULL and :pEvaluationPLNmoins4 IS NULL) OR (sectionHit.evaluationPLNmoins4 = :pEvaluationPLNmoins4)) "
		+ "and ((sectionHit.pcNuitAnnuelNmoins4 IS NULL and :pPcNuitAnnuelNmoins4 IS NULL) OR (sectionHit.pcNuitAnnuelNmoins4 = :pPcNuitAnnuelNmoins4)) "
		+ "and ((sectionHit.indiceFiabiliteMjaNmoins4 IS NULL and :pIndiceFiabiliteMjaNmoins4 IS NULL) OR (sectionHit.indiceFiabiliteMjaNmoins4 = :pIndiceFiabiliteMjaNmoins4)) "
		+ "and ((sectionHit.anneeNmoins5 IS NULL and CAST(:pAnneeNmoins5 AS date) IS NULL) OR (sectionHit.anneeNmoins5 = :pAnneeNmoins5)) "
		+ "and ((sectionHit.mjaNmoins5 IS NULL and :pMjaNmoins5 IS NULL) OR (sectionHit.mjaNmoins5 = :pMjaNmoins5)) "
		+ "and ((sectionHit.typeComptageNmoins5 IS NULL and :pTypeComptageNmoins5 IS NULL) OR (sectionHit.typeComptageNmoins5 = :pTypeComptageNmoins5)) "
		+ "and ((sectionHit.modeCalculNmoins5 IS NULL and :pModeCalculNmoins5 IS NULL) OR (sectionHit.modeCalculNmoins5 = :pModeCalculNmoins5)) "
		+ "and ((sectionHit.pcPLNmoins5 IS NULL and :pPcPLNmoins5 IS NULL) OR (sectionHit.pcPLNmoins5 = :pPcPLNmoins5)) "
		+ "and ((sectionHit.evaluationPLNmoins5 IS NULL and :pEvaluationPLNmoins5 IS NULL) OR (sectionHit.evaluationPLNmoins5 = :pEvaluationPLNmoins5)) "
		+ "and ((sectionHit.pcNuitAnnuelNmoins5 IS NULL and :pPcNuitAnnuelNmoins5 IS NULL) OR (sectionHit.pcNuitAnnuelNmoins5 = :pPcNuitAnnuelNmoins5)) "
		+ "and ((sectionHit.indiceFiabiliteMjaNmoins5 IS NULL and :pIndiceFiabiliteMjaNmoins5 IS NULL) OR (sectionHit.indiceFiabiliteMjaNmoins5 = :pIndiceFiabiliteMjaNmoins5)) "
		+ "and ((sectionHit.mjmNmoins1mois01 IS NULL and :pMjmNmoins1mois01 IS NULL) OR (sectionHit.mjmNmoins1mois01 = :pMjmNmoins1mois01)) "
		+ "and ((sectionHit.pcNuitNmoins1mois01 IS NULL and :pPcNuitNmoins1mois01 IS NULL) OR (sectionHit.pcNuitNmoins1mois01 = :pPcNuitNmoins1mois01)) "
		+ "and ((sectionHit.mjmNmoins1mois02 IS NULL and :pMjmNmoins1mois02 IS NULL) OR (sectionHit.mjmNmoins1mois02 = :pMjmNmoins1mois02)) "
		+ "and ((sectionHit.pcNuitNmoins1mois02 IS NULL and :pPcNuitNmoins1mois02 IS NULL) OR (sectionHit.pcNuitNmoins1mois02 = :pPcNuitNmoins1mois02)) "
		+ "and ((sectionHit.mjmNmoins1mois03 IS NULL and :pMjmNmoins1mois03 IS NULL) OR (sectionHit.mjmNmoins1mois03 = :pMjmNmoins1mois03)) "
		+ "and ((sectionHit.pcNuitNmoins1mois03 IS NULL and :pPcNuitNmoins1mois03 IS NULL) OR (sectionHit.pcNuitNmoins1mois03 = :pPcNuitNmoins1mois03)) "
		+ "and ((sectionHit.mjmNmoins1mois04 IS NULL and :pMjmNmoins1mois04 IS NULL) OR (sectionHit.mjmNmoins1mois04 = :pMjmNmoins1mois04)) "
		+ "and ((sectionHit.pcNuitNmoins1mois04 IS NULL and :pPcNuitNmoins1mois04 IS NULL) OR (sectionHit.pcNuitNmoins1mois04 = :pPcNuitNmoins1mois04)) "
		+ "and ((sectionHit.mjmNmoins1mois05 IS NULL and :pMjmNmoins1mois05 IS NULL) OR (sectionHit.mjmNmoins1mois05 = :pMjmNmoins1mois05)) "
		+ "and ((sectionHit.pcNuitNmoins1mois05 IS NULL and :pPcNuitNmoins1mois05 IS NULL) OR (sectionHit.pcNuitNmoins1mois05 = :pPcNuitNmoins1mois05)) "
		+ "and ((sectionHit.mjmNmoins1mois06 IS NULL and :pMjmNmoins1mois06 IS NULL) OR (sectionHit.mjmNmoins1mois06 = :pMjmNmoins1mois06)) "
		+ "and ((sectionHit.pcNuitNmoins1mois06 IS NULL and :pPcNuitNmoins1mois06 IS NULL) OR (sectionHit.pcNuitNmoins1mois06 = :pPcNuitNmoins1mois06)) "
		+ "and ((sectionHit.mjmNmoins1mois07 IS NULL and :pMjmNmoins1mois07 IS NULL) OR (sectionHit.mjmNmoins1mois07 = :pMjmNmoins1mois07)) "
		+ "and ((sectionHit.pcNuitNmoins1mois07 IS NULL and :pPcNuitNmoins1mois07 IS NULL) OR (sectionHit.pcNuitNmoins1mois07 = :pPcNuitNmoins1mois07)) "
		+ "and ((sectionHit.mjmNmoins1mois08 IS NULL and :pMjmNmoins1mois08 IS NULL) OR (sectionHit.mjmNmoins1mois08 = :pMjmNmoins1mois08)) "
		+ "and ((sectionHit.pcNuitNmoins1mois08 IS NULL and :pPcNuitNmoins1mois08 IS NULL) OR (sectionHit.pcNuitNmoins1mois08 = :pPcNuitNmoins1mois08)) "
		+ "and ((sectionHit.mjmNmoins1mois09 IS NULL and :pMjmNmoins1mois09 IS NULL) OR (sectionHit.mjmNmoins1mois09 = :pMjmNmoins1mois09)) "
		+ "and ((sectionHit.pcNuitNmoins1mois09 IS NULL and :pPcNuitNmoins1mois09 IS NULL) OR (sectionHit.pcNuitNmoins1mois09 = :pPcNuitNmoins1mois09)) "
		+ "and ((sectionHit.mjmNmoins1mois10 IS NULL and :pMjmNmoins1mois10 IS NULL) OR (sectionHit.mjmNmoins1mois10 = :pMjmNmoins1mois10)) "
		+ "and ((sectionHit.pcNuitNmoins1mois10 IS NULL and :pPcNuitNmoins1mois10 IS NULL) OR (sectionHit.pcNuitNmoins1mois10 = :pPcNuitNmoins1mois10)) "
		+ "and ((sectionHit.mjmNmoins1mois11 IS NULL and :pMjmNmoins1mois11 IS NULL) OR (sectionHit.mjmNmoins1mois11 = :pMjmNmoins1mois11)) "
		+ "and ((sectionHit.pcNuitNmoins1mois11 IS NULL and :pPcNuitNmoins1mois11 IS NULL) OR (sectionHit.pcNuitNmoins1mois11 = :pPcNuitNmoins1mois11)) "
		+ "and ((sectionHit.mjmNmoins1mois12 IS NULL and :pMjmNmoins1mois12 IS NULL) OR (sectionHit.mjmNmoins1mois12 = :pMjmNmoins1mois12)) "
		+ "and ((sectionHit.pcNuitNmoins1mois12 IS NULL and :pPcNuitNmoins1mois12 IS NULL) OR (sectionHit.pcNuitNmoins1mois12 = :pPcNuitNmoins1mois12)) "
		+ "and ((sectionHit.zoneLibre4 IS NULL and :pZoneLibre4 IS NULL) OR (sectionHit.zoneLibre4 = :pZoneLibre4))";


		/* Construction de la requête HQL. */
		requete
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête JPQL. */
		requete.setParameter("pLocalisationId", pObject.getLocalisation().getId());
		requete.setParameter("pNumSection", pObject.getNumSection());
		requete.setParameter("pSens", pObject.getSens());
		requete.setParameter("pNature", pObject.getNature());
		requete.setParameter("pClasse", pObject.getClasse());
		requete.setParameter("pAnneeTraitement", pObject.getAnneeTraitement());
		requete.setParameter("pZoneLibre1", pObject.getZoneLibre1());
		requete.setParameter("pTypeComptage", pObject.getTypeComptage());
		requete.setParameter("pClassementRoute", pObject.getClassementRoute());
		requete.setParameter("pClasseLargeurChausseeU", pObject.getClasseLargeurChausseeU());
		requete.setParameter("pClasseLargeurChausseesS", pObject.getClasseLargeurChausseesS());
		requete.setParameter("pTypeReseau", pObject.getTypeReseau());
		requete.setParameter("pPRoupK", pObject.getpRoupK());
		requete.setParameter("pLongueurSection", pObject.getLongueurSection());
		requete.setParameter("pLongueurRaseCampagne", pObject.getLongueurRaseCampagne());
		requete.setParameter("pNumDepartementRattachement", pObject.getNumDepartementRattachement());
		requete.setParameter("pNumSectionRattachement", pObject.getNumSectionRattachement());
		requete.setParameter("pSensRattachement", pObject.getSensRattachement());
		requete.setParameter("pNumDepartementLimitrophe", pObject.getNumDepartementLimitrophe());
		requete.setParameter("pNumSectionLimitrophe", pObject.getNumSectionLimitrophe());
		requete.setParameter("pSensLimitrophe", pObject.getSensLimitrophe());
		requete.setParameter("pMoisSectionnement", pObject.getMoisSectionnement());
		requete.setParameter("pAnneeSectionnement", pObject.getAnneeSectionnement());
		requete.setParameter("pZoneLibre2", pObject.getZoneLibre2());
		requete.setParameter("pMjaN", pObject.getMjaN());
		requete.setParameter("pModeCalculN", pObject.getModeCalculN());
		requete.setParameter("pPcPLN", pObject.getPcPLN());
		requete.setParameter("pEvaluationPLN", pObject.getEvaluationPLN());
		requete.setParameter("pPcNuitAnnuelN", pObject.getPcNuitAnnuelN());
		requete.setParameter("pIndiceFiabiliteMjaN", pObject.getIndiceFiabiliteMjaN());
		requete.setParameter("pMjmNmois01", pObject.getMjmNmois01());
		requete.setParameter("pPcNuitNmois01", pObject.getPcNuitNmois01());
		requete.setParameter("pMjmNmois02", pObject.getMjmNmois02());
		requete.setParameter("pPcNuitNmois02", pObject.getPcNuitNmois02());
		requete.setParameter("pMjmNmois03", pObject.getMjmNmois03());
		requete.setParameter("pPcNuitNmois03", pObject.getPcNuitNmois03());
		requete.setParameter("pMjmNmois04", pObject.getMjmNmois04());
		requete.setParameter("pPcNuitNmois04", pObject.getPcNuitNmois04());
		requete.setParameter("pMjmNmois05", pObject.getMjmNmois05());
		requete.setParameter("pPcNuitNmois05", pObject.getPcNuitNmois05());
		requete.setParameter("pMjmNmois06", pObject.getMjmNmois06());
		requete.setParameter("pPcNuitNmois06", pObject.getPcNuitNmois06());
		requete.setParameter("pMjmNmois07", pObject.getMjmNmois07());
		requete.setParameter("pPcNuitNmois07", pObject.getPcNuitNmois07());
		requete.setParameter("pMjmNmois08", pObject.getMjmNmois08());
		requete.setParameter("pPcNuitNmois08", pObject.getPcNuitNmois08());
		requete.setParameter("pMjmNmois09", pObject.getMjmNmois09());
		requete.setParameter("pPcNuitNmois09", pObject.getPcNuitNmois09());
		requete.setParameter("pMjmNmois10", pObject.getMjmNmois10());
		requete.setParameter("pPcNuitNmois10", pObject.getPcNuitNmois10());
		requete.setParameter("pMjmNmois11", pObject.getMjmNmois11());
		requete.setParameter("pPcNuitNmois11", pObject.getPcNuitNmois11());
		requete.setParameter("pMjmNmois12", pObject.getMjmNmois12());
		requete.setParameter("pPcNuitNmois12", pObject.getPcNuitNmois12());
		requete.setParameter("pZoneLibre3", pObject.getZoneLibre3());
		requete.setParameter("pAnneeNmoins1", pObject.getAnneeNmoins1());
		requete.setParameter("pMjaNmoins1", pObject.getMjaNmoins1());
		requete.setParameter("pTypeComptageNmoins1", pObject.getTypeComptageNmoins1());
		requete.setParameter("pModeCalculNmoins1", pObject.getModeCalculNmoins1());
		requete.setParameter("pPcPLNmoins1", pObject.getPcPLNmoins1());
		requete.setParameter("pEvaluationPLNmoins1", pObject.getEvaluationPLNmoins1());
		requete.setParameter("pPcNuitAnnuelNmoins1", pObject.getPcNuitAnnuelNmoins1());
		requete.setParameter("pIndiceFiabiliteMjaNmoins1", pObject.getIndiceFiabiliteMjaNmoins1());
		requete.setParameter("pAnneeNmoins2", pObject.getAnneeNmoins2());
		requete.setParameter("pMjaNmoins2", pObject.getMjaNmoins2());
		requete.setParameter("pTypeComptageNmoins2", pObject.getTypeComptageNmoins2());
		requete.setParameter("pModeCalculNmoins2", pObject.getModeCalculNmoins2());
		requete.setParameter("pPcPLNmoins2", pObject.getPcPLNmoins2());
		requete.setParameter("pEvaluationPLNmoins2", pObject.getEvaluationPLNmoins2());
		requete.setParameter("pPcNuitAnnuelNmoins2", pObject.getPcNuitAnnuelNmoins2());
		requete.setParameter("pIndiceFiabiliteMjaNmoins2", pObject.getIndiceFiabiliteMjaNmoins2());
		requete.setParameter("pAnneeNmoins3", pObject.getAnneeNmoins3());
		requete.setParameter("pMjaNmoins3", pObject.getMjaNmoins3());
		requete.setParameter("pTypeComptageNmoins3", pObject.getTypeComptageNmoins3());
		requete.setParameter("pModeCalculNmoins3", pObject.getModeCalculNmoins3());
		requete.setParameter("pPcPLNmoins3", pObject.getPcPLNmoins3());
		requete.setParameter("pEvaluationPLNmoins3", pObject.getEvaluationPLNmoins3());
		requete.setParameter("pPcNuitAnnuelNmoins3", pObject.getPcNuitAnnuelNmoins3());
		requete.setParameter("pIndiceFiabiliteMjaNmoins3", pObject.getIndiceFiabiliteMjaNmoins3());
		requete.setParameter("pAnneeNmoins4", pObject.getAnneeNmoins4());
		requete.setParameter("pMjaNmoins4", pObject.getMjaNmoins4());
		requete.setParameter("pTypeComptageNmoins4", pObject.getTypeComptageNmoins4());
		requete.setParameter("pModeCalculNmoins4", pObject.getModeCalculNmoins4());
		requete.setParameter("pPcPLNmoins4", pObject.getPcPLNmoins4());
		requete.setParameter("pEvaluationPLNmoins4", pObject.getEvaluationPLNmoins4());
		requete.setParameter("pPcNuitAnnuelNmoins4", pObject.getPcNuitAnnuelNmoins4());
		requete.setParameter("pIndiceFiabiliteMjaNmoins4", pObject.getIndiceFiabiliteMjaNmoins4());
		requete.setParameter("pAnneeNmoins5", pObject.getAnneeNmoins5());
		requete.setParameter("pMjaNmoins5", pObject.getMjaNmoins5());
		requete.setParameter("pTypeComptageNmoins5", pObject.getTypeComptageNmoins5());
		requete.setParameter("pModeCalculNmoins5", pObject.getModeCalculNmoins5());
		requete.setParameter("pPcPLNmoins5", pObject.getPcPLNmoins5());
		requete.setParameter("pEvaluationPLNmoins5", pObject.getEvaluationPLNmoins5());
		requete.setParameter("pPcNuitAnnuelNmoins5", pObject.getPcNuitAnnuelNmoins5());
		requete.setParameter("pIndiceFiabiliteMjaNmoins5", pObject.getIndiceFiabiliteMjaNmoins5());
		requete.setParameter("pMjmNmoins1mois01", pObject.getMjmNmoins1mois01());
		requete.setParameter("pPcNuitNmoins1mois01", pObject.getPcNuitNmoins1mois01());
		requete.setParameter("pMjmNmoins1mois02", pObject.getMjmNmoins1mois02());
		requete.setParameter("pPcNuitNmoins1mois02", pObject.getPcNuitNmoins1mois02());
		requete.setParameter("pMjmNmoins1mois03", pObject.getMjmNmoins1mois03());
		requete.setParameter("pPcNuitNmoins1mois03", pObject.getPcNuitNmoins1mois03());
		requete.setParameter("pMjmNmoins1mois04", pObject.getMjmNmoins1mois04());
		requete.setParameter("pPcNuitNmoins1mois04", pObject.getPcNuitNmoins1mois04());
		requete.setParameter("pMjmNmoins1mois05", pObject.getMjmNmoins1mois05());
		requete.setParameter("pPcNuitNmoins1mois05", pObject.getPcNuitNmoins1mois05());
		requete.setParameter("pMjmNmoins1mois06", pObject.getMjmNmoins1mois06());
		requete.setParameter("pPcNuitNmoins1mois06", pObject.getPcNuitNmoins1mois06());
		requete.setParameter("pMjmNmoins1mois07", pObject.getMjmNmoins1mois07());
		requete.setParameter("pPcNuitNmoins1mois07", pObject.getPcNuitNmoins1mois07());
		requete.setParameter("pMjmNmoins1mois08", pObject.getMjmNmoins1mois08());
		requete.setParameter("pPcNuitNmoins1mois08", pObject.getPcNuitNmoins1mois08());
		requete.setParameter("pMjmNmoins1mois09", pObject.getMjmNmoins1mois09());
		requete.setParameter("pPcNuitNmoins1mois09", pObject.getPcNuitNmoins1mois09());
		requete.setParameter("pMjmNmoins1mois10", pObject.getMjmNmoins1mois10());
		requete.setParameter("pPcNuitNmoins1mois10", pObject.getPcNuitNmoins1mois10());
		requete.setParameter("pMjmNmoins1mois11", pObject.getMjmNmoins1mois11());
		requete.setParameter("pPcNuitNmoins1mois11", pObject.getPcNuitNmoins1mois11());
		requete.setParameter("pMjmNmoins1mois12", pObject.getMjmNmoins1mois12());
		requete.setParameter("pPcNuitNmoins1mois12", pObject.getPcNuitNmoins1mois12());
		requete.setParameter("pZoneLibre4", pObject.getZoneLibre4());

		return requete;
		
	} // Fin de fournirRequeteParametree(...)._____________________________

	
	
	/**
	 * persiste tous les composants transients de l'Entity JPA pEntity.
	 *
	 * @param pEntity : ISectionHit : ENTITYJPA.<br/>
	 * 
	 * @throws Exception 
	 */
	private void stockerComposants(
			final ISectionHit pEntity) throws Exception {
		
		/* RECUPERATION DES ENTITY COMPOSANTES TRANSIENTES 
		 * DANS LE COMPOSITE. */
		final ILocalisationHit localisationHitEntity 
			= pEntity.getLocalisation();
		
		/* CREATION DANS LE STOCKAGE OU RECUPERATION DES ENTITY 
		 * COMPOSANTES PERSISTES. */
		final ILocalisationHit localisationHitEntityPersiste 
			= this.localisationHitDAO
							.createOrRetrieve(localisationHitEntity);
		
		/* INJECTION DES ENTITY COMPOSANTES PERSISTEES 
		 * DANS LE COMPOSITE. */
		pEntity.setLocalisation(localisationHitEntityPersiste);
		
	} // Fin de stockerComposants(...).____________________________________
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public ISectionHit create(
			final ISectionHit pObject) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pObject == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			
			final String message = DOUBLON + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de DOUBLON.___________________________________
		

		ISectionHit persistentObject = null;

		try {
			
			/* conversion de l'OBJET METIER en ENTITY. */
			final SectionHitEntityJPA entity 
				= SectionHitConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pObject);
						
			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
							, "méthode create(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________


	

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public ISectionHit createOrRetrieve(
			final ISectionHit pEntity) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pEntity == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pEntity)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pEntity.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne l'objet déjà persisté si pObject est un doublon. */
		if (this.exists(pEntity)) {
			
			return SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(
							this.retrieve(pEntity));
			
		} // Fin de DOUBLON.___________________________________
		

		ISectionHit persistentObject = null;

		try {
			
			SectionHitEntityJPA entity = null;
			
			/* conversion de l'OBJET METIER en ENTITY. */
			if (!(pEntity instanceof SectionHitEntityJPA)) {
				entity 
				= SectionHitConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pEntity);
			} else {
				entity = (SectionHitEntityJPA) pEntity;
			}
									
			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* RETOURNE L'ENTITY. */
			persistentObject = entity;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
							, "méthode create(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de createOrRetrieve(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final ISectionHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}
		
		/* ne fait rien si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return;
		}
		
		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}
		
		if (this.existsId(pObject.getId())) {
			return;
		}


		/* conversion de l'OBJET METIER en ENTITY. */
		final SectionHitEntityJPA entity = 
				SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
								
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);

		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
							, "méthode persist(object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final ISectionHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}
		
		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		
		/* conversion de l'OBJET METIER en ENTITY. */
		final SectionHitEntityJPA entity = 
				SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
				
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);
		
		ISectionHit persistentObject = null;
		
		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= SectionHitConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
							, "méthode createReturnId(object)", e);

		}
		
		/* retourne l'ID de l'Objet persistant. */
		if (persistentObject != null) {
			return persistentObject.getId();
		}
		
		return null;		

	} // Fin de createReturnId(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ISectionHit> saveIterable(
			final Iterable<ISectionHit> pList) 
					throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		/* conversion de le liste de MODEL en liste d'ENTITIES. */
		final List<SectionHitEntityJPA> listeEntities 
			= SectionHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(pList);
		
		final List<ISectionHit> resultat 
			= new ArrayList<ISectionHit>();

		final Iterator<SectionHitEntityJPA> iteS 
			= listeEntities.iterator();

		try {

			while (iteS.hasNext()) {

				final SectionHitEntityJPA entity = iteS.next();

				/* passe un null dans le lot. */
				if (entity == null) {
					continue;
				}
					
					
				/* Passe les doublons existants en base. */
				if (!this.exists(entity)) {
					
					/* passe si les attributs obligatoires 
					 * de l'objet ne sont pas remplis.*/
					if (this.champsObligatoiresRemplis(entity)) {
						
						ISectionHit objectPersistant = null;

						try {
														
							// SAUVEGARDE DES COMPOSANTS *******************************
							this.stockerComposants(entity);

							/* ***************** */
							/* PERSISTE en base. */
							this.entityManager.persist(entity);

							/* conversion de l'ENTITY en OBJET METIER. */
							objectPersistant 
								= SectionHitConvertisseurMetierEntity
								.convertirEntityJPAEnObjetMetier(entity);

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isFatalEnabled()) {
								LOG.fatal(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_SECTIONHITDAO_JPA_SPRING
											, "Méthode saveIterable(lot)", e);
						}
						
						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objectPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objectPersistant);								
						}
						
					} // Entity avec attributs obligatoires remplis.
					
				} // Entity persistante._________________
				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
							, "Méthode saveIterable(lot)", e);

		}

		/* retourne l'iterable resultat. */
		return resultat;

	} // Fin de saveIterable(...)._________________________________________
	
	

	/* READ *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHit retrieve(
			final ISectionHit pObject) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		ISectionHit objetResultat = null;		
		SectionHitEntityJPA entity = null;
		
		/* RECHERCHE DES COMPOSANTS EXISTANTS. */
		final ILocalisationHit localisationPersistante 
			= this.localisationHitDAO.retrieve(pObject.getLocalisation());
		
		/* return null si le composant de pObject n'est pas persisté. */
		if (localisationPersistante == null) {
			return null;
		}
		
		/* INJECTION DU COMPOSANT PERSISTANT DANS L'ENTITE A RECHERCHER. */
		pObject.setLocalisation(localisationPersistante);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return null;
		}
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (SectionHitEntityJPA) requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode retrieve(objet)", e);
		}
		
		/* retourne l'objet metier trouvé. */
		return objetResultat;

	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHit findById(
			final Long pId) throws Exception {
		
		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		ISectionHit objetTrouve = null;
		SectionHitEntityJPA entity = null;
		
		try {
			
			/* ************************* */
			/* récupération de l'ENTITY. */
			entity 
				= this.entityManager.find(
						SectionHitEntityJPA.class, pId);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetTrouve 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode findById(ID)", e);
			
		}
		
		/* retourne l'objet metier trouvé. */
		return objetTrouve;
				
	} // Fin de findById(...)._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final ISectionHit pObject) throws Exception {
		
		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		ISectionHit objetResultat = null;		
		SectionHitEntityJPA entity = null;

		/* RECHERCHE DES COMPOSANTS EXISTANTS. */
		final ILocalisationHit localisationPersistante 
			= this.localisationHitDAO.retrieve(pObject.getLocalisation());
		
		/* return null si le composant de pObject n'est pas persisté. */
		if (localisationPersistante == null) {
			return null;
		}
		
		/* INJECTION DU COMPOSANT PERSISTANT DANS L'ENTITE A RECHERCHER. */
		pObject.setLocalisation(localisationPersistante);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return null;
		}
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (SectionHitEntityJPA) requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode retrieveId(objet)", e);
		}
		
		/* retourne l'ID de l'objet metier trouvé. */
		if (objetResultat != null) {
			return objetResultat.getId();
		}
		
		return null;
		
	} // Fin de retrieveId(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ISectionHit> rechercherRapide(
			final String pString) throws Exception {
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête JPQL sous forme de String. */
		final String requeteString 
			= SELECT_OBJET 
			+ "where sectionHit.numRoute LIKE :pattern "
					+ "OR sectionHit.numDepartement LIKE :pattern "
					+ "OR sectionHit.prOrigine LIKE :pattern "
					+ "OR sectionHit.absOrigine LIKE :pattern";
		
		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);
		
		/* Passage des paramètres de la requête HQL. */
		final String patternRecherche = "%" + pString + "%";
		
		requete.setParameter("pattern", patternRecherche);
		
		List<SectionHitEntityJPA> resultatEntity = null;
				
		List<ISectionHit> resultat = null;
		
		try {
			
			/* Execution de la requete HQL. */
			resultatEntity 
				= requete.getResultList();
			
			/* conversion de la liste ENTITY en liste OBJET METIER. */
			resultat 
				= SectionHitConvertisseurMetierEntity
					.convertirListEntitiesJPAEnModel(resultatEntity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode rechercherRapide(String %recherche%)", e);
		}
		
		/* retourne la liste d'objets métier. */
		if (resultat != null) {
			return resultat;
		}
		
		return null;
		
	} // Fin de rechercherRapide(...)._____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ISectionHit> findAll() throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from SectionHitEntityJPA";
		
		List<SectionHitEntityJPA> resultatEntity = null;
		
		List<ISectionHit> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
			
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat = SectionHitConvertisseurMetierEntity
						.convertirListEntitiesJPAEnModel(
								resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode findAll()", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ISectionHit> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {

		/* retourne null si pId est en dehors des index de stockage. */
		if (pStartPosition > this.count() - 1) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from SectionHitEntityJPA";
		
		List<SectionHitEntityJPA> resultatEntity = null;
		
		List<ISectionHit> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString)
					.setFirstResult(pStartPosition)
						.setMaxResults(pMaxResult);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
						
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat 
			= SectionHitConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode findAllMax(int pStartPosition"
								+ ", int pMaxResult)", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ISectionHit> findAllIterable(
			final Iterable<Long> pIds) throws Exception {
		
		/* retourne null si pIds == null. */
		if (pIds == null) {
			return null;
		}

		final List<ISectionHit> resultat 
			= new ArrayList<ISectionHit>();

		for (final Long id : pIds) {
			
			final ISectionHit objet = this.findById(id);
			
			if (objet != null) {
				resultat.add(objet);
			}
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllIterable(...).______________________________________



	/* UPDATE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHit update(
			final ISectionHit pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne pObject si l'objet n'est pas 
		 * déjà persistant en base. */
		if (!this.existsId(pObject.getId())) {
			return pObject;
		}
		
		/* retourne null si pObject créerait un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
				
		/* conversion de l'OBJET METIER en ENTITY. */
		final SectionHitEntityJPA entity = 
				SectionHitConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
								
		// SAUVEGARDE DES COMPOSANTS *******************************
		this.stockerComposants(entity);

		ISectionHit persistentObject = null;
		
		try {
			
			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode update(objet)", e);
						
		}
				
		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHit updateById(
			final Long pId, final ISectionHit pObjectModifie) 
												throws Exception {
		
		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* retourne null si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return null;
		}
		
		/* retourne null si pObjectModifie == null. */
		if (pObjectModifie == null) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObjectModifie ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObjectModifie)) {
			return null;
		}
		
		/* retourne null si l'objet modifie pObjectModifie 
		 * créerait un doublon dans le stockage. */
		if (this.exists(pObjectModifie)) {
			return null;
		}
		
		/* récupère en BASE l'objet à modifier existant par son index. */
		final ISectionHit objetAModifier = this.findById(pId);
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
				
		ISectionHit persistentObject = null;
		
		/* RECHERCHE DES COMPOSANTS EXISTANTS. */
		final ILocalisationHit localisationPersistante 
			= this.localisationHitDAO.retrieve(objetAModifier.getLocalisation());
		
		/* return null si le composant de pObject n'est pas persisté. */
		if (localisationPersistante == null) {
			return null;
		}
		
		/* MODIFICATION DU COMPOSANT. */
		localisationPersistante.setNumDepartement(pObjectModifie.getNumDepartement());
		localisationPersistante.setNumRoute(pObjectModifie.getNumRoute());
		localisationPersistante.setIndiceNumRoute(pObjectModifie.getIndiceNumRoute());
		localisationPersistante.setIndiceLettreRoute(pObjectModifie.getIndiceLettreRoute());
		localisationPersistante.setCategorieAdminRoute(pObjectModifie.getCategorieAdminRoute());
		localisationPersistante.setLieuDitOrigine(pObjectModifie.getLieuDitOrigine());
		localisationPersistante.setPrOrigine(pObjectModifie.getPrOrigine());
		localisationPersistante.setAbsOrigine(pObjectModifie.getAbsOrigine());
		localisationPersistante.setLieuDitExtremite(pObjectModifie.getLieuDitExtremite());
		localisationPersistante.setPrExtremite(pObjectModifie.getPrExtremite());
		localisationPersistante.setAbsExtremite(pObjectModifie.getAbsExtremite());
		localisationPersistante.setLieuDitComptage(pObjectModifie.getLieuDitComptage());
		localisationPersistante.setPrComptage(pObjectModifie.getPrComptage());
		localisationPersistante.setAbsComptage(pObjectModifie.getAbsComptage());
		
		this.localisationHitDAO.updateById(
				localisationPersistante.getId(), localisationPersistante);
		
		/* INJECTION DU COMPOSANT PERSISTANT DANS L'ENTITE A RECHERCHER. */
		objetAModifier.setLocalisation(localisationPersistante);

		
		try {

			/* applique les modifications sur le COMPOSITE. */
			objetAModifier.setNumSection(pObjectModifie.getNumSection());
			objetAModifier.setSens(pObjectModifie.getSens());
			objetAModifier.setNature(pObjectModifie.getNature());
			objetAModifier.setClasse(pObjectModifie.getClasse());
			objetAModifier.setAnneeTraitement(pObjectModifie.getAnneeTraitement());
			objetAModifier.setZoneLibre1(pObjectModifie.getZoneLibre1());
			objetAModifier.setTypeComptage(pObjectModifie.getTypeComptage());
			objetAModifier.setClassementRoute(pObjectModifie.getClassementRoute());
			objetAModifier.setClasseLargeurChausseeU(pObjectModifie.getClasseLargeurChausseeU());
			objetAModifier.setClasseLargeurChausseesS(pObjectModifie.getClasseLargeurChausseesS());
			objetAModifier.setTypeReseau(pObjectModifie.getTypeReseau());
			objetAModifier.setpRoupK(pObjectModifie.getpRoupK());
			objetAModifier.setLongueurSection(pObjectModifie.getLongueurSection());
			objetAModifier.setLongueurRaseCampagne(pObjectModifie.getLongueurRaseCampagne());
			objetAModifier.setNumDepartementRattachement(pObjectModifie.getNumDepartementRattachement());
			objetAModifier.setNumSectionRattachement(pObjectModifie.getNumSectionRattachement());
			objetAModifier.setSensRattachement(pObjectModifie.getSensRattachement());
			objetAModifier.setNumDepartementLimitrophe(pObjectModifie.getNumDepartementLimitrophe());
			objetAModifier.setNumSectionLimitrophe(pObjectModifie.getNumSectionLimitrophe());
			objetAModifier.setSensLimitrophe(pObjectModifie.getSensLimitrophe());
			objetAModifier.setMoisSectionnement(pObjectModifie.getMoisSectionnement());
			objetAModifier.setAnneeSectionnement(pObjectModifie.getAnneeSectionnement());
			objetAModifier.setZoneLibre2(pObjectModifie.getZoneLibre2());
			objetAModifier.setMjaN(pObjectModifie.getMjaN());
			objetAModifier.setModeCalculN(pObjectModifie.getModeCalculN());
			objetAModifier.setPcPLN(pObjectModifie.getPcPLN());
			objetAModifier.setEvaluationPLN(pObjectModifie.getEvaluationPLN());
			objetAModifier.setPcNuitAnnuelN(pObjectModifie.getPcNuitAnnuelN());
			objetAModifier.setIndiceFiabiliteMjaN(pObjectModifie.getIndiceFiabiliteMjaN());
			objetAModifier.setMjmNmois01(pObjectModifie.getMjmNmois01());
			objetAModifier.setPcNuitNmois01(pObjectModifie.getPcNuitNmois01());
			objetAModifier.setMjmNmois02(pObjectModifie.getMjmNmois02());
			objetAModifier.setPcNuitNmois02(pObjectModifie.getPcNuitNmois02());
			objetAModifier.setMjmNmois03(pObjectModifie.getMjmNmois03());
			objetAModifier.setPcNuitNmois03(pObjectModifie.getPcNuitNmois03());
			objetAModifier.setMjmNmois04(pObjectModifie.getMjmNmois04());
			objetAModifier.setPcNuitNmois04(pObjectModifie.getPcNuitNmois04());
			objetAModifier.setMjmNmois05(pObjectModifie.getMjmNmois05());
			objetAModifier.setPcNuitNmois05(pObjectModifie.getPcNuitNmois05());
			objetAModifier.setMjmNmois06(pObjectModifie.getMjmNmois06());
			objetAModifier.setPcNuitNmois06(pObjectModifie.getPcNuitNmois06());
			objetAModifier.setMjmNmois07(pObjectModifie.getMjmNmois07());
			objetAModifier.setPcNuitNmois07(pObjectModifie.getPcNuitNmois07());
			objetAModifier.setMjmNmois08(pObjectModifie.getMjmNmois08());
			objetAModifier.setPcNuitNmois08(pObjectModifie.getPcNuitNmois08());
			objetAModifier.setMjmNmois09(pObjectModifie.getMjmNmois09());
			objetAModifier.setPcNuitNmois09(pObjectModifie.getPcNuitNmois09());
			objetAModifier.setMjmNmois10(pObjectModifie.getMjmNmois10());
			objetAModifier.setPcNuitNmois10(pObjectModifie.getPcNuitNmois10());
			objetAModifier.setMjmNmois11(pObjectModifie.getMjmNmois11());
			objetAModifier.setPcNuitNmois11(pObjectModifie.getPcNuitNmois11());
			objetAModifier.setMjmNmois12(pObjectModifie.getMjmNmois12());
			objetAModifier.setPcNuitNmois12(pObjectModifie.getPcNuitNmois12());
			objetAModifier.setZoneLibre3(pObjectModifie.getZoneLibre3());
			objetAModifier.setAnneeNmoins1(pObjectModifie.getAnneeNmoins1());
			objetAModifier.setMjaNmoins1(pObjectModifie.getMjaNmoins1());
			objetAModifier.setTypeComptageNmoins1(pObjectModifie.getTypeComptageNmoins1());
			objetAModifier.setModeCalculNmoins1(pObjectModifie.getModeCalculNmoins1());
			objetAModifier.setPcPLNmoins1(pObjectModifie.getPcPLNmoins1());
			objetAModifier.setEvaluationPLNmoins1(pObjectModifie.getEvaluationPLNmoins1());
			objetAModifier.setPcNuitAnnuelNmoins1(pObjectModifie.getPcNuitAnnuelNmoins1());
			objetAModifier.setIndiceFiabiliteMjaNmoins1(pObjectModifie.getIndiceFiabiliteMjaNmoins1());
			objetAModifier.setAnneeNmoins2(pObjectModifie.getAnneeNmoins2());
			objetAModifier.setMjaNmoins2(pObjectModifie.getMjaNmoins2());
			objetAModifier.setTypeComptageNmoins2(pObjectModifie.getTypeComptageNmoins2());
			objetAModifier.setModeCalculNmoins2(pObjectModifie.getModeCalculNmoins2());
			objetAModifier.setPcPLNmoins2(pObjectModifie.getPcPLNmoins2());
			objetAModifier.setEvaluationPLNmoins2(pObjectModifie.getEvaluationPLNmoins2());
			objetAModifier.setPcNuitAnnuelNmoins2(pObjectModifie.getPcNuitAnnuelNmoins2());
			objetAModifier.setIndiceFiabiliteMjaNmoins2(pObjectModifie.getIndiceFiabiliteMjaNmoins2());
			objetAModifier.setAnneeNmoins3(pObjectModifie.getAnneeNmoins3());
			objetAModifier.setMjaNmoins3(pObjectModifie.getMjaNmoins3());
			objetAModifier.setTypeComptageNmoins3(pObjectModifie.getTypeComptageNmoins3());
			objetAModifier.setModeCalculNmoins3(pObjectModifie.getModeCalculNmoins3());
			objetAModifier.setPcPLNmoins3(pObjectModifie.getPcPLNmoins3());
			objetAModifier.setEvaluationPLNmoins3(pObjectModifie.getEvaluationPLNmoins3());
			objetAModifier.setPcNuitAnnuelNmoins3(pObjectModifie.getPcNuitAnnuelNmoins3());
			objetAModifier.setIndiceFiabiliteMjaNmoins3(pObjectModifie.getIndiceFiabiliteMjaNmoins3());
			objetAModifier.setAnneeNmoins4(pObjectModifie.getAnneeNmoins4());
			objetAModifier.setMjaNmoins4(pObjectModifie.getMjaNmoins4());
			objetAModifier.setTypeComptageNmoins4(pObjectModifie.getTypeComptageNmoins4());
			objetAModifier.setModeCalculNmoins4(pObjectModifie.getModeCalculNmoins4());
			objetAModifier.setPcPLNmoins4(pObjectModifie.getPcPLNmoins4());
			objetAModifier.setEvaluationPLNmoins4(pObjectModifie.getEvaluationPLNmoins4());
			objetAModifier.setPcNuitAnnuelNmoins4(pObjectModifie.getPcNuitAnnuelNmoins4());
			objetAModifier.setIndiceFiabiliteMjaNmoins4(pObjectModifie.getIndiceFiabiliteMjaNmoins4());
			objetAModifier.setAnneeNmoins5(pObjectModifie.getAnneeNmoins5());
			objetAModifier.setMjaNmoins5(pObjectModifie.getMjaNmoins5());
			objetAModifier.setTypeComptageNmoins5(pObjectModifie.getTypeComptageNmoins5());
			objetAModifier.setModeCalculNmoins5(pObjectModifie.getModeCalculNmoins5());
			objetAModifier.setPcPLNmoins5(pObjectModifie.getPcPLNmoins5());
			objetAModifier.setEvaluationPLNmoins5(pObjectModifie.getEvaluationPLNmoins5());
			objetAModifier.setPcNuitAnnuelNmoins5(pObjectModifie.getPcNuitAnnuelNmoins5());
			objetAModifier.setIndiceFiabiliteMjaNmoins5(pObjectModifie.getIndiceFiabiliteMjaNmoins5());
			objetAModifier.setMjmNmoins1mois01(pObjectModifie.getMjmNmoins1mois01());
			objetAModifier.setPcNuitNmoins1mois01(pObjectModifie.getPcNuitNmoins1mois01());
			objetAModifier.setMjmNmoins1mois02(pObjectModifie.getMjmNmoins1mois02());
			objetAModifier.setPcNuitNmoins1mois02(pObjectModifie.getPcNuitNmoins1mois02());
			objetAModifier.setMjmNmoins1mois03(pObjectModifie.getMjmNmoins1mois03());
			objetAModifier.setPcNuitNmoins1mois03(pObjectModifie.getPcNuitNmoins1mois03());
			objetAModifier.setMjmNmoins1mois04(pObjectModifie.getMjmNmoins1mois04());
			objetAModifier.setPcNuitNmoins1mois04(pObjectModifie.getPcNuitNmoins1mois04());
			objetAModifier.setMjmNmoins1mois05(pObjectModifie.getMjmNmoins1mois05());
			objetAModifier.setPcNuitNmoins1mois05(pObjectModifie.getPcNuitNmoins1mois05());
			objetAModifier.setMjmNmoins1mois06(pObjectModifie.getMjmNmoins1mois06());
			objetAModifier.setPcNuitNmoins1mois06(pObjectModifie.getPcNuitNmoins1mois06());
			objetAModifier.setMjmNmoins1mois07(pObjectModifie.getMjmNmoins1mois07());
			objetAModifier.setPcNuitNmoins1mois07(pObjectModifie.getPcNuitNmoins1mois07());
			objetAModifier.setMjmNmoins1mois08(pObjectModifie.getMjmNmoins1mois08());
			objetAModifier.setPcNuitNmoins1mois08(pObjectModifie.getPcNuitNmoins1mois08());
			objetAModifier.setMjmNmoins1mois09(pObjectModifie.getMjmNmoins1mois09());
			objetAModifier.setPcNuitNmoins1mois09(pObjectModifie.getPcNuitNmoins1mois09());
			objetAModifier.setMjmNmoins1mois10(pObjectModifie.getMjmNmoins1mois10());
			objetAModifier.setPcNuitNmoins1mois10(pObjectModifie.getPcNuitNmoins1mois10());
			objetAModifier.setMjmNmoins1mois11(pObjectModifie.getMjmNmoins1mois11());
			objetAModifier.setPcNuitNmoins1mois11(pObjectModifie.getPcNuitNmoins1mois11());
			objetAModifier.setMjmNmoins1mois12(pObjectModifie.getMjmNmoins1mois12());
			objetAModifier.setPcNuitNmoins1mois12(pObjectModifie.getPcNuitNmoins1mois12());
			objetAModifier.setZoneLibre4(pObjectModifie.getZoneLibre4());

			/* conversion de l'OBJET METIER en ENTITY. */
			final SectionHitEntityJPA entity = 
					SectionHitConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(objetAModifier);
			
			// SAUVEGARDE DES COMPOSANTS *******************************
			this.stockerComposants(entity);
			
			/* MODIFIE en base. */
			this.entityManager.merge(entity);
			
			/* applique les modifications dans le stockage (si nécessaire). */
			this.entityManager.flush();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= SectionHitConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "méthode updateById(Id, Object)", e);
						
		}
				
		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/* DELETE *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final ISectionHit pObject) throws Exception {
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* récupère l'instance persistante. */
		final ISectionHit persistanceInstance 
			= this.retrieve(pObject);
		
		/* retourne false si pObject n'est pas persisté. */
		if (persistanceInstance == null) {
			return false;
		}
				
		boolean resultat = false;
			
		try {

			/* récupération de l'ENTITY a détruire. */
			final SectionHitEntityJPA entity 
				= this.entityManager.find(
						SectionHitEntityJPA.class
							, persistanceInstance.getId());
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
			resultat = true;
							
		} catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode delete(objet)", e);
									
		}
		
		/* retourne le boolean resultat. */
		return resultat;
										
	} // Fin de delete(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pId == null. */
		if (pId == null) {
			return;
		}
		
		/* ne fait rien si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return;
		}
						
		try {
		
			/* récupération de l'ENTITY a détruire. */
			final SectionHitEntityJPA entity 
				= this.entityManager.find(
						SectionHitEntityJPA.class
							, pId);
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode deleteById(ID)", e);
		}
		
	} // Fin de deleteById(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pId == null. */
		if (pId == null) {
			return false;
		}
		
		/* retourne false si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return false;
		}
				
		try {
						
			/* récupération de l'ENTITY a détruire. */
			final SectionHitEntityJPA entity 
				= this.entityManager.find(
						SectionHitEntityJPA.class
							, pId);
			
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
						
			return true;
				
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode deleteByIdBoolean(ID)", e);
		}
		
		return false;
		
	} // Fin de deleteByIdBoolean(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from SectionHitEntityJPA";

		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode deleteAll()", e);
			
		}
		
	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from SectionHitEntityJPA";
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			resultat = true;
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode deleteAllBoolean()", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<ISectionHit> pList) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}
		
		final Iterator<ISectionHit> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final ISectionHit objet = ite.next();
				final ISectionHit objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
								
				/* récupération de l'ENTITY a détruire. */
				final SectionHitEntityJPA entity 
					= this.entityManager.find(
							SectionHitEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_SECTIONHITDAO_JPA_SPRING
					, "Méthode deleteIterable(Iterable)", e);
			
		}
				
	} // Fin de deleteIterable(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<ISectionHit> pList) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pList == null. */
		if (pList == null) {
			return false;
		}

		boolean resultat = false;
		
		final Iterator<ISectionHit> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final ISectionHit objet = ite.next();
				final ISectionHit objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
				
				/* récupération de l'ENTITY a détruire. */
				final SectionHitEntityJPA entity 
					= this.entityManager.find(
							SectionHitEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
			resultat = true;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_SECTIONHITDAO_JPA_SPRING
					, "Méthode deleteIterableBoolean(Iterable)", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
				
	} // Fin de deleteIterableBoolean(...).________________________________



	/* TOOLS *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final ISectionHit pObject) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}

		boolean resultat = false;		
		ISectionHit objetResultat = null;
		
		/* RECHERCHE DES COMPOSANTS EXISTANTS. */
		final ILocalisationHit localisationPersistante 
			= this.localisationHitDAO.retrieve(pObject.getLocalisation());
		
		/* return false si le composant de pObject n'est pas persisté. */
		if (localisationPersistante == null) {
			return false;
		}
				
		/* INJECTION DU COMPOSANT PERSISTANT DANS L'ENTITE A RECHERCHER. */
		pObject.setLocalisation(localisationPersistante);
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		if (requete == null) {
			return false;
		}
		
		try {
						
			/* Execution de la requete HQL. */
			objetResultat 
			= (ISectionHit) requete.getSingleResult();
			
			/* retourne true si l'objet existe en base. */
			if (objetResultat != null) {
				resultat = true;
			}
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne false si l'Objet métier n'existe pas en base. */
			return false;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_SECTIONHITDAO_JPA_SPRING
						, "Méthode exists(objet)", e);
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(
			final Long pId) throws Exception {
		
		/* retourne false si pId == null . */
		if (pId == null) {
			return false;
		}
		
		/* retourne true si l'objet métier existe en base. */
		if (this.findById(pId) != null) {
			return true;
		}
		
		return false;
		
	} // Fin de existsId(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		
		/* Récupère la liste d'Objets métier de Type paramétré T. */
		final List<ISectionHit> listObjects = this.findAll();
		
		if (listObjects != null) {
			
			/* Retourne la taille de la liste. */
			return Long.valueOf(listObjects.size()) ;
		}
		
		return null;
		
	} // Fin de count().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		
		final List<ISectionHit> stockageList = this.findAll();
		
		/* ne fait rien si this.findAll() retourne null. */
		if (stockageList == null) {
			return;
		}
		
		System.out.println(this.afficherListeObjetsMetier(stockageList));
		
	} // Fin de ecrireStockageDansConsole()._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<ISectionHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final ISectionHit objet : pList) {
			
			stb.append(objet.toString());
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListeObjetsMetier(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMessagesErrorUtilisateurList() {
		return this.messagesErrorUtilisateurList;
	} // Fin de getMessagesErrorUtilisateurList()._________________________


	
	/**
	 * Getter de DAO ILocalisationHitDAO.<br/>
	 * injecté par Spring.
	 *
	 * @return this.localisationHitDAO : ILocalisationHitDAO.<br/>
	 */
	public final ILocalisationHitDAO getLocalisationHitDAO() {
		return this.localisationHitDAO;
	} // Fin de getLocalisationHitDAO().___________________________________


	
	/**
	* Setter de DAO ILocalisationHitDAO.<br/>
	* injecté par Spring.
	*
	* @param pLocalisationHitDAO : ILocalisationHitDAO : 
	* valeur à passer à this.localisationHitDAO.<br/>
	*/
	@Autowired(required=true)
    @Qualifier("LocalisationHitDAOJPASpring")
	public final void setLocalisationHitDAO(
			final ILocalisationHitDAO pLocalisationHitDAO) {
		this.localisationHitDAO = pLocalisationHitDAO;
	} // Fin de setLocalisationHitDAO(...).________________________________


	
} // FIN DE LA CLASSE SectionHitDAOJPASpring.--------------------------------
