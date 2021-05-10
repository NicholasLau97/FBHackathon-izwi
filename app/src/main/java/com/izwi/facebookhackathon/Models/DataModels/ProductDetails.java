package com.izwi.facebookhackathon.Models.DataModels;
/*
 * Lau Hui Sheng CONFIDENTIAL
 * __________________
 *  Date : 8/5/2021 20:00 MYT
 *  [2021] - [2021] Lau Hui Sheng email: huisheng97.lhs.business@gmail.com
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Lau Hui Sheng and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Lau Hui Sheng
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Lau Hui Sheng.
 */
public class ProductDetails {
    private String id;
    private Product product;

    public ProductDetails(String id, Product product) {
        this.id = id;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
