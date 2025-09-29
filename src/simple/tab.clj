;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TAB => ROTC
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.tab
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "tab.edn")

(defn tab []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; TODO: annotate rectangle hotkeys
; tab
  {:des "Tab Mode"
   :rules
   [;
  ; [:!ROTC#Pleft_arrow []]                                                    ; space             ; system
  ; [:!ROTC#Pright_arrow []]                                                   ; space             ; system
  ; [:!ROTC#Pup_arrow []]                                                      ; menu              ; system
  ; [:!ROTC#Pdown_arrow []]                                                    ; dock              ; system

  ; [:!ROTC#Popen_bracket []]                                                                      ;
  ; [:!ROTC#Pclose_bracket []]                                                                     ;
  ; [:!ROTC#Psemicolon []]                                                                         ;
  ; [:!ROTC#Pquote []]                                                                             ;
  ; [:!ROTC#Pbackslash []]                                                                         ;
  ; [:!ROTC#Pcomma []]                                                              COLLISION      ;
  ; [:!ROTC#Pperiod []]                                                             COLLISION      ;
  ; [:!ROTC#Pslash []]                                                                             ;

    [:!ROTC#Pdelete_or_backspace [c/krotcp_lock]]                      ; control center    ; system
  ; [:!ROTC#Preturn_or_enter []]                                               ; speak             ; system
    [:!ROTC#Pright_shift [:launch "Alacritty"]]
    [:!ROTC#Pright_option [:launch "Arc"]]
    [:!ROTC#Pright_command [:launch "Canary Mail"]]
    [:!ROTC#Pspacebar [c/kwp_us]]                                       ; dictation         ; system

; [:!ROTC#Pkeypad_1 []]                                                                          ;
  ; [:!ROTC#Pkeypad_2 []]                                                                          ;
  ; [:!ROTC#Pkeypad_3 []]                                                                          ;
  ; [:!ROTC#Pkeypad_4 []]                                                                          ;
  ; [:!ROTC#Pkeypad_5 []]                                                                          ;
  ; [:!ROTC#Pkeypad_6 []]                                                                          ;
  ; [:!ROTC#Pkeypad_7 []]                                                                          ;
  ; [:!ROTC#Pkeypad_8 []]                                                                          ;
  ; [:!ROTC#Pkeypad_9 []]                                                                          ;
  ; [:!ROTC#Pkeypad_0 []]                                                                          ;
  ; [:!ROTC#Pkeypad_slash []]                                                                      ;
  ; [:!ROTC#Pkeypad_asterisk []]                                                                   ;

  ; [:!ROTC#P1 []]                                                                                 ;
  ; [:!ROTC#P2 []]                                                                                 ;
  ; [:!ROTC#P3 []]                                                                                 ;
  ; [:!ROTC#P4 []]                                                                                 ;
  ; [:!ROTC#P5 []]                                                                                 ;
  ; [:!ROTC#P6 []]                                                                                 ;
  ; [:!ROTC#P7 []]                                                                                 ;
  ; [:!ROTC#P8 []]                                                                                 ;
  ; [:!ROTC#P9 []]                                                                                 ;
  ; [:!ROTC#P0 []]                                                                                 ;
  ; [:!ROTC#Phyphen []]                                                                            ;
  ; [:!ROTC#Pequal_sign []]                                                                        ;
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (tab)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
