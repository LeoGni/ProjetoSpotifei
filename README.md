# Spotifei

## 1. Introdução

O Spotifei é uma aplicação desktop desenvolvida em Java utilizando Swing para a interface gráfica e PostgreSQL como sistema gerenciador de banco de dados. O projeto tem como objetivo oferecer uma plataforma simples para cadastro, busca, gerenciamento e reprodução de músicas, com funcionalidades de playlists, curtidas e histórico de ações dos usuários, inspirado no estilo visual do Spotify.

Este documento descreve as principais funcionalidades, arquitetura, design e instruções para execução do sistema.

---

## 2. Funcionalidades Implementadas

### Cadastro e Login
- Tela de cadastro para novos usuários, com validação básica e persistência no banco.
- Tela de login que autentica usuários e mantém a sessão ativa para uso das demais funcionalidades.

### Busca e Gerenciamento de Músicas
- Interface para buscar músicas por nome, exibindo detalhes como nome, artista e gênero.
- Permite curtir e descurtir músicas, com registro dessas ações no histórico do usuário.
- Função para adicionar músicas a playlists existentes.

### Playlists
- Criação, visualização e gerenciamento de playlists próprias do usuário.
- Adição e remoção de músicas dentro das playlists.
- Visualização das músicas contidas em cada playlist, com as opções de curtir ou descurtir diretamente nesta tela.

### Histórico
- Registro e exibição das ações realizadas pelo usuário, incluindo buscas, curtidas e descurtidas.
- Visualização detalhada do histórico com separação por tipo de ação.

---

## 3. Arquitetura e Tecnologias Utilizadas

- **Linguagem:** Java 17 (recomendado).
- **Interface:** Java Swing, com componentes personalizados para melhor experiência visual.
- **Banco de Dados:** PostgreSQL, com tabelas normalizadas para usuários, músicas, playlists, curtidas e histórico.
- **DAO (Data Access Object):** Padrão DAO implementado para abstrair acesso e manipulação dos dados no banco.
- **Controle de Sessão:** Classe utilitária para manter o email do usuário logado em toda a aplicação.

---

## 4. Design e Estilo Visual

- Paleta de cores preto e verde, inspirado no Spotify, garantindo uma experiência visual agradável e moderna.
- Formulários, tabelas e botões com cores escuras de fundo e fontes claras, com destaques em verde para botões e bordas.
- Botões com aparência plana e sem foco padrão para visual mais limpo.
- Tabelas com dados centralizados e seleção destacada em verde.

---

## 5. Estrutura do Código

- O projeto está organizado em pacotes principais:
  - `view`: Telas e interfaces gráficas.
  - `dao`: Classes de acesso a dados e manipulação do banco.
  - `model`: Entidades e modelos de dados.
  - `util`: Classes utilitárias, como controle de sessão.

- Cada tela possui sua própria classe JFrame, facilitando manutenção e expansão futura.
- DAOs encapsulam comandos SQL e lidam com conexões, promovendo separação de responsabilidades.

---

## 6. Como Executar o Projeto

1. Configure um banco PostgreSQL com o esquema fornecido (script SQL disponível no repositório).
2. Ajuste as configurações de conexão no arquivo `Conexao.java` para apontar para seu banco local.
3. Compile e execute a aplicação a partir da classe principal (`TelaLogin`).
4. Faça cadastro, login e comece a usar as funcionalidades.

---

## 7. Possíveis Melhorias Futuras

- Implementar sistema de reprodução real de músicas (player).
- Adicionar funcionalidades sociais, como compartilhamento de playlists.
- Melhorar validação dos formulários e tratamento de exceções.
- Adotar padrões de projeto mais robustos, como MVC completo.
- Utilizar biblioteca gráfica moderna (JavaFX ou frameworks web).

---

## 8. Conclusão

O Spotifei é um projeto que demonstra o domínio da linguagem Java, interfaces gráficas com Swing, e integração com banco de dados relacional. Além de fornecer funcionalidades úteis para gestão de músicas, playlists e histórico, o sistema possui um visual consistente e moderno, aproximando a experiência do usuário da de aplicativos comerciais como o Spotify.

Este projeto serve como base para futuras evoluções e aprimoramentos, podendo ser expandido com funcionalidades mais avançadas conforme necessidade.

---

## Contato

Para dúvidas ou sugestões, entre em contato pelo email: seu-email@exemplo.com

---

*Obrigado por acessar o Spotifei!*
