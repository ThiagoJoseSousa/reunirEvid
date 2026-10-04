import java.util.List;

/**
 * PADRÃO: FACADE
 * Esconde do cliente (Main) todos os passos e a ordem correta:
 * gerar protocolo, montar a disputa, anexar provas, validar e enviar.
 */
public class FacadeDispute {

    private DisputeService service = new DisputeService();

    public Dispute openDispute(String nome, String cartao, String motivo,
                               List<Evidence> provas) {

        Dispute dispute = new Dispute(
                service.generateProtocol(),
                nome,
                cartao,
                motivo,
                true,   // urgente
                true,   // avisar por e-mail
                false   // avisar por SMS
        );

        for (Evidence e : provas) {
            dispute.getEvidences().add(e);
        }

        if (!service.validate(dispute)) {
            return null;
        }

        service.submit(dispute);

        return dispute;
    }
}
