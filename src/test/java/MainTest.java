import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22)
    @Disabled
    public void testMainTimeout() throws Exception {
        Main.main(null);
    }

}