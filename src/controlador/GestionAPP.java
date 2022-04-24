package controlador;

import modelos.*;
import vista.VistaAPP;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

public class GestionAPP {

    //Atributos
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tecnico> tecnicos;
    private ArrayList<Admin> admins;
    VistaAPP vista;


    //Getters y setters
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    //Constructor
    public GestionAPP() {
        this.usuarios = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.vista = new VistaAPP();
    }

    //MÉTODOS
    //Carga datos de prueba
    public void cargaDatos(){
        usuarios.add(new Usuario("Angeles", "Civantos", "1234", "ang"));
        tecnicos.add(new Tecnico("Daniel", "Cabezas", "1234", "dani"));
        admins.add(new Admin("Laura", "valki", "1234", "valki"));
    }
//TODO PROGRAMA
    //Iniciar programa
    public void  inicia() {
        int respuestaMenu = 0;
        boolean salida = false;
        cargaUsuarios();
        cargaTecnicos();
        cargaAdmins();
        do {
            //MENÚ INICIO
            do {
                vista.muestraMenuInicio();
                try {
                    respuestaMenu = vista.pideDatosEnteros("opción");
                } catch (NumberFormatException e) {
                    vista.errorDatoOpcionMenu();
                }
            } while (respuestaMenu <= 0 || respuestaMenu > 3);

            switch (respuestaMenu) {
                //INICIAR SESIÓN
                case 1:
                    String correo = vista.pideDatosString("correo");
                    String password = vista.pideDatosString("contraseña");

                    //Aquí se comprueba que el correo y la contraseña pertenezcan a un usuario, un técnico o un administrador
                    if (logIn(correo, password)) {
                        //Aquí se comprueba si el correo registrado pertenece a un usuario
                        if (buscaUsuarioInicioSesion(correo) != null) {

                            Usuario usuarioInicioSesion = buscaUsuarioInicioSesion(correo);
                            boolean cerrarSesionUsuario = false;

                            int respuestaMenuUsuario = 0;
                            almacenaActividad("Inicio de sesión", usuarioInicioSesion.getNombre());
                            do {
                                //MENÚ USUARIO
                                do {
                                    vista.muestraMenuUsuario(usuarioInicioSesion.getNombre(), usuarioInicioSesion.buscaIncidenciasSinAsignar(),usuarioInicioSesion.buscaIncidenciasAsignadas(), cargaInicioSesion(usuarioInicioSesion.getNombre()));
                                    try {
                                        respuestaMenuUsuario = vista.pideDatosEnteros("opción");
                                    } catch (NumberFormatException e) {
                                        vista.errorDatoOpcionMenu();
                                    }
                                } while (respuestaMenuUsuario <= 0 || respuestaMenuUsuario > 7);

                                switch (respuestaMenuUsuario) {
                                    //REGISTRAR INCIDENCIA
                                    case 1:
                                        int prioridad = 0;

                                        //Pedimos la descripción de la incidencia
                                        String descripcion = vista.pideDatosString("una descripción");

                                        //Pedimos la prioridad de la incidencia
                                        do {
                                            vista.muestraMenuPrioridad();
                                            try {
                                                prioridad = vista.pideDatosEnteros("la prioridad");
                                            } catch (NumberFormatException e) {
                                                vista.errorDatoOpcionMenu();
                                            }
                                        } while (prioridad <= 0 || prioridad > 5);

                                        //Aquí se crea una nueva incidencia con los datos pedidos y se almacena en el Arraylist
                                        if (insertaIncidencia(descripcion, prioridad, usuarioInicioSesion.getId())) {
                                            vista.mensajeIncidenciaRegistrada();
                                            almacenaActividad("Nueva incidencia", usuarioInicioSesion.getNombre());
                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;

                                    //BORRAR INCIDENCIA
                                    case 2:
                                        if (!usuarioInicioSesion.getIncidencias().isEmpty()) {
                                            for (int i = 0; i < usuarioInicioSesion.getIncidencias().size(); i++) {
                                                System.out.println("\n <- Incidencia nº: " + (i + 1) + " ->");
                                                System.out.println(usuarioInicioSesion.getIncidencias().get(i));
                                            }

                                            int numeroIncidencia = vista.pideDatosEnteros("el número de la incidencia que quiere borrar");
                                            if (usuarioInicioSesion.borraIncidencia(numeroIncidencia)) {
                                                vista.mensajeIncidenciaRegistrada();
                                            } else {
                                                vista.errorIncidenciaRegistrada();
                                            }
                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;

                                    //CONSULTAR INCIDENCIAS ABIERTAS
                                    case 3:
                                        if (!usuarioInicioSesion.getIncidencias().isEmpty()) {
                                            for (int i = 0; i < usuarioInicioSesion.getIncidencias().size(); i++) {
                                                if (!usuarioInicioSesion.getIncidencias().get(i).isEstaResuelta()){
                                                    System.out.println("\n <- Incidencia nº: " + (i + 1) + " ->");
                                                    System.out.println(usuarioInicioSesion.getIncidencias().get(i));
                                                }
                                            }
                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;
                                    //CONSULTAR INCIDENCIAS CERRADAS
                                    case 4:
                                        if (!usuarioInicioSesion.getIncidencias().isEmpty()) {
                                            for (Incidencia incidencia: usuarioInicioSesion.getIncidencias()) {
                                                if (incidencia.isEstaResuelta()) {
                                                    System.out.println(incidencia);
                                                }
                                            }
                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;
                                    //MOSTRAR PERFIL
                                    case 5:
                                        System.out.println(usuarioInicioSesion);
                                        break;
                                    //CAMBIAR CONTRASEÑA
                                    case 6:
                                        String passwordUsuario = vista.pideDatosString("su contraseña actual");
                                        if (usuarioInicioSesion.getClave().equals(passwordUsuario)) {
                                            String nuevaPassword = vista.pideDatosString("su nueva contraseña");
                                            usuarioInicioSesion.setClave(nuevaPassword);
                                        }
                                        break;
                                    //CERRAR SESIÓN
                                    case 7:
                                        almacenaActividad("Cierre de sesión", usuarioInicioSesion.getNombre());
                                        almacenaInicioSesion(usuarioInicioSesion.getNombre());
                                        usuarioInicioSesion = null;
                                        cerrarSesionUsuario = true;
                                        almacenaUsuarios();
                                        break;
                                    default:
                                        vista.errorOpcionMenu();
                                }
                            } while (!cerrarSesionUsuario);
                        } else if (buscaTecnicoInicioSesion(correo) != null) {
                            Tecnico tecnicoInicioSesion = buscaTecnicoInicioSesion(correo);
                            boolean cerrarSesionTecnico = false;

                            int respuestaMenuTecnico = 0;
                            almacenaActividad("Inicio de sesión", tecnicoInicioSesion.getNombre());
                            do {
                                //MENU TECNICO
                                do {
                                    vista.muestraMenuTecnico(tecnicoInicioSesion.getNombre(), tecnicoInicioSesion.buscaIncidenciasAbiertas(), tecnicoInicioSesion.buscaIncidenciasCerradas(), tecnicoInicioSesion.prioridadMediaTecnico());
                                    try {
                                        respuestaMenuTecnico = vista.pideDatosEnteros("opción");
                                    } catch (NumberFormatException e) {
                                        vista.errorDatoOpcionMenu();
                                    }
                                } while (respuestaMenuTecnico <= 0 || respuestaMenuTecnico > 6);

                                switch (respuestaMenuTecnico) {
                                    //CONSULTAR INCIDENCIAS ASIGNADAS
                                    case 1:
                                        if (!buscaIncidenciasAsignadas(tecnicoInicioSesion.getId()).isEmpty()) {
                                            ArrayList<IncidenciaDataClass> incidenciasAsignadas = buscaIncidenciasAsignadas(tecnicoInicioSesion.getId());
                                            for (IncidenciaDataClass incidencia: incidenciasAsignadas) {
                                                System.out.println(incidencia);
                                            }
                                        } else {
                                            vista.errorIncidenciaAsignada();
                                        }
                                        break;
                                    //MARCAR INCIDENCIA COMO RESUELTA
                                    case 2:
                                        if (!buscaIncidenciasAsignadas(tecnicoInicioSesion.getId()).isEmpty()) {
                                            ArrayList<IncidenciaDataClass> incidenciasAsignadas = buscaIncidenciasAsignadas(tecnicoInicioSesion.getId());
                                            for (int i = 0; i < incidenciasAsignadas.size(); i++) {
                                                System.out.println("\n <- Incidencia nº: " + (i + 1) + " ->");
                                                System.out.println(incidenciasAsignadas.get(i));
                                            }
                                            int numeroIncidencia = vista.pideDatosEnteros("número de incidencia");
                                            String solucion = vista.pideDatosString("una solución");

                                            if (cierraIncidencia(tecnicoInicioSesion.getId(), numeroIncidencia, solucion)) {
                                                vista.mensajeIncidenciaCerrada();
                                                almacenaActividad("Incidencia cerrada", tecnicoInicioSesion.getNombre());
                                            } else {
                                                vista.errorIncidenciaCerrada();
                                            }
                                        } else {
                                            vista.errorIncidenciaAsignada();
                                        }
                                        break;
                                    //CONSULTAR INCIDENCIAS CERRADAS
                                    case 3:
                                        if (!buscaIncidenciasCerradas(tecnicoInicioSesion.getId()).isEmpty()) {
                                            ArrayList<IncidenciaDataClass> incidenciasCerradas = buscaIncidenciasCerradas(tecnicoInicioSesion.getId());
                                            for (IncidenciaDataClass incidencia: incidenciasCerradas) {
                                                System.out.println(incidencia);
                                            }
                                        } else {
                                            vista.errorExistenciaIncidenciasCerradas();
                                        }
                                        break;
                                    //MOSTRAR PERFIL
                                    case 4:
                                        System.out.println(tecnicoInicioSesion);
                                        break;
                                    //CAMBIAR CONTRASEÑA
                                    case 5:
                                        String passwordTecnico = vista.pideDatosString("su contraseña actual");
                                        if (tecnicoInicioSesion.getClave().equals(passwordTecnico)) {
                                            String nuevaPassword = vista.pideDatosString("su nueva contraseña");
                                            tecnicoInicioSesion.setClave(nuevaPassword);
                                        }
                                        break;
                                    //CERRAR SESIÓN
                                    case 6:
                                        almacenaActividad("Cierre de sesión", tecnicoInicioSesion.getNombre());
                                        almacenaInicioSesion(tecnicoInicioSesion.getNombre());
                                        cerrarSesionTecnico = true;
                                        tecnicoInicioSesion = null;
                                        almacenaTecnicos();
                                        break;
                                    default:
                                        vista.errorOpcionMenu();
                                        break;
                                }

                            } while (!cerrarSesionTecnico);
                        } else {
                            Admin adminInicioSesion = buscaAdminInicioSesion(correo);
                            boolean cerrarSesionAdmin = false;

                            int respuestaMenuAdmin = 0;
                            almacenaActividad("Inicio de sesión", adminInicioSesion.getNombre());
                            do {
                                do {
                                    vista.muestraMenuAdmin(adminInicioSesion.getNombre(), buscaTodasIncidenciasAbiertas().size(), buscaTodasIncidenciasSinAsignar().size());
                                    try {
                                        respuestaMenuAdmin = vista.pideDatosEnteros("opción");
                                    } catch (NumberFormatException e) {
                                        vista.errorDatoOpcionMenu();
                                    }
                                } while (respuestaMenuAdmin <= 0 || respuestaMenuAdmin > 11);

                                switch (respuestaMenuAdmin) {
                                    //CONSULTAR INCIDENCIAS ABIERTAS (SISTEMA COMPLETO)
                                    case 1:
                                        if (!buscaTodasIncidenciasAbiertas().isEmpty()) {
                                            for (int i = 0; i < buscaTodasIncidenciasAbiertas().size(); i++) {
                                                System.out.println("\n <- Incidencia nº: " + (i + 1) + " ->");
                                                System.out.println(buscaTodasIncidenciasAbiertas().get(i));
                                            }
                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;
                                    //CONSULTAR INCIDENCIAS CERRADAS (POR TÉCNICO)
                                    case 2:
                                        for (int i = 0; i < tecnicos.size(); i++) {
                                            System.out.println("\n <- Técnico nº: " + (i + 1) + " ->");
                                            System.out.println(tecnicos.get(i));
                                        }
                                        int numeroTecnico = vista.pideDatosEnteros("número de técnico");

                                        if (!buscaIncidenciasCerradasPorTecnico(numeroTecnico).isEmpty()) {
                                            ArrayList<IncidenciaDataClass> incidenciasCerradas = buscaIncidenciasCerradasPorTecnico(numeroTecnico);
                                            for (IncidenciaDataClass incidencia: incidenciasCerradas) {
                                                System.out.println(incidencia);
                                            }
                                        } else {
                                            vista.errorExistenciaIncidenciasCerradas();
                                        }
                                        break;
                                    //CONSULTAR INCIDENCIAS POR TÉRMINO
                                    case 3:
                                        String palabra = vista.pideDatosString("una palabra");
                                        if (!buscaIncidenciaPorTermino(palabra).isEmpty()) {
                                            for (IncidenciaDataClass incidencia: buscaIncidenciaPorTermino(palabra)) {
                                                System.out.println(incidencia);
                                            }
                                        } else {
                                            vista.errorIncidenciaPorTermino();
                                        }
                                        break;
                                    //MOSTRAR TÉCNICOS
                                    case 4:
                                        if (!tecnicos.isEmpty()) {
                                            for (Tecnico tecnico: tecnicos) {
                                                System.out.println(tecnico);
                                            }
                                        } else {
                                            vista.errorTecnicoRegistrado();
                                        }
                                        break;
                                    //ASIGNAR INCIDENCIA
                                    case 5:
                                        if (!guardaTodasIncidenciasAbiertas().isEmpty()) {
                                            for (int i = 0; i < guardaTodasIncidenciasAbiertas().size(); i++) {
                                                System.out.println("\n <- Incidencia nº: " + (i + 1) + " ->");
                                                System.out.println(guardaTodasIncidenciasAbiertas().get(i));
                                            }
                                            int incidenciaElegida = vista.pideDatosEnteros("el número de la incidencia");

                                            for (int i = 0; i < tecnicos.size(); i++) {
                                                System.out.println("\n <- Técnico nº: " + (i + 1) + " ->");
                                                System.out.println(tecnicos.get(i));
                                            }
                                            int tencicoElegido = vista.pideDatosEnteros("el número del técnico");

                                            if (asignaIncidencia(incidenciaElegida, tencicoElegido)) {
                                                vista.mensajeIncidenciaAsignada();
                                                almacenaActividad("Asignación de incidencia", adminInicioSesion.getNombre());
                                            } else {
                                                vista.errorNuevaIncidenciaAsignada();

                                            }

                                        } else {
                                            vista.errorIncidenciaRegistrada();
                                        }
                                        break;
                                    //CREAR TÉCNICO
                                    case 6:
                                        String nombreTecnico = vista.pideDatosString("el nombre del técnico");
                                        String apellidoTecnico = vista.pideDatosString("el apellido del técnico");
                                        String passwordTecnico = vista.pideDatosString("la contraseña del técnico");
                                        String emailTecnico = vista.pideDatosString("el email del técnico");

                                        if (buscaTecnicoPorEmail(emailTecnico)) {
                                            vista.errorCrearTecnico();
                                        } else {
                                            Tecnico nuevoTecnico = new Tecnico(nombreTecnico, apellidoTecnico, passwordTecnico, emailTecnico);
                                            tecnicos.add(nuevoTecnico);
                                        }
                                        break;
                                    //BORRAR TÉCNICO
                                    case 7:
                                        if (!tecnicos.isEmpty()) {
                                            for (int i = 0; i < tecnicos.size(); i++) {
                                                System.out.println("\n <- Técnico nº: " + (i + 1) + " ->");
                                                System.out.println(tecnicos.get(i));
                                            }

                                            int tecnicoElegido = vista.pideDatosEnteros("el número del técnico");

                                            if (borraTecnico(tecnicoElegido)) {
                                                vista.mensajeTecnicoBorrado();
                                            } else  {
                                                vista.errorBorrarTecnico();
                                            }
                                        } else {
                                            vista.errorTecnicoRegistrado();
                                        }
                                        break;
                                    //MOSTRAR USUARIOS
                                    case 8:
                                        for (Usuario usuario: usuarios) {
                                            System.out.println(usuario);
                                        }
                                        break;
                                    //BORRAR USUARIO
                                    case 9:
                                        if (!usuarios.isEmpty()) {
                                            for (int i = 0; i < usuarios.size(); i++) {
                                                System.out.println("\n <- Usuario nº: " + (i + 1) + " ->");
                                                System.out.println(usuarios.get(i));
                                            }
                                            int usuarioElegido = vista.pideDatosEnteros("el número del usuario");

                                            if (borraUsuario(usuarioElegido)) {
                                                vista.mensajeUsuarioBorrado();
                                            } else {
                                                vista.errorBorrarUsuario();
                                            }
                                        }
                                        break;
                                    //MOSTRAR ESTADÍSTICAS DEL SISTEMA
                                    case 10:
                                        vista.muestraEstadisticas(usuarios.size(), tecnicos.size(), admins.size(), buscaTodasIncidenciasAbiertas().size(), buscaTodasIncidenciasCerradas().size(), buscaTodasIncidenciasAsignadas().size(), buscaTodasIncidenciasSinAsignar().size());
                                        break;
                                    //MOSTRAR INICIOS DE SESIÓN DEL SISTEMA
                                    case 11:
                                        cargaTodosIniciosSesion();
                                        break;
                                    //CERRAR SESIÓN
                                    case 12:
                                        almacenaActividad("Cierre de sesión", adminInicioSesion.getNombre());
                                        almacenaInicioSesion(adminInicioSesion.getNombre());
                                        cerrarSesionAdmin = true;
                                        adminInicioSesion = null;
                                        almacenaAdmins();
                                        break;
                                }
                            } while (!cerrarSesionAdmin);
                        }
                    } else {
                        vista.errorInicioSesion();
                    }
                    break;
                //REGISTRARSE
                case 2:
                    int respuestaMenuRol = 0;
                    String nombre = vista.pideDatosString("nombre");
                    String apellido = vista.pideDatosString("apellido");
                    String clave;
                    String comprobacionClave;
                    do {
                        clave = vista.pideDatosString("contraseña");
                        comprobacionClave = vista.pideDatosString("de nuevo su contraseña");
                    } while (!compruebaPassword(clave, comprobacionClave));

                    String email = vista.pideDatosString("correo electrónico");

                    do {
                        vista.muestraMenuRol();
                        try {
                            respuestaMenuRol = vista.pideDatosEnteros("una opción");
                        } catch (NumberFormatException e) {
                            vista.errorOpcionMenu();
                        }
                    } while (respuestaMenuRol <= 0 || respuestaMenuRol > 2);

                    if (respuestaMenuRol == 1) {
                        if (!buscaCorreoRegistradoUsuarios(email)) {
                            if (insertaUsuario(nombre,apellido,clave,email,respuestaMenuRol)) {
                                vista.mensajeUsuarioRegistrado();
                            } else {
                                vista.errorUsuarioRegistrado();
                            }
                        } else {
                            vista.errorCrearUsuario();
                        }
                    }
                    break;
                case 3:
                    almacenaUsuarios();
                    almacenaTecnicos();
                    almacenaAdmins();
                    salida = true;
                    break;
                default:
                    vista.errorOpcionMenu();
                    break;
            }
        } while (!salida);

    }

    //Log in
    public boolean logIn(String correo, String password) {
        boolean registrado = false;
        for (Usuario usuario: usuarios) {
            if (usuario.getEmail().equals(correo) && usuario.getClave().equals(password)) {
                registrado = true;
                break;
            }
        }

        for (Tecnico tecnico: tecnicos) {
            if (tecnico.getEmail().equals(correo) && tecnico.getClave().equals(password)) {
                registrado = true;
                break;
            }
        }

        for (Admin admin: admins) {
            if (admin.getEmail().equals(correo) && admin.getClave().equals(password)) {
                registrado = true;
                break;
            }
        }
        return registrado;
    }

    //Es Admin
    public Admin buscaAdminInicioSesion(String correo) {
        for (Admin admin : admins) {
            if (correo.equalsIgnoreCase(admin.getEmail())) {
                return admin;
            }
        }
        return null;
    }

    //Es usuario
    public Usuario buscaUsuarioInicioSesion(String correo) {
        for (Usuario usuario: usuarios) {
            if (usuario.getEmail().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioById(int idUsuario) {
        for (Usuario usuario: usuarios) {
            if (idUsuario == usuario.getId()) return usuario;
        }
        return  null;
    }

    //Es técnico
    public Tecnico  buscaTecnicoInicioSesion(String correo) {
        for (Tecnico tecnico: tecnicos) {
            if (tecnico.getEmail().equals(correo)) {
                return tecnico;
            }
        }
        return null;
    }

    //Buscar correo registrado usuarios
    public boolean buscaCorreoRegistradoUsuarios(String correo) {
        for (Usuario usuario: usuarios) {
            if (usuario.getEmail().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    //Comprobar correo
    public boolean compruebaPassword(String password, String comprobacionPassword) {
        if (password.equals(comprobacionPassword)) {
            return true;
        }
        vista.errorClaveDistinta();
        return false;
    }

    //Insertar usuario
    public boolean insertaUsuario(String nombre, String apellido, String clave, String email, int rol) {

        if (rol == 1) {
            Usuario usuarioDeRegistro;

            for (Usuario usuario: usuarios) {
                if (usuario.getEmail().equals(email)) {
                    return false;
                } else {
                    usuarioDeRegistro = new Usuario(nombre,apellido,clave,email);
                    usuarios.add(usuarioDeRegistro);
                    return true;
                }
            }
        } else {
            Admin adminDeRegistro;

            for (Admin admin: admins) {
                if (admin.getEmail().equals(email)) {
                    return false;
                } else {
                    adminDeRegistro = new Admin(nombre,apellido,clave,email);
                    admins.add(adminDeRegistro);
                    return true;
                }
            }
        }
        return false;
    }

    //Insertar incidencia
    public boolean insertaIncidencia(String descripcion, int prioridad, int idUsuario) {
        Incidencia nuevaIncidencia = new Incidencia(descripcion, prioridad, idUsuario);
        for (Usuario usuario: usuarios) {
            if (usuario.getId() == idUsuario) {
                usuario.insertaIncidencia(nuevaIncidencia);
                return true;
            }
        }
        return false;
    }

    //Buscar incidencias asignadas
    public ArrayList<IncidenciaDataClass> buscaIncidenciasAsignadas(int idTecnico) {
        ArrayList<IncidenciaDataClass> incidenciasAsignadas = new ArrayList<IncidenciaDataClass>();
        for (Tecnico tecnico: tecnicos) {
            if (idTecnico == tecnico.getId()){
                for (Incidencia incidencia: tecnico.getIncidencias()) {
                    if (!incidencia.isEstaResuelta()) {
                        //Buscamos al usuario que ha generado la incidencia para poder añadirlo a la incidencia data class
                        Usuario usuarioDataClass = buscarUsuarioById(incidencia.getIdUsuario());

                        //Creamos la data class para añadirla a lista.
                        IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(),incidencia.getSolucion(),
                                incidencia.getPrioridad(),incidencia.isEstaResuelta(), incidencia.getFechaInicio(), incidencia.getFechaFin(),incidencia.diasAbierta() , usuarioDataClass.getId(),usuarioDataClass.getNombre(),usuarioDataClass.getEmail(),tecnico.getId(),tecnico.getNombre());

                        incidenciasAsignadas.add(dataClass);
                    }
                }
            }
        }

        return incidenciasAsignadas;
    }

    //Buscar todas las incidencias asignadas
    public ArrayList<IncidenciaDataClass> buscaTodasIncidenciasAsignadas() {
        ArrayList<IncidenciaDataClass> incidenciasSinAsignar = new ArrayList<IncidenciaDataClass>();
        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (incidencia.isEstaAsignada()) {
                    IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(), incidencia.getSolucion(),incidencia.getPrioridad(),incidencia.isEstaResuelta(),incidencia.getFechaInicio(), incidencia.getFechaFin(),incidencia.diasAbierta(), incidencia.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
                    incidenciasSinAsignar.add(dataClass);
                }
            }
        }
        return incidenciasSinAsignar;
    }

    //Buscar todas las incidencias sin asignar
    public ArrayList<IncidenciaDataClass> buscaTodasIncidenciasSinAsignar() {
        ArrayList<IncidenciaDataClass> incidenciasSinAsignar = new ArrayList<IncidenciaDataClass>();
        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (!incidencia.isEstaAsignada()) {
                    IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(), incidencia.getSolucion(),incidencia.getPrioridad(),incidencia.isEstaResuelta(),incidencia.getFechaInicio(), incidencia.getFechaFin(),incidencia.diasAbierta(), incidencia.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
                    incidenciasSinAsignar.add(dataClass);
                }
            }
        }
        return incidenciasSinAsignar;
    }

    //Cerrar incidencia
    public boolean cierraIncidencia(int idTecnico, int numeroIncidencia, String solucion) {
        boolean cerradoCorrectamente = false;
        for (Tecnico tecnico: tecnicos) {
            if (idTecnico == tecnico.getId()) {
                Incidencia incidenciaEncontrada = tecnico.getIncidencias().get((numeroIncidencia-1));
                int idIncidencia = incidenciaEncontrada.getId();

                if (idIncidencia != -1) {
                    cerradoCorrectamente = tecnico.cierraIncidencia(idIncidencia,solucion);
                }
            }
        }
        return cerradoCorrectamente;
    }

    //Buscar incidencia cerrada
    public ArrayList<IncidenciaDataClass> buscaIncidenciasCerradas(int idTecnico) {
        ArrayList<IncidenciaDataClass> incidenciasAsignadas = new ArrayList<IncidenciaDataClass>();
        for (Tecnico tecnico: tecnicos) {
            if (idTecnico == tecnico.getId()){
                for (Incidencia incidencia: tecnico.getIncidencias()) {
                    if (incidencia.isEstaResuelta()) {
                        //Buscamos al usuario que ha generado la incidencia para poder añadirlo a la incidencia data class
                        Usuario usuarioDataClass = buscarUsuarioById(incidencia.getIdUsuario());

                        //Creamos la data class para añadirla a lista.
                        IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(),incidencia.getSolucion(),
                                incidencia.getPrioridad(),incidencia.isEstaResuelta(), incidencia.getFechaInicio(), incidencia.getFechaFin(), incidencia.diasAbierta(), usuarioDataClass.getId(),usuarioDataClass.getNombre(),usuarioDataClass.getEmail(),tecnico.getId(),tecnico.getNombre());

                        incidenciasAsignadas.add(dataClass);
                    }
                }
            }
        }
        return incidenciasAsignadas;
    }

    //Buscar todas las incidencias abiertas
    public ArrayList<IncidenciaDataClass> buscaTodasIncidenciasAbiertas() {
        ArrayList<IncidenciaDataClass> incidenciasAbiertas = new ArrayList<IncidenciaDataClass>();
        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (!incidencia.isEstaResuelta()) {
                    IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(), incidencia.getSolucion(),incidencia.getPrioridad(),incidencia.isEstaResuelta(),incidencia.getFechaInicio(), incidencia.getFechaFin(), incidencia.diasAbierta(),incidencia.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
                    incidenciasAbiertas.add(dataClass);
                }
            }
        }
        return incidenciasAbiertas;
    }

    //Buscar todas las incidencias cerradas
    public ArrayList<IncidenciaDataClass> buscaTodasIncidenciasCerradas() {
        ArrayList<IncidenciaDataClass> incidenciasAbiertas = new ArrayList<IncidenciaDataClass>();
        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (incidencia.isEstaResuelta()) {
                    IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(), incidencia.getSolucion(),incidencia.getPrioridad(),incidencia.isEstaResuelta(),incidencia.getFechaInicio(), incidencia.getFechaFin(), incidencia.diasAbierta(),incidencia.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
                    incidenciasAbiertas.add(dataClass);
                }
            }
        }
        return incidenciasAbiertas;
    }

    //Buscar incidencias cerradas por técnico
    public ArrayList<IncidenciaDataClass> buscaIncidenciasCerradasPorTecnico(int numeroTecnico) {
        ArrayList<IncidenciaDataClass> incidenciasCerradas = new ArrayList<>();
        //Obtengo el tecnico mediante la posición
        Tecnico tecnico = tecnicos.get(numeroTecnico-1);

        if (tecnico != null) {
            for (Incidencia incidencia: tecnico.getIncidencias()) {
                if (incidencia.isEstaResuelta()) {
                    //Buscamos al usuario que ha generado la incidencia para poder añadirlo a la incidencia data class
                    Usuario usuarioDataClass = buscarUsuarioById(incidencia.getIdUsuario());

                    //Creamos la data class para añadirla a lista.
                    IncidenciaDataClass dataClass = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(),incidencia.getSolucion(),
                            incidencia.getPrioridad(),incidencia.isEstaResuelta(), incidencia.getFechaInicio(), incidencia.getFechaFin(), incidencia.diasAbierta(), usuarioDataClass.getId(),usuarioDataClass.getNombre(),usuarioDataClass.getEmail(),tecnico.getId(),tecnico.getNombre());
                    //Añadimos la data class de incidencias
                    incidenciasCerradas.add(dataClass);
                }
            }
        }

        return incidenciasCerradas;
    }

    //Buscar técnico por email
    public boolean buscaTecnicoPorEmail(String email) {
        boolean existe = false;
        for (Tecnico tecnico: tecnicos) {
            if (email.equals(tecnico.getEmail())) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Borrar usuario
    public boolean borraUsuario(int numeroUsuario) {
        Usuario usuario = usuarios.get(numeroUsuario - 1);

        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }

        return false;
    }

    //Borrar técnico
    public boolean borraTecnico(int numeroTecnico) {
        //Obtengo tecnico
        Tecnico tecnico = tecnicos.get(numeroTecnico-1);

        //Comprobamos si existe tecnico
        if (tecnico != null) {
            //Itero en las incidencias de los tecnicos.
            for (Incidencia incidencia: tecnico.getIncidencias()) {
                // Si hay alguno que no se ha resuelto no se borra el tecnico
                if (!incidencia.isEstaResuelta()) return false;
            }
        }

        //Borramos al tecnico
        boolean estaTecnicoBorrado = tecnicos.remove(tecnico);

        return estaTecnicoBorrado;
    }

    //Guardar todas las incidencias del sistema
    public ArrayList<Incidencia> guardaTodasIncidenciasAbiertas() {
        ArrayList<Incidencia> incidenciasAbiertas = new ArrayList<Incidencia>();
        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (!incidencia.isEstaAsignada()) {
                    incidenciasAbiertas.add(incidencia);
                }
            }
        }
        return incidenciasAbiertas;
    }

    //Marcar incidencia como asignada
    public boolean marcarIncidenciaComoAsignada(Incidencia incidencia) {
        //Itero en los usuarios
        for (Usuario usuario : usuarios) {
            for (Incidencia incidenciaUsuario : usuario.getIncidencias()) {
                //Si la incidencia pasada por parametro tiene la misma id, pasa a ser asignada.
                if (incidenciaUsuario.getId() == incidencia.getId()) {
                    incidenciaUsuario.setEstaAsignada(true);
                    return true;
                }
            }
        }
        return false;
    }

    //Asignar una incidencia
    public boolean asignaIncidencia(int numeroIncidencia, int numeroTecnico) {
        boolean estaAsignada = false;
        Incidencia incidencia = new Incidencia();
        for (int i = 0; i < guardaTodasIncidenciasAbiertas().size(); i++) {
            if (i == (numeroIncidencia - 1)) {
                incidencia = guardaTodasIncidenciasAbiertas().get(i);
            }
        }

        if (incidencia != null) {
            for (int i = 0; i < tecnicos.size(); i++) {
                if (i == (numeroTecnico - 1)) {
                    tecnicos.get(i).getIncidencias().add(incidencia);
                    //Marcamos la incidencia como asignada
                    if (marcarIncidenciaComoAsignada(incidencia)) {
                        estaAsignada = true;
                    }
                }
            }
        }

        return estaAsignada;
    }

    //Buscar incidencias por término
    public ArrayList<IncidenciaDataClass> buscaIncidenciaPorTermino(String palabra) {
        ArrayList<IncidenciaDataClass> incidenciasPorTermino = new ArrayList<IncidenciaDataClass>();

        for (Usuario usuario: usuarios) {
            for (Incidencia incidencia: usuario.getIncidencias()) {
                if (incidencia.getDescripcion().contains(palabra)) {
                    IncidenciaDataClass incidenciaEncontrada = new IncidenciaDataClass(incidencia.getId(), incidencia.getDescripcion(), incidencia.getSolucion(), incidencia.getPrioridad(), incidencia.isEstaResuelta(), incidencia.getFechaInicio(), incidencia.getFechaFin(), incidencia.diasAbierta() ,usuario.getId(), usuario.getNombre(), usuario.getEmail());
                    incidenciasPorTermino.add(incidenciaEncontrada);
                }
            }
        }
        return incidenciasPorTermino;
    }

    //T6
    //Almacenar datos usuarios
    public void almacenaUsuarios() {
        try {
            ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("resources/usuarios.dat"));
            oop.writeObject(usuarios);
            oop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Almacenar datos técnicos
    public void almacenaTecnicos() {
        try {
            ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("resources/tecnicos.dat"));
            oop.writeObject(tecnicos);
            oop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Almacenar datos administradores
    public void almacenaAdmins() {
        try {
            ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("resources/admins.dat"));
            oop.writeObject(admins);
            oop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Cargar datos usuarios
    public void cargaUsuarios(){
        try {
            ObjectInputStream oisUsuarios = new ObjectInputStream(new FileInputStream("resources/usuarios.dat"));
            usuarios = (ArrayList<Usuario>) oisUsuarios.readObject();
            oisUsuarios.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Cargar datos tecnicos
    public void cargaTecnicos(){
        try {
            ObjectInputStream oisTecnicos = new ObjectInputStream(new FileInputStream("resources/tecnicos.dat"));
            tecnicos = (ArrayList<Tecnico>) oisTecnicos.readObject();
            oisTecnicos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Cargar datos administradores
    public void cargaAdmins(){
        try {
            ObjectInputStream oisAdmins = new ObjectInputStream(new FileInputStream("resources/admins.dat"));
            admins = (ArrayList<Admin>) oisAdmins.readObject();
            oisAdmins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Almacenar actividad
    public void almacenaActividad(String actividad, String nombre) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("resources/actividad.log", true));
            bw.write(actividad + " / " + nombre + " / " + LocalDateTime.now() + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Almacenar último inicio de sesión usuarios
    public void almacenaInicioSesion(String nombre) {
        Properties properties = new Properties();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String hora = sdf.format(Calendar.getInstance().getTime());

            properties.load(new FileInputStream("./resources/inicioSesion.properties"));

//            String primeraVezCargado = properties.getProperty("primeraVez");
            properties.setProperty(nombre, hora);
            properties.store(new FileWriter("./resources/inicioSesion.properties"), "Inicios de sesión en el sistema");
//            if (primeraVezCargado == null) {
//                properties.setProperty("primeraVez","true");
//
//            } else {
//                properties.setProperty(nombre, hora);
//                properties.store(new FileWriter("./resources/inicioSesion.properties",true),"");
//
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Obtener último inicio de sesión
    public String cargaInicioSesion(String nombre){
        Properties properties = new Properties();
        String inicioSesion = "";
        try {
            properties.load(new FileInputStream("./resources/inicioSesion.properties"));
            inicioSesion = properties.getProperty(nombre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inicioSesion;
    }

    //Obtener todos los inicios de sesión
    public void cargaTodosIniciosSesion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("./resources/inicioSesion.properties"));
            //Keys devuelve todas las claves del fichero
            Enumeration<Object> claves = properties.keys();
            //Aquí se compruebea que el fichero no esté vacío
            if (claves.hasMoreElements()) {
                while (claves.hasMoreElements()) {
                    Object clave = claves.nextElement();
                    System.out.println(clave.toString() + ": " + properties.get(clave).toString() );
                }
                System.out.println();
            } else {
                vista.errorDatoRegistrado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
