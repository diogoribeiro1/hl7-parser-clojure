(ns hl7-parser-clojure.segments.dsp)

(defrecord DSP
  [set-id-dsp                                               ; DSP-1: ID do conjunto (sequencial para identificar cada segmento DSP na mensagem)
   display-level                                            ; DSP-2: Nível de exibição (opcional, define o nível hierárquico dos dados)
   data-line                                                ; DSP-3: Linha de dados a ser exibida (contém os dados da resposta em formato textual)
   logical-breakpoint                                       ; DSP-4: Ponto de interrupção lógico (opcional, usado para indicar o final de um grupo lógico de dados)
   result-id                                                ; DSP-5: Identificador do resultado (opcional, identifica os dados dentro da resposta)
   ])
