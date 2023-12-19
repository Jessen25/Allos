package com.example.allos;

public class Items {
    public String barcodeID;
    public String itemImage;
    public String itemName;
    public boolean isAllowed;

    public Items(String barcodeID, String itemImage, String itemName, boolean isAllowed) {
        this.barcodeID = barcodeID;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.isAllowed = isAllowed;
    }
}
