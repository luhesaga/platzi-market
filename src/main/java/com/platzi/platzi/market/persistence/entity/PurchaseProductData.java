package com.platzi.platzi.market.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compras_productos")
public class PurchaseProductData {

    @EmbeddedId
    private PurchaseProductPK id;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "total")
    private double total;

    @Column(name = "estado")
    private Boolean status;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private PurchaseData purchaseData;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductData productData;

    public PurchaseProductPK getId() {
        return id;
    }

    public void setId(PurchaseProductPK id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PurchaseData getPurchaseData() {
        return purchaseData;
    }

    public void setPurchaseData(PurchaseData purchaseData) {
        this.purchaseData = purchaseData;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }
}
