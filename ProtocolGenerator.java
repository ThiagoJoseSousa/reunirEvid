/**
 * PADRÃO: SINGLETON
 * Garante que exista UMA única instância gerando os números de protocolo,
 * evitando protocolos repetidos.
 */
public class ProtocolGenerator {

    // única instância, compartilhada por todo o programa
    private static ProtocolGenerator instancia = new ProtocolGenerator();

    private int contador = 1000;

    // construtor privado: ninguém de fora consegue fazer "new"
    private ProtocolGenerator() { }

    // porta de entrada para obter a instância única
    public static ProtocolGenerator getInstancia() {
        return instancia;
    }

    public String gerar() {
        contador++;
        return "DISP-" + contador;
    }
}
