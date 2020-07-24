/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author DELL
 */
public class HuffmanEncoder {

    CodewordTable codeTable;
    String encodeStr = "";
    InputStream source;
    boolean encoded = false;
    int srcByteNumber = 0;
    int zipByteNumber = 0;

    public HuffmanEncoder(InputStream source) {
        try {
            srcByteNumber = source.available();
            this.encoded(source);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public HuffmanEncoder(String srcStr) {
        byte[] symbols = srcStr.getBytes();
        srcByteNumber = symbols.length;
        try {
            ByteArrayInputStream iStream = new ByteArrayInputStream(symbols);
            this.encoded(iStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public HuffmanEncoder(File srcFile) {
        if (!srcFile.exists()) {
            System.out.println("The file " + srcFile.getName() + " doesn't exist!");
            System.exit(0);
        }
        try {
            FileInputStream fi = new FileInputStream(srcFile);
            byte[] symbols = new byte[fi.available()];
            fi.read(symbols);
            fi.close();
            srcByteNumber = symbols.length;
            ByteArrayInputStream iStream = new ByteArrayInputStream(symbols);
            this.encoded(iStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void encoded(InputStream source) {
        encoded = false;
        this.source = source;
        FreqCounter freqCounter = new FreqCounter(source);
        HuffmanTree tree = new HuffmanTree(freqCounter);
        CodewordGenerator codeGen = new CodewordGenerator(tree);
        codeGen.generateCodewords();
        codeTable = codeGen.getCodeTable();
        createEncodedStr();
        encoded = true;
    }

    private boolean createEncodedStr() {
        encodeStr = "";
        try {
            if (source == null || source.available() == 0) {
                System.out.println("Source is empty!");
                return false;
            }
            int symbols;
            this.source.reset();
            while ((symbols = source.read()) != -1) {
                String code = codeTable.get(symbols).codeword;
                encodeStr += code;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public HuffmanZipResult creZipResult(String srcname) {
        if (!encoded) {
            return null;
        }
        HuffmanZipResult result = new HuffmanZipResult();
        result.srcName = srcname;
        result.minCodeTable = this.codeTable.reduce();
        int L = encodeStr.length();
        int remaider = L % 8;
        result.numberOfLastBit = remaider;
        int zipLen = L / 8;
        result.zippedData = new byte[zipLen + (remaider > 0 ? 1 : 0)];
        int pos = 0, nextPos;
        int zipData;
        for (int i = 0; i < zipLen; i++) {
            nextPos = pos + 8;
            String str8 = encodeStr.substring(pos, pos + 8);
            zipData = Integer.parseInt(str8, 2);
            result.zippedData[i] = (byte) zipData;
            pos = nextPos;
        }
        if (remaider > 0) {
            String str8 = encodeStr.substring(L - remaider, L);
            while (str8.length() < 8) {
                str8 += "0";
            }
            zipData = Integer.parseInt(str8, 2);
            int lastbyteIndex = result.zippedData.length - 1;
            result.zippedData[lastbyteIndex] = (byte) zipData;

        }
        this.zipByteNumber = result.zippedData.length;
        return result;
    }

    public boolean writeZipFile(String srcFilename, String zipFilename) {
        if (!this.encoded) {
            System.out.println("Encoding operation is not performed yet!");
            return false;
        }
        HuffmanZipResult zipResult = creZipResult(srcFilename);
        try {
            FileOutputStream fo = new FileOutputStream(zipFilename);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(zipResult);
            oo.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public CodewordTable getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(CodewordTable codeTable) {
        this.codeTable = codeTable;
    }

    public String getEncodeStr() {
        return encodeStr;
    }

    public void setEncodeStr(String encodeStr) {
        this.encodeStr = encodeStr;
    }

    public InputStream getSource() {
        return source;
    }

    public void setSource(InputStream source) {
        this.source = source;
    }

}
