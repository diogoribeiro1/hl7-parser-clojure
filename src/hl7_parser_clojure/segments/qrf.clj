(ns hl7-parser-clojure.segments.qrf)

(defrecord QRF
  [where-subject-filter                                     ; QRF-1: Filtro do assunto da consulta (especifica os critérios de filtragem, e.g., "DIAGNOSIS")
   when-data-start-datetime                                 ; QRF-2: Data e hora de início para os dados (opcional, define o período de filtragem)
   when-data-end-datetime                                   ; QRF-3: Data e hora de término para os dados (opcional)
   who-subject-filter                                       ; QRF-4: Filtro de quem é o sujeito da consulta (opcional, e.g., identificador do paciente)
   what-user-qualifier                                      ; QRF-5: Qualificador do usuário para a consulta (opcional)
   other-qry-subject-filter                                 ; QRF-6: Outros filtros de assunto para a consulta (opcional)
   which-date-time-qualifier                                ; QRF-7: Qualificador de data/hora (opcional, e.g., "ORD" para data do pedido)
   which-date-time-status                                   ; QRF-8: Status de data/hora (opcional)
   date-selection-criteria                                  ; QRF-9: Critérios de seleção de data (opcional, e.g., "GT" para maior que)
   ])

