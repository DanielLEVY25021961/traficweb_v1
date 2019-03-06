package levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * CLASSE ZoneRechercheModelObs :<br/>
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
 * @since 6 mars 2019
 *
 */
public class ZoneRechercheModelObs {

	// ************************ATTRIBUTS************************************/

	/**
	 * OBSERVABLE ASSOCIE à un Zone de Texte (TextField) 
	 * pour la recherche.<br/>
	 */
	private final StringProperty zoneRecherche = new SimpleStringProperty();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ZoneRechercheModelObs.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>ajoute un PropertyListener (ChangeListener) 
	 * à la StringProperty zone de recherche.</li>
	 * </ul>
	 */
	public ZoneRechercheModelObs() {
		
		super();
		
		/* ajoute un PropertyListener (ChangeListener) 
		 * à la StringProperty zone de recherche.*/
		this.ajouterPropertyListener();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * ajoute un PropertyListener (ChangeListener) 
	 * à la StringProperty zone de recherche.<br/>
	 */
	private void ajouterPropertyListener() {
		
		this.getZoneRechercheProperty()
			.addListener(new ChangeListener<String>() {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void changed(
					final ObservableValue<? extends String> pObservable
						, final String pOldValue
							, final String pNewValue) {

				System.out.println("NOUVELLE VALEUR : " + pNewValue);
				
			} // Fin de changed(...)._____________________
						
		}); // Fin de new ChangeListener.________________________
		
	} // Fin de ajouterPropertyListener()._________________________________
	
	
	
	/**
	 * Getter de la <b>valeur</b> dans l' OBSERVABLE ASSOCIE 
	 * à un Zone de Texte pour la recherche.<br/>
	 *
	 * @return this.zoneRecherche.get() : String.<br/>
	 */
	public final String getZoneRecherche() {
		return this.zoneRecherche.get();
	} // Fin de getZoneRecherche().________________________________________


	
	/**
	* Setter de la <b>valeur</b> dans l' OBSERVABLE ASSOCIE 
	* à un Zone de Texte pour la recherche.<br/>
	*
	* @param pZoneRecherche : String : 
	* valeur à passer à this.zoneRecherche.<br/>
	*/
	public final void setZoneRecherche(
			final String pZoneRecherche) {
		this.zoneRecherche.set(pZoneRecherche);
	} // Fin de setZoneRecherche(...)._____________________________________

	
	
	/**
	 * Getter de la <b>Property</b> OBSERVABLE zoneRecherche.<br/>
	 *
	 * @return : StringProperty : this.zoneRecherche.<br/>
	 */
	public final StringProperty getZoneRechercheProperty() {
		return this.zoneRecherche;
	} // Fin de getZoneRechercheProperty().________________________________
	
	
	
} // FIN DE LA CLASSE ZoneRechercheModelObs.---------------------------------
