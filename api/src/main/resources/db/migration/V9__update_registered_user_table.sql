-- Add the 'annualFeePaid' column as a BOOLEAN with a default value of FALSE
ALTER TABLE RegisteredUser 
ADD annualFeePaid BOOLEAN NOT NULL DEFAULT FALSE;

-- Add the 'annualFeePaidDate' column as a DATETIME, allowing NULL values initially
ALTER TABLE RegisteredUser 
ADD annualFeePaidDate DATETIME NULL;