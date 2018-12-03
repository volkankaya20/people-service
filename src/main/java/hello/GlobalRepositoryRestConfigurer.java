/**
 * 
 */
package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author allen
 *
 */
@Configuration
public class GlobalRepositoryRestConfigurer extends RepositoryRestConfigurerAdapter{
	
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                  .addMapping("/**")
                  .allowedOrigins("*")
                  .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
     }

}
