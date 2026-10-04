public class EnvioNormal implements EnvioStrategy {
    @Override
    public void enviar() {
        System.out.println("[✉] Envio NORMAL: resposta em até 7 dias.");
    }
}
