(ns hl7-parser-clojure.segments.dsc)

(defrecord DSC
  [continuation-pointer       ; DSC-1: Ponteiro de continuação (usado para identificar a posição na sequência de dados)
   continuation-style         ; DSC-2: Estilo de continuação (opcional, define como a continuação será realizada)
   ])
