package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionDarwinCsv;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHistoF07;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHit;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionMapping;

/**
 * class FactorySingletonImportateurDescription :<br/>
 * Factory chargée de fournir des Singletons d'ImportateurDescription 
 * après leur avoir fait lire la bonne description de fichier.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * singleton, Singleton,
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 6 juil. 2014
 *
 */
public final class FactorySingletonImportateurDescription {

	// ************************ATTRIBUTS************************************/

	/**
	 * impoDescHit : ImportateurDescriptionHit :<br/>
	 * Importateur de la description du HIT.<br/>
	 */
	private static transient ImportateurDescriptionHit impoDescHit;
	
	
	/**
	 * impoDescHistoF07 : ImportateurDescriptionHistoF07 :<br/>
	 * Importateur de la description de l'HistoF07.<br/>
	 */
	private static transient ImportateurDescriptionHistoF07 impoDescHistoF07;
	
	
	/**
	 * impoDarwinCsv : ImportateurDescriptionDarwinCsv :<br/>
	 * Importateur de la description du Darwin csv.<br/>
	 */
	private static transient ImportateurDescriptionDarwinCsv impoDarwinCsv;
	
	
	/**
	 * impoMapping : ImportateurDescriptionMapping :<br/>
	 * Importateur de la description du Mapping-Histo-Hit-Darwin-Isidor.<br/>
	 */
	private static transient ImportateurDescriptionMapping impoMapping;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactorySingletonImportateurDescription.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR FactorySingletonImportateurDescription() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private FactorySingletonImportateurDescription() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method getImportateurDescriptionHit() :<br/>
	 * Fournit un Singleton ImportateurDescriptionHit 
	 * après lui avoir fait lire un fichier de description.<br/>
	 * <br/>
	 *
	 * @return : ImportateurDescriptionHit.<br/>
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
	public static ImportateurDescriptionHit 
							getImportateurDescriptionHit() 
										throws FichierNullException
										, TableauNullException
										, TableauVideException
										, ExceptionImport
										, IOException {
		
		synchronized(FactorySingletonImportateurDescription.class) {
			
			if (impoDescHit == null) {
				
				/* Instanciation de l'importateur. */
				impoDescHit = new ImportateurDescriptionHit();
								
				/* Instanciation éventuelle du fichier 
				 * contenant la description et Import. */
				impoDescHit
					.importerDescription(
							ConfigurationApplicationManager
								.getFichierDescriptionHit());			
				
			}
			
			return impoDescHit;
		}
		
	} // Fin de getImportateurDescriptionHit().____________________________
	

	
	/**
	 * method getImportateurDescriptionHistoF07() :<br/>
	 * Fournit un Singleton d'ImportateurDescriptionHistoF07 
	 * après lui avoir fait lire un fichier de description.<br/>
	 * <br/>
	 *
	 * @return : ImportateurDescriptionHistoF07.<br/>
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
	public static ImportateurDescriptionHistoF07 
							getImportateurDescriptionHistoF07() 
										throws FichierNullException
										, TableauNullException
										, TableauVideException
										, ExceptionImport
										, IOException {
		
		synchronized(FactorySingletonImportateurDescription.class) {
			
			if (impoDescHistoF07 == null) {
				
				/* Instanciation de l'importateur. */
				impoDescHistoF07 = new ImportateurDescriptionHistoF07();
				
				/* Instanciation éventuelle du fichier 
				 * contenant la description et Import. */
				impoDescHistoF07
					.importerDescription(
							ConfigurationApplicationManager
								.getFichierDescriptionHistoF07());			
				
			}
			
			return impoDescHistoF07;
		}
		
	} // Fin de getImportateurDescriptionHistoF07()._______________________
	

	
	/**
	 * method getImportateurDescriptionDarwinCsv() :<br/>
	 * Fournit un Singleton d'ImportateurDescriptionDarwinCsv 
	 * après lui avoir fait lire un fichier de description.<br/>
	 * <br/>
	 *
	 * @return : ImportateurDescriptionDarwinCsv.<br/>
	 * 
	 *  @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de Darwin csv au lieu de HistonatF07 par exemple).<br/>
	 * le fichier de description passé en paramètre 
	 * pFileDescription n'a pas l'extension .csv.<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * l'ordre des champs n'est pas jointif.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 */
	public static ImportateurDescriptionDarwinCsv 
							getImportateurDescriptionDarwinCsv() 
								throws FichierNullException
								, TableauNullException
								, TableauVideException
								, ExceptionImport
									, IOException {

		synchronized (FactorySingletonImportateurDescription.class) {

			if (impoDarwinCsv == null) {

				/* Instanciation de l'importateur. */
				impoDarwinCsv = new ImportateurDescriptionDarwinCsv();

				/* Instanciation éventuelle du fichier 
				 * contenant la description et Import. */
				impoDarwinCsv
					.importerDescription(
							ConfigurationApplicationManager
								.getFichierDescriptionDarwinCsv());

			}

			return impoDarwinCsv;
		}

	} // Fin de getImportateurDescriptionDarwinCsv().______________________
	
	
	
	/**
	 * method getImportateurDescriptionMapping() :<br/>
	 * Fournit un Singleton d' ImportateurDescriptionMapping 
	 * après lui avoir fait lire un fichier de description.<br/>
	 * <br/>
	 *
	 * @return : ImportateurDescriptionMapping.<br/>
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
	public static ImportateurDescriptionMapping 
							getImportateurDescriptionMapping() 
								throws FichierNullException
								, TableauNullException
								, TableauVideException
								, ExceptionImport
									, IOException {
		
		
		synchronized (FactorySingletonImportateurDescription.class) {

			if (impoMapping == null) {

				/* Instanciation de l'importateur. */
				impoMapping = new ImportateurDescriptionMapping();

				/* Instanciation éventuelle du fichier 
				 * contenant la description et Import. */
				impoMapping
					.importerDescription(
							ConfigurationApplicationManager
								.getFichierDescriptionMapping());

			}

			return impoMapping;
			
		} // Fin de synchronized._________________________
		
	} // Fin de getImportateurDescriptionMapping().________________________

			
	
} // Fin de FactorySingletonImportateurDescription.--------------------------
