package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.AbstractImporteurNomenclature;

/**
 * class ImporteurNomenclature :<br/>
 * Importe la nomenclature pour les sens dans les HIT.<br/>
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
	 * "importeurnomenclature.importer.filenull"
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurnomenclature.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * {@inheritDoc}
	 * "importeurnomenclature.importer.filevide".<br/>
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurnomenclature.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * {@inheritDoc}
	 * "importeurnomenclature.importer.fileinexistant".<br/>
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurnomenclature.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * {@inheritDoc}
	 * "importeurnomenclature.importer.filepasnormal".<br/>
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurnomenclature.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	
	

} // FIN DE LA CLASSE ImporteurNomenclature.-----------------------------
