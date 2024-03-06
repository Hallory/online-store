package org.electronicsstore.backend;

import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] profiles = env.getActiveProfiles();
		if (Arrays.asList(profiles).contains("test")) {
			categoryService.createRootCategory();
		}
	}
}
