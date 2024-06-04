package DataStructure;

import DataStructure.DynamicList.DynamicList;

public class HashTable {
    private DynamicList[] hash;

    public HashTable(int n) {
        this.hash = new DynamicList[n];
    }

    public void insert(char elem) {
        int pos = elem % hash.length;

        if (hash[pos] == null) {
            hash[pos] = new DynamicList();
        }

        hash[pos].add(elem);
    }

    public void remove(Character elem) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null) {
                hash[i].remove(elem);
            }
        }
    }

    public Character get(int index) {
        for (int i = 0; i < hash.length; i++) {
            if (i == index) {
                return hash[i].get(0);
            }
        }

        return null;
    }

    public Integer getIndexSize(int index) {
        for (int i = 0; i < hash.length; i++) {
            if (i == index) {
                return hash[i].size();
            }
        }

        return null;
    }

    public int size() {
        return hash.length;
    }

    public void show() {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null) {
                hash[i].show();
            } else {
                System.out.println("[]");
            }
        }
    }

    public void showFrequency() {
        String concatener = "";
        String output = "";

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null) {
                concatener += hash[i].get(0) + " = " + hash[i].size() + ", ";
            }
        }

        for (int i = 0; i < concatener.length() - 2; i++) {
            output += concatener.charAt(i);
        }

        System.out.println("[" + output + "]");
    }
}