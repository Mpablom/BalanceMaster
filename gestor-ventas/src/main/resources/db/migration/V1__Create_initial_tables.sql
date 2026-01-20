-- Create category table
CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    margin_percentage NUMERIC(5,2) NOT NULL CHECK (margin_percentage >= 0 AND margin_percentage <= 100)
);

-- Create supplier table
CREATE TABLE supplier (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create supplier_account table
CREATE TABLE supplier_account (
    id BIGSERIAL PRIMARY KEY,
    balance NUMERIC(15,2) NOT NULL DEFAULT 0.00 CHECK (balance >= 0),
    credit_limit NUMERIC(15,2) NOT NULL DEFAULT 0.00 CHECK (credit_limit >= 0),
    supplier_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id) ON DELETE CASCADE
);

-- Create customer table
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255),
    credit_limit NUMERIC(15,2) NOT NULL DEFAULT 0.00 CHECK (credit_limit >= 0),
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create customer_account table
CREATE TABLE customer_account (
    id BIGSERIAL PRIMARY KEY,
    balance NUMERIC(15,2) NOT NULL DEFAULT 0.00 CHECK (balance >= 0),
    credit_limit NUMERIC(15,2) NOT NULL DEFAULT 0.00 CHECK (credit_limit >= 0),
    customer_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Create product table
CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    barcode VARCHAR(50) UNIQUE,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    purchase_price NUMERIC(12,2) NOT NULL CHECK (purchase_price >= 0),
    min_stock INTEGER NOT NULL CHECK (min_stock >= 0),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Create inventory table
CREATE TABLE inventory (
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    location VARCHAR(255),
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    product_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- Create transaction table (parent table)
CREATE TABLE transaction (
    id VARCHAR(50) PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(15,2) NOT NULL CHECK (total >= 0)
);

-- Create sale table
CREATE TABLE sale (
    id VARCHAR(50) PRIMARY KEY,
    customer_id BIGINT,
    payment_method VARCHAR(20) NOT NULL,
    amount_paid NUMERIC(15,2) NOT NULL CHECK (amount_paid >= 0),
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id) REFERENCES transaction(id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Create purchase table
CREATE TABLE purchase (
    id VARCHAR(50) PRIMARY KEY,
    supplier_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id) REFERENCES transaction(id) ON DELETE CASCADE,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

-- Create item_transaction table
CREATE TABLE item_transaction (
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(12,2) NOT NULL CHECK (unit_price >= 0),
    product_id BIGINT NOT NULL,
    transaction_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (transaction_id) REFERENCES transaction(id) ON DELETE CASCADE
);

-- Create movement table
CREATE TABLE movement (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(200) NOT NULL,
    amount NUMERIC(15,2) NOT NULL CHECK (amount != 0),
    movement_type VARCHAR(20) NOT NULL,
    customer_account_id BIGINT,
    supplier_account_id BIGINT,
    FOREIGN KEY (customer_account_id) REFERENCES customer_account(id),
    FOREIGN KEY (supplier_account_id) REFERENCES supplier_account(id),
    CHECK (customer_account_id IS NOT NULL OR supplier_account_id IS NOT NULL)
);

-- Create indexes for better performance
CREATE INDEX idx_product_category_id ON product(category_id);
CREATE INDEX idx_product_deleted ON product(deleted);
CREATE INDEX idx_sale_customer_id ON sale(customer_id);
CREATE INDEX idx_sale_date ON sale(date);
CREATE INDEX idx_purchase_supplier_id ON purchase(supplier_id);
CREATE INDEX idx_purchase_date ON purchase(date);
CREATE INDEX idx_item_transaction_product_id ON item_transaction(product_id);
CREATE INDEX idx_item_transaction_transaction_id ON item_transaction(transaction_id);
CREATE INDEX idx_movement_customer_account_id ON movement(customer_account_id);
CREATE INDEX idx_movement_supplier_account_id ON movement(supplier_account_id);
CREATE INDEX idx_movement_date ON movement(date);
CREATE INDEX idx_inventory_product_id ON inventory(product_id);

-- Insert default category
INSERT INTO category (name, description, margin_percentage) VALUES 
('General', 'General category for uncategorized products', 30.00);