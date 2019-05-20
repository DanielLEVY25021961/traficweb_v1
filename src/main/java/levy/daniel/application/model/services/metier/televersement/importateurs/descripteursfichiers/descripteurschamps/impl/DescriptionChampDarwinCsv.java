package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un IDescriptionChamp.</code><br/>
 * <code><b>IDescriptionChamp desc 
 * = new DescriptionChampDarwinCsv();</b></code><br/>
 * <code> // récupération de la Map des en-têtes de la description de fichier (créée en dur lors de l'instanciation du IDescriptionChamp).</code><br/>
 * <code><b>Map&lt;Integer, String&gt; entetesDescriptionMap = desc.getColonnesDescriptionMap();</b></code><br/>
 * <code> // tableau de tokens correspondant à la description d'un champ (ligne d'une description de fichier).</code><br/>
 * <code><b>public static final String[] CODE_CONCESSION_ORIGINE_DESC 
 * = {"4", "Code Concession du PR Origine", "Code Concession du PR Origine ('début')", "concessionPrd", "String", "false", "true"};</b></code><br/>
 * <code> // LECTURE - Injecte toutes les valeurs du tableau de tokens CODE_CONCESSION_ORIGINE_DESC dans la présente encapsulation</code><br/>
 * <code><b>desc.lireChamp(CODE_CONCESSION_ORIGINE_DESC);</b></code><br/> 
 * <code>// récupération des valeurs encapsulées.</code><br/>  
 * <code><b>SortedMap&lt;Integer, String&gt; valeursDescriptionMap = desc.getValeursDescriptionMap();</b></code><br/>
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
