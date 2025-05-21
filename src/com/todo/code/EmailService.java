/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.reminder;

import java.util.Date;

public class EmailService implements NotificationObserver {
    public void send(String message, Date time) {
        System.out.println("📧 Email sent: " + message + " at " + time);
    }
}
