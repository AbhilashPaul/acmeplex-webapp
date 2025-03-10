CREATE TABLE PaymentReceipt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transactionId VARCHAR(255) NOT NULL UNIQUE,
    status ENUM('SUCCESS', 'FAILED', 'PENDING') NOT NULL
);