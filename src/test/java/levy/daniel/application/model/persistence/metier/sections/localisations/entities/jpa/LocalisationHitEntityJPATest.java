package levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;

/**
 * CLASSE LocalisationHitEntityJPATest :<br/>
 * Test JUnit de la classe {@link LocalisationHitEntityJPA}.<br/>
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
 * @since 6 juil. 2019
 *
 */
public class LocalisationHitEntityJPATest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "0006".
	 */
	public static final String NUM_ROUTE_0006 = "0006";
	
	/**
	 * "730".
	 */
	public static final String NUM_DEPT_73 = "730";

	/**
	 * "Origine1".
	 */
	public static final String LIEU_DIT_ORIGINE_1 = "Origine1";
	
	/**
	 * "Extremite1".
	 */
	public static final String LIEU_DIT_EXTREMITE_1 = "Extremite1";
	
	/**
	 * "comptage1".
	 */
	public static final String LIEU_DIT_COMPTAGE_1 = "comptage1";
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";

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
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * Path absolu du présent projet Eclipse.<br/>
	 * <code>Paths.get(".").toAbsolutePath().normalize()</code>.<br/>
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
	 * "objet1 : ".<br/>
	 */
	public static final String OBJET1 = "objet1 : ";
	
	/**
	 * "objetNull1 : ".<br/>
	 */
	public static final String OBJETNULL1 = "objetNull1 : ";
	
	/**
	 * "objet1.equals(objet2EqualsObj1) : ".<br/>
	 */
	public static final String OBJET1_EQUALS_OBJET2 
		= "objet1.equals(objet2EqualsObj1) : ";

	/**
	 * DTO a tester.
	 */
	public static  ISectionHitDTO dto;

	/**
	 * OBJET METIER a tester.
	 */
	public static ISectionHit objetMetier;
	
	/**
	 * ILocalisationHit.<br/>
	 */
	public static ILocalisationHit localisation;

	/**
	 * SectionHit sens1.
	 */
	public static ISectionHit section1;

	/**
	 * SectionHit sens2.
	 */
	public static ISectionHit section2;

	/**
	 * SectionHit sens3.
	 */
	public static ISectionHit section3;
	
	/**
	 * ILocalisationHit sens1.<br/>
	 */
	public static ILocalisationHit localisationChaunay1;
	
	/**
	 * ILocalisationHit sens2.<br/>
	 */
	public static ILocalisationHit localisationChaunay2;
	
	/**
	 * ILocalisationHit sens2.<br/>
	 */
	public static ILocalisationHit localisationChaunay3;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ILocalisationHit objet1; 
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient ILocalisationHit objet1MemeInstance;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ILocalisationHit objet2EqualsObj1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient ILocalisationHit objet3EqualsObj1;
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ILocalisationHit objetNull1;
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient ILocalisationHit objetNull2;
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ILocalisationHit objet1AvecNull;
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient ILocalisationHit objet2EqualsObjet1AvecNull;
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient ILocalisationHit objetDiff1;
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient ILocalisationHit objetDiff2;
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient ILocalisationHit objetDiff1AvecNull;
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient ILocalisationHit objetDiff2AvecNull;
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient ILocalisationHit objetCompAvant;
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient ILocalisationHit objetCompApres;
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient ILocalisationHit objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient ILocalisationHit objetClone1;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalisationHitEntityJPATest.class);


	// *************************METHODES************************************/
	
		
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LocalisationHitEntityJPATest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * Teste la méthode <b>equals(Object pObject)</b> :
	 * <ul>
	 * <li>garantit le contrat Java reflexif x.equals(x).</li>
	 * <li>garantit le contrat Java symétrique 
	 * x.equals(y) ----> y.equals(x).</li>
	 * <li>garantit le contrat Java transitif 
	 * x.equals(y) et y.equals(z) ----> x.equals(z).</li>
	 * <li>garantit le contrat Java sur les hashcode 
	 * x.equals(y) ----> x.hashcode() == y.hashcode().</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans equals(Object pObj).</li>
	 * <li>garantit que x.equals(null) retourne false 
	 * (avec x non null).</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'égalité métier.</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'inégalité métier.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEquals() {
					
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testEquals() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 3 Objets equals ----------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println("objet2EqualsObj1.toString() : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1.toString() : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet1) : " + objet1.equals(objet1));
			System.out.println(OBJET1_EQUALS_OBJET2 + objet1.equals(objet2EqualsObj1));
			System.out.println("objet2EqualsObj1.equals(objet1) : " + objet2EqualsObj1.equals(objet1));
			System.out.println("objet2EqualsObj1.equals(objet3EqualsObj1) : " + objet2EqualsObj1.equals(objet3EqualsObj1));
			System.out.println("objet1.equals(objet3EqualsObj1) : " + objet1.equals(objet3EqualsObj1));
			System.out.println("objet1.hashCode() == objet2EqualsObj1.hashCode() : " + (objet1.hashCode() == objet2EqualsObj1.hashCode()));
		}
		
		
		/* garantit le contrat Java reflexif x.equals(x). */
		assertEquals("x.equals(x) : "
				, objet1
					, objet1);
				
		/* garantit le contrat Java symétrique 
		 * x.equals(y) ----> y.equals(x). */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		
		assertEquals("objet2EqualsObj1.equals(objet1) : "
				, objet2EqualsObj1
					, objet1);
		
		/* garantit le contrat Java transitif 
		 * x.equals(y) et y.equals(z) ----> x.equals(z). */
		assertEquals(OBJET1_EQUALS_OBJET2, objet1, objet2EqualsObj1);
		assertEquals("objet2EqualsObj1.equals(objet3EqualsObj1) : ", objet2EqualsObj1, objet3EqualsObj1);
		assertEquals("objet1.equals(objet3EqualsObj1) : ", objet1, objet3EqualsObj1);
		
		/* garantit le contrat Java sur les hashcode 
		 * x.equals(y) ----> x.hashcode() == y.hashcode(). */
		assertEquals("objet1.hashCode().equals(objet2EqualsObj1.hashCode()) : "
				, objet1.hashCode()
					, objet2EqualsObj1.hashCode());
	
				
		/* garantit que les null sont bien gérés dans equals(...). */
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou attributs par défaut) -------");
			System.out.println("OBJETNULL1 : " 
					+ objetNull1.toString());
			System.out.println("OBJETNULL2 : " 
					+ objetNull2.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNull2) : " + objetNull1.equals(objetNull2));
			System.out.println("objetNull1.hashCode().equals(objetNull2.hashCode()) : " + (objetNull1.hashCode() == objetNull2.hashCode()));
		}
	
		assertEquals("objetNull1.equals(objetNull2) : "
				, objetNull1
					, objetNull2);
		assertEquals("objetNull1.hashCode().equals(objetNull2.hashCode()) : "
				, objetNull1.hashCode()
					, objetNull2.hashCode());
		
	
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS AVEC CERTAINS ATTRIBUTS NULL -----------");
			System.out.println("objet1AvecNull : " 
					+ objet1AvecNull.toString());
			System.out.println("objet2EqualsObjet1AvecNull : " 
					+ objet2EqualsObjet1AvecNull.toString());
			System.out.println();
			System.out.println("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : " + objet1AvecNull.equals(objet2EqualsObjet1AvecNull));
			System.out.println("objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()" + (objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()));
		}
	
		assertEquals("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : "
				, objet1AvecNull
					, objet2EqualsObjet1AvecNull);
		assertEquals("objet1AvecNull.hashCode()"
				+ ".equals(objet2EqualsObjet1AvecNull.hashCode()) : "
				, objet1AvecNull.hashCode()
					, objet2EqualsObjet1AvecNull.hashCode());
	
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJET EXISTANT EQUALS null --------------------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println();
			System.out.println("objet1.equals(null) : " + (objet1 == null));
		}
		
		/* garantit que x.equals(null) retourne false (avec x non null). */
		assertNotNull("objet1 pas equals(null) : "
				, objet1);
	
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'EGALITE METIER --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objet2EqualsObj1 : " 
					+ objet2EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet2EqualsObj1) : " + objet1.equals(objet2EqualsObj1));
		}
	
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'égalité métier. */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) ---------------------");
			System.out.println("objetDiff1 : " 
					+ objetDiff1.toString());
			System.out.println("objetDiff2 : " 
					+ objetDiff2.toString());
			System.out.println();
			System.out.println("objetDiff1.equals(objetDiff2) : " + objetDiff1.equals(objetDiff2));
			System.out.println("objetDiff1.hashcode() == objetDiff2.hashcode() : " + (objetDiff1.hashCode() == objetDiff2.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1 PAS equals(objetDiff2) : "
				, objetDiff1.equals(objetDiff2));
		assertFalse("objetDiff1.hashCode() "
				+ "PAS equals(objetDiff2.hashCode()) : "
				, objetDiff1.hashCode() == objetDiff2.hashCode());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) avec des attributs null---------------------");
			System.out.println("objetDiff1AvecNull : " 
					+ objetDiff1AvecNull.toString());
			System.out.println("objetDiff2AvecNull : " 
					+ objetDiff2AvecNull.toString());
			System.out.println();
			System.out.println("objetDiff1AvecNull.equals(objetDiff2AvecNull) : " + objetDiff1AvecNull.equals(objetDiff2AvecNull));
			System.out.println("objetDiff1AvecNull.hashcode() == objetDiff2AvecNull.hashcode() : " + (objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1AvecNull PAS equals(objetDiff2AvecNull) : "
				, objetDiff1AvecNull.equals(objetDiff2AvecNull));
		assertFalse("objetDiff1AvecNull.hashCode() "
				+ "PAS equals(objetDiff2AvecNull.hashCode()) : "
				, objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode());
				
	} // Fin de testEquals().______________________________________________
	
	
	
	/**
	 * Teste la méthode <b>compareTo(...)</b> :
	 * <ul>
	 * <li>garantit que compareTo(memeInstance) retourne 0.</li>
	 * <li>garantit que compareTo(null) retourne un nombre négatif.</li>
	 * <li>garantit le contrat Java Contrat Java : 
	 * x.equals(y) ---> x.compareTo(y) == 0.</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans compareTo(...).</li>
	 * <li>garantit le bon fonctionnement (bon ordre) de compareTo().</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareTo() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testCompareTo() ********** ");
		}
	
		
		/* garantit que compareTo(memeInstance) retourne 0. */		
		final int compareMemeInstance 
			= objet1.compareTo(objet1MemeInstance);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 MEMES INSTANCES ------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println("objet1MemeInstance : " + objet1MemeInstance.toString());
			System.out.println();
			System.out.println("COMPARETO() DE LA MEME INSTANCE (objet1.compareTo(objet1MemeInstance)) : " 
					+ compareMemeInstance);
		}
		
		assertTrue("compareTo(memeInstance) doit retourner 0 : "
				, compareMemeInstance == 0);
	
		
		/* garantit que compareTo(null) retourne -1. */
		final int compareToNull = objet1.compareTo(null);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE objet1 avec null ---------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("COMPARETO(null) (objet1.compareTo(null)) : " 
					+ compareToNull);
		}
		
		assertTrue("compareTo(null) doit retourner négatif : "
				, compareToNull < 0);
	
		
		/* garantit le contrat Java Contrat Java : 
		 * x.equals(y) ---> x.compareTo(y) == 0. */		
		final int compareToEquals 
			= objet2EqualsObj1.compareTo(objet3EqualsObj1);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 objets equals ---------------------");
			System.out.println("objet2EqualsObj1 : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1 : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets equals)  (objet2EqualsObj1.compareTo(objet3EqualsObj1)) : " 
					+ compareToEquals);
		}
	
		assertTrue("Instance.compareTo(equalsInstance) doit retourner 0 : "
				, compareToEquals == 0);
		assertTrue("loc10.hashCode() == loc11.hashCode() : "
				, objet2EqualsObj1.hashCode() == objet3EqualsObj1.hashCode());
		
		
		
		/* garantit que les null sont bien gérés dans 
		 * compareTo(...). */		
		final int compareToEqualsNull 
			= objetNull1.compareTo(objetNull2);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou valeurs par défaut) ------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println("objetNull2 : " + objetNull2.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets instanciés avec le constructeur d'arite nulle)  (objetNull1.compareTo(objetNull2)) : " 
					+ compareToEqualsNull);
		}
		
		assertTrue("InstanceNull.compareTo(equalsInstanceNull) doit retourner 0 : "
				, compareToEqualsNull == 0);
		assertTrue("locNull10.hashCode() == locNull11.hashCode() : "
				, objetNull1.hashCode() == objetNull2.hashCode());
		
	
		
		/* garantit le bon fonctionnement (bon ordre) de compareTo(). */		
		final int compare 
			= objetCompAvant.compareTo(objetCompApres);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets différents -------------");
			System.out.println("objetCompAvant : " + objetCompAvant.toString());
			System.out.println("objetCompApres : " + objetCompApres.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets différents) (objetCompAvant.compareTo(this.objetCompApres) : " 
					+ compare);
		}
		
		assertTrue("objetCompAvant doit être avant objetCompApres : "
				, compare < 0);
		
	} // Fin de testCompareTo().___________________________________________
	
	
	
	/**
	 * Teste la méthode <b>clone()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans clone().</li>
	 * <li>garantit que clonex.equals(x).</li>
	 * <li>garantit que x et son clone ne sont pas la même instance.</li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testClone() throws CloneNotSupportedException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testClone() ********** ");
		}
	
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJET INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE ---------------------");
			System.out.println(OBJETNULL1 
					+ objetNull1.toString());
			System.out.println("objetNullClone1 : " 
					+ objetNullClone1.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNullClone1) : " + objetNull1.equals(objetNullClone1));
		}
		
		/* garantit que les null sont bien gérés dans clone(). */
		assertEquals("objetNull1.equals(objetNullClone1) : "
				, objetNull1
					, objetNullClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJETS ORDINAIREMENT INSTANCIES --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1.equals(objetClone1) : " + objet1.equals(objetClone1));
		}
		
		/* garantit que clone(x).equals(x). */
		assertEquals("clone(x).equals(x) : "
				, objet1
					, objetClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INSTANCE ET CLONE DIFFERENTS --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1 == objetClone1 : " + (objet1 == objetClone1));    
		}
		
		/* garantit que x et son clone ne sont pas la même instance. */
		assertNotSame("x != clonex : "
				, objet1
					, objetClone1);
				
	} // Fin de testClone()._______________________________________________
	
	
		
	/**
	 * Teste la méthode <b>toString()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans toString().</li>
	 * <li>garantit le bon affichage de toString().</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testToString() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testToString() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objetNull1.toString());
		}
		
		/* garantit que les null sont bien gérés dans toString(). */
		assertEquals("objetNull1.toString() retourne une chaine : "
				, "LocalisationHitEntityJPA [id=null, numRoute=null, indiceNumRoute=null, indiceLettreRoute=null, categorieAdminRoute=null, numDepartement=null, lieuDitOrigine=null, prOrigine=null, absOrigine=null, lieuDitExtremite=null, prExtremite=null, absExtremite=null, lieuDitComptage=null, prComptage=null, absComptage=null]"
						, objetNull1.toString());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet1.toString());
		}
		
		/* garantit le bon affichage de toString(). */
		assertEquals("affichage : "
				, "LocalisationHitEntityJPA [id=null, numRoute=0010, indiceNumRoute=0, indiceLettreRoute= , categorieAdminRoute=4, numDepartement=860, lieuDitOrigine=CHAUNAY EX RN 10    , prOrigine=100, absOrigine=110, lieuDitExtremite=DEPARTEMENT 79      , prExtremite=107, absExtremite=118, lieuDitComptage=CHAUNAY             , prComptage=106, absComptage=430]"
						, objet1.toString());
				
	} // Fin de testToString().____________________________________________
	
	
	
	/**
	 * teste la méthode fournirEnTeteCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirEnTeteCsv() ne retourne pas null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testFournirEnTeteCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteCsv = null;
		
		if (localisation != null) {
			
			enteteCsv = localisation.fournirEnTeteCsv();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(enteteCsv);
			}
			
			/* garantit que fournirEnTeteCsv() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, enteteCsv);
	
		}
		
	} // Fin de testFournirEnTeteCsv().____________________________________
	
	
	
	/**
	 * teste la méthode fournirStringCsv().<br/>
	 * <ul>
	 * <li>garantit que fournirStringCsv() ne retourne pas null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirStringCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testFournirStringCsv() ********** ");
		}
		
		// METHODE A TESTER.
		String stringCsvDTO = null;
		String stringCsvObjet = null;
		String stringCsvLocalisation = null;
		
		if (localisation != null) {
			
			stringCsvDTO = dto.fournirStringCsv();
			stringCsvObjet = objetMetier.fournirStringCsv();
			stringCsvLocalisation = localisation.fournirStringCsv();
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(stringCsvDTO);
				System.out.println(stringCsvObjet);
				System.out.println(stringCsvLocalisation);
			}
			
			/* garantit que fournirStringCsv() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsvDTO);
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsvObjet);
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, stringCsvLocalisation);
			
		}
		
	} // Fin de testFournirStringCsv().____________________________________
	
	
	
	/**
	 * teste la méthode fournirEnTeteColonne().<br/>
	 * <ul>
	 * <li>garantit que fournirEnTeteColonne(hors index) retourne "invalide".</li>
	 * <li>garantit que fournirEnTeteColonne(i) ne retourne pas null.</li>
	 * <li>garantit que fournirEnTeteColonne(i) retourne la bonne entete.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteColonne() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String enteteColonne0 = null;
		String enteteColonne1 = null;
		String enteteColonne2 = null;
		String enteteColonne3 = null;
		String enteteColonne8 = null;
		String enteteColonne14 = null;
		String enteteColonne15 = null;
		
		if (localisation != null) {
			
			enteteColonne0 = (String) localisation.fournirEnTeteColonne(0);
			enteteColonne1 = (String) localisation.fournirEnTeteColonne(1);
			enteteColonne2 = (String) localisation.fournirEnTeteColonne(2);
			enteteColonne3 = (String) localisation.fournirEnTeteColonne(3);
			enteteColonne8 = (String) localisation.fournirEnTeteColonne(8);
			enteteColonne14 = (String) localisation.fournirEnTeteColonne(14);
			enteteColonne15 = (String) localisation.fournirEnTeteColonne(15);
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("enteteColonne0 : " + enteteColonne0);
				System.out.println("enteteColonne1 : " + enteteColonne1);
				System.out.println("enteteColonne2 : " + enteteColonne2);
				System.out.println("enteteColonne3 : " + enteteColonne3);
				System.out.println("enteteColonne8 : " + enteteColonne8);
				System.out.println("enteteColonne14 : " + enteteColonne14);				
				System.out.println("enteteColonne15 : " + enteteColonne15);
			}
			
			/* garantit que fournirEnTeteColonne() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, enteteColonne1);
			
			/* garantit que fournirEnTeteColonne(hors index) retourne "invalide". */
			assertEquals(DOIT_RETOURNER_INVALIDE
					, "invalide"
						, enteteColonne15);
			
			/* garantit que fournirEnTeteColonne(i) retourne la bonne entete. */
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "id"
						, enteteColonne0);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "numRoute"
						, enteteColonne1);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "indiceNumRoute"
						, enteteColonne2);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "indiceLettreRoute"
						, enteteColonne3);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, "absOrigine"
						, enteteColonne8);
			
		}
		
	} // Fin de testFournirEnTeteColonne().________________________________
	
	
	
	/**
	 * teste la méthode fournirValeurColonne().<br/>
	 * <ul>
	 * <li>garantit que fournirValeurColonne(hors index) retourne "invalide".</li>
	 * <li>garantit que fournirValeurColonne(i) ne retourne pas null.</li>
	 * <li>garantit que fournirValeurColonne(i) retourne la bonne valeur.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurColonne() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE LocalisationHitEntityJPATest - méthode testFournirValeurColonne() ********** ");
		}
		
		// METHODE A TESTER.
		String valeurColonne0 = null;
		String valeurColonne1 = null;
		String valeurColonne2 = null;
		String valeurColonne3 = null;
		String valeurColonne8 = null;
		String valeurColonne14 = null;
		String valeurColonne15 = null;
		
		if (localisation != null) {
			
			valeurColonne0 = (String) localisation.fournirValeurColonne(0);
			valeurColonne1 = (String) localisation.fournirValeurColonne(1);
			valeurColonne2 = (String) localisation.fournirValeurColonne(2);
			valeurColonne3 = (String) localisation.fournirValeurColonne(3);
			valeurColonne8 = (String) localisation.fournirValeurColonne(8);
			valeurColonne14 = (String) localisation.fournirValeurColonne(14);
			valeurColonne15 = (String) localisation.fournirValeurColonne(15);
						
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("valeurColonne0 : " + valeurColonne0);
				System.out.println("valeurColonne1 : " + valeurColonne1);
				System.out.println("valeurColonne2 : " + valeurColonne2);
				System.out.println("valeurColonne3 : " + valeurColonne3);
				System.out.println("valeurColonne8 : " + valeurColonne8);
				System.out.println("valeurColonne14 : " + valeurColonne14);				
				System.out.println("valeurColonne15 : " + valeurColonne15);
			}
			
			/* garantit que fournirValeurColonne() ne retourne pas null. */
			assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, valeurColonne1);
			
			/* garantit que fournirValeurColonne(hors index) retourne "invalide". */
			assertEquals(DOIT_RETOURNER_INVALIDE
					, "invalide"
						, valeurColonne15);
			
			/* garantit que fournirValeurColonne(i) retourne la bonne valeur. */
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, localisation.getId()
						, valeurColonne0);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, localisation.getNumRoute()
						, valeurColonne1);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, localisation.getIndiceNumRoute()
						, valeurColonne2);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, localisation.getIndiceLettreRoute()
						, valeurColonne3);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, String.valueOf(localisation.getAbsOrigine())
						, valeurColonne8);
			
			assertEquals(DOIT_RETOURNER_BONNE_VALEUR
					, String.valueOf(localisation.getAbsComptage())
						, valeurColonne14);
			
		}
		
	} // Fin de testFournirValeurColonne().________________________________
	
	
	
	/**
	 * Exécuté avant tout test de la méthode.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("HITDIRA2017.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");
		
		// OBJET A TESTER.
		final ImporteurHit importeurHIT = new ImporteurHit();
		
		final Map<Integer, ISectionHit> fichierMapObjet 
			= importeurHIT.importerObjet(fichierDonnees, charsetAnsi);
		
		final SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap 
			= importeurHIT.getFichierImporteMap();
		
		final SortedMap<Integer, String> ligneMap = fichierImporteMap.get(1);
		
		final ISectionHit objetMap = new SectionHit(ligneMap);
		
		objetMetier = fichierMapObjet.get(1);
		localisation = new LocalisationHitEntityJPA(ligneMap);
		
		final Map<Integer, ISectionHitDTO> fichierMapDTO 
			= importeurHIT.importerDTO(fichierDonnees, charsetAnsi);
		
		final ISectionHitDTO dtoMap = new SectionHitDTO(ligneMap);
		
		dto = fichierMapDTO.get(1);
		
		assertEquals("doivent être égaux : ", dto, dtoMap);
	
		
		assertEquals("doivent être égaux : ", objetMetier, objetMap);
		
		section1 = new SectionHit(fichierImporteMap.get(1));
		section2 = new SectionHit(fichierImporteMap.get(2));
		section3 = new SectionHit(fichierImporteMap.get(3));
		
		localisationChaunay1 = new LocalisationHitEntityJPA(fichierImporteMap.get(1));
		localisationChaunay2 = new LocalisationHitEntityJPA(fichierImporteMap.get(2));
		localisationChaunay3 = new LocalisationHitEntityJPA(fichierImporteMap.get(3));
		
		objet1 = localisationChaunay1;
		objet1MemeInstance = objet1;
		objet2EqualsObj1 = localisationChaunay2;
		objet3EqualsObj1 = localisationChaunay3;
		
		objetNull1 = new LocalisationHitEntityJPA(null
				, null, null, null, null
				, null, null, null, null
				, null, null, null
				, null, null, null);
		
		objetNull2 = new LocalisationHitEntityJPA();
		
		objet1AvecNull = new LocalisationHitEntityJPA(null
				, "0020", null, null, "1"
				, NUM_DEPT_73
				, LIEU_DIT_ORIGINE_1, 7, 152
				, LIEU_DIT_EXTREMITE_1, 7, 777
				, LIEU_DIT_COMPTAGE_1, 7, 200);
		
		objet2EqualsObjet1AvecNull = new LocalisationHitEntityJPA(null
				, "0020", null, null, "1"
				, NUM_DEPT_73
				, "Origine2", 7, 152
				, "Extremite2", 7, 777
				, "comptage2", 7, 207);
		
		objetDiff1 = new LocalisationHitEntityJPA(null
				, "0086", "", "", "1"
				, NUM_DEPT_73
				, LIEU_DIT_ORIGINE_1, 7, 152
				, LIEU_DIT_EXTREMITE_1, 7, 777
				, LIEU_DIT_COMPTAGE_1, 7, 200);
		
		objetDiff2 = new LocalisationHitEntityJPA(null
				, NUM_ROUTE_0006, "", "", "1"
				, NUM_DEPT_73
				, LIEU_DIT_ORIGINE_1, 7, 152
				, LIEU_DIT_EXTREMITE_1, 7, 777
				, LIEU_DIT_COMPTAGE_1, 7, 200); 
		
		objetDiff1AvecNull = new LocalisationHitEntityJPA(null
				, "0086", null, null, "1"
				, NUM_DEPT_73
				, LIEU_DIT_ORIGINE_1, null, 152
				, LIEU_DIT_EXTREMITE_1, 7, 777
				, LIEU_DIT_COMPTAGE_1, 7, 200);
		
		objetDiff2AvecNull = new LocalisationHitEntityJPA(null
				, NUM_ROUTE_0006, null, null, "1"
				, NUM_DEPT_73
				, LIEU_DIT_ORIGINE_1, 7, 152
				, LIEU_DIT_EXTREMITE_1, null, null
				, LIEU_DIT_COMPTAGE_1, 7, 200); 
		
		objetCompAvant = new LocalisationHitEntityJPA(
				null
				, NUM_ROUTE_0006, "", "", "1", NUM_DEPT_73
				, "", 7, 0
				, "", 8, 0
				, "", null, null);
		
		objetCompApres = new LocalisationHitEntityJPA(
				null
				, NUM_ROUTE_0006, "", "", "1", NUM_DEPT_73
				, "", 8, 0
				, "", 9, 0
				, "", null, null);
		
		objetNullClone1 = objetNull1.clone();
		objetClone1 = objet1.clone();
		
	} // Fin de beforeClass()._____________________________________________
	
	
		
} // FIN DE LA CLASSE LocalisationHitEntityJPATest.--------------------------
