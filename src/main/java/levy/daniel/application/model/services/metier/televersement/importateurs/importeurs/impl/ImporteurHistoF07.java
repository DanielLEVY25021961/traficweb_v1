package levy.daniel.application.metier.importateurs.importeurs.impl;

import java.io.IOException;

import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.controleursimport.impl.ControleurImportHistoF07;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHistoF07;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.FactorySingletonImportateurDescription;
import levy.daniel.application.metier.importateurs.importeurs.AbstractImporteurAscii;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class ImporteurHistoF07 :<br/>
 * Importeur de fichiers HistoF07.<br/>
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
public class ImporteurHistoF07 extends AbstractImporteurAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_IMPORTEUR_HISTOF07 : String :<br/>
	 * "Classe ImporteurHistoF07".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_HISTOF07 
		= "Classe ImporteurHistoF07";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ImporteurHistoF07.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ImporteurHistoF07() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de Darwin csv au lieu de HistonatF07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * l'ordre des champs n'est pas jointif.<br/>
	 * les colonnes ne sont pas jointives.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 */
	public ImporteurHistoF07() 
			throws FichierNullException
			, TableauNullException
			, TableauVideException
			, ExceptionImport
			, IOException {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampHistoF07();
		
		/* Passe l'importateur de description. */
		this.importateurDescription 
			= FactorySingletonImportateurDescription
				.getImportateurDescriptionHistoF07();
		
		/* Passe le ControleurImport. */
		this.controleurImport = new ControleurImportHistoF07();
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
			
	
	/**
	 * "importeurhistof07.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurhistof07.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * "importeurhistof07.log.erreur".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurhistof07.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurHistoF07".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_HISTOF07;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurhistof07.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurhistof07.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurhistof07.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurhistof07.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurhistof07.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurhistof07.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurhistof07.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurhistof07.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 

	
	
	/**
	 * "importeurhistof07.log.controle".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public String recupererCleLogControle() {
		return "importeurhistof07.log.controle";
	} // Fin de recupererCleLogControle()._________________________________
	

	
} // FIN DE LA CLASSE ImporteurHistoF07.-------------------------------------
