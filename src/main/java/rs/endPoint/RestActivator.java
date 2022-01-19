package rs.endPoint;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import rs.ressources.GestionEmploye;

@ApplicationPath("rest")
public class RestActivator extends Application {

	public RestActivator() {
		super();
		  BeanConfig beanConfig = new BeanConfig();
		  beanConfig.setVersion("1.0.2");
		  beanConfig.setSchemes(new String[]{"http"});
		  beanConfig.setHost("localhost:8080");
		  beanConfig.setBasePath("test/rest");
		  beanConfig.setResourcePackage("io.swagger.resources");
		  beanConfig.setResourcePackage("rs.ressources");
		  beanConfig.setScan(true);
	}
	@Override
	 public Set<Class<?>> getClasses() {
		 Set<Class<?>> resources = new HashSet<Class<?>>();
		 resources.add(GestionEmploye.class);
		 //resources.add(SecondResource.class);
		 resources.add(ApiListingResource.class);
		 resources.add(SwaggerSerializers.class);
		 return resources;
	 }

	
}