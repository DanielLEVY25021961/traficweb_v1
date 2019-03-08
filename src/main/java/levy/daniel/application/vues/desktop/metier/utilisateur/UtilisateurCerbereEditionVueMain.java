package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CLASSE UtilisateurCerbereEditionVueMain :<br/>
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
 * @since 25 févr. 2019
 *
 */
public class UtilisateurCerbereEditionVueMain extends Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereEditionVueMain.class);

	
	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereEditionVueMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(
			final Stage pPrimaryStage) throws Exception {
		
		/* vue à afficher. */
		final UtilisateurCerbereEditionVue vue 
			= new UtilisateurCerbereEditionVue();
		
        /* Titre du théâtre. */
        pPrimaryStage.setTitle("UtilisateurCerbereEditionVue");
        
//        vue.injecterMessageDansLabelError(vue.getNomErrorLabel(), "le nom ne doit contenir que 15 caractères");
//        vue.injecterMessageDansLabelError(vue.getErreursGlobalesLabel(), "CREATION IMOSSIBLE !!!! : DOUBLON d'OBJET METIER");
       
         /* PASSE LA VUE à afficher à la SCENE 
         * et dimensionne la SCENE. */
//        final Scene scene = new Scene(vue, 800d, 400d);
        final Scene scene = new Scene(vue);
        
        /* ajoute la feuille de style à la Scene. */
        scene.getStylesheets().add("static/css/dan_style.css");
        
        /* passe la SCENE au THEATRE (stage). */
        pPrimaryStage.setScene(scene);
        
        /* affiche le THEATRE. */
        pPrimaryStage.show();

	} // Fin de start(...).________________________________________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 */
	public static void main(
			final String... pArgs) {
		 Application.launch(pArgs);
    } // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE UtilisateurCerbereAffichageVueMain.--------------------
