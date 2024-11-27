CREATE TABLE CreditVoucher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    issuedAt DATETIME NOT NULL,
    expiryDate DATETIME NOT NULL,
    customerEmail VARCHAR(255) NOT NULL,
    code VARCHAR(25) NOT NULL,
    ticketId BIGINT NOT NULL,
    CONSTRAINT fk_ticket_credit_voucher FOREIGN KEY (ticketId) REFERENCES Ticket(id) ON DELETE CASCADE
);
