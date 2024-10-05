(ns hl7-parser-clojure.segments.msh)

(defrecord MSH
  [field-separator                                          ; MSH-1: Caracter que separa os campos (geralmente '|')
   encoding-characters                                      ; MSH-2: Caracteres de codificação (e.g., "^~\\&")
   sending-application                                      ; MSH-3: Nome da aplicação que enviou a mensagem
   sending-facility                                         ; MSH-4: Nome da unidade ou organização que enviou a mensagem
   receiving-application                                    ; MSH-5: Nome da aplicação que receberá a mensagem
   receiving-facility                                       ; MSH-6: Nome da unidade ou organização que receberá a mensagem
   datetime-of-message                                      ; MSH-7: Data e hora em que a mensagem foi criada
   security                                                 ; MSH-8: Informação de segurança (opcional)
   message-type                                             ; MSH-9: Tipo da mensagem (e.g., "ADT^A01")
   message-control-id                                       ; MSH-10: Identificador único da mensagem
   processing-id                                            ; MSH-11: Identificador de processamento (e.g., "P" para Produção)
   version-id                                               ; MSH-12: Versão do protocolo HL7 (e.g., "2.5")
   sequence-number                                          ; MSH-13: Número de sequência da mensagem (opcional)
   continuation-pointer                                     ; MSH-14: Ponto de continuação para mensagens longas (opcional)
   accept-ack-type                                          ; MSH-15: Tipo de confirmação de aceitação (opcional)
   application-ack-type                                     ; MSH-16: Tipo de confirmação da aplicação (opcional)
   country-code                                             ; MSH-17: Código do país (opcional)
   character-set                                            ; MSH-18: Conjunto de caracteres utilizado na mensagem (opcional)
   principal-language                                       ; MSH-19: Linguagem principal da mensagem (opcional)
   alternate-char-set                                       ; MSH-20: Conjunto de caracteres alternativo (opcional)
   version-releases                                         ; MSH-21: Versões e releases compatíveis (opcional)
   message-profile-id                                       ; MSH-22: Identificador do perfil da mensagem (opcional)
   sending-responsible-org                                  ; MSH-23: Organização responsável pelo envio (opcional)
   receiving-responsible-org                                ; MSH-24: Organização responsável pelo recebimento (opcional)
   sending-network-address                                  ; MSH-25: Endereço da rede de envio (opcional)
   receiving-network-address                                ; MSH-26: Endereço da rede de recebimento (opcional)
   ])