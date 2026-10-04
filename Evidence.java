/**
 * Uma prova (documento) anexada à disputa.
 * Ex.: print do e-mail do banco, foto do cartão, PDF da fatura.
 */
public class Evidence {
    private final String type;      // "EMAIL_PRINT", "PHOTO", "PDF"
    private final String fileName;
    private final int sizeKb;

    public Evidence(String type, String fileName, int sizeKb) {
        this.type = type;
        this.fileName = fileName;
        this.sizeKb = sizeKb;
    }

    public String getType()     { return type; }
    public String getFileName() { return fileName; }
    public int getSizeKb()      { return sizeKb; }

    @Override
    public String toString() {
        return type + " -> " + fileName + " (" + sizeKb + " KB)";
    }
}
