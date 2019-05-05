package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespaths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ArboresceurPresentProjetTest :<br/>
 * Test JUnit de la classe {@link ArboresceurPresentProjet}.<br/>
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
 * @since 3 mai 2019
 *
 */
public class ArboresceurPresentProjetTest {

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
	 * "doit retourner un singleton : ".
	 */
	public static final String DOIT_RETOURNER_SINGLETON 
		= "doit retourner un singleton : ";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ArboresceurPresentProjetTest.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ArboresceurPresentProjetTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste la méthode getProjetSourcePath().<br/>
	 * <ul>
	 * <li>garantit que getProjetSourcePath() ne retourne pas null.</li>
	 * <li>garantit que getProjetSourcePath() retourne un singleton.</li>
	 * <li>garantit que getProjetSourcePath() retourne le présent Projet ECLIPSE.</li>
	 * <li>garantit que getProjetSourcePath() retourne l'équivalent de 
	 * Paths.get(".").toAbsolutePath().normalize()</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetProjetSourcePath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetProjetSourcePath() ********** ");
		}
		
		final Path projetSourcePath 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		final Path projetSourcePath2 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("projetSourcePath : " + projetSourcePath);
		}

		/* garantit que getProjetSourcePath() ne retourne pas null. */
		assertNotNull("getProjetSourcePath() ne doit pas retourner null : ", projetSourcePath);
		
		/* garantit que getProjetSourcePath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, projetSourcePath
					, projetSourcePath2);
		
		/* garantit que getProjetSourcePath() retourne l'équivalent 
		 * de Paths.get(".").toAbsolutePath().normalize() */
		assertEquals("getPathPresentProjet()"
				, Paths.get(".").toAbsolutePath().normalize()
					, projetSourcePath);

	} // Fin de testGetProjetSourcePath()._________________________________


	
	/**
	 * Teste la méthode getProjetSourceNom().<br/>
	 * <ul>
	 * <li>garantit que getProjetSourceNom() ne retourne pas null.</li>
	 * <li>garantit que getProjetSourceNom() retourne un singleton.</li>
	 * <li>garantit que getProjetSourceNom() retourne le NOM du présent Projet ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetProjetSourceNom() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetProjetSourceNom() ********** ");
		}

		final String nomPresentProjetString 
			= ArboresceurPresentProjet.getProjetSourceNom();
		
		final String nomPresentProjetString2 
			= ArboresceurPresentProjet.getProjetSourceNom();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("nomPresentProjet : " + nomPresentProjetString);
		}
		
		/* garantit que getProjetSourceNom() ne retourne pas null. */
		assertNotNull("nomPresentProjetString ne doit pas être null : ", nomPresentProjetString);
		
		/* garantit que getProjetSourceNom() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, nomPresentProjetString
					, nomPresentProjetString2);
		
	} // Fin de testGetProjetSourceNom().__________________________________


	
	/**
	 * teste la méthode getSrcMainJavaPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcMainJavaPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcMainJavaPath() retourne un singleton.</li>
	 * <li>garantit que getSrcMainJavaPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcMainJavaPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcMainJavaPath() ********** ");
		}
		
		final Path srcMainJavaPath 
			= ArboresceurPresentProjet.getSrcMainJavaPath();
		
		final Path srcMainJavaPath2 
			= ArboresceurPresentProjet.getSrcMainJavaPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcMainJavaPath : " + srcMainJavaPath);
		}

		/* garantit que getSrcMainJavaPath() ne retourne pas null. */
		assertNotNull("getSrcMainJavaPath() ne doit pas retourner null : ", srcMainJavaPath);
		
		/* garantit que getSrcMainJavaPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcMainJavaPath
					, srcMainJavaPath2);
		
		/* garantit que getSrcMainJavaPath() retourne le bon path. */
		assertEquals("srcMainJavaPath doit valoir projet + SRC_MAIN_JAVA_PATH_RELATIF : "
				, resoudrePathRelatifParRapportAProjet(ArboresceurPresentProjet.SRC_MAIN_JAVA_PATH_RELATIF)
				, srcMainJavaPath);

	} // Fin de testGetSrcMainJavaPath().__________________________________


	
	/**
	 * teste la méthode getSrcMainResourcesPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcMainResourcesPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcMainResourcesPath() retourne un singleton.</li>
	 * <li>garantit que getSrcMainResourcesPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcMainResourcesPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcMainResourcesPath() ********** ");
		}
		
		final Path srcMainResourcesPath 
			= ArboresceurPresentProjet.getSrcMainResourcesPath();
		
		final Path srcMainResourcesPath2 
			= ArboresceurPresentProjet.getSrcMainResourcesPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcMainResourcesPath : " + srcMainResourcesPath);
		}

		/* garantit que getSrcMainResourcesPath() ne retourne pas null. */
		assertNotNull("getSrcMainResourcesPath() ne doit pas retourner null : ", srcMainResourcesPath);
		
		/* garantit que getSrcMainResourcesPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcMainResourcesPath
					, srcMainResourcesPath2);
				
		/* garantit que getSrcMainResourcesMetaInfPath() retourne le bon path. */
		assertEquals("srcMainResourcesPath doit valoir projet + SRC_MAIN_RESOURCES_PATH_RELATIF : "
				, resoudrePathRelatifParRapportAProjet(ArboresceurPresentProjet.SRC_MAIN_RESOURCES_PATH_RELATIF)
				, srcMainResourcesPath);

	} // Fin de testGetSrcMainResourcesPath()._____________________________


	
	/**
	 * teste la méthode getSrcMainResourcesMetaInfPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcMainResourcesMetaInfPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcMainResourcesMetaInfPath() retourne un singleton.</li>
	 * <li>garantit que getSrcMainResourcesMetaInfPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcMainResourcesMetaInfPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcMainResourcesMetaInfPath() ********** ");
		}
		
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcMainResourcesMetaInfPath();
		
		final Path srcMainResourcesMetaInfPath2 
			= ArboresceurPresentProjet.getSrcMainResourcesMetaInfPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcMainResourcesMetaInfPath : " + srcMainResourcesMetaInfPath);
		}

		/* garantit que getSrcMainResourcesMetaInfPath() ne retourne pas null. */
		assertNotNull("getSrcMainResourcesMetaInfPath() ne doit pas retourner null : ", srcMainResourcesMetaInfPath);
		
		/* garantit que getSrcMainResourcesMetaInfPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcMainResourcesMetaInfPath
					, srcMainResourcesMetaInfPath2);
		
		final Path pathRelatifResolu 
		= ArboresceurPresentProjet.SRC_MAIN_RESOURCES_PATH_RELATIF
		.resolve("META-INF");
	
		/* garantit que getSrcMainResourcesMetaInfPath() retourne le bon path. */
		assertEquals("getSrcMainResourcesMetaInfPath() doit valoir projet + SRC_MAIN_RESOURCES_PATH_RELATIF + META-INF : "
				, resoudrePathRelatifParRapportAProjet(pathRelatifResolu)
				, srcMainResourcesMetaInfPath);

	} // Fin de testGetSrcMainResourcesMetaInfPath()._____________________________


	
	/**
	 * teste la méthode getSrcTestJavaPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcTestJavaPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcTestJavaPath() retourne un singleton.</li>
	 * <li>garantit que getSrcTestJavaPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcTestJavaPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcTestJavaPath() ********** ");
		}
		
		final Path srcTestJavaPath 
			= ArboresceurPresentProjet.getSrcTestJavaPath();
		
		final Path srcTestJavaPath2 
			= ArboresceurPresentProjet.getSrcTestJavaPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcTestJavaPath : " + srcTestJavaPath);
		}

		/* garantit que getSrcTestJavaPath() ne retourne pas null. */
		assertNotNull("getSrcTestJavaPath() ne doit pas retourner null : ", srcTestJavaPath);
		
		/* garantit que getSrcTestJavaPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcTestJavaPath
					, srcTestJavaPath2);
		
		/* garantit que getSrcTestJavaPath() retourne le bon path. */
		assertEquals("srcTestJavaPath doit valoir projet + SRC_TEST_JAVA_PATH_RELATIF : "
				, resoudrePathRelatifParRapportAProjet(ArboresceurPresentProjet.SRC_TEST_JAVA_PATH_RELATIF)
				, srcTestJavaPath);

	} // Fin de testGetSrcTestJavaPath().__________________________________


	
	/**
	 * teste la méthode getSrcTestResourcesPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcTestResourcesPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcTestResourcesPath() retourne un singleton.</li>
	 * <li>garantit que getSrcTestResourcesPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcTestResourcesPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcTestResourcesPath() ********** ");
		}
		
		final Path srcTestResourcesPath 
			= ArboresceurPresentProjet.getSrcTestResourcesPath();
		
		final Path srcTestResourcesPath2 
			= ArboresceurPresentProjet.getSrcTestResourcesPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcTestResourcesPath : " + srcTestResourcesPath);
		}

		/* garantit que getSrcTestResourcesPath() ne retourne pas null. */
		assertNotNull("getSrcTestResourcesPath() ne doit pas retourner null : ", srcTestResourcesPath);
		
		/* garantit que getSrcTestResourcesPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcTestResourcesPath
					, srcTestResourcesPath2);
		
		/* garantit que getSrcTestResourcesPath() retourne le bon path. */
		assertEquals("srcTestResourcesPath doit valoir projet + SRC_TEST_RESOURCES_PATH_RELATIF : "
				, resoudrePathRelatifParRapportAProjet(ArboresceurPresentProjet.SRC_TEST_RESOURCES_PATH_RELATIF)
				, srcTestResourcesPath);

	} // Fin de testGetSrcTestResourcesPath()._____________________________

	
	
	/**
	 * teste la méthode getSrcTestResourcesMetaInfPath().<br/>
	 * <ul>
	 * <li>garantit que getSrcTestResourcesMetaInfPath() ne retourne pas null.</li>
	 * <li>garantit que getSrcTestResourcesMetaInfPath() retourne un singleton.</li>
	 * <li>garantit que getSrcTestResourcesMetaInfPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetSrcTestResourcesMetaInfPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetSrcTestResourcesMetaInfPath() ********** ");
		}
		
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcTestResourcesMetaInfPath();
		
		final Path srcTestResourcesMetaInfPath2 
			= ArboresceurPresentProjet.getSrcTestResourcesMetaInfPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("srcTestResourcesMetaInfPath : " + srcTestResourcesMetaInfPath);
		}

		/* garantit que getSrcTestResourcesMetaInfPath() ne retourne pas null. */
		assertNotNull("getSrcTestResourcesMetaInfPath() ne doit pas retourner null : ", srcTestResourcesMetaInfPath);
		
		/* garantit que getSrcTestResourcesMetaInfPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, srcTestResourcesMetaInfPath
					, srcTestResourcesMetaInfPath2);
		
		final Path pathRelatifResolu 
		= ArboresceurPresentProjet.SRC_TEST_RESOURCES_PATH_RELATIF
		.resolve("META-INF");
	
		/* garantit que getSrcMainResourcesPath() retourne le bon path. */
		assertEquals("getSrcMainResourcesMetaInfPath() doit valoir projet + SRC_MAIN_RESOURCES_PATH_RELATIF + META-INF : "
				, resoudrePathRelatifParRapportAProjet(pathRelatifResolu)
				, srcTestResourcesMetaInfPath);

	} // Fin de testGetSrcTestResourcesMetaInfPath().______________________
	
	
	
	/**
	 * teste la méthode getGroupId().<br/>
	 * <ul>
	 * <li>garantit que getGroupId() ne retourne pas null.</li>
	 * <li>garantit que getGroupId() retourne un singleton.</li>
	 * <li>garantit que getGroupId() retourne le bon GroupId.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetGroupId() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetGroupId() ********** ");
		}
		
		final String groupId = ArboresceurPresentProjet.getGroupId();
		
		final String groupId2 = ArboresceurPresentProjet.getGroupId();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("groupId : " + groupId);
		}
		
		/* garantit que getGroupId() ne retourne pas null. */
		assertNotNull("getGroupId() ne doit pas retourner null : ", groupId);
		
		/* garantit que getGroupId() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, groupId
					, groupId2);
		
		/* garantit que getGroupId() retourne le bon groupId. */
		assertEquals("getGroupId() doit retourner GROUPID_PAR_DEFAUT : "
				, ArboresceurPresentProjet.GROUPID_PAR_DEFAUT
					, groupId);
		
	} // Fin de testGetGroupId().__________________________________________


		
	/**
	 * teste la méthode setGroupId().<br/>
	 * <ul>
	 * <li>garantit que setGroupId() modifie groupId.</li>
	 * <li>garantit que setGroupId() modifie groupIdPathRelatif.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSetGroupId() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testSetGroupId() ********** ");
		}
		
		final String nouveauGroupId = "com.google";
		
		ArboresceurPresentProjet.setGroupId(nouveauGroupId);
		
		final String presentGroupId = ArboresceurPresentProjet.getGroupId();
		final Path presentGroupIdPath = ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("groupId modifié : " + presentGroupId);
			System.out.println("groupId PATH modifié : " + presentGroupIdPath.toString());
			
		}

		/* garantit que setGroupId() modifie groupId. */
		assertEquals("setGroupId() doit modifier groupId : "
				, nouveauGroupId
					, presentGroupId);
		
		/* >garantit que setGroupId() modifie groupIdPathRelatif.*/
		assertEquals("setGroupId() doit modifier groupIdPathRelatif : "
				, Paths.get("com/google")
					, presentGroupIdPath);
		
		/* reset du groupId. */
		final String groupIdParDefaut 
			= ArboresceurPresentProjet.GROUPID_PAR_DEFAUT;
		
		ArboresceurPresentProjet.setGroupId(groupIdParDefaut);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("groupId après reset : " + ArboresceurPresentProjet.getGroupId());
			System.out.println("groupId PATH après reset : " + ArboresceurPresentProjet.getGroupIdPathRelatif().toString());
			
		}

		
		/* garantit que setGroupId() modifie groupId. */
		assertEquals("setGroupId() doit modifier groupId : "
				, groupIdParDefaut
					, ArboresceurPresentProjet.getGroupId());
		
		/* >garantit que setGroupId() modifie groupIdPathRelatif.*/
		assertEquals("setGroupId() doit modifier groupIdPathRelatif : "
				, Paths.get(ArboresceurPresentProjet.GROUPIDPATH_PAR_DEFAUT)
					, ArboresceurPresentProjet.getGroupIdPathRelatif());
		
	} // Fin de testSetGroupId().__________________________________________
	
	
	
	/**
	 * teste la méthode getGroupIdPathRelatif().<br/>
	 * <ul>
	 * <li>garantit que getGroupIdPathRelatif() ne retourne pas null.</li>
	 * <li>garantit que getGroupIdPathRelatif() retourne un singleton.</li>
	 * <li>garantit que getGroupIdPathRelatif() retourne le bon GroupIdPathRelatif.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetGroupIdPathRelatif() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetGroupIdPathRelatif() ********** ");
		}
		
		final Path groupIdPathRelatif 
			= ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		final Path groupIdPathRelatif2 
			= ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("groupIdPathRelatif : " + groupIdPathRelatif);
		}
		
		/* garantit que getGroupIdPathRelatif() ne retourne pas null. */
		assertNotNull(
				"getGroupIdPathRelatif() ne doit pas retourner null : "
					, groupIdPathRelatif);
		
		/* garantit que getGroupIdPathRelatif() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, groupIdPathRelatif
					, groupIdPathRelatif2);
		
		/* garantit que getGroupIdPathRelatif() retourne le bon groupId. */
		assertEquals("getGroupIdPathRelatif() doit retourner GROUPIDPATH_PAR_DEFAUT : "
				, Paths.get(ArboresceurPresentProjet.GROUPIDPATH_PAR_DEFAUT)
					, groupIdPathRelatif);
		
	} // Fin de testGetGroupIdPathRelatif()._______________________________


	
	/**
	 * teste la méthode setGroupIdPathRelatif().<br/>
	 * <ul>
	 * <li>garantit que setGroupIdPathRelatif() modifie groupId.</li>
	 * <li>garantit que setGroupIdPathRelatif() modifie groupIdPathRelatif.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSetGroupIdPathRelatif() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testSetGroupIdPathRelatif() ********** ");
		}
		
		final Path nouveauGroupIdPathRelatif = Paths.get("com/google");
		
		ArboresceurPresentProjet.setGroupIdPathRelatif(nouveauGroupIdPathRelatif);
		
		final String presentGroupId = ArboresceurPresentProjet.getGroupId();
		final Path presentGroupIdPathRelatif = ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("groupId modifié : " + presentGroupId);
			System.out.println("groupId PATH modifié : " + presentGroupIdPathRelatif.toString());
			
		}

		/* garantit que setGroupIdPathRelatif() modifie groupId. */
		assertEquals("setGroupIdPathRelatif() doit modifier groupId : "
				, nouveauGroupIdPathRelatif
					, presentGroupIdPathRelatif);
		
		/* >garantit que setGroupIdPathRelatif() modifie groupIdPathRelatif.*/
		assertEquals("setGroupIdPathRelatif() doit modifier groupIdPathRelatif : "
				, Paths.get("com/google")
					, presentGroupIdPathRelatif);
		
		/* reset du groupId. */
		final Path groupIdPathParDefaut 
			= Paths.get(ArboresceurPresentProjet.GROUPIDPATH_PAR_DEFAUT);
		
		ArboresceurPresentProjet.setGroupIdPathRelatif(groupIdPathParDefaut);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("groupId après reset : " + ArboresceurPresentProjet.getGroupId());
			System.out.println("groupId PATH après reset : " + ArboresceurPresentProjet.getGroupIdPathRelatif().toString());
			
		}

		
		/* garantit que setGroupIdPathRelatif() modifie groupId. */
		assertEquals("setGroupIdPathRelatif() doit modifier groupId : "
				, groupIdPathParDefaut
					, ArboresceurPresentProjet.getGroupIdPathRelatif());
		
		/* >garantit que setGroupIdPathRelatif() modifie groupIdPathRelatif.*/
		assertEquals("setGroupIdPathRelatif() doit modifier groupIdPathRelatif : "
				, Paths.get(ArboresceurPresentProjet.GROUPIDPATH_PAR_DEFAUT)
					, ArboresceurPresentProjet.getGroupIdPathRelatif());
		
	} // Fin de testSetGroupIdPathRelatif().__________________________________________
	
	
	
	/**
	 * teste la méthode getRacineSourcesJavaPath().<br/>
	 * <ul>
	 * <li>garantit que getRacineSourcesJavaPath() ne retourne pas null.</li>
	 * <li>garantit que getRacineSourcesJavaPath() retourne un singleton.</li>
	 * <li>garantit que getRacineSourcesJavaPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetRacineSourcesJavaPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetRacineSourcesJavaPath() ********** ");
		}	
		
		final Path racineSourcesJavaPath 
			= ArboresceurPresentProjet.getRacineSourcesJavaPath();
	
		final Path racineSourcesJavaPath2 
			= ArboresceurPresentProjet.getRacineSourcesJavaPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("racineSourcesJavaPath : " + racineSourcesJavaPath);
		}
	
		/* garantit que getRacineSourcesJavaPath() ne retourne pas null. */
		assertNotNull(
				"getRacineSourcesJavaPath() ne doit pas retourner null : "
				, racineSourcesJavaPath);
		
		/* garantit que getRacineSourcesJavaPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, racineSourcesJavaPath
					, racineSourcesJavaPath2);
		
		/* garantit que getRacineSourcesJavaPath() retourne le bon path. */
		assertEquals("racineSourcesJavaPath doit valoir srcMainJava + groupId : "
				, resoudrePathRelatifParRapportASrcMainJava(
						ArboresceurPresentProjet.getGroupIdPathRelatif())
				, racineSourcesJavaPath);

	} // Fin de testGetRacineSourcesJavaPath().____________________________

	
	
	/**
	 * teste la méthode getRacineTestsJavaPath().<br/>
	 * <ul>
	 * <li>garantit que getRacineTestsJavaPath() ne retourne pas null.</li>
	 * <li>garantit que getRacineTestsJavaPath() retourne un singleton.</li>
	 * <li>garantit que getRacineTestsJavaPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetRacineTestsJavaPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetRacineTestsJavaPath() ********** ");
		}	
		
		final Path racineTestsJavaPath 
			= ArboresceurPresentProjet.getRacineTestsJavaPath();
	
		final Path racineTestsJavaPath2 
			= ArboresceurPresentProjet.getRacineTestsJavaPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("racineTestsJavaPath : " + racineTestsJavaPath);
		}
	
		/* garantit que getRacineTestsJavaPath() ne retourne pas null. */
		assertNotNull(
				"getRacineTestsJavaPath() ne doit pas retourner null : "
				, racineTestsJavaPath);
		
		/* garantit que getRacineTestsJavaPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, racineTestsJavaPath
					, racineTestsJavaPath2);
		
		/* garantit que getRacineTestsJavaPath() retourne le bon path. */
		assertEquals("racineTestsJavaPath doit valoir srcTestJava + groupId : "
				, resoudrePathRelatifParRapportASrcTestJava(
						ArboresceurPresentProjet.getGroupIdPathRelatif())
				, racineTestsJavaPath);

	} // Fin de testGetRacineTestsJavaPath().______________________________
	
	
	
	/**
	 * teste la méthode getCoucheAppTechnicMainPath().<br/>
	 * <ul>
	 * <li>garantit que getCoucheAppTechnicMainPath() ne retourne pas null.</li>
	 * <li>garantit que getCoucheAppTechnicMainPath() retourne un singleton.</li>
	 * <li>garantit que getCoucheAppTechnicMainPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCoucheAppTechnicMainPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetCoucheAppTechnicMainPath() ********** ");
		}	
		
		final Path coucheAppTechnicMainPath 
			= ArboresceurPresentProjet.getCoucheAppTechnicMainPath();
	
		final Path coucheAppTechnicMainPath2 
			= ArboresceurPresentProjet.getCoucheAppTechnicMainPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("coucheAppTechnicMainPath : " + coucheAppTechnicMainPath);
		}
	
		/* garantit que getCoucheAppTechnicMainPath() ne retourne pas null. */
		assertNotNull(
				"getCoucheAppTechnicMainPath() ne doit pas retourner null : "
				, coucheAppTechnicMainPath);
		
		/* garantit que getCoucheAppTechnicMainPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, coucheAppTechnicMainPath
					, coucheAppTechnicMainPath2);
		
		/* garantit que getCoucheAppTechnicMainPath() retourne le bon path. */
		assertEquals("coucheAppTechnicMainPath doit valoir racineSourcesJava + apptechnic : "
				, resoudrePathRelatifParRapportARacineSourcesJava(
						ArboresceurPresentProjet.PATH_RELATIF_APPTECHNNIC)
				, coucheAppTechnicMainPath);

	} // Fin de testGetCoucheAppTechnicMainPath()._________________________

	
	
	/**
	 * teste la méthode getCoucheAppTechnicTestPath().<br/>
	 * <ul>
	 * <li>garantit que getCoucheAppTechnicTestPath() ne retourne pas null.</li>
	 * <li>garantit que getCoucheAppTechnicTestPath() retourne un singleton.</li>
	 * <li>garantit que getCoucheAppTechnicTestPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCoucheAppTechnicTestPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetCoucheAppTechnicTestPath() ********** ");
		}	
		
		final Path coucheAppTechnicTestPath	
			= ArboresceurPresentProjet.getCoucheAppTechnicTestPath();
	
		final Path coucheAppTechnicTestPath2 
			= ArboresceurPresentProjet.getCoucheAppTechnicTestPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("coucheAppTechnicTestPath : " + coucheAppTechnicTestPath);
		}	
			
		/* garantit que getCoucheAppTechnicTestPath() ne retourne pas null. */
		assertNotNull(
				"getCoucheAppTechnicTestPath() ne doit pas retourner null : "
				, coucheAppTechnicTestPath);
		
		/* garantit que getCoucheAppTechnicTestPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, coucheAppTechnicTestPath
					, coucheAppTechnicTestPath2);
		
		/* garantit que getCoucheAppTechnicTestPath() retourne le bon path. */
		assertEquals("coucheAppTechnicTestPath doit valoir racineTestsJava + apptechnic : "
				, resoudrePathRelatifParRapportARacineTestsJava(
						ArboresceurPresentProjet.PATH_RELATIF_APPTECHNNIC)
						, coucheAppTechnicTestPath);
		
	} // Fin de testGetCoucheAppTechnicTestPath()._________________________
	
	
	
	/**
	 * teste la méthode getCoucheControllersMainPath().<br/>
	 * <ul>
	 * <li>garantit que getCoucheControllersMainPath() ne retourne pas null.</li>
	 * <li>garantit que getCoucheControllersMainPath() retourne un singleton.</li>
	 * <li>garantit que getCoucheControllersMainPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCoucheControllersMainPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetCoucheControllersMainPath() ********** ");
		}	
		
		final Path coucheControllersMainPath 
			= ArboresceurPresentProjet.getCoucheControllersMainPath();
	
		final Path coucheControllersMainPath2 
			= ArboresceurPresentProjet.getCoucheControllersMainPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("coucheControllersMainPath : " + coucheControllersMainPath);
		}
	
		/* garantit que getCoucheControllersMainPath() ne retourne pas null. */
		assertNotNull(
				"getCoucheControllersMainPath() ne doit pas retourner null : "
				, coucheControllersMainPath);
		
		/* garantit que getCoucheControllersMainPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, coucheControllersMainPath
					, coucheControllersMainPath2);
		
		/* garantit que getCoucheControllersMainPath() retourne le bon path. */
		assertEquals("coucheControllersMainPath doit valoir racineSourcesJava + controllers : "
				, resoudrePathRelatifParRapportARacineSourcesJava(
						ArboresceurPresentProjet.PATH_RELATIF_CONTROLLERS)
				, coucheControllersMainPath);

	} // Fin de testGetCoucheControllersMainPath()._________________________

	
	
	/**
	 * teste la méthode getCoucheControllersTestPath().<br/>
	 * <ul>
	 * <li>garantit que getCoucheControllersTestPath() ne retourne pas null.</li>
	 * <li>garantit que getCoucheControllersTestPath() retourne un singleton.</li>
	 * <li>garantit que getCoucheControllersTestPath() retourne le bon path.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetCoucheControllersTestPath() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testGetCoucheControllersTestPath() ********** ");
		}	
		
		final Path coucheControllersTestPath	
			= ArboresceurPresentProjet.getCoucheControllersTestPath();
	
		final Path coucheControllersTestPath2 
			= ArboresceurPresentProjet.getCoucheControllersTestPath();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("coucheControllersTestPath : " + coucheControllersTestPath);
		}	
			
		/* garantit que getCoucheControllersTestPath() ne retourne pas null. */
		assertNotNull(
				"getCoucheControllersTestPath() ne doit pas retourner null : "
				, coucheControllersTestPath);
		
		/* garantit que getCoucheControllersTestPath() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, coucheControllersTestPath
					, coucheControllersTestPath2);
		
		/* garantit que getCoucheControllersTestPath() retourne le bon path. */
		assertEquals("coucheControllersTestPath doit valoir racineTestsJava + apptechnic : "
				, resoudrePathRelatifParRapportARacineTestsJava(
						ArboresceurPresentProjet.PATH_RELATIF_CONTROLLERS)
						, coucheControllersTestPath);
		
	} // Fin de testGetCoucheControllersTestPath().________________________


	
	/**
	 * Résoud (ajoute) le Path pPathRelatif au path 
	 * du présent projet ECLIPSE.<br/>
	 *
	 * @param pPathRelatif : Path
	 * 
	 * @return : Path : Path résolu.<br/>
	 */
	private Path resoudrePathRelatifParRapportAProjet(
			final Path pPathRelatif) {
		
		Path pathResultat = null;
		
		final Path pathProjet 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		if (pPathRelatif == null) {
			pathResultat = pathProjet;
		} else {
			pathResultat = pathProjet.resolve(pPathRelatif)
							.toAbsolutePath().normalize();
		}
		
		return pathResultat;
		
	} // Fin de resoudrePathRelatifParRapportAProjet(...)._________________


	
	/**
	 * Résoud (ajoute) le Path pPathRelatif au path 
	 * du src/main/java du présent projet ECLIPSE.<br/>
	 *
	 * @param pPathRelatif : Path
	 * 
	 * @return : Path : Path résolu.<br/>
	 */
	private Path resoudrePathRelatifParRapportASrcMainJava(
			final Path pPathRelatif) {
		
		Path pathResultat = null;
		
		final Path pathProjet 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		final Path pathSrcMainJava 
			= pathProjet.resolve(
					ArboresceurPresentProjet.SRC_MAIN_JAVA_PATH_RELATIF);
		
		if (pPathRelatif == null) {
			pathResultat = pathSrcMainJava;
		} else {
			pathResultat = pathSrcMainJava.resolve(pPathRelatif)
							.toAbsolutePath().normalize();
		}
		
		return pathResultat;
		
	} // Fin de resoudrePathRelatifParRapportASrcMainJava(...).____________


	
	/**
	 * Résoud (ajoute) le Path pPathRelatif au path 
	 * du src/test/java du présent projet ECLIPSE.<br/>
	 *
	 * @param pPathRelatif : Path
	 * 
	 * @return : Path : Path résolu.<br/>
	 */
	private Path resoudrePathRelatifParRapportASrcTestJava(
			final Path pPathRelatif) {
		
		Path pathResultat = null;
		
		final Path pathProjet 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		final Path pathSrcTestJava 
			= pathProjet.resolve(
					ArboresceurPresentProjet.SRC_TEST_JAVA_PATH_RELATIF);
		
		if (pPathRelatif == null) {
			pathResultat = pathSrcTestJava;
		} else {
			pathResultat = pathSrcTestJava.resolve(pPathRelatif)
							.toAbsolutePath().normalize();
		}
		
		return pathResultat;
		
	} // Fin de resoudrePathRelatifParRapportASrcTestJava(...).____________


	
	/**
	 * Résoud (ajoute) le Path pPathRelatif au path 
	 * du src/main/java/groupId/ du présent projet ECLIPSE.<br/>
	 *
	 * @param pPathRelatif : Path
	 * 
	 * @return : Path : Path résolu.<br/>
	 */
	private Path resoudrePathRelatifParRapportARacineSourcesJava(
			final Path pPathRelatif) {
		
		Path pathResultat = null;
		
		final Path pathProjet 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		final Path pathSrcMainJava 
			= pathProjet.resolve(
					ArboresceurPresentProjet.SRC_MAIN_JAVA_PATH_RELATIF);
		
		final Path pathRacineSourcesJava 
			= pathSrcMainJava
				.resolve(ArboresceurPresentProjet.getGroupIdPathRelatif());
		
		if (pPathRelatif == null) {
			pathResultat = pathRacineSourcesJava;
		} else {
			pathResultat = pathRacineSourcesJava.resolve(pPathRelatif)
							.toAbsolutePath().normalize();
		}
		
		return pathResultat;
		
	} // Fin de resoudrePathRelatifParRapportARacineSourcesJava(...).______


	
	/**
	 * Résoud (ajoute) le Path pPathRelatif au path 
	 * du src/test/java/groupId/ du présent projet ECLIPSE.<br/>
	 *
	 * @param pPathRelatif : Path
	 * 
	 * @return : Path : Path résolu.<br/>
	 */
	private Path resoudrePathRelatifParRapportARacineTestsJava(
			final Path pPathRelatif) {
		
		Path pathResultat = null;
		
		final Path pathProjet 
			= ArboresceurPresentProjet.getProjetSourcePath();
		
		final Path pathSrcTestJava 
			= pathProjet.resolve(
					ArboresceurPresentProjet.SRC_TEST_JAVA_PATH_RELATIF);
		
		final Path pathRacineTestsJava 
			= pathSrcTestJava
				.resolve(ArboresceurPresentProjet.getGroupIdPathRelatif());
		
		if (pPathRelatif == null) {
			pathResultat = pathRacineTestsJava;
		} else {
			pathResultat = pathRacineTestsJava.resolve(pPathRelatif)
							.toAbsolutePath().normalize();
		}
		
		return pathResultat;
		
	} // Fin de resoudrePathRelatifParRapportARacineTestsJava(...).______
	
	
	
} // FIN DE LA CLASSE ArboresceurPresentProjetTest.--------------------------
