package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.AbstractImporteurAscii;

/**
 * CLASSE ImporteurHistoF08 :<br/>
 * Importeur de fichiers HISTO_F08.<br/>
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
 * @since 7 juil. 2014
 *
 */
public class ImporteurHistoF08 extends AbstractImporteurAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * "Classe ImporteurHistoF08".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_HISTOF08 
		= "Classe ImporteurHistoF08";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ImporteurHistoF08.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe le bon <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>Passe le bon importateur de description 
	 * <code><b>this.importateurDescription</b></code>.</li>
	 * <li>Passe le bon DescriptionChamp 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImporteurHistoF08() 
			throws Exception {
		
		super();
				
		/* passe le bon this.specificationChampsMap. */
		this.specificationChampsMap 
			= FactoryDescription.getDecriptionHistoF08Map();
		
		/* Passe le bon importateur de description 
		 * this.importateurDescription. */
		this.importateurDescription 
			= FactoryDescription.getImportateurHistoF08();
		
		/* Passe le bon DescriptionChamp this.descriptionChamp. */
		this.descriptionChamp 
			= FactoryDescription.getDescriptionChampHistoF08();
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
			
	
	/**
	 * "importeurhistof08.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurhistof08.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * "importeurhistof08.log.erreur".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurhistof08.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurHistoF08".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_HISTOF08;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurhistof08.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurhistof08.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurhistof08.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurhistof08.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurhistof08.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurhistof08.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurhistof08.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurhistof08.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	

	
} // FIN DE LA CLASSE ImporteurHistoF08.-------------------------------------
