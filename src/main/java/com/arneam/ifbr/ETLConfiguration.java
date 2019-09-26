package com.arneam.ifbr;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@EnableBatchProcessing
@Configuration
@Slf4j
public class ETLConfiguration {

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Value("${spring.datasource.url")
  private String databaseUrl;

  @Value("${spring.datasource.username")
  private String databaseUserName;

  @Value("${spring.datasource.password")
  private String databasePassword;

  @Bean
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(databaseUrl);
    config.setUsername(databaseUserName);
    config.setPassword(databasePassword);
    return new HikariDataSource(config);
  }

  @Bean
  public JobRepository jobRepository(DataSource dataSource) {
    JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTransactionManager(new DataSourceTransactionManager(dataSource));
    factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITED");
    factoryBean.setTablePrefix("BATCH_");
    factoryBean.setMaxVarCharLength(2000);
    try {
      factoryBean.afterPropertiesSet();
      return factoryBean.getObject();
    } catch (Exception e) {
      log.error("JobRepository could not be initiated");
      throw new BatchConfigurationException(e);
    }
  }

  @Bean
  public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }
}


