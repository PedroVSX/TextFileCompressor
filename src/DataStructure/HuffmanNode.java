package DataStructure;

public class HuffmanNode implements Comparable<HuffmanNode> {

    Character character;
    int frequency;
    String code;
    HuffmanNode leftChild;
    HuffmanNode rightChild;

    public HuffmanNode(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.code = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public HuffmanNode(int frequency, HuffmanNode leftChild, HuffmanNode rightChild) {
        this.character = null;
        this.frequency = frequency;
        this.code = "";
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Character getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCode() {
        return code;
    }

    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public HuffmanNode getRightChild() {
        return rightChild;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLeftChild(HuffmanNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(HuffmanNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        if (this.frequency != o.frequency) {
            return Integer.compare(this.frequency, o.frequency);
        }

        if (this.character == null && o.character == null) {
            return 0;
        } else if (this.character == null) {
            return 1;
        } else if (o.character == null) {
            return -1;
        }

        return Character.compare(this.character, o.character);
    }

    @Override
    public String toString() {
        return  "(Character: " + character + " | " + "Frequency: " + frequency + ")";
    }
}
