package helper;

import java.util.UUID;

public class RandomStringGenerator {
    public String generateRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
