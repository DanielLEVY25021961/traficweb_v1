package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.AbstractImporteurNomenclature;

/**
 * CLASSE ImporteurNomenclature :<br/>
 * <p>
 * Classe CONCRETE.<br/>
 * Importe les nomenclatures au format CSV avec séparateur ';' 
 * de la forme [Integer;String;].<br/>
 * </p>
 * 
 * <p>
 *  Une <b>nomenclature</b> doit être un fichier csv avec séparateur ';' 
 *  encodé en UTF-8
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le SENS dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 *</p>
 *
 * <p>
 * La <b>clé est donc toujours entière dans une nomenclature</b>.
 * </p>
 *
 *<div>
 *<ul>
 * <li>
 * Tous les ImporteurNomenclature possèdent une 
 * méthode <b><code>importerNomenclature(File pNomenclature)</code></b> 
 * où pNomenclature encapsule la nomenclature en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.
 * </li>
 * <li>
 * La nomenclature est servie sous forme de 
 * <b>SortedMap&lt;Integer, String&gt; <code>nomenclatureMap</code></b> 
 * retournée par importerNomenclature(File pNomenclature).
 * </li>
 * <li>
 * La méthode importerNomenclature(File pNomenclature) permet également 
 * d'alimenter un <b>Set&lt;Integer&gt; <code>clesPossiblesSet</code></b> 
 * qui contient toutes les valeurs possibles pour la clé dans la nomenclature.
 * </li>
 * </ul>
 * </div>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurNomenclature : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurNomenclature.png" 
 * alt="Diagramme de classe du ImportateurNomenclature" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurNomenclature</code><br/>
 * <code><b>IImporteurNomenclature IMPORTEUR_NOMENCLATURE = new ImporteurNomenclature();</b></code><br/>
 * <code> // Import du fichier de nomenclature nomenclatureFile</code><br/>
 * <code><b>Map&lt;Integer, String&gt; resultat = IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;Integer&gt; clesPossiblesSet = IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère la nomenclature importée dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
 * </p>
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
 * @since 16 juil. 2014
 *
 */
public class ImporteurNomenclature extends AbstractImporteurNomenclature {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe ImporteurNomenclature".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_NOMENCLATURE 
		= "Classe ImporteurNomenclature";
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ImporteurNomenclature.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImporteurNomenclature() {		
		super();		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_NOMENCLATURE;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurnomenclature.importer.filenull";
	} // Fin de recupererCleImporterFileNull()().__________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurnomenclature.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurnomenclature.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurnomenclature.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	
	

} // FIN DE LA CLASSE ImporteurNomenclature.-----------------------------
