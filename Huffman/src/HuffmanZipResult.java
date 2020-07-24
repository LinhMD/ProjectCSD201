/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DELL
 */
public class HuffmanZipResult {

    String srcName = null;
    int numberOfLastBit = 0;
    CodewordTable minCodeTable = null;
    byte[] zippedData;

    @Override
    public String toString() {
        String S = "";
        S += "Source name: " + srcName + "\n";
        S += "Number of last bits: " + numberOfLastBit + "\n";
        S += "Code table: " + minCodeTable + "\n";
        S += "Data: " + new String(zippedData);
        return S;
    }

}
