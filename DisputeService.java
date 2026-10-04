/**
 * Regras de negócio da disputa.
 */
public class DisputeService {

    // Usa o Singleton para gerar protocolos
    public String generateProtocol() {
        return ProtocolGenerator.getInstancia().gerar();
    }

    public void addEvidence(Dispute dispute, String type, String fileName, int sizeKb) {
        Evidence evidence;
        if (type.equals("EMAIL_PRINT")) {
            evidence = new Evidence("EMAIL_PRINT", fileName, sizeKb);
        } else if (type.equals("PHOTO")) {
            evidence = new Evidence("PHOTO", fileName, sizeKb);
        } else if (type.equals("PDF")) {
            evidence = new Evidence("PDF", fileName, sizeKb);
        } else {
            throw new IllegalArgumentException("Tipo de prova inválido: " + type);
        }
        dispute.getEvidences().add(evidence);
        System.out.println("[+] Prova anexada: " + evidence);
    }

    public boolean validate(Dispute dispute) {
        if (dispute.getEvidences().size() < 2) {
            System.out.println("[x] Anexe pelo menos 2 provas.");
            return false;
        }
        for (Evidence e : dispute.getEvidences()) {
            if (e.getSizeKb() > 5000) {
                System.out.println("[x] Arquivo muito grande: " + e.getFileName());
                return false;
            }
            String name = e.getFileName().toLowerCase();
            if (!(name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".pdf"))) {
                System.out.println("[x] Extensão não permitida: " + e.getFileName());
                return false;
            }
        }
        System.out.println("[✓] Provas validadas.");
        return true;
    }

    public void submit(Dispute dispute) {
        switch (dispute.getStatus()) {
            case "RASCUNHO":
                dispute.setStatus("EM_ANALISE");
                System.out.println("[→] Disputa enviada para análise.");
                // Strategy: o service não sabe como o envio acontece, só chama enviar()
                escolherEstrategia(dispute).enviar();
                break;
            case "EM_ANALISE":
                System.out.println("[!] Disputa já está em análise.");
                break;
            case "APROVADA":
            case "NEGADA":
                System.out.println("[!] Disputa já finalizada, não pode reenviar.");
                break;
            default:
                throw new IllegalStateException("Status desconhecido");
        }
        notifyCustomer(dispute);
    }

    // Único ponto onde se decide qual estratégia de envio usar
    private EnvioStrategy escolherEstrategia(Dispute dispute) {
        return dispute.isUrgent() ? new EnvioUrgente() : new EnvioNormal();
    }

    public void approve(Dispute dispute) {
        switch (dispute.getStatus()) {
            case "EM_ANALISE":
                dispute.setStatus("APROVADA");
                System.out.println("[✓] Disputa aprovada! Cartão será desbloqueado.");
                break;
            default:
                System.out.println("[!] Só é possível aprovar uma disputa EM_ANALISE.");
        }
        notifyCustomer(dispute);
    }

    private void notifyCustomer(Dispute dispute) {
        if (dispute.isNotifyByEmail()) {
            System.out.println("   (e-mail) " + dispute.getCustomerName()
                    + ", sua disputa " + dispute.getProtocol()
                    + " agora está: " + dispute.getStatus());
        }
        if (dispute.isNotifyBySms()) {
            System.out.println("   (sms) Disputa " + dispute.getProtocol()
                    + ": " + dispute.getStatus());
        }
        System.out.println("   (log) " + dispute.getProtocol() + " -> " + dispute.getStatus());
    }
}
