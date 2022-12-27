
package model;


public class Auto {
    private int id;
    private String marca;
    private String modelo;
    private String caja;
    private double precio;
    private int puertas;
    private boolean disponible;

    public Auto(String marca, String modelo, String caja, double precio, int puertas, boolean disponible) {
        this.marca = marca;
        this.modelo = modelo;
        this.caja = caja;
        this.precio = precio;
        this.puertas = puertas;
        this.disponible = disponible;
    }

    public Auto(int id, String marca, String modelo, String caja, double precio, int puertas, boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.caja = caja;
        this.precio = precio;
        this.puertas = puertas;
        this.disponible = disponible;
    }

    public Auto() {
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Autos{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", caja=" + caja + ", precio=" + precio + ", puertas=" + puertas + ", disponible=" + disponible + '}';
    }
    
    
}
