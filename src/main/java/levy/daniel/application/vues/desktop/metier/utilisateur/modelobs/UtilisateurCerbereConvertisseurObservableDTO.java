package levy.daniel.application.vues.desktop.metier.utilisateur.modelobs;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl.UtilisateurCerbereModelObs;

/**
 * CLASSE UtilisateurCerbereConvertisseurObservableDTO :<br/>
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
 * @since 28 févr. 2019
 *
 */
public final class UtilisateurCerbereConvertisseurObservableDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereConvertisseurObservableDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	private UtilisateurCerbereConvertisseurObservableDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>convertit un DTO en OBSERVABLE</b>.<br/>
	 * <ul>
	 * <li>retourne null si pDTO == null.</li>
	 * <li>transforme une <code>String</code> en 
	 * OBSERVABLE <code>StringProperty</code> 
	 * à l'aide de <code>new SimpleStringProperty(String)</code>.</li>
	 * </ul>
	 *
	 * @param pDTO : IUtilisateurCerbereDTO.<br/>
	 * 
	 * @return : IUtilisateurCerbereModelObs : OBSERVABLE.<br/>
	 */
	public static IUtilisateurCerbereModelObs convertirDTOEnObservable(
										final IUtilisateurCerbereDTO pDTO) {
		
		synchronized (UtilisateurCerbereConvertisseurObservableDTO.class) {
			
			IUtilisateurCerbereModelObs resultat = null;
			
			if (pDTO != null) {
								
				/* Instanciation des StringProperty 
				 * à partir des String. */
				final StringProperty idStringProperty 
					= new SimpleStringProperty(pDTO.getId());
				final StringProperty civiliteStringProperty 
					= new SimpleStringProperty(pDTO.getCivilite());
				final StringProperty prenomStringProperty 
					= new SimpleStringProperty(pDTO.getPrenom());
				final StringProperty nomStringProperty 
					= new SimpleStringProperty(pDTO.getNom());
				final StringProperty telephoneStringProperty 
					= new SimpleStringProperty(pDTO.getTel());
				final StringProperty mailStringProperty 
					= new SimpleStringProperty(pDTO.getEmail());
				final StringProperty serviceStringProperty 
					= new SimpleStringProperty(pDTO.getService());
				final StringProperty uniteStringProperty 
					= new SimpleStringProperty(pDTO.getUnite());
				final StringProperty profilStringProperty 
					= new SimpleStringProperty(pDTO.getProfil());
				final StringProperty porteeStringProperty 
					= new SimpleStringProperty(pDTO.getPortee());
				final StringProperty restrictionStringProperty 
					= new SimpleStringProperty(pDTO.getRestriction());
				
				resultat 
					= new UtilisateurCerbereModelObs(
							idStringProperty
							, civiliteStringProperty
							, prenomStringProperty, nomStringProperty
							, telephoneStringProperty, mailStringProperty
							, serviceStringProperty, uniteStringProperty
							, profilStringProperty
							, porteeStringProperty
							, restrictionStringProperty);
				
			}
			
			/* retourne null si pDTO == null. */
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirDTOEnObservable(...)._____________________________
	

	
	/**
	 * <b>convertit un OBSERVABLE en DTO</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObservable == null.</li>
	 * <li>récupère les valeurs dans les Properties de l'OBSERVABLE.</li>
	 * <li>instancie un DTO avec les valeurs dans l'OBSERVABLE.</li>
	 * </ul>
	 *
	 * @param pObservable : IUtilisateurCerbereModelObs.<br/>
	 * 
	 * @return : IUtilisateurCerbereDTO : DTO.<br/>
	 */
	public static IUtilisateurCerbereDTO convertirObservableEnDTO(
							final IUtilisateurCerbereModelObs pObservable) {
		
		synchronized (UtilisateurCerbereConvertisseurObservableDTO.class) {
			
			IUtilisateurCerbereDTO resultat = null;
			
			if (pObservable != null) {
				
				/* récupère les valeurs dans les Properties 
				 * de l'OBSERVABLE. */
				final String idString = pObservable.getId();
				final String civiliteString = pObservable.getCivilite();
				final String prenomString = pObservable.getPrenom();
				final String nomString = pObservable.getNom();
				final String telString = pObservable.getTel();
				final String emailString = pObservable.getEmail();
				final String serviceString = pObservable.getService();
				final String uniteString = pObservable.getUnite();
				final String profilString = pObservable.getProfil();
				final String porteeString = pObservable.getPortee();
				final String restrictionString = pObservable.getRestriction();
				
				/* instancie un DTO avec les valeurs dans l'OBSERVABLE. */
				resultat 
					= new UtilisateurCerbereDTO(
						idString
						, civiliteString
						, prenomString, nomString
						, telString, emailString
						, serviceString, uniteString
						, profilString
						, porteeString
						, restrictionString);
				
			}
			
			/* retourne null si pObservable == null. */
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirObservableEnDTO(...)._____________________________
	
	
	
	/**
	 * <b>convertit un OBJET METIER en OBSERVABLE</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>transforme une <code>String</code> en 
	 * OBSERVABLE <code>StringProperty</code> 
	 * à l'aide de <code>new SimpleStringProperty(String)</code>.</li>
	 * </ul>
	 *
	 * @param pObject : IUtilisateurCerbere.<br/>
	 * 
	 * @return : IUtilisateurCerbereModelObs : OBSERVABLE.<br/>
	 */
	public static IUtilisateurCerbereModelObs convertirObjetEnObservable(
										final IUtilisateurCerbere pObject) {
		
		synchronized (UtilisateurCerbereConvertisseurObservableDTO.class) {
			
			IUtilisateurCerbereModelObs resultat = null;
			
			if (pObject != null) {
				
				final Long id = pObject.getId();
				String idString = null;
				
				if (id != null) {					
					idString = String.valueOf(id);
				}
				
				/* Instanciation des StringProperty 
				 * à partir des String. */
				final StringProperty idStringProperty 
					= new SimpleStringProperty(idString);
				final StringProperty civiliteStringProperty 
					= new SimpleStringProperty(pObject.getCivilite());
				final StringProperty prenomStringProperty 
					= new SimpleStringProperty(pObject.getPrenom());
				final StringProperty nomStringProperty 
					= new SimpleStringProperty(pObject.getNom());
				final StringProperty telephoneStringProperty 
					= new SimpleStringProperty(pObject.getTel());
				final StringProperty mailStringProperty 
					= new SimpleStringProperty(pObject.getEmail());
				final StringProperty serviceStringProperty 
					= new SimpleStringProperty(pObject.getService());
				final StringProperty uniteStringProperty 
					= new SimpleStringProperty(pObject.getUnite());
				final StringProperty profilStringProperty 
					= new SimpleStringProperty(pObject.getProfil());
				final StringProperty porteeStringProperty 
					= new SimpleStringProperty(pObject.getPortee());
				final StringProperty restrictionStringProperty 
					= new SimpleStringProperty(pObject.getRestriction());
				
				resultat 
					= new UtilisateurCerbereModelObs(
							idStringProperty
							, civiliteStringProperty
							, prenomStringProperty, nomStringProperty
							, telephoneStringProperty, mailStringProperty
							, serviceStringProperty, uniteStringProperty
							, profilStringProperty
							, porteeStringProperty
							, restrictionStringProperty);
				
			}
			
			/* retourne null si pObject == null. */
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirObjetEnObservable(...).___________________________
	

	
	/**
	 * <b>convertit un OBSERVABLE en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObservable == null.</li>
	 * <li>récupère les valeurs dans les Properties de l'OBSERVABLE.</li>
	 * <li>instancie un OBJET METIER avec les valeurs dans l'OBSERVABLE.</li>
	 * </ul>
	 *
	 * @param pObservable : IUtilisateurCerbereModelObs.<br/>
	 * 
	 * @return : IUtilisateurCerbere : OBJET METIER.<br/>
	 */
	public static IUtilisateurCerbere convertirObservableEnObjet(
							final IUtilisateurCerbereModelObs pObservable) {
		
		synchronized (UtilisateurCerbereConvertisseurObservableDTO.class) {
			
			IUtilisateurCerbere resultat = null;
			
			if (pObservable != null) {
				
				/* récupère les valeurs dans les Properties 
				 * de l'OBSERVABLE. */
				final String idString = pObservable.getId();
				final String civiliteString = pObservable.getCivilite();
				final String prenomString = pObservable.getPrenom();
				final String nomString = pObservable.getNom();
				final String telString = pObservable.getTel();
				final String emailString = pObservable.getEmail();
				final String serviceString = pObservable.getService();
				final String uniteString = pObservable.getUnite();
				final String profilString = pObservable.getProfil();
				final String porteeString = pObservable.getPortee();
				final String restrictionString = pObservable.getRestriction();
				
				/* instancie un DTO avec les valeurs dans l'OBSERVABLE. */
				Long id = null;
				
				if (idString != null) {
					id = Long.valueOf(idString);
				}
				
				resultat 
					= new UtilisateurCerbere(
						id
						, civiliteString
						, prenomString, nomString
						, telString, emailString
						, serviceString, uniteString
						, profilString
						, porteeString
						, restrictionString);
				
			}
			
			/* retourne null si pObservable == null. */
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirObservableEnObjet(...).___________________________
	
	
	
	/**
	 * convertit une liste d'OBJETS METIER en ObservableList 
	 * d'OBSERVABLES.<br/>
	 * <br/>
	 * - retourne null si pListeObjets == null.<br/>
	 * <br/>
	 *
	 * @param pListeObjets : List&lt;IUtilisateurCerbere&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * javafx.collections.ObservableList&lt;IUtilisateurCerbereModelObs&gt; : 
	 * Liste d'OBSERVABLES.<br/>
	 */
	public static ObservableList<IUtilisateurCerbereModelObs> 
							convertirListObjetsEnObservableList(
			final List<IUtilisateurCerbere> pListeObjets) {	
		
		synchronized (UtilisateurCerbereConvertisseurObservableDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final ObservableList<IUtilisateurCerbereModelObs> resultat 
				= FXCollections.observableArrayList();
			
			for (final IUtilisateurCerbere objet : pListeObjets) {
				
				final IUtilisateurCerbereModelObs observable 
					= convertirObjetEnObservable(objet);
				
				resultat.add(observable);
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirListObjetsEnObservableList(...).__________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereConvertisseurObservableDTO.----------
