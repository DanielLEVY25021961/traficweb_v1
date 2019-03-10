package levy.daniel.application.vues.desktop.panneauxcommuns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * CLASSE PanneauGestionCreation :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 8 mars 2019
 *
 */
public class PanneauGestionCreation extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * marge par défaut autout d'un composant.<br/>
	 * 2d<br/>
	 */
	public static final double MARGIN = 5d;
	
	/**
	 * Image pour le Button this.boutonEnregistrer.
	 */
	public static final Image IMAGE_ENREGISTRER 
		= new Image("static/icones/disque-lecteur-disquette-icone-4702-16.png");
	
	/**
	 * ImageView pour le Button this.boutonEnregistrer.
	 */
	public static final ImageView ICONE_ENREGISTRER 
		= new ImageView(IMAGE_ENREGISTRER); 
	
	/**
	 * Button pour l'enregistrement d'un OBJET METIER.
	 */
	private final transient Button boutonEnregistrer 
		= new Button("Enregistrer", ICONE_ENREGISTRER);

	/**
	 * Image pour le Button this.boutonAnnuler.
	 */
	public static final Image IMAGE_ANNULER 
		= new Image("static/icones/Eraser_Icon_16.png");
	
	/**
	 * ImageView pour le Button this.boutonAnnuler.
	 */
	public static final ImageView ICONE_ANNULER 
		= new ImageView(IMAGE_ANNULER); 

	/**
	 * Button pour l'annulation d'une saisie d'un OBJET METIER.
	 */
	private final transient Button boutonAnnuler 
		= new Button("Annuler", ICONE_ANNULER);

	/**
	 * Image pour le Button this.boutonQuitter.
	 */
	public static final Image IMAGE_QUITTER 
		= new Image("static/icones/fermer-croix-supprimer-erreurs-sortie-icone-4368-16.png");
	
	/**
	 * ImageView pour le Button this.boutonQuitter.
	 */
	public static final ImageView ICONE_QUITTER 
		= new ImageView(IMAGE_QUITTER); 

	/**
	 * Button pour quitter la fenêtre de saisie d'un OBJET METIER.
	 */
	private final transient Button boutonQuitter 
		= new Button("Quitter", ICONE_QUITTER);

	/**
	 * HBox pour le Layout des Buttons.
	 */
	private final transient HBox hbox = new HBox();

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(PanneauGestionCreation.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 */
	public PanneauGestionCreation() {
		
		super();
		
		/* configure la présente vue (AnchorPane). */
		this.configurerVue();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * configure la présente vue (AnchorPane).<br/>
	 * <ul>
	 * <li>configure tous les composants (labels, zones de texte, ...).</li>
	 * <li>positionne chaque composants dans un HBOX.</li>
	 * <li>ajoute chaque composant dans le HBOX.</li>
	 * <li>ajoute le HBOX au présent AnchorPane.</li>
	 * </ul>
	 */
	private void configurerVue() {
		
		/* configure tous les composants (labels, zones de texte, ...). */
		this.configurerComposants();
		
		/* positionne chaque composants dans un HBOX. */
		this.positionnerComposantsDansHBox();
		
		this.hbox.setStyle("-fx-background-color: #98fb98;");
		
		/* ajoute le HBOX au présent AnchorPane. */
		this.getChildren().add(this.hbox);
		
		/* fixe les distances entre le contenu (this.hbox) 
		 * et le présent conteneur AnchorPane. */
		AnchorPane.setTopAnchor(this.hbox, 10D);
		AnchorPane.setLeftAnchor(this.hbox, 10D);
		AnchorPane.setBottomAnchor(this.hbox, 10D);
		AnchorPane.setRightAnchor(this.hbox, 10D);
		
		
		/* ajoute une classe CSS au présent AnchorPane 
		 * (les AnchorPanes n'ont pas de classe CSS par défaut). */
		this.getStyleClass().add("anchorpane");
		
		
	} // Fin de configurerVue().___________________________________________
	
	
	
	/**
	 * configure tous les composants (labels, zones de texte, ...).<br/>
	 * <ul>
	 * <li>configure this.boutonEnregistrer.</li>
	 * <li>configure this.boutonAnnuler.</li>
	 * <li>configure this.boutonQuitter.</li>
	 * </ul>
	 */
	private void configurerComposants() {
		
		/* configure this.boutonEnregistrer. */
		this.configurerBoutonEnregistrer();
		
		/* configure this.boutonAnnuler. */
		this.configurerBoutonAnnuler();
		
		/* configure this.boutonQuitter. */
		this.configurerBoutonQuitter();
		
	} // Fin de configurerComposants().____________________________________
	

	
	/**
	 * configure this.boutonEnregistrer.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerBoutonEnregistrer() {
		
		this.boutonEnregistrer.setMaxWidth(Double.MAX_VALUE);
		this.boutonEnregistrer.getStyleClass().add("bouton");
		this.boutonEnregistrer.setTooltip(
				new Tooltip("Enregistrer un objet métier dans le stockage"));
		
	} // Fin de configurerBoutonEnregistrer()._____________________________


	
	/**
	 * configure this.boutonAnnuler.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerBoutonAnnuler() {
		
		this.boutonAnnuler.setMaxWidth(Double.MAX_VALUE);
		this.boutonAnnuler.getStyleClass().add("bouton");
		this.boutonAnnuler.setTooltip(
				new Tooltip("Nettoyer tous les champs de la saisie en cours"));
		
	} // Fin de configurerBoutonAnnuler()._________________________________


	
	/**
	 * configure this.boutonQuitter.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerBoutonQuitter() {
		
		this.boutonQuitter.setMaxWidth(Double.MAX_VALUE);
		this.boutonQuitter.getStyleClass().add("bouton");
		this.boutonQuitter.setTooltip(
				new Tooltip("Quitter la fenêtre de saisie"));
		
	} // Fin de configurerBoutonQuitter()._________________________________


	
	/**
	 * positionne les composants dans la HBOX.<br/>
	 * <ul>
	 * <li>paramètre l'espacement entre les boutons.</li>
	 * <li>positionne this.boutonEnregistrer dans la HBox.</li>
	 * <li>positionne this.boutonAnnuler dans la HBox.</li>
	 * <li>positionne this.boutonQuitter dans la HBox.</li>
	 * </ul>
	 */
	private void positionnerComposantsDansHBox() {
		
		this.hbox.setAlignment(Pos.CENTER);
		
//		this.hbox.setPrefWidth(600d);
		
		
		/* paramètre l'espacement entre les boutons. */
		this.hbox.setSpacing(10d);
		this.hbox.setPadding(new Insets(MARGIN, MARGIN, MARGIN, MARGIN)); 
		
		/* positionne this.boutonEnregistrer dans la HBox. */
		this.hbox.getChildren().add(this.boutonEnregistrer);
		
		/* positionne this.boutonAnnuler dans la HBox. */
		this.hbox.getChildren().add(this.boutonAnnuler);
		
		/* positionne this.boutonQuitter dans la HBox. */
		this.hbox.getChildren().add(this.boutonQuitter);
		
		HBox.setMargin(this.boutonEnregistrer
				, new Insets(MARGIN, MARGIN, MARGIN, MARGIN)); 
		HBox.setMargin(this.boutonAnnuler
				, new Insets(MARGIN, MARGIN, MARGIN, MARGIN)); 
		HBox.setMargin(this.boutonAnnuler
				, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));  
		
		HBox.setHgrow(this.boutonEnregistrer, Priority.ALWAYS);
		HBox.setHgrow(this.boutonAnnuler, Priority.ALWAYS);
		HBox.setHgrow(this.boutonQuitter, Priority.ALWAYS);
		
	} // Fin de positionnerComposantsDansHBox().___________________________


	
	/**
	 * Getter du Button pour l'enregistrement d'un OBJET METIER.
	 *
	 * @return this.boutonEnregistrer : 
	 * javafx.scene.control.Button.<br/>
	 */
	public final Button getBoutonEnregistrer() {
		return this.boutonEnregistrer;
	} // Fin de getBoutonEnregistrer().____________________________________


	
	/**
	 * Getter du Button pour l'annulation d'une saisie d'un OBJET METIER.
	 *
	 * @return this.boutonAnnuler : 
	 * javafx.scene.control.Button.<br/>
	 */
	public final Button getBoutonAnnuler() {
		return this.boutonAnnuler;
	} // Fin de getBoutonAnnuler().________________________________________


	
	/**
	 * Getter du Button pour quitter la fenêtre de saisie d'un OBJET METIER.
	 *
	 * @return this.boutonQuitter : 
	 * javafx.scene.control.Button.<br/>
	 */
	public final Button getBoutonQuitter() {
		return this.boutonQuitter;
	} // Fin de getBoutonQuitter().________________________________________
	
	
	
} // FIN DE LA CLASSE PanneauGestionCreation.--------------------------------
