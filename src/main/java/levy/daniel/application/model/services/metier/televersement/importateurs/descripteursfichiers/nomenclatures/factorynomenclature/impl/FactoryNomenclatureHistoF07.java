package levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.IFactoryNomenclature;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class FactoryNomenclatureHistoF07 :<br/>
 * Factory chargée de fournir les nomenclatures pour les HISTONATF07.<br/>
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
public final class FactoryNomenclatureHistoF07 
						implements IFactoryNomenclature {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_FACTORYNOMENCLATUREHISTOF07 : String :<br/>
	 * "Classe FactoryNomenclatureHistoF07".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREHISTOF07 
		= "Classe FactoryNomenclatureHistoF07";
	
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
	

	// SENS.
	/**
	 * impoNomenclatureSensHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTONATF07 pour le sens.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureSensHistoF07;

	
	/**
	 * setSens : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de sens 
	 * dans un HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setSens;
	
	
	/**
	 * nomenclatureMapSens : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le sens HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSens;
	

	// NATURE.	
	/**
	 * impoNomenclatureNatureHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTONATF07 pour la nature.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureNatureHistoF07;
		
	
	/**
	 * setNature : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de nature 
	 * dans un HISTO_F07..<br/>
	 */
	private static transient Set<Integer> setNature;
	
	
	/**
	 * nomenclatureMapNature : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour la nature HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapNature;

	
	// TYPE DE COMPTAGE HISTO_F07.********	
	/**
	 * impoNomenclatureTypeComptageHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTO_F07 
	 * pour le type de comptage HISTO_F07.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureTypeComptageHistoF07;
		
		
	/**
	 * setTypeComptage : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles du type de comptage 
	 * HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setTypeComptage;
		
		
	/**
	 * nomenclatureMapTypeComptage : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le type de comptage 
	 * HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptage;
	

	// CLASSEMENT DE LA ROUTE HISTO_F07.********	
	/**
	 * impoNomenclatureClassementRouteHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTO_F07 
	 * pour le classement de la route HISTO_F07.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClassementRouteHistoF07;
		
		
	/**
	 * setClassementRoute : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles du type de comptage 
	 * HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setClassementRoute;
		
		
	/**
	 * nomenclatureMapClassementRoute : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le type de comptage 
	 * HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClassementRoute;

	
	// CLASSE DE LARGEUR DE CHAUSSEE UNIQUE HISTO_F07.********	
	/**
	 * impoNomenclatureClasseLargeurChausseeUHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTO_F07 
	 * pour la classe de largeur de chaussée unique HISTO_F07.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClasseLargeurChausseeUHistoF07;
		
		
	/**
	 * setClasseLargeurChausseeU : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * de la classe de largeur de chaussée unique 
	 * HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setClasseLargeurChausseeU;
		
		
	/**
	 * nomenclatureMapClasseLargeurChausseeU : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * la classe de largeur de chaussée unique 
	 * HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseeU;


	

	// CLASSE DE LARGEUR DE CHAUSSEES SEPAREES.
	/**
	 * impoNomenclatureClasseLargeurChausseesSHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTONATF07 
	 * pour la classe de largeur de chaussées séparées.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClasseLargeurChausseesSHistoF07;

		
	/**
	 * setClasseLargeurChausseesS : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de 
	 * classe de largeur de chaussées séparées 
	 * dans un HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setClasseLargeurChausseesS;

	/**
	 * nomenclatureMapClasseLargeurChausseesS : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour la nature 
	 * dans un HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseesS;
	
	
	// TYPE DE RESEAU HISTO_F07.********	
	/**
	 * impoNomenclatureTypeReseauHistoF07 : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HISTO_F07 
	 * pour le type de réseau HISTO_F07.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureTypeReseauHistoF07;
		
		
	/**
	 * setTypeReseau : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * du type de réseau HISTO_F07.<br/>
	 */
	private static transient Set<Integer> setTypeReseau;
		
		
	/**
	 * nomenclatureMapTypeReseau : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * le type de réseau HISTO_F07.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeReseau;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactoryNomenclatureHistoF07.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR FactoryNomenclatureHistoF07() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public FactoryNomenclatureHistoF07() {
		
		super();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getClesPossiblesSet(
			final int pNumeroChamp) 
					throws FichierNullException
					, FichierVideException
					, FichierInexistantException
					, FichierPasNormalException
					, IOException {
		
		Set<Integer> resultat = null;
		
		if (pNumeroChamp == 0) {
			return null;
		}
		
		synchronized (FactoryNomenclatureHistoF07.this) {
			
			switch (pNumeroChamp) {

			/* SENS. */
			case 3:
			
				if (impoNomenclatureSensHistoF07 == null) {
					impoNomenclatureSensHistoF07 
						= new ImporteurNomenclature();	
				}
				
				if (setSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07SensUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHistoF07SensUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07SensUtf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07SensUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setSens 
						= impoNomenclatureSensHistoF07
									.getClesPossiblesSet();
				}

				resultat = setSens;

				break;
			
			/* NATURE. */	
			case 4:
				
				if (impoNomenclatureNatureHistoF07 == null) {					
					impoNomenclatureNatureHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setNature == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07NatureUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHistoF07NatureUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07NatureUtf8 == null) {
						
						final String message 
							= "Nomenclature de la NATURE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */					
					impoNomenclatureNatureHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07NatureUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setNature 
						= impoNomenclatureNatureHistoF07
									.getClesPossiblesSet();
				}
				
				resultat = setNature;
				
				break;
			
			/* TYPE DE COMPTAGE. */
			case 9:
				
				if (impoNomenclatureTypeComptageHistoF07 == null) {					
					impoNomenclatureTypeComptageHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setTypeComptage == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07TypeComptageUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07TypeComptageUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07TypeComptageUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE COMPTAGE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeComptageHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07TypeComptageUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setTypeComptage 
						= impoNomenclatureTypeComptageHistoF07
							.getClesPossiblesSet();
				}
				
				resultat = setTypeComptage;
				
				break;
			
			/* CLASSEMENT DE LA ROUTE. */
			case 10:
								
				if (impoNomenclatureClassementRouteHistoF07 == null) {					
					impoNomenclatureClassementRouteHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setClassementRoute == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClassementRouteUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClassementRouteUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClassementRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature du CLASSEMENT DE LA ROUTE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClassementRouteHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClassementRouteUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClassementRoute 
						= impoNomenclatureClassementRouteHistoF07
							.getClesPossiblesSet();
					
				}
				
				resultat = setClassementRoute;
				
				break;
			
			/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
			case 11:
				
				if (impoNomenclatureClasseLargeurChausseeUHistoF07 == null) {					
					impoNomenclatureClasseLargeurChausseeUHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setClasseLargeurChausseeU == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */					
					impoNomenclatureClasseLargeurChausseeUHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClasseLargeurChausseeU 
						= impoNomenclatureClasseLargeurChausseeUHistoF07
							.getClesPossiblesSet();
				}
				
				resultat = setClasseLargeurChausseeU;
				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */	
			case 12:
				
				if (impoNomenclatureClasseLargeurChausseesSHistoF07 == null) {					
					impoNomenclatureClasseLargeurChausseesSHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setClasseLargeurChausseesS == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */					
					impoNomenclatureClasseLargeurChausseesSHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClasseLargeurChausseesS 
						= impoNomenclatureClasseLargeurChausseesSHistoF07
							.getClesPossiblesSet();
				}
				
				resultat = setClasseLargeurChausseesS;
				
				break;

			/* TYPE DE RESEAU. */
			case 13:
				
				if (impoNomenclatureTypeReseauHistoF07 == null) {					
					impoNomenclatureTypeReseauHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (setTypeReseau == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07TypeReseauUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07TypeReseauUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07TypeReseauUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE RESEAU dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */										
					impoNomenclatureTypeReseauHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07TypeReseauUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setTypeReseau 
						= impoNomenclatureTypeReseauHistoF07
							.getClesPossiblesSet();
					
				}
				
				resultat = setTypeReseau;
				
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
												throws FichierNullException
												, FichierVideException
												, FichierInexistantException
												, FichierPasNormalException
												, IOException {

		SortedMap<Integer, String> resultat = null;

		if (pNumeroChamp == 0) {
			return null;
		}

		synchronized (FactoryNomenclatureHistoF07.this) {

			switch (pNumeroChamp) {
			
			/* SENS. */
			case 3:

				if (impoNomenclatureSensHistoF07 == null) {					
					impoNomenclatureSensHistoF07 = new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureSensHistoF07Utf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07SensUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureSensHistoF07Utf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensHistoF07
					.importerNomenclatureEnUtf8(
							fichierNomenclatureSensHistoF07Utf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapSens = impoNomenclatureSensHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapSens;

				break;
				
			/* NATURE. */	
			case 4:
								
				if (impoNomenclatureNatureHistoF07 == null) {					
					impoNomenclatureNatureHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapNature == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07NatureUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07NatureUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07NatureUtf8 == null) {
						
						final String message 
							= "Nomenclature de la NATURE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureNatureHistoF07
					.importerNomenclatureEnUtf8(
							fichierNomenclatureHistoF07NatureUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapNature 
						= impoNomenclatureNatureHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapNature;
				
				break;
				
			/* TYPE DE COMPTAGE. */
			case 9:
				
				if (impoNomenclatureTypeComptageHistoF07 == null) {					
					impoNomenclatureTypeComptageHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapTypeComptage == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07TypeComptageUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07TypeComptageUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07TypeComptageUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE COMPTAGE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeComptageHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07TypeComptageUtf8);
					
					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapTypeComptage 
						= impoNomenclatureTypeComptageHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapTypeComptage;
			
				break;
			
			/* CLASSEMENT DE LA ROUTE. */
			case 10:
				
				if (impoNomenclatureClassementRouteHistoF07 == null) {					
					impoNomenclatureClassementRouteHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClassementRoute == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClassementRouteUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClassementRouteUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClassementRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature du CLASSEMENT DE LA ROUTE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClassementRouteHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClassementRouteUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClassementRoute 
						= impoNomenclatureClassementRouteHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClassementRoute;
			
				break;

			/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
			case 11:
				
				if (impoNomenclatureClasseLargeurChausseeUHistoF07 == null) {					
					impoNomenclatureClasseLargeurChausseeUHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClasseLargeurChausseeU == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClasseLargeurChausseeUHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClasseLargeurChausseeU 
						= impoNomenclatureClasseLargeurChausseeUHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClasseLargeurChausseeU;
			
				break;
			
			/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */	
			case 12:
				
				if (impoNomenclatureClasseLargeurChausseesSHistoF07 == null) {					
					impoNomenclatureClasseLargeurChausseesSHistoF07 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClasseLargeurChausseesS == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClasseLargeurChausseesSHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8);
					
					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClasseLargeurChausseesS 
						= impoNomenclatureClasseLargeurChausseesSHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClasseLargeurChausseesS;
			
				break;
			
			/* TYPE DE RESEAU. */
			case 13:
				
				if (impoNomenclatureTypeReseauHistoF07 == null) {					
					impoNomenclatureTypeReseauHistoF07 = new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapTypeReseau == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHistoF07TypeReseauUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHistoF07TypeReseauUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHistoF07TypeReseauUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE RESEAU dans un HISTO_F07 manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeReseauHistoF07
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHistoF07TypeReseauUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapTypeReseau 
						= impoNomenclatureTypeReseauHistoF07
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapTypeReseau;
			
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
		
		synchronized (FactoryNomenclatureHistoF07.this) {
			
			String messageALogger = null;
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_FACTORYNOMENCLATUREHISTOF07);
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
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureHistoF07.---------------------------
