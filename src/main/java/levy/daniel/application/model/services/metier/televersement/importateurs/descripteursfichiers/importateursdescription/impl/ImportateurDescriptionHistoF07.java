package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHistoF07;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescriptionAscii;

/**
 * class ImportateurDescriptionHistoF07 :<br/>
 * Importateur concret pour les DESCRIPTIONS EN CSV des HistonatF07.<br/>
 * Chargé de lire une description de fichier HistonatF07 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * specificationChampsMap.<br/>
 * <br/>
 * La description d'un HistonatF07 commence par :<br/>
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
 * final ImportateurDescriptionHistoF07 impoDesc = new ImportateurDescriptionHistoF07();<br/>
 * <br/>
 * try {<br/>
 * <br/>
 * \\<i>Fournit la description sous forme de SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap.</i><br/>
 * final SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap = impoDesc.importerDescription(DESCRIPTION HistonatF07 en csv);<br/>
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
public class ImportateurDescriptionHistoF07 extends
									AbstractImportateurDescriptionAscii {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_IMPORTATEURDESCRIPTIONF07 : String :<br/>
	 * "Classe ImportateurDescriptionHistoF07 - ".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONF07 
	= "Classe ImportateurDescriptionHistoF07 - ";
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionHistoF07.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR ImportateurDescriptionHistoF07() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ImportateurDescriptionHistoF07() {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampHistoF07();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * method CONSTRUCTEUR ImportateurDescriptionHistoF07(
	 * File pDescriptionDuFichierFile) :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 */
	public ImportateurDescriptionHistoF07(
			final File pDescriptionDuFichierFile) {
		
		super(new DescriptionChampHistoF07(), pDescriptionDuFichierFile);
		
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
	 * "importateurdescriptionf07.niveau.anomalie"
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importateurdescriptionf07.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionf07.log.erreur".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importateurdescriptionf07.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String getNomClasse() {
		return CLASSE_IMPORTATEURDESCRIPTIONF07;
	} // Fin de getNomClasse().____________________________________________
	

		
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionf07.liredescription.mauvaisnomchamp"
	 */
	@Override
	protected final String getCleMauvaisNomChamp() {
		return "importateurdescriptionf07.liredescription.mauvaisnomchamp";
	} // Fin de getCleMauvaisNomChamp().___________________________________

	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionf07.liredescription.badlongueurs"
	 */
	@Override
	protected final String getCleBadLongueur() {
		return "importateurdescriptionf07.liredescription.badlongueurs";
	} // Fin de getCleBadLongueur()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionf07.liredescription.pasjointif"
	 */
	@Override
	protected final String getClePasJointif() {
		return "importateurdescriptionf07.liredescription.pasjointif";
	} // Fin de getClePasJointif().________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionf07.liredescription.colonnespasjointif"
	 */
	@Override
	protected final String getCleColonnesPasJointives() {
		return "importateurdescriptionf07.liredescription.colonnespasjointif";
	} // Fin de getCleColonnesPasJointives().______________________________
	
	
	
	/**
	 * "Description des champs d'un fichier HISTO_F07".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier HISTO_F07";
	} // Fin de getNomDescriptionChamp().__________________________________
	


} // FIN DE LA CLASSE ImportateurDescriptionHistoF07.------------------------
