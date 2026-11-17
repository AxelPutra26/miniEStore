package com.mycompany.miniestore;

import java.util.Scanner;

public class MiniEStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("       WELCOME TO MINI STORE SYSTEM       ");
        System.out.println("==========================================");

        System.out.print("Masukkan Nama Anda: ");
        String namaBuyer = scanner.nextLine();

        System.out.print("Masukkan Alamat Anda: ");
        String alamat = scanner.nextLine();
        
        double saldoAwal;
        
        do{
        System.out.print("Masukkan Saldo Awal Anda (Rp): ");
        saldoAwal = scanner.nextDouble();
        }while(saldoAwal<0);
        
        Buyer buyer = new Buyer(namaBuyer, alamat, saldoAwal);
        Store store = new Store();

        int pilihan = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("                 MAIN MENU                ");
            System.out.println("==========================================");
            System.out.println("Halo, " + buyer.getnama() + " | Saldo: Rp " + buyer.getsaldo());
            System.out.println("1. Lihat Katalog Toko");
            System.out.println("2. Cari Produk");
            System.out.println("3. Tambah ke Keranjang ");
            System.out.println("4. Lihat Keranjang Belanja");
            System.out.println("5. Kurangi / Hapus Item di Keranjang"); 
            System.out.println("6. Checkout / Bayar");
            System.out.println("7. Status Pesanan");
            System.out.println("8. Top-up Saldo");
            System.out.println("9. Keluar (Exit)");
            System.out.print("Pilih menu (1-8): ");
            
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    store.displayCatalog();
                    break;

                case 2:
                    scanner.nextLine(); 
                    System.out.print("1. Dengan Nama Produk\n");
                    System.out.print("2. Dengan ID Produk \n");
                    System.out.print("Masukkan metode pencarian: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice ==1){
                    System.out.print("Masukkan nama produk yang dicari: ");
                    String keyword = scanner.nextLine();
                    store.searchProduct(keyword);
                    break;
                    }else if (choice==2){
                    System.out.print("Masukkan id produk yang dicari: ");
                    int id = scanner.nextInt();
                    store.searchIdProduct(id);
                    break;
                    }
                    
                case 3:
                    char lanjutBelanja;
                    do {
                        store.displayCatalog();
                        System.out.print("Masukkan ID Produk yang ingin dibeli: ");
                        int idProd = scanner.nextInt();
                        
                        Product selectedProduct = store.findById(idProd);
                        if (selectedProduct != null) {
                            System.out.print("Masukkan jumlah (quantity): ");
                            int qty = scanner.nextInt();
                            
                            // Masukkan ke keranjang buyer
                            buyer.getcart().addtocart(selectedProduct, qty);
                        } else {
                            System.out.println("Produk dengan ID tersebut tidak ditemukan!");
                        }

                        System.out.print("\nApakah ada barang lain yang ingin dibeli? (y/t): ");
                        lanjutBelanja = scanner.next().charAt(0);
                    } while (lanjutBelanja == 'y' || lanjutBelanja == 'Y');
                    break;

                case 4:
                    buyer.getcart().viewcart();
                    break;

                case 5:
                    if (buyer.getcart().isempty()) {
                        System.out.println("Keranjang belanja Anda masih kosong.");
                        break;
                    }
                    buyer.getcart().viewcart();
                    System.out.print("Masukkan ID Produk yang ingin dikurangi/dihapus dari keranjang: ");
                    int idRemove = scanner.nextInt();
                    System.out.print("Masukkan jumlah yang ingin dikurangi: ");
                    int qtyRemove = scanner.nextInt();
                    
                    buyer.getcart().removeItem(idRemove, qtyRemove);
                    break;

                case 6:
                    if (buyer.getcart().isempty()) {
                        System.out.println("Keranjang belanja masih kosong, tidak dapat melakukan checkout.");
                        break;
                    }
                    
                    buyer.getcart().viewcart();
                    System.out.print("\nApakah Anda yakin ingin melanjutkan pembayaran (Checkout)? (y/t): ");
                    char konfirmasi = scanner.next().charAt(0);
                    
                    if (konfirmasi == 'y' || konfirmasi == 'Y') {
                        buyer.checkout();
                        int a=0;
                        a++;
                        buyer.printOrderReceipt(a);
                    } else {
                        System.out.println("Checkout dibatalkan.");
                    }
                    break;

                case 7:
                    buyer.viewOrderHistory();
                // Menawarkan cetak struk detail jika riwayat tidak kosong
                System.out.print("Ingin mencetak struk detail untuk Order ID tertentu? (y/t): ");
                char cetakStruk = scanner.next().charAt(0);
                if (cetakStruk == 'y' || cetakStruk == 'Y') {
                    System.out.print("Masukkan nomor Order ID (contoh: 1): ");
                    int searchOrderId = scanner.nextInt();
                    buyer.printOrderReceipt(searchOrderId);
                }
                break;
                case 8:
                    System.out.print("Masukkan jumlah nominal top-up: ");
                    double topUpAmount = scanner.nextDouble();
                    buyer.topUp(topUpAmount);
                    break;

                case 9:
                    System.out.println("\nTerima kasih telah berbelanja di Mini Store! Sampai jumpa.");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu 1-8.");
            }

        } while (pilihan != 8);

        scanner.close();
    }
}