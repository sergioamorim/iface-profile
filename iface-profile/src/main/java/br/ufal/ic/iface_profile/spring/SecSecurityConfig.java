package br.ufal.ic.iface_profile.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml"})
//@ComponentScan("br.com.sixplus.academico.security")
public class SecSecurityConfig extends WebMvcConfigurerAdapter{
	
	public SecSecurityConfig(){
		super();
	}

}
