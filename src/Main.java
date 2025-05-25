import java.util.Random;
import java.util.Scanner;

public class Main {
    // Definir Arreglos.
    static Scanner entrada = new Scanner(System.in);
    static String[] planetas = { "Marte", "Jupiter", "Saturno", "Mercurio" };
    static double[] distaciaPlanetas = { 78.3, 628.7, 1275.0 };
    static String[] descripcionPlanetas = {
            "Es conocido como el planeta rojo, su superficie rica en óxido de hierro. Tiene una atmósfera delgada de dióxido de carbono, temperaturas frías. Su superficie muestra volcanes, valles y evidencias de agua en el pasado.",
            "Es el planeta más grande, compuesto principalmente de hidrógeno y helio.Es conocido por sus bandas de nubes y la Gran Mancha Roja, una tormenta gigante.",
            "Es conocido por sus anillos formados por hielo y partículas rocosas. Es compuesto principalmente de hidrógeno y helio."
    };

    static String[] naves = { "Galactic Voyager", "Nebula Cruiser", "Star Runner" };
    static String[] descripcionNaves = {
            "Esta nave tiene impulsores avanzados, esta nave esta diseñada para ofrecer trayectos cortos y rapidos entre planetas cercanos.",
            "Esta equipada con tecnologia de ultima generacion para viajes interplanetarios, su diseño aerodinamico maximiza la eficiencia de combustible",
            "Su robusta estructura y capacidad de carga la hacen ideal para transportar grandes grupos de pasajeros y equipos."
    };

    static double[] velocidadesNave = { 12000.0, 20000.0, 25000.0 };
    static double velocidadFija = 100000.0;

    static boolean salir = false;
    static int planetaSeleccionado = 0;
    static int naveSeleccionada = 0;

    public static void main(String[] args) {
        do {
            mostrarMenu();
            int opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 1:
                    seleccionarPlaneta();
                    break;
                case 2:
                    seleccionarNave();
                    break;
                case 3:
                    validarSimulacion();
                    break;

                case 4:
                    System.out.println("Saliendo del simulador. ¡Gracias por confiar en nosotros!..");
                    salir = true;
                    break;
                default:
                    System.err.println("Opción inválida. Inténtalo de nuevo.");
                    break;
            }
        } while (!salir);
    }

    public static void mostrarMenu() {
        System.out.println("==================================================");
        System.out.println("     ¡Bienvenido al Simulador Interplanetario!    ");
        System.out.println("==================================================");
        System.out.println("               Menu de Opciones                   ");
        System.out.println("==================================================");
        System.out.println("1. Seleccione un planeta de destino");
        System.out.println("2. Seleccione una nave");
        System.out.println("3. Iniciar simulación del viaje");
        System.out.println("4. Salir ");
        System.out.println("Seleccione una opción: ");
    }

    public static void seleccionarPlaneta() {
        // Se muestra el planeta y la distancia desde la tierra.
        System.out.println("----- Seleccionar planeta de destino -----");
        for (int i = 0; i < planetas.length; i++) {
            System.out.printf("%d. %s (%.1f millones de km) %n", i + 1, planetas[i], distaciaPlanetas[i]);
        }
        System.out.print("Seleccione el número del planeta: ");
        int seleccion = entrada.nextInt();

        // Validar la selección
        if (seleccion >= 1 && seleccion <= planetas.length) {
            System.out.println("Ha seleccionado -> " + planetas[seleccion - 1]);

            // Confirmación del usuario
            System.out.print("¿Confirmas tu selección (Y/N)?: ");
            char confirmacion = entrada.next().toUpperCase().charAt(0);

            if (confirmacion == 'Y') {
                planetaSeleccionado = seleccion;
                System.out.printf("Planeta confirmado -> %s%n", planetas[seleccion - 1]);
                // Muestra información detallada del planeta
                System.out.printf("Distancia desde la Tierra: %.0f millones de km%n", distaciaPlanetas[seleccion - 1]);
                System.out.println("Descripción: " + descripcionPlanetas[seleccion - 1]);
            } else {
                System.out.println("Selección cancelada. Volviendo al menú...");
            }
        }else{
            System.err.println("Seleccion invalida. Por favor vuelve a intentarlo.");
        }
    }

    public static void seleccionarNave() {
        System.out.print("Ingrese la cantidad de pasajeros: ");
        int pasajeros = entrada.nextInt();

        if (pasajeros <= 0) {
            System.err.println("Por favor, ingrese un valor");
            
        }

        System.out.println("\n--- Seleccione una nave para " + pasajeros + " pasajeros ---");
        for (int i = 0; i < naves.length; i++) {
            System.out.printf("%d. %s%n Velocidad Máxima: %.0f km/h%n Descripción: %s%n ",
                    i + 1, naves[i], velocidadesNave[i], descripcionNaves[i]);
        }

        System.out.print("Seleccione el numero de la nave: ");
        int seleccionNave = entrada.nextInt();

        if (seleccionNave >= 1 && seleccionNave <= naves.length) {
            System.out.println("Ha seleccionado: " + naves[seleccionNave - 1]);
            System.out.printf("Velocidad: %.0f km/h%n", velocidadesNave[seleccionNave - 1]);

            System.out.print("¿Confirmas tu selección (Y/N)?: ");
            char confirmacionNave = entrada.next().toUpperCase().charAt(0);

            if (confirmacionNave == 'Y') {
                naveSeleccionada = seleccionNave;
                System.out.printf("Nave confirmada con éxito -> %s%n", naves[seleccionNave - 1]);
                
            } else {
                System.out.println("Selección cancelada. Volviendo al menú.");
                
            }
        } else {
            System.err.println("Selección inválida. Inténtalo de nuevo.");
            
        }
    }

    public static void validarSimulacion() {
        if (planetaSeleccionado == 0) {
            System.out.println("No has seleccionado plaeta. Por favor selecciona uno primero.");
            return;
        }
        if (naveSeleccionada == 0) {
            System.out.println("no has seleccioado una nave. Por favor selecciona primero");
            return;
        }
        iniciarSimulacion();
    }

    public static void calcularDistanciaTiempo() {
        double distancia = distaciaPlanetas[planetaSeleccionado -1] * 1_000_000; // Convertir millones de km a km
        double tiempoHoras = distancia / velocidadFija;
        double tiempoDias = tiempoHoras / 24;
    
        System.out.printf("Distancia al planeta %s: %.0f km%n", planetas[planetaSeleccionado -1], distancia);
        System.out.printf("Tiempo estimado de viaje a una velocidad fija de %.0f km/h: %.2f horas y %.2f días%n",
                velocidadFija, tiempoHoras, tiempoDias);
    }

    public static void gestionRecursos() {
        double distancia = distaciaPlanetas[planetaSeleccionado - 1];
        double combustibleNecesario = distancia * 1.2;
        double oxigenoNecesario = distancia * 0.8;

        System.out.printf("Combustible necesario: %.2f toneladas%n", combustibleNecesario);
        System.out.printf("Oxígeno necesario: %.2f toneladas%n", oxigenoNecesario);

    }

    public static void iniciarSimulacion() {
        calcularDistanciaTiempo();
        gestionRecursos();
        System.out.println("** ..INICIANDO SIMULACION.. **");

        Random rand = new Random();
        double progress = 0;

        while (progress < 100) {
            //Incremento Aleatorio entre 5  y 15%
            progress += rand.nextInt(15) + 5;
            if (progress > 100) progress = 100;
            System.out.printf("Progreso del viaje: %2f%%%n", progress);

            if (rand.nextInt(100) < 10) {
                System.out.println("Evento aleatorio: Falla en el sistema. --Iniciando Reparaciones..--");
            }
            if (rand.nextInt(100) < 30) {
                System.out.println("Evento aleatorio: Impacto de Asteroide. -_Evadiendo Asteroide--");
            }
            if (rand.nextInt(100) < 50) { // 50% de probabilidad de un evento
                System.out.println("Evento aleatorio: Falla en un motor. Reparando...");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Simulacion Interrumpida.");
            }

        }
        System.out.println("¡Felicidades!. Has llegado a tu destino");

    }
}