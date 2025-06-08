import java.text.DecimalFormat;
import java.util.ArrayList;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private static ArrayList<Producto> listaProductos = new ArrayList<Producto>();
    private static int contadorId = 0;

    public Producto(){

    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // busca un producto por id y lo retorna
    public static Producto buscarProductoPorId(int id){
        for(Producto p: listaProductos){
            if (p.getId() == id){
              return p;
            }
        }
        return null;
    }

    public static Producto buscarProductoPorNombre(String nombre){
        for(Producto p: listaProductos){
            if (p.getNombre().equalsIgnoreCase(nombre)){
                return p;
            }
        }
        return null;
    }

    // elimina producto por id
    public static boolean eliminarProducto(int id_producto){
        return listaProductos.removeIf(p -> p.getId() == id_producto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        DecimalFormat df = new DecimalFormat("#.##"); // Formatea a 2 decimales
        String formattedNum = df.format(precio);
        this.precio =  Double.parseDouble(formattedNum);
    }

    public void mostrarInfo(){
        System.out.printf("--- Información del producto #%d --- \n", this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: " + this.precio);
        System.out.println("Stock: " + this.stock);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock > 0) {
            this.stock = stock;
        }else{
            System.out.println("El stock debe ser mayor a cero");
        }
    }

    public static ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public static void agregarProducto(Producto producto){
        producto.setId(generarId());
        listaProductos.add(producto);
        System.out.println("Producto agregado a la lista");
    }

    public static int generarId(){
        contadorId++;
        return contadorId;
    }

    public void restarStock(int cantidad){
        if(cantidad <= stock && cantidad > 0) {
            this.stock -= cantidad;
        }
    }

}
