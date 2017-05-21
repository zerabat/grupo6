package com.grupo6.service.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import com.grupo6.config.MultitenantConfiguration;
import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.service.TenantService;

@Service
public class TenantServiceBean implements TenantService {

	@Value("${tenatPath}")
	private String tenatPath;

	
	@Autowired
	private MultitenantConfiguration multitenantConfiguration;

	@Override
	public void createTenat(String tenantName) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			// crear el archivo de propiedades correspondiente al nuevo tenant
			// no guardar archivo de propiedades porque no se puede escapear el
			// ":"

			String path = tenatPath + "/" + tenantName + ".properties";
			String content = new String();
			content += "name=" + tenantName + System.lineSeparator();
			content += "datasource.username=root" + System.lineSeparator();
			content += "datasource.password=root" + System.lineSeparator();
			content += "datasource.url=jdbc:mysql://localhost:3306/" + tenantName;
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			// ejecuto el script sql "database.sql" para crear la base
			// correspondiente
			TenantContext.setCurrentTenant("default");
			String query = "CREATE DATABASE " + tenantName;

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
				Statement stmt = null;
				stmt = con.createStatement();
				stmt.executeUpdate(query);

				// vuelvo a settear los datasources para que toma la conección
				// del tenat que se está creando
				multitenantConfiguration.dataSource();
				// seteo el tenant actual
				TenantContext.setCurrentTenant(tenantName);
				// se popula la nueva base
				ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
				con = multitenantConfiguration.dataSource().getConnection();
				rdp.addScript(new ClassPathResource("sql/database.sql"));
				rdp.populate(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
