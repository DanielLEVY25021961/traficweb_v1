package levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps;

import java.util.SortedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.exceptions.technical.impl.MapNullException;
import levy.daniel.application.exceptions.technical.impl.MapVideException;

/**
 * class AbstractDescriptionChampCsv :<br/>
 * CLASSE ABSTRAITE décrivant un champ (ligne) 
 * dans une description de fichier csv.<br/>
 * Par exemple, le champ décrit à la 2ème ligne de la description
 * d'un fichier DarwinCsv a pour intitulé 'route', est
 * situé en deuxième position (2ème ligne de la description)
 * , a pour champJava 'route', pour typeJava 'String' ...<br/>
 * <br/>
 * Une description de Darwin csv commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine  ('début');depPrd;String;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;<br/>
 * 5;PR Origine;PR Origine  ('début');prd;Integer;false;<br/>
 * <br/>
 * CLASSE chargée via ses constructeurs et sa méthode 
 * lireChamp(String[] pTokens) de : <br/> 
 * <br/>
 * - 1 - stocker dans une Map&lt;Integer, String&gt;
 * 'entetesDescriptionMap' :<br/>
 * 1a - l'ordre des colonnes d'une description de fichier.<br/>
 * 1b - le libellé (java) des colonnes de la description
 * de fichier.<br/>
 * Par exemple :<br/>
 * (1, 'Ordre des champs'), (2, 'intitulé'), (3, 'nomenclature')
 * , (4, 'nom java'), ...
 * dans le cas d'un DarwinCsv.<br/>
 * <br/>
 * - 2 - stocker dans une Map&lt;Integer, String&gt;
 * 'valeursDescriptionMap' :<br/>
 * 2a - l'ordre des colonnes d'une description de fichier.<br/>
 * 2b - la valeur (String) pour un champ (ligne) donné dans la description
 * de fichier.<br/>
 * Par exemple :<br/>
 * (1, '1'), (2, 'Identifiant de la section')
 * , (3, 'Numéro applicatif de la section'), (4, 'objetId'), ...
 * dans le cas du champ 'objetId' (ligne 1) de la
 * description d'un DarwinCsv.<br/>
 * <br/>
 * - Hérite de AbstractRapporteur ce qui
 * garantit que tous les DescriptionChampCsv rapporteront.<br/>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 * <br/>
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
	 * CLASSE_ABSTRACTDESCRIPTIONCHAMPCSV : String : <br/>
	 * "CLASSE AbstractDescriptionChampCsv - ".<br/>
	 */
	public static final String CLASSE_ABSTRACTDESCRIPTIONCHAMPCSV 
		= "CLASSE AbstractDescriptionChampCsv - ";
	
	
	/**
	 * CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPCSV : String : <br/>
	 * "Constructeur AbstractDescriptionChampCsv() - ".<br/>
	 */
	public static final String CONSTRUCTEUR_ABSTRACTDESCRIPTIONCHAMPCSV
		= "Constructeur AbstractDescriptionChampCsv() - ";
	
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(AbstractDescriptionChampCsv.class);

	
	
	
	// *************************METHODES**********************************/

	/**
	 * method CONSTRUCTEUR AbstractDescriptionChampCsv() :<br/>
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un AbstractDescriptionChampCsv 'tout nu'.<br/>
	 * - lit dans messagestechniques.properties si il faut
	 * rapporter ou pas.<br/>
	 * <br/>
	 * A utiliser avec les setters ou en instanciant
	 * la Map&lt;Integer, String&gt; 'entetesDescriptionMap'
	 * en son sein.<br/>
	 * <br/>
	 * - Il FAUT ALIMENTER 'nombreColonnesObligatoires' dans
	 * l'appel de ce constructeur par les classes concrètes.<br/>
	 * <br/>
	 */
	public AbstractDescriptionChampCsv() {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger la lecture du champ
		 * ou pas. */
		final String cleLogDescription 
				= this.recupererCleLogErreur();

		final String logDescriptionString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechniques()
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
		
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampCsv().________________
	
	
	
	/**
	 * method CONSTRUCTEUR AbstractDescriptionChampCsv(
	 * SortedMap<Integer, String> pColonnesDescriptionMap) :<br/>
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <br/>
	 *  - lit dans messagestechniques.properties si il faut
	 * rapporter ou pas.<br/>
	 * - Permet de construire un AbstractDescriptionChampCsv
	 * en lui passant la Map des colonnes de la description
	 * du fichier 'entetesDescriptionMap'.<br/>
	 * <br/>
	 * - Il FAUT ALIMENTER 'nombreColonnesObligatoires' dans
	 * l'appel de ce constructeur par les classes concrètes.<br/>
	 * <br/>
	 *
	 * @param pColonnesDescriptionMap : la Map des colonnes de la 
	 * description du fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map passée en paramètre
	 * est null.<br/>
	 * @throws MapVideException lorsque : la Map passée en paramètre
	 * est vide.<br/>
	 */
	public AbstractDescriptionChampCsv(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
				throws MapNullException, MapVideException {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger les erreurs de la lecture du champ
		 * ou pas. */
		final String cleLogDescription 
			= this.recupererCleLogErreur();

		final String logDescriptionString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechniques()
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
				.getBundleMessagesTechniques()
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
				.getBundleMessagesTechniques()
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
				
	} // Fin de CONSTRUCTEUR AbstractDescriptionChampCsv(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________
	

		
} // FIN DE LA CLASSE AbstractDescriptionChampCsv.---------------------------
