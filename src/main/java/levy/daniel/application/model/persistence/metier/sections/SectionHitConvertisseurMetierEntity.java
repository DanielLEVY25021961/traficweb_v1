package levy.daniel.application.model.persistence.metier.sections;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.persistence.metier.sections.entities.jpa.SectionHitEntityJPA;

/**
 * CLASSE SectionHitConvertisseurMetierEntity :<br/>
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
 * @since 9 juil. 2019
 *
 */
public final class SectionHitConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * "line.separator".<br/>
	 */
	public static final String LINE_SEPARATOR = "line.separator";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitConvertisseurMetierEntity.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private SectionHitConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : SectionHitEntityJPA.<br/>
	 * 
	 * @return : ISectionHit.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ISectionHit creerObjetMetierAPartirEntityJPA(
			final SectionHitEntityJPA pEntityJPA) throws Exception {

		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			final ISectionHit objet 
				= new SectionHit();

			if (pEntityJPA != null) {

				objet.setId(pEntityJPA.getId());
				objet.setNumDepartement(pEntityJPA.getNumDepartement());
				objet.setNumSection(pEntityJPA.getNumSection());
				objet.setSens(pEntityJPA.getSens());
				objet.setNature(pEntityJPA.getNature());
				objet.setClasse(pEntityJPA.getClasse());
				objet.setAnneeTraitement(pEntityJPA.getAnneeTraitement());
				objet.setZoneLibre1(pEntityJPA.getZoneLibre1());
				objet.setNumRoute(pEntityJPA.getNumRoute());
				objet.setIndiceNumRoute(pEntityJPA.getIndiceNumRoute());
				objet.setIndiceLettreRoute(pEntityJPA.getIndiceLettreRoute());
				objet.setCategorieAdminRoute(pEntityJPA.getCategorieAdminRoute());
				objet.setTypeComptage(pEntityJPA.getTypeComptage());
				objet.setClassementRoute(pEntityJPA.getClassementRoute());
				objet.setClasseLargeurChausseeU(pEntityJPA.getClasseLargeurChausseeU());
				objet.setClasseLargeurChausseesS(pEntityJPA.getClasseLargeurChausseesS());
				objet.setTypeReseau(pEntityJPA.getTypeReseau());
				objet.setpRoupK(pEntityJPA.getpRoupK());
				objet.setLieuDitOrigine(pEntityJPA.getLieuDitOrigine());
				objet.setPrOrigine(pEntityJPA.getPrOrigine());
				objet.setAbsOrigine(pEntityJPA.getAbsOrigine());
				objet.setLieuDitExtremite(pEntityJPA.getLieuDitExtremite());
				objet.setPrExtremite(pEntityJPA.getPrExtremite());
				objet.setAbsExtremite(pEntityJPA.getAbsExtremite());
				objet.setLieuDitComptage(pEntityJPA.getLieuDitComptage());
				objet.setPrComptage(pEntityJPA.getPrComptage());
				objet.setAbsComptage(pEntityJPA.getAbsComptage());
				objet.setLongueurSection(pEntityJPA.getLongueurSection());
				objet.setLongueurRaseCampagne(pEntityJPA.getLongueurRaseCampagne());
				objet.setNumDepartementRattachement(pEntityJPA.getNumDepartementRattachement());
				objet.setNumSectionRattachement(pEntityJPA.getNumSectionRattachement());
				objet.setSensRattachement(pEntityJPA.getSensRattachement());
				objet.setNumDepartementLimitrophe(pEntityJPA.getNumDepartementLimitrophe());
				objet.setNumSectionLimitrophe(pEntityJPA.getNumSectionLimitrophe());
				objet.setSensLimitrophe(pEntityJPA.getSensLimitrophe());
				objet.setMoisSectionnement(pEntityJPA.getMoisSectionnement());
				objet.setAnneeSectionnement(pEntityJPA.getAnneeSectionnement());
				objet.setZoneLibre2(pEntityJPA.getZoneLibre2());
				objet.setMjaN(pEntityJPA.getMjaN());
				objet.setModeCalculN(pEntityJPA.getModeCalculN());
				objet.setPcPLN(pEntityJPA.getPcPLN());
				objet.setEvaluationPLN(pEntityJPA.getEvaluationPLN());
				objet.setPcNuitAnnuelN(pEntityJPA.getPcNuitAnnuelN());
				objet.setIndiceFiabiliteMjaN(pEntityJPA.getIndiceFiabiliteMjaN());
				objet.setMjmNmois01(pEntityJPA.getMjmNmois01());
				objet.setPcNuitNmois01(pEntityJPA.getPcNuitNmois01());
				objet.setMjmNmois02(pEntityJPA.getMjmNmois02());
				objet.setPcNuitNmois02(pEntityJPA.getPcNuitNmois02());
				objet.setMjmNmois03(pEntityJPA.getMjmNmois03());
				objet.setPcNuitNmois03(pEntityJPA.getPcNuitNmois03());
				objet.setMjmNmois04(pEntityJPA.getMjmNmois04());
				objet.setPcNuitNmois04(pEntityJPA.getPcNuitNmois04());
				objet.setMjmNmois05(pEntityJPA.getMjmNmois05());
				objet.setPcNuitNmois05(pEntityJPA.getPcNuitNmois05());
				objet.setMjmNmois06(pEntityJPA.getMjmNmois06());
				objet.setPcNuitNmois06(pEntityJPA.getPcNuitNmois06());
				objet.setMjmNmois07(pEntityJPA.getMjmNmois07());
				objet.setPcNuitNmois07(pEntityJPA.getPcNuitNmois07());
				objet.setMjmNmois08(pEntityJPA.getMjmNmois08());
				objet.setPcNuitNmois08(pEntityJPA.getPcNuitNmois08());
				objet.setMjmNmois09(pEntityJPA.getMjmNmois09());
				objet.setPcNuitNmois09(pEntityJPA.getPcNuitNmois09());
				objet.setMjmNmois10(pEntityJPA.getMjmNmois10());
				objet.setPcNuitNmois10(pEntityJPA.getPcNuitNmois10());
				objet.setMjmNmois11(pEntityJPA.getMjmNmois11());
				objet.setPcNuitNmois11(pEntityJPA.getPcNuitNmois11());
				objet.setMjmNmois12(pEntityJPA.getMjmNmois12());
				objet.setPcNuitNmois12(pEntityJPA.getPcNuitNmois12());
				objet.setZoneLibre3(pEntityJPA.getZoneLibre3());
				objet.setAnneeNmoins1(pEntityJPA.getAnneeNmoins1());
				objet.setMjaNmoins1(pEntityJPA.getMjaNmoins1());
				objet.setTypeComptageNmoins1(pEntityJPA.getTypeComptageNmoins1());
				objet.setModeCalculNmoins1(pEntityJPA.getModeCalculNmoins1());
				objet.setPcPLNmoins1(pEntityJPA.getPcPLNmoins1());
				objet.setEvaluationPLNmoins1(pEntityJPA.getEvaluationPLNmoins1());
				objet.setPcNuitAnnuelNmoins1(pEntityJPA.getPcNuitAnnuelNmoins1());
				objet.setIndiceFiabiliteMjaNmoins1(pEntityJPA.getIndiceFiabiliteMjaNmoins1());
				objet.setAnneeNmoins2(pEntityJPA.getAnneeNmoins2());
				objet.setMjaNmoins2(pEntityJPA.getMjaNmoins2());
				objet.setTypeComptageNmoins2(pEntityJPA.getTypeComptageNmoins2());
				objet.setModeCalculNmoins2(pEntityJPA.getModeCalculNmoins2());
				objet.setPcPLNmoins2(pEntityJPA.getPcPLNmoins2());
				objet.setEvaluationPLNmoins2(pEntityJPA.getEvaluationPLNmoins2());
				objet.setPcNuitAnnuelNmoins2(pEntityJPA.getPcNuitAnnuelNmoins2());
				objet.setIndiceFiabiliteMjaNmoins2(pEntityJPA.getIndiceFiabiliteMjaNmoins2());
				objet.setAnneeNmoins3(pEntityJPA.getAnneeNmoins3());
				objet.setMjaNmoins3(pEntityJPA.getMjaNmoins3());
				objet.setTypeComptageNmoins3(pEntityJPA.getTypeComptageNmoins3());
				objet.setModeCalculNmoins3(pEntityJPA.getModeCalculNmoins3());
				objet.setPcPLNmoins3(pEntityJPA.getPcPLNmoins3());
				objet.setEvaluationPLNmoins3(pEntityJPA.getEvaluationPLNmoins3());
				objet.setPcNuitAnnuelNmoins3(pEntityJPA.getPcNuitAnnuelNmoins3());
				objet.setIndiceFiabiliteMjaNmoins3(pEntityJPA.getIndiceFiabiliteMjaNmoins3());
				objet.setAnneeNmoins4(pEntityJPA.getAnneeNmoins4());
				objet.setMjaNmoins4(pEntityJPA.getMjaNmoins4());
				objet.setTypeComptageNmoins4(pEntityJPA.getTypeComptageNmoins4());
				objet.setModeCalculNmoins4(pEntityJPA.getModeCalculNmoins4());
				objet.setPcPLNmoins4(pEntityJPA.getPcPLNmoins4());
				objet.setEvaluationPLNmoins4(pEntityJPA.getEvaluationPLNmoins4());
				objet.setPcNuitAnnuelNmoins4(pEntityJPA.getPcNuitAnnuelNmoins4());
				objet.setIndiceFiabiliteMjaNmoins4(pEntityJPA.getIndiceFiabiliteMjaNmoins4());
				objet.setAnneeNmoins5(pEntityJPA.getAnneeNmoins5());
				objet.setMjaNmoins5(pEntityJPA.getMjaNmoins5());
				objet.setTypeComptageNmoins5(pEntityJPA.getTypeComptageNmoins5());
				objet.setModeCalculNmoins5(pEntityJPA.getModeCalculNmoins5());
				objet.setPcPLNmoins5(pEntityJPA.getPcPLNmoins5());
				objet.setEvaluationPLNmoins5(pEntityJPA.getEvaluationPLNmoins5());
				objet.setPcNuitAnnuelNmoins5(pEntityJPA.getPcNuitAnnuelNmoins5());
				objet.setIndiceFiabiliteMjaNmoins5(pEntityJPA.getIndiceFiabiliteMjaNmoins5());
				objet.setMjmNmoins1mois01(pEntityJPA.getMjmNmoins1mois01());
				objet.setPcNuitNmoins1mois01(pEntityJPA.getPcNuitNmoins1mois01());
				objet.setMjmNmoins1mois02(pEntityJPA.getMjmNmoins1mois02());
				objet.setPcNuitNmoins1mois02(pEntityJPA.getPcNuitNmoins1mois02());
				objet.setMjmNmoins1mois03(pEntityJPA.getMjmNmoins1mois03());
				objet.setPcNuitNmoins1mois03(pEntityJPA.getPcNuitNmoins1mois03());
				objet.setMjmNmoins1mois04(pEntityJPA.getMjmNmoins1mois04());
				objet.setPcNuitNmoins1mois04(pEntityJPA.getPcNuitNmoins1mois04());
				objet.setMjmNmoins1mois05(pEntityJPA.getMjmNmoins1mois05());
				objet.setPcNuitNmoins1mois05(pEntityJPA.getPcNuitNmoins1mois05());
				objet.setMjmNmoins1mois06(pEntityJPA.getMjmNmoins1mois06());
				objet.setPcNuitNmoins1mois06(pEntityJPA.getPcNuitNmoins1mois06());
				objet.setMjmNmoins1mois07(pEntityJPA.getMjmNmoins1mois07());
				objet.setPcNuitNmoins1mois07(pEntityJPA.getPcNuitNmoins1mois07());
				objet.setMjmNmoins1mois08(pEntityJPA.getMjmNmoins1mois08());
				objet.setPcNuitNmoins1mois08(pEntityJPA.getPcNuitNmoins1mois08());
				objet.setMjmNmoins1mois09(pEntityJPA.getMjmNmoins1mois09());
				objet.setPcNuitNmoins1mois09(pEntityJPA.getPcNuitNmoins1mois09());
				objet.setMjmNmoins1mois10(pEntityJPA.getMjmNmoins1mois10());
				objet.setPcNuitNmoins1mois10(pEntityJPA.getPcNuitNmoins1mois10());
				objet.setMjmNmoins1mois11(pEntityJPA.getMjmNmoins1mois11());
				objet.setPcNuitNmoins1mois11(pEntityJPA.getPcNuitNmoins1mois11());
				objet.setMjmNmoins1mois12(pEntityJPA.getMjmNmoins1mois12());
				objet.setPcNuitNmoins1mois12(pEntityJPA.getPcNuitNmoins1mois12());
				objet.setZoneLibre4(pEntityJPA.getZoneLibre4());

			}

			return objet;
			
		} // Fin de synchronized._______________________
		
	} // Fin de creerObjetMetierAPartirEntityJPA(...)._____________________
	
	
	
	
	/**
	 * <b>convertit une ENTITY JPA en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pEntity == null.</li>
	 * <li>récupère les valeurs dans le pEntity.</li>
	 * <li>injecte les valeurs de l'ENTITY dans un OBJET METIER.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : SectionHitEntityJPA.<br/>
	 * 
	 * @return : ISectionHit : Objet métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ISectionHit convertirEntityJPAEnObjetMetier(
			final SectionHitEntityJPA pEntityJPA) 
									throws Exception {
		
		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			ISectionHit objet = null;
			
			if (pEntityJPA != null) {
				
				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet = new SectionHit();

				objet.setId(pEntityJPA.getId());
				objet.setNumDepartement(pEntityJPA.getNumDepartement());
				objet.setNumSection(pEntityJPA.getNumSection());
				objet.setSens(pEntityJPA.getSens());
				objet.setNature(pEntityJPA.getNature());
				objet.setClasse(pEntityJPA.getClasse());
				objet.setAnneeTraitement(pEntityJPA.getAnneeTraitement());
				objet.setZoneLibre1(pEntityJPA.getZoneLibre1());
				objet.setNumRoute(pEntityJPA.getNumRoute());
				objet.setIndiceNumRoute(pEntityJPA.getIndiceNumRoute());
				objet.setIndiceLettreRoute(pEntityJPA.getIndiceLettreRoute());
				objet.setCategorieAdminRoute(pEntityJPA.getCategorieAdminRoute());
				objet.setTypeComptage(pEntityJPA.getTypeComptage());
				objet.setClassementRoute(pEntityJPA.getClassementRoute());
				objet.setClasseLargeurChausseeU(pEntityJPA.getClasseLargeurChausseeU());
				objet.setClasseLargeurChausseesS(pEntityJPA.getClasseLargeurChausseesS());
				objet.setTypeReseau(pEntityJPA.getTypeReseau());
				objet.setpRoupK(pEntityJPA.getpRoupK());
				objet.setLieuDitOrigine(pEntityJPA.getLieuDitOrigine());
				objet.setPrOrigine(pEntityJPA.getPrOrigine());
				objet.setAbsOrigine(pEntityJPA.getAbsOrigine());
				objet.setLieuDitExtremite(pEntityJPA.getLieuDitExtremite());
				objet.setPrExtremite(pEntityJPA.getPrExtremite());
				objet.setAbsExtremite(pEntityJPA.getAbsExtremite());
				objet.setLieuDitComptage(pEntityJPA.getLieuDitComptage());
				objet.setPrComptage(pEntityJPA.getPrComptage());
				objet.setAbsComptage(pEntityJPA.getAbsComptage());
				objet.setLongueurSection(pEntityJPA.getLongueurSection());
				objet.setLongueurRaseCampagne(pEntityJPA.getLongueurRaseCampagne());
				objet.setNumDepartementRattachement(pEntityJPA.getNumDepartementRattachement());
				objet.setNumSectionRattachement(pEntityJPA.getNumSectionRattachement());
				objet.setSensRattachement(pEntityJPA.getSensRattachement());
				objet.setNumDepartementLimitrophe(pEntityJPA.getNumDepartementLimitrophe());
				objet.setNumSectionLimitrophe(pEntityJPA.getNumSectionLimitrophe());
				objet.setSensLimitrophe(pEntityJPA.getSensLimitrophe());
				objet.setMoisSectionnement(pEntityJPA.getMoisSectionnement());
				objet.setAnneeSectionnement(pEntityJPA.getAnneeSectionnement());
				objet.setZoneLibre2(pEntityJPA.getZoneLibre2());
				objet.setMjaN(pEntityJPA.getMjaN());
				objet.setModeCalculN(pEntityJPA.getModeCalculN());
				objet.setPcPLN(pEntityJPA.getPcPLN());
				objet.setEvaluationPLN(pEntityJPA.getEvaluationPLN());
				objet.setPcNuitAnnuelN(pEntityJPA.getPcNuitAnnuelN());
				objet.setIndiceFiabiliteMjaN(pEntityJPA.getIndiceFiabiliteMjaN());
				objet.setMjmNmois01(pEntityJPA.getMjmNmois01());
				objet.setPcNuitNmois01(pEntityJPA.getPcNuitNmois01());
				objet.setMjmNmois02(pEntityJPA.getMjmNmois02());
				objet.setPcNuitNmois02(pEntityJPA.getPcNuitNmois02());
				objet.setMjmNmois03(pEntityJPA.getMjmNmois03());
				objet.setPcNuitNmois03(pEntityJPA.getPcNuitNmois03());
				objet.setMjmNmois04(pEntityJPA.getMjmNmois04());
				objet.setPcNuitNmois04(pEntityJPA.getPcNuitNmois04());
				objet.setMjmNmois05(pEntityJPA.getMjmNmois05());
				objet.setPcNuitNmois05(pEntityJPA.getPcNuitNmois05());
				objet.setMjmNmois06(pEntityJPA.getMjmNmois06());
				objet.setPcNuitNmois06(pEntityJPA.getPcNuitNmois06());
				objet.setMjmNmois07(pEntityJPA.getMjmNmois07());
				objet.setPcNuitNmois07(pEntityJPA.getPcNuitNmois07());
				objet.setMjmNmois08(pEntityJPA.getMjmNmois08());
				objet.setPcNuitNmois08(pEntityJPA.getPcNuitNmois08());
				objet.setMjmNmois09(pEntityJPA.getMjmNmois09());
				objet.setPcNuitNmois09(pEntityJPA.getPcNuitNmois09());
				objet.setMjmNmois10(pEntityJPA.getMjmNmois10());
				objet.setPcNuitNmois10(pEntityJPA.getPcNuitNmois10());
				objet.setMjmNmois11(pEntityJPA.getMjmNmois11());
				objet.setPcNuitNmois11(pEntityJPA.getPcNuitNmois11());
				objet.setMjmNmois12(pEntityJPA.getMjmNmois12());
				objet.setPcNuitNmois12(pEntityJPA.getPcNuitNmois12());
				objet.setZoneLibre3(pEntityJPA.getZoneLibre3());
				objet.setAnneeNmoins1(pEntityJPA.getAnneeNmoins1());
				objet.setMjaNmoins1(pEntityJPA.getMjaNmoins1());
				objet.setTypeComptageNmoins1(pEntityJPA.getTypeComptageNmoins1());
				objet.setModeCalculNmoins1(pEntityJPA.getModeCalculNmoins1());
				objet.setPcPLNmoins1(pEntityJPA.getPcPLNmoins1());
				objet.setEvaluationPLNmoins1(pEntityJPA.getEvaluationPLNmoins1());
				objet.setPcNuitAnnuelNmoins1(pEntityJPA.getPcNuitAnnuelNmoins1());
				objet.setIndiceFiabiliteMjaNmoins1(pEntityJPA.getIndiceFiabiliteMjaNmoins1());
				objet.setAnneeNmoins2(pEntityJPA.getAnneeNmoins2());
				objet.setMjaNmoins2(pEntityJPA.getMjaNmoins2());
				objet.setTypeComptageNmoins2(pEntityJPA.getTypeComptageNmoins2());
				objet.setModeCalculNmoins2(pEntityJPA.getModeCalculNmoins2());
				objet.setPcPLNmoins2(pEntityJPA.getPcPLNmoins2());
				objet.setEvaluationPLNmoins2(pEntityJPA.getEvaluationPLNmoins2());
				objet.setPcNuitAnnuelNmoins2(pEntityJPA.getPcNuitAnnuelNmoins2());
				objet.setIndiceFiabiliteMjaNmoins2(pEntityJPA.getIndiceFiabiliteMjaNmoins2());
				objet.setAnneeNmoins3(pEntityJPA.getAnneeNmoins3());
				objet.setMjaNmoins3(pEntityJPA.getMjaNmoins3());
				objet.setTypeComptageNmoins3(pEntityJPA.getTypeComptageNmoins3());
				objet.setModeCalculNmoins3(pEntityJPA.getModeCalculNmoins3());
				objet.setPcPLNmoins3(pEntityJPA.getPcPLNmoins3());
				objet.setEvaluationPLNmoins3(pEntityJPA.getEvaluationPLNmoins3());
				objet.setPcNuitAnnuelNmoins3(pEntityJPA.getPcNuitAnnuelNmoins3());
				objet.setIndiceFiabiliteMjaNmoins3(pEntityJPA.getIndiceFiabiliteMjaNmoins3());
				objet.setAnneeNmoins4(pEntityJPA.getAnneeNmoins4());
				objet.setMjaNmoins4(pEntityJPA.getMjaNmoins4());
				objet.setTypeComptageNmoins4(pEntityJPA.getTypeComptageNmoins4());
				objet.setModeCalculNmoins4(pEntityJPA.getModeCalculNmoins4());
				objet.setPcPLNmoins4(pEntityJPA.getPcPLNmoins4());
				objet.setEvaluationPLNmoins4(pEntityJPA.getEvaluationPLNmoins4());
				objet.setPcNuitAnnuelNmoins4(pEntityJPA.getPcNuitAnnuelNmoins4());
				objet.setIndiceFiabiliteMjaNmoins4(pEntityJPA.getIndiceFiabiliteMjaNmoins4());
				objet.setAnneeNmoins5(pEntityJPA.getAnneeNmoins5());
				objet.setMjaNmoins5(pEntityJPA.getMjaNmoins5());
				objet.setTypeComptageNmoins5(pEntityJPA.getTypeComptageNmoins5());
				objet.setModeCalculNmoins5(pEntityJPA.getModeCalculNmoins5());
				objet.setPcPLNmoins5(pEntityJPA.getPcPLNmoins5());
				objet.setEvaluationPLNmoins5(pEntityJPA.getEvaluationPLNmoins5());
				objet.setPcNuitAnnuelNmoins5(pEntityJPA.getPcNuitAnnuelNmoins5());
				objet.setIndiceFiabiliteMjaNmoins5(pEntityJPA.getIndiceFiabiliteMjaNmoins5());
				objet.setMjmNmoins1mois01(pEntityJPA.getMjmNmoins1mois01());
				objet.setPcNuitNmoins1mois01(pEntityJPA.getPcNuitNmoins1mois01());
				objet.setMjmNmoins1mois02(pEntityJPA.getMjmNmoins1mois02());
				objet.setPcNuitNmoins1mois02(pEntityJPA.getPcNuitNmoins1mois02());
				objet.setMjmNmoins1mois03(pEntityJPA.getMjmNmoins1mois03());
				objet.setPcNuitNmoins1mois03(pEntityJPA.getPcNuitNmoins1mois03());
				objet.setMjmNmoins1mois04(pEntityJPA.getMjmNmoins1mois04());
				objet.setPcNuitNmoins1mois04(pEntityJPA.getPcNuitNmoins1mois04());
				objet.setMjmNmoins1mois05(pEntityJPA.getMjmNmoins1mois05());
				objet.setPcNuitNmoins1mois05(pEntityJPA.getPcNuitNmoins1mois05());
				objet.setMjmNmoins1mois06(pEntityJPA.getMjmNmoins1mois06());
				objet.setPcNuitNmoins1mois06(pEntityJPA.getPcNuitNmoins1mois06());
				objet.setMjmNmoins1mois07(pEntityJPA.getMjmNmoins1mois07());
				objet.setPcNuitNmoins1mois07(pEntityJPA.getPcNuitNmoins1mois07());
				objet.setMjmNmoins1mois08(pEntityJPA.getMjmNmoins1mois08());
				objet.setPcNuitNmoins1mois08(pEntityJPA.getPcNuitNmoins1mois08());
				objet.setMjmNmoins1mois09(pEntityJPA.getMjmNmoins1mois09());
				objet.setPcNuitNmoins1mois09(pEntityJPA.getPcNuitNmoins1mois09());
				objet.setMjmNmoins1mois10(pEntityJPA.getMjmNmoins1mois10());
				objet.setPcNuitNmoins1mois10(pEntityJPA.getPcNuitNmoins1mois10());
				objet.setMjmNmoins1mois11(pEntityJPA.getMjmNmoins1mois11());
				objet.setPcNuitNmoins1mois11(pEntityJPA.getPcNuitNmoins1mois11());
				objet.setMjmNmoins1mois12(pEntityJPA.getMjmNmoins1mois12());
				objet.setPcNuitNmoins1mois12(pEntityJPA.getPcNuitNmoins1mois12());
				objet.setZoneLibre4(pEntityJPA.getZoneLibre4());
								
			}
			
			return objet;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirEntityJPAEnObjetMetier(...).______________________
	

	
	/**
	 * convertit une Liste d'ENTITIES JPA 
	 * en liste d'OBJETS METIER et la retourne.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * - n'insère dans la liste résultat que les Entities non null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;SectionHitEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;ISectionHit&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static List<ISectionHit> convertirListEntitiesJPAEnModel(
			final List<SectionHitEntityJPA> pList) 
										throws Exception {
		
		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<ISectionHit> resultat 
				= new ArrayList<ISectionHit>();
			
			for (final SectionHitEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final ISectionHit objet 													
						= convertirEntityJPAEnObjetMetier(entity);
					
					resultat.add(objet);
					
				}
			}
			
			return resultat;
			
		} // Fin de synchronized.______________________
		
	} // Fin de convertirListEntitiesJPAEnModel(...).______________________
	
	
		
	/**
	 * <b>crée une ENTITY JPA à partir d'un OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne une Entity JPA avec toutes les valeurs 
	 * à null si pObject == null.</li>
	 * </ul>
	 *
	 * @param pObject : ISectionHit.<br/>
	 *  
	 * @return : SectionHitEntityJPA.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SectionHitEntityJPA creerEntityJPA(
			final ISectionHit pObject) 
						throws Exception {
		
		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			final SectionHitEntityJPA entityJPA 
				= new SectionHitEntityJPA();

			if (pObject != null) {

				entityJPA.setId(pObject.getId());
				entityJPA.setNumDepartement(pObject.getNumDepartement());
				entityJPA.setNumSection(pObject.getNumSection());
				entityJPA.setSens(pObject.getSens());
				entityJPA.setNature(pObject.getNature());
				entityJPA.setClasse(pObject.getClasse());
				entityJPA.setAnneeTraitement(pObject.getAnneeTraitement());
				entityJPA.setZoneLibre1(pObject.getZoneLibre1());
				entityJPA.setNumRoute(pObject.getNumRoute());
				entityJPA.setIndiceNumRoute(pObject.getIndiceNumRoute());
				entityJPA.setIndiceLettreRoute(pObject.getIndiceLettreRoute());
				entityJPA.setCategorieAdminRoute(pObject.getCategorieAdminRoute());
				entityJPA.setTypeComptage(pObject.getTypeComptage());
				entityJPA.setClassementRoute(pObject.getClassementRoute());
				entityJPA.setClasseLargeurChausseeU(pObject.getClasseLargeurChausseeU());
				entityJPA.setClasseLargeurChausseesS(pObject.getClasseLargeurChausseesS());
				entityJPA.setTypeReseau(pObject.getTypeReseau());
				entityJPA.setpRoupK(pObject.getpRoupK());
				entityJPA.setLieuDitOrigine(pObject.getLieuDitOrigine());
				entityJPA.setPrOrigine(pObject.getPrOrigine());
				entityJPA.setAbsOrigine(pObject.getAbsOrigine());
				entityJPA.setLieuDitExtremite(pObject.getLieuDitExtremite());
				entityJPA.setPrExtremite(pObject.getPrExtremite());
				entityJPA.setAbsExtremite(pObject.getAbsExtremite());
				entityJPA.setLieuDitComptage(pObject.getLieuDitComptage());
				entityJPA.setPrComptage(pObject.getPrComptage());
				entityJPA.setAbsComptage(pObject.getAbsComptage());
				entityJPA.setLongueurSection(pObject.getLongueurSection());
				entityJPA.setLongueurRaseCampagne(pObject.getLongueurRaseCampagne());
				entityJPA.setNumDepartementRattachement(pObject.getNumDepartementRattachement());
				entityJPA.setNumSectionRattachement(pObject.getNumSectionRattachement());
				entityJPA.setSensRattachement(pObject.getSensRattachement());
				entityJPA.setNumDepartementLimitrophe(pObject.getNumDepartementLimitrophe());
				entityJPA.setNumSectionLimitrophe(pObject.getNumSectionLimitrophe());
				entityJPA.setSensLimitrophe(pObject.getSensLimitrophe());
				entityJPA.setMoisSectionnement(pObject.getMoisSectionnement());
				entityJPA.setAnneeSectionnement(pObject.getAnneeSectionnement());
				entityJPA.setZoneLibre2(pObject.getZoneLibre2());
				entityJPA.setMjaN(pObject.getMjaN());
				entityJPA.setModeCalculN(pObject.getModeCalculN());
				entityJPA.setPcPLN(pObject.getPcPLN());
				entityJPA.setEvaluationPLN(pObject.getEvaluationPLN());
				entityJPA.setPcNuitAnnuelN(pObject.getPcNuitAnnuelN());
				entityJPA.setIndiceFiabiliteMjaN(pObject.getIndiceFiabiliteMjaN());
				entityJPA.setMjmNmois01(pObject.getMjmNmois01());
				entityJPA.setPcNuitNmois01(pObject.getPcNuitNmois01());
				entityJPA.setMjmNmois02(pObject.getMjmNmois02());
				entityJPA.setPcNuitNmois02(pObject.getPcNuitNmois02());
				entityJPA.setMjmNmois03(pObject.getMjmNmois03());
				entityJPA.setPcNuitNmois03(pObject.getPcNuitNmois03());
				entityJPA.setMjmNmois04(pObject.getMjmNmois04());
				entityJPA.setPcNuitNmois04(pObject.getPcNuitNmois04());
				entityJPA.setMjmNmois05(pObject.getMjmNmois05());
				entityJPA.setPcNuitNmois05(pObject.getPcNuitNmois05());
				entityJPA.setMjmNmois06(pObject.getMjmNmois06());
				entityJPA.setPcNuitNmois06(pObject.getPcNuitNmois06());
				entityJPA.setMjmNmois07(pObject.getMjmNmois07());
				entityJPA.setPcNuitNmois07(pObject.getPcNuitNmois07());
				entityJPA.setMjmNmois08(pObject.getMjmNmois08());
				entityJPA.setPcNuitNmois08(pObject.getPcNuitNmois08());
				entityJPA.setMjmNmois09(pObject.getMjmNmois09());
				entityJPA.setPcNuitNmois09(pObject.getPcNuitNmois09());
				entityJPA.setMjmNmois10(pObject.getMjmNmois10());
				entityJPA.setPcNuitNmois10(pObject.getPcNuitNmois10());
				entityJPA.setMjmNmois11(pObject.getMjmNmois11());
				entityJPA.setPcNuitNmois11(pObject.getPcNuitNmois11());
				entityJPA.setMjmNmois12(pObject.getMjmNmois12());
				entityJPA.setPcNuitNmois12(pObject.getPcNuitNmois12());
				entityJPA.setZoneLibre3(pObject.getZoneLibre3());
				entityJPA.setAnneeNmoins1(pObject.getAnneeNmoins1());
				entityJPA.setMjaNmoins1(pObject.getMjaNmoins1());
				entityJPA.setTypeComptageNmoins1(pObject.getTypeComptageNmoins1());
				entityJPA.setModeCalculNmoins1(pObject.getModeCalculNmoins1());
				entityJPA.setPcPLNmoins1(pObject.getPcPLNmoins1());
				entityJPA.setEvaluationPLNmoins1(pObject.getEvaluationPLNmoins1());
				entityJPA.setPcNuitAnnuelNmoins1(pObject.getPcNuitAnnuelNmoins1());
				entityJPA.setIndiceFiabiliteMjaNmoins1(pObject.getIndiceFiabiliteMjaNmoins1());
				entityJPA.setAnneeNmoins2(pObject.getAnneeNmoins2());
				entityJPA.setMjaNmoins2(pObject.getMjaNmoins2());
				entityJPA.setTypeComptageNmoins2(pObject.getTypeComptageNmoins2());
				entityJPA.setModeCalculNmoins2(pObject.getModeCalculNmoins2());
				entityJPA.setPcPLNmoins2(pObject.getPcPLNmoins2());
				entityJPA.setEvaluationPLNmoins2(pObject.getEvaluationPLNmoins2());
				entityJPA.setPcNuitAnnuelNmoins2(pObject.getPcNuitAnnuelNmoins2());
				entityJPA.setIndiceFiabiliteMjaNmoins2(pObject.getIndiceFiabiliteMjaNmoins2());
				entityJPA.setAnneeNmoins3(pObject.getAnneeNmoins3());
				entityJPA.setMjaNmoins3(pObject.getMjaNmoins3());
				entityJPA.setTypeComptageNmoins3(pObject.getTypeComptageNmoins3());
				entityJPA.setModeCalculNmoins3(pObject.getModeCalculNmoins3());
				entityJPA.setPcPLNmoins3(pObject.getPcPLNmoins3());
				entityJPA.setEvaluationPLNmoins3(pObject.getEvaluationPLNmoins3());
				entityJPA.setPcNuitAnnuelNmoins3(pObject.getPcNuitAnnuelNmoins3());
				entityJPA.setIndiceFiabiliteMjaNmoins3(pObject.getIndiceFiabiliteMjaNmoins3());
				entityJPA.setAnneeNmoins4(pObject.getAnneeNmoins4());
				entityJPA.setMjaNmoins4(pObject.getMjaNmoins4());
				entityJPA.setTypeComptageNmoins4(pObject.getTypeComptageNmoins4());
				entityJPA.setModeCalculNmoins4(pObject.getModeCalculNmoins4());
				entityJPA.setPcPLNmoins4(pObject.getPcPLNmoins4());
				entityJPA.setEvaluationPLNmoins4(pObject.getEvaluationPLNmoins4());
				entityJPA.setPcNuitAnnuelNmoins4(pObject.getPcNuitAnnuelNmoins4());
				entityJPA.setIndiceFiabiliteMjaNmoins4(pObject.getIndiceFiabiliteMjaNmoins4());
				entityJPA.setAnneeNmoins5(pObject.getAnneeNmoins5());
				entityJPA.setMjaNmoins5(pObject.getMjaNmoins5());
				entityJPA.setTypeComptageNmoins5(pObject.getTypeComptageNmoins5());
				entityJPA.setModeCalculNmoins5(pObject.getModeCalculNmoins5());
				entityJPA.setPcPLNmoins5(pObject.getPcPLNmoins5());
				entityJPA.setEvaluationPLNmoins5(pObject.getEvaluationPLNmoins5());
				entityJPA.setPcNuitAnnuelNmoins5(pObject.getPcNuitAnnuelNmoins5());
				entityJPA.setIndiceFiabiliteMjaNmoins5(pObject.getIndiceFiabiliteMjaNmoins5());
				entityJPA.setMjmNmoins1mois01(pObject.getMjmNmoins1mois01());
				entityJPA.setPcNuitNmoins1mois01(pObject.getPcNuitNmoins1mois01());
				entityJPA.setMjmNmoins1mois02(pObject.getMjmNmoins1mois02());
				entityJPA.setPcNuitNmoins1mois02(pObject.getPcNuitNmoins1mois02());
				entityJPA.setMjmNmoins1mois03(pObject.getMjmNmoins1mois03());
				entityJPA.setPcNuitNmoins1mois03(pObject.getPcNuitNmoins1mois03());
				entityJPA.setMjmNmoins1mois04(pObject.getMjmNmoins1mois04());
				entityJPA.setPcNuitNmoins1mois04(pObject.getPcNuitNmoins1mois04());
				entityJPA.setMjmNmoins1mois05(pObject.getMjmNmoins1mois05());
				entityJPA.setPcNuitNmoins1mois05(pObject.getPcNuitNmoins1mois05());
				entityJPA.setMjmNmoins1mois06(pObject.getMjmNmoins1mois06());
				entityJPA.setPcNuitNmoins1mois06(pObject.getPcNuitNmoins1mois06());
				entityJPA.setMjmNmoins1mois07(pObject.getMjmNmoins1mois07());
				entityJPA.setPcNuitNmoins1mois07(pObject.getPcNuitNmoins1mois07());
				entityJPA.setMjmNmoins1mois08(pObject.getMjmNmoins1mois08());
				entityJPA.setPcNuitNmoins1mois08(pObject.getPcNuitNmoins1mois08());
				entityJPA.setMjmNmoins1mois09(pObject.getMjmNmoins1mois09());
				entityJPA.setPcNuitNmoins1mois09(pObject.getPcNuitNmoins1mois09());
				entityJPA.setMjmNmoins1mois10(pObject.getMjmNmoins1mois10());
				entityJPA.setPcNuitNmoins1mois10(pObject.getPcNuitNmoins1mois10());
				entityJPA.setMjmNmoins1mois11(pObject.getMjmNmoins1mois11());
				entityJPA.setPcNuitNmoins1mois11(pObject.getPcNuitNmoins1mois11());
				entityJPA.setMjmNmoins1mois12(pObject.getMjmNmoins1mois12());
				entityJPA.setPcNuitNmoins1mois12(pObject.getPcNuitNmoins1mois12());
				entityJPA.setZoneLibre4(pObject.getZoneLibre4());

			}

			return entityJPA;

		} // Fin de synchronized.______________________
		
	} // Fin de creerEntityJPA(...)._______________________________________

	
	
	/**
	 * <b>convertit un OBJET METIER en ENTITY JPA</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>injecte les valeurs de l'objet métier dans une ENTITY.</li>
	 * </ul>
	 *
	 * @param pObject : ISectionHit : Objet métier.<br/>
	 * 
	 * @return : SectionHitEntityJPA : ENTITY JPA.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SectionHitEntityJPA convertirObjetMetierEnEntityJPA(
						final ISectionHit pObject) 
									throws Exception {
		
		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			SectionHitEntityJPA entityJPA = null;
			
			if (pObject != null) {
								
				/* injecte les valeurs String dans un DTO. */
				entityJPA = new SectionHitEntityJPA();

				entityJPA.setId(pObject.getId());
				entityJPA.setNumDepartement(pObject.getNumDepartement());
				entityJPA.setNumSection(pObject.getNumSection());
				entityJPA.setSens(pObject.getSens());
				entityJPA.setNature(pObject.getNature());
				entityJPA.setClasse(pObject.getClasse());
				entityJPA.setAnneeTraitement(pObject.getAnneeTraitement());
				entityJPA.setZoneLibre1(pObject.getZoneLibre1());
				entityJPA.setNumRoute(pObject.getNumRoute());
				entityJPA.setIndiceNumRoute(pObject.getIndiceNumRoute());
				entityJPA.setIndiceLettreRoute(pObject.getIndiceLettreRoute());
				entityJPA.setCategorieAdminRoute(pObject.getCategorieAdminRoute());
				entityJPA.setTypeComptage(pObject.getTypeComptage());
				entityJPA.setClassementRoute(pObject.getClassementRoute());
				entityJPA.setClasseLargeurChausseeU(pObject.getClasseLargeurChausseeU());
				entityJPA.setClasseLargeurChausseesS(pObject.getClasseLargeurChausseesS());
				entityJPA.setTypeReseau(pObject.getTypeReseau());
				entityJPA.setpRoupK(pObject.getpRoupK());
				entityJPA.setLieuDitOrigine(pObject.getLieuDitOrigine());
				entityJPA.setPrOrigine(pObject.getPrOrigine());
				entityJPA.setAbsOrigine(pObject.getAbsOrigine());
				entityJPA.setLieuDitExtremite(pObject.getLieuDitExtremite());
				entityJPA.setPrExtremite(pObject.getPrExtremite());
				entityJPA.setAbsExtremite(pObject.getAbsExtremite());
				entityJPA.setLieuDitComptage(pObject.getLieuDitComptage());
				entityJPA.setPrComptage(pObject.getPrComptage());
				entityJPA.setAbsComptage(pObject.getAbsComptage());
				entityJPA.setLongueurSection(pObject.getLongueurSection());
				entityJPA.setLongueurRaseCampagne(pObject.getLongueurRaseCampagne());
				entityJPA.setNumDepartementRattachement(pObject.getNumDepartementRattachement());
				entityJPA.setNumSectionRattachement(pObject.getNumSectionRattachement());
				entityJPA.setSensRattachement(pObject.getSensRattachement());
				entityJPA.setNumDepartementLimitrophe(pObject.getNumDepartementLimitrophe());
				entityJPA.setNumSectionLimitrophe(pObject.getNumSectionLimitrophe());
				entityJPA.setSensLimitrophe(pObject.getSensLimitrophe());
				entityJPA.setMoisSectionnement(pObject.getMoisSectionnement());
				entityJPA.setAnneeSectionnement(pObject.getAnneeSectionnement());
				entityJPA.setZoneLibre2(pObject.getZoneLibre2());
				entityJPA.setMjaN(pObject.getMjaN());
				entityJPA.setModeCalculN(pObject.getModeCalculN());
				entityJPA.setPcPLN(pObject.getPcPLN());
				entityJPA.setEvaluationPLN(pObject.getEvaluationPLN());
				entityJPA.setPcNuitAnnuelN(pObject.getPcNuitAnnuelN());
				entityJPA.setIndiceFiabiliteMjaN(pObject.getIndiceFiabiliteMjaN());
				entityJPA.setMjmNmois01(pObject.getMjmNmois01());
				entityJPA.setPcNuitNmois01(pObject.getPcNuitNmois01());
				entityJPA.setMjmNmois02(pObject.getMjmNmois02());
				entityJPA.setPcNuitNmois02(pObject.getPcNuitNmois02());
				entityJPA.setMjmNmois03(pObject.getMjmNmois03());
				entityJPA.setPcNuitNmois03(pObject.getPcNuitNmois03());
				entityJPA.setMjmNmois04(pObject.getMjmNmois04());
				entityJPA.setPcNuitNmois04(pObject.getPcNuitNmois04());
				entityJPA.setMjmNmois05(pObject.getMjmNmois05());
				entityJPA.setPcNuitNmois05(pObject.getPcNuitNmois05());
				entityJPA.setMjmNmois06(pObject.getMjmNmois06());
				entityJPA.setPcNuitNmois06(pObject.getPcNuitNmois06());
				entityJPA.setMjmNmois07(pObject.getMjmNmois07());
				entityJPA.setPcNuitNmois07(pObject.getPcNuitNmois07());
				entityJPA.setMjmNmois08(pObject.getMjmNmois08());
				entityJPA.setPcNuitNmois08(pObject.getPcNuitNmois08());
				entityJPA.setMjmNmois09(pObject.getMjmNmois09());
				entityJPA.setPcNuitNmois09(pObject.getPcNuitNmois09());
				entityJPA.setMjmNmois10(pObject.getMjmNmois10());
				entityJPA.setPcNuitNmois10(pObject.getPcNuitNmois10());
				entityJPA.setMjmNmois11(pObject.getMjmNmois11());
				entityJPA.setPcNuitNmois11(pObject.getPcNuitNmois11());
				entityJPA.setMjmNmois12(pObject.getMjmNmois12());
				entityJPA.setPcNuitNmois12(pObject.getPcNuitNmois12());
				entityJPA.setZoneLibre3(pObject.getZoneLibre3());
				entityJPA.setAnneeNmoins1(pObject.getAnneeNmoins1());
				entityJPA.setMjaNmoins1(pObject.getMjaNmoins1());
				entityJPA.setTypeComptageNmoins1(pObject.getTypeComptageNmoins1());
				entityJPA.setModeCalculNmoins1(pObject.getModeCalculNmoins1());
				entityJPA.setPcPLNmoins1(pObject.getPcPLNmoins1());
				entityJPA.setEvaluationPLNmoins1(pObject.getEvaluationPLNmoins1());
				entityJPA.setPcNuitAnnuelNmoins1(pObject.getPcNuitAnnuelNmoins1());
				entityJPA.setIndiceFiabiliteMjaNmoins1(pObject.getIndiceFiabiliteMjaNmoins1());
				entityJPA.setAnneeNmoins2(pObject.getAnneeNmoins2());
				entityJPA.setMjaNmoins2(pObject.getMjaNmoins2());
				entityJPA.setTypeComptageNmoins2(pObject.getTypeComptageNmoins2());
				entityJPA.setModeCalculNmoins2(pObject.getModeCalculNmoins2());
				entityJPA.setPcPLNmoins2(pObject.getPcPLNmoins2());
				entityJPA.setEvaluationPLNmoins2(pObject.getEvaluationPLNmoins2());
				entityJPA.setPcNuitAnnuelNmoins2(pObject.getPcNuitAnnuelNmoins2());
				entityJPA.setIndiceFiabiliteMjaNmoins2(pObject.getIndiceFiabiliteMjaNmoins2());
				entityJPA.setAnneeNmoins3(pObject.getAnneeNmoins3());
				entityJPA.setMjaNmoins3(pObject.getMjaNmoins3());
				entityJPA.setTypeComptageNmoins3(pObject.getTypeComptageNmoins3());
				entityJPA.setModeCalculNmoins3(pObject.getModeCalculNmoins3());
				entityJPA.setPcPLNmoins3(pObject.getPcPLNmoins3());
				entityJPA.setEvaluationPLNmoins3(pObject.getEvaluationPLNmoins3());
				entityJPA.setPcNuitAnnuelNmoins3(pObject.getPcNuitAnnuelNmoins3());
				entityJPA.setIndiceFiabiliteMjaNmoins3(pObject.getIndiceFiabiliteMjaNmoins3());
				entityJPA.setAnneeNmoins4(pObject.getAnneeNmoins4());
				entityJPA.setMjaNmoins4(pObject.getMjaNmoins4());
				entityJPA.setTypeComptageNmoins4(pObject.getTypeComptageNmoins4());
				entityJPA.setModeCalculNmoins4(pObject.getModeCalculNmoins4());
				entityJPA.setPcPLNmoins4(pObject.getPcPLNmoins4());
				entityJPA.setEvaluationPLNmoins4(pObject.getEvaluationPLNmoins4());
				entityJPA.setPcNuitAnnuelNmoins4(pObject.getPcNuitAnnuelNmoins4());
				entityJPA.setIndiceFiabiliteMjaNmoins4(pObject.getIndiceFiabiliteMjaNmoins4());
				entityJPA.setAnneeNmoins5(pObject.getAnneeNmoins5());
				entityJPA.setMjaNmoins5(pObject.getMjaNmoins5());
				entityJPA.setTypeComptageNmoins5(pObject.getTypeComptageNmoins5());
				entityJPA.setModeCalculNmoins5(pObject.getModeCalculNmoins5());
				entityJPA.setPcPLNmoins5(pObject.getPcPLNmoins5());
				entityJPA.setEvaluationPLNmoins5(pObject.getEvaluationPLNmoins5());
				entityJPA.setPcNuitAnnuelNmoins5(pObject.getPcNuitAnnuelNmoins5());
				entityJPA.setIndiceFiabiliteMjaNmoins5(pObject.getIndiceFiabiliteMjaNmoins5());
				entityJPA.setMjmNmoins1mois01(pObject.getMjmNmoins1mois01());
				entityJPA.setPcNuitNmoins1mois01(pObject.getPcNuitNmoins1mois01());
				entityJPA.setMjmNmoins1mois02(pObject.getMjmNmoins1mois02());
				entityJPA.setPcNuitNmoins1mois02(pObject.getPcNuitNmoins1mois02());
				entityJPA.setMjmNmoins1mois03(pObject.getMjmNmoins1mois03());
				entityJPA.setPcNuitNmoins1mois03(pObject.getPcNuitNmoins1mois03());
				entityJPA.setMjmNmoins1mois04(pObject.getMjmNmoins1mois04());
				entityJPA.setPcNuitNmoins1mois04(pObject.getPcNuitNmoins1mois04());
				entityJPA.setMjmNmoins1mois05(pObject.getMjmNmoins1mois05());
				entityJPA.setPcNuitNmoins1mois05(pObject.getPcNuitNmoins1mois05());
				entityJPA.setMjmNmoins1mois06(pObject.getMjmNmoins1mois06());
				entityJPA.setPcNuitNmoins1mois06(pObject.getPcNuitNmoins1mois06());
				entityJPA.setMjmNmoins1mois07(pObject.getMjmNmoins1mois07());
				entityJPA.setPcNuitNmoins1mois07(pObject.getPcNuitNmoins1mois07());
				entityJPA.setMjmNmoins1mois08(pObject.getMjmNmoins1mois08());
				entityJPA.setPcNuitNmoins1mois08(pObject.getPcNuitNmoins1mois08());
				entityJPA.setMjmNmoins1mois09(pObject.getMjmNmoins1mois09());
				entityJPA.setPcNuitNmoins1mois09(pObject.getPcNuitNmoins1mois09());
				entityJPA.setMjmNmoins1mois10(pObject.getMjmNmoins1mois10());
				entityJPA.setPcNuitNmoins1mois10(pObject.getPcNuitNmoins1mois10());
				entityJPA.setMjmNmoins1mois11(pObject.getMjmNmoins1mois11());
				entityJPA.setPcNuitNmoins1mois11(pObject.getPcNuitNmoins1mois11());
				entityJPA.setMjmNmoins1mois12(pObject.getMjmNmoins1mois12());
				entityJPA.setPcNuitNmoins1mois12(pObject.getPcNuitNmoins1mois12());
				entityJPA.setZoneLibre4(pObject.getZoneLibre4());
				
			}
						
			return entityJPA;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnEntityJPA(...).______________________
	

	
	/**
	 * convertit une Liste d'OBJETS METIER en liste 
	 * d'ENTITIES JPA.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;ISectionHit&gt;
	 * 
	 * @return : List&lt;SectionHitEntityJPA&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static List<SectionHitEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<ISectionHit> pList) 
									throws Exception {
		
		synchronized (SectionHitConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<SectionHitEntityJPA> resultat 
				= new ArrayList<SectionHitEntityJPA>();
			
			for (final ISectionHit objet : pList) {
				
				if (objet != null) {
					
					final SectionHitEntityJPA entity 
						= convertirObjetMetierEnEntityJPA(objet);
					
					resultat.add(entity);
					
				}
			}
			
			return resultat;

		} // Fin de synchronized._______________________
		
	} // Fin de convertirListModelEnEntitiesJPA(...).______________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_TELEVERSEMENT) 
	 * d'une liste d'entities</b>.<br/>
	 * <ul>
	 * <li>chaque item de la liste est retournée 
	 * sur une ligne formatée.</li>
	 * <li>utilise le saut de la plateforme comme saut à la ligne 
	 * (<code>System.getProperty("line.separator")</code>).</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - n'affiche pas un item null dans la liste 
	 * passée en paramètre.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;SectionHitEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
			final List<SectionHitEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ISectionHit entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= entity.toString();
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_TELEVERSEMENT) 
	 * d'une liste d'objets métier</b>.<br/>
	 * <ul>
	 * <li>chaque item de la liste est retournée 
	 * sur une ligne formatée.</li>
	 * <li>utilise le saut de la plateforme comme saut à la ligne 
	 * (<code>System.getProperty("line.separator")</code>).</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - n'affiche pas un item null dans la liste 
	 * passée en paramètre.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;ISectionHit&gt; : 
	 * liste d'Objets métier.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<ISectionHit> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ISectionHit objet : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (objet != null) {
				
				final String stringformatee 
					= objet.toString();
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListObjets(...)._____________________________
	
	
	
} // FIN DE LA CLASSE SectionHitConvertisseurMetierEntity.-------------------
