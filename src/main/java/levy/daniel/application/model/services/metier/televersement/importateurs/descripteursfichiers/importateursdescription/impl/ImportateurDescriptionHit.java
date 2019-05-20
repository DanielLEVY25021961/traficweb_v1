package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions.ConfigurationDescriptionsFichiersManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescriptionAscii;

/**
 * class ImportateurDescriptionHit :<br/>
 * <p>
 * Importateur concret pour les DESCRIPTIONS EN CSV des HIT.<br/>
 * </p>
 * 
 * <p>
 * Chargé de lire une description de fichier HIT 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * <code><b>this.specificationChampsMap</b></code>.<br/>
 * </p>
 * 
 * 
 * <p>
 * Un fichier de description d'un HIT formatée en csv (';') 
 * commence par :<br/>
 * <br/>
 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;<br/>
 * 1;1-3;3;Numéro de Département;cadré à gauche. Ex: dept 13 = 130;numDepartement;Integer;false;false;1;3;3;<br/>
 * 2;4-9;6;Numéro de Section;;numSection;String;false;false;4;9;6;<br/>
 * 3;10;1;Sens;3 - Cumul des deux sens. [sep] 4 - Sens unique P.R. croissants. [sep] 5 - Sens unique P.R. Décroissants.;sens;Integer;true;false;10;10;1;<br/>
 * ...................................................<br/>
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
 * <tr>
 * <td>3</td> <td>10</td> <td>1</td> <td>Sens</td> 
 * <td>3 - Cumul des deux sens. [sep] 4 - Sens unique P.R. croissants. [sep] 5 - Sens unique P.R. Décroissants.</td> <td>sens</td> <td>Integer</td> <td>true</td> 
 * <td>false</td> <td>10</td> <td>10</td> <td>1</td> 
 * </tr>
 * </table>
 * </p>
 * 
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
	 * "Classe ImportateurDescriptionHit".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONHIT 
	= "Classe ImportateurDescriptionHit";
	
	
	/**
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionHit.class);

	
	// *************************METHODES************************************/
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe automatiquement la <b>description de fichier du HIT</b>
	 * <code><b>ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit()</b></code> 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>délègue à un 
	 * <code><b>ConfigurationDescriptionsFichiersManager</b></code> 
	 * le soin de fournir la bonne description de fichier.</li>
	 * <li>passe automatiquement une <b>description de champ HIT</b>
	 * <code><b>DescriptionChampHit</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionHit() throws Exception {
		
		this(ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit());
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <ul>
	 * <li>passe pDescriptionDuFichierFile 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>passe automatiquement une <b>description de champ HIT</b>
	 * <code><b>DescriptionChampHit</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionHit(
			final File pDescriptionDuFichierFile) throws Exception {
		
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
	 * @throws Exception 
	 */
	private void determinerSiLogErreurs() throws Exception {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechnique()
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
