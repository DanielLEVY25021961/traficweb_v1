package levy.daniel.application.metier.importateurs.importeurs;

import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import levy.daniel.application.IConstantes;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class AbstractImporteurNonAscii :<br/>
 * RESPONSABILITE : IMPORTER LES FICHIERS NON ASCII (Darwin csv, ...).<br/>
 * Classe abstraite modélisant tous les importeurs de fichiers NON Ascii
 * (Darwin csv, ...).<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Pattern.split(";"), Décomposition d'une ligne csv, Regex<br/>
 * , Expression régulière,<br/>
 * Expression régulière, Expression reguliere, regex,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 31 juil. 2014
 *
 */
public abstract class AbstractImporteurNonAscii extends AbstractImporteur {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(AbstractImporteurNonAscii.class);

	// *************************METHODES************************************/

	
	
	 /**
	 * method CONSTRUCTEUR AbstractImporteurNonAscii() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractImporteurNonAscii() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final SortedMap<Integer, String> decomposerLigne(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String ligneADecomposer = null;
		
		/* Retire un éventuel caractère BOM-UTF-8 
		 * en début de chaque ligne. */
		if (pString.charAt(0) == IConstantes.BOM_UTF_8) {
			ligneADecomposer = StringUtils.substring(pString, 1);
		}
		else {
			ligneADecomposer = pString;
		}
		
		// DECOMPOSITION D'UNE LIGNE.----------------------------------
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne csv passée en paramètre. */
		/* Casse la ligne en Tokens. */
		final String[] tokens 
			= IConstantes.PATTERN_SEPARATEUR_CSV.split(ligneADecomposer);
		
		/* Map des descriptions. */
		final SortedMap<Integer, IDescriptionChamp> descriptionMap 
			= this.importateurDescription.getSpecificationChampsMap();

		final Set<Entry<Integer, IDescriptionChamp>> set 
			= descriptionMap.entrySet();
		
		final SortedMap<Integer, String> ligneMap 
			= new TreeMap<Integer, String>();
		
		// BOUCLE SUR LES CHAMPS DE LA DESCRIPTION.-----
		for (final Entry<Integer, IDescriptionChamp> entry : set) {
			
			final Integer numeroChamp = entry.getKey();
			final IDescriptionChamp desc = entry.getValue();
			
			/* Récupération de la position du champ dans une ligne csv. */
			final int positionChamp = desc.getOrdreChamps();
						
			// Extraction dans la ligne csv.______
			final String champ 
				= tokens[positionChamp - 1];
			
			/* Passage forcé en UTF-8 */
			final String champUtf8 
				= new String(champ.getBytes(IConstantes.CHARSET_UTF8));
			
			/* Ajout dans la Map décrivant la ligne. */
			ligneMap.put(numeroChamp, champUtf8);
			
		} // FIN BOUCLE SUR LES CHAMPS DE LA DESCRIPTION.-----			
		// FIN de DECOMPOSITION D'UNE LIGNE.---------------------------

		return ligneMap;
		
	} // Fin de decomposerLigne(
	 // String pString).___________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String reconstituerLigne(
			final SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
			, final int pI) {
		
		/* retourne null si pFichierImporteMap est null. */
		if (pFichierImporteMap == null) {
			return null;
		}
		
		/* Récupération d'une ligne dans pFichierImporteMap. */
		final SortedMap<Integer, String> ligneMap 
			= pFichierImporteMap.get(pI);
		
		if (ligneMap != null) {
			
			final StringBuffer stb = new StringBuffer();
						
			// PARCOURS DE LA LIGNE.***************
			final Set<Entry<Integer, String>> ligneMapSet 
				= ligneMap.entrySet();
			
			final Iterator<Entry<Integer, String>> iteLigneMap 
			= ligneMapSet.iterator();
			
			// BOUCLE A L'INTERIEUR DE LA LIGNE.-----
			while (iteLigneMap.hasNext()) {
				
				/* Récupération du numéro de champ et de sa valeur. */
				final Entry<Integer, String> entry = iteLigneMap.next();

				final String valeurChamp = entry.getValue();
								
				stb.append(valeurChamp);
				stb.append(IConstantes.SEP_PV);
								
			}// FIN DE BOUCLE A L'INTERIEUR DE LA LIGNE.-----
			
			return stb.toString();
			
		} // Fin de if (ligneMap != null)._______________________
		
		return null;
		
	} // Fin de reconstituerLigne(
	// SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
	// , int pI).__________________________________________________________
	


} // FIN DE LA CLASSE AbstractImporteurNonAscii.-----------------------------
