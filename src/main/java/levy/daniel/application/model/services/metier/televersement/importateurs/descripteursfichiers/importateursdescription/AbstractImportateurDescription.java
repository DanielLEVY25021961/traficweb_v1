package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.utilitaires.utilitairesstrings.FormateurChaine;


/**
 * class AbstractImportateurDescription :<br/>
 * Classe abstraite factorisant les attributs et méthodes 
 * des ImportateurDescription.<br/>
 * Tous les ImportateurDescription possèdent une 
 * méthode importerDescription(File pFileDescription) 
 * où pFileDescription encapsule la description en csv du fichier 
 * (HIT, Histonat, Darwin.csv, FEOR XML, ...) à servir.<br/>
 * La description est servie sous forme de 
 * SortedMap&lt;Integer, IDescriptionChamp&gt; specificationChampsMap 
 * retournée par importerDescription(File pFileDescription).<br/>
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
 *
 */
public abstract class AbstractImportateurDescription implements
		IImportateurDescription {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_ABSTRACTIMPORTATEURDESCRIPTION : String : <br/>
	 * "Classe AbstractImportateurDescription - ".<br/>
	 */
	public static final String CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
		= "Classe AbstractImportateurDescription - ";
	
	
	/**
	 * CONSTRUCTEUR_ABSTRACTIMPORTATEURDESCRIPTION : String :<br/>
	 * "CONSTRUCTEUR AbstractImportateurDescription(
	 * File pDescriptionDuFichier)".<br/>
	 */
	public static final String CONSTRUCTEUR_ABSTRACTIMPORTATEURDESCRIPTION 
		= "CONSTRUCTEUR AbstractImportateurDescription("
			+ "File pDescriptionDuFichier) - ";

	
	/**
	 * METHODE_GETDESCRIPTIONCHAMP : String : <br/>
	 * "Méthode getDescriptionChamp(...) - ".<br/>
	 */
	public static final String METHODE_GETDESCRIPTIONCHAMP 
		= "Méthode getDescriptionChamp(...) - ";
	
	
	
	/**
	 * METHODE_SPECCHAMPSTOSTRING : String : <br/>
	 * "Méthode specificationChampsMapToString() - ".<br/>
	 */
	public static final String METHODE_SPECCHAMPSTOSTRING 
		= "Méthode specificationChampsMapToString() - ";
	
	
	/**
	 * METHODE_GENERERDESCRIPTION : String :<br/>
	 * "Méthode genererDescriptionCsvFile(boolean, File) - ".<br/>
	 */
	public static final String METHODE_GENERERDESCRIPTION 
		= "Méthode genererDescriptionCsvFile(boolean, File) - ";

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


	/* ATTRIBUTS. */
	/**
	 * nomsChampsJavaSet : Set<String> :<br/>
	 * Instanciation d'un Set chargé de contenir les
	 * noms de champs java pour vérifier qu'ils sont uniques.<br/>
	 */
	protected final transient Set<String> nomsChampsJavaSet 
			= new HashSet<String>();
	
	
	/**
	 * descriptionChamp : IDescriptionChamp :<br/>
	 * Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HistonatF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de Darwin csv.<br/>
	 */
	protected IDescriptionChamp descriptionChamp;

	
	/**
	 * descriptionDuFichierFile : File :<br/>
	 * File encapsulant un fichier comprenant la
	 * description du fichier à lire (spécification).<br/>
	 * Précise par exemple que les colonnes 1 à 3 comprennent le
	 * Numéro du Département, les colonnes 4-9 le numéro
	 * de section, etc...<br/>
	 */
	protected File descriptionDuFichierFile;
	
	
	
	/**
	 * specificationChampsMap : SortedMap&lt;Integer,IDescriptionChamp&gt; :<br/>
	 * Description du fichier importée par le présent ImportateurDescription
	 * et fournie sous forme de Map triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HistonatF07),<br/>
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
	 * method CONSTRUCTEUR AbstractImportateurDescription() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractImportateurDescription() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractImportateurDescription() :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
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
		this.traiterMapNull();
		
		/* Map vide. */
		/* Logge, rapporte et jette une Exception. */
		this.traiterMapVide();
		
		// **************PARAMETRES VALIDES****************************/
		
		IDescriptionChamp desc = null;
		
		/* Retourne la valeur dans la Map. */
		desc = this.specificationChampsMap.get(pOrdre);
		
		return desc;
		
	} // Fin de getDescriptionChamp(
	// Integer pOrdre).____________________________________________________


	
	/**
	 * method traiterMapNull() :<br/>
	 * LOG.fatal, rapporte et jette une MapNullException 
	 * si this.specificationChampsMap est null.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterMapNull() throws Exception {
		
		if (this.specificationChampsMap == null) {
			
			final String cleMapNull
			= "abstractimportateurdescription.getdescription.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_GETDESCRIPTIONCHAMP 
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
	 * method traiterMapVide() :<br/>
	 * LOG.fatal, rapporte et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterMapVide() throws Exception {
		
		if (this.specificationChampsMap.isEmpty()) {
			
			final String cleMapVide
			= "abstractimportateurdescription.getdescription.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_GETDESCRIPTIONCHAMP 
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
	 * method instancierTableauLongueursMaxi() :<br/>
	 * Intancie le tableau des longueurs maxi et gère la ligne d'en-têtes.<br/>
	 * <br/>
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
	 * method gererLongueursMaxi(
	 * IDescriptionChamp pDesc) :<br/>
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
	 * method toStringFormatte() :<br/>
	 * Affiche la description formatée à la console.<br/>
	 * <br/>
	 *
	 * @return : String : Chaîne de caractères 
	 * formattée pour affichage à la console.<br/>
	 * 
	 * @throws Exception 
	 */
	@Override
	public final String toStringFormatte() throws Exception {
		return this.specificationChampsMapToString(this.longueursMax);
	} // Fin de toStringFormatte().________________________________________
	
	
	
	/**
	 * method specificationChampsMapToString() :<br/>
	 * Construit un String décrivant chaque champ de la
	 * spécification du fichier contenu dans la Map
	 * specificationChampsMap.<br/>
	 * <br/>
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
		if (this.specificationChampsMap == null) {
			
			final String cleMapNull
			= "abstractimportateurdescription" 
				+ ".specificationChampsMapToString.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_SPECCHAMPSTOSTRING
			+ messageMapNull;

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport Eventuel. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}

			throw new ExceptionImport(message);
			
		} // Fin de Map null.________________________________________
		
		/* Map Vide. */
		if (this.specificationChampsMap.size() == 0) {
			
			final String cleMapVide
			= "abstractimportateurdescription" 
				+ ".specificationChampsMapToString.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_SPECCHAMPSTOSTRING
			+ messageMapVide;

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport Eventuel. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}

			throw new ExceptionImport(message);
			
		} // Fin de Map vide.________________________________________

		
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
	 * method specificationChampsMapToString(
	 * int[] pLongueursMax) :<br/>
	 * Construit un String décrivant chaque champ de la
	 * spécification du fichier contenu dans la Map
	 * specificationChampsMap en le formattant 
	 * à la longueur contenue dans pLongueursMax.<br/>
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
		if (this.specificationChampsMap == null) {
			
			final String cleMapNull
			= "abstractimportateurdescription" 
				+ ".specificationChampsMapToString.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_SPECCHAMPSTOSTRING
			+ messageMapNull;

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport Eventuel. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}

			throw new ExceptionImport(message);
			
		} // Fin de Map null.________________________________________
		
		/* Map Vide. */
		if (this.specificationChampsMap.size() == 0) {
			
			final String cleMapVide
			= "abstractimportateurdescription" 
				+ ".specificationChampsMapToString.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_SPECCHAMPSTOSTRING
			+ messageMapVide;

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport Eventuel. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}

			throw new ExceptionImport(message);
			
		} // Fin de Map vide.________________________________________

		
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
	 * method tableauIntToString(
	 * int[] pTableauLongueurs) :<br/>
	 * Affichage d'un tableau d'entiers.<br/>
	 * - Retourne une chaîne vide si le tableau
	 * passé en paramètre est null.<br/>
	 * <br/>
	 * 
	 * @param pTableauLongueurs : int[].<br/>
	 * 
	 * @return String.<br/>
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
		
	} // Fin de tableauIntToString(
	 // int[] pTableauLongueurs).__________________________________________
	


	/**
	 * method fournirLigneEnTetestoString() :<br/>
	 * Fabrique une chaine de caractères comportant tous
	 * les éléments de description de l'en-tête de la description 
	 * séparés par des tabulations 
	 * et avec un saut de ligne \n à la fin.<br/>
	 * <br/>
	 * - retourne null si this.descriptionChamp est null.<br/>
	 * <br/>
	 *
	* @return String : une chaine de caractères décrivant la ligne d'en-têtes 
	* en exposant toutes ses valeurs séparées par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
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
			final String entete = this.descriptionChamp.getEnteteparColonne(i);
			stb.append(entete);
			stb.append(TAB);
		}
		
		stb.append(NEWLINE);
		
		return stb.toString();
		
	} // Fin de fournirLigneEnTetestoString()._____________________________
	
	
	
	/**
	 * method fournirLigneEnTetesCsv() :<br/>
	 * Fournit une ligne csv avec un séparateur ';' pour les en-têtes
	 * de la description de fichier.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;colonneDebut;colonneFin;longueurCalculee; 
	 * pour un DescripteurChampHistoF07.<br/>
	 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature; 
	 * pour un DescripteurChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne null si this.descriptionChamp est null.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
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
	 * method fournirLigneValeursCsv(
	 * int pL) :<br/>
	 * Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs de description d'un champ 
	 * à la pL-ième ligne (1-based) de la description du fichier.<br/>
	 * retourne la ligne d'en-têtes csv si pL == 0.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;
	 * Integer;false;1;3;3; pour le champ 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HistoNatF07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);
	 * route;String;false; 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne null si this.specificationChampsMap est null.<br/>
	 * - retourne null si la l-ième ligne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * <br/>
	 * 
	 * @param pL : int : numéro d'ordre (1-based) de la ligne dans le fichier de description.<br/>
	 *
	 * @return String : ligne csv contenant la description du champ 
	 * avec séparateur ';'.<br/>
	 */
	@Override
	public final String fournirLigneValeursCsv(
			final int pL) {
		
		/* retourne null si this.specificationChampsMap est null. */
		if (this.specificationChampsMap == null) {
			return null;
		}
		
		final IDescriptionChamp desc = this.specificationChampsMap.get(pL);
		
		/* retourne null si la l-ième ligne (1-based) 
		 * n'existe pas dans la description. */
		if (desc == null) {
			return null;
		}
		
		/* retourne la ligne d'en-têtes csv si pL == 0. */
		if (pL == 0) {
			return desc.fournirLigneEnTetesCsv();
		}
		
		return desc.fournirLigneValeursCsv();
		
	} // Fin de fournirLigneValeursCsv(
	 // int pL).___________________________________________________________
	

	
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
	public final File genererDescriptionCsvFile() 
					throws Exception {
		
		return this.genererDescriptionCsvFile(true, null);
		
	} // Fin de genererDescriptionCsvFile()._______________________________

		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFile(
			final File pFile) 
					throws Exception {
		
		return this.genererDescriptionCsvFile(true, pFile);
		
	} // Fin de genererDescriptionCsvFile(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile) 
					throws Exception {
		
		// ************* PARAMETRES INVALIDES ***************************/
			
		/* specificationChampsMap null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterMapNull();
		
		/* Map vide. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterMapVide();
		
		/* descriptionDuFichierFile null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileNull();
		
		// *************** PARAMETRES VALIDES ***************************/
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.descriptionDuFichierFile 
		 * avec l'extension _UTF-8.csv si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFile(null);

		}
		else {
			
			fileGenere = pFile;
		}
		
		String ligneLue = "";
		int compteurLigne = 0;
		final int nombreChampsInterne = this.specificationChampsMap.size();
		
		/* OUVERTURE DES FLUX. */
		final FileWriter fileWriterOut = new FileWriter(fileGenere);
		final BufferedWriter bfw = new BufferedWriter(fileWriterOut);
		
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
		fileWriterOut.close();
				
		return fileGenere;

	} // Fin de genererDescriptionCsvFile(
	 // boolean pAvecLigneEntetes
	 // , File pFile)._____________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileLatin9() 
			throws Exception {
		
		return this.genererDescriptionCsvFile(
				true, null
				, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererDescriptionCsvFileLatin9()._________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererDescriptionCsvFileUtf8() 
			throws Exception {
		
		return this.genererDescriptionCsvFile(
				true, null
				, Charset.forName("UTF-8"));
		
	} // Fin de genererDescriptionCsvFileUtf8().___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * @throws Exception 
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
		this.traiterMapNull();
		
		/* Map vide. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterMapVide();
		
		/* descriptionDuFichierFile null. */
		/* Logge, rapporte et jette une Exception circonstanciée. */
		this.traiterDescriptionFileNull();
		
		// *************** PARAMETRES VALIDES ***************************/
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.descriptionDuFichierFile 
		 * avec l'extension _charset.csv si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFile(pCharset);

		}
		else {
			
			fileGenere = pFile;
		}
		
		String ligneLue = "";
		int compteurLigne = 0;
		final int nombreChampsInterne = this.specificationChampsMap.size();
		
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
	 * method traiterDescriptionFileNull() :<br/>
	 * LOG.fatal, rapporte et jette une MapNullException 
	 * si this.descriptionDuFichierFile est null.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterDescriptionFileNull() throws Exception {
		
		if (this.descriptionDuFichierFile == null) {
			
			final String cleFileNull 
			= "abstractimportateurdescription" 
				+ ".genererdescription.filenull";

			final String messageFileNull 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleFileNull);

			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_GENERERDESCRIPTION
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
	 * method genererAutomatiquementFile(
	 * Charset pCharset) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _charset.csv si pFile est null.<br/>
	 * <br/>
	 * Par exemple : .\descriptionHistoF07_genere.csv 
	 * pour un fichier de description .\descriptionHistoF07.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException : 
	 * si this.descriptionDuFichierFile est inexistant.<br/>
	 */
	private File genererAutomatiquementFile(
			final Charset pCharset) 
			throws FichierNullException, FichierInexistantException {
		
		if (this.descriptionDuFichierFile == null) {
			
			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_GENERERDESCRIPTION 
			+  "Le fichier de description à lire est null";
			
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
			
		} // Fin de this.descriptionDuFichierFile == null.______________
		
		
		if (!this.descriptionDuFichierFile.exists()) {
			
			final String message 
			= CLASSE_ABSTRACTIMPORTATEURDESCRIPTION 
			+ METHODE_GENERERDESCRIPTION 
			+  "Le fichier de description à lire n'existe pas : " 
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
			
			/* Jette une Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de this.descriptionDuFichierFile inexistant.____________
		
				
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
		
	} // Fin de genererAutomatiquementFile(
	 // Charset pCharset)._________________________________________________
	
	
	
	/**
	* {@inheritDoc}	*/
	@Override
	public final String getEnteteparColonne(
			final int pI) {
		
		/* retourne null si this.descriptionChamp est null. */
		if (this.descriptionChamp == null) {
			return null;
		}
		
		return this.descriptionChamp.getEnteteparColonne(pI);
		
	} // Fin de getEnteteparColonne(
	// int pI).____________________________________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getValeurparLigneColonne(
		final int pL
			, final int pI) {
		
		/* retourne null si this.specificationChampsMap est null.*/
		if (this.specificationChampsMap == null) {
			return null;
		}
		
		final IDescriptionChamp desc = this.specificationChampsMap.get(pL);
		
		/* retourne null si la l-ième ligne (1-based) 
		 * n'existe pas dans la description. */
		if (desc == null) {
			return null;
		}
		
		/* si pL=0, retourne un en-tête. */
		if (pL == 0) {
			return desc.getEnteteparColonne(pI);
		}
		
		return desc.getValeurparColonne(pI);
		
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
	 * method getLongueursMax() :<br/>
	 * Getter du tableau des longueurs
	 * maximales des contenus des champs dans une description.<br/>
	 * Utile pour l'affichage avec des colonnes de taille fixe
	 * à la console.<br/>
	 *
	 * @return int[] : le tableau des longueurs
	 * maximales des contenus des champs dans une description.<br/>
	 */
	@Override
	public final int[] getLongueursMax() {
		return this.longueursMax.clone();
	} // Fin de getLongueursMax().________________________________________


	
} // FIN DE LA CLASSE AbstractImportateurDescription.------------------------
