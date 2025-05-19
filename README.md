# 🎵 Spotifei - Plataforma de Músicas com Java Swing + PostgreSQL

Sistema de gerenciamento de músicas com interface gráfica feita em Java Swing, conexão com banco de dados PostgreSQL via JDBC e arquitetura organizada em MVC.

---

## 📋 Funcionalidades

- ✅ Cadastro e login de usuários
- 🔍 Busca de músicas por nome
- 🎶 Cadastro de músicas
- 💾 Criação de playlists por usuário
- 📚 Histórico de ações (buscas, curtidas, etc.)
- 📊 Interface gráfica 100% feita em JFrame
- 🧱 Backend com DAO e JDBC para PostgreSQL

---

## 📁 Estrutura do Projeto

```
Spotifei/
├── controller/
├── dao/
├── model/
├── view/
└── Main.java
```

---

## 🛠 Tecnologias Utilizadas

- **Java 8+**
- **Swing (JFrame)**
- **PostgreSQL**
- **JDBC Driver PostgreSQL**
- **NetBeans IDE (recomendado)**

---

## 🧰 Requisitos

- Java JDK 8 ou superior
- PostgreSQL instalado
- NetBeans (ou outra IDE)
- JDBC Driver do PostgreSQL  
  🔗 [Baixar JDBC driver](https://jdbc.postgresql.org/download/postgresql-42.7.3.jar)

---

## 🧑‍💻 Como Rodar o Projeto

### 1. Clone ou extraia o projeto

```bash
# Se estiver no GitHub:
git clone https://github.com/seu-usuario/spotifei.git

# Ou extraia o .zip que recebeu
```

### 2. Configure o banco de dados

- Abra o **pgAdmin** ou outro cliente PostgreSQL
- Crie um banco de dados chamado:

```
spotifei
```

- Execute o script SQL:  
  👉 [`spotifei_postgresql.sql`](spotifei_postgresql.sql)

```sql
-- Exemplo:
CREATE DATABASE spotifei;
\c spotifei
-- Execute o conteúdo do arquivo .sql
```

### 3. Verifique as credenciais do banco

No arquivo `dao/Conexao.java`, atualize se necessário:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/spotifei";
private static final String USUARIO = "postgres";
private static final String SENHA = "123"; // sua senha
```

### 4. Adicione o driver JDBC ao projeto

- Clique com o botão direito no projeto > **Properties**
- Vá em **Libraries** > **Add JAR/Folder**
- Selecione o `postgresql-42.7.3.jar`

---

## 🖼 Telas implementadas

- Tela de Login
- Tela de Cadastro
- Tela de Busca de Músicas
- Tela de Playlists (criação e listagem)
- Tela de Histórico (últimas ações)

---

## ✍️ Sobre o Projeto

Este projeto foi desenvolvido como prática acadêmica de um sistema completo em Java, utilizando padrão MVC, banco relacional e interface gráfica com Swing.

---

## 📌 Observações

- Senhas ainda estão salvas como texto puro (pode-se aplicar `hash` com `MessageDigest`).
- O sistema não possui múltiplas permissões (apenas usuário comum).
- As playlists e histórico são vinculadas por email.

---

## 📧 Contato

Desenvolvido por Pedro  
FEI - Ciência da Computação
