package org.jeecg.config;

import org.jeecg.aop.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 *spring boot 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor())
				.addPathPatterns("/simsLesson/**","/simsStudent/**","/simsTeacher/**"
						,"/simsScore/**","/simsFile/**" )
				.excludePathPatterns("/doc.html","/**/*.js","/**/*.css","/**/*.html","/**/*.svg",
						"/**/*.jpg","/**/*.png","/**/*.ico","/druid/**","/swagger-ui.html",
						"/swagger**/**","/webjars/**","/v2/**","/error/**",
						"/simsAuthStudent/**","/simsAuthTeacher/**");


	}
	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}

//	/**
//	 * 配置拦截器
//	 */
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/businessPricing");
//	}

}
