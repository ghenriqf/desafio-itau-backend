# Desafio Itaú Backend

Bem-vindo ao repositório de resolução do **Desafio Itaú Backend**. Este desafio propõe a implementação de uma API REST utilizando Java ou Kotlin com Spring Boot, atendendo a requisitos específicos.

---

## Sobre o desafio

O objetivo do desafio é criar uma API REST que:
- Aceita transações e as armazena em memória.
- Calcula estatísticas com base nas transações registradas nos últimos 60 segundos.
- Permite a limpeza de todas as transações armazenadas.

A solução não utiliza bancos de dados ou cache externo; todos os dados são mantidos em memória.

---

## Endpoints da API

### 1. Receber Transações: `POST /transacao`
Recebe transações no seguinte formato:

```json
{
  "valor": 123.45,
  "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

- **Regras de validação**:
  - `valor` deve ser maior ou igual a 0.
  - `dataHora` deve estar no passado e em formato ISO 8601.

- **Respostas**:
  - `201 Created`: Transação aceita.
  - `422 Unprocessable Entity`: Transação inválida (ex.: valor negativo ou data futura).
  - `400 Bad Request`: JSON de requisição inválido.

---

### 2. Limpar Transações: `DELETE /transacao`
Remove todas as transações armazenadas.

- **Resposta**:
  - `200 OK`: Transações apagadas com sucesso.

---

### 3. Calcular Estatísticas: `GET /estatistica`
Calcula estatísticas das transações realizadas nos últimos 60 segundos:

```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
```

- Campos retornados:
  - `count`: Quantidade de transações.
  - `sum`: Soma total dos valores das transações.
  - `avg`: Média dos valores das transações.
  - `min`: Menor valor transacionado.
  - `max`: Maior valor transacionado.

- Valores padrão em caso de ausência de transações: `0`.

- **Resposta**:
  - `200 OK`: Estatísticas calculadas com sucesso.

---

### 4. Verificar Saúde da Aplicação: `GET /actuator/health`
Este endpoint retorna informações sobre a saúde da aplicação. É uma prática recomendada para monitoramento em produção.

- **Resposta**:
  - `200 OK`:
    ```json
    {
      "status": "UP"
    }
    ```
  - `503 Service Unavailable`: Quando a aplicação está indisponível.

---

## Boas Práticas Adotadas

### 1. Uso do Docker
Foi incluído um arquivo `Dockerfile` para facilitar a criação de contêineres da aplicação. Para criar e executar:

- Build da imagem:
  ```bash
  docker build -t desafio-itau-backend .
  ```

- Executar o contêiner:
  ```bash
  docker run -p 8080:8080 desafio-itau-backend
  ```

### 2. Tratamento de Exceções Personalizadas
A aplicação utiliza um mecanismo centralizado de tratamento de exceções para retornar mensagens de erro mais descritivas e amigáveis ao cliente:
- `@ControllerAdvice`: Garante a centralização do tratamento de exceções.
- `.ResponseEntity`: Respostas padronizadas para erros.

Exemplo de resposta para `MethodArgumentNotValidException`:
```json
{
  "error": "Invalid Request",
  "details": "The field 'valor' must be positive."
}
```

### 3. Monitoramento com Spring Actuator
O Spring Actuator foi configurado para expor métricas fundamentais da aplicação, como:
- Health check (`/actuator/health`).
- Métricas detalhadas (`/actuator/metrics`).

Essas informações auxiliam na manutenção e monitoramento da aplicação em produção.

---

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Versão Java**: 17

---

## Como Executar

### Requisitos
- **JDK**  (Java Development Kit) instalado.
- **Maven** ou **Gradle**.

### Instruções

1. Clone este repositório:
```bash
git clone https://github.com/ghenriqf/desafio-itau-backend.git
```

2. Acesse o diretório do projeto:
```bash
cd desafio-itau-backend
```

3. Compile e execute a aplicação:
```bash
./mvnw spring-boot:run
```
ou
```bash
./gradlew bootRun
```

4. Acesse a aplicação em `http://localhost:8080`.

---

Autor: **[ghenriqf](https://github.com/ghenriqf)**