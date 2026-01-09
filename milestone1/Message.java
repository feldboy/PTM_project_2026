package test;

import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    // Constructor from String
    public Message(String text) {
        this.asText = text;
        this.data = text.getBytes();
        this.date = new Date();
        
        // Try to parse as double, use NaN if fails
        double tempDouble;
        try {
            tempDouble = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            tempDouble = Double.NaN;
        }
        this.asDouble = tempDouble;
    }

    // Constructor from double
    public Message(double value) {
        this(String.valueOf(value));
    }

    // Constructor from byte array
    public Message(byte[] data) {
        this(new String(data));
    }
}
