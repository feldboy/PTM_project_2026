package test;

import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    // Constructor from byte array
    public Message(byte[] data) {
        this.data = data;
        this.asText = new String(data);
        double d = 0;
        try {
            d = Double.parseDouble(this.asText);
        } catch (Exception e) {
        }
        this.asDouble = d;
        this.date = new Date();
    }

    // Constructor from String
    public Message(String text) {
        this.asText = text;
        this.data = text.getBytes();
        double d = 0;
        try {
            d = Double.parseDouble(text);
        } catch (Exception e) {
        }
        this.asDouble = d;
        this.date = new Date();
    }

    // Constructor from double
    public Message(double value) {
        this.asDouble = value;
        this.asText = String.valueOf(value);
        this.data = this.asText.getBytes();
        this.date = new Date();
    }
}
