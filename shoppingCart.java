package com.mycompany.miniestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class shoppingCart {
    // Menggunakan interface List dengan implementasi ArrayList
    private List<CartItem> items;
    private final double pajak = 0.11; 
    private final int[] listOngkir = {0, 3000, 5000, 10000};
    private int ongkir;
    private Random rand = new Random();

    // Constructor untuk inisialisasi keranjang kosong
    public shoppingCart() {
        this.items = new ArrayList<>();
        generateOngkir();
    }
    
    private void generateOngkir() {
        this.ongkir = listOngkir[rand.nextInt(listOngkir.length)];
    }

    public void addtocart(Product product, int quantity){
        //mengecek stok
        if (quantity > product.stock) {
            System.out.println("Stok tidak mencukupi! Sisa stok: " + product.stock);
            return;
        }

        // Cek apakah produk yang sama sudah ada di dalam keranjang
        CartItem existingItem = null;
        for (CartItem item : items) {
            if (item.getProduct().id == product.id) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            // Jika sudah ada, tambahkan quantity-nya saja
            existingItem.addquantity(quantity);
            System.out.println("Updated quantity for " + product.nama + " in cart.");
        } else {
            // Jika belum ada, buat CartItem baru dan masukkan ke list
            items.add(new CartItem(product, quantity));
            System.out.println("Added " + product.nama+ "x "  +quantity +  " to cart.");
        }
    }
    public void viewcart(){
        if (items.isEmpty()) {
            System.out.println("Keranjang belanja Anda masih kosong.");
            return;
        }

        System.out.println("\n--- YOUR SHOPPING CART ---");
        for (CartItem item : items) {
            System.out.println(item.toString()); // Memanggil toString() dari CartItem
        }
        System.out.printf("Pajak (11%%)   : Rp %.2f\n", getPajak());
        System.out.printf("Ongkir        : Rp %d.00\n", ongkir);
        System.out.println("--------------------------");
        System.out.printf("Total Belanja : Rp.%.2f-\n", total());
    }

    public double getPajak() {
        double totalPajak = 0;
        for (CartItem item : items) {
            totalPajak += item.getSubtotal() * pajak; // Langsung panggil dari item
        }
        return totalPajak;
    }

    public int getOngkir() {
        return ongkir;
    }
    
    public double total() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total + getPajak() + ongkir;
    }
    public void clearcart(){
        items.clear();
    }
    public boolean isempty(){
        return items.isEmpty();
    }
    public List<CartItem> getItems() {
        return items;
    }
    public void removeItem(int productId, int quantityToRemove) {
        CartItem targetItem = null;
        
        for (CartItem item : items) {
            if (item.getProduct().id == productId) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            System.out.println("Produk dengan ID tersebut tidak ada di dalam keranjang.");
            return;
        }

        if (quantityToRemove >= targetItem.getQuantity()) {
            // Jika jumlah yang dikurangi >= isi keranjang, hapus item tersebut dari list
            items.remove(targetItem);
            System.out.println(targetItem.getProduct().nama + " berhasil dihapus dari keranjang.");
        } else {
            // Jika hanya dikurangi sebagian
            targetItem.addquantity(-quantityToRemove); // Mengurangi quantity
            System.out.println("Jumlah " + targetItem.getProduct().nama + " di keranjang dikurangi.");
        }
    }
}
