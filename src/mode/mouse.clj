;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; MOUSE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.mouse
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "mouse.edn")

(defn mouse []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; mouse
  {:des "Zero Mode - mouse"
   :rules
   [:zero-mode
    [:down_arrow {:mkey {:y 1500}}]                                                                  ;
    [:up_arrow {:mkey {:y -1500}}]                                                                   ;
    [:left_arrow {:mkey {:x -1500}}]                                                                 ;
    [:right_arrow {:mkey {:x 1500}}]                                                                 ;
    [:right_shift {:pkey :button1}]                                                              ;

    [:!Tdown_arrow {:mkey {:y 500}}]                                                                 ;
    [:!Tup_arrow {:mkey {:y -500}}]                                                                  ;
    [:!Tleft_arrow {:mkey {:x -500}}]                                                                ;
    [:!Tright_arrow {:mkey {:x 500}}]                                                                ;
    [:!Tright_shift {:pkey :button2}]                                                            ;
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (mouse)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

