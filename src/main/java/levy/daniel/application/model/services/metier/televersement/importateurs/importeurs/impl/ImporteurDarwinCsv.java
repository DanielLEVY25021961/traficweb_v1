package levy.daniel.application.metier.importateurs.importeurs.impl;

import java.io.IOException;

import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.controleursimport.impl.ControleurImportDarwinCsv;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampDarwinCsv;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.FactorySingletonImportateurDescription;
import levy.daniel.application.metier.importateurs.importeurs.AbstractImporteurNonAscii;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class ImporteurDarwinCsv :<br/>
 * Importeur de fichiers Darwin csv.<br/>
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
	 * CLASSE_IMPORTEUR_DARWINCSV : String :<br/>
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
	 * method CONSTRUCTEUR ImporteurDarwinCsv() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * 
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de Darwin csv au lieu de HistonatF07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * l'ordre des champs n'est pas jointif.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 */
	public ImporteurDarwinCsv() 
			throws FichierNullException
			, TableauNullException
			, TableauVideException
			, ExceptionImport
			, IOException {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampDarwinCsv();
		
		/* Passe l'importateur de description. */
		this.importateurDescription 
			= FactorySingletonImportateurDescription
				.getImportateurDescriptionDarwinCsv();
		
		/* Passe le ControleurImport. */
		this.controleurImport = new ControleurImportDarwinCsv();
				
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

	
	
	/**
	 * "importeurdarwincsv.log.controle".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public String recupererCleLogControle() {
		return "importeurdarwincsv.log.controle";
	} // Fin de recupererCleLogControle()._________________________________
	

	
} // FIN DE LA CLASSE ImporteurDarwinCsv.------------------------------------
