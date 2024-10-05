(ns hl7-parser-clojure.message-factory
  (:require [clojure.string :join :refer [join]]
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

;; Função para criar o segmento MSH
(defn create-msh
  [field-separator encoding-characters sending-application sending-facility
   receiving-application receiving-facility datetime-of-message security
   message-type message-control-id processing-id version-id]
  (->MSH field-separator encoding-characters sending-application sending-facility
         receiving-application receiving-facility datetime-of-message security
         message-type message-control-id processing-id version-id nil nil nil nil
         nil nil nil nil nil nil nil nil nil nil))

;; Função para criar o segmento PID
(defn create-pid
  [set-id patient-id patient-name datetime-of-birth sex address]
  (->PID set-id patient-id nil nil patient-name nil datetime-of-birth sex nil nil address nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil))

;; Função para criar o segmento OBR
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

;; Função para criar o segmento OBX
(defn create-obx
  [set-id value-type observation-identifier observation-value units references-range abnormal-flags observation-result-status effective-datetime]
  (->OBX set-id value-type observation-identifier nil observation-value units references-range abnormal-flags nil nil observation-result-status effective-datetime nil nil nil nil nil nil nil))

;; Função para criar o segmento MSA
(defn create-msa
  [acknowledgment-code message-control-id text-message]
  (->MSA acknowledgment-code message-control-id text-message nil nil nil))

;; Função para criar o segmento QAK
(defn create-qak
  [query-tag query-response-status]
  (->QAK query-tag query-response-status nil))

;; Função para criar o segmento QRD
#_{:clj-kondo/ignore [:unused-binding]}
(defn create-qrd
  [query-datetime query-format-code query-priority query-id who-subject-filter when-data-start-datetime]
  (->QRD query-datetime query-format-code query-priority query-id nil nil nil who-subject-filter nil nil nil nil))

;; Função para criar o segmento QRF
(defn create-qrf
  [which-date-time-qualifier which-date-time-status other-qry-subject-filter]
  (->QRF nil nil nil nil nil other-qry-subject-filter which-date-time-qualifier which-date-time-status nil))

;; Função para criar o segmento DSP
(defn create-dsp
  [set-id-dsp data-line]
  (->DSP set-id-dsp nil data-line nil nil))

;; Função para criar o segmento DSC
(defn create-dsc
  [continuation-pointer]
  (->DSC continuation-pointer nil))

;; Função para criar o segmento ERR
(defn create-err
  [error-code-and-location error-location hl7-error-code severity application-error-code diagnostic-information user-message]
  (->ERR error-code-and-location
         error-location
         hl7-error-code
         severity
         application-error-code
         diagnostic-information
         user-message))

;; Função para converter os segmentos em uma string HL7
(defn build-hl7-message
  [segments]
  (join "\r" segments))

;; Função para converter o segmento em uma string
(defn segment-to-string
  [segment]
  (join "|" (map str (vals segment))))
