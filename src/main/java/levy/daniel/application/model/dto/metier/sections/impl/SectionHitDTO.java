package levy.daniel.application.model.dto.metier.sections.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;

/**
 * CLASSE SectionHitDTO :<br/>
 * .<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 7 juin 2019
 *
 */
public class SectionHitDTO implements ISectionHitDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * id en base sous forme de String.<br/>
	 */
	private String id;
	
	/**
	 * numéro de département.<br/>
	 */
	private String numDepartement;
	
	/**
	 * numéro de section.<br/>
	 */
	private String numSection;

	/**
	 * sens de trafic.<br/>
	 */
	private String sens;
	
	/**
	 * nature du comptage.<br/>
	 */
	private String nature;
	
	/**
	 * classe du trafic.<br/>
	 */
	private String classe;
	
	/**
	 * année de traitement.<br/>
	 */
	private String anneeTraitement;
	
	/**
	 * zone libre 1.<br/>
	 */
	private String zoneLibre1;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionHitDTO.class);

	
	// *************************METHODES************************************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(ISectionHitDTO pObjet) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISectionHitDTO clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteCsv() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirStringCsv() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fournirEnTeteColonne(int pI) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object fournirValeurColonne(int pI) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final String pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumDepartement() {
		return this.numDepartement;
	} // Fin de getNumDepartement()._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumDepartement(
			final String pNumDepartement) {
		this.numDepartement = pNumDepartement;
	} // Fin de setNumDepartement(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNumSection() {
		return this.numSection;
	} // Fin de getNumSection().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNumSection(
			final String pNumSection) {
		this.numSection = pNumSection;
	} // Fin de setNumSection(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSens() {
		return this.sens;
	} // Fin de getSens()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSens(
			final String pSens) {
		this.sens = pSens;
	} // Fin de setSens(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNature() {
		return this.nature;
	} // Fin de getNature()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNature(
			final String pNature) {
		this.nature = pNature;
	} // Fin de setNature(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getClasse() {
		return this.classe;
	} // Fin de getClasse()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClasse(
			final String pClasse) {
		this.classe = pClasse;
	} // Fin de setClasse(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getAnneeTraitement() {
		return this.anneeTraitement;
	} // Fin de getAnneeTraitement().______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAnneeTraitement(
			final String pAnneeTraitement) {
		this.anneeTraitement = pAnneeTraitement;
	} // Fin de setAnneeTraitement(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getZoneLibre1() {
		return this.zoneLibre1;
	} // Fin de getZoneLibre1().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setZoneLibre1(
			final String pZoneLibre1) {
		this.zoneLibre1 = pZoneLibre1;
	} // Fin de setZoneLibre1(...).________________________________________

	
	
} // FIN DE LA CLASSE SectionHitDTO.-----------------------------------------
