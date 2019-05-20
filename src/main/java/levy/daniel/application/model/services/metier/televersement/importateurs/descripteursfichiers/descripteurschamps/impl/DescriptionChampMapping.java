package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;
import java.util.TreeMap;
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
 * class DescriptionChampMapping :<br/>
 * <p>
 * IMPLEMENTATION de AbstractDescriptionChampCsv chargée
 * de stocker la définition d'un champ de fichier de
 * Mapping Histonat-HIT-Darwin-ISIDOR (écrit en csv avc séparateur ';').<br/>
 * </p>
 * 
 * <p>
 * Un DescriptionChampMapping "sait" qu'une description 
 * d'un Mapping Histonat-HIT-Darwin-ISIDOR formatée en csv (';') 
 * doit être ordonnée comme suit :<br/>
 * [ordreChampsHistonat, intituleHistonat, champJavaHistonat, colonneDebut, colonneFin, longueurCalculee, ordreChampsHit, intituleHit, champJavaHit, ordreChampsDarwin, intituleDarwin, champJavaDarwin, baliseIsidor, isLocalisant].<br/>
 * Il stocke cette liste ordonnée de champs dans sa map triée 
 * 'entetesDescriptionMap' dès sa construction.
 * </p>
 * 
 * <br/>
 * Un DescriptionChampMapping ne "connait" les valeurs 
 * décrivant un champ donné
 * qu'après l'execution de sa méthode lireChamps(String[] pTokens) 
 * où pTokens représente toutes les valeurs de la description 
 * du champ sous forme de tableau de String.<br/>
 * <code>public static final String[] PRD = {"16", "P.R.Origine", "prD", "50", "52", "3", "16", "P.R. Origine", "prOrigine", "5", "PR Origine", "prd", "prD", "localisation"};</code><br/>
 * <code>descripteur.lireChamps(PRD);</code><br/>
 * <br/>
 * Un fichier de description d'un Mapping Histonat-HIT-Darwin-ISIDOR formatée en csv (';') 
 * commence par :<br/>
 * <br/>
 * ordreChampsHistonat;intituleHistonat;champJavaHistonat;colonneDebut;colonneFin;longueurCalculee;ordreChampsHit;intituleHit;champJavaHit;ordreChampDarwin;intituleDarwin;champJavaDarwin;baliseIsidor;isLocalisant;<br/>
 * 1;Numéro de Département;numDepartement;1;3;3;1;Numéro de Département;numDepartement;null;null;null;numDepartement;metier;<br/>
 * 2;Numéro de Section ;numSectionTrafic;4;9;6;2;Numéro de Section;numSection;12;Numéro de la Section de Trafic;numSectionTrafic;numSectionTrafic;metier;<br/>
 * 3;Sens;sensSectionTrafic;10;10;1;3;Sens;sens;13;Sens de la Section de Trafic;sensSectionTrafic;sensSectionTrafic;metier;<br/>
 * 4;Nature ;nature;11;11;1;4;Nature;nature;null;null;null;nature;metier;<br/>
 * 5;Classe;classe;12;13;2;5;Classe;classe;null;null;null;classe;metier;<br/>
 * 6;Année de traitement;anneeMesureTrafic;14;15;2;6;Année de traitement;anneeTraitement;11;Année de la mesure de trafic;anneeMesureTrafic;anneeMesureTrafic;metier;<br/>
 * 7;Zone libre;sans objet;16;16;1;7;Zone libre;sans objet;null;null;null;sans objet;sans objet;<br/>
 * 8;Route;route;17;23;7;8;Route;route;2;route;route;route;metier;<br/>
 * ......................................................................<br/>
 * 
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe des DescripteursChamp : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/descripteurschamps/diagramme_de_classes_DescripteurChamp_1.png" 
 * alt="Diagramme de classe des DescripteursChamp" /><br/>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/descripteurschamps/diagramme_de_classes_DescripteurChamp_2.png" 
 * alt="Diagramme de classe des DescripteursChamp" />
 * </p>
 * 
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
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
 * @since 6 oct. 2011
 *
 */
public class DescriptionChampMapping extends AbstractDescriptionChampCsv {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_DESCRIPTIONCHAMPMAPPING : String : <br/>
	 * "CLASSE DescriptionChampMapping - ".<br/>
	 */
	public static final String CLASSE_DESCRIPTIONCHAMPMAPPING
		= "CLASSE DescriptionChampMapping - ";
	
	
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
	 * ordreChampsHisto : Integer :<br/>
	 * Ordre du champ (ligne) dans la description du HistonatF07.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HistonatF07.<br/>
	 */
	private Integer ordreChampsHisto;
	
		
	/**
	 * intitule : String :<br/>
	 * Intitulé du champ dans la description du HistonatF07
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 */
	private String intitule;

	
	/**
	 * champJavaHisto : String :<br/>
	 * Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 */
	private String champJavaHisto;
	
		
	/**
	 * colonneDebut : Integer :<br/>
	 * Colonne de début du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 */
	private Integer colonneDebut;
	
	
	/**
	 * colonneFin : Integer :<br/>
	 * Colonne de fin du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 */
	private Integer colonneFin;

		
	/**
	 * longueurCalculee : Integer :<br/>
	 * calcul : colonneFin - colonneDebut.<br/>
	 */
	private Integer longueurCalculee;
	

	/**
	 * ordreChampsHit : Integer :<br/>
	 * Ordre du champ (ligne) dans la description du HIT.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HIT.<br/>
	 */
	private Integer ordreChampsHit;
	
	
	/**
	 * intituleHit : String :<br/>
	 * Intitulé du champ dans la description du HIT
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 */
	private String intituleHit;
	
	
	/**
	 * champJavaHit : String :<br/>
	 * Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 */
	private String champJavaHit;
	
	

	/**
	 * ordreChampsDarwin : Integer :<br/>
	 * Ordre du champ (ligne) dans la description du Darwin.csv.<br/>
	 * Par exemple, 'route' est le 
	 * deuxième champ dans la description du Darwin.csv.<br/>
	 */
	private Integer ordreChampsDarwin;
	
	
	/**
	 * intituleDarwin : String :<br/>
	 * Intitulé du champ dans la description du Darwin.csv
	 * comme 'route'.<br/>
	 */
	private String intituleDarwin;
	
	
	/**
	 * champJavaDarwin : String :<br/>
	 * Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 */
	private String champJavaDarwin;

	
	/**
	 * baliseIsidor : String :<br/>
	 * Nom de la balise dans ISIDOR.<br/>
	 */
	private String baliseIsidor;
	
	
	/**
	 * isLocalisant : Boolean :<br/>
	 * Boolean qui précise si le champ participe à la localisation 
	 * ou est une donnée métier.<br/>
	 */
	private Boolean isLocalisant;
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(DescriptionChampMapping.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR DescriptionChampMapping() :<br/>
	 * <br/>
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un DescriptionChampMapping en instanciant et alimentant
	 * la Map&lt;Integer, String&gt; 'entetesDescriptionMap'
	 * qui décrit les en-têtes de colonne directement en son sein.<br/>
	 * <br/>
	 * - Alimente le 'nombreColonnesObligatoires', c'est à dire
	 * les colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier de Mapping Histonat-HIT-Darwin-ISIDOR.<br/>
	 * <br/>
	 * {ordreChampsHistonat;intituleHistonat;champJavaHistonat;
	 * colonneDebut;colonneFin;longueurCalculee;
	 * ordreChampsHit;intituleHit;champJavaHit;
	 * ordreChampDarwin;intituleDarwin;champJavaDarwin;
	 * baliseIsidor;isLocalisant;}<br/>
	 * 
	 * @throws Exception 
	 */
	public DescriptionChampMapping() throws Exception {
		
		super();
		
		/* Instanciation de la Map décrivant les champs
		 * dans la définition du Mapping. */
		this.entetesDescriptionMap = new TreeMap<Integer, String>();
		
		/* Remplissage de la Map. ***********/
		this.entetesDescriptionMap.put(1, "ordreChampsHistonat");
		this.entetesDescriptionMap.put(2, "intituleHistonat");
		this.entetesDescriptionMap.put(3, "champJavaHistonat");
		this.entetesDescriptionMap.put(4, "colonneDebut");
		this.entetesDescriptionMap.put(5, "colonneFin");
		this.entetesDescriptionMap.put(6, "longueurCalculee");
		this.entetesDescriptionMap.put(7, "ordreChampsHit");
		this.entetesDescriptionMap.put(8, "intituleHit");
		this.entetesDescriptionMap.put(9, "champJavaHit");
		this.entetesDescriptionMap.put(10, "ordreChampsDarwin");
		this.entetesDescriptionMap.put(11, "intituleDarwin");
		this.entetesDescriptionMap.put(12, "champJavaDarwin");
		this.entetesDescriptionMap.put(13, "baliseIsidor");
		this.entetesDescriptionMap.put(14, "isLocalisant");
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 14;
		
	} // Fin de CONSTRUCTEUR DescriptionChampMapping().____________________
	
	
	
	/**
	 * method CONSTRUCTEUR DescriptionChampMapping(
	 * SortedMap&lt;Integer, String&gt; pColonnesDescriptionMap) :<br/>
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <br/>
	 * - Permet de construire un DescriptionChampMapping
	 * en lui passant la Map des colonnes de la description
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
	public DescriptionChampMapping(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
					throws Exception {
		
		super(pColonnesDescriptionMap);
		
		/* Alimentation du nombre de colonnes
		 * à fournir obligatoirement dans la description
		 * de fichier en entrée. */
		this.nombreColonnesObligatoires = 14;
		
	} // Fin de CONSTRUCTEUR DescriptionChampMapping(
	 // Map<Integer, String> pColonnesDescriptionMap)._____________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("DescriptionChampMapping [");
		
		if (this.ordreChampsHisto != null) {
			builder.append("ordreChampsHisto=");
			builder.append(this.ordreChampsHisto);
			builder.append(", ");
		}
		if (this.intitule != null) {
			builder.append("intitule=");
			builder.append(this.intitule);
			builder.append(", ");
		}
		if (this.champJavaHisto != null) {
			builder.append("champJavaHisto=");
			builder.append(this.champJavaHisto);
			builder.append(", ");
		}
		if (this.colonneDebut != null) {
			builder.append("colonneDebut=");
			builder.append(this.colonneDebut);
			builder.append(", ");
		}
		if (this.colonneFin != null) {
			builder.append("colonneFin=");
			builder.append(this.colonneFin);
			builder.append(", ");
		}
		if (this.longueurCalculee != null) {
			builder.append("longueurCalculee=");
			builder.append(this.longueurCalculee);
			builder.append(", ");
		}
		if (this.ordreChampsHit != null) {
			builder.append("ordreChampsHit=");
			builder.append(this.ordreChampsHit);
			builder.append(", ");
		}
		if (this.intituleHit != null) {
			builder.append("intituleHit=");
			builder.append(this.intituleHit);
			builder.append(", ");
		}
		if (this.champJavaHit != null) {
			builder.append("champJavaHit=");
			builder.append(this.champJavaHit);
			builder.append(", ");
		}
		if (this.ordreChampsDarwin != null) {
			builder.append("ordreChampsDarwin=");
			builder.append(this.ordreChampsDarwin);
			builder.append(", ");
		}
		if (this.intituleDarwin != null) {
			builder.append("intituleDarwin=");
			builder.append(this.intituleDarwin);
			builder.append(", ");
		}
		if (this.champJavaDarwin != null) {
			builder.append("champJavaDarwin=");
			builder.append(this.champJavaDarwin);
			builder.append(", ");
		}
		if (this.baliseIsidor != null) {
			builder.append("baliseIsidor=");
			builder.append(this.baliseIsidor);
			builder.append(", ");
		}
		if (this.isLocalisant != null) {
			builder.append("isLocalisant=");
			builder.append(this.isLocalisant);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________

	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void lireChamp(
			final String[] pTokens) 
				throws Exception {
		
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
		
		/* Instanciation de la Map des valeurs. */
		this.valeursDescriptionMap = new TreeMap<Integer, String>();
		
		/* Instanciation de la Map des longueurs. */
		this.longueursDescriptionMap = new TreeMap<Integer, Integer>();
		
		/* Lecture des Tokens : */				
		//* 1 - LECTURE DE L'ORDRE DES CHAMPS HISTONAT. ************/
		this.lireOrdreChampsHisto(pTokens);
		
		//* 2 - LECTURE DE 'INTITULE HISTONAT'. **************************/
		this.lireIntituleHisto(pTokens);
				
		//* 3 - LECTURE DE 'CHAMP JAVA HISTONAT'. *********************/
		this.lireChampJavaHisto(pTokens);
				
		//* 4 - LECTURE DE COLONNEDEBUT. ************/
		this.lireColonneDebut(pTokens);
				
		//* 5 - LECTURE DE COLONNEFIN. ************/
		this.lireColonneFin(pTokens);
		
		//* 6 - LECTURE DE LONGUEURCALCULEE. ************/
		this.lireLongueurCalculee(pTokens);
		
		//* 7 - LECTURE DE L'ORDRE DES CHAMPS HIT. ************/
		this.lireOrdreChampsHit(pTokens);
		
		//* 8 - LECTURE DE L'INTITULE HIT. ************/
		this.lireIntituleHit(pTokens);
				
		//* 9 - LECTURE DE CHAMP JAVA HIT. ************/
		this.lireChampJavaHit(pTokens);
				
		//* 10 - LECTURE DE L'ORDRE DES CHAMPS DARWIN. ************/
		this.lireOrdreChampsDarwin(pTokens);

		//* 11 - LECTURE DE L'INTITULE DARWIN. ************/
		this.lireIntituleDarwin(pTokens);
				
		//* 12 - LECTURE DE CHAMP JAVA DARWIN. ************/
		this.lireChampJavaDarwin(pTokens);
		
		//* 13 - LECTURE DE BALISE ISIDOR. ************/
		this.lireBaliseIsidor(pTokens);
				
		//* 14 - LECTURE DE ISLOCALISANT. ************/
		this.lireIsLocalisant(pTokens);
					
	} // Fin de lireChamp(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method lireOrdreChampsHisto(
	 * String[] pTokens) :<br/>
	 * - Récupère ordreChampsHisto 
	 * (pTokens[0]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut ordreChamps.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void lireOrdreChampsHisto(
			final String[] pTokens) 
					throws Exception {
		
		/* Si NON RENSEIGNE, exception, */
		this.traiterTokensOrdreChampsNonRenseigne(pTokens);
		
		String ordreChampsHistonatString = null;
		
		/* Sinon, CHAMP ORDRE DES CHAMPS HISTONAT RENSEIGNE. */
			
		String ordrechampNettoye = null;
		
		try {
			
			/* Nettoyage du champ (retrait des "",...). */
			 
			ordrechampNettoye = nettoyerString(pTokens[0]);
			
			/* Essaie de parser la String ordrechampNettoye 
			 * en int pour VERIFIER LE FORMAT. */
			Integer.parseInt(ordrechampNettoye);
			
			/* Passe pTokens[0] à la chaine
			 * si il est parsable en Integer. */
			ordreChampsHistonatString 
				= ordrechampNettoye;
			
			/* Passage aux atributs. */
			this.ordreChampsHisto 
			= Integer.parseInt(ordreChampsHistonatString);
						
		} 
		
		/* Si le contenu dans ordreChamps de la description
		 *  n'était pas homogène à un entier. */
		catch (NumberFormatException nfe) {
			
			final String message1 
			= CLASSE_DESCRIPTIONCHAMPMAPPING
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
				ordreChampsHistonatString 
					= matcherNombre.group(1);
				
				/* Passage des paramètres aux attributs. */
				this.ordreChampsHisto 
					= Integer.parseInt(ordreChampsHistonatString);
			
			/* ERREUR DE FORMAT. */
			} else {
				
				final String message 
				= CLASSE_DESCRIPTIONCHAMPMAPPING
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
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(1, ordreChampsHistonatString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(
				1, ordreChampsHistonatString.length());
		
	 // FIN DE LECTURE DE L'ORDRE DES CHAMPS HISTONAT. ************/

	} // Fin de lireOrdreChampsHisto(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * method lireIntituleHisto(
	 * String[] pTokens) :<br/>
	 * - Récupère intitule 
	 * (pTokens[1]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut intitule.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireIntituleHisto(
			final String[] pTokens) throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensIntituleHistoNonRenseigne(pTokens);
		
		String intituleHistonatString = null;
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		intituleHistonatString = pTokens[1];
		
		/* Ne nettoie pas. 
		 * Enlève juste d'éventuels guillemets.*/
		final String intituleNettoye 
			= StringUtils.remove(intituleHistonatString, "\"");
		
		/* Passage aux attributs. */
		this.intitule = intituleNettoye;
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(2, intituleNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(2, intituleNettoye.length());
		
	} // Fin de lireIntituleHisto(
	 // String[] pTokens)._________________________________________________
	

		
	/**
	 * method lireChampJavaHisto(
	 * String[] pTokens) :<br/>
	 * - Récupère champJavaHisto 
	 * (pTokens[2]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut champJavaHisto.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireChampJavaHisto(
			final String[] pTokens) 
				throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensChampJavaHistoNonRenseigne(pTokens);
		
		String champJavaHistonatString = null;
		
		/* Sinon, CHAMP RENSEIGNE. */
		champJavaHistonatString = pTokens[2];
		
		final String champJavaNettoye 
			= nettoyerString(champJavaHistonatString);
		
		/* Passage aux attributs. */
		this.champJavaHisto = champJavaNettoye;
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(3, champJavaNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(3, champJavaNettoye.length());
		
	} // Fin de lireChampJavaHisto(
	 // String[] pTokens)._________________________________________________
	

		
	/**
	 * method lireColonneDebut(
	 * String[] pTokens) :<br/>
	 * - Récupère colonneDebut 
	 * (pTokens[3]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut colonneDebut.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireColonneDebut(final String[] pTokens) 
			throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensColonneDebutNonRenseigne(pTokens);
		
		/* Sinon, CHAMP COLONNEDEBUT RENSEIGNE. */
		String colonneDebutString = null;			
		String colonneDebutNettoye = null;
		
		try {
			
			/* Nettoyage du champ (retrait des "",...). */
			 
			colonneDebutNettoye = nettoyerString(pTokens[3]);
			
			/* Essaie de parser la String colonneDebutNettoye 
			 * en int pour VERIFIER LE FORMAT. */
			Integer.parseInt(colonneDebutNettoye);
			
			/* Passe colonneDebutNettoye à la chaine
			 * si il est parsable en Integer. */
			colonneDebutString 
				= colonneDebutNettoye;
			
			
		} catch (NumberFormatException nfe) {
			
			/* Rapport événtuel. */
			if (this.logDescription) {
				
				final String message = colonneDebutNettoye 
				+ " n'était pas parsable en Integer.";
				
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
				
			} // Fin de rapport éventuel.__________________
			
			/*Si pTokens[3] n'était pas parsable en int,
			 * essaie d'identifier le nombre. */
			
			/* MOTIF recherchant les nombres n'importe où
			 * au milieu d'une chaine.*/
			final String motif = ".*?(\\d+).*?";
			final Pattern pattern 
				= Pattern.compile(motif);
			final Matcher matcherNombre 
				= pattern.matcher(colonneDebutNettoye);
			
			if (matcherNombre.matches()) {
				
				/* Récupération de la chaine qui matche. */
				colonneDebutString 
					= matcherNombre.group(1);
							
			/* ERREUR DE FORMAT. */
			} else {
				
				final String message 
				= CLASSE_DESCRIPTIONCHAMPMAPPING 
				+ METHODE_LIRECHAMP
				+ "colonneDebut n'est pas parsable "
				+ "en nombre : " + pTokens[3];
				
				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(
							message);
					this.rapportDescriptionStb.append(NEWLINE);
				}
				
				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(
					message, nfe);
				
			} // Fin de l'erreur de format._____________
			
		} // Fin de NumberFormatException.__________________________
		
		/* Passage aux attributs. */
		this.colonneDebut = Integer.parseInt(colonneDebutString);
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(4, colonneDebutString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(
				4, colonneDebutString.length());
		
	} // Fin de lireColonneDebut(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * method lireColonneFin(
	 * String[] pTokens) :<br/>
	 * - Récupère colonneFin 
	 * (pTokens[4]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut colonneFin.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireColonneFin(final String[] pTokens) 
			throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensColonneFinNonRenseigne(pTokens);
		
		/* Sinon, CHAMP COLONNEFIN RENSEIGNE. */
		String colonneFinString = null;			
		String colonneFinNettoye = null;
		
		try {
			
			/* Nettoyage du champ (retrait des "",...). */
			 
			colonneFinNettoye = nettoyerString(pTokens[4]);
			
			/* Essaie de parser la String colonneFinNettoye 
			 * en int pour VERIFIER LE FORMAT. */
			Integer.parseInt(colonneFinNettoye);
			
			/* Passe colonneFinNettoye à la chaine
			 * si il est parsable en Integer. */
			colonneFinString 
				= colonneFinNettoye;
			
			
		} catch (NumberFormatException nfe) {
			
			/* Rapport événtuel. */
			if (this.logDescription) {
				
				final String message = colonneFinNettoye 
				+ " n'était pas parsable en Integer.";
				
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
				
			} // Fin de rapport éventuel.__________________
			
			/*Si pTokens[4] n'était pas parsable en int,
			 * essaie d'identifier le nombre. */
			
			/* MOTIF recherchant les nombres n'importe où
			 * au milieu d'une chaine.*/
			final String motif = ".*?(\\d+).*?";
			final Pattern pattern 
				= Pattern.compile(motif);
			final Matcher matcherNombre 
				= pattern.matcher(colonneFinNettoye);
			
			if (matcherNombre.matches()) {
				
				/* Récupération de la chaine qui matche. */
				colonneFinString 
					= matcherNombre.group(1);
			
			/* ERREUR DE FORMAT. */
			} else {
				
				final String message 
				= CLASSE_DESCRIPTIONCHAMPMAPPING 
				+ METHODE_LIRECHAMP
				+ "colonneFin n'est pas parsable "
				+ "en nombre : " + pTokens[4];
				
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
		
		/* Passage aux attributs. */
		this.colonneFin = Integer.parseInt(colonneFinString);
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(5, colonneFinString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(
				5, colonneFinString.length());

	} // Fin de lireColonneFin(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * method lireLongueurCalculee(
	 * String[] pTokens) :<br/>
	 * - Récupère longueurCalculee 
	 * (pTokens[5]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Calcule la longueur réelle grâce à colonneDebut
	 * et colonneFin et compare à longueurCalculee fournie. 
	 * Remplace la longueur fournie par la longueur réelle le cas échéant<br/>
	 * - Alimente directement l'attribut longueurCalculee.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireLongueurCalculee(
			final String[] pTokens) throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensLongueurCalculeeNonRenseigne(pTokens);
		
		/* Sinon, CHAMP LONGUEURCALCULEE RENSEIGNE. */
		String longueurCalculeeString = null;
		
		/* Calcul de la longueur réelle. */
		final int longueurReelleInt 
			= this.colonneFin - this.colonneDebut + 1;
		
		String longueurNettoye = null;
		
		try {
			
			/* Nettoyage du champ (retrait des "",...). */			 
			longueurNettoye = nettoyerString(pTokens[5]);
			
			/* Essaie de parser la String longueurNettoye 
			 * en int pour VERIFIER LE FORMAT. */
			Integer.parseInt(longueurNettoye);
			
			/* Passe longueurNettoye à la chaine
			 * si il est parsable en Integer. */
			longueurCalculeeString 
				= longueurNettoye;
			
			/* VERIFICATION DE LA BONNE LONGUEUR. */
			if (longueurReelleInt 
					!= Integer.parseInt(longueurCalculeeString)) {
				
				final String message 
				= "La valeur de longueur fournie (" 
					+ longueurCalculeeString 
					+ ") ne correspond pas à la valeur " 
					+ "calculée par le programme : " 
					+ longueurReelleInt
					+ "\nLa longueur fournie a été " 
					+ "remplacée par la valeur calculée.";
				
				/* Rapport événtuel. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
				} // Fin de rapport éventuel.__________________
				
				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Remplacement de la longueur 
				 * par la longueur calculée.*/
				longueurCalculeeString 
					= String.valueOf(longueurReelleInt);
				
			} // Fin de différence de longueur._______________
			
			
		} catch (NumberFormatException nfe) {
			
			/* Rapport événtuel. */
			if (this.logDescription) {
				
				final String message = longueurNettoye 
				+ " n'était pas parsable en Integer.";
				
				this.rapportDescriptionStb.append(message);
				this.rapportDescriptionStb.append(NEWLINE);
				
			} // Fin de rapport éventuel.__________________
			
			/*Si pTokens[5] n'était pas parsable en int,
			 * essaie d'identifier le nombre. */
			
			/* MOTIF recherchant les nombres n'importe où
			 * au milieu d'une chaine.*/
			final String motif = ".*?(\\d+).*?";
			final Pattern pattern 
				= Pattern.compile(motif);
			final Matcher matcherNombre 
				= pattern.matcher(longueurNettoye);
			
			if (matcherNombre.matches()) {
				
				/* Récupération de la chaine qui matche. */
				longueurCalculeeString 
					= matcherNombre.group(1);
			
			/* ERREUR DE FORMAT. */
			} else {
				
				final String message 
				= CLASSE_DESCRIPTIONCHAMPMAPPING 
				+ METHODE_LIRECHAMP
				+ "longueurCalculee n'est pas parsable "
				+ "en nombre : " + pTokens[5];
				
				/* Logge. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				/* Rapport d'erreur. */
				if (this.logDescription) {
					this.rapportDescriptionStb.append(
							message);
					this.rapportDescriptionStb.append(NEWLINE);
				}
				
				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(
					message, nfe);
				
			} // Fin de l'erreur de format._____________
			
		} // Fin de NumberFormatException.__________________________
		
		/* Passage aux attributs */
		this.longueurCalculee = Integer.parseInt(longueurCalculeeString);
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(6, longueurCalculeeString);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(
				6, longueurCalculeeString.length());
		
	} // Fin de lireLongueurCalculee(
	 // String[] pTokens)._________________________________________________
	


	/**
	 * method lireOrdreChampsHit(
	 * String[] pTokens) :<br/>
	 * - Récupère ordreChampsHit 
	 * (pTokens[6]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut ordreChampsHit.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 * 
	 * @throws ExceptionImport lorsque : <br/>
	 * - pTokens[6] (ordreChampsHit) n'est pas homogène à un entier.<br/>
	 */
	private void lireOrdreChampsHit(
			final String[] pTokens) throws ExceptionImport {
		
		String ordreChampsHitString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[6]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[6])) {
			
			/* Passage aux attributs. */
			this.ordreChampsHit = null;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(7, ordreChampsHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					7, 0);
			
		}
		/* Sinon, CHAMP ORDRE DES CHAMPS HIT RENSEIGNE. */
		else {
			
			String ordrechampHitNettoye = null;
			
			try {
				
				/* Nettoyage du champ (retrait des "",...). */
				 
				ordrechampHitNettoye = nettoyerString(pTokens[6]);
				
				/* Essaie de parser la String ordrechampHitNettoye 
				 * en int pour VERIFIER LE FORMAT. */
				Integer.parseInt(ordrechampHitNettoye);
				
				/* Passe pTokens[6] à la chaine
				 * si il est parsable en Integer. */
				ordreChampsHitString 
					= ordrechampHitNettoye;
				
				
			} catch (NumberFormatException nfe) {
				
				/* Rapport événtuel. */
				if (this.logDescription) {
					
					final String message = ordrechampHitNettoye 
					+ " n'était pas parsable en Integer.";
					
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
					
				} // Fin de rapport éventuel.__________________
				
				/*Si pTokens[6] n'était pas parsable en int,
				 * essaie d'identifier le nombre. */
				
				/* MOTIF recherchant les nombres n'importe où
				 * au milieu d'une chaine.*/
				final String motif = ".*?(\\d+).*?";
				final Pattern pattern 
					= Pattern.compile(motif);
				final Matcher matcherNombre 
					= pattern.matcher(ordrechampHitNettoye);
				
				if (matcherNombre.matches()) {
					
					/* Récupération de la chaine qui matche. */
					ordreChampsHitString 
						= matcherNombre.group(1);
				
				/* ERREUR DE FORMAT. */
				} else {
					
					final String message 
					= CLASSE_DESCRIPTIONCHAMPMAPPING 
					+ METHODE_LIRECHAMP
					+ "l'ordre des champs HIT n'est pas parsable "
					+ "en nombre : " + pTokens[6];
					
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
			
			/* Passage aux attributs. */
			this.ordreChampsHit = Integer.parseInt(ordreChampsHitString);
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(7, ordreChampsHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					7, ordreChampsHitString.length());
			
		} // FIN DE LECTURE DE L'ORDRE DES CHAMPS HIT. ************/
		
	} // Fin de lireOrdreChampsHit(
	 // String[] pTokens)._________________________________________________
	

	
	
	/**
	 * method lireIntituleHit(
	 * String[] pTokens) :<br/>
	 * - Récupère intituleHit 
	 * (pTokens[7]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut intituleHit.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 */
	private void lireIntituleHit(
			final String[] pTokens) {
		
		String intituleHitString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[7]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[7])) {
			
			/* Passage aux attributs. */
			this.intituleHit = intituleHitString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(8, intituleHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					8, 0);
			
		}
		/* Sinon, CHAMP INTITULE HIT RENSEIGNE. */
		else {
			
			intituleHitString = pTokens[7];
			
			/* Passage aux attributs. */
			this.intituleHit = intituleHitString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(8, intituleHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					8, intituleHitString.length());
			
		} // FIN DE LECTURE DE INTITULE HIT. ************/
		
	} // Fin de lireIntituleHit(
	 // String[] pTokens)._________________________________________________
	

		
	/**
	 * method lireChampJavaHit(
	 * String[] pTokens) :<br/>
	 * - Récupère champJavaHit 
	 * (pTokens[8]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut champJavaHit.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 */
	private void lireChampJavaHit(final String[] pTokens) {
		
		String champJavaHitString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[8]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[8])) {
			
			/* Passage aux attributs. */
			this.champJavaHit = champJavaHitString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(9, champJavaHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					9, 0);
			
		}
		/* Sinon, CHAMP INTITULE HIT RENSEIGNE. */
		else {
			
			champJavaHitString = pTokens[8];
			
			/* Passage aux attributs. */
			this.champJavaHit = champJavaHitString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(9, champJavaHitString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					9, champJavaHitString.length());
			
		} // FIN DE LECTURE DE CHAMP JAVA HIT. ************/

	} // Fin de lireChampJavaHit(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * method lireOrdreChampsDarwin(
	 * String[] pTokens) :<br/>
	 * - Récupère ordreChampsDarwin 
	 * (pTokens[9]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut ordreChampsDarwin.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws ExceptionImport lorsque : <br/>
	 * - pTokens[9] (ordreChampsDarwin) n'est pas homogène à un entier.<br/>
	 */
	private void lireOrdreChampsDarwin(
			final String[] pTokens) throws ExceptionImport {
		
		String ordreChampsDarwinString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[9]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[9])) {
			
			/* Passage aux attributs. */
			this.ordreChampsDarwin = null;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(10, ordreChampsDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					10, 0);
			
		}
		/* Sinon, CHAMP ORDRE DES CHAMPS HIT RENSEIGNE. */
		else {
			
			String ordrechampsDarwinNettoye = null;
			
			try {
				
				/* Nettoyage du champ (retrait des "",...). */
				 ordrechampsDarwinNettoye = nettoyerString(pTokens[9]);
				
				/* Essaie de parser la String ordrechampsDarwinNettoye 
				 * en int pour VERIFIER LE FORMAT. */
				Integer.parseInt(ordrechampsDarwinNettoye);
				
				/* Passe pTokens[9] à la chaine
				 * si il est parsable en Integer. */
				ordreChampsDarwinString 
					= ordrechampsDarwinNettoye;
				
				
			} catch (NumberFormatException nfe) {
				
				/* Rapport événtuel. */
				if (this.logDescription) {
					
					final String message = ordrechampsDarwinNettoye 
					+ " n'était pas parsable en Integer.";
					
					this.rapportDescriptionStb.append(message);
					this.rapportDescriptionStb.append(NEWLINE);
					
				} // Fin de rapport éventuel.__________________
				
				/*Si pTokens[9] n'était pas parsable en int,
				 * essaie d'identifier le nombre. */
				
				/* MOTIF recherchant les nombres n'importe où
				 * au milieu d'une chaine.*/
				final String motif = ".*?(\\d+).*?";
				final Pattern pattern 
					= Pattern.compile(motif);
				final Matcher matcherNombre 
					= pattern.matcher(ordrechampsDarwinNettoye);
				
				if (matcherNombre.matches()) {
					
					/* Récupération de la chaine qui matche. */
					ordreChampsDarwinString 
						= matcherNombre.group(1);
				
				/* ERREUR DE FORMAT. */
				} else {
					
					final String message
					= CLASSE_DESCRIPTIONCHAMPMAPPING 
					+ METHODE_LIRECHAMP
					+ "l'ordre des champs DARWIN n'est pas parsable "
					+ "en nombre : " + pTokens[9];
					
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
			
			/* Passage aux attributs. */
			this.ordreChampsDarwin = Integer.parseInt(ordreChampsDarwinString);
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(10, ordreChampsDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					10, ordreChampsDarwinString.length());
			
		} // FIN DE LECTURE DE L'ORDRE DES CHAMPS DARWIN. ************/

	} // Fin de lireOrdreChampsDarwin(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method lireIntituleDarwin(
	 * String[] pTokens) :<br/>
	 * - Récupère intituleDarwin 
	 * (pTokens[10]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut intituleDarwin.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 */
	private void lireIntituleDarwin(
			final String[] pTokens) {
		
		String intituleDarwinString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[10]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[10])) {
			
			/* Passage aux attributs. */
			this.intituleDarwin = null;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(11, intituleDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					11, 0);
			
		}
		/* Sinon, CHAMP INTITULE DARWIN RENSEIGNE. */
		else {
			
			intituleDarwinString = pTokens[10];
			
			/* Passage aux attributs. */
			this.intituleDarwin = intituleDarwinString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(11, intituleDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					11, intituleDarwinString.length());
			
		} // FIN DE LECTURE DE INTITULE DARWIN. ************/
		
	} // Fin de lireIntituleDarwin(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * method lireChampJavaDarwin(
	 * String[] pTokens) :<br/>
	 * - Récupère champJavaDarwin 
	 * (pTokens[11]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut champJavaDarwin.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 */
	private void lireChampJavaDarwin(final String[] pTokens) {
		
		String champJavaDarwinString = null;
		
		/* Si NON RENSEIGNE, null */
		if (StringUtils.isBlank(pTokens[11]) 
				|| StringUtils.equalsIgnoreCase("null", pTokens[11])) {
			
			/* Passage aux attributs. */
			this.champJavaDarwin = null;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(12, champJavaDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					12, 0);
			
		}
		/* Sinon, CHAMP INTITULE DARWIN RENSEIGNE. */
		else {
			
			champJavaDarwinString = pTokens[11];
			
			/* Passage aux attributs. */
			this.champJavaDarwin = champJavaDarwinString;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(12, champJavaDarwinString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					12, champJavaDarwinString.length());
			
		} // FIN DE LECTURE DE CHAMP JAVA DARWIN. ************/

	} // Fin de lireChampJavaDarwin(
	 // String[] pTokens)._________________________________________________
	

		
	/**
	 * method lireBaliseIsidor(
	 * String[] pTokens) :<br/>
	 * - Récupère baliseIsidor 
	 * (pTokens[12]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut baliseIsidor.<br/>
	 * <br/>
	 *
	 *@param pTokens : String[].<br/>
	 *
	 * @throws Exception 
	 */
	private void lireBaliseIsidor(
			final String[] pTokens) throws Exception {
		
		/* Si NON RENSEIGNE, exception. */
		this.traiterTokensBaliseIsidorNonRenseigne(pTokens);
		
		/* Sinon, CHAMP RENSEIGNE. */
		/* Insertion dans la Map des Valeurs. */
		String baliseIsidorString = null;
		
		baliseIsidorString = pTokens[12];
		
		/* Ne nettoie pas. 
		 * Enlève juste d'éventuels guillemets.*/
		final String baliseIsidorNettoye 
			= StringUtils.remove(baliseIsidorString, "\"");
		
		/* Passage aux attributs */
		this.baliseIsidor = baliseIsidorNettoye;
		
		/* Insertion dans la Map des Valeurs. */
		this.valeursDescriptionMap.put(13, baliseIsidorNettoye);
		
		/* Insertion dans la Map des longueurs. */
		this.longueursDescriptionMap.put(13, baliseIsidorNettoye.length());
		// FIN DE LECTURE DE 'BALISE ISIDOR'. ************/
		
	} // Fin de lireBaliseIsidor(
	 // String[] pTokens)._________________________________________________

	
	
	/**
	 * method lireIsLocalisant(
	 * String[] pTokens) :<br/>
	 * - Récupère isLocalisant 
	 * (pTokens[13]) dans le tableau de Tokens 
	 * de la description.<br/>
	 * - Alimente directement l'attribut isLocalisant.<br/>
	 * <br/>
	 * - met null dans isLocalisant et null dans this.valeursDescriptionMap 
	 * si pTokens[13] est blank.<br/>
	 * - met true dans isLocalisant et "true" dans this.valeursDescriptionMap 
	 * si pTokens[13] vaut 'true' ou 'localisation'.<br/>
	 * - met false dans isLocalisant et "false" dans this.valeursDescriptionMap 
	 * si pTokens[13] vaut 'false' ou 'metie'r.<br/>
	 * - met null dans isLocalisant et la chaîne "null" 
	 * dans this.valeursDescriptionMap 
	 * dans tous les autres cas.<br/>
	 *
	 *@param pTokens : String[].<br/>
	 */
	private void lireIsLocalisant(
			final String[] pTokens) {
		
		String isLocalisantString = null;
		
		/* Si NON RENSEIGNE, null */		
		if (StringUtils.isBlank(pTokens[13])) {
			
			/* Passage aux attributs. */
			this.isLocalisant = null;
			
			/* Insertion dans la Map des Valeurs. */
			this.valeursDescriptionMap.put(14, isLocalisantString);
			
			/* Insertion dans la Map des longueurs. */
			this.longueursDescriptionMap.put(
					14, 0);
			
		}
		/* Sinon, CHAMP INTITULE HIT RENSEIGNE. */
		else {
			
			String isLocalisantStringCalcule = null;
			
			isLocalisantString = pTokens[13];
			
			if (StringUtils.equalsIgnoreCase(
					"localisation", isLocalisantString)) {
				
				isLocalisantStringCalcule = "true";
				
				/* Passage aux attributs. */
				this.isLocalisant = true;
								
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(14, isLocalisantStringCalcule);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(
						14, isLocalisantStringCalcule.length());
			}
			else if (StringUtils.equalsIgnoreCase("true", isLocalisantString)) {
				
				isLocalisantStringCalcule = "true";
				
				/* Passage aux attributs. */
				this.isLocalisant = true;
								
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(14, isLocalisantStringCalcule);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(
						14, isLocalisantStringCalcule.length());
			}
			else if (StringUtils.equalsIgnoreCase("metier", isLocalisantString)) {
				
				isLocalisantStringCalcule = "false";
				
				/* Passage aux attributs. */
				this.isLocalisant = false;
								
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(14, isLocalisantStringCalcule);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(
						14, isLocalisantStringCalcule.length());
			}
			else if (StringUtils.equalsIgnoreCase("false", isLocalisantString)) {
				
				isLocalisantStringCalcule = "false";
				
				/* Passage aux attributs. */
				this.isLocalisant = false;
								
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(14, isLocalisantStringCalcule);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(
						14, isLocalisantStringCalcule.length());
			}
			
			else {
				
				isLocalisantStringCalcule = "null";
				
				/* Passage aux attributs. */
				this.isLocalisant = null;
								
				/* Insertion dans la Map des Valeurs. */
				this.valeursDescriptionMap.put(14, isLocalisantStringCalcule);
				
				/* Insertion dans la Map des longueurs. */
				this.longueursDescriptionMap.put(
						14, isLocalisantStringCalcule.length());
			}
			
			
		} // FIN DE LECTURE DE ISLOCALISANT. ************/
		
	} // Fin de lireIsLocalisant(
	 // String[] pTokens)._________________________________________________
	
	
	
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
			= "descriptionchampmapping.lirechamp.tableaunull";

			final String messageTableauNull 
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauNull);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
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
			= "descriptionchampmapping.lirechamp.tableauvide";

			final String messageTableauVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
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
			= "descriptionchampmapping.lirechamp.tableautroppetit";

			final String messageTableauPetit
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleTableauPetit);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
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
			= "descriptionchampmapping.lirechamp.ordrechamphistonatvide";

			final String messageOrdreChampVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleOrdreChampVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING
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
	 * method traiterTokensIntituleHistoNonRenseigne(
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
	private void traiterTokensIntituleHistoNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[1])) {
			
			final String cleintituleeVide
			= "descriptionchampmapping.lirechamp.intitulehistonatvide";

			final String messageIntituleVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleintituleeVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
			+ METHODE_LIRECHAMP 
			+ messageIntituleVide 
			+ tokensToString(pTokens);

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logDescription) {
				this.rapportDescriptionStb.append(
						message);
				this.rapportDescriptionStb.append(NEWLINE);
			}

			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		}
		
	} // Fin de traiterTokensIntituleHistoNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method traiterTokensChampJavaHistoNonRenseigne(
	 * String[] pTokens) :<br/>
	 * - LOG.fatal si pTokens[2] (champJavaHisto) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[2] (champJavaHisto) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensChampJavaHistoNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		/* Si NON RENSEIGNE, LOG.fatal, rapporte et Exception. */
		if (StringUtils.isBlank(pTokens[2])) {
			
			final String cleChampJavaVide
			= "descriptionchampmapping.lirechamp.champjavahistonatvide";

			final String messageChampJavaVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleChampJavaVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
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
		}
		
	} // Fin de traiterTokensChampJavaHistoNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method traiterTokensColonneDebutNonRenseigne(
	 * String[] pTokens) :<br/>
	 * - LOG.fatal si pTokens[3] (colonneDebut) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[3] (colonneDebut) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensColonneDebutNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		/* Si NON RENSEIGNE, exception, */
		if (StringUtils.isBlank(pTokens[3])) {
			
			final String cleColonneDebutVide
			= "descriptionchampmapping.lirechamp.colonnedebutvide";

			final String messageColonneDebutVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleColonneDebutVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
			+ METHODE_LIRECHAMP 
			+ messageColonneDebutVide 
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
			
		}
		
	} // Fin de traiterTokensColonneDebutNonRenseigne(
	 // String[] pTokens)._________________________________________________
	

	
	/**
	 * method traiterTokensColonneFinNonRenseigne(
	 * String[] pTokens) :<br/>
	 * - LOG.fatal si pTokens[4] (colonneFin) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[4] (colonneFin) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensColonneFinNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		/* Si NON RENSEIGNE, exception, */
		if (StringUtils.isBlank(pTokens[4])) {
			
			final String cleColonneFinVide
			= "descriptionchampmapping.lirechamp.colonnefinvide";

			final String messageColonneFinVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(cleColonneFinVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
			+ METHODE_LIRECHAMP 
			+ messageColonneFinVide 
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
			
		}
		
	} // Fin de traiterTokensColonneFinNonRenseigne(
	 // String[] pTokens)._________________________________________________


	
	/**
	 * method traiterTokensLongueurCalculeeNonRenseigne(
	 * String[] pTokens) :<br/>
	 * - LOG.fatal si pTokens[5] (longueurCalculee) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[5] (longueurCalculee) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensLongueurCalculeeNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		/* Si NON RENSEIGNE, exception, */
		if (StringUtils.isBlank(pTokens[5])) {
			
			final String clelongueurVide
			= "descriptionchampmapping.lirechamp.longueurvide";

			final String messageLongueurVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(clelongueurVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
			+ METHODE_LIRECHAMP 
			+ messageLongueurVide 
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
			
		}
		
	} // Fin de traiterTokensLongueurCalculeeNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	

	/**
	 * method traiterTokensBaliseIsidorNonRenseigne(
	 * String[] pTokens) :<br/>
	 * - LOG.fatal si pTokens[12] (baliseIsidor) n'est pas renseigné.<br/>
	 * - Ajoute une ligne d'erreur dans this.rapportDescriptionStb.<br/>
	 * - Jette une ExceptionImport commentée si pTokens[12] (baliseIsidor) 
	 * n'est pas renseigné.<br/>
	 * <br/>
	 *
	 * @param pTokens : String[].<br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterTokensBaliseIsidorNonRenseigne(
			final String[] pTokens) 
				throws Exception {
		
		if (StringUtils.isBlank(pTokens[12])) {
			
			final String clebaliseVide
			= "descriptionchampmapping.lirechamp.balisevide";

			final String messageBaliseVide
			= ConfigurationApplicationManager.getBundleMessagesTechnique()
					.getString(clebaliseVide);

			final String message 
			= CLASSE_DESCRIPTIONCHAMPMAPPING 
			+ METHODE_LIRECHAMP 
			+ messageBaliseVide 
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
			
		}
		
	} // Fin de traiterTokensBaliseIsidorNonRenseigne(
	 // String[] pTokens)._________________________________________________
	
	
	
	/**
	 * method getOrdreChampsHisto() :<br/>
	 * Getter de l'Ordre du champ (ligne) 
	 * dans la description du HistonatF07.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @return ordreChampsHisto : Integer.<br/>
	 */
	public final Integer getOrdreChampsHisto() {
		return this.ordreChampsHisto;
	} // Fin de getOrdreChampsHisto()._____________________________________



	/**
	 * method setOrdreChampsHisto(
	 * Integer pOrdreChampsHisto) :<br/>
	 * Setter de l'Ordre du champ (ligne) 
	 * dans la description du HistonatF07.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HistonatF07.<br/>
	 * <br/>
	 *
	 * @param pOrdreChampsHisto : Integer : 
	 * valeur à passer à ordreChampsHisto.<br/>
	 */
	public final void setOrdreChampsHisto(
			final Integer pOrdreChampsHisto) {
		
		this.ordreChampsHisto = pOrdreChampsHisto;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					1, String.valueOf(this.ordreChampsHisto));
		}
		
	} // Fin de setOrdreChampsHisto(
	 // Integer pOrdreChampsHisto).________________________________________


	
	/**
	 * method getIntitule() :<br/>
	 * Getter de l' Intitulé du champ dans la description du HistonatF07
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 * <br/>
	 *
	 * @return intitule : String.<br/>
	 */
	@Override
	public final String getIntitule() {
		return this.intitule;
	} // Fin de getIntituleHisto().________________________________________



	/**
	 * method setIntitule(
	 * String pIntituleHisto) :<br/>
	 * Setter de l' Intitulé du champ dans la description du HistonatF07
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 * <br/>
	 *
	 * @param pIntituleHisto : String : 
	 * valeur à passer à intitule.<br/>
	 */
	@Override
	public final void setIntitule(
			final String pIntituleHisto) {
		
		this.intitule = pIntituleHisto;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					2, this.intitule);
		}
		
	} // Fin de setIntitule(
	 // String pIntituleHisto).____________________________________________


	
	/**
	 * method getChampJavaHisto() :<br/>
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return champJavaHisto : String.<br/>
	 */
	public final String getChampJavaHisto() {
		return this.champJavaHisto;
	} // Fin de getChampJavaHisto()._______________________________________



	/**
	 * method setChampJavaHisto(
	 * String pChampJavaHisto) :<br/>
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJavaHisto : String : 
	 * valeur à passer à champJavaHisto.<br/>
	 */
	public final void setChampJavaHisto(
			final String pChampJavaHisto) {
		
		this.champJavaHisto = pChampJavaHisto;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					3, this.champJavaHisto);
		}
		
	} // Fin de setChampJavaHisto(
	 // String pChampJavaHisto).___________________________________________



	/**
	 * method getColonneDebut() :<br/>
	 * Getter de la Colonne de début du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * <br/>
	 *
	 * @return colonneDebut : Integer.<br/>
	 */
	public final Integer getColonneDebut() {
		return this.colonneDebut;
	} // Fin de getColonneDebut()._________________________________________



	/**
	 * method setColonneDebut(
	 * Integer pColonneDebut) :<br/>
	 * Getter de la Colonne de début du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 4 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * <br/>
	 *
	 * @param pColonneDebut : Integer : 
	 * valeur à passer à colonneDebut.<br/>
	 */
	public final void setColonneDebut(
			final Integer pColonneDebut) {
		
		this.colonneDebut = pColonneDebut;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					4, String.valueOf(this.colonneDebut));
		}
		
	} // Fin de setColonneDebut(
	 // Integer pColonneDebut).____________________________________________



	/**
	 * method getColonneFin() :<br/>
	 * Getter de la Colonne de fin du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * <br/>
	 *
	 * @return colonneFin : Integer.<br/>
	 */
	public final Integer getColonneFin() {
		return this.colonneFin;
	} // Fin de getColonneFin().___________________________________________



	/**
	 * method setColonneFin(
	 * Integer pColonneFin) :<br/>
	 * Setter de la Colonne de fin du champ fournie dans la description 
	 * de fichier HistoNatF07 comme 9 pour 4-9 (dans colonnes) 
	 * par exemple.<br/>
	 * <br/>
	 *
	 * @param pColonneFin : Integer : 
	 * valeur à passer à colonneFin.<br/>
	 */
	public final void setColonneFin(
			final Integer pColonneFin) {
		
		this.colonneFin = pColonneFin;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					5, String.valueOf(this.colonneFin));
		}
		
	} // Fin de setColonneFin(
	 // Integer pColonneFin).______________________________________________



	/**
	 * method getLongueurCalculee() :<br/>
	 * Getter du calcul : colonneFin - colonneDebut.<br/>
	 * <br/>
	 *
	 * @return longueurCalculee : Integer.<br/>
	 */
	public final Integer getLongueurCalculee() {
		return this.longueurCalculee;
	} // Fin de getLongueurCalculee()._____________________________________



	/**
	 * method setLongueurCalculee(
	 * Integer pLongueurCalculee) :<br/>
	 * Setter du calcul : colonneFin - colonneDebut.<br/>
	 * <br/>
	 *
	 * @param pLongueurCalculee : Integer : 
	 * valeur à passer à longueurCalculee.<br/>
	 */
	public final void setLongueurCalculee(
			final Integer pLongueurCalculee) {
		
		this.longueurCalculee = pLongueurCalculee;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					6, String.valueOf(this.longueurCalculee));
		}
		
	} // Fin de setLongueurCalculee(
	 // Integer pLongueurCalculee).________________________________________



	/**
	 * method getOrdreChampsHit() :<br/>
	 * Getter de l'Ordre du champ (ligne) dans la description du HIT.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HIT.<br/>
	 * <br/>
	 *
	 * @return ordreChampsHit : Integer.<br/>
	 */
	public final Integer getOrdreChampsHit() {
		return this.ordreChampsHit;
	} // Fin de getOrdreChampsHit()._______________________________________



	/**
	 * method setOrdreChampsHit(
	 * Integer pOrdreChampsHit) :<br/>
	 * Setter de l'Ordre du champ (ligne) dans la description du HIT.<br/>
	 * Par exemple, 'route' est le 
	 * huitième champ dans la description du HIT.<br/>
	 * <br/>
	 *
	 * @param pOrdreChampsHit : Integer : 
	 * valeur à passer à ordreChampsHit.<br/>
	 */
	public final void setOrdreChampsHit(
			final Integer pOrdreChampsHit) {
		
		this.ordreChampsHit = pOrdreChampsHit;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					7, String.valueOf(this.ordreChampsHit));
		}
		
	} // Fin de setOrdreChampsHit(
	 // Integer pOrdreChampsHit).__________________________________________



	/**
	 * method getIntituleHit() :<br/>
	 * Getter de l'Intitulé du champ dans la description du HIT
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 * <br/>
	 *
	 * @return intituleHit : String.<br/>
	 */
	public final String getIntituleHit() {
		return this.intituleHit;
	} // Fin de getIntituleHit().__________________________________________



	/**
	 * method setIntituleHit(
	 * String pIntituleHit) :<br/>
	 * Setter de l'Intitulé du champ dans la description du HIT
	 * comme 'Numéro de Section' ou 'route'.<br/>
	 * <br/>
	 *
	 * @param pIntituleHit : String : valeur à passer à intituleHit.<br/>
	 */
	public final void setIntituleHit(
			final String pIntituleHit) {
		
		this.intituleHit = pIntituleHit;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					8, this.intituleHit);
		}
		
	} // Fin de setIntituleHit(
	 // String pIntituleHit).______________________________________________



	/**
	 * method getChampJavaHit() :<br/>
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return champJavaHit : String.<br/>
	 */
	public final String getChampJavaHit() {
		return this.champJavaHit;
	} // Fin de getChampJavaHit()._________________________________________



	/**
	 * method setChampJavaHit(
	 * String pChampJavaHit) :<br/>
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJavaHit : String : 
	 * valeur à passer à champJavaHit.<br/>
	 */
	public final void setChampJavaHit(
			final String pChampJavaHit) {
		
		this.champJavaHit = pChampJavaHit;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					9, this.champJavaHit);
		}
		
	} // Fin de setChampJavaHit(
	 // String pChampJavaHit)._____________________________________________



	/**
	 * method getOrdreChampsDarwin() :<br/>
	 * Getter de l'Ordre du champ (ligne) 
	 * dans la description du Darwin.csv.<br/>
	 * Par exemple, 'route' est le 
	 * deuxième champ dans la description du Darwin.csv.<br/>
	 * <br/>
	 *
	 * @return ordreChampsDarwin : Integer.<br/>
	 */
	public final Integer getOrdreChampsDarwin() {
		return this.ordreChampsDarwin;
	} // Fin de getOrdreChampsDarwin().____________________________________



	/**
	 * method setOrdreChampsDarwin(
	 * Integer pOrdreChampsDarwin) :<br/>
	 * Setter de l'Ordre du champ (ligne) 
	 * dans la description du Darwin.csv.<br/>
	 * Par exemple, 'route' est le 
	 * deuxième champ dans la description du Darwin.csv.<br/>
	 * <br/>
	 *
	 * @param pOrdreChampsDarwin : Integer : 
	 * valeur à passer à ordreChampsDarwin.<br/>
	 */
	public final void setOrdreChampsDarwin(
			final Integer pOrdreChampsDarwin) {
		
		this.ordreChampsDarwin = pOrdreChampsDarwin;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					10, String.valueOf(this.ordreChampsDarwin));
		}
		
	} // Fin de setOrdreChampsDarwin(
	 // Integer pOrdreChampsDarwin)._______________________________________



	/**
	 * method getIntituleDarwin() :<br/>
	 * Getter de l'Intitulé du champ dans la description du Darwin.csv
	 * comme 'route'.<br/>
	 * <br/>
	 *
	 * @return intituleDarwin : String.<br/>
	 */
	public final String getIntituleDarwin() {
		return this.intituleDarwin;
	} // Fin de getIntituleDarwin()._______________________________________



	/**
	 * method setIntituleDarwin(
	 * String pIntituleDarwin) :<br/>
	 * Setter de l'Intitulé du champ dans la description du Darwin.csv
	 * comme 'route'.<br/>
	 * <br/>
	 *
	 * @param pIntituleDarwin : String : valeur à passer à intituleDarwin.<br/>
	 */
	public final void setIntituleDarwin(
			final String pIntituleDarwin) {
		
		this.intituleDarwin = pIntituleDarwin;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					11, this.intituleDarwin);
		}
		
	} // Fin de setIntituleDarwin(
	 // String pIntituleDarwin).___________________________________________



	/**
	 * method getChampJavaDarwin() :<br/>
	 * Getter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @return champJavaDarwin : String.<br/>
	 */
	public final String getChampJavaDarwin() {
		return this.champJavaDarwin;
	} // Fin de getChampJavaDarwin().______________________________________



	/**
	 * method setChampJavaDarwin(
	 * String pChampJavaDarwin) :<br/>
	 * Setter du Nom du champ dans l'application comme numDepartement 
	 * pour 'Numéro de Département'.<br/>
	 * <br/>
	 *
	 * @param pChampJavaDarwin : String : 
	 * valeur à passer à champJavaDarwin.<br/>
	 */
	public final void setChampJavaDarwin(
			final String pChampJavaDarwin) {
		
		this.champJavaDarwin = pChampJavaDarwin;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					12, this.champJavaDarwin);
		}
		
	} // Fin de setChampJavaDarwin(
	 // String pChampJavaDarwin).__________________________________________



	/**
	 * method getBaliseIsidor() :<br/>
	 * Getter du Nom de la balise dans ISIDOR.<br/>
	 * <br/>
	 *
	 * @return baliseIsidor : String.<br/>
	 */
	public final String getBaliseIsidor() {
		return this.baliseIsidor;
	} // Fin de getBaliseIsidor()._________________________________________



	/**
	 * method setBaliseIsidor(
	 * String pBaliseIsidor) :<br/>
	 * Setter du Nom de la balise dans ISIDOR.<br/>
	 * <br/>
	 *
	 * @param pBaliseIsidor : String : 
	 * valeur à passer à baliseIsidor.<br/>
	 */
	public final void setBaliseIsidor(
			final String pBaliseIsidor) {
		
		this.baliseIsidor = pBaliseIsidor;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					13, this.baliseIsidor);
		}
		
	} // Fin de setBaliseIsidor(
	 // String pBaliseIsidor)._____________________________________________



	/**
	 * method getIsLocalisant() :<br/>
	 * Getter du Boolean qui précise si le champ participe à la localisation 
	 * ou est une donnée métier.<br/>
	 * <br/>
	 * true si participe à la Localisation.<br/>
	 * <br/>
	 *
	 * @return isLocalisant : Boolean.<br/>
	 */
	public final Boolean getIsLocalisant() {
		return this.isLocalisant;
	} // Fin de getIsLocalisant()._________________________________________



	/**
	 * method setIsLocalisant(
	 * Boolean pIsLocalisant) :<br/>
	 * Setter du Boolean qui précise si le champ participe à la localisation 
	 * ou est une donnée métier.<br/>
	 * <br/>
	 * true si participe à la Localisation.<br/>
	 * <br/>
	 *
	 * @param pIsLocalisant : Boolean : 
	 * valeur à passer à isLocalisant.<br/>
	 */
	public final void setIsLocalisant(
			final Boolean pIsLocalisant) {
		
		this.isLocalisant = pIsLocalisant;
		
		if (this.valeursDescriptionMap != null) {
			this.valeursDescriptionMap.put(
					14, String.valueOf(this.isLocalisant));
		}
		
	} // Fin de setIsLocalisant(
	 // Boolean pIsLocalisant).____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "descriptionchampmapping.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "descriptionchampmapping.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomChampJava() {
		return this.getChampJavaHisto();
	} // Fin de getNomChampJava().___________________________________________

	
	
	/**
	 * "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN";
	} // Fin de getNomDescriptionChamp().__________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomClasse() {
		return "";
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleANomenclatureTrue() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleALexiqueTrue() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleTableauNull() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleTableauVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleTableauTropPetit() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleOrdreChampVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleColonneVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleIntituleVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleChampJavaVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleTypeJavaVide() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCleANomenclatureVide() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
} // FIN DE LA CLASSE DescriptionChampMapping.-------------------------------
