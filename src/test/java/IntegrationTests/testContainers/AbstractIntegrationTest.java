package IntegrationTests.testContainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

import static IntegrationTests.testContainers.AbstractIntegrationTest.Initializer.mysql;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
         static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.29");

         private void startContainers(){
             Startables.deepStart(Stream.of(mysql)).join();
         }

        @SuppressWarnings({"unchecked", "rawTypes"})
        @Override
        public void initialize (ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("TestContainers", (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testContainers);
        }
    }

    private static Map<String, String> createConnectionConfiguration() {
        return Map.of("spring.datasource.url", mysql.getJdbcUrl(),
                "spring.datasource.user.name", mysql.getUsername(),
                "spring.datasource.password", mysql.getPassword());
    }
}
