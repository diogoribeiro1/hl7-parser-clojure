(ns hl7-parser-clojure.segments.qak)

(defrecord QAK
  [query-tag                                                ; QAK-1: Tag da consulta (identificador único da consulta, correlacionado com a consulta original)
   query-response-status                                    ; QAK-2: Status da resposta à consulta (e.g., "OK" para sucesso, "NF" para não encontrado)
   message-query-name                                       ; QAK-3: Nome da consulta (opcional, identifica o tipo de consulta que foi respondida)
   ])
