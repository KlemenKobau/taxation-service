package si.kkobau.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import si.kkobau.exceptions.ExceptionResponse;
import si.kkobau.exceptions.NotFoundException;

public class NotFoundExceptionMapper {
    @ServerExceptionMapper
    public RestResponse<ExceptionResponse> mapException(NotFoundException x) {
        return RestResponse.status(Response.Status.NOT_FOUND, new ExceptionResponse(x.getNotFoundMessage()));
    }
}
