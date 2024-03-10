package si.kkobau;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
public class BettingResourceIT extends BettingResourceTest {
    @Override
    public boolean useMocks() {
        return false;
    }
}
