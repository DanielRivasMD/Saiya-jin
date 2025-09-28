;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; KEY MOD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns profile.keymod
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "keymod.edn")

(def hi-normal ["normal_mode"])
(def hn-insert ["insert_mode"])
(def hs-normal ["normal_mode"])

(defn keymod []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; TODO: update
; key modifiers
  {:des "Modifier key remappings"
   :rules
   [; quit
    [:!C#Pq [:!Cq ["command-q" 0]] ["command-q" 1]]                                                  ;
    [:!C#Pq ["command-q" 1] nil {:delayed {:invoked ["command-q" 0] :canceled ["commandq" 0]}}]      ;

  ; esc
  ; TODO: CHANGE `term` pop-up new
    [:#Pescape :!EWright_shift nil {:alone [:!EW#Pnon_us_pound]}]                                    ; menu search.

  ; zero
    [:#Pkeypad_num_lock :!EQright_shift nil {:alone [:keypad_num_lock]}]                             ; mouse control

  ; launcher
    [:#Ptab :!OTCright_shift nil {:alone :tab}]                                                      ; spectacle control, speak & app launcher

; TODO: annotate escape zellij
  ; joker
    [:##caps_lock :!EWright_command nil {:alone :escape}]                                            ; esc, language server protocol

; TODO: write an alone-key detector in babel interpret
  ; grave
    ; ^{:doc/actions [{:program c/hi, :action "mode normal", :exec hi-normal}
    ;                 {:program c/hn, :action "mode insert", :exec hn-insert}
    ;                 {:program c/hs, :action "mode normal", :exec hs-normal}]} [:#Pright_control :right_control nil {:alone [:f13]}]

    [:#Pright_control :right_control nil {:alone [:f13]}]

  ; lefts
    [:#Pleft_control :left_control nil {:alone [:!EWQ#Onon_us_pound]}]                               ; alfred window switcher
    [:#Pleft_option :left_option nil {:alone [:!Otab]}]                                              ; global in-app window cycler.
    [:#Pleft_command :left_command nil {:alone [:!Ctab]}]                                            ; switch most recent app

  ; rights
    [:#Pright_shift :right_shift nil {:alone [:!R#Pnon_us_pound]}]                                   ; `browser` input. `term` history edit
    [:#Pright_option :right_option nil {:alone [:!E#Pnon_us_pound]}]                                 ; alfred paste
    [:#Pright_command :right_command nil {:alone [:!Q#Pnon_us_pound]}]                               ; alfred clipboard select
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (keymod)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
