package levy.daniel.application.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;

/**
 * CLASSE FactorySectionHit :<br/>
 * Classe UTILITAIRE chargée de fournir des sections HIT pour les tests.<br/>
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
 * @since 15 août 2019
 *
 */
public final class FactorySectionHit {

	// ************************ATTRIBUTS************************************/

	/**
	 * 1 espace.
	 */
	public static final String UN_ESPACE = " ";
	
	/**
	 * 3 espaces.
	 */
	public static final String TROIS_ESPACES = "   ";
	
	/**
	 * bonne section HIT DTO avec uniquement des bonnes valeurs.
	 */
	private static ISectionHitDTO bonneSectionHitDTO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(FactorySectionHit.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.
	 */
	private FactorySectionHit() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * retourne une bonne section HIT DTO avec uniquement des bonnes valeurs.<br/>
	 *
	 * @return : ISectionHitDTO.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ISectionHitDTO getBonneSectionHitDTO() throws Exception {
		
		synchronized (FactorySectionHit.class) {
			
			if (bonneSectionHitDTO == null) {
				
				bonneSectionHitDTO = new SectionHitDTO();

				bonneSectionHitDTO.setNumDepartement("090");
				bonneSectionHitDTO.setNumSection("000402");
				bonneSectionHitDTO.setSens("2");
				bonneSectionHitDTO.setNature("1");
				bonneSectionHitDTO.setClasse("00");
				bonneSectionHitDTO.setAnneeTraitement("17");
				bonneSectionHitDTO.setZoneLibre1(UN_ESPACE);
				bonneSectionHitDTO.setNumRoute("0020");
				bonneSectionHitDTO.setIndiceNumRoute("0");
				bonneSectionHitDTO.setIndiceLettreRoute(UN_ESPACE);
				bonneSectionHitDTO.setCategorieAdminRoute("4");
				bonneSectionHitDTO.setTypeComptage("1");
				bonneSectionHitDTO.setClassementRoute("1");
				bonneSectionHitDTO.setClasseLargeurChausseeU("0");
				bonneSectionHitDTO.setClasseLargeurChausseesS("2");
				bonneSectionHitDTO.setTypeReseau("1");
				bonneSectionHitDTO.setPRoupK("1");
				bonneSectionHitDTO.setLieuDitOrigine("PAMIERS A 66        ");
				bonneSectionHitDTO.setPrOrigine("018");
				bonneSectionHitDTO.setAbsOrigine("0");
				bonneSectionHitDTO.setLieuDitExtremite("RD 119              ");
				bonneSectionHitDTO.setPrExtremite("21");
				bonneSectionHitDTO.setAbsExtremite("444");
				bonneSectionHitDTO.setLieuDitComptage("PAMIERS             ");
				bonneSectionHitDTO.setPrComptage("19");
				bonneSectionHitDTO.setAbsComptage("290");
				bonneSectionHitDTO.setLongueurSection("3444");
				bonneSectionHitDTO.setLongueurRaseCampagne("3444");
				bonneSectionHitDTO.setNumDepartementRattachement("090");
				bonneSectionHitDTO.setNumSectionRattachement("000402");
				bonneSectionHitDTO.setSensRattachement("3");
				bonneSectionHitDTO.setNumDepartementLimitrophe("000");
				bonneSectionHitDTO.setNumSectionLimitrophe("      ");
				bonneSectionHitDTO.setSensLimitrophe(UN_ESPACE);
				bonneSectionHitDTO.setMoisSectionnement("01");
				bonneSectionHitDTO.setAnneeSectionnement("87");
				bonneSectionHitDTO.setZoneLibre2("      ");
				bonneSectionHitDTO.setMjaN("11928");
				bonneSectionHitDTO.setModeCalculN(UN_ESPACE);
				bonneSectionHitDTO.setPcPLN(" 57");
				bonneSectionHitDTO.setEvaluationPLN("7");
				bonneSectionHitDTO.setPcNuitAnnuelN(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaN(UN_ESPACE);
				bonneSectionHitDTO.setMjmNmois01("10631");
				bonneSectionHitDTO.setPcNuitNmois01(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois02("12355");
				bonneSectionHitDTO.setPcNuitNmois02(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois03("11922");
				bonneSectionHitDTO.setPcNuitNmois03(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois04("11898");
				bonneSectionHitDTO.setPcNuitNmois04(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois05("11531");
				bonneSectionHitDTO.setPcNuitNmois05(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois06("12159");
				bonneSectionHitDTO.setPcNuitNmois06(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois07("12537");
				bonneSectionHitDTO.setPcNuitNmois07(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois08("12593");
				bonneSectionHitDTO.setPcNuitNmois08(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois09("12346");
				bonneSectionHitDTO.setPcNuitNmois09(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois10("12620");
				bonneSectionHitDTO.setPcNuitNmois10(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois11("11417");
				bonneSectionHitDTO.setPcNuitNmois11(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmois12("11172");
				bonneSectionHitDTO.setPcNuitNmois12(TROIS_ESPACES);
				bonneSectionHitDTO.setZoneLibre3("            ");
				bonneSectionHitDTO.setAnneeNmoins1("16");
				bonneSectionHitDTO.setMjaNmoins1("11647");
				bonneSectionHitDTO.setTypeComptageNmoins1("1");
				bonneSectionHitDTO.setModeCalculNmoins1(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins1(TROIS_ESPACES);
				bonneSectionHitDTO.setEvaluationPLNmoins1("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins1(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins1(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins2("15");
				bonneSectionHitDTO.setMjaNmoins2("11400");
				bonneSectionHitDTO.setTypeComptageNmoins2("1");
				bonneSectionHitDTO.setModeCalculNmoins2(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins2(TROIS_ESPACES);
				bonneSectionHitDTO.setEvaluationPLNmoins2("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins2(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins2(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins3("14");
				bonneSectionHitDTO.setMjaNmoins3("11079");
				bonneSectionHitDTO.setTypeComptageNmoins3("1");
				bonneSectionHitDTO.setModeCalculNmoins3(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins3(TROIS_ESPACES);
				bonneSectionHitDTO.setEvaluationPLNmoins3("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins3(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins3(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins4("13");
				bonneSectionHitDTO.setMjaNmoins4("10748");
				bonneSectionHitDTO.setTypeComptageNmoins4("1");
				bonneSectionHitDTO.setModeCalculNmoins4(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins4(TROIS_ESPACES);
				bonneSectionHitDTO.setEvaluationPLNmoins4("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins4(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins4(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins5("12");
				bonneSectionHitDTO.setMjaNmoins5("10742");
				bonneSectionHitDTO.setTypeComptageNmoins5("1");
				bonneSectionHitDTO.setModeCalculNmoins5(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins5(TROIS_ESPACES);
				bonneSectionHitDTO.setEvaluationPLNmoins5("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins5(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins5(UN_ESPACE);
				bonneSectionHitDTO.setMjmNmoins1mois01("11001");
				bonneSectionHitDTO.setPcNuitNmoins1mois01(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois02("11765");
				bonneSectionHitDTO.setPcNuitNmoins1mois02(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois03("11767");
				bonneSectionHitDTO.setPcNuitNmoins1mois03(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois04("11488");
				bonneSectionHitDTO.setPcNuitNmoins1mois04(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois05("11403");
				bonneSectionHitDTO.setPcNuitNmoins1mois05(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois06("11466");
				bonneSectionHitDTO.setPcNuitNmoins1mois06(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois07("11990");
				bonneSectionHitDTO.setPcNuitNmoins1mois07(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois08("12258");
				bonneSectionHitDTO.setPcNuitNmoins1mois08(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois09("11796");
				bonneSectionHitDTO.setPcNuitNmoins1mois09(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois10("12099");
				bonneSectionHitDTO.setPcNuitNmoins1mois10(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois11("11223");
				bonneSectionHitDTO.setPcNuitNmoins1mois11(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois12("11498");
				bonneSectionHitDTO.setPcNuitNmoins1mois12(TROIS_ESPACES);
				bonneSectionHitDTO.setZoneLibre4("                                   ");

			}
			
			return bonneSectionHitDTO;

		}
		
	} // Fin de getBonneSectionHitDTO().___________________________________
	
	
	
} // FIN DE LA CLASSE FactorySectionHit.-------------------------------------
