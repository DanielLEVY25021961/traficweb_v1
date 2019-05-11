package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.AbstractImporteurLexique;





/**
 * CLASSE ImporteurLexique :<br/>
 * <p>
 * Classe CONCRETE.<br/>
 * Importe les nomenclatures LEXIQUE au format CSV avec séparateur ';' 
 * de la forme [String;String;].<br/>
 * </p>
 *  
 * <p>
 * Un <b>lexique</b> doit être un fichier csv avec séparateur ';' 
 * sous forme [String, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le PROFIL EN TRAVERS dans un HISTO_F07 :<br/>
 * 1V;chaussée unique 1 voie;<br/>
 * 2V<7;chaussée unique 2 voies < 7m;<br/>
 * 2V>=7;chaussée unique 2 voies >= 7m;<br/>
 * ...........................................<br/>
 * </p>
 * 
 * <p>
 * La <b>clé est donc toujours String dans un lexique</b>.
 * </p>
 * 
 * <div>
 * <ul>
 * <li>
 * Tous les ImporteurLexique possèdent une 
 * méthode <b><code>importerLexique(File pLexique)</code></b> 
 * où pLexique encapsule le lexique en csv de la donnée 
 * (profil en travers, code concession, ...) à servir.
 * </li>
 * <li>
 * Le lexique est servi sous forme de 
 * <b>SortedMap&lt;String, String&gt; <code>lexiqueMap</code></b> 
 * retournée par importerLexique(File pLexique).
 * </li>
 * <li>
 * La méthode importerLexique(File pLexique) permet également 
 * d'alimenter un <b>Set&lt;String&gt; <code>clesPossiblesSet</code></b> 
 * qui contient toutes les valeurs possibles pour la clé dans le lexique.
 * </li>
 * </ul>
 * </div>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurLexique : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurLexique.png" 
 * alt="Diagramme de classe du ImportateurLexique" />
 * </p>
 * 
 * <br/>
 *
 *<p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurLexique</code><br/>
 * <code><b>IImporteurLexique IMPORTEUR_LEXIQUE = new ImporteurLexique();</b></code><br/>
 * <code> // Import du fichier de lexique lexiqueFile</code><br/>
 * <code><b>Map&lt;String, String&gt; resultat = IMPORTEUR_LEXIQUE.importerLexiqueEnUtf8(lexiqueFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;String&gt; clesPossiblesSet = IMPORTEUR_LEXIQUE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère le lexique importé dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_LEXIQUE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
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
public class ImporteurLexique extends AbstractImporteurLexique {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe ImporteurLexique".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_LEXIQUE 
		= "Classe ImporteurLexique";
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ImporteurLexique.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ImporteurLexique() {		
		super();		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_LEXIQUE;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurnomenclature.importer.filenull";
	} // Fin de recupererCleImporterFileNull().____________________________



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
	
	

} // FIN DE LA CLASSE ImporteurLexique.--------------------------------------
