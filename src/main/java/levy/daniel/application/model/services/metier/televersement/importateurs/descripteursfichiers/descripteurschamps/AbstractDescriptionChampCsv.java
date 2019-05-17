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
 * CLASSE AbstractDescriptionChampCsv :<br/>
 * <p>
 * CLASSE ABSTRAITE décrivant un champ 
 * (correspondant à une ligne de la description) 
 * dans une description de fichier CSV comme un DARWIN_CSV.<br/>
 * Hérite de {@link AbstractDescriptionChamp}.
 * </p>
 * 
 * <p>
 * La description d'un champ de fichier CSV diffère 
 * de la description d'un champ de fichier ASCII car on besoin :<br/>
 * - des colonnes de début et de fin localisant le champ dans un fichier ASCII 
 * (par exemple, le département est localisé entre 
 * les colonnes 1 et 3 dans un HIT ASCII),<br/>
 * - de l'ordre du champ dans un fichier csv.<br/>
 * </p>
 *
 * <p>
 * Par exemple, le champ décrit à la 2ème ligne de la description
 * d'un fichier DARWIN_CSV a pour intitulé 'route', est
 * situé en deuxième position (2ème ligne de la description)
 * , a pour champJava 'route', pour typeJava 'String' ...<br/>
 * </p>
 * 
 * 
 * <p>
 * La description d'un DARWIN_CSV commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine ('début');depPrd;String;false;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;true;<br/>
 * 5;PR Origine;PR Origine ('début');prd;Integer;false;false;<br/>
 * .......................................................<br/>
 * </p>
 * 
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordreChamps</th> <th>intitule</th> <th>nomenclature</th> <th>champJava</th> 
 * <th>typeJava</th> <th>aNomenclature</th> <th>aLexique</th> 
 * </tr>
 * <tr>
 * <td>1</td> <td>Identifiant de la section</td> <td>Identifiant de la section</td> <td>objetId</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>2</td> <td>route</td> <td>Route au format Isidor (ex : A0034b1 ou A0006)</td> <td>route</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>3</td> <td>Département du PR Origine</td> <td>Département du PR Origine ('début')</td> <td>depPrd</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>4</td> <td>Code Concession du PR Origine</td> <td>Code Concession du PR Origine ('début')</td> <td>concessionPrd</td> 
 * <td>String</td> <td>false</td> <td>true</td> 
 * </tr>
 * <tr>
 * <td>5</td> <td>PR Origine</td> <td>PR Origine ('début')</td> <td>prd</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * </table>
 * </p>
 * 
 * <p>
 * L'encapsulation <code><b>DescriptionChamp</b></code> comportera donc 
 * (pour le 2ème champ 'route' 
 * d'une description de DARWIN) :
 * <ul>
 * <li>dans la SortedMap&lt;Integer, String&gt; <b><i>entetesDescriptionMap</i></b> :<br/> 
 * [1-ordreChamps;2-intitule;3-nomenclature;4-champJava;5-typeJava;6-aNomenclature;7-aLexique;]<br/>
 * <li>dans la SortedMap&lt;Integer, String&gt; <b><i>valeursDescriptionMap</i></b> :</li>
 * [1-2;2-route;3-Route au format Isidor (ex : A0034b1 ou A0006);4-route;5-String;6-false;7-false;]</li>
 * </ul>
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
 * (1, 'Ordre des champs'), (2, 'intitulé'), (3, 'nomenclature')
 * , (4, 'nom java'), ...
 * dans le cas d'un DARWIN_CSV.<br/>
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
 * (1, '1'), (2, 'Identifiant de la section')
 * , (3, 'Numéro applicatif de la section'), (4, 'objetId'), ...
 * dans le cas du champ 'objetId' (ligne 1) de la
 * description d'un DARWIN_CSV.
 * </li>
 * </ol>
 * - Hérite de AbstractRapporteur ce qui
 * garantit que tous les DescriptionChampCsv rapporteront.<br/>
 * </p>
 * 
 * <br/>
 * <br/>
 *
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <br/>
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
public abstract class AbstractDescriptionChampCsv 
									extends AbstractDescriptionChamp {

	// ************************ATTRIBUTS**********************************/
	/* CONSTANTES. */
	/**
	 * "CLASSE AbstractDescriptionChampCsv - ".<br/>
	 */
	public static final String CLASSE_ABSTRACTDESCRIPTIONCHAMPCSV 
		= "CLASSE AbstractDescriptionChampCsv - ";
		
	/**
	 * "Constructeur AbstractDescriptionChampCsv() - ".<br/>
	 */
	public static final String CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPCSV
		= "Constructeur AbstractDescriptionChampCsv() - ";
	
	/**
	 * "Méthode lireChamp(int[] pTokens) - ".<br/>
	 */
	public static final String METHODE_LIRECHAMP 
	= "Méthode lireChamp(int[] pTokens) - ";
	
	/**
	 * "Vous avez probablement inversé l'ordre des colonnes : ".<br/>
	 */
	public static final String MESSAGE_COLONNES_INVERSEES 
		= "Vous avez probablement inversé l'ordre des colonnes : ";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractDescriptionChampCsv.class);

	
	
	
	// *************************METHODES**********************************/

	/**
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un AbstractDescriptionChampCsv avec une description 
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
	 * dans la description de fichier.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 * 
	 * @throws Exception 
	 */
	public AbstractDescriptionChampCsv() throws Exception {
		
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
		 * dans la définition des fichiers CSV (DARWIN_CSV, ...). */
		this.entetesDescriptionMap = new TreeMap<Integer, String>();
		
		/* Remplissage de la Map. ***********/
		/* ordreChamps;intitule;nomenclature;champJava;
		 * typeJava;aNomenclature;aLexique; */
		this.entetesDescriptionMap.put(1, "ordreChamps");
		this.entetesDescriptionMap.put(2, "intitulé");
		this.entetesDescriptionMap.put(3, "nomenclature");
		this.entetesDescriptionMap.put(4, "champJava");
		this.entetesDescriptionMap.put(5, "typeJava");
		this.entetesDescriptionMap.put(6, "aNomenclature");
		this.entetesDescriptionMap.put(7, "aLexique");
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 7;
		
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampCsv().________________
	
	
	
	/**
	 * CONSTRUCTEUR D'ARITE 1 permettant d'instancier 
	 * un AbstractDescriptionChampCsv
	 * en lui passant la Map des colonnes de la description
	 * du fichier <code>this.entetesDescriptionMap</code>.<br/>
	 * <ul>
	 * <li>lit dans ressources_externes/messagestechniques.properties si il faut
	 * rapporter ou pas.</li>
	 * <li>Permet de construire un AbstractDescriptionChampCsv
	 * en lui passant la Map des colonnes de la description
	 * du fichier <code>this.entetesDescriptionMap</code>.</li>
	 * <li>instancie <code>this.entetesDescriptionMap</code> 
	 * avec pColonnesDescriptionMap.</li>
	 * <li>Alimente en dur dans la méthode 
	 * <code>this.nombreColonnesObligatoires</code>, c'est à dire
	 * le nombre de colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier.</li>
	 * </ul>
	 *
	 * @param pColonnesDescriptionMap : la Map des colonnes de la 
	 * description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	public AbstractDescriptionChampCsv(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
				throws Exception {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger les erreurs de la lecture du champ
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
			= "abstractdescriptionchampcsv.constructeur.mapnull";

			final String messageMapNull 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapNull);

			final String message 
			= CLASSE_ABSTRACTDESCRIPTIONCHAMPCSV 
			+ CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPCSV
			+ messageMapNull;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append('\n');
			}

			/* Jette une Exception circonstanciée. */
			throw new MapNullException(message);
			
		} // Fin de Map null.________________________________________
		
		/* Map vide. */
		if (pColonnesDescriptionMap.isEmpty()) {
			
			final String cleMapVide
			= "abstractdescriptionchampcsv.constructeur.mapvide";

			final String messageMapVide 
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleMapVide);

			final String message 
			= CLASSE_ABSTRACTDESCRIPTIONCHAMPCSV 
			+ CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPCSV 
			+ messageMapVide;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append('\n');
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
		this.nombreColonnesObligatoires = 7;
				
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampCsv(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
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
	public void lireChamp(
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
		
		/* Lecture des Tokens pour un fichier CSV (DARWIN_CSV) : */
		/* ordreChamps;intitule;nomenclature;champJava;
		 * typeJava;aNomenclature;aLexique; */
		String ordreChampsString =null;
		String intituleString = null;
		String nomenclatureString = null;
		String champJavaString = null;
		String typeJavaString = null;
		String aNomenclatureString = null;
		String aLexiqueString = null;
		
		//* 1 - LECTURE DE L'ORDRE DES CHAMPS. ************/
		ordreChampsString = this.recupererOrdreChamps(pTokens);
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(1, ordreChampsString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(1, ordreChampsString.length());
		
	 // FIN DE LECTURE DE L'ORDRE DES CHAMPS. ************/
		
		
		//* 2 - LECTURE DE 'INTITULE'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensIntituleNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		intituleString = pTokens[1];
		
		/* Ne nettoie pas. 
		 * Enlève juste d'éventuels guillemets.*/
		final String intituleNettoye 
			= StringUtils.remove(intituleString, "\"");
		
		/* Passage aux attributs. */
		this.intitule = intituleNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(2, intituleNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(2, intituleNettoye.length());
		// FIN DE LECTURE DE 'INTITULE'. ************/
		
		
		//* 3 - LECTURE DE 'NOMENCLATURE'. **************************/
		/* Nomenclature peut ne pas être renseigné si le champ
		 * n'avait pas de nomenclature.
		 *  On admet donc qu'elle ne soit
		 * pas renseignée */
		/* Insertion dans la Map des Valeurs. */
		nomenclatureString = pTokens[2];
		
		/* Ne nettoie pas.
		 * Enlève juste d'éventuels guillemets. */
		final String nomenclatureNettoye 
			= StringUtils.remove(nomenclatureString, "\"");
		
		/* Passage aux attributs. */
		this.nomenclature = nomenclatureNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(3, nomenclatureNettoye);
		
		/* Insertion dans la Map des longueurs. */
		if (nomenclatureNettoye != null) {
			this.longueursDescriptionMap
				.put(3, nomenclatureNettoye.length());
		}
		else {
			this.longueursDescriptionMap
				.put(3, 0);
		}		
		// FIN DE LECTURE DE 'NOMENCLATURE'. ************/
		
		
		//* 4 - LECTURE DE 'CHAMP JAVA'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensChampJavaNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		champJavaString = pTokens[3];
		
		final String champJavaNettoye = nettoyerString(champJavaString);
		
		/* Passage aux attributs. */
		this.champJava = champJavaNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(4, champJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(4, champJavaNettoye.length());
		// FIN DE LECTURE DE 'CHAMP JAVA'. ************/

		
		//* 5 - LECTURE DE 'TYPE JAVA'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensTypeJavaNonRenseigne(pTokens);
		
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		typeJavaString = pTokens[4];
		
		final String typeJavaNettoye = nettoyerString(typeJavaString);
		
		/* Passage aux attributs. */
		this.typeJava = typeJavaNettoye;
		
		/* Insertion dans la Map des Valeurs. ******/
		this.valeursDescriptionMap.put(5, typeJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(5, typeJavaNettoye.length());
		// FIN DE LECTURE DE 'TYPE JAVA'. ************/
		
		
		//* 6 - LECTURE DE 'A NOMENCLATURE'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensANomenclatureNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		aNomenclatureString = pTokens[5];
		
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
				this.valeursDescriptionMap.put(6, aNomenclatureNettoye);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(6
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
			this.valeursDescriptionMap.put(6, aNomenclatureNettoye);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(6
					, aNomenclatureNettoye.length());
			
		}		
		// FIN DE LECTURE DE 'A NOMENCLATURE'. ************/

		//* 7 - LECTURE DE 'A LEXIQUE'. **************************/			
		/* Lecture de aLexique. */
		aLexiqueString = pTokens[6];
		
		/* Si NON RENSEIGNE, false. */
		if (StringUtils.isBlank(aLexiqueString)) {
			aLexiqueString = "false";
			this.aLexique = false;
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
				this.valeursDescriptionMap.put(7, aLexiqueNettoye);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(7
						, aLexiqueNettoye.length());
			}
			
			/* Sinon, l'utilisateur doit fournir un 
			 * lexique. */
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
			this.valeursDescriptionMap.put(7, aLexiqueNettoye);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(7
					, aLexiqueNettoye.length());
			
		}		
		// FIN DE LECTURE DE 'A LEXIQUE'. ************/
		
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
	 * - LOG.fatal si pTokens[1] (intitule) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[1] (intitule) 
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
		
		if (StringUtils.isBlank(pTokens[1])) {
			
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
	 * - LOG.fatal si pTokens[3] (champJava) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[3] (champJava) 
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
		
		if (StringUtils.isBlank(pTokens[3])) {
			
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
	 * - LOG.fatal si pTokens[4] (typeJava) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[4] (typeJava) 
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
		
		if (StringUtils.isBlank(pTokens[4])) {
			
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
	 * - LOG.fatal si pTokens[5] (aNomenclature) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[5] (aNomenclature) 
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
		
		if (StringUtils.isBlank(pTokens[5])) {
			
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
	

		
} // FIN DE LA CLASSE AbstractDescriptionChampCsv.---------------------------
