package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.AbstractImporteurNonAscii;

/**
 * CLASSE ImporteurDarwinCsv :<br/>
 * Importeur de fichiers DARWIN_CSV.<br/>
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
public class ImporteurDarwinCsv extends AbstractImporteurNonAscii {

	// ************************ATTRIBUTS************************************/

	/* CONSTANTES. */
	/**
	 * "Classe ImporteurDarwinCsv".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_DARWINCSV 
		= "Classe ImporteurDarwinCsv";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ImporteurDarwinCsv.class);

	
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
	public ImporteurDarwinCsv() 
			throws Exception {
		
		super();
		
		/* passe le bon this.specificationChampsMap. */
		this.specificationChampsMap 
			= FactoryDescription.getDecriptionDarwinCsvMap();
		
		/* Passe le bon importateur de description 
		 * this.importateurDescription. */
		this.importateurDescription 
			= FactoryDescription.getImportateurDarwinCsv();
		
		/* Passe le bon DescriptionChamp this.descriptionChamp. */
		this.descriptionChamp 
			= FactoryDescription.getDescriptionChampDarwinCsv();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * "importeurdarwincsv.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurdarwincsv.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * "importeurdarwincsv.log.erreur".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurdarwincsv.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurDarwinCsv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_DARWINCSV;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurdarwincsv.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurdarwincsv.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurdarwincsv.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurdarwincsv.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurdarwincsv.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurdarwincsv.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurdarwincsv.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurdarwincsv.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 
	

	
} // FIN DE LA CLASSE ImporteurDarwinCsv.------------------------------------
