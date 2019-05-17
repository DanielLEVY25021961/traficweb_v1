package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * CLASSE DescriptionChampDarwinCsvTest :<br/>
 * Test JUnit de la classe {@link DescriptionChampDarwinCsv}.<br/>
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
 * @since 16 mai 2019
 *
 */
public class DescriptionChampDarwinCsvTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";

	/**
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * new DescriptionChampDarwinCsv().
	 */
	public static IDescriptionChamp descriptionChamp; 
		
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(DescriptionChampDarwinCsvTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DescriptionChampDarwinCsvTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(null) jette une TableauNullException.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampNull() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampDarwinCsvTest - méthode testLireChampNull() ********** ");
		}
		
		try {
			
			descriptionChamp.lireChamp(null);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(null) jette une TableauNullException. */
			assertTrue("doit être instance de TableauNullException : "
					, e instanceof TableauNullException);
		}
		
	} // Fin de testLireChampNull()._______________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(trop court) jette une ExceptionImport.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampTropCourt() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampDarwinCsvTest - méthode testLireChampTropCourt() ********** ");
		}
		
		try {
			
			final String[] tableauTropCourt = new String[6];
			tableauTropCourt[0] = "3";
			tableauTropCourt[1] = "intitule bidon";
			tableauTropCourt[2] = "nomenclature bidon";
			tableauTropCourt[3] = "champJavaBidon";
			tableauTropCourt[4] = "typeJavaBidon";
			tableauTropCourt[5] = "falseBidon";
			
			descriptionChamp.lireChamp(tableauTropCourt);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(trop court) jette une ExceptionImport. */
			assertTrue("doit être instance de ExceptionImport : "
					, e instanceof ExceptionImport);
		}
		
	} // Fin de testLireChampTropCourt().__________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit que lireChamp(mal renseigné) jette une ExceptionImport.</li> 
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChampMalRenseigne() throws  Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampDarwinCsvTest - méthode testLireChampMalRenseigne() ********** ");
		}
		
		try {
			
			final String[] tableauMalRenseigne = new String[9];

			tableauMalRenseigne[1] = "47";
			tableauMalRenseigne[3] = "bidon";
			tableauMalRenseigne[5] = "champJavaBidon";
			tableauMalRenseigne[6] = "StringBidon";
			tableauMalRenseigne[7] = "falseBidon";
			
			descriptionChamp.lireChamp(tableauMalRenseigne);
			
		} catch (Exception e) {
			
			/* garantit que lireChamp(mal renseigné) jette une ExceptionImport. */
			assertTrue("doit être instance de ExceptionImport : "
					, e instanceof ExceptionImport);
		}
		
	} // Fin de testLireChampMalRenseigne().__________________________________

	
	
	/**
	 * teste la méthode lireChamp(tokens[]).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de lireChamp(tokens[]).</li>
	 * <li>garantit le calcul des champs calculés par lireChamp(tokens[]).</li>
	 * <li>garantit le bon fonctionnement de tokensToString(tokens).</li>
	 * <li>garantit le bon fonctionnement de entetesDescriptionToString()</li>
	 * <li>garantit le bon fonctionnement de valeursDescriptionToString().</li>
	 * <li>garantit le bon fonctionnement de fournirLigneEnTetesCsv().</li>
	 * <li>garantit le bon fonctionnement de fournirLigneValeursCsv().</li>
	 * <li>garantit le bon fonctionnement de getEntetesDescriptionMap().</li>
	 * <li>garantit le bon fonctionnement de getValeursDescriptionMap().</li>
	 * <li>garantit le bon fonctionnement de getOrdreChamps(.</li>
	 * <li>garantit le bon fonctionnement de getIntitule().</li>
	 * <li>garantit le bon fonctionnement de getNomenclature().</li>
	 * <li>garantit le bon fonctionnement de getChampJava().</li>
	 * <li>garantit le bon fonctionnement de getTypeJava().</li>
	 * <li>garantit le bon fonctionnement de isANomenclature().</li>
	 * <li>garantit le bon fonctionnement de isALexique().</li>
	 * <li>garantit le bon fonctionnement de fournirEnteteparColonne(int pI).</li>
	 * <li>garantit le bon fonctionnement de fournirValeurparColonne(int pI).</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireChamp() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DescriptionChampDarwinCsvTest - méthode testLireChamp() ********** ");
		}
		
		final String[] tokens = {"4", "Code Concession du PR Origine", "Code Concession du PR Origine ('début')", "concessionPrd", "String", "false", "true"};
		
		/* TEST. */
		descriptionChamp.lireChamp(tokens);
		
		final Map<Integer, String> entetesDescriptionMap 
			= descriptionChamp.getEntetesDescriptionMap();
		
		final Map<Integer, String> valeursDescriptionMap 
			= descriptionChamp.getValeursDescriptionMap();
		
		final String entetesString = descriptionChamp.entetesDescriptionToString();
		final String valeursString = descriptionChamp.valeursDescriptionToString();
		final String entetesCsv = descriptionChamp.fournirLigneEnTetesCsv();
		final String valeursCsv = descriptionChamp.fournirLigneValeursCsv();
		
		/* attributs. */
		final Integer ordreChamps = descriptionChamp.getOrdreChamps();
		final String intitule = descriptionChamp.getIntitule();
		final String nomenclature = descriptionChamp.getNomenclature();
		final String champJava = descriptionChamp.getChampJava();
		final String typeJava = descriptionChamp.getTypeJava();
		final boolean aNomenclature = descriptionChamp.isANomenclature();
		final boolean aLexique = descriptionChamp.isALexique();		
		
		final String fournirEnteteparColonne0 = descriptionChamp.fournirEnteteparColonne(0);
		final String fournirEnteteparColonne1 = descriptionChamp.fournirEnteteparColonne(1);
		final String fournirEnteteparColonne2 = descriptionChamp.fournirEnteteparColonne(2);
		final String fournirEnteteparColonne3 = descriptionChamp.fournirEnteteparColonne(3);
		
		final String fournirValeurparColonne0 = descriptionChamp.fournirValeurparColonne(0);
		final String fournirValeurparColonne1 = descriptionChamp.fournirValeurparColonne(1);
		final String fournirValeurparColonne2 = descriptionChamp.fournirValeurparColonne(2);
		final String fournirValeurparColonne3 = descriptionChamp.fournirValeurparColonne(3);
		final String fournirValeurparColonne4 = descriptionChamp.fournirValeurparColonne(4);
		final String fournirValeurparColonne5 = descriptionChamp.fournirValeurparColonne(5);
		final String fournirValeurparColonne6 = descriptionChamp.fournirValeurparColonne(6);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println("tokensToString(tokens) : " + descriptionChamp.tokensToString(tokens));
			System.out.println("descriptionChampToString : " + descriptionChamp.descriptionChampToString());
			
			System.out.println();
			System.out.println("entetesString : " + entetesString);
			System.out.println("valeursString : " + valeursString);
			System.out.println("entetesCsv : " + entetesCsv);
			System.out.println("valeursCsv : " + valeursCsv);
			
			System.out.println();
			System.out.println("entetesDescriptionMap : " + entetesDescriptionMap);
			System.out.println("valeursDescriptionMap : " + valeursDescriptionMap);
			
			System.out.println();
			System.out.println("ordreChamps : " + ordreChamps);
			System.out.println("intitule : " + intitule);
			System.out.println("nomenclature : " + nomenclature);
			System.out.println("champJava : " + champJava);
			System.out.println("typeJava : " + typeJava);
			System.out.println("aNomenclature : " + aNomenclature);
			System.out.println("aLexique : " + aLexique);
			
			System.out.println();
			System.out.println("fournirEnteteparColonne0 : " + fournirEnteteparColonne0);
			System.out.println("fournirEnteteparColonne1 : " + fournirEnteteparColonne1);
			System.out.println("fournirEnteteparColonne2 : " + fournirEnteteparColonne2);
			System.out.println("fournirEnteteparColonne3 : " + fournirEnteteparColonne3);
			
			System.out.println();
			System.out.println("fournirValeurparColonne0 : " + fournirValeurparColonne0);
			System.out.println("fournirValeurparColonne1 : " + fournirValeurparColonne1);
			System.out.println("fournirValeurparColonne2 : " + fournirValeurparColonne2);
			System.out.println("fournirValeurparColonne3 : " + fournirValeurparColonne3);
			System.out.println("fournirValeurparColonne4 : " + fournirValeurparColonne4);
			System.out.println("fournirValeurparColonne5 : " + fournirValeurparColonne5);
			System.out.println("fournirValeurparColonne6 : " + fournirValeurparColonne6);
			
		}
		
		/* garantit le bon fonctionnement de lireChamp(tokens[]). */
		/* garantit le calcul des champs calculés par lireChamp(tokens[]). */
		
		/* garantit le bon fonctionnement de tokensToString(tokens).*/
		assertEquals(
				"bon fonctionnement de tokensToString(tokens) : "
				, "4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;true;"
				, descriptionChamp.tokensToString(tokens));
		
		/* garantit le bon fonctionnement de entetesDescriptionToString()). */
		assertEquals(
				"bon fonctionnement de entetesDescriptionToString() : "
				, "[ordreChamps, intitulé, nomenclature, champJava, typeJava, aNomenclature, aLexique]"
				, entetesString);
		
		/* garantit le bon fonctionnement de valeursDescriptionToString()). */
		assertEquals(
				"bon fonctionnement de valeursDescriptionToString() : "
				, "[ordreChamps = 4, intitulé = Code Concession du PR Origine, nomenclature = Code Concession du PR Origine ('début'), champJava = concessionPrd, typeJava = String, aNomenclature = false, aLexique = true]"
				, valeursString);
		
		/* garantit le bon fonctionnement de fournirLigneEnTetesCsv()). */
		assertEquals(
				"bon fonctionnement de fournirLigneEnTetesCsv() : "
				, "ordreChamps;intitulé;nomenclature;champJava;typeJava;aNomenclature;aLexique;"
				, entetesCsv);
		
		/* garantit le bon fonctionnement de fournirLigneValeursCsv()). */
		assertEquals(
				"bon fonctionnement de fournirLigneValeursCsv() : "
				, "4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;true;"
				, valeursCsv);
		
		/* garantit le bon fonctionnement de getEntetesDescriptionMap() : */
		assertNotNull("bon fonctionnement de getEntetesDescriptionMap() : "
				, entetesDescriptionMap);
		
		/* garantit le bon fonctionnement de getValeursDescriptionMap() : */
		assertNotNull("bon fonctionnement de getValeursDescriptionMap() : "
				, valeursDescriptionMap);
		
		/* garantit le bon fonctionnement de getOrdreChamps(). */
		assertEquals(
			"bon fonctionnement de getOrdreChamps() : "
			, (Integer) 4
			, ordreChamps);
		
		/* garantit le bon fonctionnement de getIntitule(). */
		assertEquals(
			"bon fonctionnement de getIntitule() : "
			, "Code Concession du PR Origine"
			, intitule);
		
		/* garantit le bon fonctionnement de getNomenclature(). */
		assertEquals(
			"bon fonctionnement de getNomenclature() : "
			, "Code Concession du PR Origine ('début')"
			, nomenclature);
		
		/* garantit le bon fonctionnement de getChampJava(). */
		assertEquals(
			"bon fonctionnement de getChampJava() : "
			, "concessionPrd"
			, champJava);
		
		/* garantit le bon fonctionnement de getTypeJava(). */
		assertEquals(
			"bon fonctionnement de getTypeJava() : "
			, "String"
			, typeJava);
		
		/* garantit le bon fonctionnement de isANomenclature(). */
		assertEquals(
			"bon fonctionnement de isANomenclature() : "
			, false
			, aNomenclature);
		
		/* garantit le bon fonctionnement de isALexique(). */
		assertEquals(
			"bon fonctionnement de isALexique() : "
			, true
			, aLexique);
		
		/* garantit le bon fonctionnement de fournirEnteteparColonne(int pI). */
		assertNull(DOIT_RETOURNER_NULL
				, fournirEnteteparColonne0);
		assertEquals(
				"bon fonctionnement de fournirEnteteparColonne(1) : "
				, "ordreChamps"
				, fournirEnteteparColonne1);
		assertEquals(
				"bon fonctionnement de fournirEnteteparColonne(2) : "
				, "intitulé"
				, fournirEnteteparColonne2);
		assertEquals(
				"bon fonctionnement de fournirEnteteparColonne(3) : "
				, "nomenclature"
				, fournirEnteteparColonne3);
		
		/* garantit le bon fonctionnement de fournirValeurparColonne(int pI). */
		assertNull(DOIT_RETOURNER_NULL
				, fournirValeurparColonne0);
		assertEquals(
				"bon fonctionnement de fournirValeurparColonne(1) : "
				, "4"
				, fournirValeurparColonne1);
		assertEquals(
				"bon fonctionnement de fournirValeurparColonne(2) : "
				, "Code Concession du PR Origine"
				, fournirValeurparColonne2);
		assertEquals(
				"bon fonctionnement de fournirValeurparColonne(3) : "
				, "Code Concession du PR Origine ('début')"
				, fournirValeurparColonne3);

	} // Fin de testLireChamp().___________________________________________


	
	/**
	 * réalisé avant tous les tests de la classe.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		descriptionChamp = new DescriptionChampDarwinCsv();
		
	} // Fin de beforeClass()._____________________________________________

	
	
} // FIN DE LA CLASSE DescriptionChampDarwinCsvTest.------------------------_
