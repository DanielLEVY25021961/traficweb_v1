package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescriptionAscii;

/**
 * class ImportateurDescriptionHit :<br/>
 * Importateur concret pour les DESCRIPTIONS EN CSV des HIT.<br/>
 * Chargé de lire une description de fichier HIT 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * specificationChampsMap.<br/>
 * <br/>
 * La description d'un HIT commence par :<br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;4;9;6;<br/>
 * .......................................................<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 * <code>
 * \\ Instanciation d'un Importateur de description.<br/>
 * final ImportateurDescriptionHit impoDesc = new ImportateurDescriptionHit();<br/>
 * <br/>
 * try {<br/>
 * <br/>
 * \\<i>Fournit la description sous forme de SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap.</i><br/>
 * final SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap = impoDesc.importerDescription(DESCRIPTION KIT en csv);<br/>
 * <br/>
 * } catch (Exception e) {<br/>
 * 			System.out.println("RAPPORT : \n" <br/>
 * 				+ impoDesc.getRapportImportDescriptionStb().toString());<br/>
 * }<br/></code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 30 juin 2014
 *
 */
public class ImportateurDescriptionHit extends
									AbstractImportateurDescriptionAscii {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_IMPORTATEURDESCRIPTIONHIT : String :<br/>
	 * "Classe ImportateurDescriptionHit - ".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONHIT 
	= "Classe ImportateurDescriptionHit - ";
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionHit.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR ImportateurDescriptionHit() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ImportateurDescriptionHit() {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampHit();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * method CONSTRUCTEUR ImportateurDescriptionHit(
	 * File pDescriptionDuFichierFile) :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 */
	public ImportateurDescriptionHit(
			final File pDescriptionDuFichierFile) {
		
		super(new DescriptionChampHit(), pDescriptionDuFichierFile);
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR ARCHICOMPLET._________________________________
	

	
	/**
	 * method determinerSiLogErreurs() :<br/>
	 * - Va chercher dans messages_techniques.properties 
	 * si il faut créer des rapports d'erreur d'import des descriptions.<br/>
	 * - Instancie le cas échéant le rapport d'erreur.<br/>
	 * <br/>
	 */
	private void determinerSiLogErreurs() {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechniques()
				.getString(cleLogImport);
		
		if (StringUtils.containsIgnoreCase(logImportString, "true")) {
			this.logImportDescription = true;
		}
		else {
			this.logImportDescription = false;
		}
		
		/* Instanciation du rapportImportDescription
		 * si logImportDescription == true. */
		if (this.logImportDescription) {
			this.rapportImportDescriptionStb = new StringBuffer();
		}
		
	} // Fin de determinerSiLogErreurs().__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.niveau.anomalie"
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importateurdescriptionhit.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.log.erreur".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importateurdescriptionhit.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String getNomClasse() {
		return CLASSE_IMPORTATEURDESCRIPTIONHIT;
	} // Fin de getNomClasse().____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.liredescription.mauvaisnomchamp"
	 */
	@Override
	protected final String getCleMauvaisNomChamp() {
		return "importateurdescriptionhit.liredescription.mauvaisnomchamp";
	} // Fin de getCleMauvaisNomChamp().___________________________________

	
		
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.liredescription.badlongueurs"
	 */
	@Override
	protected final String getCleBadLongueur() {
		return "importateurdescriptionhit.liredescription.badlongueurs";
	} // Fin de getCleBadLongueur()._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.liredescription.pasjointif"
	 */
	@Override
	protected final String getClePasJointif() {
		return "importateurdescriptionhit.liredescription.pasjointif";
	} // Fin de getClePasJointif().________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionhit.liredescription.colonnespasjointif"
	 */
	@Override
	protected final String getCleColonnesPasJointives() {
		return "importateurdescriptionhit.liredescription.colonnespasjointif";
	} // Fin de getCleColonnesPasJointives().______________________________
	

	
	/**
	 * "Description des champs d'un fichier HIT".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier HIT";
	} // Fin de getNomDescriptionChamp().__________________________________
	

	
} // FIN DE LA CLASSE ImportateurDescriptionHit.-----------------------------
