package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampAscii;

/**
 * class DescriptionChampHistoF07 :<br/>
 * <p>
 * IMPLEMENTATION de AbstractDescriptionChampAscii chargée
 * de stocker la définition d'un champ de fichier
 * HISTO_F07.<br/>
 * </p>
 * 
 * <p>
 * Un DescriptionChampHistoF07 "sait" qu'une description 
 * de fichier HISTO_F07 doit être ordonnée comme suit :<br/>
 * [ordreChamps, colonnes, longueur, intitule, nomenclature
 * , champJava, typeJava, aNomenclature, aLexique
 * , colonneDebut, colonneFin, longueurCalculee].<br/>
 * Il stocke cette liste ordonnée de champs dans sa map triée 
 * <code><b>this.entetesDescriptionMap</b></code> dès sa construction.<br/>
 * </p>
 * 
 * <p>
 * Un DescriptionChampHistoF07 ne "connait" les valeurs 
 * décrivant un champ donné
 * qu'après l'execution de sa méthode lireChamps(String[] pTokens) 
 * où pTokens représente toutes les valeurs de la description 
 * du champ donné sous forme de tableau de String.<br/>
 * </p>
 * 
 * <p>
 * <code> // Lecture d'une ligne du fichier de description.</code><br/>
 * <code><b>public static final String[] NUM_DEPT_DESC = 
 * {"1", "1-3", "3", "Numéro de Département", "cadré à gauche", "numDepartment", "Integer", "false", "false"};</b></code><br/>
 * <code> // Import de la ligne de description dans un DescriptionChampHit.</code><br/>
 * <code><b>descripteur.lireChamps(NUM_DEPT_DESC);</b></code><br/>
 * </p>
 *
 * <p>
 * La description d'un HISTO_F07 commence par :<br/>
 * <br/>
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
public class DescriptionChampHistoF07 extends AbstractDescriptionChampAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_DESCRIPTIONCHAMPHISTOF07 : String : <br/>
	 * "CLASSE DescriptionChampHistoF07 - ".<br/>
	 */
	public static final String CLASSE_DESCRIPTIONCHAMPHISTOF07
		= "CLASSE DescriptionChampHistoF07 - ";
	
		
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(DescriptionChampHistoF07.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR DescriptionChampHistoF07() :<br/>
	 * <br/>
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un DescriptionChampHistoF07 en instanciant et alimentant
	 * la Map&lt;Integer, String&gt; 'entetesDescriptionMap'
	 * qui décrit les en-têtes de colonne directement en son sein.<br/>
	 * <br/>
	 * - Alimente le 'nombreColonnesObligatoires', c'est à dire
	 * les colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier (d'autres colonnes comme 
	 * 'colonne de début', 'colonne de fin', 'longueur', ... 
	 * peuvent être calculées après l'import sans avoir été 
	 * directement fournies dans la description de fichier.
	 * Il suffit d'avoir fourni un champ 'colonnes' sous 
	 * la forme '14-24').<br/>
	 * 
	 * @throws Exception 
	 *
	 */
	public DescriptionChampHistoF07() throws Exception {
		
		super();
		
	} // Fin de CONSTRUCTEUR DescriptionChampHistoF07().___________________
	
	
	
	/**
	 * method CONSTRUCTEUR DescriptionChampHistoF07(
	 * SortedMap&lt;Integer, String&gt; pColonnesDescriptionMap) :
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <br/>
	 * - Permet de construire un DescriptionChampHistoF07
	 * en lui passant la Map des colonnes de la description
	 * du fichier 'entetesDescriptionMap'.<br/>
	 * <br/>
	 * - Alimente le 'nombreColonnesObligatoires', c'est à dire
	 * les colonnes qui doivent obligatoirement être fournies
	 * dans la description de fichier (d'autres colonnes comme 
	 * 'colonne de début', 'colonne de fin', 'longueur', ... 
	 * peuvent être calculées après l'import sans avoir été 
	 * directement fournies dans la description de fichier.
	 * Il suffit d'avoir fourni un champ 'colonnes' sous 
	 * la forme '14-24').<br/>
	 * <br/>
	 *
	 * @param pColonnesDescriptionMap : SortedMap&lt;Integer, String&gt; :
	 * la Map des colonnes de la 
	 * description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	public DescriptionChampHistoF07(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
					throws Exception{
		
		super(pColonnesDescriptionMap);
				
	} // Fin de CONSTRUCTEUR DescriptionChampHistoF07(
	 // SortedMap<Integer, String> pColonnesDescriptionMap)._______________


		
	/**
	 * method recupererCleNiveauAnomalie() :<br/>
	 * Fournit la clé du niveau d'anomalie 
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Si le niveau est 1 (anomalie bloquante), 
	 * une erreur dans la lecture d'une 
	 * ligne de description doit arrêter le programme.<br/>
	 * <br/>
	 *
	 * @return : String : "descriptionchamphistof07.niveau.anomalie" 
	 * pour un DescriptionChampHistoF07.<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "descriptionchamphistof07.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________



	/**
	 * method recupererCleLogErreur() :<br/>
	 * Fournit la clé du log des erreurs
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logDescription à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String : "descriptionchamphistof07.log.erreur" 
	 * pour un DescriptionChampHistoF07.<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "descriptionchamphistof07.log.erreur";
	} // Fin de recupererCleNiveauAnomalie().______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomClasse() {
		return CLASSE_DESCRIPTIONCHAMPHISTOF07;
	} // Fin de String getNomClasse()._____________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.anomenclaturetrue".<br/>
	 */
	@Override
	public final String getCleANomenclatureTrue() {
		return "descriptionchamphistof07.lirechamp.anomenclaturetrue";
	} // Fin de getCleANomenclatureTrue()._________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.alexiquetrue".<br/>
	 */
	@Override
	public final String getCleALexiqueTrue() {
		return "descriptionchamphistof07.lirechamp.alexiquetrue";
	} // Fin de getCleALexiqueTrue().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.tableaunull".<br/>
	 */
	@Override
	public final String getCleTableauNull() {
		return "descriptionchamphistof07.lirechamp.tableaunull";
	} // Fin de getCleTableauNull()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.tableauvide".<br/>
	 */
	@Override
	public final String getCleTableauVide() {
		return "descriptionchamphistof07.lirechamp.tableauvide";
	} // Fin de getCleTableauVide()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.tableautroppetit".<br/>
	 */
	@Override
	public final String getCleTableauTropPetit() {
		return "descriptionchamphistof07.lirechamp.tableautroppetit";
	} // Fin de getCleTableauTropPetit().__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.ordrechampvide".<br/>
	 */
	@Override
	public final String getCleOrdreChampVide() {
		return "descriptionchamphistof07.lirechamp.ordrechampvide";
	} // Fin de getCleOrdreChampVide().____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.colonnevide".<br/>
	 */
	@Override
	public final String getCleColonneVide() {
		return "descriptionchamphistof07.lirechamp.colonnevide";
	} // Fin de getCleColonneVide()._______________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.intitulevide".<br/>
	 */
	@Override
	public final String getCleIntituleVide() {
		return "descriptionchamphistof07.lirechamp.intitulevide";
	} // Fin de getCleIntituleVide().______________________________________

	
			
	/**
	 * {@inheritDoc}
	 * "descriptionchamphistof07.lirechamp.champjavavide".<br/>
	 */
	@Override
	public final String getCleChampJavaVide() {
		return "descriptionchamphistof07.lirechamp.champjavavide";
	} // Fin de getCleChampJavaVide().______________________________________


		
	/**
	* {@inheritDoc}
	* "descriptionchamphistof07.lirechamp.typejavavide".<br/>
	*/
	@Override
	public final String getCleTypeJavaVide() {
	return "descriptionchamphistof07.lirechamp.typejavavide";
	} // Fin de getCleTypeJavaVide().______________________________________


	
	/**
	* {@inheritDoc}
	* "descriptionchamphistof07.lirechamp.anomenclaturevide".<br/>
	*/
	@Override
	public final String getCleANomenclatureVide() {
	return "descriptionchamphistof07.lirechamp.anomenclaturevide";
	} // Fin de getCleANomenclatureVide()._________________________________
	
	
	
	/**
	 * "Description des champs d'un fichier HISTO_F07".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier HISTO_F07";
	} // Fin de getNomDescriptionChamp().__________________________________
	

		
} // FIN DE LA CLASSE DescriptionChampHistoF07.------------------------------
