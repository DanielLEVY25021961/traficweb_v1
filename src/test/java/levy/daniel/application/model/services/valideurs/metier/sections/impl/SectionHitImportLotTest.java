package levy.daniel.application.model.services.valideurs.metier.sections.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections.SectionHitGestionnairePreferencesRG;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.IImporteur;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;
import levy.daniel.application.model.services.valideurs.ErreursMaps;
import levy.daniel.application.model.services.valideurs.metier.sections.ISectionHitValideurService;

/**
 * CLASSE SectionHitImportLotTest :<br/>
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
 * @since 10 sept. 2019
 *
 */
public class SectionHitImportLotTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * ISectionHitValideurService.
	 */
	public static final ISectionHitValideurService SERVICE 
		= new SectionHitValideurService();

	/**
	 * ISectionHitDTO.
	 */
	public static ISectionHitDTO dto;

	/**
	 * Paths.get(".").toAbsolutePath().normalize().<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * path <b>relatif</b> (par rapport au présent projet ECLIPSE) 
	 * des ressources des tests JUnit dans le présent projet ECLIPSE.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * Path absolu vers les ressources de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_RESOURCES 
		= PATH_ABSOLU_PRESENT_PROJET
			.resolve(SRC_TEST_RESOURCES_PATH_RELATIF)
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les nomenclatures de test.<br/>
	 */
	public static final Path PATH_ABSOLU_TEST_NOMENCLATURES 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("ressources/Nomenclatures")
				.toAbsolutePath().normalize();
	
	 /**
	 * Path absolu vers les jeux d'essai de test.<br/>
	 * src/test/resources/jeux_essai
	 */
	public static final Path PATH_ABSOLU_TEST_JEUX_ESSAI 
		= PATH_ABSOLU_TEST_RESOURCES
			.resolve("jeux_essai")
				.toAbsolutePath().normalize();
	
	/**
	 * Path absolu vers le répertoire 'temp' sous le présent projet.
	 */
	public static final Path PATH_ABSOLU_REPERTOIRE_TEMP 
		= PATH_ABSOLU_PRESENT_PROJET.resolve("temp");

	/**
	 * IImporteur.<br/>
	 */
	public static IImporteur importeur;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitImportLotTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitImportLotTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * .<br/>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testImporterLotDTO() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE SectionHitValideurServiceTest - méthode testImporterLotDTO() ********** ");
		}
		
		desactiverIntelligentRG();
		
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("2018/HITDIRA2018.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		// METHODE A TESTER.
		final ImporteurHit importeurHIT = (ImporteurHit) importeur;
		
		final long topDepartImportDTO = System.currentTimeMillis();
		
		final Map<Integer, ISectionHitDTO> fichierMapDTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		final long topFinalImportDTO = System.currentTimeMillis();
		
		System.out.println("durée de l'import DTO : " + (topFinalImportDTO - topDepartImportDTO) + " ms");

//		int compteur = 0;
//		
//		for (final ISectionHitDTO dtoLocal : fichierMapDTO.values()) {			
//			compteur++;
//			final ErreursMaps erreurMaps = SERVICE.valider(dtoLocal);
//			System.out.println("ligne " + compteur + " - " + erreurMaps.afficherErrorsMap());
//			System.out.println();
//		}
		
		
		final Set<Entry<Integer, ISectionHitDTO>> entrySet 
			= fichierMapDTO.entrySet();
		
		final Iterator<Entry<Integer, ISectionHitDTO>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, ISectionHitDTO> entry = ite.next();
			
			final Integer numeroLigne = entry.getKey();
			final ISectionHitDTO dtoLocal = entry.getValue();
			
			System.out.println();
			final long topDepartValiderDTO = System.currentTimeMillis();
			final ErreursMaps erreurMaps = SERVICE.valider(dtoLocal);
			final long topFinalValiderDTO = System.currentTimeMillis();
			
			final Map<String, String> mapConcatenee = erreurMaps.getErrorsMap();
			
			System.out.println("******** ligne " + numeroLigne + " - durée de la validation du DTO : " + (topFinalValiderDTO - topDepartValiderDTO) + " ms");
			System.out.println("******** ligne " + numeroLigne + " - VALIDE ? : " + erreurMaps.isValide());
			System.out.println("******** ligne " + numeroLigne + " - ADMISSIBLE ? : " + erreurMaps.isAdmissible());
			
			for (final Entry<String, String> entryLocal : mapConcatenee.entrySet()) {
				
				System.out.println("ligne " + numeroLigne + ";" + entryLocal.getKey() + ";" + entryLocal.getValue() + ";");
			}
		}
		
	} // Fin de testImporterLotDTO().______________________________________
	
	
	
	/**
	 * active toutes les RG.
	 * 
	 * @throws Exception 
	 */
	private static void activerToutesRG() throws Exception {
		
		/* 1 - numDepartement. ***************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRegex02(true);
		
		/* 2 - numSection. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSection(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRegex02(true);
		
		/* 3 - sens. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSens(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensNomenclature03(true);
		
		/* 4 - nature. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNature(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureNomenclature03(true);
		
		/* 5 - classe. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasse(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRegex02(true);
		
		/* 6 - anneeTraitement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRegex02(true);
		
		/* 7 - zoneLibre1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Regex02(true);
		
		/* 8 - numRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRegex02(true);
		
		/* 9 - indiceNumRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRegex02(true);
		
		/* 10 - indiceLettreRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRegex02(true);
		
		/* 11 - categorieAdminRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteNomenclature03(true);
		
		/* 12 - typeComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNomenclature03(true);
		
		/* 13 - classementRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRoute(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteNomenclature03(true);
		
		/* 14 - classeLargeurChausseeU. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeU(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(true);
		
		/* 15 - classeLargeurChausseesS. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesS(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(true);
		
		/* 16 - typeReseau. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseau(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauNomenclature03(true);
		
		/* 17 - pRoupK. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupK(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKNomenclature03(true);
		
		/* 18 - lieuDitOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRegex02(true);
		
		/* 19 - prOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineNumerique03(true);
		
		/* 20 - absOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigine(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineNumerique03(true);
		
		/* 21 - lieuDitExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRegex02(true);
		
		/* 22 - prExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteNumerique03(true);
		
		/* 23 - absExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremite(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteNumerique03(true);
		
		/* 24 - lieuDitComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRegex02(true);
		
		/* 25 - prComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageNumerique03(true);
		
		/* 26 - absComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptage(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageNumerique03(true);
		
		/* 28 - longueurRaseCampagne. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagne(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneNumerique03(true);
		
		/* 29 - numDepartementRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRegex02(true);
		
		/* 30 - numSectionRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRegex02(true);
		
		/* 31 - sensRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementNomenclature03(true);
		
		/* 32 - numDepartementLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRegex02(true);
		
		/* 33 - numSectionLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRegex02(true);
		
		/* 34 - sensLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitrophe(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheNomenclature03(true);
		
		/* 35 - moisSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRegex02(true);
		
		/* 36 - anneeSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnement(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRegex02(true);
		
		/* 37 - zoneLibre2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Regex02(true);
		
		/* 38 - mjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNNumerique03(true);
		
		/* 39 - modeCalculN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRegex02(true);
		
		/* 40 - pcPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNNumerique03(true);
		
		/* 41 - evaluationPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRegex02(true);
		
		/* 42 - pcNuitAnnuelN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRegex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNNumerique03(true);
		
		/* 43 - indiceFiabiliteMjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaN(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(true);
		
		/* 44 - mjmNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Numerique03(true);
		
		/* 45 - pcNuitNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Numerique03(true);
		
		/* 46 - mjmNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Numerique03(true);
		
		/* 47 - pcNuitNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Numerique03(true);
		
		/* 48 - mjmNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Numerique03(true);
		
		/* 49 - pcNuitNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Numerique03(true);
		
		/* 50 - mjmNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Numerique03(true);
		
		/* 51 - pcNuitNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Numerique03(true);
		
		/* 52 - mjmNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Numerique03(true);
		
		/* 53 - pcNuitNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Numerique03(true);
		
		/* 54 - mjmNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Numerique03(true);
		
		/* 55 - pcNuitNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Numerique03(true);
		
		/* 56 - mjmNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Numerique03(true);
		
		/* 57 - pcNuitNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Numerique03(true);
		
		/* 58 - mjmNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Numerique03(true);
		
		/* 59 - pcNuitNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Numerique03(true);
		
		/* 60 - mjmNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Numerique03(true);
		
		/* 61 - pcNuitNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Numerique03(true);
		
		/* 62 - mjmNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Numerique03(true);
		
		/* 63 - pcNuitNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Numerique03(true);
		
		/* 64 - mjmNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Numerique03(true);
		
		/* 65 - pcNuitNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Numerique03(true);
		
		/* 66 - mjmNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Numerique03(true);
		
		/* 67 - pcNuitNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Numerique03(true);
		
		/* 68 - zoneLibre3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Regex02(true);
		
		/* 69 - anneeNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Regex02(true);
		
		/* 70 - mjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Numerique03(true);
		
		/* 71 - typeComptageNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Nomenclature03(true);
		
		/* 72 - modeCalculNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Regex02(true);
		
		/* 73 - pcPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Numerique03(true);
		
		/* 74 - evaluationPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Regex02(true);
		
		/* 75 - pcNuitAnnuelNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Numerique03(true);
		
		/* 76 - indiceFiabiliteMjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Regex02(true);
		
		/* 77 - anneeNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Regex02(true);
		
		/* 78 - mjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Numerique03(true);
		
		/* 79 - typeComptageNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Nomenclature03(true);
		
		/* 80 - modeCalculNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Regex02(true);
		
		/* 81 - pcPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Numerique03(true);
		
		/* 82 - evaluationPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Regex02(true);
		
		/* 83 - pcNuitAnnuelNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Numerique03(true);
		
		/* 84 - indiceFiabiliteMjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Regex02(true);

		
		/* 85 - anneeNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Regex02(true);
		
		/* 86 - mjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Numerique03(true);
		
		/* 87 - typeComptageNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Nomenclature03(true);
		
		/* 88 - modeCalculNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Regex02(true);
		
		/* 89 - pcPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Numerique03(true);
		
		/* 90 - evaluationPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Regex02(true);
		
		/* 91 - pcNuitAnnuelNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Numerique03(true);
		
		/* 92 - indiceFiabiliteMjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Regex02(true);

		
		/* 93 - anneeNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Regex02(true);
		
		/* 94 - mjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Numerique03(true);
		
		/* 95 - typeComptageNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Nomenclature03(true);
		
		/* 96 - modeCalculNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Regex02(true);
		
		/* 97 - pcPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Numerique03(true);
		
		/* 98 - evaluationPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Regex02(true);
		
		/* 99 - pcNuitAnnuelNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Numerique03(true);
		
		/* 100 - indiceFiabiliteMjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Regex02(true);

		
		/* 101 - anneeNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Regex02(true);
		
		/* 102 - mjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Numerique03(true);
		
		/* 103 - typeComptageNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Nomenclature03(true);
		
		/* 104 - modeCalculNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Regex02(true);
		
		/* 105 - pcPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Numerique03(true);
		
		/* 106 - evaluationPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Regex02(true);
		
		/* 107 - pcNuitAnnuelNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Numerique03(true);
		
		/* 108 - indiceFiabiliteMjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Regex02(true);
		
		/* 109 - mjmNmoins1mois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Numerique03(true);
		
		/* 110 - pcNuitNmoins1mois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Numerique03(true);
		
		/* 111 - mjmNmoins1mois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Numerique03(true);
		
		/* 112 - pcNuitNmoins1mois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Numerique03(true);
		
		/* 113 - mjmNmoins1mois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Numerique03(true);
		
		/* 114 - pcNuitNmoins1mois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Numerique03(true);
		
		/* 115 - mjmNmoins1mois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Numerique03(true);
		
		/* 116 - pcNuitNmoins1mois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Numerique03(true);
		
		/* 117 - mjmNmoins1mois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Numerique03(true);
		
		/* 118 - pcNuitNmoins1mois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Numerique03(true);
		
		/* 119 - mjmNmoins1mois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Numerique03(true);
		
		/* 120 - pcNuitNmoins1mois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Numerique03(true);
		
		/* 121 - mjmNmoins1mois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Numerique03(true);
		
		/* 122 - pcNuitNmoins1mois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Numerique03(true);
		
		/* 123 - mjmNmoins1mois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Numerique03(true);
		
		/* 124 - pcNuitNmoins1mois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Numerique03(true);
		
		/* 125 - mjmNmoins1mois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Numerique03(true);
		
		/* 126 - pcNuitNmoins1mois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Numerique03(true);
		
		/* 127 - mjmNmoins1mois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Numerique03(true);
		
		/* 128 - pcNuitNmoins1mois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Numerique03(true);
		
		/* 129 - mjmNmoins1mois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Numerique03(true);
		
		/* 130 - pcNuitNmoins1mois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Numerique03(true);
		
		/* 131 - mjmNmoins1mois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Numerique03(true);
		
		/* 132 - pcNuitNmoins1mois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Regex02(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Numerique03(true);
		
		/* 133 - zoneLibre4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Renseigne01(true);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Regex02(true);

	} // Fin de activerToutesRG()._________________________________________
	
	
	
	/**
	 * desactive toutes les RG.
	 * 
	 * @throws Exception 
	 */
	private static void desactiverToutesRG() throws Exception {
		
		/* 1 - numDepartement. ***************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRegex02(false);
		
		/* 2 - numSection. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSection(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRegex02(false);
		
		/* 3 - sens. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSens(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensNomenclature03(false);
		
		/* 4 - nature. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNature(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNatureNomenclature03(false);
		
		/* 5 - classe. *******************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasse(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseRegex02(false);
		
		/* 6 - anneeTraitement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeTraitementRegex02(false);
		
		/* 7 - zoneLibre1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre1Regex02(false);
		
		/* 8 - numRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRoute(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumRouteRegex02(false);
		
		/* 9 - indiceNumRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRoute(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceNumRouteRegex02(false);
		
		/* 10 - indiceLettreRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRoute(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceLettreRouteRegex02(false);
		
		/* 11 - categorieAdminRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRoute(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitCategorieAdminRouteNomenclature03(false);
		
		/* 12 - typeComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptage(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNomenclature03(false);
		
		/* 13 - classementRoute. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRoute(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClassementRouteNomenclature03(false);
		
		/* 14 - classeLargeurChausseeU. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeU(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(false);
		
		/* 15 - classeLargeurChausseesS. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesS(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(false);
		
		/* 16 - typeReseau. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseau(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeReseauNomenclature03(false);
		
		/* 17 - pRoupK. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupK(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKNomenclature03(false);
		
		/* 18 - lieuDitOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigine(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitOrigineRegex02(false);
		
		/* 19 - prOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigine(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrOrigineNumerique03(false);
		
		/* 20 - absOrigine. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigine(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsOrigineNumerique03(false);
		
		/* 21 - lieuDitExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremite(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitExtremiteRegex02(false);
		
		/* 22 - prExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremite(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrExtremiteNumerique03(false);
		
		/* 23 - absExtremite. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremite(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsExtremiteNumerique03(false);
		
		/* 24 - lieuDitComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptage(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLieuDitComptageRegex02(false);
		
		/* 25 - prComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptage(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPrComptageNumerique03(false);
		
		/* 26 - absComptage. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptage(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAbsComptageNumerique03(false);
		
		/* 28 - longueurRaseCampagne. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagne(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneNumerique03(false);
		
		/* 29 - numDepartementRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRegex02(false);
		
		/* 30 - numSectionRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRegex02(false);
		
		/* 31 - sensRattachement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementNomenclature03(false);
		
		/* 32 - numDepartementLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitrophe(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRegex02(false);
		
		/* 33 - numSectionLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitrophe(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRegex02(false);
		
		/* 34 - sensLimitrophe. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitrophe(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheNomenclature03(false);
		
		/* 35 - moisSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRegex02(false);
		
		/* 36 - anneeSectionnement. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnement(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRegex02(false);
		
		/* 37 - zoneLibre2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Regex02(false);
		
		/* 38 - mjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNNumerique03(false);
		
		/* 39 - modeCalculN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRegex02(false);
		
		/* 40 - pcPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNNumerique03(false);
		
		/* 41 - evaluationPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRegex02(false);
		
		/* 42 - pcNuitAnnuelN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNNumerique03(false);
		
		/* 43 - indiceFiabiliteMjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(false);
		
		/* 44 - mjmNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Numerique03(false);
		
		/* 45 - pcNuitNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Numerique03(false);
		
		/* 46 - mjmNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Numerique03(false);
		
		/* 47 - pcNuitNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Numerique03(false);
		
		/* 48 - mjmNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Numerique03(false);
		
		/* 49 - pcNuitNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Numerique03(false);
		
		/* 50 - mjmNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Numerique03(false);
		
		/* 51 - pcNuitNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Numerique03(false);
		
		/* 52 - mjmNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Numerique03(false);
		
		/* 53 - pcNuitNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Numerique03(false);
		
		/* 54 - mjmNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Numerique03(false);
		
		/* 55 - pcNuitNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Numerique03(false);
		
		/* 56 - mjmNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Numerique03(false);
		
		/* 57 - pcNuitNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Numerique03(false);
		
		/* 58 - mjmNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Numerique03(false);
		
		/* 59 - pcNuitNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Numerique03(false);
		
		/* 60 - mjmNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Numerique03(false);
		
		/* 61 - pcNuitNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Numerique03(false);
		
		/* 62 - mjmNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Numerique03(false);
		
		/* 63 - pcNuitNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Numerique03(false);
		
		/* 64 - mjmNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Numerique03(false);
		
		/* 65 - pcNuitNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Numerique03(false);
		
		/* 66 - mjmNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Numerique03(false);
		
		/* 67 - pcNuitNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Numerique03(false);
		
		/* 68 - zoneLibre3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Regex02(false);
		
		/* 69 - anneeNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Regex02(false);
		
		/* 70 - mjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Numerique03(false);
		
		/* 71 - typeComptageNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Nomenclature03(false);
		
		/* 72 - modeCalculNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Regex02(false);
		
		/* 73 - pcPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Numerique03(false);
		
		/* 74 - evaluationPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Regex02(false);
		
		/* 75 - pcNuitAnnuelNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Numerique03(false);
		
		/* 76 - indiceFiabiliteMjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Regex02(false);
		
		/* 77 - anneeNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Regex02(false);
		
		/* 78 - mjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Numerique03(false);
		
		/* 79 - typeComptageNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Nomenclature03(false);
		
		/* 80 - modeCalculNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Regex02(false);
		
		/* 81 - pcPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Numerique03(false);
		
		/* 82 - evaluationPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Regex02(false);
		
		/* 83 - pcNuitAnnuelNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Numerique03(false);
		
		/* 84 - indiceFiabiliteMjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Regex02(false);

		
		/* 85 - anneeNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Regex02(false);
		
		/* 86 - mjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Numerique03(false);
		
		/* 87 - typeComptageNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Nomenclature03(false);
		
		/* 88 - modeCalculNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Regex02(false);
		
		/* 89 - pcPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Numerique03(false);
		
		/* 90 - evaluationPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Regex02(false);
		
		/* 91 - pcNuitAnnuelNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Numerique03(false);
		
		/* 92 - indiceFiabiliteMjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Regex02(false);

		
		/* 93 - anneeNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Regex02(false);
		
		/* 94 - mjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Numerique03(false);
		
		/* 95 - typeComptageNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Nomenclature03(false);
		
		/* 96 - modeCalculNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Regex02(false);
		
		/* 97 - pcPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Numerique03(false);
		
		/* 98 - evaluationPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Regex02(false);
		
		/* 99 - pcNuitAnnuelNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Numerique03(false);
		
		/* 100 - indiceFiabiliteMjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Regex02(false);

		
		/* 101 - anneeNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Regex02(false);
		
		/* 102 - mjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Numerique03(false);
		
		/* 103 - typeComptageNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Nomenclature03(false);
		
		/* 104 - modeCalculNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Regex02(false);
		
		/* 105 - pcPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Numerique03(false);
		
		/* 106 - evaluationPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Regex02(false);
		
		/* 107 - pcNuitAnnuelNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Numerique03(false);
		
		/* 108 - indiceFiabiliteMjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Regex02(false);
		
		/* 109 - mjmNmoins1mois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Numerique03(false);
		
		/* 110 - pcNuitNmoins1mois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Numerique03(false);
		
		/* 111 - mjmNmoins1mois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Numerique03(false);
		
		/* 112 - pcNuitNmoins1mois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Numerique03(false);
		
		/* 113 - mjmNmoins1mois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Numerique03(false);
		
		/* 114 - pcNuitNmoins1mois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Numerique03(false);
		
		/* 115 - mjmNmoins1mois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Numerique03(false);
		
		/* 116 - pcNuitNmoins1mois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Numerique03(false);
		
		/* 117 - mjmNmoins1mois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Numerique03(false);
		
		/* 118 - pcNuitNmoins1mois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Numerique03(false);
		
		/* 119 - mjmNmoins1mois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Numerique03(false);
		
		/* 120 - pcNuitNmoins1mois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Numerique03(false);
		
		/* 121 - mjmNmoins1mois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Numerique03(false);
		
		/* 122 - pcNuitNmoins1mois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Numerique03(false);
		
		/* 123 - mjmNmoins1mois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Numerique03(false);
		
		/* 124 - pcNuitNmoins1mois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Numerique03(false);
		
		/* 125 - mjmNmoins1mois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Numerique03(false);
		
		/* 126 - pcNuitNmoins1mois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Numerique03(false);
		
		/* 127 - mjmNmoins1mois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Numerique03(false);
		
		/* 128 - pcNuitNmoins1mois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Numerique03(false);
		
		/* 129 - mjmNmoins1mois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Numerique03(false);
		
		/* 130 - pcNuitNmoins1mois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Numerique03(false);
		
		/* 131 - mjmNmoins1mois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Numerique03(false);
		
		/* 132 - pcNuitNmoins1mois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Numerique03(false);
		
		/* 133 - zoneLibre4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Regex02(false);

	} // Fin de desactiverToutesRG().______________________________________
	
	
	
	/**
	 * desactive intelligemment les RG.
	 * 
	 * @throws Exception 
	 */
	private static void desactiverIntelligentRG() throws Exception {
		
		/* 14 - classeLargeurChausseeU. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeU(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeURegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(false);
		
		/* 15 - classeLargeurChausseesS. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesS(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(false);
		
		/* 17 - pRoupK. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupK(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPRoupKNomenclature03(false);
		
		/* 28 - longueurRaseCampagne. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagne(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitLongueurRaseCampagneNumerique03(false);
		
//		/* 29 - numDepartementRattachement. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachement(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementRattachementRegex02(false);
//		
//		/* 30 - numSectionRattachement. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachement(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionRattachementRegex02(false);
//		
//		/* 31 - sensRattachement. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachement(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementRegex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensRattachementNomenclature03(false);
//		
//		/* 32 - numDepartementLimitrophe. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitrophe(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumDepartementLimitropheRegex02(false);
//		
//		/* 33 - numSectionLimitrophe. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitrophe(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitNumSectionLimitropheRegex02(false);
//		
//		/* 34 - sensLimitrophe. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitrophe(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheRegex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitSensLimitropheNomenclature03(false);
//		
//		/* 35 - moisSectionnement. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnement(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMoisSectionnementRegex02(false);
//		
//		/* 36 - anneeSectionnement. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnement(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeSectionnementRegex02(false);
		
//		/* 37 - zoneLibre2. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre2Regex02(false);
//		
//		/* 38 - mjaN. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaN(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRenseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNRegex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNNumerique03(false);
		
		/* 39 - modeCalculN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNRegex02(false);
		
		/* 40 - pcPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNNumerique03(false);
		
		/* 41 - evaluationPLN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNRegex02(false);
		
		/* 42 - pcNuitAnnuelN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNRegex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNNumerique03(false);
		
		/* 43 - indiceFiabiliteMjaN. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaN(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(false);
		
		/* 44 - mjmNmois01. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois01Numerique03(false);
		
		/* 45 - pcNuitNmois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois01Numerique03(false);
		
		/* 46 - mjmNmois02. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois02Numerique03(false);
		
		/* 47 - pcNuitNmois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois02Numerique03(false);
		
		/* 48 - mjmNmois03. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois03Numerique03(false);
		
		/* 49 - pcNuitNmois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois03Numerique03(false);
		
		/* 50 - mjmNmois04. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois04Numerique03(false);
		
		/* 51 - pcNuitNmois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois04Numerique03(false);
		
		/* 52 - mjmNmois05. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois05Numerique03(false);
		
		/* 53 - pcNuitNmois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois05Numerique03(false);
		
		/* 54 - mjmNmois06. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois06Numerique03(false);
		
		/* 55 - pcNuitNmois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois06Numerique03(false);
		
		/* 56 - mjmNmois07. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois07Numerique03(false);
		
		/* 57 - pcNuitNmois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois07Numerique03(false);
		
		/* 58 - mjmNmois08. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois08Numerique03(false);
		
		/* 59 - pcNuitNmois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois08Numerique03(false);
		
		/* 60 - mjmNmois09. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois09Numerique03(false);
		
		/* 61 - pcNuitNmois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois09Numerique03(false);
		
		/* 62 - mjmNmois10. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois10Numerique03(false);
		
		/* 63 - pcNuitNmois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois10Numerique03(false);
		
		/* 64 - mjmNmois11. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois11Numerique03(false);
		
		/* 65 - pcNuitNmois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois11Numerique03(false);
		
		/* 66 - mjmNmois12. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmois12Numerique03(false);
		
		/* 67 - pcNuitNmois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmois12Numerique03(false);
		
		/* 68 - zoneLibre3. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre3Regex02(false);
		
		/* 69 - anneeNmoins1. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins1Regex02(false);
		
		/* 70 - mjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins1Numerique03(false);
		
		/* 71 - typeComptageNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins1Nomenclature03(false);
		
		/* 72 - modeCalculNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins1Regex02(false);
		
		/* 73 - pcPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins1Numerique03(false);
		
		/* 74 - evaluationPLNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins1Regex02(false);
		
		/* 75 - pcNuitAnnuelNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins1Numerique03(false);
		
		/* 76 - indiceFiabiliteMjaNmoins1. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins1Regex02(false);
		
		/* 77 - anneeNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins2Regex02(false);
		
		/* 78 - mjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins2Numerique03(false);
		
		/* 79 - typeComptageNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins2Nomenclature03(false);
		
		/* 80 - modeCalculNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins2Regex02(false);
		
		/* 81 - pcPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins2Numerique03(false);
		
		/* 82 - evaluationPLNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins2Regex02(false);
		
		/* 83 - pcNuitAnnuelNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins2Numerique03(false);
		
		/* 84 - indiceFiabiliteMjaNmoins2. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins2Regex02(false);

		
		/* 85 - anneeNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins3Regex02(false);
		
		/* 86 - mjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins3Numerique03(false);
		
		/* 87 - typeComptageNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins3Nomenclature03(false);
		
		/* 88 - modeCalculNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins3Regex02(false);
		
		/* 89 - pcPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins3Numerique03(false);
		
		/* 90 - evaluationPLNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins3Regex02(false);
		
		/* 91 - pcNuitAnnuelNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins3Numerique03(false);
		
		/* 92 - indiceFiabiliteMjaNmoins3. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins3Regex02(false);

		
		/* 93 - anneeNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins4Regex02(false);
		
		/* 94 - mjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins4Numerique03(false);
		
		/* 95 - typeComptageNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins4Nomenclature03(false);
		
		/* 96 - modeCalculNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins4Regex02(false);
		
		/* 97 - pcPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins4Numerique03(false);
		
		/* 98 - evaluationPLNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins4Regex02(false);
		
		/* 99 - pcNuitAnnuelNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins4Numerique03(false);
		
		/* 100 - indiceFiabiliteMjaNmoins4. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins4Regex02(false);

		
		/* 101 - anneeNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitAnneeNmoins5Regex02(false);
		
		/* 102 - mjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjaNmoins5Numerique03(false);
		
		/* 103 - typeComptageNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitTypeComptageNmoins5Nomenclature03(false);
		
		/* 104 - modeCalculNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitModeCalculNmoins5Regex02(false);
		
		/* 105 - pcPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcPLNmoins5Numerique03(false);
		
		/* 106 - evaluationPLNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitEvaluationPLNmoins5Regex02(false);
		
		/* 107 - pcNuitAnnuelNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitAnnuelNmoins5Numerique03(false);
		
		/* 108 - indiceFiabiliteMjaNmoins5. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitIndiceFiabiliteMjaNmoins5Regex02(false);
		
		/* 109 - mjmNmoins1mois01. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois01Numerique03(false);
		
		/* 110 - pcNuitNmoins1mois01. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois01Numerique03(false);
		
		/* 111 - mjmNmoins1mois02. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois02Numerique03(false);
		
		/* 112 - pcNuitNmoins1mois02. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois02Numerique03(false);
		
		/* 113 - mjmNmoins1mois03. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois03Numerique03(false);
		
		/* 114 - pcNuitNmoins1mois03. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois03Numerique03(false);
		
		/* 115 - mjmNmoins1mois04. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois04Numerique03(false);
		
		/* 116 - pcNuitNmoins1mois04. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois04Numerique03(false);
		
		/* 117 - mjmNmoins1mois05. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois05Numerique03(false);
		
		/* 118 - pcNuitNmoins1mois05. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois05Numerique03(false);
		
		/* 119 - mjmNmoins1mois06. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois06Numerique03(false);
		
		/* 120 - pcNuitNmoins1mois06. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois06Numerique03(false);
		
		/* 121 - mjmNmoins1mois07. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois07Numerique03(false);
		
		/* 122 - pcNuitNmoins1mois07. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois07Numerique03(false);
		
		/* 123 - mjmNmoins1mois08. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois08Numerique03(false);
		
		/* 124 - pcNuitNmoins1mois08. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois08Numerique03(false);
		
		/* 125 - mjmNmoins1mois09. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois09Numerique03(false);
		
		/* 126 - pcNuitNmoins1mois09. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois09Numerique03(false);
		
		/* 127 - mjmNmoins1mois10. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois10Numerique03(false);
		
		/* 128 - pcNuitNmoins1mois10. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois10Numerique03(false);
		
		/* 129 - mjmNmoins1mois11. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois11Numerique03(false);
		
		/* 130 - pcNuitNmoins1mois11. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois11Numerique03(false);
		
		/* 131 - mjmNmoins1mois12. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Regex02(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitMjmNmoins1mois12Numerique03(false);
		
		/* 132 - pcNuitNmoins1mois12. **************/
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Renseigne01(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Regex02(false);
		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitPcNuitNmoins1mois12Numerique03(false);
		
		/* 133 - zoneLibre4. **************/
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Renseigne01(false);
//		SectionHitGestionnairePreferencesRG.setValiderRGSectionHitZoneLibre4Regex02(false);

	} // Fin de desactiverIntelligentRG()._________________________________
	

	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		System.setProperty("%log4j.skipJansi", "false");
		
		importeur = new ImporteurHit();
		
	} // Fin de beforeClass()._____________________________________________
	
	

} // FIN DE LA CLASSE SectionHitImportLotTest.-------------------------------
