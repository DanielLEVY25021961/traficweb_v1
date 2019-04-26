package levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.IFactoryNomenclature;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.metier.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

/**
 * class FactoryNomenclatureHit :<br/>
 * Factory chargée de fournir les nomenclatures pour les HIT.<br/>
 * RESPONSABILITE : 
 * IMPORTE TOUTES LES NOMENCLATURES HIT 
 * ET LES MET A DISPOSITION DE L'APPLICATION.<br/>
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
public final class FactoryNomenclatureHit implements IFactoryNomenclature {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_FACTORYNOMENCLATUREHIT : String :<br/>
	 * "Classe FactoryNomenclatureHit".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREHIT 
		= "Classe FactoryNomenclatureHit";
	
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
	

	// SENS.************** 
	/**
	 * impoNomenclatureSensHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT pour le SENS.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureSensHit;

	
		
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
	

	// NATURE.*********	
	/**
	 * impoNomenclatureNatureHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT pour la nature.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureNatureHit;
	
	
	/**
	 * setNature : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de nature.<br/>
	 */
	private static transient Set<Integer> setNature;
	
	
	/**
	 * nomenclatureMapNature : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour la nature HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapNature;

	
	// CATEGORIE ADMINISTRATIVE DE LA ROUTE HIT.********	
	/**
	 * impoNomenclatureCatAdminRouteHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour la catégorie Administrative de la route HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureCatAdminRouteHit;
		
		
		
	/**
	 * setCatAdminRoute : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles de catégorie administrative 
	 * de la route HIT.<br/>
	 */
	private static transient Set<Integer> setCatAdminRoute;
		
		
	/**
	 * nomenclatureMapCatAdminRoute : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour la catégorie administrative 
	 * de la route HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapCatAdminRoute;
	
	
	// TYPE DE COMPTAGE HIT.********	
	/**
	 * impoNomenclatureTypeComptageHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour le type de comptage HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureTypeComptageHit;
		
		
	/**
	 * setTypeComptage : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles du type de comptage 
	 * HIT.<br/>
	 */
	private static transient Set<Integer> setTypeComptage;
		
		
	/**
	 * nomenclatureMapTypeComptage : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le type de comptage 
	 * HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptage;
	

	// CLASSEMENT DE LA ROUTE HIT.********	
	/**
	 * impoNomenclatureClassementRouteHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour le classement de la route HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClassementRouteHit;
		
		
	/**
	 * setClassementRoute : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles du type de comptage 
	 * HIT.<br/>
	 */
	private static transient Set<Integer> setClassementRoute;
		
		
	/**
	 * nomenclatureMapClassementRoute : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour le type de comptage 
	 * HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClassementRoute;

	
	// CLASSE DE LARGEUR DE CHAUSSEE UNIQUE HIT.********	
	/**
	 * impoNomenclatureClasseLargeurChausseeUHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour la classe de largeur de chaussée unique HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClasseLargeurChausseeUHit;
		
		
	/**
	 * setClasseLargeurChausseeU : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * de la classe de largeur de chaussée unique 
	 * HIT.<br/>
	 */
	private static transient Set<Integer> setClasseLargeurChausseeU;
		
		
	/**
	 * nomenclatureMapClasseLargeurChausseeU : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * la classe de largeur de chaussée unique 
	 * HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseeU;


	
	// CLASSE DE LARGEUR DE CHAUSSEES SEPAREES HIT.********	
	/**
	 * impoNomenclatureClasseLargeurChausseesSHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour la classe de largeur de chaussées séparées HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureClasseLargeurChausseesSHit;
		
		
	/**
	 * setClasseLargeurChausseesS : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * de la classe de largeur de chaussées séparées 
	 * HIT.<br/>
	 */
	private static transient Set<Integer> setClasseLargeurChausseesS;
		
		
	/**
	 * nomenclatureMapClasseLargeurChausseesS : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * la classe de largeur de chaussées séparées 
	 * HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseesS;



	
	// TYPE DE RESEAU HIT.********	
	/**
	 * impoNomenclatureTypeReseauHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour le type de réseau HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclatureTypeReseauHit;
		
		
	/**
	 * setTypeReseau : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * du type de réseau HIT.<br/>
	 */
	private static transient Set<Integer> setTypeReseau;
		
		
	/**
	 * nomenclatureMapTypeReseau : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * le type de réseau HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeReseau;


	
	// TYPE PR/PK HIT.********	
	/**
	 * impoNomenclaturePrPkHit : IImporteurNomenclature :<br/>
	 * importeur de la Nomenclature HIT 
	 * pour le type PR/PK HIT.<br/>
	 */
	private static transient IImporteurNomenclature impoNomenclaturePrPkHit;
		
		
	/**
	 * setPrPk : Set&lt;Integer&gt; :<br/>
	 * Set contenant les valeurs possibles 
	 * du type PR/PK HIT.<br/>
	 */
	private static transient Set<Integer> setPrPk;
		
		
	/**
	 * nomenclatureMapPrPk : SortedMap&lt;Integer,String&gt; :<br/>
	 * Nomenclature sous forme de Map pour 
	 * le type PR/PK HIT.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapPrPk;


	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactoryNomenclatureHit.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR FactoryNomenclatureHit() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public FactoryNomenclatureHit() {
		
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
		
		synchronized (FactoryNomenclatureHit.this) {
			
			switch (pNumeroChamp) {

			/* SENS. */
			case 3:
			
				if (impoNomenclatureSensHit == null) {					
					impoNomenclatureSensHit 
						= new ImporteurNomenclature();					
				}
				
				if (setSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureSensHitUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHitSensUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureSensHitUtf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensHit
					.importerNomenclatureEnUtf8(
							fichierNomenclatureSensHitUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setSens 
						= impoNomenclatureSensHit
							.getClesPossiblesSet();
				}
				
				resultat = setSens;

				break;
			
			/* NATURE. */	
			case 4:
				
				if (impoNomenclatureNatureHit == null) {					
					impoNomenclatureNatureHit = new ImporteurNomenclature();					
				}
				
				if (setNature == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureNatureHitUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitNatureUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureNatureHitUtf8 == null) {
						
						final String message 
							= "Nomenclature de la NATURE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureNatureHit
					.importerNomenclatureEnUtf8(
							fichierNomenclatureNatureHitUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setNature = impoNomenclatureNatureHit.getClesPossiblesSet();
				}
				
				resultat = setNature;
				
				break;
				
			/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
			case 11:
				
				if (impoNomenclatureCatAdminRouteHit == null) {					
					impoNomenclatureCatAdminRouteHit = new ImporteurNomenclature();					
				}
				
				if (setCatAdminRoute == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitCatAdminRouteUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitCatAdminRouteUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitCatAdminRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CATEGORIE ADMINISTRATIVE DE LA ROUTE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureCatAdminRouteHit
					.importerNomenclatureEnUtf8(
							fichierNomenclatureHitCatAdminRouteUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setCatAdminRoute 
						= impoNomenclatureCatAdminRouteHit
							.getClesPossiblesSet();
				}
				
				resultat = setCatAdminRoute;
				
				break;
				
			/* TYPE DE COMPTAGE. */
			case 12:
				
				if (impoNomenclatureTypeComptageHit == null) {					
					impoNomenclatureTypeComptageHit = new ImporteurNomenclature();					
				}
				
				if (setTypeComptage == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitTypeComptageUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitTypeComptageUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitTypeComptageUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE COMPTAGE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeComptageHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitTypeComptageUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setTypeComptage 
						= impoNomenclatureTypeComptageHit
							.getClesPossiblesSet();
				}
				
				resultat = setTypeComptage;
				
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 13:
				
				if (impoNomenclatureClassementRouteHit == null) {					
					impoNomenclatureClassementRouteHit = new ImporteurNomenclature();					
				}
				
				if (setClassementRoute == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClassementRouteUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitClassementRouteUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClassementRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature du CLASSEMENT DE LA ROUTE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClassementRouteHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClassementRouteUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClassementRoute 
						= impoNomenclatureClassementRouteHit
							.getClesPossiblesSet();
				}
				
				resultat = setClassementRoute;
				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
			case 14:
				
				if (impoNomenclatureClasseLargeurChausseeUHit == null) {					
					impoNomenclatureClasseLargeurChausseeUHit = new ImporteurNomenclature();					
				}
				
				if (setClasseLargeurChausseeU == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClasseLargeurChausseeUUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitClasseLargeurChausseeUUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClasseLargeurChausseeUUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */					
					impoNomenclatureClasseLargeurChausseeUHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClasseLargeurChausseeUUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClasseLargeurChausseeU 
						= impoNomenclatureClasseLargeurChausseeUHit
							.getClesPossiblesSet();
				}
				
				resultat = setClasseLargeurChausseeU;
				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
			case 15:
				
				if (impoNomenclatureClasseLargeurChausseesSHit == null) {					
					impoNomenclatureClasseLargeurChausseesSHit = new ImporteurNomenclature();					
				}
				
				if (setClasseLargeurChausseesS == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClasseLargeurChausseesSUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitClasseLargeurChausseesSUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClasseLargeurChausseesSUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */					
					impoNomenclatureClasseLargeurChausseesSHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClasseLargeurChausseesSUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setClasseLargeurChausseesS 
						= impoNomenclatureClasseLargeurChausseesSHit
							.getClesPossiblesSet();
				}
				
				resultat = setClasseLargeurChausseesS;
				
				break;
				
			/* TYPE DE RESEAU. */
			case 16:
				
				if (impoNomenclatureTypeReseauHit == null) {					
					impoNomenclatureTypeReseauHit = new ImporteurNomenclature();					
				}
				
				if (setTypeReseau == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitTypeReseauUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitTypeReseauUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitTypeReseauUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE RESEAU dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */										
					impoNomenclatureTypeReseauHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitTypeReseauUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setTypeReseau 
						= impoNomenclatureTypeReseauHit
							.getClesPossiblesSet();
					
				}
				
				resultat = setTypeReseau;
				
				break;
				
			/* TYPE PR/PK. */
			case 17:
				
				if (impoNomenclaturePrPkHit == null) {					
					impoNomenclaturePrPkHit = new ImporteurNomenclature();					
				}
				
				if (setPrPk == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitPrPkUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitPrPkUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitPrPkUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE PR/PK dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETCLESPOSSIBLESSET, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */															
					impoNomenclaturePrPkHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitPrPkUtf8);
					
					/* Obtention du Set des valeurs possibles. */
					setPrPk 
						= impoNomenclaturePrPkHit
							.getClesPossiblesSet();
				}
				
				resultat = setPrPk;
				
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

		synchronized (FactoryNomenclatureHit.this) {

			switch (pNumeroChamp) {
			
			/* SENS. */
			case 3:

				if (impoNomenclatureSensHit == null) {					
					impoNomenclatureSensHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapSens == null) {
					
					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureSensHitUtf8 
					= ConfigurationApplicationManager
						.getFichierNomenclatureHitSensUtf8();
				
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureSensHitUtf8 == null) {
						
						final String message 
							= "Nomenclature du SENS dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureSensHit
					.importerNomenclatureEnUtf8(
							fichierNomenclatureSensHitUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapSens = impoNomenclatureSensHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapSens;

				break;
				
			/* NATURE*/	
			case 4:
				
				if (impoNomenclatureNatureHit == null) {					
					impoNomenclatureNatureHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapNature == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitNatureUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitNatureUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitNatureUtf8 == null) {
						
						final String message 
							= "Nomenclature de la NATURE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureNatureHit
					.importerNomenclatureEnUtf8(
							fichierNomenclatureHitNatureUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapNature 
						= impoNomenclatureNatureHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapNature;

				break;

			/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
			case 11:
				
				if (impoNomenclatureCatAdminRouteHit == null) {					
					impoNomenclatureCatAdminRouteHit = new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapCatAdminRoute == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitCatAdminRouteUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitCatAdminRouteUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitCatAdminRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CATEGORIE ADMINISTRATIVE DE LA ROUTE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureCatAdminRouteHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitCatAdminRouteUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapCatAdminRoute 
						= impoNomenclatureCatAdminRouteHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapCatAdminRoute;
			
				break;
			
			/* TYPE DE COMPTAGE. */
			case 12:
				
				if (impoNomenclatureTypeComptageHit == null) {					
					impoNomenclatureTypeComptageHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapTypeComptage == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitTypeComptageUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitTypeComptageUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitTypeComptageUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE COMPTAGE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeComptageHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitTypeComptageUtf8);
					
					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapTypeComptage 
						= impoNomenclatureTypeComptageHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapTypeComptage;
			
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 13:
				
				if (impoNomenclatureClassementRouteHit == null) {					
					impoNomenclatureClassementRouteHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClassementRoute == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClassementRouteUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitClassementRouteUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClassementRouteUtf8 == null) {
						
						final String message 
							= "Nomenclature du CLASSEMENT DE LA ROUTE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClassementRouteHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClassementRouteUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClassementRoute 
						= impoNomenclatureClassementRouteHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClassementRoute;
			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEE UNIQUE. */
			case 14:
				
				if (impoNomenclatureClasseLargeurChausseeUHit == null) {					
					impoNomenclatureClasseLargeurChausseeUHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClasseLargeurChausseeU == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClasseLargeurChausseeUUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitClasseLargeurChausseeUUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClasseLargeurChausseeUUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClasseLargeurChausseeUHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClasseLargeurChausseeUUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClasseLargeurChausseeU 
						= impoNomenclatureClasseLargeurChausseeUHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClasseLargeurChausseeU;
			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEES SEPAREES. */
			case 15:
				
				if (impoNomenclatureClasseLargeurChausseesSHit == null) {					
					impoNomenclatureClasseLargeurChausseesSHit 
						= new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapClasseLargeurChausseesS == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitClasseLargeurChausseesSUtf8 
						= ConfigurationApplicationManager
						.getFichierNomenclatureHitClasseLargeurChausseesSUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitClasseLargeurChausseesSUtf8 == null) {
						
						final String message 
							= "Nomenclature de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureClasseLargeurChausseesSHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitClasseLargeurChausseesSUtf8);
					
					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapClasseLargeurChausseesS 
						= impoNomenclatureClasseLargeurChausseesSHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapClasseLargeurChausseesS;
			
				break;
				
			/* TYPE DE RESEAU. */
			case 16:
				
				if (impoNomenclatureTypeReseauHit == null) {					
					impoNomenclatureTypeReseauHit = new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapTypeReseau == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitTypeReseauUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHitTypeReseauUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitTypeReseauUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE DE RESEAU dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclatureTypeReseauHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitTypeReseauUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapTypeReseau 
						= impoNomenclatureTypeReseauHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapTypeReseau;
			
				break;
				
			/* TYPE PR/PK. */
			case 17:
				
				if (impoNomenclaturePrPkHit == null) {					
					impoNomenclaturePrPkHit = new ImporteurNomenclature();					
				}
				
				if (nomenclatureMapPrPk == null) {

					/* Récupération du fichier de nomenclature. */
					final File fichierNomenclatureHitPrPkUtf8 
						= ConfigurationApplicationManager
							.getFichierNomenclatureHitPrPkUtf8();
					
					/* LOG.FATAL et jette une RunTimeException 
					 * si la nomenclature est absente. */
					if (fichierNomenclatureHitPrPkUtf8 == null) {
						
						final String message 
							= "Nomenclature du TYPE PR/PK dans un HIT manquante";
						
						this.loggerFatal(METHODE_GETNOMENCLATUREMAP, message);
						
						throw new RuntimeException(message);
					}
					
					/* Import de la nomenclature. */
					impoNomenclaturePrPkHit
						.importerNomenclatureEnUtf8(
								fichierNomenclatureHitPrPkUtf8);

					/* Obtention de la Map des valeurs possibles. */
					nomenclatureMapPrPk 
						= impoNomenclaturePrPkHit
							.getNomenclatureMap();
				}

				resultat = nomenclatureMapPrPk;
			
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
		
		synchronized (FactoryNomenclatureHit.this) {
			
			String messageALogger = null;
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_FACTORYNOMENCLATUREHIT);
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
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureHit.--------------------------------
