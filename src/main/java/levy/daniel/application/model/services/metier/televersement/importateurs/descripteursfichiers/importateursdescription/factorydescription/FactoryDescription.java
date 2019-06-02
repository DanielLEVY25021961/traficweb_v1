package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionDarwinCsv;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHistoF07;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHistoF08;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionMapping;

/**
 * CLASSE FactoryDescription :<br/>
 * <p>
 * classe chargée de 
 * <b>fournir à l'application des SINGLETONS des descriptions de fichier 
 * (description du HIT, description du HISTO_F07, ...)</b>.<br/>
 * </p>
 * 
 * <p>
 * délègue au bon {@link IImportateurDescription} 
 * la lecture de la description de fichier encodée en UTF-8
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une <code><b>SortedMap&lt;Integer,IDescriptionChamp&gt;</b></code>.<br/>
 * </p>
 * 
 * 
 * <p>
 * Une description de champ {@link IDescriptionChamp} 
 * est une encapsulation (pure fabrication) 
 * chargée de stocker tous les éléments caractérisant un champ défini 
 * dans une description de fichier.
 * </p>
 * 
 * <p>
 * Par exemple, le fichier de description du HISTO_F08 
 * stipule que le 3ème champ dans un fichier ASCII HISTO_F08 
 * est le <code>sens</code>.<br/>
 * le fichier de description du HISTO_F08 stipule également 
 * que le sens figure à la colonne 10 d'un HISTO_F08
 * , qu'il doit respecter une nomenclature, ...<br/>
 * IDescriptionChamp pour le sens encapsule toutes les données 
 * précisées pour ce champ dans la description de fichier du HISTO_F08.<br/>
 * </p>
 *   
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurDescription : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_1.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_2.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * </p>
 * 
 * <br/>
 *
 *<p>
 * - Exemple d'utilisation :
 *</p>
 *<p>
 *<code> // Récupère la DESCRIPTION DU FICHIER HIT sous forme de Map</code><br/>
 *<code><b>SortedMap<Integer, IDescriptionChamp> descriptionHitMap = FactoryDescription.getDecriptionHitMap();</b></code><br/>
 *</p>
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
 * @since 2 juin 2019
 *
 */
public final class FactoryDescription {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HIT.<br/>
	 * <b>SINGLETON</b>.
	 */
	private static SortedMap<Integer, IDescriptionChamp> descriptionHitMap;

	/**
	 * SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HISTO_F07.<br/>
	 * <b>SINGLETON</b>.
	 */
	private static SortedMap<Integer, IDescriptionChamp> descriptionHistoF07Map;

	/**
	 * SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HISTO_F08.<br/>
	 * <b>SINGLETON</b>.
	 */
	private static SortedMap<Integer, IDescriptionChamp> descriptionHistoF08Map;

	/**
	 * SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.
	 */
	private static SortedMap<Integer, IDescriptionChamp> descriptionDarwinCsvMap;

	/**
	 * SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier MAPPING.<br/>
	 * <b>SINGLETON</b>.
	 */
	private static SortedMap<Integer, IDescriptionChamp> descriptionMappingMap;
	
	
	/**
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(FactoryDescription.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 */
	private FactoryDescription() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * fournit une String pour l'affichage d'une description de fichier.<br/>
	 * <br/>
	 * - retourne null si pDescription == null.
	 * <br/>
	 *
	 * @param pDescription : SortedMap&ltInteger, IDescriptionChamp&gt;
	 * 
	 * @return : String : affichage d'une description de fichier.
	 */
	public static String afficherDescription(
			final SortedMap<Integer, IDescriptionChamp> pDescription) {
		
		/* retourne null si pDescription == null. */
		if (pDescription == null) {
			return null;
		}
		
		final Set<Entry<Integer, IDescriptionChamp>> entrySet 
			= pDescription.entrySet();
		
		final Iterator<Entry<Integer, IDescriptionChamp>> ite 
			= entrySet.iterator();
		
		final StringBuffer stb = new StringBuffer();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			final Entry<Integer, IDescriptionChamp> entry = ite.next();
			
			final Integer cle = entry.getKey();
			final IDescriptionChamp value = entry.getValue();
			
			if (compteur == 0) {
				final String entete = "EN-TETE : ";
				stb.append(entete);
				stb.append(value.fournirLigneEnTetesCsv());	
				stb.append(NEWLINE);
			}
			
			compteur++;
			
			final String num 
				= String.format(Locale.getDefault()
						, "CHAMP NUMERO %1$-3d : ", cle);
			stb.append(num);
			stb.append(value.fournirLigneValeursCsv());
			stb.append(NEWLINE);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherDescription(...).__________________________________
	
	
	
	/**
	 * getter de la SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HIT.<br/>
	 * <b>SINGLETON</b>.
	 *
	 * @return : SortedMap&ltInteger, IDescriptionChamp&gt; :  
	 * Description du fichier HIT sous forme de Map.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, IDescriptionChamp> getDecriptionHitMap() 
			throws Exception {
		
		synchronized(FactoryDescription.class) {
			
			if (descriptionHitMap == null) {
				
				final IImportateurDescription importateur 
					= new ImportateurDescriptionHit();
				
				importateur.importerDescriptionUtf8();
				
				descriptionHitMap 
					= importateur.getSpecificationChampsMap();
				
			}
			
			return descriptionHitMap;
			
		} // Fin de synchronized.___________________
		
	} // Fin de getDecriptionHitMap()._____________________________________
	

	
	/**
	 * getter de la SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HISTO_F07.<br/>
	 * <b>SINGLETON</b>.
	 *
	 * @return : SortedMap&ltInteger, IDescriptionChamp&gt; : 
	 * Description du fichier HISTO_F07 sous forme de Map.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, IDescriptionChamp> getDecriptionHistoF07Map() 
			throws Exception {
		
		synchronized(FactoryDescription.class) {
			
			if (descriptionHistoF07Map == null) {
				
				final IImportateurDescription importateur 
					= new ImportateurDescriptionHistoF07();
				
				importateur.importerDescriptionUtf8();
				
				descriptionHistoF07Map 
					= importateur.getSpecificationChampsMap();
				
			}
			
			return descriptionHistoF07Map;
			
		} // Fin de synchronized.___________________
		
	} // Fin de getDecriptionHistoF07Map().________________________________
	

	
	/**
	 * getter de la SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier HISTO_F08.<br/>
	 * <b>SINGLETON</b>.
	 *
	 * @return : SortedMap&ltInteger, IDescriptionChamp&gt; : 
	 * Description du fichier HISTO_F08 sous forme de Map.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, IDescriptionChamp> getDecriptionHistoF08Map() 
			throws Exception {
		
		synchronized(FactoryDescription.class) {
			
			if (descriptionHistoF08Map == null) {
				
				final IImportateurDescription importateur 
					= new ImportateurDescriptionHistoF08();
				
				importateur.importerDescriptionUtf8();
				
				descriptionHistoF08Map 
					= importateur.getSpecificationChampsMap();
				
			}
			
			return descriptionHistoF08Map;
			
		} // Fin de synchronized.___________________
		
	} // Fin de getDecriptionHistoF08Map().________________________________
	

	
	/**
	 * getter de la SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.
	 *
	 * @return : SortedMap&ltInteger, IDescriptionChamp&gt; :  
	 * Description du fichier DARWIN_CSV sous forme de Map.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, IDescriptionChamp> getDecriptionDarwinCsvMap() 
			throws Exception {
		
		synchronized(FactoryDescription.class) {
			
			if (descriptionDarwinCsvMap == null) {
				
				final IImportateurDescription importateur 
					= new ImportateurDescriptionDarwinCsv();
				
				importateur.importerDescriptionUtf8();
				
				descriptionDarwinCsvMap 
					= importateur.getSpecificationChampsMap();
				
			}
			
			return descriptionDarwinCsvMap;
			
		} // Fin de synchronized.___________________
		
	} // Fin de getDecriptionDarwinCsvMap()._______________________________
	

	
	/**
	 * getter de la SortedMap&ltInteger, IDescriptionChamp&gt; 
	 * encapsulant la description du fichier MAPPING.<br/>
	 * <b>SINGLETON</b>.
	 *
	 * @return : SortedMap&ltInteger, IDescriptionChamp&gt; :  
	 * Description du fichier MAPPING sous forme de Map.<br/>
	 * 
	 * @throws Exception 
	 */
	public static SortedMap<Integer, IDescriptionChamp> getDecriptionMappingMap() 
			throws Exception {
		
		synchronized(FactoryDescription.class) {
			
			if (descriptionMappingMap == null) {
				
				final IImportateurDescription importateur 
					= new ImportateurDescriptionMapping();
				
				importateur.importerDescriptionUtf8();
				
				descriptionMappingMap 
					= importateur.getSpecificationChampsMap();
				
			}
			
			return descriptionMappingMap;
			
		} // Fin de synchronized.___________________
		
	} // Fin de getDecriptionMappingMap()._________________________________
	
	
	
	
} // FIN DE LA CLASSE FactoryDescription.------------------------------------
