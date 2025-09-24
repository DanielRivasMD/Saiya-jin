;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctlcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctlcmd.edn")

(defn lctlcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control - Command Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{:action "jump prev buffer", :exec ["goto_previous_buffer"], :program "helix-common"}
                    {:action "jump prev tab",    :exec ["prevTab"],              :program "lazygit"}
                    {:action "jump prev buffer", :exec ["PreviousTab"],          :program "micro"}]}   [:!TC#Pleft_arrow  [:!Tb] [:term]]
    ^{:doc/actions [{:action "jump next buffer", :exec ["goto_next_buffer"],     :program "helix-common"}
                    {:action "jump next tab",    :exec ["nextTab"],              :program "lazygit"}
                    {:action "jump next buffer", :exec ["NextTab"],              :program "micro"}]}   [:!TC#Pright_arrow [:!Tf] [:term]]
    ^{:doc/actions [{:action "increment number", :exec ["increment"],            :program "helix-common"}
                    {:action "jump prev block",  :exec ["prevBlock-alt2"],       :program "lazygit"}]} [:!TC#Pup_arrow    [:!Tn] [:term]]
    ^{:doc/actions [{:action "decrement number", :exec ["decrement"],            :program "helix-common"}
                    {:action "jump next block",  :exec ["nextBlock-alt2"],       :program "lazygit"}]} [:!TC#Pdown_arrow  [:!Tp] [:term]]

    ^{:doc/actions [{}]} [:!TCS#Pleft_arrow  [:!TCSleft_arrow]]
    ^{:doc/actions [{}]} [:!TCS#Pright_arrow [:!TCSright_arrow]]
    ^{:doc/actions [{}]} [:!TCS#Pup_arrow    [:!TCSup_arrow]]
    ^{:doc/actions [{}]} [:!TCS#Pdown_arrow  [:!TCSdown_arrow]]

  ; technical glyphs
    ^{:doc/actions [{}]} [:!TC#Popen_bracket   [:!TCopen_bracket]]
    ^{:doc/actions [{}]} [:!TC#Pclose_bracket  [:!TCclose_bracket]]
    ^{:doc/actions [{}]} [:!TC#Psemicolon      [:!TCsemicolon]]
    ^{:doc/actions [{}]} [:!TC#Pquote          [:!TCquote]]
    ^{:doc/actions [{}]} [:!TC#Pbackslash      [:!TCbackslash]]
    ^{:doc/actions [{}]} [:!TC#Pcomma          [:!TCcomma]]
    ^{:doc/actions [{}]} [:!TC#Pperiod         [:!TCperiod]]
    ^{:doc/actions [{}]} [:!TC#Pslash          [:!TCslash]]

    ^{:doc/actions [{}]} [:!TCS#Popen_bracket  [:!TCSopen_bracket]]
    ^{:doc/actions [{}]} [:!TCS#Pclose_bracket [:!TCSclose_bracket]]
    ^{:doc/actions [{}]} [:!TCS#Psemicolon     [:!TCSsemicolon]]
    ^{:doc/actions [{}]} [:!TCS#Pquote         [:!TCSquote]]
    ^{:doc/actions [{}]} [:!TCS#Pbackslash     [:!TCSbackslash]]
    ^{:doc/actions [{}]} [:!TCS#Pcomma         [:!TCScomma]]
    ^{:doc/actions [{}]} [:!TCS#Pperiod        [:!TCSperiod]]
    ^{:doc/actions [{}]} [:!TCS#Pslash         [:!TCSslash]]

  ; action glyphs
    ^{:doc/actions [{:action "close tab",    :exec [":buffer-close"],           :program "helix-common"}
                    {:action "close tab",    :exec ["Quit"],                    :program "micro"}]} [:!TC#Pdelete_or_backspace [:!Tl] [:term]]
    ^{:doc/actions [{:action "close others", :exec ["wonly"],                   :program "helix-common"}
                    {:action "close others", :exec ["Unsplit"],                 :program "micro"}]} [:!TC#Preturn_or_enter     [:!Tg] [:term]]
    ^{:doc/actions [{:action "split right",  :exec ["vsplit"],                  :program "helix-common"}
                    {:action "split right",  :exec ["VSplit"],                  :program "micro"}]} [:!TC#Pright_shift         [:!Tv] [:term]]
    ^{:doc/actions [{:action "split down",   :exec ["hsplit"],                  :program "helix-common"}
                    {:action "split down",   :exec ["HSplit"],                  :program "micro"}]} [:!TC#Pright_option        [:!Th] [:term]]
    ^{:doc/actions [{:action "close window", :exec ["wclose"],                  :program "helix-common"}
                    {:action "close window", :exec ["Unsplit"],                 :program "micro"}]} [:!TC#Pright_command       [:!Tj] [:term]]
    ^{:doc/actions [{:action "last file",    :exec ["goto_last_accessed_file"], :program "helix-common"}
                    {:action "open preview"  :exec []                           :program "broot"}]} [:!TC#Pspacebar            [:!To] [:term]]

    ^{:doc/actions [{}]} [:!TCS#Pdelete_or_backspace [:!TCSdelete_or_backspace]]
    ^{:doc/actions [{}]} [:!TCS#Preturn_or_enter     [:!TCSreturn_or_enter]]
    ^{:doc/actions [{}]} [:!TCS#Pright_shift         [:!TCSright_shift]]
    ^{:doc/actions [{}]} [:!TCS#Pright_option        [:!TCSright_option]]
    ^{:doc/actions [{}]} [:!TCS#Pright_command       [:!TCSright_command]]
    ^{:doc/actions [{}]} [:!TCS#Pspacebar            [:!TCSspacebar]]

  ; numeric glyphs
    ^{:doc/actions [{}]} [:!TC#P1 [:!TC1]]
    ^{:doc/actions [{}]} [:!TC#P2 [:!TC2]]
    ^{:doc/actions [{}]} [:!TC#P3 [:!TC3]]
    ^{:doc/actions [{}]} [:!TC#P4 [:!TC4]]
    ^{:doc/actions [{}]} [:!TC#P5 [:!TC5]]
    ^{:doc/actions [{}]} [:!TC#P6 [:!TC6]]
    ^{:doc/actions [{}]} [:!TC#P7 [:!TC7]]
    ^{:doc/actions [{}]} [:!TC#P8 [:!TC8]]
    ^{:doc/actions [{}]} [:!TC#P9 [:!TC9]]
    ^{:doc/actions [{}]} [:!TC#P0 [:!TC0]]
    ^{:doc/actions [{}]} [:!TC#Phyphen [:!TChyphen]]
    ^{:doc/actions [{}]} [:!TC#Pequal_sign [:!TCequal_sign]]

    ^{:doc/actions [{}]} [:!TCS#P1 [:!TCS1]]
    ^{:doc/actions [{}]} [:!TCS#P2 [:!TCS2]]
    ^{:doc/actions [{}]} [:!TCS#P3 [:!TCS3]]
    ^{:doc/actions [{}]} [:!TCS#P4 [:!TCS4]]
    ^{:doc/actions [{}]} [:!TCS#P5 [:!TCS5]]
    ^{:doc/actions [{}]} [:!TCS#P6 [:!TCS6]]
    ^{:doc/actions [{}]} [:!TCS#P7 [:!TCS7]]
    ^{:doc/actions [{}]} [:!TCS#P8 [:!TCS8]]
    ^{:doc/actions [{}]} [:!TCS#P9 [:!TCS9]]
    ^{:doc/actions [{}]} [:!TCS#P0 [:!TCS0]]
    ^{:doc/actions [{}]} [:!TCS#Phyphen [:!TCShyphen]]
    ^{:doc/actions [{}]} [:!TCS#Pequal_sign [:!TCSequal_sign]]

  ; alphabetic glyphs
    ^{:doc/actions [{}]} [:!TC#Pa [:!TCa]]
    ^{:doc/actions [{}]} [:!TC#Pb [:!TCb]]
    ^{:doc/actions [{}]} [:!TC#Pc [:!TCc]]
    ^{:doc/actions [{}]} [:!TC#Pd [:!TCd]]
    ^{:doc/actions [{}]} [:!TC#Pe [:!TCe]]
    ^{:doc/actions [{}]} [:!TC#Pf [:!TCf]]
    ^{:doc/actions [{}]} [:!TC#Pg [:!TCg]]
    ^{:doc/actions [{}]} [:!TC#Ph [:!TCh]]
    ^{:doc/actions [{}]} [:!TC#Pi [:!TCi]]
    ^{:doc/actions [{}]} [:!TC#Pj [:!TCj]]
    ^{:doc/actions [{}]} [:!TC#Pk [:!TCk]]
    ^{:doc/actions [{}]} [:!TC#Pl [:!TCl]]
    ^{:doc/actions [{}]} [:!TC#Pm [:!TCm]]
    ^{:doc/actions [{}]} [:!TC#Pn [:!TCn]]
    ^{:doc/actions [{}]} [:!TC#Po [:!TCo]]
    ^{:doc/actions [{}]} [:!TC#Pp [:!TCp]]
    ^{:doc/actions [{}]} [:!TC#Pq [:!TCq]]
    ^{:doc/actions [{}]} [:!TC#Pr [:!TCr]]
    ^{:doc/actions [{}]} [:!TC#Ps [:!TCs]]
    ^{:doc/actions [{}]} [:!TC#Pt [:!TCt]]
    ^{:doc/actions [{}]} [:!TC#Pu [:!TCu]]
    ^{:doc/actions [{}]} [:!TC#Pv [:!TCv]]
    ^{:doc/actions [{}]} [:!TC#Pw [:!TCw]]
    ^{:doc/actions [{}]} [:!TC#Px [:!TCx]]
    ^{:doc/actions [{}]} [:!TC#Py [:!TCy]]
    ^{:doc/actions [{}]} [:!TC#Pz [:!TCz]]
    ^{:doc/actions [{}]} [:!TC#Pright_control [:!TCright_control]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctlcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
