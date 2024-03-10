package si.kkobau.exceptions.mappers;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import si.kkobau.exceptions.ExceptionResponse;

public class ConstraintViolationExceptionMapper {
    @ServerExceptionMapper
    public RestResponse<ExceptionResponse> mapException(ConstraintViolationException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, new ExceptionResponse(x.getMessage()));
    }
}
