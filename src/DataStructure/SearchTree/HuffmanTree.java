package DataStructure.SearchTree;

import DataStructure.HashTable;
import DataStructure.HuffmanNode;
import DataStructure.PriorityQueue.PriorityQueue;

public class HuffmanTree {
    private HuffmanNode root;
    private HashTable charactersCode;

    public HuffmanTree() {
        this.root = null;
        this.charactersCode = new HashTable(256);
    }

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

    private void generateCodes(HuffmanNode node, String code) {
        if (node != null) {
            if (node.getCharacter() != null) {
                node.setCode(code);
            }

            generateCodes(node.getLeftChild(), code + "0");
            generateCodes(node.getRightChild(), code + "1");
        }
    }

    private String getBinaryValue(Character character) {
        String binaryValue = Integer.toBinaryString(character);

        while (binaryValue.length() < 8) {
            binaryValue = "0" + binaryValue;
        }

        return binaryValue;
    }

    private String generateTreeCode(HuffmanNode root) {
        if (root == null) {
            return "";
        }

        if (isLeaf(root)) {
            return "1" + getBinaryValue(root.getCharacter());
        } else {
            return "0" + generateTreeCode(root.getLeftChild()) + generateTreeCode(root.getRightChild());
        }
    }

    public String encode(String text) {
        String encodedText = generateTreeCode(root);

        for (char c : text.toCharArray()) {
            encodedText += getCodeForCharacter(root, c);
        }

        return encodedText;
    }

    private boolean isLeaf(HuffmanNode branch) {
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

    private String getCodeForCharacter(HuffmanNode branch, char character) {
        if (branch == null) {
            return "";
        }

        if (branch.getCharacter() != null && branch.getCharacter() == character) {
            return branch.getCode();
        }

        String left = getCodeForCharacter(branch.getLeftChild(), character);
        if (!left.isEmpty()) {
            return left;
        }

        return getCodeForCharacter(branch.getRightChild(), character);
    }

    public void showPreOrder() {
        if (root != null) {
            showPreOrder(root);
            System.out.println();
        }
    }

    private void showPreOrder(HuffmanNode branch) {
        System.out.print(branch.getFrequency() + " ");

        if (branch.getLeftChild() != null) {
            showPreOrder(branch.getLeftChild());
        }

        if (branch.getRightChild() != null) {
            showPreOrder(branch.getRightChild());
        }
    }

    public void showInOrder() {
        if (root != null) {
            showInOrder(root);
            System.out.println();
        }
    }

    private void showInOrder(HuffmanNode branch) {
        if (branch.getLeftChild() != null) {
            showInOrder(branch.getLeftChild());
        }

        System.out.print(branch.getFrequency() + " ");

        if (branch.getRightChild() != null) {
            showInOrder(branch.getRightChild());
        }
    }

    public void showPostOrder() {
        if (root != null) {
            showPostOrder(root);
            System.out.println();
        }
    }

    private void showPostOrder(HuffmanNode branch) {
        if (branch.getLeftChild() != null) {
            showPostOrder(branch.getLeftChild());
        }

        if (branch.getRightChild() != null) {
            showPostOrder(branch.getRightChild());
        }

        System.out.print(branch.getFrequency() + " ");
    }
}
