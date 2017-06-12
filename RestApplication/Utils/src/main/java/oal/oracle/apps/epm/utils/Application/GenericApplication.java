package oal.oracle.apps.epm.utils.Application;



import javax.ws.rs.ApplicationPath;

import oracle.wsm.metadata.annotation.PolicyReference;
import oracle.wsm.metadata.annotation.PolicySet;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;




@ApplicationPath("/rest")
@PolicySet(references = { @PolicyReference(value = "oracle/http_basic_auth_over_ssl_service_policy") })
public class GenericApplication extends ResourceConfig{
    public GenericApplication() {
        packages("oal.oracle.apps.epm.utils.service").register(JacksonFeature.class);
    }
}