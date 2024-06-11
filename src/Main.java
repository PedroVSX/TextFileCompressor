import DataStructure.*;
import DataStructure.PriorityQueue.PriorityQueue;
import DataStructure.SearchTree.HuffmanTree;

import java.io.*;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static HashTable hash = new HashTable(256);
    static PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
    static HuffmanTree tree = new HuffmanTree();
    static String filePath = "C:\\Users\\Pichau\\Documents\\Programar\\java\\CompressorDeTexto\\src\\texto.txt";
    static String fileCreationPath = "C:\\Users\\Pichau\\Documents\\Programar\\java\\CompressorDeTexto\\src\\textoCompactado.txt";
    static String decodedFile = "C:\\Users\\Pichau\\Documents\\Programar\\java\\CompressorDeTexto\\src\\textoDescompactado.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Olá, Gilson! Vamos testar aí o nosso Compressor de Texto.");

        while (true) {
            System.out.print("""
                | Opções |
                [1] - Comprimir arquivo
                [2] - Descomprimir arquivo
                [0] - Sair
                """);

            System.out.print("Escolha: ");
            int choice = Integer.parseInt(input.readLine());

            if (choice == 1) {
                // Comprimir arquivo
                String text = navigateCharacters(filePath, hash);

                createQueue();

                tree.createTree(queue);
                tree.printHuffmanCodes();
                String encodedText = tree.encode(text);
                FileWriter writer = new FileWriter(fileCreationPath);
                writer.write(encodedText);
                writer.close();

                System.out.println("\nTexto comprimido com sucesso!\n\n");

            } else if (choice == 2) {
                // Descomprimir arquivo
                HuffmanTree newTree = new HuffmanTree();
                String encodedFile = getEncodedFile(fileCreationPath);
                int[] index = {0};

                HuffmanNode root = newTree.decodeTree(encodedFile, index);
                String encodedText = encodedFile.substring(index[0]);

                String decodedText = newTree.decodeMessage(encodedText, root);

                FileWriter writer = new FileWriter(decodedFile);
                writer.write(decodedText);
                writer.close();

                System.out.println("\nTexto descomprimido com sucesso!\n\n");

            } else {
                System.out.println("Valeu, Gilson!");
                break;
            }
        }
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

    public static String navigateCharacters(String filePath, HashTable hash) {
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

    public static String getEncodedFile(String filePath) {
        String text = "";

        try (FileReader fileReader = new FileReader(filePath)) {
            int charCode;
            while ((charCode = fileReader.read()) != -1) {
                char character = (char) charCode;
                text += character;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
