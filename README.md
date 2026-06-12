# LYNN — Plataforma de Inteligência Conversacional B2B
### Ecossistema TOTVS — Modelo Task as a Service (TaaS)

![Java Version](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![DDD](https://img.shields.io/badge/Architecture-DDD%20%2F%20OOP-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-brightgreen?style=for-the-badge)

---

## 📋 Sumário

1. [Descritivo do Projeto](#-descritivo-do-projeto)
   - [Contexto e Problema](#contexto-e-problema)
   - [Objetivo da Solução](#objetivo-da-solução)
   - [Funcionalidades Implementadas](#funcionalidades-implementadas)
2. [Arquitetura e Justificativa Técnica](#-arquitetura-e-justificativa-tecnica)
   - [Padrões de Orientação a Objetos Aplicados](#padrões-de-orientação-a-objetos-aplicados)
3. [Modelagem de Domínio (DDD)](#-modelagem-de-dominio-ddd)
   - [Estrutura de Pacotes](#estrutura-de-pacotes)
   - [Enumerações](#enumerações)
   - [Classes de Modelo](#classes-de-modelo)
   - [Classes de Serviço](#classes-de-serviço)
   - [Relacionamentos e Associações](#relacionamentos-e-associações)
4. [Diagrama de Classes UML](#-diagrama-de-classes-uml)
5. [Como Executar a Aplicação](#-como-executar-a-aplicação)
6. [Integrantes do Grupo](#-integrantes-do-grupo)

---

## 🚀 Descritivo do Projeto

### Contexto e Problema
No ecossistema corporativo B2B, milhares de reuniões comerciais, de suporte e de implantação são realizadas diariamente entre equipes internas da **TOTVS** e seus clientes. Essas interações geram um volume massivo de informações estratégicas — dores operacionais, menções a concorrentes, relatos de instabilidades técnicas e oportunidades de expansão de contrato.

Na prática, essas informações valiosas ficam "aprisionadas" em transcrições de texto não estruturadas e não são aproveitadas de forma sistemática. A ausência de um mecanismo inteligente para processar e classificar essas informações representa uma perda significativa de receita potencial e uma vulnerabilidade na retenção de clientes, pois sinais críticos de *churn* e insatisfação passam despercebidos pelas lideranças estratégicas.

### Objetivo da Solução
A plataforma **LYNN** foi concebida como um motor de inteligência conversacional que transforma transcrições desestruturadas de reuniões em **insights acionáveis** para os setores Comercial, Sucesso do Cliente (CS) e Qualidade/Produto. 

O sistema realiza as seguintes etapas de processamento:
1. **Enriquecimento Contextual:** Vincula a transcrição bruta a metadados contextuais (quem enviou, qual cliente, qual produto TOTVS está em uso e qual o sistema de origem).
2. **Análise Semântica:** Executa um motor semântico que classifica o conteúdo da transcrição em categorias de insight.
3. **Cálculo de Risco & Recomendação:** Avalia a urgência ou gravidade (nível de risco), extrai o trecho exato da transcrição que originou o insight e gera uma recomendação personalizada de ação direcionada ao produto TOTVS.

### Funcionalidades Implementadas
O sistema oferece uma interface interativa via console:
- **Cadastro de Usuários:** Suporta 3 perfis de personas com atributos específicos:
  - **Vendedor:** Possui território de atendimento e meta de vendas.
  - **Analista CS:** Possui tamanho da carteira de clientes e taxa de retenção.
  - **Operador de Backoffice:** Possui módulo ERP associado e nível de acesso.
- **Cadastro de Clientes:** Registra razão social, CNPJ, segmento de atuação e o produto TOTVS utilizado.
- **Envio e Análise de Transcrições:** Permite colar uma transcrição, associando-a a um usuário e a um cliente, disparando o processamento semântico instantaneamente.
- **Listagem de Insights:** Apresenta detalhadamente todos os insights gerados (tipo, risco, descrição, trecho original e recomendação de ação).
- **Filtro de Insights:** Permite segmentar e listar apenas os insights de uma categoria específica (Upsell, Cross-sell, Churn, Bug ou Fricção UX).
- **Carga de Dados de Exemplo:** Inicializa o sistema com dados fictícios para facilitar a demonstração imediata do fluxo completo (*end-to-end*).

---

## 🛠 Arquitetura e Justificativa Técnica

O projeto foi construído utilizando **Java 21** puro, sem o uso de frameworks externos (como Spring Boot ou Hibernate), para demonstrar o domínio dos fundamentos da Programação Orientada a Objetos (POO) aplicados no contexto do **Domain Driven Design (DDD)**.

### Padrões de Orientação a Objetos Aplicados
- **Herança e Polimorfismo:** A classe abstrata `Usuario` serve como base comum para as subclasses concretas `Vendedor`, `AnalistaCS` e `OperadorBackoffice`. A lógica de exibição é polimórfica através da implementação especializada do método `resumo()`.
- **Encapsulamento:** Todos os atributos das classes de domínio possuem visibilidade restrita e métodos acessores (*getters* e *setters*), garantindo a integridade dos dados.
- **Composição e Associação:** Transcrições são compostas por objetos de `Metadado`, que por sua vez associam `Usuario` e `Cliente`, mantendo a integridade referencial do domínio.
- **Pipeline de NLP Simplificado:** A análise semântica simula o comportamento de um processador de linguagem natural (NLP). O `MotorAnalise` utiliza um mapa interno de palavras-chave ponderadas para identificar padrões semânticos, determinar o tipo de insight, calcular o nível de risco e extrair o trecho de maior relevância na transcrição.

---

## 🎯 Modelagem de Domínio (DDD)

### Estrutura de Pacotes
A separação de responsabilidades segue a arquitetura de camadas de domínio limpa:
```text
com.lynn.totvs
│
├── Main.java             # Interface de linha de comando (Console Interativo)
│
├── enums                 # Constantes e tipos de dados do domínio
│   ├── NivelRisco.java
│   ├── ProdutoTotvs.java
│   └── TipoInsight.java
│
├── model                 # Entidades e agregados (Representação do Negócio)
│   ├── Usuario.java (Abstrata)
│   ├── Vendedor.java
│   ├── AnalistaCS.java
│   ├── OperadorBackoffice.java
│   ├── Cliente.java
│   ├── Metadado.java
│   ├── Transcricao.java
│   └── Insight.java
│
└── service               # Serviços de domínio (Processamento e Regras de Negócio)
    ├── MotorAnalise.java
    └── GeradorInsight.java
```

### Enumerações
| Enum | Valores | Propósito |
| :--- | :--- | :--- |
| **NivelRisco** | `BAIXO`, `MEDIO`, `ALTO`, `CRITICO` | Classifica a severidade e urgência de um insight para priorização de ações. |
| **ProdutoTotvs** | `PROTHEUS`, `RM`, `FLUIG`, `RD_STATION`, `EXACT_SPOTTER` | Representa as soluções do ecossistema TOTVS vinculadas aos clientes e às oportunidades. |
| **TipoInsight** | `UPSELL`, `CROSS_SELL`, `CHURN`, `BUG_SISTEMA`, `FRICCAO_UX` | Categoriza a natureza semântica da informação extraída da reunião. |

### Classes de Modelo
| Classe | Tipo | Atributos Principais | Descrição |
| :--- | :--- | :--- | :--- |
| **Usuario** | Abstrata | `id`, `nome`, `email` | Classe base para os perfis do sistema. Define o método abstrato `getTipoUsuario()` e o polimórfico `resumo()`. |
| **Vendedor** | Concreta | `territorioAtendimento`, `metaVendasMensal` | Persona de vendas. Atua na prospecção de negócios e utiliza integradores como o RD Station. |
| **AnalistaCS** | Concreta | `carteiraClientes`, `taxaRetencao` | Persona de Sucesso do Cliente. Focado na retenção e em reuniões periódicas. |
| **OperadorBackoffice** | Concreta | `moduloERP`, `nivelAcesso` | Persona operacional. Utiliza soluções ERP (Protheus/RM) e atua na retaguarda. |
| **Cliente** | Concreta | `id`, `razaoSocial`, `cnpj`, `segmento`, `produtoAtual` | Representa a empresa parceira B2B vinculada ao ecossistema TOTVS. |
| **Metadado** | Concreta | `id`, `usuarioLogado`, `clienteVisualizado`, `produtoContexto`, `sistemaOrigem` | Reúne informações contextuais da interação para enriquecer a transcrição. |
| **Transcricao** | Concreta | `id`, `textoOriginal`, `metadado`, `dataEnvio`, `status` | O registro bruto da conversa, que passa pelo ciclo de estados `AGUARDANDO_ANALISE` $\rightarrow$ `EM_ANALISE` $\rightarrow$ `CONCLUIDO`. |
| **Insight** | Concreta | `id`, `tipo`, `nivelRisco`, `descricao`, `trechoOriginal`, `oportunidade` | O resultado analítico consolidado do processamento de uma transcrição. |

### Classes de Serviço
| Classe | Métodos Principais | Descrição |
| :--- | :--- | :--- |
| **MotorAnalise** | `detectarTipo()`, `calcularRisco()`, `extrairTrecho()` | Núcleo semântico da aplicação. Classifica os temas com base em dicionários de termos ponderados, define a prioridade de risco e localiza as sentenças causadoras. |
| **GeradorInsight** | `processar()`, `filtrarPorTipo()`, `listarTodos()` | Orquestrador do fluxo. Recebe a transcrição, invoca a lógica de análise do `MotorAnalise`, constrói os objetos de `Insight` com recomendações e persiste os resultados na memória. |

### Relacionamentos e Associações
- **Herança (`Usuario ➔ Vendedor / AnalistaCS / OperadorBackoffice`)**: As classes de personas estendem `Usuario` para herdar atributos de identificação e implementar especializações de negócio.
- **Composição (`Transcricao ➔ Metadado`)**: Uma transcrição obrigatoriamente contém um objeto de metadado que fornece contexto de autoria e origem.
- **Associação (`Metadado ➔ Usuario, Cliente`)**: O metadado mantém referências diretas ao operador que enviou a transcrição e ao cliente envolvido.
- **Rastreabilidade (`Insight ➔ Transcricao`)**: Cada insight gerado guarda referência para a transcrição de origem, permitindo auditar o resultado gerado.
- **Delegação (`GeradorInsight ➔ MotorAnalise`)**: O orquestrador delega o processamento algorítmico complexo ao motor de análise semântica.

---

## 📊 Diagrama de Classes UML

O diagrama de classes UML representa a modelagem técnica do sistema com seus atributos, métodos e multiplicidades:

Você pode visualizar a imagem do diagrama no repositório:
👉 **[Visualizar Diagrama de Classes (Alta Resolução)](file:///c:/Users/USER/lynn-totvs-mvp/uml_alta_resolucao.png)**

---

## 🏃 Como Executar a Aplicação

### Pré-requisitos
- **Java Development Kit (JDK) 21** ou superior instalado e configurado nas variáveis de ambiente.
- **Apache Maven** instalado.

### Passo a Passo para Execução

1. **Abra o terminal** no diretório do projeto backend:
   ```bash
   cd backend-java
   ```

2. **Compile e empacote o projeto** gerando o artefato executável (`.jar`):
   ```bash
   mvn clean package
   ```

3. **Execute a aplicação** compilada:
   ```bash
   java -jar target/lynn-totvs-backend-1.0.0.jar
   ```

4. **Navegue no Console Interativo:**
   - Ao iniciar, o sistema carrega dados prévios (como uma transcrição que indica a necessidade de automação de impostos no produto RM, classificada como **Bug de Sistema** / **Risco Alto**).
   - Use o menu numérico (`1` a `6` e `0`) para experimentar o cadastro de novos usuários, novos clientes, submeter textos livres de reuniões, visualizar e filtrar os insights obtidos.

---

## 👥 Integrantes do Grupo
* **Caio N. Battista** — RM: 561383
* **Lucas Cavalcante** — RM: 562857
* **Matheus Rodrigues** — RM: 561689
* **Manoah Leão** — RM: 563713
* **Jean Pierre** — RM: 566534

*São Paulo — Junho de 2026*