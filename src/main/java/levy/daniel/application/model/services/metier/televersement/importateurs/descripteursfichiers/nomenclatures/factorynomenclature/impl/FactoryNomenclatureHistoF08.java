package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures.ConfigurationNomenclaturesHistoF08Manager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurLexique;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.IFactoryNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurLexique;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

/**
 * CLASSE FactoryNomenclatureHistoF08 :<br/>
 * <p>
 * Factory chargée de fournir les nomenclatures 
 * pour les fichiers HISTO_F08.
 * </p>
 * 
 * <p>
 * RESPONSABILITE : 
 * IMPORTER TOUTES LES NOMENCLATURES D'UN FICHIER HISTO_F08 
 * ET LES METTRE A DISPOSITION DE L'APPLICATION sous forme de 
 * <b>SINGLETONS</b>.
 * </p>
 * <p>
 * Une nomenclature est un ensemble de [clé - libellé] pouvant être prises 
 * par une variable comme par exemple pour le SENS 
 * dans un Fichier HISTO_F08 :<br/>
 * <ul>
 * <li>3 - Cumul des deux sens route à 2 sens.</li>
 * <li>4 - Sens P.R. croissants route à sens unique.</li>
 * <li>5 - Sens P.R. décroissants route à sens unique.</li>
 * </ul>
 * </p>
 * 
 * <div>
 * <p>
 * les champs à valeurs contraintes (nomenclature) dans un fichier HISTO_F08 
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
 * <td>9</td><td>type de comptage</td>
 * </tr>
 * <tr>
 * <td>10</td><td>classement de la route</td>
 * </tr>
 * <tr>
 * <td>11</td><td>classe de largeur de chaussée unique</td>
 * </tr>
 * <tr>
 * <td>12</td><td>classe de largeur de chaussées séparées</td>
 * </tr>
 * <tr>
 * <td>13</td><td>type de réseau</td>
 * </tr>
 * <tr>
 * <td>93</td><td>code concession SICRE (lexique)</td>
 * </tr>
 * <tr>
 * <td>96</td><td>profil en travers SICRE (lexique)</td>
 * </tr>
 * <tr>
 * <td>97</td><td>sous-reseau indice</td>
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
public final class FactoryNomenclatureHistoF08 
						implements IFactoryNomenclature {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe FactoryNomenclatureHistoF08Test".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREHISTOF08 
		= "Classe FactoryNomenclatureHistoF08Test";
	
	
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
	 * des clés de la nomenclature du SENS pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSens;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un fichier HISTO_F08 avec :
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
	 * les fichiers HISTO_F08 (1 pour tous véhicules).<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesNature;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la NATURE DU COMPTAGE dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapNature;
			
	// TYPE DE COMPTAGE HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptage;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptage;
	
	// CLASSEMENT DE LA ROUTE HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClassementRoute;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClassementRoute;
	
	// CLASSE DE LARGEUR DE CHAUSSEE UNIQUE HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClasseLargeurChausseeU;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseeU;
	
	// CLASSE DE LARGEUR DE CHAUSSEES SEPAREES HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClasseLargeurChausseesS;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClasseLargeurChausseesS;
	
	// TYPE DE RESEAU HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE RESEAU 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeReseau;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE RESEAU 
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeReseau;

	// CODE CONCESSION SICRE.************** 		
	/**
	 * Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du CODE CONCESSION SICRE
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<String> setClesPossiblesCodeConcessionSicre;
		
	/**
	 * Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le CODE CONCESSION SICRE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<String, String> lexiqueMapCodeConcessionSicre;

	// PROFIL EN TRAVERS SICRE.************** 		
	/**
	 * Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du PROFIL EN TRAVERS SICRE
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<String> setClesPossiblesProfilTraversSicre;
		
	/**
	 * Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le PROFIL EN TRAVERS SICRE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<String, String> lexiqueMapProfilTraversSicre;
	
	// SOUS-RESEAU INDICE HISTO_F08.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de SOUS-RESEAU INDICE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSousReseauIndice;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SOUS-RESEAU INDICE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSousReseauIndice;

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(FactoryNomenclatureHistoF08.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public FactoryNomenclatureHistoF08() {
		
		super();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getClesPossiblesSet(
			final int pNumeroChamp) 
					throws Exception {
		
		synchronized (FactoryNomenclatureHistoF08.this) {
			
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
								
			/* TYPE DE COMPTAGE. */
			case 9:
				
				resultat = getSetClesPossiblesTypeComptage();				
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 10:
				
				resultat = getSetClesPossiblesClassementRoute();				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEE UNIQUE. */
			case 11:
				
				resultat = getSetClesPossiblesClasseLargeurChausseeU();				
				break;
				
			/* CLASSE DE LARGEUR DE CHAUSSEES SEPAREES. */
			case 12:
				
				resultat = getSetClesPossiblesClasseLargeurChausseesS();				
				break;
				
			/* TYPE DE RESEAU. */
			case 13:
				
				resultat = getSetClesPossiblesTypeReseau();				
				break;
				
			/* SOUS-RESEAU INDICE. */
			case 97:
				
				resultat = getSetClesPossiblesSousReseauIndice();				
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

		synchronized (FactoryNomenclatureHistoF08.this) {

			Set<String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

			switch (pNumeroChamp) {

			/* CODE CONCESSION SICRE. */
			case 93:

				resultat = getSetClesPossiblesCodeConcessionSicre();
				break;

			/* PROFIL EN TRAVERS SICRE. */
			case 96:

				resultat = getSetClesPossiblesProfilTraversSicre();
				break;

			default:
				break;

			}

			return resultat;

		} // Fin de synchronized._________________________

	} // Fin de getClesPossiblesSetLexique(...).___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<Integer, String> getNomenclatureMap(
										final int pNumeroChamp) 
												throws Exception {
		
		synchronized (FactoryNomenclatureHistoF08.this) {

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
			
			/* TYPE DE COMPTAGE. */
			case 9:
				
				resultat = getNomenclatureMapTypeComptage();			
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 10:

				resultat = getNomenclatureMapClassementRoute();			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEE UNIQUE. */
			case 11:
				
				resultat = getNomenclatureMapClasseLargeurChausseeU();			
				break;
				
			/* CLASSE DE LA LARGEUR DE CHAUSSEES SEPAREES. */
			case 12:
				
				resultat = getNomenclatureMapClasseLargeurChausseesS();			
				break;
				
			/* TYPE DE RESEAU. */
			case 13:

				resultat = getNomenclatureMapTypeReseau();			
				break;
				
			/* SOUS-RESEAU INDICE. */
			case 97:

				resultat = getNomenclatureMapSousReseauIndice();			
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
		
		synchronized (FactoryNomenclatureHistoF08.this) {
			
			SortedMap<String, String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

			switch (pNumeroChamp) {

			/* CODE CONCESSION SICRE. */
			case 93:

				resultat = getLexiqueMapCodeConcessionSicre();
				break;

			/* PROFIL EN TRAVERS SICRE. */
			case 96:

				resultat = getLexiqueMapProfilTraversSicre();
				break;

			default:
				break;
			
			}
			
			return resultat;
			
		} // Fin de synchronized._________________________
		
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
	 * des clés de la nomenclature du SENS pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08SensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return setClesPossiblesSens;
		
	} // Fin de getSetClesPossiblesSens()._________________________________

	
		
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un HISTO_F08 avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08SensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return nomenclatureMapSens;
		
	} // Fin de getNomenclatureMapSens().__________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de NATURE DU COMPTAGE pour 
	 * les fichiers HISTO_F08 (1 pour tous véhicules).<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08NatureUtf8());
			
			setClesPossiblesNature = importeur.getClesPossiblesSet();
			nomenclatureMapNature = importeur.getNomenclatureMap();
		}

		return setClesPossiblesNature;
		
	} // Fin de getSetClesPossiblesNature()._______________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la NATURE DU COMPTAGE dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08NatureUtf8());
			
			setClesPossiblesNature = importeur.getClesPossiblesSet();
			nomenclatureMapNature = importeur.getNomenclatureMap();
		}

		return nomenclatureMapNature;
		
	} // Fin de getNomenclatureMapNature().________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08TypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return setClesPossiblesTypeComptage;
		
	} // Fin de getSetClesPossiblesTypeComptage().________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08TypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return nomenclatureMapTypeComptage;
		
	} // Fin de getNomenclatureMapTypeComptage().__________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClassementRoute;
		
	} // Fin de getSetClesPossiblesClassementRoute().______________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClassementRoute;
		
	} // Fin de getNomenclatureMapClassementRoute()._______________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8());
			
			setClesPossiblesClasseLargeurChausseeU = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseeU = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClasseLargeurChausseeU;
		
	} // Fin de getSetClesPossiblesClasseLargeurChausseeU()._______________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8());
			
			setClesPossiblesClasseLargeurChausseeU = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseeU = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClasseLargeurChausseeU;
		
	} // Fin de getNomenclatureMapClasseLargeurChausseeU().________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8());
			
			setClesPossiblesClasseLargeurChausseesS = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseesS = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClasseLargeurChausseesS;
		
	} // Fin de getSetClesPossiblesClasseLargeurChausseesS().______________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8());
			
			setClesPossiblesClasseLargeurChausseesS = importeur.getClesPossiblesSet();
			nomenclatureMapClasseLargeurChausseesS = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClasseLargeurChausseesS;
		
	} // Fin de getNomenclatureMapClasseLargeurChausseesS()._______________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE RESEAU 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08TypeReseauUtf8());
			
			setClesPossiblesTypeReseau = importeur.getClesPossiblesSet();
			nomenclatureMapTypeReseau = importeur.getNomenclatureMap();
		}

		return setClesPossiblesTypeReseau;
		
	} // Fin de getSetClesPossiblesTypeReseau().___________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE RESEAU 
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
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
						ConfigurationNomenclaturesHistoF08Manager
							.getFichierNomenclatureHistoF08TypeReseauUtf8());
			
			setClesPossiblesTypeReseau = importeur.getClesPossiblesSet();
			nomenclatureMapTypeReseau = importeur.getNomenclatureMap();
		}

		return nomenclatureMapTypeReseau;
		
	} // Fin de getNomenclatureMapTypeReseau().____________________________


	
	/**
	 * Getter du Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du CODE CONCESSION SICRE
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesCodeConcessionSicre : Set&lt;String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<String> getSetClesPossiblesCodeConcessionSicre() 
													throws Exception {
		
		if (setClesPossiblesCodeConcessionSicre == null) {
			
			final IImporteurLexique importeur 
				= new ImporteurLexique();
			
			importeur
				.importerLexiqueEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08CodeConcessionUtf8());
						
			setClesPossiblesCodeConcessionSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapCodeConcessionSicre = importeur.getLexiqueMap();
			
		}
		
		return setClesPossiblesCodeConcessionSicre;
		
	} // Fin de getSetClesPossiblesCodeConcessionSicre().__________________

	
		
	/**
	 * Getter du Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le CODE CONCESSION SICRE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return lexiqueMapCodeConcessionSicre : 
	 * SortedMap&lt;String,String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<String, String> getLexiqueMapCodeConcessionSicre() 
														throws Exception {
		
		if (lexiqueMapCodeConcessionSicre == null) {
			
			final IImporteurLexique importeur 
				= new ImporteurLexique();
			
			importeur
				.importerLexiqueEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08CodeConcessionUtf8());
						
			setClesPossiblesCodeConcessionSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapCodeConcessionSicre = importeur.getLexiqueMap();
			
		}
		
		return lexiqueMapCodeConcessionSicre;
		
	} // Fin de getLexiqueMapCodeConcessionSicre().________________________


	
	/**
	 * Getter du Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du PROFIL EN TRAVERS SICRE
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesProfilTraversSicre : Set&lt;String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<String> getSetClesPossiblesProfilTraversSicre() 
													throws Exception {
		
		if (setClesPossiblesProfilTraversSicre == null) {
			
			final IImporteurLexique importeur 
				= new ImporteurLexique();
			
			importeur
				.importerLexiqueEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08ProfilTraversUtf8());
						
			setClesPossiblesProfilTraversSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapProfilTraversSicre = importeur.getLexiqueMap();
			
		}
		
		return setClesPossiblesProfilTraversSicre;
		
	} // Fin de getSetClesPossiblesProfilTraversSicre().___________________

	
		
	/**
	 * Getter du Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le CODE CONCESSION SICRE
	 * dans un fichier HISTO_F08 avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return lexiqueMapProfilTraversSicre : 
	 * SortedMap&lt;String,String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<String, String> getLexiqueMapProfilTraversSicre() 
														throws Exception {
		
		if (lexiqueMapProfilTraversSicre == null) {
			
			final IImporteurLexique importeur 
				= new ImporteurLexique();
			
			importeur
				.importerLexiqueEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08ProfilTraversUtf8());
						
			setClesPossiblesProfilTraversSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapProfilTraversSicre = importeur.getLexiqueMap();
			
		}
		
		return lexiqueMapProfilTraversSicre;
		
	} // Fin de getLexiqueMapProfilTraversSicre()._________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de SOUS-RESEAU INDICE 
	 * pour les fichiers HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return setClesPossiblesSousReseauIndice : Set&lt;Integer&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Set<Integer> getSetClesPossiblesSousReseauIndice() 
													throws Exception {
				
		if (setClesPossiblesSousReseauIndice == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08SousReseauIndiceUtf8());
			
			setClesPossiblesSousReseauIndice = importeur.getClesPossiblesSet();
			nomenclatureMapSousReseauIndice = importeur.getNomenclatureMap();
			
		}

		return setClesPossiblesSousReseauIndice;
		
	} // Fin de getSetClesPossiblesSousReseauIndice()._____________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SOUS-RESEAU INDICE
	 * dans un fichier HISTO_F08 avec :
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
	 * <code>ConfigurationNomenclaturesHistoF08Manager</code>.</li>
	 * <li>alimente l'attribut associé (Map pour Set ou Set pour Map).</li>
	 * </ul>
	 *
	 * @return nomenclatureMapSousReseauIndice : 
	 * SortedMap&lt;Integer,String&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	public static SortedMap<Integer, String> getNomenclatureMapSousReseauIndice() 
															throws Exception {
		
		if (nomenclatureMapSousReseauIndice == null) {
			
			final IImporteurNomenclature importeur 
				= new ImporteurNomenclature();
			
			importeur
				.importerNomenclatureEnUtf8(
						ConfigurationNomenclaturesHistoF08Manager
						.getFichierNomenclatureHistoF08SousReseauIndiceUtf8());
			
			setClesPossiblesSousReseauIndice = importeur.getClesPossiblesSet();
			nomenclatureMapSousReseauIndice = importeur.getNomenclatureMap();
			
		}

		return nomenclatureMapSousReseauIndice;
		
	} // Fin de getNomenclatureMapSousReseauIndice().______________________



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
		
		synchronized (FactoryNomenclatureHistoF08.this) {
			
			String messageALogger = null;
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_FACTORYNOMENCLATUREHISTOF08);
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
	
	
	
} // FIN DE LA CLASSE FactoryNomenclatureHistoF08.---------------------------
