package br.ufal.ic.iface_profile.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan("br.ufal.ic.iface_profile.controller")
public class WebConfig extends WebMvcConfigurerAdapter{

	public WebConfig(){
		super();
	}
}
