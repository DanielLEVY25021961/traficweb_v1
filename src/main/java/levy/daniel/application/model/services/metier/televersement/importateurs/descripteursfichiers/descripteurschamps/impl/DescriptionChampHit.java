package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl;

import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampAscii;

/**
 * class DescriptionChampHit :<br/>
 * IMPLEMENTATION de AbstractDescriptionChampAscii chargée
 * de stocker la définition d'un champ de fichier
 * HIT.<br/>
 * <br/>
 * Un DescriptionChampHit "sait" qu'une description 
 * de fichier HIT doit être ordonnée comme suit :<br/>
 * [ordreChamps, colonnes, longueur, intitule, nomenclature
 * , champJava, typeJava, aNomenclature
 * , colonneDebut, colonneFin, longueurCalculee].<br/>
 * Il stocke cette liste ordonnée de champs dans sa map triée 
 * 'entetesDescriptionMap' dès sa construction.<br/>
 * <br/>
 * Un DescriptionChampHit ne "connait" les valeurs 
 * décrivant un champ donné
 * qu'après l'execution de sa méthode lireChamps(String[] pTokens) 
 * où pTokens représente toutes les valeurs de la description 
 * du champ sous forme de tableau de String.<br/>
 * <code>public static final String[] NUM_DEPT_DESC = {"1", "1-3", "3", "Numéro de Département", "calé à gauche", "numDepartment", "Integer", "false"};</code><br/>
 * <code>descripteur.lireChamps(NUM_DEPT_DESC);</code><br/>
 * <br/>
 * Un fichier de description d'un HIT formatée en csv (';') 
 * commence par :<br/>
 * <br/>
 * ordreChamps;colonnes;longueur;intitule;
 * nomenclature;champJava;typeJava;aNomenclature;
 * colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;4;9;6;<br/>
 * 3;10;1;Sens;3 - Cumul des deux sens. [sep] 4 - Sens unique P.R. croissants. [sep] 5 - Sens unique P.R. Décroissants.;sens;Integer;true;10;10;1;<br/>
 * 4;11;1;Nature;Codé 1 tous véhicules (uniquement);nature;Integer;false;11;11;1;<br/>
 * 5;12-13;2;Classe;Codé 00 tous véhicules (uniquement);classe;String;false;12;13;2;<br/>
 * 6;14-15;2;Année de traitement;Année sur deux caractères (ex 07 pour 2007);anneeTraitement;Date;false;14;15;2;<br/>
 * ..............................<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>IDescriptionChamp desc 
 * = new DescriptionChampHit();</code><br/>
 * <code>desc.getColonnesDescriptionMap();</code><br/>
 * <code>public static final String[] NUM_DEPT_DESC 
 * = {"1", "1-3", "3", "Numéro de Département"
 * , "Cadré à droite", "numDepartment"
 * , "Integer", "false"};</code><br/>
 * <code>desc.lireChamp(NUM_DEPT_DESC);   //Injecte toutes les valeurs du tableau de tokens NUM_DEPT_DESC dans la présente encapsulation</code><br/>
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
public class DescriptionChampHit extends AbstractDescriptionChampAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_DESCRIPTIONCHAMPHIT : String : <br/>
	 * "CLASSE DescriptionChampHit - ".<br/>
	 */
	public static final String CLASSE_DESCRIPTIONCHAMPHIT
		= "CLASSE DescriptionChampHit - ";
	
		
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(DescriptionChampHit.class);

	
	// *************************METHODES************************************/
	
	/**
	 * CONSTRUCTEUR D'ARITE 0 permettant d'instancier
	 * un DescriptionChampHit en instanciant et alimentant
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
	public DescriptionChampHit() throws Exception {		
		super();		
	} // Fin de CONSTRUCTEUR DescriptionChampHit().___________________
	
	
	
	/**
	 * method CONSTRUCTEUR DescriptionChampHit(
	 * SortedMap&lt;Integer, String&gt; pColonnesDescriptionMap) :
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <br/>
	 * - Permet de construire un DescriptionChampHit
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
	public DescriptionChampHit(
			final SortedMap<Integer, String> pColonnesDescriptionMap) 
					throws Exception{
		
		super(pColonnesDescriptionMap);
				
	} // Fin de CONSTRUCTEUR DescriptionChampHit(
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
	 * @return : String : "descriptionchamphit.niveau.anomalie" 
	 * pour un DescriptionChampHit.<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "descriptionchamphit.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________



	/**
	 * method recupererCleLogErreur() :<br/>
	 * Fournit la clé du log des erreurs
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logDescription à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String : "descriptionchamphit.log.erreur" 
	 * pour un DescriptionChampHit.<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "descriptionchamphit.log.erreur";
	} // Fin de recupererCleNiveauAnomalie().______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomClasse() {
		return CLASSE_DESCRIPTIONCHAMPHIT;
	} // Fin de String getNomClasse()._____________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.anomenclaturetrue".<br/>
	 */
	@Override
	public final String getCleANomenclatureTrue() {
		return "descriptionchamphit.lirechamp.anomenclaturetrue";
	} // Fin de getCleANomenclatureTrue()._________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.tableaunull".<br/>
	 */
	@Override
	public final String getCleTableauNull() {
		return "descriptionchamphit.lirechamp.tableaunull";
	} // Fin de getCleTableauNull()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.tableauvide".<br/>
	 */
	@Override
	public final String getCleTableauVide() {
		return "descriptionchamphit.lirechamp.tableauvide";
	} // Fin de getCleTableauVide()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.tableautroppetit".<br/>
	 */
	@Override
	public final String getCleTableauTropPetit() {
		return "descriptionchamphit.lirechamp.tableautroppetit";
	} // Fin de getCleTableauTropPetit().__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.ordrechampvide".<br/>
	 */
	@Override
	public final String getCleOrdreChampVide() {
		return "descriptionchamphit.lirechamp.ordrechampvide";
	} // Fin de getCleOrdreChampVide().____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.colonnevide".<br/>
	 */
	@Override
	public final String getCleColonneVide() {
		return "descriptionchamphit.lirechamp.colonnevide";
	} // Fin de getCleColonneVide()._______________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.intitulevide".<br/>
	 */
	@Override
	public final String getCleIntituleVide() {
		return "descriptionchamphit.lirechamp.intitulevide";
	} // Fin de getCleIntituleVide().______________________________________

	
			
	/**
	 * {@inheritDoc}
	 * "descriptionchamphit.lirechamp.champjavavide".<br/>
	 */
	@Override
	public final String getCleChampJavaVide() {
		return "descriptionchamphit.lirechamp.champjavavide";
	} // Fin de getCleChampJavaVide().______________________________________


		
	/**
	* {@inheritDoc}
	* "descriptionchamphit.lirechamp.typejavavide".<br/>
	*/
	@Override
	public final String getCleTypeJavaVide() {
	return "descriptionchamphit.lirechamp.typejavavide";
	} // Fin de getCleTypeJavaVide().______________________________________


	
	/**
	* {@inheritDoc}
	* "descriptionchamphit.lirechamp.anomenclaturevide".<br/>
	*/
	@Override
	public final String getCleANomenclatureVide() {
	return "descriptionchamphit.lirechamp.anomenclaturevide";
	} // Fin de getCleANomenclatureVide()._________________________________
	
	
	
	
	/**
	 * "Description des champs d'un fichier HIT".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier HIT";
	} // Fin de getNomDescriptionChamp().__________________________________
	

	
} // FIN DE LA CLASSE DescriptionChampHit.-----------------------------------
