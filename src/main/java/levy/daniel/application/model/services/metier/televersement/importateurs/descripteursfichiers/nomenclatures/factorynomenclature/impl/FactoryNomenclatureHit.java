package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
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
 * IMPORTER TOUTES LES NOMENCLATURES D'UN FICHIER HIT 
 * ET LES METTRE A DISPOSITION DE L'APPLICATION sous forme de 
 * <b>SINGLETONS</b>.
 * </p>
 * <p>
 * Une nomenclature est un ensemble de [clé - libellé] pouvant être prises 
 * par une variable comme par exemple pour le SENS 
 * dans un fichier HIT :<br/>
 * <ul>
 * <li>1 - sens P.R. croissants route à 2 sens.</li>
 * <li>2 - sens P.R. décroissants route à 2 sens.</li>
 * <li>3 - Cumul des deux sens route à 2 sens.</li>
 * <li>4 - Sens P.R. croissants route à sens unique.</li>
 * <li>5 - Sens P.R. décroissants route à sens unique.</li>
 * </ul>
 * </p>
 * 
 * <div>
 * <p>
 * les champs à valeurs contraintes (nomenclature) dans un fichier HIT 
 * sont les champs d'ordre : 
 * </p>
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordre</th><th>champ</th>
 * </tr>
 * <tr>
 * <td>3</td><td>sens</td>
 * </tr>
 * <tr>
 * <td>4</td><td>nature (1 pour tous véhicules)</td>
 * </tr>
 * <tr>
 * <td>11</td><td>catégorie administrative de la route</td>
 * </tr>
 * <tr>
 * <td>12</td><td>type de comptage</td>
 * </tr>
 * <tr>
 * <td>13</td><td>classement de la route</td>
 * </tr>
 * <tr>
 * <td>14</td><td>classe de largeur de chaussée unique</td>
 * </tr>
 * <tr>
 * <td>15</td><td>classe de largeur de chaussées séparées</td>
 * </tr>
 * <tr>
 * <td>16</td><td>type de réseau</td>
 * </tr>
 * <tr>
 * <td>17</td><td>pr/pk</td>
 * </tr>
 * <tr>
 * <td>31</td><td>sens de la section de rattachement</td>
 * </tr>
 * <tr>
 * <td>34</td><td>sens de la section limitrophe</td>
 * </tr>
 * <tr>
 * <td>71</td><td>type de comptage de l'année N-1</td>
 * </tr>
 * <tr>
 * <td>79</td><td>type de comptage de l'année N-2</td>
 * </tr>
 * <tr>
 * <td>87</td><td>type de comptage de l'année N-3</td>
 * </tr>
 * <tr>
 * <td>95</td><td>type de comptage de l'année N-4</td>
 * </tr>
 * <tr>
 * <td>103</td><td>type de comptage de l'année N-5</td>
 * </tr>
 * </table>
 * </p>
 * 
 * </div>
 * 
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
	 * "Classe FactoryNomenclatureHit".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREHIT 
		= "Classe FactoryNomenclatureHit";
	
		
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
    
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/
	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	

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
	private static transient Set<Integer> setClesPossiblesCategorieAdminRoute;
				
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
	private static transient SortedMap<Integer, String> nomenclatureMapCategorieAdminRoute;
		
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
	
	// TYPE DE COMPTAGE N-1 HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-1 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptageNmoins1;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-1
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptageNmoins1;
	
	// TYPE DE COMPTAGE N-2 HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-2 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptageNmoins2;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-2
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptageNmoins2;
	
	// TYPE DE COMPTAGE N-3 HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-3 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptageNmoins3;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-3
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptageNmoins3;
	
	// TYPE DE COMPTAGE N-4 HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-4 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptageNmoins4;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-4
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptageNmoins4;
	
	// TYPE DE COMPTAGE N-5 HIT.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-5 
	 * pour les fichiers HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptageNmoins5;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-5
	 * dans un fichier HIT avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptageNmoins5;

	
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
		
		synchronized (FactoryNomenclatureHit.this) {
			
			Set<Integer> resultat = null;
			
			if (pNumeroChamp == 0) {
				return null;
			}
			
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
								
				resultat = getSetClesPossiblesCategorieAdminRoute();				
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

			/* TYPE COMPTAGE N-1. */
			case 71:
					
				resultat = getSetClesPossiblesTypeComptageNmoins1();				
				break;

			/* TYPE COMPTAGE N-2. */
			case 79:
					
				resultat = getSetClesPossiblesTypeComptageNmoins2();				
				break;

			/* TYPE COMPTAGE N-3. */
			case 87:
					
				resultat = getSetClesPossiblesTypeComptageNmoins3();				
				break;

			/* TYPE COMPTAGE N-4. */
			case 95:
					
				resultat = getSetClesPossiblesTypeComptageNmoins4();				
				break;

			/* TYPE COMPTAGE N-5. */
			case 103:
					
				resultat = getSetClesPossiblesTypeComptageNmoins5();				
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
	public Set<String> getClesPossiblesSetLexique(
			final int pNumeroChamp) 
							throws Exception {
			return null;
			
	} // Fin de getClesPossiblesSetLexique(...).___________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<Integer, String> getNomenclatureMap(
										final int pNumeroChamp) 
												throws Exception {

		synchronized (FactoryNomenclatureHit.this) {

			SortedMap<Integer, String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

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
				
				resultat = getNomenclatureMapCategorieAdminRoute();			
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

			/* TYPE COMPTAGE N-1. */
			case 71:
					
				resultat = getNomenclatureMapTypeComptageNmoins1();				
				break;

			/* TYPE COMPTAGE N-2. */
			case 79:
					
				resultat = getNomenclatureMapTypeComptageNmoins2();				
				break;

			/* TYPE COMPTAGE N-3. */
			case 87:
					
				resultat = getNomenclatureMapTypeComptageNmoins3();				
				break;

			/* TYPE COMPTAGE N-4. */
			case 95:
					
				resultat = getNomenclatureMapTypeComptageNmoins4();				
				break;

			/* TYPE COMPTAGE N-5. */
			case 103:
					
				resultat = getNomenclatureMapTypeComptageNmoins5();				
				break;
				
			default:
				break;
			}

			return resultat;

		} // Fin de synchronized._________________________

	} // Fin de getNomenclatureMap(...).___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<String, String> getLexiqueMap(
										final int pNumeroChamp) 
												throws Exception {
						
		return null;
		
	} // Fin de getLexiqueMap(...).________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherMapIntegerString(
			final Map<Integer, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final int cle = entry.getKey();
			final String libelle = entry.getValue();
							
			/* Ajout de la ligne au StringBuilder. */
			stb.append(
					String.format(Locale.FRANCE
							, "Ligne %-5d =      ", compteur));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Clé : %-10d", cle));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Libellé : %-50s", libelle));
										
			stb.append(NEWLINE);
														
		} // Fin de Parcours de l'iterator.______________________
		
		/* Retour de la ligne. */
		return stb.toString();
		
	} // Fin de afficherMapIntegerString(...)._____________________________	
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherMapStringString(
			final Map<String, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<String, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<String, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final String cle = entry.getKey();
			final String libelle = entry.getValue();
							
			/* Ajout de la ligne au StringBuilder. */
			stb.append(
					String.format(Locale.FRANCE
							, "Ligne %-5d =      ", compteur));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Clé : %-10s", cle));
			
			stb.append(
					String.format(Locale.FRANCE
							, "Libellé : %-50s", libelle));
										
			stb.append(NEWLINE);
														
		} // Fin de Parcours de l'iterator.______________________
		
		/* Retour de la ligne. */
		return stb.toString();
		
	} // Fin de afficherMapIntegerString(...)._____________________________	


		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
	 * @return setClesPossiblesCategorieAdminRoute : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesCategorieAdminRoute() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
					
			if (setClesPossiblesCategorieAdminRoute == null) {
				
				final IImporteurNomenclature importeur 
					= new ImporteurNomenclature();
				
				importeur
					.importerNomenclatureEnUtf8(
							ConfigurationNomenclaturesHitManager
								.getFichierNomenclatureHitCatAdminRouteUtf8());
				
				setClesPossiblesCategorieAdminRoute = importeur.getClesPossiblesSet();
				nomenclatureMapCategorieAdminRoute = importeur.getNomenclatureMap();
			}
		
			return setClesPossiblesCategorieAdminRoute;
			
		} // Fin du bloc synchronized.__________________________
		
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
	 * @return nomenclatureMapCategorieAdminRoute : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapCategorieAdminRoute() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapCategorieAdminRoute == null) {
				
				final IImporteurNomenclature importeur 
					= new ImporteurNomenclature();
				
				importeur
					.importerNomenclatureEnUtf8(
							ConfigurationNomenclaturesHitManager
								.getFichierNomenclatureHitCatAdminRouteUtf8());
				
				setClesPossiblesCategorieAdminRoute = importeur.getClesPossiblesSet();
				nomenclatureMapCategorieAdminRoute = importeur.getNomenclatureMap();
			}

			return nomenclatureMapCategorieAdminRoute;
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
					
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
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
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (setClesPossiblesSensRattachement == null) {
							
				setClesPossiblesSensRattachement = getSetClesPossiblesSens();
				nomenclatureMapSensRattachement = getNomenclatureMapSens();
				
			}
			
			return setClesPossiblesSensRattachement;
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapSensRattachement == null) {
				
				setClesPossiblesSensRattachement = getSetClesPossiblesSens();
				nomenclatureMapSensRattachement = getNomenclatureMapSens();
				
			}
			
			return nomenclatureMapSensRattachement;
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (setClesPossiblesSensLimitrophe == null) {
				
				setClesPossiblesSensLimitrophe = getSetClesPossiblesSens();
				nomenclatureMapSensLimitrophe = getNomenclatureMapSens();
				
			}
			
			return setClesPossiblesSensLimitrophe;
			
		} // Fin du bloc synchronized.__________________________
		
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
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapSensLimitrophe == null) {
				
				setClesPossiblesSensLimitrophe = getSetClesPossiblesSens();
				nomenclatureMapSensLimitrophe = getNomenclatureMapSens();
				
			}
			
			return nomenclatureMapSensLimitrophe;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapSensLimitrophe().________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-1 
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
	 * @return setClesPossiblesTypeComptageNmoins1 : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptageNmoins1() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
					
			if (setClesPossiblesTypeComptageNmoins1 == null) {
				
				setClesPossiblesTypeComptageNmoins1 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins1 = getNomenclatureMapTypeComptage();
				
			}
		
			return setClesPossiblesTypeComptageNmoins1;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getSetClesPossiblesTypeComptageNmoins1().__________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-1 
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
	 * @return nomenclatureMapTypeComptageNmoins1 : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptageNmoins1() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapTypeComptageNmoins1 == null) {
				
				setClesPossiblesTypeComptageNmoins1 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins1 = getNomenclatureMapTypeComptage();
				
			}

			return nomenclatureMapTypeComptageNmoins1;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapTypeComptageNmoins1().___________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-2 
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
	 * @return setClesPossiblesTypeComptageNmoins2 : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptageNmoins2() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (setClesPossiblesTypeComptageNmoins2 == null) {
				
				setClesPossiblesTypeComptageNmoins2 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins2 = getNomenclatureMapTypeComptage();
				
			}

			return setClesPossiblesTypeComptageNmoins2;
			
		} // Fin du bloc synchronized.__________________________
				
		
	} // Fin de getSetClesPossiblesTypeComptageNmoins2().__________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-2 
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
	 * @return nomenclatureMapTypeComptageNmoins2 : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptageNmoins2() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapTypeComptageNmoins2 == null) {
				
				setClesPossiblesTypeComptageNmoins2 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins2 = getNomenclatureMapTypeComptage();
				
			}

			return nomenclatureMapTypeComptageNmoins2;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapTypeComptageNmoins2().___________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-3 
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
	 * @return setClesPossiblesTypeComptageNmoins3 : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptageNmoins3() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
					
			if (setClesPossiblesTypeComptageNmoins3 == null) {
				
				setClesPossiblesTypeComptageNmoins3 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins3 = getNomenclatureMapTypeComptage();
				
			}
		
			return setClesPossiblesTypeComptageNmoins3;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getSetClesPossiblesTypeComptageNmoins3().__________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-3 
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
	 * @return nomenclatureMapTypeComptageNmoins3 : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptageNmoins3() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapTypeComptageNmoins3 == null) {
				
				setClesPossiblesTypeComptageNmoins3 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins3 = getNomenclatureMapTypeComptage();
				
			}

			return nomenclatureMapTypeComptageNmoins3;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapTypeComptageNmoins3().___________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-4 
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
	 * @return setClesPossiblesTypeComptageNmoins4 : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptageNmoins4() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
					
			if (setClesPossiblesTypeComptageNmoins4 == null) {
				
				setClesPossiblesTypeComptageNmoins4 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins4 = getNomenclatureMapTypeComptage();
				
			}
		
			return setClesPossiblesTypeComptageNmoins4;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getSetClesPossiblesTypeComptageNmoins4().__________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-4 
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
	 * @return nomenclatureMapTypeComptageNmoins4 : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptageNmoins4() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapTypeComptageNmoins4 == null) {
				
				setClesPossiblesTypeComptageNmoins4 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins4 = getNomenclatureMapTypeComptage();
				
			}

			return nomenclatureMapTypeComptageNmoins4;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapTypeComptageNmoins4().___________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE DE L'ANNEE N-5 
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
	 * @return setClesPossiblesTypeComptageNmoins5 : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesTypeComptageNmoins5() 
													throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
					
			if (setClesPossiblesTypeComptageNmoins5 == null) {
				
				setClesPossiblesTypeComptageNmoins5 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins5 = getNomenclatureMapTypeComptage();
				
			}
		
			return setClesPossiblesTypeComptageNmoins5;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getSetClesPossiblesTypeComptageNmoins5().__________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE DE L'ANNEE N-5 
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
	 * @return nomenclatureMapTypeComptageNmoins5 : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapTypeComptageNmoins5() 
															throws Exception {
		
		synchronized (FactoryNomenclatureHit.class) {
			
			if (nomenclatureMapTypeComptageNmoins5 == null) {
				
				setClesPossiblesTypeComptageNmoins5 = getSetClesPossiblesTypeComptage();
				nomenclatureMapTypeComptageNmoins5 = getNomenclatureMapTypeComptage();
				
			}

			return nomenclatureMapTypeComptageNmoins5;
			
		} // Fin du bloc synchronized.__________________________
		
	} // Fin de getNomenclatureMapTypeComptageNmoins5().___________________



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
