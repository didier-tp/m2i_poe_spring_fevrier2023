package com.m2i.tp2;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component //pour demander à Spring de prendre en charge cette classe (new ... + ...) 
public class Coordinateur {
	
	/*
	@Autowired //injection de dépendance 
	//@Qualifier("interactionImplV1")
	@Qualifier("interactionImplV2")
	//pour demander à spring de remplacer la valeur null
	//par une référence pointant vers un autre composant spring existant
	//qui est compatible avec celui indiqué : ici l'interface Interaction
	private Interaction interaction; //valeur par défaut = null;
	//interaction est une référence sur un composant implémentant
	//l'interface Interaction , classes possibles: InteractionImplV1, InteractionImplV2
	*/
	
	
	//@Resource sans (name="...") se comporte comme @Autowired
	//@Resource(name = "interactionImplV1")//injection de dépendance 
	@Resource(name = "interactionImplV2")
	//pour demander à spring de remplacer la valeur null
	//par une référence pointant vers un autre composant spring existant
	//dont le nom logique est "interactionImplV1"
	private Interaction interaction; //valeur par défaut = null;
	

	@Resource(name=Calculateur.TP_CALCULATEUR_CARRE)
	//@Resource(name=Calculateur.TP_CALCULATEUR_DOUBLE)
	private Calculateur calculateur;//=null par defaut
	
	
	
	public Coordinateur() {
		System.out.println("dans le constructeur de Coordinateur, calculateur="+calculateur);
		//calculateur.... => nullPointerException
	}
	
	@PostConstruct
	//en java, une methode préfixée par @PostConstruct est appelée automatiquement
	//après le constructeur et après toutes les injections de dépendances
	//paramétrées par @Autowired ou @Resource ou autres
	public void init() {
		System.out.println("dans init() prefixee par @PostConstruct, calculateur="+calculateur);
		//calculateur.... => pas de nullPointerException
	}

	public void enchainement() {
		/*
		//V1 : saisi + affichage
		String valeurSaisieX = interaction.saisir("x:");
		interaction.afficher("valeurSaisieX="+valeurSaisieX);
		*/
		
		//V2 : saisi + calcul + affichage
		String valeurSaisieX = interaction.saisir("x:");
		double x= Double.parseDouble(valeurSaisieX);
		double y = calculateur.calcul(x);
		interaction.afficher("y="+y);
	}
	
	public static void main(String[] args) {
		/*
		//V0 (sans spring)
		Coordinateur coordinateur = new Coordinateur();
		//coordinateur.interaction =  new InteractionImplV1();
		coordinateur.interaction = new InteractionImplV2();
		coordinateur.enchainement();
		*/
		
		//V1 avec spring-framework : Les classes Coordinateur et InteractionImplV1 seront 
		//prises en charge par Spring
		
		//contextSpring = ensemble des objets de traitement pris en charge par spring
		AnnotationConfigApplicationContext contextSpring  
		    = new AnnotationConfigApplicationContext(ConfigSpring.class);
		
		//Coordinateur coordinateur = (Coordinateur) contextSpring.getBean("coordinateur");
		Coordinateur coordinateur = contextSpring.getBean(Coordinateur.class);
		coordinateur.enchainement();
	}

}
