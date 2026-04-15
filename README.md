# 🧪 Testes Automatizados no Backend com Java e Spring Boot

Este repositório consolida o aprendizado prático em **testes automatizados no backend**, evoluindo desde fundamentos com Java puro até a aplicação em uma arquitetura real utilizando **Spring Boot**.

Mais do que validar código, o foco está em compreender **como projetar software testável**, aplicar boas práticas e garantir qualidade de forma consistente — competências essenciais no desenvolvimento backend profissional.

---

# 📚 Estrutura do Repositório

## 🔹 `aula-junit` — Fundamentos com Java puro

Projeto voltado para o entendimento dos conceitos essenciais de testes unitários com **JUnit 5**, sem abstrações de frameworks.

Foco em:

* Testar métodos de forma isolada
* Escrever testes previsíveis e replicáveis
* Validar regras de negócio
* Trabalhar com cenários positivos e negativos

---

## 🔹 `dscatalog` — Testes em aplicação Spring Boot

Projeto baseado em uma API REST com arquitetura em camadas.

Foco em:

* Testes de Repository, Service e Controller
* Testes de integração
* Uso de Mockito e MockMvc
* Simulação de dependências

---

# 🧠 Fundamentos de Testes Automatizados

## 🔍 Tipos de Testes

### ✔️ Testes Unitários

Validam unidades isoladas de código (geralmente métodos), sem dependências externas.

* Não acessam banco, API ou arquivos
* São rápidos
* Validam regras de negócio

---

### ✔️ Testes de Integração

Validam a comunicação entre componentes da aplicação.

Exemplo:

* Controller → Service → Repository

---

### ✔️ Testes Funcionais

Validam o comportamento do sistema do ponto de vista do usuário.

---

## 🚀 Benefícios dos Testes

* Detectar erros rapidamente
* Servir como documentação viva
* Reduzir custo de manutenção
* Melhorar o design do código
* Aumentar a confiabilidade

---

# 🔄 TDD — Test Driven Development

O TDD é uma abordagem onde o desenvolvimento é guiado pelos testes.

## 🔁 Ciclo básico (explicado na prática)

### 🔴 1. Escrever o teste (e ele falha)

Você começa definindo o comportamento esperado antes de implementar.

```java
@Test
void withdrawShouldDecreaseBalance() {
    Account acc = new Account(100.0);

    acc.withdraw(50.0);

    Assertions.assertEquals(50.0, acc.getBalance());
}
```

👉 Nesse momento, o teste falha porque o comportamento ainda não existe (ou está incorreto).

---

### 🟢 2. Implementar o mínimo necessário

Agora você escreve apenas o código suficiente para fazer o teste passar:

```java
public void withdraw(double amount) {
    balance -= amount;
}
```

👉 Sem otimizações, apenas o necessário.

---

### 🔵 3. Refatorar com segurança

Agora você melhora o código:

* validações
* organização
* legibilidade

👉 Com a segurança de que os testes vão indicar se algo quebrou.

---

## 🎯 Resumo

* Primeiro define o comportamento
* Depois implementa
* Por fim melhora o código com segurança

---

# 🧩 Boas Práticas e Padrões

## 📌 Nomenclatura de testes

Um teste deve ser legível como uma frase.

### 🧱 Padrão:

```
<AÇÃO> should <EFEITO> [when <CENÁRIO>]
```

### 🔍 Exemplo:

```java
withdrawShouldThrowExceptionWhenInsufficientBalance
```

### 💡 Interpretação:

* Ação → `withdraw`
* Efeito → `shouldThrowException`
* Cenário → `whenInsufficientBalance`

👉 Tradução:

> “Saque deve lançar exceção quando saldo for insuficiente”

✔️ O nome já explica o comportamento sem precisar abrir o código.

---

## 🧱 Padrão AAA

Estrutura padrão para organizar testes:

* **Arrange** → preparar dados
* **Act** → executar ação
* **Assert** → validar resultado

```java
@Test
void depositShouldIncreaseBalance() {
    // Arrange
    Account acc = new Account(0.0);

    // Act
    acc.deposit(100.0);

    // Assert
    Assertions.assertEquals(100.0, acc.getBalance());
}
```

---

## 🔒 Isolamento

* Um teste não depende de outro
* Não depende de ordem de execução
* Testa apenas um cenário

---

## 🎯 Cenário único

Cada teste deve validar apenas um comportamento.

❌ Evitar:

* múltiplos cenários no mesmo teste
* lógica complexa dentro do teste

---

## 🔁 Previsibilidade e Replicabilidade

Um teste deve sempre produzir o mesmo resultado para os mesmos dados.

Evitar:

* uso de `LocalDate.now()`
* valores aleatórios
* dependência de ambiente externo

---

# 🧠 SOLID — Inversão de Dependência (explicado na prática)

Para escrever bons testes unitários, é essencial conseguir **isolar a classe que está sendo testada**.

---

## ❌ Problema (alto acoplamento)

```java
public class OrderService {

    private PaymentService paymentService = new PaymentService();

    public void process() {
        paymentService.pay();
    }
}
```

👉 Problema:

* A classe cria sua própria dependência
* Não conseguimos controlar o comportamento no teste

---

## ✅ Solução (Inversão de Dependência)

```java
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void process() {
        paymentService.pay();
    }
}
```

---

## 🧪 Agora no teste:

```java
@Mock
private PaymentService paymentService;

@InjectMocks
private OrderService orderService;
```

```java
when(paymentService.pay()).thenReturn(true);
```

---

## 🎯 Resultado

* Classe desacoplada
* Teste isolado
* Controle total dos cenários

---

## 💡 Tradução prática do princípio

> “Não deixe a classe criar suas dependências — receba elas de fora.”

---

# ⚙️ JUnit 5

Framework principal para testes.

## 🔹 Recursos utilizados

```java
@Test
void example() {
    Assertions.assertEquals(expected, actual);
}
```

```java
Assertions.assertThrows(Exception.class, () -> {
    method();
});
```

---

# 🧱 Testes em Aplicações Spring Boot

## 🗄️ Repository

* Testa acesso ao banco
* Usa `@DataJpaTest`
* Rollback automático

---

## ⚙️ Service

* Teste unitário
* Uso de Mockito
* Isolamento da regra de negócio

---

## 🌐 Controller

* Testes com `MockMvc`
* Não sobe servidor
* Valida HTTP (status e resposta)

---

## 🔗 Integração

* Usa `@SpringBootTest`
* Testa fluxo completo

---

# 🎭 Mockito

Utilizado para simular dependências.

## 🔹 Exemplo

```java
when(service.findById(1L)).thenReturn(product);
```

```java
verify(repository).save(entity);
```

---

## 🆚 @Mock vs @MockBean

| Situação   | Uso         |
| ---------- | ----------- |
| Sem Spring | `@Mock`     |
| Com Spring | `@MockBean` |

---

# 🧱 Fixtures

Evita repetição nos testes:

* `@BeforeEach`
* `@AfterEach`

---

# 🛠️ Tecnologias Utilizadas

* Java
* JUnit 5
* Spring Boot
* Maven
* Mockito
* MockMvc

---

# 🚀 Aplicação no Mercado

Os conceitos aplicados aqui refletem práticas reais de desenvolvimento backend:

* Escrita de testes confiáveis
* Isolamento de dependências
* Uso de mocks
* Testes em múltiplas camadas
* Garantia de qualidade contínua

👉 Testes não são apenas validação — são parte essencial do design da aplicação.

---

# 🎯 Conclusão

Este projeto representa a evolução de:

* Código funcional → código testável
* Testes simples → testes estruturados
* Conhecimento teórico → aplicação prática

---

## 👨‍💻 Autor

**Albert Silva de Jesus**  
Desenvolvedor Backend Java | Spring Boot

---

### 📬  Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/albert-backend-java-spring-boot/)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge\&logo=gmail\&logoColor=white)](mailto:albertinesilva.17@gmail.com?subject=Contato%20profissional&body=Olá%20Albert,%0D%0A%0D%0AEncontrei%20seu%20perfil/projeto%20e%20gostaria%20de%20entrar%20em%20contato.%0D%0A%0D%0APoderia%20me%20retornar%20quando%20possível?%0D%0A%0D%0AAtenciosamente,)
