package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDataBase {
    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/etab_db";
    // Nom d'utilisateur
    private static final String USER = "Domi";
    private static final String PASSWORD = "1234";

    // Instance unique de la connexion
    private static volatile Connection connection = null;

    // Constructeur  pour empêcher l'instanciation directe
    private SingletonDataBase() {
        // Initialisation de la connexion dans le constructeur
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Gestion de l'exception avec un message d'erreur plus descriptif
            System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
        }
    }

    // Méthode pour obtenir l'instance unique de la connexion
    public static Connection getInstance() {
        if (connection == null) {
            synchronized (SingletonDataBase.class) {
                // Double vérification pour éviter des problèmes en environnement multithread
                if (connection == null) {
                    new SingletonDataBase();
                }
            }
        }
        return connection;
    }
}