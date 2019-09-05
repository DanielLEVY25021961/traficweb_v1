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
				bonneSectionHitDTO.setAbsOrigine("0001");
				bonneSectionHitDTO.setLieuDitExtremite("RD 119              ");
				bonneSectionHitDTO.setPrExtremite("021");
				bonneSectionHitDTO.setAbsExtremite("0444");
				bonneSectionHitDTO.setLieuDitComptage("PAMIERS             ");
				bonneSectionHitDTO.setPrComptage("019");
				bonneSectionHitDTO.setAbsComptage("0290");
				bonneSectionHitDTO.setLongueurSection("003444");
				bonneSectionHitDTO.setLongueurRaseCampagne("002444");
				bonneSectionHitDTO.setNumDepartementRattachement("090");
				bonneSectionHitDTO.setNumSectionRattachement("000402");
				bonneSectionHitDTO.setSensRattachement("3");
				bonneSectionHitDTO.setNumDepartementLimitrophe("140");
				bonneSectionHitDTO.setNumSectionLimitrophe("000407");
				bonneSectionHitDTO.setSensLimitrophe("3");
				bonneSectionHitDTO.setMoisSectionnement("01");
				bonneSectionHitDTO.setAnneeSectionnement("87");
				bonneSectionHitDTO.setZoneLibre2("      ");
				bonneSectionHitDTO.setMjaN("011928");
				bonneSectionHitDTO.setModeCalculN(UN_ESPACE);
				bonneSectionHitDTO.setPcPLN("057");
				bonneSectionHitDTO.setEvaluationPLN("7");
				bonneSectionHitDTO.setPcNuitAnnuelN("200");
				bonneSectionHitDTO.setIndiceFiabiliteMjaN(UN_ESPACE);
				bonneSectionHitDTO.setMjmNmois01("010631");
				bonneSectionHitDTO.setPcNuitNmois01("054");
				bonneSectionHitDTO.setMjmNmois02("012355");
				bonneSectionHitDTO.setPcNuitNmois02("105");
				bonneSectionHitDTO.setMjmNmois03("011922");
				bonneSectionHitDTO.setPcNuitNmois03("201");
				bonneSectionHitDTO.setMjmNmois04("011898");
				bonneSectionHitDTO.setPcNuitNmois04("179");
				bonneSectionHitDTO.setMjmNmois05("011531");
				bonneSectionHitDTO.setPcNuitNmois05("125");
				bonneSectionHitDTO.setMjmNmois06("012159");
				bonneSectionHitDTO.setPcNuitNmois06("078");
				bonneSectionHitDTO.setMjmNmois07("012537");
				bonneSectionHitDTO.setPcNuitNmois07("054");
				bonneSectionHitDTO.setMjmNmois08("012593");
				bonneSectionHitDTO.setPcNuitNmois08("036");
				bonneSectionHitDTO.setMjmNmois09("012346");
				bonneSectionHitDTO.setPcNuitNmois09("123");
				bonneSectionHitDTO.setMjmNmois10("012620");
				bonneSectionHitDTO.setPcNuitNmois10("045");
				bonneSectionHitDTO.setMjmNmois11("011417");
				bonneSectionHitDTO.setPcNuitNmois11("000");
				bonneSectionHitDTO.setMjmNmois12("011172");
				bonneSectionHitDTO.setPcNuitNmois12("000");
				bonneSectionHitDTO.setZoneLibre3("            ");
				bonneSectionHitDTO.setAnneeNmoins1("16");
				bonneSectionHitDTO.setMjaNmoins1("011647");
				bonneSectionHitDTO.setTypeComptageNmoins1("1");
				bonneSectionHitDTO.setModeCalculNmoins1(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins1("074");
				bonneSectionHitDTO.setEvaluationPLNmoins1("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins1("079");
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins1(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins2("15");
				bonneSectionHitDTO.setMjaNmoins2("011400");
				bonneSectionHitDTO.setTypeComptageNmoins2("1");
				bonneSectionHitDTO.setModeCalculNmoins2(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins2("123");
				bonneSectionHitDTO.setEvaluationPLNmoins2("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins2(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins2(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins3("14");
				bonneSectionHitDTO.setMjaNmoins3("011079");
				bonneSectionHitDTO.setTypeComptageNmoins3("1");
				bonneSectionHitDTO.setModeCalculNmoins3(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins3("136");
				bonneSectionHitDTO.setEvaluationPLNmoins3("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins3(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins3(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins4("13");
				bonneSectionHitDTO.setMjaNmoins4("010748");
				bonneSectionHitDTO.setTypeComptageNmoins4("1");
				bonneSectionHitDTO.setModeCalculNmoins4(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins4("154");
				bonneSectionHitDTO.setEvaluationPLNmoins4("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins4(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins4(UN_ESPACE);
				bonneSectionHitDTO.setAnneeNmoins5("12");
				bonneSectionHitDTO.setMjaNmoins5("010742");
				bonneSectionHitDTO.setTypeComptageNmoins5("1");
				bonneSectionHitDTO.setModeCalculNmoins5(UN_ESPACE);
				bonneSectionHitDTO.setPcPLNmoins5("178");
				bonneSectionHitDTO.setEvaluationPLNmoins5("7");
				bonneSectionHitDTO.setPcNuitAnnuelNmoins5(TROIS_ESPACES);
				bonneSectionHitDTO.setIndiceFiabiliteMjaNmoins5(UN_ESPACE);
				bonneSectionHitDTO.setMjmNmoins1mois01("011001");
				bonneSectionHitDTO.setPcNuitNmoins1mois01(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois02("011765");
				bonneSectionHitDTO.setPcNuitNmoins1mois02(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois03("011767");
				bonneSectionHitDTO.setPcNuitNmoins1mois03(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois04("011488");
				bonneSectionHitDTO.setPcNuitNmoins1mois04(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois05("011403");
				bonneSectionHitDTO.setPcNuitNmoins1mois05(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois06("11466");
				bonneSectionHitDTO.setPcNuitNmoins1mois06(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois07("011990");
				bonneSectionHitDTO.setPcNuitNmoins1mois07(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois08("012258");
				bonneSectionHitDTO.setPcNuitNmoins1mois08(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois09("011796");
				bonneSectionHitDTO.setPcNuitNmoins1mois09(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois10("12099");
				bonneSectionHitDTO.setPcNuitNmoins1mois10(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois11("11223");
				bonneSectionHitDTO.setPcNuitNmoins1mois11(TROIS_ESPACES);
				bonneSectionHitDTO.setMjmNmoins1mois12("011498");
				bonneSectionHitDTO.setPcNuitNmoins1mois12(TROIS_ESPACES);
				bonneSectionHitDTO.setZoneLibre4("                                   ");

			}
			
			return bonneSectionHitDTO;

		}
		
	} // Fin de getBonneSectionHitDTO().___________________________________
	
	
	
} // FIN DE LA CLASSE FactorySectionHit.-------------------------------------
