/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ToDoReminderApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoReminderApp::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("To-Do Reminder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(7, 1, 10, 10));

        JTextField messageField = new JTextField();
        JLabel messageLabel = new JLabel("Enter Reminder Message:");

        JCheckBox emailCheck = new JCheckBox("Email");
        JCheckBox smsCheck = new JCheckBox("SMS");
        JCheckBox ringCheck = new JCheckBox("Ring Notification");

        JButton setReminderButton = new JButton("Set Reminder");

        frame.add(messageLabel);
        frame.add(messageField);
        frame.add(emailCheck);
        frame.add(smsCheck);
        frame.add(ringCheck);
        frame.add(setReminderButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane);

        ReminderService reminderService = new ReminderService();

        setReminderButton.addActionListener(e -> {
            String message = messageField.getText();
            Date currentTime = new Date();

            reminderService.clearObservers();

            if (emailCheck.isSelected()) reminderService.addObserver(new EmailService());
            if (smsCheck.isSelected()) reminderService.addObserver(new SMSService());
            if (ringCheck.isSelected()) reminderService.addObserver(new RingService());

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Message cannot be empty.");
                return;
            }

            if (!emailCheck.isSelected() && !smsCheck.isSelected() && !ringCheck.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Select at least one notification method.");
                return;
            }

            reminderService.triggerReminder(message, currentTime);
            outputArea.append("🔔 Reminder set: " + message + " at " + currentTime + "\n");
        });

        frame.setVisible(true);
    }
}
