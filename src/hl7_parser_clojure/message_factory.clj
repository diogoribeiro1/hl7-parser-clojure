(ns hl7-parser-clojure.message-factory
  (:require [clojure.string :refer [join]]
            [hl7-parser-clojure.segments.dsc :refer [->DSC]]
            [hl7-parser-clojure.segments.dsp :refer [->DSP]]
            [hl7-parser-clojure.segments.err :refer [->ERR]]
            [hl7-parser-clojure.segments.msa :refer [->MSA]]
            [hl7-parser-clojure.segments.msh :refer [->MSH]]
            [hl7-parser-clojure.segments.obr :refer [->OBR]]
            [hl7-parser-clojure.segments.obx :refer [->OBX]]
            [hl7-parser-clojure.segments.pid :refer [->PID]]
            [hl7-parser-clojure.segments.qak :refer [->QAK]]
            [hl7-parser-clojure.segments.qrd :refer [->QRD]]
            [hl7-parser-clojure.segments.qrf :refer [->QRF]]))

(defn create-msh
  [field-separator encoding-characters sending-application sending-facility
   receiving-application receiving-facility datetime-of-message security
   message-type message-control-id processing-id version-id]
  (->MSH field-separator encoding-characters sending-application sending-facility
         receiving-application receiving-facility datetime-of-message security
         message-type message-control-id processing-id version-id nil nil nil nil
         nil nil nil nil nil nil nil nil "ASCII" nil))

(defn create-pid
  [set-id patient-id patient-name datetime-of-birth sex address]
  (->PID set-id patient-id nil nil patient-name nil datetime-of-birth sex nil nil address nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil))

(defn create-obr
  [set-id placer-order-number filler-order-number universal-service-id priority requested-datetime observation-datetime
   observation-end-datetime specimen-received-datetime specimen-source ordering-provider diagnostic-service-id procedure-code]
  #_{:clj-kondo/ignore [:invalid-arity]}
  (->OBR set-id
         placer-order-number
         filler-order-number
         universal-service-id
         priority
         requested-datetime
         observation-datetime
         observation-end-datetime
         nil
         nil
         nil
         nil
         nil
         specimen-received-datetime
         specimen-source
         ordering-provider
         nil
         nil
         nil
         nil
         nil
         nil
         diagnostic-service-id
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         nil
         procedure-code
         nil
         nil
         nil
         nil
         nil))

(defn create-obx
  [set-id value-type observation-identifier observation-value units references-range abnormal-flags observation-result-status effective-datetime]
  (->OBX set-id value-type observation-identifier nil observation-value units references-range abnormal-flags nil nil observation-result-status effective-datetime nil nil nil nil nil nil nil))

(defn create-msa
  [acknowledgment-code message-control-id text-message]
  (->MSA acknowledgment-code message-control-id text-message nil nil nil))

(defn create-qak
  [query-tag query-response-status]
  (->QAK query-tag query-response-status nil))

(defn create-qrd
  [query-datetime query-format-code query-priority who-subject-filter when-data-start-datetime what-department-data-code query-results-level]
  (->QRD query-datetime
         query-format-code
         query-priority
         "12"                     ; QRD-4: Identificador da consulta (fixo para este exemplo)
         nil                      ; QRD-5: Tipo de resposta diferida (opcional)
         nil                      ; QRD-6: Data e hora para resposta diferida (opcional)
         nil                      ; QRD-7: Quantidade limitada de solicitação (opcional)
         who-subject-filter       ; QRD-8: Quem é o objeto do filtro
         when-data-start-datetime ; QRD-9: Qual é o assunto do filtro
         what-department-data-code ; QRD-10: Código do departamento
         nil                      ; QRD-11: Qualificação de valor do código de dados (opcional)
         query-results-level))    ; QRD-12: Nível dos resultados da consulta

(defn create-qrf
  []
  (->QRF nil nil nil nil nil "RCT" "COR" "ALL" nil))

(defn create-err
  [error-code]
  (->ERR error-code nil nil nil nil nil nil))

(defn create-dsp
  [set-id data-line]
  (->DSP set-id nil data-line nil nil))

(defn create-dsc
  []
  (->DSC nil nil))

(defn build-hl7-message
  [segments]
  (join "\r" segments))

(defn segment-to-string
  [segment]
  (let [segment-type (-> segment :__type__)
        segment-values
        (case segment-type
          "MSH" [(or (:field-separator segment) "|")
                 (or (:encoding-characters segment) "^~\\&")
                 (or (:sending-application segment) "")
                 (or (:sending-facility segment) "")
                 (or (:receiving-application segment) "")
                 (or (:receiving-facility segment) "")
                 (or (:datetime-of-message segment) "")
                 (or (:security segment) "")
                 (or (:message-type segment) "")
                 (or (:message-control-id segment) "")
                 (or (:processing-id segment) "")
                 (or (:version-id segment) "")
                 ""
                 ""
                 ""
                 ""
                 ""
                 (or (:country-code segment) "ASCII")
                 ""
                 ""
                 ""]
          "MSA" [(or (:acknowledgment-code segment) "")
                 (or (:message-control-id segment) "")
                 (or (:text-message segment) "")
                 ""
                 ""
                 (or (:expected-sequence-number segment) "0")]
          "ERR" [(or (:error-code-and-location segment) "0")]
          "QAK" [(or (:query-tag segment) "")
                 (or (:query-response-status segment) "")]
          "QRD" [(or (:datetime-of-message segment) "")
                 (or (:priority segment) "")
                 (or (:who-subject-filter segment) "")
                 (or (:what-subject-filter segment) "")
                 ""
                 ""
                 ""
                 (or (:who-subject-filter segment) "")
                 (or (:when-data-start-datetime segment) "")
                 ""
                 ""
                 (or (:what-user-qualifier segment) "T")]
          "QRF" ["" "" "" "" "" (:other-qry-subject-filter segment) (:which-date-time-qualifier segment) (:which-date-time-status segment) (:date-selection-criteria segment)]
          "DSP" [(or (:set-id-dsp segment) "")
                 ""
                 (or (:data-line segment) "")
                 ""
                 ""
                 ""]
          "DSC" [""
                 ""]

          (map str (vals segment)))]
    (join "|" (cons segment-type segment-values))))
