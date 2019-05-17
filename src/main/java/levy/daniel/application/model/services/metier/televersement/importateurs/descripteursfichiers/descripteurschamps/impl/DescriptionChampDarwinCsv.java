package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampCsv;

/**
 * CLASSE DescriptionChampDarwinCsv :<br/>
 * <p>
 * IMPLEMENTATION de {@link AbstractDescriptionChampCsv} chargée
 * de stocker la définition d'un champ de fichier
 * DARWIN_CSV.<br/>
 * </p>
 * 
 * <p>
 * Un DescriptionChampDarwinCsv "sait" qu'une description 
 * de fichier DARWIN_CSV doit être ordonnée comme suit :<br/>
 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature, aLexique].<br/>
 * Il stocke cette liste ordonnée de champs dans sa map triée 
 * <code><b>this.entetesDescriptionMap</b></code> dès sa construction.<br/>
 * </p>
 * 
 * <p>
 * Un DescriptionChampDarwinCsv ne "connait" les valeurs 
 * décrivant un champ donné
 * qu'après l'execution de sa méthode lireChamps(String[] pTokens) 
 * où pTokens représente toutes les valeurs de la description 
 * du champ sous forme de tableau de String.<br/>
 * </p>
 * 
 * <p>
 * <code> // Lecture d'une ligne du fichier de description.</code><br/>
 * <code><b>public static final String[] ROUTE_DESC = {"2", "route", "Route au format Isidor (ex : A0034b1 ou A0006)", "route", "String", "false", "false"};</b></code><br/>
 * <code> // Import de la ligne de description dans un DescriptionChampDarwinCsv.</code><br/>
 * <code><b>descripteur.lireChamps(ROUTE_DESC);</b></code><br/>
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
 * Expressions régulières, Pattern, Matcher, NumberFormatException,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Levy Lévy
 * @version 1.0
 * @since 10 août 2011
 *
 */
public class DescriptionChampDarwinCsv extends AbstractDescriptionChampCsv {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_DESCRIPTIONCHAMPDARWINCSV : String : <br/>
	 * "CLASSE DescriptionChampDarwinCsv - ".<br/>
	 */
	public static final String CLASSE_DESCRIPTIONCHAMPDARWINCSV
		= "CLASSE DescriptionChampDarwinCsv - ";
	
	
	/**
	 * METHODE_LIRECHAMP : String : <br/>
	 * "Méthode lireChamp(int[] pTokens) - ".<br/>
	 */
	public static final String METHODE_LIRECHAMP 
	= "Méthode lireChamp(int[] pTokens) - ";

	
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(DescriptionChampDarwinCsv.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR DescriptionChampDarwinCsv() :<br/>
	 * <br/>
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un DescriptionChampDarwinCsv en instanciant et alimentant
	 * la SortedMap&lt;Integer, String&gt; 'entetesDescriptionMap'
	 * qui décrit les en-têtes de colonne directement en son sein.<br/>
	 * <br/>
	 * - Alimente le 'nombreColonnesObligatoires', c'est à dire
	 * les colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	public DescriptionChampDarwinCsv() throws Exception {
		
		super();
				
	} // Fin de CONSTRUCTEUR DescriptionChampDarwinCsv().__________________
	
	
	
	/**
	 * method CONSTRUCTEUR DescriptionChampDarwinCsv(
	 * SortedMap<Integer, String> pColonnesDescriptionMap) :
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <br/>
	 * - Permet de construire un DescriptionChampDarwinCsv
	 * en lui passant la Map triée des colonnes de la description
	 * du fichier 'entetesDescriptionMap'.<br/>
	 * <br/>
	 * - Alimente le 'nombreColonnesObligatoires', c'est à dire
	 * les colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier.<br/>
	 * <br/>
	 *
	 * @param pColonnesDescriptionMap : la Map des colonnes de la 
	 * description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	public DescriptionChampDarwinCsv(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
					throws Exception {
		
		super(pColonnesDescriptionMap);
		
	} // Fin de CONSTRUCTEUR DescriptionChampDarwinCsv(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________



	/**
	 * method traiterTokensNull(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.tableaunull";

			final String messageTableauNull 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauNull);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
	 * method traiterTokensVide(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.tableauvide";

			final String messageTableauVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
	 * method traiterTokensLongueur(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.tableautroppetit";

			final String messageTableauPetit
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauPetit);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
			+ METHODE_LIRECHAMP 
			+ messageTableauPetit
			+ pTokens.length
			+ " au lieu de "
			+ this.nombreColonnesObligatoires
			+ " attendus" 
			+ "\nLigne en cause : " 
			+ tokensToString(pTokens);

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			if (this.logDescription) {
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			throw new ExceptionImport(message);
			
		} // Fin de longueur trop petite.______________________
		
	} // Fin de traiterTokensLongueur(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method traiterTokensOrdreChampsNonRenseigne(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.ordrechampvide";

			final String messageOrdreChampVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleOrdreChampVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
	 * method traiterTokensIntituleNonRenseigne(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.intitulevide";

			final String messageIntituleVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleintituleeVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
			
		} // Fin de intitule non renseigné._____________________________

	} // Fin de traiterTokensIntituleNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * method traiterTokensChampJavaNonRenseigne(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.champjavavide";

			final String messageChampJavaVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleChampJavaVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
			
		} // Fin de champJava non renseigné.__________________
		
	} // Fin de traiterTokensChampJavaNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * method traiterTokensTypeJavaNonRenseigne(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.typejavavide";

			final String messageTypeJavaVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTypeJavaVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
			
		} // Fin de typeJava non renseigné._________________________
		
	} // Fin de traiterTokensTypeJavaNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method traiterTokensANomenclatureNonRenseigne(
	 * String[] pTokens) :<br/>
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
			= "descriptionchampdarwincsv.lirechamp.anomenclaturevide";

			final String messageANomenclatureVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleANomenclatureVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
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
		
	} //  Fin de traiterTokensANomenclatureNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * method recupererOrdreChamps(
	 * String[] pTokens) :<br/>
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
			
			/* Rapport événtuel. */
			if (this.logDescription) {
				
				final String message1 
				= CLASSE_DESCRIPTIONCHAMPDARWINCSV
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
				}
					
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
				= CLASSE_DESCRIPTIONCHAMPDARWINCSV
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
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "descriptionchampdarwincsv.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________


	
	/**
	 * method recupererCleLogErreur() :<br/>
	 * Fournit la clé du log des erreurs
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logDescription à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String : "descriptionchampdarwincsv.log.erreur" 
	 * pour un DescriptionChampDarwinCsv.<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "descriptionchampdarwincsv.log.erreur";
	} // Fin de recupererCleNiveauAnomalie().______________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomChampJava() {
		return this.getChampJava();
	} // Fin de getNomChampJava().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomClasse() {
		return CLASSE_DESCRIPTIONCHAMPDARWINCSV;
	} // Fin de getNomClasse().____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.anomenclaturetrue".<br/>
	 */
	@Override
	public final String getCleANomenclatureTrue() {
		return "descriptionchampdarwincsv.lirechamp.anomenclaturetrue";
	} // Fin de getCleANomenclatureTrue()._________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.alexiquetrue".<br/>
	 */
	@Override
	public final String getCleALexiqueTrue() {
		return "descriptionchampdarwincsv.lirechamp.alexiquetrue";
	} // Fin de getCleALexiqueTrue().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.tableaunull".<br/>
	 */
	@Override
	public final String getCleTableauNull() {
		return "descriptionchampdarwincsv.lirechamp.tableaunull";
	} // Fin de getCleTableauNull()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.tableauvide".<br/>
	 */
	@Override
	public final String getCleTableauVide() {
		return "descriptionchampdarwincsv.lirechamp.tableauvide";
	} // Fin de getCleTableauVide()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.tableautroppetit".<br/>
	 */
	@Override
	public final String getCleTableauTropPetit() {
		return "descriptionchampdarwincsv.lirechamp.tableautroppetit";
	} // Fin de getCleTableauTropPetit().__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.ordrechampvide".<br/>
	 */
	@Override
	public final String getCleOrdreChampVide() {
		return "descriptionchampdarwincsv.lirechamp.ordrechampvide";
	} // Fin de getCleOrdreChampVide().____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.colonnevide".<br/>
	 */
	@Override
	public final String getCleColonneVide() {
		return "descriptionchampdarwincsv.lirechamp.colonnevide";
	} // Fin de getCleColonneVide()._______________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.intitulevide".<br/>
	 */
	@Override
	public final String getCleIntituleVide() {
		return "descriptionchampdarwincsv.lirechamp.intitulevide";
	} // Fin de getCleIntituleVide().______________________________________

	
			
	/**
	 * {@inheritDoc}
	 * "descriptionchampdarwincsv.lirechamp.champjavavide".<br/>
	 */
	@Override
	public final String getCleChampJavaVide() {
		return "descriptionchampdarwincsv.lirechamp.champjavavide";
	} // Fin de getCleChampJavaVide().______________________________________


		
	/**
	* {@inheritDoc}
	* "descriptionchampdarwincsv.lirechamp.typejavavide".<br/>
	*/
	@Override
	public final String getCleTypeJavaVide() {
	return "descriptionchampdarwincsv.lirechamp.typejavavide";
	} // Fin de getCleTypeJavaVide().______________________________________


	
	/**
	* {@inheritDoc}
	* "descriptionchampdarwincsv.lirechamp.anomenclaturevide".<br/>
	*/
	@Override
	public final String getCleANomenclatureVide() {
	return "descriptionchampdarwincsv.lirechamp.anomenclaturevide";
	} // Fin de getCleANomenclatureVide()._________________________________


	
	/**
	 * "Description des champs d'un fichier DARWIN_CSV".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier DARWIN_CSV";
	} // Fin de getNomDescriptionChamp().__________________________________
	
	
	
} // FIN DE LA CLASSE DescriptionChampDarwinCsv.-----------------------------
