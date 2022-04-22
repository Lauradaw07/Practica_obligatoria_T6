package com.company;

import controlador.GestionAPP;
import modelos.Admin;
import modelos.Tecnico;
import modelos.Usuario;

public class Main {

    public static void main(String[] args) {

        GestionAPP gestor = new GestionAPP();
        gestor.cargaDatos();
        gestor.inicia();

    }
}
