(ns hl7-parser-clojure.segments.obr)

(defrecord OBR
           [set-id                                                   ; OBR-1: ID do conjunto (sequencial para identificar cada OBR em uma mensagem)
            placer-order-number                                      ; OBR-2: Número do pedido (sistema que fez o pedido)
            filler-order-number                                      ; OBR-3: Número do pedido preenchido (sistema que executou o pedido)
            universal-service-id                                     ; OBR-4: Código universal do serviço requisitado (e.g., código do teste)
            priority                                                 ; OBR-5: Prioridade do pedido (opcional)
            requested-datetime                                       ; OBR-6: Data e hora da solicitação
            observation-datetime                                     ; OBR-7: Data e hora da observação
            observation-end-datetime                                 ; OBR-8: Data e hora de conclusão da observação
            collection-volume                                        ; OBR-9: Volume da amostra coletada (opcional)
            collector-identifier                                     ; OBR-10: Identificador da pessoa que coletou a amostra (opcional)
            specimen-action-code                                     ; OBR-11: Código de ação para a amostra (opcional)
            danger-code                                              ; OBR-12: Código de risco (opcional)
            relevant-clinical-info                                   ; OBR-13: Informações clínicas relevantes (opcional)
            specimen-received-datetime                               ; OBR-14: Data e hora em que a amostra foi recebida
            specimen-source                                          ; OBR-15: Origem da amostra (e.g., sangue, urina)
            ordering-provider                                        ; OBR-16: Identificador do provedor que fez o pedido
            order-callback-phone                                     ; OBR-17: Telefone para retorno (opcional)
            placer-field-1                                           ; OBR-18: Campo adicional para identificador do pedido (opcional)
            placer-field-2                                           ; OBR-19: Segundo campo adicional para identificador do pedido (opcional)
            filler-field-1                                           ; OBR-20: Campo adicional para identificador preenchido (opcional)
            filler-field-2                                           ; OBR-21: Segundo campo adicional para identificador preenchido (opcional)
            results-rpt-status-chg-datetime                          ; OBR-22: Data e hora da mudança de status do relatório de resultados
            charge-to-practice                                       ; OBR-23: Cobrança relacionada ao procedimento (opcional)
            diagnostic-service-id                                    ; OBR-24: Identificador do serviço de diagnóstico (e.g., "RAD" para radiologia)
            result-status                                            ; OBR-25: Status do resultado (opcional)
            parent-result                                            ; OBR-26: Resultado pai (opcional, quando este resultado depende de outro)
            quantity-timing                                          ; OBR-27: Quantidade e tempo do pedido (opcional)
            result-copies-to                                         ; OBR-28: Destinatários de cópias dos resultados (opcional)
            parent                                                   ; OBR-29: Informações sobre o pedido pai (opcional)
            transportation-mode                                      ; OBR-30: Modo de transporte da amostra (opcional)
            reason-for-study                                         ; OBR-31: Razão para o estudo ou observação (opcional)
            principal-result-interpreter                             ; OBR-32: Intérprete principal dos resultados (opcional)
            assistant-result-interpreter                             ; OBR-33: Assistente do intérprete dos resultados (opcional)
            technician                                               ; OBR-34: Técnico responsável (opcional)
            transcriptionist                                         ; OBR-35: Transcritor dos resultados (opcional)
            scheduled-datetime                                       ; OBR-36: Data e hora agendada para a observação (opcional)
            number-of-samples                                        ; OBR-37: Número de amostras coletadas (opcional)
            transport-logistics                                      ; OBR-38: Informações sobre a logística de transporte (opcional)
            transport-arrangement                                    ; OBR-39: Arranjos para transporte (opcional)
            transport-arranged                                       ; OBR-40: Quem arranjou o transporte (opcional)
            escort-required                                          ; OBR-41: Acompanhamento necessário (opcional)
            planned-patient-transport                                ; OBR-42: Transporte planejado para o paciente (opcional)
            procedure-code                                           ; OBR-43: Código do procedimento (opcional)
            procedure-code-modifier                                  ; OBR-44: Modificador do código do procedimento (opcional)
            placer-supplemental-service-info                         ; OBR-45: Informações suplementares do serviço requisitado (opcional)
            filler-supplemental-service-info                         ; OBR-46: Informações suplementares do serviço preenchido (opcional)
            medically-necessary-dupl-procedure-reason                ; OBR-47: Razão para a duplicação médica do procedimento (opcional)
            result-handling                                          ; OBR-48: Como os resultados devem ser tratados (opcional)
            ])
