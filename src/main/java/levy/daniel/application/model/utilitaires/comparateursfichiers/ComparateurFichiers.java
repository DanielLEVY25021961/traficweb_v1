package levy.daniel.application.model.utilitaires.comparateursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;

/**
 * CLASSE ComparateurFichiers :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 6 juin 2019
 *
 */
public final class ComparateurFichiers {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe ComparateurFichiers".
	 */
	public static final String CLASSE_COMPARATEURS_FICHIERS 
		= "Classe ComparateurFichiers";
	
	/**
	 * "Méthode compareFichiersLigneALigne(
	 * File pFile1, Charset pCharset1, File pFile2, Charset pCharset2".
	 */
	public static final String METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
		= "Méthode compareFichiersLigneALigne(File pFile1, Charset pCharset1"
				+ ", File pFile2, Charset pCharset2";
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * "."
	 */
	public static final String POINT = ".";
	
	/**
	 * Tabulation "\t".<br/>
	 */
	public static final String TAB = "\t";
	
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	/**
	 * rapport de comparaison non null lorsque les 2 fichiers 
	 * comparés ne sont pas égaux.<br/>
	 * instancié à chaque appel de la méthode 
	 * compareFichiersLigneALigne(...).<br/>
	 */
	private static String rapportComparaison;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ComparateurFichiers.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 */
	private ComparateurFichiers() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>détermine si deux fichiers textuels sont absolument identiques</b>.
	 * <ul>
	 * <li>utilise <code>Files.readAllLines(pFile.toPath(), charset);</code> 
	 * pour lire les lignes de chaque fichier.<br/>
	 * Peut donc jeter une MalformedInputException si le 
	 * charset ne correspond pas au Charset d'encodage d'un fichier.</li>
	 * <li>retourne false si les deux fichiers n'ont pas 
	 * le même nombre de lignes.</li>
	 * <li>passe automatiquement charset1 à UTF-8 si pCharset1 == null.</li>
	 * <li>passe automatiquement charset2 à UTF-8 si pCharset2 == null.</li>
	 * <li>retourne false si 2 lignes de même numéro d'ordre n'ont pas la 
	 * même longueur dans les deux fichiers.</li>
	 * <li>retourne false si 2 lignes de même numéro d'ordre 
	 * ne sont pas égales dans les deux fichiers.</li>
	 * <li></li>
	 * </ul>
	 * - retourne false si pFile1 est null, inexistant ou 
	 * pas normal (répertoire).<br/>
	 * - retourne false si pFile1 est null, inexistant ou 
	 * pas normal (répertoire).<br/>
	 * <br/>
	 *
	 * @param pFile1 : File : 1er fichier à comparer.
	 * @param pCharset1 : Charset : Charset d'encodage du 1er fichier.
	 * @param pFile2 : File : 2ème fichier à comparer.
	 * @param pCharset2 : Charset : Charset d'encodage du 2ème fichier.
	 * 
	 * @return : boolean : 
	 * true si les deux fichiers textuels sont absolument identiques.<br/>
	 * 
	 * @throws Exception 
	 */
	public static boolean compareFichiersLigneALigne(
			final File pFile1, final Charset pCharset1
				, final File pFile2, final Charset pCharset2) throws Exception {
		
		synchronized (ComparateurFichiers.class) {
			
			/* retourne false si pFile1 est null, inexistant ou pas normal 
			 * (répertoire). */
			if (pFile1 == null || !pFile1.exists() || !pFile1.isFile()) {
				
				final String message 
					= "le 1er fichier est null, inexistant ou pas normal";
				
				rapportComparaison = message;
				
				return false;
			}
			
			/* retourne false si pFile2 est null, inexistant ou pas normal 
			 * (répertoire). */
			if (pFile2 == null || !pFile2.exists() || !pFile2.isFile()) {
				
				final String message 
					= "le 2ème fichier est null, inexistant ou pas normal";
			
				rapportComparaison = message;
				
				return false;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			/* passe automatiquement charset1 à UTF-8 si pCharset1 == null. */
			Charset charset1 = null;
			
			if (pCharset1 == null) {
				charset1 = StandardCharsets.UTF_8;
			}
			else {
				charset1 = pCharset1;
			}
			
			/* passe automatiquement charset2 à UTF-8 si pCharset2 == null. */
			Charset charset2 = null;
			
			if (pCharset2 == null) {
				charset2 = StandardCharsets.UTF_8;
			}
			else {
				charset2 = pCharset2;
			}

			List<String> liste1 = null;
			List<String> liste2 = null;
			
			try {
				liste1 = Files.readAllLines(pFile1.toPath(), charset1);
			} catch (Exception e1) {
				
				final String message 
				= CLASSE_COMPARATEURS_FICHIERS 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Il est impossible de lire le fichier : " 
				+ pFile1.getAbsolutePath() 
				+ " avec le Charset : " 
				+ charset1.displayName(LocaleManager.getLocaleApplication());
			
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e1);
				}
				
				throw new ExceptionImport(message, e1);
				
			}
			
			try {
				liste2 = Files.readAllLines(pFile2.toPath(), charset2);
			} catch (Exception e2) {
				
				final String message 
				= CLASSE_COMPARATEURS_FICHIERS 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Il est impossible de lire le fichier : " 
				+ pFile2.getAbsolutePath() 
				+ " avec le Charset : " 
				+ charset2.displayName(LocaleManager.getLocaleApplication());
			
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e2);
				}
				
				throw new ExceptionImport(message, e2);
				
			}
			
			/* retourne false si les deux fichiers n'ont pas 
			 * le même nombre de lignes. */
			if (liste1.size() != liste2.size()) {
				
				final String message 
					= "Les 2 fichiers n'ont pas le même nombre de lignes : " 
							+ liste1.size() 
							+ " lignes pour le 1er fichier et " 
							+ liste2.size() 
							+ " lignes pour le 2ème fichier";
				
				stb.append(message);

				return false;
			}
			
			/* COMPARAISON LIGNE A LIGNE. */
			int nombreDeLignes = liste1.size();
			String ligne1 = null;
			String ligne2 = null;
			
			for (int i = 0; i < nombreDeLignes; i++) {
				
				ligne1 = liste1.get(i);
				ligne2 = liste2.get(i);
				
				if (ligne1 != null) {
					
					if (ligne2 == null) {
						
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " est nulle dans le 2ème fichier et "
						+ "pas dans le 1er fichier";

						stb.append(message);
						
						rapportComparaison = stb.toString();
						
						return false;
					}
					
					/* retourne false si 2 lignes de même numéro d'ordre 
					 * n'ont pas la même longueur dans les deux fichiers. */
					if (ligne1.length() != ligne2.length()) {
											
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " a une longueur de " 
						+ ligne1.length() 
						+ " caractères dans le 1er fichier et "
						+ "une longueur de " 
						+ ligne2.length() 
						+ " caractères dans le 2ème fichier.";

						stb.append(message);
						
						rapportComparaison = stb.toString();
						
						return false;
					}
					
					/* retourne false si 2 lignes de même numéro d'ordre 
					 * ne sont pas égales dans les deux fichiers. */
					if (!StringUtils.equals(ligne1, ligne2)) {
																	
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " dans le 1er fichier n'est pas égale à la ligne "
						+ "correspondante dans le deuxième fichier : " 
						+ ligne1 
						+ NEWLINE
						+ ligne2;
					
						stb.append(message);
						
						rapportComparaison = stb.toString();

						return false;
					}
		
				} else {
					
					if (ligne2 != null) {
						
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " est nulle dans le 1er fichier et "
						+ "pas dans le 2ème fichier";

						stb.append(message);
						
						rapportComparaison = stb.toString();

						return false;
					}
					
				}
				
			} // Fin de la boucle sur les lignes.______________
					
			return true;
			
		} // Fin de synchronized.___________________________
		
	} // Fin de compareFichiersLigneALigne(...).___________________________


	
	/**
	 * Getter du rapport de comparaison non null lorsque les 2 fichiers 
	 * comparés ne sont pas égaux.<br/>
	 * instancié à chaque appel de la méthode 
	 * compareFichiersLigneALigne(...).<br/>
	 *
	 * @return this.rapportComparaison : String.<br/>
	 */
	public static String getRapportComparaison() {
		return rapportComparaison;
	} // Fin de getRapportComparaison().___________________________________
		
	 
	
} // FIN DE LA CLASSE ComparateurFichiers.-----------------------------------
