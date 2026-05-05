package org.example;

import org.example.dao.*;
import org.example.model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicialización de los DAO
        ClientesDAO clientesDao = new ClientesDAOImpl();
        vehiculosDAO vehiculosDao = new vehiculosDAOImpl();
        servicioDAO servicioDao = new servicioDAOImpl();
        registroLavadoDAO lavadosDao = new registroLavadoDAOImpl();

        int opcionPrincipal;

        do {
            System.out.println("\n========================================");
            System.out.println("   🚗💦 SISTEMA DE GESTIÓN DEL LAVADERO");
            System.out.println("========================================");
            System.out.println(" [1] 👤 Clientes");
            System.out.println(" [2] 🚘 Vehículos");
            System.out.println(" [3] 🛠️ Servicios");
            System.out.println(" [4] 📑 Registros de Lavados");
            System.out.println(" [0] ❌ Salir");
            System.out.println("----------------------------------------");
            System.out.print("👉 Elige una opción: ");
            opcionPrincipal = sc.nextInt();

            switch (opcionPrincipal) {
                case 1 -> menuClientes(sc, clientesDao);
                case 2 -> menuVehiculos(sc, vehiculosDao);
                case 3 -> menuServicios(sc, servicioDao);
                case 4 -> menuLavados(sc, lavadosDao);
                case 0 -> System.out.println("👋 Cerrando el sistema. ¡Hasta luego!");
                default -> System.out.println("⚠️ Opción no válida, intenta de nuevo.");
            }
        } while (opcionPrincipal != 0);
    }

    // --- MODULO CLIENTES ---
    private static void menuClientes(Scanner sc, ClientesDAO dao) {
        System.out.println("\n--- 👤 SUBMENÚ CLIENTES ---");
        System.out.println(" [1] ➕ Registrar nuevo cliente");
        System.out.println(" [2] 📋 Listar todos los clientes");
        System.out.println(" [3] 🗑️ Eliminar cliente");
        System.out.print("👉 Opción: ");
        int op = sc.nextInt();

        switch (op) {
            case 1 -> {
                System.out.print("ID Cliente (manual >= 100): ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Nombre: ");
                String nom = sc.nextLine();
                System.out.print("Apellido: ");
                String ape = sc.nextLine();
                System.out.print("Teléfono: ");
                String tel = sc.nextLine();
                dao.crear(new Clientes(id, nom, ape, tel, "N/A", "N/A"));
                System.out.println("✅ Cliente registrado.");
            }
            case 2 -> {
                System.out.println("\n📋 LISTA DE CLIENTES:");
                dao.listar().forEach(c -> System.out.println("ID: " + c.getClienteID() + " | Nombre: " + c.getNombre() + " " + c.getApellido()));
            }
            case 3 -> {
                System.out.print("ID del cliente a eliminar: ");
                int idEl = sc.nextInt();
                System.out.print("¿Está seguro? (s/n): ");
                if (sc.next().equalsIgnoreCase("s")) {
                    dao.eliminar(idEl);
                    System.out.println("🗑️ Cliente eliminado.");
                }
            }
        }
    }

    // --- MODULO VEHICULOS ---
    private static void menuVehiculos(Scanner sc, vehiculosDAO dao) {
        System.out.println("\n--- 🚘 SUBMENÚ VEHÍCULOS ---");
        System.out.println(" [1] ➕ Registrar vehículo");
        System.out.println(" [2] 🔍 Listar vehículos por ClienteID");
        System.out.println(" [3] 📋 Listar TODOS los vehículos");
        System.out.println(" [4] 🗑️ Eliminar vehículo");
        System.out.print("👉 Opción: ");
        int op = sc.nextInt();

        switch (op) {
            case 1 -> {
                System.out.print("ID Vehículo (manual): ");
                int vId = sc.nextInt();
                System.out.print("ID del Dueño (ClienteID): ");
                int cId = sc.nextInt(); sc.nextLine();
                System.out.print("Placa: "); String pla = sc.nextLine();
                System.out.print("Marca: "); String mar = sc.nextLine();
                System.out.print("Modelo: "); String mod = sc.nextLine();
                System.out.print("Color: "); String col = sc.nextLine();
                System.out.print("Tipo: "); String tip = sc.nextLine();
                dao.registrar(new vehiculos(vId, cId, mar, mod, pla, col, tip));
                System.out.println("✅ Vehículo registrado.");
            }
            case 2 -> {
                System.out.print("Ingrese ID del cliente: ");
                int buscar = sc.nextInt();
                dao.listarPorCliente(buscar).forEach(v -> System.out.println("Placa: " + v.getPlaca() + " | Marca: " + v.getMarca()));
            }
            case 3 -> dao.listarTodos().forEach(v -> System.out.println("V_ID: " + v.getVehiculoID() + " | C_ID: " + v.getClienteID() + " | Placa: " + v.getPlaca() + " | Marca: " + v.getMarca() + " | Color: " + v.getColor()));
            case 4 -> {
                System.out.print("ID del vehículo a eliminar: ");
                int delId = sc.nextInt();
                dao.eliminar(delId);
                System.out.println("🗑️ Vehículo eliminado.");
            }
        }
    }

    // --- MODULO SERVICIOS ---
    private static void menuServicios(Scanner sc, servicioDAO dao) {
        System.out.println("\n--- 🛠️ SUBMENÚ SERVICIOS ---");
        System.out.println(" [1] ➕ Agregar servicio al catálogo");
        System.out.println(" [2] 📋 Ver catálogo de precios");
        System.out.print("👉 Opción: ");
        int op = sc.nextInt();

        if (op == 1) {
            System.out.print("ID Servicio: "); int sId = sc.nextInt(); sc.nextLine();
            System.out.print("Nombre Servicio: "); String nS = sc.nextLine();
            System.out.print("Precio: "); double pre = sc.nextDouble();
            dao.registrar(new servicio(sId, nS, pre));
            System.out.println("✅ Servicio agregado.");
        } else {
            System.out.println("\n📋 CATÁLOGO DE SERVICIOS:");
            dao.listar().forEach(s -> System.out.println("ID: " + s.getServicioID() + " | " + s.getNombreServicio() + " | $" + s.getPrecio()));
        }
    }

    // --- MODULO REGISTROS LAVADO ---
    private static void menuLavados(Scanner sc, registroLavadoDAO dao) {
        System.out.println("\n--- 📑 SUBMENÚ REGISTROS DE LAVADOS ---");
        System.out.println(" [1] ➕ Registrar nuevo lavado");
        System.out.println(" [2] 📋 Ver historial de lavados");
        System.out.print("👉 Opción: ");
        int op = sc.nextInt();

        if (op == 1) {
            System.out.print("ID Registro: "); int rId = sc.nextInt();
            System.out.print("ID Vehículo: "); int vId = sc.nextInt();
            System.out.print("ID Servicio: "); int sId = sc.nextInt(); sc.nextLine();
            System.out.print("Fecha (AAAA-MM-DD): "); String fec = sc.nextLine();
            dao.registrar(new registroLavado(rId, vId, sId, fec));
            System.out.println("✅ Lavado registrado.");
        } else {
            System.out.println("\n📋 HISTORIAL DE LAVADOS:");
            dao.listarTodo().forEach(r -> System.out.println("Reg: " + r.getRegistroID() + " | Veh: " + r.getVehiculoID() + " | Serv: " + r.getServicioID() + " | Fecha: " + r.getFecha()));
        }
    }
}
