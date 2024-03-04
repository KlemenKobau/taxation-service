package si.kkobau;

public interface IResourceTest {
    default String getApiRootPath() {
        return "/api/v1";
    }
}
