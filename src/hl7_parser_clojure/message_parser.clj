(ns hl7-parser-clojure.message-parser
  (:require [hl7-parser-clojure.segments.msh :refer [->MSH]]
            [hl7-parser-clojure.segments.obr :refer [->OBR]]
            [hl7-parser-clojure.segments.obx :refer [->OBX]]
            [hl7-parser-clojure.segments.pid :refer [->PID]]
            [hl7-parser-clojure.segments.qrf :refer [->QRF]]
            [hl7-parser-clojure.segments.msa :refer [->MSA]]
            [clojure.string :as str]))

(defn safe-nth [coll index]
  (if (>= (count coll) (inc index))
    (nth coll index)
    nil))

(defn parse-segment [segment]
  (let [fields (str/split segment #"\|")
        segment-type (first fields)]
    (case segment-type
      "MSH" (->MSH (safe-nth fields 1) (safe-nth fields 2) (safe-nth fields 3) (safe-nth fields 4)
                   (safe-nth fields 5) (safe-nth fields 6) (safe-nth fields 7) (safe-nth fields 8)
                   (safe-nth fields 9) (safe-nth fields 10) (safe-nth fields 11) (safe-nth fields 12)
                   (safe-nth fields 13) (safe-nth fields 14) (safe-nth fields 15) (safe-nth fields 16)
                   (safe-nth fields 17) (safe-nth fields 18) (safe-nth fields 19) (safe-nth fields 20)
                   (safe-nth fields 21) (safe-nth fields 22) (safe-nth fields 23) (safe-nth fields 24)
                   (safe-nth fields 25) (safe-nth fields 26))
      "PID" (->PID (safe-nth fields 1) (safe-nth fields 2) (safe-nth fields 3) (safe-nth fields 4)
                   (safe-nth fields 5) (safe-nth fields 6) (safe-nth fields 7) (safe-nth fields 8)
                   (safe-nth fields 9) (safe-nth fields 10) (safe-nth fields 11) (safe-nth fields 12)
                   (safe-nth fields 13) (safe-nth fields 14) (safe-nth fields 15) (safe-nth fields 16)
                   (safe-nth fields 17) (safe-nth fields 18) (safe-nth fields 19) (safe-nth fields 20)
                   (safe-nth fields 21) (safe-nth fields 22) (safe-nth fields 23) (safe-nth fields 24)
                   (safe-nth fields 25) (safe-nth fields 26) (safe-nth fields 27) (safe-nth fields 28)
                   (safe-nth fields 29) (safe-nth fields 30) (safe-nth fields 31) (safe-nth fields 32)
                   (safe-nth fields 33) (safe-nth fields 34) (safe-nth fields 35) (safe-nth fields 36)
                   (safe-nth fields 37) (safe-nth fields 38) (safe-nth fields 39))
      "OBR" (->OBR (safe-nth fields 1) (safe-nth fields 2) (safe-nth fields 3) (safe-nth fields 4)
                   (safe-nth fields 5) (safe-nth fields 6) (safe-nth fields 7) (safe-nth fields 8)
                   (safe-nth fields 9) (safe-nth fields 10) (safe-nth fields 11) (safe-nth fields 12)
                   (safe-nth fields 13) (safe-nth fields 14) (safe-nth fields 15) (safe-nth fields 16)
                   (safe-nth fields 17) (safe-nth fields 18) (safe-nth fields 19) (safe-nth fields 20)
                   (safe-nth fields 21) (safe-nth fields 22) (safe-nth fields 23) (safe-nth fields 24)
                   (safe-nth fields 25) (safe-nth fields 26) (safe-nth fields 27) (safe-nth fields 28)
                   (safe-nth fields 29) (safe-nth fields 30) (safe-nth fields 31) (safe-nth fields 32)
                   (safe-nth fields 33) (safe-nth fields 34) (safe-nth fields 35) (safe-nth fields 36)
                   (safe-nth fields 37) (safe-nth fields 38) (safe-nth fields 39) (safe-nth fields 40)
                   (safe-nth fields 41) (safe-nth fields 42) (safe-nth fields 43) (safe-nth fields 44)
                   (safe-nth fields 45) (safe-nth fields 46) (safe-nth fields 47) (safe-nth fields 48))
      "OBX" (->OBX (safe-nth fields 1) (safe-nth fields 2) (safe-nth fields 3) (safe-nth fields 4)
                   (safe-nth fields 5) (safe-nth fields 6) (safe-nth fields 7) (safe-nth fields 8)
                   (safe-nth fields 9) (safe-nth fields 10) (safe-nth fields 11) (safe-nth fields 12)
                   (safe-nth fields 13) (safe-nth fields 14) (safe-nth fields 15) (safe-nth fields 16)
                   (safe-nth fields 17) (safe-nth fields 18) (safe-nth fields 19))
      "QRF" (->QRF (safe-nth fields 1)
                   (safe-nth fields 2)
                   (safe-nth fields 3)
                   (safe-nth fields 4)
                   (safe-nth fields 5)
                   (safe-nth fields 6)
                   (safe-nth fields 7)
                   (safe-nth fields 8)
                   (safe-nth fields 9))
      "MSA" (->MSA (safe-nth fields 1)
                   (safe-nth fields 2)
                   (safe-nth fields 3)
                   (safe-nth fields 4)
                   (safe-nth fields 5)
                   (safe-nth fields 6))
      nil)))

(defn parse-hl7-message [hl7-message]
  (let [segments (str/split hl7-message #"\r")]
    (remove nil? (map parse-segment segments))))            ; Remove os valores nil para evitar segmentos não reconhecidos