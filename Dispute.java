import java.util.ArrayList;
import java.util.List;

/**
 * A disputa aberta junto ao banco (ex.: cartão bloqueado indevidamente).
 */
public class Dispute {
    private String protocol;
    private String customerName;
    private String cardLast4;
    private String reason;
    private boolean urgent;
    private boolean notifyByEmail;
    private boolean notifyBySms;
    private String status = "RASCUNHO"; // RASCUNHO, EM_ANALISE, APROVADA, NEGADA
    private final List<Evidence> evidences = new ArrayList<>();

    public Dispute(String protocol, String customerName, String cardLast4,
                   String reason, boolean urgent,
                   boolean notifyByEmail, boolean notifyBySms) {
        this.protocol = protocol;
        this.customerName = customerName;
        this.cardLast4 = cardLast4;
        this.reason = reason;
        this.urgent = urgent;
        this.notifyByEmail = notifyByEmail;
        this.notifyBySms = notifyBySms;
    }

    public String getProtocol()      { return protocol; }
    public String getCustomerName()  { return customerName; }
    public String getCardLast4()     { return cardLast4; }
    public String getReason()        { return reason; }
    public boolean isUrgent()        { return urgent; }
    public boolean isNotifyByEmail() { return notifyByEmail; }
    public boolean isNotifyBySms()   { return notifyBySms; }
    public String getStatus()        { return status; }
    public void setStatus(String s)  { this.status = s; }
    public List<Evidence> getEvidences() { return evidences; }
}
