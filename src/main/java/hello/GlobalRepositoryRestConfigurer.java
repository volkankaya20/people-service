/**
 * 
 */
package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author allen
 *
 */
@Component
public class GlobalRepositoryRestConfigurer extends RepositoryRestConfigurerAdapter{
	
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Person.class)
				  .getCorsRegistry()
                  .addMapping("/**")
                  .allowedOrigins("*")
                  .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");             
        
     }

}
