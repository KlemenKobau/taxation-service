package si.kkobau.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import si.kkobau.exceptions.ExceptionResponse;

public class GeneralMapper {
    private static final Logger LOG = Logger.getLogger(GeneralMapper.class);

    @ServerExceptionMapper
    public RestResponse<ExceptionResponse> mapException(Exception x) {
        LOG.error(x.getMessage(), x);

        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, new ExceptionResponse("Internal server error"));
    }
}
