package com.m2i.tp2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.m2i.tp2") //demander à scanner le package com.m2i.tp2
// et les sous packages pour trouver des classes préfixées par @Component ou autres @...
public class ConfigSpring {

}
