package levy.daniel.application.model.services.correcteurs.metier.sections.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;

/**
 * CLASSE SectionHitCorrecteurTabulationService :<br/>
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
 * @since 12 sept. 2019
 *
 */
public class SectionHitCorrecteurTabulationService {

	// ************************ATTRIBUTS************************************/

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitCorrecteurTabulationService.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SectionHitCorrecteurTabulationService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * : void :  .<br/>
	 * @throws IOException 
	 */
	public static void calculerLongueursLignes() throws IOException {
				
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("2018/HITDIRA2018.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");

		final List<String> listeLignes 
			= Files.readAllLines(fichierDonneesPath, charsetAnsi);
		
		int compteur = 0;
		
		for (final String ligne : listeLignes) {
			compteur++;
			System.out.println("ligne " + compteur + " - longueur : " + ligne.length());
		}
		
	}
	

	
	/**
	 * .<br/>
	 * : void :  .<br/>
	 * @throws Exception 
	 */
	public static void detecterTab() throws Exception {
				
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("2018/HITDIRA2018.txt");
		final File fichierDonnees = fichierDonneesPath.toFile();
		final Charset charsetAnsi = Charset.forName("Windows-1252");

		final List<String> listeLignes 
			= Files.readAllLines(fichierDonneesPath, charsetAnsi);
		
		final String motifTabulation = "\\t";
		final Pattern patternTabulation = Pattern.compile(motifTabulation);
		
		int compteur = 0;
		
		for (final String ligne : listeLignes) {
			
			compteur++;
			System.out.println(String.format(
					LocaleManager.getLocaleParDefautApplication()
					, "ligne %1$-7d - longueur : %2$-3d", compteur, ligne.length()));
			
			final Matcher matcherTabulation = patternTabulation.matcher(ligne);
			
			while (matcherTabulation.find()) {
				
				final String tabulationString = matcherTabulation.group();
				final int positionDebutTab = matcherTabulation.start();
				final int positionFinTab = matcherTabulation.end();
					
				String nomChamp = "";
				
				final SortedMap<Integer, IDescriptionChamp> descriptionMap 
					= FactoryDescription.getDecriptionHitMap();
				
				for (final IDescriptionChamp desc : descriptionMap.values()) {
					
					final DescriptionChampHit descHit 
						= (DescriptionChampHit) desc;
					
					final String champLocal = descHit.getChampJava();
					final int debutChamp = descHit.getColonneDebut();
					final int finChamp = descHit.getColonneFin();
					
					if (positionDebutTab >= debutChamp && positionFinTab <= finChamp) {
						nomChamp = champLocal;
						break;
					}
				}
				
				System.out.println("ligne " + compteur + " - position début tabulation : " + positionDebutTab + " - position fin tabulation : " + positionFinTab + "nom du champ : " + nomChamp);
				
			}
			
		}
		
	}
	
	
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(final String... pArgs) throws Exception {
		
		detecterTab();
		
	} // Fin de main(...)._________________________________________________

	
} // FIN DE LA CLASSE SectionHitCorrecteurTabulationService.-----------------
