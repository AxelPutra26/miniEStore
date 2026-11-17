package com.mycompany.miniestore;
import java.util.ArrayList;
import java.util.List;
public class Store {
    private final List<Product> products;
    public Store() {
        this.products = new ArrayList<>();
        techProducts(); 
        schoolProducts();
    }
    private void techProducts() {
        products.add(new Product(101, "Mechanical Keyboard", 150000.00, 10));
        products.add(new Product(102, "Wireless Mouse", 90000.00, 15));
        products.add(new Product(103, "Gaming Headset", 125000.00, 8));
        products.add(new Product(104, "USB-C Hub", 25000.00, 20));
        products.add(new Product(105, "Mousepad XXL", 15000.00, 25));
        products.add(new Product(106, "Deskmat L", 30000.00, 25));
        products.add(new Product(107, "Monitor", 500000.00, 5));
    }
    private void schoolProducts(){
        products.add(new Product(201, "Pack of book", 25000.00,50));
        products.add(new Product(202, "Pack of pencil", 15000.00,50));
        products.add(new Product(203, "Pack of pen", 20000.00,50));
        products.add(new Product(204, "Ruler", 5000.00,50));
        products.add(new Product(205, "Bag", 250000.00,10));
    }
    
    public void displayCatalog() {
        System.out.println("\n==========================================");
        System.out.println("              STORE CATALOG               ");
        System.out.println("==========================================");
        System.out.printf("%-4s | %-22s | %-8s | %-5s\n", "ID", "Nama Produk", "Harga", "Stok");
        System.out.println("------------------------------------------");
        for (Product p : products) {
            // Mencetak tiap produk dengan format rapi (rata kiri/kanan)
            System.out.printf("%-4d | %-22s | Rp.%-10.2f- | %-5d\n", p.id, p.nama, p.harga, p.stock);
        }
        System.out.println("==========================================");
    }
    
    public Product findById(int id) {
        for (Product p : products) {
            if (p.id == id) {
                return p; // Mengembalikan objek produk jika ID-nya cocok
            }
        }
        return null; // Jika tidak ada produk yang cocok dengan ID tersebut
    }
    
    public void searchIdProduct(int id) {
        boolean found = false;
        System.out.println("\n--- Search Results for \"" + id + "\" ---");
        for (Product p : products) {
            // Mengubah ke huruf kecil (.toLowerCase()) agar pencarian tidak sensitif huruf besar/kecil
            if (p.id == id) {
                System.out.printf("ID: %d | %s - Rp.%.2f- (Stock: %d)\n", p.id, p.nama, p.harga, p.stock);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Produk tidak ditemukan.");
        }
        System.out.println("---------------------------------------------");
    }
    
    public void searchProduct(String keyword) {
        boolean found = false;
        System.out.println("\n--- Search Results for ID \"" + keyword + "\" ---");
        for (Product p : products) {
            // Mengubah ke huruf kecil (.toLowerCase()) agar pencarian tidak sensitif huruf besar/kecil
            if (p.nama.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("ID: %d | %s - Rp.%.2f- (Stock: %d)\n", p.id, p.nama, p.harga, p.stock);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Produk tidak ditemukan.");
        }
        System.out.println("---------------------------------------------");
    }

    // Getter untuk mengambil data list produk
    public List<Product> getProducts() {
        return products;
    }
}
