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
 * CLASSE AbstractImporteurNomenclature :<br/>
 * <p>
 * Classe abstraite factorisant les attributs et les 
 * méthodes des ImporteurNomenclature.
 * </p>
 * 
 * <p>
 *  Une <b>nomenclature</b> doit être un fichier csv avec séparateur ';' 
 *  encodé en UTF-8
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le SENS dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 *</p>
 *
 * <p>
 * La <b>clé est donc toujours entière dans une nomenclature</b>.
 * </p>
 *
 *<div>
 *<ul>
 * <li>
 * Tous les ImporteurNomenclature possèdent une 
 * méthode <b><code>importerNomenclature(File pNomenclature)</code></b> 
 * où pNomenclature encapsule la nomenclature en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.
 * </li>
 * <li>
 * La nomenclature est servie sous forme de 
 * <b>SortedMap&lt;Integer, String&gt; <code>nomenclatureMap</code></b> 
 * retournée par importerNomenclature(File pNomenclature).
 * </li>
 * <li>
 * La méthode importerNomenclature(File pNomenclature) permet également 
 * d'alimenter un <b>Set&lt;Integer&gt; <code>clesPossiblesSet</code></b> 
 * qui contient toutes les valeurs possibles pour la clé dans la nomenclature.
 * </li>
 * </ul>
 * </div>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurNomenclature : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurNomenclature.png" 
 * alt="Diagramme de classe du ImportateurNomenclature" />
 * </p>
 * 
 * <br/>
 *
 *<p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurNomenclature</code><br/>
 * <code><b>IImporteurNomenclature IMPORTEUR_NOMENCLATURE = new ImporteurNomenclature();</b></code><br/>
 * <code> // Import du fichier de nomenclature nomenclatureFile</code><br/>
 * <code><b>Map&lt;Integer, String&gt; resultat = IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;Integer&gt; clesPossiblesSet = IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère la nomenclature importée dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
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
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AbstractImporteurNomenclature() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> importerNomenclatureEnLatin9(
			final File pNomenclature) 
						throws Exception {
		
		return this.importerNomenclature(
				pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_LATIN9
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerNomenclatureEnLatin9(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> importerNomenclatureEnUtf8(
			final File pNomenclature) 
						throws Exception {
		
		return this.importerNomenclature(
				pNomenclature
					, METHODE_IMPORTER_NOMENCLATURE_EN_UTF8
						, Charset.forName("UTF-8"));
		
	} // Fin de importerNomenclatureEnUtf8(...).___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> importerNomenclature(
			final File pNomenclature
				, final String pMethode
					, final Charset pCharset) 
							throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pNomenclature, pMethode);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pNomenclature, pMethode);

		/* Fichier vide. */
		this.traiterFichierVide(pNomenclature, pMethode);
				
		/* File directory. */
		this.traiterFichierPasNormal(pNomenclature
					, pMethode);
		
		/* vérifie que la nomenclature est au bon format CSV [Integer;String;]. */
		final boolean bonneNomenclature 
			= this.verifierNomenclatureIntegerString(
					pNomenclature, pMethode, pCharset);
		
		if (!bonneNomenclature) {
			
			final String message 
				= this.recupererNomClasse()
					+ SEPARATEUR_MOINS_AERE
					+ pMethode 
					+ SEPARATEUR_MOINS_AERE 
					+ "La nomenclature que vous essayez d'importer "
					+ "n'est pas de la forme [Integer;String;] "
					+ "(un entier pour la clé, un point-virgule, "
					+ "une chaîne de caractères pour le libellé) : " 
					+ pNomenclature.getAbsolutePath();
			
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
		this.nomenclature = pNomenclature;
		
		/* Map résultat. */
		this.nomenclatureMap = new TreeMap<Integer, String>();
		
		/* Set à remplir. */
		this.clesPossiblesSet = new HashSet<Integer>();
		
		/* Ouverture des flux. */
		final FileInputStream fis =  new FileInputStream(this.nomenclature);
		final InputStreamReader isr = new InputStreamReader(fis, charset);
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne. */
		final Pattern patternPv = Pattern.compile(SEP_PV);
		
		// LECTURE DES LIGNES.**********************************
		while ((ligneLue = bfr.readLine()) != null) {
			
			/* décompose la ligne. */
			final String[] tokens 
				= patternPv.split(ligneLue);
			
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
		
	} // Fin de importerNomenclature(...)._________________________________
	

	
	/**
	 * vérifie que la nomenclature correspond au format attendu 
	 * (fichier csv avec une clé Integer et un libellé String).<br/>
	 * <ul>
	 * <li>retourne false si la REGEX n'a pas pu trouver 
	 * au moins deux tokens (pas CSV).</li>
	 * <li>retourne false si une clé n'est pas homogène à un entier.</li>
	 * </ul>
	 *
	 * @param pNomenclature : File : fichier csv contenant la nomenclature.
	 * @param pMethode : String : 
	 * nom de la méthode appelant la présente.
	 * @param pCharset : Charset : 
	 * charset d'encodage de la nomenclature pNomenclature
	 * 
	 * @return boolean : true si la nomenclature est au bon format.
	 * 
	 * @throws Exception
	 */
	private boolean verifierNomenclatureIntegerString(
			final File pNomenclature
				, final String pMethode
					, final Charset pCharset) 
							throws Exception {
		
		// ************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pNomenclature, pMethode);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pNomenclature, pMethode);

		/* Fichier vide. */
		this.traiterFichierVide(pNomenclature, pMethode);
				
		/* File directory. */
		this.traiterFichierPasNormal(pNomenclature
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
		final FileInputStream fis =  new FileInputStream(pNomenclature);
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
								
				final String cle = tokens[0];
				
				if (!StringUtils.isBlank(cle)) {
					try {
						Integer.parseInt(cle);
						
					/* retourne false si une clé n'est pas 
					 * homogène à un entier. */	
					} catch (NumberFormatException e) {
						resultat = false;
						break;
					}
				}
								
			}
									
		} // FIN LECTURE DES LIGNES.******************************
		
		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();
		
		return resultat;
		
	} // Fin de verifierNomenclatureIntegerString(...).____________________
	
	
	
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

		/* retourne null si this.nomenclatureMap est null. */
		if (this.nomenclatureMap == null) {
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
		 * même répertoire que this.nomenclature 
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
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherNomenclatureMap() {
		
		if (this.nomenclatureMap != null) {
			return this.afficherMapIntegerString(this.nomenclatureMap);
		}
			
		return null;
				
	} // Fin de afficherNomenclatureMap()._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherMapIntegerString(
			final Map<Integer, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final int cle = entry.getKey();
			final String libelle = entry.getValue();
							
			/* Ajout de la ligne au StringBuilder. */
			stb.append(
					String.format(Locale.FRANCE
							, "Ligne %-5d =      ", compteur));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Clé : %-10d", cle));
			
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
