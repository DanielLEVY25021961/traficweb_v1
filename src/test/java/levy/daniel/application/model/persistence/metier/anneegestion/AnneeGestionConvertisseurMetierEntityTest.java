package levy.daniel.application.model.persistence.metier.anneegestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;
import levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa.AnneeGestionEntityJPA;

/**
 * CLASSE AnneeGestionConvertisseurMetierEntityTest :<br/>
 * Test JUnit de la classe {@link AnneeGestionConvertisseurMetierEntity}.<br/>
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
 * @since 20 juin 2019
 *
 */
public class AnneeGestionConvertisseurMetierEntityTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * 2016.
	 */
	public static final String ANNEE_2016 = "2016";

	/**
	 * 2017.
	 */
	public static final String ANNEE_2017 = "2017";

	/**
	 * 2018.
	 */
	public static final String ANNEE_2018 = "2018";
		
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
	 * des IAnneeGesion.<br/>
	 * "id=%1$-5d anneeGestion = %2$-5s".
	 */
	public static final String FORMAT_ANNEEGESTION 
		= "id=%1$-5d anneeGestion = %2$-5s";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AnneeGestionConvertisseurMetierEntityTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AnneeGestionConvertisseurMetierEntityTest() {
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testCreerObjetMetierAPartirEntityJPA() ********** ");
		}

		/* valeur null. */
		final AnneeGestionEntityJPA entityNull = null;
		
		// CONVERSION
		final IAnneeGestion objetNull 
			= AnneeGestionConvertisseurMetierEntity
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
				, new AnneeGestion(), objetNull);
		
		
		/* valeur existante. */
		final AnneeGestionEntityJPA entity 
			= new AnneeGestionEntityJPA(
					7L
					, ANNEE_2017);
		
		// CONVERSION
		final IAnneeGestion objet 
			= AnneeGestionConvertisseurMetierEntity
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testConvertirEntityJPAEnObjetMetier() ********** ");
		}

		/* valeur null. */
		final AnneeGestionEntityJPA entityNull = null;
		
		// CONVERSION
		final IAnneeGestion objetNull 
			= AnneeGestionConvertisseurMetierEntity
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
		final AnneeGestionEntityJPA entity 
			= new AnneeGestionEntityJPA(
					7L
					, ANNEE_2016);
		
		// CONVERSION
		final IAnneeGestion objet 
			= AnneeGestionConvertisseurMetierEntity
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testConvertirListEntitiesJPAEnModel() ********** ");
		}

		/* valeur null. */
		final List<AnneeGestionEntityJPA> listEntitiesNull = null;
		
		// CONVERSION
		final List<IAnneeGestion> listObjetsNull 
			= AnneeGestionConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(listEntitiesNull);
		
		/* garantit que convertirListEntitiesJPAEnModel(listEntitiesNull) 
		 * retourne null. */
		assertNull("convertirListEntitiesJPAEnModel("
				+ "listEntitiesNull) doit retourner null : "
				, listObjetsNull);

		
		/* valeur existante. */
		final List<AnneeGestionEntityJPA> listEntities 
			= new ArrayList<AnneeGestionEntityJPA>();
		
		
		final AnneeGestionEntityJPA entity1 
			= new AnneeGestionEntityJPA(
				7L
				, ANNEE_2017);
		
		final AnneeGestionEntityJPA entity2 = null;
		
		final AnneeGestionEntityJPA entity3 
			= new AnneeGestionEntityJPA(
				8L
				, ANNEE_2018);
		
		
		listEntities.add(entity1);
		listEntities.add(entity2);
		listEntities.add(entity3);
		
		// CONVERSION
		final List<IAnneeGestion> listObjets 
			= AnneeGestionConvertisseurMetierEntity
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
			System.out.println(AnneeGestionConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testCreerEntityJPA() ********** ");
		}

		/* valeur null. */
		final IAnneeGestion objetNull = null;
		
		// CONVERSION
		final AnneeGestionEntityJPA entityNull 
			= AnneeGestionConvertisseurMetierEntity
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
				, new AnneeGestionEntityJPA(), entityNull);
		
		
		/* valeur existante. */
		final IAnneeGestion objet 
			= new AnneeGestion(
					7L
					, ANNEE_2018);
		
		// CONVERSION
		final AnneeGestionEntityJPA entity 
			= AnneeGestionConvertisseurMetierEntity
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testConvertirObjetMetierEnEntityJPA() ********** ");
		}

		/* valeur null. */
		final IAnneeGestion objetNull = null;
		
		// CONVERSION
		final AnneeGestionEntityJPA entityNull 
			= AnneeGestionConvertisseurMetierEntity
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
		final IAnneeGestion objet 
			= new AnneeGestion(
					7L
					, ANNEE_2018);
		
		// CONVERSION
		final AnneeGestionEntityJPA entity 
			= AnneeGestionConvertisseurMetierEntity
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
		System.out.println("********** CLASSE AnneeGestionConvertisseurMetierEntityTest - méthode testConvertirListModelEnEntitiesJPA() ********** ");
		}

		/* valeur null. */
		final List<IAnneeGestion> listObjetsNull = null;
		
		// CONVERSION
		final List<AnneeGestionEntityJPA> listEntitiesNull 
			= AnneeGestionConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjetsNull);
		
		/* garantit que convertirListModelEnEntitiesJPA(listObjetsNull) 
		 * retourne null. */
		assertNull("convertirListModelEnEntitiesJPA("
				+ "listObjetsNull) doit retourner null : "
				, listEntitiesNull);

		
		/* valeur existante. */
		final List<IAnneeGestion> listObjets 
			= new ArrayList<IAnneeGestion>();
		
		final IAnneeGestion objet1 
			= new AnneeGestion(
				7L
				, ANNEE_2017);
		
		final IAnneeGestion objet2 = null;
		
		final IAnneeGestion objet3 
			= new AnneeGestion(
				8L
				, ANNEE_2018);
		
		
		listObjets.add(objet1);
		listObjets.add(objet2);
		listObjets.add(objet3);
		
		// CONVERSION
		final List<AnneeGestionEntityJPA> listEntities 
			= AnneeGestionConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(listObjets);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'OBJETS : " + listObjets.size());
			System.out.println();
			System.out.println("TAILLE DE LA lISTE D'ENTITIES : " + listEntities.size());
			System.out.println();
			System.out.println("LISTE D'OBJETS METIER : ");
			System.out.println(AnneeGestionConvertisseurMetierEntity.afficherFormateListObjets(listObjets));
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
	 * @param pList : List&lt;AnneeGestionEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String afficherFormateListEntities(
			final List<AnneeGestionEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IAnneeGestion entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_ANNEEGESTION
								, entity.getId()
								, entity.getAnneeGestion());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty("line.separator"));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	
	

}
