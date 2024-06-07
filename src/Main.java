import DataStructure.*;
import DataStructure.PriorityQueue.PriorityQueue;
import DataStructure.SearchTree.HuffmanTree;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static HashTable hash = new HashTable(256);
    static PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
    static HuffmanTree tree = new HuffmanTree();
    static String filePath = "C:\\Users\\Pichau\\Documents\\Programar\\java\\CompressorDeTexto\\src\\texto.txt";
    static String fileCreationPath = "C:\\Users\\Pichau\\Documents\\Programar\\java\\CompressorDeTexto\\src\\textoCompactado.txt";

    public static void main(String[] args) throws IOException {
        String text = navigateCharacters(filePath);

        createQueue();

        tree.createTree(queue);
        tree.printHuffmanCodes();
        String encodedText = tree.encode(text);
        FileWriter writer = new FileWriter(fileCreationPath);
        writer.write(encodedText);
        writer.close();

        System.out.println(text);
    }

    public static void createQueue() {
        for (int i = 0; i < hash.size(); i++) {
            try {
                HuffmanNode node = new HuffmanNode(hash.get(i), hash.getIndexSize(i));
                queue.enqueue(node);
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    public static String navigateCharacters(String filePath) {
        String text = "";
        try (FileReader fileReader = new FileReader(filePath)) {
            int charCode;
            while ((charCode = fileReader.read()) != -1) {
                char character = (char) charCode;
                text += character;
                hash.insert(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
