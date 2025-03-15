# Super Mercado [![maven](https://github.com/abnerjosefelixbarbosa/super-mercado/actions/workflows/maven.yml/badge.svg)](https://github.com/abnerjosefelixbarbosa/super-mercado/actions/workflows/maven.yml)

## Sobre

Sistema de automação de compras.

## Modelo

![Image](https://github.com/user-attachments/assets/5151d80a-1b9e-4128-a7a3-1ee42753b30e)

# Recursos Do Projeto

## Backend

- Spring Framework
- Java
- JPA/Hibernate
- H2 Database
- PostgreSQL
- Lombok
- MVC
- SOLID
- Open API/Swagger

# Fucionalidades

## Produto

- Registrar Produto
- Atualizar Produto pelo ID
- Listar Produtos
- Procurar Produto pelo Código de Barra

## Compra

- Registrar Compra

# Requsições

## Produto

### Registrar Produto

```json
POST
/api/v1/products/register-product

{
  "barcode": "",
  "description": "",
  "price": 0
}
```

### Atualizar Produto pelo Id

```json
PUT
/api/v1/products/update-product-by-id?id=a

{
  "barcode": "",
  "description": "",
  "price": 0
}
```

### Listar Produtos

```json
GET
/api/v1/products/list-products?page=0&size=20
```

### Procurar Produto pelo Código de Barra

```json
GET
/api/v1/products/search-product-by-barcode?barcode=1
```

## Compra

### Registrar Comprar

```json
POST
/api/v1/buys/register-buy

{
  "customerDocment": "string",
  "productItemRequestDTOs": [
    {
      "barcode": "",
      "description": "",
      "price": 0,
      "amount": 0
    }
  ]
}
```

# Execução Do Projeto

- Copie e execute repositório em uma IDE.
- Acesse [a docmentação da API](http://localhost:8080/swagger-ui/index.html) ou use outra plataforma para testa a API.
- Utilize também a API em produção no [link](https://super-mercado-production.up.railway.app/swagger-ui/index.html). 

```bash
# clone repository
git clone https://github.com/abnerjosefelixbarbosa/challenge-back-end-hit.git
```

# Autor

Abner José Felix Barbosa

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/abner-jose-feliz-barbosa/)
