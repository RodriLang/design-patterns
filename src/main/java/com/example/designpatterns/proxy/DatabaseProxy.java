package com.example.designpatterns.proxy;

import java.util.HashMap;
import java.util.Map;

public class DatabaseProxy implements DatabaseService {
    private RealDatabaseService realService; // Referencia al objeto real
    private Map<String, String> cache;       // Caché en memoria

    public DatabaseProxy() {
        this.cache = new HashMap<>();
    }

    @Override
    public String executeQuery(String sql) {
        // 1. Si ya está en caché, lo devuelve inmediatamente sin tocar la BD
        if (cache.containsKey(sql)) {
            System.out.println("⚡ [PROXY] ¡Caché encontrada! Evitando llamada a H2.");
            return cache.get(sql);
        }

        // 2. Si el objeto real no existe, lo crea por primera vez (Lazy Initialization / Proxy Virtual)
        if (realService == null) {
            System.out.println("⚠️ [PROXY] La BD real no está inicializada. Creándola ahora...");
            realService = new RealDatabaseService();
        }

        // 3. Modifica/Controla el acceso: Va a la BD real y guarda en caché
        String result = realService.executeQuery(sql);
        cache.put(sql, result);

        return result;
    }
}