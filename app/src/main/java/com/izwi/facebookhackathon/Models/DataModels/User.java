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
public class User {
    private String name;
    private String password;
    private String username;
    private boolean intent_search;
    private boolean intent_navigate;

    public User() {
    }

    public User(String name, String password, boolean intent_search, boolean intent_navigate) {
        this.name = name;
        this.password = password;
        this.intent_search = intent_search;
        this.intent_navigate = intent_navigate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIntent_search() {
        return intent_search;
    }

    public void setIntent_search(boolean intent_search) {
        this.intent_search = intent_search;
    }

    public boolean isIntent_navigate() {
        return intent_navigate;
    }

    public void setIntent_navigate(boolean intent_navigate) {
        this.intent_navigate = intent_navigate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", intent_search=" + intent_search +
                ", intent_navigate=" + intent_navigate +
                '}';
    }
}
