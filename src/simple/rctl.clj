;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "rctl.edn")

(defn rctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{}]} [:!W#Pleft_arrow  [:!Wleft_arrow]  [:term]]
    ^{:doc/actions [{}]} [:!W#Pright_arrow [:!Wright_arrow] [:term]]
    ^{:doc/actions [{}]} [:!W#Pup_arrow    [:!Wup_arrow]    [:term]]
    ^{:doc/actions [{}]} [:!W#Pdown_arrow  [:!Wdown_arrow]  [:term]]

    ^{:doc/actions [{}]} [:!WS#Pleft_arrow  [:!WSleft_arrow]  [:term]]
    ^{:doc/actions [{}]} [:!WS#Pright_arrow [:!WSright_arrow] [:term]]
    ^{:doc/actions [{}]} [:!WS#Pup_arrow    [:!WSup_arrow]    [:term]]
    ^{:doc/actions [{}]} [:!WS#Pdown_arrow  [:!WSdown_arrow]  [:term]]

  ; technical glyphs
    ^{:doc/actions [{}]} [:!W#Popen_bracket   [:!Wopen_bracket]]
    ^{:doc/actions [{}]} [:!W#Pclose_bracket  [:!Wclose_bracket]]
    ^{:doc/actions [{}]} [:!W#Psemicolon      [:!Wsemicolon]]
    ^{:doc/actions [{}]} [:!W#Pquote          [:!Wquote]]
    ^{:doc/actions [{}]} [:!W#Pbackslash      [:!Wbackslash]]
    ^{:doc/actions [{}]} [:!W#Pcomma          [:!Wcomma]]
    ^{:doc/actions [{}]} [:!W#Pperiod         [:!Wperiod]]
    ^{:doc/actions [{}]} [:!W#Pslash          [:!Wslash]]

    ^{:doc/actions [{}]} [:!WS#Popen_bracket   [:!WSopen_bracket]]
    ^{:doc/actions [{}]} [:!WS#Pclose_bracket  [:!WSclose_bracket]]
    ^{:doc/actions [{}]} [:!WS#Psemicolon      [:!WSsemicolon]]
    ^{:doc/actions [{}]} [:!WS#Pquote          [:!WSquote]]
    ^{:doc/actions [{}]} [:!WS#Pbackslash      [:!WSbackslash]]
    ^{:doc/actions [{}]} [:!WS#Pcomma          [:!WScomma]]
    ^{:doc/actions [{}]} [:!WS#Pperiod         [:!WSperiod]]
    ^{:doc/actions [{}]} [:!WS#Pslash          [:!WSslash]]

  ; action glyphs
    ^{:doc/actions [{:action "changed file picker", :exec ["changed_file_picker"], :program "helix-common"}
                    {:action "changed file picker", :exec ["OpenFile"],            :program "micro"}]} [:!W#Pdelete_or_backspace [:!Tr] [:term]]
    ^{:doc/actions [{:action "file picker",         :exec ["file_picker"],         :program "helix-common"}
                    {:action "file picker",         :exec ["AddTab,OpenFile"],     :program "micro"}]} [:!W#Preturn_or_enter     [:!Ts] [:term]]
    ^{:doc/actions [{:action "buffer picker",       :exec ["buffer_picker"],       :program "helix-common"}
                    {:action "buffer picker",       :exec ["CommandMode"],         :program "micro"}]} [:!W#Pright_shift         [:!Tt] [:term]]
    ^{:doc/actions [{}]} [:!W#Pright_option  [:!Wright_option]]
    ^{:doc/actions [{}]} [:!W#Pright_command [:!Wright_command]]
    ^{:doc/actions [{}]} [:!W#Pspacebar      [:!Wspacebar]]

    ^{:doc/actions [{}]} [:!WS#Pdelete_or_backspace [:!WSdelete_or_backspace]]
    ^{:doc/actions [{}]} [:!WS#Preturn_or_enter     [:!WSreturn_or_enter]]
    ^{:doc/actions [{}]} [:!WS#Pright_shift         [:!WSright_shift]]
    ^{:doc/actions [{}]} [:!WS#Pright_option        [:!WSright_option]]
    ^{:doc/actions [{}]} [:!WS#Pright_command       [:!WSright_command]]
    ^{:doc/actions [{}]} [:!WS#Pspacebar            [:!WSspacebar]]

  ; numeric glyphs
    ^{:doc/actions [{}]} [:!W#P1 [:!W1]]
    ^{:doc/actions [{}]} [:!W#P2 [:!W2]]
    ^{:doc/actions [{}]} [:!W#P3 [:!W3]]
    ^{:doc/actions [{}]} [:!W#P4 [:!W4]]
    ^{:doc/actions [{}]} [:!W#P5 [:!W5]]
    ^{:doc/actions [{}]} [:!W#P6 [:!W6]]
    ^{:doc/actions [{}]} [:!W#P7 [:!W7]]
    ^{:doc/actions [{}]} [:!W#P8 [:!W8]]
    ^{:doc/actions [{}]} [:!W#P9 [:!W9]]
    ^{:doc/actions [{}]} [:!W#P0 [:!W0]]
    ^{:doc/actions [{}]} [:!W#Phyphen [:!Whyphen]]
    ^{:doc/actions [{}]} [:!W#Pequal_sign [:!Wequal_sign]]

    ^{:doc/actions [{}]} [:!WS#P1 [:!WS1]]
    ^{:doc/actions [{}]} [:!WS#P2 [:!WS2]]
    ^{:doc/actions [{}]} [:!WS#P3 [:!WS3]]
    ^{:doc/actions [{}]} [:!WS#P4 [:!WS4]]
    ^{:doc/actions [{}]} [:!WS#P5 [:!WS5]]
    ^{:doc/actions [{}]} [:!WS#P6 [:!WS6]]
    ^{:doc/actions [{}]} [:!WS#P7 [:!WS7]]
    ^{:doc/actions [{}]} [:!WS#P8 [:!WS8]]
    ^{:doc/actions [{}]} [:!WS#P9 [:!WS9]]
    ^{:doc/actions [{}]} [:!WS#P0 [:!WS0]]
    ^{:doc/actions [{}]} [:!WS#Phyphen [:!WShyphen]]
    ^{:doc/actions [{}]} [:!WS#Pequal_sign [:!WSequal_sign]]

  ; alphabetic glyphs
    ^{:doc/actions [{}]} [:!W#Pa [:!Wa]]
    ^{:doc/actions [{}]} [:!W#Pb [:!Wb]]
    ^{:doc/actions [{}]} [:!W#Pc [:!Wc]]
    ^{:doc/actions [{}]} [:!W#Pd [:!Wd]]
    ^{:doc/actions [{}]} [:!W#Pe [:!We]]
    ^{:doc/actions [{}]} [:!W#Pf [:!Wf]]
    ^{:doc/actions [{}]} [:!W#Pg [:!Wg]]
    ^{:doc/actions [{}]} [:!W#Ph [:!Wh]]
    ^{:doc/actions [{}]} [:!W#Pi [:!Wi]]
    ^{:doc/actions [{}]} [:!W#Pj [:!Wj]]
    ^{:doc/actions [{}]} [:!W#Pk [:!Wk]]
    ^{:doc/actions [{}]} [:!W#Pl [:!Wl]]
    ^{:doc/actions [{}]} [:!W#Pm [:!Wm]]
    ^{:doc/actions [{}]} [:!W#Pn [:!Wn]]
    ^{:doc/actions [{}]} [:!W#Po [:!Wo]]
    ^{:doc/actions [{}]} [:!W#Pp [:!Wp]]
    ^{:doc/actions [{}]} [:!W#Pq [:!Wq]]
    ^{:doc/actions [{}]} [:!W#Pr [:!Wr]]
    ^{:doc/actions [{}]} [:!W#Ps [:!Ws]]
    ^{:doc/actions [{}]} [:!W#Pt [:!Wt]]
    ^{:doc/actions [{}]} [:!W#Pu [:!Wu]]
    ^{:doc/actions [{}]} [:!W#Pv [:!Wv]]
    ^{:doc/actions [{}]} [:!W#Pw [:!Ww]]
    ^{:doc/actions [{}]} [:!W#Px [:!Wx]]
    ^{:doc/actions [{}]} [:!W#Py [:!Wy]]
    ^{:doc/actions [{}]} [:!W#Pz [:!Wz]]
  ; ^{:doc/actions [{}]} [:!W#Pright_control [:!Wright_control]]

    ^{:doc/actions [{}]} [:!WS#Pa [:!WSa]]
    ^{:doc/actions [{}]} [:!WS#Pb [:!WSb]]
    ^{:doc/actions [{}]} [:!WS#Pc [:!WSc]]
    ^{:doc/actions [{}]} [:!WS#Pd [:!WSd]]
    ^{:doc/actions [{}]} [:!WS#Pe [:!WSe]]
    ^{:doc/actions [{}]} [:!WS#Pf [:!WSf]]
    ^{:doc/actions [{}]} [:!WS#Pg [:!WSg]]
    ^{:doc/actions [{}]} [:!WS#Ph [:!WSh]]
    ^{:doc/actions [{}]} [:!WS#Pi [:!WSi]]
    ^{:doc/actions [{}]} [:!WS#Pj [:!WSj]]
    ^{:doc/actions [{}]} [:!WS#Pk [:!WSk]]
    ^{:doc/actions [{}]} [:!WS#Pl [:!WSl]]
    ^{:doc/actions [{}]} [:!WS#Pm [:!WSm]]
    ^{:doc/actions [{}]} [:!WS#Pn [:!WSn]]
    ^{:doc/actions [{}]} [:!WS#Po [:!WSo]]
    ^{:doc/actions [{}]} [:!WS#Pp [:!WSp]]
    ^{:doc/actions [{}]} [:!WS#Pq [:!WSq]]
    ^{:doc/actions [{}]} [:!WS#Pr [:!WSr]]
    ^{:doc/actions [{}]} [:!WS#Ps [:!WSs]]
    ^{:doc/actions [{}]} [:!WS#Pt [:!WSt]]
    ^{:doc/actions [{}]} [:!WS#Pu [:!WSu]]
    ^{:doc/actions [{}]} [:!WS#Pv [:!WSv]]
    ^{:doc/actions [{}]} [:!WS#Pw [:!WSw]]
    ^{:doc/actions [{}]} [:!WS#Px [:!WSx]]
    ^{:doc/actions [{}]} [:!WS#Py [:!WSy]]
    ^{:doc/actions [{}]} [:!WS#Pz [:!WSz]]
  ; ^{:doc/actions [{}]} [:!WS#Pright_control [:!WSright_control]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
