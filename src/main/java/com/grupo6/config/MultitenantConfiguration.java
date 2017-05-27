package com.grupo6.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// No poner como configuration porque no va a tomar dinamicamente 
// los datasources de los nuevos tenants
// @Configuration
@Component
public class MultitenantConfiguration {

    @Autowired
    private DataSourceProperties properties;
    
	@Value("${tenatPath}")
	private String tenatPath;


    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    
    @PostConstruct
    public DataSource dataSource() {

    	// De acá leemos los archivos de propiedades con los datos 
    	// de la base que va a usar cada tenant cuando se crea un nuevo 
    	// hay que crear un archivo de propiedades nuevo y llamar a este servicio 
        File[] files = Paths.get(tenatPath).toFile().listFiles();
        Map<Object,Object> resolvedDataSources = new HashMap<>();

        for(File propertyFile : files) {
            Properties tenantProperties = new Properties();
            DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());

            try {
                tenantProperties.load(new FileInputStream(propertyFile));

                String tenantId = tenantProperties.getProperty("name");

                dataSourceBuilder.driverClassName(properties.getDriverClassName())
                        .url(tenantProperties.getProperty("datasource.url"))
                        .username(tenantProperties.getProperty("datasource.username"))
                        .password(tenantProperties.getProperty("datasource.password"));

                if(properties.getType() != null) {
                    dataSourceBuilder.type(properties.getType());
                }

                resolvedDataSources.put(tenantId, dataSourceBuilder.build());
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        }

        // crea el MultitenantDataSource que contiene un mapa con clave nombre del tenant 
        // y value los datos necesarios para la conexión 
        // Tiene que existir un defaul, No usar el defaul no es seguro !!!
        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    /// crea el datasource por defecto para la aplicación 
    private DataSource defaultDataSource() {
        DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader())
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());

        if(properties.getType() != null) {
            dataSourceBuilder.type(properties.getType());
        }

        return dataSourceBuilder.build();
    }
//  /// crea el datasource por defecto para la aplicación 
//    private DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader())
//                .driverClassName(properties.getDriverClassName())
//                .url(properties.getUrl())
//                .username(properties.getUsername())
//                .password(properties.getPassword());
//
//        if(properties.getType() != null) {
//            dataSourceBuilder.type(properties.getType());
//        }
//
//        return dataSourceBuilder.build();
//    }
}
