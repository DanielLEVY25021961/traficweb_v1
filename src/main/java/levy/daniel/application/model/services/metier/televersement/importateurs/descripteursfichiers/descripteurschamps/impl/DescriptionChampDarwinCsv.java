package levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.IConstantes;
import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.MapNullException;
import levy.daniel.application.exceptions.technical.impl.MapVideException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampCsv;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class DescriptionChampDarwinCsv :<br/>
 * IMPLEMENTATION de AbstractDescriptionChampCsv chargée
 * de stocker la définition d'un champ de fichier
 * Darwin csv.<br/>
 * <br/>
 * Un DescriptionChampDarwinCsv "sait" qu'une description 
 * de fichier Darwin csv doit être ordonnée comme suit :<br/>
 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature].<br/>
 * Il stocke cette liste ordonnée de champs dans sa map triée 
 * 'entetesDescriptionMap' dès sa construction.<br/>
 * <br/>
 * Un DescriptionChampDarwinCsv ne "connait" les valeurs 
 * décrivant un champ donné
 * qu'après l'execution de sa méthode lireChamps(String[] pTokens) 
 * où pTokens représente toutes les valeurs de la description 
 * du champ sous forme de tableau de String.<br/>
 * <code>public static final String[] ROUTE = {"2", "route", "Route au format Isidor (ex : A0034b1 ou A0006)", "route", "String", "false"};</code><br/>
 * <code>descripteur.lireChamps(ROUTE);</code><br/>
 * <br/>
 * Un fichier de description d'un Darwin_csv formatée en csv (';') 
 * commence par :<br/>
 * <br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine  ('début');depPrd;String;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;<br/>
 * 5;PR Origine;PR Origine  ('début');prd;Integer;false;<br/>
 * ..............................<br/>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>IDescriptionChamp desc 
 * = new DescriptionChampDarwinCsv();</code><br/>
 * <code>desc.getColonnesDescriptionMap();</code><br/>
 * <code>public static final String[] ROUTE 
 * = {"2", "route", "Route au format Isidor (ex : A0034b1 ou A0006)"
 * , "route", "String", "false"};</code><br/>
 * <code>desc.lireChamp(ROUTE);   //Injecte toutes les valeurs du tableau de tokens ROUTE dans la présente encapsulation</code><br/>
 * <code>desc.getValeursDescriptionMap();</code><br/>
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
	 */
	public DescriptionChampDarwinCsv() {
		
		super();
		
		/* Instanciation de la Map décrivant les champs
		 * dans la définition des fichiers Darwin. */
		this.entetesDescriptionMap = new TreeMap<Integer, String>();
		
		/* Remplissage de la Map. ***********/
		this.entetesDescriptionMap.put(1, "ordreChamps");
		this.entetesDescriptionMap.put(2, "intitule");
		this.entetesDescriptionMap.put(3, "nomenclature");
		this.entetesDescriptionMap.put(4, "champJava");
		this.entetesDescriptionMap.put(5, "typeJava");
		this.entetesDescriptionMap.put(6, "aNomenclature");
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 6;
		
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
	 * @throws MapNullException lorsque : la Map passée en paramètre
	 * est null.<br/>
	 * @throws MapVideException lorsque : la Map passée en paramètre
	 * est vide.<br/>
	 */
	public DescriptionChampDarwinCsv(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
					throws MapNullException, MapVideException {
		
		super(pColonnesDescriptionMap);
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 6;
		
	} // Fin de CONSTRUCTEUR DescriptionChampDarwinCsv(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void lireChamp(
			final String[] pTokens) 
				throws 
					TableauNullException
						, TableauVideException
							, ExceptionImport {
		
		
		// ***********TRAITEMENT DES PARAMETRES INVALIDES**************/
		/* pTokens null. */
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensNull(pTokens);
		
		/* pTokens vide. */
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensVide(pTokens);
		
		/* Tableau de longueur trop petite*/
		/* LOG, rapport d'erreur et Exception. */
		this.traiterTokensLongueur(pTokens);
		
		// **************PARAMETRES VALIDES****************************/

		/* INSTANCIATION DE LA MAP valeursDescriptionMap. */
		this.valeursDescriptionMap = new TreeMap<Integer, String>();
		
		/* Instanciation de la Map des longueurs. */
		this.longueursDescriptionMap = new TreeMap<Integer, Integer>();
		
		/* Lecture des Tokens : */
		String ordreChampsString =null;
		String intituleString = null;
		String nomenclatureString = null;
		String champJavaString = null;
		String typeJavaString = null;
		String aNomenclatureString = null;

		
		//* 1 - LECTURE DE L'ORDRE DES CHAMPS. ************/
		/* Si NON RENSEIGNE, exception, */
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
		
		/* Passage aux attributs */
		this.intitule = intituleNettoye;
		
		/* Insertion dans la Map des valeurs. */
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
		
		/* Passage aux attributs */
		this.nomenclature = nomenclatureNettoye;
		
		/* Insertion dans la Map des valeurs. */
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
		
		/* Passage aux attributs */
		this.champJava = champJavaNettoye;
		
		/* Insertion dans la Map des valeurs. */
		this.valeursDescriptionMap.put(4, champJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(4, champJavaNettoye.length());
		// FIN DE LECTURE DE 'CHAMP JAVA'. ************/

		
		//* 5 - LECTURE DE 'TYPE JAVA'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensTypeJavaNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		typeJavaString = pTokens[4];
		
		final String typeJavaNettoye = nettoyerString(typeJavaString);
		
		/* Passage aux attributs */
		this.typeJava = typeJavaNettoye;
		
		/* Insertion dans la Map des valeurs. */
		this.valeursDescriptionMap.put(5, typeJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(5, typeJavaNettoye.length());
		// FIN DE LECTURE DE 'TYPE JAVA'. ************/
		
		
		//* 6 - LECTURE DE 'A NOMENCLATURE'. **************************/
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensANomenclatureNonRenseigne(pTokens);
		
		
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
				
				/* Passage aux attributs */
				
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(6, aNomenclatureNettoye);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(6
						, aNomenclatureNettoye.length());
			}
			/* Sinon, l'utilisateur doit fournir une 
			 * nomenclature. */
			else {
				
				final String cleANomenclatureVide
				= "descriptionchampdarwincsv.lirechamp.anomenclaturetrue";

				final String messageANomenclatureVide
				= ConfigurationApplicationManager.getBundleMessagesTechniques()
						.getString(cleANomenclatureVide);

				final String message 
				= CLASSE_DESCRIPTIONCHAMPDARWINCSV 
				+ METHODE_LIRECHAMP 
				+ messageANomenclatureVide;

				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
				}

				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(message);
				
			}
						
		}
		/* Sinon, CHAMP RENSEIGNE à false. */
		else {
			
			aNomenclatureNettoye = "false";
			
			/* Passage aux attributs */
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(6, aNomenclatureNettoye);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(6
					, aNomenclatureNettoye.length());
			
		}		
		// FIN DE LECTURE DE 'A NOMENCLATURE'. ************/
		
	} // Fin de lireChamp(
	// int[] pTokens)._____________________________________________________



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
	 * @throws TableauNullException lorsque : pTokens est null.<br/>
	 */
	private void traiterTokensNull(
			final String[] pTokens) 
				throws TableauNullException {
		
		if (pTokens == null) {
			
			final String cleTableauNull
			= "descriptionchampdarwincsv.lirechamp.tableaunull";

			final String messageTableauNull 
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws TableauVideException lorsque : pTokens est vide.<br/>
	 */
	private void traiterTokensVide(
			final String[] pTokens) 
				throws TableauVideException {
		
		if (pTokens.length == 0) {
			
			final String cleTableauVide
			= "descriptionchampdarwincsv.lirechamp.tableauvide";

			final String messageTableauVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens est trop court.<br/>
	 */
	private void traiterTokensLongueur(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (pTokens.length < this.nombreColonnesObligatoires) {
			
			final String cleTableauPetit
			= "descriptionchampdarwincsv.lirechamp.tableautroppetit";

			final String messageTableauPetit
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens[0] (ordreChamps) 
	 * n'est pas renseigné.<br/>
	 */
	private void traiterTokensOrdreChampsNonRenseigne(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (StringUtils.isBlank(pTokens[0])) {
			
			final String cleOrdreChampVide
			= "descriptionchampdarwincsv.lirechamp.ordrechampvide";

			final String messageOrdreChampVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens[1] (intitule) 
	 * n'est pas renseigné.<br/>
	 */
	private void traiterTokensIntituleNonRenseigne(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (StringUtils.isBlank(pTokens[1])) {
			
			final String cleintituleeVide
			= "descriptionchampdarwincsv.lirechamp.intitulevide";

			final String messageIntituleVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens[3] (champJava) 
	 * n'est pas renseigné.<br/>
	 */
	private void traiterTokensChampJavaNonRenseigne(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (StringUtils.isBlank(pTokens[3])) {
			
			final String cleChampJavaVide
			= "descriptionchampdarwincsv.lirechamp.champjavavide";

			final String messageChampJavaVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens[4] (typeJava) 
	 * n'est pas renseigné.<br/>
	 */
	private void traiterTokensTypeJavaNonRenseigne(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (StringUtils.isBlank(pTokens[4])) {
			
			final String cleTypeJavaVide
			= "descriptionchampdarwincsv.lirechamp.typejavavide";

			final String messageTypeJavaVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : pTokens[5] (aNomenclature) 
	 * n'est pas renseigné.<br/>
	 */
	private void traiterTokensANomenclatureNonRenseigne(
			final String[] pTokens) 
				throws ExceptionImport {
		
		if (StringUtils.isBlank(pTokens[5])) {
			
			final String cleANomenclatureVide
			= "descriptionchampdarwincsv.lirechamp.anomenclaturevide";

			final String messageANomenclatureVide
			= ConfigurationApplicationManager.getBundleMessagesTechniques()
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
				this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque :<br/> 
	 * - pTokens[0] (ordreChamps) n'est pas renseigné.<br/>
	 * - pTokens[0] (ordreChamps) n'est pas homogène à un entier.<br/>
	 */
	private String recupererOrdreChamps(
			final String[] pTokens) throws ExceptionImport {
		
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
					this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
					this.rapportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * stocké dans messages_techniques.properties.<br/>
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
	 * "Description des champs d'un fichier Darwin csv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier Darwin csv";
	} // Fin de getNomDescriptionChamp().__________________________________
	
	
	
} // FIN DE LA CLASSE DescriptionChampDarwinCsv.-----------------------------
