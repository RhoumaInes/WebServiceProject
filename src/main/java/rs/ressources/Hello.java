package rs.ressources;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("greeting")
public class Hello {
	@Path("hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response hello() {
		return Response
				.status(200)
				.entity("Hello :)")
				.build();
	}
	@Path("hello/{fName}/{lName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloTo(@PathParam(value="fName") String name,
    		@PathParam(value="lName") String lname) {
    	return Response.status(200).entity("hello "+name+" "+lname).build();
    }
    //http://localhost:8080/test/rest/greeting/test?fName=Ahmed
    @Path("test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloTo(@QueryParam(value="fName") String name) {
    	return Response.status(200).entity("hello "+name).build();
    }

}
