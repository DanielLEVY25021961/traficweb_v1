package levy.daniel.application.model.services.correcteurs.metier.sections.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
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
public final class SectionHitCorrecteurTabulationService {

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
	 * "\\t".
	 */
	public static final  String MOTIF_TABULATION = "\\t";
		
	/**
	 * Pattern.compile(MOTIF_TABULATION).
	 */
	public static final Pattern PATTERN_TABULATION 
		= Pattern.compile(MOTIF_TABULATION);
	
	/**
	 * "\\s*\\t{1}\\s*".
	 */
	public static final  String MOTIF_TABULATION_ESPACE = "\\s*\\t{1}\\s*";
		
	/**
	 * Pattern.compile(MOTIF_TABULATION_ESPACE).
	 */
	public static final Pattern PATTERN_TABULATION_ESPACE 
		= Pattern.compile(MOTIF_TABULATION_ESPACE);

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
	private SectionHitCorrecteurTabulationService() {
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
				
				final int positionDebutTab = matcherTabulation.start() + 1;
				final int positionFinTab = matcherTabulation.end() + 1;
					
				String nomChamp = "";
				int debutChamp = 0;
				int finChamp = 0;
				
				final SortedMap<Integer, IDescriptionChamp> descriptionMap 
					= FactoryDescription.getDecriptionHitMap();
				
				for (final IDescriptionChamp desc : descriptionMap.values()) {
					
					final DescriptionChampHit descHit 
						= (DescriptionChampHit) desc;
					
					final String champLocal = descHit.getChampJava();
					debutChamp = descHit.getColonneDebut();
					finChamp = descHit.getColonneFin();
					
					if (positionDebutTab >= debutChamp && positionFinTab <= finChamp) {
						nomChamp = champLocal;
						
						break;
					}
				}
				
				System.out.println("ligne " + compteur + " - position début tabulation : " + positionDebutTab + " - position fin tabulation : " + positionFinTab + " - nom du champ : " + nomChamp + " - début du champ : " + debutChamp + " - fin du champ : " + finChamp);
				
			}
			
		}
		
	}
	

	
	/**
	 * .<br/>
	 * : void :  .<br/>
	 * @throws Exception 
	 */
	public static void supprimerTabulations() throws Exception {
				
		final Path fichierDonneesPath 
			= PATH_ABSOLU_TEST_JEUX_ESSAI.resolve("2018/HITDIRA2018.txt");

		final Charset charsetAnsi = Charset.forName("Windows-1252");

		final List<String> listeLignes 
			= Files.readAllLines(fichierDonneesPath, charsetAnsi);
		
		
		int compteur = 0;
		
		for (final String ligne : listeLignes) {
			
			compteur++;
			
			System.out.println("******ligne ORIGINALE : " + ligne);
			
			System.out.println(String.format(
					LocaleManager.getLocaleParDefautApplication()
					, "******ligne %1$-7d - longueur : %2$-3d", compteur, ligne.length()));
						
			final String ligneEpureeDesTabs = supprimerTabulation(compteur, ligne, 0);
			
			System.out.println(ligneEpureeDesTabs);
			System.out.println("longueur de la ligne épurée : " + ligneEpureeDesTabs.length());
			
		}
		
	}
	


	/**
	 * .<br/>
	 * <br/>
	 * @param pCompteur 
	 *
	 * @param pLigne
	 * @param pCompteurMatches 
	 * @return : String :  .<br/>
	 * @throws Exception 
	 */
	public static String supprimerTabulation(final int pCompteur, final String pLigne, int pCompteurMatches) throws Exception {
		
		final Matcher matcherTabulationEspace 
			= PATTERN_TABULATION_ESPACE.matcher(pLigne);
		
		System.out.println();
		System.out.println("***********************************************************************************");
		System.out.println("pLigne : " + pLigne);
		System.out.println("LONGUEUR de pLigne : " + pLigne.length());
		System.out.println("***********************************************************************************");
		System.out.println();
		
		if (matcherTabulationEspace.find()) {
			
			pCompteurMatches++;
			
			final int positionDebutTab = matcherTabulationEspace.start() + 1;
			final int positionFinTab = matcherTabulationEspace.end();
			
			System.out.println("OCCURENCE : " + pCompteurMatches);
			System.out.println("ligne " + pCompteur + " - position début tabulation : " + positionDebutTab + " - position fin tabulation : " + positionFinTab);
			System.out.println("longueur de ligne : " + pLigne.length());
				
			final DescriptionChampHit descHit 
				= fournirDescChamp(positionDebutTab, positionFinTab);
			
			if (descHit == null) {
				return null;
			}
			
			final String nomChamp = descHit.getNomChampJava();
			final int debutChamp = descHit.getColonneDebut();
			final int finChamp = descHit.getColonneFin();
			final int longueurChamp = descHit.getLongueur();
			
			String ligne = pLigne;

			System.out.println("OCCURENCE : " + pCompteurMatches);
			System.out.println("ligne " + pCompteur + " - position début tabulation : " + positionDebutTab + " - position fin tabulation : " + positionFinTab + " - nom du champ : " + nomChamp + " - début du champ : " + debutChamp + " - fin du champ : " + finChamp + " - longueur du champ : " + longueurChamp);
			System.out.println("longueur de ligne : " + ligne.length());
			
			// ************************************************************************
			final String debutLigne = StringUtils.substring(ligne, 0, matcherTabulationEspace.start());
			// ************************************************************************
			
			System.out.println();
			System.out.println("DEBUT ligne : " + debutLigne);
			System.out.println("longueur de DEBUT ligne : " + debutLigne.length());
			
			final String espacesDeRemplacement = fournirEspaces(longueurChamp);
			System.out.println();
			System.out.println("longueur espaces de remplacement : " + espacesDeRemplacement.length());
			
			final String debutLigneAugmente = debutLigne + espacesDeRemplacement;
			
			System.out.println();
			System.out.println("DEBUT ligne AUGMENTE : " + debutLigneAugmente);
			System.out.println("longueur de DEBUT ligne AUGMENTE : " + debutLigneAugmente.length());
			

			// ************************************************************************
			final String finLigne = StringUtils.substring(ligne, matcherTabulationEspace.end());
			// ************************************************************************
			
			System.out.println();
			System.out.println("FIN ligne : " + finLigne);
			System.out.println("longueur de FIN ligne : " + finLigne.length());
			
			ligne = debutLigneAugmente + finLigne;
			
			System.out.println();
			System.out.println("********** ligne reconstituee : " + ligne);
			System.out.println("********** longueur de ligne reconstituee : " + ligne.length());
			
//			matcherTabulationEspace.reset();
			
			return supprimerTabulation(pCompteur, ligne, pCompteurMatches);

		}
			
		return pLigne;

	}
	

	
	/**
	 * retourne la DescriptionChampHit du champ HIT qui contient 
	 * complètement le segment [pDebut - pFin] (1-based) 
	 * dans un fichier HIT.<br/>
	 * <br/>
	 * - retourne null si aucun champ du HIT n'englobe entièrement 
	 * [pDebut - pFin].<br/>
	 * <br/>
	 *
	 * @param pDebut : int : début de la position 1-based du segment.
	 * @param pFin : int : fin de la position 1-based du segment.
	 * 
	 * @return : DescriptionChampHit.<br/>
	 * 
	 * @throws Exception 
	 */
	public static DescriptionChampHit fournirDescChamp(
			final int pDebut, final int pFin) throws Exception {
		
		final SortedMap<Integer, IDescriptionChamp> descriptionMap 
		= FactoryDescription.getDecriptionHitMap();
		
		for (final IDescriptionChamp desc : descriptionMap.values()) {
			
			final DescriptionChampHit descHit 
				= (DescriptionChampHit) desc;
			
			final int debutChamp = descHit.getColonneDebut();
			final int finChamp = descHit.getColonneFin();
			
			if (pDebut >= debutChamp && pFin <= finChamp) {
				return descHit;
			} 
		}
		
		return null;
		
	} // Fin de fournirDescChamp(...)._____________________________________
	

	
	/**
	 * retourne le successeur immédiat de pDesc 
	 * dans la description de fichier.<br/>
	 * <br/>
	 * - retourne null si pDesc == null.<br/>
	 * - retourne null si pDesc n'a pas de successeur 
	 * dans la description de fichier.<br/>
	 * <br/>
	 *
	 * @param pDesc : DescriptionChampHit.<br/>
	 * 
	 * @return : DescriptionChampHit.<br/>
	 * 
	 * @throws Exception
	 */
	public static DescriptionChampHit fournirDescChampSuivant(
					final DescriptionChampHit pDesc) throws Exception {
		
		/* retourne null si pDesc == null. */
		if (pDesc == null) {
			return null;
		}
				
		final SortedMap<Integer, IDescriptionChamp> descriptionMap 
			= FactoryDescription.getDecriptionHitMap();
		
		final Integer numeroOrdre = pDesc.getOrdreChamps();
		final Integer numeroOrdreAChercher = numeroOrdre + 1;
		
		/* retourne null si pDesc n'a pas de successeur 
		 * dans la description de fichier. */
		if (numeroOrdreAChercher > descriptionMap.size()) {
			return null;
		}
		
		return (DescriptionChampHit) descriptionMap.get(numeroOrdreAChercher);
		
	} // Fin de fournirDescChampSuivant(...).______________________________

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pLongueurChamp
	 * @return : String :  .<br/>
	 */
	public static String fournirEspaces(final int pLongueurChamp) {
		
		if (pLongueurChamp == 0) {
			return "";
		}
		
		String resultat = "";
		
		for (int i = 0; i < pLongueurChamp; i++) {
			resultat = resultat + " ";
		}
		
		return resultat;
		
	}

	
	
	/**
	 * .<br/>
	 *
	 * @param pEntree : void :  .<br/>
	 */
	public static void trouverTabEspaces(final String pEntree) {

		final Pattern patternTabEspaces = Pattern.compile("\\s*\\t{1}\\s*");
		final Matcher matcher = patternTabEspaces.matcher(pEntree);

		int compteur = 0;
		
		while (matcher.find()) {
			
			compteur ++;
			
			System.out.println();
			System.out.println("****************************************************");
			System.out.println("occurence : " + compteur);
			System.out.println("début String : " + pEntree.substring(0, matcher.start()));
//			System.out.println(pEntree.substring(matcher.start(), matcher.end()));
			System.out.println("début tab espace (0-based) : " + matcher.start());
			System.out.println("fin tab espace (0-based) : " + matcher.end());
			System.out.println("longueur tab espacé : " + (matcher.end() - matcher.start()));
			System.out.println("fin String : " + pEntree.substring(matcher.end()));
			System.out.println("****************************************************");
			
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
		
//		supprimerTabulations();
		final String ligne62 ="330504601310017 02300 411 601ECH.25              0400703A 10                0440507S47 EXTER ET INTER  0440013003800003800          000       0290      123374 1407    114879	119300	 123949	  126142   125648   126199   135383   135208   127209	125708	 109008	  111569               171232491 1407    161237781 1367    151204371 1377    140000001         130000001         113791   119552   120745   125771   123307   125866   133235   131262   125333   123944   119420   116762                                      ";
		supprimerTabulation(62, ligne62, 0);
		
	} // Fin de main(...)._________________________________________________

	
} // FIN DE LA CLASSE SectionHitCorrecteurTabulationService.-----------------
