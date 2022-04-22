package vista;

import java.util.Scanner;

public class VistaAPP {

    //Constantes
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    //Atributos
    private Scanner sc = new Scanner(System.in);

    //Métodos
    //Pide datos String
    public String pideDatosString(String dato) {
        System.out.println("Introduzca " + dato + ":");
        return sc.nextLine();
    }

    //Pide datos int
    public int pideDatosEnteros(String dato) {
        System.out.println("Introduzca " + dato + ":");
        return Integer.parseInt(sc.nextLine());
    }

    //MENU INICIO
    public void muestraMenuInicio() {
        System.out.println("""
                
                ╔═════════════════════════════════════════════════════════════════════════╗
                 ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡    MENU DE INICIO    ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡
                 
                 Bienvenidx al Sistema de Gestión de Incidencias
                 
                  ◊◊◊ Recuerde que para reportar una incidencia debe estar registrado  ◊◊◊
                  
                      «-------------------------------------------------------------»
                        [1]  Ya estoy registrado
                      «-------------------------------------------------------------»
                        [2]  Registrarme
                      «-------------------------------------------------------------»
                        [3]  Cerrar el programa
                      «-------------------------------------------------------------»
                      
                ╚═════════════════════════════════════════════════════════════════════════╝
                """);
    }

    //MENU USUARIO
    public void muestraMenuUsuario(String nombre, int incidenciasSinAsignar, int incidenciasAdignadas) {
        System.out.println("""
                
                ╔═════════════════════════════════════════════════════════════════════════╗
                 ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   MENU DE USUARIO   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡ 
                 
                """);
        System.out.println(" Bienvenidx " + nombre + " tiene usted perfil de usuario normal");
        System.out.println(" Actualmente, tiene " + incidenciasSinAsignar + " incidencias sin asignar y " + incidenciasAdignadas + " incidencias ya asignadas");
        System.out.println("""
                
                      «-------------------------------------------------------------»
                        [1]  Registrar una incidendia
                      «-------------------------------------------------------------»
                        [2]  Borrar incidencia
                      «-------------------------------------------------------------»
                        [3]  Consultar mis incidencias abiertas
                      «-------------------------------------------------------------»
                        [4]  Consultar mis incidencias cerradas
                      «-------------------------------------------------------------»
                        [5]  Mostrar mi perfil
                      «-------------------------------------------------------------»
                        [6]  Cambiar mi clave de acceso
                      «-------------------------------------------------------------»
                        [7]  Cerrar sesión
                      «-------------------------------------------------------------»
                      
                ╚═════════════════════════════════════════════════════════════════════════╝
                """);
    }

    public void muestraMenuTecnico(String nombre, int incidenciasAbiertas, int incidenciasCerradas, float prioridadMedia) {
        System.out.println("""
                
                ╔═════════════════════════════════════════════════════════════════════════╗
                 ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   MENU DE TÉCNICO   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡ 
                 
                """);
        System.out.println(" Bienvenidx " + nombre + " tiene usted perfil de técnico");
        System.out.println(" Actualmente, tiene " + incidenciasAbiertas + " incidencias abiertas y " + incidenciasCerradas + " incidencias cerradas");
        System.out.println(" La prioridad media de sus incidencias es: " + prioridadMedia);
        System.out.println("""
                
                      «-------------------------------------------------------------»
                        [1]  Consultar las incidencias asignadas no resueltas
                      «-------------------------------------------------------------»  
                        [2]  Marcar una incidencia como resuelta
                      «-------------------------------------------------------------»         
                        [3]  Consultar mis incidencias cerradas
                      «-------------------------------------------------------------»
                        [4]  Mostrar mi perfil
                      «-------------------------------------------------------------»
                        [5]  Cambiar clave de acceso
                      «-------------------------------------------------------------»
                        [6]  Cerrar sesión
                      «-------------------------------------------------------------»
                      
                ╚═════════════════════════════════════════════════════════════════════════╝      
                """);
    }

    public void muestraMenuAdmin(String nombre, int incidenciasAbiertas, int incidenciasSinAsignar) {
        System.out.println("""
                
                ╔═════════════════════════════════════════════════════════════════════════╗
                 ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡   MENU DE ADMIN   ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡ 
                
                """);
        System.out.println(" Bienvenidx " + nombre + " tiene usted perfil de administrador");
        System.out.println(" Actualmente, hay " + incidenciasAbiertas + " incidencias abiertas, de las cuales \n " +
                " " + incidenciasSinAsignar + " no están asignadas a ningún técnico");
        //TODO INSERTAR BARRA DE ESTADO ADMINISTRADORES
        System.out.println("""

                      «-------------------------------------------------------------»
                        [1]  Consultar todas las incidencias abiertas
                      «-------------------------------------------------------------»
                        [2]  Consultar incidencias cerradas
                      «-------------------------------------------------------------»
                        [3]  Consultar incidencias por término
                      «-------------------------------------------------------------»
                        [4]  Consultar los técnicos
                      «-------------------------------------------------------------»
                        [5]  Asignar una incidencia a un técnico
                      «-------------------------------------------------------------»
                        [6]  Dar de alta un técnico
                      «-------------------------------------------------------------»
                        [7]  Borrar técnico 
                      «-------------------------------------------------------------»
                        [8]  Consultar los usuarios
                      «-------------------------------------------------------------»
                        [9]  Estadísticas de la aplicación  
                      «-------------------------------------------------------------»
                        [10]  Cerrar sesión
                      «-------------------------------------------------------------»
                """);
    }

    public void muestraMenuPrioridad() {
        System.out.println("""
                 
                ╔══════════════════════════════════════════════════════╗
                                    ☆ ☆ ☆ ☆ ☆
                  Seleccione la prioridad de su incidencia, del 1 al 5:
                  
                ╚══════════════════════════════════════════════════════╝
                """);
    }

    public void muestraMenuRol() {
        System.out.println("""
                
                ╔══════════════════════════════════════════════════════╗
                    Seleccione su rol:
                    
                    «-----------------------------------------------»
                        [1]  Usuario normal
                    «-----------------------------------------------»
                        [2]  Administrador
                    «-----------------------------------------------»
                    
                ╚══════════════════════════════════════════════════════╝    
                """);
    }

    //Barra de estado usuario
    public void muestraBarraEstadoUsuario(int incidenciasAbiertas, int incidenciasAsignadas) {
        System.out.println("Actualmente tiene " + incidenciasAbiertas + " sin asignar y " + incidenciasAsignadas + "incidencias asignadas");
    }

    //Mensajes
    public void mensajeUsuarioRegistrado() {
        System.out.println("\n------ Usuario registrado con éxito ------" + ANSI_RESET);
    }

    public void mensajeIncidenciaRegistrada() {
        System.out.println(ANSI_GREEN + "\n------ Incidencia registrada con éxito ------" + ANSI_RESET);
    }

    public void mensajeIncidenciaCerrada() {
        System.out.println(ANSI_GREEN + "\n------ Incidencia cerrada correctamente ------" + ANSI_RESET);
    }

    public void mensajeTecnicoBorrado() {
        System.out.println(ANSI_GREEN + "\n------ Técnico borrado con éxito ------" + ANSI_RESET);
    }

    public void mensajeIncidenciaAsignada() {
        System.out.println(ANSI_GREEN + "\n------ Incidencia asignada con éxito ------" + ANSI_RESET);
    }


    //Errores
    public void errorUsuarioRegistrado() {
        System.out.println(ANSI_RED + "\n------ERROR: No se ha podido registrar al usuario------" + ANSI_RESET);
    }

    public void errorDatoOpcionMenu() {
        System.out.println(ANSI_RED + "\n------ERROR: La opción seleccionada debe ser un número------" + ANSI_RESET);
    }

    public void errorInicioSesion(){
        System.out.println(ANSI_RED + "\n------ERROR: Usuario o contraseña incorrectas------" + ANSI_RESET);
    }

    public void errorOpcionMenu() {
        System.out.println(ANSI_RED + "\n------ERROR: Introduzca una opción válida------" + ANSI_RESET);
    }

    public void errorIncidenciaRegistrada() {
        System.out.println(ANSI_RED + "\n------ERROR: No existen incidencias registradas------" + ANSI_RESET);
    }

    public void errorIncidenciaAsignada() {
        System.out.println(ANSI_RED + "\n------ERROR: No existen incidencias asignadas------" + ANSI_RESET);
    }

    public void errorIncidenciaCerrada() {
        System.out.println(ANSI_RED + "\n------ERROR: No se ha podido cerrar la incidencia------" + ANSI_RESET);
    }

    public void errorExistenciaIncidenciasCerradas() {
        System.out.println(ANSI_RED + "\n------ERROR: No existen incidencias cerradas registradas------" + ANSI_RESET);
    }

    public void errorCrearTecnico() {
        System.out.println(ANSI_RED + "\n------ERROR: Ya existe un técnico con este email en el sistema------" + ANSI_RESET);
    }

    public void errorBorrarTecnico() {
        System.out.println(ANSI_RED + "\n------ERROR: No se ha podido borrar al técnico------" + ANSI_RESET);
    }

    public void errorNuevaIncidenciaAsignada() {
        System.out.println(ANSI_RED + "\n------ERROR: No se ha podido asignar la incidencia------" + ANSI_RESET);
    }

    public  void errorTecnicoRegistrado() {
        System.out.println(ANSI_RED + "\n------ERROR: No existen técnicos registrados------" + ANSI_RESET);
    }

    public void errorIncidenciaPorTermino() {
        System.out.println(ANSI_RED + "\n------ERROR: No se ha encontrado ninguna incidencia------" + ANSI_RESET);
    }
}
