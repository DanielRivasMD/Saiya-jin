;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SHIFT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rshift
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]))

(def out-file "rshift.edn")

(defn rshift []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Shift Mode"
   :rules
   [;
  ; [:!R#Pleft_arrow []]
  ; [:!R#Pright_arrow []]
  ; [:!R#Pup_arrow []]
  ; [:!R#Pdown_arrow []]

    ^{:doc/actions [{:program c/hc,    :action "delete line start", :exec ["kill_to_line_start"]}
                    {:program c/mc,    :action "delete line start", :exec ["SelectToStartOfLine,Delete"]}]} [:!R#Pleft_command [:!Wu]]
  ; [:!Rleft_option []]
    ^{:doc/actions  [{:program c/hc,   :action "delete prev word", :exec ["delete_word_backward"]}
                     {:program c/mc,   :action "delete prev word", :exec ["DeleteWordLeft"]}]} [:!R#Pleft_control [:!Ww]]
    ^{:doc/actions [{:program c/hc,    :action "delete prev char", :exec ["delete_char_backward"]}]} [:!R#Pleft_shift [:delete_or_backspace]]                                                         ;
  ; [:!R#Pcaps_lock []]
  ; [:!R#Ptab []]
  ; [:!R#Pkeypad_num_lock []]
  ; [:!R#Pescape []]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
