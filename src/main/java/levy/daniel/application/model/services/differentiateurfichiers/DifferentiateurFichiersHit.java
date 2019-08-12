package levy.daniel.application.model.services.differentiateurfichiers;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.emory.mathcs.backport.java.util.Collections;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.services.differentiateursections.DifferentiateurSectionsHit;
import levy.daniel.application.model.utilitaires.differentiateur.DifferenceChamp;

/**
 * CLASSE DifferentiateurFichiersHit :<br/>
 * Utilitaire chargé de fournir les différences 
 * section par section, et champ par champ 
 * entre deux FICHIERS DE SECTIONS HIT.<br/>
 * Le résultat est fourni via la méthode statique 
 * <code><b>differencier(Map1, Map2)</b></code> 
 * (où Map1 et Map2 encapsulent 2 fichiers de sections HIT) sous forme de 
 * Map&lt;Integer, Map&lt;Integer,DifferenceChamp&gt;&gt; avec :
 * <ul>
 * <li>Integer : le numéro de la ligne (section) dans chaque fichier.</li>
 * <ul>
 * <li>Integer : le numéro d'ordre du champ dans une section HIT.</li>
 * <li>{@link DifferenceChamp} : Encapsulation contenant le nom du champ 
 * et les valeurs prises dans chaque section HIT.</li>
 * </ul>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * trier entry sur Integer, trier Map sur clé,<br/>
 * trier Map sur key, trier Map sur Key, trier map sur key,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 10 août 2019
 *
 */
public final class DifferentiateurFichiersHit {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(DifferentiateurFichiersHit.class);

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private DifferentiateurFichiersHit() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * stocke les différences section par section, 
	 * et champ par champ entre 2 fichiers de Sections HIT
	 *  (sous fomre de Map de Sections HIT)
	 * et retourne le résultat sour forme de 
	 * Map&lt;Integer, Map&lt;Integer,DifferenceChamp&gt;&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro de la ligne (section) dans chaque fichier.</li>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ dans une section HIT.</li>
	 * <li>{@link DifferenceChamp} : Encapsulation contenant le nom du champ 
	 * et les valeurs prises dans chaque section HIT.</li>
	 * </ul>
	 * </ul>
	 * - La Map retournée n'est jamais null. TESTER SI VIDE.<br/>
	 * - retourne null si au moins une Map de sections 
	 * (pMap1 ou pMap2) est null.<br/>
	 * <br/>
	 *
	 * @param pMap1 : Map&lt;Integer,ISectionHit&gt; 
	 * Map encapsulant les sections du 1er fichier.
	 * @param pMap2 : Map&lt;Integer,ISectionHit&gt; 
	 * Map encapsulant les sections du 2ème fichier.
	 * 
	 * @return : Map&lt;Integer,Map&lt;Integer,DifferenceChamp&gt;&gt; :  .<br/>
	 */
	public static Map<Integer, Map<Integer, DifferenceChamp>> differencier(
			final Map<Integer, ISectionHit> pMap1
				, final Map<Integer, ISectionHit> pMap2) {
		
		synchronized (DifferentiateurFichiersHit.class) {
			
			/* retourne null si une Map est null. */
			if (pMap1 == null || pMap2 == null) {
				return null;
			}
			
			final Map<Integer, Map<Integer, DifferenceChamp>> resultat 
				= new ConcurrentHashMap<Integer, Map<Integer, DifferenceChamp>>();
			
			for (final Entry<Integer, ISectionHit> entry : pMap1.entrySet()) {
				
				final Integer numeroLigne = entry.getKey();
				
				final ISectionHit section1 = entry.getValue();
				final ISectionHit section2 = pMap2.get(numeroLigne);
				
				final Map<Integer, DifferenceChamp> diffMap 
					= DifferentiateurSectionsHit.differencier(section1, section2);
				
				if (!diffMap.isEmpty()) {
					resultat.put(numeroLigne, diffMap);
				}
			}
			
			return resultat;

		} // Fin de synchronized.___________________________________
		
	} // Fin de differencier(...)._________________________________________
	

	
	/**
	 * fournit une String pour l'affichage des différences entre deux fichiers de sections HIT fournies sous forme de 
	 * Map&lt;Integer, Map&lt;Integer,DifferenceChamp&gt;&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro de la ligne (section) dans chaque fichier.</li>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ dans une section HIT.</li>
	 * <li>{@link DifferenceChamp} : Encapsulation contenant le nom du champ 
	 * et les valeurs prises dans chaque section HIT.</li>
	 * </ul>
	 * </ul>.

	 * - retourne null si pMap == null.<br/>
	 * - retourne le message "AUCUNE DIFFERENCE" si pMap est vide.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer, Map&lt;Integer,DifferenceChamp&gt;&gt;.
	 * 
	 * @return : String.<br/>
	 */
	public static String afficherMapDifferences(
			final Map<Integer, Map<Integer, DifferenceChamp>> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap ==  null) {
			return null;
		}
		
		/* retourne le message "AUCUNE DIFFERENCE" si pMap est vide. */
		if (pMap.isEmpty()) {
			return "AUCUNE DIFFERENCE";
		}
		
		/* récupère la liste des Keys de la Map. */
		final List<Integer> list = new LinkedList<Integer>(pMap.keySet());
		
		/* trie la liste en fonction de l'ordre des entiers. */
		Collections.sort(list);
		
		// crée une nouvelle LinkedHashMap (qui conserve l'ordre) 
		// à partir de la LinkedList triée.
		final Map<Integer, Map<Integer, DifferenceChamp>> mapTriee 
			= new LinkedHashMap<Integer, Map<Integer, DifferenceChamp>>();

		for (final Integer numero : list) {
			mapTriee.put(numero, pMap.get(numero));
		}

		
		final StringBuffer stb = new StringBuffer();
		
		for (final Entry<Integer, Map<Integer, DifferenceChamp>> entry : mapTriee.entrySet()) {
			
			final Integer numeroLigne = entry.getKey();
			final Map<Integer, DifferenceChamp> diffMap = entry.getValue();
			
			if (diffMap == null || diffMap.isEmpty()) {
				continue;
			}
			
			stb.append("DIFFERENCES A LA LIGNE ");
			stb.append(numeroLigne);
			stb.append(NEWLINE);
			
			for (final Entry<Integer, DifferenceChamp> entry2 : diffMap.entrySet()) {
								
				final Integer numeroChamp = entry2.getKey();
				final DifferenceChamp diff = entry2.getValue();
				
				stb.append("numéro du champ : ");
				stb.append(numeroChamp);
				stb.append('\t');
				
				stb.append("nom du champ : ");
				stb.append(diff.getNomChamp());
				stb.append('\t');
				
				stb.append("Valeur 1 : ");
				stb.append(diff.getValue1());
				stb.append('\t');
								
				stb.append("Valeur 2 : ");
				stb.append(diff.getValue2());
				stb.append('\t');
				
				stb.append(NEWLINE);
			}
			
		}
		
		return stb.toString();
		
	} // Fin de afficherMapDifferences(...)._______________________________
	
	
	
} // FIN DE LA CLASSE DifferentiateurFichiersHit.----------------------------
