# super-mercado

## Sobre

Super Mercado

## modelo

```mermaid
classDiagram
    class Buy {
        - String id
        - LocalDate date
        - LocalTime time
        - String customerDocument
        - BigDecimal value
        - BuyProduct[] buyProduct 
    }
    
    class Product {
        - String id
        - String barcode
        - String description
        - BigDecimal price
        - BuyProduct[] buyProduct 
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
