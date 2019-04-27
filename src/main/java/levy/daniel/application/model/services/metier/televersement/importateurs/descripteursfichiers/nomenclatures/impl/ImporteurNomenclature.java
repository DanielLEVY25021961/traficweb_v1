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
	 * CLASSE_IMPORTATEUR_NOMENCLATURE_SENS_HIT : String :<br/>
	 * "Classe ImporteurNomenclature - ".<br/>
	 */
	public static final String CLASSE_IMPORTATEUR_NOMENCLATURE_SENS_HIT 
		= "Classe ImporteurNomenclature - ";
	
	
	
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
		return CLASSE_IMPORTATEUR_NOMENCLATURE_SENS_HIT;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importeurnomenclaturesenshit.importer.filenull".<br/>
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurnomenclaturesenshit.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * {@inheritDoc}
	 * "importeurnomenclaturesenshit.importer.filevide".<br/>
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurnomenclaturesenshit.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * {@inheritDoc}
	 * "importeurnomenclaturesenshit.importer.fileinexistant".<br/>
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurnomenclaturesenshit.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * {@inheritDoc}
	 * "importeurnomenclaturesenshit.importer.filepasnormal".<br/>
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurnomenclaturesenshit.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	
	

} // FIN DE LA CLASSE ImporteurNomenclature.-----------------------------
