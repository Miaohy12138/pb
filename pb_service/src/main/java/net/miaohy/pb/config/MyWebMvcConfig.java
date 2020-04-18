package net.miaohy.pb.config;

import net.miaohy.pb.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport  {
	@Autowired
    private AuthorizationInterceptor authorizationInterceptor;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
	   registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

/*	@Override
	  public void addCorsMappings(CorsRegistry registry) {
		 this._configCorsParams(registry.addMapping("/**"));
	  }

	 private void _configCorsParams(CorsRegistration corsRegistration) {
         corsRegistration
         .allowedOrigins("*")
         .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),HttpMethod.DELETE.name(),HttpMethod.OPTIONS.name())
         .allowedHeaders("*")
         .exposedHeaders(HttpHeaders.SET_COOKIE)
         .allowCredentials(true)
         .maxAge(3600);
 }*/
}
