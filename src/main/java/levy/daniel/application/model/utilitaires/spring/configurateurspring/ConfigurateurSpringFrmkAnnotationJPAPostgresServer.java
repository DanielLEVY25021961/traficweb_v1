package levy.daniel.application.model.utilitaires.spring.configurateurspring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.controllers.desktop.metier.utilisateur.impl.UtilisateurCerbereController;
import levy.daniel.application.model.persistence.metier.anneegestion.IAnneeGestionDAO;
import levy.daniel.application.model.persistence.metier.anneegestion.dao.jpaspring.impl.AnneeGestionDAOJPASpring;
import levy.daniel.application.model.persistence.metier.sections.ISectionHitDAO;
import levy.daniel.application.model.persistence.metier.sections.dao.jpaspring.impl.SectionHitDAOJPASpring;
import levy.daniel.application.model.persistence.metier.sections.localisations.ILocalisationHitDAO;
import levy.daniel.application.model.persistence.metier.sections.localisations.dao.jpaspring.impl.LocalisationHitDAOJPASpring;
import levy.daniel.application.model.persistence.metier.televersement.ITeleversementDAO;
import levy.daniel.application.model.persistence.metier.televersement.dao.jpaspring.impl.TeleversementDAOJPASpring;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl.UtilisateurCerbereDAOJPASpring;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.services.metier.utilisateurs.impl.UtilisateurCerbereService;

/**
 * CLASSE ConfigurateurSpringFrmkAnnotationJPAPostgresServer :<br/>
 *  Classe <b>annotée Configuration</b> chargée de configurer 
 * le Contexte SPRING FRAMEWORK.<br/>
 * <ul>
 * <li><b>instancie les Beans SPRING</b> (Controllers, Services, DAOs, ...).</li>
 * <li>délègue la configuration de la persistence 
 * à une classe de configuration dédiée via le mécanisme d'import.</li>
 * <li><b>PERSISTENCE : Base POSTGRESQL en MODE SERVER</b>.</li>
 * </ul>
 * <ul>
 * <li>appelée par <code>ApplicationContext contexteSpring 
 * = new AnnotationConfigApplicationContext(
 * ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class);</code></li>
 * <li>l'annotation <b>Configuration</b>
 * (org.springframework.context.annotation.Configuration) précise qu'il s'agit
 * d'une classe de CONFIGURATION SPRING.</li>
 * <li>l'annotation <b>Import</b>
 * (org.springframework.context.annotation.Import) permet de séparer
 * la configuration entre plusieurs classes de Config.</li>
 * <li>l'annotation <b>EnableAspectJAutoProxy</b>
 * (org.springframework.context.annotation.EnableAspectJAutoProxy) 
 * permet .</li>
 * <li>l'annotation <b>EnableTransactionManagement</b>
 * (org.springframework.transaction.annotation.EnableTransactionManagement) 
 * permet d'imposer à SPRING de gérer les transactions.</li>
 * <li>l'annotation <b>ComponentScan</b>
 * (org.springframework.context.annotation.ComponentScan) permet de préciser un
 * tableau de PACKAGES (sous forme de String) à scanner dans lequel SPRING 
 * doit découvrir les classes annotées.</li>
 * </ul>
 * 
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
 * @since 13 janv. 2019
 *
 */
@Profile("PROFIL_PROD_POSTGRES_SERVER")
@Configuration(value="ConfigurateurSpringFrmkAnnotationJPAPostgresServer")
@Import(value={levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.ConfigurateurSpringJPAPostgresServer.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScans({@ComponentScan("levy.daniel.application")})
public class ConfigurateurSpringFrmkAnnotationJPAPostgresServer {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConfigurateurSpringFrmkAnnotationJPAPostgresServer.class);

	// *************************METHODES************************************/
	

	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConfigurateurSpringFrmkAnnotationJPAPostgresServer() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * <b>configure un bean pour le DAO utilisateurCerbereDAOJPASpring 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "UtilisateurCerbereDAOJPASpring" 
	 * (au lieu de "utilisateurCerbereDAOJPASpring" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au DAO 
	 * (via l'annotation Repository).</li>
	 *  <li>Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du DAO</li>
	 * </ul>
	 *
	 * @return : UtilisateurCerbereDAOJPASpring : 
	 * IUtilisateurCerbereDAO.<br/>
	 */
	@Bean(value = "UtilisateurCerbereDAOJPASpring")
	public IUtilisateurCerbereDAO utilisateurCerbereDAOJPASpring() {
		return new UtilisateurCerbereDAOJPASpring();
	} // Fin de utilisateurCerbereDAOJPASpring().__________________________


	
	/**
	 * <b>configure un bean pour le SERVICE utilisateurCerbereService 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "UtilisateurCerbereService" 
	 * (au lieu de "utilisateurCerbereService" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au SERVICE 
	 * (via l'annotation Service).</li>
	 *  <li>Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du SERVICE</li>
	 * </ul>
	 *
	 * @return : UtilisateurCerbereService : 
	 * IUtilisateurCerbereService.<br/>
	 */
	@Bean(value = "UtilisateurCerbereService")
	public IUtilisateurCerbereService utilisateurCerbereService() {
		return new UtilisateurCerbereService();
	} // Fin de utilisateurCerbereService()._______________________________


	
	/**
	 * <b>configure un bean pour le CONTROLLER utilisateurCerbereController 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "UtilisateurCerbereController" 
	 * (au lieu de "utilisateurCerbereController" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au CONTROLLER 
	 * (via l'annotation Controller).</li>
	 *  <li>Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du CONTROLLER</li>
	 * </ul>
	 *
	 * @return : UtilisateurCerbereController : 
	 * IUtilisateurCerbereController.<br/>
	 */
	@Bean(value = "UtilisateurCerbereController")
	public IUtilisateurCerbereController utilisateurCerbereController() {
		return new UtilisateurCerbereController();
	} // Fin de utilisateurCerbereController().____________________________

	
	
	/**
	 * <b>configure un bean pour le DAO AnneeGestionDAOJPASpring 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "AnneeGestionDAOJPASpring" 
	 * (au lieu de "anneeGestionDAOJPASpring" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au DAO 
	 * (via l'annotation Repository).
	 *  Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du DAO</li>
	 * </ul>
	 *
	 * @return : AnneeGestionDAOJPASpring : 
	 * IAnneeGestionDAO.<br/>
	 */
	@Bean(value = "AnneeGestionDAOJPASpring")
	public IAnneeGestionDAO anneeGestionDAOJPASpring() {
		return new AnneeGestionDAOJPASpring();
	} // Fin de anneeGestionDAOJPASpring().________________________________

	
	
	/**
	 * <b>configure un bean pour le DAO TeleversementDAOJPASpring 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "TeleversementDAOJPASpring" 
	 * (au lieu de "televersementDAOJPASpring" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au DAO 
	 * (via l'annotation Repository).
	 *  Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du DAO</li>
	 * </ul>
	 *
	 * @return : TeleversementDAOJPASpring : 
	 * ITeleversementDAO.<br/>
	 */
	@Bean(value = "TeleversementDAOJPASpring")
	public ITeleversementDAO televersementDAOJPASpring() {
		return new TeleversementDAOJPASpring();
	} // Fin de televersementDAOJPASpring()._______________________________

	
	
	/**
	 * <b>configure un bean pour le DAO LocalisationHitDAOJPASpring 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "LocalisationHitDAOJPASpring" 
	 * (au lieu de "localisationHitDAOJPASpring" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au DAO 
	 * (via l'annotation Repository).
	 *  Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du DAO</li>
	 * </ul>
	 *
	 * @return : LocalisationHitDAOJPASpring : 
	 * ILocalisationHitDAO.<br/>
	 */
	@Bean(value = "LocalisationHitDAOJPASpring")
	public ILocalisationHitDAO localisationHitDAOJPASpring() {
		return new LocalisationHitDAOJPASpring();
	} // Fin de localisationHitDAOJPASpring()._____________________________

	
	
	/**
	 * <b>configure un bean pour le DAO SectionHitDAOJPASpring 
	 * et l'injecte dans le contexte SPRING</b>.<br/>
	 * <ul>
	 * <li>nomme le bean "SectionHitDAOJPASpring" 
	 * (au lieu de "sectionHitDAOJPASpring" provenant 
	 * du nom de la méthode) dans le contexte SPRING
	 * grâce au paramètre value.</li>
	 * <li>le nom choisi correspond au nom donné au DAO 
	 * (via l'annotation Repository).
	 *  Ce nom pourra être utilisé comme Qualifier 
	 *  lors de l'injection du DAO</li>
	 * </ul>
	 *
	 * @return : SectionHitDAOJPASpring : 
	 * ILocalisationHitDAO.<br/>
	 */
	@Bean(value = "SectionHitDAOJPASpring")
	public ISectionHitDAO sectionHitDAOJPASpring() {
		return new SectionHitDAOJPASpring();
	} // Fin de sectionHitDAOJPASpring().__________________________________

	
	
} // FIN DE LA CLASSE ConfigurateurSpringFrmkAnnotationJPAPostgresServer.----
