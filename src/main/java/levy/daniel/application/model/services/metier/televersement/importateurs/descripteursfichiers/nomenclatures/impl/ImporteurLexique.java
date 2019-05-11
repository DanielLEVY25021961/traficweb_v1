package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.AbstractImporteurLexique;





/**
 * class ImporteurNomenclature :<br/>
 * Importe les nomenclatures LEXIQUE au format CSV avec séparateur ';' 
 * de la forme [String;String;].<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
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
	
	

} // FIN DE LA CLASSE ImporteurNomenclature.-----------------------------
