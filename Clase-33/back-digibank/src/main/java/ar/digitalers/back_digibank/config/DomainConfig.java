package ar.digitalers.back_digibank.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ar.digitalers.back_digibank.domain")
@EnableJpaRepositories("ar.digitalers.back_digibank.repos")
@EnableTransactionManagement
public class DomainConfig {
}
