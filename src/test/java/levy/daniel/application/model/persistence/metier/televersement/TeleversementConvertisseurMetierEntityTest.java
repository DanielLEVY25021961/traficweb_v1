package levy.daniel.application.model.persistence.metier.televersement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa.AnneeGestionEntityJPA;
import levy.daniel.application.model.persistence.metier.televersement.entities.jpa.TeleversementEntityJPA;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.EnumCivilites;

/**
 * CLASSE TeleversementConvertisseurMetierEntityTest :<br/>
 * Test de la classe {@link TeleversementConvertisseurMetierEntity}.<br/>
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
 * @since 21 juin 2019
 *
 */
public class TeleversementConvertisseurMetierEntityTest {

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
	 * FORMAT pour affichage formaté à la console 
	 * des ITeleversement.<br/>
	 * "id = %1$-5d dateTeleversement = %2$-20s utilisateur = %3$-30s 
	 * gestionnaire = %4$-10s typeFichier = %5$-10s 
	 * nomfichierTeleverse = %6$-20s 
	 * fichierStockeServeur = %7$-100s anneeGestion = %8$-4s".
	 */
	public static final String FORMAT_TELEVERSEMENT 
		= "id = %1$-5d dateTeleversement = %2$-20s utilisateur = %3$-30s "
				+ "gestionnaire = %4$-10s typeFichier = %5$-10s "
				+ "nomfichierTeleverse = %6$-20s "
				+ "fichierStockeServeur = %7$-100s anneeGestion = %8$-4s";

	/**
	 * "line.separator".<br/>
	 */
	public static final String LINE_SEPARATOR = "line.separator";
	
	/**
	 * LocalDateTime.of(2017, 02, 25, 01, 40, 33);.
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2017 
		= LocalDateTime.of(2017, 02, 25, 01, 40, 33);
	
	/**
	 * LocalDateTime.of(2018, 02, 25, 01, 40, 33);.
	 */
	public static final LocalDateTime DATE_TELEVERSEMENT_2018 
		= LocalDateTime.of(2018, 02, 25, 01, 40, 33);

	/**
	 * "prenomTest".
	 */
	public static final String PRENOMTEST = "prenomTest";
	
	/**
	 * "nomTest".
	 */
	public static final String NOMTEST = "nomTest";
	
	/**
	 * "telTest".
	 */
	public static final String TELTEST = "telTest";
	
	/**
	 * "emailTest".
	 */
	public static final String EMAILTEST = "emailTest";
	
	/**
	 * "DIRA".
	 */
	public static final String SERVICE_DIRA = "DIRA";
	
	/**
	 * "DIRA/SIEER/TRAFICS_1".
	 */
	public static final String UNITE_DIRA_SIEER = "DIRA/SIEER/TRAFICS_1";
	
	/**
	 * "CONSULTANT".
	 */
	public static final String PROFIL_CONSULTANT = "CONSULTANT";
	
	/**
	 * "DIRA".
	 */
	public static final String PORTEE_DIRA = "DIRA";
	
	/**
	 * "44".
	 */
	public static final String RESTRICTION_DIRA_44 = "44";
	
	/**
	 * UTILISATEUR_DIRA.
	 */
	public static final IUtilisateurCerbere UTILISATEUR_DIRA 
		= new UtilisateurCerbere(7L
				, EnumCivilites.DOCTEUR.getAbreviation()
				, PRENOMTEST, NOMTEST
				, TELTEST, EMAILTEST
				, SERVICE_DIRA, UNITE_DIRA_SIEER
				, PROFIL_CONSULTANT, PORTEE_DIRA, RESTRICTION_DIRA_44);

	/**
	 * EnumGestionnaire.DIRA.
	 */
	public static final EnumGestionnaire GESTIONNAIRE_DIRA 
		= EnumGestionnaire.DIRA;
	
	/**
	 * EnumTypeFichierDonnees.HIT.
	 */
	public static final EnumTypeFichierDonnees TYPE_FICHIER_HIT 
		= EnumTypeFichierDonnees.HIT;

	/**
	 * "HITDIRA2017".
	 */
	public static final String NOM_FICHIER_TELEVERSE_DIRA2017 = "HITDIRA2017";

	/**
	 * "HITDIRA2018".
	 */
	public static final String NOM_FICHIER_TELEVERSE_DIRA2018 = "HITDIRA2018";
	
	/**
	 * new File("toto").
	 */
	public static final File FICHIER_STOCKE_SERVEUR_TOTO = new File("toto");
	
	/**
	 * 2016.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2016 
		= new AnneeGestionEntityJPA("2016");
	
	/**
	 * 2017.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2017 
		= new AnneeGestionEntityJPA("2017");
	
	/**
	 * 2018.<br/>
	 */
	public static final IAnneeGestion ANNEE_GESTION_2018 
		= new AnneeGestionEntityJPA("2018");

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementConvertisseurMetierEntityTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public TeleversementConvertisseurMetierEntityTest() {
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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerObjetMetierAPartirEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testCreerObjetMetierAPartirEntityJPA() ********** ");
		}

		/* valeur null. */
		final TeleversementEntityJPA entityNull = null;
		
		// CONVERSION
		final ITeleversement objetNull 
			= TeleversementConvertisseurMetierEntity
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
				, new Televersement(), objetNull);
		
		
		/* valeur existante. */
		final TeleversementEntityJPA entity 
			= new TeleversementEntityJPA(
					7L
					, DATE_TELEVERSEMENT_2017
					, UTILISATEUR_DIRA
					, GESTIONNAIRE_DIRA
					, TYPE_FICHIER_HIT
					, NOM_FICHIER_TELEVERSE_DIRA2017
					, FICHIER_STOCKE_SERVEUR_TOTO
					, ANNEE_GESTION_2017);
		
		// CONVERSION
		final ITeleversement objet 
			= TeleversementConvertisseurMetierEntity
			.creerObjetMetierAPartirEntityJPA(entity);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entity.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entity
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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirEntityJPAEnObjetMetier() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testConvertirEntityJPAEnObjetMetier() ********** ");
		}

		/* valeur null. */
		final TeleversementEntityJPA entityNull = null;
		
		// CONVERSION
		final ITeleversement objetNull 
			= TeleversementConvertisseurMetierEntity
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
		final TeleversementEntityJPA entity 
			= new TeleversementEntityJPA(
				7L
				, DATE_TELEVERSEMENT_2017
				, UTILISATEUR_DIRA
				, GESTIONNAIRE_DIRA
				, TYPE_FICHIER_HIT
				, NOM_FICHIER_TELEVERSE_DIRA2017
				, FICHIER_STOCKE_SERVEUR_TOTO
				, ANNEE_GESTION_2017);
		
		// CONVERSION
		final ITeleversement objet 
			= TeleversementConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entity);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(entity.toString());
			System.out.println(objet.toString());
		}
		
		/* garantit que creerObjetMetierAPartirEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, entity
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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListEntitiesJPAEnModel() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testConvertirListEntitiesJPAEnModel() ********** ");
		}

		/* valeur null. */
		final List<TeleversementEntityJPA> listEntitiesNull = null;
		
		// CONVERSION
		final List<ITeleversement> listObjetsNull 
			= TeleversementConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(listEntitiesNull);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
		 * retourne null. */
		assertNull("convertirListEntitiesJPAEnModel("
				+ "listEntitiesNull) doit retourner null : "
				, listObjetsNull);

		
		/* valeur existante. */
		final List<TeleversementEntityJPA> listEntities 
			= new ArrayList<TeleversementEntityJPA>();
		
		
		final TeleversementEntityJPA entity1 
			= new TeleversementEntityJPA(
			7L
			, DATE_TELEVERSEMENT_2017
			, UTILISATEUR_DIRA
			, GESTIONNAIRE_DIRA
			, TYPE_FICHIER_HIT
			, NOM_FICHIER_TELEVERSE_DIRA2017
			, FICHIER_STOCKE_SERVEUR_TOTO
			, ANNEE_GESTION_2017);

		
		final TeleversementEntityJPA entity2 = null;
		
		final TeleversementEntityJPA entity3 
			= new TeleversementEntityJPA(
			8L
			, DATE_TELEVERSEMENT_2018
			, UTILISATEUR_DIRA
			, GESTIONNAIRE_DIRA
			, TYPE_FICHIER_HIT
			, NOM_FICHIER_TELEVERSE_DIRA2018
			, FICHIER_STOCKE_SERVEUR_TOTO
			, ANNEE_GESTION_2018);
		
		
		listEntities.add(entity1);
		listEntities.add(entity2);
		listEntities.add(entity3);
		
		// CONVERSION
		final List<ITeleversement> listObjets 
			= TeleversementConvertisseurMetierEntity
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
			System.out.println(TeleversementConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCreerEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testCreerEntityJPA() ********** ");
		}

		/* valeur null. */
		final ITeleversement objetNull = null;
		
		// CONVERSION
		final TeleversementEntityJPA entityNull 
			= TeleversementConvertisseurMetierEntity
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
				, new TeleversementEntityJPA(), entityNull);
		
		
		/* valeur existante. */
		final ITeleversement objet 
			= new Televersement(
					7L
					, DATE_TELEVERSEMENT_2017
					, UTILISATEUR_DIRA
					, GESTIONNAIRE_DIRA
					, TYPE_FICHIER_HIT
					, NOM_FICHIER_TELEVERSE_DIRA2017
					, FICHIER_STOCKE_SERVEUR_TOTO
					, ANNEE_GESTION_2017);
		
		// CONVERSION
		final TeleversementEntityJPA entity 
			= TeleversementConvertisseurMetierEntity
				.creerEntityJPA(objet);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet.toString());
			System.out.println(entity.toString());
		}
		
		/* garantit que creerEntityJPA(objet) 
		 * retourne un entity correspondant. */
		assertEquals(
				"l'entité doit correspondre à l'objet : "
					, objet
						, entity);
		
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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirObjetMetierEnEntityJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testConvertirObjetMetierEnEntityJPA() ********** ");
		}

		/* valeur null. */
		final ITeleversement objetNull = null;
		
		// CONVERSION
		final TeleversementEntityJPA entityNull 
			= TeleversementConvertisseurMetierEntity
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
		final ITeleversement objet 
			= new Televersement(
					7L
					, DATE_TELEVERSEMENT_2017
					, UTILISATEUR_DIRA
					, GESTIONNAIRE_DIRA
					, TYPE_FICHIER_HIT
					, NOM_FICHIER_TELEVERSE_DIRA2017
					, FICHIER_STOCKE_SERVEUR_TOTO
					, ANNEE_GESTION_2017);
		
		// CONVERSION
		final TeleversementEntityJPA entity 
			= TeleversementConvertisseurMetierEntity
				.convertirObjetMetierEnEntityJPA(objet);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet.toString());
			System.out.println(entity.toString());			
		}
		
		/* garantit que convertirObjetMetierEnEntityJPA(entity) 
		 * retourne un objet métier correspondant. */
		assertEquals(
				"l'objet doit correspondre à l'entité : "
					, objet
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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConvertirListModelEnEntitiesJPA() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE TeleversementConvertisseurMetierEntityTest - méthode testConvertirListModelEnEntitiesJPA() ********** ");
		}

		/* valeur null. */
		final List<ITeleversement> listObjetsNull = null;
		
		// CONVERSION
		final List<TeleversementEntityJPA> listEntitiesNull 
			= TeleversementConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjetsNull);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
		 * retourne null. */
		assertNull("convertirListModelEnEntitiesJPA("
				+ "listObjetsNull) doit retourner null : "
				, listEntitiesNull);

		
		/* valeur existante. */
		final List<ITeleversement> listObjets 
			= new ArrayList<ITeleversement>();
		
		final ITeleversement objet1 
			= new Televersement(
					7L
					, DATE_TELEVERSEMENT_2017
					, UTILISATEUR_DIRA
					, GESTIONNAIRE_DIRA
					, TYPE_FICHIER_HIT
					, NOM_FICHIER_TELEVERSE_DIRA2017
					, FICHIER_STOCKE_SERVEUR_TOTO
					, ANNEE_GESTION_2017);
		
		final ITeleversement objet2 = null;
		
		final ITeleversement objet3 
			= new Televersement(
					8L
					, DATE_TELEVERSEMENT_2018
					, UTILISATEUR_DIRA
					, GESTIONNAIRE_DIRA
					, TYPE_FICHIER_HIT
					, NOM_FICHIER_TELEVERSE_DIRA2018
					, FICHIER_STOCKE_SERVEUR_TOTO
					, ANNEE_GESTION_2018);
		
		
		listObjets.add(objet1);
		listObjets.add(objet2);
		listObjets.add(objet3);
		
		// CONVERSION
		final List<TeleversementEntityJPA> listEntities 
			= TeleversementConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjets);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(TeleversementConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
	 * @param pList : List&lt;TeleversementEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String afficherFormateListEntities(
			final List<TeleversementEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final ITeleversement entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_TELEVERSEMENT
								, entity.getId()
								, formaterLocalDateTimeEnString(entity.getDateTeleversement())
								, entity.getUtilisateur().getNom()
								, entity.getGestionnaire().getNomCourt()
								, entity.getTypeFichier().getNomCourt()
								, entity.getNomFichierTeleverse()
								, entity.getFichierStockeServeur().getAbsolutePath()
								, entity.getAnneeGestion().getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________

	
	
	/**
	 * retourne une String de la forme 
	 * <code>[annee-mois-jour_heure_minute_seconde]</code> 
	 * à partir d'une LocalDateTime pDate.<br/>
	 * Par exemple, retourne <b>2019-06-13_21_03_57</b> 
	 * pour le 13 juin 2019 à 21h 03 minutes et 57 secondes.<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDateTime : date à formater.
	 * 
	 * @return : String : affichage de la date formatée.<br/>
	 */
	private static String formaterLocalDateTimeEnString(
			final LocalDateTime pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		/* formateur de type 2019-06-13_21_03_57 
		 * (13 juin 2019 à 21h 03 minutes et 57 secondes)*/
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
	
		final String resultat = pDate.format(formatter);
				
		return resultat;
		
	} // Fin de formaterLocalDateTimeEnString(...).________________________
	
	

} // FIN DE LA CLASSE TeleversementConvertisseurMetierEntityTest.------------
