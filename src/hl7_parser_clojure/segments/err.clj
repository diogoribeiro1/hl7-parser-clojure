(ns hl7-parser-clojure.segments.err)

(defrecord ERR
           [error-code-and-location   ; ERR-1: Localização e código do erro
            error-location            ; ERR-2: Localização detalhada do erro
            hl7-error-code            ; ERR-3: Código de erro especificado pelo HL7
            severity                  ; ERR-4: Nível de severidade (e.g., "W", "E", "I")
            application-error-code    ; ERR-5: Código de erro definido pela aplicação
            diagnostic-information    ; ERR-6: Informação diagnóstica adicional
            user-message])            ; ERR-7: Mensagem para o usuário
