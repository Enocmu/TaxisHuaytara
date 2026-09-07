import java.util.ArrayList;
import java.util.List;


// ENCAPSULAMIENTO (Uso de private en todas las clases)

class Viaje {
    private String destino;
    private double tarifa;

    public Viaje(String destino, double tarifa) {
        this.destino = destino;
        this.tarifa = tarifa;
    }
    public String getDetalle() { return "Destino: " + destino + " | Tarifa: S/" + tarifa; }
}

class Pasajero {
    private String nombre;
    public Pasajero(String nombre) { this.nombre = nombre; }
    public void solicitarViaje(Viaje v) {
        System.out.println("El pasajero " + nombre + " solicito el viaje -> " + v.getDetalle());
    }
}

class Conductor {
    private String nombre;
    private Conductor coordinador; 

    public Conductor(String nombre) { this.nombre = nombre; }
    public void asignarCoordinador(Conductor c) { this.coordinador = c; }
    public void mostrarJerarquia() {
        if (coordinador != null) {
            System.out.println("Conductor: " + nombre + " | Es coordinado por: " + coordinador.getNombre());
        } else {
            System.out.println("Conductor: " + nombre + " | Es el coordinador principal.");
        }
    }
    public String getNombre() { return nombre; }
}

class Empresa {
    private String nombre;
    private List<Conductor> conductores; 

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.conductores = new ArrayList<>();
    }
    public void agregarConductor(Conductor c) {
        conductores.add(c);
        System.out.println("Se registro al conductor: " + c.getNombre());
    }
    public void mostrarListaConductores() {
        System.out.println("Lista de conductores en " + nombre + ":");
        for (Conductor c : conductores) { System.out.println("- " + c.getNombre()); }
    }
}

class Motor {
    private String serie;
    public Motor(String serie) { this.serie = serie; }
    public String getSerie() { return serie; }
}


// HERENCIA Y POLIMORFISMO

// Clase Base (Superclase)
class Vehiculo {
    protected String placa;
    
    public Vehiculo(String placa) {
        this.placa = placa;
    }
    
    // Método original
    public void mostrarSpecs() {
        System.out.println("Vehiculo generico con placa: " + placa);
    }
}

// Subclase aplicando Herencia
class Taxi extends Vehiculo {
    private Motor motor; 

    public Taxi(String placa, String serieMotor) {
        super(placa); // Llama al constructor de Vehiculo
        this.motor = new Motor(serieMotor); 
    }
    
    // Polimorfismo
    @Override
    public void mostrarSpecs() {
        System.out.println("Taxi Placa [" + placa + "] ensamblado con motor serie: " + motor.getSerie());
    }
}


// CLASE PRINCIPAL 

public class MainSoporte {
    public static void main(String[] args) {
        System.out.println(" SISTEMA DE TAXIS \n");
        
        System.out.println(" Prueba de ASOCIACION ");
        Pasajero pasajero1 = new Pasajero("Jose Manuel");
        Viaje viaje1 = new Viaje("Huaytara Centro", 12.50);
        pasajero1.solicitarViaje(viaje1);

        System.out.println("\n Prueba de REFLEXIVIDAD ");
        Conductor coordinadorGral = new Conductor("Carlos");
        Conductor chofer = new Conductor("Luis");
        chofer.asignarCoordinador(coordinadorGral);
        coordinadorGral.mostrarJerarquia();
        chofer.mostrarJerarquia();

        System.out.println("\n Prueba de HERENCIA, POLIMORFISMO Y COMPOSICION ");
        // Se instancia como Taxi, usando el método sobrescrito (Polimorfismo)
        Taxi taxi1 = new Taxi("W3X-123", "MOT-998877");
        taxi1.mostrarSpecs();
        
        System.out.println("\n Prueba de AGREGACION CON COLECCIONES ");
        Empresa empresaTaxis = new Empresa("Taxis Express");
        empresaTaxis.agregarConductor(coordinadorGral);
        empresaTaxis.agregarConductor(chofer);
        empresaTaxis.mostrarListaConductores();
    }
}