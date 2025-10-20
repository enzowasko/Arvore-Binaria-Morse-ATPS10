# 🌲 Árvore Binária de Código Morse  
**Autor:** Enzo Wasko Amorim, Bernardo Schlottag Müller
**Disciplina:** Resolução de Problemas Estruturados em Computação
**Professor:** Andrey Cabral Meira

---

## 📘 Descrição

Este projeto implementa uma **árvore binária em Java** que representa o **Código Morse internacional**, permitindo **codificar e decodificar mensagens** de texto.  

Na estrutura da árvore:
- **`.` (ponto)** indica o **filho esquerdo**  
- **`-` (traço)** indica o **filho direito**  

Todos os caracteres de **A–Z** e **0–9** são inseridos automaticamente conforme o padrão Morse internacional.

---

## ⚙️ Funcionalidades

| Função | Método | Descrição |
|--------|---------|-----------|
| Inicializar árvore | `inicializar()` | Cria a raiz da árvore binária. |
| Inserir caractere | `inserir(String codigo, char caractere)` | Insere um caractere conforme o código Morse. |
| Buscar por código | `buscarPorCodigo(String codigo)` | Retorna o caractere correspondente a um código Morse. |
| Buscar por caractere | `buscarCodigoPorCaractere(char caractere)` | Retorna o código Morse de um caractere. |
| Remover caractere | `removerCaractere(char caractere)` | Limpa o valor do nó (mantendo a estrutura). |
| Exibir árvore | `exibirArvore()` | Mostra a estrutura da árvore no console. |
| Codificar texto | `codificarMensagem(String texto)` | Converte texto normal em código Morse. |
| Decodificar mensagem | `decodificarMensagem(String mensagemMorse)` | Converte código Morse em texto. |

---

## 🧭 Menu de Opções

| Opção | Descrição |
|:------:|------------|
| 1 | Exibir árvore Morse completa |
| 2 | Decodificar mensagem em Morse |
| 3 | Codificar texto em Morse |
| 4 | Buscar caractere por código |
| 5 | Buscar código por caractere |
| 6 | Inserir novo caractere manualmente |
| 7 | Remover caractere |
| 0 | Sair do programa |

---

## 🌳 Exemplo de Estrutura da Árvore
raiz
 ├── . (E)
 │    ├── . (I)
 │    │    └── . (S)
 │    └── - (A)
 └── - (T)
      └── - (M)
           └── - (O)
          
---

## 🧩 Exemplos de Uso

### 🔹 Decodificar mensagem
**Entrada:** ... --- ...

**Saída:** Decodificado: SOS

---

### 🔹 Codificar texto
**Entrada:** SOS

**Saída:** Codificado: ... --- ...

---

### 🔹 Buscar código por caractere
**Entrada:** Digite o caractere: S

**Saída:** Código para S -> ...

---

### 🔹 Buscar caractere por código
**Entrada:** Digite o código Morse: ---

**Saída:** Resultado: O

---

### 🔹 Inserir novo caractere
**Entrada:** Digite o código Morse: .--.-.
Digite o caractere correspondente: @

**Saída:** Inserido: @ em .--.-.

---

### 🔹 Remover caractere
**Entrada:** Digite o caractere a remover: Q

**Saída:** Removido (limpado) caractere: Q

---

## 📊 Testes Rápidos (validação)

| Entrada | Saída Esperada |
|:--------|:----------------|
| `... --- ...` | `SOS` |
| `.-` | `A` |
| `.- -...` | `AB` |
| `.... . .-.. .-.. --- / .-- --- .-. .-.. -..` | `HELLO WORLD` |
| `HELLO WORLD` | `.... . .-.. .-.. --- / .-- --- .-. .-.. -..` |

