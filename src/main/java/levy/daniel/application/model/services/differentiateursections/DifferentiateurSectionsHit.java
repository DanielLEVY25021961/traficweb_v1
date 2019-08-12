package levy.daniel.application.model.services.differentiateursections;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.utilitaires.differentiateur.DifferenceChamp;

/**
 * CLASSE DifferentiateurSectionsHit :<br/>
 * Utilitaire chargé de fournir les différences champ par champ 
 * entre deux SECTIONS HIT.<br/>
 * Le résultat est fourni via la méthode statique 
 * <code><b>differencier(section1, section2)</b></code> sous forme de 
 * Map&lt;Integer,DifferenceChamp&gt; avec :
 * <ul>
 * <li>Integer : le numéro d'ordre du champ dans une section HIT.</li>
 * <li>{@link DifferenceChamp} : Encapsulation contenant le nom du champ 
 * et les valeurs prises dans chaque section HIT.</li>
 * </ul>
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
 * @since 9 août 2019
 *
 */
public final class DifferentiateurSectionsHit {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(DifferentiateurSectionsHit.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private DifferentiateurSectionsHit() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * stocke les différences champ par champ entre 2 Sections HIT
	 * et retourne le résultat sour forme de 
	 * Map&lt;Integer,DifferenceChamp&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ dans une section HIT.</li>
	 * <li>DifferenceChamp : Encapsulation contenant le nom du champ 
	 * et les valeurs prises dans chaque section HIT.</li>
	 * </ul>
	 * - La Map retournée n'est jamais null. TESTER SI VIDE.<br/>
	 * - retourne null si au moins une section 
	 * (pSection1 ou pSection2) est null.<br/>
	 * <br/>
	 *
	 * @param pSection1 : ISectionHit : 1ère section HIT.
	 * @param pSection2 : ISectionHit : 2ème section HIT.
	 * 
	 * @return : Map&lt;Integer,DifferenceChamp&gt;.<br/>
	 */
	public static Map<Integer, DifferenceChamp> differencier(
			final ISectionHit pSection1
				, final ISectionHit pSection2) {
		
		synchronized (DifferentiateurSectionsHit.class) {
			
			/* retourne null si une section est null. */
			if (pSection1 == null || pSection2 == null) {
				return null;
			}
			
			final Map<Integer, DifferenceChamp> resultat 
				= new ConcurrentHashMap<Integer, DifferenceChamp>();
			
			/* 1 - numDepartement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumDepartement()
						, pSection2.getNumDepartement())) {
				
				final DifferenceChamp<String> diff 
					= new DifferenceChamp<String>(
							"numDepartement"
							, pSection1.getNumDepartement()
							, pSection2.getNumDepartement());
				
				resultat.put(1, diff);
			}
			
			/* 2 - numSection. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumSection()
						, pSection2.getNumSection())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numSection"
						, pSection1.getNumSection()
						, pSection2.getNumSection());
				
				resultat.put(2, diff);
				
			}
			
			/* 3 - sens. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getSens()
						, pSection2.getSens())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"sens"
						, pSection1.getSens()
						, pSection2.getSens());
				
				resultat.put(3, diff);
				
			}
			
			/* 4 - nature. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNature()
						, pSection2.getNature())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"nature"
						, pSection1.getNature()
						, pSection2.getNature());
				
				resultat.put(4, diff);
				
			}
			
			/* 5 - classe. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getClasse()
						, pSection2.getClasse())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"classe"
						, pSection1.getClasse()
						, pSection2.getClasse());
				
				resultat.put(5, diff);
				
			}
			
			/* 6 - anneeTraitement. */
			if (pSection1.getAnneeTraitement() != null) {
				
				if (!pSection1.getAnneeTraitement()
						.equals(pSection2.getAnneeTraitement())) {
					
					final DifferenceChamp<LocalDate> diff 
						= new DifferenceChamp<LocalDate>(
								"anneeTraitement"
								, pSection1.getAnneeTraitement()
								, pSection2.getAnneeTraitement());
					
					resultat.put(6, diff);
					
				}

			} else {
				
				if (pSection2.getAnneeTraitement() != null) {
					
					final DifferenceChamp<LocalDate> diff 
						= new DifferenceChamp<LocalDate>(
								"anneeTraitement"
								, null
								, pSection2.getAnneeTraitement());
					
					resultat.put(6, diff);

				}
			}
			
			/* 7 - zoneLibre1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getZoneLibre1()
						, pSection2.getZoneLibre1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"zoneLibre1"
						, pSection1.getZoneLibre1()
						, pSection2.getZoneLibre1());
				
				resultat.put(7, diff);
				
			}
			
			/* 8 - numRoute. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumRoute()
						, pSection2.getNumRoute())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numRoute"
						, pSection1.getNumRoute()
						, pSection2.getNumRoute());
				
				resultat.put(8, diff);
				
			}
			
			/* 9 - indiceNumRoute. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceNumRoute()
						, pSection2.getIndiceNumRoute())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"indiceNumRoute"
						, pSection1.getIndiceNumRoute()
						, pSection2.getIndiceNumRoute());
				
				resultat.put(9, diff);
				
			}
			
			/* 10 - indiceLettreRoute. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceLettreRoute()
						, pSection2.getIndiceLettreRoute())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"indiceLettreRoute"
						, pSection1.getIndiceLettreRoute()
						, pSection2.getIndiceLettreRoute());
				
				resultat.put(10, diff);
				
			}
			
			/* 11 - categorieAdminRoute. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getCategorieAdminRoute()
						, pSection2.getCategorieAdminRoute())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"categorieAdminRoute"
						, pSection1.getCategorieAdminRoute()
						, pSection2.getCategorieAdminRoute());
				
				resultat.put(11, diff);
				
			}
			
			/* 12 - typeComptage. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptage()
						, pSection2.getTypeComptage())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"typeComptage"
						, pSection1.getTypeComptage()
						, pSection2.getTypeComptage());
				
				resultat.put(12, diff);
				
			}
			
			/* 13 - classementRoute. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getClassementRoute()
						, pSection2.getClassementRoute())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"classementRoute"
						, pSection1.getClassementRoute()
						, pSection2.getClassementRoute());
				
				resultat.put(13, diff);
				
			}
			
			/* 14 - classeLargeurChausseeU. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getClasseLargeurChausseeU()
						, pSection2.getClasseLargeurChausseeU())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"classeLargeurChausseeU"
						, pSection1.getClasseLargeurChausseeU()
						, pSection2.getClasseLargeurChausseeU());
				
				resultat.put(14, diff);
				
			}
			
			/* 15 - classeLargeurChausseesS. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getClasseLargeurChausseesS()
						, pSection2.getClasseLargeurChausseesS())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"classeLargeurChausseesS"
						, pSection1.getClasseLargeurChausseesS()
						, pSection2.getClasseLargeurChausseesS());
				
				resultat.put(15, diff);
				
			}
			
			/* 16 - typeReseau. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeReseau()
						, pSection2.getTypeReseau())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"typeReseau"
						, pSection1.getTypeReseau()
						, pSection2.getTypeReseau());
				
				resultat.put(16, diff);
				
			}
			
			/* 17 - pRoupK. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getpRoupK()
						, pSection2.getpRoupK())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pRoupK"
						, pSection1.getpRoupK()
						, pSection2.getpRoupK());
				
				resultat.put(17, diff);
				
			}
			
			/* 18 - lieuDitOrigine. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getLieuDitOrigine()
						, pSection2.getLieuDitOrigine())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"lieuDitOrigine"
						, pSection1.getLieuDitOrigine()
						, pSection2.getLieuDitOrigine());
				
				resultat.put(18, diff);
				
			}
			
			/* 19 - prOrigine. */
			if (pSection1.getPrOrigine() != null) {
				
				if (!pSection1.getPrOrigine()
						.equals(pSection2.getPrOrigine())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prOrigine"
							, pSection1.getPrOrigine()
							, pSection2.getPrOrigine());
					
					resultat.put(19, diff);
					
				}
				
			} else {
				
				if (pSection2.getPrOrigine() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prOrigine"
							, null
							, pSection2.getPrOrigine());
					
					resultat.put(19, diff);
				}
			}
						
			/* 20 - absOrigine. */
			if (pSection1.getAbsOrigine() != null) {
				
				if (!pSection1.getAbsOrigine()
						.equals(pSection2.getAbsOrigine())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absOrigine"
							, pSection1.getAbsOrigine()
							, pSection2.getAbsOrigine());
					
					resultat.put(20, diff);
					
				}

			} else {
				
				if (pSection2.getAbsOrigine() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absOrigine"
							, null
							, pSection2.getAbsOrigine());
					
					resultat.put(20, diff);
				}
			}
			
			/* 21 - lieuDitExtremite. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getLieuDitExtremite()
						, pSection2.getLieuDitExtremite())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"lieuDitExtremite"
						, pSection1.getLieuDitExtremite()
						, pSection2.getLieuDitExtremite());
				
				resultat.put(21, diff);
				
			}
			
			/* 22 - prExtremite. */
			if (pSection1.getPrExtremite() != null) {
				
				if (!pSection1.getPrExtremite()
						.equals(pSection2.getPrExtremite())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prExtremite"
							, pSection1.getPrExtremite()
							, pSection2.getPrExtremite());
					
					resultat.put(22, diff);
					
				}
				
			} else {
				
				if (pSection2.getPrExtremite() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prExtremite"
							, null
							, pSection2.getPrExtremite());
					
					resultat.put(22, diff);

				}
			}
			
			
			/* 23 - absExtremite. */
			if (pSection1.getAbsExtremite() != null) {
				
				if (!pSection1.getAbsExtremite()
						.equals(pSection2.getAbsExtremite())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absExtremite"
							, pSection1.getAbsExtremite()
							, pSection2.getAbsExtremite());
					
					resultat.put(23, diff);
					
				}

			} else {
				
				if (pSection2.getAbsExtremite() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absExtremite"
							, null
							, pSection2.getAbsExtremite());
					
					resultat.put(23, diff);

				}
			}
			
			/* 24 - lieuDitComptage. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getLieuDitComptage()
						, pSection2.getLieuDitComptage())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"lieuDitComptage"
						, pSection1.getLieuDitComptage()
						, pSection2.getLieuDitComptage());
				
				resultat.put(24, diff);
				
			}
			
			/* 25 - prComptage. */
			if (pSection1.getPrComptage() != null) {
				
				if (!pSection1.getPrComptage()
						.equals(pSection2.getPrComptage())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prComptage"
							, pSection1.getPrComptage()
							, pSection2.getPrComptage());
					
					resultat.put(25, diff);
					
				}
				
			} else {
				
				if (pSection2.getPrComptage() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"prComptage"
							, null
							, pSection2.getPrComptage());
					
					resultat.put(25, diff);

				}
			}
			
			/* 26 - absComptage. */
			if (pSection1.getAbsComptage() != null) {
				
				if (!pSection1.getAbsComptage()
						.equals(pSection2.getAbsComptage())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absComptage"
							, pSection1.getAbsComptage()
							, pSection2.getAbsComptage());
					
					resultat.put(26, diff);
					
				}

			} else {
				
				if (pSection2.getAbsComptage() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"absComptage"
							, null
							, pSection2.getAbsComptage());
					
					resultat.put(26, diff);

				}
			}
			
			/* 27 - longueurSection. */
			if (pSection1.getLongueurSection() != null) {
				
				if (!pSection1.getLongueurSection()
						.equals(pSection2.getLongueurSection())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"longueurSection"
							, pSection1.getLongueurSection()
							, pSection2.getLongueurSection());
					
					resultat.put(27, diff);
					
				}

			} else {
				
				if (pSection2.getLongueurSection() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"longueurSection"
							, null
							, pSection2.getLongueurSection());
					
					resultat.put(27, diff);

				}
			}
			
			/* 28 - longueurRaseCampagne. */
			if (pSection1.getLongueurRaseCampagne() != null) {
				
				if (!pSection1.getLongueurRaseCampagne()
						.equals(pSection2.getLongueurRaseCampagne())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"longueurRaseCampagne"
							, pSection1.getLongueurRaseCampagne()
							, pSection2.getLongueurRaseCampagne());
					
					resultat.put(28, diff);
					
				}

			} else {
				
				if (pSection2.getLongueurRaseCampagne() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"longueurRaseCampagne"
							, null
							, pSection2.getLongueurRaseCampagne());
					
					resultat.put(28, diff);

				}
			}
			
			/* 29 - numDepartementRattachement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumDepartementRattachement()
						, pSection2.getNumDepartementRattachement())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numDepartementRattachement"
						, pSection1.getNumDepartementRattachement()
						, pSection2.getNumDepartementRattachement());
				
				resultat.put(29, diff);
				
			}
			
			/* 30 - numSectionRattachement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumSectionRattachement()
						, pSection2.getNumSectionRattachement())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numSectionRattachement"
						, pSection1.getNumSectionRattachement()
						, pSection2.getNumSectionRattachement());
				
				resultat.put(30, diff);
				
			}
			
			/* 31 - sensRattachement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getSensRattachement()
						, pSection2.getSensRattachement())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"sensRattachement"
						, pSection1.getSensRattachement()
						, pSection2.getSensRattachement());
				
				resultat.put(31, diff);
				
			}
			
			/* 32 - numDepartementLimitrophe. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumDepartementLimitrophe()
						, pSection2.getNumDepartementLimitrophe())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numDepartementLimitrophe"
						, pSection1.getNumDepartementLimitrophe()
						, pSection2.getNumDepartementLimitrophe());
				
				resultat.put(32, diff);
				
			}
			
			/* 33 - numSectionLimitrophe. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getNumSectionLimitrophe()
						, pSection2.getNumSectionLimitrophe())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"numSectionLimitrophe"
						, pSection1.getNumSectionLimitrophe()
						, pSection2.getNumSectionLimitrophe());
				
				resultat.put(33, diff);
				
			}
			
			/* 34 - sensLimitrophe. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getSensLimitrophe()
						, pSection2.getSensLimitrophe())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"sensLimitrophe"
						, pSection1.getSensLimitrophe()
						, pSection2.getSensLimitrophe());
				
				resultat.put(34, diff);
				
			}
			
			/* 35 - moisSectionnement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getMoisSectionnement()
						, pSection2.getMoisSectionnement())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"moisSectionnement"
						, pSection1.getMoisSectionnement()
						, pSection2.getMoisSectionnement());
				
				resultat.put(35, diff);
				
			}
			
			/* 36 - anneeSectionnement. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getAnneeSectionnement()
						, pSection2.getAnneeSectionnement())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"anneeSectionnement"
						, pSection1.getAnneeSectionnement()
						, pSection2.getAnneeSectionnement());
				
				resultat.put(36, diff);
				
			}
			
			/* 37 - zoneLibre2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getZoneLibre2()
						, pSection2.getZoneLibre2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"zoneLibre2"
						, pSection1.getZoneLibre2()
						, pSection2.getZoneLibre2());
				
				resultat.put(37, diff);
				
			}
			
			/* 38 - mjaN. */
			if (pSection1.getMjaN() != null) {
				
				if (!pSection1.getMjaN()
						.equals(pSection2.getMjaN())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaN"
							, pSection1.getMjaN()
							, pSection2.getMjaN());
					
					resultat.put(38, diff);
				}
				
			} else {
				
				if (pSection2.getMjaN() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaN"
							, null
							, pSection2.getMjaN());
					
					resultat.put(38, diff);

				}
			}
			
			/* 39 - modeCalculN. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculN()
						, pSection2.getModeCalculN())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"modeCalculN"
						, pSection1.getModeCalculN()
						, pSection2.getModeCalculN());
				
				resultat.put(39, diff);
				
			}
			
			/* 40 - pcPLN. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLN()
						, pSection2.getPcPLN())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcPLN"
						, pSection1.getPcPLN()
						, pSection2.getPcPLN());
				
				resultat.put(40, diff);
				
			}
			
			/* 41 - evaluationPLN. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLN()
						, pSection2.getEvaluationPLN())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"evaluationPLN"
						, pSection1.getEvaluationPLN()
						, pSection2.getEvaluationPLN());
				
				resultat.put(41, diff);
				
			}
			
			/* 42 - pcNuitAnnuelN. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelN()
						, pSection2.getPcNuitAnnuelN())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitAnnuelN"
						, pSection1.getPcNuitAnnuelN()
						, pSection2.getPcNuitAnnuelN());
				
				resultat.put(42, diff);
				
			}
			
			/* 43 - indiceFiabiliteMjaN. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaN()
						, pSection2.getIndiceFiabiliteMjaN())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"indiceFiabiliteMjaN"
						, pSection1.getIndiceFiabiliteMjaN()
						, pSection2.getIndiceFiabiliteMjaN());
				
				resultat.put(43, diff);
				
			}

			/* 44 - mjmNmois01. */
			if (pSection1.getMjmNmois01() != null) {
				
				if (!pSection1.getMjmNmois01()
						.equals(pSection2.getMjmNmois01())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois01"
							, pSection1.getMjmNmois01()
							, pSection2.getMjmNmois01());
					
					resultat.put(44, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois01() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois01"
							, null
							, pSection2.getMjmNmois01());
					
					resultat.put(44, diff);

				}
			}
			
			/* 45 - pcNuitNmois01. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois01()
						, pSection2.getPcNuitNmois01())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois01"
						, pSection1.getPcNuitNmois01()
						, pSection2.getPcNuitNmois01());
				
				resultat.put(45, diff);
				
			}

			/* 46 - mjmNmois02. */
			if (pSection1.getMjmNmois02() != null) {
				
				if (!pSection1.getMjmNmois02()
						.equals(pSection2.getMjmNmois02())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois02"
							, pSection1.getMjmNmois02()
							, pSection2.getMjmNmois02());
					
					resultat.put(46, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois02() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois02"
							, null
							, pSection2.getMjmNmois02());
					
					resultat.put(46, diff);

				}
			}
			
			/* 47 - pcNuitNmois02. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois02()
						, pSection2.getPcNuitNmois02())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois02"
						, pSection1.getPcNuitNmois02()
						, pSection2.getPcNuitNmois02());
				
				resultat.put(47, diff);
				
			}

			/* 48 - mjmNmois03. */
			if (pSection1.getMjmNmois03() != null) {
				
				if (!pSection1.getMjmNmois03()
						.equals(pSection2.getMjmNmois03())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois03"
							, pSection1.getMjmNmois03()
							, pSection2.getMjmNmois03());
					
					resultat.put(48, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois03() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois03"
							, null
							, pSection2.getMjmNmois03());
					
					resultat.put(48, diff);

				}
			}
			
			/* 49 - pcNuitNmois03. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois03()
						, pSection2.getPcNuitNmois03())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois03"
						, pSection1.getPcNuitNmois03()
						, pSection2.getPcNuitNmois03());
				
				resultat.put(49, diff);
				
			}

			/* 50 - mjmNmois04. */
			if (pSection1.getMjmNmois04() != null) {
				
				if (!pSection1.getMjmNmois04()
						.equals(pSection2.getMjmNmois04())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois04"
							, pSection1.getMjmNmois04()
							, pSection2.getMjmNmois04());
					
					resultat.put(50, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois04() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois04"
							, null
							, pSection2.getMjmNmois04());
					
					resultat.put(50, diff);

				}
			}
			
			/* 51 - pcNuitNmois04. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois04()
						, pSection2.getPcNuitNmois04())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois04"
						, pSection1.getPcNuitNmois04()
						, pSection2.getPcNuitNmois04());
				
				resultat.put(51, diff);
				
			}

			/* 52 - mjmNmois05. */
			if (pSection1.getMjmNmois05() != null) {
				
				if (!pSection1.getMjmNmois05()
						.equals(pSection2.getMjmNmois05())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois05"
							, pSection1.getMjmNmois05()
							, pSection2.getMjmNmois05());
					
					resultat.put(52, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois05() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois05"
							, null
							, pSection2.getMjmNmois05());
					
					resultat.put(52, diff);

				}
			}
			
			/* 53 - pcNuitNmois05. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois05()
						, pSection2.getPcNuitNmois05())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois05"
						, pSection1.getPcNuitNmois05()
						, pSection2.getPcNuitNmois05());
				
				resultat.put(53, diff);
				
			}

			/* 54 - mjmNmois06. */
			if (pSection1.getMjmNmois06() != null) {
				
				if (!pSection1.getMjmNmois06()
						.equals(pSection2.getMjmNmois06())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois06"
							, pSection1.getMjmNmois06()
							, pSection2.getMjmNmois06());
					
					resultat.put(54, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois06() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois06"
							, null
							, pSection2.getMjmNmois06());
					
					resultat.put(54, diff);

				}
			}
			
			/* 55 - pcNuitNmois06. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois06()
						, pSection2.getPcNuitNmois06())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois06"
						, pSection1.getPcNuitNmois06()
						, pSection2.getPcNuitNmois06());
				
				resultat.put(55, diff);
				
			}

			/* 56 - mjmNmois07. */
			if (pSection1.getMjmNmois07() != null) {
				
				if (!pSection1.getMjmNmois07()
						.equals(pSection2.getMjmNmois07())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois07"
							, pSection1.getMjmNmois07()
							, pSection2.getMjmNmois07());
					
					resultat.put(56, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois07() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois07"
							, null
							, pSection2.getMjmNmois07());
					
					resultat.put(56, diff);

				}
			}
			
			/* 57 - pcNuitNmois07. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois07()
						, pSection2.getPcNuitNmois07())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois07"
						, pSection1.getPcNuitNmois07()
						, pSection2.getPcNuitNmois07());
				
				resultat.put(57, diff);
				
			}

			/* 58 - mjmNmois08. */
			if (pSection1.getMjmNmois08() != null) {
				
				if (!pSection1.getMjmNmois08()
						.equals(pSection2.getMjmNmois08())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois08"
							, pSection1.getMjmNmois08()
							, pSection2.getMjmNmois08());
					
					resultat.put(58, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois08() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois08"
							, null
							, pSection2.getMjmNmois08());
					
					resultat.put(58, diff);

				}
			}
			
			/* 59 - pcNuitNmois08. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois08()
						, pSection2.getPcNuitNmois08())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois08"
						, pSection1.getPcNuitNmois08()
						, pSection2.getPcNuitNmois08());
				
				resultat.put(59, diff);
				
			}

			/* 60 - mjmNmois09. */
			if (pSection1.getMjmNmois09() != null) {
				
				if (!pSection1.getMjmNmois09()
						.equals(pSection2.getMjmNmois09())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois09"
							, pSection1.getMjmNmois09()
							, pSection2.getMjmNmois09());
					
					resultat.put(60, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois09() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois09"
							, null
							, pSection2.getMjmNmois09());
					
					resultat.put(60, diff);

				}
			}
			
			/* 61 - pcNuitNmois09. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois09()
						, pSection2.getPcNuitNmois09())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois09"
						, pSection1.getPcNuitNmois09()
						, pSection2.getPcNuitNmois09());
				
				resultat.put(61, diff);
				
			}

			/* 62 - mjmNmois10. */
			if (pSection1.getMjmNmois10() != null) {
				
				if (!pSection1.getMjmNmois10()
						.equals(pSection2.getMjmNmois10())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois10"
							, pSection1.getMjmNmois10()
							, pSection2.getMjmNmois10());
					
					resultat.put(62, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois10() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois10"
							, null
							, pSection2.getMjmNmois10());
					
					resultat.put(62, diff);

				}
			}
			
			/* 63 - pcNuitNmois10. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois10()
						, pSection2.getPcNuitNmois10())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois10"
						, pSection1.getPcNuitNmois10()
						, pSection2.getPcNuitNmois10());
				
				resultat.put(63, diff);
				
			}

			/* 64 - mjmNmois11. */
			if (pSection1.getMjmNmois11() != null) {
				
				if (!pSection1.getMjmNmois11()
						.equals(pSection2.getMjmNmois11())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois11"
							, pSection1.getMjmNmois11()
							, pSection2.getMjmNmois11());
					
					resultat.put(64, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois11() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois11"
							, null
							, pSection2.getMjmNmois11());
					
					resultat.put(64, diff);

				}
			}
			
			/* 65 - pcNuitNmois11. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois11()
						, pSection2.getPcNuitNmois11())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois11"
						, pSection1.getPcNuitNmois11()
						, pSection2.getPcNuitNmois11());
				
				resultat.put(65, diff);
				
			}

			/* 66 - mjmNmois12. */
			if (pSection1.getMjmNmois12() != null) {
				
				if (!pSection1.getMjmNmois12()
						.equals(pSection2.getMjmNmois12())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois12"
							, pSection1.getMjmNmois12()
							, pSection2.getMjmNmois12());
					
					resultat.put(66, diff);
					
				}
				
			} else {
				
				if (pSection2.getMjmNmois12() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmois12"
							, null
							, pSection2.getMjmNmois12());
					
					resultat.put(66, diff);

				}
			}
			
			/* 67 - pcNuitNmois12. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmois12()
						, pSection2.getPcNuitNmois12())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"pcNuitNmois12"
						, pSection1.getPcNuitNmois12()
						, pSection2.getPcNuitNmois12());
				
				resultat.put(67, diff);
				
			}
			
			/* 68 - zoneLibre3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getZoneLibre3()
						, pSection2.getZoneLibre3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>(
						"zoneLibre3"
						, pSection1.getZoneLibre3()
						, pSection2.getZoneLibre3());
				
				resultat.put(68, diff);
				
			}
			
			/* 69 - anneeNmoins1. */
			if (pSection1.getAnneeNmoins1() != null) {
				
				if (!pSection1.getAnneeNmoins1()
						.equals(pSection2.getAnneeNmoins1())) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins1"
							, pSection1.getAnneeNmoins1()
							, pSection2.getAnneeNmoins1());
					
					resultat.put(69, diff);
				}
				
			} else {
				
				if (pSection2.getAnneeNmoins1() != null) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins1"
							, null
							, pSection2.getAnneeNmoins1());
					
					resultat.put(69, diff);

				}
			}
			
			/* 70 - mjaNmoins1. */
			if (pSection1.getMjaNmoins1() != null) {
				
				if (!pSection1.getMjaNmoins1()
						.equals(pSection2.getMjaNmoins1())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins1"
							, pSection1.getMjaNmoins1()
							, pSection2.getMjaNmoins1());
					
					resultat.put(70, diff);
				}
				
			} else {
				
				if (pSection2.getMjaNmoins1() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins1"
							, null
							, pSection2.getMjaNmoins1());
					
					resultat.put(70, diff);
					
				}
			}
			
			/* 71 - typeComptageNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptageNmoins1()
						, pSection2.getTypeComptageNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("typeComptageNmoins1"
						, pSection1.getTypeComptageNmoins1()
						, pSection2.getTypeComptageNmoins1());
				
				resultat.put(71, diff);
				
			}
			
			/* 72 - modeCalculNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculNmoins1()
						, pSection2.getModeCalculNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("modeCalculNmoins1"
						, pSection1.getModeCalculNmoins1()
						, pSection2.getModeCalculNmoins1());
				
				resultat.put(72, diff);
				
			}
			
			/* 73 - pcPLNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLNmoins1()
						, pSection2.getPcPLNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcPLNmoins1"
						, pSection1.getPcPLNmoins1()
						, pSection2.getPcPLNmoins1());
				
				resultat.put(73, diff);
				
			}
			
			/* 74 - evaluationPLNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLNmoins1()
						, pSection2.getEvaluationPLNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("evaluationPLNmoins1"
						, pSection1.getEvaluationPLNmoins1()
						, pSection2.getEvaluationPLNmoins1());
				
				resultat.put(74, diff);
				
			}
			
			/* 75 - pcNuitAnnuelNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelNmoins1()
						, pSection2.getPcNuitAnnuelNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitAnnuelNmoins1"
						, pSection1.getPcNuitAnnuelNmoins1()
						, pSection2.getPcNuitAnnuelNmoins1());
				
				resultat.put(75, diff);
				
			}
			
			/* 76 - indiceFiabiliteMjaNmoins1. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaNmoins1()
						, pSection2.getIndiceFiabiliteMjaNmoins1())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("indiceFiabiliteMjaNmoins1"
						, pSection1.getIndiceFiabiliteMjaNmoins1()
						, pSection2.getIndiceFiabiliteMjaNmoins1());
				
				resultat.put(76, diff);
				
			}
			
			/* 77 - anneeNmoins2. */
			if (pSection1.getAnneeNmoins2() != null) {
				
				if (!pSection1.getAnneeNmoins2()
						.equals(pSection2.getAnneeNmoins2())) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins2"
							, pSection1.getAnneeNmoins2()
							, pSection2.getAnneeNmoins2());
					
					resultat.put(77, diff);
				}
				
			} else {
				
				if (pSection2.getAnneeNmoins2() != null) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins2"
							, null
							, pSection2.getAnneeNmoins2());
					
					resultat.put(77, diff);

				}
			}
			
			/* 78 - mjaNmoins2. */
			if (pSection1.getMjaNmoins2() != null) {
				
				if (!pSection1.getMjaNmoins2()
						.equals(pSection2.getMjaNmoins2())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins2"
							, pSection1.getMjaNmoins2()
							, pSection2.getMjaNmoins2());
					
					resultat.put(78, diff);
				}
				
			} else {
				
				if (pSection2.getMjaNmoins2() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins2"
							, null
							, pSection2.getMjaNmoins2());
					
					resultat.put(78, diff);
					
				}
			}
			
			/* 79 - typeComptageNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptageNmoins2()
						, pSection2.getTypeComptageNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("typeComptageNmoins2"
						, pSection1.getTypeComptageNmoins2()
						, pSection2.getTypeComptageNmoins2());
				
				resultat.put(79, diff);
				
			}
			
			/* 80 - modeCalculNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculNmoins2()
						, pSection2.getModeCalculNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("modeCalculNmoins2"
						, pSection1.getModeCalculNmoins2()
						, pSection2.getModeCalculNmoins2());
				
				resultat.put(80, diff);
				
			}
			
			/* 81 - pcPLNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLNmoins2()
						, pSection2.getPcPLNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcPLNmoins2"
						, pSection1.getPcPLNmoins2()
						, pSection2.getPcPLNmoins2());
				
				resultat.put(81, diff);
				
			}
			
			/* 82 - evaluationPLNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLNmoins2()
						, pSection2.getEvaluationPLNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("evaluationPLNmoins2"
						, pSection1.getEvaluationPLNmoins2()
						, pSection2.getEvaluationPLNmoins2());
				
				resultat.put(82, diff);
				
			}
			
			/* 83 - pcNuitAnnuelNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelNmoins2()
						, pSection2.getPcNuitAnnuelNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitAnnuelNmoins2"
						, pSection1.getPcNuitAnnuelNmoins2()
						, pSection2.getPcNuitAnnuelNmoins2());
				
				resultat.put(83, diff);
				
			}
			
			/* 84 - indiceFiabiliteMjaNmoins2. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaNmoins2()
						, pSection2.getIndiceFiabiliteMjaNmoins2())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("indiceFiabiliteMjaNmoins2"
						, pSection1.getIndiceFiabiliteMjaNmoins2()
						, pSection2.getIndiceFiabiliteMjaNmoins2());
				
				resultat.put(84, diff);
				
			}
			
			/* 85 - anneeNmoins3. */
			if (pSection1.getAnneeNmoins3() != null) {
				
				if (!pSection1.getAnneeNmoins3()
						.equals(pSection2.getAnneeNmoins3())) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins3"
							, pSection1.getAnneeNmoins3()
							, pSection2.getAnneeNmoins3());
					
					resultat.put(85, diff);
				}
				
			} else {
				
				if (pSection2.getAnneeNmoins3() != null) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins3"
							, null
							, pSection2.getAnneeNmoins3());
					
					resultat.put(85, diff);

				}
			}
			
			/* 86 - mjaNmoins3. */
			if (pSection1.getMjaNmoins3() != null) {
				
				if (!pSection1.getMjaNmoins3()
						.equals(pSection2.getMjaNmoins3())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins3"
							, pSection1.getMjaNmoins3()
							, pSection2.getMjaNmoins3());
					
					resultat.put(86, diff);
				}
				
			} else {
				
				if (pSection2.getMjaNmoins3() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins3"
							, null
							, pSection2.getMjaNmoins3());
					
					resultat.put(86, diff);
					
				}
			}
			
			/* 87 - typeComptageNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptageNmoins3()
						, pSection2.getTypeComptageNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("typeComptageNmoins3"
						, pSection1.getTypeComptageNmoins3()
						, pSection2.getTypeComptageNmoins3());
				
				resultat.put(87, diff);
				
			}
			
			/* 88 - modeCalculNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculNmoins3()
						, pSection2.getModeCalculNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("modeCalculNmoins3"
						, pSection1.getModeCalculNmoins3()
						, pSection2.getModeCalculNmoins3());
				
				resultat.put(88, diff);
				
			}
			
			/* 89 - pcPLNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLNmoins3()
						, pSection2.getPcPLNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcPLNmoins3"
						, pSection1.getPcPLNmoins3()
						, pSection2.getPcPLNmoins3());
				
				resultat.put(89, diff);
				
			}
			
			/* 90 - evaluationPLNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLNmoins3()
						, pSection2.getEvaluationPLNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("evaluationPLNmoins3"
						, pSection1.getEvaluationPLNmoins3()
						, pSection2.getEvaluationPLNmoins3());
				
				resultat.put(90, diff);
				
			}
			
			/* 91 - pcNuitAnnuelNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelNmoins3()
						, pSection2.getPcNuitAnnuelNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitAnnuelNmoins3"
						, pSection1.getPcNuitAnnuelNmoins3()
						, pSection2.getPcNuitAnnuelNmoins3());
				
				resultat.put(91, diff);
				
			}
			
			/* 92 - indiceFiabiliteMjaNmoins3. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaNmoins3()
						, pSection2.getIndiceFiabiliteMjaNmoins3())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("indiceFiabiliteMjaNmoins3"
						, pSection1.getIndiceFiabiliteMjaNmoins3()
						, pSection2.getIndiceFiabiliteMjaNmoins3());
				
				resultat.put(92, diff);
				
			}
			
			/* 93 - anneeNmoins4. */
			if (pSection1.getAnneeNmoins4() != null) {
				
				if (!pSection1.getAnneeNmoins4()
						.equals(pSection2.getAnneeNmoins4())) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins4"
							, pSection1.getAnneeNmoins4()
							, pSection2.getAnneeNmoins4());
					
					resultat.put(93, diff);
				}
				
			} else {
				
				if (pSection2.getAnneeNmoins4() != null) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins4"
							, null
							, pSection2.getAnneeNmoins4());
					
					resultat.put(93, diff);

				}
			}
			
			/* 94 - mjaNmoins4. */
			if (pSection1.getMjaNmoins4() != null) {
				
				if (!pSection1.getMjaNmoins4()
						.equals(pSection2.getMjaNmoins4())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins4"
							, pSection1.getMjaNmoins4()
							, pSection2.getMjaNmoins4());
					
					resultat.put(94, diff);
				}
				
			} else {
				
				if (pSection2.getMjaNmoins4() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins4"
							, null
							, pSection2.getMjaNmoins4());
					
					resultat.put(94, diff);
					
				}
			}
			
			/* 95 - typeComptageNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptageNmoins4()
						, pSection2.getTypeComptageNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("typeComptageNmoins4"
						, pSection1.getTypeComptageNmoins4()
						, pSection2.getTypeComptageNmoins4());
				
				resultat.put(95, diff);
				
			}
			
			/* 96 - modeCalculNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculNmoins4()
						, pSection2.getModeCalculNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("modeCalculNmoins4"
						, pSection1.getModeCalculNmoins4()
						, pSection2.getModeCalculNmoins4());
				
				resultat.put(96, diff);
				
			}
			
			/* 97 - pcPLNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLNmoins4()
						, pSection2.getPcPLNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcPLNmoins4"
						, pSection1.getPcPLNmoins4()
						, pSection2.getPcPLNmoins4());
				
				resultat.put(97, diff);
				
			}
			
			/* 98 - evaluationPLNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLNmoins4()
						, pSection2.getEvaluationPLNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("evaluationPLNmoins4"
						, pSection1.getEvaluationPLNmoins4()
						, pSection2.getEvaluationPLNmoins4());
				
				resultat.put(98, diff);
				
			}
			
			/* 99 - pcNuitAnnuelNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelNmoins4()
						, pSection2.getPcNuitAnnuelNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitAnnuelNmoins4"
						, pSection1.getPcNuitAnnuelNmoins4()
						, pSection2.getPcNuitAnnuelNmoins4());
				
				resultat.put(99, diff);
				
			}
			
			/* 100 - indiceFiabiliteMjaNmoins4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaNmoins4()
						, pSection2.getIndiceFiabiliteMjaNmoins4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("indiceFiabiliteMjaNmoins4"
						, pSection1.getIndiceFiabiliteMjaNmoins4()
						, pSection2.getIndiceFiabiliteMjaNmoins4());
				
				resultat.put(100, diff);
				
			}
			
			/* 101 - anneeNmoins5. */
			if (pSection1.getAnneeNmoins5() != null) {
				
				if (!pSection1.getAnneeNmoins5()
						.equals(pSection2.getAnneeNmoins5())) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins5"
							, pSection1.getAnneeNmoins5()
							, pSection2.getAnneeNmoins5());
					
					resultat.put(101, diff);
				}
				
			} else {
				
				if (pSection2.getAnneeNmoins5() != null) {
					
					final DifferenceChamp<LocalDate> diff 
					= new DifferenceChamp<LocalDate>(
							"anneeNmoins5"
							, null
							, pSection2.getAnneeNmoins5());
					
					resultat.put(101, diff);

				}
			}
			
			/* 102 - mjaNmoins5. */
			if (pSection1.getMjaNmoins5() != null) {
				
				if (!pSection1.getMjaNmoins5()
						.equals(pSection2.getMjaNmoins5())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins5"
							, pSection1.getMjaNmoins5()
							, pSection2.getMjaNmoins5());
					
					resultat.put(102, diff);
				}
				
			} else {
				
				if (pSection2.getMjaNmoins5() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjaNmoins5"
							, null
							, pSection2.getMjaNmoins5());
					
					resultat.put(102, diff);
					
				}
			}
			
			/* 103 - typeComptageNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getTypeComptageNmoins5()
						, pSection2.getTypeComptageNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("typeComptageNmoins5"
						, pSection1.getTypeComptageNmoins5()
						, pSection2.getTypeComptageNmoins5());
				
				resultat.put(103, diff);
				
			}
			
			/* 104 - modeCalculNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getModeCalculNmoins5()
						, pSection2.getModeCalculNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("modeCalculNmoins5"
						, pSection1.getModeCalculNmoins5()
						, pSection2.getModeCalculNmoins5());
				
				resultat.put(104, diff);
				
			}
			
			/* 105 - pcPLNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcPLNmoins5()
						, pSection2.getPcPLNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcPLNmoins5"
						, pSection1.getPcPLNmoins5()
						, pSection2.getPcPLNmoins5());
				
				resultat.put(105, diff);
				
			}
			
			/* 106 - evaluationPLNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getEvaluationPLNmoins5()
						, pSection2.getEvaluationPLNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("evaluationPLNmoins5"
						, pSection1.getEvaluationPLNmoins5()
						, pSection2.getEvaluationPLNmoins5());
				
				resultat.put(106, diff);
				
			}
			
			/* 107 - pcNuitAnnuelNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitAnnuelNmoins5()
						, pSection2.getPcNuitAnnuelNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitAnnuelNmoins5"
						, pSection1.getPcNuitAnnuelNmoins5()
						, pSection2.getPcNuitAnnuelNmoins5());
				
				resultat.put(107, diff);
				
			}
			
			/* 108 - indiceFiabiliteMjaNmoins5. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getIndiceFiabiliteMjaNmoins5()
						, pSection2.getIndiceFiabiliteMjaNmoins5())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("indiceFiabiliteMjaNmoins5"
						, pSection1.getIndiceFiabiliteMjaNmoins5()
						, pSection2.getIndiceFiabiliteMjaNmoins5());
				
				resultat.put(108, diff);
				
			}

			/* 109 - mjmNmoins1mois01. */
			if (pSection1.getMjmNmoins1mois01() != null) {
				
				if (!pSection1.getMjmNmoins1mois01()
						.equals(pSection2.getMjmNmoins1mois01())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois01"
							, pSection1.getMjmNmoins1mois01()
							, pSection2.getMjmNmoins1mois01());
					
					resultat.put(109, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois01() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois01"
							, null
							, pSection2.getMjmNmoins1mois01());
					
					resultat.put(109, diff);

				}
			}
			
			/* 110 - pcNuitNmoins1mois01. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois01()
						, pSection2.getPcNuitNmoins1mois01())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois01"
						, pSection1.getPcNuitNmoins1mois01()
						, pSection2.getPcNuitNmoins1mois01());
				
				resultat.put(110, diff);
				
			}

			/* 111 - mjmNmoins1mois02. */
			if (pSection1.getMjmNmoins1mois02() != null) {
				
				if (!pSection1.getMjmNmoins1mois02()
						.equals(pSection2.getMjmNmoins1mois02())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois02"
							, pSection1.getMjmNmoins1mois02()
							, pSection2.getMjmNmoins1mois02());
					
					resultat.put(111, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois02() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois02"
							, null
							, pSection2.getMjmNmoins1mois02());
					
					resultat.put(111, diff);

				}
			}
			
			/* 112 - pcNuitNmoins1mois02. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois02()
						, pSection2.getPcNuitNmoins1mois02())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois02"
						, pSection1.getPcNuitNmoins1mois02()
						, pSection2.getPcNuitNmoins1mois02());
				
				resultat.put(112, diff);
				
			}

			/* 113 - mjmNmoins1mois03. */
			if (pSection1.getMjmNmoins1mois03() != null) {
				
				if (!pSection1.getMjmNmoins1mois03()
						.equals(pSection2.getMjmNmoins1mois03())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois03"
							, pSection1.getMjmNmoins1mois03()
							, pSection2.getMjmNmoins1mois03());
					
					resultat.put(113, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois03() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois03"
							, null
							, pSection2.getMjmNmoins1mois03());
					
					resultat.put(113, diff);

				}
			}
			
			/* 114 - pcNuitNmoins1mois03. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois03()
						, pSection2.getPcNuitNmoins1mois03())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois03"
						, pSection1.getPcNuitNmoins1mois03()
						, pSection2.getPcNuitNmoins1mois03());
				
				resultat.put(114, diff);
				
			}

			/* 115 - mjmNmoins1mois04. */
			if (pSection1.getMjmNmoins1mois04() != null) {
				
				if (!pSection1.getMjmNmoins1mois04()
						.equals(pSection2.getMjmNmoins1mois04())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois04"
							, pSection1.getMjmNmoins1mois04()
							, pSection2.getMjmNmoins1mois04());
					
					resultat.put(115, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois04() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois04"
							, null
							, pSection2.getMjmNmoins1mois04());
					
					resultat.put(115, diff);

				}
			}
			
			/* 116 - pcNuitNmoins1mois04. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois04()
						, pSection2.getPcNuitNmoins1mois04())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois04"
						, pSection1.getPcNuitNmoins1mois04()
						, pSection2.getPcNuitNmoins1mois04());
				
				resultat.put(116, diff);
				
			}

			/* 117 - mjmNmoins1mois05. */
			if (pSection1.getMjmNmoins1mois05() != null) {
				
				if (!pSection1.getMjmNmoins1mois05()
						.equals(pSection2.getMjmNmoins1mois05())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois05"
							, pSection1.getMjmNmoins1mois05()
							, pSection2.getMjmNmoins1mois05());
					
					resultat.put(117, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois05() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois05"
							, null
							, pSection2.getMjmNmoins1mois05());
					
					resultat.put(117, diff);

				}
			}
			
			/* 118 - pcNuitNmoins1mois05. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois05()
						, pSection2.getPcNuitNmoins1mois05())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois05"
						, pSection1.getPcNuitNmoins1mois05()
						, pSection2.getPcNuitNmoins1mois05());
				
				resultat.put(118, diff);
				
			}

			/* 119 - mjmNmoins1mois06. */
			if (pSection1.getMjmNmoins1mois06() != null) {
				
				if (!pSection1.getMjmNmoins1mois06()
						.equals(pSection2.getMjmNmoins1mois06())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois06"
							, pSection1.getMjmNmoins1mois06()
							, pSection2.getMjmNmoins1mois06());
					
					resultat.put(119, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois06() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois06"
							, null
							, pSection2.getMjmNmoins1mois06());
					
					resultat.put(119, diff);

				}
			}
			
			/* 120 - pcNuitNmoins1mois06. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois06()
						, pSection2.getPcNuitNmoins1mois06())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois06"
						, pSection1.getPcNuitNmoins1mois06()
						, pSection2.getPcNuitNmoins1mois06());
				
				resultat.put(120, diff);
				
			}

			/* 121 - mjmNmoins1mois07. */
			if (pSection1.getMjmNmoins1mois07() != null) {
				
				if (!pSection1.getMjmNmoins1mois07()
						.equals(pSection2.getMjmNmoins1mois07())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois07"
							, pSection1.getMjmNmoins1mois07()
							, pSection2.getMjmNmoins1mois07());
					
					resultat.put(121, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois07() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois07"
							, null
							, pSection2.getMjmNmoins1mois07());
					
					resultat.put(121, diff);

				}
			}
			
			/* 122 - pcNuitNmoins1mois07. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois07()
						, pSection2.getPcNuitNmoins1mois07())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois07"
						, pSection1.getPcNuitNmoins1mois07()
						, pSection2.getPcNuitNmoins1mois07());
				
				resultat.put(122, diff);
				
			}

			/* 123 - mjmNmoins1mois08. */
			if (pSection1.getMjmNmoins1mois08() != null) {
				
				if (!pSection1.getMjmNmoins1mois08()
						.equals(pSection2.getMjmNmoins1mois08())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois08"
							, pSection1.getMjmNmoins1mois08()
							, pSection2.getMjmNmoins1mois08());
					
					resultat.put(123, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois08() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois08"
							, null
							, pSection2.getMjmNmoins1mois08());
					
					resultat.put(123, diff);

				}
			}
			
			/* 124 - pcNuitNmoins1mois08. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois08()
						, pSection2.getPcNuitNmoins1mois08())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois08"
						, pSection1.getPcNuitNmoins1mois08()
						, pSection2.getPcNuitNmoins1mois08());
				
				resultat.put(124, diff);
				
			}

			/* 125 - mjmNmoins1mois09. */
			if (pSection1.getMjmNmoins1mois09() != null) {
				
				if (!pSection1.getMjmNmoins1mois09()
						.equals(pSection2.getMjmNmoins1mois09())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois09"
							, pSection1.getMjmNmoins1mois09()
							, pSection2.getMjmNmoins1mois09());
					
					resultat.put(125, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois09() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois09"
							, null
							, pSection2.getMjmNmoins1mois09());
					
					resultat.put(125, diff);

				}
			}
			
			/* 126 - pcNuitNmoins1mois09. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois09()
						, pSection2.getPcNuitNmoins1mois09())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois09"
						, pSection1.getPcNuitNmoins1mois09()
						, pSection2.getPcNuitNmoins1mois09());
				
				resultat.put(126, diff);
				
			}

			/* 127 - mjmNmoins1mois10. */
			if (pSection1.getMjmNmoins1mois10() != null) {
				
				if (!pSection1.getMjmNmoins1mois10()
						.equals(pSection2.getMjmNmoins1mois10())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois10"
							, pSection1.getMjmNmoins1mois10()
							, pSection2.getMjmNmoins1mois10());
					
					resultat.put(127, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois10() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois10"
							, null
							, pSection2.getMjmNmoins1mois10());
					
					resultat.put(127, diff);

				}
			}
			
			/* 128 - pcNuitNmoins1mois10. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois10()
						, pSection2.getPcNuitNmoins1mois10())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois10"
						, pSection1.getPcNuitNmoins1mois10()
						, pSection2.getPcNuitNmoins1mois10());
				
				resultat.put(128, diff);
				
			}

			/* 129 - mjmNmoins1mois11. */
			if (pSection1.getMjmNmoins1mois11() != null) {
				
				if (!pSection1.getMjmNmoins1mois11()
						.equals(pSection2.getMjmNmoins1mois11())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois11"
							, pSection1.getMjmNmoins1mois11()
							, pSection2.getMjmNmoins1mois11());
					
					resultat.put(129, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois11() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois11"
							, null
							, pSection2.getMjmNmoins1mois11());
					
					resultat.put(129, diff);

				}
			}
			
			/* 130 - pcNuitNmoins1mois11. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois11()
						, pSection2.getPcNuitNmoins1mois11())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois11"
						, pSection1.getPcNuitNmoins1mois11()
						, pSection2.getPcNuitNmoins1mois11());
				
				resultat.put(130, diff);
				
			}

			/* 131 - mjmNmoins1mois12. */
			if (pSection1.getMjmNmoins1mois12() != null) {
				
				if (!pSection1.getMjmNmoins1mois12()
						.equals(pSection2.getMjmNmoins1mois12())) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois12"
							, pSection1.getMjmNmoins1mois12()
							, pSection2.getMjmNmoins1mois12());
					
					resultat.put(131, diff);
				}
				
			} else {
				
				if (pSection2.getMjmNmoins1mois12() != null) {
					
					final DifferenceChamp<Integer> diff 
					= new DifferenceChamp<Integer>(
							"mjmNmoins1mois12"
							, null
							, pSection2.getMjmNmoins1mois12());
					
					resultat.put(131, diff);

				}
			}
			
			/* 132 - pcNuitNmoins1mois12. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getPcNuitNmoins1mois12()
						, pSection2.getPcNuitNmoins1mois12())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("pcNuitNmoins1mois12"
						, pSection1.getPcNuitNmoins1mois12()
						, pSection2.getPcNuitNmoins1mois12());
				
				resultat.put(132, diff);
				
			}
			
			/* 133 - zoneLibre4. */
			if (!StringUtils.equalsIgnoreCase(
					pSection1.getZoneLibre4()
						, pSection2.getZoneLibre4())) {
				
				final DifferenceChamp<String> diff 
				= new DifferenceChamp<String>("zoneLibre4"
						, pSection1.getZoneLibre4()
						, pSection2.getZoneLibre4());
				
				resultat.put(133, diff);
				
			}
			
			return resultat;
			
		} // Fin de synchronized.___________________________________
		
	} // Fin de differencier(...)._________________________________________
	
	
	
} // FIN DE LA CLASSE DifferentiateurSectionsHit.----------------------------
