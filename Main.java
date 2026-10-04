import java.util.List;

/**
 * Fluxo completo de uma disputa:
 * "Meu cartão foi bloqueado, quero desbloquear. Tenho prints e fotos."
 */
public class Main {
    public static void main(String[] args) {

        FacadeDispute facade = new FacadeDispute();

        Dispute dispute = facade.openDispute(
                "Ana Souza",
                "1234",
                "Cartão bloqueado sem motivo; quero desbloquear.",
                List.of(
                        new Evidence("EMAIL_PRINT", "email_banco_bloqueio.png", 850),
                        new Evidence("PHOTO", "foto_cartao.jpg", 2300),
                        new Evidence("PDF", "fatura_setembro.pdf", 1200)
                )
        );

        if (dispute == null) {
            System.out.println("Corrija as provas e tente novamente.");
        } else {
            System.out.println("Disputa aberta: " + dispute.getProtocol());
        }
    }
}
