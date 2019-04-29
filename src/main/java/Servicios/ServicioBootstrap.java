package Servicios;

import org.h2.tools.Server;

import java.sql.SQLException;

public class ServicioBootstrap {
    private static ServicioBootstrap instancia;

    public static ServicioBootstrap getInstancia() {
        if (instancia == null) {
            instancia = new ServicioBootstrap();
        }
        return instancia;
    }

    /**
     * Se encarga de iniciar la base de datos para poder hacer transacciones y demás acciones.
     *
     * @throws SQLException
     */
    public static void iniciarBaseDatos() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     * Se encarga de detener la base de datos, en el caso de que sea necesario.
     *
     * @throws SQLException
     */
    public static void detenerBaseDatos() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }
}