import Modelos.Persona;
import Servicios.ServicioBootstrap;
import Servicios.ServicioPersona;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args){
        try {
            //Iniciar la base de datos
            ServicioBootstrap.iniciarBaseDatos();
            final Configuration configuration = new Configuration(new Version(2, 3, 23));
            configuration.setClassForTemplateLoading(Main.class, "/");

            staticFiles.location("/publico");

            enableDebugScreen();

            //Redireccionamiento a la platilla Index
            get("/", (req, res) -> {
                StringWriter writer = new StringWriter();
                Map<String, Object> atributos = new HashMap<>();
                Template template = configuration.getTemplate("plantillas/index.ftl");

                template.process(atributos, writer);

                return writer;
            });

            //Re Plantilla Registrar
            get("/agregarRegistro", (req, res) -> {
                StringWriter writer = new StringWriter();
                Map<String, Object> atributos = new HashMap<>();
                Template template = configuration.getTemplate("plantillas/Registrar.ftl");

                template.process(atributos, writer);

                return writer;
            });

            //Re Plantilla Listar
            get("/mapa", (req, res) -> {
                StringWriter writer = new StringWriter();
                Map<String, Object> atributos = new HashMap<>();
                Template template = configuration.getTemplate("plantillas/ubicacion.ftl");
                List<Persona> personas = ServicioPersona.getInstancia().listar();

                atributos.put("personas", personas);
                template.process(atributos, writer);

                return writer;
            });

            //Sincronizacion de los datos en modo offline
            post("/sincronizar", (req, res) -> {
                String data = req.queryParams("datos");
                data = data.replace("[", "");
                data = data.replace(']', ' ');
                data = data.replace('}', ' ');
                data = data.replace("\"", "");

                String[] datosAux = data.split("\\{");
                ArrayList<String[]> datos = new ArrayList<>();
                ArrayList<String[]> arregloAux = new ArrayList<>();

                for(String dato : datosAux) {
                    datos.add(dato.split(","));
                }

                for (String[] dato : datos) {
                    for(String dat : dato) {
                        arregloAux.add(dat.split(":"));
                    }
                }

                for (int i = 0; i < arregloAux.size(); i++) {
                    if ((i + 1) % 6 == 0) {
                        Persona personaNueva = new Persona(
                                arregloAux.get(i - 4)[arregloAux.get(i - 4).length - 1],
                                arregloAux.get(i - 3)[arregloAux.get(i - 3).length - 1],
                                arregloAux.get(i - 2)[arregloAux.get(i - 2).length - 1],
                                arregloAux.get(i - 1)[arregloAux.get(i - 1).length - 1],
                                arregloAux.get(i)[arregloAux.get(i).length - 1]
                        );

                        ServicioPersona.getInstancia().crear(personaNueva);
                    }
                }

                return "";
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   /* public static void crearEstudiante(){
        Persona p = new Persona("Joshua", "", "Universitario", "19.221188", "-70.517498");
        ServicioPersona.getInstancia().crear(p);
    }*/
}
