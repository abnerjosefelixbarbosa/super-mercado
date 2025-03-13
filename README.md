# Super Mercado [![maven](https://github.com/abnerjosefelixbarbosa/super-mercado/actions/workflows/maven.yml/badge.svg)](https://github.com/abnerjosefelixbarbosa/super-mercado/actions/workflows/maven.yml)

## Sobre

Sistema de automação de compras.

## Modelo

![Image](https://github.com/user-attachments/assets/82c99321-02c1-432c-a613-f29a64bb75ef)

```mermaid
classDiagram
    class Buy {
        - String id
        - LocalDate date
        - LocalTime time
        - String customerDocument
        - BigDecimal value
        - BuyProduct[] buyProducts 
    }
    
    class Product {
        - String id
        - String barcode
        - String description
        - BigDecimal price
        - BuyProduct[] buyProducts 
    }
    
    class BuyProduct {
        - BuyProductId buyProductId 
        - Buy buy
        - Product product
        - Integer amount
    }
    
    class BuyProductId {
        - String buyId
        - String productId
    }
    
    Buy "1" -- "*" BuyProduct
    Product "1" -- "*" BuyProduct
    BuyProductId  -- BuyProduct 
```
