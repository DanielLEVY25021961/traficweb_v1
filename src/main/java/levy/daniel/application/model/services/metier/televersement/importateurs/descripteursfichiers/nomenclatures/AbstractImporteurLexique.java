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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
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
import levy.daniel.application.apptechnic.exceptions.technical.impl.NomenclatureMauvaiseRunTimeException;

/**
 * CLASSE AbstractImporteurLexique :<br/>
 * <p>
 * Classe abstraite factorisant les attributs et les 
 * méthodes des ImporteurLexique.
 * </p>
 *  
 * <p>
 * Un <b>lexique</b> doit être un fichier csv avec séparateur ';' 
 * sous forme [String, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le PROFIL EN TRAVERS dans un HISTO_F07 :<br/>
 * 1V;chaussée unique 1 voie;<br/>
 * 2V<7;chaussée unique 2 voies < 7m;<br/>
 * 2V>=7;chaussée unique 2 voies >= 7m;<br/>
 * ...........................................<br/>
 * </p>
 * 
 * <p>
 * La <b>clé est donc toujours String dans un lexique</b>.
 * </p>
 * 
 * <div>
 * <ul>
 * <li>
 * Tous les ImporteurLexique possèdent une 
 * méthode <b><code>importerLexique(File pLexique)</code></b> 
 * où pLexique encapsule le lexique en csv de la donnée 
 * (profil en travers, code concession, ...) à servir.
 * </li>
 * <li>
 * Le lexique est servi sous forme de 
 * <b>SortedMap&lt;String, String&gt; <code>lexiqueMap</code></b> 
 * retournée par importerLexique(File pLexique).
 * </li>
 * <li>
 * La méthode importerLexique(File pLexique) permet également 
 * d'alimenter un <b>Set&lt;String&gt; <code>clesPossiblesSet</code></b> 
 * qui contient toutes les valeurs possibles pour la clé dans le lexique.
 * </li>
 * </ul>
 * </div>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurLexique : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurLexique.png" 
 * alt="Diagramme de classe du ImportateurLexique" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurLexique</code><br/>
 * <code><b>IImporteurLexique IMPORTEUR_LEXIQUE = new ImporteurLexique();</b></code><br/>
 * <code> // Import du fichier de lexique lexiqueFile</code><br/>
 * <code><b>Map&lt;String, String&gt; resultat = IMPORTEUR_LEXIQUE.importerLexiqueEnUtf8(lexiqueFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;String&gt; clesPossiblesSet = IMPORTEUR_LEXIQUE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère le lexique importé dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_LEXIQUE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
 * </p>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * switch-case,<br/>
 * créer fichier vide sur disque, créer arborescence, <br/>
 * ajouter BOM, ajouter BOM-UTF8 au début d'un fichier csv,<br/>
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
public abstract class AbstractImporteurLexique implements
		IImporteurLexique {

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
	
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';
	
	
	/**
	 * SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 */
	protected SortedMap<String, String> lexiqueMap;
	
	
	/**
	 * File en csv avec séparateur ';' qui encapsule le lexique.<br/>
	 */
	protected File lexique;
	
	
	/**
	 * Ensemble des valeurs (Set&lt;String&gt;) de clés  
	 * possibles pour le lexique.<br/>
	 */
	protected Set<String> clesPossiblesSet;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractImporteurLexique.class);

	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AbstractImporteurLexique() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<String, String> importerLexiqueEnLatin9(
			final File pLexique) 
						throws Exception {
		
		return this.importerLexique(
				pLexique
					, METHODE_IMPORTER_LEXIQUE_EN_LATIN9
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerLexiqueEnLatin9(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<String, String> importerLexiqueEnUtf8(
			final File pLexique) 
						throws Exception {
		
		return this.importerLexique(
				pLexique
					, METHODE_IMPORTER_LEXIQUE_EN_UTF8
						, Charset.forName("UTF-8"));
		
	} // Fin de importerLexiqueEnUtf8(...).___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<String, String> importerLexique(
			final File pLexique
				, final String pMethode
					, final Charset pCharset) 
							throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pLexique, pMethode);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pLexique, pMethode);

		/* Fichier vide. */
		this.traiterFichierVide(pLexique, pMethode);
				
		/* File directory. */
		this.traiterFichierPasNormal(pLexique
					, pMethode);
		
		/* vérifie que le lexique est au bon format CSV [String;String;]. */
		final boolean bonneLexique 
			= this.verifierLexiqueStringString(
					pLexique, pMethode, pCharset);
		
		if (!bonneLexique) {
			
			final String message 
				= this.recupererNomClasse()
					+ SEPARATEUR_MOINS_AERE
					+ pMethode 
					+ SEPARATEUR_MOINS_AERE 
					+ "Le lexique que vous essayez d'importer "
					+ "n'est pas de la forme [String;String;] "
					+ "(une chaîne de caractères pour la clé, un point-virgule, "
					+ "une chaîne de caractères pour le libellé) : " 
					+ pLexique.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new NomenclatureMauvaiseRunTimeException(message);
		}

		// ************ PARAMETRES VALIDES *******************************/
		
		/* choisit automatiquement le Charset UTF-8 si pCharset == null. */
		Charset charset = null;
		
		if (pCharset == null) {
			charset = Charset.forName("UTF-8");
		} else {
			charset = pCharset;
		}
		
		/* Passage des paramètres aux attributs. */
		this.lexique = pLexique;
		
		/* Map résultat. */
		this.lexiqueMap = new TreeMap<String, String>();
		
		/* Set à remplir. */
		this.clesPossiblesSet = new HashSet<String>();
		
		/* Ouverture des flux. */
		final FileInputStream fis =  new FileInputStream(this.lexique);
		final InputStreamReader isr = new InputStreamReader(fis, charset);
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne. */
		final Pattern patternPv = Pattern.compile(SEP_PV);
		
		int compteur = 0;
		
		// LECTURE DES LIGNES.**********************************
		while ((ligneLue = bfr.readLine()) != null) {
			
			compteur++;
			
			/* décompose la ligne. */
			final String[] tokens 
				= patternPv.split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'clé', 'key' ou 'code' pour l'en-tête 
			 * SUR LA PREMIERE LIGNE. */
			if (compteur == 1) {
				
				final String cle = tokens[0];
				
				if (StringUtils.containsIgnoreCase(cle, "clé") 
						|| StringUtils.containsIgnoreCase(cle, "cle") 
						|| StringUtils.containsIgnoreCase(cle, "key") 
						|| StringUtils.containsIgnoreCase(cle, "code")) {
					
					continue;
					
				}
								
			}
			
			
			/* DECOMPOSITION DE CHAQUE LIGNE. */
			final String cleLue = tokens[0];
			final String libelleLu = tokens[1];
			
			// AJOUT DANS LA MAP RESULTAT._____
			this.lexiqueMap.put(cleLue, libelleLu);
			
			// AJOUT DANS LE SET DES CLES POSSIBLES.____
			this.clesPossiblesSet.add(cleLue);
			
		} // FIN LECTURE DES LIGNES.******************************
		
		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();			
				
		return this.lexiqueMap;
		
	} // Fin de importerLexique(...).______________________________________
	

	
	/**
	 * vérifie que le lexique correspond au format attendu 
	 * (fichier csv avec une clé String et un libellé String).<br/>
	 * <ul>
	 * <li>retourne false si la REGEX n'a pas pu trouver 
	 * au moins deux tokens (pas CSV).</li>
	 * </ul>
	 *
	 * @param pLexique : File : fichier csv contenant le lexique.
	 * @param pMethode : String : 
	 * nom de la méthode appelant la présente.
	 * @param pCharset : Charset : 
	 * charset d'encodage de le lexique pLexique
	 * 
	 * @return boolean : true si le lexique est au bon format.
	 * 
	 * @throws Exception
	 */
	private boolean verifierLexiqueStringString(
			final File pLexique
				, final String pMethode
					, final Charset pCharset) 
							throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pLexique, pMethode);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pLexique, pMethode);

		/* Fichier vide. */
		this.traiterFichierVide(pLexique, pMethode);
				
		/* File directory. */
		this.traiterFichierPasNormal(pLexique
					, pMethode);

		// ************ PARAMETRES VALIDES *******************************/
		
		/* choisit automatiquement le Charset UTF-8 si pCharset == null. */
		Charset charset = null;
		
		if (pCharset == null) {
			charset = Charset.forName("UTF-8");
		} else {
			charset = pCharset;
		}
		
		boolean resultat = true;
		
		/* Ouverture des flux. */
		final FileInputStream fis =  new FileInputStream(pLexique);
		final InputStreamReader isr = new InputStreamReader(fis, charset);
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		int compteur = 0;
		
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne. */
		final Pattern patternPv = Pattern.compile(SEP_PV);
		
		// LECTURE DES LIGNES.**********************************
		while ((ligneLue = bfr.readLine()) != null) {
			
			compteur++;
			
			if (compteur > 1) {
				
				/* décompose la ligne. */
				final String[] tokens 
					= patternPv.split(ligneLue);
				
				/* retourne false si la REGEX n'a pas pu trouver
				 *  au moins deux tokens (pas CSV). */
				if (tokens.length < 2) {
					resultat = false;
					break;
				}
																
			}
									
		} // FIN LECTURE DES LIGNES.******************************
		
		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();
		
		return resultat;
		
	} // Fin de verifierLexiqueStringString(...).__________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnteteParColonne(
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
		
	} // Fin de fournirEnteteParColonne(...).______________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirValeurParLigneColonne(
			final int pL
				, final int pC) {
		
		/* retourne null si this.lexiqueMap est null. */
		if (this.lexiqueMap == null) {
			return null;
		}
		
		int compteur = 0;
		
		final Set<Entry<String, String>> set = this.lexiqueMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String cle = entry.getKey();
			final String libelle = entry.getValue();
			
			if (compteur == pL) {
				
				if (pC == 1) {
					return cle;
				}
				else if (pC == 2) {
					return libelle;
				}
				
				return null;
			}
		}
		
		return null;
		
	} // Fin de fournirValeurParLigneColonne(...)._________________________
	
	

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
		
		/* retourne null si this.lexiqueMap est null. */
		if (this.lexiqueMap == null) {
			return null;
		}
		
		int compteur = 0;
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<String, String>> set = this.lexiqueMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String cle = entry.getKey();
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
		
	} // Fin de fournirLigneValeursCsv(...)._______________________________
	
	

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
		
		/* retourne null si this.lexiqueMap est null. */
		if (this.lexiqueMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		/* Ajout de la ligne d'en-tête si pAvecLigneEntetes. */
		if (pAvecLigneEntetes) {
			stb.append(this.fournirLigneEnTetesCsv());
			stb.append(NEWLINE);
		}
		
		
		int compteurLigne = 0;
		final int nombreLignes = this.lexiqueMap.size();
		
		// PARCOURS DES LIGNES.___________	
		final Set<Entry<String, String>> set = this.lexiqueMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final Entry<String, String> entry = ite.next();
			
			final String cle = entry.getKey();
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
		
	} // Fin de String genererNomenclatureCsvString(...).__________________

	
	
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final File genererNomenclatureCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile
					, final Charset pCharset) 
							throws IOException
								, FichierNullException
									, FichierInexistantException  {

		/* retourne null si this.lexiqueMap est null. */
		if (this.lexiqueMap == null) {
			return null;
		}

		// ************ PARAMETRES VALIDES *******************************/

		/* choisit automatiquement le Charset UTF-8 si pCharset == null. */
		Charset charset = null;
		
		if (pCharset == null) {
			charset = Charset.forName("UTF-8");
		} else {
			charset = pCharset;
		}

		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.lexique 
		 * avec l'extension _genere_charset.csv si pFile est null. */
		if (pFile == null) {			
			fileGenere = this.genererAutomatiquementFile(charset);			
		}
		else {			
			fileGenere = pFile;
		}

		/* retourne null si fileGenere est null. */
		if (fileGenere == null) {
			return null;
		}
		
		/* crée un fichier vide sur disque et son arborescence 
		 * si il n'existe pas. */
		if (!fileGenere.exists()) {
			this.creerFichierVideEtArborescenceSurDisque(fileGenere);
		}
						
		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE AVEC PCHARSET. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
		final BufferedWriter bfw = new BufferedWriter(osw);
		
		/* Ajoute le BOM-UTF8 au début du fichier généré si charset vaut Charset-UTF8. */
		if (charset.equals(Charset.forName("UTF-8"))) {
			bfw.write(BOM_UTF_8);
		}
		
		/* AJOUT DES TITRES. */
		if (pAvecLigneEntetes) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Insertion d'un saut de ligne. */
			bfw.newLine();
		}
		
		
		int compteurLigne = 0;
		final int nombreLignes = this.lexiqueMap.size();
		
		// PARCOURS DES LIGNES.___________	
		final Set<Entry<String, String>> set = this.lexiqueMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final StringBuffer stb = new StringBuffer();
			
			final Entry<String, String> entry = ite.next();
			
			final String cle = entry.getKey();
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
		
	} // Fin de genererNomenclatureCsvFile(...).___________________________


	
	/**
	 * <b>Crée sur disque le File pFile <i>vide</i></b> 
	 * si il n'existe pas déjà.<br/>
	 * <ul>
	 * <li>retourne le fichier vide créé.</li>
	 * <li>crée sur disque l'<b>arborescence au dessus de pFile</b> 
	 * si elle n'existe pas.</li>
	 * <li>crée sur disque le fichier vide pFile 
	 * si il n'existe pas.</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile existe.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier inexistant sur disque à créer.
	 * 
	 * @return File : le fichier VIDE créé sur disque.<br/>
	 * 
	 * @throws IOException
	 */
	private File creerFichierVideEtArborescenceSurDisque(
							final File pFile) throws IOException {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile existe. */
		if (pFile.exists()) {
			return null;
		}
		
		final Path pFilePath = pFile.toPath();
		final Path pFileParentPath = pFilePath.getParent();
		
		/* crée l'arborescence au dessus de pFile si elle n'existe pas. */
		if (!pFileParentPath.toFile().exists()) {
			Files.createDirectories(pFileParentPath);
		}
		
		/* crée le fichier VIDE pFile si il n'existe pas. */
		Files.createFile(pFilePath);
		
		return pFile;
		
	} // Fin de creerFichierVideEtArborescenceSurDisque(...).______________
	
	
	
	/**
	 * method genererAutomatiquementFile(
	 * Charset pCharset) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.lexique 
	 * avec l'extension _genere_charset.csv si pFile est null.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si this.lexique est null.<br/>
	 * @throws FichierInexistantException : 
	 * si this.lexique est inexistant.<br/>
	 */
	private File genererAutomatiquementFile(
			final Charset pCharset) 
			throws FichierNullException, FichierInexistantException {
		
		if (this.lexique == null) {
			
			final String message 
			= this.recupererNomClasse() 
			+ METHODE_GENERERNOMENCLATURE 
			+  "Le fichier de lexique à lire est null";
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de this.descriptionDuFichierFile == null.______________
		
		
		if (!this.lexique.exists()) {
			
			final String message 
			= this.recupererNomClasse() 
			+ METHODE_GENERERNOMENCLATURE 
			+  "Le fichier de lexique à lire n'existe pas : " 
			+ this.lexique.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de this.descriptionDuFichierFile inexistant.____________
		
				
		/* Récupération du chemin complet de le lexique. */
		final String path = this.lexique.getAbsolutePath();
		
		/* Récupération du nom de le lexique. */
		final String nomDescription = this.lexique.getName();
		
		/* Récupération du chemin de le lexique sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nomDescription);
		
		/* Récupération du nom sans extension. */
		final String nomDescriptionSansExtension 
			= StringUtils.substringBeforeLast(nomDescription, ".csv");
			
		/* Création du nom complet du fichier lexique généré. */
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
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherLexiqueMap() {
		
		if (this.lexiqueMap != null) {
			return this.afficherMapStringString(this.lexiqueMap);
		}
			
		return null;
				
	} // Fin de afficherLexiqueMap()._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherMapStringString(
			final Map<String, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<String, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final String cle = entry.getKey();
			final String libelle = entry.getValue();
							
			/* Ajout de la ligne au StringBuilder. */
			stb.append(
					String.format(Locale.FRANCE
							, "Ligne %-5d =      ", compteur));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Clé : %-10s", cle));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Libellé : %-50s", libelle));
										
			stb.append(NEWLINE);
														
		} // Fin de Parcours de l'iterator.______________________
		
		/* Retour de la ligne. */
		return stb.toString();
		
	} // Fin de afficherMapIntegerString(...)._____________________________	
	

	
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
			+ SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileNull());
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
						
			/* Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile null._____________________________
		
	} // Fin de traiterFichierNull(...).___________________________________
	
	
	
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
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
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

	} // Fin de traiterFichierVide(...).___________________________________
	
	
	
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
			+ SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ SEPARATEUR_MOINS_AERE
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

	} // Fin de traiterFichierInexistant(...)._____________________________
	
	

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
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
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

	} // Fin de traiterFichierPasNormal(...).______________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<String, String> getLexiqueMap() {
		return this.lexiqueMap;
	} // Fin de getLexiqueMap().______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLexiqueMap(
			final SortedMap<String, String> pLexiqueMap) {
		this.lexiqueMap = pLexiqueMap;
	} // Fin de setLexiqueMap(
	 // SortedMap<Integer,String> pLexiqueMap)._______________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getLexique() {
		return this.lexique;
	} // Fin de getLexique()._________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLexique(
			final File pLexique) {
		this.lexique = pLexique;
	} // Fin de setLexique(
	 // File pLexique)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<String> getClesPossiblesSet() {
		return this.clesPossiblesSet;
	} // Fin de getClesPossiblesSet()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClesPossiblesSet(
			final Set<String> pClesPossiblesSet) {
		this.clesPossiblesSet = pClesPossiblesSet;
	} // Fin de setClesPossiblesSet(
	 // Set<String> pClesPossiblesSet).___________________________________

		
	
} // FIN DE LA CLASSE AbstractImporteurLexique.------------------------------
