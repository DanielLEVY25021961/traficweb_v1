package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.io.File;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IFactoryNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

/**
 * class FactoryNomenclatureDarwinCsv :<br/>
 * Factory chargée de fournir les nomenclatures pour les Darwin csv.<br/>
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
 * @since 2 août 2014
 *
 */
public final class FactoryNomenclatureDarwinCsv implements IFactoryNomenclature {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_FACTORYNOMENCLATUREDARWIN : String :<br/>
	 * "CLASSE FactoryNomenclatureDarwinCsv".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREDARWIN 
		= "CLASSE FactoryNomenclatureDarwinCsv";
	
	
	/**
	 * METHODE_GETCLESPOSSIBLESSET : String :<br/>
	 * "Méthode getClesPossiblesSet(int pNumeroChamp)".<br/>
	 */
	public static final String METHODE_GETCLESPOSSIBLESSET 
		= "Méthode getClesPossiblesSet(int pNumeroChamp)";
	
	/**
	 * METHODE_GETNOMENCLATUREMAP : String :<br/>
	 * "Méthode getNomenclatureMap(int pNumeroChamp)".<br/>
	 */
	public static final String METHODE_GETNOMENCLATUREMAP 
		= "Méthode getNomenclatureMap(int pNumeroChamp)";
	
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
    
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	

	/**
	 * impoNomenclatureSensDarwin : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature du sens pour les Darwin csv.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureSensDarwin;

	
	/**
	 * setSens : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de sens.<br/>
	 */
	private static transient Set<Integer> setSens;
	
	
	/**
	 * nomenclatureMapSens : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le sens HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSens;
	

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactoryNomenclatureDarwinCsv.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR FactoryNomenclatureDarwinCsv() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public FactoryNomenclatureDarwinCsv() {
		
		super();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getClesPossiblesSet(
			final int pNumeroChamp) 
					throws Exception {
		
		Set<Integer> resultat = null;
		
		if (pNumeroChamp == 0) {
			return null;
		}
		
		synchronized (FactoryNomenclatureDarwinCsv.this) {
			
			switch (pNumeroChamp) {

			/* SENS. */
			case 13:
				
				if (impoNomenclatureSensDarwin == null) {					
					impoNomenclatureSensDarwin 
						= new ImporteurNomenclature();					
				}
				
				if (setSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureSensDarwinUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureDarwinSensUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureSensDarwinUtf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un DARWIN manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensDarwin
					.importerNomenclatureEnUtf8(
							fichierNomenclatureSensDarwinUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setSens 
						= impoNomenclatureSensDarwin
							.getClesPossiblesSet();
				}
				
				resultat = setSens;

				break;
			
			/* TYPE DE COMPTAGE. */	
			case 14:
				break;

			default:
				break;
			}
			
			return resultat;
			
		} // Fin de synchronized._________________________
		
	} // Fin de getClesPossiblesSet(
	// int pNumeroChamp).__________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<Integer, String> getNomenclatureMap(
										final int pNumeroChamp) 
												throws Exception {

		SortedMap<Integer, String> resultat = null;

		if (pNumeroChamp == 0) {
			return null;
		}

		synchronized (FactoryNomenclatureDarwinCsv.this) {

			switch (pNumeroChamp) {
			
			/* SENS. */
			case 13:

				if (impoNomenclatureSensDarwin == null) {					
					impoNomenclatureSensDarwin 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureSensDarwinUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureDarwinSensUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureSensDarwinUtf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un DARWIN manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensDarwin
					.importerNomenclatureEnUtf8(
							fichierNomenclatureSensDarwinUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapSens = impoNomenclatureSensDarwin
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapSens;

				break;
				
			/* TYPE DE COMPTAGE. */	
			case 14:
				break;

			default:
				break;
			}

			return resultat;

		} // Fin de synchronized._________________________

	} // Fin de getNomenclatureMap(
	// int pNumeroChamp).__________________________________________________


	
	/**
	 * method loggerFatal(
	 * String pMethode
	 * , String pMessage) :<br/>
	 * LOG.Fatal.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : méthode appelante.<br/>
	 * @param pMessage : String : Message circonstancié fourni 
	 * par la méthode appelante.<br/>
	 */
	private void loggerFatal(
			final String pMethode
				, final String pMessage) {
		
		synchronized (FactoryNomenclatureDarwinCsv.this) {
			
			String messageALogger = null;
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_FACTORYNOMENCLATUREDARWIN);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			messageALogger = stb.toString();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(messageALogger);
			}
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de loggerFatal(
	 // String pMethode
	 // , String pMessage).________________________________________________
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureDarwinCsv.--------------------------
