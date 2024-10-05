(ns hl7-parser-clojure.segments.msa)

(defrecord MSA
  [acknowledgment-code                                      ; MSA-1: Código de reconhecimento (e.g., "AA" para Acknowledge Accept, "AE" para Acknowledge Error)
   message-control-id                                       ; MSA-2: ID de controle da mensagem (corresponde ao ID da mensagem original enviada)
   text-message                                             ; MSA-3: Mensagem de texto (opcional, pode fornecer detalhes adicionais)
   expected-sequence-number                                 ; MSA-4: Número de sequência esperado (opcional, utilizado para mensagens sequenciais)
   delayed-ack-type                                         ; MSA-5: Tipo de reconhecimento atrasado (opcional)
   error-condition                                          ; MSA-6: Condição de erro (opcional, pode fornecer informações adicionais sobre o erro ocorrido)
   ])