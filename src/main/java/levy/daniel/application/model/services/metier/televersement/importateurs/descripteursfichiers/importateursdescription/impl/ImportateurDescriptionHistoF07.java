package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions.ConfigurationDescriptionsFichiersManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHistoF07;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescriptionAscii;

/**
 * class ImportateurDescriptionHistoF07 :<br/>
 * <p>
 * Importateur concret pour les DESCRIPTIONS EN CSV des HISTO_F07.<br/>
 * </p>
 * 
 * <p>
 * Chargé de lire une description de fichier HISTO_F07 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * <code><b>this.specificationChampsMap</b></code>.<br/>
 * </p>
 * 
 * 
 * <p>
 * Un fichier de description d'un HISTO_F07 formatée en csv (';') 
 * commence par :<br/>
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
 *   
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurDescription : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_1.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_2.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * </p>
 * 
 * <br/>
 *
 * 
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // instanciation d'un ImportateurDescription.</code><br/>
 * <code><b>IImportateurDescription importateur = new ImportateurDescriptionHistoF07();</b></code><br/>
 * <code>// IMPORT de la bonne description de fichier encodée en UTF-8 (contenue sous classpath/resources).</code><br/>
 * <code><b>importateur.importerDescriptionUtf8();</b></code><br/>
 * <code> // Retourne l'en-tête de la 2ème colonne d'un fichier de description du HISTO_F07 ("colonnes")</code><br/>
 * <code><b>String fournirEnteteparColonne2 = importateur.fournirEnteteparColonne(2);</b></code><br/>
 * <code> // Retourne la ligne d'en-tête CSV du fichier de description du HISTO_F07 ("ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;")</code><br/>
 * <code><b>String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();</b></code><br/>
 * <code> // retourne la description du 1er champ de la description du HISTO_F07 ("1;1-3;3;Numéro de Département;Exactement 3 Chiffres. Complété par un 0 à droite si dep < 3 chiffres et complété par un 0 à gauche si dep < 2 chiffres. Exemples : (Ain) 1 = 010, (Allier) 3 = 030, (Bouches-du-Rhône) 13 = 130, (Dordogne) 24 = 240, (Guadeloupe) 971 = 971;numDepartement;Integer;false;false;1;3;3;")</code><br/>
 * <code><b>String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);</b></code><br/>
 * <code> // retourne la valeur de la 28ème ligne et de la 6ème colonne de la description du fichier HISTO_F07 ("longueurRaseCampagne")</code><br/>
 * <code><b>String valeurl28c6 = importateur.fournirValeurparLigneColonne(28, 6);</b></code><br/>
 * <code> // génère en UTF-8 la description de fichier précédemment importée dans le fichier descriptionGeneree</code><br/>
 * <code><b>Path decriptionGenereePath = PATH_ABSOLU_REPERTOIRE_TEMP.resolve("description_HISTO_F07_generee_UTF-8.csv");</b></code></br>
 * <code><b>File descriptionGeneree = decriptionGenereePath.toFile();</b></code></br>
 * <code><b>importateur.genererDescriptionCsvFileUtf8(descriptionGeneree);</b></code></br>
 * <code> // Retourne la description précédemment importée et regénérée sous forme de String.</code><br/>
 * <code><b>String resultat = importateur.genererDescriptionCsvString(true);</b></code></br>
 * <code> // Retourne la IDescriptionChamp correspondant au 2ème champ de la description du HISTO_F07 (numéro de section)</code><br/>
 * <code><b>IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);</b></code></br>
 * <br/>
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
	 * "Classe ImportateurDescriptionHistoF07".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONF07 
	= "Classe ImportateurDescriptionHistoF07";
	
	
	
	/**
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionHistoF07.class);

	
	// *************************METHODES************************************/
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe automatiquement la <b>description de fichier du HISTO_F07</b>
	 * <code><b>ConfigurationDescriptionsFichiersManager.getFichierDescriptionHit()</b></code> 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>délègue à un 
	 * <code><b>ConfigurationDescriptionsFichiersManager</b></code> 
	 * le soin de fournir la bonne description de fichier.</li>
	 * <li>passe automatiquement une <b>description de champ HISTO_F07</b>
	 * <code><b>DescriptionChampHistoF07</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionHistoF07() throws Exception {
		
		this(ConfigurationDescriptionsFichiersManager.getFichierDescriptionHistoF07());
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <ul>
	 * <li>passe pDescriptionDuFichierFile 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>passe automatiquement une <b>description de champ HISTO_F07</b>
	 * <code><b>DescriptionChampHistoF07</b></code> à 
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
	public ImportateurDescriptionHistoF07(
			final File pDescriptionDuFichierFile) throws Exception {
		
		super(new DescriptionChampHistoF07(), pDescriptionDuFichierFile);
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR ARCHICOMPLET._________________________________
	
	
	
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
	 * {@inheritDoc}
	 * "importateurdescriptionf07.liredescription.pascsv"
	 */
	@Override
	protected final String getClePasCsv() {
		return "importateurdescriptionf07.liredescription.pascsv";
	} // Fin de getClePasCsv().____________________________________________
	
	
	
	/**
	 * "Description des champs d'un fichier HISTO_F07".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getLabelDescriptionFichier() {
		return "Description des champs d'un fichier HISTO_F07";
	} // Fin de getNomDescriptionChamp().__________________________________
	


} // FIN DE LA CLASSE ImportateurDescriptionHistoF07.------------------------
