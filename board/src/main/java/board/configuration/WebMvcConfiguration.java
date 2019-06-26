package board.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	/**
	 * 파일처리 Bean
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver commonMultipartResolver = new CommonsMultipartResolver();
		
		commonMultipartResolver.setDefaultEncoding("UTF-8");
		commonMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
		
		return commonMultipartResolver;
	}
}
