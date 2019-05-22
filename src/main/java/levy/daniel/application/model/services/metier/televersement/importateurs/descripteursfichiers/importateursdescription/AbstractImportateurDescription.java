package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.utilitaires.utilitairesstrings.FormateurChaine;


/**
 * CLASSE AbstractImportateurDescription :<br/>
 * <p>
 * Classe abstraite factorisant les attributs et méthodes 
 * des ImportateurDescription.<br/>
 * IMPLEMENTE {@link IImportateurDescription}.
 * </p>
 * 
 * <p>
 * Tous les ImportateurDescription possèdent une 
 * méthode <code><b>importerDescription(File pFileDescription)</b></code> 
 * où pFileDescription encapsule la description en csv du fichier 
 * (HIT, HISTO_F08, HISTO_F07, DARWIN_CSV, FEOR XML, Histonat, ...) 
 * à servir.<br/>
 * La description est servie sous forme de 
 * <code>SortedMap&lt;Integer, IDescriptionChamp&gt; 
 * <b>this.specificationChampsMap</b></code> 
 * retournée par la méthode 
 * <code><b>importerDescription(File pFileDescription)</b></code>.
 * </p>
 * 
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
 * @since 29 juin 2014
 */
public abstract class AbstractImportateurDescription implements
		IImportateurDescription {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * "Classe AbstractImportateurDescription".<br/>
	 */
	public static final String CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
		= "Classe AbstractImportateurDescription";
		
	/**
	 * "CONSTRUCTEUR AbstractImportateurDescription(
	 * File pDescriptionDuFichier)".<br/>
	 */
	public static final String CONSTRUCTEUR_ABSTRACTIMPORTATEURDESCRIPTION 
		= "CONSTRUCTEUR AbstractImportateurDescription("
			+ "File pDescriptionDuFichier)";
	
	/**
	 * "Méthode getDescriptionChamp(...)".<br/>
	 */
	public static final String METHODE_GETDESCRIPTIONCHAMP 
		= "Méthode getDescriptionChamp(...)";
		
	/**
	 * "Méthode specificationChampsMapToString()".<br/>
	 */
	public static final String METHODE_SPECCHAMPSTOSTRING 
		= "Méthode specificationChampsMapToString()";
		
	/**
	 * "Méthode genererDescriptionCsvFile(boolean, File)".<br/>
	 */
	public static final String METHODE_GENERERDESCRIPTION 
		= "Méthode genererDescriptionCsvFile(boolean, File)";

	/**
	 * "Méthode genererAutomatiquementFile(Charset pCharset)".
	 */
	public static final String METHODE_GENERER_AUTOMATIQUEMENT_FILE 
		= "Méthode genererAutomatiquementFile(Charset pCharset)";
	
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
	 * Tabulation "\t".<br/>
	 */
	public static final String TAB = "\t";
	
	
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


	/* ATTRIBUTS. */
	/**
	 * Set&lt;String&gt; chargé de contenir les
	 * noms de champs java d'une description de fichier 
	 * pour vérifier qu'ils sont uniques.<br/>
	 */
	protected final transient Set<String> nomsChampsJavaSet 
			= new HashSet<String>();
	
	
	/**
	 * Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature, aLexique
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HISTO_F07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de DARWIN_CSV.<br/>
	 */
	protected IDescriptionChamp descriptionChamp;

	
	/**
	 * File encapsulant un fichier comprenant la
	 * description du fichier à lire (spécification).<br/>
	 * Précise par exemple que les colonnes 1 à 3 comprennent le
	 * Numéro du Département, les colonnes 4-9 le numéro
	 * de section, etc...<br/>
	 */
	protected File descriptionDuFichierFile;
	
	
	
	/**
	 * Description du fichier importée par le présent ImportateurDescription
	 * et fournie sous forme de SortedMap&lt;Integer,IDescriptionChamp&gt; 
	 * triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HISTO_F07),<br/>
	 * - IDescriptionChamp : les valeurs dans la description du champ 
	 * (N° champ, colonne début, colonne fin...).<br/>
	 * specificationChampsMap ne contient pas les en-têtes 
	 * du fichier de description.<br/>
	 */
	protected SortedMap<Integer, IDescriptionChamp> specificationChampsMap;
	

	
	/**
	 * logImportDescription : boolean : 
	 * boolean qui stipule si les ImportateurDescription
	 * doivent rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions de fichier).<br/>
	 */
	protected boolean logImportDescription;
	

	
	/**
	 * rapportImportDescriptionStb : StringBuffer : <br/>
	 * StringBuffer chargé de contenir le rapport de
	 * la lecture de la description de fichier.<br/>
	 */
	protected StringBuffer rapportImportDescriptionStb;


	
	/**
	 * longueursMax : int[] :<br/>
	 * Tableau comportant les longueurs maximales des contenus
	 * des champs des Descriptions.<br/>
	 * Par exemple, la longueur maximale du contenu du champ
	 * "nomenclature" dans toute la description de fichier.<br/>
	 * Utile pour l'affichage avec des colonnes de taille fixe
	 * à la console.<br/>
	 */
	protected transient int[] longueursMax;
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractImportateurDescription.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AbstractImportateurDescription() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <ul>
	 * <li>passe à la classe une IDescriptionChamp 
	 * pour récupérer l'en-tête du fichier de description 
	 * que l'on veut importer.</li>
	 * <li>passe à la classe le fichier de description à importer 
	 * <code><b>this.descriptionDuFichierFile</b></code></li>
	 * </ul>
	 *
	 * @param pDescriptionChamp : IDescriptionChamp.<br/>
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 */
	public AbstractImportateurDescription(
			final IDescriptionChamp pDescriptionChamp
				, final File pDescriptionDuFichierFile) {
		
		super();
		this.descriptionChamp = pDescriptionChamp;
		this.descriptionDuFichierFile = pDescriptionDuFichierFile;
		
	} // Fin de CONSTRUCTEUR ARCHICOMPLET._________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IDescriptionChamp getDescriptionChamp(
			final Integer pOrdre) 
				throws Exception {
		
				
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* Map null. */
		/* Logge, rapporte et jette une Exception. */
		this.traiterMapNull(METHODE_GETDESCRIPTIONCHAMP);
		
		/* Map vide. */
		/* Logge, rapporte et jette une Exception. */
		this.traiterMapVide(METHODE_GETDESCRIPTIONCHAMP);
		
		// **************PARAMETRES VALIDES****************************/
		
		IDescriptionChamp desc = null;
					
		/* Retourne la valeur dans la Map. */
		desc = this.specificationChampsMap.get(pOrdre);
							
		return desc;
		
	} // Fin de getDescriptionChamp(...).__________________________________


	
	/**
	 * LOG.fatal, rapporte et jette une MapNullException 
	 * si <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * 
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws Exception 
	 */
	private void traiterMapNull(
			final String pMethode) throws Exception {
		
		if (this.specificationChampsMap == null) {
			
			final String cleMapNull
			= "abstractimportateurdescription.getdescription.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION
			+ SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ SEPARATEUR_MOINS_AERE
			+ messageMapNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new MapNullException(message);
			
		} // Fin de Map null.________________________________________
		
	} // Fin de traiterMapNull().__________________________________________
	
	
	
	/**
	 * LOG.fatal, rapporte et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * 
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws Exception 
	 */
	private void traiterMapVide(
			final String pMethode) throws Exception {
		
		if (this.specificationChampsMap.isEmpty()) {
			
			final String cleMapVide
			= "abstractimportateurdescription.getdescription.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageMapVide;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new MapVideException(message);
			
		} // Fin de Map vide._______________________________________

	} // Fin de traiterMapVide().__________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void mettreAJourLongueursMax(
			final int[] pLongueurs) {
		
		if (pLongueurs.length != 0) {
			
			for (int i = 0; i < pLongueurs.length; i++) {
				this.longueursMax[i] 
				   = calculerLongueurMaxInt(
						   pLongueurs[i]
						              , this.longueursMax[i]);
			}
			
		}
				
	} // Fin de mettreAJourLongueursMax()._________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int calculerLongueurMaxInt(
			final int pNouvelleLongueur
				, final int pAncienneLongueur) {
		
		/* TRAITEMENT DE LA NULLITE de pContenu. */
		if (pNouvelleLongueur == 0) {
			return pAncienneLongueur;
		}
		
		/* TRAITEMENT CLASSIQUE. */		
		int nouvelleLongueur = 0;
		
		if (pNouvelleLongueur > pAncienneLongueur) {
			nouvelleLongueur = pNouvelleLongueur;
		}
		else {
			nouvelleLongueur = pAncienneLongueur;
		}
		
		return nouvelleLongueur;
		
	} // calculerLongueurMaxInt(
	 // int pNouvelleLongueur, int pAncienneLongueur)._____________________

	
	
	/**
	 * Intancie le tableau des longueurs maxi et gère la ligne d'en-têtes.<br/>
	 */
	protected void instancierTableauLongueursMaxi() {
		
		int nombreChamps = 0;
		
		/* Récupération du nombre de champs dans 
		 * this.descriptionChamp. */
		nombreChamps 
		= this.descriptionChamp.getEntetesDescriptionMap().size();
		
		/* Calcul du tableau des longueurs maxi pour les en-têtes. */
		if (this.longueursMax == null) {
			this.longueursMax = new int[nombreChamps];
		}
		
		final int[] longueursEnTetes 
		= this.descriptionChamp.getLongueursEnTableauEnTetes();
		
		/* Mise à jour des longueurs Maxi en 
		 * ne prenant en compte que la ligne d'en-tête. */
		mettreAJourLongueursMax(longueursEnTetes);
		
	} // Fin de instancierTableauLongueursMaxi().__________________________
	
	

	/**
	 * Tient à jour le tableau des longueurs maxi 
	 * à chaque lecture de ligne de la description.<br/>
	 * <br/>
	 *
	 * @param pDesc : IDescriptionChamp : 
	 * "Ligne" de la description du fichier.<br/>
	 */
	protected void gererLongueursMaxi(
			final IDescriptionChamp pDesc) {
		
		final int[] longueurs 
		= pDesc.getLongueursEnTableauValeurs();
		
		mettreAJourLongueursMax(longueurs);
		
	} // Fin de gererLongueursMaxi(
	 // IDescriptionChamp pDesc).__________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toStringFormate() throws Exception {
		return this.specificationChampsMapToString(this.longueursMax);
	} // Fin de toStringFormatte().________________________________________
	
	
	
	/**
	 * AFFICHAGE A LA CONSOLE NON FORMATE.<br/>
	 * Construit et retourne une String décrivant chaque champ de la
	 * spécification du fichier contenu dans la Map
	 * <code><b>this.specificationChampsMap</b></code>.<br/>
	 *
	 * @return un String décrivant chaque champ de la spécification
	 * du fichier : String.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final String specificationChampsMapToString() 
			throws Exception {
		
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* Map null. */
		this.traiterMapNull(METHODE_SPECCHAMPSTOSTRING);
		
		/* Map Vide. */
		this.traiterMapVide(METHODE_SPECCHAMPSTOSTRING);

		
		// **************PARAMETRES VALIDES****************************/
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append(NEWLINE);
		
		final Set<Entry<Integer, IDescriptionChamp>> 
		setEntry 
			= this.specificationChampsMap.entrySet();
		
		final Iterator<Entry<Integer, IDescriptionChamp>> 
		ite 
			= setEntry.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, IDescriptionChamp> entry = ite.next();
			
			final IDescriptionChamp desc = entry.getValue();
			
			stb.append(desc.descriptionChampToString());
			stb.append(NEWLINE);
						
		}
				
		return stb.toString();
		
	} // Fin de specificationChampsMapToString().__________________________


	
	/**
	 * AFFICHAGE A LA CONSOLE FORMATE.<br/>
	 * Construit et retourne un String décrivant chaque champ de la
	 * spécification du fichier contenu dans la Map
	 * <code><b>this.specificationChampsMap</b></code> en le formatant 
	 * à la longueur contenue dans le tableau pLongueursMax.<br/>
	 * <br/>
	 *
	 * @param pLongueursMax : int[] : 
	 * Tableau des longueurs maxi de chaque champ.<br/>
	 * 
	 * @return String.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final String specificationChampsMapToString(
			final int[] pLongueursMax) 
						throws Exception {
		
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* Map null. */
		this.traiterMapNull(METHODE_SPECCHAMPSTOSTRING);
		
		/* Map Vide. */
		this.traiterMapVide(METHODE_SPECCHAMPSTOSTRING);

		
		// **************PARAMETRES VALIDES****************************/
		
		/* Si le tableau des longueurs Max est null ou vide,
		 * retourne la méthode sans alignement vertical. */
		if (pLongueursMax == null || pLongueursMax.length == 0) {
			return specificationChampsMapToString();
		}
		
		final StringBuffer stb = new StringBuffer();
		
		/* EN-TETES DES COLONNES. *******/
		if (this.descriptionChamp != null) {
			
			/* Récupération des en-têtes de colonnes. */
			final Map<Integer, String> entetesMap 
			= this.descriptionChamp.getEntetesDescriptionMap();
			
			/* BALAYAGE DES EN-TETES DE COLONNES. */
			for (int i = 0; i < entetesMap.size(); i++) {
				
				/* Récupération de l'en-tête et mise à la
				 * longueur Max. */
				final String entete 
				= FormateurChaine
				.mettreALongueur(entetesMap.get(i+1), pLongueursMax[i]);
				
				/* Concatènation dans le StringBuffer. */
				stb.append(entete);
				stb.append('\t');
				
			} // Fin du balayage des en-tetes.______________________
			
			/* Saut de ligne. */
			stb.append(NEWLINE);
			
		} // Fin de if (colonnesDeDescription != null)._________________

		
		/* VALEURS DES COLONNES. *******/
		
		final Set<Entry<Integer, IDescriptionChamp>> set 
			= this.specificationChampsMap.entrySet();
		
		final Iterator<Entry<Integer, IDescriptionChamp>> ite 
			= set.iterator();
		
		/* Balayage de la Map des valeurs. ***************/
		while (ite.hasNext()) {
			
			final Entry<Integer, IDescriptionChamp> entry 
			= ite.next();
			
			final IDescriptionChamp descChamp 
				= entry.getValue();
			
			final Map<Integer, String> valeurMap 
			= descChamp.getValeursDescriptionMap();
			
			/* Parcours des valeurs du DescriptionChamp*/
			for (int i = 0; i < valeurMap.size(); i++) {
				
				/* Récupération de la valeur et mise à la
				 * longueur Max. */
				final String valeur 
				= FormateurChaine
				.mettreALongueur(valeurMap.get(i+1), pLongueursMax[i]);
				
				/* Concatènation dans le StringBuffer. */
				stb.append(valeur);
				/* Tabulation. */
				stb.append('\t');
				
			} // Fin du balayage des valeurs.______________________
			
			/* Saut de ligne. */
			stb.append(NEWLINE);
			
		} // Fin du balayage de la Map.______________________
				
		return stb.toString();
		
	} // Fin de specificationChampsMapToString(
	// int[] pLongueursMax)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String tableauIntToString(
			final int[] pTableauLongueurs) {
		
		if (pTableauLongueurs == null 
				|| pTableauLongueurs.length == 0) {
			
			return "";
			
		}
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append('[');
		
		for (int i = 0; i < pTableauLongueurs.length; i++) {
			
			stb.append(pTableauLongueurs[i]);
			
			if (i < (pTableauLongueurs.length - 1)) {
				stb.append(", ");
			}
			
		}
		
		stb.append(']');
		
		return stb.toString();
		
	} // Fin de tableauIntToString(...).___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String tokensToString(
			final String[] pTokens) {
		
		/* retourne "" si pTokens est null. */		
		if (pTokens == null) {
			return "";
		}
		
		/* retourne "" si pTokens est vide. */
		if (pTokens.length == 0) {
			return "";
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (int i = 0; i < pTokens.length; i++) {
			stb.append(pTokens[i]);
			stb.append(SEP_PV);
		}
		
		return stb.toString();
		
	} // Fin de tokensToString(
	// String[] pTokens).__________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String[] stringCsvToTokens(
			final String pStringCsv) {
		
		/* retourne null si pStringCsv est blank. */
		if (StringUtils.isBlank(pStringCsv)) {
			return null;
		}
		
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne. */
		final Pattern patternCsv = Pattern.compile(SEP_PV);
		
		final String[] tokens 
			= patternCsv.split(pStringCsv);
		
		return tokens;
		
	} // Fin de stringCsvToTokens(...).____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneEnTetestoString() {
		
		/* retourne null si this.descriptionChamp est null. */
		if (this.descriptionChamp == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final int taille 
			= this.descriptionChamp.getEntetesDescriptionMap().size();
		
		for (int i = 1; i <= taille; i++) {
			final String entete = this.descriptionChamp.fournirEnteteparColonne(i);
			stb.append(entete);
			stb.append(TAB);
		}
		
		stb.append(NEWLINE);
		
		return stb.toString();
		
	} // Fin de fournirLigneEnTetestoString()._____________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneEnTetesCsv() {
		
		/* retourne null si this.descriptionChamp est null. */
		if (this.descriptionChamp == null) {
			return null;
		}
		
		return this.descriptionChamp.fournirLigneEnTetesCsv();
		
	} // Fin de fournirLigneEnTetesCsv().__________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneValeursCsv(
			final int pL) {
		
		/* retourne null si this.specificationChampsMap est null. */
		if (this.specificationChampsMap == null) {
			return null;
		}
		
		/* retourne null si this.descriptionChamp est null. */
		if (this.descriptionChamp == null) {
			return null;
		}
		
		/* retourne la ligne d'en-têtes csv si pL == 0. */
		if (pL == 0) {
			return this.descriptionChamp.fournirLigneEnTetesCsv();
		}
		
		final IDescriptionChamp desc = this.specificationChampsMap.get(pL);
		
		/* retourne null si la l-ième ligne (1-based) 
		 * n'existe pas dans la description. */
		if (desc == null) {
			return null;
		}
		
		return desc.fournirLigneValeursCsv();
		
	} // Fin de fournirLigneValeursCsv(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererDescriptionCsvString() {
		return this.genererDescriptionCsvString(true);
	} // Fin de genererDescriptionCsvString()._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererDescriptionCsvString(
			final boolean pAvecLigneEntetes) {
		
		/* retourne null si this.specificationChampsMap est null. */
		if (this.specificationChampsMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		/* Ajout de la ligne d'en-tête si pAvecLigneEntetes. */
		if (pAvecLigneEntetes) {
			stb.append(this.fournirLigneEnTetesCsv());
			stb.append(NEWLINE);
		}
		
		int compteurLigne = 0;
		final int nombreLignes = this.specificationChampsMap.size();
		
		final Set<Entry<Integer, IDescriptionChamp>> set 
			= this.specificationChampsMap.entrySet();
		
		final Iterator<Entry<Integer, IDescriptionChamp>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final Entry<Integer, IDescriptionChamp> entry = ite.next();
			
			final IDescriptionChamp desc = entry.getValue();
			
			stb.append(desc.fournirLigneValeursCsv());
			
			if (compteurLigne < nombreLignes) {
				stb.append(NEWLINE);
			}
			
		}
		
		return stb.toString();
		
	} // Fin de genererDescriptionCsvString(
	 // boolean pAvecLigneEntetes).________________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileUtf8() 
			throws Exception {
		
		return this.genererDescriptionCsvFile(
				true
					, null
						, StandardCharsets.UTF_8);
		
	} // Fin de genererDescriptionCsvFileUtf8().___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileLatin9() 
			throws Exception {
		
		return this.genererDescriptionCsvFile(
				true
					, null
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererDescriptionCsvFileLatin9()._________________________

		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileUtf8(
			final File pFile) 
					throws Exception {
		
		return this.genererDescriptionCsvFile(
				true
					, pFile
						, StandardCharsets.UTF_8);
		
	} // Fin de genererDescriptionCsvFileUtf8(...).________________________

		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileLatin9(
			final File pFile) 
					throws Exception {
		
		return this.genererDescriptionCsvFile(
				true
					, pFile
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererDescriptionCsvFileLatin9(...).______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileUtf8(
			final boolean pAvecLigneEntetes
				, final File pFile) 
					throws Exception {
		
		return this.genererDescriptionCsvFile(
				pAvecLigneEntetes
					, pFile
						, StandardCharsets.UTF_8);
		
	} // Fin de genererDescriptionCsvFileUtf8(...).________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileLatin9(
			final boolean pAvecLigneEntetes
				, final File pFile) 
					throws Exception {
		
		return this.genererDescriptionCsvFile(
				pAvecLigneEntetes
					, pFile
						, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererDescriptionCsvFileLatin9(...).______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile
					, final Charset pCharset) 
					throws Exception {
		
		// ************* PARAMETRES INVALIDES ***************************/
			
		/* specificationChampsMap null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterMapNull(METHODE_GENERERDESCRIPTION);
		
		/* specificationChampsMap vide. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterMapVide(METHODE_GENERERDESCRIPTION);
		
		/* descriptionDuFichierFile null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileNull(METHODE_GENERERDESCRIPTION);
		
		/* descriptionDuFichierFile vide. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileVide(METHODE_GENERERDESCRIPTION);
				
		/* descriptionDuFichierFile inexistant. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileInexistant(METHODE_GENERERDESCRIPTION);
				
		/* descriptionDuFichierFile répertoire. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFilePasNormal(METHODE_GENERERDESCRIPTION);

		
		// *************** PARAMETRES VALIDES ***************************/

		/* choisit automatiquement le Charset UTF-8 si pCharset == null 
		 * ou pCharset ne peut pas encoder. */
		Charset charset = null;
		
		if (pCharset == null || !pCharset.canEncode()) {
			charset = StandardCharsets.UTF_8;
		} else {
			charset = pCharset;
		}
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.descriptionDuFichierFile 
		 * avec l'extension _charset.csv si pFile est null. */
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

		
		String ligneLue = "";
		int compteurLigne = 0;
		final int nombreChampsInterne = this.specificationChampsMap.size();
		
		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE AVEC charset. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
		final BufferedWriter bfw = new BufferedWriter(osw);
		
		/* Ajoute le BOM-UTF8 au début du fichier généré 
		 * si charset vaut Charset-UTF8. */
		if (charset.equals(StandardCharsets.UTF_8)) {
			bfw.write(BOM_UTF_8);
		}
		
		/* AJOUT DES TITRES. */
		if (pAvecLigneEntetes) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Insertion d'un saut de ligne. */
			bfw.newLine();
		}
		
		/* LECTURE DE LA MAP - ECRITURE FICHIER. */
		final Set<Entry<Integer, IDescriptionChamp>> set 
			= this.specificationChampsMap.entrySet();
		
		final Iterator<Entry<Integer, IDescriptionChamp>> ite 
			= set.iterator();
		
		while (ite.hasNext()) {
			
			compteurLigne++;
			
			final Entry<Integer, IDescriptionChamp> entry 
				= ite.next();
			
			final IDescriptionChamp desc = entry.getValue();
			
			ligneLue = desc.fournirLigneValeursCsv();
			
			/* Ecriture dans le BufferedWriter. */
			bfw.write(ligneLue);
			
			/* Passage à la ligne si ce n'est pas le
			 * dernier champ. */
			if (compteurLigne < nombreChampsInterne) {
				
				/* Insertion d'un saut de ligne. */
				bfw.newLine();
			}
			
		} // Fin de la lecture de specificationChampsMap.__________________
		
		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;

	} // Fin de genererDescriptionCsvFile(
	 // boolean pAvecLigneEntetes
	 // , File pFile
	 // , Charset pCharset)._______________________________________________
	

	
	/**
	 * LOG.fatal, rapporte et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * 
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws Exception 
	 */
	private void traiterDescriptionFileNull(
			final String pMethode) throws Exception {
		
		if (this.descriptionDuFichierFile == null) {
			
			final String cle 
				= "abstractimportateurdescription.genererdescription.filenull";
			
			final String messageFileNull 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de descriptionDuFichierFile == null.___________________
		
	} // Fin de traiterDescriptionFileNull().______________________________
	

	
	/**
	 * LOG.fatal, rapporte et jette une FichierNullException 
	 * si pFile est null.<br/>
	 * 
	 *  @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterFichierNull(
			final File pFile
				, final String pMethode) throws Exception {
		
		if (pFile == null) {
			
			final String cle 
				= "abstractimportateurdescription.constructeur.filenull";
			
			final String messageFileNull 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile == null.___________________
		
	} // Fin de traiterFichierNull().______________________________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterDescriptionFileVide(
				final String pMethode) 
								throws Exception {
		
		if (this.descriptionDuFichierFile.length() == 0) {
			
			final String cle 
				= "abstractimportateurdescription.genererdescription.filevide";
			
			final String messageFileVide 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileVide 
			+ this.descriptionDuFichierFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierVideException(message);
			
		} // Fin de this.descriptionDuFichierFile vide.______________

	} // Fin de traiterDescriptionFileVide(...)._____________________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierVideException si pFile est vide.<br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterFichierVide(
				final File pFile
					, final String pMethode) 
								throws Exception {
		
		if (pFile.length() == 0) {
			
			final String cle 
				= "abstractimportateurdescription.constructeur.filevide";
			
			final String messageFileVide 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileVide 
			+ pFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierVideException(message);
			
		} // Fin de pFile vide.______________________________

	} // Fin de traiterFichierVide(...).___________________________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> 
	 * est inexistant.<br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterDescriptionFileInexistant(
				final String pMethode) 
								throws Exception {
		
		if (!this.descriptionDuFichierFile.exists()) {
			
			final String cle 
				= "abstractimportateurdescription.genererdescription.fileinexistant";
			
			final String messageFileInexistant 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileInexistant 
			+ this.descriptionDuFichierFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de this.descriptionDuFichierFile inexistant._______

	} // Fin de traiterDescriptionFileInexistant(...)._____________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierInexistantException si pFile est inexistant.<br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterFichierInexistant(
				final File pFile
					, final String pMethode) 
								throws Exception {
		
		if (pFile.length() == 0) {
			
			final String cle 
				= "abstractimportateurdescription.constructeur.fileinexistant";
			
			final String messageFileInexistant 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFileInexistant 
			+ pFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de pFile inexistant.______________________________

	} // Fin de traiterFichierInexistant(...)._____________________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> 
	 * est un répertoire.<br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterDescriptionFilePasNormal(
				final String pMethode) 
								throws Exception {
		
		if (!this.descriptionDuFichierFile.isFile()) {
			
			final String cle 
				= "abstractimportateurdescription.genererdescription.filepasnormal";
			
			final String messageFilePasNormal 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFilePasNormal 
			+ this.descriptionDuFichierFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierPasNormalException(message);
			
		} // Fin de this.descriptionDuFichierFile pasnormal._______

	} // Fin de traiterDescriptionFilePasNormal(...).______________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierPasNormalException si pFile 
	 * est un répertoire.<br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterFichierPasNormal(
				final File pFile
					, final String pMethode) 
								throws Exception {
		
		if (!pFile.isFile()) {
			
			final String cle 
				= "abstractimportateurdescription.constructeur.filepasnormal";
			
			final String messageFilePasNormal 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cle);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ messageFilePasNormal 
			+ pFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}

			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierPasNormalException(message);
			
		} // Fin de pFile pasnormal.______________________________

	} // Fin de traiterFichierPasNormal(...).______________________________


	
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
		if (pFileParentPath != null) {
			
			if (!pFileParentPath.toFile().exists()) {
				Files.createDirectories(pFileParentPath);
			}
			
			/* crée le fichier VIDE pFile si il n'existe pas. */
			Files.createFile(pFilePath);

		}
		
		return pFile;
		
	} // Fin de creerFichierVideEtArborescenceSurDisque(...).______________
	
	
	
	/**
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que 
	 * <code><b>this.descriptionDuFichierFile</b></code> 
	 * avec l'extension _charset.csv.<br/>
	 * <ul>
	 * <li>rajoute automatiquement l'extension _pCharset.displayName().csv 
	 * si pCharset != null.</li>
	 * <li>rajoute automatiquement l'extension _UTF-8.csv 
	 * si pCharset == null.</li>
	 * <li>retourne un File <i>abstrait</i>, c'est à dire 
	 * non créé sur disque.</li>
	 * </ul>
	 * Par exemple : génère ./descriptionHistoF07_genere_UTF-8.csv 
	 * pour un fichier de description ./descriptionHistoF07.csv 
	 * et pCharset = UTF-8.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 * 
	 * @throws Exception 
	 */
	private File genererAutomatiquementFile(
			final Charset pCharset) 
			throws Exception {
		
		// ************* PARAMETRES INVALIDES ***************************/
		
		/* descriptionDuFichierFile null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileNull(METHODE_GENERER_AUTOMATIQUEMENT_FILE);
		
		/* descriptionDuFichierFile vide. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileVide(METHODE_GENERER_AUTOMATIQUEMENT_FILE);
				
		/* descriptionDuFichierFile inexistant. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileInexistant(METHODE_GENERER_AUTOMATIQUEMENT_FILE);
				
		/* descriptionDuFichierFile répertoire. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFilePasNormal(METHODE_GENERER_AUTOMATIQUEMENT_FILE);
		
		
		// ************* PARAMETRES VALIDES   ***************************/
				
		/* Récupération du chemin complet de la description. */
		final String path = this.descriptionDuFichierFile.getAbsolutePath();
		
		/* Récupération du nom de la description. */
		final String nomDescription = this.descriptionDuFichierFile.getName();
		
		/* Récupération du chemin de la description sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nomDescription);
		
		/* Récupération du nom sans extension. */
		final String nomDescriptionSansExtension 
			= StringUtils.substringBeforeLast(nomDescription, ".csv");
		
		/* rajoute automatiquement l'extension _UTF-8.csv si pCharset == null. */
		String extension = null;
		
		if (pCharset == null) {
			extension = "_UTF-8.csv";
		}
		else {
			extension = "_" + pCharset.displayName() + ".csv";
		}
		
		/* Création du nom complet du fichier description généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomDescriptionSansExtension
			+ extension;
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFile().______________________________
	
	
	
	/**
	* {@inheritDoc}	*/
	@Override
	public final String fournirEnteteparColonne(
			final int pI) {
		
		/* retourne null si this.descriptionChamp est null. */
		if (this.descriptionChamp == null) {
			return null;
		}
		
		return this.descriptionChamp.fournirEnteteparColonne(pI);
		
	} // Fin de getEnteteparColonne(
	// int pI).____________________________________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirValeurparLigneColonne(
		final int pL
			, final int pI) {
		
		/* retourne null si this.specificationChampsMap est null.*/
		if (this.specificationChampsMap == null) {
			return null;
		}
		
		final IDescriptionChamp desc = this.specificationChampsMap.get(pL);
		
		/* si pL=0, retourne un en-tête. */
		if (pL == 0) {
			return this.fournirEnteteparColonne(pI);
		}
		
		/* retourne null si la l-ième ligne (1-based) 
		 * n'existe pas dans la description. */
		if (desc == null) {
			return null;
		}
				
		return desc.fournirValeurparColonne(pI);
		
	} // Fin de getValeurparLigneColonne(
	 // int pL
	 // , int pI)._________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IDescriptionChamp getDescriptionChamp() {
		return this.descriptionChamp;
	} // Fin de getDescriptionChamp()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDescriptionChamp(
			final IDescriptionChamp pDescriptionChamp) {
		this.descriptionChamp = pDescriptionChamp;
	} // Fin de setDescriptionChamp(
	 // IDescriptionChamp pDescriptionChamp).______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getDescriptionDuFichierFile() {
		return this.descriptionDuFichierFile;
	} // Fin de getDescriptionDuFichierFile()._____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDescriptionDuFichierFile(
			final File pDescriptionDuFichierFile) {
		this.descriptionDuFichierFile = pDescriptionDuFichierFile;
	} // Fin de setDescriptionDuFichierFile(
	 // File pDescriptionDuFichierFile).___________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> getSpecificationChampsMap() {
	
		return this.specificationChampsMap;
		
	} // Fin de getSpecificationChampsMap().______________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSpecificationChampsMap(
			final SortedMap<Integer, IDescriptionChamp> 
										pSpecificationChampsMap) {
		this.specificationChampsMap = pSpecificationChampsMap;
	} // setSpecificationChampsMap(
	 // SortedMap<Integer, IDescriptionChamp> pSpecificationChampsMap).____



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isLogImportDescription() {
		return this.logImportDescription;
	} // Fin de isLogImportDescription().__________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLogImportDescription(
			final boolean pLogDescription) {
		this.logImportDescription = pLogDescription;
	} // Fin de setLogImportDescription(
	 // boolean pLogDescription).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringBuffer getRapportImportDescriptionStb() {
		return this.rapportImportDescriptionStb;
	} // Fin de getRapportImportDescriptionStb().__________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRapportImportDescriptionStb(
			final StringBuffer pRapportImportDescriptionStb) {
		this.rapportImportDescriptionStb = pRapportImportDescriptionStb;
	} // Fin de setRapportImportDescriptionStb(
	 // StringBuffer pRapportImportDescriptionStb).________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getLongueursMax() {
		return this.longueursMax.clone();
	} // Fin de getLongueursMax().________________________________________


	
} // FIN DE LA CLASSE AbstractImportateurDescription.------------------------
