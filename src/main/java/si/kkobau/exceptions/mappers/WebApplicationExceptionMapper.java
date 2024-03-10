package si.kkobau.exceptions.mappers;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import si.kkobau.exceptions.ExceptionResponse;

public class WebApplicationExceptionMapper {
    private static final Logger LOG = Logger.getLogger(WebApplicationExceptionMapper.class);

    @ServerExceptionMapper
    public RestResponse<ExceptionResponse> mapException(WebApplicationException x) {
        Response response = x.getResponse();

        if (response.getStatus() > 500) {
            LOG.error(x.getMessage(), x);
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, new ExceptionResponse("Internal server error"));
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(x.getMessage());

        return RestResponse.status(Response.Status.fromStatusCode(response.getStatus()), exceptionResponse);
    }
}
