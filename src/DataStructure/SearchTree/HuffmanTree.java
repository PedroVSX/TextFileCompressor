package DataStructure.SearchTree;

import DataStructure.HashTable;
import DataStructure.HuffmanNode;
import DataStructure.PriorityQueue.PriorityQueue;

public class HuffmanTree {
    private HuffmanNode root;
    private String code;

    public HuffmanTree() {
        this.root = null;
        this.code = "";
    }

    // Cria a árvore com base na fila de prioridade
    public void createTree(PriorityQueue<HuffmanNode> queue) {
        while (queue.size() > 1) {
            HuffmanNode left = queue.dequeue();
            HuffmanNode right = queue.dequeue();

            int frequencySum = left.getFrequency() + right.getFrequency();
            HuffmanNode newNode = new HuffmanNode(frequencySum, left, right);

            queue.enqueue(newNode);
        }

        root = queue.dequeue();
        generateCodes(root, "");
    }

    // Gera os códigos de cada caractere
    private void generateCodes(HuffmanNode node, String code) {
        if (node != null) {
            if (node.getCharacter() != null) {
                node.setCode(code);
            }

            generateCodes(node.getLeftChild(), code + "0");
            generateCodes(node.getRightChild(), code + "1");
        }
    }

    // No código da árvore, pega o valor em binário do caractere e o transforma em 8 bits
    private String getBinaryValue(Character character) {
        String binaryValue = Integer.toBinaryString(character);

        while (binaryValue.length() < 8) {
            binaryValue = "0" + binaryValue;
        }

        return binaryValue;
    }

    // Pega o valor do navigatePreOrder() e retorna o código gerado
    private String generateTreeCode() {
        navigatePreOrder();
        return code;
    }

    // Gera o código completo
    public String encode(String text) {
        String encodedText = generateTreeCode();

        for (char c : text.toCharArray()) {
            encodedText += getCodeFromCharacter(root, c);
        }

        return encodedText;
    }

    // Busca na árvore o caractere e retorna o código dele
    private String getCodeFromCharacter(HuffmanNode branch, char character) {
        if (branch == null) {
            return "";
        }

        if (branch.getCharacter() != null && branch.getCharacter() == character) {
            return branch.getCode();
        }

        String left = getCodeFromCharacter(branch.getLeftChild(), character);
        if (!left.isEmpty()) {
            return left;
        }

        return getCodeFromCharacter(branch.getRightChild(), character);
    }

    // Gerar o código da árvore
    public void navigatePreOrder() {
        if (root != null) {
            navigatePreOrder(root);
        }
    }

    // Gerar o código da árvore
    private void navigatePreOrder(HuffmanNode branch) {
        if (branch.getCharacter() != null) {
            this.code += "1" + getBinaryValue(branch.getCharacter());
        } else {
            this.code += "0";
        }

        if (branch.getLeftChild() != null) {
            navigatePreOrder(branch.getLeftChild());
        }

        if (branch.getRightChild() != null) {
            navigatePreOrder(branch.getRightChild());
        }
    }

    // Decodificar árvore
    public HuffmanNode decodeTree(String encodedText, int[] index) {
        if (encodedText.charAt(index[0]) == '1') {
            index[0]++;

            String binaryLetter = encodedText.substring(index[0], index[0] + 8);
            index[0] += 8;

            char letter = (char) Integer.parseInt(binaryLetter, 2);
            return new HuffmanNode(letter, 0);
        } else {
            index[0]++;

            HuffmanNode left = decodeTree(encodedText, index);
            HuffmanNode right = decodeTree(encodedText, index);

            return new HuffmanNode(0, left, right);
        }
    }

    // Decodificar mensagem
    public String decodeMessage(String encodedText, HuffmanNode root) {
        String decodedText = "";
        HuffmanNode current = root;

        for (int i = 0; i < encodedText.length(); i++) {
            if (encodedText.charAt(i) == '0') {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }

            if (isLeaf(current)) {
                decodedText += current.getCharacter();
                current = root;
            }
        }

        return decodedText;
    }

    // Verifica se o nó é uma folha
    public boolean isLeaf(HuffmanNode branch) {
        return branch.getLeftChild() == null && branch.getRightChild() == null;
    }

    public void printHuffmanCodes() {
        printHuffmanCodes(root, "");
    }

    private void printHuffmanCodes(HuffmanNode branch, String prefix) {
        if (branch != null) {
            if (branch.getCharacter() != null) {
                System.out.println(branch.getCharacter() + ": " + prefix);
            }
            printHuffmanCodes(branch.getLeftChild(), prefix + "0");
            printHuffmanCodes(branch.getRightChild(), prefix + "1");
        }
    }
}
