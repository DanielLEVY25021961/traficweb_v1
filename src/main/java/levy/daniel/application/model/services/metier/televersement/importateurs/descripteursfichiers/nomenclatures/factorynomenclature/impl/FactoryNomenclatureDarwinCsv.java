package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.impl;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures.ConfigurationNomenclaturesDarwinCsvManager;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurLexique;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.IImporteurNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature.IFactoryNomenclature;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurLexique;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.impl.ImporteurNomenclature;

/**
 * CLASSE FactoryNomenclatureDarwinCsv :<br/>
 * <p>
 * Factory chargée de fournir les nomenclatures 
 * pour les fichiers DARWIN_CSV.
 * </p>
 * 
 * <p>
 * RESPONSABILITE : 
 * IMPORTER TOUTES LES NOMENCLATURES D'UN FICHIER DARWIN_CSV 
 * ET LES METTRE A DISPOSITION DE L'APPLICATION sous forme de 
 * <b>SINGLETONS</b>.
 * </p>
 * <p>
 * Une nomenclature est un ensemble de [clé - libellé] pouvant être prises 
 * par une variable comme par exemple pour le SENS 
 * dans un Fichier DARWIN_CSV :<br/>
 * <ul>
 * <li>3 - Cumul des deux sens route à 2 sens.</li>
 * <li>4 - Sens P.R. croissants route à sens unique.</li>
 * <li>5 - Sens P.R. décroissants route à sens unique.</li>
 * </ul>
 * </p>
 * 
 * <div>
 * <p>
 * les champs à valeurs contraintes (nomenclature) dans un fichier DARWIN_CSV 
 * sont les champs d'ordre : 
 * </p>
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordre</th><th>champ</th>
 * </tr>
 * <tr>
 * <td>13</td><td>sens</td>
 * </tr>
 * <tr>
 * <td>14</td><td>type de comptage</td>
 * </tr>
 * <tr>
 * <td>57</td><td>classement de la route</td>
 * </tr>
 * <tr>
 * <td>58</td><td>profil en travers SICRE (lexique)</td>
 * </tr>
 * <tr>
 * <td>65</td><td>sous-reseau indice</td>
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
public final class FactoryNomenclatureDarwinCsv implements IFactoryNomenclature {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_FACTORYNOMENCLATUREDARWIN : String :<br/>
	 * "CLASSE FactoryNomenclatureDarwinCsv".<br/>
	 */
	public static final String CLASSE_FACTORYNOMENCLATUREDARWIN 
		= "CLASSE FactoryNomenclatureDarwinCsv";
		
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
	 * des clés de la nomenclature du SENS pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSens;
		
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un fichier DARWIN_CSV avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapSens;
			
	// TYPE DE COMPTAGE DARWIN_CSV.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesTypeComptage;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier DARWIN_CSV avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapTypeComptage;
	
	// CLASSEMENT DE LA ROUTE DARWIN_CSV.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesClassementRoute;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier DARWIN_CSV avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<Integer, String> nomenclatureMapClassementRoute;
	
	// PROFIL EN TRAVERS SICRE.************** 		
	/**
	 * Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du PROFIL EN TRAVERS SICRE
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<String> setClesPossiblesProfilTraversSicre;
		
	/**
	 * Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le PROFIL EN TRAVERS SICRE
	 * dans un fichier DARWIN_CSV avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient SortedMap<String, String> lexiqueMapProfilTraversSicre;
	
	// SOUS-RESEAU INDICE DARWIN_CSV.********	
	/**
	 * Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de SOUS-RESEAU INDICE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 */
	private static transient Set<Integer> setClesPossiblesSousReseauIndice;
				
	/**
	 * Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SOUS-RESEAU INDICE
	 * dans un fichier DARWIN_CSV avec :
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
			.getLog(FactoryNomenclatureDarwinCsv.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
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
		
		synchronized (FactoryNomenclatureDarwinCsv.this) {
			
			Set<Integer> resultat = null;
			
			if (pNumeroChamp == 0) {
				return null;
			}
			
			switch (pNumeroChamp) {

			/* SENS. */
			case 13:
			
				resultat = getSetClesPossiblesSens();
				break;
								
			/* TYPE DE COMPTAGE. */
			case 14:
				
				resultat = getSetClesPossiblesTypeComptage();				
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 57:
				
				resultat = getSetClesPossiblesClassementRoute();				
				break;
				
			/* SOUS-RESEAU INDICE. */
			case 65:
				
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

		synchronized (FactoryNomenclatureDarwinCsv.this) {

			Set<String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

			switch (pNumeroChamp) {

			/* PROFIL EN TRAVERS SICRE. */
			case 58:

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
		
		synchronized (FactoryNomenclatureDarwinCsv.this) {

			SortedMap<Integer, String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

			switch (pNumeroChamp) {
			
			/* SENS. */
			case 13:

				resultat = getNomenclatureMapSens();
				break;
			
			/* TYPE DE COMPTAGE. */
			case 14:
				
				resultat = getNomenclatureMapTypeComptage();			
				break;
				
			/* CLASSEMENT DE LA ROUTE. */
			case 57:

				resultat = getNomenclatureMapClassementRoute();			
				break;
				
			/* SOUS-RESEAU INDICE. */
			case 65:

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
		
		synchronized (FactoryNomenclatureDarwinCsv.this) {
			
			SortedMap<String, String> resultat = null;

			if (pNumeroChamp == 0) {
				return null;
			}

			switch (pNumeroChamp) {

			/* PROFIL EN TRAVERS SICRE. */
			case 58:

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
	 * des clés de la nomenclature du SENS pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvSensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return setClesPossiblesSens;
		
	} // Fin de getSetClesPossiblesSens()._________________________________

	
		
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SENS dans un DARWIN_CSV avec :
	 * <ul>
	 * <li>Integer : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvSensUtf8());
			
			setClesPossiblesSens = importeur.getClesPossiblesSet();
			nomenclatureMapSens = importeur.getNomenclatureMap();
		}
		
		return nomenclatureMapSens;
		
	} // Fin de getNomenclatureMapSens().__________________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de TYPE DE COMPTAGE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvTypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return setClesPossiblesTypeComptage;
		
	} // Fin de getSetClesPossiblesTypeComptage().________________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le TYPE DE COMPTAGE 
	 * dans un fichier DARWIN_CSV avec :
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
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvTypeComptageUtf8());
			
			setClesPossiblesTypeComptage = importeur.getClesPossiblesSet();
			nomenclatureMapTypeComptage = importeur.getNomenclatureMap();
		}

		return nomenclatureMapTypeComptage;
		
	} // Fin de getNomenclatureMapTypeComptage().__________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de CLASSEMENT DE LA ROUTE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return setClesPossiblesClassementRoute;
		
	} // Fin de getSetClesPossiblesClassementRoute().______________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le CLASSEMENT DE LA ROUTE 
	 * dans un fichier DARWIN_CSV avec :
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
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
							.getFichierNomenclatureDarwinCsvClassementRouteUtf8());
			
			setClesPossiblesClassementRoute = importeur.getClesPossiblesSet();
			nomenclatureMapClassementRoute = importeur.getNomenclatureMap();
		}

		return nomenclatureMapClassementRoute;
		
	} // Fin de getNomenclatureMapClassementRoute()._______________________


	
	/**
	 * Getter du Set&lt;String&gt; contenant les valeurs possibles 
	 * des clés du lexique du PROFIL EN TRAVERS SICRE
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
						.getFichierNomenclatureDarwinCsvProfilTraversUtf8());
						
			setClesPossiblesProfilTraversSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapProfilTraversSicre = importeur.getLexiqueMap();
			
		}
		
		return setClesPossiblesProfilTraversSicre;
		
	} // Fin de getSetClesPossiblesProfilTraversSicre().___________________

	
		
	/**
	 * Getter du Lexique sous forme de SortedMap&lt;String,String&gt;
	 * pour le CODE CONCESSION SICRE
	 * dans un fichier DARWIN_CSV avec :
	 * <ul>
	 * <li>String : la clé</li>
	 * <li>String : le libellé</li>
	 * </ul>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurLexique</code> pour importer 
	 * le fichier de lexique.</li>
	 * <li>délègue l'obtention du bon fichier de lexique à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
						.getFichierNomenclatureDarwinCsvProfilTraversUtf8());
						
			setClesPossiblesProfilTraversSicre 
				= importeur.getClesPossiblesSet();
			lexiqueMapProfilTraversSicre = importeur.getLexiqueMap();
			
		}
		
		return lexiqueMapProfilTraversSicre;
		
	} // Fin de getLexiqueMapProfilTraversSicre()._________________________


	
	/**
	 * Getter du Set&lt;Integer&gt; contenant les valeurs possibles 
	 * des clés de la nomenclature de SOUS-RESEAU INDICE 
	 * pour les fichiers DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * <ul>
	 * <li>utilise un <code>IImporteurNomenclature</code> pour importer 
	 * le fichier de nomenclature.</li>
	 * <li>délègue l'obtention du bon fichier de nomenclature à un 
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
						.getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8());
			
			setClesPossiblesSousReseauIndice = importeur.getClesPossiblesSet();
			nomenclatureMapSousReseauIndice = importeur.getNomenclatureMap();
			
		}

		return setClesPossiblesSousReseauIndice;
		
	} // Fin de getSetClesPossiblesSousReseauIndice()._____________________


	
	/**
	 * Getter de la Nomenclature sous forme de SortedMap&lt;Integer,String&gt; 
	 * pour le SOUS-RESEAU INDICE
	 * dans un fichier DARWIN_CSV avec :
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
	 * <code>ConfigurationNomenclaturesDarwinCsvManager</code>.</li>
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
						ConfigurationNomenclaturesDarwinCsvManager
						.getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8());
			
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
