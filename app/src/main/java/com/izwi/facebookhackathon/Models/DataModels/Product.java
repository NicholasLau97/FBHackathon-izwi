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
public class Product {
    private String  name, price,image, description, color,category,gender, target_user,function;

    public Product() {
    }

    public Product(String name, String price, String image, String description, String color, String category, String gender, String target_user, String function) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.color = color;
        this.category = category;
        this.gender = gender;
        this.target_user = target_user;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTarget_user() {
        return target_user;
    }

    public void setTarget_user(String target_user) {
        this.target_user = target_user;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSearchTag(){
        String result = "";

        result = category+", "+gender+", "+color+", "+target_user+", "+function;

        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", gender='" + gender + '\'' +
                ", target_user='" + target_user + '\'' +
                '}';
    }
}
