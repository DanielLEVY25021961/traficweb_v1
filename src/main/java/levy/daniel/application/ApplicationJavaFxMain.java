package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import levy.daniel.application.model.utilitaires.spring.configurateurspring.ConfigurateurSpringFrmkAnnotationJPAH2Memory;
import levy.daniel.application.vues.desktop.metier.utilisateur.UtilisateurCerbereCreationVue;

/**
 * CLASSE ApplicationJavaFxMain :<br/>
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
 * @since 28 févr. 2019
 *
 */
public class ApplicationJavaFxMain extends Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * contexte SPRING.<br/>
	 */
	private static transient ApplicationContext context;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ApplicationJavaFxMain.class);
	

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ApplicationJavaFxMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(
			final Stage pPrimaryStage) throws Exception {
				
		/* vue à afficher. */
		final UtilisateurCerbereCreationVue vue 
			= new UtilisateurCerbereCreationVue();
		
        /* Titre du théâtre. */
        pPrimaryStage.setTitle("UtilisateurCerbereCreationVue");
        
        /* PASSE LA VUE à afficher à la SCENE 
         * et dimensionne la SCENE. */
        final Scene scene = new Scene(vue, 600d, 500d);
        
        /* ajoute la feuille de style à la Scene. */
        scene.getStylesheets().add("static/css/dan_style.css");
        
        /* passe la SCENE au THEATRE (stage). */
        pPrimaryStage.setScene(scene);
        
        /* affiche le THEATRE. */
        pPrimaryStage.show();

	} // Fin de start(...).________________________________________________
	

	
	/**
	 * instancie le contexte SPRING.<br/>
	 */
	private static void instancierContexteSpring() {
		
		context = new AnnotationConfigApplicationContext(
				ConfigurateurSpringFrmkAnnotationJPAH2Memory.class);
		
	} // Fin de instancierContexteSpring().________________________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 * <ul>
	 * <li>instancie le contexte SPRING.</li>
	 * <li>lance l'application Javafx.</li>
	 * </ul>
	 *
	 * @param pArgs : String[].<br/>
	 */
	public static void main(
			final String... pArgs) {
		
		/* instancie le contexte SPRING. */
		instancierContexteSpring();
		
		/* lance l'application Javafx. */
		 Application.launch(pArgs);
		 
    } // Fin de main(...)._________________________________________________

	

} // FIN DE LA CLASSE ApplicationJavaFxMain.---------------------------------
