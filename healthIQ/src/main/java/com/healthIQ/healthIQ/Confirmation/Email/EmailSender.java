package com.healthIQ.healthIQ.Confirmation.Email;

public interface EmailSender {
    void send(String to, String email);
}
