;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptctl.edn")

(defn loptctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Control Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{:action "pane left",  :exec ["MoveFocus \"Left\";"], :program "zellij"}]}
    [:!OT#Pleft_arrow  [:!Oj] [:term]]

    ^{:doc/actions [{:action "pane right", :exec ["MoveFocus \"Right\";"], :program "zellij"}]}
    [:!OT#Pright_arrow [:!Ok] [:term]]

    ^{:doc/actions [{:action "pane up",    :exec ["MoveFocus \"Up\";"], :program "zellij"}]}
    [:!OT#Pup_arrow    [:!Ol] [:term]]

    ^{:doc/actions [{:action "pane down",  :exec ["MoveFocus \"Down\";"], :program "zellij"}]}
    [:!OT#Pdown_arrow  [:!Om] [:term]]

    ^{:doc/actions [{}]} [:!OTS#Pleft_arrow  [:!OTSleft_arrow]  [:term]]
    ^{:doc/actions [{}]} [:!OTS#Pright_arrow [:!OTSright_arrow] [:term]]
    ^{:doc/actions [{}]} [:!OTS#Pup_arrow    [:!OTSup_arrow]    [:term]]
    ^{:doc/actions [{}]} [:!OTS#Pdown_arrow  [:!OTSdown_arrow]  [:term]]

  ; technical glyphs
    ^{:doc/actions [{}]} [:!OT#Popen_bracket   [:!OTopen_bracket]]
    ^{:doc/actions [{}]} [:!OT#Pclose_bracket  [:!OTclose_bracket]]
    ^{:doc/actions [{}]} [:!OT#Psemicolon      [:!OTsemicolon]]
    ^{:doc/actions [{}]} [:!OT#Pquote          [:!OTquote]]
    ^{:doc/actions [{}]} [:!OT#Pbackslash      [:!OTbackslash]]
    ^{:doc/actions [{}]} [:!OT#Pcomma          [:!OTcomma]]
    ^{:doc/actions [{}]} [:!OT#Pperiod         [:!OTperiod]]
    ^{:doc/actions [{}]} [:!OT#Pslash          [:!OTslash]]

    ^{:doc/actions [{}]} [:!OTS#Popen_bracket  [:!OTSopen_bracket]]
    ^{:doc/actions [{}]} [:!OTS#Pclose_bracket [:!OTSclose_bracket]]
    ^{:doc/actions [{}]} [:!OTS#Psemicolon     [:!OTSsemicolon]]
    ^{:doc/actions [{}]} [:!OTS#Pquote         [:!OTSquote]]
    ^{:doc/actions [{}]} [:!OTS#Pbackslash     [:!OTSbackslash]]
    ^{:doc/actions [{}]} [:!OTS#Pcomma         [:!OTScomma]]
    ^{:doc/actions [{}]} [:!OTS#Pperiod        [:!OTSperiod]]
    ^{:doc/actions [{}]} [:!OTS#Pslash         [:!OTSslash]]

  ; action glyphs
    ^{:doc/actions [{:action "pane close",       :exec ["CloseFocus;"], :program "zellij"}]}
    [:!OT#Pdelete_or_backspace :!Oo [:term]]

    ^{:doc/actions [{:action "pane focus",       :exec ["ToggleFocusFullscreen;"], :program "zellij"}]}
    [:!OT#Preturn_or_enter     :!Op [:term]]

    ^{:doc/actions [{:action "pane split right", :exec ["NewPane \"Right\";"], :program "zellij"}]}
    [:!OT#Pright_shift         :!Oq [:term]]

    ^{:doc/actions [{:action "pane split down",  :exec ["NewPane \"Down\";"], :program "zellij"}]}
    [:!OT#Pright_option        :!Or [:term]]

    ^{:doc/actions [{:action "pane rename",      :exec ["SwitchToMode \"RenamePane\"; PaneNameInput 0;"], :program "zellij"}]}
    [:!OT#Pright_command       :!Os [:term]]

    ^{:doc/actions [{:action "pane jump back",   :exec ["SwitchFocus;"], :program "zellij"}]}
    [:!OT#Pspacebar            :!Ot [:term]]

    ^{:doc/actions [{}]} [:!OTS#Pdelete_or_backspace [:!OTSdelete_or_backspace]]
    ^{:doc/actions [{}]} [:!OTS#Preturn_or_enter     [:!OTSreturn_or_enter]]
    ^{:doc/actions [{}]} [:!OTS#Pright_shift         [:!OTSright_shift]]
    ^{:doc/actions [{}]} [:!OTS#Pright_option        [:!OTSright_option]]
    ^{:doc/actions [{}]} [:!OTS#Pright_command       [:!OTSright_command]]
    ^{:doc/actions [{}]} [:!OTS#Pspacebar            [:!OTSspacebar]]

  ; numeric glyphs
    ^{:doc/actions [{}]} [:!OT#P1 [:!OT1]]
    ^{:doc/actions [{}]} [:!OT#P2 [:!OT2]]
    ^{:doc/actions [{}]} [:!OT#P3 [:!OT3]]
    ^{:doc/actions [{}]} [:!OT#P4 [:!OT4]]
    ^{:doc/actions [{}]} [:!OT#P5 [:!OT5]]
    ^{:doc/actions [{}]} [:!OT#P6 [:!OT6]]
    ^{:doc/actions [{}]} [:!OT#P7 [:!OT7]]
    ^{:doc/actions [{}]} [:!OT#P8 [:!OT8]]
    ^{:doc/actions [{}]} [:!OT#P9 [:!OT9]]
    ^{:doc/actions [{}]} [:!OT#P0 [:!OT0]]
    ^{:doc/actions [{}]} [:!OT#Phyphen [:!OThyphen]]
    ^{:doc/actions [{}]} [:!OT#Pequal_sign [:!OTequal_sign]]

    ^{:doc/actions [{}]} [:!OTS#P1 [:!OTS1]]
    ^{:doc/actions [{}]} [:!OTS#P2 [:!OTS2]]
    ^{:doc/actions [{}]} [:!OTS#P3 [:!OTS3]]
    ^{:doc/actions [{}]} [:!OTS#P4 [:!OTS4]]
    ^{:doc/actions [{}]} [:!OTS#P5 [:!OTS5]]
    ^{:doc/actions [{}]} [:!OTS#P6 [:!OTS6]]
    ^{:doc/actions [{}]} [:!OTS#P7 [:!OTS7]]
    ^{:doc/actions [{}]} [:!OTS#P8 [:!OTS8]]
    ^{:doc/actions [{}]} [:!OTS#P9 [:!OTS9]]
    ^{:doc/actions [{}]} [:!OTS#P0 [:!OTS0]]
    ^{:doc/actions [{}]} [:!OTS#Phyphen [:!OTShyphen]]
    ^{:doc/actions [{}]} [:!OTS#Pequal_sign [:!OTSequal_sign]]

  ; alphabetic glyphs
    ^{:doc/actions [{}]} [:!OT#Pa [:!OTa]]
    ^{:doc/actions [{}]} [:!OT#Pb [:!OTb]]
    ^{:doc/actions [{}]} [:!OT#Pc [:!OTc]]
    ^{:doc/actions [{}]} [:!OT#Pd [:!OTd]]
    ^{:doc/actions [{}]} [:!OT#Pe [:!OTe]]
    ^{:doc/actions [{}]} [:!OT#Pf [:!OTf]]
    ^{:doc/actions [{}]} [:!OT#Pg [:!OTg]]
    ^{:doc/actions [{}]} [:!OT#Ph [:!OTh]]
    ^{:doc/actions [{}]} [:!OT#Pi [:!OTi]]
    ^{:doc/actions [{}]} [:!OT#Pj [:!OTj]]
    ^{:doc/actions [{}]} [:!OT#Pk [:!OTk]]
    ^{:doc/actions [{}]} [:!OT#Pl [:!OTl]]
    ^{:doc/actions [{}]} [:!OT#Pm [:!OTm]]
    ^{:doc/actions [{}]} [:!OT#Pn [:!OTn]]
    ^{:doc/actions [{}]} [:!OT#Po [:!OTo]]
    ^{:doc/actions [{}]} [:!OT#Pp [:!OTp]]
    ^{:doc/actions [{}]} [:!OT#Pq [:!OTq]]
    ^{:doc/actions [{}]} [:!OT#Pr [:!OTr]]
    ^{:doc/actions [{}]} [:!OT#Ps [:!OTs]]
    ^{:doc/actions [{}]} [:!OT#Pt [:!OTt]]
    ^{:doc/actions [{}]} [:!OT#Pu [:!OTu]]
    ^{:doc/actions [{}]} [:!OT#Pv [:!OTv]]
    ^{:doc/actions [{}]} [:!OT#Pw [:!OTw]]
    ^{:doc/actions [{}]} [:!OT#Px [:!OTx]]
    ^{:doc/actions [{}]} [:!OT#Py [:!OTy]]
    ^{:doc/actions [{}]} [:!OT#Pz [:!OTz]]
    ^{:doc/actions [{}]} [:!OT#Pright_control [:!OTright_control]]

    ^{:doc/actions [{}]} [:!OTS#Pa [:!OTSa]]
    ^{:doc/actions [{}]} [:!OTS#Pb [:!OTSb]]
    ^{:doc/actions [{}]} [:!OTS#Pc [:!OTSc]]
    ^{:doc/actions [{}]} [:!OTS#Pd [:!OTSd]]
    ^{:doc/actions [{}]} [:!OTS#Pe [:!OTSe]]
    ^{:doc/actions [{}]} [:!OTS#Pf [:!OTSf]]
    ^{:doc/actions [{}]} [:!OTS#Pg [:!OTSg]]
    ^{:doc/actions [{}]} [:!OTS#Ph [:!OTSh]]
    ^{:doc/actions [{}]} [:!OTS#Pi [:!OTSi]]
    ^{:doc/actions [{}]} [:!OTS#Pj [:!OTSj]]
    ^{:doc/actions [{}]} [:!OTS#Pk [:!OTSk]]
    ^{:doc/actions [{}]} [:!OTS#Pl [:!OTSl]]
    ^{:doc/actions [{}]} [:!OTS#Pm [:!OTSm]]
    ^{:doc/actions [{}]} [:!OTS#Pn [:!OTSn]]
    ^{:doc/actions [{}]} [:!OTS#Po [:!OTSo]]
    ^{:doc/actions [{}]} [:!OTS#Pp [:!OTSp]]
    ^{:doc/actions [{}]} [:!OTS#Pq [:!OTSq]]
    ^{:doc/actions [{}]} [:!OTS#Pr [:!OTSr]]
    ^{:doc/actions [{}]} [:!OTS#Ps [:!OTSs]]
    ^{:doc/actions [{}]} [:!OTS#Pt [:!OTSt]]
    ^{:doc/actions [{}]} [:!OTS#Pu [:!OTSu]]
    ^{:doc/actions [{}]} [:!OTS#Pv [:!OTSv]]
    ^{:doc/actions [{}]} [:!OTS#Pw [:!OTSw]]
    ^{:doc/actions [{}]} [:!OTS#Px [:!OTSx]]
    ^{:doc/actions [{}]} [:!OTS#Py [:!OTSy]]
    ^{:doc/actions [{}]} [:!OTS#Pz [:!OTSz]]
    ^{:doc/actions [{}]} [:!OTS#Pright_control [:!OTSright_control]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
