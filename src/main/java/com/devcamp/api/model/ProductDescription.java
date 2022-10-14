package com.devcamp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "product_descriptions")
public class ProductDescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String inches;
    private String pixels;
    private String memory;
    private String chipset;
    private String weight;
    private String screen;
    private String video;
    private String size;
    private String camera1;
    private String camera2;
    private String pin;
    private String sim;
    private String OS;
    private String wlan;
    private String bluetooth;
    private String gps;
    private String nfc;
    private String sensor;
    private String memoryCard;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductDescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInches() {
        return inches;
    }

    public void setInches(String inches) {
        this.inches = inches;
    }

    public String getPixels() {
        return pixels;
    }

    public void setPixels(String pixels) {
        this.pixels = pixels;
    }

    public String getMemory() {
        return memory;
    }



    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCamera1() {
        return camera1;
    }

    public void setCamera1(String camera1) {
        this.camera1 = camera1;
    }

    public String getCamera2() {
        return camera2;
    }

    public void setCamera2(String camera2) {
        this.camera2 = camera2;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String oS) {
        OS = oS;
    }

    public String getWlan() {
        return wlan;
    }

    public void setWlan(String wlan) {
        this.wlan = wlan;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getMemoryCard() {
        return memoryCard;
    }

    public void setMemoryCard(String memoryCard) {
        this.memoryCard = memoryCard;
    }

    
    
}
