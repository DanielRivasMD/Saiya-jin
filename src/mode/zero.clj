;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; ZERO
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.zero
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]
))

(def out-file "zero.edn")

; (def zj-swap-tab-left        ["MoveTab \"Left\";"])
; (def zj-swap-tab-right       ["MoveTab \"Right\";"])
(def ze-size-inc             ["Resize \"Increase\";"])
(def ze-size-dec             ["Resize \"Decrease\";"])
(def zj-toggle-pin           ["TogglePanePinned;"])
(def zj-toggle-float         ["ToggleFloatingPanes;"])
(def zj-entersearch-mode     ["SwitchToMode \"EnterSearch\"; SearchInput 0;"])
(def zj-toggle-embed         ["TogglePaneEmbedOrFloating;"])
(def zj-locked-mode          ["SwitchToMode \"Locked\";"])
(def z-normal-mode           ["SwitchToMode \"Normal\";"])

(defn zero []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Zero Mode"
   :rules
   [:zero-mode
    ; arrow glyphs
    ; ^{:doc/actions [{:program c/zj,    :action "page up",            :exec zj-swap-tab-left}]}     [r/kp_al       [f/kts_f1]     c/term]
    ; ^{:doc/actions [{:program c/zj,    :action "page down",          :exec zj-swap-tab-right}]}    [r/kp_ar       [f/kts_f2]     c/term]
    ^{:doc/actions [{:program c/ze,    :action "home",               :exec ze-size-inc}]}          [r/kp_au       [f/kts_f4]     c/term]
    ^{:doc/actions [{:program c/ze,    :action "end",                :exec ze-size-dec}]}          [r/kp_ad       [f/kts_f5]     c/term]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kp_ob       [t/k_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cb       [t/k_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sc       [t/k_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kp_qu       [t/k_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kp_bl       [t/k_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cm       [t/k_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kp_pe       [t/k_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sl       [t/k_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj, :action "pane float pin",    :exec zj-toggle-pin}]}            [a/kp_db       [f/kts_f6]     c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float toggle", :exec zj-toggle-float}]}          [a/kp_re       [f/kts_f7]     c/term]
    ^{:doc/actions [{:program c/zj, :action "mode search",       :exec zj-entersearch-mode}
                    {:program c/ze, :action "mode normal",       :exec z-normal-mode}]}            [a/kp_rs       [f/kts_f8]     c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float pop",    :exec zj-toggle-embed}]}          [a/kp_ro       [f/kts_f9]     c/term]
    ^{:doc/actions [{}]}                                                                           [a/kp_rc       [f/kts_f10]]
    ^{:doc/actions [{:program c/zj, :action "mode lock",         :exec zj-locked-mode}
                    {:program c/zl, :action "mode normal",       :exec z-normal-mode}]}            [a/kp_sp       [f/kts_f11]    c/term]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kp_1        [n/k_1]]
    ^{:doc/actions [{}]}                                                                           [n/kp_2        [n/k_2]]
    ^{:doc/actions [{}]}                                                                           [n/kp_3        [n/k_3]]
    ^{:doc/actions [{}]}                                                                           [n/kp_4        [n/k_4]]
    ^{:doc/actions [{}]}                                                                           [n/kp_5        [n/k_5]]
    ^{:doc/actions [{}]}                                                                           [n/kp_6        [n/k_6]]
    ^{:doc/actions [{}]}                                                                           [n/kp_7        [n/k_7]]
    ^{:doc/actions [{}]}                                                                           [n/kp_8        [n/k_8]]
    ^{:doc/actions [{}]}                                                                           [n/kp_9        [n/k_9]]
    ^{:doc/actions [{}]}                                                                           [n/kp_0        [n/k_0]]
    ^{:doc/actions [{}]}                                                                           [n/kp_hy       [n/k_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kp_eq       [n/k_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kp_a        [b/k_a]]
    ^{:doc/actions [{}]}                                                                           [b/kp_b        [b/k_b]]
    ^{:doc/actions [{}]}                                                                           [b/kp_c        [b/k_c]]
    ^{:doc/actions [{}]}                                                                           [b/kp_d        [b/k_d]]
    ^{:doc/actions [{}]}                                                                           [b/kp_e        [b/k_e]]
    ^{:doc/actions [{}]}                                                                           [b/kp_f        [b/k_f]]
    ^{:doc/actions [{}]}                                                                           [b/kp_g        [b/k_g]]
    ^{:doc/actions [{}]}                                                                           [b/kp_h        [b/k_h]]
    ^{:doc/actions [{}]}                                                                           [b/kp_i        [b/k_i]]
    ^{:doc/actions [{}]}                                                                           [b/kp_j        [b/k_j]]
    ^{:doc/actions [{}]}                                                                           [b/kp_k        [b/k_k]]
    ^{:doc/actions [{}]}                                                                           [b/kp_l        [b/k_l]]
    ^{:doc/actions [{}]}                                                                           [b/kp_m        [b/k_m]]
    ^{:doc/actions [{}]}                                                                           [b/kp_n        [b/k_n]]
    ^{:doc/actions [{}]}                                                                           [b/kp_o        [b/k_o]]
    ^{:doc/actions [{}]}                                                                           [b/kp_p        [b/k_p]]
    ^{:doc/actions [{}]}                                                                           [b/kp_q        [b/k_q]]
    ^{:doc/actions [{}]}                                                                           [b/kp_r        [b/k_r]]
    ^{:doc/actions [{}]}                                                                           [b/kp_s        [b/k_s]]
    ^{:doc/actions [{}]}                                                                           [b/kp_t        [b/k_t]]
    ^{:doc/actions [{}]}                                                                           [b/kp_u        [b/k_u]]
    ^{:doc/actions [{}]}                                                                           [b/kp_v        [b/k_v]]
    ^{:doc/actions [{}]}                                                                           [b/kp_w        [b/k_w]]
    ^{:doc/actions [{}]}                                                                           [b/kp_x        [b/k_x]]
    ^{:doc/actions [{}]}                                                                           [b/kp_y        [b/k_y]]
    ^{:doc/actions [{}]}                                                                           [b/kp_z        [b/k_z]]
    ^{:doc/actions [{}]}                                                                           [b/kp_rt       [b/k_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (zero)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
