package com.github.rafaelsilvestri.order;

public class FtpWatcher {

    private OrderProcessor orderProcessor;

    public void watch() {
        // read file from ftp
        String content = null;
        // parse file to Order
        Order order = OrderParser.fromTxt(content);
        try {
            order = orderProcessor.processOrder(order);
        } catch (OrderValidationException ex) {
            // send failed result back to FTP
        }
        // send success result back to FTP
    }

}
