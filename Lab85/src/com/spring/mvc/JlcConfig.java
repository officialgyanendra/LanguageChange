package com.spring.mvc;

import java.util.Locale;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.*;

@EnableWebMvc
@Configuration
@ComponentScan({"com.spring.mvc"})
public class JlcConfig extends WebMvcConfigurerAdapter {
@Bean
public InternalResourceViewResolver viewResolver(){
	InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
	viewResolver.setViewClass(JstlView.class);
	viewResolver.setPrefix("/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
}
@Bean
public MessageSource messageSource(){
	ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
}
/*@Bean(name=DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
public SessionLocaleResolver  sessionLocaleResolver(){
	final SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
	sessionLocaleResolver.setDefaultLocale(new Locale("en"));
	return sessionLocaleResolver;
}*/
@Bean
public LocaleResolver localeResolver(){
	CookieLocaleResolver localeResolver=new CookieLocaleResolver();
	localeResolver.setDefaultLocale(new Locale("en"));
	return localeResolver;
}
@Override
public void addInterceptors(InterceptorRegistry registry){
	LocaleChangeInterceptor localChangeInterceptor=new LocaleChangeInterceptor();
	localChangeInterceptor.setParamName("language");
	registry.addInterceptor(localChangeInterceptor);
}
}
