package com.example.designpatterns.proxy;

public class RealDatabaseService implements DatabaseService {

    public RealDatabaseService() {
        // Simula una inicialización pesada (abrir conexión a la BD)
        System.out.println("⚙️ [BD Real] Conectando a la base de datos H2... (Operación costosa)");
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public String executeQuery(String sql) {
        System.out.println("🔄 [BD Real] Ejecutando query en H2: " + sql);
        try {
            Thread.sleep(2000); // Simula que la consulta tarda 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Resultados de '" + sql + "' -> [Registro 1, Registro 2, Registro 3]";
    }
}