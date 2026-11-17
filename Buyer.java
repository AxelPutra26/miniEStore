package com.mycompany.miniestore;
import java.util.ArrayList;
import java.util.List;
public class Buyer {
    public String nama;
    public String alamat;
    public double saldo;
    public shoppingCart cart;
    private List<Order> orderHistory;
    
    public Buyer(String nama, String alamat, double saldo){
        this.nama=nama;
        this.alamat=alamat;
        this.saldo=saldo;
        this.cart = new shoppingCart();
        this.orderHistory = new ArrayList<>();
    }
    public String getnama(){
        return nama;
    }
    public String getalamat(){
        return alamat;
    }
    public void setalamat(String alamat){
        this.alamat=alamat;
    }
    public void setnama(String nama){
        this.nama=nama;
    }
    public double getsaldo(){
        return saldo;
    }
    public void setsaldo(double saldo){
        this.saldo=saldo;
    } 
    public void topup(double amount){
        saldo+=amount;
    }
    public shoppingCart getcart(){
        return cart;
    }
    public void topUp(double amount) {
        if (amount > 0) {
            this.saldo += amount;
            System.out.println("Top-up berhasil! Saldo sekarang: Rp." + this.saldo+"-");
        } else {
            System.out.println("Jumlah top-up tidak valid.");
        }
    }
    public void checkout() {
        double totalBelanja = cart.total();

        // 1. Cek apakah saldo pembeli mencukupi
        if (this.saldo < totalBelanja) {
            System.out.println("Transaksi Gagal! Saldo Anda tidak mencukupi untuk total belanja Rp." + totalBelanja+"-");
            return;
        }

        // 2. Kurangi stok produk asli di toko berdasarkan item di keranjang
        for (CartItem item : cart.getItems()) {
            Product prod = item.getProduct();
            int qtyBought = item.getQuantity();
            prod.reduceStock(qtyBought); // Mengurangi stok produk di toko
        }

        // 3. Potong saldo pembeli
        this.saldo -= totalBelanja;
        System.out.printf("Purchase successful! Remaining saldo: Rp.%.2f-\n", this.saldo);

        //4. Simpan pesanan ke orderHistory
        int newOrderId = orderHistory.size() + 1;
        Order completedOrder = new Order(newOrderId, cart.getItems(), totalBelanja);
        orderHistory.add(completedOrder);
        
        // 5. Kosongkan keranjang setelah transaksi sukses
        cart.clearcart();
    }
    public void printOrderReceipt(int searchId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == searchId) {
                order.printReceipt(this.nama, this.alamat);
                return; // Langsung berhenti setelah struk ditemukan & dicetak
            }
        }
        System.out.println("Struk dengan Order ID #" + searchId + " tidak ditemukan.");
    }
    public void viewOrderHistory() {
        System.out.println("\n==========================================");
        System.out.println("              ORDER HISTORY               ");
        System.out.println("==========================================");
        if (orderHistory.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
        } else {
            for (Order order : orderHistory) {
                order.displayOrderDetails();
            }
        }
        System.out.println("==========================================");
    }
}
