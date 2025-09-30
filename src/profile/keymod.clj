;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; KEY MOD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns profile.keymod
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "keymod.edn")

(def hc-collapse             ["collapse_selection", "keep_primary_selection"])
(def hi-normal               ["normal_mode"])
(def hn-insert               ["insert_mode"])
(def hs-normal               ["normal_mode"])
(def zp-escape               ["UndoRenamePane; SwitchToMode \"Normal\";"])
(def zt-escape               ["UndoRenameTab; SwitchToMode \"Normal\";"])
(def zx-escape               ["SwitchToMode \"Normal\";"])

(defn keymod []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ; key modifiers
  {:des "Modifier key remappings"
   :rules
   [; quit
    [:!C#Pq [:!Cq ["command-q" 0]] ["command-q" 1]]
    [:!C#Pq ["command-q" 1] nil {:delayed {:invoked ["command-q" 0] :canceled ["commandq" 0]}}]

    ; esc
    ^{:doc/actions [{:program c/alf,   :action "menu search"}]}                                    [c/kp_esc  [c/kew_rs]  nil    {:alone [c/kewp_us]}]

    ; zero
    ^{:doc/actions [{:program c/sys,   :action "mouse mode"}]}                                     [c/kp_lock [c/keq_rs]  nil    {:alone [c/k_lock]}]

    ; launcher
    ^{:doc/actions [{:program c/sys,   :action "tab"}]}                                            [c/kp_tab  [c/kotc_rs] nil    {:alone [c/k_tab]}]

    ; joker
    ^{:doc/actions [{:program c/hc,    :action "collapse selection", :exec hc-collapse}
                    {:program c/zp,    :action "zellij escape",      :exec zp-escape}
                    {:program c/zt,    :action "zellij escape",      :exec zt-escape}
                    {:program c/zx,    :action "zellij escape",      :exec zx-escape}]}            [c/kh_caps [c/kew_rc]  nil    {:alone [c/k_esc]}]

    ; grave
    ^{:doc/actions [{:program c/hi,    :action "mode normal",        :exec hi-normal}
                    {:program c/hn,    :action "mode insert",        :exec hn-insert}
                    {:program c/hs,    :action "mode normal",        :exec hs-normal}]}            [c/kp_rt   [c/k_rt]    nil    {:alone [:f13]}]

    ; lefts
    ^{:doc/actions [{:program c/alf,   :action "window switcher"}]}                                [c/kp_lt   [c/k_lt]    nil    {:alone [c/kewqp_us]}]
    ^{:doc/actions [{:program c/sys,   :action "global in-app window cycler"}]}                    [c/kp_lo   [c/k_lo]    nil    {:alone [c/ko_tab]}]
    ^{:doc/actions [{:program c/sys,   :action "switch most recent app"}]}                         [c/kp_lc   [c/k_lc]    nil    {:alone [c/kc_tab]}]

    ; rights
    ^{:doc/actions [{:program c/zj,    :action "history edit"}]}                                   [c/kp_rs   [c/k_rs]    c/term {:alone [c/ko_a]}]
    ^{:doc/actions [{:program c/sys,   :action "browser input / term edit"}]}                      [c/kp_rs   [c/k_rs]    nil    {:alone [c/krp_us]}]
    ^{:doc/actions [{:program c/alf,   :action "paste"}]}                                          [c/kp_ro   [c/k_ro]    nil    {:alone [c/kep_us]}]
    ^{:doc/actions [{:program c/alf,   :action "clipboard select"}]}                               [c/kp_rc   [c/k_rc]    nil    {:alone [c/kqp_us]}]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (keymod)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
