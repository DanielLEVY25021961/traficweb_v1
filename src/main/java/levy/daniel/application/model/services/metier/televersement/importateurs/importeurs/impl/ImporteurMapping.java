package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.AbstractImporteurNonAscii;

/**
 * CLASSE ImporteurMapping :<br/>
 * Importeur de fichiers MAPPING.<br/>
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
 * @since 31 juil. 2014
 *
 */
public class ImporteurMapping extends AbstractImporteurNonAscii {

	// ************************ATTRIBUTS************************************/

	/* CONSTANTES. */
	/**
	 * "Classe ImporteurMapping".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_MAPPING 
		= "Classe ImporteurMapping";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ImporteurMapping.class);

	
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
	public ImporteurMapping() 
			throws Exception {
		
		super();
		
		/* passe le bon this.specificationChampsMap. */
		this.specificationChampsMap 
			= FactoryDescription.getDecriptionMappingMap();
		
		/* Passe le bon importateur de description 
		 * this.importateurDescription. */
		this.importateurDescription 
			= FactoryDescription.getImportateurMapping();
		
		/* Passe le bon DescriptionChamp this.descriptionChamp. */
		this.descriptionChamp 
			= FactoryDescription.getDescriptionChampMapping();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * "importeurmapping.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurmapping.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * "importeurmapping.log.erreur".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurmapping.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurMapping".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_MAPPING;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurmapping.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurmapping.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurmapping.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurmapping.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurmapping.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurmapping.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurmapping.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurmapping.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	

	
} // FIN DE LA CLASSE ImporteurMapping.------------------------------------__
