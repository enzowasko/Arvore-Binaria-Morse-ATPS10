import java.util.Scanner;

public class ArvoreBinariaMorse {

    // Classe interna que representa um nó da árvore binária
    static class Nodo {
        char valor;
        Nodo esquerdo;
        Nodo direito;

        Nodo() {
            this.valor = '\0'; // '\0' indica que o nó está vazio
            this.esquerdo = null;
            this.direito = null;
        }
    }

    private Nodo raiz;

    // Cria a árvore com nó raiz vazio
    public void inicializar() {
        raiz = new Nodo();
    }

    // Insere um caractere seguindo a sequência Morse:
    // '.' -> filho esquerdo | '-' -> filho direito
    public void inserir(String codigo, char caractere) {
        if (codigo == null) return;
        Nodo atual = raiz;
        int n = codigo.length();
        for (int i = 0; i < n; i++) {
            char c = codigo.charAt(i);
            if (c == '.') {
                if (atual.esquerdo == null) atual.esquerdo = new Nodo();
                atual = atual.esquerdo;
            } else if (c == '-') {
                if (atual.direito == null) atual.direito = new Nodo();
                atual = atual.direito;
            }
        }
        atual.valor = caractere; // caractere armazenado no nó final
    }

    // Busca um caractere a partir de um código Morse
    public char buscarPorCodigo(String codigo) {
        if (codigo == null) return '\0';
        Nodo atual = raiz;
        int n = codigo.length();
        for (int i = 0; i < n; i++) {
            char c = codigo.charAt(i);
            if (c == '.') {
                if (atual.esquerdo == null) return '\0';
                atual = atual.esquerdo;
            } else if (c == '-') {
                if (atual.direito == null) return '\0';
                atual = atual.direito;
            } else {
                return '\0';
            }
        }
        return atual.valor;
    }

    // Busca recursiva: encontra o caminho Morse de um caractere
    private String buscarCodigoRec(Nodo node, char alvo, String path) {
        if (node == null) return null;
        if (node.valor == alvo) return path;
        String left = buscarCodigoRec(node.esquerdo, alvo, path + ".");
        if (left != null) return left;
        String right = buscarCodigoRec(node.direito, alvo, path + "-");
        if (right != null) return right;
        return null;
    }

    // Retorna o código Morse correspondente a um caractere
    public String buscarCodigoPorCaractere(char caractere) {
        return buscarCodigoRec(raiz, caractere, "");
    }

    // Remove um caractere sem destruir os ramos da árvore
    public boolean removerCaractere(char caractere) {
        String codigo = buscarCodigoPorCaractere(caractere);
        if (codigo == null) return false;
        Nodo atual = raiz;
        int n = codigo.length();
        for (int i = 0; i < n; i++) {
            char c = codigo.charAt(i);
            if (c == '.') {
                if (atual.esquerdo == null) return false;
                atual = atual.esquerdo;
            } else {
                if (atual.direito == null) return false;
                atual = atual.direito;
            }
        }
        atual.valor = '\0'; // limpa o valor do nó
        return true;
    }

    // Exibe a árvore de forma hierárquica (pré-ordem)
    private void exibirRec(Nodo node, String prefix, String branch) {
        if (node == null) return;
        String displayChar = (node.valor == '\0') ? "( )" : "(" + node.valor + ")";
        System.out.println(prefix + branch + " " + displayChar);
        String newPrefix = prefix + "    ";
        if (node.esquerdo != null) exibirRec(node.esquerdo, newPrefix, ".");
        else System.out.println(newPrefix + ". ( )");
        if (node.direito != null) exibirRec(node.direito, newPrefix, "-");
        else System.out.println(newPrefix + "- ( )");
    }

    public void exibirArvore() {
        System.out.println("Arvore Morse ( . => esquerdo , - => direito )");
        exibirRec(raiz, "", "raiz");
    }

    // Decodifica uma mensagem Morse para texto
    public String decodificarMensagem(String mensagemMorse) {
        if (mensagemMorse == null) return "";
        String resultado = "";
        // palavras separadas por " / "
        String[] palavras = splitBySeparator(mensagemMorse, " / ");
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i].trim();
            if (palavra.length() == 0) {
                if (i < palavras.length - 1) resultado += " ";
                continue;
            }
            // letras separadas por espaço simples
            String[] letras = splitBySeparator(palavra, " ");
            for (int j = 0; j < letras.length; j++) {
                String codigo = letras[j];
                if (codigo.length() == 0) continue;
                char dec = buscarPorCodigo(codigo);
                resultado += (dec == '\0') ? "?" : dec;
            }
            if (i < palavras.length - 1) resultado += " ";
        }
        return resultado;
    }

    // Codifica um texto normal (A-Z, 0-9) para Morse
    public String codificarMensagem(String texto) {
        if (texto == null) return "";
        String resultado = "";
        String[] palavras = splitBySeparator(texto.trim(), " ");
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            if (palavra.length() == 0) {
                if (i < palavras.length - 1) resultado += " / ";
                continue;
            }
            for (int j = 0; j < palavra.length(); j++) {
                char ch = palavra.charAt(j);
                if (ch >= 'a' && ch <= 'z') ch = (char)(ch - 32); // converte minúscula em maiúscula
                String code = buscarCodigoPorCaractere(ch);
                resultado += (code == null) ? "?" : code;
                if (j < palavra.length() - 1) resultado += " ";
            }
            if (i < palavras.length - 1) resultado += " / ";
        }
        return resultado;
    }

    // Divide uma string por um separador simples (sem regex)
    private String[] splitBySeparator(String s, String sep) {
        if (s == null) return new String[0];
        int count = 1;
        int slen = s.length();
        int seplen = sep.length();
        int idx = indexOfFrom(s, sep, 0);
        while (idx >= 0) {
            count++;
            idx = indexOfFrom(s, sep, idx + seplen);
        }
        String[] out = new String[count];
        int outi = 0;
        int start = 0;
        int pos = indexOfFrom(s, sep, start);
        while (pos >= 0) {
            out[outi++] = substring(s, start, pos);
            start = pos + seplen;
            pos = indexOfFrom(s, sep, start);
        }
        out[outi] = substring(s, start, slen);
        return out;
    }

    // Implementação manual de substring
    private String substring(String s, int begin, int end) {
        if (s == null) return null;
        int len = end - begin;
        if (len <= 0) return "";
        char[] buffer = new char[len];
        for (int i = 0; i < len; i++) buffer[i] = s.charAt(begin + i);
        return new String(buffer);
    }

    // Busca o índice de um padrão a partir de uma posição
    private int indexOfFrom(String s, String pat, int start) {
        if (s == null || pat == null) return -1;
        int sl = s.length();
        int pl = pat.length();
        if (pl == 0) return start <= sl ? start : -1;
        for (int i = start; i <= sl - pl; i++) {
            boolean ok = true;
            for (int j = 0; j < pl; j++) {
                if (s.charAt(i + j) != pat.charAt(j)) {
                    ok = false;
                    break;
                }
            }
            if (ok) return i;
        }
        return -1;
    }

    // Monta a árvore completa com A-Z e 0-9 conforme código Morse internacional
    private void inicializarPadrao() {
        String[] codigos = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
                "-----", ".----", "..---", "...--", "....-", ".....", "-....",
                "--...", "---..", "----."
        };
        char[] chars = {
                'A','B','C','D','E','F','G','H','I',
                'J','K','L','M','N','O','P','Q','R',
                'S','T','U','V','W','X','Y','Z',
                '0','1','2','3','4','5','6','7','8','9'
        };
        for (int i = 0; i < codigos.length; i++) inserir(codigos[i], chars[i]);
    }

    // Menu principal de interação com o usuário
    public static void main(String[] args) {
        ArvoreBinariaMorse arv = new ArvoreBinariaMorse();
        arv.inicializar();
        arv.inicializarPadrao();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Árvore Binária de Código Morse inicializada com A-Z e 0-9.");
        boolean sair = false;

        while (!sair) {
            System.out.println();
            System.out.println("1 - Exibir árvore;");
            System.out.println("2 - Decodificar mensagem em Morse (letras separadas por espaço, palavras por ' / ');");
            System.out.println("3 - Codificar texto em Morse (palavras separadas por espaço);");
            System.out.println("4 - Buscar caractere por código;");
            System.out.println("5 - Buscar código por caractere;");
            System.out.println("6 - Inserir novo caractere manualmente;");
            System.out.println("7 - Remover caractere;");
            System.out.println("0 - Sair.");
            System.out.print("Opção: ");
            String opt = scanner.nextLine().trim();

            if (opt.equals("1")) {
                arv.exibirArvore();
            } else if (opt.equals("2")) {
                System.out.print("Digite a mensagem Morse: ");
                String msg = scanner.nextLine();
                System.out.println("Decodificado: " + arv.decodificarMensagem(msg));
            } else if (opt.equals("3")) {
                System.out.print("Digite o texto: ");
                String texto = scanner.nextLine();
                System.out.println("Codificado: " + arv.codificarMensagem(texto));
            } else if (opt.equals("4")) {
                System.out.print("Digite o código Morse: ");
                String code = scanner.nextLine().trim();
                char c = arv.buscarPorCodigo(code);
                System.out.println(c == '\0' ? "Não encontrado." : "Resultado: " + c);
            } else if (opt.equals("5")) {
                System.out.print("Digite o caractere: ");
                String s = scanner.nextLine().trim();
                if (s.length() > 0) {
                    char ch = s.charAt(0);
                    if (ch >= 'a' && ch <= 'z') ch = (char)(ch - 32);
                    String code = arv.buscarCodigoPorCaractere(ch);
                    System.out.println(code == null ? "Código não encontrado para: " + ch
                            : "Código para " + ch + " -> " + code);
                }
            } else if (opt.equals("6")) {
                System.out.print("Digite o código Morse: ");
                String code = scanner.nextLine().trim();
                System.out.print("Digite o caractere correspondente: ");
                String s = scanner.nextLine().trim();
                if (s.length() > 0) {
                    char ch = s.charAt(0);
                    if (ch >= 'a' && ch <= 'z') ch = (char)(ch - 32);
                    arv.inserir(code, ch);
                    System.out.println("Inserido: " + ch + " em " + code);
                }
            } else if (opt.equals("7")) {
                System.out.print("Digite o caractere a remover: ");
                String s = scanner.nextLine().trim();
                if (s.length() > 0) {
                    char ch = s.charAt(0);
                    if (ch >= 'a' && ch <= 'z') ch = (char)(ch - 32);
                    if (arv.removerCaractere(ch))
                        System.out.println("Removido (limpado) caractere: " + ch);
                    else System.out.println("Caractere não encontrado: " + ch);
                }
            } else if (opt.equals("0")) {
                sair = true;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}
