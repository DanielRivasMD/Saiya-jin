;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctlcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctlcmd.edn")

(def hc-prev-tab ["goto_previous_buffer"])
(def lg-prev-tab ["prevTab"])
(def mc-prev-tab ["PreviousTab"])
(def ze-move-left ["MovePane \"Left\";"])
(def hc-next-tab ["goto_next_buffer"])
(def lg-next-tab ["nextTab"])
(def mc-next-tab ["NextTab"])
(def ze-move-rigth ["MovePane \"Right\";"])
(def hc-inc ["increment"])
(def lg-prev-block ["prevBlock-alt2"])
(def ze-move-up ["MovePane \"Up\";"])
(def hc-dec ["decrement"])
(def lg-next-block ["nextBlock-alt2"])
(def ze-move-down ["MovePane \"Down\";"])
(def hc-close-tab [":buffer-close"])
(def mc-close-tab ["Quit"])
(def hc-unsplit ["wonly"])
(def mc-unsplit ["Unsplit"])
(def hc-vsplit ["vsplit"])
(def mc-vsplit ["VSplit"])
(def hc-hsplit ["hsplit"])
(def mc-hsplit ["HSplit"])
(def hc-close-split ["wclose"])
(def mc-close-split ["Unsplit"])
(def hc-last-tab ["goto_last_accessed_file"])
(def br-toggle-preview [])

(defn lctlcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump prev buffer",   :exec hc-prev-tab}
                    {:program c/lg,    :action "jump prev tab",      :exec lg-prev-tab}
                    {:program c/mc,    :action "jump prev buffer",   :exec mc-prev-tab}
                    {:program c/ze,    :action "move left",          :exec ze-move-left}]}         [:!TC#Pleft_arrow  [:!Tb] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "jump next buffer",   :exec hc-next-tab}
                    {:program c/lg,    :action "jump next tab",      :exec lg-next-tab}
                    {:program c/mc,    :action "jump next buffer",   :exec mc-next-tab}
                    {:program c/ze,    :action "move right",         :exec ze-move-rigth}]}        [:!TC#Pright_arrow [:!Tf] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "increment number",   :exec hc-inc}
                    {:program c/lg,    :action "jump prev block",    :exec lg-prev-block}
                    {:program c/ze,    :action "move up",            :exec ze-move-up}]}           [:!TC#Pup_arrow    [:!Tn] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "decrement number",   :exec hc-dec}
                    {:program c/lg,    :action "jump next block",    :exec lg-next-block}
                    {:program c/ze,    :action "move down",          :exec ze-move-down}]}       [:!TC#Pdown_arrow  [:!Tp] [:term]]

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
    ^{:doc/actions [{:program c/hc,    :action "close tab",          :exec hc-close-tab}
                    {:program c/mc,    :action "close tab",          :exec mc-close-tab}]}         [:!TC#Pdelete_or_backspace [:!Tl] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "close others",       :exec hc-unsplit}
                    {:program c/mc,    :action "close others",       :exec mc-unsplit}]}           [:!TC#Preturn_or_enter     [:!Tg] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "split right",        :exec hc-vsplit}
                    {:program c/mc,    :action "split right",        :exec mc-vsplit}]}            [:!TC#Pright_shift         [:!Tv] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "split down",         :exec hc-hsplit}
                    {:program c/mc,    :action "split down",         :exec mc-hsplit}]}            [:!TC#Pright_option        [:!Th] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "close window",       :exec hc-close-split}
                    {:program c/mc,    :action "close window",       :exec mc-close-split}]}       [:!TC#Pright_command       [:!Tj] [:term]]
    ^{:doc/actions [{:program c/hc,    :action "last file",          :exec hc-last-tab}
                    {:program c/br,    :action "open preview"        :exec br-toggle-preview}]}    [:!TC#Pspacebar            [:!To] [:term]]

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
