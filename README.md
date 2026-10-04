# Disputa Bancária — Design Patterns em Java

Cenário: *"Meu cartão foi bloqueado e quero desbloquear. Tenho prints do e-mail do banco e fotos da situação."*

O sistema monta uma disputa com as provas (prints, fotos, PDFs), valida os arquivos e envia para análise do banco.

## Padrões aplicados manualmente

| Padrão | Arquivo | O que resolve |
|--------|---------|---------------|
| **Singleton** | `ProtocolGenerator.java` | Uma única instância gera os protocolos, sem números repetidos |
| **Facade** | `FacadeDispute.java` | O `Main` chama um único método; a ordem dos passos (protocolo, montar, anexar, validar, enviar) fica escondida |
| **Strategy** | `EnvioStrategy`, `EnvioUrgente`, `EnvioNormal` | Cada tipo de envio (urgente ou normal) fica em uma classe; o service só chama `enviar()` |

## Estrutura

```
Main  ->  FacadeDispute  ->  DisputeService  ->  ProtocolGenerator (Singleton)
                                             ->  EnvioStrategy (Strategy)
```

## Como rodar

Requer Java 11 ou superior.

```bash
cd src
javac -encoding UTF-8 *.java
java Main
```

## Próximos passos possíveis

Refatorar o `DisputeService` com outros padrões: Builder (criação da `Dispute`), Factory Method (tipos de prova), Chain of Responsibility (validações), State (status) e Observer (notificações).
