(ns hl7-parser-clojure.core
  #_{:clj-kondo/ignore [:unused-namespace]}
  (:require
   [hl7-parser-clojure.message-factory :refer [create-msh create-msa create-err create-qak create-dsc create-dsp create-qrf create-qrd build-hl7-message segment-to-string]]
   [cheshire.core :as json]
   [hl7-parser-clojure.message-parser :refer [parse-hl7-message]]
   [clojure.java.io :as io]))

(def qry-message "MSH|^~\\&|||||20240823103911||QRY^Q02|1|P|2.3.1||||||ASCII|||
                  \rQRF||||||RCT|COR|ALL|||2|||RD|004|OTH|||T|")

(def oru-message "MSH|^~\\&|||||20240418123839||ORU^R01|5|P|2.3.1||||0||ASCII|||
                  \rPID|1||||GEORGINA PETRONILHA DE MELO|||F||||||||||||||||||FEMININA L 02|||||
                  \rOBR|1|GEOGINA|3725|^|N|20240418105847|20240418105604|20240418105604||1^13||||20240418105604|Soro|||||||||||||||||||||||||||||||||
                  \rOBX|1|NM||CK NAC UV AA líquida|61.131959|U/L|-|N|||F||61.131959|20240418112956|||0||
                  \rOBX|2|NM||Creatinina cinética AA líquida - Tca. compensada|0.962758|mg/dL|-|N|||F||0.962758|20240418063201|||0|SLP|")

(def qck-message
  (build-hl7-message [(segment-to-string (assoc (create-msh "|" "^~\\&" "" "" "" "" "20240823103911" "" "QCK^Q02" "1" "P" "2.3.1") :__type__ "MSH"))
                      (segment-to-string (assoc (create-msa "AA" "1" "Message accepted") :__type__ "MSA"))
                      (segment-to-string (assoc (create-err "0") :__type__ "ERR"))
                      (segment-to-string (assoc (create-qak "SR" "OK") :__type__ "QAK"))]))

(def dsr-message
  (build-hl7-message [(segment-to-string (assoc (create-msh "|" "^~\\&" "" "" "" "" "20241001172113" "" "DSR^Q03" "1" "P" "2.3.1") :__type__ "MSH"))
                      (segment-to-string (assoc (create-msa "AA" "1" "Message accepted") :__type__ "MSA"))
                      (segment-to-string (assoc (create-err "0") :__type__ "ERR"))
                      (segment-to-string (assoc (create-qak "SR" "OK") :__type__ "QAK"))
                      (segment-to-string (assoc (create-qrd "20241001172113" "R" "D" "12" "20240830" "OTH" "T") :__type__ "QRD"))
                      (segment-to-string (assoc (create-qrf) :__type__ "QRF"))
                      (segment-to-string (assoc (create-dsp "1" "John Doe") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "2" "2^27") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "3" "John Doe") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "4" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "5" "M") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "6" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "7" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "8" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "9" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "10" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "11" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "12" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "13" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "15" "outpatient") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "16" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "17" "own") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "18" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "19" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "20" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "21" "20240830") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "22" "3") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "23" "20241001172113") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "24" "N") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "25" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "26" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "27" "") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "28" "Dept1") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "29" "ALB") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "30" "CREA") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsp "31" "GLI") :__type__ "DSP"))
                      (segment-to-string (assoc (create-dsc) :__type__ "DSC"))]))

(defn -main []
  (let [parsed-qry-message (parse-hl7-message qry-message)
        parsed-oru-message (parse-hl7-message oru-message)
        output-file "output.txt"]

    (spit output-file (str "QCK Message:\n" qck-message "\n\n") :append true)

    (spit output-file (str "DSR Message:\n" dsr-message "\n\n") :append true)

    (spit output-file (str "Parsed QRY Message in JSON:\n" (json/generate-string parsed-qry-message {:pretty true}) "\n\n") :append true)

    (spit output-file (str "Parsed ORU Message in JSON:\n" (json/generate-string parsed-oru-message {:pretty true}) "\n\n") :append true)

    (println "Output written to" output-file)))

(-main)
