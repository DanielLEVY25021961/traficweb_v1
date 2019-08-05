package levy.daniel.application.model.persistence.metier.sections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.persistence.metier.sections.entities.jpa.SectionHitEntityJPA;
import levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa.LocalisationHitEntityJPA;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl.ImporteurHit;

/**
 * CLASSE SectionHitConvertisseurMetierEntityTest :<br/>
 * Test JUnit de la classe {@link SectionHitConvertisseurMetierEntity}.<br/>
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
public class SectionHitConvertisseurMetierEntityTest {

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
	 * ENTITY JPA a tester.
	 */
	public static ISectionHit entityJPA;
	
	/**
	 * SectionHitEntityJPA.<br/>
	 */
	public static SectionHitEntityJPA entity1;
	
	/**
	 * SectionHitEntityJPA.<br/>
	 */
	public static SectionHitEntityJPA entity3;

	/**
	 * OBJET METIER a tester.
	 */
	public static ISectionHit objetMetier;
	
	/**
	 * SectionHit.<br/>
	 */
	public static SectionHit objet1;
	
	/**
	 * SectionHit.<br/>
	 */
	public static SectionHit objet3;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitConvertisseurMetierEntityTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitConvertisseurMetierEntityTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste la méthode creerObjetMetierAPartirEntityJPA(Entity).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que creerObjetMetierAPartirEntityJPA(entityNull) 
	 * retourne un objet avec toutes les valeurs à null.</li>
	 * <li>garantit que creerObjetMetierAPartirEntityJPA(entity) 
	 * retourne un objet métier correspondant.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerObjetMetierAPartirEntityJPA() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testCreerObjetMetierAPartirEntityJPA() ********** ");
		}

		/* valeur null. */
		final SectionHitEntityJPA entityNull = null;
		
		final ISectionHit objetNull 
			= SectionHitConvertisseurMetierEntity
				.creerObjetMetierAPartirEntityJPA(entityNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Entity JPA null");
			System.out.println(objetNull.toString());
		}

		/* garantit que creerObjetMetierAPartirEntityJPA(entityNull) 
		 * retourne un objet avec toutes les valeurs à null. */
		assertEquals("creerObjetMetierAPartirEntityJPA(entityNull) "
				+ "doit retourner un objet instancié par "
				+ "le constructeur d'arité nulle : "
				, new SectionHit(), objetNull);
		
		
		/* valeur existante. */
		final ISectionHit objet 
			= SectionHitConvertisseurMetierEntity
				.creerObjetMetierAPartirEntityJPA((SectionHitEntityJPA) entityJPA);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entityJPA.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entityJPA
						, objet);
		
	} // Fin de testCreerObjetMetierAPartirEntityJPA().____________________


	
	/**
	 * teste la méthode convertirEntityJPAEnObjetMetier(Entity).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que convertirEntityJPAEnObjetMetier(entityNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirEntityJPAEnObjetMetier(entity) 
	 * retourne un objet métier correspondant.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirEntityJPAEnObjetMetier() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testConvertirEntityJPAEnObjetMetier() ********** ");
		}

		/* valeur null. */
		final SectionHitEntityJPA entityNull = null;
		
		final ISectionHit objetNull 
			= SectionHitConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entityNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Entity JPA null");
			System.out.println("null");
		}

		/* garantit que convertirEntityJPAEnObjetMetier(entityNull) 
		 * retourne null. */
		assertNull("convertirEntityJPAEnObjetMetier(entityNull) "
				+ "doit retourner null : "
				, objetNull);
		
		
		/* valeur existante. */
		final ISectionHit objet 
			= SectionHitConvertisseurMetierEntity
			.convertirEntityJPAEnObjetMetier((SectionHitEntityJPA) entityJPA);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entityJPA.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entityJPA
						, objet);

	} // Fin de testConvertirEntityJPAEnObjetMetier()._____________________


	
	/**
	 * teste la méthode convertirListEntitiesJPAEnModel(List Entities).<br/>
	 * <ul>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntities) 
	 * ne prend pas en compte les items null dans la liste.</li>
	 * <li>garantit que convertirListEntitiesJPAEnModel(listEntities) 
	 * retourne la liste d'objets métier correspondants.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListEntitiesJPAEnModel() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testConvertirListEntitiesJPAEnModel() ********** ");
		}

		/* valeur null. */
		final List<SectionHitEntityJPA> listEntitiesNull = null;
		
		final List<ISectionHit> listObjetsNull 
			= SectionHitConvertisseurMetierEntity
			.convertirListEntitiesJPAEnModel(listEntitiesNull);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
		 * retourne null. */
		assertNull("convertirListEntitiesJPAEnModel("
				+ "listEntitiesNull) doit retourner null : "
				, listObjetsNull);

		
		/* valeur existante. */
		final List<SectionHitEntityJPA> listEntities 
			= new ArrayList<SectionHitEntityJPA>();
		
		
		final SectionHitEntityJPA entity2 = null;
				
		listEntities.add(entity1);
		listEntities.add(entity2);
		listEntities.add(entity3);
		
		final List<ISectionHit> listObjets 
			= SectionHitConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(listEntities);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("LISTE D'ENTITES : ");
			System.out.println(this.afficherFormateListEntities(listEntities));
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(SectionHitConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
		}
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntities) 
		 * ne prend pas en compte les items null dans la liste. */
		assertTrue(
				"listObjets ne doit pas prendre en compte les items null : "
				, listObjets.size() == listEntities.size() - 1);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntities) 
		 * retourne la liste d'objets métier correspondants. */
		assertEquals("l'objet doit correspondre à l'entité : "
				, listEntities.get(0), listObjets.get(0));
		assertEquals("l'objet doit correspondre à l'entité : "
				, listEntities.get(2), listObjets.get(1));
		
	} // Fin de testConvertirListEntitiesJPAEnModel()._____________________


	
	/**
	 * teste la méthode creerEntityJPA(Objet).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que creerEntityJPA(objetNull) 
	 * retourne une entity avec toutes les valeurs à null.</li>
	 * <li>garantit que creerEntityJPA(objet) 
	 * retourne une entity correspondante.</li>
	 * <li>garantit que la Localisation de l'ENTITY obtenue par Conversion 
	 * de l'OBJET METIER est bien une instance d'ENTITY.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerEntityJPA() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testCreerEntityJPA() ********** ");
		}

		/* valeur null. */
		final ISectionHit objetNull = null;
		
		final SectionHitEntityJPA entityNull 
			= SectionHitConvertisseurMetierEntity
			.creerEntityJPA(objetNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Objet métier null");
			System.out.println(entityNull.toString());
		}

		/* garantit que creerEntityJPA(objetNull) 
		 * retourne un entity avec toutes les valeurs à null. */
		assertEquals("creerEntityJPA(entityNull) "
				+ "doit retourner un entity instancié par "
				+ "le constructeur d'arité nulle : "
				, new SectionHitEntityJPA(), entityNull);
		
		
		/* valeur existante. */
		final SectionHitEntityJPA entity 
			= SectionHitConvertisseurMetierEntity
			.creerEntityJPA(objetMetier);
		
		final ILocalisationHit localisation = entity.getLocalisation();
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objetMetier.toString());
			System.out.println(entity.toString());
			System.out.println(localisation.toString());
		}
		
		/* garantit que creerEntityJPA(objet) 
		 * retourne un entity correspondant. */
		assertEquals(
				"l'entité doit correspondre à l'objet : "
					, objetMetier
						, entity);
		
		/* garantit que la Localisation de l'ENTITY obtenue par 
		 * Conversion de l'OBJET METIER est bien une instance d'ENTITY. */
		assertTrue("Instance d'Entity JPA : "
				, localisation instanceof LocalisationHitEntityJPA);
		
	} // Fin de testCreerEntityJPA().______________________________________


	
	/**
	 * teste la méthode convertirObjetMetierEnEntityJPA(objet).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de la méthode.</li>
	 * <li>garantit que convertirObjetMetierEnEntityJPA(objetNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirObjetMetierEnEntityJPA(objet) 
	 * retourne un entity correspondant.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirObjetMetierEnEntityJPA() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testConvertirObjetMetierEnEntityJPA() ********** ");
		}

		/* valeur null. */
		final ISectionHit objetNull = null;
		
		final SectionHitEntityJPA entityNull 
			= SectionHitConvertisseurMetierEntity
			.convertirObjetMetierEnEntityJPA(objetNull);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("objet métier null");
			System.out.println("null");
		}

		/* garantit que convertirObjetMetierEnEntityJPA(objetNull) 
		 * retourne null. */
		assertNull("convertirObjetMetierEnEntityJPA(objetNull) "
				+ "doit retourner null : "
				, entityNull);
		
		
		/* valeur existante. */
		final SectionHitEntityJPA entity 
			= SectionHitConvertisseurMetierEntity
			.convertirObjetMetierEnEntityJPA(objetMetier);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objetMetier.toString());
			System.out.println(entity.toString());			
		}
		
		/* garantit que convertirObjetMetierEnEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, objetMetier
						, entity);

	} // Fin de testConvertirObjetMetierEnEntityJPA()._____________________


	
	/**
	 * teste la méthode convertirListModelEnEntitiesJPA(List Objets).<br/>
	 * <ul>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
	 * retourne null.</li>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjets) 
	 * ne prend pas en compte les items null dans la liste.</li>
	 * <li>garantit que convertirListModelEnEntitiesJPA(listObjets) 
	 * retourne la liste d'entities correspondants.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListModelEnEntitiesJPA() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE SectionHitConvertisseurMetierEntityTest - méthode testConvertirListModelEnEntitiesJPA() ********** ");
		}

		/* valeur null. */
		final List<ISectionHit> listObjetsNull = null;
		
		final List<SectionHitEntityJPA> listEntitiesNull 
			= SectionHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjetsNull);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
		 * retourne null. */
		assertNull("convertirListModelEnEntitiesJPA("
				+ "listObjetsNull) doit retourner null : "
				, listEntitiesNull);

		
		/* valeur existante. */
		final List<ISectionHit> listObjets 
			= new ArrayList<ISectionHit>();
				
		final ISectionHit objet2 = null;
				
		listObjets.add(objet1);
		listObjets.add(objet2);
		listObjets.add(objet3);
		
		final List<SectionHitEntityJPA> listEntities 
			= SectionHitConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjets);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(SectionHitConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
			System.out.println();
			System.out.println("LISTE D'ENTITES : ");
			System.out.println(this.afficherFormateListEntities(listEntities));			
		}
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjets) 
		 * ne prend pas en compte les items null dans la liste. */
		assertTrue(
				"listEntities ne doit pas prendre en compte les items null : "
				, listEntities.size() == listObjets.size() - 1);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjets) 
		 * retourne la liste d'entites correspondants. */
		assertEquals("l'entité doit correspondre à l'objet : "
				, listObjets.get(0), listEntities.get(0));
		assertEquals("l'entité doit correspondre à l'objet : "
				, listObjets.get(2), listEntities.get(1));
		
	} // Fin de testConvertirListModelEnEntitiesJPA()._____________________
	

	
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
		objet1 = new SectionHit(fichierImporteMap.get(15));
		objet3 = new SectionHit(fichierImporteMap.get(17));
		
		entityJPA = new SectionHitEntityJPA(ligneMap);
		entity1 = new SectionHitEntityJPA(fichierImporteMap.get(5));
		entity3 = new SectionHitEntityJPA(fichierImporteMap.get(8));
				
		assertEquals("doivent être égaux : ", objetMetier, objetMap);
		
		assertEquals("doivent être égaux : ", objetMetier, entityJPA);
		
	} // Fin de beforeClass()._____________________________________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_UTILISATEURCERBERE) 
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
	private String afficherFormateListEntities(
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
				stb.append(System.getProperty("line.separator"));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________

		
	
} // FIN DE LA CLASSE SectionHitConvertisseurMetierEntityTest.---------------
