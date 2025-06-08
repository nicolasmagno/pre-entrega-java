import java.util.ArrayList;

public class Orden {
    private int id;
    private double total;
    private ArrayList<ProductoPedido> productosEnLaOrden = new ArrayList<ProductoPedido>();
    private static ArrayList<Orden> pedidosRealizados = new ArrayList<Orden>();
    private static int contadorId = 0;

    public void calcularTotal(){
        double total = 0;
        for (ProductoPedido productoPedido : productosEnLaOrden) {
            total += productoPedido.getProducto().getPrecio() * productoPedido.getCantidad();
        }
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotal(){
        return this.total;
    }

    public static int generateId() {
        contadorId++;
        return contadorId;
    }

    public void setId(int id){
        this.id = id;
    }

    public void agregarProducto(ProductoPedido productoPedido){
        this.productosEnLaOrden.add(productoPedido);
        System.out.println("Producto agregado a la orden");
    }

    public static ArrayList<Orden> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public void mostrarInfo(){
        System.out.println(" --- Orden #" + this.id + " ---");
        for(ProductoPedido productoPedido: productosEnLaOrden){
            System.out.print("Producto: ");
            System.out.println(productoPedido.getProducto().getNombre());
            System.out.print("Precio: ");
            System.out.println(productoPedido.getProducto().getPrecio());
            System.out.print("Cantidad: ");
            System.out.println(productoPedido.getCantidad());
            System.out.println(" ----------- ");
        }
        System.out.println();
        System.out.println("TOTAL: " + "$" + this.getTotal());
        System.out.println();
    }

    public static void guardarOrden(Orden orden){
        pedidosRealizados.add(orden);
    }
}
