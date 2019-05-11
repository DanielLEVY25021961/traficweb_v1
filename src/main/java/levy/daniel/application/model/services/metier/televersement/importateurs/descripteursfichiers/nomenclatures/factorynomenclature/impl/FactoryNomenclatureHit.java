package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures.ConfigurationNomenclaturesHitManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.IFactoryNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

/**
 * CLASSE FactoryNomenclatureHit :<br/>
 * <p>
 * Factory chargée de fournir les nomenclatures 
 * pour les fichiers HIT.
 * </p>
 * 
 * <p>
 * RESPONSABILITE : 
 * IMPORTER TOUTES LES NOMENCLATURES HIT 
 * ET LES METTRE A DISPOSITION DE L'APPLICATION sous forme de 
 * <b>SINGLETONS</b>.
 * </p>
 * <p>
 * Une nomenclature est un ensemble de [clé - libellé] pouvant être prises 
 * par une variable comme par exemple pour le sens HIT :<br/>
 * <ul>
 * <li>1 - sens P.R. croissants route à 2 sens.</li>
 * <li>2 - sens P.R. décroissants route à 2 sens.</li>
 * <li>3 - Cumul des deux sens route à 2 sens.</li>
 * <li>4 - Sens P.R. croissants route à sens unique.</li>
 * <li>5 - Sens P.R. décroissants route à sens unique.</li>
 * </ul>
 * </p>
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
	 * "Classe FactoryNomenclatureHit".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREHIT 
		= "Classe FactoryNomenclatureHit";
	
	/**
	 * "Méthode getClesPossiblesSet(int pNumeroChamp)".<br/>
	 */
	public static final String METHODE_GETCLESPOSSIBLESSET 
		= "Méthode getClesPossiblesSet(int pNumeroChamp)";
	
	/**
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
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSens;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSens;
	
	// NATURE DU COMPTAGE (1 pour tous véhicules).*********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de NATURE DU COMPTAGE pour 
	 * les fichiers HIT (1 pour tous véhicules).<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesNature;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la NATURE DU COMPTAGE dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapNature;
	
	// CATEGORIE ADMINISTRATIVE DE LA ROUTE HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CATEGORIE ADMINISTRATIVE 
	 * DE LA ROUTE pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesCatAdminRoute;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CATEGORIE ADMINISTRATIVE DE LA ROUTE
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapCatAdminRoute;
		
	// TYPE DE COMPTAGE HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptage;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptage;
	
	// CLASSEMENT DE LA ROUTE HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClassementRoute;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClassementRoute;
	
	// CLASSE DE LARGEUR DE CHAUSSEE UNIQUE HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClasseLargeurChausseeU;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseeU;
	
	// CLASSE DE LARGEUR DE CHAUSSEES SEPAREES HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClasseLargeurChausseesS;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseesS;
	
	// TYPE DE RESEAU HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE RESEAU 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeReseau;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE RESEAU 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeReseau;
	
	// TYPE PR/PK HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de PR/PK 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesPrPk;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le type PR/PK 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapPrPk;

	// SENS SECTION RATTACHEMENT.************** 		
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS DE LA SECTION DE RATTACHEMENT
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSensRattachement;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS DE LA SECTION DE RATTACHEMENT
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSensRattachement;

	// SENS SECTION LIMITROPHE.************** 		
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS DE LA SECTION LIMITROPHE
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSensLimitrophe;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS DE LA SECTION LIMITROPHE
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSensLimitrophe;

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactoryNomenclatureHit.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
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
					throws Exception {
		
		Set<Integer> resultat = null;
		
		if (pNumeroChamp == 0) {
			return null;
		}
		
		synchronized (FactoryNomenclatureHit.this) {
			
			switch (pNumeroChamp) {

			/* SENS. */
			case 3:
			
				resultat = getSetClesPossiblesSens();
				break;
			
			/* NATURE. */	
			case 4:
				
				resultat = getSetClesPossiblesNature();				
				break;
				
			/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
			case 11:
								
				resultat = getSetClesPossiblesCatAdminRoute();				
				break;
				
			/* TYPE DE COMPTAGE. */
			case 12:
				
				resultat = getSetClesPossiblesTypeComptage();				
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 13:
				
				resultat = getSetClesPossiblesClassementRoute();				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
			case 14:
				
				resultat = getSetClesPossiblesClasseLargeurChausseeU();				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
			case 15:
				
				resultat = getSetClesPossiblesClasseLargeurChausseesS();				
				break;
				
			/* TYPE DE RESEAU. */
			case 16:
				
				resultat = getSetClesPossiblesTypeReseau();				
				break;
				
			/* TYPE PR/PK. */
			case 17:
				
				resultat = getSetClesPossiblesPrPk();				
				break;
				
			/* SENS DE LA SECTION DE RATTACHEMENT. */
			case 31:
				
				resultat = getSetClesPossiblesSensRattachement();				
				break;
				
			/* SENS DE LA SECTION LIMITROPHE. */
			case 34:
					
				resultat = getSetClesPossiblesSensLimitrophe();				
				break;
				
			default:
				break;
			}
			
			return resultat;
			
		} // Fin de synchronized._________________________
		
	} // Fin de getClesPossiblesSet(...).__________________________________
	
	

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

		synchronized (FactoryNomenclatureHit.this) {

			switch (pNumeroChamp) {
			
			/* SENS. */
			case 3:

				resultat = getNomenclatureMapSens();
				break;
				
			/* NATURE*/	
			case 4:
				
				resultat = getNomenclatureMapNature();
				break;

			/* CATEGORIE ADMINISTRATIVE DE LA ROUTE. */
			case 11:
				
				resultat = getNomenclatureMapCatAdminRoute();			
				break;
			
			/* TYPE DE COMPTAGE. */
			case 12:
				
				resultat = getNomenclatureMapTypeComptage();			
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 13:

				resultat = getNomenclatureMapClassementRoute();			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEE UNIQUE. */
			case 14:
				
				resultat = getNomenclatureMapClasseLargeurChausseeU();			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEES SEPAREES. */
			case 15:
				
				resultat = getNomenclatureMapClasseLargeurChausseesS();			
				break;
				
			/* TYPE DE RESEAU. */
			case 16:

				resultat = getNomenclatureMapTypeReseau();			
				break;
				
			/* TYPE PR/PK. */
			case 17:

				resultat = getNomenclatureMapPrPk();			
				break;
				
			/* SENS DE LA SECTION DE RATTACHEMENT. */
			case 31:
				
				resultat = getNomenclatureMapSensRattachement();				
				break;
				
			/* SENS DE LA SECTION LIMITROPHE. */
			case 34:
				
				resultat = getNomenclatureMapSensLimitrophe();				
				break;
				
			default:
				break;
			}

			return resultat;

		} // Fin de synchronized._________________________

	} // Fin de getNomenclatureMap(...).___________________________________


		
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesSens : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesSens() 
													throws Exception {
		
		if (setClesPossiblesSens == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return setClesPossiblesSens;
		
	} // Fin de getSetClesPossiblesSens()._________________________________

	
		
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapSens : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, String> getNomenclatureMapSens() 
														throws Exception {
		
		if (nomenclatureMapSens == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return nomenclatureMapSens;
		
	} // Fin de getNomenclatureMapSens().__________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de NATURE DU COMPTAGE pour 
	 * les fichiers HIT (1 pour tous véhicules).<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesNature : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesNature() 
													throws Exception {
				
		if (setClesPossiblesNature == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitNatureUtf8());
			
			setClesPossiblesNature = importeur.getClesPossiblesSet();
			nomenclatureMapNature = importeur.getNomenclatureMap();
		}

		return setClesPossiblesNature;
		
	} // Fin de getSetClesPossiblesNature()._______________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la NATURE DU COMPTAGE dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapNature : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapNature() 
															throws Exception {
		
		if (nomenclatureMapNature == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitNatureUtf8());
			
			setClesPossiblesNature = importeur.getClesPossiblesSet();
			nomenclatureMapNature = importeur.getNomenclatureMap();
		}

		return nomenclatureMapNature;
		
	} // Fin de getNomenclatureMapNature().________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CATEGORIE ADMINISTRATIVE 
	 * DE LA ROUTE pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesCatAdminRoute : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesCatAdminRoute() 
													throws Exception {
				
		if (setClesPossiblesCatAdminRoute == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitCatAdminRouteUtf8());
			
			setClesPossiblesCatAdminRoute = importeur.getClesPossiblesSet();
			nomenclatureMapCatAdminRoute = importeur.getNomenclatureMap();
		}

		return setClesPossiblesCatAdminRoute;
		
	} // Fin de getSetClesPossiblesCatAdminRoute().________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CATEGORIE ADMINISTRATIVE DE LA ROUTE
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapCatAdminRoute : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapCatAdminRoute() 
															throws Exception {
		
		if (nomenclatureMapCatAdminRoute == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitCatAdminRouteUtf8());
			
			setClesPossiblesCatAdminRoute = importeur.getClesPossiblesSet();
			nomenclatureMapCatAdminRoute = importeur.getNomenclatureMap();
		}

		return nomenclatureMapCatAdminRoute;
		
	} // Fin de getNomenclatureMapCatAdminRoute()._________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesTypeComptage : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptage() 
													throws Exception {
				
		if (setClesPossiblesTypeComptage == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitTypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return setClesPossiblesTypeComptage;
		
	} // Fin de getSetClesPossiblesTypeComptage().________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapTypeComptage : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptage() 
															throws Exception {
		
		if (nomenclatureMapTypeComptage == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitTypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return nomenclatureMapTypeComptage;
		
	} // Fin de getNomenclatureMapTypeComptage().__________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesClassementRoute : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesClassementRoute() 
													throws Exception {
				
		if (setClesPossiblesClassementRoute == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClassementRoute;
		
	} // Fin de getSetClesPossiblesClassementRoute().______________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapClassementRoute : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapClassementRoute() 
															throws Exception {
		
		if (nomenclatureMapClassementRoute == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClassementRoute;
		
	} // Fin de getNomenclatureMapClassementRoute()._______________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesClasseLargeurChausseeU : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesClasseLargeurChausseeU() 
													throws Exception {
				
		if (setClesPossiblesClasseLargeurChausseeU == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClasseLargeurChausseeUUtf8());
			
			setClesPossiblesClasseLargeurChausseeU = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseeU = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClasseLargeurChausseeU;
		
	} // Fin de getSetClesPossiblesClasseLargeurChausseeU()._______________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapClasseLargeurChausseeU : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapClasseLargeurChausseeU() 
															throws Exception {
		
		if (nomenclatureMapClasseLargeurChausseeU == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClasseLargeurChausseeUUtf8());
			
			setClesPossiblesClasseLargeurChausseeU = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseeU = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClasseLargeurChausseeU;
		
	} // Fin de getNomenclatureMapClasseLargeurChausseeU().________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesClasseLargeurChausseesS : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesClasseLargeurChausseesS() 
													throws Exception {
				
		if (setClesPossiblesClasseLargeurChausseesS == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClasseLargeurChausseesSUtf8());
			
			setClesPossiblesClasseLargeurChausseesS = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseesS = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClasseLargeurChausseesS;
		
	} // Fin de getSetClesPossiblesClasseLargeurChausseesS().______________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapClasseLargeurChausseesS : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapClasseLargeurChausseesS() 
															throws Exception {
		
		if (nomenclatureMapClasseLargeurChausseesS == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitClasseLargeurChausseesSUtf8());
			
			setClesPossiblesClasseLargeurChausseesS = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseesS = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClasseLargeurChausseesS;
		
	} // Fin de getNomenclatureMapClasseLargeurChausseesS()._______________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE RESEAU 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesTypeReseau : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeReseau() 
													throws Exception {
				
		if (setClesPossiblesTypeReseau == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitTypeReseauUtf8());
			
			setClesPossiblesTypeReseau = importeur.getClesPossiblesSet();
			nomenclatureMapTypeReseau = importeur.getNomenclatureMap();
		}

		return setClesPossiblesTypeReseau;
		
	} // Fin de getSetClesPossiblesTypeReseau().___________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE RESEAU 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapTypeReseau : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeReseau() 
															throws Exception {
		
		if (nomenclatureMapTypeReseau == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitTypeReseauUtf8());
			
			setClesPossiblesTypeReseau = importeur.getClesPossiblesSet();
			nomenclatureMapTypeReseau = importeur.getNomenclatureMap();
		}

		return nomenclatureMapTypeReseau;
		
	} // Fin de getNomenclatureMapTypeReseau().____________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de PR/PK 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesPrPk : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesPrPk() 
													throws Exception {
				
		if (setClesPossiblesPrPk == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitPrPkUtf8());
			
			setClesPossiblesPrPk = importeur.getClesPossiblesSet();
			nomenclatureMapPrPk = importeur.getNomenclatureMap();
		}

		return setClesPossiblesPrPk;
		
	} // Fin de getSetClesPossiblesPrPk()._________________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le type PR/PK 
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapPrPk : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapPrPk() 
															throws Exception {
		
		if (nomenclatureMapPrPk == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitPrPkUtf8());
			
			setClesPossiblesPrPk = importeur.getClesPossiblesSet();
			nomenclatureMapPrPk = importeur.getNomenclatureMap();
		}

		return nomenclatureMapPrPk;
		
	} // Fin de getNomenclatureMapPrPk().__________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS DE LA SECTION DE RATTACHEMENT
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesSensRattachement : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesSensRattachement() 
													throws Exception {
		
		if (setClesPossiblesSensRattachement == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSensRattachement = importeur.getClesPossiblesSet();
			nomenclatureMapSensRattachement = importeur.getNomenclatureMap();
		}
		
		return setClesPossiblesSensRattachement;
		
	} // Fin de getSetClesPossiblesSensRattachement()._____________________

	
		
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS DE LA SECTION DE RATTACHEMENT dans un HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapSensRattachement : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, String> getNomenclatureMapSensRattachement() 
														throws Exception {
		
		if (nomenclatureMapSensRattachement == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSensRattachement = importeur.getClesPossiblesSet();
			nomenclatureMapSensRattachement = importeur.getNomenclatureMap();
		}
		
		return nomenclatureMapSensRattachement;
		
	} // Fin de getNomenclatureMapSensRattachement().______________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature du SENS DE LA SECTION LIMITROPHE
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesSensLimitrophe : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesSensLimitrophe() 
													throws Exception {
		
		if (setClesPossiblesSensLimitrophe == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSensLimitrophe = importeur.getClesPossiblesSet();
			nomenclatureMapSensLimitrophe = importeur.getNomenclatureMap();
		}
		
		return setClesPossiblesSensLimitrophe;
		
	} // Fin de getSetClesPossiblesSensLimitrophe()._______________________

	
		
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS DE LA SECTION LIMITROPHE dans un HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHitManager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapSensLimitrophe : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, String> getNomenclatureMapSensLimitrophe() 
														throws Exception {
		
		if (nomenclatureMapSensLimitrophe == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHitManager
							.getFichierNomenclatureHitSensUtf8());
			
			setClesPossiblesSensLimitrophe = importeur.getClesPossiblesSet();
			nomenclatureMapSensLimitrophe = importeur.getNomenclatureMap();
		}
		
		return nomenclatureMapSensLimitrophe;
		
	} // Fin de getNomenclatureMapSensLimitrophe().________________________



	/**
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
