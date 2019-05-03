package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class AbstractDescriptionChamp :<br/>
 * Classe abstraite factorisant les attributs et méthodes 
 * des DescriptionChamp.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Expression régulière, Expression reguliere, regex,<br/>
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
public abstract class AbstractDescriptionChamp 
								implements IDescriptionChamp {

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
	 * entetesDescriptionMap : SortedMap&lt;Integer,String&gt; : <br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : le libellé (java) des colonnes de la description
	 * de fichier.<br/>
	 * Par exemple :<br/>
	 * [1-ordreChamps;2-colonnes;3-longueur;4-intitule;5-nomenclature;
	 * 6-champJava;7-typeJava;8-aNomenclature;
	 * 9-colonneDebut;10-colonneFin;11-longueurCalculee;]
	 * pour un DescriptionChampHistoF07.<br/>
	 * [1-ordreChamps;2-intitule;3-nomenclature;
	 * 4-champJava;5-typeJava;7-aNomenclature;] 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 */
	protected SortedMap<Integer, String> entetesDescriptionMap;
	

	
	/**
	 * valeursDescriptionMap : SortedMap&lt;Integer,String&gt; : <br/>
	 * Map triée stockant :<br/>
	 * - Integer : l'ordre des colonnes d'une description de fichier.<br/>
	 * - String : les valeurs dans les colonnes de la description
	 * de fichier pour un champ donné 
	 * (une ligne de la description de fichier).<br/>
	 * Par exemple :<br/>
	 * [(1 pour ordreChamps, '3'), (2 pour colonnes, '10')
	 * , (3 pour longueur, '1')
	 * , (4 pour intitule, 'Sens'), etc...] pour le champ 'sens'
	 *  (ligne 3) dans 
	 * DescriptionChampHistoF07.<br/>
	 */
	protected SortedMap<Integer, String> valeursDescriptionMap;

	
	
	/**
	 * longueursDescriptionMap : SortedMap&lt;Integer,Integer&gt; : <br/>
	 * Map triée stockant :<br/>
	 * - l'ordre des colonnes d'une description de fichier.<br/>
	 * - la longueur du contenu dans les colonnes de la description
	 * de fichier pour un champ donné (une ligne).<br/>
	 * Map utile pour formater les sorties à la console 
	 * (alignement des tableaux).<br/>
	 * <br/>
	 */
	protected SortedMap<Integer, Integer> longueursDescriptionMap;
	
	
	
	/**
	 * nombreColonnesObligatoires : int : <br/>
	 * nombre de colonnes que la description du fichier
	 * passée en entrée doit obligatoirement comporter.<br/>
	 * Intéressant si on doit générer des colonnes à partir
	 * de la description. Par exemple, générer 'colonneDebut'
	 * et 'colonneFin' à partir de 'colonnes' (1-3).<br/>
	 * 'colonneDebut' et 'colonneFin' ne sont pas à fournir
	 * dans la description de fichier. Cependant, ces colonnes
	 * figureront dans entetesDescriptionMap.<br/>
	 */
	protected int nombreColonnesObligatoires;
	
	
	
	/**
	 * logDescription : boolean : 
	 * boolean qui stipule si les DescriptionChamp
	 * doivent rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 */
	protected boolean logDescription;
	

	
	/**
	 * rapportDescriptionStb : StringBuffer : <br/>
	 * StringBuffer chargé de contenir le rapport de
	 * la lecture du champ.<br/>
	 */
	protected StringBuffer rapportDescriptionStb;


		
	/**
	* ordreChamps : Integer :<br/>
	* Ordre du champ (ligne) dans la description.<br/>
	* Par exemple, 'Numéro de Section' est le 
	* deuxième champ dans la description du HistonatF07.<br/>
	*/
	protected Integer ordreChamps;


	
	/**
	 * intitule : String :<br/>
	 * Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 */
	protected String intitule;
	
	
	/**
	 * nomenclature : String :<br/>
	 * Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 */
	protected String nomenclature;
	
	
	/**
	 * champJava : String :<br/>
	 * Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 */
	protected String champJava;
	
	
	/**
	 * typeJava : String :<br/>
	 * type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 */
	protected String typeJava;
	
	
	/**
	 * aNomenclature : boolean :<br/>
	 * boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 */
	protected boolean aNomenclature;
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(AbstractDescriptionChamp.class);



	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractDescriptionChamp() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractDescriptionChamp() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String entetesDescriptionToString() {
		
		/* retourne "" si this.entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return "";
		}
		
		/* retourne "" si this.entetesDescriptionMap est vide. */
		if (this.entetesDescriptionMap.isEmpty()) {
			return "";
		}
		
			
		final StringBuffer stb = new StringBuffer();
		stb.append('[');
		
		final Set<Entry<Integer, String>> entrySet 
			= this.entetesDescriptionMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite 
			= entrySet.iterator();
		
		int compteur = 0;
		final int nombreColonnes = this.entetesDescriptionMap.size();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<Integer, String> entry 
				= ite.next();
			
			final String nomColonne 
				= entry.getValue();
			
			stb.append(nomColonne);
			
			if (compteur < nombreColonnes) {
				stb.append(", ");
			}
			
		}
		
		stb.append(']');
		
		return stb.toString();
			
	} // Fin de entetesDescriptionToString().______________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String valeursDescriptionToString() {
		
		/* retourne "" si valeursDescriptionMap est null. */
		if (this.valeursDescriptionMap == null) {
			return "";
		}
		
		/* retourne "" si valeursDescriptionMap est vide. */
		if (this.valeursDescriptionMap.isEmpty()) {
			return "";
		}
		
			
		final StringBuffer stb = new StringBuffer();
		stb.append('[');
		
		final Set<Entry<Integer, String>> entrySet 
		= this.valeursDescriptionMap.entrySet();
	
		final Iterator<Entry<Integer, String>> ite 
		= entrySet.iterator();
	
		int compteur = 0;
		final int nombreColonnes = this.valeursDescriptionMap.size();
	
		while (ite.hasNext()) {
		
			compteur++;
			
			final Entry<Integer, String> entry 
				= ite.next();
			
			/* Nom de la colonne. */
			stb.append(this.entetesDescriptionMap.get(compteur));
			/* = */
			stb.append(" = ");
			
			final String valeurColonne 
				= entry.getValue();
			
			/* Valeur dans la colonne,
			 * comme 'Numéro de Département'. */
			stb.append(valeurColonne);
			
			if (compteur < nombreColonnes) {
				/* ,  si pas le dernier. */
				stb.append(", ");
			}
		
		}
		
		stb.append(']');
		
		return stb.toString();			
				
	} // Fin de valeursDescriptionToString().______________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneEnTetesCsv() {
		
		/* retourne "" si this.entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return "";
		}
		
		/* retourne "" si this.entetesDescriptionMap est vide. */
		if (this.entetesDescriptionMap.isEmpty()) {
			return "";
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, String>> set 
			= this.entetesDescriptionMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			final String valeur = entry.getValue();
			
			stb.append(valeur);
			
			stb.append(SEP_PV);
		}
		
		return stb.toString();
		
	} // Fin de fournirLigneEnTetesCsv().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneValeursCsv() {
		
		/* retourne "" si valeursDescriptionMap est null. */
		if (this.valeursDescriptionMap == null) {
			return "";
		}
		
		/* retourne "" si valeursDescriptionMap est vide. */
		if (this.valeursDescriptionMap.isEmpty()) {
			return "";
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, String>> set 
			= this.valeursDescriptionMap.entrySet();
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			final String valeur = entry.getValue();
			
			stb.append(valeur);
			
			stb.append(SEP_PV);
		}
		
		return stb.toString();
		
	} // Fin de fournirLigneValeursCsv().__________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnteteparColonne(
			final int pI) {
		
		if (this.entetesDescriptionMap != null) {
			if (pI > 0 && pI <= this.entetesDescriptionMap.size()) {
				return this.entetesDescriptionMap.get(pI);
			}
		}
		
		return null;
		
	} // Fin de getEnteteparColonne(
	 // int pI)._____________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirValeurparColonne(
			final int pI) {
		
		if (this.valeursDescriptionMap != null) {
			if (pI > 0 && pI <= this.valeursDescriptionMap.size()) {
				return this.valeursDescriptionMap.get(pI);
			}
		}
				
		return null;
		
	} // Fin de getValeurparColonne(
	 // int pI)._____________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getLongueursEnTableauEnTetes() {
		
		/* Retourne null si entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return null;
		}
		
		int[] longueursEnTableau 
		= new int[this.entetesDescriptionMap.size()];
	
		for (int i = 0; i < this.entetesDescriptionMap.size(); i++) {
			
			longueursEnTableau[i] = 0;
			
			
			longueursEnTableau[i] 
				   	= this.entetesDescriptionMap.get(i + 1).length();
						
		}
		
		return longueursEnTableau;
	
	} // Fin de getLongueursEnTableauEnTetes().____________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getLongueursEnTableauValeurs() {
		
		/* retourne null si entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return null;
		}
		
		/* retourne null si longueursDescriptionMap est null. */
		if (this.longueursDescriptionMap == null) {
			return null;
		}
		
		int[] longueursEnTableau 
			= new int[this.entetesDescriptionMap.size()];
		
		for (int i = 0; i < this.entetesDescriptionMap.size(); i++) {
			
			longueursEnTableau[i] = 0;
			
			/* Rappel : longueurDescriptionMap est remplie 
			 * lors de la lecture de la description d'un champ. */
			if (i < this.longueursDescriptionMap.size()) {
				longueursEnTableau[i] 
					   	= this.longueursDescriptionMap.get(i + 1);
			}
									
		}
		
		return longueursEnTableau;
		
	} // Fin de getLongueursEnTableauValeurs().____________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherTableauLongueurEnTetes() {
		
		/* Retourne null si entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return null;
		}
		
		/* Récupération du tableau des longueurs des en-têtes. */
		final int[] tableauLongueurs = this.getLongueursEnTableauEnTetes();
		
		/* retourne null 
		 * si this.getLongueursEnTableauEnTetes() retourne null. */
		if (tableauLongueurs == null) {
			return null;
		}
		
		final int taille = tableauLongueurs.length;
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append('[');
		
		for (int i = 0; i < taille; i++) {
			final int longueur = tableauLongueurs[i];
			
			stb.append("Longueur de '");
			stb.append(this.entetesDescriptionMap.get(i + 1));
			stb.append("' = ");
			stb.append(longueur);
			
			if (i < taille - 1) {
				stb.append(", ");
			}
		}
		
		stb.append(']');
		
		return stb.toString();
		
	} // Fin de afficherTableauLongueurEnTetes().__________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherTableauLongueurValeurs() {
		
		/* Retourne null si entetesDescriptionMap est null. */
		if (this.entetesDescriptionMap == null) {
			return null;
		}
		
		/* Récupération du tableau des longueurs des valeurs. */
		final int[] tableauLongueurs = this.getLongueursEnTableauValeurs();
		
		/* retourne null 
		 * si this.getLongueursEnTableauValeurs() retourne null. */
		if (tableauLongueurs == null) {
			return null;
		}
		
		final int taille = tableauLongueurs.length;
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append('[');
		
		for (int i = 0; i < taille; i++) {
			final int longueur = tableauLongueurs[i];
			
			stb.append("Longueur du contenu dans '");
			stb.append(this.entetesDescriptionMap.get(i + 1));
			stb.append("' = ");
			stb.append(longueur);
			
			if (i < taille - 1) {
				stb.append(", ");
			}
		}
		
		stb.append(']');
		
		return stb.toString();
		
	} // Fin de afficherTableauLongueurValeurs().__________________________
	
	
	
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
	public final String descriptionChampToString() {
	
		/* retourne null si valeursDescriptionMap est null. */
		if (this.valeursDescriptionMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
				
		/* VALEURS. */
		final Set<Entry<Integer, String>> valeursSet 
			= this.valeursDescriptionMap.entrySet();
		
		final Iterator<Entry<Integer, String>> iteValeurs 
			= valeursSet.iterator();
		
		while (iteValeurs.hasNext()) {
			
			final Entry<Integer, String> entryValeur 
				= iteValeurs.next();
			
			final String valeur = entryValeur.getValue();
			
			stb.append(valeur);
			
			stb.append(TAB);
		}
		
		/* Passage à la ligne. */
		stb.append(NEWLINE);
								
		return stb.toString();
	
	} // Fin de descriptionChampToString()._______________________________



	/**
	 * method nettoyerString(
	 * String pString) :<br/>
	 * Sert à retirer des caractères incongrus dans la description
	 * de fichier.<br/>
	 * Par exemple, "Sens" devient 'sens' (guillemets nettoyés).<br/>
	 * <br/>
	 * - retourne null si pString est null.<br/>
	 * <br/>
	 * 
	 * @param pString : la chaîne de caractères à nettoyer.<br/>
	 * 
	 * @return String : la chaîne nettoyée.<br/>
	 */
	@Override
	public final String nettoyerString(
			final String pString) {
		
		/* retourne null si pString est null. */
		if (pString == null) {
			return null;
		}
		
		String nettoyee = null;
				
		//**** MOTIF A RECHERCHER. ******/
		/*
		 * - GROUPE 0 = (\\A\\W*((\\w+)(([\\s\\W]*?\\w*))*)\\W*\\z)
		 * - GROUPE 1 = (\\A\\W*((\\w+)(([\\s\\W]*?\\w*))*)\\W*\\z)
		 * - GROUPE 2 = ((\\w+)(([\\s\\W]*?\\w*))*) :
		 * - UN OU PLUSIEURS
		 * CARACTERES DE MOT (avide, groupe capturant 3)
		 * ZERO, UN, OU PLUSIEURS FOIS :
		 * 		- ZERO, UN, OU PLUSIEURS ESPACES OU CARACTERES DE
		 * 		NON MOT réticent TERMINANT PAR ZERO, UN OU PLUSIEURS
		 * 		CARACTERES DE MOT avide (([\\s\\W]*?\\w*))* groupe4.
		 * 
		 * 
		 * */
		final String motifNettoye = "(\\A\\W*((\\w+)(([\\s\\W]*?\\w*))*)\\W*\\z)";
		
		//***** PATTERN. **********/
		final Pattern patternMotNettoye = Pattern.compile(motifNettoye);
		
		//***** MATCHER. **********/
		/* Recherche du motif dans la chaîne en entrée. */
		final Matcher matcherMotNettoye 
			= patternMotNettoye.matcher(pString);
		
		/* Si le matcher a matché, c'est que le
		 * motif existait. On ne retourne que le groupe
		 * capturant (le mot signifiant nettoyé).*/
		if (matcherMotNettoye.matches()) {
					
			/* ((\\w+)([\\s\\W]*\\w*)) - GROUPE 2.*/
			nettoyee = matcherMotNettoye.group(2);
						
			/* Rapport éventuel. */
			if (this.logDescription) {
								
				final String difference 
				= StringUtils.remove(pString, nettoyee);
								
				if (!StringUtils.isBlank(difference)) {
					
					this.rapportDescriptionStb.append(pString);
					this.rapportDescriptionStb.append(" : ");
					
					final String message = difference + " nettoyé.\n";
					
					this.rapportDescriptionStb.append(message);
				}
							
			} // Fin du rapport éventuel
		}
		
		/* Sinon, il n'y avait rien à nettoyer. */
		else {
			
			nettoyee = pString;
			
		}
		
		return nettoyee;
		
	} // Fin de nettoyerString(
	// String pString).____________________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String longueursMapToString() {
		
		/* retourne "" si longueursDescriptionMap est null. */
		if (this.longueursDescriptionMap == null) {
			return "";
		}
		
		/* retourne "" si longueursDescriptionMap est vide. */
		if (this.longueursDescriptionMap.isEmpty()) {
			return "";
		}
		
			
		final StringBuffer stb = new StringBuffer();
		stb.append('[');
		
		final Set<Entry<Integer, Integer>> entrySet 
		= this.longueursDescriptionMap.entrySet();
	
		final Iterator<Entry<Integer, Integer>> ite 
		= entrySet.iterator();
	
		int compteur = 0;
		final int nombreColonnes = this.longueursDescriptionMap.size();
	
		while (ite.hasNext()) {
		
			compteur++;
			
			final Entry<Integer, Integer> entry 
				= ite.next();
			
			final Integer longueurContenu 
				= entry.getValue();
			
			/* Valeur dans la colonne,
			 * comme 'Numéro de Département'. */
			stb.append(longueurContenu);
			
			if (compteur < nombreColonnes) {
				/* ,  si pas le dernier. */
				stb.append(", ");
			}
		
		}
		
		stb.append(']');
		
		return stb.toString();			
				
	} // Fin de longueursMapToString().____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> getEntetesDescriptionMap() {
		return this.entetesDescriptionMap;
	} // Fin de getEntetesDescriptionMap().________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEntetesDescriptionMap(
			final SortedMap<Integer, String> pColonnesDescriptionMap) {
		this.entetesDescriptionMap = pColonnesDescriptionMap;
	} // Fin de setEntetesDescriptionMap(
	 // SortedMap<Integer, String> pColonnesDescriptionMap)._______________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> getValeursDescriptionMap() {
		return this.valeursDescriptionMap;
	} // Fin de getValeursDescriptionMap().________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setValeursDescriptionMap(
			final SortedMap<Integer, String> pValeursDescriptionMap) {
		this.valeursDescriptionMap = pValeursDescriptionMap;
	} // Fin de setValeursDescriptionMap(
	 // SortedMap<Integer, String> pValeursDescriptionMap).________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNombreColonnesObligatoires() {
		return this.nombreColonnesObligatoires;
	} // Fin de getNombreColonnesObligatoires().___________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNombreColonnesObligatoires(
			final int pNombreColonnesObligatoires) {
		this.nombreColonnesObligatoires = pNombreColonnesObligatoires;
	} // Fin de setNombreColonnesObligatoires(
	 // int pNombreColonnesObligatoires.___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, Integer> getLongueursDescriptionMap() {
		return this.longueursDescriptionMap;
	} // Fin de getLongueursDescriptionMap().______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLongueursDescriptionMap(
			final SortedMap<Integer, Integer> pLongueursDescriptionMap) {
		this.longueursDescriptionMap = pLongueursDescriptionMap;
	} // Fin de setLongueursDescriptionMap(
	 // SortedMap<Integer, Integer> pLongueursDescriptionMap)._____________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isLogDescription() {
		return this.logDescription;
	} // Fin de isLogDescription().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLogDescription(
			final boolean pLogDescription) {
		this.logDescription = pLogDescription;
	} // Fin de setLogDescription(
	 // boolean pLogDescription).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringBuffer getRapportDescriptionStb() {
		return this.rapportDescriptionStb;
	} // Fin de getRapportDescriptionStb().________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRapportDescriptionStb(
			final StringBuffer pRapportDescriptionStb) {
		this.rapportDescriptionStb = pRapportDescriptionStb;
	} // Fin de setRapportDescriptionStb(
	 // StringBuffer pRapportDescriptionStb).______________________________


	
	/**
	 * method getOrdreChamps() :<br/>
	 * Getter de l'Ordre du champ (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @return ordreChamps : Integer.<br/>
	 */
	@Override
	public final Integer getOrdreChamps() {
		return this.ordreChamps;
	} // Fin de getOrdreChamps().__________________________________________



	/**
	 * method setOrdreChamps(
	 * Integer pOrdreChamps) :<br/>
	 * Setter de l'Ordre du champ (ligne) dans la description.<br/>
	 * Par exemple, 'Numéro de Section' est le 
	 * deuxième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @param pOrdreChamps : Integer : valeur à passer à ordreChamps.<br/>
	 */
	@Override
	public final void setOrdreChamps(
			final Integer pOrdreChamps) {
		this.ordreChamps = pOrdreChamps;
	} // Fin de setOrdreChamps(
	 // Integer pOrdreChamps)._____________________________________________



	/**
	 * method getIntitule() :<br/>
	 * Getter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @return intitule : String.<br/>
	 */
	@Override
	public String getIntitule() {
		return this.intitule;
	} // Fin de getIntitule()._____________________________________________



	/**
	 * method setIntitule(
	 * String pIntitule) :<br/>
	 * Setter de l'Intitulé du champ dans la description 
	 * comme 'Numéro de Section'.<br/>
	 * <br/>
	 *
	 * @param pIntitule : String : 
	 * valeur à passer à intitule.<br/>
	 */
	@Override
	public void setIntitule(
			final String pIntitule) {
		this.intitule = pIntitule;
	} // Fin de setIntitule(
	 // String pIntitule)._________________________________________________



	/**
	 * method getNomenclature() :<br/>
	 * Getter de la Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 *
	 * @return nomenclature : String.<br/>
	 */
	@Override
	public final String getNomenclature() {
		return this.nomenclature;
	} // Fin de getNomenclature()._________________________________________



	/**
	 * method setNomenclature(
	 * String pNomenclature) :<br/>
	 * Setter de la Nomenclature éventuelle du champ comme :<br/>
	 * "3 - Cumul des deux sens. [sep]<br/>
	 *  4 - Sens unique P.R. croissants.[sep]  <br/>
	 *  5 - Sens unique P.R. Décroissants.".<br/>
	 * <br/>
	 * <br/>
	 *
	 * @param pNomenclature : String : 
	 * valeur à passer à nomenclature.<br/>
	 */
	@Override
	public final void setNomenclature(
			final String pNomenclature) {
		this.nomenclature = pNomenclature;
	} // Fin de setNomenclature(
	 // String pNomenclature)._____________________________________________



	/**
	 * method getChampJava() :<br/>
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return champJava : String.<br/>
	 */
	@Override
	public final String getChampJava() {
		return this.champJava;
	} // Fin de getChampJava().____________________________________________



	/**
	 * method setChampJava(
	 * String pChampJava) :<br/>
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJava : String : valeur à passer à champJava.<br/>
	 */
	@Override
	public final void setChampJava(
			final String pChampJava) {
		this.champJava = pChampJava;
	} // Fin de setChampJava(
	 // String pChampJava).________________________________________________



	/**
	 * method getTypeJava() :<br/>
	 * Getter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return typeJava : String.<br/>
	 */
	@Override
	public final String getTypeJava() {
		return this.typeJava;
	} // Fin de getTypeJava()._____________________________________________



	/**
	 * method setTypeJava(
	 * String pTypeJava) :<br/>
	 * Setter du type Java du champ dans l'application 
	 * comme Integer pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pTypeJava : String : valeur à passer à typeJava.<br/>
	 */
	@Override
	public final void setTypeJava(
			final String pTypeJava) {
		this.typeJava = pTypeJava;
	} // Fin de setTypeJava(
	 // String pTypeJava)._________________________________________________



	/**
	 * method isANomenclature() :<br/>
	 * Getter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @return aNomenclature : boolean.<br/>
	 */
	@Override
	public final boolean isANomenclature() {
		return this.aNomenclature;
	} // Fin de isANomenclature()._________________________________________



	/**
	 * method setANomenclature(
	 * boolean pANomenclature) :<br/>
	 * Setter du boolean qui stipule si le champ 
	 * fait l'objet d'une nomenclature.<br/>
	 * <br/>
	 *
	 * @param pANomenclature : boolean : 
	 * valeur à passer à aNomenclature.<br/>
	 */
	@Override
	public final void setANomenclature(
			final boolean pANomenclature) {
		this.aNomenclature = pANomenclature;
	} // Fin de setANomenclature(
	 // boolean pANomenclature).___________________________________________

	
	
} // FIN DE LA CLASSE AbstractDescriptionChamp.------------------------------
