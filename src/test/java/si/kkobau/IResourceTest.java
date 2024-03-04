package si.kkobau;

public interface IResourceTest {
    default String getApiRootPath() {
        return "/v1/api";
    }
}
