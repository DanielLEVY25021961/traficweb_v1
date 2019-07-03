package levy.daniel.application.model.dto.metier.sections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;



/**
 * CLASSE SectionHitConvertisseurMetierDTO :<br/>
 * classe <b>utilitaire</b> chargée de <b>convertir 
 * un DTO en OBJET METIER</b> et de <b>convertir un
 * OBJET METIER en DTO</b>.<br/>
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
 * @since 3 juil. 2019
 *
 */
public final class SectionHitConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe SectionHitConvertisseurMetierDTO".
	 */
	public static final String CLASSE_SECTION_HIT_CONVERTISSEUR_METIER_DTO 
		= "Classe SectionHitConvertisseurMetierDTO";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private SectionHitConvertisseurMetierDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>convertit un DTO en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pDTO == null.</li>
	 * <li>récupère les valeurs String dans le DTO.</li>
	 * <li>convertit les String du DTO en types de l'Objet métier.</li>
	 * <li>injecte les valeurs typées dans un OBJET METIER 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pDTO : ISectionHitDTO.<br/>
	 * 
	 * @return : ISectionHit : Objet métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ISectionHit convertirDTOEnObjetMetier(
			final ISectionHitDTO pDTO) throws Exception {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			ISectionHit objet = null;
			
			if (pDTO != null) {
				
				// **********************************************
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getId();
				final String numDepartementString = pDTO.getNumDepartement();
				final String numSectionString = pDTO.getNumSection();
				final String sensString = pDTO.getSens();
				final String natureString = pDTO.getNature();
				final String classeString = pDTO.getClasse();
				final String anneeTraitementString = pDTO.getAnneeTraitement();
				final String zoneLibre1String = pDTO.getZoneLibre1();
				final String numRouteString = pDTO.getNumRoute();
				final String indiceNumRouteString = pDTO.getIndiceNumRoute();
				final String indiceLettreRouteString = pDTO.getIndiceLettreRoute();
				final String categorieAdminRouteString = pDTO.getCategorieAdminRoute();
				final String typeComptageString = pDTO.getTypeComptage();
				final String classementRouteString = pDTO.getClassementRoute();
				final String classeLargeurChausseeUString = pDTO.getClasseLargeurChausseeU();
				final String classeLargeurChausseesSString = pDTO.getClasseLargeurChausseesS();
				final String typeReseauString = pDTO.getTypeReseau();
				final String pRoupKString = pDTO.getPRoupK();
				final String lieuDitOrigineString = pDTO.getLieuDitOrigine();
				final String prOrigineString = pDTO.getPrOrigine();
				final String absOrigineString = pDTO.getAbsOrigine();
				final String lieuDitExtremiteString = pDTO.getLieuDitExtremite();
				final String prExtremiteString = pDTO.getPrExtremite();
				final String absExtremiteString = pDTO.getAbsExtremite();
				final String lieuDitComptageString = pDTO.getLieuDitComptage();
				final String prComptageString = pDTO.getPrComptage();
				final String absComptageString = pDTO.getAbsComptage();
				final String longueurSectionString = pDTO.getLongueurSection();
				final String longueurRaseCampagneString = pDTO.getLongueurRaseCampagne();
				final String numDepartementRattachementString = pDTO.getNumDepartementRattachement();
				final String numSectionRattachementString = pDTO.getNumSectionRattachement();
				final String sensRattachementString = pDTO.getSensRattachement();
				final String numDepartementLimitropheString = pDTO.getNumDepartementLimitrophe();
				final String numSectionLimitropheString = pDTO.getNumSectionLimitrophe();
				final String sensLimitropheString = pDTO.getSensLimitrophe();
				final String moisSectionnementString = pDTO.getMoisSectionnement();
				final String anneeSectionnementString = pDTO.getAnneeSectionnement();
				final String zoneLibre2String = pDTO.getZoneLibre2();
				final String mjaNString = pDTO.getMjaN();
				final String modeCalculNString = pDTO.getModeCalculN();
				final String pcPLNString = pDTO.getPcPLN();
				final String evaluationPLNString = pDTO.getEvaluationPLN();
				final String pcNuitAnnuelNString = pDTO.getPcNuitAnnuelN();
				final String indiceFiabiliteMjaNString = pDTO.getIndiceFiabiliteMjaN();
				final String mjmNmois01String = pDTO.getMjmNmois01();
				final String pcNuitNmois01String = pDTO.getPcNuitNmois01();
				final String mjmNmois02String = pDTO.getMjmNmois02();
				final String pcNuitNmois02String = pDTO.getPcNuitNmois02();
				final String mjmNmois03String = pDTO.getMjmNmois03();
				final String pcNuitNmois03String = pDTO.getPcNuitNmois03();
				final String mjmNmois04String = pDTO.getMjmNmois04();
				final String pcNuitNmois04String = pDTO.getPcNuitNmois04();
				final String mjmNmois05String = pDTO.getMjmNmois05();
				final String pcNuitNmois05String = pDTO.getPcNuitNmois05();
				final String mjmNmois06String = pDTO.getMjmNmois06();
				final String pcNuitNmois06String = pDTO.getPcNuitNmois06();
				final String mjmNmois07String = pDTO.getMjmNmois07();
				final String pcNuitNmois07String = pDTO.getPcNuitNmois07();
				final String mjmNmois08String = pDTO.getMjmNmois08();
				final String pcNuitNmois08String = pDTO.getPcNuitNmois08();
				final String mjmNmois09String = pDTO.getMjmNmois09();
				final String pcNuitNmois09String = pDTO.getPcNuitNmois09();
				final String mjmNmois10String = pDTO.getMjmNmois10();
				final String pcNuitNmois10String = pDTO.getPcNuitNmois10();
				final String mjmNmois11String = pDTO.getMjmNmois11();
				final String pcNuitNmois11String = pDTO.getPcNuitNmois11();
				final String mjmNmois12String = pDTO.getMjmNmois12();
				final String pcNuitNmois12String = pDTO.getPcNuitNmois12();
				final String zoneLibre3String = pDTO.getZoneLibre3();
				final String anneeNmoins1String = pDTO.getAnneeNmoins1();
				final String mjaNmoins1String = pDTO.getMjaNmoins1();
				final String typeComptageNmoins1String = pDTO.getTypeComptageNmoins1();
				final String modeCalculNmoins1String = pDTO.getModeCalculNmoins1();
				final String pcPLNmoins1String = pDTO.getPcPLNmoins1();
				final String evaluationPLNmoins1String = pDTO.getEvaluationPLNmoins1();
				final String pcNuitAnnuelNmoins1String = pDTO.getPcNuitAnnuelNmoins1();
				final String indiceFiabiliteMjaNmoins1String = pDTO.getIndiceFiabiliteMjaNmoins1();
				final String anneeNmoins2String = pDTO.getAnneeNmoins2();
				final String mjaNmoins2String = pDTO.getMjaNmoins2();
				final String typeComptageNmoins2String = pDTO.getTypeComptageNmoins2();
				final String modeCalculNmoins2String = pDTO.getModeCalculNmoins2();
				final String pcPLNmoins2String = pDTO.getPcPLNmoins2();
				final String evaluationPLNmoins2String = pDTO.getEvaluationPLNmoins2();
				final String pcNuitAnnuelNmoins2String = pDTO.getPcNuitAnnuelNmoins2();
				final String indiceFiabiliteMjaNmoins2String = pDTO.getIndiceFiabiliteMjaNmoins2();
				final String anneeNmoins3String = pDTO.getAnneeNmoins3();
				final String mjaNmoins3String = pDTO.getMjaNmoins3();
				final String typeComptageNmoins3String = pDTO.getTypeComptageNmoins3();
				final String modeCalculNmoins3String = pDTO.getModeCalculNmoins3();
				final String pcPLNmoins3String = pDTO.getPcPLNmoins3();
				final String evaluationPLNmoins3String = pDTO.getEvaluationPLNmoins3();
				final String pcNuitAnnuelNmoins3String = pDTO.getPcNuitAnnuelNmoins3();
				final String indiceFiabiliteMjaNmoins3String = pDTO.getIndiceFiabiliteMjaNmoins3();
				final String anneeNmoins4String = pDTO.getAnneeNmoins4();
				final String mjaNmoins4String = pDTO.getMjaNmoins4();
				final String typeComptageNmoins4String = pDTO.getTypeComptageNmoins4();
				final String modeCalculNmoins4String = pDTO.getModeCalculNmoins4();
				final String pcPLNmoins4String = pDTO.getPcPLNmoins4();
				final String evaluationPLNmoins4String = pDTO.getEvaluationPLNmoins4();
				final String pcNuitAnnuelNmoins4String = pDTO.getPcNuitAnnuelNmoins4();
				final String indiceFiabiliteMjaNmoins4String = pDTO.getIndiceFiabiliteMjaNmoins4();
				final String anneeNmoins5String = pDTO.getAnneeNmoins5();
				final String mjaNmoins5String = pDTO.getMjaNmoins5();
				final String typeComptageNmoins5String = pDTO.getTypeComptageNmoins5();
				final String modeCalculNmoins5String = pDTO.getModeCalculNmoins5();
				final String pcPLNmoins5String = pDTO.getPcPLNmoins5();
				final String evaluationPLNmoins5String = pDTO.getEvaluationPLNmoins5();
				final String pcNuitAnnuelNmoins5String = pDTO.getPcNuitAnnuelNmoins5();
				final String indiceFiabiliteMjaNmoins5String = pDTO.getIndiceFiabiliteMjaNmoins5();
				final String mjmNmoins1mois01String = pDTO.getMjmNmoins1mois01();
				final String pcNuitNmoins1mois01String = pDTO.getPcNuitNmoins1mois01();
				final String mjmNmoins1mois02String = pDTO.getMjmNmoins1mois02();
				final String pcNuitNmoins1mois02String = pDTO.getPcNuitNmoins1mois02();
				final String mjmNmoins1mois03String = pDTO.getMjmNmoins1mois03();
				final String pcNuitNmoins1mois03String = pDTO.getPcNuitNmoins1mois03();
				final String mjmNmoins1mois04String = pDTO.getMjmNmoins1mois04();
				final String pcNuitNmoins1mois04String = pDTO.getPcNuitNmoins1mois04();
				final String mjmNmoins1mois05String = pDTO.getMjmNmoins1mois05();
				final String pcNuitNmoins1mois05String = pDTO.getPcNuitNmoins1mois05();
				final String mjmNmoins1mois06String = pDTO.getMjmNmoins1mois06();
				final String pcNuitNmoins1mois06String = pDTO.getPcNuitNmoins1mois06();
				final String mjmNmoins1mois07String = pDTO.getMjmNmoins1mois07();
				final String pcNuitNmoins1mois07String = pDTO.getPcNuitNmoins1mois07();
				final String mjmNmoins1mois08String = pDTO.getMjmNmoins1mois08();
				final String pcNuitNmoins1mois08String = pDTO.getPcNuitNmoins1mois08();
				final String mjmNmoins1mois09String = pDTO.getMjmNmoins1mois09();
				final String pcNuitNmoins1mois09String = pDTO.getPcNuitNmoins1mois09();
				final String mjmNmoins1mois10String = pDTO.getMjmNmoins1mois10();
				final String pcNuitNmoins1mois10String = pDTO.getPcNuitNmoins1mois10();
				final String mjmNmoins1mois11String = pDTO.getMjmNmoins1mois11();
				final String pcNuitNmoins1mois11String = pDTO.getPcNuitNmoins1mois11();
				final String mjmNmoins1mois12String = pDTO.getMjmNmoins1mois12();
				final String pcNuitNmoins1mois12String = pDTO.getPcNuitNmoins1mois12();
				final String zoneLibre4String = pDTO.getZoneLibre4();

				
				// ***********************************************************
				/* convertit les String du DTO en types de l'Objet métier. */
				/* id */
				Long id = null;
				
				if (!StringUtils.isBlank(idString)) {
					try {
						id = Long.valueOf(idString);
					} catch (NumberFormatException e) {
						id = null;
					}
				}


				final String numDepartement = numDepartementString;
				final String numSection = numSectionString;
				final String sens = sensString;
				final String nature = natureString;
				final String classe = classeString;
				
				LocalDate anneeTraitement = null;

				if (!StringUtils.isBlank(anneeTraitementString)) {
					try {
						anneeTraitement 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeTraitementString);
					} catch (Exception e) {
						anneeTraitement = null;
					}
				}

				final String zoneLibre1 = zoneLibre1String;
				final String numRoute = numRouteString;
				final String indiceNumRoute = indiceNumRouteString;
				final String indiceLettreRoute = indiceLettreRouteString;
				final String categorieAdminRoute = categorieAdminRouteString;
				final String typeComptage = typeComptageString;
				final String classementRoute = classementRouteString;
				final String classeLargeurChausseeU = classeLargeurChausseeUString;
				final String classeLargeurChausseesS = classeLargeurChausseesSString;
				final String typeReseau = typeReseauString;
				final String pRoupK = pRoupKString;
				final String lieuDitOrigine = lieuDitOrigineString;
				
				Integer prOrigine = null;

				if (!StringUtils.isBlank(prOrigineString)) {
					try {
						prOrigine = Integer.valueOf(prOrigineString);
					} catch (Exception e) {
						prOrigine = null;
					}
				}

				Integer absOrigine = null;

				if (!StringUtils.isBlank(absOrigineString)) {
					try {
						absOrigine = Integer.valueOf(absOrigineString);
					} catch (Exception e) {
						absOrigine = null;
					}
				}

				final String lieuDitExtremite = lieuDitExtremiteString;
				
				Integer prExtremite = null;

				if (!StringUtils.isBlank(prExtremiteString)) {
					try {
						prExtremite = Integer.valueOf(prExtremiteString);
					} catch (Exception e) {
						prExtremite = null;
					}
				}

				Integer absExtremite = null;

				if (!StringUtils.isBlank(absExtremiteString)) {
					try {
						absExtremite = Integer.valueOf(absExtremiteString);
					} catch (Exception e) {
						absExtremite = null;
					}
				}

				final String lieuDitComptage = lieuDitComptageString;
				
				Integer prComptage = null;

				if (!StringUtils.isBlank(prComptageString)) {
					try {
						prComptage = Integer.valueOf(prComptageString);
					} catch (Exception e) {
						prComptage = null;
					}
				}

				Integer absComptage = null;

				if (!StringUtils.isBlank(absComptageString)) {
					try {
						absComptage = Integer.valueOf(absComptageString);
					} catch (Exception e) {
						absComptage = null;
					}
				}

				Integer longueurSection = null;

				if (!StringUtils.isBlank(longueurSectionString)) {
					try {
						longueurSection = Integer.valueOf(longueurSectionString);
					} catch (Exception e) {
						longueurSection = null;
					}
				}

				Integer longueurRaseCampagne = null;

				if (!StringUtils.isBlank(longueurRaseCampagneString)) {
					try {
						longueurRaseCampagne = Integer.valueOf(longueurRaseCampagneString);
					} catch (Exception e) {
						longueurRaseCampagne = null;
					}
				}

				final String numDepartementRattachement = numDepartementRattachementString;
				final String numSectionRattachement = numSectionRattachementString;
				final String sensRattachement = sensRattachementString;
				final String numDepartementLimitrophe = numDepartementLimitropheString;
				final String numSectionLimitrophe = numSectionLimitropheString;
				final String sensLimitrophe = sensLimitropheString;
				final String moisSectionnement = moisSectionnementString;
				final String anneeSectionnement = anneeSectionnementString;
				
				final String zoneLibre2 = zoneLibre2String;
				
				Integer mjaN = null;

				if (!StringUtils.isBlank(mjaNString)) {
					try {
						mjaN = Integer.valueOf(mjaNString);
					} catch (Exception e) {
						mjaN = null;
					}
				}

				final String modeCalculN = modeCalculNString;
				final String pcPLN = pcPLNString;
				final String evaluationPLN = evaluationPLNString;
				final String pcNuitAnnuelN = pcNuitAnnuelNString;
				final String indiceFiabiliteMjaN = indiceFiabiliteMjaNString;
				
				Integer mjmNmois01 = null;

				if (!StringUtils.isBlank(mjmNmois01String)) {
					try {
						mjmNmois01 = Integer.valueOf(mjmNmois01String);
					} catch (Exception e) {
						mjmNmois01 = null;
					}
				}

				final String pcNuitNmois01 = pcNuitNmois01String;
				
				Integer mjmNmois02 = null;

				if (!StringUtils.isBlank(mjmNmois02String)) {
					try {
						mjmNmois02 = Integer.valueOf(mjmNmois02String);
					} catch (Exception e) {
						mjmNmois02 = null;
					}
				}

				final String pcNuitNmois02 = pcNuitNmois02String;
				
				Integer mjmNmois03 = null;

				if (!StringUtils.isBlank(mjmNmois03String)) {
					try {
						mjmNmois03 = Integer.valueOf(mjmNmois03String);
					} catch (Exception e) {
						mjmNmois03 = null;
					}
				}

				final String pcNuitNmois03 = pcNuitNmois03String;
				
				Integer mjmNmois04 = null;

				if (!StringUtils.isBlank(mjmNmois04String)) {
					try {
						mjmNmois04 = Integer.valueOf(mjmNmois04String);
					} catch (Exception e) {
						mjmNmois04 = null;
					}
				}

				final String pcNuitNmois04 = pcNuitNmois04String;
				
				Integer mjmNmois05 = null;

				if (!StringUtils.isBlank(mjmNmois05String)) {
					try {
						mjmNmois05 = Integer.valueOf(mjmNmois05String);
					} catch (Exception e) {
						mjmNmois05 = null;
					}
				}

				final String pcNuitNmois05 = pcNuitNmois05String;
				
				Integer mjmNmois06 = null;

				if (!StringUtils.isBlank(mjmNmois06String)) {
					try {
						mjmNmois06 = Integer.valueOf(mjmNmois06String);
					} catch (Exception e) {
						mjmNmois06 = null;
					}
				}

				final String pcNuitNmois06 = pcNuitNmois06String;
				
				Integer mjmNmois07 = null;

				if (!StringUtils.isBlank(mjmNmois07String)) {
					try {
						mjmNmois07 = Integer.valueOf(mjmNmois07String);
					} catch (Exception e) {
						mjmNmois07 = null;
					}
				}

				final String pcNuitNmois07 = pcNuitNmois07String;
				
				Integer mjmNmois08 = null;

				if (!StringUtils.isBlank(mjmNmois08String)) {
					try {
						mjmNmois08 = Integer.valueOf(mjmNmois08String);
					} catch (Exception e) {
						mjmNmois08 = null;
					}
				}

				final String pcNuitNmois08 = pcNuitNmois08String;
				
				Integer mjmNmois09 = null;

				if (!StringUtils.isBlank(mjmNmois09String)) {
					try {
						mjmNmois09 = Integer.valueOf(mjmNmois09String);
					} catch (Exception e) {
						mjmNmois09 = null;
					}
				}

				final String pcNuitNmois09 = pcNuitNmois09String;
				
				Integer mjmNmois10 = null;

				if (!StringUtils.isBlank(mjmNmois10String)) {
					try {
						mjmNmois10 = Integer.valueOf(mjmNmois10String);
					} catch (Exception e) {
						mjmNmois10 = null;
					}
				}

				final String pcNuitNmois10 = pcNuitNmois10String;
				
				Integer mjmNmois11 = null;

				if (!StringUtils.isBlank(mjmNmois11String)) {
					try {
						mjmNmois11 = Integer.valueOf(mjmNmois11String);
					} catch (Exception e) {
						mjmNmois11 = null;
					}
				}

				final String pcNuitNmois11 = pcNuitNmois11String;
				
				Integer mjmNmois12 = null;

				if (!StringUtils.isBlank(mjmNmois12String)) {
					try {
						mjmNmois12 = Integer.valueOf(mjmNmois12String);
					} catch (Exception e) {
						mjmNmois12 = null;
					}
				}

				final String pcNuitNmois12 = pcNuitNmois12String;
				
				final String zoneLibre3 = zoneLibre3String;
				
				LocalDate anneeNmoins1 = null;

				if (!StringUtils.isBlank(anneeNmoins1String)) {
					try {
						anneeNmoins1 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeNmoins1String);
					} catch (Exception e) {
						anneeNmoins1 = null;
					}
				}

				Integer mjaNmoins1 = null;

				if (!StringUtils.isBlank(mjaNmoins1String)) {
					try {
						mjaNmoins1 = Integer.valueOf(mjaNmoins1String);
					} catch (Exception e) {
						mjaNmoins1 = null;
					}
				}

				final String typeComptageNmoins1 = typeComptageNmoins1String;
				final String modeCalculNmoins1 = modeCalculNmoins1String;
				final String pcPLNmoins1 = pcPLNmoins1String;
				final String evaluationPLNmoins1 = evaluationPLNmoins1String;
				final String pcNuitAnnuelNmoins1 = pcNuitAnnuelNmoins1String;
				final String indiceFiabiliteMjaNmoins1 = indiceFiabiliteMjaNmoins1String;
				
				LocalDate anneeNmoins2 = null;

				if (!StringUtils.isBlank(anneeNmoins2String)) {
					try {
						anneeNmoins2 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeNmoins2String);
					} catch (Exception e) {
						anneeNmoins2 = null;
					}
				}

				Integer mjaNmoins2 = null;

				if (!StringUtils.isBlank(mjaNmoins2String)) {
					try {
						mjaNmoins2 = Integer.valueOf(mjaNmoins2String);
					} catch (Exception e) {
						mjaNmoins2 = null;
					}
				}

				final String typeComptageNmoins2 = typeComptageNmoins2String;
				final String modeCalculNmoins2 = modeCalculNmoins2String;
				final String pcPLNmoins2 = pcPLNmoins2String;
				final String evaluationPLNmoins2 = evaluationPLNmoins2String;
				final String pcNuitAnnuelNmoins2 = pcNuitAnnuelNmoins2String;
				final String indiceFiabiliteMjaNmoins2 = indiceFiabiliteMjaNmoins2String;
				
				LocalDate anneeNmoins3 = null;

				if (!StringUtils.isBlank(anneeNmoins3String)) {
					try {
						anneeNmoins3 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeNmoins3String);
					} catch (Exception e) {
						anneeNmoins3 = null;
					}
				}

				Integer mjaNmoins3 = null;

				if (!StringUtils.isBlank(mjaNmoins3String)) {
					try {
						mjaNmoins3 = Integer.valueOf(mjaNmoins3String);
					} catch (Exception e) {
						mjaNmoins3 = null;
					}
				}

				final String typeComptageNmoins3 = typeComptageNmoins3String;
				final String modeCalculNmoins3 = modeCalculNmoins3String;
				final String pcPLNmoins3 = pcPLNmoins3String;
				final String evaluationPLNmoins3 = evaluationPLNmoins3String;
				final String pcNuitAnnuelNmoins3 = pcNuitAnnuelNmoins3String;
				final String indiceFiabiliteMjaNmoins3 = indiceFiabiliteMjaNmoins3String;
				
				LocalDate anneeNmoins4 = null;

				if (!StringUtils.isBlank(anneeNmoins4String)) {
					try {
						anneeNmoins4 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeNmoins4String);
					} catch (Exception e) {
						anneeNmoins4 = null;
					}
				}

				Integer mjaNmoins4 = null;

				if (!StringUtils.isBlank(mjaNmoins4String)) {
					try {
						mjaNmoins4 = Integer.valueOf(mjaNmoins4String);
					} catch (Exception e) {
						mjaNmoins4 = null;
					}
				}

				final String typeComptageNmoins4 = typeComptageNmoins4String;
				final String modeCalculNmoins4 = modeCalculNmoins4String;
				final String pcPLNmoins4 = pcPLNmoins4String;
				final String evaluationPLNmoins4 = evaluationPLNmoins4String;
				final String pcNuitAnnuelNmoins4 = pcNuitAnnuelNmoins4String;
				String indiceFiabiliteMjaNmoins4 = indiceFiabiliteMjaNmoins4String;
				
				LocalDate anneeNmoins5 = null;

				if (!StringUtils.isBlank(anneeNmoins5String)) {
					try {
						anneeNmoins5 
							= fournirDateAvecAnneeSurDeuxChiffres(
									anneeNmoins5String);
					} catch (Exception e) {
						anneeNmoins5 = null;
					}
				}

				Integer mjaNmoins5 = null;

				if (!StringUtils.isBlank(mjaNmoins5String)) {
					try {
						mjaNmoins5 = Integer.valueOf(mjaNmoins5String);
					} catch (Exception e) {
						mjaNmoins5 = null;
					}
				}

				final String typeComptageNmoins5 = typeComptageNmoins5String;
				final String modeCalculNmoins5 = modeCalculNmoins5String;
				final String pcPLNmoins5 = pcPLNmoins5String;
				final String evaluationPLNmoins5 = evaluationPLNmoins5String;
				final String pcNuitAnnuelNmoins5 = pcNuitAnnuelNmoins5String;
				final String indiceFiabiliteMjaNmoins5 = indiceFiabiliteMjaNmoins5String;
				
				Integer mjmNmoins1mois01 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois01String)) {
					try {
						mjmNmoins1mois01 = Integer.valueOf(mjmNmoins1mois01String);
					} catch (Exception e) {
						mjmNmoins1mois01 = null;
					}
				}

				final String pcNuitNmoins1mois01 = pcNuitNmoins1mois01String;
				
				Integer mjmNmoins1mois02 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois02String)) {
					try {
						mjmNmoins1mois02 = Integer.valueOf(mjmNmoins1mois02String);
					} catch (Exception e) {
						mjmNmoins1mois02 = null;
					}
				}

				final String pcNuitNmoins1mois02 = pcNuitNmoins1mois02String;
				
				Integer mjmNmoins1mois03 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois03String)) {
					try {
						mjmNmoins1mois03 = Integer.valueOf(mjmNmoins1mois03String);
					} catch (Exception e) {
						mjmNmoins1mois03 = null;
					}
				}

				final String pcNuitNmoins1mois03 = pcNuitNmoins1mois03String;
				
				Integer mjmNmoins1mois04 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois04String)) {
					try {
						mjmNmoins1mois04 = Integer.valueOf(mjmNmoins1mois04String);
					} catch (Exception e) {
						mjmNmoins1mois04 = null;
					}
				}

				final String pcNuitNmoins1mois04 = pcNuitNmoins1mois04String;
				
				Integer mjmNmoins1mois05 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois05String)) {
					try {
						mjmNmoins1mois05 = Integer.valueOf(mjmNmoins1mois05String);
					} catch (Exception e) {
						mjmNmoins1mois05 = null;
					}
				}

				final String pcNuitNmoins1mois05 = pcNuitNmoins1mois05String;
				
				Integer mjmNmoins1mois06 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois06String)) {
					try {
						mjmNmoins1mois06 = Integer.valueOf(mjmNmoins1mois06String);
					} catch (Exception e) {
						mjmNmoins1mois06 = null;
					}
				}

				final String pcNuitNmoins1mois06 = pcNuitNmoins1mois06String;
				
				Integer mjmNmoins1mois07 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois07String)) {
					try {
						mjmNmoins1mois07 = Integer.valueOf(mjmNmoins1mois07String);
					} catch (Exception e) {
						mjmNmoins1mois07 = null;
					}
				}

				final String pcNuitNmoins1mois07 = pcNuitNmoins1mois07String;
				
				Integer mjmNmoins1mois08 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois08String)) {
					try {
						mjmNmoins1mois08 = Integer.valueOf(mjmNmoins1mois08String);
					} catch (Exception e) {
						mjmNmoins1mois08 = null;
					}
				}

				final String pcNuitNmoins1mois08 = pcNuitNmoins1mois08String;
				
				Integer mjmNmoins1mois09 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois09String)) {
					try {
						mjmNmoins1mois09 = Integer.valueOf(mjmNmoins1mois09String);
					} catch (Exception e) {
						mjmNmoins1mois09 = null;
					}
				}

				final String pcNuitNmoins1mois09 = pcNuitNmoins1mois09String;
				
				Integer mjmNmoins1mois10 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois10String)) {
					try {
						mjmNmoins1mois10 = Integer.valueOf(mjmNmoins1mois10String);
					} catch (Exception e) {
						mjmNmoins1mois10 = null;
					}
				}

				final String pcNuitNmoins1mois10 = pcNuitNmoins1mois10String;
				
				Integer mjmNmoins1mois11 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois11String)) {
					try {
						mjmNmoins1mois11 = Integer.valueOf(mjmNmoins1mois11String);
					} catch (Exception e) {
						mjmNmoins1mois11 = null;
					}
				}

				final String pcNuitNmoins1mois11 = pcNuitNmoins1mois11String;
				
				Integer mjmNmoins1mois12 = null;

				if (!StringUtils.isBlank(mjmNmoins1mois12String)) {
					try {
						mjmNmoins1mois12 = Integer.valueOf(mjmNmoins1mois12String);
					} catch (Exception e) {
						mjmNmoins1mois12 = null;
					}
				}

				final String pcNuitNmoins1mois12 = pcNuitNmoins1mois12String;
				
				final String zoneLibre4 = zoneLibre4String;
				
				
				// ***************************************************
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet = new SectionHit();
				
				objet.setId(id);
				objet.setNumDepartement(numDepartement);
				objet.setNumSection(numSection);
				objet.setSens(sens);
				objet.setNature(nature);
				objet.setClasse(classe);
				objet.setAnneeTraitement(anneeTraitement);
				objet.setZoneLibre1(zoneLibre1);
				objet.setNumRoute(numRoute);
				objet.setIndiceNumRoute(indiceNumRoute);
				objet.setIndiceLettreRoute(indiceLettreRoute);
				objet.setCategorieAdminRoute(categorieAdminRoute);
				objet.setTypeComptage(typeComptage);
				objet.setClassementRoute(classementRoute);
				objet.setClasseLargeurChausseeU(classeLargeurChausseeU);
				objet.setClasseLargeurChausseesS(classeLargeurChausseesS);
				objet.setTypeReseau(typeReseau);
				objet.setPRoupK(pRoupK);
				objet.setLieuDitOrigine(lieuDitOrigine);
				objet.setPrOrigine(prOrigine);
				objet.setAbsOrigine(absOrigine);
				objet.setLieuDitExtremite(lieuDitExtremite);
				objet.setPrExtremite(prExtremite);
				objet.setAbsExtremite(absExtremite);
				objet.setLieuDitComptage(lieuDitComptage);
				objet.setPrComptage(prComptage);
				objet.setAbsComptage(absComptage);
				objet.setLongueurSection(longueurSection);
				objet.setLongueurRaseCampagne(longueurRaseCampagne);
				objet.setNumDepartementRattachement(numDepartementRattachement);
				objet.setNumSectionRattachement(numSectionRattachement);
				objet.setSensRattachement(sensRattachement);
				objet.setNumDepartementLimitrophe(numDepartementLimitrophe);
				objet.setNumSectionLimitrophe(numSectionLimitrophe);
				objet.setSensLimitrophe(sensLimitrophe);
				objet.setMoisSectionnement(moisSectionnement);
				objet.setAnneeSectionnement(anneeSectionnement);
				objet.setZoneLibre2(zoneLibre2);
				objet.setMjaN(mjaN);
				objet.setModeCalculN(modeCalculN);
				objet.setPcPLN(pcPLN);
				objet.setEvaluationPLN(evaluationPLN);
				objet.setPcNuitAnnuelN(pcNuitAnnuelN);
				objet.setIndiceFiabiliteMjaN(indiceFiabiliteMjaN);
				objet.setMjmNmois01(mjmNmois01);
				objet.setPcNuitNmois01(pcNuitNmois01);
				objet.setMjmNmois02(mjmNmois02);
				objet.setPcNuitNmois02(pcNuitNmois02);
				objet.setMjmNmois03(mjmNmois03);
				objet.setPcNuitNmois03(pcNuitNmois03);
				objet.setMjmNmois04(mjmNmois04);
				objet.setPcNuitNmois04(pcNuitNmois04);
				objet.setMjmNmois05(mjmNmois05);
				objet.setPcNuitNmois05(pcNuitNmois05);
				objet.setMjmNmois06(mjmNmois06);
				objet.setPcNuitNmois06(pcNuitNmois06);
				objet.setMjmNmois07(mjmNmois07);
				objet.setPcNuitNmois07(pcNuitNmois07);
				objet.setMjmNmois08(mjmNmois08);
				objet.setPcNuitNmois08(pcNuitNmois08);
				objet.setMjmNmois09(mjmNmois09);
				objet.setPcNuitNmois09(pcNuitNmois09);
				objet.setMjmNmois10(mjmNmois10);
				objet.setPcNuitNmois10(pcNuitNmois10);
				objet.setMjmNmois11(mjmNmois11);
				objet.setPcNuitNmois11(pcNuitNmois11);
				objet.setMjmNmois12(mjmNmois12);
				objet.setPcNuitNmois12(pcNuitNmois12);
				objet.setZoneLibre3(zoneLibre3);
				objet.setAnneeNmoins1(anneeNmoins1);
				objet.setMjaNmoins1(mjaNmoins1);
				objet.setTypeComptageNmoins1(typeComptageNmoins1);
				objet.setModeCalculNmoins1(modeCalculNmoins1);
				objet.setPcPLNmoins1(pcPLNmoins1);
				objet.setEvaluationPLNmoins1(evaluationPLNmoins1);
				objet.setPcNuitAnnuelNmoins1(pcNuitAnnuelNmoins1);
				objet.setIndiceFiabiliteMjaNmoins1(indiceFiabiliteMjaNmoins1);
				objet.setAnneeNmoins2(anneeNmoins2);
				objet.setMjaNmoins2(mjaNmoins2);
				objet.setTypeComptageNmoins2(typeComptageNmoins2);
				objet.setModeCalculNmoins2(modeCalculNmoins2);
				objet.setPcPLNmoins2(pcPLNmoins2);
				objet.setEvaluationPLNmoins2(evaluationPLNmoins2);
				objet.setPcNuitAnnuelNmoins2(pcNuitAnnuelNmoins2);
				objet.setIndiceFiabiliteMjaNmoins2(indiceFiabiliteMjaNmoins2);
				objet.setAnneeNmoins3(anneeNmoins3);
				objet.setMjaNmoins3(mjaNmoins3);
				objet.setTypeComptageNmoins3(typeComptageNmoins3);
				objet.setModeCalculNmoins3(modeCalculNmoins3);
				objet.setPcPLNmoins3(pcPLNmoins3);
				objet.setEvaluationPLNmoins3(evaluationPLNmoins3);
				objet.setPcNuitAnnuelNmoins3(pcNuitAnnuelNmoins3);
				objet.setIndiceFiabiliteMjaNmoins3(indiceFiabiliteMjaNmoins3);
				objet.setAnneeNmoins4(anneeNmoins4);
				objet.setMjaNmoins4(mjaNmoins4);
				objet.setTypeComptageNmoins4(typeComptageNmoins4);
				objet.setModeCalculNmoins4(modeCalculNmoins4);
				objet.setPcPLNmoins4(pcPLNmoins4);
				objet.setEvaluationPLNmoins4(evaluationPLNmoins4);
				objet.setPcNuitAnnuelNmoins4(pcNuitAnnuelNmoins4);
				objet.setIndiceFiabiliteMjaNmoins4(indiceFiabiliteMjaNmoins4);
				objet.setAnneeNmoins5(anneeNmoins5);
				objet.setMjaNmoins5(mjaNmoins5);
				objet.setTypeComptageNmoins5(typeComptageNmoins5);
				objet.setModeCalculNmoins5(modeCalculNmoins5);
				objet.setPcPLNmoins5(pcPLNmoins5);
				objet.setEvaluationPLNmoins5(evaluationPLNmoins5);
				objet.setPcNuitAnnuelNmoins5(pcNuitAnnuelNmoins5);
				objet.setIndiceFiabiliteMjaNmoins5(indiceFiabiliteMjaNmoins5);
				objet.setMjmNmoins1mois01(mjmNmoins1mois01);
				objet.setPcNuitNmoins1mois01(pcNuitNmoins1mois01);
				objet.setMjmNmoins1mois02(mjmNmoins1mois02);
				objet.setPcNuitNmoins1mois02(pcNuitNmoins1mois02);
				objet.setMjmNmoins1mois03(mjmNmoins1mois03);
				objet.setPcNuitNmoins1mois03(pcNuitNmoins1mois03);
				objet.setMjmNmoins1mois04(mjmNmoins1mois04);
				objet.setPcNuitNmoins1mois04(pcNuitNmoins1mois04);
				objet.setMjmNmoins1mois05(mjmNmoins1mois05);
				objet.setPcNuitNmoins1mois05(pcNuitNmoins1mois05);
				objet.setMjmNmoins1mois06(mjmNmoins1mois06);
				objet.setPcNuitNmoins1mois06(pcNuitNmoins1mois06);
				objet.setMjmNmoins1mois07(mjmNmoins1mois07);
				objet.setPcNuitNmoins1mois07(pcNuitNmoins1mois07);
				objet.setMjmNmoins1mois08(mjmNmoins1mois08);
				objet.setPcNuitNmoins1mois08(pcNuitNmoins1mois08);
				objet.setMjmNmoins1mois09(mjmNmoins1mois09);
				objet.setPcNuitNmoins1mois09(pcNuitNmoins1mois09);
				objet.setMjmNmoins1mois10(mjmNmoins1mois10);
				objet.setPcNuitNmoins1mois10(pcNuitNmoins1mois10);
				objet.setMjmNmoins1mois11(mjmNmoins1mois11);
				objet.setPcNuitNmoins1mois11(pcNuitNmoins1mois11);
				objet.setMjmNmoins1mois12(mjmNmoins1mois12);
				objet.setPcNuitNmoins1mois12(pcNuitNmoins1mois12);
				objet.setZoneLibre4(zoneLibre4);
				
			} // Fin de if (pDTO != null).___________________________
			
			return objet;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirDTOEnObjetMetier(...).____________________________
	

	
	/**
	 * <b>convertit un OBJET METIER en DTO</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>convertit les types de l'Objet métier en String du DTO.</li>
	 * <li>injecte les valeurs String dans un DTO 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pObject : ISectionHit : 
	 * Objet métier.<br/>
	 * 
	 * @return : ISectionHitDTO : DTO.<br/>
	 */
	public static ISectionHitDTO convertirObjetMetierEnDTO(
			final ISectionHit pObject) {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			ISectionHitDTO dto = null;
			
			if (pObject != null) {
				
				// ****************************************************
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				
				// ****************************************************
				/* convertit les types de l'Objet métier en String du DTO. */
				
				// ****************************************************
				/* injecte les valeurs String dans un DTO. */
				dto = new SectionHitDTO();
				
			}
						
			return dto;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnDTO(...).____________________________
	
	
	
	/**
	 * convertit une liste d'OBJETS METIER en liste 
	 * de DTOs.<br/>
	 * <br/>
	 * - retourne null si pListeObjets == null.<br/>
	 * <br/>
	 *
	 * @param pListeObjets : List&lt;ISectionHit&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;ISectionHitDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<ISectionHitDTO> convertirListeObjetEnListeDTO(
			final List<ISectionHit> pListeObjets) {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<ISectionHitDTO> resultat 
				= new ArrayList<ISectionHitDTO>();
			
			for (final ISectionHit objet : pListeObjets) {
				
				final ISectionHitDTO dto 
					= convertirObjetMetierEnDTO(objet);
				
				resultat.add(dto);
				
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirListeObjetEnListeDTO(...).________________________

	
	
	/**
	 * retourne une LocalDate positionnée au 1er Janvier 20AA 
	 * ou AA est l'année sur deux chiffres fournie dans pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * - LOG.fatal et retourne null si pString n'est pas homogène 
	 * à une année sur deux chiffres.<br/>
	 * <br/>
	 *
	 * @param pString : String : année sur deux chiffres comme "19" pour 2019.
	 * 
	 * @return : LocalDate : 1er Janvier 20AA.<br/>
	 */
	private static LocalDate fournirDateAvecAnneeSurDeuxChiffres(
			final String pString) {

		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			LocalDate resultat = null;
			
			final String motifAnneeSurDeuxChiffres = "\\d\\d";
			final Pattern patternAnneeSurDeuxChiffres 
				= Pattern.compile(motifAnneeSurDeuxChiffres); 
			final Matcher matcher = patternAnneeSurDeuxChiffres.matcher(pString);
			
			/* retourne null si pString n'est pas homogène 
			 * à une année sur deux chiffres. */
			if (!matcher.matches()) {
				
				final String message 
					= CLASSE_SECTION_HIT_CONVERTISSEUR_METIER_DTO
					+ MOINS_ESPACE
					+ "méthode fournirDateAvecAnneeSurDeuxChiffres(String)"
					+ MOINS_ESPACE 
					+ "pString passé en paramètre n'est pas homogène à une année sur deux chiffres : " 
					+ pString;
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				return null;
			}
			
			final String anneeString = "20" + pString;
			
			final Integer annee = Integer.valueOf(anneeString);
			
			resultat = LocalDate.of(annee, 1, 1);
			
			return resultat;

		} // Fin de synchronized.____________________________________
		
	} // Fin de fournirDateAvecAnneeSurDeuxChiffres(...).__________________

	
	
	/**
	 * retourne un Integer à partir de la chaine fournie dans pString.<br/>
	 * <br/>
	 * - retire d'éventuels espaces avant et/ou après la valeur entière.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - LOG.fatal et retourne null si pString n'est pas homogène 
	 * à un entier.<br/>
	 * <br/>
	 *
	 * @param pString : String : String homogène à un Integer.
	 * 
	 * @return : Integer.<br/>
	 */
	private static Integer fournirInteger(final String pString) {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			Integer resultat = null;
			
			final String motifInteger = "\\s*(\\d+)\\s*";
			final Pattern patternInteger 
				= Pattern.compile(motifInteger); 
			final Matcher matcher = patternInteger.matcher(pString);
			
			/* retourne null si pString n'est pas homogène 
			 * à un entier. */
			if (!matcher.matches()) {
				
				final String message 
					= CLASSE_SECTION_HIT_CONVERTISSEUR_METIER_DTO
					+ MOINS_ESPACE
					+ "méthode fournirInteger(String)"
					+ MOINS_ESPACE 
					+ "pString passé en paramètre n'est pas homogène à un entier : " 
					+ pString;
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				return null;
			}

			/* retire d'éventuels espaces avant et/ou après la valeur entière. */
			final String valeur = matcher.group(1);
			
			resultat = Integer.valueOf(valeur);
			
			return resultat;

		} // Fin de synchronized._________________________________
		
	} // Fin de fournirInteger(...)._______________________________________

	
	
	/**
	 * retourne sous forme de String les deux derniers chiffres 
	 * de l'année d'une LocalDate.<br/>
	 * Par exemple : "19" pour 2019.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDate.
	 * 
	 * @return : String : 
	 * deux derniers chiffres de l'année de la LocalDate.<br/>
	 */
	private static String fournirAnneeDeuxChiffresAPartirDate(
			final LocalDate pDate) {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pDate == null. */
			if (pDate == null) {
				return null;
			}
			
			String resultat = null;
			
			final String motifFormatAnneeSurDeuxChiffres = "yy";
			
			final DateTimeFormatter formatterAnneeSurDeuxChiffres 
				= DateTimeFormatter.ofPattern(motifFormatAnneeSurDeuxChiffres);
			
			resultat = pDate.format(formatterAnneeSurDeuxChiffres);
			
			return resultat;

		} // Fin de synchronized._____________________________________
		
	} // Fin de fournirAnneeDeuxChiffresAPartirDate(...).__________________


	
	/**
	 * <b>retourne la chaine de caractères pString complétée 
	 * avec des zéros à gauche pour atteindre pTaille</b>.<br/>
	 * <ul>
	 * <li>Par exemple : retourne "0025" 
	 * si pString == "25" et pTaille == 4.</li>
	 * <li>retourne pString inchangée si sa longueur >= pTaille.</li>
	 * </ul>
	 * - retourne null si pTaille == 0.<br/>
	 * - retourne null si pString == null.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chaine de caractères à compléter avec des zéros à gauche.
	 * @param pTaille : 
	 * taille finale de la chaine complétée avec des zéros à gauche.
	 * 
	 * @return : String : 
	 * chaine de caractère pString complétée avec des zéros à gauche 
	 * pour atteindre pTaille.<br/>
	 */
	private static String completerAvecZerosAGauche(
			final String pString, final int pTaille) {

		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pTaille == 0. */
			if (pTaille == 0) {
				return null;
			}
			
			/* retourne null si pString == null. */
			if (pString == null) {
				return null;
			}
			
			final int tailleString = pString.length();
			
			/* retourne pString inchangée si sa longueur >= pTaille. */
			if (tailleString >= pTaille) {
				return pString;
			}
			
			String resultat = null;
			
			final int nombreZeros = pTaille - tailleString;
			
			resultat = pString;
			
			for (int i = 0; i < nombreZeros; i++) {
				resultat = "0" + resultat;
			}
			
			return resultat;

		} // Fin de synchronized.__________________________________
		
	} // Fin de completerAvecZerosAGauche(...).____________________________
	

	
	/**
	 * retourne la longueur du champ de numéro d'ordre pNumeroChamp 
	 * dans la description de fichier 
	 * <code><b>this.descriptionFichier</b></code>.<br/>
	 * <br/>
	 * Par exemple : <code><b>fournirlongueurChamp(20)</b></code> 
	 * retourne 4 pour le champ absOrigine du HIT.
	 *
	 * @param pNumeroChamp : int : numéro d'ordre du champ dans la description.
	 * 
	 * @return : int : 
	 * longueur du champ d'ordre pNumeroChamp dans la description du fichier.
	 * 
	 * @throws Exception 
	 */
	private static int fournirLongueurChamp(
			final int pNumeroChamp) throws Exception {
		
		synchronized (SectionHitConvertisseurMetierDTO.class) {
			
			FactoryDescription.getDecriptionHitMap();		
			final IImportateurDescription descriptionFichier 
				= FactoryDescription.getImportateurHit();
			
			DescriptionChampHit description = null;
			int longueurChamp = 0;
			
			try {
				
				description 
				= (DescriptionChampHit) 
					descriptionFichier.getDescriptionChamp(pNumeroChamp);
				
				longueurChamp = description.getLongueur();
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			return longueurChamp;

		} // Fin de synchronized._______________________________________
		
	} // Fin de fournirLongueurChamp(...)._________________________________

	
	
} // FIN DE LA CLASSE SectionHitConvertisseurMetierDTO.----------------------
