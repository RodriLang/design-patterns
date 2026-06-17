package com.example.designpatterns;

import com.example.designpatterns.proxy.DatabaseProxy;
import com.example.designpatterns.proxy.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignPatternsApplication {

	static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
		System.out.println("Design Patterns Application is running...");

		// El cliente usa el Proxy pensando que es el servicio directo
		DatabaseService db = new DatabaseProxy();

		System.out.println("=== 1° Consulta (La BD se inicializa y tarda) ===");
		long tiempo = System.currentTimeMillis();
		System.out.println(db.executeQuery("SELECT * FROM usuarios"));
		System.out.println("Tiempo total: " + (System.currentTimeMillis() - tiempo) + " ms\n");

		System.out.println("=== 2° Consulta (Misma query: Debería ser instantánea gracias al Proxy) ===");
		tiempo = System.currentTimeMillis();
		System.out.println(db.executeQuery("SELECT * FROM usuarios"));
		System.out.println("Tiempo total: " + (System.currentTimeMillis() - tiempo) + " ms\n");

		System.out.println("=== 3° Consulta (Nueva query: Va a la BD pero ya está inicializada) ===");
		tiempo = System.currentTimeMillis();
		System.out.println(db.executeQuery("SELECT * FROM productos"));
		System.out.println("Tiempo total: " + (System.currentTimeMillis() - tiempo) + " ms\n");
	}

}