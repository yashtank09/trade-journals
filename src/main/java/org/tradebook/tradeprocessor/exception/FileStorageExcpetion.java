package org.tradebook.tradeprocessor.exception;

public class FileStorageExcpetion extends TradeBookException {
    public FileStorageExcpetion(String message) {
        super(message);
    }
    public FileStorageExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
