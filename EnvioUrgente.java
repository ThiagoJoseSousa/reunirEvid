public class EnvioUrgente implements EnvioStrategy {
    @Override
    public void enviar() {
        System.out.println("[⚡] Envio URGENTE: resposta em até 24 horas.");
    }
}
