package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;

/**
 * CLASSE AbstractDescriptionChampAscii :<br/>
 * <p>
 * CLASSE ABSTRAITE décrivant un champ 
 * (correspondant à une ligne de la description) 
 * dans une description de fichier ASCII comme un HIT ou un HISTO_F07.<br/>
 * Hérite de {@link AbstractDescriptionChamp}.
 * </p>
 * 
 * <p>
 * La description d'un champ de fichier ASCII diffère 
 * de la description d'un champ de fichier CSV car on besoin :<br/>
 * - des colonnes de début et de fin localisant le champ dans un fichier ASCII 
 * (par exemple, le département est localisé entre 
 * les colonnes 1 et 3 dans un HIT ASCII),<br/>
 * - de l'ordre du champ dans un fichier csv.<br/>
 * </p>
 * 
 * <p>
 * Par exemple, le champ décrit à la 2ème ligne de la description
 * d'un fichier HISTO_F07 a pour intitulé 'Numéro de Section', est
 * situé dans les  colonnes 4 à 9, ...<br/>
 * </p>
 * 
 * <p>
 * La description d'un HISTO_F07 commence par :<br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;false;4;9;6;<br/>
 * .......................................................<br/>
 * </p>
 * 
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordreChamps</th> <th>colonnes</th> <th>longueur</th> <th>intitule</th> 
 * <th>nomenclature</th> <th>champJava</th> <th>typeJava</th> <th>aNomenclature</th>
 * <th>aLexique</th> <th>colonneDebut</th> <th>colonneFin</th> <th>longueurCalculee</th>
 * </tr>
 * <tr>
 * <td>1</td> <td>1-3</td> <td>3</td> <td>Numéro de Département</td> 
 * <td>cadré à gauche. Ex: dept 13 = 130</td> <td>numDepartement</td> <td>Integer</td> <td>false</td> 
 * <td>false</td> <td>1</td> <td>3</td> <td>3</td> 
 * </tr>
 * <tr>
 * <td>2</td> <td>4-9</td> <td>6</td> <td>Numéro de Section</td> 
 * <td> </td> <td>numSection</td> <td>String</td> <td>false</td> 
 * <td>false</td> <td>4</td> <td>9</td> <td>6</td> 
 * </tr>
 * </table>
 * </p>
 * 
 * <br/>
 * <p>
 * CLASSE chargée via ses constructeurs et sa méthode 
 * <code><b>lireChamp(String[] pTokens)</b></code> de : <br/> 
 * <ol>
 * <li>stocker dans une Map&lt;Integer, String&gt;
 * <code><b>this.entetesDescriptionMap</b></code> :
 * <ul>
 * <li>l'ordre des colonnes d'une description de fichier.</li>
 * <li>le libellé (java) des colonnes de la description
 * de fichier.</li>
 * </ul>
 * Par exemple :<br/>
 * {1=ordreChamps, 2=colonnes, 3=longueur, 4=intitule, 5=nomenclature, 6=champJava, 7=typeJava, 8=aNomenclature, 9=aLexique, 10=colonneDebut, 11=colonneFin, 12=longueurCalculee}
 * dans le cas d'un HISTO_F07.<br/>
 * <br/>
 * </li>
 * <li>stocker dans une Map&lt;Integer, String&gt;
 * <code><b>this.valeursDescriptionMap</b></code> :
 * <ul>
 * <li>l'ordre des colonnes d'une description de fichier.</li>
 * <li>la valeur (String) pour un champ (ligne) donné dans la description
 * de fichier.</li>
 * </ul>
 * Par exemple :<br/>
 * {1=1, 2=1-3, 3=3, 4=Numéro de Département, 5=cadré à gauche, 6=numDepartment, 7=Integer, 8=false, 9=false, 10=1, 11=3, 12=3}
 * dans le cas du champ 'numéro de département' (ligne 1) de la
 * description d'un HISTO_F07.
 * </li>
 * </ol>
 * - Hérite de AbstractRapporteur ce qui
 * garantit que tous les DescriptionChamp rapporteront.<br/>
 * </p>
 * 
 * <br/>
 * <br/>
 *
 * 
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un IDescriptionChamp.</code><br/>
 * <code><b>IDescriptionChamp desc 
 * = new DescriptionChampHistoF07();</b></code><br/>
 * <code> // récupération de la Map des en-têtes de la description de fichier (créée en dur lors de l'instanciation du IDescriptionChamp).</code><br/>
 * <code><b>Map&lt;Integer, String&gt; entetesDescriptionMap = desc.getColonnesDescriptionMap();</b></code><br/>
 * <code> // tableau de tokens correspondant à la description d'un champ (ligne d'une description de fichier).</code><br/>
 * <code><b>public static final String[] NUM_DEPT_DESC 
 * = {"1", "1-3", "3", "Numéro de Département", "cadré à gauche", "numDepartment", "Integer", "false", "false"};</b></code><br/>
 * <code> // LECTURE - Injecte toutes les valeurs du tableau de tokens NUM_DEPT_DESC dans la présente encapsulation</code><br/>
 * <code><b>desc.lireChamp(NUM_DEPT_DESC);</b></code><br/> 
 * <code>// récupération des valeurs encapsulées.</code><br/>  
 * <code><b>SortedMap&lt;Integer, String&gt; valeursDescriptionMap = desc.getValeursDescriptionMap();</b></code><br/>
 * </p>

 *
 * - Mots-clé :<br/>
 * Regex, Pattern, compile, Matcher, StringUtils, <br/>
 * Expression régulière, Expression reguliere, regex,<br/>
 * Nettoyer caractères de non-mot en début et en fin de chaîne,
 * composée ou non.<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Levy Lévy
 * @version 1.0
 * @since 27 juil. 2011
 *
 */
public abstract class AbstractDescriptionChampAscii 
							extends AbstractDescriptionChamp {

	// ************************ATTRIBUTS**********************************/
	/* CONSTANTES. */
	/**
	 * "CLASSE AbstractDescriptionChampAscii - ".<br/>
	 */
	public static final String CLASSE_ABSTRACTDESCRIPTIONCHAMP 
		= "CLASSE AbstractDescriptionChampAscii - ";
		
	/**
	 * "Constructeur AbstractDescriptionChampAscii() - ".<br/>
	 */
	public static final String CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPASCII 
		= "Constructeur AbstractDescriptionChampAscii() - ";
		
	/**
	 * "Méthode trouverColonneDebutFin(String pColonnes) - ".<br/>
	 */
	public static final String METHODE_TROUVERCOLONNEDEBUTFIN 
		= "Méthode trouverColonneDebutFin(String pColonnes) - ";
		
	/**
	 * "Impossible de trouver des colonnes 
	 * dans la chaine passée en paramètre : ".<br/>
	 */
	public static final String MESSAGE_COLONNES_INTROUVABLES 
		= "Impossible de trouver des colonnes " 
			+ "dans la chaine passée en paramètre : ";
		
	/**
	 * "Méthode lireChamp(int[] pTokens) - ".<br/>
	 */
	public static final String METHODE_LIRECHAMP 
	= "Méthode lireChamp(int[] pTokens) - ";
		
	/**
	 * "La chaine pColonnes passée en paramètre est null.".<br/>
	 */
	public static final String MESSAGE_COLONNES_NULL 
	= "La chaine pColonnes passée en paramètre est null.";
	
	/**
	 * "Vous avez probablement inversé l'ordre des colonnes : ".<br/>
	 */
	public static final String MESSAGE_COLONNES_INVERSEES 
		= "Vous avez probablement inversé l'ordre des colonnes : ";


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
	 * Colonne unique ou fourchette de colonnes positionnant 
	 * le champ à lire dans une ligne d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...).<br/>
	 * C'est la valeur fournie dans la description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) 
	 * comme 4-9 par exemple.<br/>
	 * Par exemple, 'Numéro de Section' est situé entre les colonnes 
	 * 4 et 9 d'une ligne d'un HISTO_F07.<br/>
	 */
	protected String colonnes;
		
	/**
	 * Longueur (nombre de colonnes) du champ 
	 * fournie dans la description du fichier.<br/>
	 */
	protected Integer longueur;
		
	/**
	 * Colonne de début du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 */
	protected Integer colonneDebut;
		
	/**
	 * Colonne de fin du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 */
	protected Integer colonneFin;
			
	/**
	 * calcul : colonneFin - colonneDebut.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 * Sert à s'assurer que la longueurCalculee correspond 
	 * avec la longueur indiquée dans la description de fichier.<br/>
	 */
	protected Integer longueurCalculee;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractDescriptionChampAscii.class);

	
		
	// *************************METHODES**********************************/

	/**
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un AbstractDescriptionChampAscii avec une description 
	 * des en-têtes et un nombre de colonnes obligatoires 
	 * défini en dur.<br/>
	 * <ul>
	 * <li>lit dans ressources_externes/messagestechniques.properties si il faut
	 * rapporter ou pas.</li>
	 * <li>instancie <code>this.entetesDescriptionMap</code> 
	 * avec des valeurs en dur dans la méthode.</li>
	 * <li>Alimente en dur dans la méthode 
	 * <code>this.nombreColonnesObligatoires</code>, c'est à dire
	 * le nombre de colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier <br/>(d'autres colonnes comme 
	 * 'colonne de début', 'colonne de fin', 'longueur', ... 
	 * peuvent être calculées après l'import sans avoir été 
	 * directement fournies dans la description de fichier.
	 * Il suffit d'avoir fourni un champ 'colonnes' sous 
	 * la forme '14-24').</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public AbstractDescriptionChampAscii() throws Exception {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger la lecture du champ
		 * ou pas. */
		final String cleLogDescription 
				= this.recupererCleLogErreur();

		final String logDescriptionString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechnique()
				.getString(cleLogDescription);
		
		if (StringUtils.containsIgnoreCase(logDescriptionString, "true")) {
			this.logDescription = true;
		}
		else {
			this.logDescription = false;
		}
		
		/* Instanciation du rapportDescriptionStb
		 * si le boolean est à true. */
		if (this.logDescription) {
			this.rapportDescriptionStb = new StringBuffer();
		}
		
		/* Instanciation de la Map décrivant les champs
		 * dans la définition des fichiers ASCII (HIT, HISTO_F07, ...). */
		this.entetesDescriptionMap = new TreeMap<Integer, String>();
		
		/* Remplissage de la Map. ***********/
		/* ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;
		 * typeJava;aNomenclature;aLexique; */
		this.entetesDescriptionMap.put(1, "ordreChamps");
		this.entetesDescriptionMap.put(2, "colonnes");
		this.entetesDescriptionMap.put(3, "longueur");
		this.entetesDescriptionMap.put(4, "intitule");
		this.entetesDescriptionMap.put(5, "nomenclature");
		this.entetesDescriptionMap.put(6, "champJava");
		this.entetesDescriptionMap.put(7, "typeJava");
		this.entetesDescriptionMap.put(8, "aNomenclature");
		this.entetesDescriptionMap.put(9, "aLexique");
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 9;
		
		/* Champs à calculer 
		 * colonneDebut;colonneFin;longueurCalculee;*/
		this.entetesDescriptionMap.put(10, "colonneDebut");
		this.entetesDescriptionMap.put(11, "colonneFin");
		this.entetesDescriptionMap.put(12, "longueurCalculee");
		
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampAscii().___________________
	
	
	
	/**
	 * CONSTRUCTEUR D'ARITE 1 permettant d'instancier 
	 * un AbstractDescriptionChampAscii
	 * en lui passant la Map des colonnes de la description
	 * du fichier <code>this.entetesDescriptionMap</code>.<br/>
	 * <ul>
	 * <li>lit dans ressources_externes/messagestechniques.properties si il faut
	 * rapporter ou pas.</li>
	 * <li>Permet de construire un AbstractDescriptionChampAscii
	 * en lui passant la Map des colonnes de la description
	 * du fichier <code>this.entetesDescriptionMap</code>.</li>
	 * <li>instancie <code>this.entetesDescriptionMap</code> 
	 * avec pColonnesDescriptionMap.</li>
	 * <li>Alimente en dur dans la méthode 
	 * <code>this.nombreColonnesObligatoires</code>, c'est à dire
	 * le nombre de colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier <br/>(d'autres colonnes comme 
	 * 'colonne de début', 'colonne de fin', 'longueur', ... 
	 * peuvent être calculées après l'import sans avoir été 
	 * directement fournies dans la description de fichier.
	 * Il suffit d'avoir fourni un champ 'colonnes' sous 
	 * la forme '14-24').</li>
	 * </ul>
	 *
	 * @param pColonnesDescriptionMap : SortedMap&lt;Integer, String&gt; : 
	 * la Map des colonnes de la description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	public AbstractDescriptionChampAscii(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
				throws Exception {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger la lecture du champ
		 * ou pas. */
		final String cleLogDescription 
			= this.recupererCleLogErreur();

		final String logDescriptionString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechnique()
				.getString(cleLogDescription);
		
		if (StringUtils.containsIgnoreCase(logDescriptionString, "true")) {
			this.logDescription = true;
		}
		else {
			this.logDescription = false;
		}
		
		/* Instanciation du rapportDescriptionStb
		 * si le boolean est à true. */
		if (this.logDescription) {
			this.rapportDescriptionStb = new StringBuffer();
		}
		
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* Map null. */
		if (pColonnesDescriptionMap == null) {
			
			final String cleMapNull
			= "abstractdescriptionchamp.constructeur.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTDESCRIPTIONCHAMP 
			+ CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPASCII 
			+ messageMapNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
			}

			/* Jette une Exception circonstanciée. */
			throw new MapNullException(message);
			
		} // Fin de Map null.________________________________________
		
		/* Map vide. */
		if (pColonnesDescriptionMap.isEmpty()) {
			
			final String cleMapVide
			= "abstractdescriptionchamp.constructeur.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTDESCRIPTIONCHAMP 
			+ CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPASCII 
			+ messageMapVide;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
			}

			/* Jette une Exception circonstanciée. */
			throw new MapVideException(message);
			
		} // Fin de Map vide._______________________________________
		
		// **************PARAMETRES VALIDES****************************/
		/* Passage des paramètres aux attributs. */
		this.entetesDescriptionMap = pColonnesDescriptionMap;
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 9;
				
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampAscii(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		if (this.ordreChamps != null && this.intitule != null) {
			
			return "Ligne : " + this.ordreChamps.toString() 
					+ " : " + this.intitule;
		}
		
		return "null";
		
	} // Fin de toString().________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void lireChamp(
			final String[] pTokens) 
				throws 
					Exception {
				
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* pTokens null. */
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensNull(pTokens);
		
		/* pTokens vide. */
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensVide(pTokens);
		
		/* Tableau de longueur trop petite. */
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensLongueur(pTokens);
		
		// **************PARAMETRES VALIDES****************************/
		
		/* INSTANCIATION DE LA MAP valeursDescriptionMap. */
		this.valeursDescriptionMap = new TreeMap<Integer, String>();
		
		/* Instanciation de la Map des longueurs. */
		this.longueursDescriptionMap = new TreeMap<Integer, Integer>();
		
		/* Lecture des Tokens pour un fichier ASCII (HIT, HISTO_F07, ...) : */
		/* ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;
		 * typeJava;aNomenclature;aLexique;
		 * colonneDebut;colonneFin;longueurCalculee; */
		String ordreChampsString =null;
		String colonnesString = null;
		String longueurString = null;
		String intituleString = null;
		String nomenclatureString = null;
		String champJavaString = null;
		String typeJavaString = null;
		String aNomenclatureString = null;
		String aLexiqueString = null;
		
		/* Champs calculés. */
		String colonneDebutString = null;
		String colonneFinString = null;
		String longueurCalculeeString = null;

		
		//* 1 - LECTURE DE L'ORDRE DES CHAMPS. ************/
		ordreChampsString = this.recupererOrdreChamps(pTokens);
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(1, ordreChampsString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(1, ordreChampsString.length());
		
	 // FIN DE LECTURE DE L'ORDRE DES CHAMPS. ************/
		
		
		
		//* 2 - LECTURE DE 'COLONNES'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensColonnesNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
			
		/* Lecture de colonnes. */
		colonnesString = pTokens[1];
		
		/* Nettoie le token. */
		final String colonnesnettoye = nettoyerString(colonnesString);
		
		/* Extraction des positions de début et de fin
		 * si colonnes était de la forme '1-3'. */
		/* Jette une ExceptionImport si c'est impossible. */
		int[] colonnesDetectees = null;
		
		try {
			colonnesDetectees = this.trouverColonneDebutFin(colonnesnettoye);
		} catch (Exception e1) {
			
			final String messageExc = e1.getMessage();
			final String message 
			= this.getNomClasse()
			+ METHODE_LIRECHAMP 
			+ messageExc;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e1);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message, e1);
		}
		
		/* Passage aux attributs. */
		this.colonnes = colonnesnettoye;	
		
		if (colonnesDetectees != null) {
			
			this.colonneDebut = colonnesDetectees[0];
			this.colonneFin = colonnesDetectees[1];
			
			/* Calcul de la longueur. */
			this.longueurCalculee = this.colonneFin - this.colonneDebut + 1;
			
			colonneDebutString = Integer.toString(this.colonneDebut);							
			colonneFinString = Integer.toString(this.colonneFin);		
			longueurCalculeeString 
				= Integer.toString(this.longueurCalculee);
		
			/* Insertion dans la Map des Valeurs. ******/
			this.valeursDescriptionMap.put(2, colonnesnettoye);
			
			this.valeursDescriptionMap.put(10, colonneDebutString);
			this.valeursDescriptionMap.put(11, colonneFinString);
			this.valeursDescriptionMap.put(12, longueurCalculeeString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(2
					, colonnesnettoye.length());
			
			this.longueursDescriptionMap.put(10
					, colonneDebutString.length());
			this.longueursDescriptionMap.put(11
					, colonneFinString.length());
			this.longueursDescriptionMap.put(12
					, longueurCalculeeString.length());
			
		}
		
	 // FIN DE LECTURE DE 'COLONNES'. ************/
		
		
		//* 3 - LECTURE DE 'LONGUEUR'. **************************/
		/* longueur n'a aucun intérêt puisque l'on peut
		 * la calculer. On admet donc qu'elle ne soit
		 * pas renseignée */
		/* Insertion dans la Map des Valeurs. */
		
		/* Si 'longueur' est renseignée, on vérifie qu'elle
		 * est bien parsable en Integer.
		 * Sinon, on l'annule. */
		
		String longueurNettoyee = null;
		
		if (pTokens[2] != null) {
			
			longueurString = pTokens[2];
			longueurNettoyee = nettoyerString(longueurString);
			
			try {
				/* Passage aux attributs. */
				this.longueur = Integer.parseInt(longueurNettoyee);
			} catch (NumberFormatException e) {
				longueurNettoyee = null;
			}
		}
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(3, longueurNettoyee);
		
		/* Insertion dans la Map des longueurs. */
		if (longueurNettoyee != null) {
			this.longueursDescriptionMap.put(3
					, longueurNettoyee.length());
		}
		else {
			this.longueursDescriptionMap.put(3
					, 0);
		}
		// FIN DE LECTURE DE 'LONGUEUR'. ************/
		
		
		//* 4 - LECTURE DE 'INTITULE'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensIntituleNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		intituleString = pTokens[3];
		
		/* Ne nettoie pas. 
		 * Enlève juste d'éventuels guillemets.*/
		final String intituleNettoye 
			= StringUtils.remove(intituleString, "\"");
		
		/* Passage aux attributs. */
		this.intitule = intituleNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(4, intituleNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(4, intituleNettoye.length());
		// FIN DE LECTURE DE 'INTITULE'. ************/
		
		
		//* 5 - LECTURE DE 'NOMENCLATURE'. **************************/
		/* Nomenclature peut ne pas être renseigné si le champ
		 * n'avait pas de nomenclature.
		 *  On admet donc qu'elle ne soit
		 * pas renseignée */
		/* Insertion dans la Map des Valeurs. */
		nomenclatureString = pTokens[4];
		
		/* Ne nettoie pas.
		 * Enlève juste d'éventuels guillemets. */
		final String nomenclatureNettoye 
			= StringUtils.remove(nomenclatureString, "\"");
		
		/* Passage aux attributs. */
		this.nomenclature = nomenclatureNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(5, nomenclatureNettoye);
		
		/* Insertion dans la Map des longueurs. */
		if (nomenclatureNettoye != null) {
			this.longueursDescriptionMap
				.put(5, nomenclatureNettoye.length());
		}
		else {
			this.longueursDescriptionMap
				.put(5, 0);
		}		
		// FIN DE LECTURE DE 'NOMENCLATURE'. ************/
		
		
		//* 6 - LECTURE DE 'CHAMP JAVA'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensChampJavaNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		champJavaString = pTokens[5];
		
		final String champJavaNettoye = nettoyerString(champJavaString);
		
		/* Passage aux attributs. */
		this.champJava = champJavaNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(6, champJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(6, champJavaNettoye.length());
		// FIN DE LECTURE DE 'CHAMP JAVA'. ************/

		
		//* 7 - LECTURE DE 'TYPE JAVA'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensTypeJavaNonRenseigne(pTokens);
		
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		typeJavaString = pTokens[6];
		
		final String typeJavaNettoye = nettoyerString(typeJavaString);
		
		/* Passage aux attributs. */
		this.typeJava = typeJavaNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(7, typeJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(7, typeJavaNettoye.length());
		// FIN DE LECTURE DE 'TYPE JAVA'. ************/
		
		
		//* 8 - LECTURE DE 'A NOMENCLATURE'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensANomenclatureNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		aNomenclatureString = pTokens[7];
		
		String aNomenclatureNettoye 
			= nettoyerString(aNomenclatureString);
		
		/* Sinon, CHAMP RENSEIGNE à true
		 * si la valeur est true avec n'importe quelle casse. */		
		if (Boolean.parseBoolean(aNomenclatureNettoye)) {
			
			/*Le champ nomenclature doit être
			 * renseigné si aNomenclature est à true. */
			if (StringUtils.isNotBlank(nomenclatureString)) {
				
				/* Transformation de 'True' ou 'tRue',...
				 * en 'true'. */
				aNomenclatureNettoye = "true";
				
				/* Passage aux attributs. */
				this.aNomenclature = true;
				
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(8, aNomenclatureNettoye);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(8
						, aNomenclatureNettoye.length());
			}
			
			/* Sinon, l'utilisateur doit fournir une 
			 * nomenclature. */
			else {
				
				final String cleANomenclatureTrue
					= this.getCleANomenclatureTrue();

				final String messageANomenclatureVide
				= ConfigurationApplicationManager
					.getBundleMessagesTechnique()
						.getString(cleANomenclatureTrue);

				final String message 
				= this.getNomClasse() 
				+ METHODE_LIRECHAMP 
				+ messageANomenclatureVide;

				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
				}

				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(message);
				
			} // Fin de pas de nomenclature
						
		} // Fin de aNomenclature renseigné avec qqchose ressemblant à true._
		
		/* Sinon, CHAMP RENSEIGNE à false. */
		else {
			
			aNomenclatureNettoye = "false";
			
			/* Passage aux attributs. */
			this.aNomenclature = false;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(8, aNomenclatureNettoye);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(8
					, aNomenclatureNettoye.length());
			
		}		
		// FIN DE LECTURE DE 'A NOMENCLATURE'. ************/

		//* 9 - LECTURE DE 'A LEXIQUE'. **************************/			
		/* Lecture de aLexique. */
		aLexiqueString = pTokens[8];
		
		/* Si NON RENSEIGNE, false. */
		if (StringUtils.isBlank(aLexiqueString)) {
			aLexiqueString = "false";
		}
		
		String aLexiqueNettoye 
			= nettoyerString(aLexiqueString);
		
		/* Sinon, CHAMP RENSEIGNE à true
		 * si la valeur est true avec n'importe quelle casse. */		
		if (Boolean.parseBoolean(aLexiqueNettoye)) {
			
			/*Le champ nomenclature doit être
			 * renseigné si aLexique est à true. */
			if (StringUtils.isNotBlank(nomenclatureString)) {
				
				/* Transformation de 'True' ou 'tRue',...
				 * en 'true'. */
				aLexiqueNettoye = "true";
				
				/* Passage aux attributs. */
				this.aLexique = true;
				
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(9, aLexiqueNettoye);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(9
						, aLexiqueNettoye.length());
			}
			
			/* Sinon, l'utilisateur doit fournir une 
			 * nomenclature. */
			else {
				
				final String cleALexiqueTrue
					= this.getCleALexiqueTrue();

				final String messageALexiqueVide
				= ConfigurationApplicationManager
					.getBundleMessagesTechnique()
						.getString(cleALexiqueTrue);

				final String message 
				= this.getNomClasse() 
				+ METHODE_LIRECHAMP 
				+ messageALexiqueVide;

				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
				}

				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(message);
				
			} // Fin de pas de nomenclature
						
		} // Fin de aLexique renseigné avec qqchose ressemblant à true._
		
		/* Sinon, CHAMP RENSEIGNE à false. */
		else {
			
			aLexiqueNettoye = "false";
			
			/* Passage aux attributs. */
			this.aLexique = false;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(9, aLexiqueNettoye);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(9
					, aLexiqueNettoye.length());
			
		}		
		// FIN DE LECTURE DE 'A LEXIQUE'. ************/

		/* 10 - CALCUL de COLONNE DE DEBUT. ***************************/
		/* Réalisé dans 2 - COLONNES. */
		
		/* 11 - CALCUL de COLONNE DE FIN. ****************************/
		/* Réalisé dans 2 - COLONNES. */
		
		/* 12 - CALCUL de LONGUEUR CALCULEE. **************************/
		/* Réalisé dans 2 - COLONNES. */
		
	} // Fin de lireChamp(
	// int[] pTokens)._____________________________________________________



	/**
	 * - LOG.fatal si pTokens est null.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une TableauNullException commentée si pTokens est null.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensNull(
			final String[] pTokens) 
				throws Exception {
		
		if (pTokens == null) {
			
			final String cleTableauNull
				= this.getCleTableauNull();

			final String messageTableauNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleTableauNull);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageTableauNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new TableauNullException(message);
			
		} // Fin de if (pTokens == null)._____________________________
		
	} // Fin de traiterTokensNull(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * - LOG.fatal si pTokens est vide.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une TableauVideException commentée si pTokens est vide.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensVide(
			final String[] pTokens) 
				throws Exception {
		
		if (pTokens.length == 0) {
			
			final String cleTableauVide
				= this.getCleTableauVide();

			final String messageTableauVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleTableauVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageTableauVide;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new TableauVideException(message);
			
		} // Fin de if (pTokens.length == 0)._________________________
		
	} // Fin de traiterTokensVide(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * - LOG.fatal si pTokens n'a pas la longueur minimale requise.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens est trop court.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensLongueur(
			final String[] pTokens) 
				throws Exception {
		
		if (pTokens.length < this.nombreColonnesObligatoires) {
			
			final String cleTableauPetit
				= this.getCleTableauTropPetit();

			final String messageTableauPetit
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleTableauPetit);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageTableauPetit
			+ pTokens.length
			+ " au lieu de "
			+ this.nombreColonnesObligatoires
			+ " attendus" 
			+ "\nLigne en cause : " 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de pToken trop court._____________________________
		
	} // Fin de traiterTokensLongueur(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * - LOG.fatal si pTokens[0] (ordreChamps) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[0] (ordreChamps) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensOrdreChampsNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[0])) {
			
			final String cleOrdreChampVide
				= this.getCleOrdreChampVide();

			final String messageOrdreChampVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleOrdreChampVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageOrdreChampVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de ordreChamps non renseigné.________________________
		
	} // Fin de traiterTokensOrdreChampsNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * - LOG.fatal si pTokens[1] (colonnes) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[1] (colonnes) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensColonnesNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[1])) {
			
			final String clecolonneVide
				= this.getCleColonneVide();

			final String messageColonneVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(clecolonneVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageColonneVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de colonnes non renseigné._______________________
		
	} // Fin de traiterTokensColonnesNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * - LOG.fatal si pTokens[3] (intitule) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[3] (intitule) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensIntituleNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[3])) {
			
			final String cleintituleeVide
				= this.getCleIntituleVide();

			final String messageIntituleVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleintituleeVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageIntituleVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de intitulé non renseigné.___________________

	} // Fin de traiterTokensIntituleNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * - LOG.fatal si pTokens[5] (champJava) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[5] (champJava) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensChampJavaNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[5])) {
			
			final String cleChampJavaVide
				= this.getCleChampJavaVide();

			final String messageChampJavaVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleChampJavaVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageChampJavaVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de champJava non renseigné._____________________________
		
	} // Fin de traiterTokensChampJavaNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * - LOG.fatal si pTokens[6] (typeJava) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[6] (typeJava) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensTypeJavaNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[6])) {
			
			final String cleTypeJavaVide
				= this.getCleTypeJavaVide();

			final String messageTypeJavaVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleTypeJavaVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageTypeJavaVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de typeJava non renseigné._____________________________

	} // Fin de traiterTokensTypeJavaNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * - LOG.fatal si pTokens[7] (aNomenclature) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[7] (aNomenclature) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensANomenclatureNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[7])) {
			
			final String cleANomenclatureVide
				= this.getCleANomenclatureVide();

			final String messageANomenclatureVide
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleANomenclatureVide);

			final String message 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP 
			+ messageANomenclatureVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de aNomenclature non renseigné.___________________
		
	} // Fin de traiterTokensANomenclatureNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * - Récupère et retourne ordreChamps (pTokens[0]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut ordreChamps.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @return String : ordreChamps sous forme de String.<br/>
	 * 
	 * @throws Exception 
	 */
	private String recupererOrdreChamps(
			final String[] pTokens) throws Exception {
		
		/* Si NON RENSEIGNE, exception, */
		this.traiterTokensOrdreChampsNonRenseigne(pTokens);
				
		/* Sinon, CHAMP ORDRE DES CHAMPS RENSEIGNE. */
		String ordreChampsString = null;
		String ordrechampNettoye = null;
		
		try {
			
			/* Nettoyage du champ (retrait des "",...). */			 
			ordrechampNettoye = nettoyerString(pTokens[0]);
			
			/* Essaie de parser la String ordrechampNettoye 
			 * en int pour VERIFIER LE FORMAT. */
			this.ordreChamps = Integer.parseInt(ordrechampNettoye);
			
			/* Passe pTokens[0] à la chaine
			 * si il est parsable en Integer. */
			ordreChampsString 
				= ordrechampNettoye;
						
		}
		
		/* Si le contenu dans ordreChamps de la description
		 *  n'était pas homogène à un entier. */
		catch (NumberFormatException nfe) {
			
			final String message1 
			= this.getNomClasse() 
			+ METHODE_LIRECHAMP
			+ "la valeur 'Ordre des champs' n'est pas convenablement "
			+ "renseignée pour cette ligne. ERREUR : "
			+ ordrechampNettoye 
			+ " n'était pas parsable en Integer.";
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message1);
			}
			
			/* Rapport événtuel. */
			if (this.logDescription) {
						
				this.rapportDescriptionStb.append(message1);
				this.rapportDescriptionStb.append(NEWLINE);
				
			} // Fin de rapport éventuel.__________________
			
			/*Si pTokens[0] n'était pas parsable en int,
			 * essaie d'identifier le nombre. */
			
			/* MOTIF recherchant les nombres n'importe où
			 * au milieu d'une chaine.*/
			final String motif = ".*?(\\d+).*?";
			final Pattern pattern 
				= Pattern.compile(motif);
			final Matcher matcherNombre 
				= pattern.matcher(ordrechampNettoye);
			
			if (matcherNombre.matches()) {
				
				/* Récupération de la chaine qui matche. */
				ordreChampsString 
					= matcherNombre.group(1);
				
				this.ordreChamps = Integer.parseInt(ordrechampNettoye);
			
			/* ERREUR DE FORMAT. */
			} else {
				
				final String message 
				= this.getNomClasse() 
				+ METHODE_LIRECHAMP
				+ "l'ordre des champs n'est pas parsable "
				+ "en nombre : " + pTokens[0];
				
				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
				}
				
				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(
					message, nfe);
				
			} // Fin de l'erreur de format._____________
			
		} // Fin de NumberFormatException.__________________________
		
		return ordreChampsString;
		
	} // Fin de recupererOrdreChamps(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * Recoit dans pColonnes "7" ou "18" ou "14-123".<br/>
	 * Retourne un tableau de 2 entiers correspondant
	 * aux colonnes de début et de fin 
	 * (par exemple 14 et 123 pour "14-123").<br/>
	 * <br/>
	 * Retourne la même colonne de début et de fin 
	 * si il s'agissait d'une colonne unique.<br/>
	 * <br/>
	 * - jette une ExceptionImport et LOG.fatal si pColonnes est null.<br/>
	 * <br/>
	 *
	 * @param pColonnes : String : "7" ou "125" ou "7 - 26"<br/>
	 * 
	 * @return colonnesDebutEtFin : int[] : tableau comportant
	 * les colonnes de début et de fin.<br/>
	 * 
	 * @throws ExceptionImport lorsque :<br/>
	 * - la chaine de caractères indiquant les colonnes
	 * passée en paramètre est null.<br/>
	 * - Il est impossible de trouver des colonnes 
	 * dans la chaine de caractères passée en paramètre.<br/>
	 */
	public final int[] trouverColonneDebutFin(
			final String pColonnes) 
					throws ExceptionImport {
		
		/* Déclaration-initialisation d'un tableau de int
		 * que la méthode va retourner. */
		int[] colonnesDebutEtFin = null;

		
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		if (pColonnes == null) {
			
			final String message 
				= CLASSE_ABSTRACTDESCRIPTIONCHAMP 
				+ METHODE_TROUVERCOLONNEDEBUTFIN
				+ MESSAGE_COLONNES_NULL;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new ExceptionImport(message);
			
		} // Fin de if (pColonnes == null)._______________________________

		
		
		
		// **************PARAMETRES VALIDES****************************/
		
		
		/* Déclaration-initialisation d'un motif détectant
		 * les numéros de colonne unique (comme "7" ou "124" par exemple)*/
		/* N'IMPORTE QUOI - QUE DES CHIFFRES 
		 * (AU MOINS UN CHIFFRE OU PLUS) - N'IMPORTE QUOI . */
		/* "\\W*?(\\d{1,})\\W*?". */
		final String motifColonneUnique = "\\W*?(\\d+)\\W*?";
		
		/* Déclaration-initialisation d'un motif détectant
		 * les fourchettes de colonnes (comme 7-124 ou 7-  124). */
		/* ZERO OU PLUSIEURS ESPACES - UN CHIFFRE OU PLUS - 
		 * ZERO OU PLUSIEURS ESPACES - 
		 * UN TIRET - ZERO OU PLUSIEURS ESPACES - UN CHIFFRE OU PLUS
		 * - ZERO OU PLUSIEURS ESPACES. */
		/* "\\s*(\\d{1,})\\s*-\\s*(\\d+)\\s*" */
		/* N'IMPORTE QUOI - UN CHIFFRE OU PLUS - 
		 * ZERO OU PLUSIEURS ESPACES - 
		 * UN TIRET - ZERO OU PLUSIEURS ESPACES - UN CHIFFRE OU PLUS
		 * - N'IMPORTE QUOI. */
		/* ".*?(\\d{1,})\\s*-\\s*(\\d+).*?". */
		final String motifDeuxColonnes = ".*?(\\d{1,})\\s*-\\s*(\\d+).*?";
		
		/* Instanciation des Pattern. */
		final Pattern patternColUnique 
			= Pattern.compile(motifColonneUnique);
		final Pattern patternDeuxColonnes 
			= Pattern.compile(motifDeuxColonnes);
		
		/* Instanciation d'un Matcher. */
		final Matcher matcherColUnique 
			= patternColUnique.matcher(pColonnes);
		final Matcher matcherDeuxColonnes 
			= patternDeuxColonnes.matcher(pColonnes);

		
		
		/* Si pColonnes ne contenait qu'une colonne unique :*/
		if (matcherColUnique.matches()) {
			
			/* Récupération de la chaine de caractères trouvée. */
			final String colonneUnique 
				= matcherColUnique.group(1);
			
			/*Instanciation du tableau de int à 2 éléments.*/
			colonnesDebutEtFin = new int[2];
			
			/* Affectation aux colonne de début et de fin*/
			colonnesDebutEtFin[0] 
			                   = Integer.parseInt(colonneUnique);
			
			colonnesDebutEtFin[1] 
			                   = Integer.parseInt(colonneUnique);
			
		} // Fin de Si Colonne Unique.____________________________________
		

		
		
		/* Si pColonnes contenait une fourchette de colonnes :*/
		else if (matcherDeuxColonnes.matches()) {
			
			/* Récupération des groupes capturant trouvés. */
			final String colonneDebutInterne 
				= matcherDeuxColonnes.group(1);
			final String colonneFinInterne 
				= matcherDeuxColonnes.group(2);
			
			/*Instanciation du tableau de int à 2 éléments.*/
			colonnesDebutEtFin = new int[2];
			
			/* Vérifie que la colonne de fin est
			 * >= à la colonne de début*/
			if (Integer.parseInt(colonneFinInterne) 
					>= Integer.parseInt(colonneDebutInterne)) {
				
				/* Affectation aux colonne de début et de fin*/
				colonnesDebutEtFin[0] 
				                   = Integer.parseInt(colonneDebutInterne);
				
				colonnesDebutEtFin[1] 
				                   = Integer.parseInt(colonneFinInterne);
			}
			/* Sinon, les colonnes sont inversées. */
			else {
				
				final String message 
				= CLASSE_ABSTRACTDESCRIPTIONCHAMP 
				+ METHODE_TROUVERCOLONNEDEBUTFIN
				+ MESSAGE_COLONNES_INVERSEES
				+ "colonne de début = "
				+ colonneDebutInterne
				+ '\t'
				+ "colonne de fin = "
				+ colonneFinInterne;
			
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
			
				throw new ExceptionImport(message);
			}
			
			
			
		} // Fin de si Fourchette de Colonnes.____________________________
		
		else {
			
			final String message 
				= CLASSE_ABSTRACTDESCRIPTIONCHAMP 
				+ METHODE_TROUVERCOLONNEDEBUTFIN
				+ MESSAGE_COLONNES_INTROUVABLES
				+ pColonnes;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new ExceptionImport(message);
			
		}
		
		/* Renvoi du tableau comportant les colonnes de début
		 * et de fin.*/
		return colonnesDebutEtFin;
		
	} // Fin de trouverColonneDebutFin(String pColonnes).___________________
	

	
	/**
	 * Getter de la Colonne unique ou fourchette de colonnes positionnant 
	 * le champ à lire dans une ligne d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...).<br/>
	 * C'est la valeur fournie dans la description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) 
	 * comme 4-9 par exemple.<br/>
	 * Par exemple, 'Numéro de Section' est situé entre les colonnes 
	 * 4 et 9 d'une ligne d'un HISTO_F07.<br/>
	 *
	 * @return colonnes : String.<br/>
	 */
	public final String getColonnes() {
		return this.colonnes;
	} // Fin de getColonnes()._____________________________________________



	/**
	 * Setter de la Colonne unique ou fourchette de colonnes positionnant 
	 * le champ à lire dans une ligne d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...).<br/>
	 * C'est la valeur fournie dans la description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) 
	 * comme 4-9 par exemple.<br/>
	 * Par exemple, 'Numéro de Section' est situé entre les colonnes 
	 * 4 et 9 d'une ligne d'un HISTO_F07.<br/>
	 *
	 * @param pColonnes : String : valeur à passer à colonnes.<br/>
	 */
	public final void setColonnes(
			final String pColonnes) {
		this.colonnes = pColonnes;
	} // Fin de setColonnes(
	 // String pColonnes)._________________________________________________



	/**
	 * Getter de la Longueur (nombre de colonnes) du champ 
	 * fournie dans la description du fichier.<br/>
	 *
	 * @return longueur : Integer.<br/>
	 */
	public final Integer getLongueur() {
		return this.longueur;
	} // Fin de getLongueur()._____________________________________________



	/**
	 * Setter de la Longueur (nombre de colonnes) du champ 
	 * fournie dans la description du fichier.<br/>
	 *
	 * @param pLongueur : Integer : 
	 * valeur à passer à longueur.<br/>
	 */
	public final void setLongueur(
			final Integer pLongueur) {
		this.longueur = pLongueur;
	} // Fin de setLongueur(
	 // Integer pLongueur).________________________________________________



	/**
	 * Getter de la Colonne de début du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 *
	 * @return colonneDebut : Integer.<br/>
	 */
	public final Integer getColonneDebut() {
		return this.colonneDebut;
	} // Fin de getColonneDebut()._________________________________________



	/**
	 * Setter de la Colonne de début du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 *
	 * @param pColonneDebut : Integer : valeur à passer à colonneDebut.<br/>
	 */
	public final void setColonneDebut(
			final Integer pColonneDebut) {
		this.colonneDebut = pColonneDebut;
	} // Fin de setColonneDebut(
	 // Integer pColonneDebut).____________________________________________



	/**
	 * Getter de la Colonne de fin du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 *
	 * @return colonneFin : Integer.<br/>
	 */
	public final Integer getColonneFin() {
		return this.colonneFin;
	} // Fin de getColonneFin().___________________________________________



	/**
	 * Setter de la Colonne de fin du champ (1-based) fournie 
	 * dans une ligne de la d'une description de fichier ASCII 
	 * (HIT, HISTO_F07, HISTO_F08, ...) comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 *
	 * @param pColonneFin : Integer : 
	 * valeur à passer à colonneFin.<br/>
	 */
	public final void setColonneFin(
			final Integer pColonneFin) {
		this.colonneFin = pColonneFin;
	} // Fin de setColonneFin(
	 // Integer pColonneFin).______________________________________________



	/**
	 * Getter du calcul : colonneFin - colonneDebut.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 * Sert à s'assurer que la longueurCalculee correspond 
	 * avec la longueur indiquée dans la description de fichier.<br/>
	 *
	 * @return longueurCalculee : Integer.<br/>
	 */
	public final Integer getLongueurCalculee() {
		return this.longueurCalculee;
	} // Fin de getLongueurCalculee()._____________________________________



	/**
	 * Setter du calcul : colonneFin - colonneDebut.<br/>
	 * La présente méthode <i>lireChamp(...) calcule cette valeur</i>.<br/>
	 * Sert à s'assurer que la longueurCalculee correspond 
	 * avec la longueur indiquée dans la description de fichier.<br/>
	 *
	 * @param pLongueurCalculee : Integer : 
	 * valeur à passer à longueurCalculee.<br/>
	 */
	public final void setLongueurCalculee(
			final Integer pLongueurCalculee) {
		this.longueurCalculee = pLongueurCalculee;
	} // Fin de setLongueurCalculee(
	 // Integer pLongueurCalculee).________________________________________

		

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomChampJava() {
		return this.getChampJava();
	} // Fin de getNomChampJava().___________________________________________

	
	
	/**
	 * Retourne le nom de la Classe.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getNomClasse();
	

	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ANomenclature à true et la nomenclature vide.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleANomenclatureTrue();
	

	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ALexique à true et le lexique vide.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleALexiqueTrue();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties
	 * en cas de ligne de description null.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleTableauNull();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description vide.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleTableauVide();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description trop courte.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleTableauTropPetit();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec ordreChamps non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleOrdreChampVide();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec colonnes non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleColonneVide();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec intitule non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleIntituleVide();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec champJava non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleChampJavaVide();
	
	
	
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec typeJava non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleTypeJavaVide();
	

		
	/**
	 * Retourne la clé contenue dans 
	 * ressources_externes/messagestechniques.properties 
	 * en cas de ligne de description avec aNomenclature non renseigné.<br/>
	 *
	 * @return : String.<br/>
	 */
	public abstract String getCleANomenclatureVide();
	
	
	
} // FIN DE LA CLASSE AbstractDescriptionChampAscii.-------------------------
