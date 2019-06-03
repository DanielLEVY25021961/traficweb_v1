package levy.daniel.application.metier.importateurs.importeurs.impl;

import java.io.IOException;

import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.controleursimport.impl.ControleurImportHit;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.FactorySingletonImportateurDescription;
import levy.daniel.application.metier.importateurs.importeurs.AbstractImporteurAscii;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class ImporteurHit :<br/>
 * Importeur de fichiers HIT.<br/>
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
public class ImporteurHit extends AbstractImporteurAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * CLASSE_IMPORTEUR_HIT : String :<br/>
	 * "Classe ImporteurHit".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_HIT 
		= "Classe ImporteurHit";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ImporteurHit.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ImporteurHit() :<br/>
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
	 * les colonnes ne sont pas jointives.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 */
	public ImporteurHit() 
			throws FichierNullException
			, TableauNullException
			, TableauVideException
			, ExceptionImport
			, IOException {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampHit();
		
		/* Passe l'importateur de description. */
		this.importateurDescription 
			= FactorySingletonImportateurDescription
				.getImportateurDescriptionHit();
		
		/* Passe le ControleurImport. */
		this.controleurImport = new ControleurImportHit();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
			
	
	/**
	 * {@inheritDoc}
	 * "importeurhit.niveau.anomalie".<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurhit.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * {@inheritDoc}
	 * "importeurhit.log.erreur".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurhit.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurHit".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_HIT;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurhit.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurhit.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurhit.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurhit.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurhit.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurhit.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurhit.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurhit.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 




	/**
	 * "importeurhit.log.controle".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public String recupererCleLogControle() {
		return "importeurhit.log.controle";
	} // Fin de recupererCleLogControle()._________________________________
	
	
	
} // FIN DE LA CLASSE ImporteurHit.------------------------------------------
