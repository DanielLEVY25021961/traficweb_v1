package levy.daniel.application.vues.desktop.metier.utilisateur;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.metier.utilisateurs.UtilisateurCerbereResponse;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.IUtilisateurCerbereModelObs;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.UtilisateurCerbereConvertisseurObservableDTO;
import levy.daniel.application.vues.desktop.panneauxcommuns.PanneauGestionCreation;

/**
 * CLASSE UtilisateurCerbereCreationVue :<br/>
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
public class UtilisateurCerbereCreationVueKO extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * marge par défaut autout d'un composant.<br/>
	 * 2d<br/>
	 */
	public static final double MARGIN = 2d;

	/**
	 * THEATRE (Stage = fenêtre) d'affichage de la présente VUE.
	 */
	private final transient Stage stageAffichage;
	
	/**
	 * VUE (AnchorPane) appelant la présente VUE.<br/>
	 * Utile pour les callbacks.<br/>
	 */
	private final transient UtilisateurCerbereAccueilVue vueAppelante;
		
	/**
	 * VUE (AnchorPane) d'<b>édition</b> d'un OBJET METIER.<br/>
	 * Composant du haut de la présente VUE.<br/>
	 */
	private final transient UtilisateurCerbereEditionVueKO editionVue 
		= new UtilisateurCerbereEditionVueKO();
	
	/**
	 * VUE (AnchorPane) de <b>gestion de la création (boutons)</b> 
	 * d'un OBJET METIER.<br/>
	 * Composant du bas de la présente VUE.<br/>
	 */
	private final transient PanneauGestionCreation panneauGestion 
		= new PanneauGestionCreation();
	
	/**
	 * GridPane pour placer les composants dans la présente vue.<br/>
	 */
	private final transient GridPane gridPane = new GridPane();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereCreationVueKO.class);


	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 */
	public UtilisateurCerbereCreationVueKO() {
		
		this(null, null);
				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>passe en paramètre la STAGE (fenêtre) 
	 * affichant la présente VUE</li>
	 * <li>passe en paramètre la VUE appelant la présente 
	 * (Utile pour les callbacks).</li>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 * 
	 * @param pStage : javafx.stage.Stage : 
	 * fenêtre d'affichage de la présente vue.
	 * @param pVueAppelante : UtilisateurCerbereAccueilVue :
	 * VUE appelant la présente (utile pour les callbacks).
	 */
	public UtilisateurCerbereCreationVueKO(
			final Stage pStage
				, final UtilisateurCerbereAccueilVue pVueAppelante) {
		
		super();
		
		this.stageAffichage = pStage;
		this.vueAppelante = pVueAppelante;
		
		/* configure la présente vue (AnchorPane). */
		this.configurerVue();
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	/**
	 * configure la présente vue (AnchorPane).<br/>
	 * <ul>
	 * <li>configure tous les composants (labels, zones de texte, ...).</li>
	 * <li>positionne chaque composants dans un Constraints de GridPane.</li>
	 * <li>ajoute chaque composant dans le GridPane.</li>
	 * <li>ajoute le gridPane au présent AnchorPane.</li>
	 * </ul>
	 */
	private void configurerVue() {
		
		/* configure tous les composants (labels, zones de texte, ...). */
		this.configurerComposants();
		
		/* positionne chaque composants dans un Constraints de GridPane. */
		this.positionnerComposantsDansConstraints();
		
		/* ajoute chaque composant dans le GridPane. */
		this.gridPane.getChildren().add(this.editionVue);
		this.gridPane.getChildren().add(this.panneauGestion);
		
		/* affiche les lignes de la grille du GridPane. */
		this.gridPane.setGridLinesVisible(false);
		
		/* ajoute une classe CSS à this.gridPane 
		 * (les GridPanes n'ont pas de classe CSS par défaut). */
		this.gridPane.getStyleClass().add("gridpane");
		
		/* ajoute le gridPane au présent AnchorPane. */
		this.getChildren().add(this.gridPane);
		
		/* fixe les distances entre le contenu (this.gridPane) 
		 * et le présent conteneur AnchorPane. */
		AnchorPane.setTopAnchor(this.gridPane, 10D);
		AnchorPane.setLeftAnchor(this.gridPane, 10D);
		AnchorPane.setBottomAnchor(this.gridPane, 10D);
		AnchorPane.setRightAnchor(this.gridPane, 10D);
		
		
		/* ajoute une classe CSS au présent AnchorPane 
		 * (les AnchorPanes n'ont pas de classe CSS par défaut). */
		this.getStyleClass().add("anchorpane");
		
	} // Fin de configurerVue().___________________________________________
	
	
	
	/**
	 * configure tous les composants (labels, zones de texte, ...).<br/>
	 * <ul>
	 * <li>configure l'AnchorPane d'édition de l'OBJET METIER.</li>
	 * li>configure l'AnchorPane de gestion de la création (Buttons) de l'OBJET METIER.</li>
	 * </ul>
	 */
	private void configurerComposants() {
		
		/* >configure l'AnchorPane d'édition de l'OBJET METIER. */
		this.configurerEditionVue();
		
		/* configure l'AnchorPane de gestion de la 
		 * création (Buttons) de l'OBJET METIER. */
		this.configurerPanneauGestion();
		
	} // Fin de configurerComposants().____________________________________


	
	/**
	 * configure l'AnchorPane d'édition de l'OBJET METIER.<br/>
	 */
	private void configurerEditionVue() {
		/**/
	} // Fin de configurerEditionVue().____________________________________


	
	/**
	 * configure l'AnchorPane de gestion de la création (Buttons) 
	 * de l'OBJET METIER.<br/>
	 * <ul>
	 * <li></li>
	 * <li>ajoute un Listener au bouton Enregistrer.</li>
	 * <li>ajoute un Listener au bouton Annuler.</li>
	 * <li>ajoute un Listener au bouton Quitter.</li>
	 * </ul>
	 */
	private void configurerPanneauGestion() {
		
		/* ajoute un Listener au bouton Enregistrer. */
		this.ajouterListenerAEnregistrer();
		
		/* ajoute un Listener au bouton Annuler. */
		this.ajouterListenerAAnnuler();
		
		/* ajoute un Listener au bouton Quitter. */
		this.ajouterListenerAQuitter();
		
	} // Fin de configurerPanneauGestion().________________________________

	
	
	/**
	 * ajoute un Listener au bouton Enregistrer du panneau de gestion.<br/>
	 * <b>enregistre dans le stockage un nouvel objet</b>.<br/>
	 * <ul>
	 * <li>récupère la vue appelante (pour obtenir le CONTROLLER).</li>
	 * <li>récupère le formulaire d'édition de l'objet métier.</li>
	 * <li>récupère le CONTROLLER auprès de la vue appelante.</li>
	 * <li>récupère l'objet OBSERVABLE créé dans le formulaire d'édition.</li>
	 * <li>convertit l'OBSERVABLE en OBJET METIER.</li>
	 * <li>délègue au CONTROLLER la CREATION de l'OBJET METIER.</li>
	 * </ul>
	 */
	private void ajouterListenerAEnregistrer() {
		
		this.panneauGestion.getBoutonEnregistrer()
			.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handle(
					final ActionEvent pEvent) {
				
				/* récupère la vue appelante. */
				final UtilisateurCerbereAccueilVue vueAppelanteLocal 
					= UtilisateurCerbereCreationVueKO.this.getVueAppelante();
				
				/* récupère le formulaire d'édition de l'objet métier. */
				final UtilisateurCerbereEditionVueKO editionVueLocal 
					= UtilisateurCerbereCreationVueKO.this.getEditionVue();
				
				if (vueAppelanteLocal != null) {
					
					/* récupère le CONTROLLER auprès de la vue appelante. */
					final IUtilisateurCerbereController controller 
						= vueAppelanteLocal.getUtilisateurCerbereController();
					
					/* récupère l'objet OBSERVABLE créé dans 
					 * le formulaire d'édition. */
					final IUtilisateurCerbereModelObs objetObs 
						= editionVueLocal.lireVue();
					
					/* convertit l'OBSERVABLE en DTO. */
					final IUtilisateurCerbereDTO objetDTO 
						= UtilisateurCerbereConvertisseurObservableDTO
								.convertirObservableEnDTO(objetObs);
					
					if (controller != null) {
						try {
							
							/* délègue au CONTROLLER la CREATION 
							 * de l'OBJET METIER. */
							final UtilisateurCerbereResponse reponse 
								= controller.create(objetDTO);
							
							if (!reponse.isValide()) {
								
								final Map<String, String> errorsMap 
									= reponse.getErrorsMap();
								
								/* instanciation d'un panneau d'édition KO. */
								final UtilisateurCerbereEditionVueKO editionVueKO 
									= new UtilisateurCerbereEditionVueKO();
								
								if (!errorsMap.isEmpty()) {
									editionVueKO
										.injecterErrorsMapDansLabels(
												errorsMap);
								}
								
							} else {
								/**/
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				
			} // Fin de handle(...).____________________
			
		}); // Fin de new EventHandler(...).__________________
		
	} // Fin de ajouterListenerAEnregistrer()._____________________________

	
	
	/**
	 * ajoute un Listener au bouton Annuler du panneau de gestion.<br/>
	 * <ul>
	 * <li>nettoie le formulaire d'édition en cas d'appui 
	 * sur le bouton Annuler.</li>
	 * <li>positionne le curseur sur le 1er champ (civilité) 
	 * du formulaire d'édition.</li>
	 * </ul>
	 */
	private void ajouterListenerAAnnuler() {
		
		this.panneauGestion.getBoutonAnnuler()
			.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handle(
					final ActionEvent pEvent) {
				
				final UtilisateurCerbereEditionVueKO editionVueLocal 
					= UtilisateurCerbereCreationVueKO.this.getEditionVue();
				
				if (editionVueLocal != null) {
					
					/* nettoie le formulaire d'édition en cas d'appui 
					 * sur le bouton Annuler. */
					editionVueLocal.resetVue();
					
					/* positionne le curseur sur le 1er champ (civilité) 
					 * du formulaire d'édition. */
					editionVueLocal.positionnerCurseur();
				}
				
			} // Fin de handle(...).____________________
			
		}); // Fin de new EventHandler(...).__________________
		
	} // Fin de ajouterListenerAAnnuler()._________________________________

	
	
	/**
	 * ajoute un Listener au bouton Quitter du panneau de gestion.<br/>
	 * <ul>
	 * <li>ferme la fenêtre d'affichage de la présente VUE 
	 * <code>this.stageAffichage</code> lors d'un appui 
	 * sur le bouton Quitter.</li>
	 * </ul>
	 */
	private void ajouterListenerAQuitter() {
		
		this.panneauGestion.getBoutonQuitter()
			.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handle(
					final ActionEvent pEvent) {
				
				final Stage stageLocal 
					= UtilisateurCerbereCreationVueKO.this
							.getStageAffichage();
				
				/* ferme la fenêtre d'affichage de la présente VUE 
				 * this.stageAffichage lors d'un appui sur 
				 * le bouton Quitter.*/
				if (stageLocal != null) {
					stageLocal.hide();
				}
								
			} // Fin de handle(...).____________________
			
		}); // Fin de new EventHandler(...).__________________
		
	} // Fin de ajouterListenerAQuitter()._________________________________

	
	
	/**
	 * positionne chaque composants dans un Constraints de GridPane.<br/>
	 */
	private void positionnerComposantsDansConstraints() {
		
		GridPane.setConstraints(
				this.editionVue
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.panneauGestion
					, 0, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
	} // Fin de positionnerComposantsDansConstraints().____________________


	
	/**
	 * Getter du THEATRE (Stage = fenêtre) d'affichage de la présente VUE.
	 *
	 * @return this.stageAffichage : Stage.<br/>
	 */
	public final Stage getStageAffichage() {
		return this.stageAffichage;
	} // Fin de getStageAffichage()._______________________________________


	
	/**
	 * Getter de la VUE (AnchorPane) appelant la présente VUE.<br/>
	 * Utile pour les callbacks.<br/>
	 *
	 * @return this.vueAppelante : UtilisateurCerbereAccueilVue.<br/>
	 */
	public final UtilisateurCerbereAccueilVue getVueAppelante() {
		return this.vueAppelante;
	} // Fin de getVueAppelante()._________________________________________



	/**
	 * Getter de la VUE (AnchorPane) d'<b>édition</b> d'un OBJET METIER.<br/>
	 * Composant du haut de la présente VUE.<br/>
	 *
	 * @return this.editionVue : UtilisateurCerbereEditionVueKO.<br/>
	 */
	public final UtilisateurCerbereEditionVueKO getEditionVue() {
		return this.editionVue;
	} // Fin de getEditionVue().___________________________________________


	
	/**
	 * Getter de la VUE (AnchorPane) de 
	 * <b>gestion de la création (boutons)</b> 
	 * d'un OBJET METIER.<br/>
	 * Composant du bas de la présente VUE.<br/>
	 *
	 * @return this.panneauGestion : PanneauGestionCreation.<br/>
	 */
	public final PanneauGestionCreation getPanneauGestion() {
		return this.panneauGestion;
	} // Fin de getPanneauGestion()._______________________________________
	

	
} // FIN DE LA CLASSE UtilisateurCerbereCreationVue.-------------------------
