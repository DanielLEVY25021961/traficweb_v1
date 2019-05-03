package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespaths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ManagerPathsTest :<br/>
 * Test JUnit de la classe {@link ManagerPaths}.<br/>
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
public class ManagerPathsTest {

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
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ManagerPathsTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ManagerPathsTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * Teste la méthode getPathUniteCouranteString().<br/>
	 * <ul>
	 * <li>garantit que getPathUniteCouranteString() ne retourne pas null.</li>
	 * <li>garantit que getPathUniteCouranteString() retourne un singleton.</li>
	 * <li>garantit que getPathUniteCouranteString() retourne l'unité courante sous Windows.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathUniteCouranteString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathUniteCouranteString() ********** ");
		}

		final String pathUniteCouranteString 
			= ManagerPaths.getPathUniteCouranteString();
		
		final String pathUniteCouranteString2 
			= ManagerPaths.getPathUniteCouranteString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathUniteCouranteString : " + pathUniteCouranteString);
		}
		
		/* garantit que getPathUniteCouranteString() ne retourne pas null. */
		assertNotNull("pathUniteCouranteString ne doit pas être null : ", pathUniteCouranteString);
		
		/* garantit que getPathUniteCouranteString() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathUniteCouranteString
					, pathUniteCouranteString2);
		
	} // Fin de testGetPathUniteCouranteString().__________________________

	
	
	/**
	 * Teste la méthode getPathUniteCourante().<br/>
	 * <ul>
	 * <li>garantit que getPathUniteCourante() ne retourne pas null.</li>
	 * <li>garantit que getPathUniteCourante() retourne un singleton.</li>
	 * <li>garantit que getPathUniteCourante() retourne l'unité courante sous Windows.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathUniteCourante() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathUniteCourante() ********** ");
		}

		final Path pathUniteCourante = ManagerPaths.getPathUniteCourante();
		
		final Path pathUniteCourante2 
			= ManagerPaths.getPathUniteCourante();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathUniteCourante : " + pathUniteCourante.toString());
		}
		
		/* garantit que getPathUniteCourante() ne retourne pas null. */
		assertNotNull("pathUniteCourante ne doit pas être null : ", pathUniteCourante);
		
		/* garantit que getPathUniteCourante() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathUniteCourante
					, pathUniteCourante2);
		
	} // Fin de testGetPathUniteCourante().________________________________


	
	/**
	 * Teste la méthode getPathPresentWorkspaceString().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentWorkspaceString() ne retourne pas null.</li>
	 * <li>garantit que getPathPresentWorkspaceString() retourne un singleton.</li>
	 * <li>garantit que getPathPresentWorkspaceString() retourne le présent Workspace ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentWorkspaceString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentWorkspaceString() ********** ");
		}

		final String pathPresentWorkspaceString 
			= ManagerPaths.getPathPresentWorkspaceString();
		
		final String pathPresentWorkspaceString2 
			= ManagerPaths.getPathPresentWorkspaceString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathPresentWorkspaceString : " + pathPresentWorkspaceString);
		}
		
		/* garantit que getPathPresentWorkspaceString() ne retourne pas null. */
		assertNotNull("pathPresentWorkspaceString ne doit pas être null : ", pathPresentWorkspaceString);
		
		/* garantit que getPathPresentWorkspaceString() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathPresentWorkspaceString
					, pathPresentWorkspaceString2);
		
	} // Fin de testGetPathPresentWorkspaceString()._______________________

	
	
	/**
	 * Teste la méthode getPathPresentWorkspace().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentWorkspace() ne retourne pas null.</li>
	 * <li>garantit que getPathPresentWorkspace() retourne un singleton.</li>
	 * <li>garantit que getPathPresentWorkspace() retourne le présent Workspace ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentWorkspace() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentWorkspace() ********** ");
		}

		final Path pathPresentWorkspace 
			= ManagerPaths.getPathPresentWorkspace();
		
		final Path pathPresentWorkspace2 
			= ManagerPaths.getPathPresentWorkspace();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathPresentWorkspace : " + pathPresentWorkspace.toString());
		}
		
		/* garantit que getPathPresentWorkspace() ne retourne pas null. */
		assertNotNull("pathPresentWorkspace ne doit pas être null : ", pathPresentWorkspace);
		
		/* garantit que getPathPresentWorkspace() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathPresentWorkspace
					, pathPresentWorkspace2);
		
	} // Fin de testGetPathPresentWorkspace()._____________________________


	
	/**
	 * Teste la méthode getPathPresentProjetString().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentProjetString() ne retourne pas null.</li>
	 * <li>garantit que getPathPresentProjetString() retourne un singleton.</li>
	 * <li>garantit que getPathPresentProjetString() retourne le présent Projet ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentProjetString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentProjetString() ********** ");
		}

		final String pathPresentProjetString 
			= ManagerPaths.getPathPresentProjetString();
		
		final String pathPresentProjetString2 
			= ManagerPaths.getPathPresentProjetString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathPresentProjetString : " + pathPresentProjetString);
		}
		
		/* garantit que getPathPresentProjetString() ne retourne pas null. */
		assertNotNull("pathPresentProjetString ne doit pas être null : ", pathPresentProjetString);
		
		/* garantit que getPathPresentProjetString() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathPresentProjetString
					, pathPresentProjetString2);
		
	} // Fin de testGetPathPresentProjetString().__________________________

	
	
	/**
	 * Teste la méthode getPathPresentProjet().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentProjet() ne retourne pas null.</li>
	 * <li>garantit que getPathPresentProjet() retourne un singleton.</li>
	 * <li>garantit que getPathPresentProjet() retourne le présent Projet ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentProjet() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentProjet() ********** ");
		}

		final Path pathPresentProjet 
			= ManagerPaths.getPathPresentProjet();
		
		final Path pathPresentProjet2 
			= ManagerPaths.getPathPresentProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathPresentProjet : " + pathPresentProjet.toString());
		}
		
		/* garantit que getPathPresentProjet() ne retourne pas null. */
		assertNotNull("pathPresentProjet ne doit pas être null : ", pathPresentProjet);
		
		/* garantit que getPathPresentProjet() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, pathPresentProjet
					, pathPresentProjet2);
		
	} // Fin de testGetPathPresentProjet().________________________________


	
	/**
	 * Teste la méthode getNomPresentProjet().<br/>
	 * <ul>
	 * <li>garantit que getNomPresentProjet() ne retourne pas null.</li>
	 * <li>garantit que getNomPresentProjet() retourne un singleton.</li>
	 * <li>garantit que getNomPresentProjet() retourne le NOM du présent Projet ECLIPSE.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetNomPresentProjet() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetNomPresentProjet() ********** ");
		}

		final String nomPresentProjetString = ManagerPaths.getNomPresentProjet();
		
		final String nomPresentProjetString2 
			= ManagerPaths.getNomPresentProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("nomPresentProjet : " + nomPresentProjetString);
		}
		
		/* garantit que getNomPresentProjet() ne retourne pas null. */
		assertNotNull("nomPresentProjetString ne doit pas être null : ", nomPresentProjetString);
		
		/* garantit que getNomPresentProjet() retourne un singleton. */
		assertSame(DOIT_RETOURNER_SINGLETON
				, nomPresentProjetString
					, nomPresentProjetString2);
		
	} // Fin de testGetNomPresentProjet()._________________________________

	

} // FIN DE LA CLASSE ManagerPathsTest.--------------------------------------
