package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;

/**
 * class AbstractImporteurNomenclature :<br/>
 * Classe abstraite factorisant les attributs et les 
 * méthodes des ImporteurNomenclature.<br/>
 * <br/>
 *  Une nomenclature doit être un fichier csv avec séparateur ';' 
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le sens dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 * <br/>
 * Tous les ImporteurNomenclature possèdent une 
 * méthode importerNomenclature(File pNomenclature) 
 * où pNomenclature encapsule la nomenclature en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.<br/>
 * La nomenclature est servie sous forme de 
 * SortedMap&lt;Integer, String&gt; nomenclatureMap 
 * retournée par importerNomenclature(File pNomenclature).<br/>
 * La méthode importerNomenclature(File pNomenclature) permet également 
 * d'alimenter un Set&lt;Integer&gt; clesPossiblesSet 
 * qui contient toutes les valeurs possibles pour la clé dans la nomenclature.<br/>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * switch-case,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 16 juil. 2014
 *
 */
public abstract class AbstractImporteurNomenclature implements
		IImporteurNomenclature {

	// ************************ATTRIBUTS************************************/
	
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
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	public static final String SAUTDELIGNE_UNIX = "\n";

	
	/**
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	public static final String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne Line Feed LF).
	 */
	public static final String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	
	/**
	 * SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 */
	protected SortedMap<Integer, String> nomenclatureMap;
	
	
	/**
	 * File en csv avec séparateur ';' qui encapsule la nomenclature.<br/>
	 */
	protected File nomenclature;
	
	
	/**
	 * Ensemble des valeurs (Set&lt;Integer&gt;) de clés  
	 * possibles pour la nomenclature.<br/>
	 */
	protected Set<Integer> clesPossiblesSet;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractImporteurNomenclature.class);

	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractImporteurNomenclature() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractImporteurNomenclature() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public final SortedMap<Integer, String> importerNomenclature(
			final File pNomenclature) 
						throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pNomenclature, METHODE_IMPORTER_NOMENCLATURE);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pNomenclature, METHODE_IMPORTER_NOMENCLATURE);

		/* Fichier vide. */
		this.traiterFichierVide(pNomenclature, METHODE_IMPORTER_NOMENCLATURE);

		/* File directory. */
		this.traiterFichierPasNormal(pNomenclature, METHODE_IMPORTER_NOMENCLATURE);

		// ************ PARAMETRES VALIDES *******************************/
		
		/* Passage des paramètres aux attributs. */
		this.nomenclature = pNomenclature;
		
		/* Map résultat. */
		this.nomenclatureMap = new TreeMap<Integer, String>();
		
		/* Set à remplir. */
		this.clesPossiblesSet = new HashSet<Integer>();
		
		/* Ouverture des flux. */
		final FileInputStream fis =  new FileInputStream(this.nomenclature);
		final InputStreamReader isr 
			= new InputStreamReader(fis, Charset.forName("ISO-8859-15"));
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		// LECTURE DES LIGNES.**********************************
		while ((ligneLue = bfr.readLine()) != null) {
			
			/* Instancie un Pattern chargé de retrouver le 
			 * séparateur ';' dans la ligne. */
			final String[] tokens 
				= Pattern.compile(SEP_PV).split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'clé' pour l'en-tête  
			 * et une valeur entière pour toutes les lignes significatives. */
			final String cle = tokens[0];
			
			if (!StringUtils.isBlank(cle)) {
				try {
					Integer.parseInt(cle);
				} catch (NumberFormatException e) {
					continue;
				}
			}
			
			/* DECOMPOSITION DE CHAQUE LIGNE. */
			final Integer cleLue = Integer.parseInt(tokens[0]);
			final String libelleLu = tokens[1];
			
			// AJOUT DANS LA MAP RESULTAT._____
			this.nomenclatureMap.put(cleLue, libelleLu);
			
			// AJOUT DANS LE SET DES CLES POSSIBLES.____
			this.clesPossiblesSet.add(cleLue);
			
		} // FIN LECTURE DES LIGNES.******************************
		
		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();			
				
		return this.nomenclatureMap;
		
	} // Fin de importerNomenclature(
	// File pNomenclature).________________________________________________


	
	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public final SortedMap<Integer, String> importerNomenclatureEnUtf8(
			final File pNomenclature) 
						throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_UTF8);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_UTF8);

		/* Fichier vide. */
		this.traiterFichierVide(pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_UTF8);
				
		/* File directory. */
		this.traiterFichierPasNormal(pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_UTF8);

		// ************ PARAMETRES VALIDES *******************************/
		
		/* Passage des paramètres aux attributs. */
		this.nomenclature = pNomenclature;
		
		/* Map résultat. */
		this.nomenclatureMap = new TreeMap<Integer, String>();
		
		/* Set à remplir. */
		this.clesPossiblesSet = new HashSet<Integer>();
		
		/* Ouverture des flux. */
		final FileInputStream fis =  new FileInputStream(this.nomenclature);
		final InputStreamReader isr 
			= new InputStreamReader(fis, Charset.forName("UTF-8"));
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		// LECTURE DES LIGNES.**********************************
		while ((ligneLue = bfr.readLine()) != null) {
			
			/* Instancie un Pattern chargé de retrouver le 
			 * séparateur ';' dans la ligne. */
			final String[] tokens 
				= Pattern.compile(SEP_PV).split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'clé' pour l'en-tête  
			 * et une valeur entière pour toutes les lignes significatives. */
			final String cle = tokens[0];
			
			if (!StringUtils.isBlank(cle)) {
				try {
					Integer.parseInt(cle);
				} catch (NumberFormatException e) {
					continue;
				}
			}
			
			/* DECOMPOSITION DE CHAQUE LIGNE. */
			final Integer cleLue = Integer.parseInt(tokens[0]);
			final String libelleLu = tokens[1];
			
			// AJOUT DANS LA MAP RESULTAT._____
			this.nomenclatureMap.put(cleLue, libelleLu);
			
			// AJOUT DANS LE SET DES CLES POSSIBLES.____
			this.clesPossiblesSet.add(cleLue);
			
		} // FIN LECTURE DES LIGNES.******************************
		
		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();			
				
		return this.nomenclatureMap;
		
	} // Fin de importerNomenclatureEnUtf8(
	// File pNomenclature).________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEnteteParColonne(
			final int pI) {
		
		String entete = null;
		
		switch (pI) {
		
		case 1:
			entete = "Clé";
			break;
			
		case 2:
			entete = "Libellé";
			break;
			
		default:
			break;
		}
		
		return entete;
		
	} // Fin de getEnteteParColonne(
	// int pI).____________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getValeurParLigneColonne(
			final int pL
				, final int pC) {
		
		/* retourne null si this.nomenclatureMap est null. */
		if (this.nomenclatureMap == null) {
			return null;
		}
		
		int compteur = 0;
		
		final Set<Entry<Integer, String>> set = this.nomenclatureMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<Integer, String> entry = ite.next();
			
			final Integer cle = entry.getKey();
			final String libelle = entry.getValue();
			
			if (compteur == pL) {
				
				if (pC == 1) {
					return String.valueOf(cle);
				}
				else if (pC == 2) {
					return libelle;
				}
				
				return null;
			}
		}
		
		return null;
		
	} // Fin de getValeurParLigneColonne(
	// int pL
	// , int pC).__________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneEnTetesCsv() {
		return "Clé;Libellé;";
	} // Fin de fournirLigneEnTetesCsv().__________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneValeursCsv(
			final int pL) {
		
		/* retourne null si this.nomenclatureMap est null. */
		if (this.nomenclatureMap == null) {
			return null;
		}
		
		int compteur = 0;
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, String>> set = this.nomenclatureMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<Integer, String> entry = ite.next();
			
			final Integer cle = entry.getKey();
			final String libelle = entry.getValue();
			
			if (compteur == pL) {
				
				stb.append(cle);
				stb.append(SEP_PV);
				stb.append(libelle);
				stb.append(SEP_PV);
								
				return stb.toString();
								
			}
		}
		
		return null;
		
	} // Fin de fournirLigneValeursCsv(
	// int pL).____________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererNomenclatureCsvString() {
		return this.genererNomenclatureCsvString(true);
	} // Fin de genererNomenclatureCsvString().____________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererNomenclatureCsvString(
			final boolean pAvecLigneEntetes) {
		
		/* retourne null si this.nomenclatureMap est null. */
		if (this.nomenclatureMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		/* Ajout de la ligne d'en-tête si pAvecLigneEntetes. */
		if (pAvecLigneEntetes) {
			stb.append(this.fournirLigneEnTetesCsv());
			stb.append(NEWLINE);
		}
		
		
		int compteurLigne = 0;
		final int nombreLignes = this.nomenclatureMap.size();
		
		// PARCOURS DES LIGNES.___________	
		final Set<Entry<Integer, String>> set = this.nomenclatureMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final Entry<Integer, String> entry = ite.next();
			
			final Integer cle = entry.getKey();
			final String libelle = entry.getValue();
							
			stb.append(cle);
			stb.append(SEP_PV);
			stb.append(libelle);
			stb.append(SEP_PV);
							
			if (compteurLigne < nombreLignes) {
				stb.append(NEWLINE);
			}
											
		} // FIN DE PARCOURS DES LIGNES.___________
								
		return stb.toString();
		
	} // Fin de String genererNomenclatureCsvString(
	// boolean pAvecLigneEntetes)._________________________________________

	
		
	/**
	 * method genererNomenclatureCsvFileUtf8() :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv.<br/>
	 * <br/>
	 *
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileUtf8() 
				throws IOException
					, FichierNullException
						, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				true
					, null
						, Charset.forName("UTF-8"));
		
	} // Fin de genererNomenclatureCsvFileUtf8().__________________________

	
	
	/**
	 * method genererNomenclatureCsvFileLatin9() :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv.<br/>
	 * <br/>
	 *
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileLatin9() 
			throws IOException
				, FichierNullException
					, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				true
					, null
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererNomenclatureCsvFileLatin9().________________________
	
	
	
	/**
	 * method genererNomenclatureCsvFileUtf8(
	 * File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileUtf8(
			final File pFile) 
			throws IOException
				, FichierNullException
					, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				true
					, pFile
						, Charset.forName("UTF-8"));
		
	} // Fin de genererNomenclatureCsvFileUtf8(
	 // File pFile)._______________________________________________________

	
	
	/**
	 * method genererNomenclatureCsvFileLatin9(
	 * File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileLatin9(
			final File pFile) 
			throws IOException
				, FichierNullException
					, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				true
					, pFile
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererNomenclatureCsvFileLatin9(
	 // File pFile)._______________________________________________________

	
	
	/**
	 * method genererNomenclatureCsvFileUtf8(
	 * boolean pAvecLigneEntetes
	 * , File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileUtf8(
			final boolean pAvecLigneEntetes
				, final File pFile)
			throws IOException
			, FichierNullException
				, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				pAvecLigneEntetes
					, pFile
						, Charset.forName("UTF-8"));
		
	} // Fin de genererNomenclatureCsvFileUtf8(
	 // boolean pAvecLigneEntetes
	 // , File pFile)._____________________________________________________
	

	
	/**
	 * method genererNomenclatureCsvFileLatin9(
	 * boolean pAvecLigneEntetes
	 * , File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFileLatin9(
			final boolean pAvecLigneEntetes
				, final File pFile)
			throws IOException
			, FichierNullException
				, FichierInexistantException {
		
		return this.genererNomenclatureCsvFile(
				pAvecLigneEntetes
					, pFile
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererNomenclatureCsvFileLatin9(
	 // boolean pAvecLigneEntetes
	 // , File pFile)._____________________________________________________
	
	
	
	/**
	 * method genererNomenclatureCsvFile(
	 * boolean pAvecLigneEntetes
	 * , File pFile
	 * , Charset pCharset) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en pCharset encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_charset.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * @param pCharset : Charset : l'encodage voulu pour le fichier généré.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	@Override
	public final File genererNomenclatureCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile
					, final Charset pCharset) 
							throws IOException
								, FichierNullException
									, FichierInexistantException  {

		/* retourne null si this.nomenclatureMap est null. */
		if (this.nomenclatureMap == null) {
			return null;
		}

		// ************ PARAMETRES VALIDES *******************************/
				
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.nomenclature 
		 * avec l'extension _genere_charset.csv si pFile est null. */
		if (pFile == null) {			
			fileGenere = this.genererAutomatiquementFile(pCharset);			
		}
		else {			
			fileGenere = pFile;
		}

		/* retourne null si fileGenere est null. */
		if (fileGenere == null) {
			return null;
		}
				
		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE AVEC PCHARSET. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, pCharset);
		final BufferedWriter bfw = new BufferedWriter(osw);
		
		/* AJOUT DES TITRES. */
		if (pAvecLigneEntetes) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Insertion d'un saut de ligne. */
			bfw.newLine();
		}
		
		
		int compteurLigne = 0;
		final int nombreLignes = this.nomenclatureMap.size();
		
		// PARCOURS DES LIGNES.___________	
		final Set<Entry<Integer, String>> set = this.nomenclatureMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final StringBuffer stb = new StringBuffer();
			
			final Entry<Integer, String> entry = ite.next();
			
			final Integer cle = entry.getKey();
			final String libelle = entry.getValue();
							
			stb.append(cle);
			stb.append(SEP_PV);
			stb.append(libelle);
			stb.append(SEP_PV);
			
			bfw.write(stb.toString());
							
			if (compteurLigne < nombreLignes) {
				/* Insertion d'un saut de ligne. */
				bfw.newLine();
			}
											
		} // FIN DE PARCOURS DES LIGNES.___________

		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;
		
	} // Fin de genererNomenclatureCsvFile(
	 // boolean pAvecLigneEntetes
	 // , File pFile
	 // , Charset pCharset)._______________________________________________

	
		
	/**
	 * method genererAutomatiquementFile(
	 * Charset pCharset) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.nomenclature 
	 * avec l'extension _genere_charset.csv si pFile est null.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si this.nomenclature est null.<br/>
	 * @throws FichierInexistantException : 
	 * si this.nomenclature est inexistant.<br/>
	 */
	private File genererAutomatiquementFile(
			final Charset pCharset) 
			throws FichierNullException, FichierInexistantException {
		
		if (this.nomenclature == null) {
			
			final String message 
			= this.recupererNomClasse() 
			+ METHODE_GENERERNOMENCLATURE 
			+  "Le fichier de nomenclature à lire est null";
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de this.descriptionDuFichierFile == null.______________
		
		
		if (!this.nomenclature.exists()) {
			
			final String message 
			= this.recupererNomClasse() 
			+ METHODE_GENERERNOMENCLATURE 
			+  "Le fichier de nomenclature à lire n'existe pas : " 
			+ this.nomenclature.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de this.descriptionDuFichierFile inexistant.____________
		
				
		/* Récupération du chemin complet de la nomenclature. */
		final String path = this.nomenclature.getAbsolutePath();
		
		/* Récupération du nom de la nomenclature. */
		final String nomDescription = this.nomenclature.getName();
		
		/* Récupération du chemin de la nomenclature sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nomDescription);
		
		/* Récupération du nom sans extension. */
		final String nomDescriptionSansExtension 
			= StringUtils.substringBeforeLast(nomDescription, ".csv");
			
		/* Création du nom complet du fichier nomenclature généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomDescriptionSansExtension
			+ "_genere_" + pCharset.displayName() + ".csv";
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFile(
	// Charset pCharset).__________________________________________________
	
	

	/**
	 * method traiterFichierNull(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal, et jette une Exception si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierNull(
								final File pFile
									, final String pMethode) 
											throws Exception {
		
		if (pFile == null) {
			
			final String message 
			= this.recupererNomClasse() 
			+ pMethode 
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileNull());
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
						
			/* Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile null._____________________________
		
	} // Fin de traiterFichierNull(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * method traiterFichierVide(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal et jette une Exception si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierVide(
				final File pFile
					, final String pMethode) 
								throws Exception {
		
		if (pFile.length() == 0) {
			
			final String message 
			= this.recupererNomClasse() 
			+ pMethode 
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileVide()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
						
			/* Exception circonstanciée. */
			throw new FichierVideException(message);
			
		} // Fin de pFile vide.______________________________

	} // Fin de traiterFichierVide(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * method traiterFichierInexistant(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal et jette une Exception 
	 * si pFile est inexistant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierInexistant(
			final File pFile
				, final String pMethode) 
						throws Exception {
		
		if (!pFile.exists()) {
			
			final String message 
			= this.recupererNomClasse() 
			+ pMethode 
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileInexistant()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
						
			/* Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de fichier inexistant._______________________

	} // Fin de traiterFichierInexistant(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	

	/**
	 * method traiterFichierPasNormal(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal et jette une Exception 
	 * si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierPasNormal(
			final File pFile
				, final String pMethode) 
						throws Exception {
		
		if (!pFile.isFile()) {
			
			final String message 
			= this.recupererNomClasse() 
			+ pMethode 
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFilePasNormal()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
						
			/* Exception circonstanciée. */
			throw new FichierPasNormalException(message);

		} // Fin de File directory.____________________________

	} // Fin de traiterFichierPasNormal(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> getNomenclatureMap() {
		return this.nomenclatureMap;
	} // Fin de getNomenclatureMap().______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNomenclatureMap(
			final SortedMap<Integer, String> pNomenclatureMap) {
		this.nomenclatureMap = pNomenclatureMap;
	} // Fin de setNomenclatureMap(
	 // SortedMap<Integer,String> pNomenclatureMap)._______________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getNomenclature() {
		return this.nomenclature;
	} // Fin de getNomenclature()._________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNomenclature(
			final File pNomenclature) {
		this.nomenclature = pNomenclature;
	} // Fin de setNomenclature(
	 // File pNomenclature)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Integer> getClesPossiblesSet() {
		return this.clesPossiblesSet;
	} // Fin de getClesPossiblesSet()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClesPossiblesSet(
			final Set<Integer> pClesPossiblesSet) {
		this.clesPossiblesSet = pClesPossiblesSet;
	} // Fin de setClesPossiblesSet(
	 // Set<Integer> pClesPossiblesSet).___________________________________

		
	
} // FIN DE LA CLASSE AbstractImporteurNomenclature.-------------------------
