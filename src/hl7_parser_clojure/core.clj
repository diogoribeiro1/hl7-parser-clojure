(ns hl7-parser-clojure.core
  (:require
   [hl7-parser-clojure.message-factory :refer [create-msh create-msa create-err create-qak build-hl7-message segment-to-string]]
   [cheshire.core :as json]
   [hl7-parser-clojure.message-parser :refer [parse-hl7-message]]))

(def qry-message "MSH|^~\\&|||||20240823103911||QRY^Q02|1|P|2.3.1||||||ASCII|||\
                  \rQRF||||||RCT|COR|ALL|||2|||RD|004|OTH|||T|")

(def oru-message "MSH|^~\\&|||||20240418123839||ORU^R01|5|P|2.3.1||||0||ASCII|||\
                  \rPID|1||||GEORGINA PETRONILHA DE MELO|||F||||||||||||||||||FEMININA L 02|||||\
                  \rOBR|1|GEOGINA|3725|^|N|20240418105847|20240418105604|20240418105604||1^13||||20240418105604|Soro|||||||||||||||||||||||||||||||||\
                  \rOBX|1|NM||CK NAC UV AA líquida|61.131959|U/L|-|N|||F||61.131959|20240418112956|||0||\r...")

;; Criação das mensagens fornecidas
(def qck-message
  (build-hl7-message [#_{:clj-kondo/ignore [:unresolved-var]}
                      (segment-to-string (create-msh "|" "^~\\&" "SendingApp" "SendingFac" "ReceivingApp" "ReceivingFac" "20240823103911" "" "QCK^Q02" "1" "P" "2.3.1"))
                      #_{:clj-kondo/ignore [:unresolved-var]}
                      (segment-to-string (create-msa "AA" "1" "Message accepted"))
                      #_{:clj-kondo/ignore [:unresolved-var]}
                      (segment-to-string (create-err "ERR_CODE" "Field Location" "100" "E" "APP_CODE" "Additional diagnostic information" "User-friendly message"))
                      #_{:clj-kondo/ignore [:unresolved-var]}
                      (segment-to-string (create-qak "SR" "OK"))]))

(defn -main []
  (let [parsed-qry-message (parse-hl7-message qry-message)
        parsed-qck-message (parse-hl7-message qck-message)]

    (println "QCK Message:")
    (println qck-message)

    ;; (println "\nParsed QRY Message in JSON:")
    ;; (println (json/generate-string parsed-qry-message {:pretty true}))

    ;; (println "\nParsed QCK Message in JSON:")
    ;; (println (json/generate-string parsed-qck-message {:pretty true}))
    ))

(-main)
